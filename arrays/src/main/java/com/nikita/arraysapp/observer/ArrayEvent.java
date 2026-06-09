package com.nikita.arraysapp.observer;

import com.nikita.arraysapp.entity.CustomIntArray;

import java.util.EventObject;

public class ArrayEvent extends EventObject {
    
    public ArrayEvent(CustomIntArray source) {
        super(source);
    }

    @Override
    public CustomIntArray getSource() {
        return (CustomIntArray) super.getSource();
    }
}