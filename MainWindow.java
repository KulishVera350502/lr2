package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.StackLayout;

public class MainWindow {

	protected Shell shlYourshows;

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
		shlYourshows.setLayout(new StackLayout());
		
		Label lblShow = new Label(shlYourshows, SWT.SHADOW_IN);
		lblShow.setText("Show #1: ");
		
		Combo comboshow1 = new Combo(shlYourshows, SWT.NONE);
		
		Label lblShow_1 = new Label(shlYourshows, SWT.NONE);
		lblShow_1.setText("Show #2: ");
		
		Combo comboshow2 = new Combo(shlYourshows, SWT.NONE);
		
		Label lblShow_2 = new Label(shlYourshows, SWT.NONE);
		lblShow_2.setText("Show #3: ");
		
		Combo comboshow3 = new Combo(shlYourshows, SWT.NONE);
		
		Label lblShow_3 = new Label(shlYourshows, SWT.NONE);
		lblShow_3.setText("Show #4: ");
		
		Combo comboshow4 = new Combo(shlYourshows, SWT.NONE);
		
		Label lblShow_4 = new Label(shlYourshows, SWT.NONE);
		lblShow_4.setText("Show #5: ");
		
		Combo comboshow5 = new Combo(shlYourshows, SWT.NONE);
		
		Label lblGenre = new Label(shlYourshows, SWT.NONE);
		lblGenre.setText("Genre: ");
		
		Combo combogenre = new Combo(shlYourshows, SWT.NONE);
		combogenre.setEnabled(false);
		
		Label lblTimePeriod = new Label(shlYourshows, SWT.NONE);
		lblTimePeriod.setText("Time period: ");
		
		Label label_2 = new Label(shlYourshows, SWT.NONE);
		label_2.setText("from");
		
		Combo combotimefrom = new Combo(shlYourshows, SWT.NONE);
		combotimefrom.setEnabled(false);
		
		Label label_3 = new Label(shlYourshows, SWT.NONE);
		label_3.setText("to");
		
		Combo combotimeto = new Combo(shlYourshows, SWT.NONE);
		combotimeto.setEnabled(false);
		
		Label lblNewLabel = new Label(shlYourshows, SWT.NONE);
		lblNewLabel.setText("Rating: ");
		
		Label label_1 = new Label(shlYourshows, SWT.NONE);
		label_1.setText("from");
		
		Combo comboratingfrom = new Combo(shlYourshows, SWT.NONE);
		comboratingfrom.setEnabled(false);
		
		Label label = new Label(shlYourshows, SWT.NONE);
		label.setText("to");
		
		Combo comboratingto = new Combo(shlYourshows, SWT.NONE);
		comboratingto.setEnabled(false);
		
		Button btnOnlyTheOnes = new Button(shlYourshows, SWT.CHECK);
		btnOnlyTheOnes.setText("Only the ones that ended");
		
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
		btnByParameters.setText("by parameters");
		
		Button btnGo = new Button(shlYourshows, SWT.NONE);
		btnGo.setText("GO");

	}
}
