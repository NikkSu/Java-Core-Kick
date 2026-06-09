package com.nikita.arraysapp.entity;

import com.nikita.arraysapp.exception.ArrayProcessingException;
import com.nikita.arraysapp.observer.ArrayEvent;
import com.nikita.arraysapp.observer.ArrayObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomIntArray extends AbstractCustomArray {

    private final Logger logger = LogManager.getLogger(CustomIntArray.class);

    private int[] array;
    private final long id;

    private ArrayObserver observer;

    public CustomIntArray(long id, int[] array) throws ArrayProcessingException {
        this.id = id;
        setArray(array);
    }

    public void attach(ArrayObserver observer) {
        boolean isNotNull = observer != null;
        if (isNotNull) {
            this.observer = observer;
            logger.debug("Observer attached to array id: {}", id);
        }
    }

    public void detach(ArrayObserver observer) {
        this.observer = null;
        logger.debug("Observer detached from array id: {}", id);
    }

    public void notifyObservers() {
        boolean isNotNull = observer != null;
        if (isNotNull) {
            ArrayEvent event = new ArrayEvent(this);
            observer.update(event);
        }
    }

    public long getId() {
        return id;
    }

    public void setArray(int[] array) throws ArrayProcessingException {
        boolean isNotNull = array != null;

        if (isNotNull) {
            this.array = array.clone();
            notifyObservers();
        } else {
            throw new ArrayProcessingException("Array cannot be null");
        }
    }

    public void setElement(int index, int value) throws ArrayProcessingException {
        int length = array.length;
        boolean isValidIndex = index >= 0 && index < length;

        if (isValidIndex) {
            array[index] = value;
            notifyObservers();
        } else {
            throw new ArrayProcessingException("Index out of bounds: " + index);
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
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
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