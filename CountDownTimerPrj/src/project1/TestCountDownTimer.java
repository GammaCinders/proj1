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

	//			1 Parameter Constructor Test

	//Testing for the correct default constructor initialized values
	@Test
	public void testDefaultConstructor() {
		CountDownTimer s = new CountDownTimer();
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);
	}



	//			3 Parameter Constructor Tests

	//Testing the 3param constructor for proper initialized values
	@Test
	public void testConstructor3Parameters() {
		CountDownTimer s = new CountDownTimer(2, 3, 4);
		assertTrue(s.getHours() == 2);
		assertTrue(s.getMinutes() == 3);
		assertTrue(s.getSeconds() == 4);
	}

	//Testing 3param constructor for Correct Exception for Negative Hours
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersNegHour() {
		new CountDownTimer(-2, 10, 8);
	}

	//Testing 3param constructor for Negative Minutes
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersNegMinute() {
		new CountDownTimer(2, -10, 8);
	}

	//Testing 3param constructor for Negative Seconds
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersNegSecond() {
		new CountDownTimer(2, 10, -8);
	}

	//Testing 3param constructor for Minutes Too Large
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersLargeMinute() {
		new CountDownTimer(12, 60, 14);
	}

	//Testing 3param constructor for Seconds Too Big
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersLargeSecond() {
		new CountDownTimer(12, 59, 60);
	}



	//			2 Parameter Constructor Tests

	//Testing the 2param constructor for proper initialized values
	@Test
	public void testConstructor2Parameters() {
		CountDownTimer s = new CountDownTimer(3, 4);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 3);
		assertTrue(s.getSeconds() == 4);
	}

	//Testing 2param constructor for Negative Minutes
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor2ParametersNegMinute() {
		new CountDownTimer(-10, 8);
	}

	//Testing 2param constructor for Negative Seconds
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor2ParametersNegSecond() {
		new CountDownTimer(10, -8);
	}

	//Testing 2param constructor for Minutes Too Large
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor2ParametersLargeMinute() {
		new CountDownTimer(60, 14);
	}

	//Testing 2param constructor for Seconds Too Big
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor2ParametersLargeSecond() {
		new CountDownTimer(59, 60);
	}



	//			1 Parameter Constructor Tests

	//Testing the 1param constructor for proper initialized values
	@Test
	public void testConstructor1Parameter() {
		CountDownTimer s = new CountDownTimer(4);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 4);
	}

	//Testing 2param constructor for Negative Seconds
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor1ParameterNegSecond() {
		new CountDownTimer(-8);
	}

	//Testing 2param constructor for Seconds Too Big
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor1ParameterLargeSecond() {
		new CountDownTimer(60);
	}



	//			Other CountDownTimer Parameter Constructor Tests

	//Testing copy constructor for proper initialized values
	@Test
	public void testCopyConstructor() {
		CountDownTimer c = new CountDownTimer(5, 9, 1);
		CountDownTimer s = new CountDownTimer(c);
		assertTrue(s.getHours() == 5);
		assertTrue(s.getMinutes() == 9);
		assertTrue(s.getSeconds() == 1);
	}

	//Testing copy constructor for a null CountDownTimer being passed
	@Test (expected = IllegalArgumentException.class)
	public void testCopyConstructorNull() {
		CountDownTimer n = null;
		new CountDownTimer(n);
	}



	//			String Parameter Constructor Tests, oh boy here we go!

	//Testing String param with an empty string (should init to 0:00:00)
	@Test
	public void testConstructorStringParameterEmpty() {
		CountDownTimer c = new CountDownTimer("");
		assertTrue(c.getSeconds() == 0);
		assertTrue(c.getMinutes() == 0);
		assertTrue(c.getHours() == 0);
	}

	//Testing String param with a proper string with hours (3 parts)
	@Test
	public void testConstructorStringParameter3GoodFormat() {
		CountDownTimer c = new CountDownTimer("12:30:10");
		assertTrue(c.getSeconds() == 10);
		assertTrue(c.getMinutes() == 30);
		assertTrue(c.getHours() == 12);
	}

	//Testing String param with a proper string with minutes (2 parts)
	@Test
	public void testConstructorStringParameter2GoodFormat() {
		CountDownTimer c = new CountDownTimer("40:20");
		assertTrue(c.getSeconds() == 20);
		assertTrue(c.getMinutes() == 40);
		assertTrue(c.getHours() == 0);
	}

	//Testing String param with a proper string with hours (1 part)
	@Test
	public void testConstructorStringParameter1GoodFormat() {
		CountDownTimer c = new CountDownTimer("30");
		assertTrue(c.getSeconds() == 30);
		assertTrue(c.getMinutes() == 0);
		assertTrue(c.getHours() == 0);
	}

	//Testing String param for a null passed
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameterNull() {
		String s = null;
		new CountDownTimer(s);
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