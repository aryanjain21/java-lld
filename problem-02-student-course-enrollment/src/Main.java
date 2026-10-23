import course.Course;
import service.EnrollmentService;
import student.Student;

import java.util.UUID;

public class Main {
    static void main() {
        Student student = new Student("Aryan");
        Student student1 = new Student("Bandan");
        Student student2 = new Student("Crox");
        EnrollmentService enrollmentService = new EnrollmentService();

        try {
            Course course = new Course("Gen AI", 1);
            System.out.println("course" + course.getCourseName() + course.getMaxCapacity());
            enrollmentService.registerCourse(course);
            enrollmentService.registerStudent(student);
            enrollmentService.registerStudent(student1);
            enrollmentService.registerStudent(student2);

            boolean isEnrolled = enrollmentService.enroll(student.getId(), course.getId());
            System.out.println("Enrolled to course " + (isEnrolled ? "Successfully" : "failed"));

            boolean isEnrolled1 = enrollmentService.enroll(student1.getId(), course.getId());
            System.out.println("Enrolled to course " + (isEnrolled1 ? "Successfully" : "failed"));

            boolean isEnrolled2 = enrollmentService.enroll(student2.getId(), course.getId());
            System.out.println("Enrolled to course " + (isEnrolled2 ? "Successfully" : "failed"));

//            boolean isEnrolled1 = enrollmentService.enroll(student.getId(), course.getId());
//            System.out.println("2 Enrolled to course " + (isEnrolled1 ? "Successfully" : "failed"));
            boolean unerolled = enrollmentService.unenroll(
                    student.getId(),
                    course.getId()
            );
            System.out.println("UnEnrolled to course " + (unerolled ? "Successfully" : "failed"));

//            boolean unerolled1 = enrollmentService.unenroll(
//                    student.getId(),
//                    course.getId()
//            );
//            System.out.println("UnEnrolled to course " + (unerolled1 ? "Successfully" : "failed"));

//        enrollmentService.unenroll(
//                UUID.fromString("550e8400-e29b-41d4-a716-446655440000"),
//                UUID.fromString("550e8400-e29b-41d4-a716-446655440002")
//        );
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}