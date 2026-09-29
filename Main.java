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

}
