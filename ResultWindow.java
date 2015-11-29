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

	public ResultWindow() {
		// TODO Auto-generated constructor stub
	}

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
		try {
				
			if(!genres.isEmpty()) {
				for(String genre : genres){
					
					// genre???????
					
					sql = "select * from tvshows where (genre1 = '" + genre + "' or genre2 = '" + genre + "') "
							+ "and (releaseYear between " + yearFrom + " and " + yearTo + ") "
							+ "and (rating between " + ratingFrom + " and " + ratingTo + ") "
							+ "and (numberOfseasons between " + seasonsFrom + " and " + seasonsTo + ")";
					sql += (showIsClosed) ? "and closed = 'true'" : "";
					sql += " order by name";
					
					preparedStatement = connection.prepareStatement(sql);
					resultSet = preparedStatement.executeQuery();
					while(resultSet.next()) {
						tvshows.add(resultSet.getString("name"));
						tvshows.add(resultSet.getString("genre1"));
						tvshows.add(resultSet.getString("genre2"));
						tvshows.add(resultSet.getString("releaseYear"));
						tvshows.add(resultSet.getString("rating"));
						tvshows.add(resultSet.getString("numberOfSeasons"));
					}
				}
			}
			else {
				sql = "select * from tvshows where " + 
						"(releaseYear between " + yearFrom + " and " + yearTo + 
						") and (rating between " + ratingFrom + " and " + ratingTo + 
						") and (numberOfseasons between " + seasonsFrom + " and " + seasonsTo + ")";
				sql += (showIsClosed) ? "and closed = 'true'" : "";
				sql += " order by name";
				
				preparedStatement = connection.prepareStatement(sql);
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					tvshows.add(resultSet.getString("name"));
					tvshows.add(resultSet.getString("genre1"));
					tvshows.add(resultSet.getString("genre2"));
					tvshows.add(resultSet.getString("releaseYear"));
					tvshows.add(resultSet.getString("rating"));
					tvshows.add(resultSet.getString("numberOfSeasons"));
				}
				System.out.println("no genres!");
			}
			
			
			for(int i = 0; i < tvshows.size(); i += 6) {
				System.out.print(tvshows.get(i) + " | " + tvshows.get(i+1) + " | " + tvshows.get(i+2)
						 						+ " | " + tvshows.get(i+3) + " | " + tvshows.get(i+4)
						 						+ " | " + tvshows.get(i+5));
				System.out.println();
			}
	/*		
			sql = "select name, rating from tvshows where rating > " + ratingTo + " order by rating";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				tvshows.add(resultSet.getString(1));
				tvshows.add(resultSet.getString(2));
			}
			System.out.println(tvshows);
	*/		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
