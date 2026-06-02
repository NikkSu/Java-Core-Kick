package by.course.innovice.validator.impl;

import by.course.innovice.validator.StringValidator;

public class ArrayLineValidatorImpl implements StringValidator {
    private static final String VALID_LINE_REGEX = "^[-–;\\s,\\d]*$";

    @Override
    public boolean isValid(String line) {
        boolean isNotNull = line != null;

        if (isNotNull) {
            return line.matches(VALID_LINE_REGEX);
        } else {
            return false;
        }
    }
}