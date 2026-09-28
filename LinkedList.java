public class LinkedList {

    // ------------------ Node class
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

    // ------------------ Add a new student 
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


    // ------------------ Search student
     public Student findStudent(String studentId) {
        Node current = head;
        while (current != null) {
            if (current.student.studentId.equalsIgnoreCase(studentId)) {
                return current.student;
            }
            current = current.next;
        }
        return null;
    }

    // ------------------ Update student details
    public boolean updateStudent(String studentId, String name, String programme, double marks) {
        Student s = findStudent(studentId);
        if (s == null) {
            return false;
        }
        s.name = name;
        s.programme = programme;
        s.marks = marks;
        return true;
    }


}