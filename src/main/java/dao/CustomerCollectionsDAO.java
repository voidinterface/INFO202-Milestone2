/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Customer;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author erictgb
 */
public class CustomerCollectionsDAO implements CustomerDAO {
    private static final Map<String, Customer> customers = new HashMap<>();

    @Override
    public void saveCustomer(Customer customer) {
        customers.put(customer.getUsername(), customer);
    }

    @Override
    public boolean authenticate(String username, String password) {
        Customer customer = getCustomerByUsername(username);
        if (customer == null) {
            return false;
        }
        return customer.getPassword().equals(password);
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return customers.get(username);
    }

    @Override
    public void removeCustomer(Customer customer) {
        customers.remove(customer.getUsername());
    }
    
}
