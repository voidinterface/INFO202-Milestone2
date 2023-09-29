package web;

import domain.Sale;
import io.jooby.Jooby;
import io.jooby.StatusCode;

public class SaleModule extends Jooby {
    public SaleModule() {
        post("/api/sales/", ctx -> {
            Sale sale = ctx.body().to(Sale.class);

            System.out.println(sale.toString());

            return ctx.send(StatusCode.CREATED);
        });
    }
}
