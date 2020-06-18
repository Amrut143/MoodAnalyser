package com.moodanalyse;

/**
 * @Author : Amrut
 * Purpose : This class is to analyse the mood
 */
public class MoodAnalyser {
    /**
     *
     * @param message
     * @return : String
     */
    public String analyseMood(String message) {
        if(message.contains("sad"))
            return "SAD";
        else
            return "HAPPY";
    }
}
