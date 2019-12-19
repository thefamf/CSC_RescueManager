/**
 * 
 */
package edu.ncsu.csc216.packdoption.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests NoSuchListElementException
 * 
 * @author BrendanRowan
 * @author mlee25
 *
 */
public class NoSuchListElementExceptionTest {

	/**
	 * Test method for NoSuchListElementException()
	 */
	@Test
	public void testNoSuchListElementException() {
		NoSuchListElementException e = new NoSuchListElementException();
	    assertEquals("No such element in list.", e.getMessage());
	}

	/**
	 * Test method for NoSuchListElementExceptionString()
	 */
	@Test
	public void testNoSuchListElementExceptionString() {
		NoSuchListElementException e = new NoSuchListElementException("test");
		assertEquals("test", e.getMessage());
	}

}
