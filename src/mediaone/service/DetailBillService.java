package mediaone.service;

import java.util.ArrayList;

import mediaone.model.DetailBill;

public interface DetailBillService {
	
	// Get DetailBill
	public ArrayList<DetailBill> getDetailBill();
	
	// Insert one detailbill
	public boolean insertDetailBill();
	
}
