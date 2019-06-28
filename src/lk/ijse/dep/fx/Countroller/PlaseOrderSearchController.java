package lk.ijse.dep.fx.Countroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dep.fx.Module.OrderClass;
import lk.ijse.dep.fx.Module.PlaseOderClass;

import java.util.ArrayList;

public class PlaseOrderSearchController {

    @FXML
    private TableView<ArrayList<OrderClass>> OderTableView;
    @FXML
    public AnchorPane PlaseOderSearch;

    public void initialize(){
        OderTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("BillID"));
        OderTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("ThatDay"));
        OderTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("CusID"));
        OderTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("CusName"));
//        ObservableList<PlaseOderClass> items= FXCollections.observableArrayList(PlaseOderClass.ListOder);
//        OderTableView.setItems(items);
    }
    public void SearchOrder(ActionEvent actionEvent) {

    }
}
