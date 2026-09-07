package hospital;

import java.util.Scanner;

/**
 * Mini Hospital Emergency Management System
 *
 * Integrates four core data structures around a single Patient model:
 *  1. PatientBST          - Binary Search Tree for patient records (keyed by Patient ID)
 *  2. EmergencyQueue       - Queue (FIFO) for patients waiting for emergency treatment
 *  3. TreatmentHistoryStack- Stack (LIFO) for completed treatment records
 *  4. VisitLinkedList      - Singly Linked List (one per patient) for past visit history
 *
 * This class provides a console-based menu that demonstrates all required
 * operations for each data structure.
 */
public class HospitalManagementSystem {

    private static final PatientBST patientBST = new PatientBST();
    private static final EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static final TreatmentHistoryStack treatmentHistoryStack = new TreatmentHistoryStack();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> patientRecordsMenu();
                case 2 -> emergencyQueueMenu();
                case 3 -> treatmentHistoryMenu();
                case 4 -> visitHistoryMenu();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting Hospital Emergency Management System. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // ------------------------------------------------------------------
    // Main menu
    // ------------------------------------------------------------------

    private static void printMainMenu() {
        System.out.println("\n===================================================");
        System.out.println(" MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM");
        System.out.println("===================================================");
        System.out.println("1. Patient Records (BST)");
        System.out.println("2. Emergency Patient Queue (Queue)");
        System.out.println("3. Treatment History (Stack)");
        System.out.println("4. Patient Visit History (Singly Linked List)");
        System.out.println("0. Exit");
        System.out.println("===================================================");
    }

    // ------------------------------------------------------------------
    // 1. Patient Records - BST
    // ------------------------------------------------------------------

    private static void patientRecordsMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Patient Records (BST) ---");
            System.out.println("1. Insert new patient");
            System.out.println("2. Search patient by ID");
            System.out.println("3. Delete patient by ID");
            System.out.println("4. Display all patients (in-order traversal)");
            System.out.println("0. Back to main menu");
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> insertPatient();
                case 2 -> {
                    int id = readInt("Enter Patient ID to search: ");
                    Patient patient = patientBST.search(id);
                    if (patient != null) {
                        System.out.println("Patient found:\n" + patient);
                    } else {
                        System.out.println("No patient found with ID " + id);
                    }
                }
                case 3 -> {
                    int id = readInt("Enter Patient ID to delete: ");
                    boolean removed = patientBST.delete(id);
                    System.out.println(removed
                            ? "Patient with ID " + id + " deleted successfully."
                            : "No patient found with ID " + id);
                }
                case 4 -> patientBST.inOrderTraversal();
                case 0 -> back = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void insertPatient() {
        int id = readInt("Enter Patient ID: ");
        if (patientBST.search(id) != null) {
            System.out.println("A patient with ID " + id + " already exists. Insert cancelled.");
            return;
        }
        String name = readString("Enter Patient Name: ");
        int age = readInt("Enter Age: ");
        String contact = readString("Enter Contact Number: ");
        String condition = readString("Enter Medical Condition: ");

        Patient patient = new Patient(id, name, age, contact, condition);
        patientBST.insert(patient);
        System.out.println("Patient registered successfully.");
    }

    // ------------------------------------------------------------------
    // 2. Emergency Patient Queue - Queue
    // ------------------------------------------------------------------

