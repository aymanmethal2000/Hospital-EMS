package hospital;

/**
 * Represents a single past hospital visit belonging to a patient.
 * Stored as a node payload inside each patient's VisitLinkedList.
 */
public class Visit {

    private int visitId;
    private String visitDate;
    private String doctorName;
    private String diagnosis;
    private String treatment;

    public Visit(int visitId, String visitDate, String doctorName, String diagnosis, String treatment) {
        this.visitId = visitId;
        this.visitDate = visitDate;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public int getVisitId() {
        return visitId;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    @Override
    public String toString() {
        return String.format(
                "Visit ID: %-5d | Date: %-12s | Doctor: %-15s | Diagnosis: %-20s | Treatment: %s",
                visitId, visitDate, doctorName, diagnosis, treatment);
    }
}
