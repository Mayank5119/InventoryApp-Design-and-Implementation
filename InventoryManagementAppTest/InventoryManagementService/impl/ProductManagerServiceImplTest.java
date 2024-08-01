package test.InventoryManagementAppTest.InventoryManagementService.impl;

import InventoryManagementApp.InventoryDAO.Accessor.impl.ProductsDAOImpl;
import InventoryManagementApp.InventoryDAO.model.Product;
import InventoryManagementApp.InventoryDAO.model.ProductKey;
import InventoryManagementApp.InventoryManagementService.impl.ProductManagerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

// future enhancement: shift all constants to a unit test constants file
public class ProductManagerServiceImplTest {

    private ProductsDAOImpl productsDAO;
    private ProductManagerServiceImpl productManagerService;

    @BeforeEach
    public void setUp() {
        productsDAO = mock(ProductsDAOImpl.class);
        productManagerService = new ProductManagerServiceImpl(productsDAO);
    }

    @Test
    public void testAddProduct() {
        Product product = new Product("Book", "Books", 12.49, 10, 5);
        productManagerService.addProduct(product);
        verify(productsDAO, times(1)).add(product);
    }

    @Test
    public void testAddProductBelowThreshold() {
        Product product = new Product("Music CD", "Music", 14.99, 2, 3);
        productManagerService.addProduct(product);
        verify(productsDAO, times(1)).add(product);
        // will add verify on the warning message when api input output model classes will be defined
    }

    @Test
    public void testUpdateProductQuantity() {
        Product existingProduct = new Product("Music CD", "Music", 14.99, 3, 5);
        when(productsDAO.get(new ProductKey("Music CD", "Music"))).thenReturn(existingProduct);

        productManagerService.updateProductQuantity("Music CD", "Music", 2);
        verify(productsDAO, times(1)).update("Music CD", "Music", 2);
        // will add verify on the warning message when api input output model classes will be defined
    }

    @Test
    public void testUpdateNonExistentProductQuantity() {
        when(productsDAO.get(new ProductKey("Non Existent", "Books"))).thenReturn(null);

        productManagerService.updateProductQuantity("Non Existent", "Books", 5);
        verify(productsDAO, times(0)).update("Non Existent", "Books", 5);
    }

    @Test
    public void testListProducts() {
        Product product = new Product("Book", "Books", 12.49, 10, 5);
        productManagerService.listProducts();
        when(productsDAO.findAllProducts()).thenReturn(Collections.singletonList(product));
        verify(productsDAO, times(1)).findAllProducts();
    }

    @Test
    public void testSearchProductByName() {
        Product product = new Product("Book", "Books", 12.49, 10, 5);
        when(productsDAO.findProductByName("Book")).thenReturn(Optional.of(product));

        productManagerService.searchProductByName("Book");
        verify(productsDAO, times(1)).findProductByName("Book");
    }

    @Test
    public void testSearchNonExistentProductByName() {
        when(productsDAO.findProductByName("Non Existent")).thenReturn(Optional.empty());

        productManagerService.searchProductByName("Non Existent");
        verify(productsDAO, times(1)).findProductByName("Non Existent");
    }

    @Test
    public void testSearchProductByCategory() {
        Product product = new Product("Chocolate Bar", "Food", 0.85, 20, 5);
        when(productsDAO.findProductByCategory("Food")).thenReturn(Collections.singletonList(product));

        productManagerService.searchProductByCategory("Food");
        verify(productsDAO, times(1)).findProductByCategory("Food");
    }

    @Test
    public void testSearchNonExistentProductByCategory() {
        when(productsDAO.findProductByCategory("Non Existent")).thenReturn(Collections.emptyList());

        productManagerService.searchProductByCategory("Non Existent");
        verify(productsDAO, times(1)).findProductByCategory("Non Existent");
    }
}
