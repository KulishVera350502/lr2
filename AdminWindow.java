package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Composite;

public class AdminWindow {

	protected Shell shell;
	private Text text;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AdminWindow window = new AdminWindow();
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
		shell.setSize(350, 450);
		shell.setText("SWT Application");
		
		CTabFolder tabFolder = new CTabFolder(shell, SWT.BORDER);
		tabFolder.setBounds(0, 0, 334, 411);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmNewItem = new CTabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("New Item");
		
		Group group_1 = new Group(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(group_1);
		
		Label lblName = new Label(group_1, SWT.NONE);
		lblName.setBounds(10, 50, 55, 15);
		lblName.setText("Name: *");
		
		text = new Text(group_1, SWT.BORDER);
		text.setBounds(70, 47, 200, 21);
		
		Label label = new Label(group_1, SWT.NONE);
		label.setText("Name: *");
		label.setBounds(10, 91, 55, 15);
		
		text_1 = new Text(group_1, SWT.BORDER);
		text_1.setBounds(70, 88, 200, 21);
		
		CTabItem tbtmNewItem_1 = new CTabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("New Item");
		
		Group group = new Group(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(group);
		
		

	}
}
