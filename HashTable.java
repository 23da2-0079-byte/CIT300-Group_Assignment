public class HashTable {
    private Student[] table;
    private int capacity;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.table = new Student[capacity];
    }

    // A simple hash function: add up the character codes of the ID
    // and use the remainder to pick a slot in the table.
    private int hash(String studentId) {
        int sum = 0;
        for (int i = 0; i < studentId.length(); i++) {
            sum = sum + studentId.charAt(i);
        }
        return sum % capacity;
    }

   // Insert a student into the hash table.
    // Returns false if the ID already exists or the table is full.
    public boolean insert(Student student) {
        int index = hash(student.studentId);
        int startIndex = index;

        while (table[index] != null) {
            if (table[index].studentId.equalsIgnoreCase(student.studentId)) {
                return false;
            }
            index = (index + 1) % capacity;
            if (index == startIndex) {
                System.out.println("Hash table is full.");
                return false;
            }
        }

        table[index] = student;
        return true;
    }

    // Search student by ID. Returns the Student, or null if not found.
    public Student search(String studentId) {
        int index = hash(studentId);
        int startIndex = index;

        while (table[index] != null) {
            if (table[index].studentId.equalsIgnoreCase(studentId)) {
                return table[index];
            }
            index = (index + 1) % capacity;
            if (index == startIndex) {
                break;
            }
        }
        return null;
    }

    // Remove a student from the hash table by ID.
    public boolean remove(String studentId) {
        int index = hash(studentId);
        int startIndex = index;

        while (table[index] != null) {
            if (table[index].studentId.equalsIgnoreCase(studentId)) {
                table[index] = null;
                return true;
            }
            index = (index + 1) % capacity;
            if (index == startIndex) {
                break;
            }
        }
        return false;
    }
}
