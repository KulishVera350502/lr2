package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

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
	 */
	protected void createContents() {
		shlYourshows = new Shell();
		shlYourshows.setSize(600, 400);
		shlYourshows.setText("yourShows");
		
		Label lblShow = new Label(shlYourshows, SWT.SHADOW_IN);
		lblShow.setBounds(10, 87, 55, 15);
		lblShow.setText("Show #1: ");
		
		Combo comboshow1 = new Combo(shlYourshows, SWT.NONE);
		comboshow1.setBounds(70, 84, 203, 23);
		
		Label lblShow_1 = new Label(shlYourshows, SWT.NONE);
		lblShow_1.setText("Show #2: ");
		lblShow_1.setBounds(10, 126, 55, 15);
		
		Combo comboshow2 = new Combo(shlYourshows, SWT.NONE);
		comboshow2.setBounds(70, 123, 203, 23);
		
		Label lblShow_2 = new Label(shlYourshows, SWT.NONE);
		lblShow_2.setText("Show #3: ");
		lblShow_2.setBounds(10, 167, 55, 15);
		
		Combo comboshow3 = new Combo(shlYourshows, SWT.NONE);
		comboshow3.setBounds(70, 164, 203, 23);
		
		Label lblShow_3 = new Label(shlYourshows, SWT.NONE);
		lblShow_3.setText("Show #4: ");
		lblShow_3.setBounds(10, 209, 55, 15);
		
		Combo comboshow4 = new Combo(shlYourshows, SWT.NONE);
		comboshow4.setBounds(70, 206, 203, 23);
		
		Label lblShow_4 = new Label(shlYourshows, SWT.NONE);
		lblShow_4.setText("Show #5: ");
		lblShow_4.setBounds(10, 252, 55, 15);
		
		Combo comboshow5 = new Combo(shlYourshows, SWT.NONE);
		comboshow5.setBounds(70, 249, 203, 23);
		
		Label lblGenre = new Label(shlYourshows, SWT.NONE);
		lblGenre.setBounds(301, 87, 55, 15);
		lblGenre.setText("Genre: ");
		
		Combo combogenre = new Combo(shlYourshows, SWT.NONE);
		combogenre.setBounds(380, 84, 183, 23);
		combogenre.setEnabled(false);
		
		Label lblTimePeriod = new Label(shlYourshows, SWT.NONE);
		lblTimePeriod.setBounds(301, 126, 75, 15);
		lblTimePeriod.setText("Time period: ");
		
		Label label_2 = new Label(shlYourshows, SWT.NONE);
		label_2.setText("from");
		label_2.setBounds(380, 126, 26, 15);
		
		Combo combotimefrom = new Combo(shlYourshows, SWT.NONE);
		combotimefrom.setBounds(412, 123, 61, 23);
		combotimefrom.setEnabled(false);
		
		Label label_3 = new Label(shlYourshows, SWT.NONE);
		label_3.setText("to");
		label_3.setBounds(482, 126, 11, 15);
		
		Combo combotimeto = new Combo(shlYourshows, SWT.NONE);
		combotimeto.setBounds(497, 123, 64, 23);
		combotimeto.setEnabled(false);
		
		Label lblNewLabel = new Label(shlYourshows, SWT.NONE);
		lblNewLabel.setBounds(301, 167, 55, 15);
		lblNewLabel.setText("Rating: ");
		
		Label label_1 = new Label(shlYourshows, SWT.NONE);
		label_1.setText("from");
		label_1.setBounds(380, 167, 26, 15);
		
		Combo comboratingfrom = new Combo(shlYourshows, SWT.NONE);
		comboratingfrom.setBounds(412, 164, 61, 23);
		comboratingfrom.setEnabled(false);
		
		Label label = new Label(shlYourshows, SWT.NONE);
		label.setText("to");
		label.setBounds(482, 167, 11, 15);
		
		Combo comboratingto = new Combo(shlYourshows, SWT.NONE);
		comboratingto.setBounds(497, 164, 64, 23);
		comboratingto.setEnabled(false);
		
		Button btnOnlyTheOnes = new Button(shlYourshows, SWT.CHECK);
		btnOnlyTheOnes.setBounds(10, 304, 159, 16);
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
				combogenre.setEnabled(false);
				combotimefrom.setEnabled(false);
				combotimeto.setEnabled(false);
				comboratingfrom.setEnabled(false);
				comboratingto.setEnabled(false);
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
				combogenre.setEnabled(true);
				combotimefrom.setEnabled(true);
				combotimeto.setEnabled(true);
				comboratingfrom.setEnabled(true);
				comboratingto.setEnabled(true);
			}
		});
		btnByParameters.setBounds(301, 23, 107, 16);
		btnByParameters.setText("by parameters");
		
		Button btnGo = new Button(shlYourshows, SWT.NONE);
		btnGo.setBounds(254, 326, 75, 25);
		btnGo.setText("GO");

	}
}
