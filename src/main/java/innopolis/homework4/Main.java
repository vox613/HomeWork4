package innopolis.homework4;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        NewMap<String, String> newMap = new NewMap<>(16);

        for (int i = 0; i < 100; i++) {
            newMap.put("" + i, "" + (100 - i));
        }

        System.out.println(newMap.toString());
        Set<String> fieldsToCleanup = new HashSet<>() {{
            add("1");
            add("2");
            add("3");
            add("4");
        }};

        Set<String> fieldsToOutput = new HashSet<>() {{
            add("50");
            add("60");
            add("70");
            add("80");
        }};

        cleanup(newMap, fieldsToCleanup, fieldsToOutput);
        System.out.println(newMap.toString());


        Node newNode = new Node("5", "500");

        Set<String> fieldsToCleanup_1 = new HashSet<>() {{
            add("intValue");
            add("longValue");
            add("shortValue");
            add("doubleValue");
            add("floatValue");
            add("byteValue");
            add("charValue");
            add("booleanValue");
            add("linkToNextNode");
        }};


        Set<String> fieldsToOutput_1 = new HashSet<>() {{
            add("intValue");
            add("longValue");
            add("shortValue");
            add("doubleValue");
            add("floatValue");
            add("byteValue");
            add("charValue");
            add("booleanValue");
            add("linkToNextNode");
        }};

        cleanup(newNode, fieldsToCleanup_1, fieldsToOutput_1);
    }


    static void cleanup(Object obj, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        if (fieldsToCleanup.isEmpty() && fieldsToOutput.isEmpty()) {
            return;
        }

        Class clazz = obj.getClass();
        List<String> fieldsList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            fieldsList.add(field.getName());
        }

        if (obj instanceof Map) {
            Method mKeySet = clazz.getMethod("keySet");
            Set<?> keysets = (Set<?>) mKeySet.invoke(obj);
            if (keysets.containsAll(fieldsToCleanup) && keysets.containsAll(fieldsToOutput)) {
                Class[] getTypes = new Class[]{Object.class};
                Method mGet = clazz.getMethod("get", getTypes);
                for (String keyToOutput : fieldsToOutput) {
                    System.out.println(mGet.invoke(obj, keyToOutput));
                }
                Class[] removeTypes = new Class[]{Object.class};
                Method mRemove = clazz.getMethod("remove", removeTypes);
                for (String keyToRemove : fieldsToCleanup) {
                    mRemove.invoke(obj, keyToRemove);
                }
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            if (fieldsList.containsAll(fieldsToCleanup) && fieldsList.containsAll(fieldsToOutput)) {
                for (Field field : fields) {
                    String fieldType = field.getType().getName();
                    field.setAccessible(true);
                    if (fieldsToOutput.contains(field.getName())) {
                        switch (fieldType) {
                            case ("int"): {
                                System.out.println(String.valueOf(field.get(obj)));
                                break;
                            }
                            case ("long"): {
                                System.out.println(String.valueOf(field.get(obj)));
                                break;
                            }
                            case ("short"): {
                                System.out.println(String.valueOf(field.get(obj)));
                                break;
                            }
                            case ("byte"): {
                                System.out.println(String.valueOf(field.get(obj)));
                                break;
                            }
                            case ("float"): {
                                System.out.println(String.valueOf(field.get(obj)));
                                break;
                            }
                            case ("double"): {
                                System.out.println(String.valueOf(field.get(obj)));
                                break;
                            }
                            case ("char"): {
                                System.out.println(String.valueOf(field.get(obj)));
                                break;
                            }
                            case ("boolean"): {
                                System.out.println(String.valueOf(field.get(obj)));
                                break;
                            }
                            default: {
                                if (field.get(obj) == null) {
                                    System.out.println(field.get(obj));
                                } else {
                                    System.out.println(field.get(obj).toString());
                                }
                            }
                        }
                    }
                    if (fieldsToCleanup.contains(field.getName())) {
                        switch (fieldType) {
                            case ("int"): {
                                field.set(obj, 0);
                                break;
                            }
                            case ("long"): {
                                field.set(obj, 0L);
                                break;
                            }
                            case ("short"): {
                                field.set(obj, (short) 0);
                                break;
                            }
                            case ("byte"): {
                                field.set(obj, (byte) 0);
                                break;
                            }
                            case ("float"): {
                                field.set(obj, 0.0f);
                                break;
                            }
                            case ("double"): {
                                field.set(obj, 0.0d);
                                break;
                            }
                            case ("char"): {
                                field.set(obj, '\u0000');
                                break;
                            }
                            case ("boolean"): {
                                field.set(obj, false);
                                break;
                            }
                            default: {
                                field.set(obj, null);
                            }
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
    }
}
