package entities;

import lombok.*;
import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Teacher {


    @Id
    private int id;


    private String firstName;
    private String lastName;
    private String userName;
    private String password;
}
