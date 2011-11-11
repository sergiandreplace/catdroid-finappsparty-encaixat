package org.catdroid.encaixat.j2ee.servlet;

import javax.servlet.ServletException;

import org.catdroid.encaixat.dao.SprintDAO;
import org.catdroid.encaixat.util.Log;

public class SprintFooServlet extends BaseHttpServlet{

	@Override
	public void init() throws ServletException {
		super.init();
		
		Log.i("Adding fake users...");
		SprintDAO.addCustomer("Roc Boronat", "http://es.wikipedia.org/wiki/Archivo:Bananas_white_background.jpg");
		SprintDAO.addCustomer("Jordi Bernabeu", "http://es.wikipedia.org/wiki/Archivo:Bananas_white_background.jpg");
		SprintDAO.addCustomer("Sergi Martinez", "http://es.wikipedia.org/wiki/Archivo:Bananas_white_background.jpg");
		SprintDAO.addCustomer("Jordi Varela", "http://es.wikipedia.org/wiki/Archivo:Bananas_white_background.jpg");
		
		Log.i("Adding fake shops...");
		SprintDAO.addShop("Catdroid's Store");
		SprintDAO.addShop("StarBUGs");
		SprintDAO.addShop("Banana Split");
		SprintDAO.addShop("Mc Nugget");
		
	}
}
