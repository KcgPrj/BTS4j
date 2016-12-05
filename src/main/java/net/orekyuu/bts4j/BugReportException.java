package net.orekyuu.bts4j;

public final class BugReportException extends RuntimeException {

    public BugReportException(String message, Throwable cause) {
        super(message, cause);
    }

    public BugReportException(String message) {
        super(message);
    }

    public BugReportException(Throwable cause) {
        super(cause);
    }
}
