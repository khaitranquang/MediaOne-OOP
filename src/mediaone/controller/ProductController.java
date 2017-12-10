package mediaone.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import mediaone.dao.ProductRepositoryImpl;
import mediaone.model.Book;
import mediaone.model.FilmCD;
import mediaone.model.MusicCD;
import mediaone.model.Product;
import mediaone.service.BookService;
import mediaone.service.FilmCDService;
import mediaone.service.MusicCDService;
import mediaone.view.AddProductView;
import mediaone.view.EditProductView;
import mediaone.view.MainUI;
import mediaone.view.ProductInformation;
import mediaone.view.TableProductView;

public class ProductController{
	private MainUI mainUI;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JRadioButton radBook;
	private JRadioButton radMusic;
	private JRadioButton radFilm;
	private JTextField tfSearch;
	private JComboBox<String> cbSearch;
	private TableProductView tableProductView;
	
	private BookService bookService;
	private MusicCDService musicCDService;
	private FilmCDService filmCDService;
	private ProductRepositoryImpl productRepositoryImpl = new ProductRepositoryImpl();
	
	List<Book> resultSearchBook;
	List<MusicCD> resultSearchMusic;
	List<FilmCD> resultSearchFilm;
	
	public ProductController(MainUI mainUI){
		this.mainUI = mainUI;
		bookService = new BookService();
		musicCDService = new MusicCDService();
		filmCDService = new FilmCDService();
		
		resultSearchBook = bookService.findAll();
		resultSearchMusic = musicCDService.findAll();
		resultSearchFilm = filmCDService.findAll();
		
		tableProductView = mainUI.getManagerProduct().getTableProductView();
		btnAdd = mainUI.getManagerProduct().getButtonProductView().getBtnAdd();
		btnEdit = mainUI.getManagerProduct().getButtonProductView().getBtnEdit();
		btnDelete = mainUI.getManagerProduct().getButtonProductView().getBtnDelete();
		radBook = mainUI.getManagerProduct().getTableProductView().getRadBook();
		radMusic = mainUI.getManagerProduct().getTableProductView().getRadMusic();
		radFilm = mainUI.getManagerProduct().getTableProductView().getRadFilm();
		tfSearch = mainUI.getManagerProduct().getTableProductView().getTfSearch();
		cbSearch = mainUI.getManagerProduct().getTableProductView().getCbSearch();
		
		/* 
		 * Update View when click radio button
		 */
		radBook.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				cbSearch.removeAllItems();
				cbSearch.addItem("Nhà xuất bản");
				cbSearch.addItem("Tác giả");
				tableProductView.updateTableBook(bookService.findAll());	
			}
		});
		radMusic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cbSearch.removeAllItems();
				cbSearch.addItem("Tên ca sĩ");
				cbSearch.addItem("Thể loại");
				tableProductView.updateTableMusicCD(musicCDService.findAll());
			}
		});
		radFilm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cbSearch.removeAllItems();
				cbSearch.addItem("Đạo diễn");
				cbSearch.addItem("Thể loại");
				tableProductView.updateTableFilmCD(filmCDService.findAll());
			}
		});
		
		tfSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(radBook.isSelected()) {
					actionSearch(0);
				}
				else if (radMusic.isSelected()) {
					actionSearch(1);
				}
				else if (radFilm.isSelected()) {
					actionSearch(2);
				}
			}
		});
		
		/* Add Action - Add product */
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionsAdd();
			}
		});
		
		/* Edit Action - Edit product */
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionsEdit();
			}
		});
		
		/* Action Delete - Delete Product */
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionsDelete();
			}
		});
		
	}
	
	/*
	 * Action search
	 *  create a product template and use findBySpecialProps method of productService
	 */
	private void actionSearch(int radIndexSelected) {
		String textFind = tfSearch.getText().toString();
		int indexCbSearch = cbSearch.getSelectedIndex();
		
		if (indexCbSearch == 0 && !textFind.trim().equals("")) {
			Book bookTemplate = new Book("", "", 0, 0, 0, textFind, "");
			MusicCD musicCDTemplate = new MusicCD("", "", 0, 0, 0, textFind, "");
			FilmCD filmCDTemplate = new FilmCD("", "", 0, 0, 0, textFind, "");
			resultSearchBook = bookService.findBySpecialProps(bookTemplate);
			resultSearchMusic = musicCDService.findBySpecialProps(musicCDTemplate);
			resultSearchFilm = filmCDService.findBySpecialProps(filmCDTemplate);
		}
		else if (indexCbSearch == 1 && !textFind.trim().equals("")) {
			Book bookTemplate = new Book("", "", 0, 0, 0, "", textFind);
			MusicCD musicCDTemplate = new MusicCD("", "", 0, 0, 0, "", textFind);
			FilmCD filmCDTemplate = new FilmCD("", "", 0, 0, 0, "", textFind);
			resultSearchBook = bookService.findBySpecialProps(bookTemplate);
			resultSearchMusic = musicCDService.findBySpecialProps(musicCDTemplate);
			resultSearchFilm = filmCDService.findBySpecialProps(filmCDTemplate);
		}
		else if (textFind.trim().equals("")) {
			resultSearchBook = bookService.findAll();
			resultSearchMusic = musicCDService.findAll();
			resultSearchFilm = filmCDService.findAll();
		}
		/* Update table product */
		if (radIndexSelected == 0) {
			tableProductView.updateTableBook(resultSearchBook);
		}
		else if (radIndexSelected == 1) {
			tableProductView.updateTableMusicCD(resultSearchMusic);
		}
		else if (radIndexSelected == 2) {
			tableProductView.updateTableFilmCD(resultSearchFilm);
		}
	}
	

	/*
	 * Add Product
	 */
	private void actionsAdd() {
		/* Add Book */
		if (radBook.isSelected() && !radFilm.isSelected() && !radMusic.isSelected()) {
			AddProductView addProductView = new AddProductView(mainUI);
			ProductInformation productInformation = addProductView.getProductInformation();
			productInformation.getLbProp1().setText("Nhà xuất bản ");
			productInformation.getLbProp2().setText("Tác giả ");
			addProductView.setVisible(true);
			
			actionAddBook(addProductView);
		}
		/* Add MusicCD */
		if (radMusic.isSelected() && !radFilm.isSelected() && !radBook.isSelected()) {
			AddProductView addProductView = new AddProductView(mainUI);
			ProductInformation productInformation = addProductView.getProductInformation();
			productInformation.getLbProp1().setText("Ca sĩ ");
			productInformation.getLbProp2().setText("Thể loại ");
			addProductView.setVisible(true);
			
			actionAddMusic(addProductView);
		}
		/* Add FilmCD */
		if (radFilm.isSelected() && !radMusic.isSelected() && !radBook.isSelected()) {
			AddProductView addProductView = new AddProductView(mainUI);
			ProductInformation productInformation = addProductView.getProductInformation();
			productInformation.getLbProp1().setText("Đạo diễn ");
			productInformation.getLbProp2().setText("Thể loại ");
			addProductView.setVisible(true);
			
			actionAddFilm(addProductView);
		}
		return;
	}
	
	/* 
	 * Action on AddBook Dialog
	 */
	private void actionAddBook(AddProductView addProductView) {
		JButton btnAddBook = addProductView.getBtnAdd();
		JButton btnReset = addProductView.getBtnReset();
		JButton btnCancel = addProductView.getBtnCancel();
		ProductInformation productInformation = addProductView.getProductInformation();
		/* Add to database */
		btnAddBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String idProduct = productInformation.getTfIdProduct().getText().toString();
					String nameProduct = productInformation.getTfNameProduct().getText().toString();
					String publisher = productInformation.getTfProp1().getText().toString();
					String author = productInformation.getTfProp2().getText().toString();
					int quantity = Integer.parseInt(productInformation.getTfQuantity().getText().toString());
					double outPrice = Double.parseDouble(productInformation.getTfOutPrice().getText().toString());
					double inPrice = Double.parseDouble(productInformation.getTfInPrice().getText().toString());
					Book book = new Book(idProduct, nameProduct, quantity, outPrice, inPrice, publisher, author);
					
					Book bookIsAdd = bookService.add(book);
					if (bookIsAdd == null) {
						JOptionPane.showMessageDialog(new JDialog(), "Các trường dữ liệu không được để trống - Các trường số không âm \n" +
																	 "Mã sản phẩm không được trùng");
						return;
					}
					tableProductView.updateTableBook(bookService.findAll());
					addProductView.setVisible(false);
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(new JDialog(), "Các trường số cần nhập đúng định dạng");
				}
			}
		});
		/* Clear input */
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearInput(productInformation);
			}
		});
		/* Close Dialog */
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearInput(productInformation);
				addProductView.setVisible(false);
			}
		});
	}
	
	/*
	 * Actions on Add MusicCD Dialog
	 */
	private void actionAddMusic(AddProductView addProductView) {
		JButton btnAddMusic = addProductView.getBtnAdd();
		JButton btnReset = addProductView.getBtnReset();
		JButton btnCancel = addProductView.getBtnCancel();
		ProductInformation productInformation = addProductView.getProductInformation();
		/* Add to database */
		btnAddMusic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String idProduct = productInformation.getTfIdProduct().getText().toString();
					String nameProduct = productInformation.getTfNameProduct().getText().toString();
					String singerName = productInformation.getTfProp1().getText().toString();
					String type = productInformation.getTfProp2().getText().toString();
					int quantity = Integer.parseInt(productInformation.getTfQuantity().getText().toString());
					double outPrice = Double.parseDouble(productInformation.getTfOutPrice().getText().toString());
					double inPrice = Double.parseDouble(productInformation.getTfInPrice().getText().toString());
					
					MusicCD musicCD = new MusicCD(idProduct, nameProduct, quantity, outPrice, inPrice, singerName, type);
					MusicCD musicIsAdd = musicCDService.add(musicCD);
					if (musicIsAdd == null) {
						JOptionPane.showMessageDialog(new JDialog(), "Các trường dữ liệu không được để trống - Các trường số không âm \n" +
								 "Mã sản phẩm không được trùng");
						return;
					}
					tableProductView.updateTableMusicCD(musicCDService.findAll());
					addProductView.setVisible(false);
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(new JDialog(), "Các trường số cần nhập đúng định dạng");
				}
				
			}
		});
		/* Clear input */
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearInput(productInformation);
			}
		});
		/* Close Dialog */
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearInput(productInformation);
				addProductView.setVisible(false);
			}
		});
	}
	
	/*
	 * Actions on Add FilmCD Dialog
	 */
	private void actionAddFilm(AddProductView addProductView) {
		JButton btnAddFilm = addProductView.getBtnAdd();
		JButton btnReset = addProductView.getBtnReset();
		JButton btnCancel = addProductView.getBtnCancel();
		ProductInformation productInformation = addProductView.getProductInformation();
		/* Add to database */
		btnAddFilm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String idProduct = productInformation.getTfIdProduct().getText().toString();
					String nameProduct = productInformation.getTfNameProduct().getText().toString();
					String director = productInformation.getTfProp1().getText().toString();
					String type = productInformation.getTfProp2().getText().toString();
					int quantity = Integer.parseInt(productInformation.getTfQuantity().getText().toString());
					double outPrice = Double.parseDouble(productInformation.getTfOutPrice().getText().toString());
					double inPrice = Double.parseDouble(productInformation.getTfInPrice().getText().toString());
					
					FilmCD filmCD = new FilmCD(idProduct, nameProduct, quantity, outPrice, inPrice, director, type);
					FilmCD filmIsAdd = filmCDService.add(filmCD);
					if (filmIsAdd == null) {
						JOptionPane.showMessageDialog(new JDialog(), "Các trường dữ liệu không được để trống - Các trường số không âm \n" +
								 "Mã sản phẩm không được trùng");
						return;
					}
					tableProductView.updateTableFilmCD(filmCDService.findAll());
					addProductView.setVisible(false);
				}
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(new JDialog(), "Các trường số cần nhập đúng định dạng");

				}
			}
		});
		/* Clear input */
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearInput(productInformation);
			}
		});
		/* Close Dialog */
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearInput(productInformation);
				addProductView.setVisible(false);
			}
		});
	}
	
	/* -------------------------------------------------------------------
	 * Edit Product
	 * -------------------------------------------------------------------
	 */
	private void actionsEdit() {
		/* Edit Book */
		if (radBook.isSelected() && !radFilm.isSelected() && !radMusic.isSelected()) {
			EditProductView editProductView = new EditProductView(mainUI);
			ProductInformation productInformation = editProductView.getProductInformation();
			productInformation.getLbProp1().setText("Nhà xuất bản ");
			productInformation.getLbProp2().setText("Tác giả ");
			
			int indexOfRow = findIndexOfData();
			if (indexOfRow >= 0) {
				String id = getValueFromTable(indexOfRow, 0);
				loadInfor(indexOfRow, id, productInformation);
				actionEditBook(editProductView);
			}
			else {
				JOptionPane.showMessageDialog(new JDialog(), "chọn 1 sản phẩm để sửa");
			}
		}
		/* Edit MusicCD */
		if (radMusic.isSelected() && !radFilm.isSelected() && !radBook.isSelected()) {
			EditProductView editProductView = new EditProductView(mainUI);
			ProductInformation productInformation = editProductView.getProductInformation();
			productInformation.getLbProp1().setText("Ca sĩ ");
			productInformation.getLbProp2().setText("Thể loại ");
			
			int indexOfRow = findIndexOfData();
			if (indexOfRow >= 0) {
				String id = getValueFromTable(indexOfRow, 0);
				loadInfor(indexOfRow, id, productInformation);
				actionEditMusic(editProductView);
			}
			else {
				JOptionPane.showMessageDialog(new JDialog(), "chọn 1 sản phẩm để sửa");
			}
			
		}
		/* Add FilmCD */
		if (radFilm.isSelected() && !radMusic.isSelected() && !radBook.isSelected()) {
			EditProductView editProductView = new EditProductView(mainUI);
			ProductInformation productInformation = editProductView.getProductInformation();
			productInformation.getLbProp1().setText("Đạo diễn ");
			productInformation.getLbProp2().setText("Thể loại ");
			
			int indexOfRow = findIndexOfData();
			if (indexOfRow >= 0) {
				String id = getValueFromTable(indexOfRow, 0);
				loadInfor(indexOfRow, id, productInformation);
				actionEditFilm(editProductView);
			}
			else {
				JOptionPane.showMessageDialog(new JDialog(), "Chọn 1 sản phẩm để sửa");
			}
			
		}
		return;
	}
	
	/* Find index of row is selected */
	private int findIndexOfData() {
		int index = tableProductView.getTable().getSelectedRow();
		return index;
	}
	
	/* Get value from table */
	private String getValueFromTable(int indexRow, int indexCol) {
		JTable table = tableProductView.getTable();
		String id = table.getModel().getValueAt(indexRow, indexCol).toString();
		return id;
	}
	
	/*
	 * Action Edit Book
	 */
	private void actionEditBook(EditProductView editProductView) {
		ProductInformation productInformation = editProductView.getProductInformation();
		editProductView.setVisible(true);
		editProductView.getProductInformation().getTfIdProduct().setEditable(false);
		JButton btnEdit = editProductView.getBtnEdit();
		JButton btnCancel = editProductView.getBtnCancel();
		
		/* Edit Book */
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String idProduct = productInformation.getTfIdProduct().getText().toString();
					String nameProduct = productInformation.getTfNameProduct().getText().toString();
					String publisher = productInformation.getTfProp1().getText().toString();
					String author = productInformation.getTfProp2().getText().toString();
					int quantity = Integer.parseInt(productInformation.getTfQuantity().getText().toString());
					double outPrice = Double.parseDouble(productInformation.getTfOutPrice().getText().toString());
					double inPrice = Double.parseDouble(productInformation.getTfInPrice().getText().toString());
					
					Book book = new Book(idProduct, nameProduct, quantity, outPrice, inPrice, publisher, author);
					
					Book bookIsUpdate = bookService.update(book);
					if (bookIsUpdate == null) {
						JOptionPane.showMessageDialog(new JDialog(), "Các trường dữ liệu không được để trống - Các trường số không âm \n" +
																	 "Mã sản phẩm không được trùng");
						return;
					}
					tableProductView.updateTableBook(bookService.findAll());
					editProductView.setVisible(false);
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(new JDialog(), "Các trường số cần nhập đúng định dạng");
				}
			}
		});
		/* Cancel */
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editProductView.setVisible(false);
			}
		});	
	}
	
	/*
	 * Action Edit Music
	 */
	private void actionEditMusic(EditProductView editProductView) {
		ProductInformation productInformation = editProductView.getProductInformation();
		editProductView.setVisible(true);
		JButton btnEdit = editProductView.getBtnEdit();
		JButton btnCancel = editProductView.getBtnCancel();
		
		/* Edit Book */
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String idProduct = productInformation.getTfIdProduct().getText().toString();
					String nameProduct = productInformation.getTfNameProduct().getText().toString();
					String singer = productInformation.getTfProp1().getText().toString();
					String type = productInformation.getTfProp2().getText().toString();
					int quantity = Integer.parseInt(productInformation.getTfQuantity().getText().toString());
					double outPrice = Double.parseDouble(productInformation.getTfOutPrice().getText().toString());
					double inPrice = Double.parseDouble(productInformation.getTfInPrice().getText().toString());
					
					MusicCD musicCD = new MusicCD(idProduct, nameProduct, quantity, outPrice, inPrice, singer, type);
					MusicCD musicCDIsUpdate = musicCDService.update(musicCD);
					if (musicCDIsUpdate == null) {
						JOptionPane.showMessageDialog(new JDialog(), "Các trường dữ liệu không được để trống - Các trường số không âm \n" +
																	 "Mã sản phẩm không được trùng");
						return;
					}
					tableProductView.updateTableMusicCD(musicCDService.findAll());
					editProductView.setVisible(false);
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(new JDialog(), "Các trường số cần nhập đúng định dạng");
				}
			}
		});
		/* Cancel */
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editProductView.setVisible(false);
			}
		});	
	}
	
	/*
	 * Action Edit Film
	 */
	private void actionEditFilm(EditProductView editProductView) {
		ProductInformation productInformation = editProductView.getProductInformation();
		editProductView.setVisible(true);
		JButton btnEdit = editProductView.getBtnEdit();
		JButton btnCancel = editProductView.getBtnCancel();
		
		/* Edit Book */
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String idProduct = productInformation.getTfIdProduct().getText().toString();
					String nameProduct = productInformation.getTfNameProduct().getText().toString();
					String director = productInformation.getTfProp1().getText().toString();
					String type = productInformation.getTfProp2().getText().toString();
					int quantity = Integer.parseInt(productInformation.getTfQuantity().getText().toString());
					double outPrice = Double.parseDouble(productInformation.getTfOutPrice().getText().toString());
					double inPrice = Double.parseDouble(productInformation.getTfInPrice().getText().toString());
					
					FilmCD musicCD = new FilmCD(idProduct, nameProduct, quantity, outPrice, inPrice, director, type);
					FilmCD filmCDIsUpdate = filmCDService.update(musicCD);
					if (filmCDIsUpdate == null) {
						JOptionPane.showMessageDialog(new JDialog(), "Các trường dữ liệu không được để trống - Các trường số không âm \n" +
																	 "Mã sản phẩm không được trùng");
						return;
					}
					tableProductView.updateTableFilmCD(filmCDService.findAll());
					editProductView.setVisible(false);
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(new JDialog(), "Các trường số cần nhập đúng định dạng");
				}
			}
		});
		/* Cancel */
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editProductView.setVisible(false);
			}
		});	
	}
	
	/* -------------------------------------------------------------------
	 * Delete Product
	 * -------------------------------------------------------------------
	 */
	private void actionsDelete() {
		/* Delete Book */
		if (radBook.isSelected() && !radFilm.isSelected() && !radMusic.isSelected()) {
			int indexOfRow = findIndexOfData();
			if (indexOfRow >= 0) {
				int select = JOptionPane.showOptionDialog(null, "Bạn có muốn xoá sản phẩm này không?",
						 "Xoá", JOptionPane.YES_NO_OPTION,
						 JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (select == 0) {
					String id = getValueFromTable(indexOfRow, 0);
					bookService.remove(id);
					tableProductView.updateTableBook(bookService.findAll());
				}
			
			}
			else {
				JOptionPane.showMessageDialog(new JDialog(), "chọn 1 sản phẩm để xóa");
			}
		}
		/* Delete MusicCD */
		if (radMusic.isSelected() && !radFilm.isSelected() && !radBook.isSelected()) {
			int indexOfRow = findIndexOfData();
			if (indexOfRow >= 0) {
				int select = JOptionPane.showOptionDialog(null, "Bạn có muốn xoá sản phẩm này không?",
						 "Xoá", JOptionPane.YES_NO_OPTION,
						 JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (select == 0) {
					String id = getValueFromTable(indexOfRow, 0);
					bookService.remove(id);
					tableProductView.updateTableMusicCD(musicCDService.findAll());
				}
			
			}
			else {
				JOptionPane.showMessageDialog(new JDialog(), "chọn 1 sản phẩm để xóa");
			}
		}
		/* Delete FilmCD */
		if (radFilm.isSelected() && !radMusic.isSelected() && !radBook.isSelected()) {
			int indexOfRow = findIndexOfData();
			if (indexOfRow >= 0) {
				int select = JOptionPane.showOptionDialog(null, "Bạn có muốn xoá sản phẩm này không?",
						 "Xoá", JOptionPane.YES_NO_OPTION,
						 JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (select == 0) {
					String id = getValueFromTable(indexOfRow, 0);
					filmCDService.remove(id);
					tableProductView.updateTableFilmCD(filmCDService.findAll());
				}
			
			}
			else {
				JOptionPane.showMessageDialog(new JDialog(), "chọn 1 sản phẩm để xóa");
			}	
		}
		return;
	}
	
	private void loadInfor (int indexOfRow, String id, ProductInformation productInformation) {
		Product product = productRepositoryImpl.findOne(id);
		productInformation.getTfIdProduct().setText(id);
		productInformation.getTfNameProduct().setText(product.getNameProduct());
		productInformation.getTfOutPrice().setText(product.getOutPrice() + "");
		productInformation.getTfInPrice().setText(product.getInPrice() + "");
		productInformation.getTfQuantity().setText(product.getQuantity() + "");
		productInformation.getTfProp1().setText(getValueFromTable(indexOfRow, 2));
		productInformation.getTfProp2().setText(getValueFromTable(indexOfRow, 3));
	}
	
	private void clearInput(ProductInformation productInformation) {
		productInformation.getTfIdProduct().setText("");
		productInformation.getTfNameProduct().setText("");
		productInformation.getTfProp1().setText("");
		productInformation.getTfProp2().setText("");
		productInformation.getTfQuantity().setText("");
		productInformation.getTfOutPrice().setText("");
		productInformation.getTfInPrice().setText("");
	}
}
