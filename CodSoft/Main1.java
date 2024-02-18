import java.util.ArrayList;
import java.util.List;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private List<String> schedule;
    private List<String> registeredStudents;

    public Course(String code, String title, String description, int capacity, List<String> schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public List<String> getRegisteredStudents() {
        return registeredStudents;
    }

    
    public boolean registerStudent(String studentID) {
        if (registeredStudents.size() < capacity) {
            registeredStudents.add(studentID);
            return true;
        }
        return false; 
    }

    
    public boolean removeStudent(String studentID) {
        return registeredStudents.remove(studentID);
    }
}

class Student {
    private String studentID;
    private String name;
    private List<String> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    
    public void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
    }

    
    public boolean dropCourse(String courseCode) {
        return registeredCourses.remove(courseCode);
    }
}

class CourseRegistrationSystem {
    private List<Course> courses;
    private List<Student> students;

    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    
    public void addCourse(Course course) {
        courses.add(course);
    }

  
    public void addStudent(Student student) {
        students.add(student);
    }

   
    public List<Course> getCourses() {
        return courses;
    }

    
    public List<Student> getStudents() {
        return students;
    }

   
    public boolean registerStudentForCourse(String studentID, String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                if (course.registerStudent(studentID)) {
                    for (Student student : students) {
                        if (student.getStudentID().equals(studentID)) {
                            student.registerCourse(courseCode);
                            return true;
                        }
                    }
                } else {
                    return false; 
                }
            }
        }
        return false; 
    }

    
    public boolean dropCourseForStudent(String studentID, String courseCode) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                if (student.dropCourse(courseCode)) {
                    for (Course course : courses) {
                        if (course.getCode().equals(courseCode)) {
                            course.removeStudent(studentID);
                            return true;
                        }
                    }
                } else {
                    return false; 
                }
            }
        }
        return false; 
    }
}

public class Main1 {
    public static void main(String[] args) {
        
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        
        Course course1 = new Course("CSE101", "Introduction to Computer Science", "Fundamentals of programming", 30, List.of("Mon, Wed, Fri 10:00 - 11:30"));
        Course course2 = new Course("MAT201", "Linear Algebra", "Introduction to linear algebra", 25, List.of("Tue, Thu 13:00 - 14:30"));
        system.addCourse(course1);
        system.addCourse(course2);

       
        Student student1 = new Student("101", "John");
        Student student2 = new Student("102", "Alice");
        system.addStudent(student1);
        system.addStudent(student2);

        
        boolean success1 = system.registerStudentForCourse("101", "CSE101");
        boolean success2 = system.registerStudentForCourse("102", "MAT201");

        
        boolean dropped = system.dropCourseForStudent("101", "CSE101");

        
        if (success1 && success2 && dropped) {
            System.out.println("Operations completed successfully.");
        } else {
            System.out.println("Error occurred during operations.");
        }
    }
}
