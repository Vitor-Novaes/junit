package com.example;

import java.util.*;

public class Client {
    private final Scanner scanner;
    private final Map<String, List<String>> shiftChoices;

    public Client(Scanner scanner, Map<String, List<String>> shiftChoices) {
        this.scanner = scanner;
        this.shiftChoices = shiftChoices;
    }

    public void runScheduler() {
        System.out.println("Welcome to Scheduler!");

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Dentist");
            System.out.println("2. Medic Cardiac");
            System.out.println("3. Emergency");
            System.out.println("4. Exit");

            System.out.print("Enter your choice (1-4): ");
            int choice = getValidChoice();

            if (choice == 4) {
                break;
            } else if (choice >= 1 && choice <= 3) {
                recordShiftChoice(choice);
            } else {
                System.out.println(handleInvalidChoice(choice));
            }
        }

        displayShiftChoices();
        System.out.println("Shift choices and patient names recorded:");
        for (Map.Entry<String, List<String>> entry : shiftChoices.entrySet()) {
            String shiftName = entry.getKey();
            List<String> patients = entry.getValue();
            System.out.println(shiftName + ": " + patients);
        }
    }

    public int getValidChoice() {
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private void recordShiftChoice(int choice) {
        String shiftName = getShiftName(choice);
        System.out.print("Enter patient name: ");
        String patientName = scanner.nextLine();

        shiftChoices.computeIfAbsent(shiftName, k -> new ArrayList<>()).add(patientName);
        System.out.println("Shift choice recorded: " + shiftName + " for patient " + patientName);
    }

    public void displayShiftChoices() {
        System.out.println("Shift choices and patient names recorded:");
        for (Map.Entry<String, List<String>> entry : shiftChoices.entrySet()) {
            String shiftName = entry.getKey();
            List<String> patients = entry.getValue();
            System.out.println(shiftName + ": " + patients);
        }
    }

    public static String handleInvalidChoice(int choice) {
        return "Invalid choice. Please select a valid option (1-4).";
    }

    public static String getShiftName(int choice) {
        switch (choice) {
            case 1:
                return "Dentist";
            case 2:
                return "Cardiac Medical";
            case 3:
                return "Emergency";
            default:
                return "Invalid Choice";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> shiftChoices = new HashMap<>();
        Client client = new Client(scanner, shiftChoices);
        client.runScheduler();
    }
}
