package org.catdroid.encaixat.j2ee.test;

import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;

import org.catdroid.encaixat.bean.Customer;
import org.catdroid.encaixat.bean.Invoice;
import org.catdroid.encaixat.bean.Shop;
import org.catdroid.encaixat.bean.Transaction;
import org.catdroid.encaixat.dao.SprintDAO;
import org.catdroid.encaixat.j2ee.bo.SprintBO;
import org.catdroid.encaixat.j2ee.servlet.SprintFooServlet;
import org.catdroid.encaixat.util.Log;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FullWorkflowTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Log.i("setUp");
		SprintFooServlet foo = new SprintFooServlet();
		foo.init();
	}

	@After
	public void tearDown() throws Exception {
		Log.i("tearDown");
	}

	@Test
	public void testFullWorkflow() {
		Customer roc = SprintDAO.getCustomer("0");
		Customer jordi = SprintDAO.getCustomer("1");
		Customer sergi = SprintDAO.getCustomer("2");
		
		Shop catdroid = SprintDAO.getShop("0");
		Shop starbugs = SprintDAO.getShop("1");
		
		try {
			SprintBO.sayHello(roc.getIdCustomer(), catdroid.getIdShop());
			SprintBO.sayHello(jordi.getIdCustomer(), starbugs.getIdShop());
			SprintBO.sayHello(sergi.getIdCustomer(), starbugs.getIdShop());
			
			List<Transaction> list1 = SprintBO.listActiveTransactions(catdroid.getIdShop());
			Log.i("People at " + catdroid.getName() + ":");
			for (Iterator<Transaction> iterator = list1.iterator(); iterator.hasNext();) {
				Transaction t = (Transaction) iterator.next();
				Log.i(t.getCustomer().getName());
			}
			List<Transaction> list2 = SprintBO.listActiveTransactions(starbugs.getIdShop());
			Log.i("People at " + starbugs.getName() + ":");
			for (Iterator<Transaction> iterator = list2.iterator(); iterator.hasNext();) {
				Transaction t = (Transaction) iterator.next();
				Log.i(t.getCustomer().getName());
			}
			
			Log.i(SprintBO.sendInvoice(roc.getIdCustomer(), catdroid.getIdShop(), 30d).toString());
			Log.i(SprintBO.sendInvoice(jordi.getIdCustomer(), starbugs.getIdShop(), 20d).toString());
			
			Invoice iRoc = SprintBO.getWaitingInvoice(roc.getIdCustomer(), catdroid.getIdShop());
			Invoice iJordi = SprintBO.getWaitingInvoice(jordi.getIdCustomer(), starbugs.getIdShop());
			Invoice iSergi = SprintBO.getWaitingInvoice(sergi.getIdCustomer(), starbugs.getIdShop());
			
			Log.i(SprintBO.payInvoice(roc.getIdCustomer(), catdroid.getIdShop(), iRoc.getIdInvoice()).toString());
			
			Log.i("finish!");
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}}
