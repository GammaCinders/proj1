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

    //1 if t1 is greater, 0 if same, -1 if t1 is smaller
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
                    //TODO maybe add some Exception here
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
        if(seconds < 1) {
            throw new IllegalArgumentException();
        } else {
            for(int i=0; i<seconds; i++) {
                dec();
            }
        }
    }

    public void sub(CountDownTimer other) {
        if(other == null) {
            throw new IllegalArgumentException();
        } else {
            this.sub(other.timeInSeconds());
        }
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
        if(seconds < 0) {
            throw new IllegalArgumentException();
        } else {
            for(int i=0; i<seconds; i++) {
                inc();
            }
        }
    }

    public void add(CountDownTimer other) {
        if(other == null) {
            throw new IllegalArgumentException();
        } else {
            this.add(other.timeInSeconds());
        }
    }

    /**
     * Just sets time to 00:00:00
     */
    private void setTimeZero(){
        setHours(0);
        setMinutes(0);
        setSeconds(0);
    }

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
