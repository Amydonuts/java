import java.util.ArrayList;

public class Player extends Character{
    private String name;
    private ArrayList<Artefact> artefacts;
    private Location location;
    private int healthLevel;

    public Player(String name){
        super(name);
        this.name = name;
        this.artefacts = new ArrayList<Artefact>();
        this.healthLevel = 3;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public boolean hasArtefact(String arte){
        for (Artefact artefact : artefacts) {
            if (artefact.getName().equals(arte)) {
                return true;
            }
        }
        return false;
    }

    public Artefact getArtefact(String arte){
        for (Artefact artefact : artefacts) {
            if (artefact.getName().equals(arte)) {
                return artefact;
            }
        }
        return null;
    }
    public ArrayList<Artefact> getAllArtefacts(){
        return artefacts;
    }
    public void addArtefact(Artefact a){
        artefacts.add(a);
    }
    public void addArtefact(ArrayList<Artefact> a){
        artefacts.addAll(a);
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

    public void setLocation(Location l){
        location = l;
    }

    public Location getLocation(){
        return location;
    }
    public ArrayList<String> getAllArtefactsName(){
        ArrayList<String> names = new ArrayList<>();
        for (Artefact artefact : artefacts) {
            names.add(artefact.getName());
        }
        return names;
    }
    public void deleteAllArtefact(){
        if (artefacts.size() > 0) {
            artefacts.subList(0, artefacts.size()).clear();
        }
    }
    public void minisHealth(){
        healthLevel--;
    }
    public void plusHealth(){
        healthLevel++;
    }
    public int getHealthLevel(){
        return healthLevel;
    }
    public void resetHealthLevel(){
        healthLevel = 3;
    }
}
