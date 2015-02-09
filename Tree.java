
/*
Name: Jonathan Vasquez
Signed: JV, jvasquez@unomaha.edu

Description: This file is the binary tree which holds all the elements that are inserted in the tree as treeNode. It contains all the methods that control any changes made to the tree. 
*/

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.EOFException;
import java.io.Serializable;
import java.io.IOException; 

//extends comparable and serializable
public class Tree<T extends Comparable<T> & Serializable>
{
    //given private variables
    private TreeNode<T> root;
    private T[] values;
    private Integer index;

    public Tree()
    {	
        //Create an empty tree
        root = null;
    }
    //calls insert method
    public void insertItem(T item)
    {
        //non-recursive method
        if(isEmpty())
            root = new TreeNode<T>(item);
        else 
            insert(root, item);    	
    }
    //inserts recursively the item into the tree
    private void insert(TreeNode<T> tree, T item)
    {
        //recursively call this to find the position for the new item in the tree
        if (item.compareTo(tree.getData()) < 0)
        {
            if(tree.getLeft() == null)
                tree.setLeft(new TreeNode<T>(item));
            else
                insert(tree.getLeft(), item);
        }
        else if (item.compareTo(tree.getData()) > 0)
        {
            if(tree.getRight() == null)
                tree.setRight(new TreeNode<T>(item)); 
            else
                insert(tree.getRight(), item);
        }
        else
        {
            //do nothing
            return;
        }
    }
    //calls deleteNode()
    public void deleteItem(T item)
    {
        //non-recursive to delete an item
        root = deleteNode(root, item);
    }
    //deletes the node passed 
    private TreeNode<T> deleteNode(TreeNode<T> tree, T item)
    {
        //use recursion to search for the item to remove
        if(tree != null)
        {
            if(item.compareTo(tree.getData()) < 0)
            {
                tree.setLeft(deleteNode(tree.getLeft(), item));
            }	
            else if(item.compareTo(tree.getData()) > 0)
            {
                tree.setRight(deleteNode(tree.getRight(), item));
            }	
            else if(item.compareTo(tree.getData()) == 0)
            {
                //we wil check for tree possible cases since if we are in the current
                //	node to be eliminated we need to replace the current node position
                //	so we do not lose the descendant nodes.
                if(tree.getLeft() == null)
                {
                    tree = (tree.getRight()); // = tree.getRight();
                }
                else if (tree.getRight() == null)
                {	
                    tree = (tree.getLeft()); // = tree.getLeft();
                }
                else
                {
                    TreeNode<T> tempNode = tree.getLeft();
                    while(tempNode.getRight() != null)
                        tempNode = tempNode.getRight();

                    tree.setData(tempNode.getData());
                    tree.setLeft(deleteNode(tree.getLeft(), tempNode.getData()));
                }
            }
        }
        //returns the tree
        return tree;
    }
    //calls method preOrderHelper
    public void preOrderTraversal()
    {
        if(root != null)
            preOrderHelper(root);
        else
            System.out.print("\nTree is empty");
        System.out.println();
    }
    //this reorders the elements in the binary tree in preOrder'
    private void preOrderHelper(TreeNode<T> tree)
    {
        //helpful to store data from a binary search tree
        //it can reconstruct the hold binary tree
        if(tree == null)
            return;

        System.out.printf("%s ", tree.getData());
        preOrderHelper(tree.getLeft());
        preOrderHelper(tree.getRight());
    }
    //calls inOrderHelper method
    public void inOrderTraversal()
    {	
        if(root != null)
            inOrderHelper(root);
        else
            System.out.print("\nTree is empty");
        System.out.println();
    }
    //prints the elements of the tree in order
    private void inOrderHelper(TreeNode<T> tree)
    {
        //if tree is null - do nothing
        if(tree == null)
            return;

        //else - print current node and check recursively the left and right of the current
        inOrderHelper(tree.getLeft());
        System.out.printf("%s ", tree.getData());
        inOrderHelper(tree.getRight());
    }
    //calls method postOrderHelper
    public void postOrderTraversal()
    {
        if(root != null)
            postOrderHelper(root);
        else
            System.out.print("\nTree is empty");
        System.out.println();
    }
    //reorganize the tree elements in postOrder'
    private void postOrderHelper(TreeNode<T> tree)
    {
        //if tree is null - does nothing
        if(tree == null)
            return;

        //else - prints in postOrder
        postOrderHelper(tree.getLeft());
        postOrderHelper(tree.getRight());
        System.out.printf("%s ", tree.getData());
    }
    //begins the tree formatting traverse
    public void treeFormatTraversal()
    {
        if(root!=null)
        {
            System.out.println("\nThe Tree Contains:");
            treeFormatHelper(root, 0);
        }
        else
            System.out.print("\nTree is empty\n");
    }
    //uses recursion to print the binary tree-from left to right
    private void treeFormatHelper(TreeNode<T> tree, int indentSpaces)
    {
        String indent = "";
        if(tree == null)
            return;

        //else-
        treeFormatHelper(tree.getRight(), indentSpaces+5);   
        for(int i=0; i < indentSpaces; i++)
            indent += " ";
        System.out.println(indent + tree.getData());
        treeFormatHelper(tree.getLeft(), indentSpaces+5);
    }
    //calls recursive method countNodes to measure the length of the tree
    public int lengthIs()
    {
        //calls recursive method countNodes to count nodes in tree
        return countNodes(root);	
    }
    //return the number of elements stored in the tree
    private int countNodes(TreeNode<T> tree)
    {
        //this method will check if the current node is null
        //if is not, it will recursively check the right and left of the passed tree
        if(tree == null)
            return 0;
        else
            return countNodes(tree.getLeft()) + countNodes(tree.getRight()) + 1;
    }
    // iteratively looks for an item in the array
    public boolean findItem(T item)
    {
        TreeNode<T> tree = root;
        boolean found = false;
        while(tree != null && !found)
        {
            if(item.compareTo(tree.getData())<0)
                tree = tree.getLeft();
            else if(item.compareTo(tree.getData())>0)
                tree = tree.getRight();
            else
                found = true;
        }
        return found;
    }
    //method that compares the nodes to the item searched for recursively
    //not used in this tree - just wanted to try it out
    private boolean retrieve(TreeNode<T> tree, T item)
    {
        boolean found = false;
        if(tree == null)
            found = false;
        else if(item.compareTo(tree.getData())<0)
            found = retrieve(tree.getLeft(), item);
        else if(item.compareTo(tree.getData())>0)
            found = retrieve(tree.getRight(), item);
        else
            found = true;
        return found;
    }
    //checks if the tree is empty or not by checking if the root node 
    //is null or not
    public boolean isEmpty()
    {
        if(root == null)
            return true;
        else
            return false;
    }
    //restarts the binary tree by eliminating the root
    public void clear()
    {
        root = null;
    }
    //this method rearranges the tree by balancing the tree
    // so we have a tree more evenly distributed
    public void balance()
    {
        if(isEmpty())
            System.out.println("Tree is Empty");
        else
        {
            values = (T[]) new Comparable[lengthIs()];
            index = 0;
            createAscendingArray(root);
            clear();
            balanceRecursive(0, values.length-1);
            //sets the values to null
            for(int i = 0; i <lengthIs()-1; i++)
            {
                values[i] = null;
            }
        }
    }
    //this method organizes the values of the tree in ascending order
    private void createAscendingArray(TreeNode<T> tree) 
    {
        if(tree == null)
        {
            return;
        }
        else
        {
            createAscendingArray(tree.getLeft());
            values[index++] = tree.getData();
            createAscendingArray(tree.getRight());
        }
    }
    //recursive method of balancing the tree by entering the values starting
    //  from the midpoint in order to have an array better balanced
    //      if possible
    private void balanceRecursive(Integer low, Integer high)
    {
        //low>=
        if(low>high)
            return;
        else
        {
            int midpoint = (low+high)/2;

            insertItem(values[midpoint]);
            balanceRecursive(low, midpoint-1);
            balanceRecursive(midpoint+1, high); 
        }
        //insertItem(values[high]);
    }
    //this method saves the current tree to a serialized file
    public boolean saveToSerializedFile()
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the filename to create: ");
        String filename = input.next();
        try
        {
            ObjectOutputStream output = 
                new ObjectOutputStream(new FileOutputStream(filename));
            saveTree(output, root);
            output.close();

            if(output == null)
               System.out.println("\noutput file did not open\n");
            else if(output!=null)
               output.close();
        }
        catch(FileNotFoundException fnf)
        {
            System.out.println("File not found");
            return false;
        }
        catch(SecurityException se)
        {
            System.out.println("No write access to this file");
            return false;
        }
        catch(Exception e)
        {
            System.out.println("Error opening file");
            return false;
        }
        return true;
    }
    //saves the tree nodes recursively
    private boolean saveTree(ObjectOutputStream output, TreeNode<T> tree)
    {
        try
        {
            if(tree == null)
            {
                return false;
            }
            else
            {
                output.writeObject(tree.getData());
                saveTree(output, tree.getLeft());
                saveTree(output, tree.getRight());
            }
        }
        catch(IOException ioE)
        {
            System.err.println("Error writing to file.");
        }
        return true;
    }
    //creates a tree from the serialized files in the directory
    public boolean buildFromSerializedFile()
    {
        Scanner input = new Scanner(System.in);
        clear();
        System.out.print("Enter the name of the input file: ");
        String filename = input.next();
        ObjectInputStream inputFile = null;
        try
        {
            inputFile = 
                new ObjectInputStream(new FileInputStream(filename));
            while(true)
            {
                T obj = (T) inputFile.readObject();
                insertItem(obj);
            }
        }
        catch(FileNotFoundException fnf)
        {
            System.out.printf("Invalid filename entered - file does not exist: %s\n", filename);
            System.out.println("\nUnable to build tree from file");
            return false;
        }
        catch(NoSuchElementException nsE)
        {
            System.out.println("No such element. - line 350");
            return false;
        }
        catch(IllegalStateException isE)
        {
            System.out.println("Illegal state exception. line 355");
            return false;
        }
        catch(EOFException eofE)
        {
            try
            {
                inputFile.close();
            }
            catch(IOException e)
            {
                System.out.println("Error closing file.");
            }
        }
        catch(Exception e)
        {
            System.out.println("Error creating the file. - line 364");
            return false;
        }
        return true;
    }
}
