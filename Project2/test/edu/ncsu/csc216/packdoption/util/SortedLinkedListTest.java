/**
 * 
 */
package edu.ncsu.csc216.packdoption.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests SortedLinkedList class
 * 
 * @author BrendanRowan
 *
 */
public class SortedLinkedListTest {
	/** Test list */
	SortedLinkedList<String> linkedList;
	/**
	 * Set up for testing.
	 * 
	 * @throws Exception if exception
	 */
	@Before
	public void setUp() throws Exception {
		linkedList = new SortedLinkedList<String>();
	}

	/**
	 * Test method for hashCode
	 */
	@Test
	public void testHashCode() {
		linkedList.add("one");
		linkedList.add("two");
		linkedList.add("three");
		linkedList.add("four");
		linkedList.add("five");
		
		SortedLinkedList<String> linkedList2 = new SortedLinkedList<String>();
		linkedList2.add("one");
		linkedList2.add("two");
		linkedList2.add("three");
		linkedList2.add("four");
		linkedList2.add("five");
		
		SortedLinkedList<String> linkedList3 = new SortedLinkedList<String>();
		linkedList3.add("one");
		linkedList3.add("two");
		linkedList3.add("three");
		linkedList3.add("four");
		linkedList3.add("fie");
		
		assertEquals(linkedList.hashCode(), linkedList2.hashCode());
		assertEquals(linkedList2.hashCode(), linkedList.hashCode());
		
		assertNotEquals(linkedList.hashCode(), linkedList3.hashCode());
		assertNotEquals(linkedList3.hashCode(), linkedList.hashCode());

		assertNotEquals(linkedList2.hashCode(), linkedList3.hashCode());
		assertNotEquals(linkedList3.hashCode(), linkedList2.hashCode());
	}

	/**
	 * Test method for size
	 */
	@Test
	public void testSize() {
		assertEquals(0, linkedList.size());
		assertTrue(linkedList.add("object1"));
		assertTrue(linkedList.add("object2"));
		assertEquals(2, linkedList.size());
	}

	/**
	 * Test method for isEmpty
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(linkedList.isEmpty());
		assertTrue(linkedList.add("1"));
		assertFalse(linkedList.isEmpty());
	}

	/**
	 * Test method for contains()
	 */
	@Test
	public void testContains() {
		assertFalse(linkedList.contains("not here"));
		assertTrue(linkedList.add("not here"));
		assertTrue(linkedList.contains("not here"));
	}

	/**
	 * Test method for add
	 */
	@Test
	public void testAdd() {
		try {
			linkedList.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(null, e.getMessage());
		}
		assertTrue(linkedList.add("obj"));
		
		try {
			linkedList.add("obj");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
	}

	/**
	 * Test method for get
	 */
	@Test
	public void testGet() {
		linkedList.add("1");
		linkedList.add("2");
		linkedList.add("3");
		
		try {
			linkedList.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(null, e.getMessage());
		}
		try {
			linkedList.get(3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(null, e.getMessage());
		}
		
		assertEquals("1", linkedList.get(0));
		assertEquals("3", linkedList.get(2));
		linkedList.add("0");
		assertEquals("0", linkedList.get(0));
	}

	/**
	 * Test method for remove
	 */
	@Test
	public void testRemove() {
		linkedList.add("1");
		linkedList.add("2");
		linkedList.add("3");
		
		try {
			linkedList.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(null, e.getMessage());
		}
		try {
			linkedList.remove(3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(null, e.getMessage());
		}
		
		assertEquals("1", linkedList.remove(0));
		assertEquals(2, linkedList.size());
		assertEquals("2", linkedList.remove(0));
		assertEquals(1, linkedList.size());
		assertEquals("3", linkedList.get(0));
	}

	/**
	 * Test method for indexOf
	 */
	@Test
	public void testIndexOf() {
		linkedList.add("1");
		linkedList.add("2");
		linkedList.add("3");
		linkedList.add("4");
		linkedList.add("4444");
		
		assertEquals(1, linkedList.indexOf("2"));
		assertEquals(-1, linkedList.indexOf("blank"));
		assertEquals(3, linkedList.indexOf("4"));
	}

	/**
	 * Test method for iterator
	 */
	@Test
	public void testIterator() {
		linkedList.add("1");
		linkedList.add("2");
		linkedList.add("3");
		linkedList.add("4");
		linkedList.add("5");
		
		SimpleListIterator<String> iterator = linkedList.iterator();
		int testInt = 1;
		while (iterator.hasNext()) {
			assertEquals(testInt + "", iterator.next());
			testInt++;
		}
		
//		try {
//			iterator.next();
//			fail();
//		} catch (Exception e) {
//			// invalid
//		}
		
		
	}

	/**
	 * Test method for toString
	 */
	@Test
	public void testToString() {
		linkedList.add("one");
		linkedList.add("two");
		linkedList.add("three");
		linkedList.add("four");
		linkedList.add("five");
		
		String string = linkedList.toString();
		
		String expectedString = "-five\n-four\n-one\n-three\n-two";
		
		assertEquals(expectedString, string);
		
		
	}

	/**
	 * Test method for equals
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testEqualsObject() {
		linkedList.add("one");
		linkedList.add("two");
		linkedList.add("three");
		linkedList.add("four");
		linkedList.add("five");
		
		SortedLinkedList<String> linkedList2 = new SortedLinkedList<String>();
		linkedList2.add("one");
		linkedList2.add("two");
		linkedList2.add("three");
		linkedList2.add("four");
		linkedList2.add("five");
		
		SortedLinkedList<String> linkedList3 = new SortedLinkedList<String>();
		linkedList3.add("one");
		linkedList3.add("two");
		linkedList3.add("three");
		linkedList3.add("four");
		linkedList3.add("fie");
		
		assertTrue(linkedList.equals(linkedList));
		assertTrue(linkedList2.equals(linkedList));
		assertFalse(linkedList.equals(linkedList3));
		
		SortedLinkedList test1 = new SortedLinkedList();
		SortedLinkedList test2 = new SortedLinkedList();
		test1.add("a");
		test2.add("a");
		test1.add("null");
		assertFalse(test1.equals(test2));
		assertFalse(test2.equals(test1));

		//		try {
//			
//		}
//		
//		
//		
//		test1.add("a");
//		test2.add("a");
//		assertTrue(test1.get(0).equals(test1.get(0)));
//		test2.add("a");
//		assertNull(test2.get(0));
//		assertTrue(test1.get(0).equals(test2.get(0)));
		
		
		
	}

}
