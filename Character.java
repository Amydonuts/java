import com.alexmerz.graphviz.objects.Node;

public class Character {
    private String name;
    private String description;

    public Character(Node n){
        this.name = n.getId().getId();
        this.description = n.getAttribute("description");
    }
    public Character(String name){
        this.name = name;
        this.description = "user";
    }

    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
}
