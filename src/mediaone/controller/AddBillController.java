package mediaone.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import mediaone.dao.BillRepository;
import mediaone.dao.BillRepositoryImpl;
import mediaone.model.Bill;
import mediaone.model.DetailBill;
import mediaone.model.Product;
import mediaone.service.BillServiceImpl;
import mediaone.service.BookService;
import mediaone.service.FilmCDService;
import mediaone.service.MusicCDService;
import mediaone.service.StaffService;
import mediaone.service.TransactionService;
import mediaone.service.TransactionServiceImpl;
import mediaone.view.AddBillView;
import mediaone.view.BillInformation;
import mediaone.view.MainUI;
import mediaone.view.ProductIsBuyView;
import mediaone.view.TableBillView;
import mediaone.view.TableProductView;

/*
 * AddBillController - add a new bll
 */
public class AddBillController {
	private MainUI mainUI;
	private BillServiceImpl billServiceImpl;
	private TransactionService  transactionServiceImpl;
	private StaffService staffService;
	
	private AddBillView addBillView;
	private BillInformation billInformation;
	private TableBillView tableBillView;
	
	private List<Product> listProductIsBuy = new ArrayList<Product>(); 
	private JPanel rightPanel;
	private List<ProductIsBuyView> arrProductIsBuyView = new ArrayList<ProductIsBuyView>();
	
	public AddBillController(MainUI mainUI) {
		this.mainUI = mainUI;
		billServiceImpl = new BillServiceImpl();
		transactionServiceImpl = new TransactionServiceImpl();
		staffService = new StaffService();
		JButton btnAddBillManager = mainUI.getManagerBill().getButtonBillView().getBtnAdd();
		/* Update Table */
		tableBillView = mainUI.getManagerBill().getTableBillView();
		tableBillView.updateTable(billServiceImpl.findAll());
		
		/*
		 * Set action for btnAddBill on billManagerPanel
		 */
		btnAddBillManager.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/* Define views:
				 * 	addBillView - main dialog, add a bill
				 *  billInformation - information of bill
				 *  rightPanel - show all products is selected
				 */
				addBillView = new AddBillView(mainUI);
				billInformation = addBillView.getBillInformation();
				rightPanel = billInformation.getRightPanel();
				
				/* Get current staff who login, and current day */
				String idCurrentStaff = mainUI.getCurrentAccount();
				billInformation.getLbIdStaff().setText(idCurrentStaff);
				billInformation.getLbNameStaff().setText(staffService.findOne(idCurrentStaff).getNameStaff());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate now = LocalDate.now();
				billInformation.getTfExportDate().setText(now.format(formatter));
				billInformation.getTfExportDate().setEditable(false);
				
				/* Set action for btnAddProductIsBuy */
				JButton btnAddProductIsBuy = billInformation.getBtnAddThisProduct();
				btnAddProductIsBuy.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setActionForBtnAddProductIsBuy();
						setActionOnRightPanel();
					}
				});
				
				addBillView.setVisible(true);
				setMainActions();
			}
		});
	}
	
	/*
	 * Set Action for btnAddProductIsBuy
	 *  Check product is available ?
	 */
	private void setActionForBtnAddProductIsBuy() {
		try {
			String idProduct = billInformation.getTfIdProduct().getText().toString();
			int number = Integer.parseInt(billInformation.getTfQuantity().getText().toString());
			Product productIsBuy = transactionServiceImpl.addProductToBill(idProduct, number);
			
			if (productIsBuy == null) {
				JOptionPane.showMessageDialog(new JDialog(), "Sản phẩm này không khả dụng \n"
														   + "Nhập lại đúng mã hàng và số lượng \n"
														   + "Chú ý mã phân biệt hoa thường");
				return;
			}
			
			/* Add this product to list and show it on rightPanel */
			listProductIsBuy.add(productIsBuy);
			ProductIsBuyView productIsBuyView = new ProductIsBuyView(idProduct, productIsBuy.getOutPrice() + "", number + "");
			arrProductIsBuyView.add(productIsBuyView);
			rightPanel.add(productIsBuyView);
			
			rightPanel.revalidate();
			rightPanel.repaint();
			rightPanel.setVisible(true);
			billInformation.getTfIdProduct().setText("");
			billInformation.getTfQuantity().setText("");	
		}
		catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(new JDialog(), "Nhập đúng định dạng trường số");
			return;
		}	
	}
	
	/*
	 * Action: right-click to remove product is buy
	 */
	private void setActionOnRightPanel() {
		for (int i = 0; i < arrProductIsBuyView.size(); i++) {
			ProductIsBuyView productIsBuyView = arrProductIsBuyView.get(i);
			JMenuItem menuItem    = new JMenuItem("Remove");
		    JPopupMenu menuPopup  = new JPopupMenu();
		    menuPopup.add(menuItem);
		    productIsBuyView.setComponentPopupMenu(menuPopup);
		   
		    String idProduct = productIsBuyView.getLbIdProduct().getText().toString();
		    
		    menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int j = 0; j < listProductIsBuy.size(); j++) {
						if (listProductIsBuy.get(j).getIdProduct().equals(idProduct)) 
							listProductIsBuy.remove(j);
					}
					arrProductIsBuyView.remove(productIsBuyView);
					rightPanel.remove(productIsBuyView);
					rightPanel.revalidate();
					rightPanel.repaint();
				}
			});
		}
	}
	
	/**
	 * Set MainAction - Add Bill and list detail bill to database
	 */
	private void setMainActions() {
		JButton btnAddBill = addBillView.getBtnAdd();
		JButton btnReset = addBillView.getBtnReset();
		JButton btnCancel = addBillView.getBtnCancel();
		
		/* Set Action for each button */
		btnAddBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addBill();
			}
		});
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearInput();
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearInput();
				addBillView.setVisible(false);
			}
		});
	}
	
	/*
	 * Add bill (and detail bill)
	 */
	private void addBill() {
		BillRepository billRepositoryImpl = new BillRepositoryImpl();
		String idStaff = billInformation.getLbIdStaff().getText().toString();
		System.out.println(idStaff);
		LocalDate exportDate = LocalDate.now();
		
		Bill newBill = new Bill();
		newBill.setIdStaff(idStaff);
		newBill.setExportDate(exportDate);
		DetailBill detailBill = new DetailBill();
		detailBill.setProducts(listProductIsBuy);
		
		for (int i = 0; i < listProductIsBuy.size(); i++) {
			System.out.println(listProductIsBuy.get(i).getIdProduct() + " ID product"
							 + "So luong " + listProductIsBuy.get(i).getQuantity() 
							 + "Gia ban " + listProductIsBuy.get(i).getOutPrice());
		}
			
		newBill.setDetailBill(detailBill);
		billRepositoryImpl.add(newBill);
		Double priceOfBill = transactionServiceImpl.pay(detailBill);
		System.out.println("Price: " + priceOfBill);
		
		TableProductView tableProductView = mainUI.getManagerProduct().getTableProductView();
		tableProductView.updateTableBook(new BookService().findAll());
		tableProductView.updateTableMusicCD(new MusicCDService().findAll());
		tableProductView.updateTableFilmCD(new FilmCDService().findAll());
		clearInput();
		tableBillView.updateTable(billServiceImpl.findAll());
		JOptionPane.showMessageDialog(new JDialog(), "Tổng tiền khách phải thanh toán là: " + priceOfBill);
		addBillView.setVisible(false);
	}
	
	private void clearInput() {
		billInformation.getTfIdProduct().setText("");
		billInformation.getTfQuantity().setText("");
		arrProductIsBuyView.clear();
		listProductIsBuy.clear();
	}
}
