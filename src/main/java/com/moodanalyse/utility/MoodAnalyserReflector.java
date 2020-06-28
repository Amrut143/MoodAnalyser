package com.moodanalyse.utility;

import com.moodanalyse.exception.MoodAnalysisException;
import com.moodanalyse.service.MoodAnalyser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class MoodAnalyserReflector {

    /**
     * @return
     * @throws MoodAnalysisException
     */
    public static MoodAnalyser createMoodAnalyserObject(String... value) throws MoodAnalysisException {
        try {
            Class<?> moodAnalyserClass = Class.forName(value[0]);
            Constructor<?> moodConstructor = moodAnalyserClass.getConstructor(Object.class);
            Object moodAnalyserObject = moodConstructor.newInstance(value[1]);
            return (MoodAnalyser) moodAnalyserObject;
        } catch (ClassNotFoundException e) {
            throw new MoodAnalysisException(MoodAnalysisException.exceptionType.NO_SUCH_CLASS, "NO_SUCH_CLASS_ERROR");
        } catch (NoSuchMethodException e) {
            throw new MoodAnalysisException(MoodAnalysisException.exceptionType.NO_SUCH_METHOD, "NO_SUCH_METHOD_ERROR");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
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
            if (fieldValue.length() == 0)
                throw new MoodAnalysisException(MoodAnalysisException.exceptionType.NULL_VALUE, "Field value is null");
        } catch (NoSuchFieldException e) {
            throw new MoodAnalysisException(MoodAnalysisException.exceptionType.NO_SUCH_FIELD, "Define Proper Field Name");
        } catch (IllegalAccessException e) {
            throw new MoodAnalysisException(MoodAnalysisException.exceptionType.FIELD_SETTING_ISSUE, "May be issue in data entry");
        }
    }
}



