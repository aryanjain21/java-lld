package library;

import java.util.Objects;

public class Magazine extends LibraryItem {
    private final String publisher;

    public Magazine(String id, String title, String publisher) {
        super(id, title, ItemType.MAGAZINE);
        this.publisher = validatePublisher(publisher);
    }

    public String getPublisher() {
        return publisher;
    }

    private String validatePublisher(String publisher) {
        Objects.requireNonNull(publisher, "Publisher cannot be null.");

        String trimmedPublisher = publisher.trim();
        if(trimmedPublisher.isBlank()) {
            throw new IllegalArgumentException("Publisher cannot be blank.");
        }

        return trimmedPublisher;
    }
}
