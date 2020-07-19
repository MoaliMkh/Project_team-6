package server.controller;

import server.Server;
import server.model.product.Category;
import server.model.product.Product;
import server.model.product.SubCategory;
import client.view.old.CategoriesAndSubCategoriesMenuView;

import java.io.IOException;
import java.util.ArrayList;


public class CategoriesAndSubCategoriesMenu implements Parent {
    private Server server = null;

    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        String message = "";
        for (int i = 0; i < allCategoriesArrayList.size(); i++) {
            message = message + "---" + i + ". " + allCategoriesArrayList.get(i);
        }
        sendMessage(message);
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("12-" + message);
    }

    ///////////////////////////////////////////
    private CategoriesAndSubCategoriesMenuView view;
    private Category currentCategory = null;
    private SubCategory currentSubcategory = null;

    private ArrayList<Category> allCategoriesArrayList;
    private ArrayList<SubCategory> allSubCategoriesArrayList;
    private ArrayList<Product> allProductsArrayList;

    private void updateCategoriesArrayList() {
        allCategoriesArrayList = new ArrayList<>(ProgramManager.getProgramManagerInstance().getAllCategories());
        //TODO : Kamali please Do some Sort:)
        categorySort(allCategoriesArrayList);
    }

    private void updateSubCategoriesArrayList() {
        allSubCategoriesArrayList = new ArrayList<>(currentCategory.getAllSubCategories());
        //TODO : Kamali please Do some Sort:)
        subCategorySort(allSubCategoriesArrayList);
    }

    private void updateProductsArrayList() {
        ArrayList<Integer> productIds = currentSubcategory.getAllProductIds();
        allProductsArrayList = new ArrayList<>();
        ProgramManager programManager = ProgramManager.getProgramManagerInstance();
        for (Integer id : productIds) {
            allProductsArrayList.add(programManager.getProductById(id));
        }
        //TODO: maybe do some sorting?
    }


    ///////////////////////////////////
    public void editCategory(int index, String newName) {
        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
            Category category = allCategoriesArrayList.get(index);
            category.setName(newName);
            updateCategoriesArrayList();
            try {
                sendMessage("edited");
            } catch (IOException e) {
                System.err.println("error occurred");
            }
        }
    }

    public void editSubCategory(int index, String newName) {
        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
            SubCategory subCategory = allSubCategoriesArrayList.get(index);
            subCategory.setName(newName);
            updateSubCategoriesArrayList();
            try {
                sendMessage("edited");
            } catch (IOException e) {
                System.err.println("error occurred");
            }
        }
    }

    public void addCategory(String Data) throws IOException {
        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
            if (!ProgramManager.getProgramManagerInstance().getAllCategories().contains(ProgramManager.getProgramManagerInstance().getCategoryByName(Data.split("---")[0]))) {
                ArrayList<String> additionalAttributes = new ArrayList<>();
                String[] dataSplit = Data.split("---");
                for (int i = 1; i < dataSplit.length; i++) {
                    additionalAttributes.add(dataSplit[i]);
                }
                allCategoriesArrayList.add(new Category(dataSplit[0], additionalAttributes));
                updateCategoriesArrayList();
                sendMessage("Added");
            } else
                sendMessage("duplicateCategory");
        }
    }

    public void addSubCategory(String Data) {
        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
            if (!currentCategory.getAllSubCategories().contains(currentCategory.getSubCategoryByName(Data.split("---")[0]))) {
                ArrayList<String> additionalAttributes = new ArrayList<>();
                String[] dataSplit = Data.split("---");
                for (int i = 1; i < dataSplit.length; i++) {
                    additionalAttributes.add(dataSplit[i]);
                }
                allSubCategoriesArrayList.add(new SubCategory(dataSplit[0], additionalAttributes));
                updateSubCategoriesArrayList();
                try {
                    sendMessage("Added");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    sendMessage("duplicateSubCategory");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void removeCategory(int index) {
        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
            allCategoriesArrayList.remove(index);
            updateCategoriesArrayList();
            try {
                sendMessage("removed");
            } catch (IOException e) {
                System.err.println("error occurred");
            }
        }
    }

    public void removeSubCategory(int index) {
        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
            allSubCategoriesArrayList.remove(index);
            updateSubCategoriesArrayList();
            try {
                sendMessage("removed");
            } catch (IOException e) {
                System.err.println("error occurred");
            }
        }
    }

    public void openCategory(int index) {
        Category tempCategory = allCategoriesArrayList.get(index);
        try {
            String categoriesMessage = "";
            for (SubCategory allSubCategory : currentCategory.getAllSubCategories()) {
                categoriesMessage = categoriesMessage + allSubCategory.getName() + "---";
            }
            currentCategory = tempCategory;
            sendMessage(categoriesMessage);
        } catch (IOException e) {
            System.err.println("error occurred");
        }
    }

    public void openSubCategory(int index) {
        SubCategory tempSubCategory = allSubCategoriesArrayList.get(index);
        try {
            String subCategoriesMessage = "";
            for (Integer allProductId : currentSubcategory.getAllProductIds()) {
                subCategoriesMessage = subCategoriesMessage + ProgramManager.getProgramManagerInstance().getProductById(allProductId).getName() + "---";
            }
            currentSubcategory = tempSubCategory;
            sendMessage(subCategoriesMessage);
        } catch (IOException e) {
            System.err.println("error occurred");
        }
    }


    public void categorySort(ArrayList<Category> allCategoriesArrayList) {
        //TODO ostad kamali

    }

    public void subCategorySort(ArrayList<SubCategory> allSubCategoriesArrayList) {
        //TODO ostad kamali
    }
}
