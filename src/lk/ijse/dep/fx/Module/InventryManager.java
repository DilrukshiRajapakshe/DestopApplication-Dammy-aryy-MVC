package lk.ijse.dep.fx.Module;

import lk.ijse.dep.fx.Data.LinkList;

public class InventryManager {

    private static LinkList<StockClass> InventryItemList = new LinkList<>();
    private static LinkList<OrderClass> OderItemList = new LinkList<>();

    public static boolean AddingUpItem(String Id, String Name, String description, int qty, double price){

        for (int i = 0; i < InventryItemList.getSize() ; i++) {
            StockClass st = InventryItemList.get(i);
            if (st.getItemID().equals(Id) ){
                return false;
            }
        }
        StockClass stock = new StockClass(Id,Name,description,qty,price);
        InventryItemList.add(stock);
        return true;
    }
}
