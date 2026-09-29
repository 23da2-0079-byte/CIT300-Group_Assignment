public class Graph {
    private String[] locations;
    private boolean[][] adjMatrix;
    private int locationCount;
    private int maxLocations;

    public Graph(int maxLocations) {
        this.maxLocations = maxLocations;
        this.locations = new String[maxLocations];
        this.adjMatrix = new boolean[maxLocations][maxLocations];
        this.locationCount = 0;
    }

    // Find index of a location by its name
    private int findIndex(String name) {
        for (int i = 0; i < locationCount; i++) {
            if (locations[i].equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    // Add new location 
    public boolean addLocation(String name) {
        if (locationCount >= maxLocations) {
            System.out.println("Cannot add more locations, the campus map is full.");
            return false;
        }
        if (findIndex(name) != -1) {
            return false; 
        }
        locations[locationCount] = name;
        locationCount++;
        return true;
    }
	
    // Remove location 
    public boolean removeLocation(String name) {
        int index = findIndex(name);
        if (index == -1) {
            return false;
        }

        // Shift locations 
        for (int i = index; i < locationCount - 1; i++) {
            locations[i] = locations[i + 1];
        }
        locations[locationCount - 1] = null;

        // Shift matrix rows up
        for (int i = index; i < locationCount - 1; i++) {
            for (int j = 0; j < locationCount; j++) {
                adjMatrix[i][j] = adjMatrix[i + 1][j];
            }
        }
        // Shift matrix columns left
        for (int j = index; j < locationCount - 1; j++) {
            for (int i = 0; i < locationCount; i++) {
                adjMatrix[i][j] = adjMatrix[i][j + 1];
            }
        }

        locationCount--;
        return true;
    }
	
    // Add a connection
    public boolean addConnection(String from, String to) {
        int i = findIndex(from);
        int j = findIndex(to);
        if (i == -1 || j == -1) {
            return false;
        }
        adjMatrix[i][j] = true;
        adjMatrix[j][i] = true;
        return true;
    }

    // Remove a connection 
    public boolean removeConnection(String from, String to) {
        int i = findIndex(from);
        int j = findIndex(to);
        if (i == -1 || j == -1) {
            return false;
        }
        adjMatrix[i][j] = false;
        adjMatrix[j][i] = false;
        return true;
    }

    public boolean locationExists(String name) {
        return findIndex(name) != -1;
    }

    // Print all location 
    public void displayConnections() {
        if (locationCount == 0) {
            System.out.println("No campus locations added yet.");
            return;
        }
        System.out.println("---- Campus Locations and Connections ----");
        for (int i = 0; i < locationCount; i++) {
            System.out.print(locations[i] + " -> ");
            boolean hasConnection = false;
            for (int j = 0; j < locationCount; j++) {
                if (adjMatrix[i][j]) {
                    System.out.print(locations[j] + "   ");
                    hasConnection = true;
                }
            }
            if (!hasConnection) {
                System.out.print("(no connections)");
            }
            System.out.println();
        }
    }
	
    // Print a simple ASCII drawing 

    public void printAsciiMap() {
        System.out.println("---- Campus Map (ASCII) ----");
        System.out.println("                [Library]");
        System.out.println("                    |");
        System.out.println("   [Research Bldg]--[Main Building]");
        System.out.println("        |    \\            |");
        System.out.println("        |     \\------[Offices]");
        System.out.println("        |               |");
        System.out.println("      [Hubs]-------------+");
        System.out.println("        |");
        System.out.println("      [Gym]");
        System.out.println();
    }

    // Breadth-First Search traversal

    public void bfsTraversal(String startLocation) {
        int startIndex = findIndex(startLocation);
        if (startIndex == -1) {
            System.out.println("Starting location not found.");
            return;
        }

        boolean[] visited = new boolean[locationCount];
        String[] queue = new String[locationCount];
        int front = 0;
        int rear = -1;
        int count = 0;

        rear++;
        queue[rear] = locations[startIndex];
        count++;
        visited[startIndex] = true;

        System.out.println("---- BFS Traversal starting from " + startLocation + " ----");
        while (count > 0) {
            String current = queue[front];
            front++;
            count--;
            System.out.print(current + "   ");

            int currentIndex = findIndex(current);
            for (int j = 0; j < locationCount; j++) {
                if (adjMatrix[currentIndex][j] && !visited[j]) {
                    visited[j] = true;
                    rear++;
                    queue[rear] = locations[j];
                    count++;
                }
            }
        }
        System.out.println();
    }
	   // Depth-First Search traversal

    public void dfsTraversal(String startLocation) {
        int startIndex = findIndex(startLocation);
        if (startIndex == -1) {
            System.out.println("Starting location not found.");
            return;
        }
        boolean[] visited = new boolean[locationCount];
        System.out.println("---- DFS Traversal starting from " + startLocation + " ----");
        dfsHelper(startIndex, visited);
        System.out.println();
    }

    private void dfsHelper(int index, boolean[] visited) {
        visited[index] = true;
        System.out.print(locations[index] + "   ");
        for (int j = 0; j < locationCount; j++) {
            if (adjMatrix[index][j] && !visited[j]) {
                dfsHelper(j, visited);
            }
        }
    }

    public int getLocationCount() {
        return locationCount;
    }

    // Count unique connections 
    public int getConnectionCount() {
        int total = 0;
        for (int i = 0; i < locationCount; i++) {
            for (int j = i + 1; j < locationCount; j++) {
                if (adjMatrix[i][j]) {
                    total++;
                }
            }
        }
        return total;
    }
}


