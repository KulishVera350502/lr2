package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import app.DatabaseConnection;
import view.ResultWindow;

public class MainWindow extends DatabaseConnection{

	protected Shell shlYourshows;
	
	public List<String> shows = new ArrayList<String>();
	
	public List<String> genres = new ArrayList<String>();
	public int yearFrom;
	public int yearTo;
	public double ratingFrom;
	public double ratingTo;
	public int seasonsFrom;
	public int seasonsTo;
	
	public boolean showIsClosed; 
	
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	public MainWindow() {
		// TODO Auto-generated constructor stub
//		connection = connection_;
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @param connection 
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlYourshows.open();
		shlYourshows.layout();
		while (!shlYourshows.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlYourshows = new Shell();
		shlYourshows.setSize(600, 500);
		shlYourshows.setText("yourShows");
		
		Label lblShow[] = new Label[5];
		
		for(int i = 0; i < 5; i++) {
			lblShow[i] = new Label(shlYourshows, SWT.SHADOW_IN);
			lblShow[i].setBounds(10, 90 + i*40, 55, 15);
			lblShow[i].setText("Show #" + (i+1) + ": ");
		}
		
		Combo comboShow[] = new Combo[5];
		
		for(int i = 0; i < 5; i++) {
			comboShow[i] = new Combo(shlYourshows, SWT.NONE);
			fillComboBox(comboShow[i]);
			comboShow[i].setBounds(70, 86 + i*40, 200, 20);
		}
		
		Label lblGenre = new Label(shlYourshows, SWT.NONE);
		lblGenre.setBounds(300, 90, 55, 15);
		lblGenre.setText("Genre: ");
		
		Button btnGenres[] = new Button[10];
		
		for(int i = 0; i < 5; i++) {
			btnGenres[i] = new Button(shlYourshows, SWT.CHECK);
			btnGenres[i].setEnabled(false);
			btnGenres[i].setBounds(360, 90 + i*40, 70, 15);
		}
		
		for(int i = 5; i < 10; i++) {
			btnGenres[i] = new Button(shlYourshows, SWT.CHECK);
			btnGenres[i].setEnabled(false);
			btnGenres[i].setBounds(450, 90 + (i-5)*40, 70, 15);
		}

		btnGenres[0].setText("drama");
		btnGenres[1].setText("action");
		btnGenres[2].setText("comedy");
		btnGenres[3].setText("adventure");
		btnGenres[4].setText("horror");
		btnGenres[5].setText("animation");
		btnGenres[6].setText("fantasy");
		btnGenres[7].setText("mystery");
		btnGenres[8].setText("comics");
		btnGenres[9].setText("crime");
		
		Label lblSeasons = new Label(shlYourshows, SWT.NONE);
		lblSeasons.setBounds(301, 302, 73, 23);
		lblSeasons.setText("Seasons: ");
		
		Label label_2 = new Label(shlYourshows, SWT.NONE);
		label_2.setText("from");
		label_2.setBounds(380, 302, 26, 15);
		
		Label label_3 = new Label(shlYourshows, SWT.NONE);
		label_3.setText("to");
		label_3.setBounds(482, 302, 11, 15);
		
		Combo comboseasonsto = new Combo(shlYourshows, SWT.NONE);
		for (int i = getMinNumberofSeasons(); i < getMaxNumberofSeasons(); i++)
			comboseasonsto.add("" + i);
		comboseasonsto.setBounds(497, 299, 64, 23);
		comboseasonsto.setEnabled(false);
		
		Combo comboseasonsfrom = new Combo(shlYourshows, SWT.NONE);
		comboseasonsfrom.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comboseasonsto.removeAll();
				for (int i = Integer.valueOf(comboseasonsfrom.getText()); i < getMaxNumberofSeasons(); i++)
					comboseasonsto.add("" + i);
			}
		});
		for (int i = getMinNumberofSeasons(); i < getMaxNumberofSeasons(); i++)
			comboseasonsfrom.add("" + i);
		comboseasonsfrom.setBounds(412, 299, 61, 23);
		comboseasonsfrom.setEnabled(false);
		
		Label lblNewLabel = new Label(shlYourshows, SWT.NONE);
		lblNewLabel.setBounds(301, 343, 55, 15);
		lblNewLabel.setText("Rating: ");
		
		Label label_1 = new Label(shlYourshows, SWT.NONE);
		label_1.setText("from");
		label_1.setBounds(380, 343, 26, 15);
		
		Label label = new Label(shlYourshows, SWT.NONE);
		label.setText("to");
		label.setBounds(482, 343, 11, 15);
		
		Combo comboratingto = new Combo(shlYourshows, SWT.NONE);
		for (double i = getMinRating(); i < getMaxRating(); i += 0.1)
			comboratingto.add("" + i);
		comboratingto.setBounds(497, 340, 64, 23);
		comboratingto.setEnabled(false);
		
		Combo comboratingfrom = new Combo(shlYourshows, SWT.NONE);
		comboratingfrom.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comboratingto.removeAll();
				for (double i = Double.valueOf(comboratingfrom.getText()); i < getMaxRating(); i += 0.1)
					comboratingto.add("" + i);
			}
		});
		for (double i = getMinRating(); i < getMaxRating(); i += 0.1)
			comboratingfrom.add("" + i);
		comboratingfrom.setBounds(412, 340, 61, 23);
		comboratingfrom.setEnabled(false);
		
		Label lblReleaseYear = new Label(shlYourshows, SWT.NONE);
		lblReleaseYear.setBounds(301, 388, 73, 15);
		lblReleaseYear.setText("Release year:");
		
		Label label_4 = new Label(shlYourshows, SWT.NONE);
		label_4.setText("from");
		label_4.setBounds(380, 388, 26, 15);
		
		Label label_5 = new Label(shlYourshows, SWT.NONE);
		label_5.setText("to");
		label_5.setBounds(482, 388, 11, 15);
		
		Combo comboyearto = new Combo(shlYourshows, SWT.NONE);
		for (int i = getMinYear(); i < getMaxYear(); i += 1)
			comboyearto.add("" + i);
		comboyearto.setEnabled(false);
		comboyearto.setBounds(497, 385, 64, 23);
		
		Combo comboyearfrom = new Combo(shlYourshows, SWT.NONE);
		comboyearfrom.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comboyearto.removeAll();
				for (int i = Integer.valueOf(comboyearfrom.getText()); i < getMaxYear(); i += 1)
					comboyearto.add("" + i);
			}
		});
		for (int i = getMinYear(); i < getMaxYear(); i += 1)
			comboyearfrom.add("" + i);
		comboyearfrom.setEnabled(false);
		comboyearfrom.setBounds(412, 385, 61, 23);
		
		
		
		Button btnOnlyTheOnes = new Button(shlYourshows, SWT.CHECK);
		btnOnlyTheOnes.setBounds(10, 430, 159, 16);
		btnOnlyTheOnes.setText("Only the closed shows");
		
		Button btnByShows = new Button(shlYourshows, SWT.RADIO);
		btnByShows.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for(Combo combo : comboShow)
					combo.setEnabled(true);
				for(Button btn : btnGenres)
					btn.setEnabled(false);
				comboseasonsfrom.setEnabled(false);
				comboseasonsto.setEnabled(false);
				comboratingfrom.setEnabled(false);
				comboratingto.setEnabled(false);
				comboyearfrom.setEnabled(false);
				comboyearto.setEnabled(false);
			}
		});
		btnByShows.setSelection(true);
		btnByShows.setBounds(10, 40, 90, 16);
		btnByShows.setText("by shows");
		
		Button btnByParameters = new Button(shlYourshows, SWT.RADIO);
		btnByParameters.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for(Combo combo : comboShow)
					combo.setEnabled(false);
				for(Button btn : btnGenres)
					btn.setEnabled(true);
				comboseasonsfrom.setEnabled(true);
				comboseasonsto.setEnabled(true);
				comboratingfrom.setEnabled(true);
				comboratingto.setEnabled(true);
				comboyearfrom.setEnabled(true);
				comboyearto.setEnabled(true);
			}
		});
		btnByParameters.setBounds(300, 40, 107, 16);
		btnByParameters.setText("by parameters");
		
		Button btnGo = new Button(shlYourshows, SWT.NONE);
		btnGo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ResultWindow window;
				if (btnByParameters.getSelection()) {
					
					genres.clear();
					seasonsFrom = 0;
					seasonsTo = 0;
					ratingFrom = 0;
					ratingTo = 0;
					yearFrom = 0;
					yearTo = 0;
					
					yearFrom = (comboyearfrom.getText() != "") ? Integer.valueOf(comboyearfrom.getText()) : getMinYear();
					yearTo = (comboyearto.getText() != "") ? Integer.valueOf(comboyearto.getText()) : getMaxYear();
					ratingFrom = (comboratingfrom.getText() != "") ? Double.valueOf(comboratingfrom.getText()) : getMinRating();
					ratingTo = (comboratingto.getText() != "") ? Double.valueOf(comboratingto.getText()) : getMaxRating();
					seasonsFrom = (comboseasonsfrom.getText() != "") ? Integer.valueOf(comboseasonsfrom.getText()) : getMinNumberofSeasons();
					seasonsTo = (comboseasonsto.getText() != "") ? Integer.valueOf(comboseasonsto.getText()) : getMaxNumberofSeasons();

					for(Button btn : btnGenres) 
						if (btn.getSelection())
							genres.add(btn.getText());
					
					showIsClosed = btnOnlyTheOnes.getSelection();
					
					System.out.println("" + genres + seasonsFrom + " " + seasonsTo + " " + ratingFrom + " " + ratingTo + " " + yearFrom + " " + yearTo + " " + showIsClosed);
					
					window = new ResultWindow(genres, seasonsFrom, seasonsTo, ratingFrom, ratingTo, yearFrom, yearTo, showIsClosed);
					window.open();
				}
				else {
					shows.clear();
					for(Combo combo : comboShow)
						if (combo.getText() != "")
							shows.add(combo.getText());

					showIsClosed = btnOnlyTheOnes.getSelection();
					
					System.out.println("" + shows + " " + showIsClosed);
					
					window = new ResultWindow(shows, showIsClosed);
					window.open();
				}
			}
		});
		btnGo.setBounds(250, 426, 75, 25);
		btnGo.setText("GO");

	}

	private double getMinRating() {
		// TODO Auto-generated method stub
		Double min = null;
		String sql = "select min(rating) from tvshows";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			min = Double.valueOf(resultSet.getString(1));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return min;
	}

	private double getMaxRating() {
		// TODO Auto-generated method stub
		Double max = null;
		String sql = "select max(rating) from tvshows";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			max = Double.valueOf(resultSet.getString(1));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return max;	
	}

	protected int getMinNumberofSeasons() {
		// TODO Auto-generated method stub
		int min = 0;
		String sql = "select min(seasons) from tvshows";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			min = Integer.valueOf(resultSet.getString(1));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return min;
	}
	
	protected int getMaxNumberofSeasons() {
		// TODO Auto-generated method stub
		int max = 0;
		String sql = "select max(seasons) from tvshows";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			max = Integer.valueOf(resultSet.getString(1));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return max + 1;
	}
	
	private int getMinYear() {
		// TODO Auto-generated method stub
		int min = 0;
		String sql = "select min(year) from tvshows";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			min = Integer.valueOf(resultSet.getString(1));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return min;
	}

	private int getMaxYear() {
		// TODO Auto-generated method stub
		int max = 0;
		String sql = "select max(year) from tvshows";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			max = Integer.valueOf(resultSet.getString(1));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return max;	
	}

	private void fillComboBox(Combo comboshow) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from tvshows order by name";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String name = resultSet.getString("name");
				comboshow.add(name);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
