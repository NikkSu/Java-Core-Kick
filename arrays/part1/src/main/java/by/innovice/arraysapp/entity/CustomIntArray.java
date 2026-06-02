package by.innovice.arraysapp.entity;

import by.innovice.arraysapp.exception.ArrayProcessingException;

public class CustomIntArray extends AbstractCustomArray {

    private int[] array;

    // public just for tests
    public CustomIntArray(int[] array) throws ArrayProcessingException {
        setArray(array);
    }

    public void setArray(int[] array) throws ArrayProcessingException {
        boolean isNotNull = array != null;

        if (isNotNull) {
            this.array = array.clone();
        } else {
            throw new ArrayProcessingException("Array cannot be null");
        }
    }

    public int[] getArray() {
        return array.clone();
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

        //Check that both classes are CustomIntArray
        Class<?> thisClass = getClass();
        Class<?> objClass = obj.getClass();
        if (thisClass != objClass) {
            return false;
        }

        CustomIntArray that = (CustomIntArray) obj;
        int thisLength = this.array.length;
        int thatLength = that.array.length;

        if (thisLength != thatLength) {
            return false;
        }

        for (int i = 0; i < thisLength; i++) {
            if (this.array[i] != that.array[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int element : array) {
            result = 31 * result + element;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("CustomIntArray{array=[");
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