package innopolis.homework4;

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

    public Animal(String name) {
        this.name = name;
    }


    public int getIntValue() {
        return intValue;
    }

    public long getLongValue() {
        return longValue;
    }

    public short getShortValue() {
        return shortValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public byte getByteValue() {
        return byteValue;
    }

    public char getCharValue() {
        return charValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public String getName() {
        return name;
    }
}
