package mediaone.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginView extends JFrame{
	private JTextField tfAccount;
	private JPasswordField tfPass;
	private JButton btnLogin = new JButton("Login");
	
	public JTextField getTfAccount() {
		return tfAccount;
	}
	public JPasswordField getTfPass() {
		return tfPass;
	}
	public JButton getBtnLogin() {
		return btnLogin;
	}
	
	public LoginView() {
		setTitle("Đăng nhập - Media One");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(5, 5));
		
		add(createFieldPanel(), BorderLayout.CENTER);
		add(createButtonPanel(), BorderLayout.PAGE_END);
		setVisible(true);
	}
	
	public JPanel createFieldPanel() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		panel.setBorder(new EmptyBorder(30, 30, 90, 30));
		panel.add(createTitlePanel(), BorderLayout.WEST);
		panel.add(createTextPanel(), BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
		panel.add(new JLabel("Tài khoản "));
		panel.add(new JLabel("Mật khẩu "));
		return panel;
	}
	
	private JPanel createTextPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
		panel.add(tfAccount = new JTextField(20));
		panel.add(tfPass = new JPasswordField(20));
		return panel;
	}
	
	public JPanel createButtonPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(0, 30, 40, 30));
		panel.add(btnLogin);
		return panel;
	}
	
	public static void main(String[] args) {
		new LoginView();
	}
}
