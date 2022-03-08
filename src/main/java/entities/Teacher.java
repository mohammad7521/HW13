package entities;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Teacher {


    @Id
    private Long TeacherID;


    private String firstName;
    private String lastName;
    private String userName;
    private String password;


    @ManyToMany (mappedBy = "teacherLessonList")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Lesson> lessonList;
}
