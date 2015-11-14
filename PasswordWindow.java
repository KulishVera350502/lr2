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
		btnOk.setBounds(98, 96, 75, 25);
		btnOk.setText("OK");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(82, 44, 172, 21);
		
		Label lblPassword = new Label(shell, SWT.NONE);
		lblPassword.setBounds(10, 47, 55, 15);
		lblPassword.setText("Password");
		

	}
}
