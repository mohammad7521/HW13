package utils;

import entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSingleton {

    private HibernateSingleton() {}

    private static class LazyHolder {
        static SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder()
                    .configure() // goes and fetches configuration from hibernate.cfg.xml
                    .build();

            // registry is useful for creating SessionFactory
            // SessionFactory is a heavyweight object.
            // SessionFactory is thread safe.
            // SessionFactory is immutable.

            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(Lesson.class)
                    .addAnnotatedClass(Staff.class)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Teacher.class)
                    .addAnnotatedClass(StudentTerm.class)
                    .addAnnotatedClass(TeacherTerm.class)
                    .addAnnotatedClass(LessonTerm.class)
                    .addAnnotatedClass(Term.class)
                    .buildMetadata()
                    .buildSessionFactory();

        }
    }

    public static SessionFactory getInstance() {
        return LazyHolder.INSTANCE;
    }
}
