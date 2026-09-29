import java.util.Scanner;

public class Main {

    static LinkedList studentList = new LinkedList();
    static Stack recentActions = new Stack(20);
    static Queue serviceQueue = new Queue(20);
    static BST studentTree = new BST();
    static HashTable studentHash = new HashTable(50);
    static Graph campusGraph = new Graph(20);
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        setupCampusGraph();

        boolean running = true;
        while (running) {
            printDashboard();
            printMenu();
            System.out.print("Enter your choice: ");
            String choiceInput = scanner.nextLine();
            int choice = InputValidator.parseInteger(choiceInput);

            switch (choice) {
                case 1: addStudentRecord(); break;
                case 2: updateStudentRecord(); break;
                case 3: deleteStudentRecord(); break;
                case 4: studentList.displayAll(); break;
                case 5: addServiceRequest(); break;
                case 6: processNextServiceRequest(); break;
                case 7: recentActions.displayAll(); break;
                case 8: studentTree.displayInOrder(); break;
                case 9: searchStudentUsingHashing(); break;
                case 10: addCampusLocation(); break;
                case 11: removeCampusLocation(); break;
                case 12: addCampusConnection(); break;
                case 13: removeCampusConnection(); break;
                case 14:
                    campusGraph.printAsciiMap();
                    campusGraph.displayConnections();
                    break;
                case 15: traverseCampus(); break;
                case 16:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 16.");
            }

            System.out.println();
        }
        scanner.close();
    }

    private static void setupCampusGraph() {
        campusGraph.addLocation("Research Building");
        campusGraph.addLocation("Hubs");
        campusGraph.addLocation("Offices");
        campusGraph.addLocation("Main Building");
        campusGraph.addLocation("Library");
        campusGraph.addLocation("Gym");

        campusGraph.addConnection("Research Building", "Main Building");
        campusGraph.addConnection("Research Building", "Offices");
        campusGraph.addConnection("Research Building", "Hubs");
        campusGraph.addConnection("Offices", "Main Building");
        campusGraph.addConnection("Offices", "Hubs");
        campusGraph.addConnection("Hubs", "Main Building");
        campusGraph.addConnection("Hubs", "Gym");
        campusGraph.addConnection("Main Building", "Library");
    }

