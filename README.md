# Mini Hospital Emergency Management System

A console-based Java application built for the CIT300 Data Structures and Algorithms
Individual Mid Assignment. It simulates patient registration, emergency treatment
queuing, treatment history tracking, and patient visit history — each backed by a
specific core data structure implemented **from scratch** (no `java.util` collections
used for the required structures).

## Data Structures Used

| Requirement | Data Structure | Class |
|---|---|---|
| Patient Records | Binary Search Tree (BST), keyed by Patient ID | `PatientBST` |
| Emergency Patient Queue | Queue (FIFO), linked-node based | `EmergencyQueue` |
| Treatment History | Stack (LIFO), linked-node based | `TreatmentHistoryStack` |
| Patient Visit History | Singly Linked List (one per patient) | `VisitLinkedList` |

## Project Structure

```
HospitalEMS/
├── src/hospital/
│   ├── Patient.java                  # Patient record model (BST payload)
│   ├── PatientBST.java               # BST: insert, search, delete, in-order traversal
│   ├── EmergencyQueue.java           # Queue: enqueue, dequeue, display, empty handling
│   ├── TreatmentRecord.java          # Treatment record model (Stack payload)
│   ├── TreatmentHistoryStack.java    # Stack: push, pop, display, empty handling
│   ├── Visit.java                    # Visit model (Linked List payload)
│   ├── VisitLinkedList.java          # Singly Linked List: add, remove, search, display
│   ├── HospitalManagementSystem.java # Main class - console menu integrating everything
│   └── DataStructureTest.java        # Standalone correctness checks for all 4 structures
├── bin/                               # Compiled .class files (generated)
└── README.md
```

## How Everything Connects

- Each `Patient` is stored as a node in the `PatientBST`, keyed by Patient ID.
- Each `Patient` also owns its own `VisitLinkedList`, holding that patient's past visits.
- When a patient needs emergency care, their existing `Patient` object (looked up from
  the BST) is enqueued into the `EmergencyQueue`.
- When a patient is dequeued and treated, a `TreatmentRecord` is created and pushed onto
  the shared `TreatmentHistoryStack`.

This means the BST is the single source of truth for patient identity, while the Queue,
Stack, and each patient's Linked List represent different "views" of that same patient
data over time.

## How to Compile and Run

Requires JDK 17+ (developed and tested on JDK 21).

```bash
cd HospitalEMS
javac -d bin src/hospital/*.java
java -cp bin hospital.HospitalManagementSystem
```

To run the standalone data-structure correctness checks instead of the interactive menu:

```bash
java -cp bin hospital.DataStructureTest
```

## Menu Overview

```
1. Patient Records (BST)
   1. Insert new patient
   2. Search patient by ID
   3. Delete patient by ID
   4. Display all patients (in-order traversal)

2. Emergency Patient Queue (Queue)
   1. Enqueue patient (add to waiting queue)
   2. Dequeue patient (send next patient for treatment -> logs a TreatmentRecord)
   3. Display all waiting patients

3. Treatment History (Stack)
   1. Push a completed treatment record manually
   2. Pop most recent treatment record
   3. Display treatment history

4. Patient Visit History (Singly Linked List)
   1. Add a new visit for a patient
   2. Remove a visit for a patient
   3. Search for a visit for a patient
   4. Display a patient's visit history
```

## Design Decisions

- **Linked-node structures over arrays**: The Queue, Stack, and Visit Linked List are
  all implemented with custom node classes (not `ArrayList`/`LinkedList` from the JDK)
  to demonstrate the underlying mechanics required by the assignment.
- **BST deletion (two-children case)**: Uses the in-order successor (smallest node in
  the right subtree) to replace the deleted node, preserving BST ordering.
- **Duplicate Patient IDs**: Inserting a patient with an ID that already exists in the
  BST updates the existing record rather than creating a duplicate node, since Patient
  ID is meant to be a unique key.
- **Empty-structure handling**: `dequeue()` on an empty queue and `pop()` on an empty
  stack print a clear message and return `null` instead of throwing an exception.
- **Separation of concerns**: Each data structure is its own class with a single
  responsibility; `HospitalManagementSystem` only orchestrates user interaction and
  wires the structures together.

## Testing

`DataStructureTest.java` exercises all four data structures directly (bypassing the
console menu) and asserts on:
- BST: insert, search (hit/miss), delete of leaf / two-children / root nodes, in-order
  traversal ordering, deleting a non-existent ID.
- Queue: FIFO ordering, empty-queue dequeue, display, size tracking.
- Stack: LIFO ordering, empty-stack pop, display, size tracking.
- Linked List: add, search (hit/miss), remove head/tail, display ordering, empty-list
  operations.

All checks pass (`ALL TESTS PASSED`).

## Notes on Assignment Submission

Per the assignment brief, this repository/source code should be committed to GitHub
progressively (project structure → BST → queue → stack → linked list → testing →
README), accompanied by a demonstration video showing the running system, the GitHub
commit history, and an explanation of each data structure and key design decisions.
