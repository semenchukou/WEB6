package model.exceptions;

/**
 * Class that represents DAO exception.
 * @version 1.0
 */


public class DAOException extends Exception{
    /**
     * Constructor with specified string
     *
     * @param message string
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Constructor with specified string and exception
     *
     * @param message string
     * @param e       error covered
     */
    public DAOException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * Returns exception's message
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    /**
     * Prints exception's stacktrace
     */
    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
