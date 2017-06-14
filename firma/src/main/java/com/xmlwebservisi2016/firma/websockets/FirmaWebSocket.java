package com.xmlwebservisi2016.firma.websockets;

import com.xmlwebservisi2016.firma.dto.FaktureDTO;
import com.xmlwebservisi2016.firma.dto.ZaglavljeStavkeDTO;
import com.xmlwebservisi2016.firma.model.database_entities.Firma;
import com.xmlwebservisi2016.firma.model.database_entities.Stavka;
import com.xmlwebservisi2016.firma.model.database_entities.User;
import com.xmlwebservisi2016.firma.model.database_entities.Zaglavlje;
import com.xmlwebservisi2016.firma.service.StavkaService;
import com.xmlwebservisi2016.firma.service.UserService;
import com.xmlwebservisi2016.firma.service.ZaglavljeService;
import com.xmlwebservisi2016.firma.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Svetozar Stojkovic on 6/12/2017.
 */
@ServerEndpoint("/firma/{userId}")
public class FirmaWebSocket {

    // for more info about this - https://blog.idrsolutions.com/2013/12/websockets-an-introduction/

    private static Map<String, Session> sessions = new HashMap<>();

    @Autowired
    private UserService userService;

    @Autowired
    private ZaglavljeService zaglavljeService;

    @Autowired
    private StavkaService stavkaService;

    /**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was
     * successful.
     */
    @OnOpen
    public void onOpen(String userId, Session session){

        sessions.put(userId, session);

        faktureZaPotvrdu(userId);
        potvrdjeneFakture(userId);

        System.out.println(session.getId() + " has opened a connection");
//        try {
//            session.getBasicRemote().sendText(Converter.getJSONString(fakture));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }

    /**
     * When a user sends a message to the server, this method will intercept the message
     * and allow us to react to it. For now the message is read as a String.
     */
    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("Message from " + session.getId() + ": " + message);
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * The user closes the connection.
     *
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session session){
        for (String userId : sessions.keySet()) {
            if (sessions.get(userId) == session) {
                sessions.remove(userId);
                break;
            }
        }
        System.out.println("Session " +session.getId()+" has ended");
    }

    private void faktureZaPotvrdu(String userId) {
        User foundUser = userService.findById(Long.parseLong(userId));

        Firma firma = foundUser.getFirma();

        List<Zaglavlje> zaglavljaUToku = zaglavljeService.findByPibDobavljacaAndZavrsenoIsFalse(firma.getPib()); // TODO [SVS] fix this

        List<ZaglavljeStavkeDTO> zaglavljeStavkeDTOS = new ArrayList<>();

        for (Zaglavlje zaglavlje : zaglavljaUToku) {
            List <Stavka> stavke = stavkaService.findByZaglavlje(zaglavlje);
            zaglavljeStavkeDTOS.add(new ZaglavljeStavkeDTO(zaglavlje, stavke));
        }

        FaktureDTO webSocketFaktureDTO = new FaktureDTO();
        webSocketFaktureDTO.setZaglavljeStavkeDTOS(zaglavljeStavkeDTOS);
        webSocketFaktureDTO.setTip("ZA_POTVRDU");

        Session session = sessions.get(userId);
        if (session != null && session.isOpen()) {
            try {
                session.getBasicRemote().sendText(Converter.getJSONString(webSocketFaktureDTO));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void potvrdjeneFakture(String userId) {
        User foundUser = userService.findById(Long.parseLong(userId));

        Firma firma = foundUser.getFirma();

        List<Zaglavlje> zaglavljaUToku = zaglavljeService.findByPibKupcaAndPotvrdjenoIsFalse(firma.getPib());

        List<ZaglavljeStavkeDTO> zaglavljeStavkeDTOS = new ArrayList<>();

        for (Zaglavlje zaglavlje : zaglavljaUToku) {
            List <Stavka> stavke = stavkaService.findByZaglavlje(zaglavlje);
            zaglavljeStavkeDTOS.add(new ZaglavljeStavkeDTO(zaglavlje, stavke));
        }

        FaktureDTO webSocketFaktureDTO = new FaktureDTO();
        webSocketFaktureDTO.setZaglavljeStavkeDTOS(zaglavljeStavkeDTOS);
        webSocketFaktureDTO.setTip("POTVRDJENE");

        Session session = sessions.get(userId);
        if (session != null && session.isOpen()) {
            try {
                session.getBasicRemote().sendText(Converter.getJSONString(webSocketFaktureDTO));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void displayMessageToActiveUsers() {
        System.out.println("Displaying messages to active users");
        for (String userId : sessions.keySet()) {
            faktureZaPotvrdu(userId);
            potvrdjeneFakture(userId);
        }
    }
}
