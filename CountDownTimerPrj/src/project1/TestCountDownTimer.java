package project1;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.IllformedLocaleException;

import org.junit.Assert;
import org.junit.Test;

public class TestCountDownTimer {

	//			0 Parameter Constructor Test

	//Testing for the correct default constructor initialized values
	@Test
	public void testDefaultConstructor() {
		CountDownTimer s = new CountDownTimer();
		assertEquals(0, s.getHours());
		assertEquals(0, s.getMinutes());
		assertEquals(0, s.getSeconds());
	}



	//			3 Parameter Constructor Tests

	//Testing the 3param constructor for proper initialized values
	@Test
	public void testConstructor3Parameters() {
		CountDownTimer s = new CountDownTimer(2, 3, 4);
		assertEquals(2, s.getHours());
		assertEquals(3, s.getMinutes());
		assertEquals(4, s.getSeconds());
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
		assertEquals(0, s.getHours());
		assertEquals(3, s.getMinutes());
		assertEquals(4, s.getSeconds());
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
		assertEquals(0, s.getHours());
		assertEquals(0, s.getMinutes());
		assertEquals(4, s.getSeconds());
	}

	//Testing 1param constructor for Negative Seconds
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor1ParameterNegSecond() {
		new CountDownTimer(-8);
	}

