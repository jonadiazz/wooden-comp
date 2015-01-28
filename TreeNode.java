
/*
Name: Jonathan Vasquez
Class : 1620-003 
Program # : 06
Due Date : May 01 / 2014 

Honor Pledge: On my honor as a student of the University 
of Nebraska at Omaha, I have neither given nor received 
unauthorized help on this homework assignment. 
    Signed: Jhonatan Vasquez, 88139896, jvasquez@unomaha.edu 

Collaborators : NONE

Description: This file creates and implements the methods to access the nodes variables of the binary tree. 
 */

import java.io.Serializable;

//file extends comparable and serializable
public class TreeNode <T extends Comparable <T> & Serializable>
{
	private T data;  //	data item stored in the node
	private TreeNode<T> leftNode;	//	reference to left sub-tree
	private TreeNode<T> rightNode;	//	reference to right sub-tree
	
    //constructor
	public TreeNode(T item)
	{
		data = item;
		rightNode = leftNode = null;
	}
    //returns the data in the TreeNode
	public T getData()
	{
		return data;
	}
    //assigns item to data
	public void setData(T item)
	{
		data = item;
	}
    //returns the node to the left of the current node
	public TreeNode<T> getLeft()
	{
		return leftNode;
	}
    //sets a new node to the left of current node
	public void setLeft(TreeNode<T> tree)
	{
		leftNode = tree;
	}
    //returns the right node of the current
	public TreeNode<T> getRight()
	{
		return rightNode;
	}
    //sets a right node to the current
	public void setRight(TreeNode<T> tree)
	{
		rightNode = tree;
	}
}
