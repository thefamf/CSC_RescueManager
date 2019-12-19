/**
 * 
 */
package edu.ncsu.csc216.packdoption.util;

import static org.junit.Assert.*;


import org.junit.Test;

/**
 * Tests Date
 * 
 * @author BrendanRowan
 *
 */
public class DateTest {

	/** test date */
	Date date;

	/**
	 * Test method for Date()
	 */
	@Test
	public void testDateIntIntInt() {
		try {
			date = new Date(-1, 1, 2000);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date(0, 1, 2000);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date(13, 1, 2000);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date(1, -1, 2000);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date(1, 0, 2000);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date(1, 32, 2000);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date(1, 1, 1999);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date(1, 1, 2051);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date(2, 30, 2000);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date(2, 29, 2001);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date(2, 29, 2002);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
//		try {
//			date = new Date(2, 29, 2003);
//			fail();
//		} catch (IllegalArgumentException e) {
//			assertEquals("Invalid date", e.getMessage());
//		}
//		try {
//			date = new Date(3, 32, 2000);
//			fail();
//		} catch (IllegalArgumentException e) {
//			assertEquals("Invalid date", e.getMessage());
//		}
//		try {
//			date = new Date(5, 32, 2000);
//			fail();
//		} catch (IllegalArgumentException e) {
//			assertEquals("Invalid date", e.getMessage());
//		}
//		try {
//			date = new Date(6, 31, 2000);
//			fail();
//		} catch (IllegalArgumentException e) {
//			assertEquals("Invalid date", e.getMessage());
//		}
//		try {
//			date = new Date(7, 32, 2000);
//			fail();
//		} catch (IllegalArgumentException e) {
//			assertEquals("Invalid date", e.getMessage());
//		}
//		try {
//			date = new Date(8, 32, 2000);
//			fail();
//		} catch (IllegalArgumentException e) {
//			assertEquals("Invalid date", e.getMessage());
//		}
//		try {
//			date = new Date(9, 31, 2000);
//			fail();
//		} catch (IllegalArgumentException e) {
//			assertEquals("Invalid date", e.getMessage());
//		}
//		try {
//			date = new Date(10, 32, 2000);
//			fail();
//		} catch (IllegalArgumentException e) {
//			assertEquals("Invalid date", e.getMessage());
//		}
//		try {
//			date = new Date(1, 1, 0000);
//			fail();
//		} catch (IllegalArgumentException e) {
//			assertEquals("Invalid date", e.getMessage());
//		}

		date = new Date(1, 1, 2000);
		assertEquals(1, date.getMonth());
		assertEquals(1, date.getDay());
		assertEquals(2000, date.getYear());

		date = new Date(2, 01, 2000);
		assertEquals(2, date.getMonth());
		assertEquals(1, date.getDay());
		assertEquals(2000, date.getYear());
		
		date = new Date(1, 10, 2000);
		assertEquals(1, date.getMonth());
		assertEquals(10, date.getDay());
		assertEquals(2000, date.getYear());
		
		date = new Date(1, 31, 2000);
		date = new Date(2, 29, 2000);
		date = new Date(3, 31, 2000);
		date = new Date(4, 30, 2000);
		date = new Date(5, 31, 2000);
		date = new Date(6, 30, 2000);
		date = new Date(7, 31, 2000);
		date = new Date(8, 31, 2000);
		date = new Date(9, 30, 2000);
		date = new Date(10, 31, 2000);
		date = new Date(11, 30, 2000);
		date = new Date(12, 31, 2000);
		
		date = new Date(2, 28, 2000);
		date = new Date(2, 29, 2000);
		date = new Date(2, 29, 2004);
		date = new Date(2, 29, 2008);
		date = new Date(2, 29, 2012);
		date = new Date(2, 29, 2016);
		date = new Date(2, 29, 2020);
		date = new Date(2, 29, 2024);
		date = new Date(2, 29, 2028);
		date = new Date(2, 29, 2032);
		date = new Date(2, 29, 2036);
		date = new Date(2, 29, 2040);
		date = new Date(2, 29, 2044);
		date = new Date(2, 29, 2048);
		assertEquals(2, date.getMonth());
		assertEquals(29, date.getDay());
		assertEquals(2048, date.getYear());
		
		date = new Date(8, 31, 2050);
		assertEquals(8, date.getMonth());
		assertEquals(31, date.getDay());
		assertEquals(2050, date.getYear());

		date = new Date(9, 1, 2050);
		assertEquals(9, date.getMonth());
		assertEquals(1, date.getDay());
		assertEquals(2050, date.getYear());
		
		date = new Date(10, 28, 2050);
		assertEquals(10, date.getMonth());
		assertEquals(28, date.getDay());
		assertEquals(2050, date.getYear());
		
		date = new Date(12, 31, 2000);
		assertEquals(12, date.getMonth());
		assertEquals(31, date.getDay());
		assertEquals(2000, date.getYear());

		date = new Date(12, 31, 2050);
		assertEquals(12, date.getMonth());
		assertEquals(31, date.getDay());
		assertEquals(2050, date.getYear());
		
	}

	/**
	 * Test method for toString()
	 */
	@Test
	public void testDateString() {
		try {
			date = new Date(" /1/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date("/1/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date("/1/1/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date(" 1/1/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date("//1/1/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date("001/1/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date("0/1/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date("13/1/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date("01//2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date("01/001/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date("01/0/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date("01/32/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date("1/1/");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date("1/1/0");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date("1/1/00");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date("1/1/000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date("1/1/0000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date("1/1/02000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date("1/ 1/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date("1/1/2000/");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date("1/1/2000 ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date(" 1/1/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date(" 1/ 1/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date(" 1/1 /2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		try {
			date = new Date(" 1/1/ 2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		
		// test valid
		date = new Date("02/29/2048");
		assertEquals(2, date.getMonth());
		assertEquals(29, date.getDay());
		assertEquals(2048, date.getYear());
		date = new Date("2/29/2048");
		assertEquals(2, date.getMonth());
		assertEquals(29, date.getDay());
		assertEquals(2048, date.getYear());
	}

	/**
	 * Test method for daysTo()
	 */
	@Test
	public void testDaysTo() {
		date = new Date(1, 1, 2000);
		Date date2 = new Date(1, 2, 2000);
		assertEquals(1, date.daysTo(date2));
		date2 = new Date(12, 31, 2000);
		assertEquals(365, date.daysTo(date2));
		date2 = new Date(1, 1, 2001);
		assertEquals(366, date.daysTo(date2));
		date2 = new Date(2, 29, 2048);
		assertEquals(17591, date.daysTo(date2));
		date2 = new Date(12, 31, 2050);
		assertEquals(18627, date.daysTo(date2));
		
		date = new Date(1, 1, 2001);
		date2 = new Date(1, 1, 2000);
		assertEquals(-366, date.daysTo(date2));
	}

	/**
	 * Test method for yearsTo()
	 */
	@Test
	public void testYearsto() {
		date = new Date(1, 1, 2000);
		Date date2 = new Date(1, 2, 2000);
		assertEquals(0, date.yearsTo(date2));
		date2 = new Date(1, 1, 2001);
		assertEquals(1, date.yearsTo(date2));
		date2 = new Date(2, 29, 2048);
		assertEquals(48, date.yearsTo(date2));
		date2 = new Date(12, 31, 2050);
		assertEquals(50, date.yearsTo(date2));
	
		date = new Date(1, 1, 2001);
		date2 = new Date(12, 31, 2000);
		assertEquals(0, date.yearsTo(date2));
		date2 = new Date(1, 1, 2000);
		assertEquals(-1, date.yearsTo(date2));
	}

	/**
	 * Test method for toString()
	 */
	@Test
	public void testToString() {
		date = new Date(2, 29, 2048);
		assertEquals("2/29/2048", date.toString());
	}

	/**
	 * Test method for compareTo()
	 */
	@Test
	public void testCompareTo() {
		Date d1 = new Date(1, 1, 2000);
		Date d2 = new Date(1, 1, 2000);
		Date dYear = new Date(1, 1, 2020);
		Date dMonth = new Date(2, 1, 2000);
		Date dDay = new Date(1, 2, 2000);
		
		try {
			d1.compareTo(null);
			fail();
		} catch (NullPointerException e) {
			assertNull(e.getMessage());
		}
		
		assertTrue(d1.compareTo(d1) == 0);
		assertTrue(d1.compareTo(d2) == 0);
		assertTrue(d2.compareTo(d1) == 0);

		assertTrue(d1.compareTo(dYear) < 0);
		assertTrue(d1.compareTo(dMonth) < 0);
		assertTrue(d1.compareTo(dDay) < 0);
		assertTrue(dYear.compareTo(d1) > 0);
		assertTrue(dMonth.compareTo(d1) > 0);
		assertTrue(dDay.compareTo(d1) > 0);
	}

	/**
	 * Test method for equals()
	 */
	@Test
	public void testEqualsObject() {
		Date d1 = new Date(1, 1, 2000);
		Date d2 = new Date(1, 1, 2000);
		assertTrue(d1.equals(d2));
		assertTrue(d2.equals(d1));
	}

	/**
	 * Test method for hashCode()
	 */
	@Test
	public void testHashCode() {
		Date d1 = new Date(1, 1, 2000);
		Date d2 = new Date(1, 1, 2000);
		Date d3 = new Date(1, 1, 2020);
		
		assertEquals(d1.hashCode(), d2.hashCode());
		assertNotEquals(d1.hashCode(), d3.hashCode());
	}

}
