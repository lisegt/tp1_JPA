package monprojet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import monprojet.dto.PopulationPays;
import monprojet.entity.Country;

// This will be AUTO IMPLEMENTED by Spring 
//

public interface CountryRepository extends JpaRepository<Country, Integer> {
    /**
     * Calcule la population d'un pays (somme des populations des villes)
     * @param idPays l'id du pays à traiter
     * @return la population du pays, sous la forme d'un entier
     */
    @Query(value="SELECT SUM(City.population) AS Population_Totale "
    + "FROM City "
    + "WHERE city.country_id = :idPays ",
    nativeQuery = true)
    public int sommePopulationPaysSQL(int idPays);
    
    /**
     * Calcule la population pour chaque pays
     * @return la population par pays, sous la forme d'une liste de DTO PopulationPays
     */
    @Query(value="SELECT Country.name as Nom_Du_Pays, SUM(City.population) AS Population "
    + "FROM City "
    + "INNER JOIN Country ON City.country_id = Country.id "
    + "GROUP BY Country.name "
    + "ORDER BY Country.id ",
    nativeQuery = true)
    public List<PopulationPays> populationParPaysSQL();


}
