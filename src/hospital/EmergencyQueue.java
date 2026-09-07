package hospital;

/**
 * Emergency Patient Queue implemented as a linked-node Queue (FIFO).
 *
 * Supports:
 *  - enqueue(Patient)  -> add patient to the back of the waiting line
 *  - dequeue()         -> remove and return the patient at the front (next for treatment)
 *  - displayQueue()    -> show all patients currently waiting
 *  - isEmpty()
 */
public class EmergencyQueue {

    private class QueueNode {
        Patient patient;
        QueueNode next;

        QueueNode(Patient patient) {
            this.patient = patient;
        }
    }

    private QueueNode front; // next patient to be treated
    private QueueNode rear;  // last patient who arrived
    private int size;

    /** Add a patient to the back of the emergency waiting queue. */
    public void enqueue(Patient patient) {
        QueueNode newNode = new QueueNode(patient);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    /** Remove and return the next patient for treatment (front of the queue). */
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patient to dequeue.");
            return null;
        }
        Patient patient = front.patient;
        front = front.next;
        if (front == null) {
            rear = null; // queue just became empty
        }
        size--;
        return patient;
    }

    /** Look at the next patient without removing them. */
    public Patient peek() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty.");
            return null;
        }
        return front.patient;
    }

    /** Display all patients currently waiting, in FIFO order. */
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patients waiting.");
            return;
        }
        System.out.println("---- Patients Currently Waiting (Front -> Rear) ----");
        QueueNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }
}
