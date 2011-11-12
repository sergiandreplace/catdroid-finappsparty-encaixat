package org.catdroid.encaixat.j2ee.servlet;

import javax.servlet.ServletException;

import org.catdroid.encaixat.dao.SprintDAO;
import org.catdroid.encaixat.util.Log;

public class SprintFooServlet extends BaseHttpServlet{

	@Override
	public void init() throws ServletException {
		super.init();
		
		Log.i("Adding fake users...");
		SprintDAO.addCustomer("Roc Boronat", "http://mascot.crystalxp.net/png/riko-tooney---happy-face-2792.png");
		SprintDAO.addCustomer("Jordi Bernabeu", "http://mascot.crystalxp.net/png/riko-tooney---happy-face-2792.png");
		SprintDAO.addCustomer("Sergi Martinez", "http://mascot.crystalxp.net/png/riko-tooney---happy-face-2792.png");
		SprintDAO.addCustomer("Jordi Varela", "http://mascot.crystalxp.net/png/riko-tooney---happy-face-2792.png");
		SprintDAO.addCustomer("Margarita Limon", "http://mascot.crystalxp.net/png/riko-tooney---happy-face-2792.png");
		SprintDAO.addCustomer("Rosa Naranjas", "http://mascot.crystalxp.net/png/riko-tooney---happy-face-2792.png");
		
		Log.i("Adding fake shops...");
		SprintDAO.addShop("Catdroid's Store");
		SprintDAO.addShop("StarBUGs");
		SprintDAO.addShop("Banana Split");
		SprintDAO.addShop("Mc Nugget");
		SprintDAO.addShop("La Alergia de Vivir");
		
	}
}
