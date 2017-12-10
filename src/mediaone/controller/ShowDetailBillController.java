package mediaone.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import mediaone.dao.BillRepositoryImpl;
import mediaone.model.Bill;
import mediaone.model.DetailBill;
import mediaone.model.Product;
import mediaone.service.BillServiceImpl;
import mediaone.service.StaffService;
import mediaone.service.TransactionServiceImpl;
import mediaone.view.DetailInformation;
import mediaone.view.DetailView;
import mediaone.view.MainUI;
import mediaone.view.TableBillView;
import mediaone.view.TableDetailView;

public class ShowDetailBillController {
	private MainUI mainUI;
	private DetailBill detailBill;
	private TransactionServiceImpl transactionServiceImpl;
	private BillServiceImpl billServiceImpl;
	private BillRepositoryImpl billRepositoryImpl;
	
	private DetailView detailView;
	private DetailInformation detailInformation;
	private JButton btnShowDetail;
	private TableDetailView tableDetailView;
	private TableBillView tableBillView;
	
	public ShowDetailBillController(MainUI mainUI) {
		this.mainUI = mainUI;
		billServiceImpl = new BillServiceImpl();
		billRepositoryImpl = new BillRepositoryImpl();
		transactionServiceImpl = new TransactionServiceImpl();
		tableBillView = mainUI.getManagerBill().getTableBillView();
		btnShowDetail = mainUI.getManagerBill().getButtonBillView().getBtnDetail();
		
		btnShowEvent();
	}
	
	/*
	 * Handle event for btnShowDetail
	 */
	private void btnShowEvent() {
		btnShowDetail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				detailView = new DetailView(mainUI);
				detailInformation = detailView.getDetailInformation();
				tableDetailView = detailView.getDetailInformation().getTabelDetailView();
				
				int index = findIndexOfData();
				if (index >= 0) {
					detailView.setVisible(true);
					int idBill = Integer.parseInt(getMaMT(index, 0));
					loadInfor(idBill);
					setAction();
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Chọn 1 hóa đơn để xem chi tiết");
				}
			}
		});
	}
		
	/* Find index of row is selected */
	private int findIndexOfData() {
		int index = tableBillView.getTable().getSelectedRow();
		return index;
	}
		
	/* Get maMT of row is selected */
	private String getMaMT (int indexRow, int indexCol) {
		JTable table = tableBillView.getTable();
		String maMT  = table.getModel().getValueAt(indexRow, indexCol).toString();
		return maMT;
	}
	
	private void loadInfor (int idBill) {
		List<Bill> listBill = billServiceImpl.findAll();
		Bill bill = null;;
		for (int i = 0; i < listBill.size(); i++) {
			if (listBill.get(i).getIdBill() == idBill) bill = listBill.get(i);
		}
		detailInformation.getLbIdBill().setText(idBill + "");
		detailInformation.getLbIdStaff().setText(bill.getIdStaff());
		detailInformation.getLbNameStaff().setText(new StaffService().findOne(bill.getIdStaff()).getNameStaff());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate exportDateTime = bill.getExportDate();
		String exportDate = exportDateTime.format(formatter);
		detailInformation.getLbExportDate().setText(exportDate);
		
		List<Product> listProduct = bill.getDetailBill().getProducts();
		String[][] listProductIsBuy = new String[listProduct.size()][3];
		for (int i = 0; i < listProduct.size(); i++) {
			Product product = listProduct.get(i);
			listProductIsBuy[i][0] = product.getIdProduct();
			listProductIsBuy[i][1] = product.getOutPrice() + "";
			listProductIsBuy[i][2] = product.getQuantity() + "";
		}
		
		for (int i = 0; i < listProductIsBuy.length; i++) {
			System.out.println(listProductIsBuy[i][0] + " out: " + listProductIsBuy[i][1] + "so luong: " + listProductIsBuy[i][1]);
		}
		
		tableDetailView.updateTable(listProductIsBuy);
		
		Double price = bill.getDetailBill().getPrice();
		detailInformation.getLbPrice().setText(price + "");
	}
	
	private void setAction() {
		JButton btnCancel = detailView.getBtnCancel();
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				detailView.setVisible(false);
			}
		});
	}
}
