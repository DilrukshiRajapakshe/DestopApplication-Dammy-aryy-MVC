package lk.ijse.dep.fx.Countroller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dep.fx.Module.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;

public class PlaceOrder {

    @FXML
    private TextField OrderID;
    @FXML
    private TextField CuID;
    @FXML
    private TextField ItemCord;
    @FXML
    private TextField AvailableQty;
    @FXML
    private TextField CuName;
    @FXML
    private TextField Descrefion;
    @FXML
    private TextField ItemPrice;
    @FXML
    private TextField OrderQty;
    @FXML
    private TextField Total;
    @FXML
    private TextField TodayDate;
    @FXML
    private Button OrderAdd;
    @FXML
    private TableView<OrderClass> RandomeOrderTable;
    @FXML
    public AnchorPane PlaseOrderDesing;

    static ArrayList<OrderClass> listOder = new ArrayList<>();
    static ArrayList<PlaseOderClass> listOder2 = new ArrayList<>();
    public void initialize(){
        LinkedList l= new LinkedList();
        int Size= l.size();
        ID i = new ID(Size);
        String Oid= i.idGenaraer();
        OrderID.setText(Oid);
        OrderID.setEditable(false);
        getDateANDTime();
        TodayDate.setEditable(false);

        RandomeOrderTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("ItemID"));
        RandomeOrderTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Descreption"));
        RandomeOrderTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Price"));
        RandomeOrderTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("OrdQTY"));
        RandomeOrderTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("tot"));
        ObservableList<OrderClass> items= FXCollections.observableArrayList(listOder);
        RandomeOrderTable.setItems(items);

        RandomeOrderTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<OrderClass>() {
            @Override
            public void changed(ObservableValue<? extends OrderClass> observable, OrderClass oldValue, OrderClass newValue) {
                OrderID.setText(newValue.getBillID());
                TodayDate.setText(newValue.getThatDay());
                CuID.setText(newValue.getCusID());
                CuName.setText(newValue.getCusName());
                ItemCord.setText(newValue.getItemID());
                Descrefion.setText(newValue.getDescreption());
                AvailableQty.setText(String.valueOf(newValue.getAvaQTY()));
                ItemPrice.setText(String.valueOf(newValue.getPrice()));
                OrderQty.setText(String.valueOf(newValue.getOrdQTY()));
            }
        });
    }

    public void AddInformationTable(ActionEvent actionEvent) {
        String bilID = OrderID.getText();
        String date = TodayDate.getText();
        int Oqty = Integer.parseInt(OrderQty.getText());
        String customerID = CuID.getText();
        String ItemID = ItemCord.getText();
        String name = CuName.getText();
        String dis = Descrefion.getText();
        int qty = Integer.parseInt(AvailableQty.getText());
        double UnitPrice = Double.parseDouble(ItemPrice.getText());
        double OneitemTotal=UnitPrice*qty;
        if(customerID.trim().isEmpty()|| ItemID.trim().isEmpty()||String.valueOf(Oqty).trim().isEmpty()){
            OrderAdd.setDisable(true);
        }else{
            OrderAdd.setDisable(false);
        }

        boolean result = PlaseOrderDesingManager.AddingUpItem(bilID,date,customerID,name,ItemID,dis,qty,UnitPrice,Oqty,OneitemTotal);
        int x=(PlaseOrderDesingManager.DicreaseAmount(ItemID,Oqty));
        for(int a=0;a< Inventry.list.size();a++) {
            if (Inventry.list.get(a) != null && ItemID.equals(Inventry.list.get(a).getItemID())) {
                int Uqty = Inventry.list.get(a).getItemAMOUNT();
                Uqty=x;
                Inventry.list.get(a).setItemAMOUNT(x);
            }
        }
        int rowNo = RandomeOrderTable.getSelectionModel().getSelectedIndex();
        boolean z =PlaseOrderDesingManager.cheakItemID(ItemID,Oqty);
        OrderClass o = new OrderClass(bilID, date, customerID, name, ItemID, dis, qty, UnitPrice, Oqty, OneitemTotal);
        if(result && Oqty<=qty ){
            new Alert(Alert.AlertType.INFORMATION,"You are successfully add a  table", ButtonType.OK).showAndWait();
            for (int a = 0; a < listOder.size(); a++) {
                if(ItemID.equals(listOder.get(a).getItemID())&& (listOder.get(a).getOrdQTY() < Oqty)) {
                    int UOqty1 = listOder.get(a).getOrdQTY();
                    System.out.println("1. " + UOqty1);
                    System.out.println("2. " + Oqty);
                    Oqty  = Oqty + UOqty1;

                    System.out.println("3. " + Oqty);
                    listOder.get(a).setOrdQTY(Oqty);
                    RandomeOrderTable.refresh();
                    double tot= 0;
                    for (int i = 0; i < listOder.size() ; i++) {

                        tot=listOder.get(i).getTot()+tot;

                    }Total.setText(String.valueOf(tot));
                    return;
                }
                if(ItemID.equals(listOder.get(a).getItemID())&& (listOder.get(a).getOrdQTY() > Oqty)) {
                    listOder.get(a).setOrdQTY(Oqty);
                    RandomeOrderTable.refresh();
                    double tot= 0;
                    for (int i = 0; i < listOder.size() ; i++) {

                        tot=listOder.get(i).getTot()+tot;

                    }Total.setText(String.valueOf(tot));
                    return;
                }
            }
            listOder.add(o);
            CuID.setEditable(false);
            CuName.setEditable(false);
            OrderQty.clear();
            ItemCord.clear();
            Descrefion.clear();
            AvailableQty.clear();
            ItemPrice.clear();
            ObservableList<OrderClass> items = FXCollections.observableArrayList(listOder);
            RandomeOrderTable.setItems(items);

        }
        else if(Oqty>qty){
            new Alert(Alert.AlertType.ERROR,"Qty is out off area", ButtonType.OK).show();
        }double tot= 0;
        for (int i = 0; i < listOder.size() ; i++) {

            tot=listOder.get(i).getTot()+tot;

        }Total.setText(String.valueOf(tot));

    }

    public void RemovrTheInformationTable(ActionEvent actionEvent) {
        int rowNo = RandomeOrderTable.getSelectionModel().getSelectedIndex();
        listOder.remove(rowNo);
        ObservableList<OrderClass> items= FXCollections.observableArrayList(listOder);
        RandomeOrderTable.setItems(items);
        double tot= 0;
        for (int i = 0; i < listOder.size() ; i++) {

            tot=listOder.get(i).getTot()+tot;

        }
    }

    public void AddTableData(ActionEvent actionEvent)
    {
        PlaseOderClass p = new PlaseOderClass(listOder);
        listOder2.add(p);
        LinkedList l= new LinkedList();
        int Size= l.size();
        ID i = new ID(Size);
        String Oid= i.idGenaraer();
        OrderID.setText(Oid);
        OrderID.setEditable(false);
        getDateANDTime();
        TodayDate.setEditable(false);
        CuID.clear();
        CuName.clear();
        OrderQty.clear();
        ItemCord.clear();
        Descrefion.clear();
        AvailableQty.clear();
        ItemPrice.clear();

    }

    private void getDateANDTime(){
        Timeline systemDateTime = new Timeline(new KeyFrame(Duration.ZERO,e->{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
            TodayDate.setText(LocalDateTime.now().format(formatter));
        }),new KeyFrame(Duration.seconds(1)));
        systemDateTime.setCycleCount(Animation.INDEFINITE);
        systemDateTime.play();
    }

    public void GetCustermerName(KeyEvent keyEvent) {
        CuID.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    String customerID = CuID.getText();
                    for(int a=0;a< Customer.list.size();a++){
                        if(Customer.list.get(a) != null && customerID.equals(Customer.list.get(a).getCuId())){
                            String name=Customer.list.get(a).getCuname();
                            CuName.setText(name);
                        }
                    }
                }
            }

        });
        CuName.setEditable(false);
    }

    public void getTheItemDescreftion(KeyEvent keyEvent) {

        ItemCord.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    String ItemID = ItemCord.getText();
                    for(int a=0;a< Inventry.list.size();a++){
                        if(Inventry.list.get(a) != null && ItemID.equals(Inventry.list.get(a).getItemID())){
                            String name=Inventry.list.get(a).getItemDESCRIPTION();
                            Descrefion.setText(name);
                            int qty = Inventry.list.get(a).getItemAMOUNT();
                            AvailableQty.setText(String.valueOf(qty));
                            double UnitPrice =Inventry.list.get(a).getItemPRICE();
                            ItemPrice.setText(String.valueOf(UnitPrice));
                        }
                    }
                }

            }
        });
    }

    public void EnableAddButton(KeyEvent keyEvent) {
        String bilID = OrderID.getText();
        String date = TodayDate.getText();
        int Oqty = Integer.parseInt(OrderQty.getText());
        String customerID = CuID.getText();
        String ItemID = ItemCord.getText();
        String name = CuName.getText();
        String dis = Descrefion.getText();
        int qty = Integer.parseInt(AvailableQty.getText());
        double UnitPrice = Double.parseDouble(ItemPrice.getText());
        double OneitemTotal=UnitPrice*qty;
        boolean result = PlaseOrderDesingManager.AddingUpItem(bilID,date,customerID,name,ItemID,dis,qty,UnitPrice,Oqty,OneitemTotal);
        OrderQty.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(result){
                    OrderAdd.setDisable(false);
                }else{
                    OrderAdd.setDisable(true);
                    new Alert(Alert.AlertType.INFORMATION,"You can't add", ButtonType.OK).showAndWait();
                }
            }
        });

    }

    public void goToBack(ActionEvent actionEvent) throws IOException {
        Parent dashRoot = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/view/Main.fxml"));
        Scene se = new Scene(dashRoot);
        Stage primaryStage = (Stage)PlaseOrderDesing.getScene().getWindow();
        primaryStage.setScene(se);
        //primaryStage.show();
    }

    public void PlaceOrderView(ActionEvent actionEvent) throws IOException {
        Parent dashRoot = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/view/PlaseOrderSearch.fxml"));
        Scene se = new Scene(dashRoot);
        Stage primaryStage = (Stage)PlaseOrderDesing.getScene().getWindow();
        primaryStage.setScene(se);
        //primaryStage.show();
    }
}
