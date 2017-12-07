package mediaone.service;

import mediaone.model.DetailBill;
import mediaone.model.Product;
/**
 * All service in sell product
 * @author Chu lun Kute
 *
 */
public interface TransactionService {
	/**
	 * validate product to add to bill
	 * @param id
	 * @return product if validated else null
	 */
	Product addProductToBill(String id, int number);
	/**
	 * pay bill
	 * @param detailBill
	 * @return price of bill
	 */
	Double pay(DetailBill detailBill);
}
