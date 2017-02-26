package core;

import java.util.HashMap;
import java.util.Map;

public class ParseUtil {
    public static int asInt(String string) {
        return Integer.parseInt(string);
    }

    public static int getInt(String[] data, int index) {
        return Integer.parseInt(data[index]);
    }

    @SuppressWarnings("unchecked")
    public static Map<Integer, Integer> asIndexedMap(String[] data) {
        HashMap<Integer, Integer> map = new HashMap<>(data.length);
        for (int i = 0; i < data.length; ++i)
            map.put(i, getInt(data, i));

        return map;
    }
}
