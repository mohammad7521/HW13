package entities;

import lombok.*;
import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Lesson {


    @Id
    private Long lessonId;

    private String name;
    private int unit;
    private int grade;

    @ManyToMany
    @JoinTable(name="lesson_student",
            joinColumns = @JoinColumn(name="lessonId"),
            inverseJoinColumns = @JoinColumn(name = "studentId"))
    private List<Student> studentLessonList;

    @ManyToMany
    @JoinTable(name="lesson_teacher",
            joinColumns = @JoinColumn(name="lessonId"),
            inverseJoinColumns = @JoinColumn(name = "teacherId"))
    private List<Teacher> teacherLessonList;

    @ManyToMany
    @JoinTable(name="lesson_term",
            joinColumns = @JoinColumn(name="lessonId"),
            inverseJoinColumns = @JoinColumn(name = "termId"))
    private List<Term> termLessonList;
}
