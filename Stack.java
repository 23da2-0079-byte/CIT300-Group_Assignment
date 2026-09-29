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
       // Remove recent action.
    public String pop() {
        if (isEmpty()) {
            return null;
        }
        String action = actions[top];
        actions[top] = null;
        top--;
        return action;
    }

    // Show all action 
    public void displayAll() {
        if (isEmpty()) {
            System.out.println("No recent actions recorded.");
            return;
        }
        System.out.println("---- Recent Actions (Stack) ----");
        int count = 1;
        for (int i = top; i >= 0; i--) {
            System.out.println(count + ". " + actions[i]);
            count++;
        }
    }

    public int getCount() {
        return top + 1;
    }

    // Return last actions.
    public String[] getLastActions(int count) {
        int actualCount = Math.min(count, getCount());
        String[] result = new String[actualCount];
        for (int i = 0; i < actualCount; i++) {
            result[i] = actions[top - i];
        }
        return result;
    }
}


