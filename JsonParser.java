import com.alexmerz.graphviz.objects.Graph;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class JsonParser {
    public static void main(String[] args) {
        try{
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(args[0]);
            Object obj = parser.parse(reader);
            JSONObject jo = (JSONObject) obj;
            JSONArray actions = (JSONArray) jo.get("actions");
            Iterator<JSONObject> iterator = actions.iterator();

            while(iterator.hasNext()) {
                JSONObject actionsJO = iterator.next();
                parseObject(actionsJO,"triggers");
                parseObject(actionsJO,"subjects");
                parseObject(actionsJO,"consumed");
                parseObject(actionsJO,"produced");

                String narration = (String) actionsJO.get("narration");
                System.out.println("narration = " + narration);
                System.out.print("\n");
            }



        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void parseObject(JSONObject jo,String s){
        JSONArray objects = (JSONArray) jo.get(s);
        Iterator<String> object = objects.iterator();
        System.out.print(s+" = ");
        while (object.hasNext()) {
            System.out.print(object.next()+", ");
        }
        System.out.print("\n");
    }
}
