/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JTextField;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class startTest {
    
    public startTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getjTextField1 method, of class start.
     */
    @Test
    public void testGetjTextField1() {
        System.out.println("getjTextField1");
        start instance = new start();
        JTextField expResult = null;
        JTextField result = instance.getjTextField1();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setjTextField1 method, of class start.
     */
    @Test
    public void testSetjTextField1() {
        System.out.println("setjTextField1");
        JTextField jTextField1 = null;
        start instance = new start();
        instance.setjTextField1(jTextField1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class start.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        start.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
