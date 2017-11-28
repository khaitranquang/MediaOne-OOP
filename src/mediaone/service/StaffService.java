package mediaone.service;

import java.util.ArrayList;
import java.util.List;

import mediaone.dao.StaffRepositoryImpl;
import mediaone.model.Staff;



public class StaffService {
	
	private StaffRepositoryImpl staffRepository;
	
	public boolean checkID(String id) {
		List<Staff> listStaff = staffRepository.findAll();
		for (int i = 0; i < listStaff.size(); i++) {
			String idStaffFromDB = listStaff.get(i).getIdStaff();
			if (id.equals(idStaffFromDB)) return false;
		}
		return true;
	}
	
	public boolean isLogin (String account, String pass) {
		if (account.equals("admin")) {
			String passOfAdmin = staffRepository.getPassAdmin();
			if (pass.equals(passOfAdmin)) return true;
			else return false;
		}
		else {
			List<Staff> listEmpl = staffRepository.findAll();
			List<String> listidStaff = null;
			for (int i = 0; i < listEmpl.size(); i++) {
				listidStaff.add(listEmpl.get(i).getIdStaff());
			}
			if (listidStaff.indexOf(account) >= 0 && pass.equals(staffRepository.getPassEmpl(account))) {
				increaseDays(account);
				return true;
			}
			else return false;
		}
	}
	
	public void changePassFromDB(String account, String newPass) {
		if (account.equals("admin")) {
			staffRepository.updatePassAdmin(newPass);
		} else {
			staffRepository.updatePass(account, newPass);
		}
	}
	
	public int increaseDays(String id) {
		Staff staff= staffRepository.findOne(id);
		String nameStaff= staff.getNameStaff();
		String pass= staff.getPass();
		Double salary= staff.getSalary();
		int newdays= staff.getDays();
		newdays++;
		staff = new Staff(id, nameStaff, pass, salary, newdays);
		staffRepository.update(staff, id);
		
		return newdays;
	}
	
	public Double countSalary(String id) {
		Staff staff = staffRepository.findOne(id);
		String nameStaff= staff.getNameStaff();
		String pass = staff.getPass();
		Double salary = staff.getSalary();
		int newdays= staff.getDays();
		Double countsalary;
		countsalary=salary*newdays;
		newdays=0;
		staff = new Staff(id, nameStaff, pass, salary, newdays);
		staffRepository.update(staff, id);
		return countsalary;
		
	}
}
