//package com.nickolasboyce.io;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.*;
import java.nio.file.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;


public class EFileOpen extends JFrame implements ActionListener {

	private JToolBar toolbar;
	  private JButton prevButton;
	  private JButton nextButton;
	  private JButton upButton;
	  private JButton homeButton;
	  private JButton menuButton;
	  private JButton deleteButton;

	  private ImageIcon folderIcon;
	  private ImageIcon fileIcon;
	  private ImageIcon dirIcon;
	  private ImageIcon docIcon;
	  private ImageIcon xlsIcon;
	  private ImageIcon pptIcon;
	  private ImageIcon imgIcon;
	  private ImageIcon audIcon;
	  private ImageIcon vidIcon;
	  private ImageIcon sysIcon;
	  private ImageIcon pkgIcon;
	  private ImageIcon srcIcon;
	  private ImageIcon execIcon;
	  private ImageIcon linkIcon;
	  private ImageIcon miscIcon;

	private JLabel infoLabel;
	private JLabel spacerLabel;
		public JTextField pathField;
		public JScrollPane scroll;
		public JTextArea content;
		public JTable table;
		public JList list;
	  	  private JButton cancel;
	  	  private JButton ok;

	public String thispath= System.getProperty("user.dir");
	public String homepath= System.getProperty("user.home");
	  public String selectfile= "No file selected.";
	  public int index= 0;
	  public int filesize= 0;

	public boolean firstprev= true;
	public boolean firstnext= true;
	public boolean noadd= false;
	  public String thepath= "";
	  public String somepath= "";
	  public String prevpath= "";
	  public String nextpath= "";
		public ArrayList<String> thePathArrayList= new ArrayList<String>();;
		public ArrayList<String> thePrevArrayList= new ArrayList<String>();
		public ArrayList<String> theNextArrayList= new ArrayList<String>();
		public ArrayList<String> theFileArrayList= new ArrayList<String>();

	public String thelines;
	  public String[] theheaders= {"TYPE ","FILENAME ","OWNER  ","GROUP  ","SIZE ","TIMESTAMP  ","PERMISSIONS  "};
	  public String[][] therows;
		public Object[][] theRows;
  		public Object[][] someRows;
	  	  public String[] theitems;
	  	  public String[] thepaths;

		public String[] mimedoc= { "rtf", "doc", "docx", "dot", "asc", "lwp", "odt", "pdf", "wpd" };
		public String[] mimeimg= { "gif", "bmp", "ico", "eps", "jpg", "jpeg", "png", "ppm", "svg", "tga", "tif", "tiff", "webp", "ai", "cdr", "drw", "fh", "qxd", "psd", "xcf" };
		public String[] mimeaud= { "aac", "aiff", "cda", "mid", "midi", "mp1", "mp2", "mp3", "ogg", "ra", "voc", "vox", "wav", "wma" };
		public String[] mimevid= { "aaf", "avi", "flv", "mpg", "mpeg", "mpe", "mpv", "mp4", "m1v", "m2v", "m4v", "3gp", "webm", "mov", "wmv" };
		public String[] mimesys= { "bak", "bat", "bin", "cnf", "conf", "cfg", "dat", "dll", "dsk", "log", "so", "sys", "tmp", "temp", "raw"};
		public String[] mimepkg= { "7z", "ar", "arj", "deb", "dmg", "gz", "gzip", "iso", "jar", "pkg", "rar", "rpm", "tar", "zip" };
		public String[] mimesrc= { "as", "bat", "cgi", "cmd", "c", "cc", "cpp", "cs", "erb", "js", "java", "lua", "php", "pm", "py", "vbs" };
		public String[] mimeexec= { "apk", "app", "bac", "class", "desktop", "exe", "h", "o", "pl", "pyc", "rb", "sh" };
		  public String[] openpatterns= { ".*?\\.txt$", ".*?\\.text$", ".*?\\.save$", ".*?\\.csv$", ".*?\\.htm$", ".*?\\.html$", ".*?\\.php$", ".*?\\.pl$", ".*?\\.cgi$", ".*?\\.py$", ".*?\\.as$", ".*?\\.as3$", ".*?\\.js$", ".*?\\.java$", ".*?\\.sh$", ".*?\\.c$", ".*?\\.cc$", ".*?\\.cpp$", ".*?\\.cs$", ".*?\\.xml$", ".*?\\.org$", ".*?\\.log$", ".*?\\.man$", ".*?\\.?README$" };

