//import com.nickolasboyce.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.io.*;

public class EFrame extends JFrame implements ActionListener {

	// CLASS DECLARATIONS
	private JMenuBar menubar;

	  private JMenu menua;
	  private JMenu menub;
	  private JMenu menuc;
	  private JMenu menud;
	  private JMenu menue;

	private JToolBar toolBar;
	public JToolBar addressBar;
	public JToolBar findBar;
	public JToolBar replBar;
	public JToolBar statusBar;

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

	public JTextField addressField;
	  public JTextField findField;
	  public JTextField replField;

		private JButton find;
		private JButton replace;

	  public JLabel statusLabel;
	  	public int wordcount= 0;
	  	public int linecount= 0;

	public JPanel linesPanel;
	  public JLabel linesLabel;

	public JTabbedPane tabsPane;
	  public JScrollPane scrollPane;
	  public JTextArea contentArea;

	public HashMap<String,String> tabsHashMap= new HashMap<String,String>();

	  public int tabindex;
	  public String tabtitle;
		public String thepath;
		public String thetext;

		private Boolean tb_visible= true;
		private Boolean ab_visible= true;
		private Boolean sb_visible= true;
		private Boolean find_visible= false;
		private Boolean repl_visible= false;
		private Boolean ww_bool= true;
		private Boolean ln_bool= false;
		private Integer zm_factor= 100;

		public Highlighter highlighter;
		public Highlighter.HighlightPainter painter;
		public DefaultCaret caret;
			public boolean startover= false;
			public int startfrom= 0;
			public int hi_index= 0;
			public int hi_endex= 0;

		private Editor theEditor= new Editor();
		private EFrame theWindow= this;
		  private EFileOpen theFileOpen;
		  private EFileSave theFileSave;
		  private EFileSaveAs theFileSaveAs;

		public String thispath= System.getProperty("user.dir");
		public String homepath= System.getProperty("user.home");
		public String username= System.getProperty("user.name");
		public String desktop= System.getenv("XDG_CURRENT_DESKTOP");


