package view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import app.DatabaseConnection;

public class ResultWindow extends DatabaseConnection{

	protected Shell shell;
	Table table;
	
	public List<String> tvshows = new ArrayList<String>();
	
	public List<String> shows = new ArrayList<String>();
	public List<String> genres = new ArrayList<String>();
	public int yearFrom;
	public int yearTo;
	public double ratingFrom;
	public double ratingTo;
	public int seasonsFrom;
	public int seasonsTo;
	public boolean showIsClosed; 
	
	public String mode;
	
	PreparedStatement preparedStatement;
	ResultSet resultSet;

	public ResultWindow(List<String> shows_, boolean showIsClosed_) {
		// TODO Auto-generated constructor stub
		mode = "shows";
		shows = shows_;
		showIsClosed = showIsClosed_;
		
	}

	public ResultWindow(List<String> genres_, int seasonsFrom_, int seasonsTo_, 
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

	/**
	 * Launch the application.
	 * @param args
	 */
/*	public static void main(String[] args) {
		try {
			ResultWindow window = new ResultWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**
	 * Open the window.
	 */
	public void open() {
		search();
/*		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		*/
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		

		shell = new Shell();
		shell.setSize(400, 300);
		shell.setText("SWT Application");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setBounds(0, 0, 400, 300);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
		tblclmnName.setWidth(150);
		tblclmnName.setText("name");
		
		TableColumn tblclmnRating = new TableColumn(table, SWT.NONE);
		tblclmnRating.setWidth(50);
		tblclmnRating.setText("rating");
		
		TableColumn tblclmnYear = new TableColumn(table, SWT.NONE);
		tblclmnYear.setWidth(50);
		tblclmnYear.setText("year");
		
		TableColumn tblclmnGenre = new TableColumn(table, SWT.NONE);
		tblclmnGenre.setWidth(150);
		tblclmnGenre.setText("genre");
		
		

	}
	
	/*	
	String sql = "select * from tvshows order by name";
	preparedStatement = connection.prepareStatement(sql);
	resultSet = preparedStatement.executeQuery();
	while(resultSet.next()) {
		String name = resultSet.getString("name");
	}*/
	
	public void search() {
		String sql = null;
		tvshows.clear();
		try {
			switch (mode) {	
				case "parameters" : {
					if(!genres.isEmpty()) {
						for(String genre : genres){
						
						// genre???????
						
							sql = "select * from tvshows where (genre1 = '" + genre + "' or genre2 = '" + genre + "') "
									+ "and (year between " + yearFrom + " and " + yearTo + ") "
									+ "and (rating between " + ratingFrom + " and " + ratingTo + ") "
									+ "and (seasons between " + seasonsFrom + " and " + seasonsTo + ")";
							sql += (showIsClosed) ? "and showIsClosed = 'true'" : "";
							sql += " order by name";

							resultSet = getQueryResultSet(sql);
							while(resultSet.next()) {
								tvshows.add(resultSet.getString("name"));
								tvshows.add(resultSet.getString("genre1"));
								tvshows.add(resultSet.getString("genre2"));
								tvshows.add(resultSet.getString("year"));
								tvshows.add(resultSet.getString("rating"));
								tvshows.add(resultSet.getString("seasons"));
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
		
						resultSet = getQueryResultSet(sql);
						while(resultSet.next()) {
							tvshows.add(resultSet.getString("name"));
							tvshows.add(resultSet.getString("genre1"));
							tvshows.add(resultSet.getString("genre2"));
							tvshows.add(resultSet.getString("year"));
							tvshows.add(resultSet.getString("rating"));
							tvshows.add(resultSet.getString("seasons"));
						}
						System.out.println("no genres!");
					}
					break;
				}
				case "shows": {
					
					String genre1 = null;
					int year1 = 0;
					Double rating1 = null;
					
					String genre2 = null;
					int year2 = 0;
					Double rating2 = null;
					
					boolean sameShow = false;
					ResultSet resultSet1;
					ResultSet resultSet2;
					
					for(String showName : shows) {
						
						sql = "select * from tvshows where name = '" + showName + "'";
						resultSet1 = getQueryResultSet(sql);
						
						genre1 = resultSet1.getString("genre1");
						year1 = resultSet1.getInt("year");
						rating1 = resultSet1.getDouble("rating");
						
//						genre1 = getFromDatabaseByName("genre1", showName);
//						year = Integer.valueOf(getFromDatabaseByName("year", showName));
//						rating = Double.valueOf(getFromDatabaseByName("rating", showName));
						
						System.out.println(showName + " | " + genre1 + " | " + year1 + " | " + rating1);
						
						for(int id = 1; id <= getCountFromDatabase(); id++) {
													
							sql = "select * from tvshows where id = " + id;
							resultSet2 = getQueryResultSet(sql);
							
							genre2 = resultSet2.getString("genre1");
							year2 = resultSet2.getInt("year");
							rating2 = resultSet2.getDouble("rating");
							
							sameShow = false;
							
							for(String showNameCheck : shows) {
								if(showNameCheck.equals(resultSet2.getString("name"))) {
									sameShow = true;
									break;
								}
							}
							if(sameShow) continue;
							
							if((showIsClosed == true) && (resultSet2.getBoolean("showIsClosed") == false))
								continue;
							
	//						System.out.println(genre1 + "=" + resultSet.getString("genre1"));
							if(!genre1.equals(genre2)) {
								continue;
							}
							System.out.println("!!!!!!!!!!!");
							
							
							if((Math.abs(year1 - year2) <= 3)
									&& (Math.abs(rating1 - rating2) <= 0.3)) {
								tvshows.add(resultSet2.getString("name"));
								tvshows.add(resultSet2.getString("genre1"));
								tvshows.add(resultSet2.getString("genre2"));
								tvshows.add(resultSet2.getString("year"));
								tvshows.add(resultSet2.getString("rating"));
								tvshows.add(resultSet2.getString("seasons"));
							}
								
						}
						
					}
					
					break;
				}
			}
			
			for(int i = 0; i < tvshows.size(); i += 6) {
				System.out.print(tvshows.get(i) + " | " + tvshows.get(i+1) + " | " + tvshows.get(i+2)
						 						+ " | " + tvshows.get(i+3) + " | " + tvshows.get(i+4)
						 						+ " | " + tvshows.get(i+5));
				System.out.println();
			}
			
			//System.out.println(tvshows);
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int getCountFromDatabase() {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "select count(*) from tvshows";
		count = Integer.valueOf(getQueryResultData(sql, 1));
		
		return count;
	}

	private String getFromDatabaseByName(String parameter, String showName) {
		// TODO Auto-generated method stub
		String showData = null;
		String sql = "select * from tvshows where name = '" + showName + "'";
		showData = getQueryResultData(sql, parameter);
		return showData;
	}
	
	private ResultSet getQueryResultSet(String sql) {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return resultSet;
	}

	private String getQueryResultData(String sql, String parameter) {
		// TODO Auto-generated method stub
		String data = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			data = resultSet.getString(parameter);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return data;
	}

	private String getQueryResultData(String sql, int number) {
		// TODO Auto-generated method stub
		String data = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			data = resultSet.getString(number);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return data;
	}
	

}
