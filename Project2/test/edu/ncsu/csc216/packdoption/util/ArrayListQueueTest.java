/**
 * 
 */
package edu.ncsu.csc216.packdoption.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**
 * Tests ArrayListQueue
 * 
 * @author BrendanRowan
 *
 */
public class ArrayListQueueTest {
	/** Test ArrayQueue */
	private ArrayListQueue<String> arrayListQueue;

	/**
	 * Set up for testing.
	 * 
	 * @throws Exception if exception
	 */
	@Before
	public void setUp() throws Exception {
		arrayListQueue = new ArrayListQueue<String>();
	}

	/**
	 * Test method for add()
	 */
	@Test
	public void testAdd() {
		assertEquals(0, arrayListQueue.size());
		arrayListQueue.add("a");
		assertEquals(1, arrayListQueue.size());
		arrayListQueue.add("b");
		assertEquals(2, arrayListQueue.size());
	}

	/**
	 * Test method for remove()
	 */
	@Test
	public void testRemove() {
		try {
			arrayListQueue.remove();
			fail();
		} catch (NoSuchListElementException e) {
			assertEquals("No such element in list.", e.getMessage());
		}
		arrayListQueue.add("a");
		assertEquals("a", arrayListQueue.remove());
		arrayListQueue.add("a");
		arrayListQueue.add("b");
		arrayListQueue.add("c");
		assertEquals("a", arrayListQueue.remove());
		assertEquals("b", arrayListQueue.remove());
		assertEquals("c", arrayListQueue.remove());
		assertEquals(0, arrayListQueue.size());
	}

	/**
	 * Test method for element()
	 */
	@Test
	public void testElement() {
		arrayListQueue.add("a");
		arrayListQueue.add("b");
		assertEquals(2, arrayListQueue.size());
		String s = arrayListQueue.element();
		assertEquals("a", s);
	}

	/**
	 * Test method for isEmpty()
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(arrayListQueue.isEmpty());
		arrayListQueue.add("a");
		assertFalse(arrayListQueue.isEmpty());
		arrayListQueue.remove();
		assertTrue(arrayListQueue.isEmpty());		
	}

}
