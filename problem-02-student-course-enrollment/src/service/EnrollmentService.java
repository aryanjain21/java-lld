package service;

import course.Course;
import student.Student;

import java.util.*;

public class EnrollmentService {
    private final Map<UUID, Student> students = new HashMap<>();
    private final Map<UUID, Course> courses = new HashMap<>();
    private final Map<UUID, Set<UUID>> enrollmentByStudent = new HashMap<>();

    public void registerStudent(Student student) {
        if (students.containsKey(student.getId())) {
            throw new IllegalArgumentException("Student with this id already exist.");
        }

        students.put(student.getId(), student);
    }

    public void registerCourse(Course course) {
        if (courses.containsKey(course.getId())) {
            throw new IllegalArgumentException("Course with this id already exist.");
        }

        courses.put(course.getId(), course);
    }

    public Map<UUID, Course> getCoursesForStudent(UUID studentId) {
        Objects.requireNonNull(studentId, "Student does not exist.");
        Set<UUID> courseIds =  enrollmentByStudent.get(studentId);
        Objects.requireNonNull(courseIds, "Courses does not exist.");
        Map<UUID, Course> enrolledCourses = new HashMap<>();
        for (UUID courseId: courseIds) {
            enrolledCourses.putIfAbsent(courseId, courses.get(courseId));
        }

        return  enrolledCourses;
    }

    public boolean enroll(UUID studentId, UUID courseId) {
        validate(studentId, courseId);

        if (isEnrolled(studentId, courseId)) {
            throw new IllegalArgumentException("Student is already enrolled in this course.");
        }

        Course course = courses.get(courseId);
        int enrolledCount = countEnrolledStudents(courseId);
        if (enrolledCount >= course.getMaxCapacity()) {
            throw new IllegalArgumentException("Course has reached its maximum capacity.");
        }

        enrollmentByStudent.computeIfAbsent(studentId, id -> new HashSet<>()).add(courseId);

        return true;
    }

    public boolean unenroll(UUID studentId, UUID courseId) {
        validate(studentId, courseId);

        if (!isEnrolled(studentId, courseId)) {
            throw new IllegalArgumentException("Student doesn't enrolled to this course.");
        }

        Set<UUID> enrolledCourses = enrollmentByStudent.get(studentId);

        enrolledCourses.remove(courseId);

        if (enrolledCourses.isEmpty()) {
            enrollmentByStudent.remove(studentId);
        }

        return true;
    }

    private void validate(UUID studentId, UUID courseId) {
        Objects.requireNonNull(studentId, "Student does not exist.");
        Objects.requireNonNull(courseId, "Course does not exist.");

        if (!students.containsKey(studentId)) {
            throw new IllegalArgumentException("Student with this id doesn't exist.");
        }

        if (!courses.containsKey(courseId)) {
            throw new IllegalArgumentException("Course with this id doesn't exist.");
        }
    }

    public boolean isEnrolled(UUID studentId, UUID courseId) {
        Set<UUID> coursesEnrolled = enrollmentByStudent.get(studentId);
        return coursesEnrolled != null && coursesEnrolled.contains(courseId);
    }

    private int countEnrolledStudents(UUID courseId) {
        int count = 0;
        for (Set<UUID> enrolledCourses: enrollmentByStudent.values()) {
            if (enrolledCourses.contains(courseId)) {
                count++;
            }
        }

        return count;
    }
}
