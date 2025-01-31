package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dao.Room_DAO;
import entity.Room;

public class frmAllRoom extends JPanel implements MouseListener {
	private JPanel pnlCalendar, pnlAllRoom, pnlNode, pnlRed, pnlGreen, pnlOrange, pnlViolet, pnlRoom1;
	private JTextField txtCalendar, txtRoomNumber1, txt1Room1, txt2Room1, txt3Room1, txt4Room1;
	private JLabel lblCalendar, lblRed, lblGreen, lblOrange, lblViolet;
	private Room_DAO roomList;
	private JPanel[] panels;

	public frmAllRoom(String maNhanVien) {		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		pnlCalendar = new JPanel();
		pnlCalendar.setBounds(0, 0, screenSize.width - 300, 50);
		pnlCalendar.setBackground(new Color(202, 197, 160));

		txtCalendar = new JTextField();
		LocalDate currentdate = LocalDate.now();
		Month currentMonth = currentdate.getMonth();
		String month = (String) currentMonth.toString();
		int currentDay = currentdate.getDayOfMonth();
		int currentYear = currentdate.getYear();
		String calendar = month + " " + currentDay + ", " + currentYear;
		txtCalendar.setFont(new Font("Arial", Font.BOLD, 24));
		String imageCalendarPath = "/icon/IconCalendar.png";
		URL imageCalendarURL = getClass().getResource(imageCalendarPath);
		Image IconCalendar = Toolkit.getDefaultToolkit().createImage(imageCalendarURL);
		lblCalendar = new JLabel(new ImageIcon(IconCalendar));
		lblCalendar.setBounds(screenSize.width - 650, 5, 40, 40);
		txtCalendar.setText(calendar);
		txtCalendar.setBackground(new Color(202, 197, 160));
		txtCalendar.setBorder(new LineBorder(new Color(202, 197, 160)));
		txtCalendar.setEditable(false);
		txtCalendar.setBounds(screenSize.width - 600, 5, 300, 40);

		pnlAllRoom = new JPanel();
		pnlAllRoom.setLayout(new BoxLayout(pnlAllRoom, BoxLayout.Y_AXIS));

		roomList = new Room_DAO();

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(1090, 150));

