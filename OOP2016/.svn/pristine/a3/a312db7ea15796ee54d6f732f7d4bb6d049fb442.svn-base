/**
 * Created on Nov 14, 2012
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.ces.dom2tree;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This tree model describes the tree structure of an XML document.
 */
class DOMTreeModel implements TreeModel {
	/**
	 * Constructs a document tree model.
	 * @param doc the document
	 */
	private Document doc;

	public DOMTreeModel(Document doc)
	{
		this.doc = doc;
	}

	public Object getRoot()
	{
		return doc.getDocumentElement();
	}

	public int getChildCount(Object parent)
	{
		Node node = (Node) parent;
		NodeList list = node.getChildNodes();
		return list.getLength();
	}

	public Object getChild(Object parent, int index)
	{
		Node node = (Node) parent;
		NodeList list = node.getChildNodes();
		return list.item(index);
	}

	public int getIndexOfChild(Object parent, Object child)
	{
		Node node = (Node) parent;
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++)
			if (getChild(node, i) == child) return i;
		return -1;
	}

	public boolean isLeaf(Object node)
	{
		return getChildCount(node) == 0;
	}

	public void valueForPathChanged(TreePath path, Object newValue)
	{
	}

	public void addTreeModelListener(TreeModelListener l)
	{
	}

	public void removeTreeModelListener(TreeModelListener l)
	{
	}

}
