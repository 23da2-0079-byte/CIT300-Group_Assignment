public class Stack {
    private String[] actions;
    private int top;
    private int capacity;

    public Stack(int capacity) {
        this.capacity = capacity;
        this.actions = new String[capacity];
        this.top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    // Push new action

    public void push(String action) {
        if (isFull()) {
            for (int i = 0; i < capacity - 1; i++) {
                actions[i] = actions[i + 1];
            }
            actions[capacity - 1] = action;
        } else {
            top++;
            actions[top] = action;
        }
    }