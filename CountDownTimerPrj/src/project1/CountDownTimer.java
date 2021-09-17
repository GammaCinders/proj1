package project1;

import java.util.Scanner;
import java.io.*;

/******************************************************************************************8
 * Stores and manipulates an amount of time as controlled by the user.
 * Can also be saved and loaded from files for a more permanent solution.
 * Can Initialize to any valid amount of seconds, minutes, and hours,
 * and can then update with useful methods such as inc/dec/add/sub.
 */
public class CountDownTimer {

    /** Number of hours (3600 seconds) that this CountDownTimer Has. Cannot be negotive */
    private int hours;

    /** Number of minutes (60 seconds) that this CountDownTimer Has. Cannot be negative or > 59 */
    private int minutes;

    /** Number of seconds that this CountDownTimer Has. Cannot be negative or > 59 */
    private int seconds;

    /** Determines whether mutation methods will work (mutation work when suspended = false) */
    private static boolean suspended = false;

    /************************************
     * Initializes this object to time 0:00:00
     */
    public CountDownTimer() {
        setTimeZero();
    }

    /**********************************************************************
     * Initializes this object with the hours, minutes and seconds based on params
     * @param hours Number of hours to initialize hours to
     * @param minutes Number of minutes to initialize minutes to
     * @param seconds Number of seconds to initialize seconds to
     * @throws IllegalArgumentException If hours, minutes or seconds are negative; also if minutes or seconds are greater than 59
     */
    public CountDownTimer(int hours, int minutes, int seconds) {
        if(hours < 0 || !CountDownTimer.isValid(minutes) || !CountDownTimer.isValid(seconds)) {
            throw new IllegalArgumentException();
        }
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    /*****************************************************
     * Initializes this object with minutes and seconds based on params
     * @param minutes Number of minutes to set minutes to
     * @param seconds Number of seconds to set seconds to
     * @throws IllegalArgumentException If minutes or seconds is greater than 59 or negative
     */
    public CountDownTimer(int minutes, int seconds) {
        if(!CountDownTimer.isValid(minutes) || !CountDownTimer.isValid(seconds)) {
            throw new IllegalArgumentException();
        }
        this.hours = 0;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    /**************************************************************
     * Initializes this object with seconds based on params
     * @param seconds Number of seconds to set seconds to
     * @throws IllegalArgumentException If seconds is negative or greater than 59
     */
    public CountDownTimer(int seconds) {
        if(!CountDownTimer.isValid(seconds)) {
            throw new IllegalArgumentException();
        }
        this.hours = 0;
        this.minutes = 0;
        this.seconds = seconds;
    }

    /************************************************************************************************************
     * Initializes this object with hours, minutes and seconds equal to another timer's (a copy constructor)
     * @param other CountDownTimer with desired values to copy to this one
     * @throws IllegalArgumentException If other is null
     */
    public CountDownTimer(CountDownTimer other) {
        if(other == null) {
            throw new IllegalArgumentException();
        }
        this.hours = other.getHours();
        this.minutes = other.getMinutes();
        this.seconds = other.getSeconds();
    }


    /************************************************************************************************
     * Initializes this object with time equal to the properly formatted string param
     * @param startTime Properly formatted string to be parsed for time
     *                  The correct format is: 'h:mm:ss', 'mm:ss', or 'ss', where h/m/s are any valid combo of the 10 digits.
     *                  The seconds and minutes must always be 2 digits (8 seconds would be 08).
     *                  The hours can be any amount of digits.
     * @throws IllegalArgumentException This gets thrown for a lot of things, it is very picky.
     *                                  ANY non ":" or digit character in the param string will cause a throw.
     *                                  And any non-properly formatted strings will be thrown (see param startTime for formatting)
     */
    public CountDownTimer(String startTime) {
        //Obligatory null check
        if (startTime == null) {
            throw new IllegalArgumentException();
        }

        String[] split = startTime.split(":");

        //"" is valid I guess
        if (startTime.equals("")) {
            setTimeZero();
            //More than 2 :'s is always bad, and so is 0 length (given startTime != "")
        } else if (split.length > 3 || split.length == 0) {
            throw new IllegalArgumentException();
        } else {
            //Go through each string in split
            for (String s : split) {

                //This checks to make sure that mins and secs are always 2 digits
                if (s.length() != 2) {
                    //This is the exception for hours, since it can be as many digits as it wants
                    //Hours are only present at split[0] specifically when there are 3 strings in split
                    if (!s.equals(split[0]) || split.length != 3 || s.length() == 0) {
                        throw new IllegalArgumentException();
                    }
                }

                //Check each char to be valid (in each string in split)
                for (int i = 0; i < s.length(); i++) {
                    //9 is the code for digits, anything else is therefore a bad char
                    if (Character.getType(s.charAt(i)) != 9) {
                        throw new IllegalArgumentException();
                    }
                }

            }


            //Checking for numbers too large (can't be negative due to valid char check above)
            for(int i=0; i<split.length; i++) {
                int num = Integer.parseInt(split[i]);

                if(!CountDownTimer.isValid(num)) {
                    //If num isn't hours and is invalid
                    if(split.length != 3 || i > 0) {
                        throw new IllegalArgumentException();
                    }
                }
            }

            //Im sure there is a more elegant way to do this without repeating code, but this is fine
            switch(split.length) {
                case 1:
                    this.seconds = Integer.parseInt(split[0]);
                    this.minutes = 0;
                    this.hours = 0;
                    break;
                case 2:
                    this.seconds = Integer.parseInt(split[1]);
                    this.minutes = Integer.parseInt(split[0]);
                    this.hours = 0;
                    break;
                case 3:
                    this.seconds = Integer.parseInt(split[2]);
                    this.minutes = Integer.parseInt(split[1]);
                    this.hours = Integer.parseInt(split[0]);
                    break;
            }
        }
    }

    /******************************************************************************************************
     * Determines if the value of time on this object is equal to the value of another CountDownTimer
     * @param other Object to compare to this one (Has to be a CountDownTimer)
     * @throws IllegalArgumentException If other is null or not a CountDownTimer instance
     * @return boolean true if the total time on this timer is equal to the total time of the other object; false otherwise
     */
    public boolean equals(Object other) {
        if(other == null) {
            throw new IllegalArgumentException();
        }

        if(other instanceof CountDownTimer) {
            CountDownTimer check = (CountDownTimer)other;
            return (check.timeInSeconds() == this.timeInSeconds());
        } else {
            throw new IllegalArgumentException();
        }
    }

    /*********************************************************************************************************************
     * Determines if the total time value of a CountDownTimer is equal to the time value of another CountDownTimer
     * @param t1 First CountDownTimer to compare to t2
     * @param t2 Second CountDownTimer to compare to t1
     * @throws IllegalArgumentException If t1 or t2 is null
     * @return boolean true if t1 and t2 have the same total time value; false otherwise
     */
    public static boolean equals(CountDownTimer t1, CountDownTimer t2) {
        if(t1 == null || t2 == null) {
            throw new IllegalArgumentException();
        }
        return (t1.timeInSeconds() == t2.timeInSeconds());
    }

    /****************************************************************************************
     * Returns a string representation of the time on this object (see return for format)
     * @return String formatted like: 0:00:00, where hours (left) can be any digit length, but minutes (middle) and seconds (right) will always be 2 digits
     */
    public String toString() {
        String formattedMins = Integer.toString(minutes);
        String formattedSecs = Integer.toString(seconds);
        if(this.minutes < 10) {
            formattedMins = "0" + minutes;
        } if(this.seconds < 10) {
            formattedSecs = "0" + seconds;
        }

        return "" + hours + ":" + formattedMins + ":" + formattedSecs;
    }

    /************************************************************************************
     * Compares this CountDownTimer to another, returning an int based on total time
     * @param other CountDownTimer to compare to this one
     * @throws IllegalArgumentException If other is null
     * @return int: 1 if this timer has more time than other;
     *             -1 if other timer has more time than this timer;
     *              0 if both timers have the same time
     */
    public int compareTo(CountDownTimer other) {
        if(other == null) {
            throw new IllegalArgumentException();
        } else {
            if(timeInSeconds() > other.timeInSeconds()) {
                return 1;
            } else if (other.timeInSeconds() > timeInSeconds()) {
                return -1;
            } else {
                return 0;
            }
        }

    }

    /*******************************************************************************************************
     * Compares two Timers and returns a number based on which one has a greater total timeInSeconds()
     * @param t1 Timer to be compared with t2's timeInSeconds()
     * @param t2 Timer to be compared with t1's timeInSeconds()
     * @throws IllegalArgumentException If t1 or t2 are null
     * @return int: 1 if t1 has more time than t2;
     *             -1 if t1 has less time than t2;
     *              0 if t1 and t2 have the same time
     */
    public static int compareTo(CountDownTimer t1, CountDownTimer t2) {
        if(t1 == null || t2 == null) {
            throw new IllegalArgumentException();
        } else {
            if(t1.timeInSeconds() > t2.timeInSeconds()) {
                return 1;
            } else if (t2.timeInSeconds() > t1.timeInSeconds()) {
                return -1;
            } else {
                return 0;
            }
        }

    }

    /*************************************************
     * Subtracts one second from the current time
     * @throws IllegalArgumentException If this timer is already at 0:00:00
     */
    public void dec() {
        if(!suspended) {
            if(this.timeInSeconds() < 1) {
                throw new IllegalArgumentException();
            } else {
                seconds--;

                if(seconds < 0) {
                    minutes--;
                    seconds = 59;
                }

                if(minutes < 0) {
                    hours--;
                    minutes = 59;
                }
            }
        }
    }

    /************************************************************************************
     * Subtracts an amount of seconds from the current time on this object
     * @param seconds Number of seconds to subtract from the current time
     * @throws IllegalArgumentException If seconds is negative or 0
     */
    public void sub(int seconds) {
        if(!suspended) {
            if(seconds < 1 || seconds > timeInSeconds()) {
                throw new IllegalArgumentException();
            } else {
                for(int i=0; i<seconds; i++) {
                    dec();
                }
            }
        }
    }

    /**********************************************************************************************
     * Subtracts seconds from this timer equal to the timeInSeconds() of another timer
     * @param other Timer used to subtract its time equal to its timeInSeconds() from this timer
     * @throws IllegalArgumentException If a null Timer or a Timer with more total seconds than this one is passed
     */
    public void sub(CountDownTimer other) {
        if(!suspended) {
            if(other == null || other.timeInSeconds() > this.timeInSeconds()) {
                throw new IllegalArgumentException();
            } else {
                this.sub(other.timeInSeconds());
            }
        }
    }

    /*************************************************************
     * Adds one second to the current timer value
     */
    public void inc() {
        if(!suspended) {
            seconds++;

            if(seconds > 59) {
                minutes++;
                seconds = 0;
            }

            if(minutes > 59) {
                hours++;
                minutes = 0;
            }
        }
    }

    /******************************************************************
     * Adds an amount of seconds to the current time on this object
     * @param seconds Number of seconds to add to the current time
     * @throws IllegalArgumentException If param seconds is negative or 0
     */
    public void add(int seconds) {
        if(!suspended) {
            if(seconds < 1) {
                throw new IllegalArgumentException();
            } else {
                for(int i=0; i<seconds; i++) {
                    inc();
                }
            }
        }
    }

    /**********************************************************************************************
     * Adds seconds to this timer equal to the timeInSeconds() of another timer
     * @param other Timer used to add its time equal to its timeInSeconds() to this timer
     * @throws IllegalArgumentException If a null Timer is passed
     */
    public void add(CountDownTimer other) {
        if(!suspended) {
            if(other == null) {
                throw new IllegalArgumentException();
            } else {
                this.add(other.timeInSeconds());
            }
        }
    }

    /*************************************************************************************
     * Saves a file at the specified location (fileName) with the current time of this timer,
     * can then call load() with the same fileName to retrieve the time
     * @param fileName location of file to save info into, should be some path in the program that ends with a .txt
     * @throws IllegalArgumentException If the fileName is invalid, check stack trace for exact exception
     */
    public void save(String fileName) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
        } catch (Exception e) {
            //e.printStackTrace();
            throw new IllegalArgumentException();
        }

        out.println(toString());
        out.close();
    }

