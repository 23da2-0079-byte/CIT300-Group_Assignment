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

   