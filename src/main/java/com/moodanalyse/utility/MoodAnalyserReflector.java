package com.moodanalyse.utility;

import com.moodanalyse.exception.MoodAnalysisException;
import com.moodanalyse.service.MoodAnalyser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MoodAnalyserReflector {

    public static MoodAnalyser createMoodAnalyserObject(String... message) throws MoodAnalysisException {
        try {
            Class<?> moodAnalyserClass = Class.forName("com.moodanalyse.service.MoodAnalyser");
            Constructor<?> moodConstructor = moodAnalyserClass.getConstructor(Object.class);
            Object moodAnalyserObject = moodConstructor.newInstance(message);
            return (MoodAnalyser) moodAnalyserObject;
        } catch (ClassNotFoundException e) {
            throw new MoodAnalysisException(MoodAnalysisException.exceptionType.NO_SUCH_CLASS, "NO_SUCH_CLASS_ERROR");
        } catch (InstantiationException e) {
            throw new MoodAnalysisException(MoodAnalysisException.exceptionType.OBJECT_CREATION_ISSUE, "OBJECT_CREATION_ISSUE");
        }  catch (InvocationTargetException e) {
            throw new MoodAnalysisException(MoodAnalysisException.exceptionType.METHOD_INVOCATION_ISSUE, "METHOD_INVOCATION_ISSUE");
        } catch (NoSuchMethodException e) {
            throw new MoodAnalysisException(MoodAnalysisException.exceptionType.NO_SUCH_METHOD, "NO_SUCH_METHOD_ERROR");
        } catch (IllegalAccessException e) {
            throw new MoodAnalysisException(MoodAnalysisException.exceptionType.NO_ACCESS, "NO_ACCESS");
        }
    }

    public static Object invokeMethod(Object moodAnalyseObject, String message) throws MoodAnalysisException {
        try {
            return moodAnalyseObject.getClass().getMethod(message).invoke(moodAnalyseObject);
        } catch (NoSuchMethodException e) {
            throw new MoodAnalysisException(MoodAnalysisException.exceptionType.NO_SUCH_METHOD, "NO_SUCH_METHOD_ERROR");
        } catch (IllegalAccessException e) {
            throw new MoodAnalysisException(MoodAnalysisException.exceptionType.NO_ACCESS, "NO_ACCESS");
        } catch (InvocationTargetException e) {
            throw new MoodAnalysisException(MoodAnalysisException.exceptionType.METHOD_INVOCATION_ISSUE, "METHOD_INVOCATION_ISSUE");
        }
    }
}

