package ru.sirlacmus.note;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class NoteApp {
    private final Map<Integer, Note> notes;
    private final Scanner scanner;
    private String author;
    private final String delimiter = "------------------------------------";

    public NoteApp() {

        this.notes = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        String input;

        System.out.println("Welcome to Note App!\n");
        System.out.println("Please, enter your name:\n");

        author = scanner.next();


        while (true) {


            System.out.println(
                    "Please select the menu item below:\n" +
                            "1. View all notes\n" +
                            "2. Create new note\n" +
                            "3. Edit note\n" +
                            "4. Delete note\n" +
                            "5. Exit\n");

            input = scanner.next();

            switch (input) {
                case "1":
                    if (notes.size() == 0) {
                        System.out.println("There are no notes! Please create one.");
                        break;
                    } else {
                        System.out.println(delimiter);
                        for (Note note : notes.values()) {
                            System.out.println("ID: " + note.getID() + "\n");
                            System.out.println("Note: " + note.getContent() + "\n");
                            System.out.println("Date: " + note.getDate() + "\n");
                            System.out.println("Author: " + note.getAuthor() + "\n");
                            System.out.println("Category: " + note.getCategory() + "\n");
                            System.out.println("------------------------------------");
                        }
                    }
                    break;
                case "2":
                    createNewNote();
                    break;
                case "3":
                    editNote();
                    break;
                case "4":
                    deleteNote();
                    break;
                case "5":
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter correct menu value");
                    break;
            }

        }
    }

    private void createNewNote() {
        String noteText;
        Note.Categories category = null;
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.println("Please enter note text:");
        noteText = scanner.next();

        while (category == null) {

            System.out.println("Please select a category: 1 - WORK, 2 - EVERYDAY, 3 - TRAVEL, 4 - PERSONAL, 5 - NO CATEGORY");
            String categoryInput = scanner.next();

            switch (categoryInput) {
                case "1":
                    category = Note.Categories.WORK;
                    break;
                case "2":
                    category = Note.Categories.EVERYDAY;
                    break;
                case "3":
                    category = Note.Categories.TRAVEL;
                    break;
                case "4":
                    category = Note.Categories.PERSONAL;
                    break;
                case "5":
                    category = Note.Categories.NO_CATEGORY;
                    break;
                default:
                    System.out.println("Please enter correct category number!");
            }
        }

        Note newNote = new Note(notes.size() + 1, date, noteText, category, this.author);
        notes.put(newNote.getID(), newNote);
    }

    private void editNote() {
        System.out.println("Enter the note ID you wish to edit: ");
        int editInput = Integer.parseInt(scanner.next());

        if (!notes.containsKey(editInput)) {
            System.out.println("There are no notes with such ID");
            return;
        }

        while (true) {
            System.out.println("What do you want to edit? 1 - Note text, 2 - Note category, 3 - Quit to main menu");

            String input = scanner.next();

            switch (input) {
                case "1":
                    System.out.println("Enter new text:");
                    String content = scanner.next();
                    notes.get(editInput).setContent(content);
                    System.out.println("Successfully edited content of a note with ID " + editInput);
                    break;
                case "2":
                    Note.Categories category = null;

                    while (category == null) {

                        System.out.println("Please select a category: 1 - WORK, 2 - EVERYDAY, 3 - TRAVEL, 4 - PERSONAL, 5 - NO CATEGORY");
                        String categoryInput = scanner.next();

                        switch (categoryInput) {
                            case "1":
                                category = Note.Categories.WORK;
                                break;
                            case "2":
                                category = Note.Categories.EVERYDAY;
                                break;
                            case "3":
                                category = Note.Categories.TRAVEL;
                                break;
                            case "4":
                                category = Note.Categories.PERSONAL;
                                break;
                            case "5":
                                category = Note.Categories.NO_CATEGORY;
                                break;
                            default:
                                System.out.println("Please enter correct category number!");
                        }
                    }
                    notes.get(editInput).setCategory(category);
                    System.out.println("Successfully edited category of a note with ID " + editInput);
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Please choose correct menu section");
            }
        }
    }

    private void deleteNote() {
        if (notes.isEmpty()) {
            System.out.println("Sorry, there are no notes to delete.");
            return;
        }

        System.out.println("Please enter the ID of the note you wish to delete:");
        int id = Integer.parseInt(scanner.next());

        if (notes.containsKey(id)) {
            notes.remove(id);
            System.out.println("Note with ID " + id + " have been deleted.");
        } else {
            System.out.println("Please enter correct Note ID!");
        }
    }


}