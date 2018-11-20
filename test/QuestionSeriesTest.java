/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class QuestionSeriesTest {
    
    public QuestionSeriesTest() {
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
     * Test of name method, of class QuestionSeries.
     */
    @Test
    public void testName() {
        System.out.println("name");
        String nmae = "";
        int id = 0;
        QuestionSeries instance = new QuestionSeries();
        instance.name(nmae, id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of jdbc method, of class QuestionSeries.
     */
    @Test
    public void testJdbc() {
        System.out.println("jdbc");
        int Choice = 0;
        QuestionSeries instance = new QuestionSeries();
        instance.jdbc(Choice);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
