package pl.polsl.concretecalculator;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

public class View {

	private JFrame frmConcreteCalculator;
	public JTextField textField_h;
	public JTextField textField_b;
	public JTextField textField_mEd;
	private JTextField textField_cnom;
	private JTextField textField_fck;
	private JTextField textField_fctm;
	private JTextField textField_yc;
	private JTextField textField_dg;
	private JTextField textField_fiS;
	private JTextField textField_fiM;
	private JTextField textField_ys;
	private JTextField textField_Es;
	private JTextField textField_fyk;
	private JTextPane textPane_calculationResults;
	DrawCrossSection panel = new DrawCrossSection();
	Calculations calculations = new Calculations();
	
	
	
	public void setAllValues () {
		//do przeliczenia a1, d, fcd, fyd
	
		
		double b = Double.valueOf(textField_b.getText())*0.001;
		double h = Double.valueOf(textField_h.getText())*0.001;
		double mEd = Double.valueOf(textField_mEd.getText());
		double cnom = Double.valueOf(textField_cnom.getText())*0.001;
		double fck = Double.valueOf(textField_fck.getText())*1000;
		double fctm = Double.valueOf(textField_fctm.getText())*1000;
		double yc = Double.valueOf(textField_yc.getText());
		double dg = Double.valueOf(textField_dg.getText())*0.001;
		double fyk = Double.valueOf(textField_fyk.getText())*1000;
		double Es = Double.valueOf(textField_Es.getText())*1000000;
		double ys = Double.valueOf(textField_ys.getText());
		double fiM = Double.valueOf(textField_fiM.getText())*0.001;
		double fiS = Double.valueOf(textField_fiS.getText())*0.001;
		double a1 = cnom+fiS+fiM*0.5;
		double d = h-a1;
		double fcd = fck/yc;
		double fyd = fyk/ys;
		
		calculations.setB(b);
		calculations.setH(h);
		calculations.setmEd(mEd);
		calculations.setCnom(cnom);
		calculations.setFck(fck);
		calculations.setFctm(fctm);
		calculations.setYc(yc);
		calculations.setDg(dg);
		calculations.setFyk(fyk);
		calculations.setEs(Es);
		calculations.setYs(ys);
		calculations.setFiM(fiM);
		calculations.setFiS(fiS);
		calculations.setA1(a1);
		calculations.setD(d);
		calculations.setFcd(fcd);
		calculations.setFyd(fyd);
		calculations.setNrz(0);
		

		
		calculations.checkSGN();
		
		
		double lsrz = calculations.calculateActualDistanceBetweenRebarsInRow()*1000;
		double lsmin = calculations.calculateMinimumDistanceBetweenRebars()*1000;
		double nRebars = calculations.getNrz ();
		double nrzMaxInRow = calculations.calculateMaximumAmountOfRebarsInRow();
		double nRows = calculations.calculateAmountOfRows ();

		panel.setValues(b*1000, h*1000, fiM*1000, fiS*1000, cnom*1000, lsrz, lsmin, nRebars, nrzMaxInRow, nRows);

		
		
		textPane_calculationResults.setText(calculations.calculationsResults());
	
		panel.repaint();
		
	
		
		
		 
	}
	

	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frmConcreteCalculator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConcreteCalculator = new JFrame();
		frmConcreteCalculator.getContentPane().setFont(UIManager.getFont("CheckBoxMenuItem.acceleratorFont"));
		frmConcreteCalculator.setTitle("Kalkulator beton EC");
		frmConcreteCalculator.setResizable(false);
		frmConcreteCalculator.setBounds(100, 100, 800, 600);
		frmConcreteCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConcreteCalculator.getContentPane().setLayout(null);
		frmConcreteCalculator.setLocationRelativeTo(null);
		
		
		
	
	
		
		panel.setBackground(Color.WHITE);
		panel.setBounds(450, 50, DrawCrossSection.JPANELWIDTH, DrawCrossSection.JPANELHEIGHT);
		frmConcreteCalculator.getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("OBLICZ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
				setAllValues();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addMouseListener(new MouseAdapter() {
			

			@Override
			public void mouseClicked(MouseEvent e) {
			//	crossSection.setStaticMoment(Double.valueOf(textField_2.getText()));
			//System.out.println(Double.valueOf(textField_2.getText()));
			//System.out.println(crossSection.getStaticMoment());
			//	crossSection.setStaticMoment(Double.valueOf(textField_2.getText()));
			//	crossSection.setWidth(Double.valueOf(textField_1.getText()));
			//	crossSection.setHeight(Double.valueOf(textField.getText()));
				
			//	calculations.setmEd(Double.valueOf(textField_2.getText()));
				
			}
		});
		
		btnNewButton.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				
			}
		});
		btnNewButton.setBounds(450, 471, 300, 82);
		frmConcreteCalculator.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("przekr\u00F3j projektowanej belki");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(450, 30, 300, 13);
		frmConcreteCalculator.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_description_crossSectionParameters = new JLabel("parametry przekroju:");
		lblNewLabel_description_crossSectionParameters.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_description_crossSectionParameters.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_description_crossSectionParameters.setBounds(40, 55, 120, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_description_crossSectionParameters);
		
		JLabel lblNewLabel_description_concreteParameters = new JLabel("parametry betonu:");
		lblNewLabel_description_concreteParameters.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_description_concreteParameters.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_description_concreteParameters.setBounds(40, 165, 120, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_description_concreteParameters);
		
		textField_h = new JTextField();
		textField_h.setHorizontalAlignment(SwingConstants.CENTER);
		textField_h.setText("300");
		textField_h.setBounds(220, 95, 96, 19);
		frmConcreteCalculator.getContentPane().add(textField_h);
		textField_h.setColumns(10);
		
		textField_b = new JTextField();
		textField_b.setHorizontalAlignment(SwingConstants.CENTER);
		textField_b.setText("250");
		textField_b.setBounds(220, 75, 96, 19);
		frmConcreteCalculator.getContentPane().add(textField_b);
		textField_b.setColumns(10);
		
		textField_mEd = new JTextField();
		textField_mEd.setHorizontalAlignment(SwingConstants.CENTER);
		textField_mEd.setText("60");
		textField_mEd.setBounds(220, 115, 96, 19);
		frmConcreteCalculator.getContentPane().add(textField_mEd);
		textField_mEd.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("wprowadzanie danych");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(40, 30, 389, 13);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_4);
		
		JSeparator separator = new JSeparator();
		separator.setToolTipText("123");
		separator.setBackground(Color.WHITE);
		separator.setBounds(40, 50, 389, 2);
		frmConcreteCalculator.getContentPane().add(separator);
		
		JLabel lblNewLabel_description_b = new JLabel("szeroko\u015B\u0107:");
		lblNewLabel_description_b.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_description_b.setBounds(40, 75, 120, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_description_b);
		
		JLabel lblNewLabel_description_h = new JLabel("wysoko\u015B\u0107:");
		lblNewLabel_description_h.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_description_h.setBounds(40, 95, 120, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_description_h);
		
		JLabel lblNewLabel_description_cnom = new JLabel("grubo\u015B\u0107 otuliny:");
		lblNewLabel_description_cnom.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_description_cnom.setBounds(40, 135, 120, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_description_cnom);
		
		JLabel lblNewLabel_description_mEd = new JLabel("moment statyczny:");
		lblNewLabel_description_mEd.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_description_mEd.setBounds(40, 115, 120, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_description_mEd);
		
		textField_cnom = new JTextField();
		textField_cnom.setHorizontalAlignment(SwingConstants.CENTER);
		textField_cnom.setText("30");
		textField_cnom.setBounds(220, 135, 96, 19);
		frmConcreteCalculator.getContentPane().add(textField_cnom);
		textField_cnom.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(40, 160, 389, 2);
		frmConcreteCalculator.getContentPane().add(separator_1);
		
		JLabel lblNewLabel_description_concreteClass = new JLabel("klasa betonu:");
		lblNewLabel_description_concreteClass.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_description_concreteClass.setBounds(40, 185, 120, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_description_concreteClass);
		
		JLabel lblNewLabel_description_fck = new JLabel("wytrz. na \u015Bciskanie:");
		lblNewLabel_description_fck.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_description_fck.setBounds(40, 205, 120, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_description_fck);
		
		JLabel lblNewLabel_description_fctm = new JLabel("\u015Br. wytrz. na rozci\u0105ganie:");
		lblNewLabel_description_fctm.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_description_fctm.setBounds(40, 225, 140, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_description_fctm);
		
		JLabel lblNewLabel_description_yc = new JLabel("wsp. bezpiecze\u0144stwa:");
		lblNewLabel_description_yc.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_description_yc.setBounds(40, 245, 120, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_description_yc);
		
		textField_fck = new JTextField();
		textField_fck.setHorizontalAlignment(SwingConstants.CENTER);
		textField_fck.setText("20");
		textField_fck.setBounds(220, 205, 96, 19);
		frmConcreteCalculator.getContentPane().add(textField_fck);
		textField_fck.setColumns(10);
		
		textField_fctm = new JTextField();
		textField_fctm.setHorizontalAlignment(SwingConstants.CENTER);
		textField_fctm.setText("2.2");
		textField_fctm.setBounds(220, 225, 96, 19);
		frmConcreteCalculator.getContentPane().add(textField_fctm);
		textField_fctm.setColumns(10);
		
		textField_yc = new JTextField();
		textField_yc.setHorizontalAlignment(SwingConstants.CENTER);
		textField_yc.setText("1.4");
		textField_yc.setBounds(220, 245, 96, 19);
		frmConcreteCalculator.getContentPane().add(textField_yc);
		textField_yc.setColumns(10);
		
		JLabel lblNewLabel_symbol_b = new JLabel("b =");
		lblNewLabel_symbol_b.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_symbol_b.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_symbol_b.setBounds(175, 75, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_symbol_b);
		
		JLabel lblNewLabel_symbol_cnom = new JLabel("cnom =");
		lblNewLabel_symbol_cnom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_symbol_cnom.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_symbol_cnom.setBounds(175, 135, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_symbol_cnom);
		
		JLabel lblNewLabel_symbol_mEd = new JLabel("MEd =");
		lblNewLabel_symbol_mEd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_symbol_mEd.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_symbol_mEd.setBounds(175, 115, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_symbol_mEd);
		
		JLabel lblNewLabel_symbol_h = new JLabel("h =");
		lblNewLabel_symbol_h.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_symbol_h.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_symbol_h.setBounds(175, 95, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_symbol_h);
		
		JLabel lblNewLabel_unit_cnom = new JLabel("[mm]");
		lblNewLabel_unit_cnom.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_unit_cnom.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_unit_cnom.setBounds(320, 135, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_unit_cnom);
		
		JLabel lblNewLabel_13_unit_mEd = new JLabel("[kNm]");
		lblNewLabel_13_unit_mEd.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_13_unit_mEd.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_13_unit_mEd.setBounds(320, 115, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_13_unit_mEd);
		
		JLabel lblNewLabel_unit_h = new JLabel("[mm]");
		lblNewLabel_unit_h.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_unit_h.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_unit_h.setBounds(320, 95, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_unit_h);
		
		JLabel lblNewLabel_unit_b = new JLabel("[mm]");
		lblNewLabel_unit_b.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_unit_b.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_unit_b.setBounds(320, 75, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_unit_b);
		
		JLabel lblNewLabel_symbol_fck = new JLabel("fck =");
		lblNewLabel_symbol_fck.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_symbol_fck.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_symbol_fck.setBounds(175, 205, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_symbol_fck);
		
		JLabel lblNewLabel_symbol_fctm = new JLabel("fctm =");
		lblNewLabel_symbol_fctm.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_symbol_fctm.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_symbol_fctm.setBounds(175, 225, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_symbol_fctm);
		
		JLabel lblNewLabel_symbol_yc = new JLabel("yc =");
		lblNewLabel_symbol_yc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_symbol_yc.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_symbol_yc.setBounds(175, 245, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_symbol_yc);
		
		JLabel lblNewLabel_unit_concreteClass = new JLabel("[ - ]");
		lblNewLabel_unit_concreteClass.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_unit_concreteClass.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_unit_concreteClass.setBounds(320, 185, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_unit_concreteClass);
		
		JLabel lblNewLabel_unit_fck = new JLabel("[MPa]");
		lblNewLabel_unit_fck.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_unit_fck.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_unit_fck.setBounds(320, 205, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_unit_fck);
		
		JLabel lblNewLabel_unit_fctm = new JLabel("[MPa]");
		lblNewLabel_unit_fctm.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_unit_fctm.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_unit_fctm.setBounds(320, 225, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_unit_fctm);
		
		JLabel lblNewLabel_unit_yc = new JLabel("[ - ]");
		lblNewLabel_unit_yc.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_unit_yc.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_unit_yc.setBounds(320, 245, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_unit_yc);
		
		JLabel lblNewLabel_description_dg = new JLabel("maks. \u015Brednica kruszywa:");
		lblNewLabel_description_dg.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_description_dg.setBounds(40, 265, 140, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_description_dg);
		
		JLabel lblNewLabel_symbol_dg = new JLabel("dg =");
		lblNewLabel_symbol_dg.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_symbol_dg.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_symbol_dg.setBounds(175, 265, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_symbol_dg);
		
		textField_dg = new JTextField();
		textField_dg.setHorizontalAlignment(SwingConstants.CENTER);
		textField_dg.setText("12");
		textField_dg.setColumns(10);
		textField_dg.setBounds(220, 265, 96, 19);
		frmConcreteCalculator.getContentPane().add(textField_dg);
		
		JLabel lblNewLabel_unit_dg = new JLabel("[mm]");
		lblNewLabel_unit_dg.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_unit_dg.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_unit_dg.setBounds(320, 265, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_unit_dg);
		
		JComboBox<String> comboBox_concreteClass = new JComboBox<String>();
		comboBox_concreteClass.setBackground(Color.WHITE);
		((JLabel) comboBox_concreteClass.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		comboBox_concreteClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (comboBox_concreteClass.getSelectedItem().toString()) {
				case "C20/25": textField_fck.setText("20"); textField_fctm.setText("2.2"); break;
				case "C25/30": textField_fck.setText("25"); textField_fctm.setText("2.6"); break;
				case "C30/37": textField_fck.setText("30"); textField_fctm.setText("2.9"); break;
				case "C35/45": textField_fck.setText("35"); textField_fctm.setText("3.2"); break;
				case "C40/50": textField_fck.setText("40"); textField_fctm.setText("3.5"); break;
				case "C45/55": textField_fck.setText("45"); textField_fctm.setText("3.8"); break;
				case "C50/60": textField_fck.setText("50"); textField_fctm.setText("4.1"); break;
				
				}
			
			}
		});
		comboBox_concreteClass.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBox_concreteClass.setBounds(220, 185, 96, 19);
		frmConcreteCalculator.getContentPane().add(comboBox_concreteClass);
		comboBox_concreteClass.addItem("C20/25");
		comboBox_concreteClass.addItem("C25/30");
		comboBox_concreteClass.addItem("C30/37");
		comboBox_concreteClass.addItem("C35/45");
		comboBox_concreteClass.addItem("C40/50");
		comboBox_concreteClass.addItem("C45/55");
		comboBox_concreteClass.addItem("C50/60");
	
		
		JLabel lblNewLabel_description_steelParameters = new JLabel("parametry stali:");
		lblNewLabel_description_steelParameters.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_description_steelParameters.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_description_steelParameters.setBounds(40, 295, 120, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_description_steelParameters);
		
		JLabel lblNewLabel_description_fyk = new JLabel("granica plastyczno\u015Bci:");
		lblNewLabel_description_fyk.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_description_fyk.setBounds(40, 315, 120, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_description_fyk);
		
		JLabel lblNewLabel_description_Es = new JLabel("modu\u0142 spr\u0119\u017Cysto\u015Bci:");
		lblNewLabel_description_Es.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_description_Es.setBounds(40, 335, 120, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_description_Es);
		
		JLabel lblNewLabel_description_ys = new JLabel("wsp. bezpiecze\u0144stwa:");
		lblNewLabel_description_ys.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_description_ys.setBounds(40, 355, 140, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_description_ys);
		
		JLabel lblNewLabel_description_fiM = new JLabel("\u015Brednica zbr. g\u0142\u00F3wnego:");
		lblNewLabel_description_fiM.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_description_fiM.setBounds(40, 375, 132, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_description_fiM);
		
		JLabel lblNewLabel_description_fiS = new JLabel("\u015Brednica strzemion:");
		lblNewLabel_description_fiS.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_description_fiS.setBounds(40, 395, 140, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_description_fiS);
		
		JLabel lblNewLabel_symbol_fiS = new JLabel("\u03C6S =");
		lblNewLabel_symbol_fiS.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_symbol_fiS.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_symbol_fiS.setBounds(175, 395, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_symbol_fiS);
		
		JLabel lblNewLabel_symbol_fiM = new JLabel("\u03C6G =");
		lblNewLabel_symbol_fiM.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_symbol_fiM.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_symbol_fiM.setBounds(175, 375, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_symbol_fiM);
		
		JLabel lblNewLabel_symbol_ys = new JLabel("ys =");
		lblNewLabel_symbol_ys.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_symbol_ys.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_symbol_ys.setBounds(175, 355, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_symbol_ys);
		
		JLabel lblNewLabel_symbol_Es = new JLabel("Es =");
		lblNewLabel_symbol_Es.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_symbol_Es.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_symbol_Es.setBounds(175, 335, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_symbol_Es);
		
		JLabel lblNewLabel_unit_Es = new JLabel("[GPa]");
		lblNewLabel_unit_Es.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_unit_Es.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_unit_Es.setBounds(320, 335, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_unit_Es);
		
		JLabel lblNewLabel_unit_ys = new JLabel("[ - ]");
		lblNewLabel_unit_ys.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_unit_ys.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_unit_ys.setBounds(320, 355, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_unit_ys);
		
		JLabel lblNewLabel_unit_fiM = new JLabel("[mm]");
		lblNewLabel_unit_fiM.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_unit_fiM.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_unit_fiM.setBounds(320, 375, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_unit_fiM);
		
		JLabel lblNewLabel_unit_fiS = new JLabel("[mm]");
		lblNewLabel_unit_fiS.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_unit_fiS.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_unit_fiS.setBounds(320, 395, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_unit_fiS);
		
		textField_fiS = new JTextField();
		textField_fiS.setHorizontalAlignment(SwingConstants.CENTER);
		textField_fiS.setText("8");
		textField_fiS.setColumns(10);
		textField_fiS.setBounds(220, 395, 96, 19);
		frmConcreteCalculator.getContentPane().add(textField_fiS);
		
		textField_fiM = new JTextField();
		textField_fiM.setHorizontalAlignment(SwingConstants.CENTER);
		textField_fiM.setText("16");
		textField_fiM.setColumns(10);
		textField_fiM.setBounds(220, 375, 96, 19);
		frmConcreteCalculator.getContentPane().add(textField_fiM);
		
		textField_ys = new JTextField();
		textField_ys.setHorizontalAlignment(SwingConstants.CENTER);
		textField_ys.setText("1.15");
		textField_ys.setColumns(10);
		textField_ys.setBounds(220, 355, 96, 19);
		frmConcreteCalculator.getContentPane().add(textField_ys);
		
		textField_Es = new JTextField();
		textField_Es.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Es.setText("200");
		textField_Es.setColumns(10);
		textField_Es.setBounds(220, 335, 96, 19);
		frmConcreteCalculator.getContentPane().add(textField_Es);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(40, 290, 389, 2);
		frmConcreteCalculator.getContentPane().add(separator_1_1);
		
		JLabel lblNewLabel_symbol_fyk = new JLabel("fyk =");
		lblNewLabel_symbol_fyk.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_symbol_fyk.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_symbol_fyk.setBounds(175, 315, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_symbol_fyk);
		
		textField_fyk = new JTextField();
		textField_fyk.setHorizontalAlignment(SwingConstants.CENTER);
		textField_fyk.setText("500");
		textField_fyk.setColumns(10);
		textField_fyk.setBounds(220, 315, 96, 19);
		frmConcreteCalculator.getContentPane().add(textField_fyk);
		
		JLabel lblNewLabel_unit_fyk = new JLabel("[MPa]");
		lblNewLabel_unit_fyk.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_unit_fyk.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_unit_fyk.setBounds(320, 315, 40, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_unit_fyk);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBounds(40, 420, 389, 2);
		frmConcreteCalculator.getContentPane().add(separator_1_1_1);
		
		JLabel lblNewLabel_description_calculationResults = new JLabel("wyniki oblicze\u0144:");
		lblNewLabel_description_calculationResults.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_description_calculationResults.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_description_calculationResults.setBounds(40, 449, 120, 19);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_description_calculationResults);
		
		textPane_calculationResults = new JTextPane();
		textPane_calculationResults.setBounds(40, 471, 389, 82);
		frmConcreteCalculator.getContentPane().add(textPane_calculationResults);
		
		JLabel lblNewLabel_author = new JLabel("tw\u00F3rca programu: in\u017C. Jakub G\u00F3recki");
		lblNewLabel_author.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_author.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_author.setBounds(40, 10, 248, 13);
		frmConcreteCalculator.getContentPane().add(lblNewLabel_author);
		
	
		
	}
}
