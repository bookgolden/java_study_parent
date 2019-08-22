## 创建Stream
    三个操作步骤 1、创建Stream 2、中间操作 3、终止操作（终端操作）
    
###     第一步:
    // 1、通过Collection系列集合提供的Stream()或parallelStream
	List<String> list = new ArrayList<>();
	Stream<String> stream1 = list.stream();

	// 2、通过Arrays中的静态方法stream()获取数组流
	Person[] person = new Person[10];
	Stream<Person> stream2 = Arrays.stream(person);

	// 3、通过Stream类中的静态方法of()
	Stream<String> stream3 = Stream.of("aaa", "bbb", "ccc");
	
	//4、创建无限流
	//迭代
	Stream<Integer> stream4 = Stream.iterate(0, x -> x+2);
	stream4.forEach(System.out::println);
	stream4.limit(10).forEach(System.out::println);
	
	//生成
	Stream.generate(() -> Math.random()).forEach(x -> System.out.println(x));
	Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);
	
### 	第二步 中间操作:

####     筛选与切片
	/**
	 * 筛选与切片
	 * filter--接收Lambda,从流中排除某些元素
	 * limit--截断流，使其元素不超过给定数量
	 * skip(n)--跳过元素，返回一个扔掉了前n个元素的流，若流中不足n个，则返回一个空流，与limit(n)互补
	 * distinct--筛选，通过流所生成元素的hashCode()和equals()去除重复元素
	 */
    static List<Employee> employee = Arrays.asList(
		new Employee("张三", 18, 9999.99, Status.FREE),
		new Employee("李四", 38, 5555.99, Status.BUSY),
		new Employee("王五", 50, 6666.66, Status.VOCATION),
		new Employee("赵六", 16, 3333.33, Status.FREE),
		new Employee("麻五", 116, 30.33, Status.FREE),
		new Employee("田七", 8, 7777.77, Status.BUSY)
		new Employee("田七", 8, 7777.77, Status.BUSY)
		new Employee("田七", 8, 7777.77, Status.BUSY)
	);
---
####     sorted、filter、limit、skip、distinct
    		
    		employee.stream().sorted((x, y) -> Integer.compare(x.getAge(), y.getAge()))
    						.filter((w) -> w.getAge() > 2)
    						.skip(2)
    						.limit(10)
    						.distinct()
    						.forEach(System.out::println);
    
    
####     .map映射
    
        /**
		 * 映射：
		 * map--接收Lambda,将元素转换成其他形式或提取信息，接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素，
		 * flatMap--接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
		 */
    	
    //		employee.stream().map(Employee::getName).forEach(System.out::println);
    		
    		List<String> list = Arrays.asList("eee","bbb","ccc","ddd","aaa");
    		
    //		list.stream().map((str)->str.toUpperCase())
    //		.forEach(System.out::println);
    		
    //		list.stream().map((s)->filterCharacter(s))
    //						.forEach(sm -> {
    //							sm.forEach(System.out::print);
    //						});
    		
    //		list.stream().map(x->filterCharacter(x)).forEach(System.out::print);
    		
    		//flatMap
    		list.stream().flatMap(x -> filterCharacter(x)).forEach(System.out::print);
    		System.out.println();
    		Stream<Character> stream = list.stream().flatMap(x -> filterCharacter(x));
    		String str = stream.map(x -> x.toString()).collect(Collectors.joining(",", "===head===", "===tail==="));
    		System.out.println(str);
    		
    		//sorted
    		list.stream().flatMap(x->filterCharacter(x)).sorted().forEach(System.out::print);
    		
    	}
    	
    	public static Stream<Character> filterCharacter(String str){
    		List<Character> list = new ArrayList<>();
    		for(Character c : str.toCharArray()){
    			list.add(c);
    		}
    		return list.stream();
    	}
   
	
