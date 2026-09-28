public class Queue {
    private String[] requests;
    private int front;
    private int rear;
    private int count;
    private int capacity;

    public Queue(int capacity) {
        this.capacity = capacity;
        this.requests = new String[capacity];
        this.front = 0;
        this.rear = -1;
        this.count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == capacity;
    }

    // Add new request
    public boolean enqueue(String request) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot add more requests right now.");
            return false;
        }
        rear = (rear + 1) % capacity;
        requests[rear] = request;
        count++;
        return true;
    }