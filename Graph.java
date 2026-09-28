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