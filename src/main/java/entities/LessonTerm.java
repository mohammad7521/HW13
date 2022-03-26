package entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class LessonTerm {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer lessonId;
    protected String name;
    protected int unit;
    private int grade;
    private boolean passed;


    @ManyToOne
    private StudentTerm studentTermLesson;


    @ManyToOne
    private TeacherTerm teacherTermLesson;



    @Override
    public String toString() {
        return "Lesson{" +
                "lessonId=" + lessonId +
                ", name='" + name + '\'' +
                ", unit=" + unit +
                ", grade=" + grade +
                ", passed=" + passed +
//                ", studentTermLesson=" + studentTermLesson +
//                ", teacherTermLesson=" + teacherTermLesson +
                '}';
    }
}
