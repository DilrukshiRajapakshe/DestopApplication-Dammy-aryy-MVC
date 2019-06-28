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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.arrays.ArrayIndex;
import lk.ijse.dep.fx.Data.LinkList;
import lk.ijse.dep.fx.Module.CustermerClass;
import lk.ijse.dep.fx.Module.MangeCustermer;

import java.io.IOException;
import java.util.ArrayList;

public class Customer {
    @FXML
    public AnchorPane CustomerForm;
    @FXML
    private TextField CustermerId;
    @FXML
    private TextField CustomerAddress;
    @FXML
    private TextField CustomerName;
    @FXML
    private TableView<CustermerClass> CustomerTable;

    static  ArrayList<CustermerClass> list = new ArrayList<>();

    public void initialize(){

        CustomerTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("CuId"));
        CustomerTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Cuname"));
        CustomerTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("CuAddress"));

        ObservableList<CustermerClass> items= FXCollections.observableArrayList(list);
        CustomerTable.setItems(items);

        CustomerTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustermerClass>() {
            @Override
            public void changed(ObservableValue<? extends CustermerClass> observable, CustermerClass oldValue, CustermerClass newValue) {
                CustermerId.setText(newValue.getCuId());
                CustomerName.setText(newValue.getCuname());
                CustomerAddress.setText(newValue.getCuAddress());
                CustermerId.setEditable(false);
                CustomerName.setEditable(true);
                CustomerAddress.setEditable(true);
            }
        });

    }

    public void CreatNewCustomer(ActionEvent actionEvent) {
        CustermerId.setEditable(true);
        CustomerName.setEditable(true);
        CustomerAddress.setEditable(true);
        CustermerId.clear();
        CustomerName.clear();
        CustomerAddress.clear();
    }

    public void GoToTheBack(ActionEvent actionEvent) throws IOException {
        Parent dashRoot = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/view/Main.fxml"));
        Scene se = new Scene(dashRoot);
        Stage primaryStage = (Stage)CustomerForm.getScene().getWindow();
        primaryStage.setScene(se);
        //primaryStage.show();
    }

    public void SaveCustomer(ActionEvent actionEvent) {
        String id = CustermerId.getText();
        String name = CustomerName.getText();
        String address = CustomerAddress.getText();
        CustermerClass c= new CustermerClass(id,name,address);
        if(id.trim().isEmpty()||name.trim().isEmpty()||address.trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Can't have blank fields", ButtonType.OK).show();
            return;
        }

        boolean result = MangeCustermer.AddingUpCustomer(id, name,address);
        if (result ){
            new Alert(Alert.AlertType.INFORMATION,"You are successfully add a Custermer in database", ButtonType.OK).showAndWait();
            list.add(c);
            ObservableList<CustermerClass> items= FXCollections.observableArrayList(list);
            CustomerTable.setItems(items);
            CustermerId.setEditable(false);
            CustomerName.setEditable(false);
            CustomerAddress.setEditable(false);
        }else {
            new Alert(Alert.AlertType.ERROR,"Custermer Id is already in", ButtonType.OK).show();
        }
        int rowNo = CustomerTable.getSelectionModel().getSelectedIndex();
        list.remove(rowNo);
        new Alert(Alert.AlertType.INFORMATION,"You are successfully add a Custermer in database", ButtonType.OK).showAndWait();
        list.add(c);
        ObservableList<CustermerClass> items= FXCollections.observableArrayList(list);
        CustomerTable.setItems(items);
        CustermerId.setEditable(false);
        CustomerName.setEditable(false);
        CustomerAddress.setEditable(false);
    }

    public void DeletedCustomer(ActionEvent actionEvent) {
        int rowNo = CustomerTable.getSelectionModel().getSelectedIndex();
        list.remove(rowNo);
        ObservableList<CustermerClass> items= FXCollections.observableArrayList(list);
        CustomerTable.setItems(items);
    }


}
