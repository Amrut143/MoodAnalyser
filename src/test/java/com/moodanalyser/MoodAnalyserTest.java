package com.moodanalyser;

import com.moodanalyse.MoodAnalyser;
import org.junit.Assert;
import org.junit.Test;

public class MoodAnalyserTest {

    @Test
    public void givenMessage_WhenSad_ShouldReturnSad() {
        MoodAnalyser moodAnalyser = new MoodAnalyser();
        String mood = moodAnalyser.analyseMood("This message is for sad mood");
        Assert.assertEquals("SAD", mood);
    }
}
