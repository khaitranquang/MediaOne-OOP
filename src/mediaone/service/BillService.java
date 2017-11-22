package mediaone.service;

import java.util.ArrayList;

import mediaone.model.Bill;
import mediaone.model.Statistic;

public interface BillService {
	
	// Get one Bill
	public Bill getBill();
	
	//Get all Bill
	public ArrayList<Bill> getAllBill();
	
	//Insert one Bill
	public boolean insertBill();
	
	//Delete
	public boolean deleteBill();
	
	//Statistic Bill
	public ArrayList<Statistic> StatisticBill(String colName);
}
