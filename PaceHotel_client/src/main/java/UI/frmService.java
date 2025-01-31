package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.BillDetailIDao;
import dao.BillIDao;
import dao.BookingIDao;
import dao.EmployeeIDao;
import dao.ServiceIDao;
import entity.Bill;
import entity.BillDetail;
import entity.Booking;
import entity.Service;
import service.BillDetail_DAO;
import service.Booking_DAO;
import service.Service_DAO;

public class frmService extends JPanel implements ActionListener, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7843740201907709429L;
	private JLabel lblRoomID, lblServiceName, lblQuantity, lblNote, lblTotal;
	private JTextField txtRoomID, txtQuantity, txtNote, txtTotal;
	private JButton btnAdd, btnDelete, btnOrder, btnFind;
	private JTable tbService;
	private DefaultTableModel model;
	private JComboBox<String> cbServiceName;
	private ServiceIDao listService;
	private BookingIDao listBooking;
	private BillDetailIDao listBillDetail;
	private BillDetail billDetail;
	private BillIDao listBill;
	
	public frmService(Registry registry) throws RemoteException, NotBoundException
	{
		setLayout(new BorderLayout());
		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		
		JLabel lblTitle = new JLabel("Service");
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
		
		pnC1.add(lblRoomID = new JLabel("Room ID:"));
		pnC1.add(txtRoomID = new JTextField());
		pnC1.add(lblServiceName = new JLabel("Service Name:"));
		pnC1.add(cbServiceName = new JComboBox<String>());
		
		listService = (ServiceIDao) registry.lookup("serviceIDao");
		listBooking = (BookingIDao) registry.lookup("bookingIDao");
		listBillDetail = (BillDetailIDao) registry.lookup("billDetailIDao");
		listBill = (BillIDao) registry.lookup("billIDao");
		for (Service sv : listService.getAllService()) {
			cbServiceName.addItem(sv.getServiceName());
		}
		
		pnC1.add(lblQuantity = new JLabel("Quantity"));
		pnC1.add(txtQuantity = new JTextField());
		pnC1.add(lblNote = new JLabel("Note"));
		pnC1.add(txtNote = new JTextField());
		pnC1.add(btnAdd = new JButton("Add"));
		pnC1.add(btnFind = new JButton("Find"));
		
		lblRoomID.setBounds(15, 10, 60, 20);
		txtRoomID.setBounds(lblRoomID.getX() + 60, lblRoomID.getY(), 120, 20);
		lblServiceName.setBounds(lblRoomID.getX(), lblRoomID.getY() + 30, 130, 20);
		cbServiceName.setBounds(lblServiceName.getX() + 90, lblServiceName.getY(), 200, 20);
		lblQuantity.setBounds(cbServiceName.getX() + cbServiceName.getWidth() + 20, cbServiceName.getY(), 50, 20);
		txtQuantity.setBounds(lblQuantity.getX() + 60, lblQuantity.getY(), 100, 20);
		lblNote.setBounds(txtQuantity.getX() + 120, txtQuantity.getY(), 50, 20);
		txtNote.setBounds(lblNote.getX() + 50, lblNote.getY(), 250, 20);
		btnAdd.setBounds(txtNote.getX() + txtNote.getWidth() + 20, txtNote.getY(), 60, 20);
		btnFind.setBounds(lblRoomID.getX() + 200, lblRoomID.getY(), 60, 20);
		
		JPanel pnC2 = new JPanel();
		pnC2.setLayout(new BorderLayout());
		
		String[] cols = {"STT", "Service id","Service Name", "Price", "Quantity", "Amount", "Note"};
		model = new DefaultTableModel(cols, 0);
		tbService = new JTable(model);
		
		tbService.getTableHeader().setBackground(new Color(228,201,139));
		
		JScrollPane pane = new JScrollPane(tbService);
		
		pnC2.add(pane, BorderLayout.CENTER);
		
		JPanel pnC3 = new JPanel();
		pnC3.setLayout(new FlowLayout(FlowLayout.RIGHT,5, 15));
		pnC3.setBackground(new Color(166,180,184));
		
		pnC3.add(lblTotal = new JLabel("Total:"));
		pnC3.add(txtTotal = new JTextField(10));
		
		pnCenter.add(pnC1, BorderLayout.NORTH);
		pnCenter.add(pnC2, BorderLayout.CENTER);
		pnCenter.add(pnC3, BorderLayout.SOUTH);
		
		JPanel pnSouth = new JPanel();
		pnSouth.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 20));
		pnSouth.setBackground(new Color(97,118,126));
		
		pnSouth.add(btnDelete = new JButton("Delete"));
		pnSouth.add(btnOrder = new JButton("Order"));
		
		add(pnNorth, BorderLayout.NORTH);
		add(pnCenter, BorderLayout.CENTER);
		add(pnSouth, BorderLayout.SOUTH);
		
		btnAdd.setFocusable(false);
		btnDelete.setFocusable(false);
		btnOrder.setFocusable(false);
		
		btnAdd.setBackground(new Color(6,110,23));
		btnAdd.setForeground(Color.white);
		btnDelete.setBackground(new Color(197, 4, 4));
		btnDelete.setForeground(Color.white);
		btnOrder.setBackground(new Color(255,165,0));
		btnOrder.setForeground(Color.white);
		
		txtTotal.setEditable(false);
		
		btnAdd.addActionListener(this);
		btnOrder.addActionListener(this);
		btnFind.addActionListener(this);
		btnDelete.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnAdd)) {
			try {
				Add();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if(e.getSource().equals(btnFind)) {
			try {
				Find();
			} catch (NumberFormatException | HeadlessException | RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if(e.getSource().equals(btnDelete)) {
			try {
				Delete();
			} catch (NumberFormatException | RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void Find() throws NumberFormatException, HeadlessException, RemoteException {
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
		String roomID = txtRoomID.getText();
		if(listBooking.findRoomBooking(roomID) != -1) {
			int idBookingGet = listBooking.findRoomBooking(roomID);
			int index = 1;
			float total = 0;
			
			Locale localeVN = new Locale("vi", "VN");
	        NumberFormat vnFormat = NumberFormat.getCurrencyInstance(localeVN);
			for (Object[] o : listBillDetail.getAllService(idBookingGet)) {
				double price = Double.parseDouble(o[2].toString());
		        String priceVNFormat = vnFormat.format(price);
		        
		        double amount = Double.parseDouble(o[4].toString());
		        String priceVNAmount = vnFormat.format(amount);
				
				total += Float.parseFloat(o[4].toString());
				Object[] rowData = {index, o[0], o[1], priceVNFormat, o[3], priceVNAmount};
				model.addRow(rowData);
				index++;
			}
			String priceTotal = vnFormat.format(total);
			txtTotal.setText(priceTotal);
		} else {
			JOptionPane.showMessageDialog(null, "Phòng không tồn tại hoặc không có khách");
		}
	}
	
	public void Add() throws RemoteException {
		String roomID = txtRoomID.getText();
		
		
		if(listBooking.findRoomBooking(roomID) != -1) {
			
			String serviceName = cbServiceName.getSelectedItem().toString();
			int qty = Integer.parseInt(txtQuantity.getText());
			String serviceChoseID = "";
			for (Service sv : listService.getAllService()) {
				if(sv.getServiceName().equalsIgnoreCase(serviceName)) {
					serviceChoseID = sv.getServiceID();
				}
			}
			int idBookingGet = listBooking.findRoomBooking(roomID);
			int billID = listBillDetail.findBillID(idBookingGet);
			
			Bill billId = listBill.findBill(billID);
			Booking bookingId = listBooking.getOneBooking1(idBookingGet);
			Service serviceId = listService.findOneService(serviceChoseID);
			
			billDetail = new BillDetail(qty, billId, bookingId, serviceId);
			listBillDetail.addBillDetail(billDetail);
			Find();
		} else {
			JOptionPane.showMessageDialog(null, "Phòng không tồn tại hoặc không có khách");
		}
		
	}
	
	public void Delete() throws NumberFormatException, RemoteException {
		int r = tbService.getSelectedRow();
		if (r != -1) {
			int notice = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa môn học này chứ?", "Delete",
					JOptionPane.YES_NO_OPTION);
			if(notice == JOptionPane.YES_OPTION) {
				listBillDetail.deleteService(Integer.parseInt(model.getValueAt(r, 1).toString()));
				model.removeRow(r);
				Clear();
			}
		}
	}
	
	public void Clear() {
		txtNote.setText("");
		txtQuantity.setText("");
		cbServiceName.setSelectedIndex(1);
	}
	
	
}
