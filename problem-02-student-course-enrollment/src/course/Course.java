package course;

import java.util.Objects;
import java.util.UUID;

public class Course {
    private final UUID id;
    private String courseName;
    private final int maxCapacity;

    public Course(String courseName, int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException(
                    "Maximum capacity must be greater than 0"
            );
        }
        this.maxCapacity = maxCapacity;
        this.courseName = validateCourseName(courseName);
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setCourseName(String courseName) {
        this.courseName = validateCourseName(courseName);
    }

    private String validateCourseName(String name) {
        Objects.requireNonNull(name, "Course name cannot be null");

        String trimmedCourseName = name.trim();

        if (trimmedCourseName.isBlank()) {
            throw new IllegalArgumentException(
                    "Course name cannot be blank"
            );
        }

        return trimmedCourseName;
    }
}