	public EFrame() {


		super("Editor Window  ");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				ImageIcon titleImage= new ImageIcon(thispath+"/images/favicon.png");
				setIconImage(titleImage.getImage());

		  	this.addWindowFocusListener( new WindowAdapter() {
               	public void windowGainedFocus(WindowEvent e) {

        			theEditor.setCurrentWindow(theWindow);  // <-- SET CURRENT INSTANCE

        		}
	  		});


		// MENUBAR
		menubar= new JMenuBar();

		JMenuItem ja1= new JMenuItem("New File");
		JMenuItem ja2= new JMenuItem("New Window");
		JMenuItem ja3= new JMenuItem("Open File...");
		JMenuItem ja4= new JMenuItem("Save");
		JMenuItem ja5= new JMenuItem("Save As...");
		JMenuItem ja6= new JMenuItem("Print...");
		JMenuItem ja7= new JMenuItem("Close");
		  menua= new JMenu("File");
		menua.add(ja1);
		menua.add(ja2);
		  menua.addSeparator();
		menua.add(ja3);
		menua.add(ja4);
		menua.add(ja5);
		  menua.addSeparator();
		menua.add(ja6);
		  menua.addSeparator();
		menua.add(ja7);
			ja1.addActionListener(this);
			ja2.addActionListener(this);
			ja3.addActionListener(this);
			ja4.addActionListener(this);
			ja5.addActionListener(this);
			ja6.addActionListener(this);
			ja7.addActionListener(this);
		JMenuItem jb1= new JMenuItem("Cut");
		JMenuItem jb2= new JMenuItem("Copy");
		JMenuItem jb3= new JMenuItem("Paste");
		JMenuItem jb4= new JMenuItem("Undo");
		JMenuItem jb5= new JMenuItem("Redo");
		JMenuItem jb6= new JMenuItem("Find...");
		JMenuItem jb7= new JMenuItem("Replace...");
		JMenuItem jb8= new JMenuItem("Preferences");
		  menub= new JMenu("Edit");
		menub.add(jb1);
		menub.add(jb2);
		menub.add(jb3);
		  menub.addSeparator();
		menub.add(jb4);
		menub.add(jb5);
			menub.addSeparator();
		menub.add(jb6);
		menub.add(jb7);
			menub.addSeparator();
		menub.add(jb8);
			jb1.addActionListener(this);
 			jb2.addActionListener(this);
 			jb3.addActionListener(this);
 			jb4.addActionListener(this);
 			jb5.addActionListener(this);
			jb6.addActionListener(this);
			jb7.addActionListener(this);
			jb8.addActionListener(this);
		JMenuItem jc1= new JMenuItem("Word Wrap");
		JMenuItem jc2= new JMenuItem("Line Numbers");
		JMenuItem jc3= new JMenuItem("Toolbar");
		JMenuItem jc4= new JMenuItem("Addressbar");
		JMenuItem jc5= new JMenuItem("Statusbar");
		JMenuItem jc6= new JMenuItem("Zoom -/+");
		JMenuItem jc7= new JMenuItem("Enter Fullscreen");
		  menuc= new JMenu("View");
		menuc.add(jc1);
		menuc.add(jc2);
		  menuc.addSeparator();
		menuc.add(jc3);
		menuc.add(jc4);
		menuc.add(jc5);
		menuc.add(jc6);
		  menuc.addSeparator();
		menuc.add(jc7);
			jc1.addActionListener(this);
 			jc2.addActionListener(this);
 			jc3.addActionListener(this);
 			jc4.addActionListener(this);
 			jc5.addActionListener(this);
			jc6.addActionListener(this);
			jc7.addActionListener(this);
		JMenuItem jd1= new JMenuItem("Fonts");
		JMenuItem jd2= new JMenuItem("Color Scheme");
		JMenuItem jd3= new JMenuItem("Tabs...");
		JMenuItem jd4= new JMenuItem("Line Endings...");
		JMenuItem jd5= new JMenuItem("Encoding...");
		  menud= new JMenu("Tools");
		menud.add(jd1);
		menud.add(jd2);
		menud.add(jd3);
		menud.add(jd4);
		menud.add(jd5);
			jd1.addActionListener(this);
 			jd2.addActionListener(this);
 			jd3.addActionListener(this);
 			jd4.addActionListener(this);
 			jd5.addActionListener(this);
		JMenuItem je1= new JMenuItem("Application Info");
		JMenuItem je2= new JMenuItem("Submit Feedback");
		JMenuItem je3= new JMenuItem("Help...");
		  menue= new JMenu("Help");
		menue.add(je1);
		  menue.addSeparator();
		menue.add(je2);
	   	menue.add(je3);
		 	je1.addActionListener(this);
			je2.addActionListener(this);
			je3.addActionListener(this);

		  menubar.add(menua);
		  menubar.add(menub);
		  menubar.add(menuc);
		  menubar.add(menud);
		  menubar.add(menue);

		// TOOLBAR + ADDRESSBAR
		toolBar= new JToolBar();
		addressBar= new JToolBar();

		/*try {
			thispath= new File(".").getCanonicalPath();
		} catch (IOException ioe) { ioe.getMessage(); }*/

		ImageIcon newIcon= new ImageIcon(thispath+"/images/actions/document-new.png");
		ImageIcon openIcon= new ImageIcon(thispath+"/images/actions/document-open.png");
		ImageIcon saveIcon= new ImageIcon(thispath+"/images/actions/document-save.png");
		ImageIcon saveasIcon= new ImageIcon(thispath+"/images/actions/document-save-as.png");
		ImageIcon clearIcon= new ImageIcon(thispath+"/images/actions/edit-delete.png");
		   newButton= new JButton(newIcon);
		   openButton= new JButton(openIcon);
		   saveButton= new JButton(saveIcon);
		   saveasButton= new JButton(saveasIcon);
		   clearButton= new JButton(clearIcon);

		ImageIcon cutIcon= new ImageIcon(thispath+"/images/actions/edit-cut.png");
		ImageIcon copyIcon= new ImageIcon(thispath+"/images/actions/edit-copy.png");
		ImageIcon pasteIcon= new ImageIcon(thispath+"/images/actions/edit-paste.png");
		   cutButton= new JButton(cutIcon);
		   copyButton= new JButton(copyIcon);
		   pasteButton= new JButton(pasteIcon);

		ImageIcon undoIcon= new ImageIcon(thispath+"/images/actions/edit-undo.png");
 		ImageIcon redoIcon= new ImageIcon(thispath+"/images/actions/edit-redo.png");
 		ImageIcon findIcon= new ImageIcon(thispath+"/images/actions/edit-find.png");
 		ImageIcon gotoIcon= new ImageIcon(thispath+"/images/actions/go-jump.png");
 		   undoButton= new JButton(undoIcon);
 		   redoButton= new JButton(redoIcon);
 		   findButton= new JButton(findIcon);
 		   gotoButton= new JButton(gotoIcon);

		ImageIcon homeIcon= new ImageIcon(thispath+"/images/actions/folder-home.png");
	 	   homeButton= new JButton(homeIcon);

		ImageIcon handleIcon= new ImageIcon(thispath+"/images/handle.png");
		  JLabel handle1= new JLabel(handleIcon);
		  JLabel handle2= new JLabel(handleIcon);
		  JLabel handle3= new JLabel(handleIcon);
		  JLabel handle4= new JLabel(handleIcon);

			newButton.addActionListener(this);
			openButton.addActionListener(this);
			saveButton.addActionListener(this);
			saveasButton.addActionListener(this);
			clearButton.addActionListener(this);
			cutButton.addActionListener(this);
			copyButton.addActionListener(this);
			pasteButton.addActionListener(this);
			undoButton.addActionListener(this);
			redoButton.addActionListener(this);
			findButton.addActionListener(this);
			gotoButton.addActionListener(this);
			homeButton.addActionListener(this);

		  toolBar.add(newButton);
		  toolBar.add(openButton);
		  toolBar.add(saveButton);
		  toolBar.add(saveasButton);
			toolBar.add(clearButton);

			toolBar.add(handle2);
		  toolBar.add(cutButton);
		  toolBar.add(copyButton);
		  toolBar.add(pasteButton);

			toolBar.add(handle3);
		  toolBar.add(undoButton);
		  toolBar.add(redoButton);
		  toolBar.add(findButton);
		  toolBar.add(gotoButton);

			toolBar.add(handle4);
		  toolBar.add(homeButton);

			toolBar.setVisible(true);

		addressField= new JTextField(""); thepath= "";
			addressField.addActionListener(this);
				addressBar.add(addressField);
				addressBar.setVisible(true);

		// ADD BOTH TO A PANEL  <-----
		JPanel topBar = new JPanel();
	 		topBar.setLayout(new BorderLayout());
	 		topBar.add(toolBar,BorderLayout.NORTH);
			topBar.add(addressBar,BorderLayout.CENTER);


		// SCROLLPANE <= TEXTAREA ------------------
		contentArea= new JTextArea();
			contentArea.setText("");
			contentArea.setLineWrap(true);
			contentArea.setWrapStyleWord(true);
			contentArea.setTabSize(4);

		scrollPane= new JScrollPane(contentArea,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
	   		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// -----------------------------------------


		// FINDBAR + REPLACEBAR + STATUSBAR
		findBar= new JToolBar();
		  findField= new JTextField("");
		  find= new JButton(" FIND ");
		replBar= new JToolBar();
  		  replField= new JTextField("");
		  replace= new JButton(" REPLACE ");

		statusBar= new JToolBar();
		  statusLabel= new JLabel("");

		findBar.add(findField);
		findBar.add(find);
		  findBar.setVisible(false);
			findField.addActionListener(this);
			find.addActionListener(this);
		replBar.add(replField);
		replBar.add(replace);
  		  replBar.setVisible(false);
  			replField.addActionListener(this);
			replace.addActionListener(this);

			contentArea.addFocusListener( new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					highlighter.removeAllHighlights();
					startfrom= 0; hi_index= 0; hi_endex= 0;
				}
			});

