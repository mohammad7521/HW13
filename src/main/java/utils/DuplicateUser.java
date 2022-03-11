package utils;

public class DuplicateUser extends RuntimeException {
    public DuplicateUser(String message) {
        super(message);
    }
}
