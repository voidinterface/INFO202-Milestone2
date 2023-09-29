package web;

import dao.JdbiDaoFactory;
import dao.ProductDAO;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.gson.GsonModule;

public class Server extends Jooby {


	public Server() {
            install(new GsonModule());

            mount(new StaticAssetModule());
            mount(new ProductModule(JdbiDaoFactory.getProductDAO()));
            mount(new CustomerModule(JdbiDaoFactory.getCustomerDAO()));
			mount(new SaleModule(JdbiDaoFactory.getSaleDAO()));
	}

	public static void main(String[] args) {
		System.out.println("\nStarting Server.");
		new Server()
			 .setServerOptions(new ServerOptions().setPort(8087))
			 .start();
	}

}
