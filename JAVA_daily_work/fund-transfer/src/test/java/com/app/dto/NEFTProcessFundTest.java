package com.app.dto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NEFTProcessFundTest {

    @Test
    void testValidateCustomer() {
        Customer valid = new Customer("Alice", "alice@gmail.com", "123", 1000);
        Customer invalid = new Customer("Bob", "bob@gmail.com", null, 1000);

        NEFTProcessFund neft = new NEFTProcessFund();

        assertTrue(neft.validateCustomer(valid));
        assertFalse(neft.validateCustomer(invalid));
    }

    @Test
    void testProcessFundSuccess() throws Exception {
        Customer from = new Customer("Alice", "alice@gmail.com", "111", 10000);
        Customer to = new Customer("Bob", "bob@gmail.com", "222", 5000);

        NEFTProcessFund neft = new NEFTProcessFund();
        neft.processFund(from, to, 2000);

        assertEquals(8000, from.getAmountbalance());
        assertEquals(7000, to.getAmountbalance());
    }

    @Test
    void testProcessFundInsufficientBalance() {
        Customer from = new Customer("Alice", "alice@gmail.com", "111", 1000);
        Customer to = new Customer("Bob", "bob@gmail.com", "222", 5000);

        NEFTProcessFund neft = new NEFTProcessFund();

        Exception e = assertThrows(Exception.class, () -> {
            neft.processFund(from, to, 20000);
        });

        assertEquals("Insufficient Balance", e.getMessage());
    }
}
