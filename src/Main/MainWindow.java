package Main;

import Commands.*;
import Commands.ShellCommand.Compile;
import Commands.ShellCommand.Run;
import Editor.DefaultTextArea;
import FileExplorer.DefaultFileExplorer;
import FileExplorer.FileExplorer;
import MenuBar.*;
import Splash.SplashPanel;
import TabBar.DefaultTabBar;
import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;
import ToolBar.DefaultTool;
import ToolBar.DefaultToolBar;
import ToolBar.IToolBar;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Hanif Sudira on 10/30/2016.
 */
public class MainWindow extends JFrame {
    private IMenuBar iMenuBar;
    private IMenu iMenu;
    private IToolBar iToolBar;
    private IMenuItem iMenuItem;
    private JSplitPane splitTextReplace;
    private JSplitPane splitFindReplace;
    private JSplitPane splitPane;
    private JScrollPane leftPanel;
    private JScrollPane rightPanel;
    private JSplitPane mainSplit;
    private JTabbedPane tabPane;
    private JSplitPane tabArea;
    private JWindow splashScreen;
    private SplashPanel splashPanel;
    private JPanel textPanel;
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static KeyStroke ctrlN = KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlS = KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlaltS = KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK);
    private static KeyStroke ctrlO = KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlX = KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlW = KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlC = KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlV = KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlF = KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlR = KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlY = KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlA = KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlZ = KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK);
    private static KeyStroke altF4 = KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK);
    private static KeyStroke F9 = KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0);
    private static KeyStroke F10 = KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0);

    public MainWindow() throws IOException {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        InitUI();


    }

    private void InitUI() throws IOException {
        JFrame frame = new JFrame("MABA IDE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setIconImage(ImageIO.read(new File("assets/logo.png")));
        /* ===========================================================================================================
            SPLASH SCREEN
           ===========================================================================================================
         */
        splashPanel = new SplashPanel();
        splashScreen = new JWindow();
        splashScreen.getContentPane().add(splashPanel);
        splashScreen.pack();
        Dimension size = splashScreen.getSize();
        splashScreen.setLocation(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                splashScreen.setVisible(true);
            }
        });
        /* ===========================================================================================================
            FORM FIND
           ===========================================================================================================
         */
        JTextField textField = new JTextField(20);
        JButton textFind = new JButton("Find");
        JLabel labelClose= new JLabel("X");
        labelClose.setToolTipText("close find panel");
        JPanel findPanel = new JPanel();
        findPanel.add(textField);
        findPanel.add(textFind);
        findPanel.add(labelClose);
        labelClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                findPanel.setVisible(false);
                splitFindReplace.setVisible(false);
                frame.revalidate();
            }
        });
        /* ===========================================================================================================
             FORM REPLACE
           ===========================================================================================================
         */
        JTextField textFieldFind = new JTextField(20);
        JButton btnFind = new JButton("Find");
        JTextField textFieldReplace = new JTextField(20);
        JButton btnReplace = new JButton("Replace");
        JPanel repalcePanel = new JPanel();
        repalcePanel.add(textFieldFind);
        repalcePanel.add(btnFind);
        repalcePanel.add(textFieldReplace);
        repalcePanel.add(btnReplace);
        JLabel labelCloseReplace = new JLabel("X");
        repalcePanel.add(labelCloseReplace);
        labelCloseReplace.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                repalcePanel.setVisible(false);
                splitFindReplace.setVisible(false);
                frame.revalidate();
            }
        });

        /*
            ==========================================================================================================
                TEXT AREA
            ==========================================================================================================
         */
        DefaultTextArea defaultTextArea = new DefaultTextArea();
        JScrollPane scroolTextArea = new JScrollPane(defaultTextArea);
        scroolTextArea.setMinimumSize(new Dimension(300,200));
        DefaultTabSubject.getInstance().attachObserver(defaultTextArea);
        DefaultTabSubject.getInstance().attachObserver(new DefaultTabEditor("untitled.c"));
        defaultTextArea.setDefaultTabEditor(DefaultTabSubject.getInstance().getActiveTab());
        DefaultTabSubject.getInstance().getActiveTab().pushCommandUndoStack("");

        DefaultTabBar.getInstance().addTab(DefaultTabSubject.getInstance().getActiveTab());

        /* ===========================================================================================================
             FOLDER EXPLORER
           ===========================================================================================================
         */

        DefaultFileExplorer defaultFileExplorer = new DefaultFileExplorer(".");
        leftPanel = new JScrollPane(new FileExplorer("."));
        leftPanel.setMinimumSize(new Dimension(200,100));
        pack();
        setLocationRelativeTo(null);

        /*
           ===========================================================================================================
            PRINT OUT/CONSOLE
           ===========================================================================================================
        */
        JTextPane terminalText = new JTextPane();
        terminalText.setEnabled(false);
        JScrollPane terminalPanel = new JScrollPane(terminalText);

        /*
           ==========================================================================================================
             SPLITING AREA
           ==========================================================================================================
        */
        splitFindReplace = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,findPanel, repalcePanel);
        tabArea = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,DefaultTabBar.getInstance().getTabbedPane(),scroolTextArea);
        splitTextReplace = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, splitFindReplace,tabArea );
        splitFindReplace.getTopComponent().setVisible(false);
        splitFindReplace.getBottomComponent().setVisible(false);
        //splitFindReplace.setVisible(false);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, leftPanel,splitTextReplace);
        mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,splitPane,terminalPanel);

        /* ==========================================================================================================
              MENUBAR
           ==========================================================================================================
        */
        this.iMenuBar = new DefaultMenuBar();
        frame.setJMenuBar((JMenuBar) this.iMenuBar);

        /* Menu File */
        DefaultMenu fileMenu= new DefaultMenu("File");
        this.iMenuBar.AddMenu(fileMenu);

        DefaultMenuItem newMenuItem= new DefaultMenuItem("New File");
        NewFile newFile = new NewFile(defaultTextArea,defaultFileExplorer,this);
        newMenuItem.SetCommand(newFile);
        newMenuItem.SetAcceleration(ctrlN);
        newMenuItem.SetIcon(new ImageIcon("assets/new.png"));
        fileMenu.AddMenuItem(newMenuItem);

        DefaultMenuItem openFileMenuItem= new DefaultMenuItem("Open File");
        OpenFile openFile = new OpenFile(defaultTextArea,defaultFileExplorer);
        openFileMenuItem.SetCommand(openFile);
        openFileMenuItem.SetAcceleration(ctrlO);
        openFileMenuItem.SetIcon(new ImageIcon("assets/open-file.png"));
        fileMenu.AddMenuItem(openFileMenuItem);

        DefaultMenuItem openFolderMenuItem= new DefaultMenuItem("Open Folder");
        OpenFolder openFolder = new OpenFolder(defaultFileExplorer);
        openFolderMenuItem.SetCommand(openFolder);
        openFolderMenuItem.SetIcon(new ImageIcon("assets/open.png"));
        fileMenu.AddMenuItem(openFolderMenuItem);

        fileMenu.AddSeparator();

        DefaultMenuItem saveMenuItem= new DefaultMenuItem("Save");
        Save save = new Save(defaultTextArea,defaultFileExplorer);
        saveMenuItem.SetCommand(save);
        saveMenuItem.SetAcceleration(ctrlS);
        saveMenuItem.SetIcon(new ImageIcon("assets/save.png"));
        fileMenu.AddMenuItem(saveMenuItem);

        DefaultMenuItem saveAsMenuItem= new DefaultMenuItem("Save As");
        SaveAs saveAs = new SaveAs(defaultTextArea,defaultFileExplorer);
        saveAsMenuItem.SetCommand(saveAs);
        saveAsMenuItem.SetAcceleration(ctrlaltS);
        saveAsMenuItem.SetIcon(new ImageIcon("assets/save-as.png"));
        fileMenu.AddMenuItem(saveAsMenuItem);

        fileMenu.AddSeparator();

        DefaultMenuItem closeFileMenuItem= new DefaultMenuItem("Close File");
        CloseFile closeFile = new CloseFile(defaultTextArea,defaultFileExplorer);
        closeFileMenuItem.SetCommand(closeFile);
        closeFileMenuItem.SetAcceleration(ctrlW);
        closeFileMenuItem.SetIcon(new ImageIcon("assets/close-file.png"));
        fileMenu.AddMenuItem(closeFileMenuItem);

        DefaultMenuItem closeAllMenuFile= new DefaultMenuItem("Close All Files");
        CloseAllFile closeAllFile = new CloseAllFile(defaultTextArea,defaultFileExplorer);
        closeFileMenuItem.SetCommand(closeAllFile);
        closeAllMenuFile.SetIcon(new ImageIcon("assets/close-all.png"));
        fileMenu.AddMenuItem(closeAllMenuFile);


        fileMenu.AddSeparator();

        DefaultMenuItem exitMenuFile= new DefaultMenuItem("Exit");
        exitMenuFile.SetIcon(new ImageIcon("assets/close.png"));
        Exit exit = new Exit();
        exitMenuFile.SetCommand(exit);
        exitMenuFile.SetAcceleration(altF4);
        fileMenu.AddMenuItem(exitMenuFile);

        /* Menu Edit */
        DefaultMenu editMenu= new DefaultMenu("Edit");
        this.iMenuBar.AddMenu(editMenu);

        DefaultMenuItem undoMenuItem= new DefaultMenuItem("Undo");
        Undo undo = new Undo(defaultTextArea);
        undoMenuItem.SetCommand(undo);
        undoMenuItem.SetAcceleration(ctrlZ);
        undoMenuItem.SetIcon(new ImageIcon("assets/undo.png"));
        editMenu.AddMenuItem(undoMenuItem);


        DefaultMenuItem redoMenuItem= new DefaultMenuItem("Redo");
        Redo redo = new Redo(defaultTextArea);
        redoMenuItem.SetCommand(redo);
        newMenuItem.SetAcceleration(ctrlY);
        redoMenuItem.SetIcon(new ImageIcon("assets/redo.png"));
        editMenu.AddMenuItem(redoMenuItem);

        editMenu.AddSeparator();

        DefaultMenuItem cutMenuItem= new DefaultMenuItem("Cut");
        Cut cut = new Cut(defaultTextArea);
        cutMenuItem.SetCommand(cut);
        cutMenuItem.SetAcceleration(ctrlX);
        cutMenuItem.SetIcon(new ImageIcon("assets/cut.png"));
        editMenu.AddMenuItem(cutMenuItem);

        DefaultMenuItem copyMenuItem= new DefaultMenuItem("Copy");
        Copy copy = new Copy(defaultTextArea);
        copyMenuItem.SetCommand(copy);
        copyMenuItem.SetAcceleration(ctrlC);
        copyMenuItem.SetIcon(new ImageIcon("assets/copy.png"));
        editMenu.AddMenuItem(copyMenuItem);

        DefaultMenuItem pasteMenuItem= new DefaultMenuItem("Paste");
        Paste paste = new Paste(defaultTextArea);
        pasteMenuItem.SetCommand(paste);
        pasteMenuItem.SetAcceleration(ctrlV);
        pasteMenuItem.SetIcon(new ImageIcon("assets/paste.png"));
        editMenu.AddMenuItem(pasteMenuItem);

        DefaultMenuItem selectAllMenuItem= new DefaultMenuItem("Select All");
        SelectAll selectAll = new SelectAll(defaultTextArea);
        selectAllMenuItem.SetCommand(selectAll);
        selectAllMenuItem.SetAcceleration(ctrlA);
        selectAllMenuItem.SetIcon(new ImageIcon("assets/select-all.png"));
        editMenu.AddMenuItem(selectAllMenuItem);

        /* Menu Search */
        DefaultMenu searchMenu= new DefaultMenu("Search");
        this.iMenuBar.AddMenu(searchMenu);

        DefaultMenuItem findMenuItem = new DefaultMenuItem("Find");
        Find find = new Find(defaultTextArea,findPanel,splitFindReplace);
        findMenuItem.SetCommand(find);
        findMenuItem.SetAcceleration(ctrlF);
        findMenuItem.SetIcon(new ImageIcon("assets/find.png"));
        searchMenu.AddMenuItem(findMenuItem);

        DefaultMenuItem replaceMenuItem = new DefaultMenuItem("Replace");
        Replace replace = new Replace(defaultTextArea,repalcePanel,splitFindReplace);
        replaceMenuItem.SetCommand(replace);
        replaceMenuItem.SetIcon(new ImageIcon("assets/replace.png"));
        replaceMenuItem.SetAcceleration(ctrlR);
        searchMenu.AddMenuItem(replaceMenuItem);


        /* Menu Compile */
        DefaultMenu compileMenu= new DefaultMenu("Compile");
        this.iMenuBar.AddMenu(compileMenu);

        Run run = new Run(defaultFileExplorer, terminalText);
        DefaultMenuItem runMenuItem= new DefaultMenuItem("Run");
        runMenuItem.SetIcon(new ImageIcon("assets/run.png"));
        runMenuItem.SetAcceleration(F10);
        runMenuItem.SetCommand(run);
        compileMenu.AddMenuItem(runMenuItem);

        Compile compile = new Compile(defaultFileExplorer, terminalText);
        DefaultMenuItem compileMenuItem= new DefaultMenuItem("Compile");
        compileMenuItem.SetIcon(new ImageIcon("assets/compile.png"));
        compileMenuItem.SetAcceleration(F9);
        compileMenuItem.SetCommand(compile);
        compileMenu.AddMenuItem(compileMenuItem);

        /* Menu Snippet */
        DefaultMenu snippetMenu = new DefaultMenu("Snippet");
        this.iMenuBar.AddMenu(snippetMenu);

        DefaultMenuItem bubbleSortMenuItem= new DefaultMenuItem("Bubble Sort");
        snippetMenu.AddMenuItem(bubbleSortMenuItem);
        Snippet snippetBubleSort = new Snippet(defaultTextArea,"buble-sort");
        bubbleSortMenuItem.SetCommand(snippetBubleSort);

        DefaultMenuItem selectionSortMenuItem = new DefaultMenuItem("Selection Sort");
        snippetMenu.AddMenuItem(selectionSortMenuItem);
        Snippet snippetSelectionSort = new Snippet(defaultTextArea,"selection-sort");
        selectAllMenuItem.SetCommand(snippetSelectionSort);

        DefaultMenuItem insertionSortMenuItem = new DefaultMenuItem("Insertion Sort");
        snippetMenu.AddMenuItem(insertionSortMenuItem);
        Snippet snippetInsertionSort = new Snippet(defaultTextArea, "insertion-sort");
        insertionSortMenuItem.SetCommand(snippetInsertionSort);

        /* Menu Themes */
        DefaultMenu themesMenu = new DefaultMenu("Themes");
        this.iMenuBar.AddMenu(themesMenu);

        /*
           ===========================================================================================================
               TOOLBAR
           ===========================================================================================================
         */
        this.iToolBar = new DefaultToolBar();
        DefaultTool newFileTool = new DefaultTool("assets/new.png","New file");
        newFileTool.SetCommand(newFile);
        this.iToolBar.AddToolItem(newFileTool);
        DefaultTool openFileTool = new DefaultTool("assets/open-file.png","Open file");
        openFileTool.SetCommand(openFile);
        this.iToolBar.AddToolItem(openFileTool);
        DefaultTool openFolderTool = new DefaultTool("assets/open.png","Open folder");
        openFolderTool.SetCommand(openFolder);
        this.iToolBar.AddToolItem(openFolderTool);
        DefaultTool saveTool = new DefaultTool("assets/save.png","Save file");
        saveTool.SetCommand(save);
        this.iToolBar.AddToolItem(saveTool);
        DefaultTool saveAsTool = new DefaultTool("assets/save-as.png","Save As file");
        saveAsTool.SetCommand(saveAs);
        this.iToolBar.AddToolItem(saveAsTool);
        DefaultTool closeFileTool = new DefaultTool("assets/close-file.png","Close file");
        closeFileTool.SetCommand(closeFile);
        this.iToolBar.AddToolItem(closeFileTool);
        DefaultTool closeAllFileTool = new DefaultTool("assets/close-all.png","Close All file");
        closeAllFileTool.SetCommand(closeAllFile);
        this.iToolBar.AddToolItem(closeAllFileTool);

        this.iToolBar.AddSeparator();
        final DefaultTool undoTool = new DefaultTool("assets/undo.png","Undo");
        this.iToolBar.AddToolItem(undoTool);
        undoTool.setEnabled(false);
        undoTool.SetCommand(undo);

        final DefaultTool redoTool = new DefaultTool("assets/redo.png","Redo");
        this.iToolBar.AddToolItem(redoTool);
        redoTool.setEnabled(false);
        redoTool.SetCommand(redo);
        DefaultTool cutTool = new DefaultTool("assets/cut.png","Cut text");
        cutTool.SetCommand(cut);
        this.iToolBar.AddToolItem(cutTool);
        DefaultTool copyTool = new DefaultTool("assets/copy.png","Copy text");
        copyTool.SetCommand(copy);
        this.iToolBar.AddToolItem(copyTool);
        DefaultTool pasteTool = new DefaultTool("assets/paste.png","Paste text");
        pasteTool.SetCommand(paste);
        this.iToolBar.AddToolItem(pasteTool);
        DefaultTool selectAllTool = new DefaultTool("assets/select-all.png","Select all text");
        selectAllTool.SetCommand(selectAll);
        this.iToolBar.AddToolItem(selectAllTool);

        this.iToolBar.AddSeparator();
        DefaultTool findTool = new DefaultTool("assets/find.png","Find text");
        findTool.SetCommand(find);
        this.iToolBar.AddToolItem(findTool);

        DefaultTool replaceTool = new DefaultTool("assets/replace.png","Replace text");
        replaceTool.SetCommand(replace);
        this.iToolBar.AddToolItem(replaceTool);

        this.iToolBar.AddSeparator();
        DefaultTool runTool = new DefaultTool("assets/run.png","Run");
        runTool.SetCommand(run);
        this.iToolBar.AddToolItem(runTool);
        DefaultTool compileTool = new DefaultTool("assets/compile.png","Compile");
        compileTool.SetCommand(compile);
        this.iToolBar.AddToolItem(compileTool);
        Container container = frame.getContentPane();
        container.add((Component) this.iToolBar, BorderLayout.NORTH);


        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
        contentPanel.add(mainSplit, BorderLayout.CENTER);

        final JLabel statusBar = new JLabel("Status:");
        statusBar.setText("Status: Line 1 Column 1");


        contentPanel.add(statusBar, BorderLayout.SOUTH);
        defaultTextArea.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                int linenum = 1;
                int columnnum = 1;
                DefaultTextArea editArea = (DefaultTextArea)e.getSource();
                if(DefaultTabSubject.getInstance().getActiveTab() == null || DefaultTabSubject.getInstance().getActiveTab().getCommandUndoStackSize() <= 1) undoTool.setEnabled(false);
                else undoTool.setEnabled(true);
                if(DefaultTabSubject.getInstance().getActiveTab() == null || DefaultTabSubject.getInstance().getActiveTab().getCommandRedoStackSize() == 0 ) redoTool.setEnabled(false);
                else redoTool.setEnabled(true);
                try {
                    int caretpos = editArea.getCaretPosition();
                    linenum = editArea.getLineOfOffset(caretpos);
                    columnnum = caretpos - editArea.getLineStartOffset(linenum);
                    linenum += 1;
                }
                catch(Exception ex) { }
                if(columnnum==0) columnnum=1;
                statusBar.setText("Status: Line "+linenum+" Column "+columnnum);
            }
        });

        setContentPane(contentPanel);
        frame.add(contentPanel);
        splashScreen.setVisible(false);
        frame.setVisible(true);
        splitFindReplace.setVisible(false);

    }
    private static class CloseableTabComponent extends JPanel {

        private static int tabNo = 0;
        private JLabel titleLabel = null;
        private JButton closeButton = null;
        private JTabbedPane tabPane = null;

        public CloseableTabComponent(JTabbedPane aTabbedPane) {
            super(new BorderLayout());
            tabPane = aTabbedPane;

            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));

            titleLabel = new JLabel("Tab " + (++tabNo) + " ");
            titleLabel.setOpaque(false);

            closeButton = new JButton("X");
            closeButton.setFont(new Font("Dialog", Font.BOLD, 14));
            closeButton.setForeground(Color.red);
            closeButton.setBorderPainted(false);

            closeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < tabPane.getTabCount(); i++) {
                        if (MainWindow.CloseableTabComponent.this.equals(tabPane.getTabComponentAt(i))) {
                            tabPane.removeTabAt(i);
                            break;
                        }
                    }
                    if ((tabPane.getTabCount() > 1) && (tabPane.getSelectedIndex() == tabPane.getTabCount() - 1)) {
                        tabPane.setSelectedIndex(tabPane.getTabCount() - 2);
                    }
                }
            });

            add(titleLabel, BorderLayout.CENTER);
            add(closeButton, BorderLayout.EAST);
        }
    }

    public static void main(String args[]) throws IOException {
        MainWindow mainWindow = new MainWindow();
    }
}
