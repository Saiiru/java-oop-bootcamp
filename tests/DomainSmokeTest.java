import dominio.*;
import java.util.HashSet;
import java.util.Set;

public class DomainSmokeTest {
    public static void main(String[] args) {
        Bootcamp bootcamp = new Bootcamp();
        Curso course = new Curso();
        course.setTitle("Java");
        course.setDuration(2);
        bootcamp.getContent().add(course);
        Dev student = new Dev();
        student.setName("Learner");
        Set<Dev> students = new HashSet<>();
        students.add(student);
        student.subscribeBootcamp(bootcamp);
        if (!students.contains(student) || !bootcamp.getSubscribedDevs().contains(student)) {
            throw new AssertionError("Enrollment changed entity identity");
        }
        student.progression();
        student.setName("Renamed learner");
        if (!students.contains(student) || !bootcamp.getSubscribedDevs().contains(student)) {
            throw new AssertionError("Mutable fields broke HashSet membership");
        }
        if (student.calculateTotalXp() != 20.0) throw new AssertionError("Expected 20 XP");
        if (student.getSubscribedContent().size() != 0 || student.getCompletedContent().size() != 1) {
            throw new AssertionError("Progression should move course to completed set");
        }
        System.out.println("JAVA_OOP_DOMAIN_SMOKE_PASS");
    }
}
