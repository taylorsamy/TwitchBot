package test.modules;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import test.Module;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Taylor on 21/06/2016.
 */
public class CommandModule extends Module {

    public void receiveMessage(String sender, String message) {
        //if sender is mod
        if (message.toLowerCase().startsWith("!cmd add ")) {

            String[] tmp = message.split(" ");

            for (String s : tmp) {
                System.out.println(s);
            }
            JSONObject commands = new JSONObject();
            JSONArray command = new JSONArray();

            command.add("trigger: " + tmp[2]);
            command.add("perm: " + tmp[3]);
            command.add("message: " + tmp[4]);
            commands.put("Commands: ", command);

            try {
                try (FileWriter file = new FileWriter("commands.json", true)) {
                    file.append((commands.toJSONString()));
                    file.append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
