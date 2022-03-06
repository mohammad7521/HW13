package entities;

import lombok.*;
import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
//
//    @ManyToMany
//    private List<Lesson> lessonList;
}