		private Editor theEditor= new Editor();
		private EFrame theWindow;


	public EFileOpen() {


	  super(" Files Window");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon titleimage= new ImageIcon(thispath+"/images/favicon.png");
        setIconImage(titleimage.getImage());


        // TOOLBAR + PATH + BUTTONS
		if (true) {
			//
			toolbar= new JToolBar();

	 		ImageIcon prevIcon= new ImageIcon(thispath+"/images/files/previous.png");
	 		ImageIcon nextIcon= new ImageIcon(thispath+"/images/files/next.png");
	 		ImageIcon upIcon= new ImageIcon(thispath+"/images/files/up.png");
			ImageIcon homeIcon= new ImageIcon(thispath+"/images/files/gohome.png");
			ImageIcon menuIcon= new ImageIcon(thispath+"/images/files/xfce-wm-menu.png");
			ImageIcon deleteIcon= new ImageIcon(thispath+"/images/files/xfce-wm-close.png");

			prevButton= new JButton(prevIcon);
			nextButton= new JButton(nextIcon);
			upButton= new JButton(upIcon);
			homeButton= new JButton(homeIcon);
			menuButton= new JButton(menuIcon);
			deleteButton= new JButton(deleteIcon);
				prevButton.addActionListener(this);
				nextButton.addActionListener(this);
				upButton.addActionListener(this);
				homeButton.addActionListener(this);
				menuButton.addActionListener(this);
				deleteButton.addActionListener(this);

			thepaths= getPaths(homepath);	//////////////////////////////////////////////////////////////////

			pathField= new JTextField(44);
			infoLabel= new JLabel("\n");
			spacerLabel= new JLabel("");
			  pathField.addActionListener(this);

			cancel= new JButton("Cancel");
  			ok= new JButton("Ok");
  			  cancel.addActionListener(this);
  			  ok.addActionListener(this);

			    //
				toolbar.addSeparator();
				//
				toolbar.add(prevButton);
				toolbar.add(nextButton);
				toolbar.add(upButton);
				toolbar.add(homeButton);
				//
				toolbar.addSeparator();
				toolbar.add(menuButton);
				//
				toolbar.addSeparator();
				toolbar.add(pathField);
				//
				toolbar.addSeparator();
				toolbar.add(deleteButton);
				//
				toolbar.addSeparator();
				  toolbar.setRollover(true);
				  toolbar.setFloatable(false);
				  toolbar.setVisible(true);
			//
		}

		// CONTENT + TABLE + LIST + SCROLL
		if (true) {
			//
			folderIcon= new ImageIcon(thispath+"/images/files/hc-mime-folder.png");
			fileIcon= new ImageIcon(thispath+"/images/files/hc-mime-text.png");
			dirIcon= new ImageIcon(thispath+"/images/files/hc-folder.png");
			docIcon= new ImageIcon(thispath+"/images/files/hc-libreoffice-writer.png");
			xlsIcon= new ImageIcon(thispath+"/images/files/hc-libreoffice-calc.png");
			pptIcon= new ImageIcon(thispath+"/images/files/hc-libreoffice-impress.png");
			imgIcon= new ImageIcon(thispath+"/images/files/hc-mime-image.png");
			audIcon= new ImageIcon(thispath+"/images/files/hc-mime-audio.png");
			vidIcon= new ImageIcon(thispath+"/images/files/hc-mime-vidio.png");
			sysIcon= new ImageIcon(thispath+"/images/files/hc-mime-system.png");
			pkgIcon= new ImageIcon(thispath+"/images/files/hc-mime-package.png");
			srcIcon= new ImageIcon(thispath+"/images/files/hc-mime-system-exec.png");
			execIcon= new ImageIcon(thispath+"/images/files/hc-mime-exec.png");
			linkIcon= new ImageIcon(thispath+"/images/files/hc-link.png");
			miscIcon= new ImageIcon(thispath+"/images/files/hc-unknown.png");

			thelines= getContentLines(homepath);
	  		  content= new JTextArea(28,65);
	  			content.setText("");
	    		content.setLineWrap(false);

			therows= getTableRows(homepath);
			theRows= new Object[therows.length][7];
			  table= new JTable(theRows,theheaders) {
			      public Class getColumnClass(int column) {
			        	return (column == 0) ? Icon.class : Object.class;
			      }
			  };
			  setTable(therows,theheaders);
		  	  this.table.addMouseListener( new MouseAdapter() {
			      public void mouseClicked(MouseEvent e) {
			  			String filepath= "";
			  			File fileObj;
						  	filesize= 0;
						  	selectfile= "No file selected.";

					  	if (e.getClickCount() == 1) {
					  		int i= table.getSelectedRow();
							String somefile= table.getValueAt(table.getSelectedRow(),1).toString();
							  therows= getTableRows(filepath); // file size update
					  		  filepath= thepaths[i];
							  fileObj= new File(filepath);
							if (fileObj.isFile()) {
								selectfile= somefile; pathField.setText(filepath); // file selected update
							}
					  	}
				      	if (e.getClickCount() == 2) {
				      		int i= table.getSelectedRow();
							  filepath= thepaths[i];
							  fileObj= new File(filepath);
							if (fileObj.isFile()) {
								for (String pattern : openpatterns) {
									if (filepath.matches(pattern)) {
										getOpenEvent(filepath); break; // else wait for "OK"
									}
								}
							} else if (i!= 0 && i< thepaths.length) { // process a dir open event...
								noadd= false;

								  somepath= thepaths[i];  // <--
								  if (somepath.equals("")) { somepath= "/"; }
						  		  if (somepath.substring(0,2).equals("//")) { somepath= somepath.substring(1,somepath.length()); }
								    setPathArrayList(somepath);  // <--

								  firstprev= true; firstnext= true;
									thePrevArrayList.add(somepath);
									prevpath= thepath;
									//
									therows= getTableRows(somepath);
									  setTable(therows,theheaders);
									  pathField.setText(somepath);
									  	thepath= somepath;
									//
									thepaths= getPaths(somepath);
									  System.out.println(somepath);
							}
						}
						// update info label
						setInfoLabel(thepaths.length,filesize,selectfile);
				  }
			  });

			theitems= getListItems(homepath);
			  list= new JList(theitems);
				list.setFixedCellHeight(28);
				list.setSelectionInterval(0,0);

	 		scroll= new JScrollPane(table,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			//
		}

		// LAYOUT + STARTUP
		if (true) {
			//
			BorderLayout brdrlayout= new BorderLayout();
				setLayout(brdrlayout);
				JPanel npanel= new JPanel();
			   	JPanel cpanel= new JPanel();
			   	JPanel spanel= new JPanel();
					scroll.setAlignmentX(Component.TOP_ALIGNMENT);
					scroll.setAlignmentY(Component.CENTER_ALIGNMENT);
					scroll.setPreferredSize(new Dimension(720,350));

				npanel.add(toolbar);
				cpanel.add(scroll);
				spanel.add(infoLabel);
				spanel.add(cancel);
				spanel.add(ok);
				   add("North",npanel);
				   add("Center",cpanel);
				   add("South",spanel);

			setSize(750,500);
			setVisible(true);
			//
		}

		//
   		setPathArrayList(homepath);
   		  thePrevArrayList.add(homepath);
		  pathField.setText(homepath);
		  infoLabel.setText("\nFile count: "+thepaths.length+" :: "+(filesize/1000)+" KB     Selected : "+selectfile+"                   ");  //  <--

			thepath= homepath;
			//
	}

	public void setInfoLabel(int fcount, int size, String select) {

		this.infoLabel.setText("\nFile count: "+fcount+" :: "+(size/1000)+" KB     Selected : "+select+"                   ");

	}

	private void setTable(String[][] rows, String[] headers) {

		someRows= new Object[rows.length][7];
		for (int x=0; x<rows.length; x++) {
			for (int y=0; y<7; y++) {
				String perm= rows[x][0];
				String type= rows[x][6];
				if (y==0) { someRows[x][6]= therows[x][y]; }
				else if (y==1) {
				  	if (perm.matches("^d.*")) { someRows[x][0]= this.dirIcon; }
					else if (perm.matches("^l.*")) { someRows[x][0]= this.linkIcon; }
					else if (type.matches("(.*)\\.[a-z][a-z]?[a-z]?[a-z]?[a-z]?$")) {
						//  <--
					  	if (type.matches("(.*)\\.[a-z]+?$")) { someRows[x][0]= this.miscIcon; }
					  	if (type.matches("(.*)\\.[a-z]{3}$")) { someRows[x][0]= this.fileIcon; }
					  	if (type.matches("(.*)\\.[a-z]{4}$")) { someRows[x][0]= this.fileIcon; }
					  	for (String m : mimedoc) {
						  	if (type.matches("(.*)\\."+m+"$")) { someRows[x][0]= this.docIcon; }
					  	}
					  	for (String m : mimeimg) {
						  	if (type.matches("(.*)\\."+m+"$")) { someRows[x][0]= this.imgIcon; }
					  	}
					  	for (String m : mimeaud) {
						  	if (type.matches("(.*)\\."+m+"$")) { someRows[x][0]= this.audIcon; }
					  	}
					  	for (String m : mimevid) {
						  	if (type.matches("(.*)\\."+m+"$")) { someRows[x][0]= this.vidIcon; }
					  	}
					  	for (String m : mimesys) {
						  	if (type.matches("(.*)\\."+m+"$")) { someRows[x][0]= this.sysIcon; }
					  	}
						for (String m : mimepkg) {
							if (type.matches("(.*)\\."+m+"$")) { someRows[x][0]= this.pkgIcon; }
						}
					  	for (String m : mimesrc) {
						  	if (type.matches("(.*)\\."+m+"$")) { someRows[x][0]= this.srcIcon; }
					  	}
					  	for (String m : mimeexec) {
						  	if (type.matches("(.*)\\."+m+"$")) { someRows[x][0]= this.execIcon; }
					  	}
						// <--
					}
					else { someRows[x][0]= this.miscIcon; }
					  if (x==0) { someRows[0][0]= ""; }
				}
				else if (y==2) { someRows[x][y]= therows[x][y]; }
				else if (y==3) { someRows[x][y]= therows[x][y]; }
				else if (y==4) { someRows[x][y]= therows[x][y]; }
				else if (y==5) { someRows[x][y]= therows[x][y]; }
				else if (y==6) { someRows[x][1]= therows[x][y]; }
			}
		}
		DefaultTableModel dtm = new DefaultTableModel(someRows, headers);
			this.table.setModel(dtm);

		  this.table.setShowVerticalLines(false);
		  //this.table.setAutoCreateRowSorter(true);
			this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			this.table.getColumnModel().getColumn(0).setPreferredWidth(47);
			this.table.getColumnModel().getColumn(1).setPreferredWidth(182);
			this.table.getColumnModel().getColumn(2).setPreferredWidth(109);
			this.table.getColumnModel().getColumn(3).setPreferredWidth(109);
			this.table.getColumnModel().getColumn(4).setPreferredWidth(57);
			this.table.getColumnModel().getColumn(5).setPreferredWidth(103);
			this.table.getColumnModel().getColumn(6).setPreferredWidth(95);
		  this.table.setFocusable(false);
		  this.table.setRowSelectionAllowed(true);
		  this.table.setIntercellSpacing(new Dimension(0,-2));
			for (int c= 0; c< this.table.getColumnCount(); c++) {
				Class<?> col_class= this.table.getColumnClass(c);
				this.table.setDefaultEditor(col_class, null);
			}

	}


	private ArrayList<String> getLines(String path) {

		String lscommand= "ls -Al "+path;
		  if (path.matches("[;&|]"))  System.exit(1);
     	ArrayList<String> lines= new ArrayList<String>();
        	String nextline= "";

	    	try {
	        	Process process = Runtime.getRuntime().exec(lscommand);
	        	BufferedReader reader = new BufferedReader(
	                   new InputStreamReader(process.getInputStream()));

			        while ((nextline = reader.readLine()) != null) {
			          	lines.add(nextline);
			        }
			        reader.close();

			} catch (IOException e) { e.printStackTrace(); }

		return lines;
	}

	private String[] getPaths(String path) {

		ArrayList<String> lines= getLines(path);
			int l= lines.size();

		String apath= ""; String sym= "";
		  int j= 0; int k= -1;

		String[] paths= new String[l];

			for (String ln : lines) { k++;
				if (ln.matches("^total\\s\\d+$")) continue;

					String[] elems= ln.split("\\s+");
						if (ln.matches("->"))  sym= "*";
						else  sym= "";

				for (String elem : elems) {

					apath= path+"/"+elem+sym;

				if (j==8) { j= 0; break; } else { j++; }
				}

			paths[k]= apath;
			}

		return paths;
	}

	private String getContentLines(String path) {

		ArrayList<String> lines= getLines(path);
			int l= lines.size();

		String line= ""; String space= "  ";
		  String tselem= ""; String sym= ""; String nl= "\n";
		  int j= 0; int k= -1;

		  String[] months= {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
			boolean b= false;
			boolean m= false;
			boolean d= false;

			for (String ln : lines) { k++;

				if (ln.matches("^total\\s\\d+$")) continue;

					String[] elems= ln.split("\\s+");
						if (ln.matches("->"))  sym= "*";
						else  sym= "";

				for (String elem : elems) {
					if (j==6) nl= sym+"\n\n";
					if (j==5) { nl= "\n";
						for (String month : months) {
							if (elem.matches(month)) { b= true; break; }
						}
					}
					if (b) { tselem= elem+" "; m= true; b= false; continue; }
					if (m) { tselem= tselem+elem+", "; d= true; m= false; continue; }
					if (d) { tselem= tselem+elem; d= false; elem= tselem; }
						// formatted for printing
						line= line+space+elem+nl;

				if (j==6) { nl= "\n"; j= 0; break; } else { j++; }
				}

			}

		return line;
	}

	private String[] getListItems(String path) {

		ArrayList<String> lines= getLines(path);
			int l= lines.size();

		String item= "";
		  String tselem= ""; String sym=  "";
		  int j= 0; int k= -1;

		String[] items= new String[l];
		String[] elems= new String[7];

			String space= "  ";
          	String[] months= {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
          	  boolean b= false;
           	  boolean m= false;
           	  boolean d= false;

			for (String ln : lines) { k++; item= "";
  	        	if (ln.matches("^total\\s\\d+$")) continue;

  	         		elems= ln.split("\\s+");
						if (ln.matches("->"))  sym= "*";
						else  sym= "";

  	         	for (String elem : elems) {
					if (elem.matches("->")) {
						items[6]+= "*";
						break;
					}
             		if (j==5) {
           				for (String month : months) {
           					if (elem.matches(month)) { b= true; break; }
           				}
           	  		}
           	  		if (b) { tselem= elem+" "; m= true; b= false; continue; }
             		if (m) { tselem= tselem+elem+", "; d= true; m= false; continue; }
             		if (d) { tselem= tselem+elem; d= false; elem= tselem; }

           			item= item+space+elem;

             	if (j==6) { item+=sym; j= 0; break; } else { j++; }
  	         	}

  		    items[k]= item;
  		    }
  			//this.content.setText(items);

		return items;
	}

	private String[][] getTableRows(String path) {

		ArrayList<String> lines= getLines(path);
			int l= lines.size();

		  String tscol= ""; String sym= "";
		    int j= 0; int k= -1;

		String[] cols= new String[7];
		String[][] rows= new String[l][7];
		  for (int x=0; x<l; x++) {
			for (int y=0; y<7; y++) {
			  rows[x][y]= " ";
			}
		  }

          	String[] months= {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
          	  boolean b= false;
           	  boolean m= false;
           	  boolean d= false;

	        for (String ln : lines) { k++;
	        	if (ln.matches("^total\\s\\d+$")) continue;

	         		cols= ln.split("\\s+");
						if (ln.matches("^l.*$"))  sym= "*";
						else  sym= "";

	         	for (String col : cols) {

					if (j==4) {													//////////
						Integer size= Integer.parseInt(col);							//
						int s= (int)size;												//
						  filesize= filesize+s;											//
					}															//////////
           			if (j==5) {
         				for (String month : months) {
         					if (col.matches(month)) { b= true; break; }
         				}
         	  		}
         	  		if (b) { tscol= col+" "; m= true; b= false; continue; }
           			if (m) { tscol= tscol+col+", "; d= true; m= false; continue; }
           			if (d) { tscol= tscol+col; d= false; col= tscol; }

					cols[j]= col;

           		if (j==6) { cols[j]+=sym; j= 0; break; } else { j++; }
	         	}

			rows[k]= cols;
		    }
			//this.content.setText(lines);

		return rows;
	}

	private String getPath() {
		String path= this.pathField.getText();

			if (!path.equals("")) {
				index= path.length()-1;
		  		if (path.charAt(index) == '/')  path= path.substring(0, index);
			}

		return path;
	}

	private ArrayList<String> getPathArrayList(String path) {

		String[] patharray= path.split("/");

			java.util.List<String> pathList= Arrays.asList(patharray);
			ArrayList<String> pathArrayList= new ArrayList<String>(pathList);

		return pathArrayList;

	}

	private void setPathArrayList(String path) {

		String[] patharray= path.split("/");

			java.util.List<String> pathList= Arrays.asList(patharray);
			thePathArrayList= new ArrayList<String>(pathList);

	}

	public void getInstanceEvent(String path) {

		noadd= false;

			System.out.println("FILES INSTANCE: "+path);

		somepath= path;  // <--
		  if (somepath.equals("")) { somepath= "/"; }
  		  if (somepath.substring(0,2).equals("//")) { somepath= somepath.substring(1,somepath.length()); }
		    setPathArrayList(somepath);  // <--

		firstprev= true; firstnext= true;
		thePrevArrayList.add(somepath);
			prevpath= thepath;
		//
		therows= getTableRows(somepath);
		  setTable(therows,theheaders);
		  pathField.setText(somepath);
		  	thepath= somepath;
		//
		thepaths= getPaths(somepath);
		  System.out.println(somepath);

	}

	public void getOpenEvent(String path) {

			System.out.println("OPEN FILE: "+path);

		theWindow= theEditor.getCurrentWindow();
		String filepath= path;
		String isempty= theWindow.contentArea.getText();
			theEditor.setPath(filepath);
			if (isempty.equals("")) {
				theEditor.setFileOpen(theWindow,filepath);
			} else {
				theEditor.getWindow(filepath);
			}
				  this.dispose();

	}

	private void getRootEvent() {

		somepath= "/";

		firstprev= true; firstnext= true;
		if (!noadd) {
			//
			thePrevArrayList.add(somepath);
				prevpath= thepath;
			//
			therows= getTableRows(somepath);
			  setTable(therows,theheaders);
			  pathField.setText(somepath);
			  	thepath= somepath;
			//
			thepaths= getPaths(somepath);
		}

	}


	// action events
	@Override
	public void actionPerformed(ActionEvent event) {

		Object source= event.getSource();
		String src= event.getSource().toString();
			File fileObj;
			boolean root= false;
			boolean stop= false;
			boolean prev= false;
			boolean next= false;
			boolean home= false;
			boolean menu= false;
			boolean delete= false;
			  if (somepath.equals("/")) { noadd= true; }

			// reset indexed path array
			thepaths= getPaths(thepath);
			// update info label
			this.setInfoLabel(thepaths.length,filesize,selectfile);  //  <--

		// CONFIRM BUTTONS
		if (source==this.cancel)
          	this.dispose();
		if (source==this.ok) {
			String filepath= getPath();
			  fileObj= new File(filepath);
			if (fileObj.isFile()) {
			for (String pattern : openpatterns) {
				if (filepath.matches(pattern))  getOpenEvent(filepath);
			}
			}
		}

		// TOOLBAR BUTTONS
		if (source==this.prevButton && !root) {  prev= true; noadd= false;
			nextpath= thepath;
			if (firstprev) {  firstprev= false;
				somepath= thepath= thePrevArrayList.get(thePrevArrayList.size()-2);
				thePrevArrayList.remove(thePrevArrayList.size()-1);
			} else if (thePrevArrayList.size() > 1) {
				somepath= thepath= thePrevArrayList.get(thePrevArrayList.size()-2);
				thePrevArrayList.remove(thePrevArrayList.size()-1);
			} else if (thePrevArrayList.size() > 0) {
				somepath= thePrevArrayList.get(thePrevArrayList.size()-1);
				noadd= true; stop= true;
			}
			if (!nextpath.equals(homepath))
			if (!noadd)  theNextArrayList.add(nextpath);
			if (theNextArrayList.size() == 0 && nextpath.equals(homepath))  noadd= true;
		}
    	if (source==this.nextButton && !root) {  next= true; noadd= false;
			if (firstnext) {  firstnext= false;
				somepath= nextpath= theNextArrayList.get(theNextArrayList.size()-1);
				theNextArrayList.remove(theNextArrayList.size()-1);
			} else if (theNextArrayList.size() > 1) {
				somepath= nextpath= theNextArrayList.get(theNextArrayList.size()-1);
				theNextArrayList.remove(theNextArrayList.size()-1);
			} else if (theNextArrayList.size() > 0) {
				somepath= nextpath= theNextArrayList.get(theNextArrayList.size()-1);
				theNextArrayList.remove(theNextArrayList.size()-1);
			} else {
				noadd= true; stop= true;
			}
		}
		if (source==this.upButton && !root) {  noadd= false;
			   setPathArrayList(somepath);
			thePathArrayList= getPathArrayList(thepath);
			if (thePathArrayList.size() > 2) {
				thePathArrayList.remove(thePathArrayList.size()-1);
					somepath= String.join("/",thePathArrayList);
					  setPathArrayList(somepath);
					if (somepath.equals("")) { root= true;
						somepath= "/"; this.pathField.setText("/");
						getRootEvent(); noadd= true;
					}
			} else { root= true;
				somepath= "/"; this.pathField.setText("/");
				  getRootEvent(); noadd= true;
			}
		}
		if (source==this.homeButton && !root) {  home= true;
			somepath= homepath;
			setPathArrayList(somepath);
		}
		// URI BUTTONS ////////////////////////////////////////////////////////////
		if (source==this.menuButton) {  menu= true;
			//

		}
		if (source==this.pathField) { noadd= false;
			somepath= getPath();
			if (somepath.equals("")) { root= true;
				somepath= "/"; this.pathField.setText("/");
				getRootEvent();
			} else if (somepath.matches(".*?\\.txt$")) {
				getOpenEvent(somepath);
			}
			this.pathField.setText(somepath);
			setPathArrayList(somepath);
		}
		if (source==this.deleteButton) {  delete= true;
			if (!root)  somepath= getPath();
			this.pathField.setText("");
		}

		// --------------------
		if (!(root || stop)) {
			if (!(prev || menu || delete)) {  firstprev= true;
				if (!noadd)  thePrevArrayList.add(somepath);
			}
			if (!(menu || delete)) {
				// -- here would need an equals test for the owner of somepath...
				if (!next)  firstnext= true;
				if (!prev)  prevpath= thepath;
				else if (!next)  prevpath= nextpath;
					therows= getTableRows(somepath);
					  this.setTable(therows,theheaders);
					  this.pathField.setText(somepath);

					// FOR DEBUGGING...
					/*this.content.setText(this.content.getText()+"\nthepath="+thepath+"\n"
											+"somepath="+somepath+"\n"
											+"prevpath="+prevpath+"\n"
											+"nextpath="+nextpath+"\n"
											+"thePrevArrayList="+thePrevArrayList.toString()+"\n"
											+"theNextArrayList="+theNextArrayList.toString()+"\n");*/

				//////////////////
				thepaths= getPaths(somepath);
				thepath= somepath;
			}
		}

	}

	// main
	public static void main(String[] args) {
			EFileOpen f= new EFileOpen();
	}

}
