import com.alexmerz.graphviz.objects.Graph;

import java.util.ArrayList;

public class Entity {
    private ArrayList<Location> locations;
    private ArrayList<Artefact> artefacts;
    private ArrayList<Furniture> furniture;
    private ArrayList<Character> characters;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Location currentLocation;

    public Entity(Graph g, Graph path){
        ArrayList<Graph> subGraphs1 = g.getSubgraphs();
        locations = new ArrayList<>();
        artefacts = new ArrayList<>();
        furniture = new ArrayList<>();
        characters = new ArrayList<>();
        players = new ArrayList<>();
        for (Graph g1 : subGraphs1) {
            locations.add(new Location(g1,path)) ;
        }
        for (Location location:locations){
            artefacts.addAll(location.getAllArtefacts());
            furniture.addAll(location.getAllFurniture());
            characters.addAll(location.getAllCharacters());
        }
    }

    public Location getLocation(String name){
        for (Location location : locations) {
            if (location.getName().equals(name)) {
                return location;
            }
        }
        return null;
    }
    public Artefact getArtefact(String name){
        for (Artefact artefact : artefacts) {
            if (artefact.getName().equals(name)) {
                return artefact;
            }
        }
        return null;
    }
    public Furniture getFurniture(String name){
        for (Furniture furniture1 : furniture) {
            if (furniture1.getName().equals(name)) {
                return furniture1;
            }
        }
        return null;
    }
    public Character getCharacter(String name){
        for (Character character : characters) {
            if (character.getName().equals(name)) {
                return character;
            }
        }
        return null;
    }
    public Player getPlayer(String name){
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public Location getCurrentLocation(){
        return currentLocation;
    }

    public void setCurrentLocation(Location location){
        currentLocation = location;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public void setCurrentPlayer(Player player){
        currentPlayer = player;
    }

    public boolean artefactExist(String arte){
        for (Artefact artefact : artefacts) {
            if (artefact.getName().equals(arte)) {
                return true;
            }
        }
        return false;
    }
    public boolean locationExist(String loca){
        for (Location location : locations) {
            if (location.getName().equals(loca)) {
                return true;
            }
        }
        return false;
    }
    public boolean furnitureExist(String fur){
        for (Furniture furni : furniture) {
            if (furni.getName().equals(fur)) {
                return true;
            }
        }
        return false;
    }
    public boolean characterExist(String ch){
        for (Character character : characters) {
            if (character.getName().equals(ch)) {
                return true;
            }
        }
        return false;
    }
    public void printAll(){
        for (Location location:locations){
            System.out.println(location.getName());
            for (Artefact artefact : location.getAllArtefacts()) {
                System.out.println("\t"+artefact.getName());
            }
            for (Furniture furniture : location.getAllFurniture()) {
                System.out.println("\t"+furniture.getName());
            }
            for (Character character : location.getAllCharacters()) {
                System.out.println("\t"+character.getName());
            }
        }
    }
    public Location getFirstLocation(){
        return locations.get(0);
    }
}
