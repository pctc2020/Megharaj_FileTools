package megharaj.intelora;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javax.media.Manager;
import javax.media.Player;

class FindMoveADStructures extends JPanel implements ActionListener {
	JFrame frame;
	JButton b1, b3;
	JPanel p, p2;
	JFrame f1;
	JMenuBar mbar;
	JMenu m1, m2;
	JMenuItem ofile, exitfile, aboutus;
	JFileChooser fc;
	private Player audioplayer = null;
	private File file;

	public FindMoveADStructures() {
		f1 = new JFrame("Audio Player");
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fc = new JFileChooser();
		mbar = new JMenuBar();
		mbar.setOpaque(true);
		mbar.setBackground(new Color(154, 165, 127));
		m1 = new JMenu("File");
		m2 = new JMenu("Help");
		mbar.add(m1);
		mbar.add(m2);
		ofile = new JMenuItem("Open");
		ofile.addActionListener(new playing());
		exitfile = new JMenuItem("Exit");
		exitfile.addActionListener(this);
		aboutus = new JMenuItem("AboutUs");
		aboutus.addActionListener(this);
		m1.add(ofile);
		m1.addSeparator();
		m1.add(exitfile);
		m2.add(aboutus);
		f1.setJMenuBar(mbar);
		p = new JPanel();
		p2 = new JPanel();
		p.setLayout(null);
		p.setBackground(Color.blue);
		p.add(p2).setBounds(0, 0, 350, 60);
		p2.setBackground(Color.darkGray);
		f1.add(p);
		b1 = new JButton("Play");
		b1.addActionListener(new playing());
		b3 = new JButton("Stop");
		b3.setEnabled(false);
		b3.addActionListener(this);
		p2.add(b1);
		p2.add(b3);
		f1.setSize(350, 110);
		f1.setVisible(true);
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == ofile) {
			int returnVal = fc.showOpenDialog(FindMoveADStructures.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile();
			} else {
				System.out.println("File Access Canceled by user");
			}
		}
		if (evt.getSource() == b3) {
			audioplayer.stop();
			b1.setEnabled(true);
			b3.setEnabled(false);
		}
		if (evt.getSource() == exitfile) {
			System.exit(0);
		}
		if (evt.getSource() == aboutus) {
			JFrame af1 = new JFrame("About Us");
			af1.setVisible(true);
			af1.setSize(350, 200);
			af1.setLocation(300, 300);
			JPanel ap1 = new JPanel();
			ap1.setBackground(Color.white);
			af1.add(ap1);
			JLabel l1 = new JLabel("Developed by Ashish....", JLabel.CENTER);
			ap1.add(l1);
		}
	}

	public void oplay() {
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = fc.showOpenDialog(this);
		if (result == JFileChooser.CANCEL_OPTION)
			file = null;
		else
			file = fc.getSelectedFile();
	}

	public void cplay() {
		if (file == null)
			return;
		try {
			audioplayer = Manager.createRealizedPlayer(file.toURI().toURL());
			audioplayer.start();
		} catch (Exception xe) {
			JOptionPane.showMessageDialog(frame, "Invalid Files Types..", "Error  loading page",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public class playing implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			oplay();
			cplay();
			b1.setEnabled(false);
			b3.setEnabled(true);
		}
	}

	public static void main(String args[]) {
		FindMoveADStructures me = new FindMoveADStructures();
	}
}