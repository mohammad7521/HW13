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
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer termId;


    @OneToMany (mappedBy = "masterStudentTerm")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<StudentTerm> termStudent;



    @OneToMany (mappedBy = "masterTeacherTerm")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<TeacherTerm> termTeacher;


    public void addStudentTerm(StudentTerm st){
        termStudent.add(st);
    }


    public void addTeacherTerm(TeacherTerm tt){
        termTeacher.add(tt);
    }


    @Override
    public String toString() {
        return "Term{" +
                "termId=" + termId +
                '}';
    }
}
