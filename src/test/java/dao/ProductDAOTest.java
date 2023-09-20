/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import domain.Product;
import java.math.BigDecimal;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 *
 * @author erictgb
 */
public class ProductDAOTest {
    
    private ProductDAO products;
    
    private Product product1;
    private Product product2;
    private Product product3;
    
    @BeforeAll
    public static void initialise() {
        try {
            JdbiDaoFactory.setJdbcUri("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/schema.sql'");
        } catch (IllegalStateException ex) {
            
        }
    }
    
    @BeforeEach
    public void setUp() {
//        products = new ProductCollectionsDAO();
        products = JdbiDaoFactory.getProductDAO();
        
        product1 = new Product(
                "001",
                "Product 1",
                "1st Product",
                "P1",
                new BigDecimal("11.11"),
                new BigDecimal("1")
        );
        
        product2 = new Product(
                "002",
                "Product 2",
                "2nd Product",
                "P2",
                new BigDecimal("22.22"),
                new BigDecimal("2")
        );
        
        product3 = new Product(
                "003",
                "Product 3",
                "3rd Product",
                "P1",
                new BigDecimal("33.33"),
                new BigDecimal("3")
        );
        
        products.saveProduct(product1);
        products.saveProduct(product2);
    }
    
    @AfterEach
    public void tearDown() {
        products.removeProduct(product1);
        products.removeProduct(product2);
        products.removeProduct(product3);
        
    }

    @Test
    public void testSaveProduct() {
        // make sure that product3 does not already exist
        assertThat(products.getProducts(),
                contains(product1, product2));

        // add it
        products.saveProduct(product3);

        // check that it was added
        assertThat(
                products.getProducts(),
                contains(product1, product2, product3));
    }

    @Test
    public void testRemoveProduct() {
        // make sure that product2 does already exist
        assertThat(products.getProducts(),
                contains(product1, product2));

        // delete it
        products.removeProduct(product2);

        // check that it was deleted
        assertThat(
                products.getProducts(),
                contains(product1));
    }

    @Test
    public void testGetProducts() {
        assertThat(products.getProducts(),
                contains(product1, product2));
    }

    @Test
    public void testGetCategories() {
        assertThat(products.getCategories(),
                contains("P1", "P2"));
    }

    @Test
    public void testSearchById() {
        assertThat(products.searchById("001"),
                is(product1));
        assertThat(products.searchById("002"),
                is(product2));
        assertThat(products.searchById("222"),
                is(nullValue()));
    }

    @Test
    public void testFilterByCategory() {
        assertThat(products.filterByCategory("P1"),
                contains(product1));
        
        products.saveProduct(product3);
        
        assertThat(products.filterByCategory("P1"),
                contains(product1, product3));
        
        assertThat(products.filterByCategory("PX"),
                hasSize(0));
    }
}
