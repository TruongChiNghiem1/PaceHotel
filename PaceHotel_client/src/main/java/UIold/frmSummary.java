package UIold;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class frmSummary extends JPanel implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -526133524363696988L;
	private JLabel lblEmployeeID, lblFullName, lblGender, lblPhoneNo, lblLogintime, lblLogouttime, lbTotal;
	private JTextField txtEmployeeID, txtEmployeeName, txtPhoneNo, txtGender, txtTotal, txtLogin, txtLogout;
	private JButton  btnCancel, btnEndShift;
	private JTable tbService;
	private DefaultTableModel model;
	
	public frmSummary(Registry registry)
	{
		setLayout(new BorderLayout());
		
		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		
		JLabel lblTitle = new JLabel("Summary");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 25));
		
		lblTitle.setForeground(Color.BLACK);
		pnNorth.add(lblTitle);
		pnNorth.setBackground(new Color(97,118,126));
		
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		
		JPanel pnC1 = new JPanel();
		pnC1.setLayout(null);
		pnC1.setPreferredSize(new Dimension(pnCenter.getWidth(), 90));
		pnC1.setBackground(new Color(166,180,184));
		
		pnC1.add(lblEmployeeID = new JLabel("Employee ID:"));
		pnC1.add(txtEmployeeID = new JTextField());
		pnC1.add(lblFullName = new JLabel("Full Name:"));
		pnC1.add(txtEmployeeName = new JTextField());
		pnC1.add(lblGender = new JLabel("Gender: "));
		pnC1.add(txtGender = new JTextField());
		pnC1.add(lblPhoneNo = new JLabel("PhoneNo: "));
		pnC1.add(txtPhoneNo = new JTextField());
		pnC1.add(lblLogintime = new JLabel("Login time: "));
		pnC1.add(txtLogin = new JTextField());
		pnC1.add(lblLogouttime = new JLabel("Logout time: "));
		pnC1.add(txtLogout = new JTextField());
		pnC1.add(lbTotal = new JLabel("Total: "));
		
		lblEmployeeID.setBounds(15, 10, 80, 20);
		txtEmployeeID.setBounds(lblEmployeeID.getX() + 80, lblEmployeeID.getY(), 120, 20);
		lblFullName.setBounds(lblEmployeeID.getX()+ 250,10, 130, 20);
		txtEmployeeName.setBounds(lblFullName.getX()+ 70 , 10, 200, 20);
		lblGender.setBounds(txtEmployeeName.getX() + txtEmployeeName.getWidth() + 20, txtEmployeeName.getY(), 50, 20);
		txtGender.setBounds(lblGender.getX() + 50, lblGender.getY(), 100, 20);
		lblPhoneNo.setBounds(lblPhoneNo.getX() + 750 , 10, 80, 20);
		txtPhoneNo.setBounds(lblPhoneNo.getX()+ txtPhoneNo.getX() + 60, 10, 250, 20);
		lblLogintime.setBounds(15, 50, 80, 20);
		txtLogin.setBounds(lblEmployeeID.getX() + 80 , 50, 80, 20);
		lblLogouttime.setBounds(lblEmployeeID.getX()+ 250, 50, 80, 20);
		txtLogout.setBounds(lblFullName.getX()+ 90, 50, 80, 20);
		
		JPanel pnC2 = new JPanel();
		pnC2.setLayout(new BorderLayout());
		
		String[] cols = {"STT", "Guest Name", "Payment Date", "Discount", "Total", "Note"};
		model = new DefaultTableModel(cols, 0);
		tbService = new JTable(model);
		
		tbService.getTableHeader().setBackground(new Color(228,201,139));
		
		JScrollPane pane = new JScrollPane(tbService);
		
		pnC2.add(pane, BorderLayout.CENTER);
		
		JPanel pnC3 = new JPanel();
		pnC3.setLayout(new FlowLayout(FlowLayout.RIGHT,5, 15));
		pnC3.setBackground(new Color(166,180,184));
		pnC3.add(lbTotal);
		pnC3.add(txtTotal = new JTextField(10));
		
		pnCenter.add(pnC1, BorderLayout.NORTH);
		pnCenter.add(pnC2, BorderLayout.CENTER);
		pnCenter.add(pnC3, BorderLayout.SOUTH);
		
		JPanel pnSouth = new JPanel();
		pnSouth.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 20));
		pnSouth.setBackground(new Color(97,118,126));
		
		pnSouth.add(btnCancel = new JButton("Cancel"));
		pnSouth.add(btnEndShift = new JButton("End Shift"));
		
		add(pnNorth, BorderLayout.NORTH);
		add(pnCenter, BorderLayout.CENTER);
		add(pnSouth, BorderLayout.SOUTH);
		
		btnCancel.setFocusable(false);
		btnEndShift.setFocusable(false);
		
		btnCancel.setBackground(new Color(126,126,126));
		btnCancel.setForeground(Color.white);
		btnEndShift.setBackground(new Color(255,165,0));
		btnEndShift.setForeground(Color.white);
		
		txtTotal.setEditable(false);
	}
}
