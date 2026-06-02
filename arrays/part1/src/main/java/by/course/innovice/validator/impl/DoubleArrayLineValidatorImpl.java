package by.course.innovice.validator.impl;

import by.course.innovice.validator.StringValidator;

public class DoubleArrayLineValidatorImpl implements StringValidator {

    private static final String VALID_DOUBLE_LINE_REGEX = "^[-–;\\s,\\d\\.]*$";

    @Override
    public boolean isValid(String line) {
        boolean isNotNull = line != null;

        if (isNotNull) {
            boolean isMatching = line.matches(VALID_DOUBLE_LINE_REGEX);
            return isMatching;
        } else {
            return false;
        }
    }
}