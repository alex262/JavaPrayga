package main;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class MainWindow {

	private JFrame frame;
	public static JButton GetDataTerracot18;
	public static JLabel labelLoad;
	public static JLabel lblLoadText;
	public static MainBase BaseTerracot;
	public static boolean flCreateBase=false;
	public static JTable tableBase;
	/**
	
	/**
	 * Launch the privaprivate JLabel label;
	/**
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public MainWindow() throws IOException {
		initialize();
	}
	
	//-------------------------------------------------
	// Загрузка базы
	public static void LoadBaseTerrakot18() {
		lblLoadText.setText("Подождите идет загрузка данных...");
		lblLoadText.setVisible(true);
		GetDataTerracot18.setEnabled(false);
		labelLoad.setVisible(true);
		try {
			BaseTerracot.CreateBase(tableBase);
		} catch (IOException e) {
			e.printStackTrace();
		}
		flCreateBase = false;
		GetDataTerracot18.setEnabled(true);
		labelLoad.setVisible(false);
		lblLoadText.setVisible(false);
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		BaseTerracot = new MainBase(); 
		frame = new JFrame();
		frame.setTitle("\u0411\u0430\u0437\u0430 \u043F\u0440\u044F\u0436\u0438");
		frame.setBounds(100, 100, 868, 607);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		new Thread(new MainThread(this)).start();
		//------------------------------------------------------------------------------------------------------------------------------------------------------		
		// запрос данных с сайта		
		GetDataTerracot18 = new JButton("\u0417\u0430\u043F\u0440\u043E\u0441\u0438\u0442\u044C \u0434\u0430\u043D\u043D\u044B\u0435 \u0441 \u0441\u0430\u0439\u0442\u0430 http://www.terrakot18.ru");
		GetDataTerracot18.setIcon(new ImageIcon(MainWindow.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		GetDataTerracot18.setBackground(Color.ORANGE);
		GetDataTerracot18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				//DefaultTableModel model = (DefaultTableModel) tableBase.getModel();
				//Vector <String> v = new Vector<String>(3);
				//v.add("Василий");
				//v.add("Пупкин");
				//v.add("1");
				//model.addRow(v);
			  
			    if(flCreateBase == false){
					flCreateBase = true;
				}else
				{
					JOptionPane.showMessageDialog(frame,"Подождите идет выгрузка базы","Ошибка!!!",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GetDataTerracot18.setBounds(10, 535, 356, 23);
		frame.getContentPane().add(GetDataTerracot18);
		
		labelLoad = new JLabel("");
		labelLoad.setIcon(new ImageIcon(MainWindow.class.getResource("/main/res/ajax_loader.gif")));
		labelLoad.setBounds(356, 220, 100, 100);
		frame.getContentPane().add(labelLoad);
		
		lblLoadText = new JLabel("Text");
		lblLoadText.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoadText.setBounds(385, 544, 295, 14);
		frame.getContentPane().add(lblLoadText);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 832, 514);
		frame.getContentPane().add(scrollPane);
		
		tableBase = new JTable();
		tableBase.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u2116", "\u041F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0438\u0442\u0435\u043B\u044C", "\u041F\u0440\u044F\u0436\u0430", "\u0426\u0432\u0435\u0442", "\u041D\u0430\u043B\u0438\u0447\u0438\u0435", "\u0426\u0435\u043D\u0430"
			}
		));
		tableBase.getColumnModel().getColumn(5).setMaxWidth(2147483592);
		scrollPane.setViewportView(tableBase);
		
		
		//Vector <String> v = new Vector<String>(4);
		//----------------------------------------------------
		lblLoadText.setVisible(false);
		labelLoad.setVisible(false);
		
		
	}
}
