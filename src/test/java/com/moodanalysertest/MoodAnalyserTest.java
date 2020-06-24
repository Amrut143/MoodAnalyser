package com.moodanalysertest;

import com.moodanalyse.service.MoodAnalyser;
import com.moodanalyse.exception.MoodAnalysisException;
import org.junit.Assert;
import org.junit.Test;

public class MoodAnalyserTest {

    @Test
    public void givenMessage_WhenSad_ShouldReturnSad() {
        MoodAnalyser moodAnalyser = new MoodAnalyser("I am in sad Mood");
        String mood = null;
        try {
            mood = moodAnalyser.analyseMood();
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("SAD", mood);
    }
    @Test
    public void givenMessage_WhenIn_AnyMood_ShouldReturnHappy() {
        MoodAnalyser moodAnalyser = new MoodAnalyser("I am in happy Mood");
        String mood = null;
        try {
            mood = moodAnalyser.analyseMood();
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("HAPPY", mood);
    }
    @Test
    public void whenMood_IsNull_ShouldReturnHappy() {
        MoodAnalyser moodAnalyser = new MoodAnalyser(null);
        String mood = null;
        try {
            mood = moodAnalyser.analyseMood();
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("HAPPY",mood);
    }
    @Test
    public void whenMood_IsNull_ShouldReturn_CustomException(){
        MoodAnalyser moodAnalyser = new MoodAnalyser(null);
        try {
            String mood = moodAnalyser.analyseMood();
        } catch (MoodAnalysisException e) {
            Assert.assertEquals("MoodAnalysisException",e.getMessage());
        }
    }
    @Test
    public void whenMood_IsNull_ShouldReturn_CustomException_WithExceptionType(){
        MoodAnalyser moodAnalyser = new MoodAnalyser(null);
        try {
            String mood = moodAnalyser.analyseMood();
        } catch (MoodAnalysisException e ) {
            Assert.assertEquals("ENTERED_NULL",e.getMessage());
        }
    }
    @Test
    public void whenMood_IsEmpty_ShouldReturn_CustomException_WithExceptionType(){
        MoodAnalyser moodAnalyser = new MoodAnalyser("");
        try {
            String mood = moodAnalyser.analyseMood();
        } catch (MoodAnalysisException e ) {
            Assert.assertEquals("ENTERED_EMPTY",e.getMessage());
        }
    }
}