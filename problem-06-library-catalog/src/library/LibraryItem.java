package library;

import java.util.Objects;

public abstract class LibraryItem {
    private final String id;
    private final String title;
    private final ItemType type;

    public LibraryItem(String id, String title, ItemType type) {
        this.id = validateId(id);
        this.title = validateTitle(title);
        this.type = Objects.requireNonNull(type, "Type cannot be null.");
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ItemType getType() {
        return type;
    }

    private String validateId(String id) {
        Objects.requireNonNull(id, "Id cannot be null.");

        String trimmedId = id.trim();
        if(trimmedId.isBlank()) {
            throw new IllegalArgumentException("Id cannot be blank.");
        }

        return trimmedId;
    }

    private String validateTitle(String title) {
        Objects.requireNonNull(title, "Title cannot be null.");

        String trimmedTitle = title.trim();
        if(trimmedTitle.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank.");
        }

        return trimmedTitle;
    }
}
