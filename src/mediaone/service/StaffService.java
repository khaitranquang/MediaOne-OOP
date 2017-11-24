package mediaone.service;

import java.util.ArrayList;

import mediaone.model.Staff;
import mediaone.model.Statistic;

public interface StaffService {
	// Get one staff
		public Staff getStaff();
		
		// Get all staff from database
		public ArrayList<Staff> getAllStaff();
		
		// Update one staff
		public boolean updateStaff ();
		
		// Insert one staff
		public boolean insertStaff ();
		
		// Delete one staff
		public boolean deleteStaff ();
		
		// Statistic staff
		public ArrayList<Statistic> statisticStaff(String colName);
}
