package com.nikita.composite.entity;

import java.util.List;

public class SymbolLeaf implements TextComponent {

    private final char value;
    private final ComponentType type;

    public SymbolLeaf(char value, ComponentType type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        throwUnsupported();
    }

    @Override
    public void remove(TextComponent component) {
        throwUnsupported();
    }

    @Override
    public List<TextComponent> getChildren() {
        throwUnsupported();
        return null;
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public String reconstruct() {
        return String.valueOf(value);
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

        SymbolLeaf that = (SymbolLeaf) obj;
        if (this.type != that.type) {
            return false;
        }

        return this.value == that.value;
    }

    @Override
    public int hashCode() {
        int result = 1;
        int typeHash = type != null ? type.hashCode() : 0;
        result = 31 * result + typeHash;
        result = 31 * result + Character.hashCode(value);
        return result;
    }

    private void throwUnsupported() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("SymbolLeaf{type=");
        stringBuilder.append(type);
        stringBuilder.append(", value='");
        stringBuilder.append(value);
        stringBuilder.append("'}");

        return stringBuilder.toString();
    }
}