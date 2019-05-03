package com.Unitest.Unitest.sercive;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculateServiceTest {

    @Test
    public void testSum() {
        CalculateService calculateService = new CalculateService();
        int result = calculateService.sum(1,2);
        assertEquals(3,result);
    }

    @Test
    public void testMultiple() {
        CalculateService calculateService = new CalculateService();
        int result = calculateService.multiple(1,2);
        assertEquals(2,result);
    }

    @Test
    public void testDeviceNull() {
        CalculateService calculateService = new CalculateService();
        assertNull(calculateService.device(2,0));
    }

    @Test
    public void testDevice(){
        Float expected = (float)0.5;
        CalculateService calculateService = new CalculateService();
        Float result = calculateService.device(1,2);
        assertEquals(expected,result);
    }
}