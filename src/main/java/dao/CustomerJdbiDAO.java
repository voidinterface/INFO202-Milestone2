/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Customer;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author erictgb
 */
public interface CustomerJdbiDAO extends CustomerDAO {

    @Override
    @SqlUpdate("delete from Customers where Username = :username")
    public void removeCustomer(@BindBean Customer customer);

    @Override
    @SqlQuery("select * from Customers where Username = :username")
    @RegisterBeanMapper(Customer.class)
    public Customer getCustomerByUsername(@Bind("username") String username);

    @Override
    @SqlQuery("select exists (select * from Customers where Username = :username "
            + "and Password = :password)")
    public boolean authenticate(@Bind("username") String username, 
            @Bind("password") String password);

    @Override
    @SqlUpdate("insert into Customers(Username, "
            + "First_Name, Surname, Password, Email_Address, Shipping_Address) "
            + "values (:username, :firstName, :surname, :password, "
            + ":emailAddress, :shippingAddress)")
    public void saveCustomer(@BindBean Customer customer);
    
}
