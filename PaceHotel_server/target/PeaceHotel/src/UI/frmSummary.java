package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dao.Booking_DAO;
import dao.Employee_DAO;
import entity.Employee;
import entity.Service;

public class frmSummary extends JPanel implements ActionListener{
	private JPanel pnlStatistics,pnlFunction,pnlTable,pnlImport;
	private JLabel lblStatistics,lblEmployeeName,lblRoomType,lblFromDate,lblToDate;
	private JTextField txtEmployeeName,txtRoomType,txtFromDate,txtFind,txtToDate;
	private JButton btnFilter,btnFind;
	private JTable tbStatistics;
	private DefaultTableModel model;
	private JComboBox<String> cbEmployeeName;
	private Employee_DAO employeeList;
	private Booking_DAO bookingList;
	public frmSummary() {
		employeeList = new Employee_DAO();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		pnlStatistics = new JPanel();
		pnlStatistics.setBounds(0, 0, screenSize.width-300, 70);
		pnlStatistics.setBackground(new Color(97,118,126)); 
		lblStatistics = new JLabel("Statistics");
		lblStatistics.setFont(new Font("Arial", Font.BOLD, 25));
		lblStatistics.setBounds((screenSize.width-300)/2-100, 20, 200, 30);
		
		pnlTable = new JPanel();
		pnlTable.setBounds(0, 70, screenSize.width-300, screenSize.height-220);
		pnlImport = new JPanel();
		pnlTable.setBackground(new Color(166,180,184));
		lblEmployeeName = new JLabel("Employee Name:");
		pnlImport.add(cbEmployeeName = new JComboBox<String>());
		
		for (Employee e : employeeList.getAllEmployee()) {
			cbEmployeeName.addItem(e.getEmployeeID() + " - " + e.getFullName());
		}
		
		lblFromDate = new JLabel("From Date:");
		pnlImport.add(lblFromDate ,BorderLayout.CENTER);
		txtFromDate = new JTextField(10);
		pnlImport.add(txtFromDate,BorderLayout.CENTER);
		lblToDate = new JLabel("To Date:");
		pnlImport.add(lblToDate ,BorderLayout.CENTER);
		txtToDate = new JTextField(10);
		pnlImport.add(txtToDate,BorderLayout.CENTER);
		btnFilter = new JButton("Filter");
		btnFilter.setPreferredSize(new Dimension(80, 30));
		btnFilter.setBackground(new Color(0, 0, 0));
		btnFilter.setForeground(Color.white);
		btnFilter.setBorder(null);
		pnlImport.add(btnFilter,BorderLayout.CENTER);
		pnlImport.setBackground(new Color(166,180,184));
		pnlImport.setBounds(0, 30, screenSize.width-300, 50);
		
		String[] tieuDe = {"STT","Room Type","Room ID","Check in","CheckOut","GuestName","Total"};
		model = new DefaultTableModel(tieuDe, 0);
		JScrollPane sc = new JScrollPane();
		sc.setViewportView(tbStatistics = new JTable(model));
		tbStatistics.getTableHeader().setBackground(new Color(228,201,139));
		sc.setBounds(20, 100, screenSize.width-340, 500);
		
		
		
		pnlFunction = new JPanel();
		pnlFunction.setBackground(new Color(97,118,126)); 
		pnlFunction.setBounds(0, screenSize.height-150, screenSize.width-300, 80);
		txtFind = new JTextField();
		txtFind.setBounds(20, 25, 150, 30);
		txtFind.setBorder(null);
		btnFind = new JButton("Find");
		btnFind.setBounds(190, 25, 70, 30);
		btnFind.setBackground(new Color(0, 0, 162));
		btnFind.setForeground(Color.white);
		btnFind.setBorder(null);

		
		add(pnlStatistics);
		pnlStatistics.add(lblStatistics);
		add(pnlFunction);
		pnlFunction.add(txtFind);
		pnlFunction.add(btnFind);
		add(pnlTable);
		pnlTable.add(pnlImport);
		pnlTable.add(sc);
		
		setLayout(null);
		pnlStatistics.setLayout(null);
		pnlTable.setLayout(null);
		pnlFunction.setLayout(null);
		
		btnFilter.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnFilter)) {
			try {
				if(validData()) {
					filter();
				}
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void filter() throws ParseException {
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
		
		bookingList = new Booking_DAO();
		
		String employeeIDName = cbEmployeeName.getSelectedItem().toString();
		String employeeID = employeeIDName.substring(0, 4);
		String fromDate = txtFromDate.getText();
		String toDate = txtToDate.getText();
		System.out.println(employeeID);
		
		int index = 1;
		if(fromDate.equalsIgnoreCase("") && toDate.equalsIgnoreCase("")) {
			fromDate = "1970-01-01 00:00:00";
			LocalDateTime current = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			toDate = current.format(formatter);
			
			for (Object[] o : bookingList.filterStatistics(employeeID, fromDate, toDate)) {
				Object[] rowData = {index, o[0], o[1], o[2], o[3], o[4], o[5]};
				model.addRow(rowData);
				index++;
			}
		} else if (!fromDate.equalsIgnoreCase("") && toDate.equalsIgnoreCase("")) {
			fromDate += " 00:00:00";
			LocalDateTime current = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			toDate = current.format(formatter);
			for (Object[] o : bookingList.filterStatistics(employeeID, fromDate, toDate)) {
				Object[] rowData = {index, o[0], o[1], o[2], o[3], o[4], o[5]};
				model.addRow(rowData);
				index++;
			}
		} else if (fromDate.equalsIgnoreCase("") && !toDate.equalsIgnoreCase("")) {
			fromDate = "1970-01-01 00:00:00";
			toDate += " 23:59:59";
			for (Object[] o : bookingList.filterStatistics(employeeID, fromDate, toDate)) {
				Object[] rowData = {index, o[0], o[1], o[2], o[3], o[4], o[5]};
				model.addRow(rowData);
				index++;
			}
		} else {
			for (Object[] o : bookingList.filterStatistics(employeeID, fromDate, toDate)) {
				Object[] rowData = {index, o[0], o[1], o[2], o[3], o[4], o[5]};
				model.addRow(rowData);
				index++;
			}
		}
		
		
	}
	
	private void showMessage(String txt, JTextField focus) {
		JOptionPane.showMessageDialog(null, txt);
		focus.requestFocus();
	}

	private boolean validData() {
		String FromDate = txtFromDate.getText();
		String ToDate = txtToDate.getText();
		
		if(!FromDate.equalsIgnoreCase("")) {
			if (!(FromDate.length() > 0 && FromDate.matches("\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])*"))) {
				showMessage("Ngày bắt đầu phải đúng định dạng yyyy-mm-dd", txtFromDate);
				return false;
			}
		}
		if(!ToDate.equalsIgnoreCase("")) {
			if (!(ToDate.length() > 0 && ToDate.matches("\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])*"))) {
				showMessage("Ngày kết thúc phải đúng định dạng yyyy-mm-dd", txtToDate);
				return false;
			}
		}
		
		return true;
	}

}
