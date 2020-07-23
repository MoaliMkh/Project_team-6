package server.controller.sellerPanels;

import server.Server;
import server.controller.Parent;
import server.controller.ProgramManager;
import server.model.requests.OffRequest;
import client.view.userPanel.OffManagementSellerView;

import java.io.IOException;

public class OffManagementSeller implements Parent {

    private Server server = null;

    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        sendMessage("start");
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("02-" + message);
    }

    public void viewOff(String substring) throws IOException {
        sendMessage("");
    }

    public void editOff(String substring) throws IOException {
        sendMessage("");
    }

    public void addOff(String substring) throws IOException {
        sendMessage("");
    }

    /*
    private static OffManagementSeller instance;
    public static OffManagementSeller getInstance(){
        if (instance == null)
            instance = new OffManagementSeller();
        return instance;
    }
    ////////////////////////////
    private OffManagementSellerView view;

    public void start(){
        view = new OffManagementSellerView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.matches("view \\.+")) {
                ProgramManager.getProgramManagerInstance().showOff(ProgramManager.getProgramManagerInstance().getOffById(Integer.parseInt(command.split("\\s")[1])));
            }
            else if (command.matches("edit \\.+")) {
                OffRequest offRequest = new OffRequest(ProgramManager.getProgramManagerInstance().getOffById(Integer.parseInt(command.split("\\s")[1])), (byte) 1,command.split("\\s")[2]);
                ProgramManager.getProgramManagerInstance().addRequestToList(offRequest);
            }
            else if(command.equalsIgnoreCase("add off")){
                OffRequest offRequest = new OffRequest(ProgramManager.getProgramManagerInstance().getOffById(Integer.parseInt(command.split("\\s")[1])),(byte)0,null);
                ProgramManager.getProgramManagerInstance().addRequestToList(offRequest);
            }
            else if (command.equals("back")) {
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to OffManagementSeller by client.view");
            }
        }
    }
    */


}