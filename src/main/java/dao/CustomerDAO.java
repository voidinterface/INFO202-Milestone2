/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Customer;

/**
 *
 * @author erictgb
 */
public interface CustomerDAO {
    void saveCustomer(Customer customer);
    
    boolean authenticate(String username, String password);
    
    Customer getCustomerByUsername(String username);
    
    void removeCustomer(Customer customer);
}
