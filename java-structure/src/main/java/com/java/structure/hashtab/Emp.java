package com.java.structure.hashtab;

/**
 * 雇员
 */
//@Data
public class Emp {
    public int id;
    public String name;
    public Emp next; //next 默认为 null

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Emp getNext() {
		return next;
	}

	public void setNext(Emp next) {
		this.next = next;
	}

	public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}