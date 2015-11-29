package view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class ResultWindow {

	protected Shell shell;
	Table table;
	
	public List<String> shows = new ArrayList<String>();
	
	public List<String> genres = new ArrayList<String>();
	public int yearFrom;
	public int yearTo;
	public double ratingFrom;
	public double ratingTo;
	public int seasonsFrom;
	public int seasonsTo;
	
	public boolean showIsClosed; 

	public ResultWindow() {
		// TODO Auto-generated constructor stub
	}

	public ResultWindow(List<String> shows, boolean showIsClosed) {
		// TODO Auto-generated constructor stub
		
	}

	public ResultWindow(List<String> genre_, int seasonsFrom_, int seasonsTo_, 
						double ratingFrom_, double ratingTo_, 
						int yearFrom_, int yearTo_,
						boolean showIsClosed_) {
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ResultWindow window = new ResultWindow();
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		

		shell = new Shell();
		shell.setSize(400, 300);
		shell.setText("SWT Application");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setBounds(0, 0, 434, 261);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("name");
		
		TableColumn tblclmnRating = new TableColumn(table, SWT.NONE);
		tblclmnRating.setWidth(100);
		tblclmnRating.setText("rating");
		
		TableColumn tblclmnYear = new TableColumn(table, SWT.NONE);
		tblclmnYear.setWidth(100);
		tblclmnYear.setText("year");
		
		TableColumn tblclmnGenre = new TableColumn(table, SWT.NONE);
		tblclmnGenre.setWidth(100);
		tblclmnGenre.setText("genre");
		

	}

}
