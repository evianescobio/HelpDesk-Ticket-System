/**
 * This class provides helper methods for validating user input.
 * It checks text input, numeric ranges, positive numbers, and integer values.
 */

package utils;

public class InputValidator {

    public static boolean isNotBlank(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isWithinRange(int input, int min, int max) {
        return input >= min && input <= max;
    }

    public static boolean isPositive(int input) {
        return input > 0;
    }
    
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
