import com.alexmerz.graphviz.objects.*;

import java.util.ArrayList;

public class Location {
    private String id;
    private String name;
    private ArrayList<String> paths;
    private ArrayList<Character> characters;
    private ArrayList<Artefact> artefacts;
    private ArrayList<Furniture> furniture;

    public Location(Graph g,Graph g1) {
        this.furniture = new ArrayList<Furniture>();
        this.artefacts = new ArrayList<Artefact>();
        this.characters = new ArrayList<Character>();
        ArrayList<Node> nodesLoc = g.getNodes(false);
        Node nLoc = nodesLoc.get(0);
        this.id = g.getId().getId();
        this.name = nLoc.getId().getId();
        ArrayList<Graph> subGraphs2 = g.getSubgraphs();
        for (Graph g2 : subGraphs2) {
            if(g2.getId().getId().equals("artefacts")){
                ArrayList<Node> nodesEnt = g2.getNodes(false);
                for (Node nEnt : nodesEnt) {
                    artefacts.add(new Artefact(nEnt));
                }
            }
            if(g2.getId().getId().equals("furniture")){
                ArrayList<Node> nodesEnt = g2.getNodes(false);
                for (Node nEnt : nodesEnt) {
                    furniture.add(new Furniture(nEnt));
                }
            }
            if(g2.getId().getId().equals("characters")){
                ArrayList<Node> nodesEnt = g2.getNodes(false);
                for (Node nEnt : nodesEnt) {
                    characters.add(new Character(nEnt));
                }
            }
        }
        this.paths = new ArrayList<>();
        ArrayList<Edge> edges = g1.getEdges();
        for (Edge e : edges){
            if(e.getSource().getNode().getId().getId().equals(this.name)) {
                paths.add(e.getTarget().getNode().getId().getId());
            }
        }
    }

    public String getName(){
        return name;
    }
    public void addCharacter(Character c){
        characters.add(c);
    }

    public ArrayList<Artefact> getAllArtefacts(){
        return artefacts;
    }
    public ArrayList<Furniture> getAllFurniture(){
        return furniture;
    }
    public ArrayList<Character> getAllCharacters(){
        return characters;
    }
    public ArrayList<String> getPaths(){
        return paths;
    }

    public ArrayList<String> getAllCharactersName(){
        ArrayList<String> names = new ArrayList<>();
        for (Character character : characters) {
            names.add(character.getName());
        }
        return names;
    }
    public ArrayList<String> getAllArtefactsName(){
        ArrayList<String> names = new ArrayList<>();
        for (Artefact artefact : artefacts) {
            names.add(artefact.getName());
        }
        return names;
    }
    public ArrayList<String> getAllFurnitureName(){
        ArrayList<String> names = new ArrayList<>();
        for (Furniture furniture1 : furniture) {
            names.add(furniture1.getName());
        }
        return names;
    }
    public boolean hasArtefact(String arte){
        for (Artefact artefact : artefacts) {
            if (artefact.getName().equals(arte)) {
                return true;
            }
        }
        return false;
    }
    public boolean hasFurniture(String fur){
        for (Furniture furniture1 : furniture) {
            if (furniture1.getName().equals(fur)) {
                return true;
            }
        }
        return false;
    }
    public boolean hasCharacter(String chara){
        for (Character character : characters) {
            if (character.getName().equals(chara)) {
                return true;
            }
        }
        return false;
    }
    public boolean hasItem(String item){
        return hasFurniture(item)||hasArtefact(item)||hasCharacter(item);
    }
    public boolean hasPath(String path){
        for(String path1:paths){
            if(path1.equals(path)){
                return true;
            }
        }
        return false;
    }
    public Furniture getFurniture(String fur){
        for (Furniture furniture1 : furniture) {
            if (furniture1.getName().equals(fur)) {
                return furniture1;
            }
        }
        return null;
    }
    public Artefact getArtefact(String arte){
        for (Artefact artefact : artefacts) {
            if (artefact.getName().equals(arte)) {
                return artefact;
            }
        }
        return null;
    }
    public void deleteArtefact(Artefact a){
        for(int i=0,len=artefacts.size();i<len;i++){
            if(artefacts.get(i)==a){
                artefacts.remove(i);
                --len;
                i=len;
            }
        }
    }
    public void deleteFurniture(Furniture f){
        for(int i=0,len=furniture.size();i<len;i++){
            if(furniture.get(i)==f){
                furniture.remove(i);
                --len;
                i=len;
            }
        }
    }
    public void deleteCharacter(Character a){
        for(int i=0,len=characters.size();i<len;i++){
            if(characters.get(i)==a){
                characters.remove(i);
                --len;
                i=len;
            }
        }
    }
    public void addArtefact(Artefact a){
        artefacts.add(a);
    }
    public void addArtefact(ArrayList<Artefact> a){
        artefacts.addAll(a);
    }
    public void addPath(String path){
        paths.add(path);
    }
}
