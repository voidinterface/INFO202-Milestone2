/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Product;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author erictgb
 */
public interface ProductJdbiDAO extends ProductDAO {

    @Override
    @SqlQuery("select * from Products where Product_ID = :id")
    @RegisterBeanMapper(Product.class)
    public Product searchById(@Bind("id") String id);

    @Override
    @SqlUpdate("insert into Products(Product_ID, Name, "
            + "Description, Category, List_Price, Quantity_In_Stock) "
            + "values (:productId, :name, :description, :category, :listPrice, "
            + ":quantityInStock)")
    public void saveProduct(@BindBean Product product);

    @Override
    @SqlUpdate("delete from Products where Product_ID = :productId")
    public void removeProduct(@BindBean Product product);

    @Override
    @SqlQuery("select * from Products order by Product_ID")
    @RegisterBeanMapper(Product.class)
    public Collection<Product> getProducts();

    @Override
    @SqlQuery("select distinct Category from Products order by Category")
    public Collection<String> getCategories();

    @Override
    @SqlQuery("select * from Products where Category = :category")
    @RegisterBeanMapper(Product.class)
    public Collection<Product> filterByCategory(@Bind("category") String category);
    
}
