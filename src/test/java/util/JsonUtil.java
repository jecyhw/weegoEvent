package util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;

/**
 * Created by root on 16-5-12.
 */
public class JsonUtil {
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static void write(Writer writer, Object value)
    {
        try {
            objectMapper.writeValue(writer, value);
        } catch (IOException e) {
            TestOutput.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void write(OutputStream outputStream, Object value) {
        try {
            objectMapper.writeValue(outputStream, value);
        } catch (IOException e) {
            TestOutput.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public static String writeAsString(Object value) {
        String result = "[]";
        try {
            result = objectMapper.writeValueAsString(value);
            TestOutput.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Object read(String jsonString, Class clsName)
    {

        Object obj = null;
        try {
            obj = objectMapper.readValue(jsonString, clsName);
            TestOutput.println(obj);
        } catch (IOException e) {
            TestOutput.println(e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

}
