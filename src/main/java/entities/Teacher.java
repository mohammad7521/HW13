package entities;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Teacher {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer TeacherID;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String username;
    private String password;
    private long salary;
    private boolean isBoardMember;



    @OneToMany(mappedBy = "termTeacher")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<TeacherTerm> teacherTerm;



    @Override
    public String toString() {
        return "Teacher{" +
                "TeacherID=" + TeacherID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salary=" + salary +
                ", isBoardMember=" + isBoardMember +
                '}';
    }
}
