
    
    LocalDateTime
    LocalDate
    LocalTime
    
    LocalDateTime localDateTime = LocalDateTime.now();
    LocalDateTime localDate = LocalDateTime.now();
    LocalTime localTime = LocalTime.now();
	localDateTime = localDateTime.plusMonths(1);
    localDate.withDayOfMonth(2).withYear(2012).withDayOfMonth(4).toString()
    
    localDateTime.getDayOfYear();
	localDateTime.getMonthValue();
	localDateTime.getDayOfMonth();
	localDateTime.getDayOfWeek();

### 日期/时间格式化 DateTimeFormatter
    String today = localDateTime.format(DateTimeFormatter.ofPattern("MM月dd日"));
    LocalDateTime lt = LocalDateTime.parse("2018-02-12 12:23:12", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    
    DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
	DateTimeFormatter dtf2 = DateTimeFormatter.ISO_DATE;
	LocalDateTime ldt = LocalDateTime.now();
	System.out.println(ldt.format(dtf));
	System.out.println(ldt.format(dtf2));
	
    2019-03-13T02:29:45.234
    2019-03-13

    
    
###     Instant: 时间戳
    （以Unix元年：1970年1月1日00:00:00到某个时间之间的毫秒值）
    
    Instant instant = Instant.now();	//某认获取UTC时区
	System.out.println(instant);
	OffsetDateTime offsetDateTime = Instant.now().atOffset(ZoneOffset.ofHours(8));
	System.out.println(offsetDateTime);
	System.out.println(instant.toEpochMilli());//时间戳
	
	2019-03-12T18:15:09.539Z
    2019-03-13T02:15:09.619+08:00
    1552414509539
    
###     Duration 和 Period
#####     Duration:用于计算两个“时间”间隔
    
    
	Instant inst1 = Instant.now();
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	Instant inst2 = Instant.now();
	Duration duration = Duration.between(inst1, inst2);
	System.out.println(duration.getSeconds());
	
##### 	Period:用于计算两个“日期”间隔
	LocalDate ld1 = LocalDate.of(2015, 01, 01);
	LocalDate ld2 = LocalDate.now();
	Period period = Period.between(ld1, ld2);
	
	System.out.println(period);
	System.out.println(period.getYears());
	System.out.println(period.getMonths());
	System.out.println(period.getDays());
	System.out.println(period.getYears());
	System.out.println(period.toTotalMonths());
	
	P4Y2M12D
    4
    2
    12
    4
    50
    
###     TemporalAdjusters 时间调整器
    LocalDateTime ldt = LocalDateTime.now();
	LocalDateTime ldt2 = ldt.withDayOfMonth(10);	//指定为这个月的10号
	LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));	//个下周五
	System.out.println(ldt2);
	System.out.println(ldt3);
	2019-03-10T02:21:54.986
    2019-03-15T02:21:54.986

    LocalDateTime firstDayOfMonth = ldt.with(TemporalAdjusters.firstDayOfMonth());
	LocalDateTime lastDayOfYear = ldt.with(TemporalAdjusters.lastDayOfYear());
	LocalDateTime firstDayOfNextYear = ldt.with(TemporalAdjusters.firstDayOfNextYear());
	
	System.out.println(firstDayOfMonth);
	System.out.println(lastDayOfYear);
	System.out.println(firstDayOfNextYear);
	2019-03-01T02:23:08.115
    2019-12-31T02:23:08.115
    2020-01-01T02:23:08.115
    
###     时区
    Set<String> set = ZoneId.getAvailableZoneIds();
	set.forEach(System.out::println);	//
	
	LocalDateTime ldtd2 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
	ZonedDateTime zdt = ldtd2.atZone(ZoneId.of("Asia/Shanghai"));
	System.out.println(zdt);
	2019-03-13T02:41:38.167+08:00[Asia/Shanghai]