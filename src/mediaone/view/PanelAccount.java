package mediaone.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelAccount extends JPanel{
	private JButton btnChangePass = new JButton("Đổi mật khẩu");
	private JButton btnLogout = new JButton("Đăng xuất");
	
	public JButton getBtnChangePass() {
		return btnChangePass;
	}
	public JButton getBtnLogout() {
		return btnLogout;
	}
	
	public PanelAccount() {
		btnChangePass.setIcon(new ImageIcon(this.getClass().getResource("/mediaone/resource/change-pass-icon.png")));
		btnLogout.setIcon(new ImageIcon(this.getClass().getResource("/mediaone/resource/log-out-icon.png")));
		
		
		add(btnChangePass);
		add(btnLogout);
	}
}
