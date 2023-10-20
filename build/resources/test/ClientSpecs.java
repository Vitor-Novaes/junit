package src.test.resources;

import org.junit.Test;

import src.main.resources.Client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

public class ClientSpecs {

    @Test
    public void testToDentist() {
        assertEquals("Dentist", Client.getShiftName(1));
    }

    @Test
    public void testInvalidChoice() {
        assertEquals("Invalid Choice", Client.getShiftName(5));
    }

    @Test
    public void testGetShiftNameEmergency() {
        assertEquals("Emergency", Client.getShiftName(3));
    }

    @Test
    public void testDisplayShiftChoicesWithEntries() {
        Map<String, List<String>> shiftChoices = new HashMap<>();
        shiftChoices.put("Dentist", List.of("Patient1", "Patient2"));
        shiftChoices.put("Emergency", List.of("Patient3"));

        Scanner scanner = new Scanner(System.in); // Create a valid scanner instance
        Client client = new Client(scanner, shiftChoices);
        String expectedOutput = "Shift choices and patient names recorded:\nDentist: [Patient1, Patient2]\nEmergency: [Patient3]";
        assertEquals(expectedOutput, captureOutput(() -> client.displayShiftChoices()));
    }

    // @Test
    // public void testShiftChoicesRecording() {
    //   Map<String, List<String>> shiftChoices = new HashMap<>();
    //   Client client = new Client(null, null);
    //   client.recordShiftChoice(shiftChoices, "Dentist", "Patient1");
    //   assertEquals("Dentist: [Patient1]", captureOutput(() -> client.displayShiftChoices(shiftChoices)));
    // }

    @Test
    public void testInvalidChoiceHandling() {
      assertEquals("Invalid Choice", Client.getShiftName(5));
    }

    @Test
    public void testRecordPatientName() {
        // Implement this test to verify that the program correctly stores the patient's name
        // ...
    }

    // More tests for different parts of your code...

    @Test
    public void testExitOption() {
        // Implement this test to verify that the program correctly handles the "Exit" option
        // ...
    }

    @Test
    public void testExitOptionHandling() {
      Scanner scanner = new Scanner("4\n");
      Map<String, List<String>> shiftChoices = new HashMap<>();
      Client client = new Client(scanner, shiftChoices);
      client.runScheduler(); // Simulate selecting the "Exit" option
      // You can assert the output or any desired behavior here
      // For example, you can check if the program exited gracefully.
    }

    @Test
    public void testMenuDisplay() {
      Scanner scanner = new Scanner("4\n");
      Map<String, List<String>> shiftChoices = new HashMap<>();
      Client client = new Client(scanner, shiftChoices);
      String expectedMenuOutput = "Welcome to Scheduler!\n" +
          "Select an option:\n" +
          "1. Dentist\n" +
          "2. Medic Cardiac\n" +
          "3. Emergency\n" +
          "4. Exit\n" +
          "Enter your choice (1-4): S";
      assertEquals(expectedMenuOutput, captureMenuOutput(() -> client.runScheduler()));
    }

    @Test
    public void testErrorHandling() {
      Scanner scanner = new Scanner("invalid\n4\n");
      Map<String, List<String>> shiftChoices = new HashMap<>();
      Client client = new Client(scanner, shiftChoices);
      client.runScheduler(); // Simulate entering invalid input
      // You can assert the output or any desired error handling behavior here
    }

    @Test
    public void testNewlineConsumption() {
      Scanner scanner = new Scanner("1\n\n\n4\n");
      Map<String, List<String>> shiftChoices = new HashMap<>();
      Client client = new Client(scanner, shiftChoices);
      client.runScheduler(); // Simulate entering extra newline characters
      // You can assert the output or any desired behavior related to newline
      // consumption
    }

    @Test
    public void testGetValidChoiceWithInputMismatchException() {
        String input = "invalid\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        Map<String, List<String>> shiftChoices = new HashMap<>();
        Client client = new Client(scanner, shiftChoices);

        // Use assertThrows to check if the method throws an InputMismatchException
        assertThrows(NoSuchElementException.class, () -> client.getValidChoice());
    }

    // Helper method to capture the standard output and return it as a string
    private String captureOutput(Runnable code) {
      java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
      System.setOut(new java.io.PrintStream(out));
      code.run(); // Run the provided code block
      System.setOut(System.out); // Reset System.out for subsequent output
      return out.toString().trim(); // Return the captured output
    }

    private String captureMenuOutput(Runnable code) {
      java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
      System.setOut(new java.io.PrintStream(out));
      code.run(); // Run the provided code block
      System.setOut(System.out); // Reset System.out for subsequent output
      String capturedOutput = out.toString();
      int endIndex = capturedOutput.indexOf("Enter your choice (1-4): ") + 26; // The menu ends here
      return capturedOutput.substring(0, endIndex);
    }
}