package ubung_11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

public class MyHashTable<Item> implements HashTable {
	
	int M;
	int listsSize;
	LinkedList<String>[] items;
	int maxSteps;

	public MyHashTable() {
		M = 24593;
		M = getNextPrime();
		listsSize = 16;
		items = new LinkedList[ M];
		
		for(int i = 0; i<items.length;i++) {
			items[i]= new LinkedList<String>();
		}
	}
	public MyHashTable(int M) {
		this.M = M;
		this.M = getNextPrime();
		items = new LinkedList[M];
		
		for(int i = 0; i<items.length;i++) {
			items[i]= new LinkedList<String>();
		}
	}
	@Override
	public void resize() {
		System.out.println("resizing");
		MyHashTable<String> newHashTable = new MyHashTable<String>(M*2);
			
		for(LinkedList<String> List : items) {
			for(String item : List) {
				newHashTable.add(item);
			}
		}
		  
	}
	@Override
	public void add(String item) {
		char[] stringAsChars = item.toUpperCase().toCharArray();
		
		int i = 0;
		int value = 0;
		
		BigInteger BigIndex = BigInteger.ZERO;
		BigInteger digitValue;
		
		for(char c : stringAsChars){
			value = c;
			digitValue = new BigInteger(value+"");
			long multiplicator = (long) Math.pow(20, i);
			BigIndex = BigIndex.add(digitValue.multiply(new BigInteger(multiplicator+"")));
			i++;
		}
		BigIndex = BigIndex.mod(new BigInteger(M+""));
		value = BigIndex.intValue();
		
		//pro
		if(items[value].size()>listsSize-1) {
			value = getNewIndex(value,1);
		}
		items[value].add(item.toUpperCase());
		
		//resize if we got less then 1/3 free lists
		/*
		if(33>getEmptyLists()) {
			resize();
		}
		*/
	}
	@Override
	public boolean contains(String item) {
		char[] stringAsChars = item.toUpperCase().toCharArray();
		LinkedList<String> MyLinkedList = null;
		int i = 0;
		int value;
		
		BigInteger BigIndex = BigInteger.ZERO;
		BigInteger digitValue;
		for(char c : stringAsChars){
			value = c;
			digitValue = new BigInteger(value+"");
			long multiplicater = (long) Math.pow(20, i);
			BigIndex = BigIndex.add(digitValue.multiply(new BigInteger(multiplicater+"")));
			i++;
		}
		BigIndex = BigIndex.mod(new BigInteger(M+""));
		value = BigIndex.intValue();
		MyLinkedList = items[value];

		if(MyLinkedList.contains(item.toUpperCase())) {
			return true;
		}		
		else {
			if(items[value].size()>listsSize-1) {
				return findWord(item.toUpperCase(),value,1);
			}
			return false;
		}	
	}
	@Override
	public void reset() {
		items = new LinkedList[M];
	}
	private int getNextPrime() {
		for(int i = M;true;i++) {
			if(UM.isPrime((long) i)) {
				return i;
			}
		}
	}
	@Override
	public void print() {
		System.out.println(toString());
	}
	@Override
	public String toString() {
		String outcome = "";
		
		for(LinkedList<String> List : items) {
			for(String item : List) {
				outcome = outcome + item + "\n";
			}
		}
		return outcome;
	}
	public ArrayList<String> lookup(String bench) {
		Set<String> permute = UM.permute(bench);
		ArrayList<String> existsPermute = new ArrayList<String>();	
		for(String per : permute) {
			if(contains(per))
				existsPermute.add(per.toUpperCase());
		}
		return existsPermute;
	}
	public void printListsSize() {
		for(LinkedList<String> list : items) {
			if(list.size()>0)
				System.out.println(list.size());
		}
	}
	private int getNewIndex(int value, int step) {
		
		int change = (int)Math.pow(step, 2);
		//even numbers change to left
		if(value % 2 == 0) {
			if(items[bordered(value-change)].size()<listsSize){
				return bordered(value-change);
			}
			else {
				return bordered(getNewIndex(value-change, step+1));
			}
		}
		//odd numbers change to right
		else{
			if(items[bordered(value+change)].size()<listsSize){
				return bordered(value+change);
			}
			else {
				return bordered(getNewIndex(value+change, step+1));
			}
		}
	}
	//gets an int thats round about the array
	private int bordered(int index) {
		//already in border
		if(index<items.length && index > -1)
			return index;
		else {
			//if its bigger
			if(index >items.length-1) {

				return bordered(index-items.length);
			}
			//if its lower
			else {
				return bordered(items.length+index);
			}
		}
	}
	private boolean findWord(String word, int value, int step) {	
		int change = (int)Math.pow(step, 2);
		//even numbers change to left
		if(value % 2 == 0) {
			value = bordered(value-change);
			if(items[value].contains(word)) {
				return true;
			}
			else {
				if(items[value].size()<listsSize) {
					return false;
				}
				else {
					return findWord(word, value, step+1);
				}
			}
		}
		//odd numbers change to right
		else {
			value = bordered(value+change);
			if(items[value].contains(word)) {
				return true;
			}
			else {
				if(items[value].size()<listsSize) {
					return false;
				}
				else {
					return findWord(word, value, step+1);
				}
			}
		}
	}
	public double getEmptyLists() {
		int i = 0;
		for(LinkedList<String> list : items) {
			if(list.size() == 0)
				i++;
		}
		double percent= ((double)i/items.length)*100;
		percent = Math.round(percent*100);
		return percent/100;
	}
}
