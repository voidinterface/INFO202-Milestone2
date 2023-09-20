/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package gui;

import dao.ProductDAO;
import domain.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author erictgb
 */
public class ProductEditorTest {
    
    private ProductDAO dao;
    private Robot robot;
    private DialogFixture fixture;
    
    @BeforeEach
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();
        
//        robot.settings().delayBetweenEvents(75);
        
        Collection<String> categories = new ArrayList<>();
        categories.add("Bakeware");
        categories.add("Plants");
        
        dao = mock(ProductDAO.class);
        
        when(dao.getCategories()).thenReturn(categories);
    }
    
    @AfterEach
    public void tearDown() {
        fixture.cleanUp();
    }

    @Test
    public void testSave() {
        ProductEditor dialog = new ProductEditor(null, true, dao);
        
        fixture = new DialogFixture(robot, dialog);
        
        fixture.show().requireVisible();
        
        fixture.click();
        
        fixture.textBox("txtId").enterText("001");
        fixture.textBox("txtName").enterText("Product 1");
        fixture.textBox("txtDescription").enterText("1st Product");
        fixture.comboBox("cmbCategory").selectItem("Bakeware");
        fixture.textBox("txtPrice").enterText("11.11");
        fixture.textBox("txtQuantity").enterText("1");
        
        fixture.button("btnSave").click();
        
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(
                Product.class);
        
        verify(dao).saveProduct(argument.capture());
        
        Product savedProduct = argument.getValue();
        
        assertThat("Ensure the ID was saved", 
                savedProduct, 
                hasProperty("productId", 
                        equalTo("001")));
        
        assertThat("Ensure the Name was saved", 
                savedProduct, 
                hasProperty("name", 
                        equalTo("Product 1")));
        
        assertThat("Ensure the Description was saved", 
                savedProduct, 
                hasProperty("description", 
                        equalTo("1st Product")));
        
        assertThat("Ensure the Category was saved", 
                savedProduct, 
                hasProperty("category", 
                        equalTo("Bakeware")));
        
        assertThat("Ensure the Price was saved", 
                savedProduct, 
                hasProperty("listPrice",
                        equalTo(new BigDecimal("11.11"))));
        
        assertThat("Ensure the Quantity was saved", 
                savedProduct, 
                hasProperty("quantityInStock", 
                        equalTo(new BigDecimal("1"))));
    }
    
}
