import com.alexmerz.graphviz.objects.Node;

public class Furniture {
    private String name;
    private String description;
    private Location location;

    public Furniture(Node n){
        this.name = n.getId().getId();
        this.description = n.getAttribute("description");
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location l) {
        location = l;
    }

}
