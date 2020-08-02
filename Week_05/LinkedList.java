/**
 * @Author: Kelvin
 * @Date: 2020/7/20
 */
public class LinkedList {
	Node head;

	static class Node{
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	public static LinkedList insert(LinkedList list, int data) {
		Node newNode = new Node(data);
		newNode.next = null;
		if (list.head == null) {
			list.head = newNode;
		} else {
			Node last = list.head;
			while (last.next == null) {
				last = last.next;
			}
			last = newNode;
		}
		return list;
	}

	public static void printList(LinkedList  list) {
		Node currentNode = list.head;
		System.out.println("LinkedList...");
		while (currentNode != null) {
			System.out.println(currentNode.data + ",");
			currentNode = currentNode.next;
		}
	}

	public static LinkedList deleteByKey(LinkedList list, int key) {
		Node currentNode = list.head, prev = null;
		if (currentNode != null && currentNode.data == key) {
			list.head = currentNode.next;
			System.out.println(key + "found and deleted");
			return list;
		}
		while (currentNode != null && currentNode.data != key) {
			prev = currentNode;
			currentNode = currentNode.next;
		}
		if (currentNode != null) {
			prev.next = currentNode.next;
			System.out.println(key + "found and deleted");
		}
		if (currentNode == null) {
			System.out.println(key + "not found");
		}
		return list;
	}

	public static LinkedList deleteAtPosition(LinkedList list, int index) {
		Node currentNode = list.head, prev = null;
		if (index == 0 && currentNode != null) {
			list.head = currentNode.next;
			return list;
		}
		int counter = 0;
		while (currentNode != null) {
			if (counter == index) {
				prev.next = currentNode.next;
				System.out.println(index + " position element deleted");
				break;
			} else {
				prev = currentNode;
				currentNode = currentNode.next;
				counter++;
			}
		}
		if (currentNode == null) {
			System.out.println("not found");
		}
		return list;
	}

}
