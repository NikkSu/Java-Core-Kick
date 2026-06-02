package by.course.innovice.service;

import by.course.innovice.entity.CustomIntArray;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public interface ArrayMathService {

    OptionalInt findMin(CustomIntArray customArray);

    OptionalInt findMax(CustomIntArray customArray);

    OptionalInt calculateSum(CustomIntArray customArray);

    OptionalDouble calculateAverage(CustomIntArray customArray);
}