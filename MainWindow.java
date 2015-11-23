package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.DragDetectListener;
import org.eclipse.swt.events.DragDetectEvent;

public class MainWindow {

	protected Shell shlYourshows;
	
	public String show1;
	public String show2;
	public String show3;
	public String show4;
	public String show5;
	
	public String genre;
	public int timeFrom;
	public int timeTo;
	public double ratingFrom;
	public double ratingTo;
	public boolean showIsClosed; 
	
	Connection connection;
	PreparedStatement pst;
	ResultSet rs;

	/**
	 * Open the window.
	 * @param connection 
	 */
	public void open(Connection connection_) {
		connection = connection_;
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
		
		Label lblShow = new Label(shlYourshows, SWT.SHADOW_IN);
		lblShow.setBounds(10, 87, 55, 15);
		lblShow.setText("Show #1: ");
		
		Combo comboshow1 = new Combo(shlYourshows, SWT.NONE);
		fillComboBox(comboshow1);
		comboshow1.setBounds(70, 84, 203, 23);
		
		Label lblShow_1 = new Label(shlYourshows, SWT.NONE);
		lblShow_1.setText("Show #2: ");
		lblShow_1.setBounds(10, 126, 55, 15);
		
		Combo comboshow2 = new Combo(shlYourshows, SWT.NONE);
		fillComboBox(comboshow2);
		comboshow2.setBounds(70, 123, 203, 23);
		
		Label lblShow_2 = new Label(shlYourshows, SWT.NONE);
		lblShow_2.setText("Show #3: ");
		lblShow_2.setBounds(10, 167, 55, 15);
		
		Combo comboshow3 = new Combo(shlYourshows, SWT.NONE);
		fillComboBox(comboshow3);
		comboshow3.setBounds(70, 164, 203, 23);
		
		Label lblShow_3 = new Label(shlYourshows, SWT.NONE);
		lblShow_3.setText("Show #4: ");
		lblShow_3.setBounds(10, 209, 55, 15);
		
		Combo comboshow4 = new Combo(shlYourshows, SWT.NONE);
		fillComboBox(comboshow4);
		comboshow4.setBounds(70, 206, 203, 23);
		
		Label lblShow_4 = new Label(shlYourshows, SWT.NONE);
		lblShow_4.setText("Show #5: ");
		lblShow_4.setBounds(10, 252, 55, 15);
		
		Combo comboshow5 = new Combo(shlYourshows, SWT.NONE);
		fillComboBox(comboshow5);
		comboshow5.setBounds(70, 249, 203, 23);
		
		Label lblGenre = new Label(shlYourshows, SWT.NONE);
		lblGenre.setBounds(301, 87, 55, 15);
		lblGenre.setText("Genre: ");
		
		Button btnDrama = new Button(shlYourshows, SWT.CHECK);
		btnDrama.setEnabled(false);
		btnDrama.setBounds(380, 86, 61, 16);
		btnDrama.setText("drama");
		
		Button btnAction = new Button(shlYourshows, SWT.CHECK);
		btnAction.setEnabled(false);
		btnAction.setBounds(380, 125, 75, 16);
		btnAction.setText("action");
		
		Button btnComedy = new Button(shlYourshows, SWT.CHECK);
		btnComedy.setEnabled(false);
		btnComedy.setBounds(380, 167, 61, 16);
		btnComedy.setText("comedy");
		
		Button btnAdventure = new Button(shlYourshows, SWT.CHECK);
		btnAdventure.setEnabled(false);
		btnAdventure.setBounds(461, 86, 93, 16);
		btnAdventure.setText("adventure");
		
		Button btnHorror = new Button(shlYourshows, SWT.CHECK);
		btnHorror.setEnabled(false);
		btnHorror.setBounds(461, 125, 93, 16);
		btnHorror.setText("horror");
		
		Button btnAnimation = new Button(shlYourshows, SWT.CHECK);
		btnAnimation.setEnabled(false);
		btnAnimation.setBounds(461, 167, 93, 16);
		btnAnimation.setText("animation");
		
		Button btnFantasy = new Button(shlYourshows, SWT.CHECK);
		btnFantasy.setEnabled(false);
		btnFantasy.setBounds(380, 208, 64, 16);
		btnFantasy.setText("fantasy");
		
		Button btnMystery = new Button(shlYourshows, SWT.CHECK);
		btnMystery.setEnabled(false);
		btnMystery.setBounds(461, 208, 93, 16);
		btnMystery.setText("mystery");
		
		Button btnComics = new Button(shlYourshows, SWT.CHECK);
		btnComics.setEnabled(false);
		btnComics.setBounds(380, 252, 61, 16);
		btnComics.setText("comics");
		
		Button btnCrime = new Button(shlYourshows, SWT.CHECK);
		btnCrime.setEnabled(false);
		btnCrime.setBounds(461, 252, 93, 16);
		btnCrime.setText("crime");
		
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
				for (double i = Integer.valueOf(comboratingfrom.getText()); i < getMaxRating(); i += 0.1)
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
		comboyearto.setEnabled(false);
		comboyearto.setBounds(497, 385, 64, 23);
		
		Combo comboyearfrom = new Combo(shlYourshows, SWT.NONE);
		comboyearfrom.setEnabled(false);
		comboyearfrom.setBounds(412, 385, 61, 23);
		
		
		
		Button btnOnlyTheOnes = new Button(shlYourshows, SWT.CHECK);
		btnOnlyTheOnes.setBounds(10, 430, 159, 16);
		btnOnlyTheOnes.setText("Only the closed shows");
		
		Button btnByShows = new Button(shlYourshows, SWT.RADIO);
		btnByShows.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comboshow1.setEnabled(true);
				comboshow2.setEnabled(true);
				comboshow3.setEnabled(true);
				comboshow4.setEnabled(true);
				comboshow5.setEnabled(true);
				btnDrama.setEnabled(false);
				btnComedy.setEnabled(false);
				btnHorror.setEnabled(false);
				btnAction.setEnabled(false);
				btnAdventure.setEnabled(false);
				btnAnimation.setEnabled(false);
				btnFantasy.setEnabled(false);
				btnMystery.setEnabled(false);
				btnComics.setEnabled(false);
				btnCrime.setEnabled(false);
				comboseasonsfrom.setEnabled(false);
				comboseasonsto.setEnabled(false);
				comboratingfrom.setEnabled(false);
				comboratingto.setEnabled(false);
				comboyearfrom.setEnabled(false);
				comboyearto.setEnabled(false);
			}
		});
		btnByShows.setSelection(true);
		btnByShows.setBounds(10, 23, 90, 16);
		btnByShows.setText("by shows");
		
		Button btnByParameters = new Button(shlYourshows, SWT.RADIO);
		btnByParameters.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comboshow1.setEnabled(false);
				comboshow2.setEnabled(false);
				comboshow3.setEnabled(false);
				comboshow4.setEnabled(false);
				comboshow5.setEnabled(false);
				btnDrama.setEnabled(true);
				btnComedy.setEnabled(true);
				btnHorror.setEnabled(true);
				btnAction.setEnabled(true);
				btnAdventure.setEnabled(true);
				btnAnimation.setEnabled(true);
				btnFantasy.setEnabled(true);
				btnMystery.setEnabled(true);
				btnComics.setEnabled(true);
				btnCrime.setEnabled(true);
				comboseasonsfrom.setEnabled(true);
				comboseasonsto.setEnabled(true);
				comboratingfrom.setEnabled(true);
				comboratingto.setEnabled(true);
				comboyearfrom.setEnabled(true);
				comboyearto.setEnabled(true);
			}
		});
		btnByParameters.setBounds(301, 23, 107, 16);
		btnByParameters.setText("by parameters");
		
		Button btnGo = new Button(shlYourshows, SWT.NONE);
		btnGo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnByParameters.getSelection()) {
//					timeFrom = combotimefrom.getText();
//					timeTo = combotimeto.getText();
//					ratingFrom = comboratingfrom.getText();
//					ratingTo = comboratingfrom.getText();
				}
			}
		});
		btnGo.setBounds(249, 426, 75, 25);
		btnGo.setText("GO");
		
		

	}

	private double getMinRating() {
		// TODO Auto-generated method stub
		Float min = null;
		String sql = "select min(rating) from tvshows";
		try {
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			min = Float.valueOf(rs.getString(1));
			System.out.println(rs.getString(1));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		java.lang.Math.
		return min;
	}

	private double getMaxRating() {
		// TODO Auto-generated method stub
		Float max = null;
		String sql = "select max(rating) from tvshows";
		try {
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			max = Float.valueOf(rs.getString(1));
			System.out.println(rs.getString(1));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return max;	
	}

	protected int getMinNumberofSeasons() {
		// TODO Auto-generated method stub
		int min = 0;
		String sql = "select min(numberOfSeasons) from tvshows";
		try {
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			min = Integer.valueOf(rs.getString(1));
			System.out.println(rs.getString(1));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return min;
	}
	
	protected int getMaxNumberofSeasons() {
		// TODO Auto-generated method stub
		int max = 0;
		String sql = "select max(numberOfSeasons) from tvshows";
		try {
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			max = Integer.valueOf(rs.getString(1));
			System.out.println(rs.getString(1));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return max + 1;
	}

	private void fillComboBox(Combo comboshow) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from tvshows order by name";
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("name");
				comboshow.add(name);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
