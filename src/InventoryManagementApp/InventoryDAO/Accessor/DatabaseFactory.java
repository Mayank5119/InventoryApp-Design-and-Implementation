package InventoryManagementApp.InventoryDAO.Accessor;

import InventoryManagementApp.Persistance.Products;

public interface DatabaseFactory {
    public static Products getProductsDatabase(){
        return new Products();
    }
}
