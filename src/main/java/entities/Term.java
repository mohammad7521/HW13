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
    private Long termId;

    @ManyToMany
    @JoinTable(name="term_lesson",
            joinColumns = @JoinColumn(name="termId"),
            inverseJoinColumns = @JoinColumn(name = "lessonId"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Lesson> termLessonList;
}
