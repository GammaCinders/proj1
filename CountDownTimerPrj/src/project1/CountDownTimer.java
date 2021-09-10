package project1;

import sun.rmi.transport.proxy.RMISocketInfo;

public class CountDownTimer {

    private int hours;
    private int minutes;
    private int seconds;



    public CountDownTimer() {
        setTimeZero();
    }

    public CountDownTimer(int hours, int minutes, int seconds) {
        if(hours < 0 || minutes < 0 || seconds < 0) {
            throw new IllegalArgumentException();
        } else if(minutes > 59 || seconds > 59) {
            throw new IllegalArgumentException();
        }
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public CountDownTimer(int minutes, int seconds) {
        if(minutes < 0 || seconds < 0) {
            throw new IllegalArgumentException();
        } else if(minutes > 59 || seconds > 59) {
            throw new IllegalArgumentException();
        }
        this.hours = 0;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public CountDownTimer(int seconds) {
        if(seconds > 59) {
            throw new IllegalArgumentException();
        } else if(seconds < 0) {
            throw new IllegalArgumentException();
        }
        this.hours = 0;
        this.minutes = 0;
        this.seconds = seconds;
    }

    public CountDownTimer(CountDownTimer other) {
        if(other == null) {
            throw new IllegalArgumentException();
        }
        this.hours = other.getHours();
        this.minutes = other.getMinutes();
        this.seconds = other.getSeconds();
    }

    /*
    got a lot of work for this, justt need a lot of checks here
    TODO this is the only thing I really need to work on yet
     */
    public CountDownTimer(String startTime) {
       //This code is kinda shit and not very flexible but it's a start for now
        int i = startTime.indexOf(':');
        int j = startTime.lastIndexOf(':');

        //If no ':', then it must be one num (given not empty)
        if(j == -1) {
            this.seconds = Integer.parseInt(startTime);
        } else {
            //TODO this will shit itself if the is an ':' at the end, oh well
            //TODO actually the entire thing will shit itself if not formatted correctly, this code sucks
            this.seconds = Integer.parseInt(startTime.substring(j+1));
            //For hours/minutes
            if(i == j) {
                this.hours = 0;
                this.minutes = Integer.parseInt(startTime.substring(0, i));
            } else {
                this.hours = Integer.parseInt(startTime.substring(0, i));
                this.minutes = Integer.parseInt(startTime.substring(i+1, j));
            }
        }
    }



    public boolean equals(Object other) {
        if(other == null) {
            throw new IllegalArgumentException();
        }

        if(other instanceof CountDownTimer) {
            CountDownTimer check = (CountDownTimer)other;
            return (check.toString().equals(toString()));
        } else {
            throw new IllegalArgumentException();
        }

    }

    public static boolean equals(CountDownTimer t1, CountDownTimer t2) {
        if(t1 == null || t2 == null) {
            throw new IllegalArgumentException();
        }
        return (t1.toString().equals(t2.toString()));
    }

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

    //1 if this is greater, 0 if same, -1 if this is smaller
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

    /*************************************************************************
     * Compares two Timers and returns a number based on which one has a greater total timeInSeconds()
     * @param t1 Timer to be compared with t2's timeInSeconds()
     * @param t2 Timer to be compared with t1's timeInSeconds()
     * @return can return: 1:t1 is greater than t2, -1:t1 is less than t2, 0:t1 and t2 are equal
     */
    public static int compareTo(CountDownTimer t1, CountDownTimer t2) {
        if(t1 == null || t2 == null) {
            throw new IllegalArgumentException();
        } else {
            if(t1.timeInSeconds() > t2.timeInSeconds()) {
                return 1;
            } else if (t2.timeInSeconds() > t2.timeInSeconds()) {
                return -1;
            } else {
                return 0;
            }
        }

    }

    /**********************************************
     * Subtracts one second from the current time
     * @throws exception TODO add here after as well
     */
    public void dec() {
        if(this.timeInSeconds() < 0) {
            //TODO throw some error m8
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

    /************************************************************************************
     * Subtracts an amount of seconds from the current time on this object
     * @param seconds Number of seconds to subtract from the current time
     * @throws IllegalArgumentException If seconds is negative or 0
     */
    public void sub(int seconds) {
        if(seconds < 1) {
            throw new IllegalArgumentException();
        } else {
            for(int i=0; i<seconds; i++) {
                dec();
            }
        }
    }

    /**********************************************************************************************
     * Subtracts seconds from this timer equal to the timeInSeconds() of another timer
     * @param other Timer used to subtract its time equal to its timeInSeconds() from this timer
     * @throws IllegalArgumentException If a null Timer or a Timer with more total seconds than this one is passed
     */
    public void sub(CountDownTimer other) {
        if(other == null || other.timeInSeconds() > this.timeInSeconds()) {
            throw new IllegalArgumentException();
        } else {
            this.sub(other.timeInSeconds());
        }
    }

    /*************************************************************
     * Adds one second to the current timer value
     */
    public void inc() {
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

    /******************************************************************
     * Adds an amount of seconds to the current time on this object
     * @param seconds Number of seconds to add to the current time
     * @throws IllegalArgumentException If param seconds is negative or 0
     */
    public void add(int seconds) {
        if(seconds < 1) {
            throw new IllegalArgumentException();
        } else {
            for(int i=0; i<seconds; i++) {
                inc();
            }
        }
    }

    /**********************************************************************************************
     * Adds seconds to this timer equal to the timeInSeconds() of another timer
     * @param other Timer used to add its time equal to its timeInSeconds() to this timer
     * @throws IllegalArgumentException If a null Timer is passed
     */
    public void add(CountDownTimer other) {
        if(other == null) {
            throw new IllegalArgumentException();
        } else {
            this.add(other.timeInSeconds());
        }
    }

    /***************************************************
     * Just sets time to 00:00:00
     */
    private void setTimeZero(){
        setHours(0);
        setMinutes(0);
        setSeconds(0);
    }

    /******************************************************
     * Returns the time of this Timer in seconds
     * instead of 'hours:minutes:seconds', so it can be a very large number
     * @return int total time in seconds of this timer
     */
    public int timeInSeconds() {
        return (hours*3600) + (minutes*60) + (seconds);
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
        if(hours < 0) {
            throw new IllegalArgumentException();
        }
        this.hours = hours;
    }

    /**********************************************************
     * Sets only the number of minutes to the param value, does not change seconds or hours
     * @param minutes number of minutes to set this timer's minutes to
     * @throws IllegalArgumentException If minutes is negative of greater than 59
     */
    public void setMinutes(int minutes) {
        if(minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException();
        }
        this.minutes = minutes;
    }

    /*********************************************************
     * Sets only the number of seconds to the param value, does not change hours or minutes
     * @param seconds number of seconds to set this timer's seconds value to
     * @throws IllegalArgumentException If seconds are negative or greater than 59
     */
    public void setSeconds(int seconds) {
        if(seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException();
        }
        this.seconds = seconds;
    }


}
