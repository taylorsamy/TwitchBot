package test.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

/**
 * Created by Taylor on 21/06/2016.
 */
public class CommandUtils {

    JsonUtils json = new JsonUtils();


    public void addCommand(String trigger, int perm, String message) {
        JSONArray commands = json.loadJson("commands.json");
        JSONObject command = new JSONObject();
        command.put("trigger", trigger);
        command.put("rank", perm);
        command.put("message", message);
        commands.add(command);
        json.writeJson(commands, "commands.json");
    }

    public String searchCommands(String trigger, int rank) {
        JSONArray commands = json.loadJson("commands.json");

        for (Object command1 : commands) {
            JSONObject command = (JSONObject) command1;

            if (trigger.equalsIgnoreCase((String) command.get("trigger"))) {
                if (rank >= (Long) command.get("rank")) {
                    return (String) command.get("message");
                }
            }
        }
        return null;
    }

    public int deleteCommand(String trigger) {
        JSONArray commands = json.loadJson("commands.json");
        Iterator i = commands.iterator();

        while (i.hasNext()) {
            JSONObject command = (JSONObject) i.next();

            if (trigger.equalsIgnoreCase((String) command.get("trigger"))) {
                command.clear();
                commands.remove(command);
                json.writeJson(commands, "commands.json");
                return 1;
            }
        }
        return 0;
    }
}