    /*********************************************************************************
     * Loads a file at the specified location (fileName) and sets this timer to that time
     * @param fileName Location of file to load info from, most likely should be some fileName that you already called save() with
     * @throws IllegalArgumentException If the data in the file is invalid the string constructor will throw
     *                                  Also if file can't be found or loaded
     */
    public void load(String fileName) {
        if(!suspended) {
            try {
                Scanner fileReader = new Scanner(new File(fileName));
                CountDownTimer temp = new CountDownTimer(fileReader.next());
                copyOtherTimer(temp);
                //File could not be read/found
            } catch(Exception e){
                //e.printStackTrace();
                //That means a bad string was passed
                throw new IllegalArgumentException();
            }
        }
    }

    /*************************************************************
     * Sets this timers time to that of the 'other' timer passed
     * @param other CountDownTimer with time to write onto this timer
     * @throws IllegalArgumentException If other is null
     */
    public void copyOtherTimer(CountDownTimer other) {
        if(!suspended) {
            if(other == null) {
                throw new IllegalArgumentException();
            }

            this.seconds = other.getSeconds();
            this.minutes = other.getMinutes();
            this.hours = other.getHours();
        }
    }

    /********************************************************************
     * Sets the suspended variable to the param.
     * If true, no mutation methods will work.
     * (Constructors do still work, as well as save())
     * @param suspend value to set 'suspended' to (see desc for more info)
     */
    public static void setSuspend(boolean suspend) {
        CountDownTimer.suspended = suspend;
    }

