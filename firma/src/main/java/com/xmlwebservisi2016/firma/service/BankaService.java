package com.xmlwebservisi2016.firma.service;

import com.xmlwebservisi2016.firma.model.database_entities.Firma;
import com.xmlwebservisi2016.firma.model.jaxb.izvod.Izvod;
import com.xmlwebservisi2016.firma.model.jaxb.prenos.NalogZaPrenos;
import com.xmlwebservisi2016.firma.model.jaxb.zahtev.ZahtevZaIzvod;

/**
 * Created by Hp on 6/13/2017.
 */

public interface BankaService {

    Izvod preuzimanjeIzvoda(ZahtevZaIzvod zahtev, Firma firma);

    boolean slanjeNalogaZaPlacanje(NalogZaPrenos nalog, Firma firma);

}
