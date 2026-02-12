package ee.maik.filmid.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "filmid")
@Data
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pealkiri;
    private String rezissoor;
    private Integer aasta;
}