package web;

import dao.JdbiDaoFactory;
import dao.ProductDAO;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.gson.GsonModule;

public class Server extends Jooby {


	public Server() {
            ProductDAO productDao = JdbiDaoFactory.getProductDAO();


            install(new GsonModule());

            mount(new StaticAssetModule());
            mount(new ProductModule(productDao));
	}

	public static void main(String[] args) {
		System.out.println("\nStarting Server.");
		new Server()
			 .setServerOptions(new ServerOptions().setPort(8087))
			 .start();
	}

}
