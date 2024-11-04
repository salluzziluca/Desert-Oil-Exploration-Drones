package org.example;
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No input provided.");
            return;
        }

        Controller controller = new Controller();
        String output = null;
        try {
            output = controller.processInput(args);
        } catch (IllegalArgumentException e) {
            System.out.println("Error processing input: " + e.getMessage());
        }
        System.out.println(output);
    }
}