package project1;

import javax.swing.*;

public class CountDownTimerGUISwing {
	public static void main(String arg[]){

		JMenu fileMenu;
		JMenuItem quitItem;
		JCheckBoxMenuItem suspendItem;
		JMenuBar menus;

		fileMenu = new JMenu("File");
		quitItem = new JMenuItem("Quit");
		suspendItem = new JCheckBoxMenuItem ("Suspend");
		fileMenu.add(suspendItem);
		fileMenu.add(quitItem);
		menus = new JMenuBar();

		menus.add(fileMenu);

		JFrame gui = new JFrame("Count Down Timer");
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		CountDownTimerPanelMainSwing panel = new CountDownTimerPanelMainSwing(quitItem, suspendItem);
		gui.getContentPane().add(panel);

		gui.setSize(600,400);
		gui.setJMenuBar(menus);
		gui.pack();
		gui.setVisible(true);
	}

}
