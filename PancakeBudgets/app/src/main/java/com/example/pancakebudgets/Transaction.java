package com.example.pancakebudgets;

public class Transaction {
    String transactionId;
    String transactionName;
    String transactionDate;
    String transactionPrice;
    String transactionCategory;

    public Transaction(){

    }

    public Transaction(String transactionId, String transactionName, String transactionDate, String transactionPrice, String transactionCategory) {
        this.transactionId = transactionId;
        this.transactionName = transactionName;
        this.transactionDate = transactionDate;
        this.transactionPrice = transactionPrice;
        this.transactionCategory = transactionCategory;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionPrice() {
        return transactionPrice;
    }

    public String getTransactionCategory() {
        return transactionCategory;
    }
}
