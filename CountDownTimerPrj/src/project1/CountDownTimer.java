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
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public CountDownTimer(int minutes, int seconds) {
        this.hours = 0;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public CountDownTimer(int seconds) {
        this.hours = 0;
        this.minutes = 0;
        this.seconds = seconds;
    }

    public CountDownTimer(CountDownTimer other) {
        this.hours = other.hours;
        this.minutes = other.minutes;
        this.seconds = other.seconds;
    }

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
        CountDownTimer check = (CountDownTimer)other;
        return check.toString().equals(toString());
    }

    public static boolean equals(CountDownTimer t1, CountDownTimer t2) {
        return t1.toString().equals(t2.toString());
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
            minutes--;
            seconds = 59;
        }
    }

//    Why did I do this method instead of doing it the epic gaming way???
//    public  void sub(int seconds) {
//        if (seconds <= this.seconds) {
//            this.seconds -= seconds;
//        } else {
//            int numOfHours = seconds/3600;
//            this.hours -= numOfHours;
//            seconds -= numOfHours*3600;
//            this.minutes -= seconds/60;
//            this.seconds -= seconds%60;
//        }
//    }

    public void sub(int seconds) {
        //I don't know if this is dumb or smart but I like it
        for(int i=0; i<seconds; i++) {
            dec();
        }
    }

    public void sub(CountDownTimer other) {

    }


    private void setTimeZero(){
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }

    public int timeInSeconds() {
        return (hours*3600) + (minutes*60) + (seconds);
    }
}
