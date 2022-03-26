package entities;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String username;
    private String password;



    @OneToMany(mappedBy = "termStudent")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<StudentTerm> studentTerm;


    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
//                ", studentTerm=" + studentTerm +
                '}';
    }


    public void addStudentTerm(StudentTerm st){
        studentTerm.add(st);
    }
}
