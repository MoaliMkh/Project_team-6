package model.account;

import controller.ProgramManager;
import model.logs.BuyLog;
import model.logs.LogsInGeneral;
import model.product.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Buyer extends Account implements Comparable<Buyer> {
    private long credit;
    public static ArrayList<Integer> buyLogIds = new ArrayList<Integer>();
    private ArrayList<Product> buyBasket = new ArrayList<>();

    public Buyer(String username, String password, String firstName, String lastName, String emailAddress, String phoneNumber) {
        super(username, password, firstName, lastName, emailAddress, phoneNumber);
        this.role = 1;
        credit = 0;
    }

    public boolean doesHaveEnoughMoney(long price) {
        return credit >= price;
    }

    public void modifyCreditBy(long amount) {
        credit += amount;
    }

    public long getCredit() {
        return credit;
    }

    public int compareTo(Buyer buyer) {
        switch (field) {
            case 1:
                return -(buyer.firstName.compareTo(this.firstName));
            case 2:
                return -(buyer.lastName.compareTo(this.lastName));
            case 3:
                return -(buyer.phoneNumber.compareTo(this.phoneNumber));
            case 4:
                return -(buyer.emailAddress.compareTo(this.emailAddress));
            case 5:
                return -(buyer.username.compareTo(this.username));
            default:
                return 0;
        }
    }

    private static int field = 1;
    private static ArrayList<Buyer> buyerArrayList = new ArrayList<>();

    public static ArrayList<Buyer> sortBuyers(int fieldSort) {
        /*
        How to use this method :
        fieldSort = 1 for firstName sort
        fieldSort = 2 for lastName sort
        fieldSort = 3 for phoneNumber sort
        fieldSort = 4 for emailAddress sort
        fieldSort = 5 for username sort
         */
        field = fieldSort;
        Collection<Account> values = ProgramManager.getProgramManagerInstance().getAllAccounts();
        ArrayList<Account> listOfValues = new ArrayList<>(values);
        for (int i = 0; i < listOfValues.size(); i++) {
            if (listOfValues.get(i).role == 1) {
                buyerArrayList.add((Buyer) listOfValues.get(i));
            }
        }
        Collections.sort(buyerArrayList);
        return buyerArrayList;
    }

    public void addProductToBuyBasket(Product product) {
        buyBasket.add(product);
    }

    public void addProductToBuyBasket(ArrayList<Product> products) {
        buyBasket.addAll(products);
    }
}