    private static void emergencyQueueMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Emergency Patient Queue (Queue) ---");
            System.out.println("1. Enqueue patient (add to waiting queue)");
            System.out.println("2. Dequeue patient (send next patient for treatment)");
            System.out.println("3. Display all waiting patients");
            System.out.println("0. Back to main menu");
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> {
                    int id = readInt("Enter Patient ID to add to the emergency queue: ");
                    Patient patient = patientBST.search(id);
                    if (patient == null) {
                        System.out.println("No registered patient with ID " + id
                                + ". Please register the patient first (Patient Records menu).");
                    } else {
                        emergencyQueue.enqueue(patient);
                        System.out.println("Patient " + patient.getName() + " added to the emergency queue.");
                    }
                }
                case 2 -> {
                    Patient patient = emergencyQueue.dequeue();
                    if (patient != null) {
                        System.out.println("Now treating patient:\n" + patient);
                        String treatment = readString("Enter treatment given: ");
                        treatmentHistoryStack.push(
                                new TreatmentRecord(patient.getPatientId(), patient.getName(), treatment));
                        System.out.println("Treatment completed and recorded in treatment history.");
                    }
                }
                case 3 -> emergencyQueue.displayQueue();
                case 0 -> back = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // ------------------------------------------------------------------
    // 3. Treatment History - Stack
    // ------------------------------------------------------------------

    private static void treatmentHistoryMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Treatment History (Stack) ---");
            System.out.println("1. Push a completed treatment record manually");
            System.out.println("2. Pop most recent treatment record");
            System.out.println("3. Display treatment history");
            System.out.println("0. Back to main menu");
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> {
                    int id = readInt("Enter Patient ID: ");
                    Patient patient = patientBST.search(id);
                    if (patient == null) {
                        System.out.println("No registered patient with ID " + id + ".");
                    } else {
                        String treatment = readString("Enter treatment details: ");
                        treatmentHistoryStack.push(
                                new TreatmentRecord(patient.getPatientId(), patient.getName(), treatment));
                        System.out.println("Treatment record pushed to history.");
                    }
                }
                case 2 -> {
                    TreatmentRecord record = treatmentHistoryStack.pop();
                    if (record != null) {
                        System.out.println("Removed most recent treatment record:\n" + record);
                    }
                }
                case 3 -> treatmentHistoryStack.displayHistory();
                case 0 -> back = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // ------------------------------------------------------------------
    // 4. Patient Visit History - Singly Linked List
    // ------------------------------------------------------------------

    private static void visitHistoryMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Patient Visit History (Singly Linked List) ---");
            System.out.println("1. Add a new visit for a patient");
            System.out.println("2. Remove a visit for a patient");
            System.out.println("3. Search for a visit for a patient");
            System.out.println("4. Display a patient's visit history");
            System.out.println("0. Back to main menu");
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> addVisit();
                case 2 -> {
                    Patient patient = findPatientOrPrompt();
                    if (patient != null) {
                        int visitId = readInt("Enter Visit ID to remove: ");
                        boolean removed = patient.getVisitHistory().removeVisit(visitId);
                        System.out.println(removed
                                ? "Visit removed successfully."
                                : "No visit found with ID " + visitId);
                    }
                }
                case 3 -> {
                    Patient patient = findPatientOrPrompt();
                    if (patient != null) {
                        int visitId = readInt("Enter Visit ID to search: ");
                        Visit visit = patient.getVisitHistory().searchVisit(visitId);
                        System.out.println(visit != null
                                ? "Visit found:\n" + visit
                                : "No visit found with ID " + visitId);
                    }
                }
                case 4 -> {
                    Patient patient = findPatientOrPrompt();
                    if (patient != null) {
                        patient.getVisitHistory().displayVisits();
                    }
                }
                case 0 -> back = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addVisit() {
        Patient patient = findPatientOrPrompt();
        if (patient == null) {
            return;
        }
        int visitId = readInt("Enter Visit ID: ");
        String date = readString("Enter Visit Date (e.g. 2026-01-15): ");
        String doctor = readString("Enter Doctor Name: ");
        String diagnosis = readString("Enter Diagnosis: ");
        String treatment = readString("Enter Treatment: ");
        patient.getVisitHistory().addVisit(new Visit(visitId, date, doctor, diagnosis, treatment));
        System.out.println("Visit added to patient's history.");
    }

    private static Patient findPatientOrPrompt() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No registered patient with ID " + id
                    + ". Please register the patient first (Patient Records menu).");
        }
        return patient;
    }

    // ------------------------------------------------------------------
    // Input helpers
    // ------------------------------------------------------------------

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
