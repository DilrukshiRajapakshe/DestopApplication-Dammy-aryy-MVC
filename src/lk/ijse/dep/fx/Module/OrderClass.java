package lk.ijse.dep.fx.Module;

import java.util.Date;
import java.util.LinkedList;

public class OrderClass {
    public String BillID;
    public String ThatDay;
    public String CusID;
    public String CusName;
    public String ItemID;
    public String Descreption;
    public int AvaQTY;
    public double Price;
    public int OrdQTY;
    public double tot;

    public OrderClass() {
    }

    public OrderClass(String billID, String thatDay, String cusID, String cusName, String itemID, String descreption, int avaQTY, double price, int ordQTY,double Tot) {
        BillID = billID;
        ThatDay = thatDay;
        CusID = cusID;
        CusName = cusName;
        ItemID = itemID;
        Descreption = descreption;
        AvaQTY = avaQTY;
        Price = price;
        OrdQTY = ordQTY;
        tot=Tot;
    }

    public String getCusID() {
        return CusID;
    }

    public void setCusID(String cusID) {
        CusID = cusID;
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String cusName) {
        CusName = cusName;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }

    public String getBillID() {
        ID i = new ID();
        BillID=i.idGenaraer();
        return BillID;
    }

    public void setBillID(String billID) {
        BillID = billID;
    }

    public int getAvaQTY() {
        return AvaQTY;
    }

    public void setAvaQTY(int avaQTY) {
        AvaQTY = avaQTY;
    }

    public String getDescreption() {
        return Descreption;
    }

    public void setDescreption(String descreption) {
        Descreption = descreption;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getOrdQTY() {
        return OrdQTY;
    }

    public void setOrdQTY(int ordQTY) {
        OrdQTY = ordQTY;
    }

    public double getTot() {
        tot=Price*OrdQTY;
        return tot;
    }

    public void setTot(double tot) {
        this.tot = tot;
    }

    public String getThatDay() {
        return ThatDay;
    }

    public void setThatDay(String thatDay) {
        ThatDay = thatDay;
    }
}
