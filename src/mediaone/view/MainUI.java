package mediaone.view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainUI extends JFrame{
	private PanelManagerProduct managerProduct;
	private PanelManagerStaff managerStaff;
	private PanelManagerBill managerBill;
	private PanelAccount panelAccount;
	private String currentAccount = "";
	
	public String getCurrentAccount() {
		return currentAccount;
	}
	public void setCurrentAccount(String currentAccount) {
		this.currentAccount = currentAccount;
	}
	public PanelManagerProduct getManagerProduct() {
		return managerProduct;
	}
	public PanelManagerStaff getManagerStaff() {
		return managerStaff;
	}
	public PanelManagerBill getManagerBill() {
		return managerBill;
	}
	public PanelAccount getPanelAccount() {
		return panelAccount;
	}
	
	public MainUI() {
		createGUI();
		setDisplay();
	}
	
	private void setDisplay() {
		setTitle("MedieOne - Nhóm..." + "Account " + currentAccount);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 600);
		setLocationRelativeTo(null);
		setVisible(false);
	}
	
	private void createGUI() {
		add(createTabbedPane());
	}
	
	private JTabbedPane createTabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane();
		managerProduct = new PanelManagerProduct();
		tabbedPane.addTab("Quản lí sản phẩm", null, managerProduct, "Quản lí sản phẩm");
		managerStaff = new PanelManagerStaff();
		tabbedPane.addTab("Quản lí nhân viên", null, managerStaff, "Quản lí nhân viên");
		managerBill = new PanelManagerBill();
		tabbedPane.addTab("Quản lí bán hàng", null, managerBill, "Quản lí bán hàng");
		panelAccount = new PanelAccount();
		tabbedPane.addTab("Quản lí tài khoản", null, panelAccount, "Quản lí tài khoảnn");
		
		return tabbedPane;
	}
	
	public static void main(String[] args) {
		MainUI main = new MainUI();
	}
}
