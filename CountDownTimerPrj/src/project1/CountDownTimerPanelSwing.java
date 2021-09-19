package project1;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Locale;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * This is the portion of the GUI that includes the boxes where you type your hours, minutes, and seconds.
 * Also included are buttons to start/stop/continue the timer, as well as a way to add seconds,
 * load/save your timer, etc.
 *
 * @author Eric Hargrove and Keagen Talsma
 * @version 9/14/2021
 */
public class CountDownTimerPanelSwing extends JPanel {

    private CountDownTimer watch;
    private Timer javaTimer;

    private JLabel hourLabel, minLabel, secLabel;
    private JButton initButton, stopButton, saveButton, loadButton, addButton, stringInputButton, startButton, subButton, incButton, decButton, equalToButton, compareToButton;
    private JTextField hourField, minField, secField, addSecondsField, newStringField, subSecondsField;

    private JLabel lblTime;

    public CountDownTimerPanelSwing() {
        // Creates the CountDownTimer and Timer
        watch = new CountDownTimer();
        javaTimer = new Timer(1000, new TimerListener());

        //General setup for the window
        setLayout(new GridLayout(17, 2));
        setBackground(Color.lightGray);


        //I like how this looks at the top: string init constructor
        stringInputButton = new JButton("New (From String)");
        add(stringInputButton);
        newStringField = new JTextField();
        add(newStringField);

        add(newBlankGrayPanel());
        add(newBlankGrayPanel());

        //add Listener
        stringInputButton.addActionListener(new ButtonListener());


        //Creates and adds the 2nd portion of the panel (setup + adding hours, minutes, seconds and init)
        hourLabel = new JLabel("Hours:");
        add(hourLabel);
        hourField = new JTextField();
        add(hourField);

        minLabel = new JLabel("Minutes:");
        add(minLabel);
        minField = new JTextField();
        add(minField);

        secLabel = new JLabel("Seconds:");
        add(secLabel);
        secField = new JTextField();
        add(secField);

        add(newBlankGrayPanel());
        initButton = new JButton("Initialize");
        add(initButton);

        add(newBlankGrayPanel());
        add(newBlankGrayPanel());

        //add Listener
        initButton.addActionListener(new ButtonListener());


        // Setup and adding the control buttons and their needed fields
        stopButton = new JButton("Stop");
        add(stopButton);
        startButton = new JButton("Start");
        add(startButton);

        add(newBlankGrayPanel());
        add(newBlankGrayPanel());

        //add Listeners
        stopButton.addActionListener(new ButtonListener());
        startButton.addActionListener(new ButtonListener());


        // Setup and adding the mutation control buttons and fields
        addButton = new JButton("Add");
        add(addButton);
        addSecondsField = new JTextField();
        add(addSecondsField);

        subButton = new JButton("Subtract");
        add(subButton);
        subSecondsField = new JTextField();
        add(subSecondsField);

        incButton = new JButton("Increment");
        add(incButton);
        decButton = new JButton("Decrement");
        add(decButton);

        add(newBlankGrayPanel());
        add(newBlankGrayPanel());

        //add Listener
        subButton.addActionListener(new ButtonListener());
        addButton.addActionListener(new ButtonListener());
        incButton.addActionListener(new ButtonListener());
        decButton.addActionListener(new ButtonListener());


        //Compare to and equal to checks buttons
        equalToButton = new JButton("Equal to");
        add(equalToButton);
        compareToButton = new JButton("Compare to");
        add(compareToButton);

        add(newBlankGrayPanel());
        add(newBlankGrayPanel());

        //add Listener
        equalToButton.addActionListener(new ButtonListener());
        compareToButton.addActionListener(new ButtonListener());


        //Load buttons/fields
        loadButton = new JButton("Load");
        add(loadButton);
        saveButton = new JButton("Save");
        add(saveButton);

        //add Listeners
        saveButton.addActionListener(new ButtonListener());
        loadButton.addActionListener(new ButtonListener());


        //Add bottom text for showing time
        add(new JLabel("Time:"));
        lblTime = new JLabel();
        add(lblTime);
        lblTime.setText(watch.toString());
    }

