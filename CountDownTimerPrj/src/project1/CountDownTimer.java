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
        this.hours = other.hours;
        this.minutes = other.minutes;
        this.seconds = other.seconds;
    }

    /*
    got a lot of work for this, justt need a lot of checks here
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
        //TODO add checks for null and instanceOf CountdownTimer on both equals
        //It is also only equal if mins and hours and seconds are the same
        if (other == null) {
             throw new IllegalArgumentException();
        }

        ;
        CountDownTimer check = (CountDownTimer)other;
        return (check.timeInSeconds() == timeInSeconds());
    }

    public static boolean equals(CountDownTimer t1, CountDownTimer t2) {
        if(t1 == null || t2 == null) {
            throw new IllegalArgumentException();
        }
        return (t1.timeInSeconds()== t2.timeInSeconds());
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
        if(timeInSeconds() > other.timeInSeconds()) {
            return 1;
        } else if (other.timeInSeconds() > timeInSeconds()) {
            return -1;
        } else {
            return 0;
        }


    }

    //1 if t1 is greater, 0 if same, -1 if t1 is smaller
    public static int compareTo(CountDownTimer t1, CountDownTimer t2) {
        if(t1.timeInSeconds() > t2.timeInSeconds()) {
            return 1;
        } else if (t2.timeInSeconds() > t2.timeInSeconds()) {
            return -1;
        } else {
            return 0;
        }
    }

    public void dec() {

        if(seconds > 0) {
            seconds--;
        } else {
            if(minutes > 0) {
                minutes--;
                seconds = 59;
            } else {
                if(hours > 0) {
                    hours--;
                    minutes = 59;
                    seconds = 59;
                } else {
                    //Can't decrease, timer is 0:0:0, maybe add something for this case later
                }
            }
        }

    }

    /**
     * Subtracts an amount of seconds from the current time on this object
     * @param seconds Number of seconds to subtract from the total time
     */
    public void sub(int seconds) {
        /* This is obviously bad code, it's stupid and doesn't scale well
        However I think it will be plenty good enough for a simple program like this,
        especially since it will usually be called with smaller 'seconds' values
        */
        for(int i=0; i<seconds; i++) {
            dec();
        }
    }

    public void sub(CountDownTimer other) {
        this.sub(other.timeInSeconds());
    }

    public void inc() {
        if(seconds < 59) {
            seconds++;
        } else {
            if(minutes < 59) {
                minutes++;
                seconds = 0;
            } else {
                hours++;
                minutes = 0;
                seconds = 0;
            }
        }
    }

    /**
     * Adds an amount of seconds to the current time on this object
     * @param seconds Number of seconds to add to the total time
     */
    public void add(int seconds) {
        /* This is also obviously bad code,
            but again, it should work fine for a simple
            program like this
        */
        for(int i=0; i<seconds; i++) {
            inc();
        }
    }

    public void add(CountDownTimer other) {
        this.add(other.timeInSeconds());
    }

    /**
     * Just sets time to 00:00:00
     */
    private void setTimeZero(){
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }


    //TODO maybe just remove this
    public int timeInSeconds() {
        return (hours*3600) + (minutes*60) + (seconds);
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
