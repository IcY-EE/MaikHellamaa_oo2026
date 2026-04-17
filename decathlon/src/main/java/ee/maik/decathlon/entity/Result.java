package ee.maik.decathlon.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String discipline; // Spordiala (nt 100m)
    private double value;      // Tulemus (nt 12.5)
    private int points;        // Punktid (arvutatakse back-endis)

    @ManyToOne
    private Athlete athlete;   // Seos sportlasega
}