			contentArea.addMouseListener( new MouseAdapter() {
			      public void mouseClicked(MouseEvent e) {
					  	if (e.getClickCount() == 1) {
					  		caret= (DefaultCaret)contentArea.getCaret();
					  		int someindex= caret.getDot();
					  			startfrom= someindex;
					  	}
				  }
			});

		  statusBar.add(statusLabel);
		  statusBar.setVisible(true);

		// ADD BOTH TO A PANEL  <-----
		JPanel bottomBar = new JPanel();
	 		bottomBar.setLayout(new BorderLayout());
			bottomBar.add(findBar,BorderLayout.NORTH);
			bottomBar.add(replBar,BorderLayout.CENTER);
			bottomBar.add(statusBar,BorderLayout.SOUTH);


			// LAYOUT
			BorderLayout brdrlayout= new BorderLayout();
				setLayout(brdrlayout);
					add("North",topBar);
						add("Center",scrollPane);
							//add("West",linesPanel);
							add("South",bottomBar);
				setJMenuBar(menubar);
				pack();

		  setSize(960,640);
		  setVisible(true);


		statusLabel.setText(":: Word count: "+wordcount+"  :: Line count: "+linecount+"  ::  _100%_   :: username  :: desktop  :: tab size  :: line endings  :: encoding");
		//

	}

	public void setStatusLabel(int wcount, int lcount, int tabsize, String lendings, String encoding) {

		this.statusLabel.setText("::  Word count: "+wcount+"  ::  Line count: "
										+lcount+"                          _"+zm_factor+"%_                        ::  "
										+username+"   ::  "
										+desktop+"   ::  Tabs="
										+tabsize+"   ::  "
										+lendings+"   ::  "
										+encoding+"");

	}

	public boolean compareFiles(String path, String text) {

		//
		if (path.equals("") && text.equals(""))  return true;

		ReadinFile rif= new ReadinFile();
			String filetext= rif.readToString(path);

			int result= text.compareTo(filetext);

			  	if (result==0) { return true; }
			  	else { return false; }

	}

	public boolean getOptionPane(String icon, String str1, String str2) {

		ImageIcon confirmIcon= new ImageIcon(icon);
			JPanel confirm = new JPanel();
		      confirm.setSize(new Dimension(280, 120));
		      confirm.setLayout(null);
		   	JLabel otherLabel = new JLabel(str1);
		      otherLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		      otherLabel.setBounds(20, 20, 260, 30);
		      otherLabel.setHorizontalAlignment(SwingConstants.CENTER);
		      	confirm.add(otherLabel);
		    JLabel confirmLabel = new JLabel(str2);
		      confirmLabel.setVerticalAlignment(SwingConstants.TOP);
		      confirmLabel.setHorizontalAlignment(SwingConstants.CENTER);
		      confirmLabel.setBounds(20, 80, 260, 20);
		      	confirm.add(confirmLabel);

	    UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));
	      int response = JOptionPane.showConfirmDialog(null, confirm, "File System",
	      													JOptionPane.OK_CANCEL_OPTION,
	      													JOptionPane.QUESTION_MESSAGE, confirmIcon);
		        if (response == JOptionPane.OK_OPTION) {
		        	System.out.println("... OK");
		         	  return true;
		        } else {
		        	System.out.println("... CANCEL");
		         	  return false;
		        }

	}

	public void findEvent() {

		if (!findField.getText().equals("")) {
		try {
			String findstr= this.findField.getText();
			String contentstr= this.contentArea.getText();
			  int index= contentstr.indexOf(findstr,startfrom);
			  int endex= index+findstr.length();
			  int last= contentstr.lastIndexOf(findstr);

			highlighter= contentArea.getHighlighter();
			highlighter.removeAllHighlights();
			  painter= painter = new DefaultHighlighter.DefaultHighlightPainter(Color.LIGHT_GRAY);

			  //  find highlight from last highlight endex
			  hi_index= index; hi_endex= endex; startfrom= endex;
				caret.setDot(endex);

				highlighter.addHighlight(index, endex, painter);

			if (startover) {  startover= false; startfrom= 0; hi_index= 0; hi_endex= 0; }
			if (index == last)  startover= true;


		} catch (BadLocationException ble) {
			ble.getMessage();

		}
		}

	}

	public void replaceEvent() {

		if (startfrom != 0) {
		if (!findField.getText().equals("")) {
			String replstr= this.replField.getText();
			String contentstr= this.contentArea.getText();
				int index= hi_index; int endex= hi_endex; startfrom= endex;
				  caret.setDot(endex);

			  String begin= contentstr.substring(0,index);
			  String end= contentstr.substring(endex,contentstr.length());
			  	contentArea.setText(begin+replstr+end);
		}
		}

	}


	@Override
	public void actionPerformed(ActionEvent event) {

		Object source= event.getSource();
		String src= event.getSource().toString();
			//System.out.println(src);

		thetext= this.contentArea.getText();
		thepath= this.addressField.getText();
			String filetext= "";
			String filepath= "";

			//////////////////////////////////////////////
			// NEW WINDOW
			if (src.matches("(.*)text\\=New\\ Window(.*)") || (source==this.newButton)) {
				System.out.println("NEW WINDOW");
					theEditor.getNewWindow("");
			}
			// OPEN FILE
			if (src.matches("(.*)text\\=Open\\ File\\.\\.\\.(.*)") || (source==this.openButton)) {
				System.out.println("OPEN");
					if (thepath.equals(""))  thepath= theEditor.getHomePath();
					theEditor.getFileOpen(thepath);
			}
			// SAVE
			if (src.matches("(.*)text\\=Save(?!\\ As)(.*)") || (source==this.saveButton)) {
				System.out.println("SAVE");
					thepath= this.addressField.getText();
					thetext= this.contentArea.getText();
						boolean same= compareFiles(thepath,thetext);
					if (!same) {
						String icon_str= thispath+"/images/actions/dialog-question.png";
					   	String label_str1= "This will overwrite the current file contents.  ";
					    String label_str2= "Continue?  ";
					    	boolean ok_bool= getOptionPane(icon_str,label_str1,label_str2);
					    	if (ok_bool) { theEditor.setFileSave(theWindow,thepath,thetext); }
					    	else { theEditor.getFileSave(thepath); }
				    }
			}
			// SAVE AS
			if (src.matches("(.*)text\\=Save\\ As\\.\\.\\.(.*)") || (source==this.saveasButton)) {
				System.out.println("SAVE AS");
					if (thepath.equals("")) { thepath= theEditor.getHomePath(); }
					else { theEditor.getFileSaveAs(thepath); }
			}
			// PRINT
			if (src.matches("(.*)text\\=Print(.*)")) {
				System.out.println("PRINT");

			}
			// CLOSE
			if (src.matches("(.*)text\\=Close(.*)")) {
				System.out.println("CLOSE");
					thepath= this.addressField.getText();
					thetext= this.contentArea.getText();
						boolean same= compareFiles(thepath,thetext);
					if (!same) {
						String icon_str= thispath+"/images/actions/dialog-warning.png";
					   	String label_str1= "The file contents were changed.  ";
					    String label_str2= "Close without saving?  ";
					    	boolean ok_bool= getOptionPane(icon_str,label_str1,label_str2);
					    	if (ok_bool) { this.dispose(); }
					    	else { theEditor.getFileSaveAs(thepath); }
				    } else {
				    	this.dispose();
				    }
			}
			// CLEAR (NEW FILE)
			if (src.matches("(.*)text\\=New\\ File(.*)") || (source==this.clearButton)) {
				System.out.println("CLEAR");
					thepath= this.addressField.getText();
					thetext= this.contentArea.getText();
						boolean same= compareFiles(thepath,thetext);
					if (!same) {
						String icon_str= thispath+"/images/actions/dialog-warning.png";
					   	String label_str1= "The file contents were changed.  ";
					    String label_str2= "Delete without saving?  ";
					    	boolean ok_bool= getOptionPane(icon_str,label_str1,label_str2);
					    	if (ok_bool) {
					    		thepath= ""; thetext= "";
								this.addressField.setText("");
								this.contentArea.setText("");
					    	} else { theEditor.getFileSaveAs(thepath); }
				    } else {
				    	thepath= ""; thetext= "";
						this.addressField.setText("");
						this.contentArea.setText("");
				    }
			}

			////////////////////////////////////////////////////////////////////
			// CUT
			if (src.matches("(.*)text\\=Cut(.*)") || (source==this.cutButton)) {
				System.out.println("CUT");
					this.contentArea.cut();
			}
			// COPY
			if (src.matches("(.*)text\\=Copy(.*)") || (source==this.copyButton)) {
				System.out.println("COPY");
					this.contentArea.copy();
			}
			// PASTE
			if (src.matches("(.*)text\\=Paste(.*)") || (source==this.pasteButton)) {
				System.out.println("PASTE");
					this.contentArea.paste();
			}
			// FIND
			if (src.matches("(.*)text\\=Find\\.\\.\\.(.*)")) {
				System.out.println("FIND");
					if (find_visible) { find_visible= false; } else { find_visible= true; }
					  this.findBar.setVisible(find_visible);
			}
			// REPLACE
			if (src.matches("(.*)text\\=Replace\\.\\.\\.(.*)")) {
				System.out.println("REPLACE");
					if (repl_visible) { repl_visible= false; } else { repl_visible= true; }
					  this.replBar.setVisible(repl_visible);
			}
			// FIND + REPLACE
			if ((source==this.findButton)) {
				System.out.println("FIND + REPLACE");
					if (find_visible) { find_visible= false; repl_visible= false; }
					else { find_visible= true; repl_visible= true; }
				  	  this.findBar.setVisible(find_visible);
					  this.replBar.setVisible(repl_visible);
			}

			//////////////////////////////////////
			// HOME
			if ((source==this.homeButton)) {
				System.out.println("HOME");
					thepath= homepath;
					  this.addressField.setText(homepath);
					  this.contentArea.setText("");
			}
			// VIEW OPTIONS
			if (src.matches("(.*)text\\=Word\\ Wrap(.*)")) {
				System.out.println("WORDWRAP");
					if(ww_bool) { ww_bool= false; } else { ww_bool= true; }
				      this.contentArea.setLineWrap(ww_bool);
					  this.contentArea.setWrapStyleWord(true);
			}
			if (src.matches("(.*)text\\=Toolbar(.*)")) {
				System.out.println("TOOLBAR");
					if(tb_visible) { tb_visible= false; } else { tb_visible= true; }
					  this.toolBar.setVisible(tb_visible);
			}
			if (src.matches("(.*)text\\=Addressbar(.*)")) {
				System.out.println("ADDRESSBAR");
					if(ab_visible) { ab_visible= false; } else { ab_visible= true; }
					  this.addressBar.setVisible(ab_visible);
			}
			if (src.matches("(.*)text\\=Statusbar(.*)")) {
				System.out.println("STATUSBAR");
					if(sb_visible) { sb_visible= false; } else { sb_visible= true; }
					  this.statusBar.setVisible(sb_visible);
			}
			// ADDRESSBAR
			if ((source==this.addressField)) {
				System.out.println("ADDRESS FIELD");
					filepath= thepath;
					ReadinFile rif= new ReadinFile();
					filetext= rif.readToString(filepath);
		  			  this.addressField.setText(filepath);
		  			  this.contentArea.setText(filetext);
			}
			// FIND...
			if ((source==this.findField || (source==this.find))) {
				System.out.println("FIND");
					this.findEvent();
			}
			// REPLACE...
			if ((source==this.replField || (source==this.replace))) {
				System.out.println("REPLACE");
					this.replaceEvent();
					this.findEvent();

			}

	}


		////////////////////////////////////////
		public static void main(String[] args) {
			//EFrame editor= new EFrame();
		}


}
