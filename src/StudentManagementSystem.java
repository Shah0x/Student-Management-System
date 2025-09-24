import java.util.*;
import java.io.*;
import javax.swing.*;

abstract class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public abstract void display();
}

class Department {
    private String departmentName;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}

class Course {
    private String title;

    public Course(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        return title;
    }
}

class Student extends Person {
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

    public void display() {
        System.out.println("\n--- Student Info ---");
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Roll No: " + rollNumber);
        System.out.println("GPA: " + gpa);
        System.out.println("Department: " + department.getDepartmentName());
        System.out.print("Courses: ");
        if (!courses.isEmpty()) {
            for (Course c : courses)
                System.out.print(c + "  ");
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
        for (Course c : courses)
            sb.append(",").append(c.getTitle());
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

public class StudentManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();
    static final String FILE_NAME = "IO_File.txt";

    public static void main(String[] args) {
        String userName = JOptionPane.showInputDialog(null, "Enter Your Name:", "Welcome", JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(null, "Welcome, " + userName + "! Let's begin the Student Management System.",
                "Welcome", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null,
                "Project Members:\n"
                        + "- Shahmeer Akram__21016\n"
                        + "- Urooj Hassan__21180\n",
                "Team Information",
                JOptionPane.INFORMATION_MESSAGE);
        loadFromFile();
        while (true) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║      Student Management System         ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("Please select an option below:");
            System.out.println("----------------------------------------------");
            System.out.println("-- [1] > Add New Student");
            System.out.println("-- [2] > View All Students");
            System.out.println("-- [3] > Edit Existing Student");
            System.out.println("-- [4] > Delete Student Record");
            System.out.println("-- [5] > Exit Application");
            System.out.println("----------------------------------------------");
            int choice = getIntInput("Enter your choice [1-5]: ");
            System.out.println("______________________________________________");
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> editStudent();
                case 4 -> deleteStudent();
                case 5 -> {
                    saveToFile();
                    return;
                }
                default -> System.out.println("Invalid Option.");
            }
        }
    }

    static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            try {
                int value = Integer.parseInt(sc.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            try {
                double value = Double.parseDouble(sc.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    static void addStudent() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        int age = getIntInput("Age: ");
        System.out.print("Roll No: ");
        String roll = sc.nextLine();
        double gpa = getDoubleInput("GPA: ");
        System.out.print("Department: ");
        Department dept = new Department(sc.nextLine());
        Student s = new Student(name, age, roll, gpa, dept);
        int n = getIntInput("How many courses? ");
        for (int i = 0; i < n; i++) {
            System.out.print("Course " + (i + 1) + ": ");
            s.addCourse(new Course(sc.nextLine()));
        }
        students.add(s);
    }

    static void viewStudents() {
        if (students.isEmpty())
            System.out.println("No! Students Found.");
        else
            for (Student s : students)
                s.display();
    }

    static void editStudent() {
        System.out.print("Enter Roll No: ");
        String roll = sc.nextLine();
        for (Student s : students) {
            if (s.getRollNumber().equals(roll)) {
                System.out.print("New Name: ");
                s.setName(sc.nextLine());
                s.setAge(getIntInput("New Age: "));
                s.setGpa(getDoubleInput("New GPA: "));
                System.out.print("Update Courses? (yes/no): ");
                if (sc.nextLine().equalsIgnoreCase("yes")) {
                    s.getCourses().clear();
                    int n = getIntInput("How many courses? ");
                    for (int i = 0; i < n; i++) {
                        System.out.print("Course " + (i + 1) + ": ");
                        s.addCourse(new Course(sc.nextLine()));
                    }
                }
                return;
            }
        }
        System.out.println("Student not Found.");
    }

    static void deleteStudent() {
        System.out.print("Enter Roll No to delete: ");
        String roll = sc.nextLine();
        boolean removed = students.removeIf(s -> s.getRollNumber().equals(roll));
        if (!removed)
            System.out.println("Student not Found.");
    }

    static void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students)
                pw.println(s.toFileString());
        } catch (IOException e) {
            System.out.println("Error Saving File.");
        }
    }

    static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists())
            return;
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine())
                students.add(Student.fromFileString(reader.nextLine()));
        } catch (IOException e) {
            System.out.println("Error Loading File.");
        }
    }
}
