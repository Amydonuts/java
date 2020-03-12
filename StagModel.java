import com.alexmerz.graphviz.Parser;
import com.alexmerz.graphviz.objects.Graph;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class StagModel {
    private Entity entity;
    private ArrayList<Actions> actions;

    public StagModel(String dotFlie, String jsonFile){
        this.actions = new ArrayList<>();
        this.entity = parseDotFile(dotFlie);
        actions = parseJsonFile(jsonFile);
    }

    public Entity getEntity(){
        return entity;
    }

    public ArrayList<Actions> getActions(){
        return actions;
    }

    public Entity parseDotFile(String dotFile) {
        try {
            //parse dot file.
            Parser parser = new Parser();
            FileReader reader = new FileReader(dotFile);
            parser.parse(reader);
            ArrayList<Graph> graphs = parser.getGraphs();
            ArrayList<Graph> subGraphs = graphs.get(0).getSubgraphs();
            /*ArrayList<Entity> entities = new ArrayList<Entity>();
            for(Graph g : subGraphs){
                entities.add(new Entity(g));
            }*/
            //entities.get(0).printAll();
            entity = new Entity(subGraphs.get(0),subGraphs.get(1));

        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe);
        } catch (com.alexmerz.graphviz.ParseException pe) {
            System.out.println(pe);
        }
        return entity;
    }


    public ArrayList<Actions> parseJsonFile(String jsonFile) {
        try {//parse json file.
            JSONParser jsonParser = new JSONParser();
            FileReader jsonReader = new FileReader(jsonFile);
            Object obj = jsonParser.parse(jsonReader);
            JSONObject jo = (JSONObject) obj;
            JSONArray actionArray = (JSONArray) jo.get("actions");
            Iterator<JSONObject> iterator = actionArray.iterator();
            while (iterator.hasNext()) {
                JSONObject actionsJO = iterator.next();
                actions.add(new Actions(actionsJO));
            }
            /*for(Actions action:actions){
                action.printAll();
            }*/
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return actions;
    }
}
