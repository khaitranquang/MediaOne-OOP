package mediaone.service;

import java.time.LocalDate;
import java.util.List;

import mediaone.dao.BillRepository;
import mediaone.dao.BillRepositoryImpl;
import mediaone.model.Bill;

public class BillServiceImpl implements BillService {
	private BillRepository billRepository = new BillRepositoryImpl();
	
	@Override
	public List<Bill> findAll() {
		return billRepository.findAll();
	}

	@Override
	public Double getProfit(LocalDate startDate, LocalDate endDate) {
		List<Bill> bills = billRepository.findByExportDayBetween(startDate, endDate);
		double sum = 0;
		for (Bill bill : bills) {
			sum += bill.getDetailBill().getProfit();
		}
		return sum;
	}

	@Override
	public int getNoOfBills(LocalDate startDate, LocalDate endDate) {
		List<Bill> bills = billRepository.findByExportDayBetween(startDate, endDate);
		return bills.size();
	}

	@Override
	public boolean removeByID(int id) {
		return billRepository.removeByID(id);
	}
}
