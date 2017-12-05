package mediaone.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mediaone.model.Book;
import mediaone.model.FilmCD;
import mediaone.model.MusicCD;

public class TableProductView extends JPanel{
	public static final int TABLE_PRODUCT_WIDTH  = 950;
	public static final int TABLE_PRODUCT_HEIGHT = 350;
	
	private JTable table;
	private String[] defaultTitle = {"Mã sản phẩm", "Tên sản phẩm", "Thuộc tính 1", "Thuộc tính 2",
									"Số lượng", "Giá bán", "Giá nhập"};
	private String[] titleBook = {"Mã sản phẩm", "Tên sản phẩm", "Nhà xuất bản", "Tác giả",
								  "Số lượng", "Gía bán", "Giá nhập"};
	private String[] titleMusic = {"Mã sản phẩm", "Tên sản phẩm", "Tên ca sĩ", "Thể loại",
			  					   "Số lượng", "Giá bán", "Giá nhập"};
	private String[] titleFilm = {"Mã sản phẩm", "Tên sản phẩm", "Đạo diễn", "Thể loại",
			  					  "Số lượng", "Giá bán", "Giá nhập"};
	private int mode = 0;
	
	private JRadioButton radBook = new JRadioButton("Sách", false);
	private JRadioButton radMusic = new JRadioButton("Nhạc", false);
	private JRadioButton radFilm = new JRadioButton("Phim", false);
	
	private String[] searchBook = {"Nhà xuất bản", "Tác giả"};
	private String[] searchMusicCD = {"Tên ca sĩ", "Thể loại"};
	private String[] searchFilmCD = {"Đạo diễn", "Thể loại"};
	private JTextField tfSearch = new JTextField();
	private JComboBox<String> cbSearch;
	
