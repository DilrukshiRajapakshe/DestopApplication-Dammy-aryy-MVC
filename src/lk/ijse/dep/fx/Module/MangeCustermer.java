package lk.ijse.dep.fx.Module;

import lk.ijse.dep.fx.Data.LinkList;

public class MangeCustermer {
    private static LinkList<CustermerClass> CustomerList = new LinkList<>();

    public static boolean AddingUpCustomer(String CuId, String CuName, String CuAddress){

        for (int i = 0; i < CustomerList.getSize() ; i++) {
            CustermerClass cu = CustomerList.get(i);
            if (cu.getCuId().equals(CuId) ){
                return false;
            }
        }
        CustermerClass customer = new CustermerClass(CuId,CuName,CuAddress);
        CustomerList.add(customer);
        return true;
    }
}