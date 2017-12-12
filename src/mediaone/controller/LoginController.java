package mediaone.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mediaone.service.StaffService;
import mediaone.view.ChangePassView;
import mediaone.view.LoginView;
import mediaone.view.MainUI;

public class LoginController {
	private LoginView loginView;
	private MainUI mainUI;
	
	private JTextField tfAccount;
	private JPasswordField tfPass;
	private JButton btnLogin;
	private JButton btnChangePass;
	private JButton btnLogout;
	
	private StaffService staffService;
	private String account;
	
	public String getAccount() {
		return account;
	}
	
	public LoginController (MainUI mainUI) {
		staffService = new StaffService();
		this.mainUI = mainUI;
		/* Initialize view */
		loginView = new LoginView();
		tfAccount = loginView.getTfAccount();
		tfPass = loginView.getTfPass();
		btnLogin = loginView.getBtnLogin();
		btnLogout = mainUI.getPanelAccount().getBtnLogout();
		btnChangePass = mainUI.getPanelAccount().getBtnChangePass();
		
		setActions();	
	}
	
	/*
	 * Initialize controllers for mainUI
	 */
	private void createController() {
		new ProductController(mainUI);
		new StaffController(mainUI);
		new AddBillController(mainUI);
		new StatisticController(mainUI);
		new ShowDetailBillController(mainUI);
		new DeleteBillController(mainUI);
	}
	
	private void setActions() {
		/* 
		 * Event Login 
		 *  If this account is not administrator, this account will not action on StaffPanel
		 */
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/* If admin is not exists - initial admin */
				if (staffService.getPassAdmin().equals("")) {
					staffService.initAdmin();
				}
				
				account = tfAccount.getText().toString().trim();
				String pass = new String(tfPass.getPassword());
				System.out.println(pass);
				
				/* Login success */
				if (staffService.isLogin(account, pass)) {
					System.out.println("Login success");
					tfPass.setText("");
					loginView.setVisible(false);
					mainUI.setCurrentAccount(account);
					mainUI.setVisible(true);
					if (!account.equals("admin")) {
						/* Staff isn't allowed manage staff */
						mainUI.getTabbedPane().setEnabledAt(1, false);
						mainUI.getManagerBill().getButtonBillView().getBtnAdd().setEnabled(true);
					}
					else {
						mainUI.getTabbedPane().setEnabledAt(1, true);
						mainUI.getManagerBill().getButtonBillView().getBtnAdd().setEnabled(false);
					}
					createController();
					return;
				}
				/* Account or password is incorrect */
				else {
					JOptionPane.showMessageDialog(new JDialog(), "Tài khoản hoặc mật khẩu không đúng - Vui lòng nhập lại");
				}
			}
		});
		
		/*
		 * Logout Event
		 * 	Set current account is null and cancel all mainUI, loginController
		 *  Initialize new loginController
		 */
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int select = JOptionPane.showOptionDialog(null, "Bạn có đăng xuất không?",
						 "Đăng xuất", JOptionPane.YES_NO_OPTION,
						 JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (select == 0) {
					account = "";
					mainUI.setVisible(false);
					mainUI = null;
					loginView.setVisible(false);
					loginView = null;
					MainUI mainUI2 = new MainUI();
					LoginController newLogin = new LoginController(mainUI2);				
					return;
				}
			}
		});
		
		/*
		 * Change Password Event
		 * 	Check old Password and newPasword
		 */
		btnChangePass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePassView changePassView = new ChangePassView(mainUI);
				changePassView.setVisible(true);
				JLabel lbAccount  = changePassView.getLbAccount();
				lbAccount.setText(account);
				JPasswordField tfOldPass   = changePassView.getTfOldPass();
				JPasswordField tfNewPass   = changePassView.getTfNewPass();
				JPasswordField tfReNewPass = changePassView.getTfReNewPass();
				JButton btnChange = changePassView.getBtnChange();
				JButton btnCancel = changePassView.getBtnCancel();
				
				/* Confirm changePass */
				btnChange.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String oldPassFromDB = "";
						/* If account is administrator, we will get password from Admin*/
						if (account.equals("admin")) {
							oldPassFromDB = staffService.getPassAdmin();
						}
						/* Else get password of staff */
						else oldPassFromDB = staffService.getPassEmpl(account);
						
						String oldPass   = new String(tfOldPass.getPassword());
						String newPass   = new String(tfNewPass.getPassword());
						String reNewPass = new String(tfReNewPass.getPassword());
						
						if (!oldPass.equals(oldPassFromDB)) {
							System.out.println(oldPass + "   " + oldPassFromDB);
							JOptionPane.showMessageDialog(new JDialog(), "Mật khẩu cũ không đúng");
						}
						else if(!newPass.equals(reNewPass)) {
							JOptionPane.showMessageDialog(new JDialog(), "Vui lòng nhập lại đúng mật khẩu mới");
						}
						else {
							staffService.changePassFromDB(account, newPass);
							JOptionPane.showMessageDialog(new JDialog(), "Đổi mật khẩu thành công");
							changePassView.setVisible(false);
						}
					}
				});
				
				/* Close changePassView */
				btnCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						tfOldPass.setText("");
						tfNewPass.setText("");
						tfReNewPass.setText("");
						changePassView.setVisible(false);
					}
				});
			}
		});
	}
}
