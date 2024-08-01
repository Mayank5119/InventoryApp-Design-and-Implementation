package test.InventoryManagementAppTest.InventoryDAO.Accessor.impl;

import InventoryManagementApp.InventoryDAO.Accessor.impl.ProductsDAOImpl;
import InventoryManagementApp.InventoryDAO.model.Product;
import InventoryManagementApp.InventoryDAO.model.ProductKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// future enhancement: shift all constants to a unit test constants file
public class ProductsDAOImplTest {

    private ProductsDAOImpl productsDAO;
    private Map<ProductKey, Product> mockProductDB;

    @BeforeEach
    public void setUp() {
        mockProductDB = mock(Map.class);
        productsDAO = new ProductsDAOImpl();

        try (MockedStatic<DatabaseFactory> mockedStatic = mockStatic(DatabaseFactory.class)) {
            mockedStatic.when(DatabaseFactory::getProductsDatabase).thenReturn(new DatabaseFactory() {
                @Override
                public Map<ProductKey, Product> getProductDatabase() {
                    return mockProductDB;
                }
            });
        }
    }

    @Test
    public void testAddProduct() {
        Product product = new Product("Book", "Books", 12.49, 10, 5);
        productsDAO.add(product);

        ProductKey key = new ProductKey("Book", "Books");
        verify(mockProductDB, times(1)).put(key, product);
    }

    @Test
    public void testGetProduct() {
        Product product = new Product("Music CD", "Music", 14.99, 3, 5);
        ProductKey key = new ProductKey("Music CD", "Music");
        when(mockProductDB.get(key)).thenReturn(product);

        Product result = productsDAO.get(key);
        assertNotNull(result);
        assertEquals(product, result);
    }

    @Test
    public void testUpdateProductQuantity() {
        Product product = new Product("Chocolate Bar", "Food", 0.85, 20, 5);
        ProductKey key = new ProductKey("Chocolate Bar", "Food");
        when(mockProductDB.get(key)).thenReturn(product);

        productsDAO.update("Chocolate Bar", "Food", 10);
        verify(mockProductDB, times(1)).put(key, product);
        assertEquals(10, product.getQuantity());
    }

    @Test
    public void testUpdateNonExistentProductQuantity() {
        ProductKey key = new ProductKey("Non Existent", "Books");
        when(mockProductDB.get(key)).thenReturn(null);

        productsDAO.update("Non Existent", "Books", 5);
        verify(mockProductDB, times(0)).put(key, null);
    }

    @Test
    public void testFindAllProducts() {
        Product product1 = new Product("Book", "Books", 12.49, 10, 5);
        Product product2 = new Product("Music CD", "Music", 14.99, 3, 5);
        when(mockProductDB.values()).thenReturn(List.of(product1, product2));

        List<Product> result = productsDAO.findAllProducts();
        assertEquals(2, result.size());
        assertTrue(result.contains(product1));
        assertTrue(result.contains(product2));

    }

    @Test
    public void testFindProductByName() {
        Product product = new Product("Book", "Books", 12.49, 10, 5);
        ProductKey key = new ProductKey("Book", "Books");
        when(mockProductDB.keySet()).thenReturn(Set.of(key));
        when(mockProductDB.get(key)).thenReturn(product);

        Optional<Product> result = productsDAO.findProductByName("Book");
        assertTrue(result.isPresent());
        assertEquals(product, result.get());
    }

    @Test
    public void testFindNonExistentProductByName() {
        ProductKey key = new ProductKey("Non Existent", "Books");
        when(mockProductDB.keySet()).thenReturn(Set.of(key));
        when(mockProductDB.get(key)).thenReturn(null);

        Optional<Product> result = productsDAO.findProductByName("Non Existent");
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindProductByCategory() {
        Product product1 = new Product("Chocolate Bar", "Food", 0.85, 20, 5);
        Product product2 = new Product("Apple", "Food", 0.99, 30, 10);
        when(mockProductDB.values()).thenReturn(List.of(product1, product2));

        List<Product> result = productsDAO.findProductByCategory("Food");
        assertEquals(2, result.size());
        assertTrue(result.contains(product1));
        assertTrue(result.contains(product2));
    }

    @Test
    public void testFindNonExistentProductByCategory() {
        when(mockProductDB.values()).thenReturn(new ArrayList<>());

        List<Product> result = productsDAO.findProductByCategory("Non Existent");
        assertTrue(result.isEmpty());
    }
}
