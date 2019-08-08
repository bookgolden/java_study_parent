package com.java.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.java.cn.bean.Person;

public class TestQueue {

	public static void main(String[] args) {
		Person person1 = new Person("Wilma", "Flintstone", 30, "F");
		Person person2 = new Person("Fred", "Flintstone", 32, "M");
		Person person3 = new Person("Betty", "Rubble", 31, "F");
		Person person4 = new Person("Barney", "Rubble", 33, "M");
		Person person5 = new Person("Barney", "Rubble", 34, "M");
		Person person6 = new Person("Barney", "Rubble", 35, "M");
		
		List<Integer> vsList = Arrays.asList(30,33,35);
		
//		List<Person> personList = Lists.newArrayList(person1, person2, person3, person4, person5, person6);
//		FluentIterable<Person> flu = FluentIterable.from(personList).filter(new Predicate<Person>(){
//			@Override
//			public boolean apply(Person input) {
//				return vsList.contains(input.getAge());
//			}
//		});
		
//		for(Iterator<Person> iter=flu.iterator();iter.hasNext();){
//			Person p = (Person)iter.next();
//			System.out.println(p.getFirstName()+", "+p.getAge());
//		}
		
	}
	
	public void t(){
		List lt = new ArrayList();
		LinkedList<String> list = new LinkedList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		
//		System.out.println(list.peekLast());
//		System.out.println(list.toString());
		if(list.contains("2")){
			System.out.println("true");
			list.remove("2");
		}
		list.addFirst("2");
		System.out.println(list.toString());
		
		
//		String vs = "[180003]";
//		System.out.println(vs.substring(1, vs.length()-1));
	}
}
