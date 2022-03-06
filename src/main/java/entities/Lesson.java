package entities;

import lombok.*;
import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Lesson {


    @Id
    private Long id;

    private String name;
    private int unit;
    private int grade;
}
