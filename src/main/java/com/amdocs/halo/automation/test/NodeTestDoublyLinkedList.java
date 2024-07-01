package com.amdocs.halo.automation.test;

import com.amdocs.halo.automation.test.NodeTestLinkedList.Node;

public class NodeTestDoublyLinkedList 
{
	
	Node head=null;
	Node last=null;
		
	public class Node
	{
		int data;
		Node next=null;
		Node previous=null;
		Node(int data)
		{
			this.data=data;
		}
	}
	
	public Node[] createNodeDoubleList()
	{
		Node[] nodes= new Node[2];
		head= new Node(40);
		Node root_b= new Node(50);
		Node root_c= new Node(60);
		Node root_d= new Node(70);
		Node root_e= new Node(80);
		Node root_f= new Node(30);
		Node root_g= new Node(20);
		Node root_h= new Node(10);	
		
		head.next=root_b;
		head.previous=null;
		
		root_b.next=root_c;
		root_b.previous=head;
		
		root_c.next=root_d;
		root_c.previous=root_b;
		
		root_d.next=root_e;
		root_d.previous=root_c;
		
		root_e.next=root_f;
		root_e.previous=root_d;
		
		root_f.next=root_g;
		root_f.previous=root_e;
		
		root_g.next=root_h;
		root_g.previous=root_f;
		
		root_h.next=null;
		root_h.previous=root_g;
		
		last=root_h;
		
		nodes[0]=head;
		nodes[1]=last;
	
		return nodes;
		
	}
	
	public void traverseDoubleLinkedList(Node head)
	{
		Node node=head;
		System.out.println("==================Traverse from last=======================");
		while(node != null)
		{
			System.out.println(node.data);
			node=node.next;
		}
		
	}
	
	public void traverseReverseDoubleLinkedList(Node last)
	{
		Node node=last;
		System.out.println("==================Traverse from behind=======================");
		
		while(node != null)
		{
			System.out.println(node.data);
			node=node.previous;
		}
		
	}
	
	public static void main(String args[])
	{
		NodeTestDoublyLinkedList ndeList= new NodeTestDoublyLinkedList();
		Node[] nodes=ndeList.createNodeDoubleList();
		ndeList.traverseDoubleLinkedList(nodes[0]);
		ndeList.traverseReverseDoubleLinkedList(nodes[1]);
	}

}
