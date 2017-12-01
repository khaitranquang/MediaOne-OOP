package mediaone.dao;

import java.time.LocalDate;
import java.util.List;

import mediaone.model.Bill;

public interface BillRepository {
	/**
	 * Add bill into database
	 * @param bill
	 * @return bill
	 */
	Bill add(Bill bill);
	/**
	 * Load all bills from database
	 * @return bills
	 */
	List<Bill> findAll();
	/**
	 * Load bills from database from startDate to endDate
	 * @param startDate
	 * @param endDate
	 * @return bills
	 */
	List<Bill> findByExportDayBetween(LocalDate startDate, LocalDate endDate);
	/**
	 * Remove bill by id
	 * @param id
	 * @return
	 */
	boolean removeByID(int id);
}
