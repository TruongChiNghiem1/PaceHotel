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
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.ServiceIDao;
import entity.Service;
import service.Service_DAO;

public class frmServiceManager extends JFrame implements ActionListener, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7732602064944188757L;
	private JLabel pServiceID, pServiceName, pPrice, pTitle;
	private JTextField inputServiceID, inputServiceName, inputPrice, inputFind;
	private JButton btnAdd, btnClear, btnUpdate, btnDelete, btnFind, btnSave;
	private JTable tbService;
	private DefaultTableModel model;
	private JPanel sectionBorder;
	private ServiceIDao serviceList;
	private int index = 1;

	public frmServiceManager(Registry registry) throws RemoteException, NotBoundException {
		setTitle("Service manager");
//		setDefaultCloseOperation(frmRoomManage.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setLayout(new BorderLayout());

		sectionBorder = new JPanel();
		sectionBorder.setLayout(new BorderLayout());
		add(sectionBorder);

		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

		pTitle = new JLabel("Service manager");
		pTitle.setFont(new Font("Arial", Font.BOLD, 25));

		pTitle.setForeground(Color.WHITE);
		pnNorth.add(pTitle);
		pnNorth.setBackground(new Color(23, 38, 47));

		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());

		JPanel pnC1 = new JPanel();
		pnC1.setLayout(null);
		pnC1.setPreferredSize(new Dimension(pnCenter.getWidth(), 90));
		pnC1.setBackground(new Color(166, 180, 184));

		pnC1.add(pServiceID = new JLabel("Service ID:"));
		pnC1.add(inputServiceID = new JTextField());
		pnC1.add(pServiceName = new JLabel("Service Name:"));
		pnC1.add(inputServiceName = new JTextField());
		pnC1.add(pPrice = new JLabel("Price:"));
		pnC1.add(inputPrice = new JTextField());

		pServiceID.setBounds(580, 30, 120, 20);
		inputServiceID.setBounds(pServiceID.getX() + 80, pServiceID.getY(), 150, 20);

		pServiceName.setBounds(pServiceID.getX() + 250, pServiceID.getY(), 130, 20);
		inputServiceName.setBounds(pServiceName.getX() + 120, pServiceID.getY(), 230, 20);

		pPrice.setBounds(pServiceName.getX() + 370, pServiceID.getY(), 50, 20);
		inputPrice.setBounds(pPrice.getX() + 50, pServiceID.getY(), 100, 20);

		JPanel pnC2 = new JPanel();

		String[] cols = { "STT", "Service ID", "Service Name", "Price" };
		model = new DefaultTableModel(cols, 0);
		serviceList = (ServiceIDao) registry.lookup("serviceIDao");
		for (Service s : serviceList.getAllService()) {
			Object[] service = { index, s.getServiceID(), s.getServiceName(), s.getPrice() };
			model.addRow(service);
			index++;
		}
		tbService = new JTable(model);

		tbService.getTableHeader().setBackground(new Color(228, 201, 139));

		JScrollPane pane = new JScrollPane(tbService, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setPreferredSize(new Dimension(1100, 760));
		pnC2.add(pane);

		pnCenter.add(pnC1, BorderLayout.NORTH);
		pnCenter.add(pnC2, BorderLayout.CENTER);

		JPanel pnSouth = new JPanel();
		pnSouth.setLayout(new BorderLayout());
		pnSouth.setBackground(new Color(97, 118, 126));

		JPanel pnSouthR = new JPanel();
		pnSouthR.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 20));
		pnSouthR.setBackground(new Color(97, 118, 126));

		JPanel pnSouthL = new JPanel();
		pnSouthL.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
		pnSouthL.setBackground(new Color(97, 118, 126));

		pnSouthL.add(inputFind = new JTextField(30));
		pnSouthL.add(btnFind = new JButton("Find"));

		pnSouthR.add(btnAdd = new JButton("Add"));
		pnSouthR.add(btnDelete = new JButton("Delete"));
		pnSouthR.add(btnUpdate = new JButton("Update"));
		pnSouthR.add(btnClear = new JButton("Clear"));
		pnSouthR.add(btnSave = new JButton("Save"));
		btnSave.setVisible(false);
