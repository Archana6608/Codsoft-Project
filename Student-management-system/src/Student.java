public class Student implements Comparable<Student> {
    private String name, roll, grade;
    public Student(String n, String r, String g) { this.name = n; this.roll = r; this.grade = g; }
    public String getName() { return name; } public String getRoll() { return roll; }
    @Override public int compareTo(Student other) { return this.name.compareTo(other.name); }
    @Override public String toString() { return String.format(" | %-8s | %-12s | %-6s |", roll, name, grade); }
}
