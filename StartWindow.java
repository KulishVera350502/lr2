package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import view.PasswordWindow;
import view.MainWindow;

public class StartWindow {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			StartWindow window = new StartWindow();
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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(300, 400);
		shell.setText("yourShows");
		
		Label lblWelcomeToYourshows = new Label(shell, SWT.NONE);
		lblWelcomeToYourshows.setBounds(76, 45, 133, 15);
		lblWelcomeToYourshows.setText("Welcome to yourShows!");
		
		Label lblWellHelpYou = new Label(shell, SWT.NONE);
		lblWellHelpYou.setBounds(57, 66, 170, 15);
		lblWellHelpYou.setText("We'll help you waste your time!");
		
		Button btnAdmin = new Button(shell, SWT.NONE);
		btnAdmin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PasswordWindow window = new PasswordWindow(shell, SWT.CLOSE);
				window.open();
			}
		});
		btnAdmin.setBounds(100, 167, 75, 25);
		btnAdmin.setText("Admin");
		
		Button btnUser = new Button(shell, SWT.NONE);
		btnUser.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MainWindow window = new MainWindow();
				window.open();
			}
		});
		btnUser.setBounds(100, 214, 75, 25);
		btnUser.setText("User");

	}
}
