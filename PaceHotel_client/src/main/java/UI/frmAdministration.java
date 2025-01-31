package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class frmAdministration extends JPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7079201434430394483L;
	private JButton btnRoomMNG, btnEmployeeManager, btnServiceManager, btnLoginHistory, btnRoomType;
	private Registry registryGlo;
	public frmAdministration(Registry registry)
	{
		registryGlo = registry;
		setLayout(new BorderLayout());
		
		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
		pnNorth.setBackground(new Color(97,118,126));
		
		JLabel lblTitle = new JLabel("Administration");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 25));
		lblTitle.setForeground(Color.BLACK);
		pnNorth.add(lblTitle);
		
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(null);
		pnCenter.setBackground(new Color(166,180,184));
		
		pnCenter.add(btnRoomMNG = new JButton("Room manager"));
		pnCenter.add(btnEmployeeManager = new JButton("Employe manager"));
		pnCenter.add(btnServiceManager = new JButton("Service Manager"));
		pnCenter.add(btnLoginHistory = new JButton("Login history"));
		pnCenter.add(btnRoomType = new JButton("Room type"));
		
		btnRoomMNG.setBounds(120, 150, 300, 150);
		btnEmployeeManager.setBounds(btnRoomMNG.getX() + btnRoomMNG.getWidth() + 40, btnRoomMNG.getY(), btnRoomMNG.getWidth(), btnRoomMNG.getHeight());
		btnServiceManager.setBounds(btnEmployeeManager.getX() + btnEmployeeManager.getWidth() + 40, btnEmployeeManager.getY(), btnEmployeeManager.getWidth(), btnEmployeeManager.getHeight());
		btnLoginHistory.setBounds(btnEmployeeManager.getX(), btnEmployeeManager.getY() + 190, btnEmployeeManager.getWidth(), btnEmployeeManager.getHeight());
		btnRoomType.setBounds(btnEmployeeManager.getX() + btnEmployeeManager.getWidth() + 40, btnLoginHistory.getY(), btnEmployeeManager.getWidth(), btnEmployeeManager.getHeight());
		
		btnRoomMNG.setBackground(new Color(217,217,217));
		btnEmployeeManager.setBackground(btnRoomMNG.getBackground());
		btnServiceManager.setBackground(btnRoomMNG.getBackground());
		btnLoginHistory.setBackground(btnRoomMNG.getBackground());
		btnRoomType.setBackground(btnRoomMNG.getBackground());
		
		btnRoomMNG.setFocusable(false);
		btnEmployeeManager.setFocusable(false);
		btnServiceManager.setFocusable(false);
		btnLoginHistory.setFocusable(false);
		btnRoomType.setFocusable(false);
		
		add(pnNorth, BorderLayout.NORTH);
		add(pnCenter, BorderLayout.CENTER);
		
		btnRoomMNG.addActionListener(this);
		btnLoginHistory.addActionListener(this);
		btnEmployeeManager.addActionListener(this);
		btnServiceManager.addActionListener(this);
		btnRoomType.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnEmployeeManager))
		{
			JFrame frEmployee = new JFrame();
			setLayout(new BorderLayout());
//			frEmployee.setDefaultCloseOperation(frEmployee.EXIT_ON_CLOSE);
			frEmployee.setExtendedState(JFrame.MAXIMIZED_BOTH);
			
			JPanel pn = new JPanel();
			try {
				pn = new frmEmployeeManager(registryGlo);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			frEmployee.add(pn, BorderLayout.CENTER);
			
			frEmployee.setVisible(true);
		}
		
		else if (o.equals(btnRoomMNG))
		{
			JFrame fr = new JFrame();
			try {
				fr = new frmRoomManage(registryGlo);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			fr.setDefaultCloseOperation(fr.EXIT_ON_CLOSE);
			fr.setVisible(true);
		}else if (o.equals(btnServiceManager)) {
			JFrame fr = new JFrame();
			try {
				fr = new frmServiceManager(registryGlo);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			fr.setDefaultCloseOperation(fr.EXIT_ON_CLOSE);
			fr.setVisible(true);
		} else if(o.equals(btnRoomType)) {
			JFrame fr = new JFrame();
			try {
				fr = new frmRoomType(registryGlo);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			fr.setVisible(true);
		}
	}
}
