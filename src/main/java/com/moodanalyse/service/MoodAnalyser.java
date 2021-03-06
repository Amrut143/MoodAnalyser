package com.moodanalyse.service;

import com.moodanalyse.exception.MoodAnalysisException;

/**
 * @Author : Amrut
 * Purpose : This class is to analyse the mood
 */
public class MoodAnalyser {
    private String message;

    /*Default constructor*/
    public MoodAnalyser() {
    }

    /**
     * @param message
     */
    public MoodAnalyser(String message) {
        this.message = message;
    }

    public String analyseMood(String message) throws MoodAnalysisException {
        this.message = message;
        return analyseMood();
    }
    /*function to analyse mood and handle exception*/
    public String analyseMood() throws MoodAnalysisException {
        try {
            if (message.length() == 0)
                throw new MoodAnalysisException(MoodAnalysisException.exceptionType.ENTERED_EMPTY, "ENTERED_EMPTY");
            else if (message.contains("sad"))
                return "SAD";
            else
                return "HAPPY";
        } catch (NullPointerException e) {
            throw new MoodAnalysisException(MoodAnalysisException.exceptionType.ENTERED_NULL, "ENTERED_NULL");
        }
    }

    @Override
    public boolean equals(Object another) {
        if (this.message.equals(((MoodAnalyser) another).message))
            return true;
        return false;
    }
}
