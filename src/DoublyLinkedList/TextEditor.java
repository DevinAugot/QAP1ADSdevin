package DoublyLinkedList;

import java.util.Scanner;

/** NOTE: I put an overabundance of comments in the code to help me logically understand what exactly is going on
 * in the program for later use, it also helped me work through the general logic i was trying to implement during
 * the project */

public class TextEditor {
    private class Node {
        private String line;
        private Node previous;
        private Node next;

        public Node(String line) {
            this.line = line;
        }
    }

    private Node currentLine;
    private int cursorPosition;

    public void insertLine(String line) {
        Node newNode = new Node(line); // Create a new node with the given line

        if (currentLine != null) {   // Insert the new node into the linked list
            newNode.next = currentLine.next;
            if (currentLine.next != null) {
                currentLine.next.previous = newNode;
            }
            currentLine.next = newNode; // Update the next and previous pointers of the neighboring nodes
            newNode.previous = currentLine;
        }

        currentLine = newNode; // Update the current line and cursor position
    }

    public void deleteLine() {
        if (currentLine == null) {   // Check if there is a current line
            System.out.println("No line to delete.");
            return;
        }
        if (currentLine.previous != null) { // Remove the current line from the linked list
            currentLine.previous.next = currentLine.next;
        }
        if (currentLine.next != null) {  // Update the neighboring nodes' next and previous pointers
            currentLine.next.previous = currentLine.previous;
        }
        currentLine = currentLine.previous;  // Update the current line and cursor position
    }

    public void moveCursorUp() {
        if (currentLine != null && currentLine.previous != null) {
            currentLine = currentLine.previous; // Move the current line pointer to the previous line
        }
    }

    public void moveCursorDown() {
        if (currentLine != null && currentLine.next != null) {
            currentLine = currentLine.next; // Move the current line pointer to the next line
        }
    }

    public void moveCursorLeft() {
        if (currentLine != null && cursorPosition > 0) {
            cursorPosition--; // Move the cursor position to the left
        } else if (currentLine != null && currentLine.previous != null) {
            currentLine = currentLine.previous; // Move to the previous line (if available)
            cursorPosition = currentLine.line.length(); // Set the cursor position to the end of the line
        }
    }

    public void moveCursorRight() {
        if (currentLine != null && cursorPosition < currentLine.line.length()) {
            cursorPosition++; // Move the cursor position to the right
        } else if (currentLine != null && currentLine.next != null) {
            currentLine = currentLine.next; // Move to the next line (if available)
            cursorPosition = 0; // Set the cursor position to the beginning of the line
        }
    }


    public void editLine(String line) {
        if (currentLine != null) {
            currentLine.line = line; // Update the content of the current line with the given line
        }
    }

    public void printBuffer() { // The buffer in a text editor is a data structure that holds the lines of text being
            // edited.
        Node node = currentLine; // Start from the current line
        int lineNumber = 1;

        while (node != null) {
            String lineContent = node.line;

            if (node == currentLine) {
                if (cursorPosition >= lineContent.length()) {
                    lineContent = "> " + lineContent + "|";
                } else {
                    lineContent = "Current cursor position --> " + lineContent.substring(0, cursorPosition) + "|" + lineContent.substring(cursorPosition);
                }
            }

            System.out.println(lineNumber + ". " + lineContent); // Print the line number, content, and cursor position
            node = node.next; // Move to the next line
            lineNumber++;
        }
    }




    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        // Display menu options for Text Editor
        while (running) {
            System.out.println("======---{Welcome to the Text Editor}---======");
            System.out.println("1. Insert line");
            System.out.println("2. Delete line");
            System.out.println("3. Move cursor up");
            System.out.println("4. Move cursor down");
            System.out.println("5. Move cursor left");
            System.out.println("6. Move cursor right");
            System.out.println("7. Edit line");
            System.out.println("8. Print buffer");
            System.out.println("9. Exit");

            // Validate user input
            boolean validChoice = false;
            int choice = 0;
            while (!validChoice) {
                System.out.print("Enter your choice: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice >= 1 && choice <= 9) {
                        validChoice = true;
                    }
                } else {
                    System.out.println("ERROR! Please try again and enter a number from 1-9 only");
                    scanner.nextLine();
                }
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter the line to insert: ");
                    String lineToInsert = scanner.nextLine();
                    textEditor.insertLine(lineToInsert);
                }
                case 2 -> textEditor.deleteLine();
                case 3 -> textEditor.moveCursorUp();
                case 4 -> textEditor.moveCursorDown();
                case 5 -> textEditor.moveCursorLeft();
                case 6 -> textEditor.moveCursorRight();
                case 7 -> {
                    System.out.print("Enter the text to edit: ");
                    String textToEdit = scanner.nextLine();
                    textEditor.editLine(textToEdit);
                }
                case 8 -> textEditor.printBuffer();
                case 9 -> running = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