		int lengthRoom = roomList.getAllRoom().size();
		int lengthRoom3 = 0;
		int lengthRoom2 = 0;
		int index = 0;
		DecimalFormat formatter = new DecimalFormat("#,###.## VND"); // set the format with VND currency symbol
		Box boxCol = Box.createVerticalBox();
		panels = new JPanel[roomList.getAllRoom().size()];
		for (; lengthRoom > 0; lengthRoom -= 4) {

			lengthRoom2 = lengthRoom / 4;//
			if (lengthRoom2 >= 1)
				lengthRoom3 = 4;
			else
				lengthRoom3 = lengthRoom;
		
			for (int j = 0; j < lengthRoom3; j++) {
				JPanel pnlRoom1 = new JPanel();
				pnlRoom1.setName(roomList.getAllRoom().get(index).getRoom());
				panels[index] = pnlRoom1;
				
				JPanel boxRow = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
				boxRow.setPreferredSize(new Dimension(250, 250));
				
				pnlRoom1.setPreferredSize(new Dimension(200, 190));

				txtRoomNumber1 = new JTextField();
				txtRoomNumber1.setEditable(false);
				txtRoomNumber1.setPreferredSize(new Dimension(50, 30));

				txtRoomNumber1.setText(roomList.getAllRoom().get(index).getRoom());
				txtRoomNumber1.setFont(new Font("Arial", Font.BOLD, 30));
				txtRoomNumber1.setForeground(Color.WHITE);

				txt1Room1 = new JTextField();
				txt1Room1.setEditable(false);
				txt1Room1.setPreferredSize(new Dimension(150, 30));
				txt1Room1.setFont(new Font("Arial", Font.BOLD, 20));
				txt1Room1.setForeground(Color.WHITE);

				txt1Room1.setText(roomList.getAllRoom().get(index).getRoomType());

				txt2Room1 = new JTextField();
				txt2Room1.setEditable(false);
				txt2Room1.setPreferredSize(new Dimension(150, 30));

				txt3Room1 = new JTextField();
				txt3Room1.setEditable(false);
				txt3Room1.setPreferredSize(new Dimension(150, 30));

				txt4Room1 = new JTextField();
				txt4Room1.setEditable(false);
				txt4Room1.setPreferredSize(new Dimension(150, 30));

				txt1Room1.setBorder(BorderFactory.createEmptyBorder());
				txt2Room1.setBorder(BorderFactory.createEmptyBorder());
				txt3Room1.setBorder(BorderFactory.createEmptyBorder());
				txt4Room1.setBorder(BorderFactory.createEmptyBorder());
				txtRoomNumber1.setBorder(BorderFactory.createEmptyBorder());

				if (roomList.getAllRoom().get(index).getStatus().equalsIgnoreCase("Dirty")) {
					pnlRoom1.setBackground(new Color(255, 165, 0));
					txtRoomNumber1.setBackground(new Color(255, 165, 0));
					txt1Room1.setBackground(new Color(255, 165, 0));
					txt2Room1.setBackground(new Color(255, 165, 0));
					txt3Room1.setBackground(new Color(255, 165, 0));
					txt4Room1.setBackground(new Color(255, 165, 0));
				} else if (roomList.getAllRoom().get(index).getStatus().equalsIgnoreCase("Occupied")) {
					pnlRoom1.setBackground(new Color(197, 4, 4));
					txtRoomNumber1.setBackground(new Color(197, 4, 4));
					txt1Room1.setBackground(new Color(197, 4, 4));
					txt2Room1.setBackground(new Color(197, 4, 4));
					txt3Room1.setBackground(new Color(197, 4, 4));
					txt4Room1.setBackground(new Color(197, 4, 4));
				} else if (roomList.getAllRoom().get(index).getStatus().equalsIgnoreCase("Ordered")) {
					pnlRoom1.setBackground(new Color(195, 44, 180));
					txtRoomNumber1.setBackground(new Color(195, 44, 180));
					txt1Room1.setBackground(new Color(195, 44, 180));
					txt2Room1.setBackground(new Color(195, 44, 180));
					txt3Room1.setBackground(new Color(195, 44, 180));
					txt4Room1.setBackground(new Color(195, 44, 180));
				} else {
					pnlRoom1.setBackground(new Color(6, 111, 23));
					txtRoomNumber1.setBackground(new Color(6, 111, 23));
					txt1Room1.setBackground(new Color(6, 111, 23));
					txt2Room1.setBackground(new Color(6, 111, 23));
					txt3Room1.setBackground(new Color(6, 111, 23));
					txt4Room1.setBackground(new Color(6, 111, 23));

					txt2Room1.setText(
							formatter.format(Double.parseDouble(roomList.getAllInfoRoom().get(index)[4].toString())));
					txt2Room1.setFont(new Font("Arial", Font.BOLD, 20));
					txt2Room1.setForeground(Color.WHITE);
					txt3Room1.setText(
							formatter.format(Double.parseDouble(roomList.getAllInfoRoom().get(index)[5].toString())));
					txt3Room1.setFont(new Font("Arial", Font.BOLD, 20));
					txt3Room1.setForeground(Color.WHITE);
					txt4Room1.setText(
							formatter.format(Double.parseDouble(roomList.getAllInfoRoom().get(index)[6].toString())));
					txt4Room1.setFont(new Font("Arial", Font.BOLD, 20));
					txt4Room1.setForeground(Color.WHITE);
				}

				pnlRoom1.add(txtRoomNumber1);
				pnlRoom1.add(txt1Room1);
				pnlRoom1.add(txt2Room1);
				pnlRoom1.add(txt3Room1);
				pnlRoom1.add(txt4Room1);

				boxRow.add(pnlRoom1);
				boxRow.add(Box.createHorizontalStrut(50));
				panel.add(boxRow);

				pnlRoom1.addMouseListener(new MouseAdapter() {
		                @Override
		                public void mouseClicked(MouseEvent e) {
		                	JFrame infoBookingFrame = new JFrame();
		                    JPanel clickedPanel = (JPanel)e.getSource();
		                    String panelName = clickedPanel.getName();
		                    infoBookingFrame = new frmInforBooking(panelName, maNhanVien);
		                    infoBookingFrame.setVisible(true);
		                }
		            });
				index++;

			}

//			

			boxCol.setPreferredSize(new Dimension(550, 1500));
			boxCol.add(panel);
			pnlAllRoom.add(boxCol);

		}

		JScrollPane scrollPane = new JScrollPane(pnlAllRoom);
		scrollPane.setBorder(null);
		scrollPane.setBounds((screenSize.width - 1400) / 2, 100, 1120, screenSize.height - 250);
		scrollPane.revalidate();
		scrollPane.repaint();

		pnlNode = new JPanel();
		pnlNode.setBounds((screenSize.width - 1400) / 2, screenSize.height - 120, 1100, 50);

		pnlRed = new JPanel();
		pnlRed.setPreferredSize(new Dimension(30, 30));
		pnlRed.setBackground(new Color(197, 4, 4));
		lblRed = new JLabel("Occupied");

		pnlGreen = new JPanel();
		pnlGreen.setPreferredSize(new Dimension(30, 30));
		pnlGreen.setBackground(new Color(6, 111, 23));
		lblGreen = new JLabel("Ready");

		pnlOrange = new JPanel();
		pnlOrange.setPreferredSize(new Dimension(30, 30));
		pnlOrange.setBackground(new Color(255, 165, 0));
		lblOrange = new JLabel("Dirty");

		pnlViolet = new JPanel();
		pnlViolet.setPreferredSize(new Dimension(30, 30));
		pnlViolet.setBackground(new Color(195, 44, 180));
		lblViolet = new JLabel("Ordered");

		pnlCalendar.add(txtCalendar);
		pnlCalendar.add(lblCalendar);
		add(pnlCalendar);
		add(scrollPane);
		add(pnlNode);
		pnlNode.add(pnlRed, BorderLayout.CENTER);
		pnlNode.add(lblRed, BorderLayout.CENTER);
		pnlNode.add(pnlGreen, BorderLayout.CENTER);
		pnlNode.add(lblGreen, BorderLayout.CENTER);
		pnlNode.add(pnlOrange, BorderLayout.CENTER);
		pnlNode.add(lblOrange, BorderLayout.CENTER);
		pnlNode.add(pnlViolet, BorderLayout.CENTER);
		pnlNode.add(lblViolet, BorderLayout.CENTER);

		pnlCalendar.setLayout(null);
		setLayout(null);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
