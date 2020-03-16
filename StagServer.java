import java.io.*;
import java.net.*;
import java.util.*;

class StagServer
{
    public static void main(String args[])
    {
        if(args.length != 2) System.out.println("Usage: java StagServer <entity-file> <action-file>");
        else new StagServer(args[0], args[1], 8888);
    }

    public StagServer(String entityFilename, String actionFilename, int portNumber)
    {
        try {
            ServerSocket ss = new ServerSocket(portNumber);
            System.out.println("Server Listening");
            StagModel model = new StagModel(entityFilename,actionFilename);
            StagController controller = new StagController("player", model.getEntity(), model.getActions());
            while(true) acceptNextConnection(controller,ss);
        } catch(IOException ioe) {
            System.err.println(ioe);
        }
    }

    private void acceptNextConnection(StagController controller,ServerSocket ss)
    {
        try {
            // Next line will block until a connection is received
            Socket socket = ss.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            processNextCommand(controller, in, out);

            out.close();
            in.close();
            socket.close();
        } catch(IOException ioe) {
            System.err.println(ioe);
        }
    }

    private void processNextCommand(StagController controller, BufferedReader in, BufferedWriter out)  {

        try {
            String line = in.readLine();
            String playerName = line.substring(0, line.indexOf(":"));
            if(controller.getCurrentPlayerName().equals("player")) {
                controller.setCurrentPlayerName(playerName);
            }
            if(!controller.getCurrentPlayerName().equals(playerName)){
                if(controller.hasPlayer(playerName)){
                    controller.setCurrentPlayer(playerName);
                }
                else{
                    controller.addNewPlayer(playerName);
                }
            }
            controller.separateCommand(line);
            out.write("You said... " + line + "\n"+controller.getResult());
            System.out.println("You said... " + line + "\n"+controller.getResult());
        }
        catch(IOException | SubjectDoesNotExistException | NoValidCommandException ioe) {
            System.err.println(ioe);
        }
    }
}
