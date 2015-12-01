package view;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import view.AdminWindow;

public class PasswordWindow extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public PasswordWindow(Shell parent, int style) {
		super(parent, style);
		setText("Enter password");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(270, 160);
		shell.setText(getText());
		
		Label lblPassword = new Label(shell, SWT.NONE);
		lblPassword.setBounds(10, 47, 55, 15);
		lblPassword.setText("Password:");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(82, 44, 172, 21);
		
		Label lblWrong = new Label(shell, SWT.NONE);
		lblWrong.setVisible(false);
		lblWrong.setBounds(82, 71, 154, 15);
		lblWrong.setText("Wrong password. Try again");
		
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnCancel.setBounds(179, 96, 75, 25);
		btnCancel.setText("Cancel");
		
		Button btnOk = new Button(shell, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (text.getText() != "admin") {
					System.out.print(text.getText());
					lblWrong.setVisible(true);
				}
				else {
					AdminWindow window = new AdminWindow();
					window.open();
					
				}
			}
		});
		btnOk.setBounds(98, 96, 75, 25);
		btnOk.setText("OK");
		
	}
}
