package entities;

import lombok.*;
import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Teacher {


    @Id
    private Long id;


    private String firstName;
    private String lastName;
    private String userName;
    private String password;

//    @ManyToMany
//    private List<Lesson> lessonList;
}
