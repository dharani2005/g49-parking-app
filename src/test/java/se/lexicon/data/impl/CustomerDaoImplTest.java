package se.lexicon.data.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerDaoImplTest {

    private CustomerDaoImpl testObject;

    @BeforeEach
    public void setUp() {

        testObject = new CustomerDaoImpl();
    }

    @Test
    public void testCreateCustomer() {
        Customer input=new Customer("asdfg","abcd");
        Customer result = testObject.create(input);
        int id=result.getId();
        /*assertEquals(result,input);*/
        assertEquals("asdfg",result.getName());
        assertEquals("abcd",result.getPhoneNumber());
        assertEquals(1001,result.getId());
        assertTrue(testObject.find(id).isPresent());
    }

    @Test
    public void testfindById() {
        Customer input=new Customer("asdfg","abcd");
        Customer result = testObject.create(input);
        int id=result.getId();
        assertTrue(testObject.find(id).isPresent());

    }

    @Test
    public void testNonExistentCustomer() {
        assertFalse(testObject.find(1).isPresent());
       /* boolean test=!testObject.find(1).isPresent();
        assertTrue(test);*/

    }

    @Test
    public void testRemoveCustomer() {
        Customer actualValue=new Customer("abcd","efgh");
        Customer result = testObject.create(actualValue);
        int id=result.getId();
        assertTrue(testObject.remove(id));
       /* assertFalse(testObject.remove(id));*/

    }

    @Test
    public void testRemoveNonExistentCustomer() {
        Customer actualValue=new Customer("sdfg","12345");
        Customer result = testObject.create(actualValue);
        int id=result.getId();
        boolean removed = testObject.remove(id);
        assertFalse(removed);

    }

    @Test
    public void testFindAllCustomers() {
        Customer customer=new Customer("sdfg","12345");
        Customer result=testObject.create(customer);
       List<Customer> customers= testObject.findAll();
       assertEquals(1,customers.size());

    }

    @Test
    public void testFindAllCustomersEmptyList() {
        List<Customer> customers =testObject.findAll();
        assertEquals(0,customers.size());

    }

}
