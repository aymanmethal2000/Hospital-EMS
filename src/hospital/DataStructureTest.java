package hospital;

/**
 * Standalone sanity checks for each data structure, run directly (no console input).
 * Not part of the menu application — used only to verify correctness during development.
 */
public class DataStructureTest {
    public static void main(String[] args) {
        testBST();
        testQueue();
        testStack();
        testLinkedList();
        System.out.println("\nALL TESTS PASSED");
    }

    private static void testBST() {
        System.out.println("=== BST TEST ===");
        PatientBST bst = new PatientBST();
        int[] ids = {50, 30, 70, 20, 40, 60, 80};
        for (int id : ids) {
            bst.insert(new Patient(id, "P" + id, 30, "000", "Cond" + id));
        }
        assertTrue(bst.size() == 7, "size after 7 inserts");
        bst.inOrderTraversal(); // should print 20,30,40,50,60,70,80

        assertTrue(bst.search(60) != null, "search existing 60");
        assertTrue(bst.search(999) == null, "search missing 999");

        // Delete a leaf
        assertTrue(bst.delete(20), "delete leaf 20");
        assertTrue(bst.search(20) == null, "20 gone after delete");

        // Delete a node with one child (40 has no children now, but 70 has two children: 60,80)
        // Delete node with two children: 70
        assertTrue(bst.delete(70), "delete two-child node 70");
        assertTrue(bst.search(70) == null, "70 gone after delete");
        assertTrue(bst.search(60) != null && bst.search(80) != null, "children of 70 preserved");

        // Delete root
        assertTrue(bst.delete(50), "delete root 50");
        assertTrue(bst.search(50) == null, "root gone");
        bst.inOrderTraversal(); // remaining should still be sorted: 30,40,60,80

        assertTrue(!bst.delete(12345), "deleting non-existent id returns false");
        System.out.println("BST OK\n");
    }

    private static void testQueue() {
        System.out.println("=== QUEUE TEST (FIFO) ===");
        EmergencyQueue q = new EmergencyQueue();
        assertTrue(q.isEmpty(), "queue starts empty");
        assertTrue(q.dequeue() == null, "dequeue on empty returns null, no crash");

        Patient p1 = new Patient(1, "A", 20, "111", "X");
        Patient p2 = new Patient(2, "B", 21, "222", "Y");
        Patient p3 = new Patient(3, "C", 22, "333", "Z");
        q.enqueue(p1);
        q.enqueue(p2);
        q.enqueue(p3);
        assertTrue(q.size() == 3, "size 3 after 3 enqueues");

        Patient out1 = q.dequeue();
        assertTrue(out1.getPatientId() == 1, "FIFO: first out is first in (id 1)");
        Patient out2 = q.dequeue();
        assertTrue(out2.getPatientId() == 2, "FIFO: second out is id 2");
        q.displayQueue(); // only p3 left
        assertTrue(q.size() == 1, "size 1 remaining");
        q.dequeue();
        assertTrue(q.isEmpty(), "queue empty after draining");
        System.out.println("QUEUE OK\n");
    }

    private static void testStack() {
        System.out.println("=== STACK TEST (LIFO) ===");
        TreatmentHistoryStack stack = new TreatmentHistoryStack();
        assertTrue(stack.isEmpty(), "stack starts empty");
        assertTrue(stack.pop() == null, "pop on empty returns null, no crash");

        stack.push(new TreatmentRecord(1, "A", "Treat1"));
        stack.push(new TreatmentRecord(2, "B", "Treat2"));
        stack.push(new TreatmentRecord(3, "C", "Treat3"));
        stack.displayHistory(); // Treat3, Treat2, Treat1

        TreatmentRecord top = stack.pop();
        assertTrue(top.getPatientId() == 3, "LIFO: last in (id 3) is first out");
        assertTrue(stack.size() == 2, "size 2 after one pop");
        System.out.println("STACK OK\n");
    }

    private static void testLinkedList() {
        System.out.println("=== SINGLY LINKED LIST TEST (Visit History) ===");
        VisitLinkedList list = new VisitLinkedList();
        assertTrue(list.isEmpty(), "list starts empty");
        assertTrue(list.searchVisit(1) == null, "search on empty list returns null");
        assertTrue(!list.removeVisit(1), "remove on empty list returns false");

        list.addVisit(new Visit(1, "2026-01-01", "Dr.A", "Flu", "Rest"));
        list.addVisit(new Visit(2, "2026-02-01", "Dr.B", "Cold", "Meds"));
        list.addVisit(new Visit(3, "2026-03-01", "Dr.C", "Cough", "Syrup"));
        assertTrue(list.size() == 3, "size 3 after 3 adds");
        list.displayVisits(); // should show in insertion order 1,2,3

        assertTrue(list.searchVisit(2) != null, "search existing visit 2");
        assertTrue(list.searchVisit(999) == null, "search missing visit");

        assertTrue(list.removeVisit(1), "remove head visit 1");
        assertTrue(list.searchVisit(1) == null, "visit 1 gone after remove");
        assertTrue(list.removeVisit(3), "remove tail visit 3");
        assertTrue(list.size() == 1, "size 1 remaining (visit 2 only)");
        list.displayVisits();
        System.out.println("LINKED LIST OK\n");
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError("FAILED: " + message);
        }
        System.out.println("  [PASS] " + message);
    }
}
