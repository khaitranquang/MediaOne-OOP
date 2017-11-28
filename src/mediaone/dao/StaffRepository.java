package mediaone.dao;

import java.util.List;

import mediaone.model.Staff;

public interface StaffRepository {
	
	// Lay toan bo nhan vien
	List<Staff> findAll();
	
	// Lay ra 1 nhan vien
	Staff findOne(String id);
	
	// Them nhan vien
	Staff add(Staff staff);
	
	//Xoa nhan vien
	Staff removebyid(String id);
	
	//Cap nhat du lieu
	Staff update(Staff staff, String oldId);
	
	public boolean updatePass(String id, String newpass);
	
	public String getPassAdmin();
	
	public String getPassEmpl (String idStaff);
	
	public void updatePassAdmin(String newPass);
}
