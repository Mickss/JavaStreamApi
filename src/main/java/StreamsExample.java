import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsExample {

    List<Employee> employees = new ArrayList<>();

    @Before
    public void setUp() {
        Employee employee1 = new Employee("Beezlebub", "Rivendell", 25, List.of("Java", "PHP"));
        Employee employee2 = new Employee("Bandicoot", "Colonize", 33, List.of("JavaScript", "Python", "Java"));
        Employee employee3 = new Employee("Burberry", "Grackerdong", 45, List.of("C", "C++"));
        Employee employee4 = new Employee("Brandybuck", "Roochyrash", 19, List.of("Rust", "Python", "React"));
        Employee employee5 = new Employee("Cogglesnatch", "Candlestick", 31, List.of("Java", "Spring", "Hibernate"));
        Employee employee6 = new Employee("Bourgeoisie", "Kollywog", 21, List.of("Java", "Spring"));
        Employee employee7 = new Employee("Tiddleywomp", "Crackerdong", 28, List.of("Rust", "Java", "Python"));
        Employee employee8 = new Employee("Fragglerock", "Lolonize", 41, List.of("C#"));

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
        employees.add(employee6);
        employees.add(employee7);
        employees.add(employee8);

    }

    @Test
    public void firstStream() {
        employees.stream()
                .forEach(System.out::println);
    }

    @Test
    public void mapOperation() {
        employees.stream()
                .map(employee -> employee.getFirstName() + " " + employee.getLastName())
                .forEach(System.out::println);
    }

    @Test
    public void flatMapOperation() {
        List<List<String>> allSkills = employees.stream()
                .map(Employee::getSkills)
                .collect(Collectors.toList());

        System.out.println(allSkills);

        List<String> allSkills2 = employees.stream()
                .map(Employee::getSkills)
                .flatMap(list -> list.stream())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(allSkills2);
    }

    @Test
    public void filterOperation() {
        employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("C"))
                .forEach(System.out::println);

    }

    @Test
    public void sortedOperation() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .forEach(System.out::println);
    }

    @Test
    public void limitOperation() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .limit(4)
                .forEach(System.out::println);

    }

    @Test
    public void skipOperation() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .skip(4)
                .forEach(System.out::println);

    }

    @Test
    public void countOperation() {
        long numberOfEmpolyees = employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("C"))
                .count();

        System.out.println(numberOfEmpolyees);

    }

    @Test
    public void minMaxOperation() {
        Employee youngestEmployee = employees.stream()
                .min(Comparator.comparing(Employee::getAge)).get();

        System.out.println(youngestEmployee);

    }

    @Test
    public void findAnyFIndFIrstOperation() {
        Employee emp = employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("C"))
                .findAny().get();

        System.out.println(emp);

    }

    @Test
    public void matchOperation() {
        boolean c = employees.stream()
                .anyMatch(employee -> employee.getFirstName().contains("c"));

        System.out.println(c);

    }

    @Test
    public void reduceOperation() {
        Integer sumOfAllAges = employees.stream()
                .map(Employee::getAge)
                .reduce(Integer::sum)
                .get();

        System.out.println(sumOfAllAges);


        Integer sumOfAllAges2 = employees.stream()
                .map(Employee::getAge)
                .reduce(0, Integer::sum);

        System.out.println(sumOfAllAges2);

        Integer sumOfAllAges3 = employees.stream()
                .reduce(0, (age, employees) -> age + employees.getAge(), Integer::sum);

        System.out.println(sumOfAllAges3);

        String allNames = employees.stream()
                .map(Employee::getFirstName)
                .reduce((name, name2) -> name + ", " + name2)
                .get();

        System.out.println(allNames);
    }

    @Test
    public void takeWhileOperation() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .takeWhile(employee -> employee.getAge() < 30)
                .forEach(System.out::println);
    }

    @Test
    public void dropWhileOperation() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .dropWhile(employee -> employee.getAge() < 30)
                .forEach(System.out::println);
    }

    @Test
    public void forEachOrdered() {
        String sentence = "How I'm do?";

        sentence.chars().forEach(s -> System.out.print((char) s));
        System.out.println();
        sentence.chars().parallel().forEachOrdered(s -> System.out.print((char) s));
    }
}
