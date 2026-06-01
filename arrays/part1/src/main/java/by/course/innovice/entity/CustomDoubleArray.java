package by.course.innovice.entity;

import java.util.Arrays;

public class CustomDoubleArray extends AbstractCustomArray {

    private double[] array;

    // public just for tests
    public CustomDoubleArray(double[] array) {
        boolean isNotNull = array != null;

        if (isNotNull) {
            this.array = array.clone();
        } else {
            this.array = new double[0];
        }
    }

    public double[] getArray() {
        return array.clone();
    }

    public void setArray(double[] array) {
        boolean isNotNull = array != null;

        if (isNotNull) {
            this.array = array.clone();
        } else {
            this.array = new double[0];
        }

    }

    @Override
    public int length() {
        return array.length;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        //Check that both classes are AbstractCustomArray
        Class<?> thisClass = getClass();
        Class<?> objClass = obj.getClass();

        if (thisClass != objClass) {
            return false;
        }

        CustomDoubleArray that = (CustomDoubleArray) obj;
        int thisLength = this.array.length;
        int thatLength = that.array.length;

        if (thisLength != thatLength) {
            return false;
        }

        for (int i = 0; i < thisLength; i++) {
            double thisElement = this.array[i];
            double thatElement = that.array[i];
            int compareResult = Double.compare(thisElement, thatElement);
            if (compareResult != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (double element : array) {
            int elementHash = Double.hashCode(element);
            result = 31 * result + elementHash;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("CustomDoubleArray{array=[");
        int length = array.length;

        for (int i = 0; i < length; i++) {
            stringBuilder.append(array[i]);
            int lastIndex = length - 1;
            if (i < lastIndex) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]}");

        return stringBuilder.toString();
    }
}