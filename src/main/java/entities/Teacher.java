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
public class Teacher {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TeacherID;


    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private double salary;
    private boolean isBoardMember;



    @ManyToMany
    @JoinTable(name="teacher_lesson",
            joinColumns = @JoinColumn(name="teacherId"),
            inverseJoinColumns = @JoinColumn(name = "lessonId"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Lesson> teacherLessonList;
}
