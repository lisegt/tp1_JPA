package monprojet.entity;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;

import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Proxy(lazy = false)
@Entity // Une entité JPA
public class Country {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @Column(unique=true)
    @NonNull
    private String code;
    
    @Column(unique=true)
    @NonNull
    private String name;

    @OneToMany(mappedBy = "country")
    @ToString.Exclude
    private List<City> cities = new ArrayList<>();

    /* PB : marche plus
    La méthode toString() de Country (générée par Lombok) affiche les villes, qui affichent leur pays, qui affiche leurs villes...
    On peut contrôler le toString() généré par Lombok

    // Lombok https://www.projectlombok.org/features/ToString
    @ToString.Exclude // On ne veut pas inclure la liste des villes dans le toString
    // Sinon récursivité infinie    
    private List<City> cities = new ArrayList<>();
    */
}
