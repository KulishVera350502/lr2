package view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class ResultWindow {

	protected Shell shell = new Shell();
	Table table;
	
	public List<String> tvshows = new ArrayList<String>();

	/**
	 * @wbp.parser.entryPoint
	 */
	public ResultWindow(List<String> tvshows_) {
		// TODO Auto-generated constructor stub
		tvshows = tvshows_;
	}
		
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		
		if(tvshows.isEmpty()) {
			MessageBox emptyListBox = new MessageBox(shell);
			emptyListBox.setMessage("No TVshows were found!");
			emptyListBox.open();
		}
		else {
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
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		
		shell.setSize(500, 340);
		shell.setText("Search result");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setBounds(0, 0, 485, 300);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
		tblclmnName.setWidth(150);
		tblclmnName.setText("name");
		
		TableColumn tblclmnGenre = new TableColumn(table, SWT.NONE);
		tblclmnGenre.setWidth(150);
		tblclmnGenre.setText("genre");
		
		TableColumn tblclmnYear = new TableColumn(table, SWT.NONE);
		tblclmnYear.setWidth(50);
		tblclmnYear.setText("year");
		
		TableColumn tblclmnRating = new TableColumn(table, SWT.NONE);
		tblclmnRating.setWidth(50);
		tblclmnRating.setText("rating");
		
		TableColumn tblclmnSeasons = new TableColumn(table, SWT.NONE);
		tblclmnSeasons.setWidth(60);
		tblclmnSeasons.setText("seasons");
				
		TableItem item;
		for (int i = 0, row = 0; i < tvshows.size()-4; i+=5, row++) {
			item = new TableItem(table, row);
			for (int j = 0; j < 5; j++)
				item.setText(j, tvshows.get(i+j));
		}	
	}
}
