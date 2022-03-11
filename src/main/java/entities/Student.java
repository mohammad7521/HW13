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
    private String username;
    private String password;



    @ManyToMany
    @JoinTable(name="studentTerm",
            joinColumns = @JoinColumn(name="studentId"),
            inverseJoinColumns = @JoinColumn(name = "termID"))
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Term> studentTerm;


    public void addTerm(Term term){
        studentTerm.add(term);
    }

}
