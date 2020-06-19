package com.moodanalyse;

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
     *
     * @param message
     */
    public MoodAnalyser(String message) {
        this.message = message;
    }
    /*function to analyse mood*/
    public String analyseMood() {
        if(message.contains("sad"))
            return "SAD";
        else
            return "HAPPY";
    }
}
