package lk.ijse.dep.fx.Module;

import java.util.ArrayList;

public class PlaseOderClass {

    //public static PlaseOderClass listOder2;
    public static ArrayList<OrderClass> listOder = new ArrayList<>();
    double total;

    public PlaseOderClass(ArrayList<OrderClass> listOder) {
        this.listOder = listOder;
        total = 0;
    }

    public ArrayList<OrderClass> getListOder() {
        return listOder;
    }

    public void setListOder(ArrayList<OrderClass> listOder) {
        this.listOder = listOder;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
