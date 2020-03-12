public class NoValidCommandException extends Exception{
    String command;
    public NoValidCommandException(String commmand)
    {
        this.command = commmand;
    }

    public String toString()
    {
        return this.getClass().getName() + ": The command: \""+command+ "\" is not valid";
    }
}
