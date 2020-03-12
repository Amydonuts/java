import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Produced {
    ArrayList<String> productions;
    public Produced(JSONObject jo, String s){
        this.productions = new ArrayList<>();
        JSONArray objects = (JSONArray) jo.get(s);
        Iterator<String> object = objects.iterator();
        while (object.hasNext()) {
            productions.add(object.next());
        }
    }
    public boolean hasSubject(String subj) {
        for(String production: productions){
            if(production.equals(subj)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getProductions() {
        return productions;
    }
}
