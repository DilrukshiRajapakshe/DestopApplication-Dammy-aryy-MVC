package lk.ijse.dep.fx.Module;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import lk.ijse.dep.fx.Data.LinkList;
import org.jetbrains.annotations.Contract;

public class PlaseOrderDesingManager {

    private static LinkList<OrderClass> OderItemList = new LinkList<>();

    public static boolean AddingUpItem(String bilID,String date,String customerID,String name,String ItemID,String dis,int qty,double UnitPrice,int Oqty,double total){
        if(customerID.trim().isEmpty()|| ItemID.trim().isEmpty()||String.valueOf(Oqty).trim().isEmpty()){
            return false;
        }

        OrderClass oder = new OrderClass(bilID,date,customerID,name,ItemID,dis,qty,UnitPrice,Oqty,total);
        OderItemList.add(oder);
        return true;
    }
    public static int DicreaseAmount(String ItemID, int Oqty){
        int x=0;
        for (int i = 0; i < OderItemList.getSize() ; i++) {
            OrderClass st = OderItemList.get(i);
            if (st.getItemID().equals(ItemID)){
                x = st.getAvaQTY()-Oqty;
            }
        }
        return x;
    }
    public static boolean cheakItemID(String ItemID, int Oqty){
        //boolean a=true;
        for (int i = 0; i < OderItemList.getSize() ; i++) {

            OrderClass st = OderItemList.get(i);
            if (st.getItemID().equals(ItemID)){
                return true;
            }
        }
        return false;
    }

}
