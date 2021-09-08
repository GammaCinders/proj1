package project1;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Assert;
import org.junit.Test;

public class TestCountDownTimer {

	@Test
	public void testDefaultConstructor() {
		CountDownTimer s = new CountDownTimer();
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);
	}

	@Test
	public void testConstructor3Parameters() {
		CountDownTimer s = new CountDownTimer(0, 0, 0);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);

		s = new CountDownTimer(2, 3, 4);
		assertTrue(s.getHours() == 2);
		assertTrue(s.getMinutes() == 3);
		assertTrue(s.getSeconds() == 4);
	}

	//Testing for Negative Hours
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersNegHour() {
		new CountDownTimer(-2, 10, 8);
	}

	//Testing for Negative Minutes
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersNegMinute() {
		new CountDownTimer(2, -10, 8);
	}

	//Testing for Negative Seconds
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersNegSecond() {
		new CountDownTimer(2, 10, -8);
	}

//	// Testing for exceptions; testing all 3 at the same time
//	@Test
//	public void testConstructor3ParametersNegInput() {
//		try {
//			new CountDownTimer(-2, 3, 4);
//		}
//		catch (IllegalArgumentException e) {
//			assertTrue(e != null);
//		}
//
//		try {
//			new CountDownTimer(2, -3, 4);
//		}
//		catch (IllegalArgumentException e) {
//			assertTrue(e != null);
//		}
//
//		try {
//			new CountDownTimer(2, 3, -4);
//		}
//		catch (IllegalArgumentException e) {
//			assertTrue(e != null);
//		}
//	}

	//Testing for Minutes Too Large
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3LargeMinute() {
		new CountDownTimer(12, 60, 14);
	}

	//Testing for Seconds Too Big
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3LargeSecond() {
		new CountDownTimer(12, 59, 60);
	}

//	// Testing for an exception; no lines of code after
//	// "new CountDownTimer("a");" will be run
//	@Test (expected = IllegalArgumentException.class)
//	public void testConstructorString1ParameterAlpha() {
//		new CountDownTimer("a");
//	}
//
//	@Test (expected = IllegalArgumentException.class)
//	public void testConstructorStringLarge() {
//		new CountDownTimer("1:23:45:678");
//	}

	@Test
	public void testAdd1() {
		CountDownTimer s = new CountDownTimer();

		s.add(1);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 1);
	}

	@Test
	public void testAdd30() {
		CountDownTimer s = new CountDownTimer();

		s.add(30);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 30);
	}

	@Test
	public void testAdd59() {
		CountDownTimer s = new CountDownTimer();

		s.add(59);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testAdd60() {
		CountDownTimer s = new CountDownTimer();

		// inc to 1 min
		s.add(60);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 1);
		assertEquals(s.getSeconds(), 0);
	}

	//Uses add to inc to 1:1:1, adds one hour, one min, and one sec
	//Great test, ensures all rollover on add() is working
	@Test
	public void testAddOneEach() {
		CountDownTimer s = new CountDownTimer();

		//inc 1 hour, 1 min, 1 sec
		s.add(3661);
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 1);
		assertEquals(s.getSeconds(), 1);
	}

	//Calls add() with negative arg
	@Test (expected = IllegalArgumentException.class)
	public void testAddNeg() {
		CountDownTimer s = new CountDownTimer();
		s.add(-10);
	}

//	@Test
//	public void testDec1Second() {
//		CountDownTimer s = new CountDownTimer(1, 59, 59);
//
//		// dec 1
//		s.dec();
//		assertEquals(s.getHours(), 1);
//		assertEquals(s.getMinutes(), 59);
//		assertEquals(s.getSeconds(), 58);
//	}
//
//	@Test
//	public void testEquals() {
//		CountDownTimer s1 = new CountDownTimer(5, 59, 30);
//		CountDownTimer s2 = new CountDownTimer(6, 01, 20);
//		CountDownTimer s3 = new CountDownTimer(5, 59, 30);
//		CountDownTimer s4 = new CountDownTimer(5, 59, 20);
//		CountDownTimer s5 = new CountDownTimer(5, 40, 30);
//		CountDownTimer s6 = new CountDownTimer(4, 59, 30);
//		CountDownTimer s7 = new CountDownTimer(5, 40, 20);
//		CountDownTimer s8 = new CountDownTimer(4, 59, 20);
//		CountDownTimer s9 = new CountDownTimer(4, 40, 30);
//
//		assertFalse(s1.equals(s2));
//		assertTrue(s1.equals(s3));
//		assertEquals(s3, s1);
//		assertFalse(s1.equals(s4));
//		assertFalse(s1.equals(s5));
//		assertFalse(s1.equals(s6));
//		assertFalse(s1.equals(s7));
//		assertFalse(s1.equals(s8));
//		assertFalse(s1.equals(s9));
//	}
//
//	@Test (expected = IllegalArgumentException.class)
//	public void testEqualsNull() {
//		CountDownTimer s = new CountDownTimer();
//		s.equals(null);
//	}
}