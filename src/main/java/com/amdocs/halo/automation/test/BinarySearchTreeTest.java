package com.amdocs.halo.automation.test;

import com.amdocs.halo.automation.test.NodeTestDoublyLinkedList.Node;

public class BinarySearchTreeTest 
{

	Node root=null;
		
	public class Node
	{
		int data;
		Node right=null;
		Node left=null;
		Node(int data)
		{
			this.data=data;
		}
	}
	
	public Node createTree()
	{
		root= new Node(40);
		Node root_b= new Node(50);
		Node root_c= new Node(60);
		Node root_d= new Node(70);
		Node root_e= new Node(80);
		Node root_f= new Node(30);
		Node root_g= new Node(20);
		Node root_h= new Node(10);	
		Node root_i= new Node(65);
		Node root_j= new Node(75);
		Node root_k= new Node(63);
		Node root_l= new Node(68);
		
		root.right=root_c;
		root.left=root_g;
		
		root_b.right=null;
		root_b.left=null;
		
		root_c.right=root_e;
		root_c.left=root_b;
		
		root_d.left=root_i;
		root_d.right=root_j;
		
		root_e.left=root_d;
		root_e.right=null;
		
		root_f.left=null;
		root_f.right=null;
		
		root_g.left=root_h;
		root_g.right=root_f;
		
		root_h.left=null;
		root_h.right=null;
		
		root_i.left=root_k;
		root_i.right=root_l;
		
		root_j.left=null;
		root_j.right=null;
		
		root_k.left=null;
		root_k.right=null;
		
		root_l.left=null;
		root_l.right=null;
	
		return root;	
	}
	
	public void printLeftNodes(Node node)
	{
		while(node!=null)
		{
			System.out.println(node.data);
			node=node.left;
		}
	}
	
	public static void printRightNodes(Node node)
	{
		if(node.left==null && node.right==null)
		{
			System.out.println(node.data);
			return;
		}
		System.out.println(node.data);
		if(node.right!=null)
			printRightNodes(node.right);
		else if(node.left!=null)
			printRightNodes(node.left);
		else
			return;
	}
	
	public static void main(String[] args) 
	{
		BinarySearchTreeTest bst= new BinarySearchTreeTest();
		Node root=bst.createTree();
		bst.printRightNodes(root);
	}
	
	

}
