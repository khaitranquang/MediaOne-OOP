package mediaone.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ButtonBillView extends JPanel{
	private JButton btnAdd = new JButton("Thêm hóa đơn");
	private JButton btnStatistic = new JButton("Thống kê");
	private JButton btnDetail = new JButton("Xem chi tiết");
	private JButton btnDelete = new JButton("Xóa");
	
	public JButton getBtnAdd() {
		return btnAdd;
	}
	public JButton getBtnDetail() {
		return btnDetail;
	}
	public JButton getBtnDelete() {
		return btnDelete;
	}
	public JButton getBtnStatistic() {
		return btnStatistic;
	}
	
	public ButtonBillView() {
		setLayout(new BorderLayout(10, 10));
		setBorder(new EmptyBorder(50, 200, 45, 200));
		add (createButtonPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
		
		btnAdd.setIcon(new ImageIcon(this.getClass().getResource("/mediaone/resource/add-buy-icon.png")));
		btnStatistic.setIcon(new ImageIcon(this.getClass().getResource("/mediaone/resource/chart-add-icon.png")));
		btnDetail.setIcon(new ImageIcon(this.getClass().getResource("/mediaone/resource/detail-icon.png")));
		btnDelete.setIcon(new ImageIcon(this.getClass().getResource("/mediaone/resource/cancel-icon.png")));
		
		buttonPanel.add(btnAdd);
		buttonPanel.add(btnStatistic);
		buttonPanel.add(btnDetail);
		buttonPanel.add(btnDelete);
		return buttonPanel;
	}
}
