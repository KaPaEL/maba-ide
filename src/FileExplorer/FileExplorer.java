package FileExplorer;

import TabBar.DefaultTabBar;
import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Vector;

public class FileExplorer extends JScrollPane{
	JTree tree = new JTree();
	File dir = new File("");

	/** Construct a FileTree */
	public FileExplorer(String folderPath) {
		reloadTree(folderPath);
	}

	/** Add nodes from under "dir" into curTop. Highly recursive. */
	DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
//		System.out.print(dir);
		String curPath = dir.getPath();
//		System.out.print(curPath);
		DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
		if (curTop != null) { // should only be null at root
			curTop.add(curDir);
		}
		Vector ol = new Vector();
		String[] tmp = dir.list();
//		System.out.println(tmp.toString());
		for (int i = 0; i < tmp.length; i++){
			ol.addElement(tmp[i]);
		}
		Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
		File f;
		Vector files = new Vector();
		// Make two passes, one for Dirs and one for Files. This is #1.
		for (int i = 0; i < ol.size(); i++) {
			String thisObject = (String) ol.elementAt(i);
//			System.out.println(thisObject);
			String newPath;
			if (curPath.equals("."))
				newPath = thisObject;
			else
				newPath = curPath + File.separator + thisObject;
			if ((f = new File(newPath)).isDirectory())
				addNodes(curDir, f);
			else {
				files.addElement(thisObject);
			}
		}
		// Pass two: for files.
		for (int fnum = 0; fnum < files.size(); fnum++){
			curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
		}
		return curDir;
	}


	public void reloadTree(String folderPath){
		dir = new File(folderPath);
		tree = new JTree(addNodes(null, dir));
		this.getViewport().add(tree);


		// Add a listener
		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					doMouseClicked(e);
				}
			}
		});
	}

	static String readFile(String path, Charset encoding)
			throws IOException
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	void doMouseClicked(MouseEvent me) {
		TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
		if (tp != null){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tp.getLastPathComponent();
			if(node.isLeaf()){
				String text = "";
				try {
					text = readFile(dir.getAbsolutePath().replace(".","")+"\\"+node.toString(), StandardCharsets.UTF_8);
				} catch (IOException e) {
					e.printStackTrace();
				}

				DefaultTabEditor tabEditor = new DefaultTabEditor(node.toString());
				tabEditor.setTextContent(text);
				tabEditor.setFilePath(dir.getAbsolutePath().replace(".",""));
				DefaultTabSubject.getInstance().attachObserver(tabEditor);
				DefaultTabSubject.getInstance().setActiveTab(tabEditor);
				DefaultTabSubject.getInstance().update();
				DefaultTabBar.getInstance().addTab(tabEditor);
			}
//			System.out.println("You selected " + node);
//			System.out.print(dir.getAbsolutePath().replace(".","")+"\\"+node.toString());
		}
	}

}
