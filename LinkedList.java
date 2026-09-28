public class LinkedList {

    // Node class
    class Node {
        Student student;
        Node next;

        Node(Student student) {
            this.student = student;
            this.next = null;
        }
    }

    Node head;
    int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    // Add a new student 
    public boolean addStudent(Student newStudent) {
        if (findStudent(newStudent.studentId) != null) {
            return false;
        }

        Node newNode = new Node(newStudent);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        size++;
        return true;
    }
}