package test.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

/**
 * Created by Taylor on 03/07/2016.
 */
public class QuoteUtils {
    JsonUtils json = new JsonUtils();

    public void addQuote(String message) {
        JSONArray quotes = json.loadJson("quotes.json");
        JSONObject quote = new JSONObject();
        quote.put("index", 1 + quotes.size());
        quote.put("quote", message);
        quotes.add(quote);
        json.writeJson(quotes, "quotes.json");
    }

    public String getQuote(int index) {
        JSONArray quotes = json.loadJson("quotes.json");

        for (Object quote1 : quotes) {
            JSONObject quote = (JSONObject) quote1;

            if (index == (Long) quote.get("index")) {
                return "[Quote " + index + " of " + quotes.size() + "] " + quote.get("quote");
            }
        }
        return null;
    }

    public int deleteQuote(int index) {

        JSONArray quotes = json.loadJson("quotes.json");
        Iterator i = quotes.iterator();

        while (i.hasNext()) {
            JSONObject quote = (JSONObject) i.next();

            if (((Long) quote.get("index")).intValue() > index) {
                quote.put("index", ((((Long) quote.get("index")).intValue()) - 1));
                json.writeJson(quotes, "quotes.json");
            }
        }

        quotes = json.loadJson("quotes.json");
        i = quotes.iterator();

        while (i.hasNext()) {
            JSONObject quote = (JSONObject) i.next();

            if (index == (Long) quote.get("index")) {
                quote.clear();
                quotes.remove(quote);
                json.writeJson(quotes, "quotes.json");
                return 1;
            }
        }
        return 0;
    }

    public int getQuotesNum() {
        JSONArray quotes = json.loadJson("quotes.json");
        return quotes.size();
    }
}
