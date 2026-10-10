import java.util.*;
public class StudentService {
    private List<Student> repo = new ArrayList<>();
    public void add(String n, String r, String g) { repo.add(new Student(n, r, g)); }
    public void remove(String roll) { repo.removeIf(s -> s.getRoll().equals(roll)); }
    public void search(String roll) { repo.stream().filter(s -> s.getRoll().equals(roll)).forEach(System.out::println); }
    public void sortByName() { Collections.sort(repo); repo.forEach(System.out::println); }
    public void showAll() { repo.forEach(System.out::println); }
}