    /***************************************************
     * Returns the value of the 'suspended' var. See setSuspend(),
     * for more info on what 'suspended' does
     * @return Value of 'suspended'
     */
    public static boolean isSuspended() {
        return CountDownTimer.suspended;
    }

    /***************************************************
     * Just sets time to 00:00:00 (Sets minutes, seconds and hours to 0)
     */
    public void setTimeZero(){
        if(!suspended) {
            setHours(0);
            setMinutes(0);
            setSeconds(0);
        }
    }

    /******************************************************
     * Returns the total time of this Timer in seconds
     * instead of 'hours:minutes:seconds', so it can be a very large number
     * @return int total time in seconds of this timer
     */
    public int timeInSeconds() {
        return (hours*3600) + (minutes*60) + (seconds);
    }

    /************************************************************
     * Used to test if some number of minutes or seconds are valid
     * @param minsOrSecs number of minutes or seconds to check if valid
     * @return boolean false if param is negative or greater than 59
     */
    public static boolean isValid(int minsOrSecs) {
        if(minsOrSecs < 0 || minsOrSecs > 59) {
            return false;
        } else {
            return true;
        }
    }

    /*************************************************
     * Gets the value of hours on this timer
     * @return int hours that this timer holds
     */
    public int getHours() {
        return hours;
    }

