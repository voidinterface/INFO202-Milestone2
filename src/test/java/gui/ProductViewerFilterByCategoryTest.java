/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package gui;

import dao.ProductDAO;
import domain.Product;
import helpers.SimpleListModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author erictgb
 */
public class ProductViewerFilterByCategoryTest {
    
    private ProductDAO dao;
    private Robot robot;
    private DialogFixture fixture;
    
    private Product product1;
    private Product product2;
//    private Product product3;
    
    @BeforeEach
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();
        
//        robot.settings().delayBetweenEvents(75);
        
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
        
//        product3 = new Product(
//                "003",
//                "Product 3",
//                "3rd Product",
//                "P1",
//                new BigDecimal("33.33"),
//                new BigDecimal("3")
//        );
        
        Collection<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
//        products.add(product3);
       
        dao = mock(ProductDAO.class);
        
        when(dao.getProducts()).thenReturn(products);
        
        Collection<Product> productsByCategory = new ArrayList<>();
        productsByCategory.add(product1);
        
        when(dao.filterByCategory(product1.getCategory()))
                .thenReturn(productsByCategory);
        
        Collection<String> categories = new ArrayList<>();
        categories.add(product1.getCategory());
        categories.add(product2.getCategory());
        
        when(dao.getCategories()).thenReturn(categories);
    }
    
    @AfterEach
    public void tearDown() {
        fixture.cleanUp();
    }

    @Test
    public void testFilterByCategory() {
        ProductViewer dialog = new ProductViewer(null, true, dao);
        
        fixture = new DialogFixture(robot, dialog);
        
        fixture.show().requireVisible();
        
        fixture.click();
        
        verify(dao).getProducts();

        SimpleListModel model = (SimpleListModel) 
                fixture.list("lstProducts").target().getModel();
        
        assertThat(model, containsInAnyOrder(product1, product2));
        
        fixture.comboBox("cmbCategories").selectItem(product1.getCategory());
        
        verify(dao).getCategories();
        
        verify(dao).filterByCategory(product1.getCategory());
        
        assertThat(model, contains(product1));
    }
    
}
