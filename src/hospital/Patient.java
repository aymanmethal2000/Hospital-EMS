package hospital;

/**
 * Represents a single patient record.
 * This object is used as the node payload for the Patient Records BST,
 * and also carries a reference to that patient's Visit History
 * (a Singly Linked List of Visit objects).
 */
public class Patient {

    private int patientId;
    private String name;
    private int age;
    private String contactNumber;
    private String medicalCondition;

    // Each patient owns a singly linked list of their past visits.
    private VisitLinkedList visitHistory;

    public Patient(int patientId, String name, int age, String contactNumber, String medicalCondition) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.medicalCondition = medicalCondition;
        this.visitHistory = new VisitLinkedList();
    }

    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public VisitLinkedList getVisitHistory() {
        return visitHistory;
    }

    @Override
    public String toString() {
        return String.format(
                "Patient ID: %-6d | Name: %-20s | Age: %-3d | Contact: %-15s | Condition: %s",
                patientId, name, age, contactNumber, medicalCondition);
    }
}
