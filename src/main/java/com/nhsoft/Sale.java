package com.nhsoft;

import java.io.Serializable;

public class Sale implements Serializable{

    private int branchNums;
    private String branchName;
    private String money;
    private String cost;
    private String qty;
    private String target;

    public int getBranchNums() {
        return branchNums;
    }

    public void setBranchNums(int branchNums) {
        this.branchNums = branchNums;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
