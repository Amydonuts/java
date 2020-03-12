import com.alexmerz.graphviz.objects.Node;

public class Artefact {
    private String name;
    private String description;
    private Object owner;

    public Artefact(Node n){
        this.name = n.getId().getId();
        this.description = n.getAttribute("description");
    }
    public Artefact(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public void setOwner(Object o){
        owner = o;
    }
    public Object getOwner(){
        return owner;
    }
}
