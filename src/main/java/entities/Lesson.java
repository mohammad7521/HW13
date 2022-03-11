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
    private Integer lessonId;

    private String name;
    private int unit;
    private int grade;

    @ManyToMany (mappedBy = "student_term_lesson")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Term> student_term_lesson;


    @ManyToMany (mappedBy = "teacher_term_lesson")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Term> teacher_term_lesson;






}
