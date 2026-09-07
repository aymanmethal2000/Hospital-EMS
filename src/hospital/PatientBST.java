package hospital;

/**
 * Binary Search Tree (BST) used to store Patient Records, keyed by Patient ID.
 *
 * Supports:
 *  - insert(Patient)
 *  - search(patientId)
 *  - delete(patientId)
 *  - inOrderTraversal() -> prints patients in ascending order of Patient ID
 */
public class PatientBST {

    /** Internal BST node holding a Patient. */
    private class Node {
        Patient patient;
        Node left, right;

        Node(Patient patient) {
            this.patient = patient;
        }
    }

    private Node root;
    private int size;

    /** Insert a new patient into the tree, keyed by patientId. */
    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private Node insertRec(Node node, Patient patient) {
        if (node == null) {
            size++;
            return new Node(patient);
        }
        if (patient.getPatientId() < node.patient.getPatientId()) {
            node.left = insertRec(node.left, patient);
        } else if (patient.getPatientId() > node.patient.getPatientId()) {
            node.right = insertRec(node.right, patient);
        } else {
            // Duplicate ID: update existing record instead of inserting a duplicate node.
            node.patient = patient;
        }
        return node;
    }

    /** Search for a patient using their Patient ID. Returns null if not found. */
    public Patient search(int patientId) {
        Node current = root;
        while (current != null) {
            if (patientId == current.patient.getPatientId()) {
                return current.patient;
            } else if (patientId < current.patient.getPatientId()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    /** Delete a patient record by Patient ID. Returns true if a record was removed. */
    public boolean delete(int patientId) {
        if (search(patientId) == null) {
            return false;
        }
        root = deleteRec(root, patientId);
        size--;
        return true;
    }

    private Node deleteRec(Node node, int patientId) {
        if (node == null) {
            return null;
        }

        if (patientId < node.patient.getPatientId()) {
            node.left = deleteRec(node.left, patientId);
        } else if (patientId > node.patient.getPatientId()) {
            node.right = deleteRec(node.right, patientId);
        } else {
            // Found the node to delete.

            // Case 1: no children
            if (node.left == null && node.right == null) {
                return null;
            }
            // Case 2: one child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            // Case 3: two children -> replace with the in-order successor
            // (smallest value in the right subtree)
            Node successor = findMin(node.right);
            node.patient = successor.patient;
            node.right = deleteRec(node.right, successor.patient.getPatientId());
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /** Print all patients in ascending order of Patient ID (in-order traversal). */
    public void inOrderTraversal() {
        if (root == null) {
            System.out.println("No patient records found.");
            return;
        }
        System.out.println("---- Patient Records (Ascending Patient ID) ----");
        inOrderRec(root);
    }

    private void inOrderRec(Node node) {
        if (node == null) {
            return;
        }
        inOrderRec(node.left);
        System.out.println(node.patient);
        inOrderRec(node.right);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return root == null;
    }
}
