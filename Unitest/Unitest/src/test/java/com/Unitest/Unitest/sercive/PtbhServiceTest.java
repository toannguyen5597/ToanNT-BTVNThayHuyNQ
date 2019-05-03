package com.Unitest.Unitest.sercive;

import org.junit.Test;

import static org.junit.Assert.*;

public class PtbhServiceTest {

    @Test
    public void testPtbhNull() {
        PtbhService ptbhService = new PtbhService();
        assertNull(ptbhService.ptbh(0,0,1));
    }

    @Test
    public void testPtbhVoNghiem() {
        PtbhService ptbhService = new PtbhService();
        assertNull(ptbhService.ptbh(1,0,1));
    }

    @Test
    public void testPtbhBac1() {
        Float exceptd[] = new Float[1];
        exceptd[0]=(float)2;
        PtbhService ptbhService = new PtbhService();
        assertEquals(exceptd ,ptbhService.ptbh(0,1,-2));
    }

    @Test
    public void testPtbhBac22Nghiem() {
        Float exceptd[] = new Float[2];
        exceptd[0]=(float)1;
        exceptd[1]=(float)2;
        PtbhService ptbhService = new PtbhService();
        assertEquals(exceptd ,ptbhService.ptbh(1,-3,2));
    }

    @Test
    public void testPtbhBac21Nghiem() {
        Float exceptd[] = new Float[1];
        exceptd[0]=(float)1;
        PtbhService ptbhService = new PtbhService();
        assertEquals(exceptd ,ptbhService.ptbh(1,-2,1));
    }
}