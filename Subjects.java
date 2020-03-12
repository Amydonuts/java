import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Subjects {
    ArrayList<String> subjects;
    public Subjects(JSONObject jo, String s){
        this.subjects = new ArrayList<>();
        JSONArray objects = (JSONArray) jo.get(s);
        Iterator<String> object = objects.iterator();
        while (object.hasNext()) {
            subjects.add(object.next());
        }
    }
    public boolean hasSubject(Location location,Player player) {
        for(String subject: subjects){
            if(!location.hasItem(subject)&&!player.hasArtefact(subject)){
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> getSubjects() {
        return subjects;
    }
}
