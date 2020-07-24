package server.controller.sellerPanels;

import server.Server;
import server.controller.Parent;
import server.controller.ProgramManager;
import server.model.account.Account;
import server.model.account.Seller;
import server.model.product.Product;
import server.model.requests.OffRequest;
import client.view.userPanel.OffManagementSellerView;

import java.io.IOException;
import java.security.AccessControlContext;

public class OffManagementSeller implements Parent {

    private Server server = null;

    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        String message = "";
        for (Integer integer : ProgramManager.getProgramManagerInstance().getAllOffs().keySet()) {
            message = message + ProgramManager.getProgramManagerInstance().getAllOffs().get(integer).getOffId() + "---";
        }
        sendMessage(message);
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("02-" + message);
    }
    public void showProductIds(){
        String message = "";
        Account tempAccount = server.getCurrentlyLoggedInUsers();
        for (Integer productId : ((Seller) (tempAccount)).productIds) {
            message = message + ProgramManager.getProgramManagerInstance().getProductById(productId).getName() + "---";
        }
        try {
            sendMessage(message);
        } catch (IOException e) {
            System.err.println("error occurred");
        }

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
