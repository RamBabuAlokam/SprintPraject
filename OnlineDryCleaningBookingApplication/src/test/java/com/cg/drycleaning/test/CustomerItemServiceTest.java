package com.cg.drycleaning.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.drycleaning.beans.CustomerItem;
import com.cg.drycleaning.service.ICustomerItemService;

@SpringBootTest
public class CustomerItemServiceTest {
	@Autowired
	private ICustomerItemService iCustomerItemService;
	
	@Test
	public void addItemTest() {
		CustomerItem customerItem1= new CustomerItem(104L, "SHIRT", "BLUE", "TOPS", 1, "COTTON", "DELICATE WASH", null);
		assertEquals(104L, iCustomerItemService.addItem(customerItem1).getItemId());
	}
	
	@Test
	public void removeItemTest() {
		CustomerItem customerItem1= new CustomerItem(104L, "SHIRT", "BLUE", "TOPS", 1, "COTTON", "DELICATE WASH", null);
		iCustomerItemService.addItem(customerItem1);
		assertEquals("SHIRT", iCustomerItemService.removeItem(104L).getName());
	}
	@Test
	public void updateItemTest() {
		CustomerItem customerItem1= new CustomerItem(104L, "SHIRT", "BLUE", "TOPS", 1, "COTTON", "DELICATE WASH", null);
		iCustomerItemService.addItem(customerItem1);
		customerItem1.setItemId(106L);
		assertEquals(106L,iCustomerItemService.updateItem(104L, customerItem1).getItemId());
	}
	@Test
	public void getItemTest() {
		CustomerItem customerItem1= new CustomerItem(104L, "SHIRT", "BLUE", "TOPS", 1, "COTTON", "DELICATE WASH", null);
		iCustomerItemService.addItem(customerItem1);
		assertEquals("BLUE",iCustomerItemService.getItem(104L).getColor());
	}
	@Test
	public void getItemsByCustomerTest() {
		CustomerItem customerItem1= new CustomerItem(104L, "SHIRT", "BLUE", "TOPS", 1, "COTTON", "DELICATE WASH", null);
		iCustomerItemService.addItem(customerItem1);
		assertEquals(107L,iCustomerItemService.getItemsByCustomer("Santu").get(0).getItemId());
	}
	
	

}