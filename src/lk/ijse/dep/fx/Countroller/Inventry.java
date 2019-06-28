package lk.ijse.dep.fx.Countroller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.fx.Module.InventryManager;
import lk.ijse.dep.fx.Module.OrderClass;
import lk.ijse.dep.fx.Module.PlaseOrderDesingManager;
import lk.ijse.dep.fx.Module.StockClass;

import java.io.IOException;
import java.util.ArrayList;

import static lk.ijse.dep.fx.Countroller.PlaceOrder.listOder;

public class Inventry {

    @FXML
    public AnchorPane InventryFrom;
    @FXML
    private TextField ItemId;
    @FXML
    private TextField ItemName;
    @FXML
    private TextField ItemDescription;
    @FXML
    private TextField ItemAmount;
    @FXML
    private TextField ItemPrice;
    @FXML
    private TableView<StockClass> ItemTable;
    static ArrayList<StockClass> list = new ArrayList<>();
    public void initialize(){

        ItemTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("ItemID"));
        ItemTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("ItemNAME"));
        ItemTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("ItemDESCRIPTION"));
        ItemTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("ItemAMOUNT"));
        ItemTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("ItemPRICE"));
        ObservableList<StockClass> items= FXCollections.observableArrayList(list);
        ItemTable.setItems(items);

        ItemTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StockClass>() {
            @Override
            public void changed(ObservableValue<? extends StockClass> observable, StockClass oldValue, StockClass newValue) {
                ItemId.setText(newValue.getItemID());
                ItemName.setText(newValue.getItemNAME());
                ItemDescription.setText(newValue.getItemDESCRIPTION());
                ItemAmount.setText(String.valueOf(newValue.getItemAMOUNT()));
                ItemPrice.setText(String.valueOf(newValue.getItemPRICE()));
                ItemId.setEditable(false);
                ItemName.setEditable(true);
                ItemDescription.setEditable(true);
                ItemAmount.setEditable(true);
                ItemPrice.setEditable(true);
            }
        });

    }
    public void CreatNewItem(ActionEvent actionEvent) {
        ItemId.setEditable(true);
        ItemName.setEditable(true);
        ItemDescription.setEditable(true);
        ItemAmount.setEditable(true);
        ItemPrice.setEditable(true);
        ItemId.clear();
        ItemName.clear();
        ItemDescription.clear();
        ItemAmount.clear();
        ItemPrice.clear();
    }

    public void GoToTheBack(ActionEvent actionEvent) throws IOException {
        Parent dashRoot = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/view/Main.fxml"));
        Scene se = new Scene(dashRoot);
        Stage primaryStage = (Stage)InventryFrom.getScene().getWindow();
        primaryStage.setScene(se);
        //primaryStage.show();
    }

    public void SaveItem(ActionEvent actionEvent) {
        String id = ItemId.getText();
        String name = ItemName.getText();
        String description = ItemDescription.getText();
        int qty = Integer.parseInt(ItemAmount.getText());
        double price = Double.parseDouble(ItemPrice.getText());
        StockClass s = new StockClass(id,name,description,qty,price);
        if(id.trim().isEmpty()||name.trim().isEmpty()||description.trim().isEmpty()|| ItemAmount.getText().trim().isEmpty()|| ItemPrice.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Can't have blank fields", ButtonType.OK).show();
            return;
        }

        boolean result = InventryManager.AddingUpItem(id,name,description,qty,price);

        if(result){
            new Alert(Alert.AlertType.INFORMATION,"You are successfully add a Custermer in database", ButtonType.OK).showAndWait();
            list.add(s);
            ObservableList<StockClass> items= FXCollections.observableArrayList(list);
            ItemTable.setItems(items);
            ItemId.setEditable(false);
            ItemName.setEditable(false);
            ItemDescription.setEditable(false);
            ItemAmount.setEditable(false);
            ItemPrice.setEditable(false);
        }else {
            new Alert(Alert.AlertType.ERROR,"Custermer Id is already in", ButtonType.OK).show();
        }
//        int q = InventryManager.cheakItemID(id, OrderClass.OrdQTY);
//        System.out.println("oooooooo");
//        System.out.println(q);
//        s.setItemAMOUNT(q);
        int rowNo = ItemTable.getSelectionModel().getSelectedIndex();
        list.remove(rowNo);
        new Alert(Alert.AlertType.INFORMATION,"You are successfully add a Custermer in database", ButtonType.OK).showAndWait();
        list.add(s);
        ObservableList<StockClass> items= FXCollections.observableArrayList(list);
        ItemTable.setItems(items);
        ItemId.setEditable(false);
        ItemName.setEditable(false);
        ItemDescription.setEditable(false);
        ItemAmount.setEditable(false);
        ItemPrice.setEditable(false);

    }

    public void DeletedItem(ActionEvent actionEvent) {

        int rowNo = ItemTable.getSelectionModel().getSelectedIndex();
        list.remove(rowNo);
        ObservableList<StockClass> items= FXCollections.observableArrayList(list);
        ItemTable.setItems(items);

    }
}
