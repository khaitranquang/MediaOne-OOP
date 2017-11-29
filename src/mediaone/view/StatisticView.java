package mediaone.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatisticView extends JDialog{
	private MainUI mainUI;
	
	private String[] date = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", 
			 "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
			 "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	private String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", 
			  "11", "12"};
	private String[] year = {"2017", "2018", "2019", "2020"};
	
	private JComboBox<String> cbStartDay = new JComboBox<String>(date);
	private JComboBox<String> cbStartMonth = new JComboBox<>(month);
	private JComboBox<String> cbStartYear = new JComboBox<>(year);
	
	private JComboBox<String> cbEndDay = new JComboBox<String>(date);
	private JComboBox<String> cbEndMonth = new JComboBox<>(month);
	private JComboBox<String> cbEndYear = new JComboBox<>(year);
	
	private JLabel lbNumberOfBill = new JLabel();
	private JLabel lbMoney = new JLabel();
	
	private JButton btnStatistic = new JButton("Thống kê danh thu");
	private JButton btnCancel = new JButton("Hủy bỏ");
	
	public JLabel getLbNumberOfBill() {
		return lbNumberOfBill;
	}
	public JLabel getLbMoney() {
		return lbMoney;
	}
	public JComboBox<String> getCbStartDay() {
		return cbStartDay;
	}
	public JComboBox<String> getCbStartMonth() {
		return cbStartMonth;
	}
	public JComboBox<String> getCbStartYear() {
		return cbStartYear;
	}
	public JComboBox<String> getCbEndDay() {
		return cbEndDay;
	}
	public JComboBox<String> getCbEndMonth() {
		return cbEndMonth;
	}
	public JComboBox<String> getCbEndYear() {
		return cbEndYear;
	}
	public JButton getBtnStatistic() {
		return btnStatistic;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	
	public StatisticView(MainUI mainUI) {
		this.mainUI = mainUI;
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(true);
		setTitle("Thống kê doanh thu");
		add(createComboPanel(), BorderLayout.CENTER);
		add(createButtonPanel(), BorderLayout.PAGE_END);
		pack();
		setLocationRelativeTo(null);
	}
	
	private JPanel createComboPanel() {
		JPanel panel = new JPanel (new BorderLayout(10, 10));
		panel.add(createLbPanel(), BorderLayout.WEST);
		panel.add(createCbDate(), BorderLayout.CENTER);
		panel.add(createResultPanel(), BorderLayout.PAGE_END);
		return panel;
	}
	
	private JPanel createLbPanel() {
		JPanel lbPanel = new JPanel (new GridLayout(2, 1, 5, 5));
		lbPanel.add(new JLabel("Ngày bắt đầu "));
		lbPanel.add(new JLabel("Ngày kết thúc "));
		return lbPanel;
	}
	
	private JPanel createCbDate() {
		JPanel cbPanel = new JPanel (new GridLayout(2, 1, 5, 5));
		cbPanel.add(createStartDate());
		cbPanel.add(createEndDate());
		return cbPanel;
	}
	
	private JPanel createStartDate() {
		JPanel startDatePanel = new JPanel(new GridLayout(1, 5, 5, 5));
		startDatePanel.add(cbStartDay);
		startDatePanel.add(new JLabel(" - "));
		startDatePanel.add(cbStartMonth);
		startDatePanel.add(new JLabel(" - "));
		startDatePanel.add(cbStartYear);
		return startDatePanel;
	}
	
	private JPanel createEndDate() {
		JPanel endDatePanel = new JPanel(new GridLayout(1, 5, 5, 5));
		endDatePanel.add(cbEndDay);
		endDatePanel.add(new JLabel(" - "));
		endDatePanel.add(cbEndMonth);
		endDatePanel.add(new JLabel(" - "));
		endDatePanel.add(cbEndYear);
		return endDatePanel;
	}
	
	private JPanel createResultPanel() {
		JPanel resultPanel = new JPanel(new GridLayout(2, 2, 5, 5));
		resultPanel.add(new JLabel("Số hóa đơn: "));
		resultPanel.add(lbNumberOfBill);
		resultPanel.add(new JLabel("Doanh thu: "));
		resultPanel.add(lbMoney);
		return resultPanel;
	}
	
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10 ,10));
		buttonPanel.add(btnStatistic);
		buttonPanel.add(btnCancel);
		return buttonPanel;
	}
}
