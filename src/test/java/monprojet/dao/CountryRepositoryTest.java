package monprojet.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import monprojet.dto.PopulationPays;
import monprojet.entity.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

@Log4j2 // Génère le 'logger' pour afficher les messages de trace
@DataJpaTest
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryDAO;

    @Test
    void lesNomsDePaysSontTousDifferents() {
        log.info("On vérifie que les noms de pays sont tous différents ('unique') dans la table 'Country'");
        
        Country paysQuiExisteDeja = new Country("XX", "France");
        try {
            countryDAO.save(paysQuiExisteDeja); // On essaye d'enregistrer un pays dont le nom existe   

            fail("On doit avoir une violation de contrainte d'intégrité (unicité)");
        } catch (DataIntegrityViolationException e) {
            // Si on arrive ici c'est normal, l'exception attendue s'est produite
        }
    }

    @Test
    @Sql("test-data.sql") // On peut charger des donnnées spécifiques pour un test
    void onSaitCompterLesEnregistrements() {
        log.info("On compte les enregistrements de la table 'Country'");
        int combienDePaysDansLeJeuDeTest = 3 + 1; // 3 dans data.sql, 1 dans test-data.sql
        long nombre = countryDAO.count();
        assertEquals(combienDePaysDansLeJeuDeTest, nombre, "On doit trouver 4 pays" );
    }

    @Test
    @Sql("test-data.sql") // On peut charger des donnnées spécifiques pour un test
    void populationDUnPays() {
        log.info("On compte la population d'un pays dont l'id est passé en paramètre");
        int populationEnItalie = countryDAO.sommePopulationPaysSQL(4); 
        assertEquals(19, populationEnItalie, "On doit trouver une population de 19" );
    }

    @Test
    @Sql("test-data.sql") // On peut charger des donnnées spécifiques pour un test
    void populationDeTousLesPays() {
        log.info("On compte la population de chaque pays");
        List<PopulationPays> populationPays = countryDAO.populationParPaysSQL(); 

        //on teste la population en France
        PopulationPays populationFrance = populationPays.get(0); //France : 1er pays de la table
        assertEquals(12, populationFrance.getPopulation(), "On doit trouver une population de 12");

        //on teste la population en Italie
        PopulationPays populationItalie = populationPays.get(3); //Italie : 4e pays de la table
        assertEquals(19, populationItalie.getPopulation(), "On doit trouver une population de 18");
    }

}
