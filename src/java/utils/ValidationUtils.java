package utils;

public class ValidationUtils {
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) return false;
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    
    public static boolean isValidPassword(String password) {
        // Require at least 6 characters
        return password != null && password.length() >= 6;
    }
    
    public static String sanitize(String input) {
        if (input == null) return null;
        // Basic HTML entity encoding to prevent XSS
        return input.replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\"", "&quot;")
                    .replaceAll("'", "&#x27;");
    }
}
