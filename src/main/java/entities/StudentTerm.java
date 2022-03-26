package entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StudentTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    private Student termStudent;


    @OneToMany(mappedBy = "studentTermLesson")
    private List<LessonTerm> termStudentLesson;

    @Override
    public String toString() {
        return "StudentTerm{" +
                "id=" + id +
                ", termStudent=" + termStudent +
                ", termStudentLesson=" + termStudentLesson +
                '}';
    }

    @ManyToOne
    private Term masterStudentTerm;
}
