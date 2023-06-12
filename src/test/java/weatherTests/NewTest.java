package weatherTests;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.poi.poifs.filesystem.Entry;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class NewTest {
    public static void main(String[] args) {
        String json1 = "{\n" +
                "  \"name\": {\n" +
                "    \"first\": \"John\",\n" +
                "    \"last\": \"Doe\"\n" +
                "  },\n" +
                "  \"address\": null,\n" +
                "  \"birthday\": \"1980-01-01\",\n" +
                "  \"company\": \"Acme\",\n" +
                "  \"occupation\": \"Software engineer\",\n" +
                "  \"phones\": [\n" +
                "    {\n" +
                "      \"number\": \"000000000\",\n" +
                "      \"type\": \"home\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"number\": \"999999999\",\n" +
                "      \"type\": \"mobile\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        String json2 = "{\n" +
                "  \"name\": {\n" +
                "    \"first\": \"Jane\",\n" +
                "    \"last\": \"Doe\",\n" +
                "    \"nickname\": \"Jenny\"\n" +
                "  },\n" +
                "  \"birthday\": \"1990-01-01\",\n" +
                "  \"occupation\": null,\n" +
                "  \"phones\": [\n" +
                "    {\n" +
                "      \"number\": \"111111111\",\n" +
                "      \"type\": \"mobile\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"favorite\": true,\n" +
                "  \"groups\": [\n" +
                "    \"close-friends\",\n" +
                "    \"gym\"\n" +
                "  ]\n" +
                "}";

        Gson g = new Gson();
        Type maxType = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> map1 = g.fromJson(json1, maxType);
        Map<String, Object> map2 = g.fromJson(json2, maxType);
        MapDifference<String, Object> difference = Maps.difference(map1, map2);
        Set set = difference.entriesDiffering().entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Object> mentry = (Map.Entry) iterator.next();
            System.out.println(mentry.getKey() + " : " + mentry.getValue());
        }
    }
}
