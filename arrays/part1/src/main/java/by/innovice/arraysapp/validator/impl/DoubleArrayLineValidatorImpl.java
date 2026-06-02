package by.innovice.arraysapp.validator.impl;

import by.innovice.arraysapp.validator.StringValidator;

public class DoubleArrayLineValidatorImpl implements StringValidator {

    private static final String VALID_DOUBLE_LINE_REGEX = "^([-–;\\s,]*\\d+(\\.\\d+)?)*[-–;\\s,]*$";

    @Override
    public boolean isValid(String line) {
        boolean isNotNull = line != null;

        if (isNotNull) {
            return line.matches(VALID_DOUBLE_LINE_REGEX);
        } else {
            return false;
        }
    }
}