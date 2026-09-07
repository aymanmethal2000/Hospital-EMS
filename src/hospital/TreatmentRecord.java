package hospital;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a completed treatment event for a patient.
 * These are pushed onto the Treatment History Stack when a
 * patient's treatment is completed.
 */
public class TreatmentRecord {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private int patientId;
    private String patientName;
    private String treatmentDetails;
    private LocalDateTime completedAt;

    public TreatmentRecord(int patientId, String patientName, String treatmentDetails) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.treatmentDetails = treatmentDetails;
        this.completedAt = LocalDateTime.now();
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getTreatmentDetails() {
        return treatmentDetails;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    @Override
    public String toString() {
        return String.format(
                "Patient ID: %-6d | Name: %-20s | Treatment: %-25s | Completed At: %s",
                patientId, patientName, treatmentDetails, completedAt.format(FORMATTER));
    }
}
