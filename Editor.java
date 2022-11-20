//import com.nickolasboyce.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class Editor {

	/*
	private JMenuBar menubar;

	  private JMenu menua;
	  private JMenu menub;
	  private JMenu menuc;
	  private JMenu menud;
	  private JMenu menue;

	private JToolBar toolbar;

	  private JButton newButton;
	  private JButton openButton;
	  private JButton saveButton;
	  private JButton saveasButton;
	  private JButton clearButton;
	  private JButton cutButton;
	  private JButton copyButton;
	  private JButton pasteButton;
	  private JButton undoButton;
	  private JButton redoButton;
	  private JButton findButton;
	  private JButton gotoButton;
	  private JButton homeButton;

		public JToolBar addressbar;
		public JToolBar statusbar;
		public JPanel linespanel;

		public JTextField addressField;
		public JLabel statusLabel;
		public JLabel linesLabel;
		*/


	// ENV VARIABLES
	public static String thispath= System.getProperty("user.dir");
	public static String homepath= System.getProperty("user.home");
	public static String username= System.getProperty("user.name");
		public static String shell= System.getenv("SHELL");
		public static String language= System.getenv("LANG");
		public static String desktop= System.getenv("XDG_CURRENT_DESKTOP");
		public static String session= System.getenv("XDG_SESSION_DESKTOP");
		  public static int wordcount= 0;
		  public static int linecount= 0;
		  public static int tabsize= 4;
		  public static String lineendings= "CRLF";
		  public static String encoding= "UTF-8";
		  	public static String[] textarray;

	public static EFrame someWindow;
	public static EFrame currentWindow;
	public static String currentpath= "";
	public static String currenttext= "";
	  public static int currentindex= 0;

	  public static JTabbedPane currentTabs;
	  public static JScrollPane currentScroll;
	  public static JTextArea currentContent;

	    public static EFrame awindow;
	      public static ArrayList<EFrame> windowArrayList= new ArrayList<EFrame>();
		public static String apath;
		  public static ArrayList<String> pathArrayList= new ArrayList<String>();
		public static String atext;
		  public static ArrayList<String> textArrayList= new ArrayList<String>();

		  /*
		  private Boolean tb_visible= true;
		  private Boolean ab_visible= true;
		  private Boolean sb_visible= true;
		  private Boolean ww_bool= true;
		  private Boolean ln_bool= false;
		  private Integer zm_factor= 100;
		  */
		  

		// FIRST INSTANCE
		private static boolean firstwindow= true;



	//
	public Editor() { firstwindow= false; }



	// ACCESSOR METHODS
	public static String getHomePath() {
		return homepath;
	}
	//
	public static String getPath() {
		return currentpath;
	}
	public static void setPath(String path) {
		currentpath= path;
	}
	//
	public static String getText() {
		return currenttext;
	}
	public static void setText(String text) {
		currenttext= text;
	}
	//
	public static EFrame getCurrentWindow() {
		return currentWindow;
	}
	public static void setCurrentWindow(EFrame eframe) {
		currentWindow= eframe;
	}
	//
	public static ArrayList<EFrame> getWindowArrayList() {
		return windowArrayList;
	}
	public static void setWindowArrayList(ArrayList<EFrame> al) {
		windowArrayList= al;
	}
	//
	public static ArrayList<String> getPathArrayList() {
		return pathArrayList;
	}
	public static void setPathArrayList(ArrayList<String> al) {
		pathArrayList= al;
	}
	//
	public static ArrayList<String> getTextArrayList() {
		return textArrayList;
	}
	public static void setTextArrayList(ArrayList<String> al) {
		textArrayList= al;
	}


	// WINDOW METHODS
	public static void getRootWindow() {
		someWindow= new EFrame();
		windowArrayList.add(someWindow);
			setCurrentWindow(someWindow);
				//
				int someindex= windowArrayList.indexOf(someWindow);
				System.out.println("This is window : "+someindex);

		currentindex= windowArrayList.indexOf(someWindow);
	}

	public static void getNewWindow(String path) {
		someWindow= new EFrame();
		currenttext= "";
		windowArrayList.add(someWindow);
			setCurrentWindow(someWindow);
				//
				int someindex= windowArrayList.indexOf(someWindow);
				System.out.println("This is window : "+someindex);

		currentindex= windowArrayList.indexOf(someWindow);
		if (currentpath.equals(""))  currentpath= path;
		if (currentpath.equals(""))  currentpath= homepath;
	  		someWindow.addressField.setText("");
	  		someWindow.contentArea.setText("");
	}

	public static void getWindow(String path) {
		someWindow= new EFrame();
		ReadinFile rif= new ReadinFile();
			windowArrayList.add(someWindow);
			setCurrentWindow(someWindow);
				//
				int someindex= windowArrayList.indexOf(someWindow);
				System.out.println("This is window : "+someindex);

		currentindex= windowArrayList.indexOf(someWindow);
		if (path.equals(""))  System.exit(1);
			currentpath= path;
	  		currenttext= rif.readToString(currentpath);
	  		someWindow.addressField.setText(currentpath);
	  		someWindow.contentArea.setText(currenttext);
	  			wordcount= getWordCount(currenttext);
				linecount= getLineCount(currenttext);
					setStatus(someWindow, wordcount, linecount, tabsize, lineendings, encoding);
    }

    // OTHER METHODS
    public static int getWordCount(String text) {

		textarray= text.split("\\s+");
		  int count= textarray.length;

		return count;

	}

	public static int getLineCount(String text) {

		textarray= text.split("\\n+");
		  int count= textarray.length;

		return count;

	}

	public static void setStatus(EFrame eframe, int wcount, int lcount, int tabsize, String lendings, String encoding){

		someWindow= eframe;
		  someWindow.setStatusLabel(wcount, lcount, tabsize, lendings, encoding);

	}


	// FILES METHODS
	public static void getFileOpen(String path) {
		EFileOpen efo= new EFileOpen();
			if (currentpath.equals(""))  currentpath= path;
			if (currentpath.equals(""))  currentpath= homepath;
			if (path.matches(".*\\.[a-z]{3,4}$")) {
				int i= path.lastIndexOf("/");
				currentpath= path.substring(0,i);
			}
	  		efo.pathField.setText(currentpath);
	  		efo.getInstanceEvent(currentpath);
	}

	public static void getFileSave(String path) {
		EFileSave efs= new EFileSave();
				currentpath= path;
	  		efs.pathField.setText(currentpath);
	  		efs.getInstanceEvent(currentpath);
	}

	public static void getFileSaveAs(String path) {
		EFileSaveAs efsa= new EFileSaveAs();
			if (currentpath.equals(""))  currentpath= homepath;
			if (path.equals(""))  path= currentpath;
		  		efsa.pathField.setText(currentpath);
		  		efsa.getInstanceEvent(currentpath);
	}

	// IO METHODS
	public static void setFileOpen(EFrame eframe, String path) {

	  	someWindow= eframe;
	    ReadinFile rif= new ReadinFile();
		currentindex= windowArrayList.indexOf(someWindow);
			if (path.equals(""))  System.exit(1);
				currentpath= path;
		  		currenttext= rif.readToString(currentpath);
	  		someWindow.addressField.setText(currentpath);
			someWindow.contentArea.setText(currenttext);
				wordcount= getWordCount(currenttext);
				linecount= getLineCount(currenttext);
					setStatus(someWindow, wordcount, linecount, tabsize, lineendings, encoding);

	}

	public static void setFileSave(EFrame eframe, String path, String text) {

		someWindow= eframe;
		WriteoutFile wof= new WriteoutFile();
			wof.writeFromString(path,text);

	}

	public static void setFileSaveAs(EFrame eframe, String path, String text) {

		someWindow= eframe;
		WriteoutFile wof= new WriteoutFile();
			wof.writeFromString(path,text);

			someWindow.addressField.setText(path);

	}


		/////////////////////////////////////////
		public static void main(String[] args) {

			if (firstwindow) {  firstwindow= false;
				Editor e= new Editor();
				  e.getRootWindow();
			}

		}


}
