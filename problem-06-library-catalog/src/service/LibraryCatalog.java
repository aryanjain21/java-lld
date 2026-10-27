package service;

import library.ItemType;
import library.LibraryItem;

import java.util.*;
import java.util.stream.Collectors;

public class LibraryCatalog {
    private final Map<String, LibraryItem> items = new HashMap<>();

    public LibraryItem addCatalog(LibraryItem item) {
        Objects.requireNonNull(item, "Item cannot be null.");

        LibraryItem existing = items.putIfAbsent(item.getId(), item);

        if (existing != null) {
            throw new IllegalArgumentException("Duplicate item ID");
        }

        return item;
    }

    public LibraryItem removeCatalog(String id) {
        LibraryItem libraryItem = findExistingItem(id);
        items.remove(libraryItem.getId());
        return libraryItem;
    }

    public LibraryItem findItem(String id) {
        return findExistingItem(id);
    }

    public List<LibraryItem> searchByTitle(String title) {
        Objects.requireNonNull(title, "Title cannot be null.");
        String trimmedTitle = title.trim();

        if (trimmedTitle.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank.");
        }

        return items.values().stream().filter(it -> it.getTitle().equalsIgnoreCase(trimmedTitle)).collect(Collectors.toList());
    }

    public List<LibraryItem> filterByType(ItemType type) {
        Objects.requireNonNull(type, "Type cannot be null");

        return items.values().stream().filter(it -> it.getType() == type).collect(Collectors.toList());
    }

    private LibraryItem findExistingItem(String id) {
        Objects.requireNonNull(id, "Id cannot be null.");

        String trimmedId = id.trim();

        if (trimmedId.isBlank()) {
            throw new IllegalArgumentException("Id cannot be blank.");
        }
        LibraryItem libraryItem = items.get(trimmedId);
        if (libraryItem == null) {
            throw new IllegalArgumentException("Item does not exist with this ID.");
        }
        return libraryItem;
    }
}
