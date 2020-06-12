package view;

import controller.ProgramManager;
import model.product.Category;

import java.util.ArrayList;

public class CategoriesAndSubCategoriesMenuView {
    public CategoriesAndSubCategoriesMenuView(){
        System.out.println("=== Categories menu");
        ArrayList<Category> categories = (ArrayList<Category>) ProgramManager.getProgramManagerInstance().getAllCategories();
        for (Category category : categories) {
            System.out.println("\t" + category.getName());
        }
    }
    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if(command.matches("edit \\.+")){
                return command;
            }
            else if(command.equals("help")){
                showHelp();
            }
            else if(command.matches("add \\.+")){
                return command;
            }
            else if(command.matches("remove \\.+")){
                return command;
            }
            else if(command.equals("show categories")){
                return command;
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }

    private void showHelp() {
        System.out.println("List of commands:\n\tadd [Category's name]\n\tedit [Category's name] [newValue]\n\tremove [Category's name]");
    }

    public void giveOutPut(String message){
        System.out.println(message);
    }
    public void showCategories(){
        System.out.println(ProgramManager.getProgramManagerInstance().getAllCategories());
    }
}
