package app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import view.ResultWindow;

public class Search extends DatabaseConnection{
	
	public List<String> tvshows = new ArrayList<String>();
	public String mode;
	
	public List<String> shows = new ArrayList<String>();
	public List<String> genres = new ArrayList<String>();
	public int yearFrom;
	public int yearTo;
	public double ratingFrom;
	public double ratingTo;
	public int seasonsFrom;
	public int seasonsTo;
	public boolean showIsClosed; 
		
	public Search(List<String> shows_, boolean showIsClosed_) {
		// TODO Auto-generated constructor stub
		mode = "shows";
		shows = shows_;
		showIsClosed = showIsClosed_;		
	}

	public Search(List<String> genres_, int seasonsFrom_, int seasonsTo_, 
						double ratingFrom_, double ratingTo_, 
						int yearFrom_, int yearTo_,
						boolean showIsClosed_) {
		// TODO Auto-generated constructor stub
		mode = "parameters";
		genres = genres_;
		yearFrom = yearFrom_;
		yearTo = yearTo_;
		ratingFrom = ratingFrom_;
		ratingTo = ratingTo_;
		seasonsFrom = seasonsFrom_;
		seasonsTo = seasonsTo_;
		showIsClosed = showIsClosed_;
	}
	
	public void search() {
		String sql = null;
		tvshows.clear();
		
		ResultSet resultSet1;
		ResultSet resultSet2;
		boolean sameShow = false;
		
		try {
			switch (mode) {	
				case "parameters" : {
										
					if(!genres.isEmpty()) {
						for(String genre : genres){
													
							sql = "select * from tvshows where (genre1 = '" + genre + "' or genre2 = '" + genre + "') "
									+ "and (year between " + yearFrom + " and " + yearTo + ") "
									+ "and (rating between " + ratingFrom + " and " + ratingTo + ") "
									+ "and (seasons between " + seasonsFrom + " and " + seasonsTo + ")";
							sql += (showIsClosed) ? "and showIsClosed = 'true'" : "";
							sql += " order by name";

							resultSet1 = getQueryResultSet(sql);
							while(resultSet1.next()) {

								sameShow = false;
								
								for(String showNameCheck : tvshows) {
									if(showNameCheck.equals(resultSet1.getString("name"))) {
										sameShow = true;
										break;
									}
								}
								if(sameShow) continue;
								
								tvshows.add(resultSet1.getString("name"));
								if(!resultSet1.getString("genre1").equals(resultSet1.getString("genre2")))
									tvshows.add(resultSet1.getString("genre1") + ", " + resultSet1.getString("genre2"));
								else
									tvshows.add(resultSet1.getString("genre1"));
								tvshows.add(resultSet1.getString("year"));
								tvshows.add(resultSet1.getString("rating"));
								tvshows.add(resultSet1.getString("seasons"));
							}
						}	
					}
					else {
						
						sql = "select * from tvshows where " + 
								"(year between " + yearFrom + " and " + yearTo + 
								") and (rating between " + ratingFrom + " and " + ratingTo + 
								") and (seasons between " + seasonsFrom + " and " + seasonsTo + ")";
						sql += (showIsClosed) ? "and showIsClosed = 'true'" : "";
						sql += " order by name";
		
						resultSet1 = getQueryResultSet(sql);
						while(resultSet1.next()) {

							sameShow = false;
							
							for(String showNameCheck : tvshows) {
								if(showNameCheck.equals(resultSet1.getString("name"))) {
									sameShow = true;
									break;
								}
							}
							if(sameShow) continue;
							
							tvshows.add(resultSet1.getString("name"));
							if(!resultSet1.getString("genre1").equals(resultSet1.getString("genre2")))
								tvshows.add(resultSet1.getString("genre1") + ", " + resultSet1.getString("genre2"));
							else
								tvshows.add(resultSet1.getString("genre1"));
							tvshows.add(resultSet1.getString("year"));
							tvshows.add(resultSet1.getString("rating"));
							tvshows.add(resultSet1.getString("seasons"));
						}
					}
					break;
				}
				case "shows": {
					
					String genre1 = null;
					int year1 = 0;
					Double rating1 = null;
					
					String name2 = null;
					String genre21 = null;
					String genre22 = null;
					int year2 = 0;
					Double rating2 = null;
					
					sameShow = false;
					
					for(String showName : shows) {
						
						sql = "select * from tvshows where name = '" + showName + "'";
						resultSet1 = getQueryResultSet(sql);
						
						genre1 = resultSet1.getString("genre1");
						year1 = resultSet1.getInt("year");
						rating1 = resultSet1.getDouble("rating");
						
						sql = "select * from tvshows";
						resultSet2 = getQueryResultSet(sql);
							
						while(resultSet2.next()) {
							
							name2 = resultSet2.getString("name");
							genre21 = resultSet2.getString("genre1");
							genre22 = resultSet2.getString("genre2");
							year2 = resultSet2.getInt("year");
							rating2 = resultSet2.getDouble("rating");
							
							
							sameShow = false;
							
							for(String showNameCheck : shows) {
								if(showNameCheck.equals(name2)) {
									sameShow = true;
									break;
								}
							}
							
							for(String showNameCheck : tvshows) {
								if(showNameCheck.equals(name2)) {
									sameShow = true;
									break;
								}
							}
							if(sameShow) continue;
							
							if(!genre1.equals(genre21)) {
								continue;
							}
							
							if((showIsClosed == true) && (resultSet2.getString("showIsClosed").equals("false")))
								continue;
							
							if((Math.abs(year1 - year2) <= 5)
									&& (Math.abs(rating1 - rating2) <= 0.5)) {
								tvshows.add(resultSet2.getString("name"));
								if(!genre21.equals(genre22))
									tvshows.add(genre21 + ", " + genre22);
								else
									tvshows.add(genre21);
								tvshows.add("" + year2);
								tvshows.add("" + rating2);
								tvshows.add(resultSet2.getString("seasons"));
							}
						}
					}
					break;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultWindow window = new ResultWindow(tvshows);
		window.open();
		
	}
	
	private ResultSet getQueryResultSet(String sql) {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return resultSet;
	}
}