	public JTable getTable() {
		return table;
	}
	public JRadioButton getRadBook() {
		return radBook;
	}
	public JRadioButton getRadMusic() {
		return radMusic;
	}
	public JRadioButton getRadFilm() {
		return radFilm;
	}
	public JTextField getTfSearch() {
		return tfSearch;
	}
	public JComboBox<String> getCbSearch() {
		return cbSearch;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public TableProductView() {
		setLayout(new BorderLayout(5, 5));
		add(createGroupRadioPanel(), BorderLayout.PAGE_START);
		add(createProductPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createGroupRadioPanel() {
		JPanel radioPanel = new JPanel(new GridLayout(1, 3, 5, 5));
		radioPanel.setBorder(new EmptyBorder(5, 300, 5, 300));
		ButtonGroup group = new ButtonGroup();
		group.add(radBook);
		group.add(radMusic);
		group.add(radFilm);
		radioPanel.add(radBook);
		radioPanel.add(radMusic);
		radioPanel.add(radFilm);
		return radioPanel;
	}
	
	private JPanel createProductPanel() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		panel.add(createSearchPanel(), BorderLayout.PAGE_START);
		panel.add(createTableProductPanel(), BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel createSearchPanel() {
		JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
		searchPanel.setBorder(new EmptyBorder(5, 40, 5, 40));
		searchPanel.add(new JLabel("Tìm kiếm "), BorderLayout.WEST);
		searchPanel.add(tfSearch, BorderLayout.CENTER);
		String[] titleCbSeach = selectTitleCbSearch(mode);
		cbSearch = new JComboBox<>(titleCbSeach);
		searchPanel.add(cbSearch, BorderLayout.EAST);
		return searchPanel;
	}
	
	private JPanel createTableProductPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(5, 40, 5, 40));
		table = createTable();
		
		loadData(table);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(TABLE_PRODUCT_WIDTH, TABLE_PRODUCT_HEIGHT));
		panel.add(scroll);
		
		return panel;
		
	}
	
	private JTable createTable() {
		JTable table = new JTable();
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return table;
	}
	
	// Load Data
	private void loadData(JTable table) {
		SwingUtilities.invokeLater(new Runnable(){public void run(){
			String data[][] = null;
		    //Update the model here
			DefaultTableModel tableModel = new DefaultTableModel(data, changeTitle(0)) {
				@Override
				public boolean isCellEditable(int row, int column) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			table.setModel(tableModel);		
		}});
	}
	
	private String[] changeTitle(int mode) {
		if (mode == 0) return titleBook;
		else if (mode == 1) return titleMusic;
		else if (mode == 2) return titleFilm;
		return defaultTitle;
	}
	
	private String[] selectTitleCbSearch(int mode) {
		if (mode == 0) return searchBook;
		else if (mode == 1) return searchMusicCD;
		else if (mode == 2) return searchFilmCD;
		return null;
	}
	
	// Update Model of table
	public void updateTableBook(List<Book> listBook) {
		SwingUtilities.invokeLater(new Runnable(){public void run(){
		    //Update the model here
			String data[][] = convertData(listBook, 0);
			DefaultTableModel tableModel = new DefaultTableModel(data, changeTitle(0)) {
				@Override
				public boolean isCellEditable(int row, int column) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			table.setModel(tableModel);	
		}});
	}
	
	public void updateTableMusicCD(List<MusicCD> listMusicCD) {
		SwingUtilities.invokeLater(new Runnable(){public void run(){
		    //Update the model here
			String data[][] = convertData(listMusicCD, 1);
			DefaultTableModel tableModel = new DefaultTableModel(data, changeTitle(1)) {
				@Override
				public boolean isCellEditable(int row, int column) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			table.setModel(tableModel);	
		}});
	}
	
	public void updateTableFilmCD(List<FilmCD> listFilmCD) {
		SwingUtilities.invokeLater(new Runnable(){public void run(){
		    //Update the model here
			String data[][] = convertData(listFilmCD, 2);
			DefaultTableModel tableModel = new DefaultTableModel(data, changeTitle(2)) {
				@Override
				public boolean isCellEditable(int row, int column) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			table.setModel(tableModel);	
		}});
	}
	
	private String[][] convertData(List listProduct, int mode) {
		int size = listProduct.size();
		String[][] data = new String[size][7];
		
		// Convert Book
		if (mode == 0) {
			for (int i = 0; i < size; i++) {
				Book book = (Book) listProduct.get(i);
				data[i][0] = book.getIdProduct();
				data[i][1] = book.getNameProduct();
				data[i][2] = book.getPublisher();
				data[i][3] = book.getAuthor();
				data[i][4] = book.getQuantity() + "";
				data[i][5] = book.getOutPrice() + "";
				data[i][6] = book.getInPrice() + "";
			}
		}
		// Convert MusicCD
		else if (mode == 1) {
			for (int i = 0; i < size; i++) {
				MusicCD musicCD = (MusicCD) listProduct.get(i);
				data[i][0] = musicCD.getIdProduct();
				data[i][1] = musicCD.getNameProduct();
				data[i][2] = musicCD.getSingerName();
				data[i][3] = musicCD.getType();
				data[i][4] = musicCD.getQuantity() + "";
				data[i][5] = musicCD.getOutPrice() + "";
				data[i][6] = musicCD.getInPrice() + "";
			}
		}
		//Convert FilmCD
		else if (mode == 2) {
			for (int i = 0; i < size; i++) {
				FilmCD filmCD = (FilmCD) listProduct.get(i);
				data[i][0] = filmCD.getIdProduct();
				data[i][1] = filmCD.getNameProduct();
				data[i][2] = filmCD.getDirector();
				data[i][3] = filmCD.getType();
				data[i][4] = filmCD.getQuantity() + "";
				data[i][5] = filmCD.getOutPrice() + "";
				data[i][6] = filmCD.getInPrice() + "";
			}
		}

		return data;
	}
	
	
	
	// Convert list of Product => Array 2D
//	private String[][] convertData(ArrayList listProduct) {
//		int size = list.size();
//		String data[][] = new String[size][titleCols.length];
//		for (int i = 0; i < size; i++) {
//			Xe xe = list.get(i);
//			data[i][0] = xe.getIdXe();
//			data[i][1] = xe.getBienXe();
//			data[i][2] = xe.getTenXe();
//			data[i][3] = xe.getLoaiXe();
//			data[i][4] = xe.getHangSanXuat();
//			data[i][5] = xe.getNamSanXuat();
//			data[i][6] = xe.getNgayBaoTri();
//			data[i][7] = xe.getNhienLieu();
//			data[i][8] = xe.getTrangThai() + "";
//			data[i][9] = xe.getGiaThue() + "";
//		}
//		return data;
//	}
	
	
}
