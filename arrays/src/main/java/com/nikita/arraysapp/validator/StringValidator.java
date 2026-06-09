package com.nikita.arraysapp.validator;

public interface StringValidator {

    public static final String VALID_DOUBLE_LINE_REGEX = "^([-–;\\s,]*\\d+(\\.\\d+)?)*[-–;\\s,]*$";
    public static final String VALID_LINE_REGEX = "^[-–;\\s,\\d]*$";

    boolean isValid(String line);
}