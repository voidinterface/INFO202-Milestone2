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
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author erictgb
 */
public class ProductViewerViewTest {
    
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
    }
    
    @AfterEach
    public void tearDown() {
        fixture.cleanUp();
    }

    @Test
    public void testView() {
        ProductViewer dialog = new ProductViewer(null, true, dao);
        
        fixture = new DialogFixture(robot, dialog);
        
        fixture.show().requireVisible();
        
        fixture.click();
        
        verify(dao).getProducts();
        
        SimpleListModel model = (SimpleListModel) 
                fixture.list("lstProducts").target().getModel();
        
        assertThat(model, containsInAnyOrder(product1, product2));
        
        
    }
    
}
