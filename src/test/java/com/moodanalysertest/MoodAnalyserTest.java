package com.moodanalysertest;

import com.moodanalyse.service.MoodAnalyser;
import com.moodanalyse.exception.MoodAnalysisException;
import com.moodanalyse.utility.MoodAnalyserReflector;
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
        Assert.assertEquals("HAPPY", mood);
    }

    @Test
    public void whenMood_IsNull_ShouldReturn_CustomException() {
        MoodAnalyser moodAnalyser = new MoodAnalyser(null);
        try {
            String mood = moodAnalyser.analyseMood();
        } catch (MoodAnalysisException e) {
            Assert.assertEquals("MoodAnalysisException", e.getMessage());
        }
    }

    @Test
    public void whenMood_IsNull_ShouldReturn_CustomException_WithExceptionType() {
        MoodAnalyser moodAnalyser = new MoodAnalyser(null);
        try {
            String mood = moodAnalyser.analyseMood();
        } catch (MoodAnalysisException e) {
            Assert.assertEquals("ENTERED_NULL", e.getMessage());
        }
    }

    @Test
    public void whenMood_IsEmpty_ShouldReturn_CustomException_WithExceptionType() {
        MoodAnalyser moodAnalyser = new MoodAnalyser("");
        try {
            String mood = moodAnalyser.analyseMood();
        } catch (MoodAnalysisException e) {
            Assert.assertEquals("ENTERED_EMPTY", e.getMessage());
        }
    }

    @Test
    public void givenMoodAnalyzerClass_ShouldReturnMoodAnalyzerObject() {
        try {
            MoodAnalyser moodAnalyser = MoodAnalyserReflector.createMoodAnalyserObject("com.moodanalyse.service.MoodAnalyser");
            Assert.assertEquals(new MoodAnalyser(), moodAnalyser);
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMoodAnalyserClass_WhenNotProper_ShouldReturn_CustomException_WithNoSuchClass() {
        try {
            MoodAnalyser moodAnalyser = MoodAnalyserReflector.createMoodAnalyserObject("com.moodanalyse.service.MoodAnalyzer");
        } catch (MoodAnalysisException e) {
            Assert.assertEquals("NO_SUCH_CLASS_ERROR", e.getMessage());
        }
    }

    @Test
    public void whenMood_ConstructorIsNotProper_ShouldReturn_CustomException_WithNoSuchMethod() {
        try {
            MoodAnalyser moodAnalyser = MoodAnalyserReflector.createMoodAnalyserObject("mood");
        } catch (MoodAnalysisException e) {
            Assert.assertEquals("NO_SUCH_METHOD_ERROR", e.getMessage());
        }
    }

    @Test
    public void givenMoodAnalyzerClass_IsEquals_ShouldReturnMoodAnalyzerObject() {
        MoodAnalyser moodAnalyser;
        try {
            moodAnalyser = MoodAnalyserReflector.createMoodAnalyserObject("I am in happy mood");
            boolean result = moodAnalyser.equals(new MoodAnalyser("I m in Happy Mood."));
            Assert.assertTrue(result);
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMoodAnalyserClass_WhenNotProper_ByParametrizedConstructor_ShouldReturn_CustomException_WithNoSuchClass() {
        try {
            MoodAnalyser moodAnalyser = MoodAnalyserReflector.createMoodAnalyserObject("com.moodanalyse.service.MoodAnalyzer");
        } catch (MoodAnalysisException e) {
            Assert.assertEquals("NO_SUCH_CLASS_ERROR", e.getMessage());
        }
    }

    @Test
    public void whenMood_ConstructorIsNotProper_ByParametrizedConstructor_ShouldReturn_CustomException_WithNoSuchMethod() {
        try {
            MoodAnalyser moodAnalyser = MoodAnalyserReflector.createMoodAnalyserObject("MoodAnalyse");
        } catch (MoodAnalysisException e) {
            Assert.assertEquals("NO_SUCH_METHOD_ERROR", e.getMessage());
        }
    }

    @Test
    public void givenHappyMessage_WithReflection_ShouldReturnHappy() {
        try {
            Object object = MoodAnalyserReflector.createMoodAnalyserObject("I m in Happy Mood");
            Object mood = MoodAnalyserReflector.invokeMethod(object, "analyseMood");
            Assert.assertEquals("HAPPY",mood);
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenHappyMessage_WithReflection_InCorrectMethod_ShouldReturn_NoSuchMethodError() {
        try {
            Object object = MoodAnalyserReflector.createMoodAnalyserObject("I m in Happy Mood");
            Object mood = MoodAnalyserReflector.invokeMethod(object, "analyseMood");
            Assert.assertEquals("HAPPY", mood);
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
    }
}
