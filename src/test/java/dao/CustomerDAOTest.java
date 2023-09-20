/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import domain.Customer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author erictgb
 */
public class CustomerDAOTest {
    private CustomerDAO customers;
    
    private Customer customer1;
    private Customer customer2;
    private Customer customer3;
    
    @BeforeAll
    public static void initialise() {
        try {
            JdbiDaoFactory.setJdbcUri("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/schema.sql'");
        } catch (IllegalStateException ex) {

        }
    }
    
    @BeforeEach
    public void setUp() {
//        customers = new CustomerCollectionsDAO();
        customers = JdbiDaoFactory.getCustomerDAO();
        
        customer1 = new Customer(
                "customer1",
                "Customer",
                "1",
                "1 Customer Lane",
                "customer1@example.com",
                "password1"
        );
        
        customer2 = new Customer(
                "customer2",
                "Customer",
                "2",
                "2 Customer Lane",
                "customer2@example.com",
                "password2"
        );
        
        customer3 = new Customer(
                "customer3",
                "Customer",
                "3",
                "3 Customer Lane",
                "customer3@example.com",
                "password3"
        );
        
        customers.saveCustomer(customer1);
        customers.saveCustomer(customer2);
    }
    
    @AfterEach
    public void tearDown() {
        customers.removeCustomer(customer1);
        customers.removeCustomer(customer2);
        customers.removeCustomer(customer3);
    }

    @Test
    public void testSaveCustomer() {
        assertThat(customers.getCustomerByUsername(customer3.getUsername()),
                is(nullValue()));
        
        customers.saveCustomer(customer3);
        
        assertThat(customers.getCustomerByUsername(customer3.getUsername()),
                is(customer3));
    }

    @Test
    public void testAuthenticate() {
        assertTrue(customers.authenticate("customer1", "password1"));
        assertFalse(customers.authenticate("customer1", "password2"));
    }

    @Test
    public void testGetCustomerByUsername() {
        assertThat(customers.getCustomerByUsername(customer1.getUsername()),
                is(customer1));
    }

    @Test
    public void testRemoveCustomer() {
        assertThat(customers.getCustomerByUsername(customer1.getUsername()),
                is(customer1));
        
        customers.removeCustomer(customer1);
        
        assertThat(customers.getCustomerByUsername(customer1.getUsername()),
                is(nullValue()));
    }
}
