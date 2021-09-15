package project1;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.NoSuchFileException;
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

	//Testing String param with a bunch of spaces
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameterJustSpaces() {
		CountDownTimer c = new CountDownTimer("      ");
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
		assertTrue(t1.equals(t2));
	}

	//Testing for a passed a non CountDownTimer object
	@Test (expected = IllegalArgumentException.class)
	public void testEqualsParameterNotCountDownTimer() {
		CountDownTimer t1 = new CountDownTimer(3, 35);
		String t2 = "This is not a CountDownTimer";
		assertTrue(t1.equals(t2));
	}

	//Testing for a passed null object
	@Test (expected = IllegalArgumentException.class)
	public void testEqualsParameterNull() {
		CountDownTimer t1 = new CountDownTimer(3, 35);
		CountDownTimer t2 = null;
		t1.equals(t2);
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



	//			toString Method Tests

	//Testing for correct return string with all 2 digits (less formatting)
	@Test
	public void testToString2DigitValues() {
		CountDownTimer c = new CountDownTimer(49, 34, 26);
		assertEquals("49:34:26", c.toString());
	}

	//Testing for correct return string with 1 digit values (should become 2 in output)
	@Test
	public void testToString1DigitValues() {
		CountDownTimer c = new CountDownTimer(4, 3, 9);
		assertEquals("4:03:09", c.toString());
	}

	//Testing for correct return string with large hours (no error)
	@Test
	public void testToStringLargeHours() {
		CountDownTimer c = new CountDownTimer(920, 45, 29);
		assertEquals("920:45:29", c.toString());
	}



	//			1 Parameter CompareTo Method Tests

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
	public void testCompareToParameter1EqualTo() {
		CountDownTimer c = new CountDownTimer(8, 56, 2);
		CountDownTimer c1 = new CountDownTimer(8, 56, 2);
		assertEquals(0, c.compareTo(c1));
	}



	//			2 Parameter CompareTo Method Tests

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
		assertEquals(1, CountDownTimer.compareTo(c, c1));
	}

	//Testing for passed timer with more time
	@Test
	public void testCompareToParameter2MoreThan() {
		CountDownTimer c = new CountDownTimer(2, 22, 0);
		CountDownTimer c1 = new CountDownTimer(2, 22, 1);
		assertEquals(-1, CountDownTimer.compareTo(c, c1));
	}

	//Testing for passed timer with same amount of time
	@Test
	public void testCompareToParameter2EqualTo() {
		CountDownTimer c = new CountDownTimer(8, 56, 2);
		CountDownTimer c1 = new CountDownTimer(8, 56, 2);
		assertEquals(0, CountDownTimer.compareTo(c, c1));
	}



	//			Dec Method Tests

	//Testing for simple one second decrease
	@Test
	public void testDecSimple() {
		CountDownTimer c = new CountDownTimer(3, 43, 28);
		c.dec();
		assertEquals(27, c.getSeconds());
		assertEquals("3:43:27", c.toString());
	}

	//Testing for no error when called with one second on timer
	@Test
	public void testDecOneSecondLeft() {
		CountDownTimer c = new CountDownTimer(1);
		c.dec();
		assertEquals(0 , c.getSeconds());
		assertEquals("0:00:00", c.toString());
	}

	//Testing for minute rollover on decrease
	@Test
	public void testDecMinuteRollover() {
		CountDownTimer c = new CountDownTimer(10, 23, 0);
		c.dec();
		assertEquals("10:22:59", c.toString());
	}

	//Testing for hour rollover on decrease
	@Test
	public void testDecHourRollover() {
		CountDownTimer c = new CountDownTimer(5, 0, 0);
		c.dec();
		assertEquals( "4:59:59", c.toString());
	}

	//Testing for an illegal call when the timer is at 0
	@Test (expected = IllegalArgumentException.class)
	public void testDecAlreadyAtZero() {
		CountDownTimer c = new CountDownTimer();
		c.dec();
	}



	//			Sub Seconds Parameter Method Tests

	//Testing for simple one second subtraction
	@Test
	public void testSubSecondsOneSecond() {
		CountDownTimer c = new CountDownTimer(56, 41, 51);
		c.sub(1);
		assertEquals(50, c.getSeconds());
		assertEquals("56:41:50", c.toString());
	}

	//Testing for simple subtraction with no rollover
	@Test
	public void testSubSeconds30Seconds() {
		CountDownTimer c = new CountDownTimer(56, 41, 50);
		c.sub(30);
		assertEquals(20, c.getSeconds());
		assertEquals("56:41:20", c.toString());
	}

	//Testing for no error when subtracting time equal to timers time (should be 0 after)
	@Test
	public void testSubSecondsTimeEqualsTimerTime() {
		CountDownTimer c = new CountDownTimer(1, 20, 20);
		c.sub(c.timeInSeconds());
		assertEquals(0, c.getSeconds());
		assertEquals("0:00:00", c.toString());
	}

	//Testing for minute rollover on subtract
	@Test
	public void testSubSecondsMinuteRollover() {
		CountDownTimer c = new CountDownTimer(1, 20, 10);
		c.sub(15);
		assertEquals("1:19:55", c.toString());
	}

	//Testing for hour rollover on subtract
	@Test
	public void testSubSecondsHourRollover() {
		CountDownTimer c = new CountDownTimer(5, 1, 10);
		c.sub(75);
		assertEquals("4:59:55", c.toString());
	}

	//Testing for an illegal call when param to subtract is greater than time on timer
	@Test (expected = IllegalArgumentException.class)
	public void testSubSecondsParameterTooLarge() {
		CountDownTimer c = new CountDownTimer(0, 3, 10);
		c.sub(191);
	}

	//Testing for an illegal call when param is negative
	@Test (expected = IllegalArgumentException.class)
	public void testSubSecondsNegParam() {
		CountDownTimer c = new CountDownTimer(0, 3, 10);
		c.sub(-3);
	}



	//			Sub CountDownTimer Parameter Method Tests

	//Testing for simple one second subtraction
	@Test
	public void testSubTimerOneSecond() {
		CountDownTimer c = new CountDownTimer(40, 26, 51);
		CountDownTimer c2 = new CountDownTimer(0, 0, 1);
		c.sub(c2);
		assertEquals(50, c.getSeconds());
		assertEquals("40:26:50", c.toString());
	}

	//Testing for simple subtraction with no rollover
	@Test
	public void testSubTimer35Seconds() {
		CountDownTimer c = new CountDownTimer(40, 26, 51);
		CountDownTimer c2 = new CountDownTimer(0, 0, 35);
		c.sub(c2);
		assertEquals(16, c.getSeconds());
		assertEquals("40:26:16", c.toString());
	}

	//Testing for simple subtraction with passed null timer
	@Test (expected = IllegalArgumentException.class)
	public void testSubTimerNullParameter() {
		CountDownTimer c = new CountDownTimer(40, 26, 51);
		CountDownTimer c2 = null;
		c.sub(c2);
	}

	//Testing for no error when subtracting time equal to timers time (should be 0 after)
	@Test
	public void testSubTimerEqualsTimerTime() {
		CountDownTimer c = new CountDownTimer(1, 50, 13);
		CountDownTimer c2 = new CountDownTimer(1, 50, 13);
		c.sub(c2);
		assertEquals("0:00:00", c.toString());
	}

	//Testing for minute rollover on subtract
	@Test
	public void testSubTimerMinuteRollover() {
		CountDownTimer c = new CountDownTimer(12, 30, 5);
		CountDownTimer c2 = new CountDownTimer(0, 0, 30);
		c.sub(c2);
		assertEquals("12:29:35", c.toString());
	}

	//Testing for hour rollover on subtract
	@Test
	public void testSubTimerHourRollover() {
		CountDownTimer c = new CountDownTimer(2, 50, 20);
		CountDownTimer c2 = new CountDownTimer(0, 55, 0);
		c.sub(c2);
		assertEquals("1:55:20", c.toString());
	}

	//Testing for an illegal call when param timer is greater than time on timer
	@Test (expected = IllegalArgumentException.class)
	public void testSubTimerParameterTooLarge() {
		CountDownTimer c = new CountDownTimer(1, 50, 13);
		CountDownTimer c2 = new CountDownTimer(1, 50, 14);
		c.sub(c2);
	}

	//Testing some random good numbers so that each one is tested
	@Test
	public void testSubSecondsRandomNumbers() {
		CountDownTimer c = new CountDownTimer(4, 50, 30);
		CountDownTimer c2 = new CountDownTimer(2, 45, 20);
		c.sub(c2);
		assertEquals("2:05:10", c.toString());
	}



	//			Inc Method Tests

	//Testing for simple one second increment
	@Test
	public void testIncSimple() {
		CountDownTimer c = new CountDownTimer(3, 43, 28);
		c.inc();
		assertEquals(29, c.getSeconds());
		assertEquals("3:43:29", c.toString());
	}

	//Testing for minute rollover on increment
	@Test
	public void testAddMinuteRollover() {
		CountDownTimer c = new CountDownTimer(10, 23, 59);
		c.inc();
		assertEquals("10:24:00", c.toString());
	}

	//Testing for hour rollover on increment
	@Test
	public void testAddHourRollover() {
		CountDownTimer c = new CountDownTimer(5, 59, 59);
		c.inc();
		assertEquals( "6:00:00", c.toString());
	}



	//			Add Seconds Parameter Method Tests

	//Testing for simple one second addition
	@Test
	public void testAddSecondsOneSecond() {
		CountDownTimer c = new CountDownTimer(56, 41, 51);
		c.add(1);
		assertEquals(52, c.getSeconds());
		assertEquals("56:41:52", c.toString());
	}

	//Testing for simple addition with no rollover
	@Test
	public void testAddSeconds30Seconds() {
		CountDownTimer c = new CountDownTimer(56, 41, 10);
		c.add(30);
		assertEquals(40, c.getSeconds());
		assertEquals("56:41:40", c.toString());
	}

	//Testing for minute rollover on addition
	@Test
	public void testAddSecondsMinuteRollover() {
		CountDownTimer c = new CountDownTimer(1, 20, 50);
		c.add(15);
		assertEquals("1:21:05", c.toString());
	}

	//Testing for hour rollover on subtract
	@Test
	public void testAddSecondsHourRollover() {
		CountDownTimer c = new CountDownTimer(5, 59, 50);
		c.add(85);
		assertEquals("6:01:15", c.toString());
	}

	//Testing for an illegal call when param is negative
	@Test (expected = IllegalArgumentException.class)
	public void testAddSecondsNegParam() {
		CountDownTimer c = new CountDownTimer(0, 3, 10);
		c.add(-3);
	}



	//			Add CountDownTimer Parameter Method Tests

	//Testing for simple one second addition
	@Test
	public void testAddTimerOneSecond() {
		CountDownTimer c = new CountDownTimer(40, 26, 51);
		CountDownTimer c2 = new CountDownTimer(0, 0, 1);
		c.add(c2);
		assertEquals(52, c.getSeconds());
		assertEquals("40:26:52", c.toString());
	}

	//Testing for simple addition with no rollover
	@Test
	public void testAddTimer35Seconds() {
		CountDownTimer c = new CountDownTimer(40, 26, 10);
		CountDownTimer c2 = new CountDownTimer(0, 0, 35);
		c.add(c2);
		assertEquals(45, c.getSeconds());
		assertEquals("40:26:45", c.toString());
	}

	//Testing for addition with passed null timer
	@Test (expected = IllegalArgumentException.class)
	public void testAddTimerNullParameter() {
		CountDownTimer c = new CountDownTimer(40, 26, 51);
		CountDownTimer c2 = null;
		c.add(c2);
	}

	//Testing for minute rollover on addition
	@Test
	public void testAddTimerMinuteRollover() {
		CountDownTimer c = new CountDownTimer(12, 30, 40);
		CountDownTimer c2 = new CountDownTimer(0, 0, 30);
		c.add(c2);
		assertEquals("12:31:10", c.toString());
	}

	//Testing for hour rollover on subtract
	@Test
	public void testAddTimerHourRollover() {
		CountDownTimer c = new CountDownTimer(2, 50, 50);
		CountDownTimer c2 = new CountDownTimer(0, 9, 10);
		c.add(c2);
		assertEquals("3:00:00", c.toString());
	}

	//Testing some random good numbers with no rollover so that each one is tested
	@Test
	public void testAddSecondsRandomNumbers() {
		CountDownTimer c = new CountDownTimer(4, 20, 30);
		CountDownTimer c2 = new CountDownTimer(2, 35, 20);
		c.add(c2);
		assertEquals("6:55:50", c.toString());
	}



	//			Save Method Tests

	//Testing that save works good, have to use load to make sure it saved properly
	@Test
	public void testSaveGoodInput() {
		CountDownTimer c = new CountDownTimer(321, 43, 23);
		c.save("testTimer.txt");
		CountDownTimer c2 = new CountDownTimer();
		c2.load("testTimer.txt");
		assertEquals("321:43:23", c2.toString());
	}

	//Testing for correct error when passed string is empty
	@Test (expected = IllegalArgumentException.class)
	public void testSaveEmptyString() {
		CountDownTimer c = new CountDownTimer(321, 43, 23);
		c.save("");
	}



	//			Load Method Tests

	//Testing that load works good, have to use save to make sure it loads properly
	@Test
	public void testLoadGoodInput() {
		CountDownTimer c = new CountDownTimer(9, 3, 23);
		c.save("testTimer.txt");
		CountDownTimer c2 = new CountDownTimer();
		c2.load("testTimer.txt");
		assertEquals("9:03:23", c2.toString());
	}

	//Testing for proper error when an empty string is passed
	@Test (expected = IllegalArgumentException.class)
	public void testLoadEmptyString() {
		CountDownTimer c = new CountDownTimer(321, 43, 23);
		c.load("");
	}

	//Testing for proper error when a file is not found
	@Test (expected = IllegalArgumentException.class)
	public void testLoadNotFound() {
		CountDownTimer c = new CountDownTimer(321, 43, 23);
		c.load("thisDoesntExist.txt");
	}



	//			CopyOtherTimer Method Test

	//Testing that the values are copied correctly
	@Test
	public void	testCopy() {
		CountDownTimer c = new CountDownTimer(12, 12, 43);
		CountDownTimer c2 = new CountDownTimer();
		c2.copyOtherTimer(c);
		assertEquals(c.toString(), c2.toString());
	}

	//Testing for correct error from null
	@Test (expected = IllegalArgumentException.class)
	public void	testCopyParameterNull() {
		CountDownTimer c = new CountDownTimer(12, 12, 43);
		CountDownTimer c2 = null;
		c.copyOtherTimer(c2);
	}


	//			SetSuspend Method Tests

	//Testing for stopping decrement
	@Test
	public void testSetSuspendDec() {
		CountDownTimer c = new CountDownTimer(20);
		CountDownTimer.setSuspend(true);
		c.dec();
		assertEquals(20, c.getSeconds());
		CountDownTimer.setSuspend(false);
	}

	//Testing for stopping sub(int);
	@Test
	public void testSetSuspendSubIntParameter() {
		CountDownTimer c = new CountDownTimer(20);
		CountDownTimer.setSuspend(true);
		c.sub(30);
		assertEquals(20, c.getSeconds());
		CountDownTimer.setSuspend(false);
	}

	//Testing for stopping sub(CountDownTimer);
	@Test
	public void testSetSuspendSubCountDownTimerParameter() {
		CountDownTimer c = new CountDownTimer(20);
		CountDownTimer c2 = new CountDownTimer(10);
		CountDownTimer.setSuspend(true);
		c.sub(c2);
		assertEquals(20, c.getSeconds());
		CountDownTimer.setSuspend(false);
	}

	//Testing for stopping increment
	@Test
	public void testSetSuspendInc() {
		CountDownTimer c = new CountDownTimer(20);
		CountDownTimer.setSuspend(true);
		c.inc();
		assertEquals(20, c.getSeconds());
		CountDownTimer.setSuspend(false);
	}

	//Testing for stopping add(int);
	@Test
	public void testSetSuspendAddIntParameter() {
		CountDownTimer c = new CountDownTimer(20);
		CountDownTimer.setSuspend(true);
		c.add(30);
		assertEquals(20, c.getSeconds());
		CountDownTimer.setSuspend(false);
	}

	//Testing for stopping add(CountDownTimer);
	@Test
	public void testSetSuspendAddCountDownTimerParameter() {
		CountDownTimer c = new CountDownTimer(20);
		CountDownTimer c2 = new CountDownTimer(10);
		CountDownTimer.setSuspend(true);
		c.add(c2);
		assertEquals(20, c.getSeconds());
		CountDownTimer.setSuspend(false);
	}

	//Testing for stopping load();
	@Test
	public void testSetSuspendLoad() {
		CountDownTimer c = new CountDownTimer(30);
		c.save("testTimer.txt");
		assertEquals(30, c.getSeconds());
		c.setSeconds(10);
		CountDownTimer.setSuspend(true);
		c.load("testTimer.txt");
		assertEquals(10, c.getSeconds());
		CountDownTimer.setSuspend(false);
	}

	//Testing for stopping copyOtherTimer();
	@Test
	public void testSetSuspendCopyOtherTimer() {
		CountDownTimer c = new CountDownTimer(28, 49, 30);
		CountDownTimer c2 = new CountDownTimer(10, 10, 10);
		CountDownTimer.setSuspend(true);
		c.copyOtherTimer(c2);
		assertEquals("28:49:30", c.toString());
		CountDownTimer.setSuspend(false);
	}

	//Testing for stopping SetTimeZero
	@Test
	public void testSetSuspendSetTimeZero() {
		CountDownTimer c = new CountDownTimer(28, 49, 30);
		CountDownTimer.setSuspend(true);
		c.setTimeZero();
		assertEquals("28:49:30", c.toString());
		CountDownTimer.setSuspend(false);
	}

	//Testing for stopping setHours
	@Test
	public void testSetSuspendSetHours() {
		CountDownTimer c = new CountDownTimer(28, 49, 30);
		CountDownTimer.setSuspend(true);
		c.setHours(9);
		assertEquals("28:49:30", c.toString());
		CountDownTimer.setSuspend(false);
	}

	//Testing for stopping setMinutes
	@Test
	public void testSetSuspendSetMinutes() {
		CountDownTimer c = new CountDownTimer(28, 49, 30);
		CountDownTimer.setSuspend(true);
		c.setMinutes(9);
		assertEquals("28:49:30", c.toString());
		CountDownTimer.setSuspend(false);
	}

	//Testing for stopping setSeconds
	@Test
	public void testSetSuspendSetSeconds() {
		CountDownTimer c = new CountDownTimer(28, 49, 30);
		CountDownTimer.setSuspend(true);
		c.setSeconds(9);
		assertEquals("28:49:30", c.toString());
		CountDownTimer.setSuspend(false);
	}



	//			IsSuspended Method Tests

	//Testing for before anything is changed, should be false and should not change when a CountDownTimer is created
	@Test
	public void testIsSuspendedDefault() {
		assertTrue(!CountDownTimer.isSuspended());
		CountDownTimer c = new CountDownTimer();
		assertTrue(!CountDownTimer.isSuspended());
	}

	//Testing proper change of suspended, should be true
	@Test
	public void testIsSuspendedTrue() {
		CountDownTimer c = new CountDownTimer();
		CountDownTimer.setSuspend(true);
		assertTrue(CountDownTimer.isSuspended());
		//Reset to not mess up other tests
		CountDownTimer.setSuspend(false);
	}



	//			SetTimeZero Method Test

	//Testing for proper reset of values to 0
	@Test
	public void testSetTimeZero() {
		CountDownTimer c = new CountDownTimer(12 , 45, 21);
		c.setTimeZero();
		assertEquals(0, c.getHours());
		assertEquals(0, c.getMinutes());
		assertEquals(0, c.getSeconds());
	}



	//			TimeInSeconds Method Test

	//Testing for correct time in seconds from method
	@Test
	public void testTimeInSeconds() {
		CountDownTimer c = new CountDownTimer(2, 2, 10);
		assertEquals(7330, c.timeInSeconds());
	}



	//			IsValid Method Tests

	//Testing for good value in range
	@Test
	public void testIsValidParameterGood() {
		assertEquals(true, CountDownTimer.isValid(35));
	}

	//Testing for error from negative value
	@Test
	public void testIsValidParameterNeg() {
		assertEquals(false, CountDownTimer.isValid(-20));
	}

	//Testing for error from num too big
	@Test
	public void testIsValidParameterBig() {
		assertEquals(false, CountDownTimer.isValid(60));
	}



	//			GetHours Getter Test

	//Simple getter test for hours, there really is only one test
	@Test
	public void testHoursGetter() {
		CountDownTimer c = new CountDownTimer(3, 23, 34);
		assertEquals(3, c.getHours());
	}



	//			GetMinutes Getter Test

	//Simple getter test for minutes, there really is only one test
	@Test
	public void testMinutesGetter() {
		CountDownTimer c = new CountDownTimer(3, 23, 34);
		assertEquals(23, c.getMinutes());
	}



	//			GetSeconds Getter Test

	//Simple getter test for seconds, there really is only one test
	@Test
	public void testSecondsGetter() {
		CountDownTimer c = new CountDownTimer(3, 23, 34);
		assertEquals(34, c.getSeconds());
	}



	//			SetHours Setter Tests

	//Testing for good param properly being set
	@Test
	public void testSetHoursGoodParam() {
		CountDownTimer c = new CountDownTimer(23, 53, 12);
		c.setHours(54);
		assertEquals(54, c.getHours());
	}

	//Testing for correct throw for negative param
	@Test (expected = IllegalArgumentException.class)
	public void testSetHoursNegParam() {
		CountDownTimer c = new CountDownTimer(23, 53, 12);
		c.setHours(-35);
	}



	//			SetMinutes Setter Tests

	//Testing for good param properly being set
	@Test
	public void testSetMinutesGoodParam() {
		CountDownTimer c = new CountDownTimer(23, 53, 12);
		c.setMinutes(10);
		assertEquals(10, c.getMinutes());
	}

	//Testing for correct throw for param too big (>59)
	@Test (expected = IllegalArgumentException.class)
	public void testSetMinutesBigParam() {
		CountDownTimer c = new CountDownTimer(23, 53, 12);
		c.setMinutes(60);
	}

	//Testing for correct throw for negative param
	@Test (expected = IllegalArgumentException.class)
	public void testSetMinutesNegParam() {
		CountDownTimer c = new CountDownTimer(23, 53, 12);
		c.setMinutes(-35);
	}



	//			SetSeconds Setter Tests

	//Testing for good param properly being set
	@Test
	public void testSetSecondsGoodParam() {
		CountDownTimer c = new CountDownTimer(23, 53, 12);
		c.setSeconds(10);
		assertEquals(10, c.getSeconds());
	}

	//Testing for correct throw for param too big (>59)
	@Test (expected = IllegalArgumentException.class)
	public void testSetSecondsBigParam() {
		CountDownTimer c = new CountDownTimer(23, 53, 12);
		c.setSeconds(65);
	}

	//Testing for correct throw for negative param
	@Test (expected = IllegalArgumentException.class)
	public void testSetSecondsNegParam() {
		CountDownTimer c = new CountDownTimer(23, 53, 12);
		c.setSeconds(-1);
	}





}