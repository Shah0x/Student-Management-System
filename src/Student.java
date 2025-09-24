import java.util.ArrayList;
public class Student extends Person {
    private String rollNumber;
    private double gpa;
    private Department department;
    private ArrayList<Course> courses = new ArrayList<>();
    public Student(String name, int age, String rollNumber, double gpa, Department department) {
        super(name, age);
        this.rollNumber = rollNumber;
        this.gpa = gpa;
        this.department = department;
    }
    public String getRollNumber() {
        return rollNumber;
    }
    public double getGpa() {
        return gpa;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    public void addCourse(Course course) {
        courses.add(course);
    }
    public ArrayList<Course> getCourses() {
        return courses;
    }
    @Override
    public void display() {
        System.out.println("\n--- Student Info ---");
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Roll No: " + rollNumber);
        System.out.println("GPA: " + gpa);
        System.out.println("Department: " + department.getDepartmentName());
        System.out.print("Courses: ");
        if (!courses.isEmpty()) {
            for (Course c : courses) {
                System.out.print(c + "  ");
            }
        } else {
            System.out.print("No courses assigned.");
        }
        System.out.println();
    }
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(",").append(getAge()).append(",")
                .append(rollNumber).append(",").append(gpa).append(",")
                .append(department.getDepartmentName());
        for (Course c : courses) {
            sb.append(",").append(c.getTitle());
        }
        return sb.toString();
    }
    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        String name = parts[0];
        int age = Integer.parseInt(parts[1]);
        String roll = parts[2];
        double gpa = Double.parseDouble(parts[3]);
        Department dept = new Department(parts[4]);
        Student s = new Student(name, age, roll, gpa, dept);
        for (int i = 5; i < parts.length; i++) {
            s.addCourse(new Course(parts[i]));
        }
        return s;
    }
}
