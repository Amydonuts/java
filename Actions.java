import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Actions {
    private ArrayList<String> triggers;
    private ArrayList<String> subjects;
    private ArrayList<String> consumptions;
    private ArrayList<String> productions;
    private String narration;

    public Actions(JSONObject jo){
        this.triggers = new ArrayList<>();
        this.subjects = new ArrayList<>();
        this.consumptions = new ArrayList<>();
        this.productions = new ArrayList<>();
        parseObject(jo,"triggers",triggers);
        parseObject(jo,"subjects",subjects);
        parseObject(jo,"consumed",consumptions);
        parseObject(jo,"produced",productions);
        narration = (String) jo.get("narration");
    }

    public void parseObject(JSONObject jo,String s,ArrayList<String> strings){
        JSONArray objects = (JSONArray) jo.get(s);
        Iterator<String> object = objects.iterator();
        while (object.hasNext()) {
            strings.add(object.next());
        }
    }

    public boolean hasTriggered(String word){
        for(String trigger: triggers){
            if(trigger.equals(word)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getSubjects(Entity entity){
        return subjects;
    }

    public boolean hasSubjects(Entity entity) throws SubjectDoesNotExistException {
        Player player = entity.getCurrentPlayer();
        Location location = entity.getCurrentLocation();
        for(String subject: subjects){
            if(!player.hasArtefact(subject)&&!location.hasItem(subject)){
                throw new SubjectDoesNotExistException(subject);
            }
        }
        return true;
    }
    public void consumed(Entity entity){
        Player player = entity.getCurrentPlayer();
        Location location = entity.getCurrentLocation();
        for(String comsuption : consumptions){
            if(player.hasArtefact(comsuption)){
                //delete artefact from player
                Artefact artefact = player.getArtefact(comsuption);
                player.deleteArtefact(artefact);
            }
            if(location.hasFurniture(comsuption)){
                //delete furniture from location
                Furniture furniture = location.getFurniture(comsuption);
                location.deleteFurniture(furniture);
            }
            if(comsuption.equals("health")){
                //minis health level and check if it goes to 0
                if(player.getHealthLevel()>=2) {
                    player.minisHealth();
                }
                else{
                    location.addArtefact(player.getAllArtefacts());//Location get all the artefacts dropped from player.
                    location.deleteCharacter(player);
                    player.deleteAllArtefact();//Player lose all artefacts
                    player.resetHealthLevel();//Recover
                    //Back to the start location.
                    location = entity.getFirstLocation();
                    entity.setCurrentLocation(location);
                    location.addCharacter(player);
                    narration = narration+"\nYour health runs out, return to start";
                }
            }
        }
    }
    public void produced(Entity entity){
        Player player = entity.getCurrentPlayer();
        Location location = entity.getCurrentLocation();
        for(String production: productions) {
            if (entity.locationExist(production)) {
                location.addPath(production);
            }
            else if(production.equals("health")){
                player.plusHealth();
            }
            else if(entity.artefactExist(production)){
                location.addArtefact(entity.getArtefact(production));
            }
            else if(entity.furnitureExist(production)){
                location.addFurniture(entity.getFurniture(production));
            }
            else if(entity.characterExist(production)){
                location.addCharacter(entity.getCharacter(production));
            }
        }
    }
    public void startAction(Entity entity) throws SubjectDoesNotExistException {
        if(hasSubjects(entity)) {
            consumed(entity);
            produced(entity);
        }
    }
    public void print(ArrayList<String> a){
        for(String s:a){
            System.out.print(s+", ");
        }
        System.out.print("\n");
    }
    public String getNarration(){
        return narration;
    }
    public void printAll(){
        print(triggers);
        print(subjects);
        print(consumptions);
        print(productions);
        System.out.println(narration);
    }

}
