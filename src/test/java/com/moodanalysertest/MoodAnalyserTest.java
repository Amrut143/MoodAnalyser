package com.moodanalysertest;

import com.moodanalyse.service.MoodAnalyser;
import com.moodanalyse.exception.MoodAnalysisException;
import com.moodanalyse.utility.MoodAnalyserReflector;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;

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
            Assert.assertEquals("HAPPY", mood);
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
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
            MoodAnalyser moodAnalyser = MoodAnalyserReflector.createMoodAnalyserObject("com.moodanalyse.service.MoodAnalyser", "I am in happy mood", "Object");
            Assert.assertEquals(new MoodAnalyser(), moodAnalyser);
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMoodAnalyserClass_WhenNotProper_ShouldReturn_CustomException_WithNoSuchClass() {
        try {
            MoodAnalyser moodAnalyser = MoodAnalyserReflector.createMoodAnalyserObject("com.moodanalyse.service.Mood");
            Assert.assertEquals("NO_SUCH_CLASS_ERROR", moodAnalyser);
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenMood_ConstructorIsNotProper_ShouldReturn_CustomException_WithNoSuchMethod() {
        try {
            MoodAnalyser moodAnalyser = MoodAnalyserReflector.createMoodAnalyserObject("com.moodanalyse.service.MoodAnalyser", "Iam in happy mood");
        } catch (MoodAnalysisException e) {
            Assert.assertEquals("NO_SUCH_METHOD_ERROR", e.getMessage());
        }
    }

    @Test
    public void givenMoodAnalyzerClass_IsEquals_ShouldReturnMoodAnalyzerObject() {
        MoodAnalyser moodAnalyser;
        try {
            moodAnalyser = MoodAnalyserReflector.createMoodAnalyserObject("com.moodanalyse.service.MoodAnalyser", "I am in happy mood");
            Assert.assertEquals(new MoodAnalyser("HAPPY"), moodAnalyser);
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMoodAnalyserClass_WhenNotProper_ByParametrizedConstructor_ShouldReturn_CustomException_WithNoSuchClass() {
        try {
            MoodAnalyser moodAnalyser = MoodAnalyserReflector.createMoodAnalyserObject("com.moodanalyse.service.Mood");
            Assert.assertEquals("NO_SUCH_CLASS_ERROR", moodAnalyser);
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenMood_ConstructorIsNotProper_ByParametrizedConstructor_ShouldReturn_CustomException_WithNoSuchMethod() {
        try {
            MoodAnalyser moodAnalyser = MoodAnalyserReflector.createMoodAnalyserObject("com.moodanalyse.service.MoodAnalyser", "I am in happy mood");
        } catch (MoodAnalysisException e) {
            Assert.assertEquals("NO_SUCH_METHOD_ERROR", e.getMessage());
        }
    }

    @Test
    public void givenHappyMessage_WithReflection_ShouldReturnHappy() {
        try {
            Object analyserObject = MoodAnalyserReflector.createMoodAnalyserObject("com.moodanalyse.service.MoodAnalyser", "I m in Happy Mood");
            Object mood = MoodAnalyserReflector.invokeMethod(analyserObject, "analyseMood");
            Assert.assertEquals("HAPPY", mood);
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenHappyMessage_WithReflection_InCorrectMethod_ShouldReturn_NoSuchMethodError() {
        try {
            Object analyserObject = MoodAnalyserReflector.createMoodAnalyserObject("com.moodanalyse.service.MoodAnalyser", "I m in Happy Mood");
            Object mood = MoodAnalyserReflector.invokeMethod(analyserObject, "Mood");
            Assert.assertEquals("Happy", mood);
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setHappyMessage_WithReflector_ShouldReturnHappy() {
        try {
            Object analyserObject = MoodAnalyserReflector.createMoodAnalyserObject("com.moodanalyse.service.MoodAnalyser", "I m in Happy Mood");
            MoodAnalyserReflector.setFieldValue(analyserObject, "message", "I am in happy mood");
            Object mood = MoodAnalyserReflector.invokeMethod(analyserObject, "analyseMood");
            Assert.assertEquals("HAPPY", mood);
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenImproperFieldToSet_ShouldReturnMoodAnalysisException() {
        try {
            Object analyserObject = MoodAnalyserReflector.createMoodAnalyserObject("com.moodanalyse.service.MoodAnalyser", "I m in Happy Mood");
            MoodAnalyserReflector.setFieldValue(analyserObject, "msg", "I am in happy mood");
            Object mood = MoodAnalyserReflector.invokeMethod(analyserObject, "analyseMood");
            Assert.assertEquals("Happy", mood);
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void settingNullFieldValueToReflector_ShouldGiveMoodAnalysisException() {
        try {
            Object analyserObject = MoodAnalyserReflector.createMoodAnalyserObject("com.moodanalyse.service.MoodAnalyser", "I m in Happy Mood");
            MoodAnalyserReflector.setFieldValue(analyserObject, "message", "");
            Object mood = MoodAnalyserReflector.invokeMethod(analyserObject, "analyseMood");
            Assert.assertEquals("Happy", mood);
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
    }
}