	//Testing 1param constructor for Seconds Too Big
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
		assertEquals(5, s.getHours());
		assertEquals(9, s.getMinutes());
		assertEquals(1, s.getSeconds());
	}

	//Testing copy constructor for a null CountDownTimer being passed
	@Test (expected = IllegalArgumentException.class)
	public void testCopyConstructorNull() {
		CountDownTimer n = null;
		new CountDownTimer(n);
	}



	//			String Parameter Constructor Tests, oh boy here we go!

	//Testing String param with just colons
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameterOnlyColons() {
		new CountDownTimer(":::::");
	}

	//Testing String param with just one colon
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameterOneColon() {
		new CountDownTimer(":");
	}

	//Testing String param with extra info
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameter4() {
		new CountDownTimer("00:00:00:00");
	}

	//Testing String param with an empty string (should init to 0:00:00)
	@Test
	public void testConstructorStringParameterEmpty() {
		CountDownTimer c = new CountDownTimer("");
		assertEquals(0, c.getSeconds());
		assertEquals(0, c.getMinutes());
		assertEquals(0, c.getHours());
	}

	//Testing String param with a proper string with hours (3 parts)
	@Test
	public void testConstructorStringParameter3GoodFormat() {
		CountDownTimer c = new CountDownTimer("12:30:10");
		assertEquals(10, c.getSeconds());
		assertEquals(30, c.getMinutes());
		assertEquals(12, c.getHours());
	}

	//Testing String param with a proper string with minutes (2 parts)
	@Test
	public void testConstructorStringParameter2GoodFormat() {
		CountDownTimer c = new CountDownTimer("40:20");
		assertEquals(20, c.getSeconds());
		assertEquals(40, c.getMinutes());
		assertEquals(0, c.getHours());
	}

	//Testing String param with a proper string with seconds (1 part)
	@Test
	public void testConstructorStringParameter1GoodFormat() {
		CountDownTimer c = new CountDownTimer("30");
		assertEquals(30, c.getSeconds());
		assertEquals(0, c.getMinutes());
		assertEquals(0, c.getHours());
	}

	//Testing String param with hours and minutes too large
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameter3LargeMinutes() {
		new CountDownTimer("27:78:34");
	}

	//Testing String param with hours and seconds too large
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameter3LargeSeconds() {
		new CountDownTimer("5:28:92");
	}

	//Testing String param with minutes and minutes too large
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameter2LargeMinutes() {
		new CountDownTimer("62:54");
	}

	//Testing String param with minutes and seconds too large
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameter2LargeSeconds() {
		new CountDownTimer("28:96");
	}

	//Testing String param with seconds and seconds too large
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameter1LargeSeconds() {
		new CountDownTimer("96");
	}

	//Testing String param with minutes and bad number of minutes digits
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameter3BadMinutesDigits() {
		new CountDownTimer("20:8:30");
	}

	//Testing String param with hours and bad number of seconds digits
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameter3BadSecondsDigits() {
		new CountDownTimer("27:38:3");
	}

	//Testing String param with hours and bad number of minutes digits
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameter2BadMinutesDigits() {
		new CountDownTimer("2:00");
	}

	//Testing String param with minutes and bad number of seconds digits
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameter2BadSecondsDigits() {
		new CountDownTimer("32:0");
	}

	//Testing String param with seconds and bad number of seconds digits
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameter1BadSecondsDigits() {
		new CountDownTimer("0");
	}

	//Testing String param with hours and bad hours character types. THIS is the same as testing for negatives
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameter3BadHoursCharType() {
		new CountDownTimer("-1:02:00");
	}

	//Testing String param with hours and bad minutes character types. THIS is the same as testing for negatives
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameter3BadMinutesCharType() {
		new CountDownTimer("2:;2:00");
	}

	//Testing String param with hours and bad seconds character types. THIS is the same as testing for negatives
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameter3BadSecondsCharType() {
		new CountDownTimer("4:32:.0");
	}

	//Testing String param with minutes and bad minutes character types. THIS is the same as testing for negatives
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameter2BadMinutesCharType() {
		new CountDownTimer("4+:00");
	}

	//Testing String param with minutes and bad seconds character types. THIS is the same as testing for negatives
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameter2BadSecondsCharType() {
		new CountDownTimer("32:4-");
	}

	//Testing String param with seconds and bad seconds character types. THIS is the same as testing for negatives
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameter1BadSecondsCharType() {
		new CountDownTimer("/0");
	}

	//Testing String param with hours and bad minutes digits and char type
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameter3BadMinsCharAndDigits() {
		new CountDownTimer("9:=:03");
	}

	//Testing String param for a null passed
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameterNull() {
		String s = null;
		new CountDownTimer(s);
	}



	//			Equals 1 Parameter Method Tests

	//Testing for correct return on two equal objects
	@Test
	public void testEquals1ParameterEqualObjects() {
		CountDownTimer t1 = new CountDownTimer(3, 35);
		CountDownTimer t2 = new CountDownTimer("03:35");
		assertEquals(t1, t2);
	}

	//Testing for a passed a non CountDownTimer object
	@Test (expected = IllegalArgumentException.class)
	public void testEqualsParameterNotCountDownTimer() {
		CountDownTimer t1 = new CountDownTimer(3, 35);
		String t2 = "This is not a CountDownTimer";
		assertEquals(t1, t2);
	}

	//Testing for a passed null object
	@Test (expected = IllegalArgumentException.class)
	public void testEqualsParameterNull() {
		CountDownTimer t1 = new CountDownTimer(3, 35);
		CountDownTimer t2 = null;
		assertEquals(t1, t2);
	}



	//			Equals 2 Parameter Method Tests

	//Testing for correct return on two equal objects
	@Test
	public void testEquals2ParameterEqualObjects() {
		CountDownTimer t1 = new CountDownTimer(56, 1);
		CountDownTimer t2 = new CountDownTimer("56:01");
		assertTrue(CountDownTimer.equals(t1, t2));
	}

	//Testing for a passed null object in only in first param
	@Test (expected = IllegalArgumentException.class)
	public void testEquals2Parameter1Null() {
		CountDownTimer t1 = null;
		CountDownTimer t2 = new CountDownTimer(40, 28);
		assertTrue(CountDownTimer.equals(t1, t2));
	}

	//Testing for a passed null object only in seconds param
	@Test (expected = IllegalArgumentException.class)
	public void testEquals2Parameter2Null() {
		CountDownTimer t1 = new CountDownTimer(32, 3);
		CountDownTimer t2 = null;
		assertTrue(CountDownTimer.equals(t1, t2));
	}

	//Testing for both passed null objects
	@Test (expected = IllegalArgumentException.class)
	public void testEquals2ParameterBothNull() {
		CountDownTimer t1 = null;
		CountDownTimer t2 = null;
		assertTrue(CountDownTimer.equals(t1, t2));
	}



	//	toString Method Tests

	//Testing for correct return string with all 2 digits (less formatting)
	@Test
	public void testToString2DigitValues() {
		CountDownTimer c = new CountDownTimer(49, 34, 26);
		assertEquals(c.toString(), "49:34:26");
	}

	//Testing for correct return string with 1 digit values (should become 2 in output)
	@Test
	public void testToString1DigitValues() {
		CountDownTimer c = new CountDownTimer(4, 3, 9);
		assertEquals(c.toString(), "4:03:09");
	}

	//Testing for correct return string with large hours (no error)
	@Test
	public void testToStringLargeHours() {
		CountDownTimer c = new CountDownTimer(920, 45, 29);
		assertEquals(c.toString(), "920:45:29");
	}



	//	1 Parameter CompareTo Method Tests

	//Testing for a passed null in compareTo
	@Test (expected = IllegalArgumentException.class)
	public void testCompareToParameter1Null() {
		CountDownTimer c = new CountDownTimer(1, 23, 45);
		CountDownTimer n = null;
		c.compareTo(n);
	}

	//Testing for a passed timer with less time
	@Test
	public void testCompareToParameter1LessThan() {
		CountDownTimer c = new CountDownTimer(2, 22, 0);
		CountDownTimer c1 = new CountDownTimer(2, 21, 0);
		assertEquals(1, c.compareTo(c1));
	}

	//Testing for passed timer with more time
	@Test
	public void testCompareToParameter1MoreThan() {
		CountDownTimer c = new CountDownTimer(2, 22, 0);
		CountDownTimer c1 = new CountDownTimer(2, 22, 1);
		assertEquals(-1, c.compareTo(c1));
	}

	//Testing for passed timer with same amount of time
	@Test
	public void testCompareParameter1EqualTo() {
		CountDownTimer c = new CountDownTimer(8, 56, 2);
		CountDownTimer c1 = new CountDownTimer(8, 56, 2);
		assertEquals(0, c.compareTo(c1));
	}



	//	2 Parameter CompareTo Method Tests

	//Testing for a passed null in compareTo in 1st param only
	@Test (expected = IllegalArgumentException.class)
	public void testCompareToParameter2Null1() {
		CountDownTimer c = new CountDownTimer(4, 23, 52);
		CountDownTimer n = null;
		CountDownTimer.compareTo(n, c);
	}

	//Testing for a passed null in compareTo in 2nd param only
	@Test (expected = IllegalArgumentException.class)
	public void testCompareToParameter2Null2() {
		CountDownTimer c = new CountDownTimer(12, 51, 25);
		CountDownTimer n = null;
		CountDownTimer.compareTo(c, n);
	}

	//Testing for a passed timer with less time
	@Test
	public void testCompareToParameter2LessThan() {
		CountDownTimer c = new CountDownTimer(2, 22, 0);
		CountDownTimer c1 = new CountDownTimer(2, 21, 0);
		assertEquals(1, c.compareTo(c1));
	}

	//Testing for passed timer with more time
	@Test
	public void testCompareToParameter2MoreThan() {
		CountDownTimer c = new CountDownTimer(2, 22, 0);
		CountDownTimer c1 = new CountDownTimer(2, 22, 1);
		assertEquals(-1, c.compareTo(c1));
	}

	//Testing for passed timer with same amount of time
	@Test
	public void testCompareParameter2EqualTo() {
		CountDownTimer c = new CountDownTimer(8, 56, 2);
		CountDownTimer c1 = new CountDownTimer(8, 56, 2);
		assertEquals(0, c.compareTo(c1));
	}



	//



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