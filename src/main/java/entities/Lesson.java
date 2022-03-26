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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Lesson  {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer lessonId;
    protected String name;
    protected int unit;


    @Override
    public String toString() {
        return "Lesson{" +
                "lessonId=" + lessonId +
                ", name='" + name + '\'' +
                ", unit=" + unit +
                '}';
    }
}
