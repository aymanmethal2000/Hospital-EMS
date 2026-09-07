package hospital;

/**
 * Treatment History implemented as a linked-node Stack (LIFO).
 *
 * Supports:
 *  - push(TreatmentRecord)  -> add a completed treatment record
 *  - pop()                  -> remove the most recently completed treatment record
 *  - displayHistory()       -> show all treatment records, most recent first
 *  - isEmpty()
 */
public class TreatmentHistoryStack {

    private class StackNode {
        TreatmentRecord record;
        StackNode next;

        StackNode(TreatmentRecord record) {
            this.record = record;
        }
    }

    private StackNode top;
    private int size;

    /** Push a newly completed treatment record onto the stack. */
    public void push(TreatmentRecord record) {
        StackNode newNode = new StackNode(record);
        newNode.next = top;
        top = newNode;
        size++;
    }

    /** Remove and return the most recently completed treatment record. */
    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment history stack is empty. Nothing to pop.");
            return null;
        }
        TreatmentRecord record = top.record;
        top = top.next;
        size--;
        return record;
    }

    /** Look at the most recent treatment record without removing it. */
    public TreatmentRecord peek() {
        if (isEmpty()) {
            System.out.println("Treatment history stack is empty.");
            return null;
        }
        return top.record;
    }

    /** Display all treatment records, most recent (top of stack) first. */
    public void displayHistory() {
        if (isEmpty()) {
            System.out.println("Treatment history is empty. No completed treatments yet.");
            return;
        }
        System.out.println("---- Treatment History (Most Recent First) ----");
        StackNode current = top;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.record);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}
