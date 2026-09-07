package hospital;

/**
 * Singly Linked List that stores a single patient's Visit History.
 *
 * Supports:
 *  - addVisit(Visit)         -> append a new visit to the history
 *  - removeVisit(visitId)    -> remove a visit by Visit ID
 *  - searchVisit(visitId)    -> find a visit by Visit ID
 *  - displayVisits()         -> print the patient's visit history in order
 */
public class VisitLinkedList {

    private class VisitNode {
        Visit visit;
        VisitNode next;

        VisitNode(Visit visit) {
            this.visit = visit;
        }
    }

    private VisitNode head;
    private VisitNode tail;
    private int size;

    /** Add a new visit to the end of this patient's visit history. */
    public void addVisit(Visit visit) {
        VisitNode newNode = new VisitNode(visit);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /** Remove a visit from the history by its Visit ID. Returns true if removed. */
    public boolean removeVisit(int visitId) {
        if (head == null) {
            return false;
        }

        // Removing the head node.
        if (head.visit.getVisitId() == visitId) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            size--;
            return true;
        }

        VisitNode current = head;
        while (current.next != null) {
            if (current.next.visit.getVisitId() == visitId) {
                current.next = current.next.next;
                if (current.next == null) {
                    tail = current; // removed node was the tail
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false; // not found
    }

    /** Search for a visit by Visit ID. Returns null if not found. */
    public Visit searchVisit(int visitId) {
        VisitNode current = head;
        while (current != null) {
            if (current.visit.getVisitId() == visitId) {
                return current.visit;
            }
            current = current.next;
        }
        return null;
    }

    /** Display the patient's visit history in chronological (insertion) order. */
    public void displayVisits() {
        if (head == null) {
            System.out.println("No visit history found for this patient.");
            return;
        }
        System.out.println("---- Patient Visit History ----");
        VisitNode current = head;
        while (current != null) {
            System.out.println(current.visit);
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }
}
