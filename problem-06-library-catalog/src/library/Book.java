package library;

import java.util.Objects;

public class Book extends LibraryItem {
    private final String author;

    public Book(String id, String title, String author) {
        super(id, title, ItemType.BOOK);
        this.author = validateAuthor(author);
    }

    public String getAuthor() {
        return author;
    }

    private String validateAuthor(String author) {
        Objects.requireNonNull(author, "Author cannot be null.");

        String trimmedAuthor = author.trim();
        if(trimmedAuthor.isBlank()) {
            throw new IllegalArgumentException("Author cannot be blank.");
        }

        return trimmedAuthor;
    }
}
