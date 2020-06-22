package com.moodanalyse.exception;

/**
 * @author : Amrut
 * Custom Exception class
 */
public class MoodAnalysisException extends Exception {
    /*Use enum for exception type*/
    public enum exceptionType { ENTERED_NULL, ENTERED_EMPTY };
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