#### 	查找与匹配
	
	/**
	 * 查找与匹配
	 * allMath--检查是否匹配所有元素
	 * anyMath--检查是否至少匹配一个元素
	 * noneMath--检查是否没有匹配所有元素
	 * findFirst--返回第一个元素
	 * findAny--返回当前流中的任意元素
	 * count--返回流中元素的总个数
	 * max--返回流中最大值
	 * min--返回流中最小值
	 */

    //		boolean b1 = employee.stream().allMatch((e) -> e.getStatus().equals(Status.BUSY));
    //		boolean b2 = employee.stream().anyMatch((e) -> e.getStatus().equals(Status.BUSY));
    //		boolean b3 = employee.stream().noneMatch((e) -> e.getStatus().equals(Status.BUSY));
    //		System.out.println("b1=" + b1 + ", b2=" + b2 + ", b3=" + b3);
    		
    //		Optional<Employee> op1 = employee.stream().sorted((x, y) -> Double.compare(x.getSalary(), x.getSalary())).findFirst();
    //		Optional<Employee> op2 = employee.stream().filter((e)->e.getStatus().equals(Status.FREE)).findAny();
    //		Optional<Employee> op3 = employee.parallelStream().filter((e)->e.getStatus().equals(Status.FREE)).findAny();
    //		System.out.println(op1.get() + " \n" + op2.get() + " \n" + op3.get());
    		
    //		long count = employee.parallelStream().count();
    //		System.out.println(count);
    
    //		Optional<Employee> maxEmp = employee.stream().max((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
    //		Optional<Employee> minEmp = employee.stream().max((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
    //		System.out.println(maxEmp.get() + "\n" + minEmp.get());
    		
    //		Optional<Double> max = employee.stream().map((x)->x.getSalary()).max((x, y)-> Double.compare(x, y));
    //		Optional<Double> min = employee.stream().map(Employee::getSalary).min((x, y)-> Double.compare(x, y));
    //		System.out.println(max.get() + "\n" + min.get());
    		
    		/**
    		 * 归约
    		 * reduce(T identity, BinaryOperator)
    		 * reduce(BinaryOperator)--可以将流中元素反复结合起来，得到一个值
    		 */
    //		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
    //		Integer sum = list.stream().reduce(0, (x, y)-> x + y);
    //		System.out.println(sum);
    		
    //		Optional<Double> salarySum = employee.stream().map(Employee::getSalary).reduce(Double::sum);
    //		System.out.println(salarySum.get());
    		
    		/**
    		 * 收集
    		 * collect--将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
    		 */
    //		employee.stream().map(Employee::getName).collect(Collectors.toList()).forEach(System.out::print);
    		
    //		String vs = employee.stream().map(Employee::getName).collect(Collectors.joining(",", "===", "==="));
    //      String vs = employee.stream().map(Employee::getName).collect(Collectors.joining(","));
    //		System.out.println(vs);
    		
    //		employee.stream().map(Employee::getName).collect(Collectors.toSet()).forEach(System.out::println);
    //		employee.stream().collect(Collectors.toSet()).forEach(System.out::println);
    		
    //		HashSet<String> hashSet = employee.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
    //		System.out.println(hashSet.toString());
    //		hashSet.forEach(System.out::print);
    		
    //		Map<Integer, Employee> ep = employee.stream().collect(Collectors.toMap(Employee::getAge, Function.identity()));
    //		System.out.println(ep);
    		
    		//总数
    //		Long count = employee.stream().collect(Collectors.counting());
    //		Long cound = employee.stream().count();
    //		System.out.println(count + ", " + cound);
    		
    		//平均值
    //		Double avg = employee.stream().collect(Collectors.averagingDouble(Employee::getSalary));
    //		System.out.println(avg);
    		
    		//总和
    //		double sum = (double) employee.stream().collect(Collectors.summingDouble(Employee::getSalary));
    //		System.out.println(sum);
    		
    		//最大值
    //		Optional<Employee> emp = employee.stream().collect(Collectors.maxBy((x, y)-> Double.compare(x.getSalary(), y.getSalary())));
    //		Optional<Employee> emp = employee.stream().max((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
    //		System.out.println(emp.get());
    		
    		//最小值
    //		Optional<Double> min = employee.stream().map(Employee::getSalary).collect(Collectors.minBy(Double::compare));
    //		System.out.println(min.get());
    		
    		//分组
    //		Map<Status, List<Employee>> map = employee.stream().collect(Collectors.groupingBy(Employee::getStatus));
    //		System.out.println(map);
    //      map.forEach((k, v) -> System.out.println(k + " = " + v));
    		
    		//多级分组
    //		Map<Status, Map<String, List<Employee>>> map = employee.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e->{
    //			if(((Employee)e).getAge()<25){
    //				return "青年";
    //			}else{
    //				return "中老年";
    //			}
    //		})));
    //		System.out.println(map);
    		
    		//分区
    //		Map<Boolean,List<Employee>> map = employee.stream().collect(Collectors.partitioningBy((e)-> e.getSalary()>8000));
    //		System.out.println(map.get(true));
    		
    		//综合统计 
    //		DoubleSummaryStatistics ds = employee.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
    //		System.out.println("count="+ds.getCount()+", sum="+ds.getSum()+", average="+ds.getAverage()+", max="+ds.getMax()+", min="+ds.getMin());
    //		System.out.printf("count=%s, sum=%s, average=%s, max=%s, min=%s", ds.getCount(), ds.getSum(), ds.getAverage(), ds.getMax(), ds.getMin());
    		
    		//获取数字的个数、最小值、最大值、总和以及平均值
    //		List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
    //		IntSummaryStatistics is = primes.stream().mapToInt((x) -> x).summaryStatistics();
    //		System.out.printf("count=%s, sum=%s, average=%s, max=%s, min=%s", is.getCount(), is.getSum(), is.getAverage(), is.getMax(), is.getMin());
		