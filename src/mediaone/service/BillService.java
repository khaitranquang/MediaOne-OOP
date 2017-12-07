package mediaone.service;

import java.time.LocalDate;
import java.util.List;

import mediaone.model.Bill;
/**
 * All service in manager bills
 * @author Chu lun Kute
 *
 */
public interface BillService {
	/**
	 * find all bills from database
	 * @return bills
	 */
	List<Bill> findAll();
	/**
	 * get profit from startDate to endDate
	 * @param startDate
	 * @param endDate
	 * @return profit
	 */
	Double getProfit(LocalDate startDate, LocalDate endDate);
	/**
	 * get number of Bill from startDate to endDate
	 * @return int
	 */
	int getNoOfBills(LocalDate startDate, LocalDate endDate);
	
	/**
	 * Delete bill
	 * @param id
	 * @return true if delete is success
	 */
	boolean removeByID(int id);
}	
