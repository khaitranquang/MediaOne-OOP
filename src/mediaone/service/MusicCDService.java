package mediaone.service;

import java.util.ArrayList;

import mediaone.model.MusicCD;
import mediaone.model.Statistic;

public interface MusicCDService {
	
	// Get one MusicCD
	public MusicCD getMusicCD();
		
	// Get all MusicCD from database
	public ArrayList<MusicCD> getAllMusicCD();
		
	// Update one MusicCD
	public boolean updateMusicCD ();
		
	// Insert one MusicCD
	public boolean insertMusicCD ();
		
	// Delete one MusicCD
	public boolean deleteMusicCD ();
		
	// Statistic MusicCD
	public ArrayList<Statistic> statisticMusicCD(String colName);
}
