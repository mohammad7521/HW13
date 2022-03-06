package entities;


import lombok.*;
import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Staff {

    @Id
    private int id;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
