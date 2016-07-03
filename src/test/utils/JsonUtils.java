package test.utils;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Taylor on 02/07/2016.
 */
public class JsonUtils {

    public JSONArray loadJson(String path) {
        JSONArray array = null;
        try {
            JSONParser parser = new JSONParser();
            if (Files.exists(Paths.get(path))) {
                array = (JSONArray) parser.parse(new FileReader(path));
            } else {
                array = new JSONArray();
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return array;
    }

    public void writeJson(JSONArray json, String path) {
        try {
            FileWriter fw = new FileWriter(path);
            fw.write(json.toJSONString());
            fw.flush();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
