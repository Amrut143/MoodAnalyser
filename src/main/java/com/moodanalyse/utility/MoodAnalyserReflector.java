package com.moodanalyse.utility;

import com.moodanalyse.exception.MoodAnalysisException;
import com.moodanalyse.service.MoodAnalyser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class MoodAnalyserReflector {

    /**
     *
     * @param message
     * @return
     * @throws MoodAnalysisException
     * */
    public static MoodAnalyser createMoodAnalyserObject(String message) throws MoodAnalysisException {
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

    /**
     *
     * @param moodAnalyseObject
     * @param methodName
     * @return
     * @throws MoodAnalysisException
     */
    public static Object invokeMethod(Object moodAnalyseObject, String methodName) throws MoodAnalysisException {
        try {
            return moodAnalyseObject.getClass().getMethod(methodName).invoke(moodAnalyseObject);
        } catch (NoSuchMethodException e) {
            throw new MoodAnalysisException(MoodAnalysisException.exceptionType.NO_SUCH_METHOD, "NO_SUCH_METHOD_ERROR");
        } catch (IllegalAccessException e) {
            throw new MoodAnalysisException(MoodAnalysisException.exceptionType.NO_ACCESS, "NO_ACCESS");
        } catch (InvocationTargetException e) {
            throw new MoodAnalysisException(MoodAnalysisException.exceptionType.METHOD_INVOCATION_ISSUE, "METHOD_INVOCATION_ISSUE");
        }
    }

    public static void setFieldValue(Object analyserObject, String fieldName, String fieldValue) throws MoodAnalysisException {
        try {
            Field field = analyserObject.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(analyserObject, fieldValue);
        } catch (NoSuchFieldException e) {
            throw new MoodAnalysisException(MoodAnalysisException.exceptionType.NO_SUCH_FIELD, "Define Proper Field Name");
        } catch (IllegalAccessException e) {
            throw new MoodAnalysisException(MoodAnalysisException.exceptionType.FIELD_SETTING_ISSUE, "May be issue in data entry");
        }
    }
}



