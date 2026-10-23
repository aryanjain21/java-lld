package student;

import java.util.Objects;
import java.util.UUID;

public class Student {
    private final UUID id;
    private String name;

    public Student(String name) {
        this.id = UUID.randomUUID();
        this.name = validateName(name);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = validateName(name);
    }

    private String validateName(String name) {
        Objects.requireNonNull(name, "name cannot be null");

        String trimmedName = name.trim();

        if (trimmedName.isBlank()) {
            throw new IllegalArgumentException(
                    "Name cannot be blank"
            );
        }

        return trimmedName;
    }
}
