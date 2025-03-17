import java.util.*;
import java.util.stream.Collectors;
class Student {
    String name;
    double marks;

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }
}
public class StudentFilter {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("John", 80),
            new Student("Emma", 90),
            new Student("Mike", 70),
            new Student("Sophia", 85)
        );
        List<String> topStudents = students.stream()
            .filter(s -> s.marks > 75)
            .sorted(Comparator.comparingDouble(s -> -s.marks)) 
            .map(s -> s.name)
            .collect(Collectors.toList());
        topStudents.forEach(System.out::println);
    }
}
