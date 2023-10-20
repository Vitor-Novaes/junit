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

        Scanner scanner = new Scanner(System.in);
        Client client = new Client(scanner, shiftChoices);
        String expectedOutput = "Shift choices and patient names recorded:\nDentist: [Patient1, Patient2]\nEmergency: [Patient3]";
        assertEquals(expectedOutput, captureOutput(() -> client.displayShiftChoices()));
    }

    @Test
    public void testInvalidChoiceHandling() {
      assertEquals("Invalid Choice", Client.getShiftName(5));
    }

    // @Test
    // public void testRecordPatientName() {
        
        
    // }

    

    // @Test
    // public void testExitOption() {
        
        
    // }

    @Test
    public void testExitOptionHandling() {
      Scanner scanner = new Scanner("4\n");
      Map<String, List<String>> shiftChoices = new HashMap<>();
      Client client = new Client(scanner, shiftChoices);
      client.runScheduler(); 
      
      
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
      client.runScheduler(); 
      
    }

    @Test
    public void testNewlineConsumption() {
      Scanner scanner = new Scanner("1\n\n\n4\n");
      Map<String, List<String>> shiftChoices = new HashMap<>();
      Client client = new Client(scanner, shiftChoices);
      client.runScheduler(); 
      
      
    }

    @Test
    public void testGetValidChoiceWithInputMismatchException() {
        String input = "invalid\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        Map<String, List<String>> shiftChoices = new HashMap<>();
        Client client = new Client(scanner, shiftChoices);

        
        assertThrows(NoSuchElementException.class, () -> client.getValidChoice());
    }

    
    private String captureOutput(Runnable code) {
      java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
      System.setOut(new java.io.PrintStream(out));
      code.run(); 
      System.setOut(System.out); 
      return out.toString().trim(); 
    }

    private String captureMenuOutput(Runnable code) {
      java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
      System.setOut(new java.io.PrintStream(out));
      code.run(); 
      System.setOut(System.out); 
      String capturedOutput = out.toString();
      int endIndex = capturedOutput.indexOf("Enter your choice (1-4): ") + 26; 
      return capturedOutput.substring(0, endIndex);
    }
}