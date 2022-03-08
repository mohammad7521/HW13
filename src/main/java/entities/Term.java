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
public class Term {

    @Id
    private Long termId;

    @ManyToMany (mappedBy = "termLessonList")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Lesson> lessonList;
}
