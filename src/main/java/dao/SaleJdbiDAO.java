package dao;

import domain.Sale;
import domain.SaleItem;
import java.time.LocalDateTime;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

public interface SaleJdbiDAO extends SaleDAO {

	@SqlUpdate("insert into Sale(Customer_Username, Date, Status)"
        + " values (:customer.username, :date, :status)")
	@GetGeneratedKeys
	Integer insertSale(@BindBean Sale sale);

	@SqlUpdate("insert into Sale_Item(Product_ID, Quantity_Purchased, Sale_Price, Sale_ID)"
        + " values (:product.productId, :quantityPurchased, :salePrice, :saleId)")
	void insertSaleItem(@BindBean SaleItem item, @Bind("saleId") Integer saleId);

	@SqlUpdate("update Products set Quantity_In_Stock = Quantity_In_Stock - :quantityPurchased"
        + " where Product_ID = :product.productId")
	void updateStockLevel(@BindBean SaleItem item);
        
        @SqlUpdate("update Products set Quantity_Sold = Quantity_Sold + :quantityPurchased"
        + " where Product_ID = :product.productId")
	void updateSalesLevel(@BindBean SaleItem item);

	@Override
	@Transaction
	default void save(Sale sale) {
        // save current date
        sale.setDate(LocalDateTime.now());

        // set sale status
        sale.setStatus("NEW ORDER");

        // call the insertSale method, and get the generated sale ID.
		Integer saleId = insertSale(sale);
		sale.setSaleId(saleId);

        // loop through the sale's items.
		for (SaleItem item : sale.getItems()) {
			insertSaleItem(item, saleId);
			updateStockLevel(item);
                        updateSalesLevel(item);
		}

	}
}