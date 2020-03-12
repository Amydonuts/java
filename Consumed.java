import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Consumed {
    ArrayList<String> consumptions;
    public Consumed(JSONObject jo, String s){
        this.consumptions = new ArrayList<>();
        JSONArray objects = (JSONArray) jo.get(s);
        Iterator<String> object = objects.iterator();
        while (object.hasNext()) {
            consumptions.add(object.next());
        }
    }
    public Artefact getConsumption(Player player,String name) {
        for(String consumption: consumptions){
            if(consumption.equals(name)){
                Artefact artefact = player.getArtefact(name);
                return artefact;
            }
        }
        return null;
    }

    public ArrayList<String> getConsumes() {
        return consumptions;
    }
}
