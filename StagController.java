import java.util.ArrayList;

public class StagController {
    private Entity entity;
    private ArrayList<Actions> actions;
    private Player currentPlayer;
    private String result;

    public StagController(String defaultPlayerName, Entity entity, ArrayList<Actions> actions){
        this.actions = new ArrayList<>();
        this.entity = entity;
        this.actions = actions;
        this.currentPlayer = new Player(defaultPlayerName);
        this.entity.addPlayer(currentPlayer);
        this.entity.setCurrentPlayer(currentPlayer);
        Location location = this.entity.getFirstLocation();
        location.addCharacter(currentPlayer);
        this.entity.setCurrentLocation(location);
    }

    public void separateCommand(String command) throws SubjectDoesNotExistException, NoValidCommandException {
        String playerName = command.substring(0, command.indexOf(":"));
        command = command.substring(command.indexOf(":")+2);
        String[] commands = command.split("\\s+");
        handleCommands(commands);
        if(result.equals("")){
            throw(new NoValidCommandException(command));
        }
    }


    public boolean hasCommand(String subject,String[] commands)  {
        for(String command:commands){
            if(command.equals(subject)){
                return true;
            }
        }
        return false;
    }

    public void gotoLocation(String[] commands){
        Location location = entity.getCurrentLocation();
        ArrayList<String> paths= location.getPaths();
        for(String path:paths){
            if(hasCommand(path,commands)){
                location.deleteCharacter(currentPlayer);
                Location l = entity.getLocation(path);
                entity.setCurrentLocation(l);
                l.addCharacter(currentPlayer);
                result = "You go to the "+l.getName()+"\n";
            }
        }
    }

    public void getArtefact(String[] commands){
        Location location = entity.getCurrentLocation();
        ArrayList<String> artefactsNames = location.getAllArtefactsName();
        for(String name:artefactsNames) {
            if (hasCommand(name, commands)) {
                currentPlayer.addArtefact(location.getArtefact(name));
                location.deleteArtefact(location.getArtefact(name));
                result = "You get the "+name;
            }
        }
    }

    public void dropAretefact(String[] commands){
        Location location = entity.getCurrentLocation();
        ArrayList<String> artefactsNames = currentPlayer.getAllArtefactsName();
        for(String name:artefactsNames) {
            if (hasCommand(name, commands)) {
                location.addArtefact(currentPlayer.getArtefact(name));
                currentPlayer.deleteArtefact(currentPlayer.getArtefact(name));
                result = "You drop the "+name;
            }
        }
    }

    public void lookEntitiesInLocation(){
        Location location = entity.getCurrentLocation();
        String loc = "location name: "+location.getName()+"\n";
        ArrayList<String> characterNames = location.getAllCharactersName();
        ArrayList<String> artefactNames = location.getAllArtefactsName();
        ArrayList<String> furnitureNames = location.getAllFurnitureName();
        ArrayList<String> pathNames = location.getPaths();
        result = loc+"characters: "+characterNames+"\nartefacts: "+artefactNames+"\nfurniture: "+furnitureNames+"\npaths: "+pathNames+"\n";
    }

    public void listArtefactsPlayerHave(){
        String playerName = "player name: "+currentPlayer.getName()+"\n";
        ArrayList<String> artefactNames = currentPlayer.getAllArtefactsName();
        result = playerName+"artefacts: "+artefactNames+"\n";
    }



    public void handleCommands(String[] commands) throws SubjectDoesNotExistException{
        Location location = entity.getCurrentLocation();
        result="";
        //Check triggers in actions.
        for (Actions action : actions) {
            if (action.hasTriggered(commands[0])){
                action.startAction(entity);
                result = action.getNarration();
            }
        }
        if(hasCommand("goto",commands)){
            gotoLocation(commands);
        }
        if(hasCommand("get",commands)){
            getArtefact(commands);
        }
        if(hasCommand("drop",commands)){
            dropAretefact(commands);
        }
        if(commands.length==1&&commands[0].equals("look")){
            lookEntitiesInLocation();
        }
        if(commands.length==1&&(commands[0].equals("inventory")||commands[0].equals("inv"))){
            listArtefactsPlayerHave();
        }
        if(commands.length==1&&commands[0].equals("health")){
            result = currentPlayer.getName()+"'s health level: "+ currentPlayer.getHealthLevel();
        }

    }

    public String getResult(){
        return result;
    }
    public void setCurrentPlayerName(String name){
        currentPlayer.setName(name);
    }
    public boolean hasPlayer(String name){
        return entity.getPlayer(name) != null;
    }
    public void addNewPlayer(String name){
        Player player = new Player(name);
        entity.addPlayer(player);
        //New player start from the start room.
        Location startLoc = entity.getFirstLocation();
        player.setLocation(entity.getFirstLocation());
        startLoc.addCharacter(player);
        setCurrentPlayer(name);
    }
    public String getCurrentPlayerName(){
        return currentPlayer.getName();
    }
    public void setCurrentPlayer(String name){
        //When change the current player. First store the location of the former player.
        currentPlayer.setLocation(entity.getCurrentLocation());
        //Then get the changed player and its location.
        currentPlayer = entity.getPlayer(name);
        entity.setCurrentPlayer(currentPlayer);
        entity.setCurrentLocation(currentPlayer.getLocation());
    }
}
