package com.xmlwebservisi2016.firma.repository;

import com.xmlwebservisi2016.firma.model.database_entities.Firma;
import com.xmlwebservisi2016.firma.model.database_entities.Proizvod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/10/2017.
 */
@Repository
public interface ProizvodiRepository extends JpaRepository<Proizvod, Long>{

    Proizvod findByNazivAndFirma(String naziv, Firma firma);

    List<Proizvod> findAll();

    List<Proizvod> findByFirma(Firma firma);

}
