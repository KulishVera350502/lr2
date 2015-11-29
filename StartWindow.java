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
import app.DatabaseConnection;

import java.sql.*;

public class StartWindow {

	protected Shell shell;
	
	Connection connection = null;

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
		connection = DatabaseConnection.dbConnector();
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
				MainWindow window = new MainWindow(connection);
				window.open();
				shell.close();
			}
		});
		btnUser.setBounds(100, 214, 75, 25);
		btnUser.setText("User");
		
		Button btnExit = new Button(shell, SWT.NONE);
		btnExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnExit.setBounds(100, 263, 75, 25);
		btnExit.setText("Exit");

	}
}