    private JPanel newBlankGrayPanel() {
        JPanel j = new JPanel();
        j.setBackground(Color.lightGray);
        return j;
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == initButton) {
                int hours, mins, secs;
                try {
                    if(!CountDownTimer.isSuspended()) {
                        //Setup hours/mins/secs for constructor call
                        if(hourField.getText().equals("")) {
                            hours = 0;
                        } else {
                            hours = Integer.parseInt(hourField.getText());
                        }

                        if(minField.getText().equals("")) {
                            mins = 0;
                        } else {
                            mins = Integer.parseInt(minField.getText());
                        }

                        if(secField.getText().equals("")) {
                            secs = 0;
                        } else {
                            secs = Integer.parseInt(secField.getText());
                        }

                        //Call correct constructor here (I want to make sure they all work)
                        if(hours != 0) {
                            watch = new CountDownTimer(hours, mins, secs);
                        } else if(mins != 0) {
                            watch = new CountDownTimer(mins, secs);
                        } else if(secs != 0) {
                            watch = new CountDownTimer(secs);
                        } else {
                            watch = new CountDownTimer();
                        }
                    }
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            if (event.getSource() == stopButton) {
                javaTimer.stop();
            }

            if (event.getSource() == startButton) {
                javaTimer.start();
            }

            if (event.getSource() == saveButton) {

                JTextArea textArea = new JTextArea(20, 30);
                JFileChooser chooser = new JFileChooser();
                int status = chooser.showSaveDialog(null);

                if (status != JFileChooser.APPROVE_OPTION) {
                    textArea.setText("No file saved");
                }
                else {
                    watch.save(chooser.getSelectedFile().getPath());
                }
            }

            if (event.getSource() == loadButton) {
                if(!CountDownTimer.isSuspended()) {
                    JFileChooser chooser = new JFileChooser();
                    int status = chooser.showOpenDialog(null);

                    if (status != JFileChooser.APPROVE_OPTION) {
                        JOptionPane.showMessageDialog(null, "No file chosen");
                    } else {
                        File file = chooser.getSelectedFile();
                        watch.load(file.getPath());
                    }
                }
            }

            if (event.getSource() == addButton) {
                try {
                    watch.add(Integer.parseInt(addSecondsField.getText()));
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Illegal seconds in Add field");
                }
            }

            if (event.getSource() == stringInputButton) {
                watch = new CountDownTimer(newStringField.getText());
                javaTimer.start();
            }

            if(event.getSource() == subButton) {
                try {
                    watch.sub(Integer.parseInt(subSecondsField.getText()));
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(null, "Illegal seconds in Subtract field");
                }
            }

            if(event.getSource() == incButton) {
                watch.inc();
            }

            if(event.getSource() == decButton) {
                watch.dec();
            }

            if(event.getSource() == compareToButton) {
                JFileChooser fileChooser = new JFileChooser();
                int status = fileChooser.showOpenDialog(null);

                if(status != JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(null, "No other CountDownTimer selected to compare to");
                } else {
                    File file = fileChooser.getSelectedFile();

                    try {
                        Scanner sc = new Scanner(file);
                        String otherTimerString = sc.nextLine();
                        CountDownTimer c = new CountDownTimer(otherTimerString);

                        switch (CountDownTimer.compareTo(watch, c)) {
                            case -1:
                                JOptionPane.showMessageDialog(null, "This CountDownTimer has less time");
                                break;
                            case 0:
                                JOptionPane.showMessageDialog(null, "Both CountDownTimers have the same time");
                                break;
                            case 1:
                                JOptionPane.showMessageDialog(null, "This CountDownTimer has more time");
                                break;
                        }
                    } catch (Exception e) {

                    }
                }
            }

            if(event.getSource() == equalToButton) {
                JFileChooser fileChooser = new JFileChooser();
                int status = fileChooser.showOpenDialog(null);

                if(status != JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(null, "No other CountDownTimer selected to check if equal to");
                } else {
                    File file = fileChooser.getSelectedFile();
                    String otherTimerString;
                    try {
                        Scanner sc = new Scanner(file);
                        otherTimerString = sc.nextLine();
                        CountDownTimer c = new CountDownTimer(otherTimerString);
                        if(CountDownTimer.equals(watch, c)) {
                            JOptionPane.showMessageDialog(null, "The two CountDownTimers are equal (same time)");
                        } else {
                            JOptionPane.showMessageDialog(null, "The two CountDownTimers are not equal (different time)");
                        }
                    } catch (Exception e){

                    }

                }
            }

            lblTime.setText(watch.toString());
        }

    }

    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                if(watch.timeInSeconds() > 0) {
                    watch.sub(1);
                    lblTime.setText(watch.toString());
                }
            } catch (Exception exception) {
                throw new IllegalArgumentException();
            }
        }
    }

}
