package model.account;

import controller.ProgramManager;
import model.logs.BuyLog;
import model.logs.LogsInGeneral;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Buyer extends Account implements Comparable<Buyer> {
    private long credit;
    public static ArrayList<Integer> buyLogIds = new ArrayList<Integer>();

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
        if (field == 1) {
            return -(buyer.firstName.compareTo(this.firstName));
        } else if (field == 2) {
            return -(buyer.lastName.compareTo(this.lastName));
        } else if (field == 3) {
            return -(buyer.phoneNumber.compareTo(this.phoneNumber));
        } else if (field == 4) {
            return -(buyer.emailAddress.compareTo(this.emailAddress));
        } else if (field == 5) {
            return -(buyer.username.compareTo(this.username));
        } else {
            return 0;
        }
    }

    private static int field = 1;
    private static ArrayList<Buyer> buyerArrayList = new ArrayList<>();

    public ArrayList<Buyer> sortBuyers(int fieldSort) {
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
            if (listOfValues.get(i).role==1) {
                buyerArrayList.add((Buyer) listOfValues.get(i));
            }
        }
        Collections.sort(buyerArrayList);
        return buyerArrayList;
    }
}