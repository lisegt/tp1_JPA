package monprojet.entity;

import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class City {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    @NonNull
    private String name;

    @Column(unique=true)
    @NonNull
    private int population;

    @ManyToOne(optional = false)
    private Country country;
}
