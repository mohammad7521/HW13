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
    private Long studentId;

    private String firstName;
    private String lastName;
    private String username;
    private String password;



    @ManyToMany
    @JoinTable(name="student_lesson",
            joinColumns = @JoinColumn(name="studentId"),
            inverseJoinColumns = @JoinColumn(name = "lessonId"))
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Lesson> studentLessonList;
}
