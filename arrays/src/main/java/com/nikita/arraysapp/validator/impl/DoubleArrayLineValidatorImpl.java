package com.nikita.arraysapp.validator.impl;

import com.nikita.arraysapp.validator.StringValidator;

public class DoubleArrayLineValidatorImpl implements StringValidator {

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