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

import dao.RoomIDao;
import dao.RoomTypeIDao;
import entity.RoomType;
import service.RoomType_DAO;
import service.Room_DAO;

public class frmRoomType extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5288935686394046558L;
	private JLabel pRoomType, pFirtHour, pNextHour, pOverNight, pQTYBed, pPeople, pDiscount, pSurchage, pTitle;
	private JTextField inputRoomType, inputFirtHour, inputNextHour, inputFind,inputOverNight, inputQTYBed, inputPeople, inputDiscount, inputSurchage;
	private JButton btnAdd, btnUpdate, btnClear, btnSave, btnDelete, btnFind;
	private JTable tbService;
	private DefaultTableModel model;
	private JPanel sectionBorder;
	private int index = 1;
	private RoomIDao roomList;
	private RoomTypeIDao roomTypeList;

	public frmRoomType(Registry registry) throws RemoteException, NotBoundException {
		setTitle("Room type");
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setLayout(new BorderLayout());

		sectionBorder = new JPanel();
		sectionBorder.setLayout(new BorderLayout());
		add(sectionBorder);

		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

		pTitle = new JLabel("Room type");
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

		pnC1.add(pRoomType = new JLabel("Room type:"));
		pnC1.add(inputRoomType = new JTextField());
		pnC1.add(pFirtHour = new JLabel("First hour:"));
		pnC1.add(inputFirtHour = new JTextField());
		pnC1.add(pNextHour = new JLabel("Next hour:"));
		pnC1.add(inputNextHour = new JTextField());
		pnC1.add(pOverNight = new JLabel("Over night:"));
		pnC1.add(inputOverNight = new JTextField());
		pnC1.add(pQTYBed = new JLabel("Quality bed:"));
		pnC1.add(inputQTYBed = new JTextField());
		pnC1.add(pPeople = new JLabel("Quality people:"));
		pnC1.add(inputPeople = new JTextField());
		pnC1.add(pDiscount = new JLabel("Discount:"));
		pnC1.add(inputDiscount = new JTextField());
		pnC1.add(pSurchage = new JLabel("Surchage:"));
		pnC1.add(inputSurchage = new JTextField());
		roomTypeList = (RoomTypeIDao) registry.lookup("roomTypeIDao");
//		for (RoomType rt : roomTypeList.getAllRoomType()) {
//			cbRoomType.addItem(rt.getRoomType());
//		}

		pRoomType.setBounds(30, 30, 90, 20);
		inputRoomType.setBounds(pRoomType.getX() + 70, pRoomType.getY(), 150, 20);

		pFirtHour.setBounds(pRoomType.getX() + 250, pRoomType.getY(), 130, 20);
		inputFirtHour.setBounds(pFirtHour.getX() + 70, pRoomType.getY(), 150, 20);

		pNextHour.setBounds(pFirtHour.getX() + 250, pRoomType.getY(), 130, 20);
		inputNextHour.setBounds(pNextHour.getX() + 70, pRoomType.getY(), 150, 20);
		
		pOverNight.setBounds(pNextHour.getX() + 250, pRoomType.getY(), 130, 20);
		inputOverNight.setBounds(pOverNight.getX() + 70, pRoomType.getY(), 150, 20);
		
		pQTYBed.setBounds(pOverNight.getX() +250, pRoomType.getY(), 130, 20);
		inputQTYBed.setBounds(pQTYBed.getX() + 70, pRoomType.getY(), 70, 20);
		
		pPeople.setBounds(pQTYBed.getX() + 170, pRoomType.getY(), 130, 20);
		inputPeople.setBounds(pPeople.getX() + 100, pRoomType.getY(), 70, 20);
		
		pDiscount.setBounds(pPeople.getX() + 200, pRoomType.getY(), 130, 20);
		inputDiscount.setBounds(pDiscount.getX() + 70, pRoomType.getY(), 150, 20);
		
		pSurchage.setBounds(pDiscount.getX() + 250, pRoomType.getY(), 130, 20);
		inputSurchage.setBounds(pSurchage.getX() + 70, pRoomType.getY(), 150, 20);
		
		JPanel pnC2 = new JPanel();
		String[] cols = { "STT", "Room Type", "First hour", "Next hour", "Over night", "QtyBed","People", "Disscount", "Surchage"};
		model = new DefaultTableModel(cols, 0);
		roomList = (RoomIDao) registry.lookup("roomIDao");

		for (RoomType o : roomTypeList.getAllRoomType()) {
			Object[] rowData = { index, o.getRoomType(), o.getFisrtHourFee(), o.getNextHourFee(), o.getOverNightFee(), o.getQtyBed(), o.getPeople(), o.getDisscount(), o.getSurcharge() };
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
	
	public void renderRowOfTable() {
		int r = tbService.getSelectedRow();
		inputRoomType.setText(model.getValueAt(r, 1).toString());
		inputFirtHour.setText(model.getValueAt(r, 2).toString());
		inputNextHour.setText(model.getValueAt(r, 3).toString());
		inputOverNight.setText(model.getValueAt(r, 4).toString());
		inputQTYBed.setText(model.getValueAt(r, 5).toString());
		inputPeople.setText(model.getValueAt(r, 6).toString());
		inputDiscount.setText(model.getValueAt(r, 7).toString());
		inputSurchage.setText(model.getValueAt(r, 8).toString());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAdd)) {
				try {
					Add();
				} catch (HeadlessException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
			} catch (HeadlessException | RemoteException e1) {
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
				try {
					Update();
				} catch (HeadlessException | RemoteException e1) {
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
		String roomID = inputRoomType.getText().trim();

		if (!(roomID.length() > 0 && roomID.matches("^[1-9]\\d{2}"))) {
			showMessage("Mã dịch vụ phải là S + d(3)", inputRoomType);
			return false;
		}

		return true;
	}

	public void Add() throws HeadlessException, RemoteException {
		String roomType = inputRoomType.getText().toString();
		double firstHour = Double.parseDouble(inputFirtHour.getText());
		double nextHour = Double.parseDouble(inputNextHour.getText());
		double overNight = Double.parseDouble(inputOverNight.getText());
		int qtyBed = Integer.parseInt(inputQTYBed.getText());
		int people = Integer.parseInt(inputPeople.getText());
		float discount = Float.parseFloat(inputDiscount.getText());
		float surchage = Float.parseFloat(inputSurchage.getText());
		
		RoomType newRoom = new RoomType(roomType, firstHour, nextHour, overNight, qtyBed, people, discount, surchage, null);
		if (roomTypeList.addRoomType(newRoom) != 0) {
			RoomType rt = roomTypeList.getLastRoom(roomType);
			Object[] row = { index, rt.getRoomType(), rt.getFisrtHourFee(), rt.getNextHourFee(), rt.getOverNightFee(), rt.getQtyBed(), rt.getPeople(), rt.getDisscount(), rt.getSurcharge()};
			model.addRow(row);
			index++;
		} else {
			JOptionPane.showMessageDialog(null, "Error! ID already exists.");
		}
	}

	public void Delete() throws HeadlessException, RemoteException {
		int r = tbService.getSelectedRow();
		int notice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this row?", "Delete",
				JOptionPane.YES_NO_OPTION);
		if (notice == JOptionPane.YES_OPTION) {
			if(roomTypeList.deleteRoomType(model.getValueAt(r, 1).toString())) {
				model.removeRow(r);
				Clear();
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng xóa những phòng có roomtype này trước");
			}
		}else {
			JOptionPane.showMessageDialog(null,"Select the row to delete!");
		}
	}

	public void Update() throws HeadlessException, RemoteException {
		String roomType = inputRoomType.getText();
		double firstHour = Double.parseDouble(inputFirtHour.getText());
		double nextHour = Double.parseDouble(inputNextHour.getText());
		double overNight = Double.parseDouble(inputOverNight.getText());
		int qtyBed = Integer.parseInt(inputQTYBed.getText());
		int people = Integer.parseInt(inputPeople.getText());
		float discount = Float.parseFloat(inputDiscount.getText());
		float surchage = Float.parseFloat(inputSurchage.getText());
		int notice = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this row?", "Delete",
				JOptionPane.YES_NO_OPTION);
		if (notice == JOptionPane.YES_OPTION) {
			if (roomTypeList.updateRoomType(roomType, firstHour, nextHour, overNight, qtyBed, people, discount, surchage) != -1) {
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
		inputRoomType.setText("");
		inputFirtHour.setText("");
		inputNextHour.setText("");
		inputFind.setText("");
		inputOverNight.setText("");
		inputQTYBed.setText("");
		inputPeople.setText("");
		inputDiscount.setText("");
		inputSurchage.setText("");
	}
	
//	public static void main(String[] args) {
//		new frmRoomType().setVisible(true);
//	}
}
