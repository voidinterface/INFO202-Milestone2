package web;

import dao.SaleDAO;
import domain.Sale;
import io.jooby.Jooby;
import io.jooby.StatusCode;

public class SaleModule extends Jooby {
    public SaleModule(SaleDAO dao) {
        post("/api/sales/", ctx -> {
            Sale sale = ctx.body().to(Sale.class); 

            System.out.println(sale.toString());
            dao.save(sale);


            return ctx.send(StatusCode.CREATED);
        });
    }
}
