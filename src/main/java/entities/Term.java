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



    @ManyToMany (mappedBy = "studentTerm")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Student> termStudent;



    @ManyToMany (mappedBy = "teacherTerm")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Teacher> termTeacher;


    @ManyToMany
    @JoinTable(name="student_term_lesson",
            joinColumns = @JoinColumn(name="student_termId"),
            inverseJoinColumns = @JoinColumn(name = "lessonId"))
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Lesson> student_term_lesson;


    @ManyToMany
    @JoinTable(name="teacher_term_lesson",
            joinColumns = @JoinColumn(name="teacher_termId"),
            inverseJoinColumns = @JoinColumn(name = "lessonId"))
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Lesson> teacher_term_lesson;

    //assign lessons to a term of a student
    public void addStudentLessons(Lesson lesson){
        student_term_lesson.add(lesson);
    }
}
