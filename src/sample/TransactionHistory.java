package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TransactionHistory extends Item {
    private SimpleStringProperty transactionId;
    private SimpleStringProperty date;
    private SimpleIntegerProperty price;
    private SimpleStringProperty name;
    private SimpleStringProperty address;
    private SimpleStringProperty phoneNumber;
    private SimpleStringProperty transactionType;
    private SimpleIntegerProperty transactorId;



    public TransactionHistory(String transactionId,String date,String itemId, Integer itemQty, String itemDesc,String name,String address,String phoneNumber,Integer price) {
        super(itemId, itemQty,itemDesc);
        this.transactionId = new SimpleStringProperty(transactionId);
        this.date = new SimpleStringProperty(date);
        this.price = new SimpleIntegerProperty(price);
        this.name =  new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }
    public TransactionHistory(String transactionId,String date,String itemId, Integer itemQty, String itemDesc,String name,String address,String phoneNumber,Integer price,String transactionType,int transactorId) {
        super(itemId, itemQty,itemDesc);
        this.transactionId = new SimpleStringProperty(transactionId);
        this.date = new SimpleStringProperty(date);
        this.price = new SimpleIntegerProperty(price);
        this.name =  new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.transactionType = new SimpleStringProperty(transactionType);
        this.transactorId = new SimpleIntegerProperty(transactorId);
    }

    public int getTransactorId() {
        return transactorId.get();
    }

    public SimpleIntegerProperty transactorIdProperty() {
        return transactorId;
    }

    public void setTransactorId(int transactorId) {
        this.transactorId.set(transactorId);
    }

    public String getTransactionType() {
        return transactionType.get();
    }

    public SimpleStringProperty transactionTypeProperty() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType.set(transactionType);
    }

    public String getTransactionId() {
        return transactionId.get();
    }

    public SimpleStringProperty transactionIdProperty() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId.set(transactionId);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public int getPrice() {
        return price.get();
    }

    public SimpleIntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }
}
