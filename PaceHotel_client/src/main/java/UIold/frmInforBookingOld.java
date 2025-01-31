package UIold;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.RoomIDao;

public class frmInforBookingOld extends JFrame implements ActionListener, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7165065202248133588L;
	private JLabel pName, pGender, pCard, pPhone, pNumOfAdults, pNumOfChildren, pTimeOfArrival, pTimeOfDeparture, pNote, pTotal,
		pRoomType, pNumOfBed, pPrice, pSurcharge, pDiscount, pFirstHour, pNextHour,pOvernight
		, pTitle, pRoomID;
	private JTextField inputName, inputGender, inputCard, inputPhone, inputNumOfAdults, inputNumOfChildren, inputTimeOfArrival, inputTimeOfDeinputarture, inputNote, inputTotal,
	inputRoomType, inputNumOfBed, inputPrice, inputSurcharge, inputDiscount, inputFirstHour,inputNextHour, inputOvernight;
	private JButton btnCancel, btnCheckIn, btnCheckOut, btnAddService;
	private JPanel sectionBottom, sectionCenter, sectionBorder;
	private RoomIDao roomList;
	
	public frmInforBookingOld(String id, Registry registry) throws RemoteException, NotBoundException
	{
		setTitle("Room manager");
		setSize(1200,700);
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
		
		sectionBorder = new JPanel();
		sectionBorder.setLayout(new BorderLayout());
		add(sectionBorder);
		
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		
		JPanel pnC1 = new JPanel();
		pnC1.setLayout(null);
		
		RoomIDao roomList = (RoomIDao) registry.lookup("roomIDao");
		
		
		btnAddService = new JButton("Add service");
		btnCancel = new JButton("Cancel");
		btnCheckIn = new JButton("Check in");
		btnCheckOut = new JButton("Check out");
		
		pTitle = new JLabel("Guest information");
		pName = new JLabel("Fullname:");
		pGender = new JLabel("Gender:");
		pCard = new JLabel("Indentity Card:");
		pPhone = new JLabel("Phone Number:");
		pNumOfAdults = new JLabel("Number of adults:");
		pNumOfChildren = new JLabel("Number of Children:");
		pTimeOfArrival = new JLabel("Time of arrival:");
		pTimeOfDeparture = new JLabel("Time of departure:");
		pNote = new JLabel("Note:");
		pTotal = new JLabel("Total:");
		pRoomType = new JLabel("Room type:");
		pNumOfBed = new JLabel("Number of beds:");
		pPrice = new JLabel("Price:");
		pSurcharge = new JLabel("Surcharge:");
		pDiscount = new JLabel("Discount:");
		pFirstHour = new JLabel("First hour fee:");
		pNextHour = new JLabel("Next hour fee:");
		pOvernight = new JLabel("Overnight fee");
		pRoomID = new JLabel("ROOM " + id);
		
		inputName = new JTextField();
		inputGender = new JTextField();
		inputCard = new JTextField();
		inputPhone = new JTextField();
		inputNumOfAdults = new JTextField();
		inputNumOfChildren = new JTextField();
		inputTimeOfArrival = new JTextField();
		inputTimeOfDeinputarture = new JTextField();
		inputNote = new JTextField();
		inputTotal = new JTextField();
		
		int iRoom = roomList.findRoom(id);
		
		
		inputRoomType = new JTextField(roomList.getAllInfoRoom().get(iRoom)[1].toString());
		inputNumOfBed = new JTextField(roomList.getAllInfoRoom().get(iRoom)[3].toString());
		inputPrice = new JTextField();
		inputSurcharge = new JTextField(20);
		inputDiscount = new JTextField();
		inputFirstHour = new JTextField(roomList.getAllInfoRoom().get(iRoom)[4].toString());
		inputNextHour = new JTextField(roomList.getAllInfoRoom().get(iRoom)[5].toString());
		inputOvernight = new JTextField(roomList.getAllInfoRoom().get(iRoom)[6].toString());
		
		
		pnC1.add(pTitle);
		pnC1.add(pName);
		pnC1.add(inputName);
		pnC1.add(pGender);
		pnC1.add(inputGender);
		pnC1.add(pCard);
		pnC1.add(inputCard);
		pnC1.add(pPhone);
		pnC1.add(inputPhone);
		pnC1.add(pNumOfAdults);
		pnC1.add(inputNumOfAdults);
		pnC1.add(pNumOfChildren);
		pnC1.add(inputNumOfChildren);
		pnC1.add(pTimeOfArrival);
		pnC1.add(inputTimeOfArrival);
		pnC1.add(pTimeOfDeparture);
		pnC1.add(inputTimeOfDeinputarture);
		pnC1.add(pNote);
		pnC1.add(inputNote);
		
		pTitle.setBounds(250, 30, 500, 30);
		pTitle.setFont(new Font("Arial", Font.BOLD, 30));
		
		pName.setBounds(50, 100, 60, 20);
		inputName.setBounds(pName.getX() + 70, pName.getY(), 300, 20);
		
		pGender.setBounds(pName.getX() + 400, pName.getY(), 100, 20);
		inputGender.setBounds(pGender.getX() + 70, pName.getY(), 130, 20);
		
		pCard.setBounds(50, pName.getY() + 40, 100, 20);
		inputCard.setBounds(pCard.getX() + 100, pCard.getY(), 200, 20);
		
		pPhone.setBounds(pCard.getX() + 350, pCard.getY(), 100, 20);
		inputPhone.setBounds(pPhone.getX() + 100, pCard.getY(), 150, 20);
		
		pNumOfAdults.setBounds(50, pPhone.getY() + 40, 100, 20);
		inputNumOfAdults.setBounds(pCard.getX() + 120, pNumOfAdults.getY(), 120, 20);
		
		pNumOfChildren.setBounds(pNumOfAdults.getX() + 350, pNumOfAdults.getY(), 130, 20);
		inputNumOfChildren.setBounds(pNumOfChildren.getX() + 130, pNumOfChildren.getY(), 120, 20);
		
		pTimeOfArrival.setBounds(50, pNumOfAdults.getY() + 40, 100, 20);
		inputTimeOfArrival.setBounds(pTimeOfArrival.getX() + 120, pTimeOfArrival.getY(), 120, 20);
		
		pTimeOfDeparture.setBounds(pNumOfAdults.getX() + 350, pTimeOfArrival.getY(), 150, 20);
		inputTimeOfDeinputarture.setBounds(pTimeOfDeparture.getX() + 130, pTimeOfDeparture.getY(), 120, 20);
		
		pNote.setBounds(50, pTimeOfDeparture.getY() + 40, 100, 20);
		inputNote.setBounds(pNote.getX() + 50, pNote.getY(), 550, 20);
		
		
		JPanel pnC2 = new JPanel();
		pnC2.setLayout(new BoxLayout(pnC2, BoxLayout.Y_AXIS));
		
//		pnC2.add(pRoomID);
//		pnC2.add(pRoomType);
//		pnC2.add(inputRoomType);
//		pnC2.add(pNumOfBed);
//		pnC2.add(inputNumOfBed);
//		pnC2.add(pPrice);
//		pnC2.add(inputPrice);
//		pnC2.add(pSurcharge);
//		pnC2.add(inputSurcharge);
//		pnC2.add(pDiscount);
//		pnC2.add(inputDiscount);
//		pnC2.add(pFirstHour);
//		pnC2.add(inputFirstHour);
//		pnC2.add(pNextHour);
//		pnC2.add(inputNextHour);
//		pnC2.add(pOvernight);
//		pnC2.add(inputOvernight);
		
		
		Box b = Box.createVerticalBox();//dọc
		Box b1 = Box.createHorizontalBox();//ngang
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();
		Box b6 = Box.createHorizontalBox();
		Box b7 = Box.createHorizontalBox();
		Box b8 = Box.createHorizontalBox();
		Box b9 = Box.createHorizontalBox();
		Box b10 = Box.createHorizontalBox();
		b.add(Box.createVerticalStrut(30));
		b1.add(Box.createHorizontalStrut(60));
		b.add(b1);
		b.add(Box.createVerticalStrut(30));
		b2.add(Box.createHorizontalStrut(40));
		b.add(b2);
		b.add(Box.createVerticalStrut(10));
		b3.add(Box.createHorizontalStrut(40));
		b.add(b3);
		b.add(Box.createVerticalStrut(10));
		b4.add(Box.createHorizontalStrut(40));
		b.add(b4);
		b.add(Box.createVerticalStrut(10));
		b5.add(Box.createHorizontalStrut(40));
		b.add(b5);
		b.add(Box.createVerticalStrut(10));
		b6.add(Box.createHorizontalStrut(40));
		b.add(b6);
		b.add(Box.createVerticalStrut(10));
		b7.add(Box.createHorizontalStrut(40));
		b.add(b7);
		b.add(Box.createVerticalStrut(10));
		b8.add(Box.createHorizontalStrut(40));
		b.add(b8);
		b.add(Box.createVerticalStrut(10));
		b9.add(Box.createHorizontalStrut(40));
		b.add(b9);
		b.add(Box.createVerticalStrut(30));
		b10.add(Box.createHorizontalStrut(60));
		b.add(b10);
		b.add(Box.createVerticalStrut(180));
		
		b1.add(pRoomID);
		pRoomID.setFont(new Font("Arial", Font.BOLD, 30));
		
		b2.add(pRoomType);
		b2.add(inputRoomType);
		pRoomType.setPreferredSize(pNumOfBed.getPreferredSize());
		
		b3.add(pNumOfBed);
		b3.add(inputNumOfBed);
		pNumOfBed.setPreferredSize(pNumOfBed.getPreferredSize());
		
		b4.add(pPrice);
		b4.add(inputPrice);
		pPrice.setPreferredSize(pNumOfBed.getPreferredSize());
		
		b5.add(pSurcharge);
		b5.add(inputSurcharge);
		pSurcharge.setPreferredSize(pNumOfBed.getPreferredSize());
		
		b6.add(pDiscount);
		b6.add(inputDiscount);
		pDiscount.setPreferredSize(pNumOfBed.getPreferredSize());
		
		b7.add(pFirstHour);
		b7.add(inputFirstHour);
		pFirstHour.setPreferredSize(pNumOfBed.getPreferredSize());
		
		b8.add(pNextHour);
		b8.add(inputNextHour);
		pNextHour.setPreferredSize(pNumOfBed.getPreferredSize());
		
		b9.add(pOvernight);
		b9.add(inputOvernight);
		pOvernight.setPreferredSize(pNumOfBed.getPreferredSize());
		
		b10.add(btnAddService);
		
		pnC2.add(b);
		
		pnC2.setBackground(new Color(166, 180, 184));
		
		pnC2.setBorder(new EmptyBorder(0, 0, 0, 50));
		
		pnCenter.add(pnC1, BorderLayout.CENTER);
		pnCenter.add(pnC2, BorderLayout.EAST);

		
		JPanel pnSouth = new JPanel();
		pnSouth.setLayout(new BorderLayout());
		pnSouth.setBackground(new Color(227, 219, 176));
		
		JPanel pnSouthR = new JPanel();
		pnSouthR.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 20));
		pnSouthR.setBackground(new Color(227, 219, 176));
		
		JPanel pnSouthL = new JPanel();
		pnSouthL.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
		pnSouthL.setBackground(new Color(227, 219, 176));
		
		JPanel pnSouthC = new JPanel();
		pnSouthC.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		pnSouthC.setBackground(new Color(227, 219, 176));
		
		pnSouthL.add(pTotal);
		pnSouthL.add(inputTotal = new JTextField(10));
		
		pnSouthC.add(btnCancel = new JButton("Cancel"));
		pnSouthC.add(btnCheckIn = new JButton("Check In"));
		
		
		pnSouthR.add(btnCheckOut = new JButton("Check Out"));
		
		pnSouth.add(pnSouthL, BorderLayout.WEST);
		pnSouth.add(pnSouthC, BorderLayout.CENTER);
		pnSouth.add(pnSouthR, BorderLayout.EAST);
		

		sectionBorder.add(pnCenter, BorderLayout.CENTER);
		sectionBorder.add(pnSouth, BorderLayout.SOUTH);
		
		btnCancel.setFocusable(false);
		btnCheckIn.setFocusable(false);
		btnCheckOut.setFocusable(false);
		
		
		btnCancel.setBackground(new Color(126,126,126));
		btnCancel.setForeground(Color.white);
		btnCheckIn.setBackground(new Color(0, 0, 0));
		btnCheckIn.setForeground(Color.white);
		btnCheckOut.setBackground(new Color(197, 4, 4));
		btnCheckOut.setForeground(Color.white);
		btnAddService.setBackground(new Color(255, 165, 0));
		btnAddService.setForeground(Color.white);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
//	public static void main(String[] args) {
//		new frmInforBooking().setVisible(true);
//	}
}
