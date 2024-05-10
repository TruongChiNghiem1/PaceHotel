package UIold;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import entity.Service;

public class frmService extends JPanel implements ActionListener, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5112460979510726595L;
	private JLabel lblRoomID, lblServiceName, lblQuantity, lblNote, lblTotal;
	private JTextField txtRoomID, txtQuantity, txtNote, txtTotal;
	private JButton btnAdd, btnCancel, btnOrder;
	private JTable tbService;
	private DefaultTableModel model;
	private JComboBox<String> cbServiceName;
	
	public frmService(Registry registry)
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
		
//		listService = new Service_DAO();
//		for (Service sv : listService.getAllService()) {
//			cbServiceName.addItem(sv.getServiceName());
//		}
		
		pnC1.add(lblQuantity = new JLabel("Quantity"));
		pnC1.add(txtQuantity = new JTextField());
		pnC1.add(lblNote = new JLabel("Note"));
		pnC1.add(txtNote = new JTextField());
		pnC1.add(btnAdd = new JButton("Add"));
		
		lblRoomID.setBounds(15, 10, 60, 20);
		txtRoomID.setBounds(lblRoomID.getX() + 60, lblRoomID.getY(), 120, 20);
		lblServiceName.setBounds(lblRoomID.getX(), lblRoomID.getY() + 30, 130, 20);
		cbServiceName.setBounds(lblServiceName.getX() + 90, lblServiceName.getY(), 200, 20);
		lblQuantity.setBounds(cbServiceName.getX() + cbServiceName.getWidth() + 20, cbServiceName.getY(), 50, 20);
		txtQuantity.setBounds(lblQuantity.getX() + 60, lblQuantity.getY(), 100, 20);
		lblNote.setBounds(txtQuantity.getX() + 120, txtQuantity.getY(), 50, 20);
		txtNote.setBounds(lblNote.getX() + 50, lblNote.getY(), 250, 20);
		btnAdd.setBounds(txtNote.getX() + txtNote.getWidth() + 20, txtNote.getY(), 60, 20);
		
		JPanel pnC2 = new JPanel();
		pnC2.setLayout(new BorderLayout());
		
		String[] cols = {"STT", "Service Name", "Price", "Quantity", "Amount", "Note"};
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
		
		pnSouth.add(btnCancel = new JButton("Cancel"));
		pnSouth.add(btnOrder = new JButton("Order"));
		
		add(pnNorth, BorderLayout.NORTH);
		add(pnCenter, BorderLayout.CENTER);
		add(pnSouth, BorderLayout.SOUTH);
		
		btnAdd.setFocusable(false);
		btnCancel.setFocusable(false);
		btnOrder.setFocusable(false);
		
		btnAdd.setBackground(new Color(6,110,23));
		btnAdd.setForeground(Color.white);
		btnCancel.setBackground(new Color(126,126,126));
		btnCancel.setForeground(Color.white);
		btnOrder.setBackground(new Color(255,165,0));
		btnOrder.setForeground(Color.white);
		
		txtTotal.setEditable(false);
		
		btnAdd.addActionListener(this);
		btnOrder.addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnAdd)) {
			Add();
		}
	}
	
	public void Add() {
		String roomID = txtRoomID.getText();
		String serviceName = cbServiceName.getSelectedItem().toString();
		int qty = Integer.parseInt(txtQuantity.getText());
		String note = txtNote.getText();
		
		
		
	}
}
