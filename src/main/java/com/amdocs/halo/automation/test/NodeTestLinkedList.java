package com.amdocs.halo.automation.test;

import groovy.util.Node;

public class NodeTestLinkedList 
{
	Node head=null;
	public class Node
	{
		int data;
		Node next=null;
		Node(int data)
		{
			this.data=data;
		}
	}
	
	public Node createNodeSingleList()
	{
		head= new Node(40);
		Node root_b= new Node(50);
		Node root_c= new Node(60);
		Node root_d= new Node(70);
		Node root_e= new Node(80);
		Node root_f= new Node(30);
		Node root_g= new Node(20);
		Node root_h= new Node(10);	
		
		head.next=root_b;
		root_b.next=root_c;
		root_c.next=root_d;
		root_d.next=root_e;
		root_e.next=root_f;
		root_f.next=root_g;
		root_g.next=root_h;
		root_h.next=null;
		
		return head;
		
	}
	
	public int numberOfNodeLinkedList(Node head)
	{
		int count=0;
		Node node=head;
		while(node != null)
		{
			count++;
			node=node.next;
		}
		return count;
	}
	
	public void traverseSingleLinkedList(Node head)
	{
		Node node=head;
		while(node != null)
		{
			System.out.println(node.data);
			node=node.next;
		}
		
	}
	
	public static void main(String args[])
	{
		NodeTestLinkedList ndeList= new NodeTestLinkedList();
		Node head=ndeList.createNodeSingleList();
		ndeList.traverseSingleLinkedList(head);
		System.out.println(ndeList.numberOfNodeLinkedList(head));
	}
}
