package UIold;

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
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.RoomIDao;
import dao.RoomTypeIDao;
import entity.Room;
import entity.RoomType;

public class frmRoomManage extends JFrame implements ActionListener, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1555877702877333228L;
	private JLabel pRoomID, pRoomType, pPeople, pStatus, pTitle;
	private JTextField inputRoomID, inputRoomType, inputStatus, inputFind;
	private JButton btnAdd, btnUpdate, btnClear, btnSave, btnDelete, btnFind;
	private JComboBox<String> cbRoomType, cbRoomStatus;
	private JTable tbService;
	private DefaultTableModel model;
	private JPanel sectionBorder;
	private int index = 1;
	private RoomIDao roomList;
	private RoomTypeIDao roomTypeList;
	private Object[] lastRoom;

	public frmRoomManage(Registry registry) throws AccessException, RemoteException, NotBoundException {
		setTitle("Room manager");
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setLayout(new BorderLayout());

		sectionBorder = new JPanel();
		sectionBorder.setLayout(new BorderLayout());
		add(sectionBorder);

		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

		pTitle = new JLabel("Room manager");
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

		pnC1.add(pRoomID = new JLabel("Room ID:"));
		pnC1.add(inputRoomID = new JTextField());
		pnC1.add(pRoomType = new JLabel("Room Type:"));
		pnC1.add(cbRoomType = new JComboBox<>());
		roomTypeList = (RoomTypeIDao) registry.lookup("roomTypeIDao");
		for (RoomType rt : roomTypeList.getAllRoomType()) {
			cbRoomType.addItem(rt.getRoomType());
		}
		pnC1.add(pStatus = new JLabel("Status:"));
		pnC1.add(cbRoomStatus = new JComboBox());
		cbRoomStatus.addItem("Ready");
		cbRoomStatus.addItem("Occupied");
		cbRoomStatus.addItem("Dirty");
		cbRoomStatus.addItem("Ordered");

		pRoomID.setBounds(300, 30, 60, 20);
		inputRoomID.setBounds(pRoomID.getX() + 50, pRoomID.getY(), 150, 20);

		pRoomType.setBounds(pRoomID.getX() + 250, pRoomID.getY(), 130, 20);
		cbRoomType.setBounds(pRoomType.getX() + 80, pRoomID.getY(), 230, 20);

		pStatus.setBounds(pRoomType.getX() + 370, pRoomID.getY(), 50, 20);
		cbRoomStatus.setBounds(pStatus.getX() + 50, pRoomID.getY(), 100, 20);
		
		JPanel pnC2 = new JPanel();
		String[] cols = { "STT", "Room ID", "Room Type", "People", "Bed", "First Hour Fee", "Second Hour Fee",
				"Overnight", "Status" };
		model = new DefaultTableModel(cols, 0);
		roomList = (RoomIDao) registry.lookup("roomIDao");

		for (Object[] o : roomList.getAllInfoRoom()) {
			Object[] rowData = { index, o[0], o[1], o[2], o[3], o[4], o[5], o[6], o[7] };
			model.addRow(rowData);
			index++;
		}

		tbService = new JTable(model);

		tbService.getTableHeader().setBackground(new Color(228, 201, 139));

		JScrollPane pane = new JScrollPane(tbService, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setPreferredSize(new Dimension(1400, 760));
		pnC2.add(pane);

		pnCenter.add(pnC1, BorderLayout.NORTH);
		pnCenter.add(pnC2, BorderLayout.CENTER);

		JPanel pnSouth = new JPanel();
//		pnSouth.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 20));
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
		pnSouth.add(pnSouthL, BorderLayout.WEST);
		pnSouth.add(pnSouthR, BorderLayout.EAST);

		sectionBorder.add(pnNorth, BorderLayout.NORTH);
		sectionBorder.add(pnCenter, BorderLayout.CENTER);
		sectionBorder.add(pnSouth, BorderLayout.SOUTH);

		btnUpdate.setFocusable(false);
		btnClear.setFocusable(false);
		btnSave.setFocusable(false);
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
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent e) {
				try {
					renderRowOfTable();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
	
	public void renderRowOfTable() throws RemoteException {
		int r = tbService.getSelectedRow();
		inputRoomID.setText(model.getValueAt(r, 1).toString());
		for (Object[] o : roomList.getAllInfoRoom()) {
			if(o[1].equals(model.getValueAt(r, 2))) {
				cbRoomType.setSelectedItem(o[1]);
			}
			if(o[7].equals(model.getValueAt(r, 8))) {
				cbRoomStatus.setSelectedItem(o[7]);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAdd)) {
			if(validData()) {
				Add();
			}
		} else if (e.getSource().equals(btnFind)) {
			Find();
		} else if (e.getSource().equals(btnClear)) {
			Clear();
		} else if (e.getSource().equals(btnDelete)) {
			Delete();
		} else if (e.getSource().equals(btnUpdate)) {
			if (btnUpdate.getText().equals("Update")) {
				btnUpdate.setText("Cancle");
				btnUpdate.setBackground(new Color(126, 126, 126));
				btnSave.setVisible(true);
			} else {
				btnUpdate.setText("Update");
				btnUpdate.setBackground(new Color(255, 165, 0));
				btnSave.setVisible(false);
			}
		} else if (e.getSource().equals(btnSave)) {
			if(validData()) {
				Update();
			}
		}

	}
	
	private void showMessage(String txt, JTextField focus) {
		JOptionPane.showMessageDialog(null, txt);
		focus.requestFocus();
	}
	
	private boolean validData() {
		String roomID = inputRoomID.getText().trim();

		if (!(roomID.length() > 0 && roomID.matches("^[1-9]\\d{2}"))) {
			showMessage("Mã dịch vụ phải là S + d(3)", inputRoomID);
			return false;
		}

		return true;
	}

	public void Add() {
		String roomID = inputRoomID.getText();
		String roomType = cbRoomType.getSelectedItem().toString();
		String roomStatus = cbRoomStatus.getSelectedItem().toString();
		Room newRoom = new Room(roomID, roomType, roomStatus);
		if (roomList.addRoom(newRoom) != 0) {
			Object[] o = roomList.getLastRoom(roomID);
			Object[] row = { index, o[0], o[1], o[2], o[3], o[4], o[5], o[6], o[7] };
			model.addRow(row);
			index++;
		} else {
			JOptionPane.showMessageDialog(null, "Error! ID already exists.");
		}
	}

	public void Delete() throws RemoteException {
		int r = tbService.getSelectedRow();
		int notice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this row?", "Delete",
				JOptionPane.YES_NO_OPTION);
		if (notice == JOptionPane.YES_OPTION) {
			roomList.deleteRoom(model.getValueAt(r, 1).toString());
			model.removeRow(r);
			Clear();
		}else {
			JOptionPane.showMessageDialog(null,"Select the row to delete!");
		}
	}

	public void Update() {
		String roomNo = inputRoomID.getText();
		String roomType = cbRoomType.getSelectedItem().toString();
		String roomStatus = cbRoomStatus.getSelectedItem().toString();
		int notice = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this row?", "Delete",
				JOptionPane.YES_NO_OPTION);
		if (notice == JOptionPane.YES_OPTION) {
			if (roomList.updateRoom(roomNo, roomType, roomStatus) != -1) {
				JOptionPane.showMessageDialog(null, "Update Success!");
				renderRowOfTable();
			} else {
				JOptionPane.showMessageDialog(null, "ID does not exist !");

			}
		}
		
	}

	public void Find() throws HeadlessException, RemoteException {
		String IDFind = inputFind.getText();
		if (roomList.findRoom(IDFind) == -1) {
			JOptionPane.showMessageDialog(null, "ID does not exist !");
		} else {
			tbService.changeSelection(roomList.findRoom(IDFind), 0, false, false);
			renderRowOfTable();
		}
	}

	public void Clear() {
		inputRoomID.setText("");
		cbRoomType.setSelectedIndex(0);
		cbRoomStatus.setSelectedIndex(0);
	}

}
