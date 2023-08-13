package mutlithreading;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MTDemo extends JFrame implements ActionListener {
	JTextField timeNMTTask;
	JTextField timeMTTask;
	JTextField testHang;

	JButton btnNMTTask;
	JButton btnMTTask;
	JButton btnTestHang;

	public MTDemo() {
		GridLayout layout = new GridLayout(2, 3);
		super.setLayout(layout);

		Font font = new Font("Comic Sans MS", 1, 100);

		timeNMTTask = new JTextField();
		timeNMTTask.setFont(font);
		super.add(timeNMTTask);

		timeMTTask = new JTextField();
		timeMTTask.setFont(font);
		super.add(timeMTTask);

		testHang = new JTextField();
		testHang.setFont(font);
		super.add(testHang);

		btnNMTTask = new JButton("NMT");
		btnNMTTask.addActionListener(this);
		btnNMTTask.setFont(font);
		super.add(btnNMTTask);

		btnMTTask = new JButton("MT");
		btnMTTask.addActionListener(this);
		btnMTTask.setFont(font);
		super.add(btnMTTask);

		btnTestHang = new JButton("Hang");
		btnTestHang.addActionListener(this);
		btnTestHang.setFont(font);
		super.add(btnTestHang);

		super.setTitle("MT Demo");
		super.setVisible(true);
		super.setExtendedState(MAXIMIZED_BOTH);
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNMTTask) {
			long start = System.currentTimeMillis();
			longtask();
			long duration = System.currentTimeMillis() - start;
			timeNMTTask.setText(duration + "");
		} else if (e.getSource() == btnMTTask) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					long start = System.currentTimeMillis();
					longtask();
					long duration = System.currentTimeMillis() - start;
					timeMTTask.setText(duration + "");
				}
			});
			t.start();
		} else {
		}
	}

	public static void longtask() {
		String s = "";
		for (int i = 0; i < 100000; i++) {
			s = s + i;
		}
	}

}