    /*******************************************
     * Gets value of minutes on this timer
     * @return int minutes that this timer holds (from 0 to 59)
     */
    public int getMinutes() {
        return minutes;
    }

    /**********************************
     * Gets value of seconds on this timer
     * @return int seconds that this timer holds (from 0 to 59)
     */
    public int getSeconds() {
        return seconds;
    }

    /*********************************************************
     * Sets only the number of hours to the param value, does not change minutes or seconds
     * @param hours Number of hours to set this timer's hours to
     * @throws IllegalArgumentException If hours is negative
     */
    public void setHours(int hours) {
        if(!suspended) {
            if(hours < 0) {
                throw new IllegalArgumentException();
            }
            this.hours = hours;
        }
    }

    /********************************************************************************************
     * Sets only the number of minutes to the param value, does not change seconds or hours
     * @param minutes number of minutes to set this timer's minutes to
     * @throws IllegalArgumentException If minutes is negative of greater than 59
     */
    public void setMinutes(int minutes) {
        if(!suspended) {
            if(!CountDownTimer.isValid(minutes)) {
                throw new IllegalArgumentException();
            }
            this.minutes = minutes;
        }
    }

    /************************************************************************************************
     * Sets only the number of seconds to the param value, does not change hours or minutes
     * @param seconds number of seconds to set this timer's seconds value to
     * @throws IllegalArgumentException If seconds are negative or greater than 59
     */
    public void setSeconds(int seconds) {
        if(!suspended) {
            if(!CountDownTimer.isValid(seconds)) {
                throw new IllegalArgumentException();
            }
            this.seconds = seconds;
        }
    }


}
