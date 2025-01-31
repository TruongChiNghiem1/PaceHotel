package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.Employee_DAO;
import entity.Employee;

public class frmEmployeeManager extends JPanel implements ActionListener {
	private JLabel lblEmployeeID, lblFullName, lblGender, lblDOB, lblPhoneNo, lblSalary, lblPassword;
	private JTextField txtEmployeeID, txtFullName, txtDOB, txtPhoneNo, txtSalary, txtEmail, txtFind;
	private JComboBox<String> cbGender;
	private JButton btnFind, btnUpdate, btnClear, btnAdd, btnDelete, btnSave;
	private JTable tbEmployee;
	private DefaultTableModel model;
	private Employee_DAO employeeList;
	private int index = 1;

	public frmEmployeeManager() {
		setLayout(new BorderLayout());

		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

		JLabel lblTitle = new JLabel("Employee manager");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 25));

		lblTitle.setForeground(Color.WHITE);
		pnNorth.add(lblTitle);
		pnNorth.setBackground(new Color(23, 38, 47));

		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());

		JPanel pnC1 = new JPanel();
		pnC1.setLayout(null);
		pnC1.setPreferredSize(new Dimension(pnCenter.getWidth(), 120));
		pnC1.setBackground(new Color(166, 180, 184));

		pnC1.add(lblEmployeeID = new JLabel("EmployeeID:"));
		pnC1.add(txtEmployeeID = new JTextField());
		pnC1.add(lblFullName = new JLabel("Full Name:"));
		pnC1.add(txtFullName = new JTextField());
		pnC1.add(lblGender = new JLabel("Gender:"));
		pnC1.add(cbGender = new JComboBox<String>());
		cbGender.addItem("Nam");
		cbGender.addItem("Nữ");

		pnC1.add(lblDOB = new JLabel("DOB:"));
		pnC1.add(txtDOB = new JTextField());
		pnC1.add(lblPhoneNo = new JLabel("PhoneNo:"));
		pnC1.add(txtPhoneNo = new JTextField());
		pnC1.add(lblSalary = new JLabel("Salary:"));
		pnC1.add(txtSalary = new JTextField());
		pnC1.add(lblPassword = new JLabel("Email:"));
		pnC1.add(txtEmail = new JTextField());

		lblEmployeeID.setBounds(40, 15, 80, 20);
		txtEmployeeID.setBounds(lblEmployeeID.getX() + 80, lblEmployeeID.getY(), 140, 20);
		lblFullName.setBounds(lblEmployeeID.getX(), lblEmployeeID.getY() + 30, 80, 20);
		txtFullName.setBounds(lblFullName.getX() + 80, lblFullName.getY(), 310, 20);
		lblGender.setBounds(txtFullName.getX() + txtFullName.getWidth() + 20, txtFullName.getY(), 50, 20);
		cbGender.setBounds(lblGender.getX() + 60, lblGender.getY(), 120, 20);
		lblDOB.setBounds(cbGender.getX() + 150, cbGender.getY(), 50, 20);
		txtDOB.setBounds(lblDOB.getX() + 70, lblDOB.getY(), 140, 20);
		lblPhoneNo.setBounds(lblFullName.getX(), lblFullName.getY() + 30, lblFullName.getWidth(), 20);
		txtPhoneNo.setBounds(txtFullName.getX(), lblPhoneNo.getY(), txtFullName.getWidth(), 20);
		lblSalary.setBounds(lblGender.getX(), txtPhoneNo.getY(), lblGender.getWidth(), 20);
		txtSalary.setBounds(cbGender.getX(), lblSalary.getY(), cbGender.getWidth(), 20);
		lblPassword.setBounds(lblDOB.getX(), txtSalary.getY(), lblDOB.getWidth() + 20, 20);
		txtEmail.setBounds(txtDOB.getX(), lblPassword.getY(), txtDOB.getWidth(), 20);

		JPanel pnC2 = new JPanel();
		pnC2.setLayout(new BorderLayout());

		String[] cols = { "STT", "EmployeeID", "FullName", "DOB", "Gender", "PhoneNo", "Email", "Salary" };
		model = new DefaultTableModel(cols, 0);
		employeeList = new Employee_DAO();
		for (Employee e : employeeList.getAllEmployee()) {
			String gender = "";
			if (e.getGender() == 1) {
				gender = "Nữ";
			} else {
				gender = "Nam";
			}
			Object[] rowData = { index, e.getEmployeeID(), e.getFullName(), e.getDOB(), gender, e.getPhoneNo(),
					e.getEmail(), e.getSalary() };
			model.addRow(rowData);
			index++;
		}
		tbEmployee = new JTable(model);

		tbEmployee.getTableHeader().setBackground(new Color(228, 201, 139));

		JScrollPane pane = new JScrollPane(tbEmployee);

		pnC2.add(pane, BorderLayout.CENTER);

		JPanel pnC3 = new JPanel();
		pnC3.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 25));
		pnC3.setBackground(new Color(166, 180, 184));

		pnCenter.add(pnC1, BorderLayout.NORTH);
		pnCenter.add(pnC2, BorderLayout.CENTER);
		pnCenter.add(pnC3, BorderLayout.SOUTH);

		JPanel pnSouth = new JPanel();
		pnSouth.setLayout(new BorderLayout());
		pnSouth.setBackground(new Color(97, 118, 126));

		JPanel pnS1 = new JPanel();
		pnS1.setBackground(new Color(97, 118, 126));
		pnS1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));

		pnS1.add(txtFind = new JTextField(15));
		pnS1.add(btnFind = new JButton("Find"));

		JPanel pnS2 = new JPanel();
		pnS2.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 20));
		pnS2.setBackground(new Color(97, 118, 126));

		pnS2.add(btnAdd = new JButton("Add"));
		pnS2.add(btnClear = new JButton("Clear"));
		pnS2.add(btnUpdate = new JButton("Update"));
		pnS2.add(btnDelete = new JButton("Delete"));
		pnS2.add(btnSave = new JButton("Save"));
		btnSave.setVisible(false);
		btnFind.setBackground(new Color(0, 0, 162));
		btnUpdate.setBackground(new Color(255, 165, 0));
		btnClear.setBackground(new Color(30, 30, 30));
		btnAdd.setBackground(new Color(6, 111, 23));
		btnDelete.setBackground(new Color(197, 4, 4));

		Color White = Color.white;
		btnFind.setForeground(White);
		btnUpdate.setForeground(White);
		btnClear.setForeground(White);
		btnAdd.setForeground(White);
		btnDelete.setForeground(White);

		btnFind.setFocusable(false);
		btnUpdate.setFocusable(false);
		btnClear.setFocusable(false);
		btnAdd.setFocusable(false);
		btnDelete.setFocusable(false);

		pnSouth.add(pnS1, BorderLayout.WEST);
		pnSouth.add(pnS2, BorderLayout.EAST);

		add(pnNorth, BorderLayout.NORTH);
		add(pnCenter, BorderLayout.CENTER);
		add(pnSouth, BorderLayout.SOUTH);

		btnAdd.addActionListener(this);
		btnDelete.addActionListener(this);
		btnFind.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnClear.addActionListener(this);
		btnSave.addActionListener(this);

		tbEmployee.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				renderRowOfTable();
			}
		});
		
		dinhDangMaNhanVien();
		txtEmployeeID.setEditable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnFind)) {
			Find();
		} else if (e.getSource().equals(btnClear)) {
			Clear();
		} else if (e.getSource().equals(btnDelete)) {
			Delete();
		} else if (e.getSource().equals(btnAdd)) {
			if (validData()) {
				try {
					Add();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource().equals(btnUpdate)) {
			if (btnUpdate.getText().equals("Update")) {
				btnUpdate.setText("Cancel");
				btnUpdate.setBackground(new Color(126, 126, 126));
				btnSave.setVisible(true);
			} else {
				btnUpdate.setText("Update");
				btnUpdate.setBackground(new Color(255, 165, 0));
				btnSave.setVisible(false);
			}
		} else if (e.getSource().equals(btnSave)) {
			try {
				Update();
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void showMessage(String txt, JTextField focus) {
		JOptionPane.showMessageDialog(null, txt);
		focus.requestFocus();
	}

	private boolean validData() {
		String EmID = txtEmployeeID.getText().trim();
		String Name = txtFullName.getText().trim();
		String DOB = txtDOB.getText().trim();
		String phone = txtPhoneNo.getText().trim();
		String email = txtEmail.getText().trim();
		String salary = txtSalary.getText().trim();

		if (!(EmID.length() > 0 && EmID.matches("E\\d{3}"))) {
			showMessage("Mã nhân viên phải là E + d(3)", txtEmployeeID);
			return false;
		}
		if (!(Name.length() > 0 && Name.matches(
				"[a-zA-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ']+"))) {
			showMessage("Họ tên nhân viên phải là chữ hoa hoặc chữ thường và không chứa các ký tự đặc biệt",
					txtFullName);
			return false;
		}
		if (!(DOB.length() > 0 && DOB.matches("\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])*"))) {
			showMessage("Ngày sinh phải đúng định dạng yyyy-mm-dd", txtDOB);
			return false;
		}
		if (!(phone.length() > 0 && phone.matches("\\d{10}"))) {
			showMessage("Số điện thoại phải là 10 số", txtPhoneNo);
			return false;
		}

		if (!(email.length() > 0 && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))) {
			showMessage("Vui lòng nhập đúng định dạng email", txtEmail);
			return false;
		}
		if (!(salary.length() > 0 && salary.matches("[0-9']+"))) {
			showMessage("lương phải là số", txtSalary);
			return false;
		}

		return true;
	}

	public void Add() throws ParseException {
		String EmID = txtEmployeeID.getText();
		String Name = txtFullName.getText();
		String DOB = txtDOB.getText();
		String gender = cbGender.getSelectedItem().toString();
		String phone = txtPhoneNo.getText();
		String email = txtEmail.getText();
		String salary = txtSalary.getText();
		int gd = 0;
		if (gender.equalsIgnoreCase("Nữ")) {
			gd = 1;
		}
		Employee s = new Employee(EmID, Name, DOB, gd, phone, email, "123", Double.parseDouble(salary), "2");
		if (employeeList.addEmployee(s) != -1) {
			Object[] row = { index, s.getEmployeeID(), s.getFullName(), s.getDOB(), gender, s.getPhoneNo(),
					s.getEmail(), s.getSalary() };
			JOptionPane.showMessageDialog(null, "Employee has been successfully added !");
			model.addRow(row);
			index++;
			dinhDangMaNhanVien();
		} else {
			JOptionPane.showMessageDialog(null, "Error! ID already exists.");
		}
		dinhDangMaNhanVien();
	}

	public void Clear() {
		txtFullName.setText("");
		txtEmail.setText("");
		txtDOB.setText("");
		txtSalary.setText("");
		txtPhoneNo.setText("");
		cbGender.setSelectedIndex(0);
	}

	public void Delete() {
		int r = tbEmployee.getSelectedRow();
		if (r != -1) {
			int notice = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa nhân viên này chứ?", "Delete",
					JOptionPane.YES_NO_OPTION);
			if (notice == JOptionPane.YES_OPTION) {
				employeeList.deleteEmployee(model.getValueAt(r, 1).toString());
				model.removeRow(r);
				Clear();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng cần xóa!");
		}
	}

	public void Find() {
		String IDFind = txtFind.getText();
		if (employeeList.findEmployee(IDFind) == -1) {
			JOptionPane.showMessageDialog(null, "ID does not exist !");
		} else {
			tbEmployee.changeSelection(employeeList.findEmployee(IDFind), 0, false, false);
			renderRowOfTable();
		}
	}

	public void Update() throws HeadlessException, ParseException {
		String IDUpdate = txtEmployeeID.getText();
		String newName = txtFullName.getText();
		String DOB = txtDOB.getText();
		String gender = cbGender.getSelectedItem().toString();
		int gd = 0;
		if (gender.equalsIgnoreCase("Nữ")) {
			gd = 1;
		}
		String PhoneNo = txtPhoneNo.getText();
		String Email = txtEmail.getText();
		String Salary = txtSalary.getText();

		int notice = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this row?", "Delete",
				JOptionPane.YES_NO_OPTION);
		if (notice == JOptionPane.YES_OPTION) {
			if (employeeList.updateEmployee(IDUpdate, newName, DOB, gd, PhoneNo, Email, Salary) != -1) {
				JOptionPane.showMessageDialog(null, "Update Success!");
				renderRowOfTable();
			} else {
				JOptionPane.showMessageDialog(null, "ID does not exist !");

			}
		}
	}

	public void renderRowOfTable() {
		int r = tbEmployee.getSelectedRow();
//		txtEmployeeID.setText(model.getValueAt(r, 1).toString());
		txtFullName.setText(model.getValueAt(r, 2).toString());
		txtDOB.setText(model.getValueAt(r, 3).toString());
		if (model.getValueAt(r, 4).toString().equalsIgnoreCase("Nam")) {
			cbGender.setSelectedIndex(0);
		} else {
			cbGender.setSelectedIndex(1);
		}
		txtPhoneNo.setText(model.getValueAt(r, 5).toString());
		txtEmail.setText(model.getValueAt(r, 6).toString());
		txtSalary.setText(model.getValueAt(r, 7).toString());
	}
	
	private void dinhDangMaNhanVien()
	{
		String maNhanVien = "";
		maNhanVien += "E";
		if (String.valueOf(employeeList.layMaNVLonNhat()).length() == 2)
		{
			maNhanVien += "0";
		}
		
		else if (String.valueOf(employeeList.layMaNVLonNhat()).length() == 1)
		{
			maNhanVien += "00";
		}
		maNhanVien += String.valueOf(employeeList.layMaNVLonNhat() + 1);
		txtEmployeeID.setText(maNhanVien);
	}
	
}
