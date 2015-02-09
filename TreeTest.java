
/*
Name: Jonathan Vasquez
Signed: JV, jvasquez@unomaha.edu

Description: This file creates an object of type Menu which control the options we have from the Tree file. While we do not want to quit, we keep asking the user for a new order to process. Also, includes the getRaw values method which gets a file of type txt in our tree. In case that we were given a database with raw numbers or elements.
*/

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.NoSuchElementException;
import java.lang.IllegalStateException;
import java.util.*;
import java.io.EOFException;

public class TreeTest
{
    public static Scanner input;

    //this method is in charge of getting the values from a txt file to 
    //input in our binary tree
    public static boolean getRawDataValues(Tree tree)
    {

        //        boolean validFile = true;
        boolean readStatus = true;
        Scanner inputElements = null;
        input = new Scanner(System.in);
        String filename = "";

        //need a try catch in case the user inputted a nonexisting or wrong file
        try
        {
            System.out.print("Enter the name of the input file: ");
            filename = input.next();
            inputElements = new Scanner(new File(filename));
        }
        catch(FileNotFoundException fileNotFoundExc)
        {
            System.err.printf("Invalid filename entered - file does not exist: %s\n", filename);
            return false;
        }
        catch(Exception e)
        {
            System.out.println("Error opening file.");
            return false;
        }

        //try to input the elements of the file passed
        //if there is any exception we will catch it
        try
        {
            tree.clear();
            while(inputElements.hasNext())
            {
                tree.insertItem(inputElements.nextInt());
            }            
            if(inputElements != null)
                inputElements.close();
        }
        catch(NoSuchElementException nseE)
        {
            System.err.println("File does not contain integer values.");
            inputElements.close();
            readStatus = false;
        }
        catch(IllegalStateException isE)
        {
            System.err.println("Error reading from file. - Illegal");
            readStatus = false;
        }
        catch(Exception e)
        {
            System.err.println("Error reading file. - E");
            readStatus = false;
        }
        //returns true if the file elements were succesfully inserted
        //returns false if any exception ocurred
        return readStatus;
    }

    //menu options and method calls of the binary tree file
    public static void main (String [] args)
    {
        Tree tree = new Tree();
        input = new Scanner(System.in);

        String opts[] = {"Quit",
            "Insert Item",
            "Load Data Values from Text File",
            "Delete Item",
            "Find Item",
            "Get Length of Tree",
            "Determine if Tree is Empty",
            "Clear the Tree",
            "Print the tree in Sorted Order (In-Order)",
            "Print the tree in Pre-Order",
            "Print the thre in Post-Order",
            "Print the tree in Tree format",
            "Balance the Tree",
            "Save tree to a Serialized File",
            "Build tree from a Serialized File"};
        Menu mainMenu = new Menu(opts);

        int choice = mainMenu.runMenu();
        while(choice != 0)
        {
            try
            {
                //    while(choice != 0)
                //  {
                switch(choice)
                {
                    //findItem call
                    case 4:
                        System.out.print("Enter the value to find: ");
                        int toFind = input.nextInt();
                        String inTree = (tree.findItem(toFind)==false?
                                " NOT found in tree":" found in tree"); 
                        System.out.println("\n" + toFind + inTree);
                        break;
                    case 3:
                        System.out.print("Enter a value to delete: ");
                        int toDelete = input.nextInt();
                        tree.deleteItem(toDelete);
                        System.out.println("\nDelete attempted");
                        tree.treeFormatTraversal();
                        break;
                    case 5:
                        System.out.printf("\nNumber of nodes in the tree is %d\n", tree.lengthIs());
                        break;
                    case 6:
                        String isEmptyCall = (tree.isEmpty()==false?"not empty":"empty");
                        System.out.println("\nTree is " + isEmptyCall);
                        break;
                    case 7:
                        if(tree.isEmpty())
                            System.out.println("\nTree was already clear");
                        else
                            System.out.println("\nTree is now cleared");
                        tree.clear();
                        break;
                    case 1:
                        System.out.print("Enter the value to insert: ");
                        int toInsert = input.nextInt();
                        tree.insertItem(toInsert);
                        System.out.println("\nItem inserted successfully");
                        break;
                    case 8:
                        System.out.println("\nInOrder traversal");
                        tree.inOrderTraversal();
                        break;
                    case 9:
                        System.out.println("\nPreOrder traversal");
                        tree.preOrderTraversal();
                        break;
                    case 10:
                        System.out.println("\nPostOrder traversal");
                        tree.postOrderTraversal();
                        break;
                    case 11:
                        System.out.print("Level Order traversal");
                        tree.treeFormatTraversal();
                        break;
                    case 2:
                        if(getRawDataValues(tree))
                            System.out.println("Tree Data Read From File");
                        else
                        {
                            System.out.println(
                                    "Unable to Read Raw Values from file");
                            tree.clear();
                        }
                        tree.treeFormatTraversal();
                        break;
                    case 12:
                        if(!tree.isEmpty())
                        {
                            tree.balance();
                            tree.treeFormatTraversal();
                        }
                        else
                            System.out.println("Tree is empty");
                        break;
                    case 13:
                        if(tree.isEmpty())
                            System.out.print("\nTree is empty\n");
                        else if(tree.saveToSerializedFile())
                        {
                            System.out.print("\nTree saved\n");
                            tree.treeFormatTraversal();
                        }
                        break;
                    case 14:
                        tree.buildFromSerializedFile();
                        if(!tree.isEmpty())
                        {
                            System.out.print("\nTree built from file\n");
                            tree.treeFormatTraversal();
                        }
                        break;
                }
            }
            //catch any input mistmatched and ask the user for a new menu choice
            catch(InputMismatchException imE)
            {
                System.err.printf(
                        "Invalid input - try an integer next time\n");
                input.next();
            }
            choice = mainMenu.runMenu();
        }

    }
}

