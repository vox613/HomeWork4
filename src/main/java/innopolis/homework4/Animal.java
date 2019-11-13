package innopolis.homework4;

import java.util.LinkedList;
import java.util.List;

public class Animal {

    private int intValue = 5;
    private long longValue = 10;
    private short shortValue = 15;
    private double doubleValue = 5.5;
    private float floatValue = 5.8f;
    private byte byteValue = 125;
    private char charValue = 'h';
    private boolean booleanValue = true;
    private final String name;

    Animal(String name) {
        this.name = name;
    }



    int getIntValue() {
        return intValue;
    }

    long getLongValue() {
        return longValue;
    }

    short getShortValue() {
        return shortValue;
    }

    double getDoubleValue() {
        return doubleValue;
    }

    float getFloatValue() {
        return floatValue;
    }

    byte getByteValue() {
        return byteValue;
    }

    char getCharValue() {
        return charValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    String getName() {
        return name;
    }
}
