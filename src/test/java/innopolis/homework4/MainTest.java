package innopolis.homework4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class MainTest {
    private NewMap<String, String> newMap = new NewMap<>(16);
    private static final double DELTA = 1e-15;
    Animal dog = new Animal("Dog");


    private Set<String> fieldsMapToCleanup = new HashSet<>() {{
        add("1");
        add("5");
        add("10");
        add("15");
        add("20");
        add("25");
        add("30");
    }};

    private Set<String> fieldsMapToOutput = new HashSet<>() {{
        add("15");
        add("20");
        add("25");
        add("30");
        add("35");
        add("40");
        add("45");
    }};


    Set<String> fieldsAnimalToCleanup = new HashSet<>() {{
        add("intValue");
        add("longValue");
        add("shortValue");
        add("doubleValue");
        add("floatValue");
        add("byteValue");
        add("charValue");
        add("booleanValue");
        add("name");
    }};

    Set<String> fieldsAnimalToOutput = new HashSet<>() {{
        add("intValue");
        add("longValue");
        add("shortValue");
        add("doubleValue");
        add("floatValue");
        add("byteValue");
        add("charValue");
        add("booleanValue");
        add("name");
    }};


    @Before
    public void init() {
        for (int i = 0; i < 100; i++) {
            newMap.put(String.valueOf(i), String.valueOf(i));
        }
    }

    @After
    public void clean() {
        newMap.clear();
    }


    @Test
    public void cleanup() {
        System.out.println(newMap.toString());

        try {
            Main.cleanup(newMap, fieldsMapToCleanup, fieldsMapToOutput);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        assertNull(newMap.get("1"));
        assertNull(newMap.get("5"));
        assertNull(newMap.get("10"));
        assertNull(newMap.get("15"));
        assertNull(newMap.get("20"));
        assertNull(newMap.get("25"));
        assertNull(newMap.get("30"));


        System.out.println();

        try {
            Main.cleanup(dog, fieldsAnimalToCleanup, fieldsAnimalToOutput);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        assertEquals(0, dog.getIntValue());
        assertEquals(0L, dog.getLongValue());
        assertEquals(0, dog.getShortValue());
        assertEquals(0.0d, dog.getDoubleValue(), DELTA);
        assertEquals(0.0f, dog.getFloatValue(), DELTA);
        assertEquals(0, dog.getByteValue());
        assertEquals('\u0000', dog.getCharValue());
        assertNull(dog.getName());


        System.out.println(dog.getIntValue());
        System.out.println(dog.getLongValue());
        System.out.println(dog.getShortValue());
        System.out.println(dog.getDoubleValue());
        System.out.println(dog.getFloatValue());
        System.out.println(dog.getByteValue());
        System.out.println(dog.getCharValue());
        System.out.println(dog.getName());

    }
}