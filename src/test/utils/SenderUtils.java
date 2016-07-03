package test.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by Taylor on 22/06/2016.
 */
public class SenderUtils {
    JsonUtils json = new JsonUtils();

    public void setRank(int rank, String name) {
        JSONArray ranks = json.loadJson("ranks.json");
        JSONObject temp = null;
        for (Object rank2 : ranks) {
            JSONObject user = (JSONObject) rank2;

            if (name.equalsIgnoreCase((String) user.get("name"))) {
                temp = user;
            }
        }

        if (temp != null) {
            ranks.remove(temp);
        }

        JSONObject user = new JSONObject();
        user.put("name", name);
        user.put("rank", rank);
        ranks.add(user);
        json.writeJson(ranks, "ranks.json");

    }

    public boolean ckechRank(String name, int rank) {
        if (rank == 0) {
            return true;
        }
        JSONArray ranks = json.loadJson("ranks.json");

        for (Object rank1 : ranks) {
            JSONObject user = (JSONObject) rank1;

            if (name.equalsIgnoreCase((String) user.get("name"))) {
                if (rank <= ((long) user.get("rank"))) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getRank(String sender) {
        JSONArray ranks = json.loadJson("ranks.json");

        for (Object rank1 : ranks) {
            JSONObject user = (JSONObject) rank1;

            if (sender.equalsIgnoreCase((String) user.get("name"))) {
                return ((Long) user.get("rank")).intValue();
            }
        }
        return 0;
    }
}
