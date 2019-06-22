package BMI;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class BMICalculator extends JFrame {

	PreparedStatement pst = null;
	ResultSet rs = null;
	Connection conn = null;

	protected double BMI = 0.0;
	protected double BMIRes = 0.0;
	protected double heightM = 0.0;
	protected int id = 1;
	protected String clockD;

	private JPanel contentPane;
	private JTextField heightTF;
	private JTextField weightTF;
	private JLabel bmiresult;
	private JLabel bmiHint;
	private JLabel lblInches;
	private JLabel lblKg;
	protected JButton btnSaveResult;
	protected JButton btnHistory;
	private JLabel lblAndYouAre;
	protected JButton btnBack;
	private JLabel welcomeLbl;
	protected JButton logoutBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BMICalculator frame = new BMICalculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BMICalculator() {

		design();

		btnBack.setVisible(false);

		conn = SQLConnection.ConnecrDb();
		middle();
		loadValues();
		calculateBMI();

		clock();

	}

	public void middle() {

		Dimension screenSize, frameSize;
		int x, y;
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameSize = getSize();
		x = (screenSize.width - frameSize.width) / 2;
		y = (screenSize.height - frameSize.height) / 2;
		setLocation(x, y);
	}

	private void saveResult() {

		try {
			String query = "INSERT INTO history (serial_FK,BMI,insertDate) VALUES (?,?,?)";

			pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			pst.setDouble(2, Double.parseDouble(bmiresult.getText()));
			pst.setString(3, clockD);

			pst.execute();
			pst.close();
			JOptionPane.showMessageDialog(null, "Saved...");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void calculateBMI() {
		heightM = (Double.parseDouble(heightTF.getText()) * 0.3048);
		BMI = (Double.parseDouble(weightTF.getText()) / Math.pow(heightM, 2));
		DecimalFormat df = new DecimalFormat(".#");
		// bmiresult.setText(String.valueOf(df.format(BMI)));
		bmiresult.setText(String.valueOf(df.format(BMI)));

		BMIHint();
	}

	private void BMIHint() {
		BMIRes = BMI;
		if (BMIRes < 18.5 && BMIRes > 1) {
			bmiHint.setText("Under Weight");
		} else if (BMIRes >= 18.5 && BMIRes <= 24.9) {
			bmiHint.setText("Healthy Weight");
		} else if (BMIRes >= 25 && BMIRes <= 29.9) {
			bmiHint.setText("Over Weight");
		} else if (BMIRes > 30) {
			bmiHint.setText("Obese");
		} else {
			bmiHint.setText("Calculate BMI First");
		}
	}

	private void clock() {

		Thread clock = new Thread() {
			public void run() {
				try {
					for (;;) {
						Calendar cal = new GregorianCalendar();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						Date date = cal.getTime();
						String timeString = formatter.format(date);
						clockD = timeString;
						// System.out.println(clockD);
						sleep(1000);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		clock.start();

	}

	@SuppressWarnings("static-access")
	private void loadValues() {

		Login l = new Login();
		welcomeLbl.setText("Welcome  " + l.name);
		heightTF.setText(String.valueOf(l.height));
		weightTF.setText(String.valueOf(l.weight));
		id = l.serial;

		// System.out.println(id);

	}

	@SuppressWarnings("static-access")
	private void guestEntry() {

		Login l = new Login();
		l.serial = 1;
		l.height = 0.0;
		l.weight = 0.0;
		l.age = 0;
		l.gender = "";
		l.name = "Guest";
	}

	private void design() {
		setTitle("BMI Calculator");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		heightTF = new JTextField();
		heightTF.setHorizontalAlignment(SwingConstants.CENTER);
		heightTF.setBounds(91, 47, 143, 36);
		contentPane.add(heightTF);
		heightTF.setColumns(10);

		weightTF = new JTextField();
		weightTF.setHorizontalAlignment(SwingConstants.CENTER);
		weightTF.setColumns(10);
		weightTF.setBounds(91, 94, 143, 36);
		contentPane.add(weightTF);

		JLabel lblHeight = new JLabel("Height");
		lblHeight.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeight.setBounds(10, 47, 71, 36);
		contentPane.add(lblHeight);

		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeight.setBounds(10, 94, 71, 36);
		contentPane.add(lblWeight);

		JButton btnCalculateBmi = new JButton("Calculate BMI");
		btnCalculateBmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculateBMI();
			}
		});
		btnCalculateBmi.setBounds(68, 174, 119, 23);
		contentPane.add(btnCalculateBmi);

		JLabel lblYourBmiIs = new JLabel("Your BMI is");
		lblYourBmiIs.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourBmiIs.setBounds(318, 29, 119, 28);
		contentPane.add(lblYourBmiIs);

		bmiresult = new JLabel("");
		bmiresult.setFont(new Font("Tahoma", Font.BOLD, 11));
		bmiresult.setForeground(Color.BLUE);
		bmiresult.setHorizontalAlignment(SwingConstants.CENTER);
		bmiresult.setBounds(318, 74, 119, 33);
		contentPane.add(bmiresult);

		bmiHint = new JLabel("-");
		bmiHint.setForeground(Color.ORANGE);
		bmiHint.setFont(new Font("Tahoma", Font.BOLD, 14));
		bmiHint.setHorizontalAlignment(SwingConstants.CENTER);
		bmiHint.setBounds(267, 154, 207, 44);
		contentPane.add(bmiHint);

		lblInches = new JLabel("foot");
		lblInches.setHorizontalAlignment(SwingConstants.CENTER);
		lblInches.setBounds(232, 47, 42, 36);
		contentPane.add(lblInches);

		lblKg = new JLabel("KG");
		lblKg.setHorizontalAlignment(SwingConstants.CENTER);
		lblKg.setBounds(232, 94, 42, 36);
		contentPane.add(lblKg);

		btnSaveResult = new JButton("Save Result");
		btnSaveResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (bmiresult.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Calculate BMI First");
				} else {
					saveResult();
				}
			}
		});
		btnSaveResult.setBounds(253, 209, 119, 23);
		contentPane.add(btnSaveResult);

		btnHistory = new JButton("History");
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BMIHistory bh = new BMIHistory();
				bh.setVisible(true);
				dispose();
			}
		});
		btnHistory.setBounds(382, 209, 93, 23);
		contentPane.add(btnHistory);

		logoutBtn = new JButton("Logout");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login();
				l.setVisible(true);
				dispose();
				guestEntry();
			}
		});
		logoutBtn.setBounds(321, 237, 89, 23);
		contentPane.add(logoutBtn);

		lblAndYouAre = new JLabel("and You are");
		lblAndYouAre.setHorizontalAlignment(SwingConstants.CENTER);
		lblAndYouAre.setForeground(Color.BLACK);
		lblAndYouAre.setBounds(318, 117, 119, 33);
		contentPane.add(lblAndYouAre);

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login();
				l.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(78, 208, 89, 23);
		contentPane.add(btnBack);

		welcomeLbl = new JLabel("Welcome");
		welcomeLbl.setBounds(10, 11, 207, 23);
		contentPane.add(welcomeLbl);
	}
}
