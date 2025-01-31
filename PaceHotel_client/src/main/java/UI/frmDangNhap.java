package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.EmployeeIDao;
import entity.Employee;

public class frmDangNhap extends JFrame implements ActionListener, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8198317889788230564L;
	private JLabel lblUserName, lblPassword;
	private JTextField txtUser, txtPass;
	private JButton btnLogin, btnSigup;
//	private Employee_DAO EmployeeDAO;
	
	public frmDangNhap()
	{
		super("Peace Hotel LOGIN");
		
		createUI();
	}
	
	private void createUI()
	{
//		
		setSize(800, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		JPanel pnLeft, pnRight;
		
		pnLeft = new JPanel();
		pnLeft.setLayout(null);
		pnLeft.setPreferredSize(new Dimension(400, 500));
		pnLeft.setBackground(new Color(23,38,47));
		
		String Logo1Path = "/image/Logo1.png";
		URL Logo1URL = getClass().getResource(Logo1Path);
		ImageIcon iconLogo = new ImageIcon(Logo1URL);
		JLabel lblLogo = new JLabel(iconLogo);
		
		pnLeft.add(lblLogo);
		lblLogo.setBounds(60, 30, 250, 330);
		
		String iconUserPath = "/icon/UserIcon.png";
		URL iconUserURL = getClass().getResource(iconUserPath);
		ImageIcon iconUser = new ImageIcon(iconUserURL);
		Image image = iconUser.getImage();
		Image newImage = image.getScaledInstance(30, 30, image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImage);
		
		JLabel lblLogoUser = new JLabel(newIcon);
		
		pnLeft.add(lblLogoUser);
		lblLogoUser.setBounds(50, 303, 100, 100);
		pnLeft.add(lblUserName = new JLabel("User name"));
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("Arial", Font.PLAIN, 20));
		lblUserName.setBounds(118, 308, 130, 100);
		
		pnLeft.add(txtUser = new JTextField());
		txtUser.setBounds(90, 380, 200, 23);
		txtUser.setBackground(new Color(246,246,246));
		
		String iconPassWordPath = "/icon/PasswordIcon.png";
		URL iconPassWordURL = getClass().getResource(iconPassWordPath);
		ImageIcon iconPass = new ImageIcon(iconPassWordURL);
		image = iconPass.getImage();
		newImage = image.getScaledInstance(25, 25, image.SCALE_SMOOTH);
		newIcon = new ImageIcon(newImage);
		
		JLabel lblLogoPass = new JLabel(newIcon);
		pnLeft.add(lblLogoPass);
		lblLogoPass.setBounds(lblLogoUser.getX(), 390, 100, 100);
		
		pnLeft.add(lblPassword = new JLabel("Password"));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(lblUserName.getFont());
		lblPassword.setBounds(lblUserName.getX() + 4, lblLogoPass.getY() + 42, 130, 20);
		
		pnLeft.add(txtPass = new JTextField());
		txtPass.setBounds(txtUser.getX(), lblPassword.getY() + 30, 200, 23);
		txtPass.setBackground(new Color(246,246,246));
		
		pnLeft.add(btnLogin = new JButton("Login"));
		btnLogin.setBounds(txtPass.getX(), txtPass.getY() + 50, 90, 30);
		
		pnLeft.add(btnSigup = new JButton("Sigup"));
		btnSigup.setBounds(btnLogin.getX() + 100, btnLogin.getY(), 100, 30);
		
		pnRight = new JPanel();	
		String LoginPath = "/image/Login_BG.jpeg";
		URL LoginURL = getClass().getResource(LoginPath);
		ImageIcon iconLogoR = new ImageIcon(LoginURL);
		image = iconLogoR.getImage();
		newImage = image.getScaledInstance(400, 700, image.SCALE_SMOOTH);
		newIcon = new ImageIcon(newImage);
		
		JLabel lblr = new JLabel(newIcon);
		pnRight.add(lblr);
		
		add(pnLeft, BorderLayout.WEST);
		add(pnRight, BorderLayout.EAST);
		
		btnLogin.addActionListener(this);
		
		setVisible(true);
	}
	

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLogin))
		{
			try
			{
				dangNhap();
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("depcrecation")
	public void dangNhap() throws MalformedURLException, RemoteException, NotBoundException, ClassNotFoundException 
	{
		Registry registry = LocateRegistry.getRegistry("192.168.1.19", 2000);
		
		EmployeeIDao employeeIDao = (EmployeeIDao) registry.lookup("employeeIDao");

		Employee tk = employeeIDao.login(txtUser.getText(), txtPass.getText());
		if (tk != null) {
//			JOptionPane.showMessageDialog(null, "Login");
			frmChucNang gdc = new frmChucNang(txtUser.getText());
			gdc.setVisible(true);
			this.disable();
			this.setVisible(false);
			return;
		} else {
			JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không chính xác!");
		}
	}
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, ClassNotFoundException, InterruptedException {
//		ManHinhKhoiDong manHinhKhoiDong = new ManHinhKhoiDong();
//		manHinhKhoiDong.setVisible(true);
//		manHinhKhoiDong.setLocationRelativeTo(null);
		for (int i = 0; i < 101; i++)
		{
//			Thread.sleep(15);
//			manHinhKhoiDong.progressBar.setValue(i);
//			manHinhKhoiDong.progressBar.setString("Đang tải " + Integer.toString(i) + "%");
			if (i == 100)
			{
//				manHinhKhoiDong.setVisible(false);
				new frmDangNhap().setVisible(true);
			}
		}
	}
}
