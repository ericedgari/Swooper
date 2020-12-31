package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerAdmin implements Initializable {
    @FXML
    public Button goStorageButtonST,goShopButtonST,moveButtonST,goShopButtonTT, addButtonTT, saveButtonTT, goStorageButtonBIT, addButtonBIT, saveButtonBIT;
    @FXML
    public TableView storageTableViewST, shopTableViewST,shopStockTableViewTT, currentTransactionTableViewTT, transactionHistoryTableViewTT, storageTableViewBIT, currentTransactionTableViewBIT, transactionHistoryTableViewBIT;
    @FXML
    public TextField itemIdTextFieldST, itemQuantityTextFieldST,customerNameTextField, customerAddressTextField, customerNumberTextField,
            enterItemIdTextField, enterItemQtyTextField, supplierNameTextFieldBIT, supplierAddressTextFieldBIT, supplierPhoneTextFieldBIT,
            itemIdTextFieldBIT, itemQuantityTextFieldBIT, sellPriceTextFieldBIT, buyPriceTextFieldBIT,itemNameTextFieldBIT;
    @FXML
    public ComboBox storageComboBoxST, shopComboBoxST, fromComboBox, toComboBox, shopComboBoxTT, storageComboBoxBIT ;

    @FXML
    public Text storageIdTextST, storageAddressTextST, storageNumberTextST, shopIdTextST, shopAddressTextST, shopNumberTextST, shopIdTextTT,
            shopAddressTextTT, shopNumberTextTT, customerNameLabelTT,customerAddressLabelTT,customerPhoneLabelTT,storageIdTextBIT, storageAddressTextBIT,
            storageNumberTextBIT, supplierNameLabelBIT, supplierAddressLabelBIT,supplierPhoneLabelBIT;


    ObservableList<String> storageLists = FXCollections.observableArrayList(
            "Storage 1", "Storage 2"
    );
    ObservableList<String> shopLists = FXCollections.observableArrayList(
            "Shop 1", "Shop 2"
    );
    ObservableList<String> shopStorageLists = FXCollections.observableArrayList(
            "Shop 1", "Shop 2", "Storage 1", "Storage 2"
    );
    ObservableList<Item> shopItems = FXCollections.observableArrayList();
    ObservableList<Item> storageItems = FXCollections.observableArrayList();





    public TableColumn<CurrentTransactionTableTT,String>transactionIdColumnTT;
    public TableColumn<CurrentTransactionTableTT,Integer>transactionQtyColumnTT;
    public TableColumn<CurrentTransactionTableTT,Integer>transactionPriceColumnTT;
    public TableColumn<CurrentTransactionTableTT,Integer>transactionTotalColumnTT;
    ObservableList<CurrentTransactionTableTT> currentTransactionTableDataTT = FXCollections.observableArrayList();

    public TableColumn<CurrentTransactionTableBIT,String>transactionIdColumnBIT;
    public TableColumn<CurrentTransactionTableBIT,String>transactionItemNameColumnBIT;
    public TableColumn<CurrentTransactionTableBIT,Integer>transactionQtyColumnBIT;
    public TableColumn<CurrentTransactionTableBIT,Integer>transactionBuyPriceColumnBIT;
    public TableColumn<CurrentTransactionTableBIT,Integer>transactionSellPriceColumnBIT;
    public TableColumn<CurrentTransactionTableBIT,Integer>transactionTotalColumnBIT;
    ObservableList<CurrentTransactionTableBIT> currentTransactionTableDataBIT = FXCollections.observableArrayList();

//    initialize database connection
    public Database database = new Database();


//static int for transactionTable
    static int transactionId;
    static int transactorId;





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        storageComboBoxST.setItems(storageLists);
        shopComboBoxST.setItems(shopLists);
        shopComboBoxTT.setItems(shopLists);
        storageComboBoxBIT.setItems(storageLists);
        fromComboBox.setItems(shopStorageLists);



        //Storage Tab
        TableColumn itemIdStorage = new TableColumn("Item ID");
        itemIdStorage.setMinWidth(300);
        itemIdStorage.setCellValueFactory(
                //sets from what class to take the data from and what is the type of data
                new PropertyValueFactory<Item, String>("itemId"));

        TableColumn itemQtyStorage = new TableColumn("Item Quantity");
        itemQtyStorage.setMinWidth(300);
        itemQtyStorage.setCellValueFactory(
                //sets from what class to take the data from and what is the type of data
                new PropertyValueFactory<Item, Integer>("itemQty"));

        TableColumn itemDescStorage = new TableColumn("Item Desc");
        itemDescStorage.setMinWidth(300);
        itemDescStorage.setCellValueFactory(
                //sets from what class to take the data from and what is the type of data
                new PropertyValueFactory<Item, Integer>("itemDesc"));
        storageTableViewST.setItems(storageItems);

        storageTableViewST.getColumns().addAll(itemIdStorage, itemQtyStorage, itemDescStorage);




        TableColumn itemIdShop = new TableColumn("Item ID");
        itemIdShop.setMinWidth(300);
        itemIdShop.setCellValueFactory(
                //sets from what class to take the data from and what is the type of data
                new PropertyValueFactory<Item, String>("itemId"));

        TableColumn itemQtyShop = new TableColumn("Item Quantity");
        itemQtyShop.setMinWidth(300);
        itemQtyShop.setCellValueFactory(
                //sets from what class to take the data from and what is the type of data
                new PropertyValueFactory<Item, Integer>("itemQty"));

        TableColumn itemDescShop = new TableColumn("Item Desc");
        itemDescShop.setMinWidth(300);
        itemDescShop.setCellValueFactory(
                //sets from what class to take the data from and what is the type of data
                new PropertyValueFactory<Item, String>("itemDesc"));

        shopTableViewST.setItems(shopItems);

        shopTableViewST.getColumns().addAll(itemIdShop, itemQtyShop, itemDescShop);







//        setting the observable list for the current transaction Table TT;
        transactionIdColumnTT.setCellValueFactory(new PropertyValueFactory<CurrentTransactionTableTT,String>("itemId"));
        transactionQtyColumnTT.setCellValueFactory(new PropertyValueFactory<CurrentTransactionTableTT,Integer>("itemQty"));
        transactionPriceColumnTT.setCellValueFactory(new PropertyValueFactory<CurrentTransactionTableTT,Integer>("itemPrice"));
        transactionTotalColumnTT.setCellValueFactory(new PropertyValueFactory<CurrentTransactionTableTT,Integer>("total"));
        currentTransactionTableViewTT.setItems(currentTransactionTableDataTT);

//        Setting the observable list for the current transaction table BIT
        transactionIdColumnBIT.setCellValueFactory(new PropertyValueFactory<CurrentTransactionTableBIT,String>("itemId"));
        transactionItemNameColumnBIT.setCellValueFactory(new PropertyValueFactory<CurrentTransactionTableBIT,String>("itemName"));
        transactionQtyColumnBIT.setCellValueFactory(new PropertyValueFactory<CurrentTransactionTableBIT,Integer>("itemQty"));
        transactionBuyPriceColumnBIT.setCellValueFactory(new PropertyValueFactory<CurrentTransactionTableBIT,Integer>("itemBuyPrice"));
        transactionSellPriceColumnBIT.setCellValueFactory(new PropertyValueFactory<CurrentTransactionTableBIT,Integer>("itemSellPrice"));
        transactionTotalColumnBIT.setCellValueFactory(new PropertyValueFactory<CurrentTransactionTableBIT,Integer>("total"));
        currentTransactionTableViewBIT.setItems(currentTransactionTableDataBIT);
    }
    public void fromComboBoxPicked(){
        if(shopLists.contains(fromComboBox.getValue()))
        {
            toComboBox.setItems(storageLists);
        }
        else
        {
            toComboBox.setItems(shopStorageLists);
        }
    }





    public void goStorageButtonSTClicked() throws SQLException {
        if(storageComboBoxST.getValue() == "Storage 1")
        {
            storageItems.clear();
            ResultSet storage1 = database.getShopStorageInfo("ST001");
            ResultSet storage1Items = database.getShopStorageItems("ST001");

            while (storage1.next()) {
                storageIdTextST.setText(storage1.getString("storage_id"));
                storageAddressTextST.setText(storage1.getString("storage_address"));
                storageNumberTextST.setText(storage1.getString("storage_telephone_number"));
            }
            while (storage1Items.next())
            {
                storageItems.add(new Item(storage1Items.getString("item_id"), Integer.parseInt(storage1Items.getString("storage_stock_quantity")), storage1Items.getString("item_description")));
            }
            storage1.close();
            storage1Items.close();

        }
        else
        {
            storageItems.clear();
            ResultSet storage2 = database.getShopStorageInfo("ST002");
            ResultSet storage2Items = database.getShopStorageItems("ST002");

            while (storage2.next()) {
                storageIdTextST.setText(storage2.getString("storage_id"));
                storageAddressTextST.setText(storage2.getString("storage_address"));
                storageNumberTextST.setText(storage2.getString("storage_telephone_number"));
            }
            while (storage2Items.next())
            {
                storageItems.add(new Item(storage2Items.getString("item_id"), Integer.parseInt(storage2Items.getString("storage_stock_quantity")),storage2Items.getString("item_description")));
            }
            storage2.close();
            storage2Items.close();

        }
    }
    public void goShopButtonSTClicked() throws SQLException {
        if(shopComboBoxST.getValue() == "Shop 1")
        {
            shopItems.clear();
            ResultSet shop1 = database.getShopStorageInfo("SH001");
            ResultSet shop1Items = database.getShopStorageItems("SH001");


            while (shop1.next()) {
                shopIdTextST.setText(shop1.getString("shop_id"));
                shopAddressTextST.setText(shop1.getString("shop_address"));
                shopNumberTextST.setText(shop1.getString("shop_telephone_number"));
            }
            while(shop1Items.next())
            {
                shopItems.add(new Item(shop1Items.getString("item_id"), Integer.parseInt(shop1Items.getString("shop_stock_quantity")), shop1Items.getString("item_description")));
            }
            shop1.close();
            shop1Items.close();

        }
        else
        {
            shopItems.clear();
            ResultSet shop2 = database.getShopStorageInfo("SH002");
            ResultSet shop2Items = database.getShopStorageItems("SH002");

            while (shop2.next()) {
                shopIdTextST.setText(shop2.getString("shop_id"));
                shopAddressTextST.setText(shop2.getString("shop_address"));
                shopNumberTextST.setText(shop2.getString("shop_telephone_number"));
            }
            while (shop2Items.next())
            {
                shopItems.add(new Item(shop2Items.getString("item_id"), Integer.parseInt(shop2Items.getString("shop_stock_quantity")), shop2Items.getString("item_description")));
            }
            shop2.close();
            shop2Items.close();
        }
    }

    public void MoveButtonSTClicked() throws SQLException {
//        String inputId = itemIdTextFieldST.getText();
//        String inputQty = itemQuantityTextFieldST.getText();
//        if(shopLists.contains(toComboBox.getValue()))
//        {
//            if(toComboBox.getValue() == "Shop 1"){
//                ResultSet rsInsertTo = database.insertItem("SH001",inputId, Integer.parseInt(inputQty));
//
//                while (rsInsertTo.next())
//                {
//                    if(isUnique(itemIdTextFieldST))
//                    {
//                        shopItems.add(new Item(shop1Items.getString("item_id"), Integer.parseInt(shop1Items.getString("shop_stock_quantity")), shop1Items.getString("item_description")));
//                    }
//                    else
//                    {
//                        shopItems
//                    }
//
//                }
//            }
//
//        }else{
//            if(toComboBox.getValue() == "Storage 1"){
//
//            }
//
//        }

    }
    public boolean isUnique(TextField itemIdTextField)
    {
        //For each loop to check every product inside products observable array list.
        for(Item item:shopItems)
        {
            //if the part number inputted by the user exist in the observable list the function returns false
            if(item.getItemId().equals(itemIdTextFieldST.getText()))
            {
                return false;
            }
        }
        //returns true by default
        return true;
    }

    public void goShopButtonTTClicked() throws SQLException {
        if (shopComboBoxTT.getValue() == "Shop 1") {
            ResultSet shop1 = database.getShopStorageInfo("SH001");
            while (shop1.next()) {
                shopIdTextTT.setText(shop1.getString("shop_id"));
                shopAddressTextTT.setText(shop1.getString("shop_address"));
                shopNumberTextTT.setText(shop1.getString("shop_telephone_number"));
            }
            shop1.close();
        } else {
            ResultSet shop2 = database.getShopStorageInfo("SH002");
            while (shop2.next()) {
                shopIdTextTT.setText(shop2.getString("shop_id"));
                shopAddressTextTT.setText(shop2.getString("shop_address"));
                shopNumberTextTT.setText(shop2.getString("shop_telephone_number"));
            }
            shop2.close();
        }
    }

    public void goStorageButtonBITClicked() throws SQLException{
        if (storageComboBoxBIT.getValue() == "Storage 1") {
            ResultSet storage1 = database.getShopStorageInfo("ST001");
            while (storage1.next()) {
                storageIdTextBIT.setText(storage1.getString("storage_id"));
                storageAddressTextBIT.setText(storage1.getString("storage_address"));
                storageNumberTextBIT.setText(storage1.getString("storage_telephone_number"));
            }
            storage1.close();
        } else {
            ResultSet storage2 = database.getShopStorageInfo("ST002");
            while (storage2.next()) {
                storageIdTextBIT.setText(storage2.getString("storage_id"));
                storageAddressTextBIT.setText(storage2.getString("storage_address"));
                storageNumberTextBIT.setText(storage2.getString("storage_telephone_number"));
            }
            storage2.close();
        }
    }

    public void addButtonClickedTT(javafx.event.ActionEvent event) {

        String customerName = customerNameTextField.getText();
        String customerAddress = customerAddressTextField.getText();
        String customerPhoneNumber = customerNumberTextField.getText();
        String itemId = enterItemIdTextField.getText();
        int itemQty = Integer.parseInt(enterItemQtyTextField.getText());
        int itemPrice = 800;
        int itemTotal = itemPrice*itemQty;

        customerNameLabelTT.setText(customerName);
        customerAddressLabelTT.setText(customerAddress);
        customerPhoneLabelTT.setText(String.valueOf(customerPhoneNumber));

        currentTransactionTableDataTT.add(new CurrentTransactionTableTT(itemId,itemQty,itemPrice,itemTotal));

    }

    public void addButtonClickedBIT(javafx.event.ActionEvent event){
        String supplierName = supplierNameTextFieldBIT.getText();
        String supplierAddress = supplierAddressTextFieldBIT.getText();
        String supplierPhoneNumber = supplierPhoneTextFieldBIT.getText();
        String itemId = itemIdTextFieldBIT.getText();
        String itemName = itemNameTextFieldBIT.getText();
        int itemQty = Integer.parseInt(itemIdTextFieldBIT.getText());
        int itemSellPrice = Integer.parseInt(sellPriceTextFieldBIT.getText());
        int itemBuyPrice = Integer.parseInt(buyPriceTextFieldBIT.getText());
        int itemTotal = itemBuyPrice*itemQty;

        supplierNameLabelBIT.setText(supplierName);
        supplierAddressLabelBIT.setText(supplierAddress);
        supplierPhoneLabelBIT.setText(supplierPhoneNumber);

        currentTransactionTableDataBIT.add(new CurrentTransactionTableBIT(itemId,itemName,itemQty,itemBuyPrice,itemSellPrice,itemTotal));


    }

}