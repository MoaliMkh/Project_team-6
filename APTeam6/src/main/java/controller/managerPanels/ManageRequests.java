package controller.managerPanels;

import controller.LoginMenu;
import controller.ProgramManager;
import model.requests.ProductRequest;
import model.requests.Request;
import view.LoginMenuView;
import view.ManageRequestsView;

public class ManageRequests {
    private static ManageRequests ManageRequestsInstance = null;

    public static ManageRequests getManageRequestsInstance() {
        if (ManageRequestsInstance == null)
            ManageRequestsInstance = new ManageRequests();
        return ManageRequestsInstance;
    }

    private ManageRequestsView view;

    public void start() {
        view = new ManageRequestsView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.matches("details \\.+")) {


            }
            else if (command.matches("accept \\.+")) {

            }
            else if (command.matches("decline \\.+")) {

            }
            else if (command.equals("back")) {
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to ManageRequests by view");
            }
        }
    }

    public void accept() {
        view.accept();

    }

    public void decline() {

    }

    public void showDetails() {

    }
}
