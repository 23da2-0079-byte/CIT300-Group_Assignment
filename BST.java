public class BST {

    class Node {
        Student student;
        Node left;
        Node right;

        Node(Student student) {
            this.student = student;
            this.left = null;
            this.right = null;
        }
    }

    Node root;

     public BST() {
        root = null;
    }

    // Insert a student into the correct position in the tree.
    public void insert(Student student) {
        root = insertNode(root, student);
    }

    private Node insertNode(Node node, Student student) {
        if (node == null) {
            return new Node(student);
        }

        int compareResult = student.studentId.compareToIgnoreCase(node.student.studentId);

        if (compareResult < 0) {
            node.left = insertNode(node.left, student);
        } else if (compareResult > 0) {
            node.right = insertNode(node.right, student);
        }
        // if compareResult == 0, the ID already exists in the tree, so we do nothing here
        // (duplicate IDs are already blocked when adding to the LinkedList).

        return node;
    }

    public Student search(String studentId) {
        return searchNode(root, studentId);
    }

    private Student searchNode(Node node, String studentId) {
        if (node == null) {
            return null;
        }

        int compareResult = studentId.compareToIgnoreCase(node.student.studentId);

        if (compareResult == 0) {
            return node.student;
        } else if (compareResult < 0) {
            return searchNode(node.left, studentId);
        } else {
            return searchNode(node.right, studentId);
        }
    }

    // Remove a student from the tree by ID.
    public void delete(String studentId) {
        root = deleteNode(root, studentId);
    }

    private Node deleteNode(Node node, String studentId) {
        if (node == null) {
            return null;
        }

        int compareResult = studentId.compareToIgnoreCase(node.student.studentId);

        if (compareResult < 0) {
            node.left = deleteNode(node.left, studentId);
        } else if (compareResult > 0) {
            node.right = deleteNode(node.right, studentId);
        } else {
            // Found the node to delete
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            // Node has two children: find the smallest value in the right subtree
            Node successor = findMin(node.right);
            node.student = successor.student;
            node.right = deleteNode(node.right, successor.student.studentId);
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Print all students in sorted order by Student ID (in-order traversal).
    public void displayInOrder() {
        if (root == null) {
            System.out.println("No student records in the tree.");
            return;
        }
        System.out.println("---- Students Sorted by ID (BST In-Order) ----");
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            node.student.display();
            inOrderTraversal(node.right);
        }
    }
}
