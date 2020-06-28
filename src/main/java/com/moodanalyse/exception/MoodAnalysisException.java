package com.moodanalyse.exception;

/**
 * @author : Amrut
 * Custom Exception class
 */
public class MoodAnalysisException extends Exception {
    /*Use enum for exception type*/
    public enum exceptionType { ENTERED_NULL, ENTERED_EMPTY, NO_SUCH_CLASS, METHOD_INVOCATION_ISSUE, NO_SUCH_METHOD, NO_ACCESS, NULL_VALUE, NO_SUCH_FIELD, FIELD_SETTING_ISSUE };
    exceptionType type;
    /**
     *
     * @param message
     */
    public MoodAnalysisException(String message) {
        super(message);
    }
    /**
     *
     * @param type
     * @param message
     */
    public MoodAnalysisException( exceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
