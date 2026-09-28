public class InputValidator {

    // ---------------  Check string is not null 
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    // --------------- Check marks (0 to 100)
    public static boolean isValidMarks(double marks) {
        return marks >= 0 && marks <= 100;
    }

     // --------------- convert text to  whole number
    public static int parseInteger(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (Exception e) {
            return -1;
        }
    }

     // --------------- convert text to decimal 
    public static double parseDouble(String input) {
        try {
            return Double.parseDouble(input.trim());
        } catch (Exception e) {
            return -1;
        }
    }

}