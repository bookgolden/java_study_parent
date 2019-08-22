    
    HashMap<Integer, String> map = new HashMap<>();
	map.put(1, "one");
	map.put(2, "two");
	map.put(3, "three");
	
	map.forEach((k, v) -> System.out.println(k + "=" + v));
	结果：
	    1=one
        2=two
        3=three
	
	// Java7以及之前做法
	if (map.containsKey(4)) { // 1
		System.out.println(map.get(4));
	} else {
		System.out.println("NoValue");
	}
	// Java8使用Map.getOrDefault()
	System.out.println(map.getOrDefault(4, "NoValue")); // 2
	结果：
    	NoValue
        NoValue
	
	HashMap<Integer, String> map = new HashMap<>();
	map.put(1, "one");
	map.put(2, "two");
	map.put(3, "three");
	map.replaceAll((k, v) -> v.toUpperCase());
	map.forEach((k, v) -> System.out.println(k + "=" + v));
	结果：
    	1=ONE
        2=TWO
        3=THREE
	
-----------    
    
    package com.guavas.test;
    
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.Collections;
    import java.util.Comparator;
    import java.util.HashMap;
    import java.util.HashSet;
    import java.util.IntSummaryStatistics;
    import java.util.List;
    import java.util.Map;
    import java.util.Optional;
    import java.util.Set;
    import java.util.function.Predicate;
    import java.util.stream.Collectors;
    import org.junit.Test;
    import com.google.common.base.Function;
    import com.google.common.base.Joiner;
    import com.google.common.base.Preconditions;
    import com.google.common.collect.ArrayListMultimap;
    import com.google.common.collect.BiMap;
    import com.google.common.collect.HashBasedTable;
    import com.google.common.collect.HashBiMap;
    import com.google.common.collect.Lists;
    import com.google.common.collect.Multimap;
    import com.google.common.collect.Ordering;
    import com.google.common.collect.Range;
    import com.google.common.collect.Table;
    import com.lambda.Studentt;
    
    public class App {
    	
    	/**
    	 * 找出其中所有不重复的素数
    	 * */
    	public static void distinctPrimary(String... numbers) {
            List<String> l = Arrays.asList(numbers);
            List<Integer> r = l.stream()
                    .map(e -> new Integer(e))
                    .filter(e -> isPrime(e))
                    .distinct()
                    .collect(Collectors.toList());
            System.out.println("distinctPrimary result is: " + r);
    	}
    	
    	/**
    	 * 找出其中所有不重复的素数，并统计其出现次数
    	 * */
    	public static void primaryOccurrence(String... numbers) {
            List<String> l = Arrays.asList(numbers);
            Map<Integer, Integer> r = l.stream()
                .map(e -> new Integer(e))
                .filter(e -> isPrime(e))
                .collect( Collectors.groupingBy(p->p, Collectors.summingInt(p->1)) );
            System.out.println("primaryOccurrence result is: " + r);
    	}
    	
    	/**
    	 * 所有不重复素数的和
    	 * */
    	public static void distinctPrimarySum(String... numbers) {
            List<String> l = Arrays.asList(numbers);
            int sum = l.stream()
                .map(e -> new Integer(e))
                .filter(e -> isPrime(e))
                .distinct()
                .reduce(0, (x,y) -> x+y); // equivalent to .sum()
            System.out.println("distinctPrimarySum result is: " + sum);
        }
    	
    	@Test
        public void testDistinctPrimary(){
    		distinctPrimary("1","6","7","11","9","7");
        }
    	
    	@Test
    	public void testPrimaryOccurrence(){
    		primaryOccurrence("1","6","7","11","9","7");
    	}
    	
    	
    	/**
    	 * 判定素数
    	 * */
    	public static boolean isPrime(int n){
          if(n==1) return false;
     
          for(int i=2; i<=Math.sqrt(n); i++){
            if(n%i == 0)
                  return false;
          }  
          return true;
    	}
    	
    	/**
    	 * 统计年龄在25-35岁的男女人数、比例
    	 * */
    /*    public static void boysAndGirls(List<Person> persons) {
            Map<Integer, Integer> result = persons.parallelStream().filter(p -> p.getAge()>=25 && p.getAge()<=35).collect(Collectors.groupingBy(p->p.getSex(), Collectors.summingInt(p->1)));
            System.out.print("boysAndGirls result is " + result);
            System.out.println(", ratio (male : female) is " + (float)result.get(Person.MALE)/result.get(Person.FEMALE));
        }*/
    	
    	
    	protected void getSysInfo(){
    		System.out.println("xxxxxx");
    	}
    	
        @Test
    	public void testCheckArguments(){
        	int i=4;
    		Preconditions.checkArgument(i>5, "prames is %s",5);//如果i>5返回false,抛出 IllegalArgumentException异常
    	}
    	
        @Test
        public void testThread(){
        	new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();
        }
        
        /*@Test
        public void testMultiset(){
        	List<String> strings = new ArrayList<String>();
        	strings.add("aaaa");
        	strings.add("BB");
        	strings.add("Caac");
        	strings.add("ddd");
        	
        	Multiset<Map> set = HashMultiset.create(
    	    FluentIterable.from(strings).filter(new Predicate<String>() {
                public boolean apply(String string) {
                    return CharMatcher.JAVA_UPPER_CASE.matchesAllOf(string);
                }
            })
            .transform(new Function<String, Map>() {
                public Map apply(String string) {
                    return ImmutableMap.of(string, string.length());
                }
            }));
        	System.out.println(set);
        }*/
        
        @Test
        public void testBiMap(){
        	BiMap<Integer,String> biMap=HashBiMap.create();
        	biMap.put(1,"hello");
        	biMap.put(2,"helloa");
        	biMap.put(3,"world");
        	biMap.put(4,"worldb");
        	biMap.put(5,"my");
        	biMap.put(6,"myc");
        	int value= biMap.inverse().get("my");
        	System.out.println("my --"+value);
        }
        
        @Test
        public void testTable(){
        	Table<Integer,Integer,Object> personTable=HashBasedTable.create();
        	personTable.put(1,20,new Object());
        	personTable.put(0,30,new Object());
        	personTable.put(0,25,new Object());
        	personTable.put(1,50,new Object());
        	personTable.put(0,27,new Object());
        	personTable.put(1,29,new Object());
        	personTable.put(0,33,new Object());
        	personTable.put(1,66,new Object());
    
        	//1,得到行集合
        	Map<Integer,Object> rowMap= personTable.row(0);
        	int maxAge= Collections.max(rowMap.keySet());
        	System.out.println(maxAge);
        }
        
        @Test
        public void testMutilMap(){
        	Multimap<String, String> customersByType =ArrayListMultimap.create();
        	customersByType.put("abc", "a");
        	customersByType.put("abc", "b");
        	customersByType.put("abc", "c");
        	customersByType.put("abc", "d");
        	customersByType.put("abcd", "e");
        	customersByType.put("abcde", "f");
    
        	for(String obj:customersByType.get("abc")){
        		System.out.println(obj);
        	}
        }
        
        @Test
        public void testRange(){
        	System.out.println(Range.closed("left", "right"));
        }
        
        @Test
        public void testJoiner(){
        	//Joiner joiner = Joiner.on("; ").skipNulls();
        	Joiner joiner = Joiner.on("; ").useForNull("---");
        	System.out.println(joiner.join("a",null,"b","c","d"));
        }
        
        @Test
        public void testSplit(){
        	System.out.println(",a,,b,".split(",").length);
        }
        
        @Test
        public void testOrdering(){
        	List<Student> students = Lists.newArrayList();
        	Student student1 = new Student();
        	student1.setName("aaa");
        	student1.setAge(12);
        	
        	Student student2 = new Student();
        	student2.setAge(18);
        	
        	Student student3 = new Student();
        	student3.setName("ccc");
        	student3.setAge(20);
        	
        	students.add(student1);
        	students.add(student2);
        	students.add(student3);
        	//自然排序
        	Ordering<Student> ordering = Ordering.natural().nullsFirst().reverse().onResultOf(new Function<Student, String>() {
    			public String apply(Student u) {
    				return u.getName();
    			}
    		});
    		Collections.sort(students, ordering);
    		for(Student student:students){
    			System.out.println(student.getAge()+student.getName());
    		}
        }
        
        
        @Test
        public void testLambda(){
        	new Thread(()-> System.out.println("aaaa")).start() ;
        }
        
        @Test
        public void testComparator(){
    	    //Comparator<String> c = (String lhs, String rhs) -> lhs.compareTo(rhs);  
    	    //System.out.println(c.compare("Hello", "World")); 
        	Comparator<String> c = (String lhs, String rhs) -> {
        		System.out.println("I am comparing " +lhs + " to " + rhs);
        		return lhs.compareTo(rhs); 
        	};
        	
    	    int result = c.compare("Hello", "World");  
            System.out.println(result);  
        }
        
        /**
         * Lambda 迭代
         * */
        @Test
        public void testIterable(){
        	// Before Java 8
        	List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        	for (String feature : features) {
        	    System.out.println(feature);
        	}
        	System.out.println("xxxxxxxxxx");
        	// After Java 8
        	features.forEach(System.out::println);
        	features.forEach((feature)-> System.out.println(feature));
        	
        	ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        	list.forEach( str -> {if(str.length()>3) System.out.println(str);});
        }
        
        @Test
        public void testCount(){
        	List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        	long count = features.stream().filter(feature -> feature.contains("d")).count();
        	System.out.println(count);
        }
        
        
        @Test
        public void testDel(){
        	// 使用迭代器删除列表元素
    /*    	ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        	Iterator<String> it = list.iterator();
        	while(it.hasNext()){
        	    if(it.next().length()>3) // 删除长度大于3的元素
        	        it.remove();
        	}*/
        	
        	// 使用removeIf()结合匿名名内部类实现
    /*    	ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        	list.removeIf(new Predicate<String>(){ // 删除长度大于3的元素
        	    @Override
        	    public boolean test(String str){
        	        return str.length()>3;
        	    }
        	});*/
        	
        	// 使用Lambda
        	ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        	list.removeIf(str -> str.length()>3); // 删除长度大于3的元素
        }
        
        @Test
        public void testMap(){
        	// Java7以及之前迭代Map
    /*    	HashMap<Integer, String> map = new HashMap<>();
        	map.put(1, "one");
        	map.put(2, "two");
        	map.put(3, "three");
        	for(Map.Entry<Integer, String> entry : map.entrySet()){
        	    System.out.println(entry.getKey() + "=" + entry.getValue());
        	}*/
        	
        	// 使用forEach()结合Lambda表达式迭代Map
        	HashMap<Integer, String> map = new HashMap<>();
        	map.put(1, "one");
        	map.put(2, "two");
        	map.put(3, "three");
        	map.forEach((k, v) -> System.out.println(k + "=" + v));
        }
        
        @Test
        public void testDef(){
        	// 查询Map中指定的值，不存在时使用默认值
        	HashMap<Integer, String> map = new HashMap<>();
        	map.put(1, "one");
        	map.put(2, "two");
        	map.put(3, "three");
        	// Java7以及之前做法
        	if(map.containsKey(4)){ // 1
        	    System.out.println(map.get(4));
        	}else{
        	    System.out.println("NoValue");
        	}
        	// Java8使用Map.getOrDefault()
        	System.out.println(map.getOrDefault(4, "NoValue")); // 2
        }
        
        @Test
        public void testUpMap(){
        	HashMap<Integer, String> map = new HashMap<>();
        	map.put(1, "one");
        	map.put(2, "two");
        	map.put(3, "three");
        	map.replaceAll((k, v) -> v.toUpperCase());
        	
        	map.forEach((k, v) -> System.out.println(k + "=" + v));
        }
        
        @Test 
        public void testComputeIfAbsent(){
        	Map<Integer, Set<String>> map = new HashMap<>();
        	// Java7及以前的实现方式
        	if(map.containsKey(1)){
        	    map.get(1).add("one");
        	}else{
        	    Set<String> valueSet = new HashSet<String>();
        	    valueSet.add("one");
        	    map.put(1, valueSet);
        	}
        	// Java8的实现方式
        	map.computeIfAbsent(1, v -> new HashSet<String>()).add("yi");
        	
        	map.forEach((k, v) -> System.out.println(k + "=" + v));
        }
        
        @Test
        public void testSort(){
        	// Collections.sort()方法
        	ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        	Collections.sort(list, new Comparator<String>(){
        	    @Override
        	    public int compare(String str1, String str2){
        	        return str1.length()-str2.length();
        	    }
        	});
        	
        	// List.sort()方法结合Lambda表达式
        	ArrayList<String> list1 = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        	list1.sort((str1, str2) -> str1.length()-str2.length());
        }
        
        
        @Test
        public void testFilter(){
        	List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        	 
    	    System.out.println("Languages which starts with J :");
    	    filter(languages, (str)->((String) str).startsWith("J"));
    	 
    	    System.out.println("Languages which ends with a ");
    	    filter(languages, (str)->((String) str).endsWith("a"));
    	 
    	    System.out.println("Print all languages :");
    	    filter(languages, (str)->true);
    	 
    	    System.out.println("Print no language : ");
    	    filter(languages, (str)->false);
    	 
    	    System.out.println("Print language whose length greater than 4:");
    	    filter(languages, (str)->((String) str).length() > 4);
        }
        
        public static void filter(List<String> names, Predicate condition) {
            for(String name: names)  {
                if(condition.test(name)) {
                    System.out.println(name + " ");
                }
            }
        }
        
        
        @Test
        public void testMyFilter(){
        	// 创建一个字符串列表，每个字符串长度大于2
        	List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        	
        	List<String> filtered = languages.stream().filter(x -> x.length()> 4).collect(Collectors.toList());
        	System.out.printf("Original List : %s, filtered list : %s %n", languages, filtered);
        }
        
        @Test
        public void testMapReduce(){
        	//Before Java 1.8
        	List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        	/*for (Integer cost : costBeforeTax) {
        	    double price = cost + .12*cost;
        	    System.out.println(price);
        	}*/
        	//**********************
        	//After Java 1.8
        	costBeforeTax.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);;
        }
        
        @Test
        public void testSuperMapReduce(){
        	// 为每个订单加上12%的税
        	// Before Java 1.8 老方法：
        	List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        	/*double total = 0;
        	for (Integer cost : costBeforeTax) {
        	    double price = cost + .12*cost;
        	    total = total + price;
        	}
        	System.out.println("Total : " + total);*/
        	// After Java 1.8
        	double bill = costBeforeTax.stream().map((cost) -> cost +.12*cost).reduce((sum,cost) -> sum +cost).get();
        	System.out.println("Total : " + bill);
        }
        
        
        @Test
        public void testFilterFunc(){
        	// 将字符串换成大写并用逗号链接起来
        	List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        	String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        	System.out.println(G7Countries);
        }
        
        /**
         * 测试去重
         * */
        @Test
        public void testDuplicate(){
        	List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        	List<Integer> distinct = numbers.stream().distinct().collect(Collectors.toList());
        	System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
        }
        
        /**
         * 测试计算
         * */
        @Test
        public void testShuxue(){
        	//获取数字的个数、最小值、最大值、总和以及平均值
            List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
            IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
            System.out.println("Highest prime number in List : " + stats.getMax());
            System.out.println("Lowest prime number in List : " + stats.getMin());
            System.out.println("Sum of all prime numbers : " + stats.getSum());
            System.out.println("Average of all prime numbers : " + stats.getAverage());
        }
        
       /**
        * 测试Lamdal 比较器
        * */
       @Test
       public void testCompareTo(){
    	   Person[] persons = new Person[]{  new Person("sdfsdf", "Neward",10),  new Person("bbccff", "Neward",30),  new Person("hhsdfg", "Neward",25),  new Person("tert", "Neward",60)}; 
    	   Arrays.sort(persons, (a,b) -> a.age.compareTo(b.age));  
           for (Person person : persons){  
               System.out.println(person);  
           }  
       }
       
       
       @Test
       public void testBianli(){
    	   Set<String> books = new HashSet<String>();
           books.add("轻量级Java EE企业应用实战");
           books.add("疯狂Java 讲义");
           books.add("疯狂Android 讲义");
           books.forEach(obj -> System.out.println("迭代集合元素:" + obj)); 
       }
       
       
       @Test
    	public void testSum(){
    	   List<Studentt> students = new ArrayList<Studentt>();
           students.add(new Studentt(20160001, "孔明4", 20, 1, "土木工程", "武汉大学"));
           students.add(new Studentt(20160002, "伯约", 21, 2, "信息安全", "武汉大学"));
           students.add(new Studentt(20160003, "玄德", 22, 3, "经济管理", "武汉大学"));
           students.add(new Studentt(20160004, "云长", 21, 2, "信息安全", "武汉大学"));
           students.add(new Studentt(20161001, "翼德", 21, 2, "机械与自动化", "华中科技大学"));
           students.add(new Studentt(20161002, "元直", 23, 4, "土木工程", "华中科技大学"));
           students.add(new Studentt(20161003, "奉孝", 23, 4, "计算机科学", "华中科技大学"));
           students.add(new Studentt(20162001, "仲谋", 22, 3, "土木工程", "浙江大学"));
           students.add(new Studentt(20162002, "鲁肃", 23, 4, "计算机科学", "浙江大学"));
           students.add(new Studentt(20163001, "丁奉", 24, 5, "土木工程", "南京大学"));
           
           //mapToLong的使用demo
           double value = students.stream().filter(student -> "计算机科学".equals(student.getMajor())).mapToLong(a -> a.getId()).sum();
           System.out.println(value);
    	}
    	
    	/**
    	 * 字符没有排重
    	 * */
    	@Test
    	public void testChar(){
    		String[] strs = {"java8", "is", "easy", "to", "use"};
    		List<String[]> distinctStrs = Arrays.stream(strs)
                   .map(str -> str.split(""))  // 映射成为Stream<String[]>
                   .distinct()
                   .collect(Collectors.toList());
    		
    		distinctStrs.forEach(strings -> Arrays.stream(strings).forEach(s -> System.out.println(s)));
    	}
    	
    	/**
    	 * 字符排重了
    	 * */
    	@Test
    	public void testFlat(){
    		String[] strs = {"java8", "is", "easy", "to", "use"};
    		
    		List<String> distinctStrsByFlatMap = Arrays.stream(strs)
                   .map(str -> str.split(""))  // 映射成为Stream<String[]>
                   .flatMap(Arrays::stream)    // 扁平化为Stream<String>
                   .distinct()
                   .collect(Collectors.toList());
    		distinctStrsByFlatMap.forEach(s -> System.out.println(s));
    		
    	}
    	
    	/**
    	 * 其中所有长度大于3的元素转换成大写，其余元素不变
    	 * */
    	@Test
    	public void testTransfer(){
    		ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
    		list.replaceAll(str -> str.length() > 3 ? str.toUpperCase():str);
    		list.forEach(System.out::println);
    	}
    	
    	@Test
    	public void testOptional(){
    	  Student stu = new Student(10,"aaa");
    	  List<Order> orders = Arrays.asList(new Order("a","ddd"), new Order("b","eee"));
    	  stu.setOrders(orders);
    		
    	  Optional<Student> student = Optional.of(stu);
    	  // map可以多级的嵌套
    	  List<Order> myOrders = student.map(u->u.getOrders()).orElse(Collections.emptyList());
    	  
    	  myOrders.forEach(order->{System.out.println(order.getName());});
    	}
    	
    	@Test
    	public void testOptional2(){
    		//不可以传入null
    		//Optional<String> str1 = Optional.of(null);
    		
    		//可以传入null 返回空的Optional
    		Optional<String> str2 = Optional.ofNullable("ttt");
    		
    		if(str2.isPresent()){
    			System.out.println("1->"+str2.get());
    		}
    		
    		System.out.println(str2.orElse("2->println no"));
    		
    		System.out.println(str2.orElseGet(()->"default value"));
    		
    		System.out.println(str2.orElseGet(String::new));
    		
    		//System.out.println(str2.orElseThrow(NullPointerException::new));
    		
    		//System.out.println(str2.get());
    		
    		//有就执行,否则不做任何处理
    		str2.ifPresent((value) -> {System.out.println(value); System.out.println("test");});
    		
    		Optional<String> name = str2.map(value-> value.toUpperCase());
    		
    		System.out.println(name.orElse("no value found"));
    		
    		//和map类似，只不过返回的一定是Optional类型的
    		Optional<String> name1 = str2.flatMap((value) -> Optional.of(value.toUpperCase()));
    		
    		
    		String[] strings = {"java8", "is", "easy", "to", "use"};
    		
    		for(String str:strings){
    			Optional<String> x = Optional.of(str).filter(value->value.length()<3);
    			
    			System.out.println(x.orElse("no str length >=2"));
    		}
    		
    	}
    	
    	
    }
    
    class Person{  
        public String firstName;  
        public String lastName;  
        public Integer age;  
        public Person(String firstName, String lastName, Integer age){  
                this.firstName = firstName;  
                this.lastName = lastName;  
                this.age = age;  
        }  
        public String toString(){  
                return firstName+","+lastName+","+age;  
        }  
    }  
    
    class Order{
    	public Order() {
    		super();
    	}
    	public Order(String id, String name) {
    		super();
    		this.id = id;
    		this.name = name;
    	}
    	public String id;
    	public String name;
    	public String getId() {
    		return id;
    	}
    	public void setId(String id) {
    		this.id = id;
    	}
    	public String getName() {
    		return name;
    	}
    	public void setName(String name) {
    		this.name = name;
    	}
    }
    
    class Student{
    	private int age;
    	private String name;
    	
    	List<Order> orders;
    	
    	public Student() {
    		super();
    	}
    	public Student(int age, String name) {
    		super();
    		this.age = age;
    		this.name = name;
    	}
    	public Student(int age, String name, List<Order> orders) {
    		super();
    		this.age = age;
    		this.name = name;
    		this.orders = orders;
    	}
    	public int getAge() {
    		return age;
    	}
    	public void setAge(int age) {
    		this.age = age;
    	}
    	public String getName() {
    		return name;
    	}
    	public void setName(String name) {
    		this.name = name;
    	}
    	public List<Order> getOrders() {
    		return orders;
    	}
    	public void setOrders(List<Order> orders) {
    		this.orders = orders;
    	}
    }
