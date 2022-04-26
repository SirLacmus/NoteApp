package ru.sirlacmus.note;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NoteApp {
    private final List<Note> list;
    private final Scanner scanner;
    private String author;

    public NoteApp() {

        this.list = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void menu() {

        String input;

        while (true) {

            System.out.println("Welcome to Note App!\n");
            System.out.println("Please, enter your name:");
            author = scanner.nextLine();


            System.out.println(
                    "Please select the menu item below:\n" +
                            "1. View all notes\n" +
                            "2. Create new note\n" +
                            "3. Edit note\n" +
                            "4. Delete note\n" +
                            "5. Exit");


            input = scanner.nextLine();

            switch (input) {
                case "1":
                    for (Note note : list) {
                        System.out.println("Note: " + note.getContent() + "\n");
                        System.out.println("Date: " + note.getDate() + "\n");
                        System.out.println("Author: " + note.getAuthor() + "\n");
                        System.out.println("Category: " + note.getCategory() + "\n");
                        System.out.println("------------------------------------");
                    }
                    break;
                case "2":
                    createNewNote();
                    break;
                case "3":
                    //--
                    break;
                case "4":
                    //--
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
        String date = LocalDateTime.now().toString();

        System.out.println("Please enter note text:");
        noteText = scanner.nextLine();

        while (category == null) {

            System.out.println("Please select a category: 1 - WORK, 2 - EVERYDAY, 3 - TRAVEL, 4 - PERSONAL, 5 - NO CATEGORY");
            String categoryInput = scanner.nextLine();

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

        list.add(new Note(date, noteText, category, this.author));
    }


}
