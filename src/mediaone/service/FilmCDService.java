package mediaone.service;

import java.util.ArrayList;

import mediaone.model.FilmCD;
import mediaone.model.Statistic;

public interface FilmCDService {
	// Get one FilmCD
	public FilmCD getFilmCD();
		
	// Get all FilmCD from database
	public ArrayList<FilmCD> getAllFilmCD();
		
	// Update one FilmCD
	public boolean updateFilmCD ();
		
	// Insert one FilmCD
	public boolean insertFilmCD ();
		
	// Delete one FilmCD
	public boolean deleteFilmCD ();
		
	// Statistic FilmCD
	public ArrayList<Statistic> statisticFilmCD(String colName);
}
