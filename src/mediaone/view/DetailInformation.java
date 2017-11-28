package mediaone.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DetailInformation extends JPanel{
	private JLabel lbIdBill;
	private JLabel lbIdStaff;
	private JLabel lbNameStaff;
	
	private TableDetailView tableDetailView;
	
	private JLabel lbExportDate;
	private JLabel lbPrice;
	
	public JLabel getLbIdBill() {
		return lbIdBill;
	}
	public JLabel getLbIdStaff() {
		return lbIdStaff;
	}
	public JLabel getLbNameStaff() {
		return lbNameStaff;
	}
	public TableDetailView getTabelDetailView() {
		return tableDetailView;
	}
	public JLabel getLbExportDate() {
		return lbExportDate;
	}
	public JLabel getLbPrice() {
		return lbPrice;
	}
	
	public DetailInformation() {
		setLayout(new BorderLayout());
		add(createHeaderPanel(), BorderLayout.PAGE_START);
		add(createCenterPanel(),  BorderLayout.CENTER);
		add(createFooterPanel(), BorderLayout.PAGE_END);
		
	}
	
	private JPanel createHeaderPanel() {
		JPanel header = new JPanel (new GridLayout(5, 2, 10, 10));
		header.add(new JLabel("Mã mượn trả:      "));	  		header.add(lbIdBill    = new JLabel());
		header.add(new JLabel("Mã nhân viên:     "));			header.add(lbIdStaff   = new JLabel());
		header.add(new JLabel("Họ tên nhân viên: "));			header.add(lbNameStaff = new JLabel());
		return header;
	}
	
	private JPanel createCenterPanel() {
		JPanel center = new JPanel (new BorderLayout());
		center.add(createTitlePanel(), BorderLayout.PAGE_START);
		tableDetailView = new TableDetailView();
		center.add(tableDetailView, BorderLayout.CENTER);
		return center;
	}
	
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel();
		JLabel lbTitle  = new JLabel("DANH SÁCH SẢN PHẨM MUA");
		lbTitle.setFont(new Font("Caribli", Font.BOLD, 22));
		lbTitle.setForeground(Color.BLUE);
		lbTitle.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(lbTitle);
		return panel;
	}
	
	private JPanel createFooterPanel() {
		JPanel footer = new JPanel(new GridLayout(7, 2, 10, 10));
		footer.add(new JLabel("Ngày mua: "));			footer.add(lbExportDate = new JLabel());
		footer.add(new JLabel("Thành tiền "));   		footer.add(lbPrice = new JLabel());
		return footer;
	}
}
