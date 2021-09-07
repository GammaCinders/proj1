package project1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CountDownTimerPanelMainSwing extends JPanel {

	private JMenuItem quitItem;
	private JMenuItem suspendItem;
	private JButton supsendTrue;
	private JButton suspendFalse;
	
	public CountDownTimerPanelMainSwing(JMenuItem quitItem, JMenuItem suspendItem) {
		this.quitItem = quitItem;
		this.suspendItem = suspendItem;
		
		JPanel panel = new JPanel();
		panel.add(new CountDownTimerPanelSwing());

		suspendFalse = new JButton("Suspend false");
		supsendTrue = new JButton("Suspend true");
		add(panel);
		add (suspendFalse);
		add (supsendTrue);
	
		quitItem.addActionListener(new Mylistener());
		suspendItem.addActionListener(new Mylistener());
		supsendTrue.addActionListener(new Mylistener());
		suspendFalse.addActionListener(new Mylistener());
	}
	
	private class Mylistener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == quitItem){
				System.exit(1);
			}

//			if (e.getSource() == suspendFalse)
//				CountDownTimer.setSuspend(false);
//			if (e.getSource() == supsendTrue)
//				CountDownTimer.setSuspend(true);
		}
		
	}
}
