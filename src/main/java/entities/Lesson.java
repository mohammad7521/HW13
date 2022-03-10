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
public class Lesson {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;

    private String name;
    private int unit;
    private int grade;

    @ManyToMany (mappedBy = "studentLessonList")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Student> lessonStudent;


    @ManyToMany (mappedBy = "teacherLessonList")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Teacher> lessonTeacher;



    @ManyToMany (mappedBy = "termLessonList")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Term> lessonTerm;


}
