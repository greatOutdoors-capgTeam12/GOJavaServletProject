package com.capgemini.go.z;

import java.net.ConnectException;
import java.util.Calendar;
import java.util.TimeZone;

import com.capgemini.go.dao.RetailerDao;
import com.capgemini.go.dao.RetailerDaoImpl;
import com.capgemini.go.dto.RetailerInventoryDTO;
import com.capgemini.go.exception.RetailerException;

public class RetailerInventoryTest {

	public static void main (String [] args) {
		RetailerDao retailer = new RetailerDaoImpl ();
		RetailerInventoryDTO argument = new RetailerInventoryDTO();
		argument.setRetailerUserId("ret05");
		argument.setProductCategory(5);
		argument.setProductUIN("cat5uid0990");
		argument.setProductDispatchTime(Calendar.getInstance(TimeZone.getTimeZone("UTC")));
		
		RetailerInventoryDTO argument2 = new RetailerInventoryDTO();
		argument2.setProductUIN("cat5uid0990");
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
		argument2.setProductRecieveTime(c);
		
		RetailerInventoryDTO argument3 = new RetailerInventoryDTO();
		argument3.setProductUIN("cat5uid0990");
		Calendar d = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		d.set(Calendar.MONTH, d.get(Calendar.MONTH) + 2);
		argument3.setProductShelfTimeOut(d);
		
		try {
			System.out.println(retailer.insertItemInRetailerInventory(argument));
			System.out.println(retailer.updateProductReceiveTimeStamp(argument2));
			System.out.println(retailer.updateProductSaleTimeStamp(argument3));
		} catch (ConnectException | RetailerException e) {
			
		}
	}
}
