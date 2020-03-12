

public class Triggers {
    String trigger;
    public Triggers(String trigger){
        this.trigger = trigger;
    }
    public boolean hasTrigger(String tri) {
        return trigger.equals(tri);
    }

    public String getTrigger() {
        return trigger;
    }
}