//		inputServiceID.setEditable(false);
		pnSouth.add(pnSouthL, BorderLayout.WEST);
		pnSouth.add(pnSouthR, BorderLayout.EAST);

		sectionBorder.add(pnNorth, BorderLayout.NORTH);
		sectionBorder.add(pnCenter, BorderLayout.CENTER);
		sectionBorder.add(pnSouth, BorderLayout.SOUTH);

		btnClear.setFocusable(false);
		btnUpdate.setFocusable(false);
		btnAdd.setFocusable(false);
		btnDelete.setFocusable(false);

		btnClear.setBackground(new Color(0, 0, 0));
		btnClear.setForeground(Color.white);
		btnUpdate.setBackground(new Color(255, 165, 0));
		btnUpdate.setForeground(Color.white);
		btnAdd.setBackground(new Color(6, 111, 23));
		btnAdd.setForeground(Color.white);
		btnDelete.setBackground(new Color(197, 4, 4));
		btnDelete.setForeground(Color.white);
		btnFind.setBackground(new Color(0, 0, 162));
		btnFind.setForeground(Color.white);

		btnAdd.addActionListener(this);
		btnFind.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnClear.addActionListener(this);
		btnDelete.addActionListener(this);
		btnSave.addActionListener(this);

		tbService.addMouseListener(new MouseListener() {

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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAdd)) {
			if(validData()) {
				try {
					Add();
				} catch (HeadlessException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (e.getSource().equals(btnFind)) {
			try {
				Find();
			} catch (HeadlessException | RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource().equals(btnClear)) {
			Clear();
		} else if (e.getSource().equals(btnDelete)) {
			try {
				Delete();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
			if(validData()) {
				try {
					Update();
				} catch (HeadlessException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	public void renderRowOfTable() {
		int r = tbService.getSelectedRow();
		inputServiceID.setText(model.getValueAt(r, 1).toString());
		inputServiceName.setText(model.getValueAt(r, 2).toString());
		inputPrice.setText(model.getValueAt(r, 3).toString());
	}
	
	private void showMessage(String txt, JTextField focus) {
		JOptionPane.showMessageDialog(null, txt);
		focus.requestFocus();
	}

	private boolean validData() {
		String ServiceID = inputServiceID.getText().trim();
		String ServiceName = inputServiceName.getText().trim();
		String Price = inputPrice.getText().trim();

		if (!(ServiceID.length() > 0 && ServiceID.matches("S\\d{3}"))) {
			showMessage("Mã dịch vụ phải là S + d(3)", inputServiceID);
			return false;
		}
		if (!(ServiceName.length() > 0 && ServiceName.matches(
				"[a-zA-Z0-9 ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ']+"))) {
			showMessage("dịch vụ phải là số, chữ hoa hoặc chữ thường và không chứa các ký tự đặc biệt",
					inputServiceName);
			return false;
		}
		if (!(Price.length() > 0 && Price.matches("[0-9']+"))) {
			showMessage("Tiền là số", inputPrice);
			return false;
		}

		return true;
	}

	public void Add() throws HeadlessException, RemoteException {
		String ServiceID = inputServiceID.getText();
		String ServiceName = inputServiceName.getText();
		String Price = inputPrice.getText();

		Service s = new Service(ServiceID, ServiceName, Double.parseDouble(Price), null);
		if (serviceList.addService(s) != 0) {
			Object[] row = { index, s.getServiceID(), s.getServiceName(), s.getPrice() };
			model.addRow(row);
			index++;
		} else {
			JOptionPane.showMessageDialog(null, "Error! ID already exists.");
		}
	}

	public void Find() throws HeadlessException, RemoteException {
		String IDFind = inputFind.getText();
		if (serviceList.findService(IDFind) == -1) {
			JOptionPane.showMessageDialog(null, "ID does not exist !");
		} else {
			tbService.changeSelection(serviceList.findService(IDFind), 0, false, false);
			renderRowOfTable();
		}
	}

	public void Delete() throws RemoteException {
		int r = tbService.getSelectedRow();
		if (r != -1) {
			int notice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this row?", "Delete",
					JOptionPane.YES_NO_OPTION);
			if (notice == JOptionPane.YES_OPTION) {
				serviceList.deleteService(model.getValueAt(r, 1).toString());
				model.removeRow(r);
				Clear();
			}
		} else {
			JOptionPane.showMessageDialog(null,"Select the row to delete!");
		}
	}

	public void Update() throws HeadlessException, RemoteException {
		String IDUpdate = inputServiceID.getText();
		String NewName = inputServiceName.getText();
		double Price = Double.parseDouble(inputPrice.getText());
		int notice = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this row?", "Delete",
				JOptionPane.YES_NO_OPTION);
		if (notice == JOptionPane.YES_OPTION) {
			if (serviceList.updateService(IDUpdate, NewName, Price) != -1) {
				JOptionPane.showMessageDialog(null, "Update Success!");
				renderRowOfTable();
			} else {
				JOptionPane.showMessageDialog(null, "ID does not exist !");

			}
		}
	}

	public void Clear() {
		inputServiceID.setText("");
		inputServiceName.setText("");
		inputPrice.setText("");
	}
}