// ----------------  Dashboard ----------------
private static void printDashboard() {
        System.out.println("╔══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                       SLTC STUDENT RECORD SYSTEM                         ║");
        System.out.println("║                              Group No: 21                                ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║                                                                          ║");
        System.out.println("║  ┌────────────────────┐   ┌────────────────────┐   ┌────────────────────┐ ║");
        System.out.println("║  │   STUDENT CARD     │   │    QUEUE CARD      │   │   LOCATIONS CARD   │ ║");
        System.out.println("║  ├────────────────────┤   ├────────────────────┤   ├────────────────────┤ ║");
        System.out.println("║  │ Total Students     │   │ Pending Queue      │   │ Total Locations    │ ║");
        System.out.printf( "║  │ %-18s │   │ %-18s │   │ %-18s │ ║%n",
                studentList.getSize(),
                serviceQueue.getCount(),
                campusGraph.getLocationCount());
        System.out.println("║  │                    │   │                    │   │                    │ ║");
        System.out.printf( "║  │                    │   │                    │   │ Connections: %-5s │ ║%n",
                campusGraph.getConnectionCount());
        System.out.println("║  └────────────────────┘   └────────────────────┘   └────────────────────┘ ║");
        System.out.println("║                                                                          ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  RECENT ACTIONS                                                          ║");
        System.out.println("╟──────────────────────────────────────────────────────────────────────────╢");
        String[] lastActions = recentActions.getLastActions(3);
        if (lastActions.length == 0) {
            System.out.println("║  No actions recorded yet.                                                ║");
        } else {
            for (int i = 0; i < lastActions.length; i++) {
                String line = "  " + (i + 1) + ". " + lastActions[i];
                System.out.printf("║ %-72s ║%n", line);
            }
        }
        System.out.println("╚══════════════════════════════════════════════════════════════════════════╝");
    }

    
 // ----------------  Menu ----------------
    private static void printMenu() {
        System.out.println("1.  Add Student Record");
        System.out.println("2.  Update Student Record");
        System.out.println("3.  Delete Student Record");
        System.out.println("4.  Display All Records using Linked List");
        System.out.println("5.  Add Service Request to Queue");
        System.out.println("6.  Process Next Service Request");
        System.out.println("7.  Display Recent Actions using Stack");
        System.out.println("8.  Display Students using BST");
        System.out.println("9.  Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS or DFS");
        System.out.println("16. Exit");
    }

    // ---------------- Student Record Menu ----------------

    private static void addStudentRecord() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        if (!InputValidator.isNotEmpty(id)) {
            System.out.println("Student ID cannot be empty.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        if (!InputValidator.isNotEmpty(name)) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter Programme: ");
        String programme = scanner.nextLine();
        if (!InputValidator.isNotEmpty(programme)) {
            System.out.println("Programme cannot be empty.");
            return;
        }

        System.out.print("Enter Marks (0-100): ");
        String marksInput = scanner.nextLine();
        double marks = InputValidator.parseDouble(marksInput);
        if (!InputValidator.isValidMarks(marks)) {
            System.out.println("Invalid marks. Marks must be a number between 0 and 100.");
            return;
        }

        Student newStudent = new Student(id, name, programme, marks);
        boolean added = studentList.addStudent(newStudent);

        if (!added) {
            System.out.println("A student with ID " + id + " already exists.");
            return;
        }


        studentTree.insert(newStudent);
        studentHash.insert(newStudent);

        recentActions.push("Added student " + id);
        System.out.println("Student added successfully.");
    }

    private static void updateStudentRecord() {
        System.out.print("Enter Student ID to update: ");
        String id = scanner.nextLine();

        Student existing = studentList.findStudent(id);
        if (existing == null) {
            System.out.println("No student found with ID " + id);
            return;
        }

        System.out.print("Enter new Name: ");
        String name = scanner.nextLine();
        if (!InputValidator.isNotEmpty(name)) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter new Programme: ");
        String programme = scanner.nextLine();
        if (!InputValidator.isNotEmpty(programme)) {
            System.out.println("Programme cannot be empty.");
            return;
        }

        System.out.print("Enter new Marks (0-100): ");
        String marksInput = scanner.nextLine();
        double marks = InputValidator.parseDouble(marksInput);
        if (!InputValidator.isValidMarks(marks)) {
            System.out.println("Invalid marks. Marks must be a number between 0 and 100.");
            return;
        }

        studentList.updateStudent(id, name, programme, marks);

        recentActions.push("Updated student " + id);
        System.out.println("Student updated successfully.");
    }

    private static void deleteStudentRecord() {
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine();

        Student removed = studentList.deleteStudent(id);
        if (removed == null) {
            System.out.println("No student found with ID " + id);
            return;
        }

        studentTree.delete(id);
        studentHash.remove(id);

        recentActions.push("Deleted student " + id);
        System.out.println("Student deleted successfully.");
    }

       // ---------------- Queue Menu Option ----------------

    private static void addServiceRequest() {
        System.out.print("Enter Student ID for the request: ");
        String id = scanner.nextLine();
        if (!InputValidator.isNotEmpty(id)) {
            System.out.println("Student ID cannot be empty.");
            return;
        }

        System.out.print("Enter Request Description: ");
        String description = scanner.nextLine();
        if (!InputValidator.isNotEmpty(description)) {
            System.out.println("Description cannot be empty.");
            return;
        }

        String requestText = "Student " + id + " - " + description;
        boolean success = serviceQueue.enqueue(requestText);

        if (success) {
            recentActions.push("Added service request for " + id);
            System.out.println("Service request added.");
        }
    }

    private static void processNextServiceRequest() {
        String request = serviceQueue.dequeue();
        if (request == null) {
            System.out.println("No pending service requests.");
            return;
        }
        System.out.println("Processing request: " + request);
        recentActions.push("Processed request: " + request);
    }

    // ---------------- Hashing Menu Option ----------------

    private static void searchStudentUsingHashing() {
        System.out.print("Enter Student ID to search: ");
        String id = scanner.nextLine();

        Student found = studentHash.search(id);
        if (found == null) {
            System.out.println("No student found with ID " + id);
        } else {
            System.out.println("Student found:");
            found.display();
        }
    }

       // ---------------- Graph Menu Options ----------------

    private static void addCampusLocation() {
        System.out.print("Enter new Campus Location name: ");
        String name = scanner.nextLine();
        if (!InputValidator.isNotEmpty(name)) {
            System.out.println("Location name cannot be empty.");
            return;
        }

        boolean added = campusGraph.addLocation(name);
        if (added) {
            recentActions.push("Added campus location " + name);
            System.out.println("Location added successfully.");
        } else {
            System.out.println("That location already exists or the map is full.");
        }
    }

    private static void removeCampusLocation() {
        System.out.print("Enter Campus Location name to remove: ");
        String name = scanner.nextLine();

        boolean removed = campusGraph.removeLocation(name);
        if (removed) {
            recentActions.push("Removed campus location " + name);
            System.out.println("Location removed successfully.");
        } else {
            System.out.println("Location not found: " + name);
        }
    }

    private static void addCampusConnection() {
        System.out.print("Enter first Location name: ");
        String from = scanner.nextLine();
        System.out.print("Enter second Location name: ");
        String to = scanner.nextLine();

        boolean added = campusGraph.addConnection(from, to);
        if (added) {
            recentActions.push("Added connection: " + from + " - " + to);
            System.out.println("Connection added successfully.");
        } else {
            System.out.println("Could not add connection. Check that both locations exist.");
        }
    }

    private static void removeCampusConnection() {
        System.out.print("Enter first Location name: ");
        String from = scanner.nextLine();
        System.out.print("Enter second Location name: ");
        String to = scanner.nextLine();

        boolean removed = campusGraph.removeConnection(from, to);
        if (removed) {
            recentActions.push("Removed connection: " + from + " - " + to);
            System.out.println("Connection removed successfully.");
        } else {
            System.out.println("That connection was not available.");
        }
    }

    private static void traverseCampus() {
        campusGraph.printAsciiMap();

        System.out.print("Traverse using (1) BFS or (2) DFS? Enter 1 or 2: ");
        String choiceInput = scanner.nextLine();
        int choice = InputValidator.parseInteger(choiceInput);

        System.out.print("Enter starting Location name: ");
        String start = scanner.nextLine();

        if (!campusGraph.locationExists(start)) {
            System.out.println("Starting location not found: " + start);
            return;
        }

        if (choice == 1) {
            campusGraph.bfsTraversal(start);
            recentActions.push("Ran BFS traversal from " + start);
        } else if (choice == 2) {
            campusGraph.dfsTraversal(start);
            recentActions.push("Ran DFS traversal from " + start);
        } else {
            System.out.println("Invalid choice. Please enter 1 for BFS or 2 for DFS.");
        }
    }

}
