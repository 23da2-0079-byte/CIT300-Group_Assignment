public class InputValidator {

    // ---------------  Check string is not null 
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    // --------------- Check marks (0 to 100)
    public static boolean isValidMarks(double marks) {
        return marks >= 0 && marks <= 100;
    }

}