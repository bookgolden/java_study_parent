package com.java.cn.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class DateTimeFormatterTest {
//	private static final Logger logger = LoggerFactory.getLogger(DateTimeFormatterTest.class);
	
	public static void main(String[] args) {
//		LocalDateTime localDateTime = LocalDateTime.now();
//		localDateTime = localDateTime.plusMonths(1);
//		System.out.println(localDateTime);
		
//		String vs = DayOfWeeekEnum.getEnumByValue(localDateTime.getDayOfWeek().getValue()).getAlias();
//		System.out.println(today+"("+vs+")");
		
//		System.out.println(localDateTime.getDayOfYear());	//43
//		System.out.println(localDateTime.getMonthValue());	//2
//		System.out.println(localDateTime.getDayOfMonth());	//12
//		System.out.println(localDateTime.getDayOfWeek());	//MONDAY
		
//		LocalTime localTime = LocalTime.now();
//		LocalDateTime localDate = LocalDateTime.now();
//		System.out.println("localDate = "+localDate+", localTime = "+localTime);
//		System.out.println(localDate.withDayOfMonth(2).withYear(2012).withDayOfMonth(4).toString());
		
		//Instant: 时间戳（以Unix元年：1970年1月1日00:00:00到某个时间之间的毫秒值）
//		Instant instant = Instant.now();	//某认获取UTC时区
//		System.out.println(instant);
//		OffsetDateTime offsetDateTime = Instant.now().atOffset(ZoneOffset.ofHours(8));
//		System.out.println(offsetDateTime);
//		System.out.println(instant.toEpochMilli());//时间戳
		
		//Duration获取时间之间的间隔
//		Instant inst1 = Instant.now();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		Instant inst2 = Instant.now();
//		Duration duration = Duration.between(inst1, inst2);
//		System.out.println(duration.getSeconds());
		
		
		//Period两个日期之间的间隔
//		LocalDate ld1 = LocalDate.of(2015, 01, 01);
//		LocalDate ld2 = LocalDate.now();
//		Period period = Period.between(ld1, ld2);
//		System.out.println(period);
//		System.out.println(period.getYears());
//		System.out.println(period.getMonths());
//		System.out.println(period.getDays());
//		System.out.println(period.getYears());
//		System.out.println(period.toTotalMonths());
		
		//TemporalAdjusters 时间调整器
		LocalDateTime ldt = LocalDateTime.now();
//		LocalDateTime ldt2 = ldt.withDayOfMonth(10);	//指定为这个月的10号
//		LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));	//个下周五
//		System.out.println(ldt2);
//		System.out.println(ldt3);
//		//自定义：下一个工作日
//		LocalDateTime ldt5 = ldt.with((x)->{
//			LocalDateTime ldt4 = (LocalDateTime)x;
//			DayOfWeek dow = ldt4.getDayOfWeek();
//			if(dow.equals(DayOfWeek.FRIDAY)){
//				return ldt4.plusDays(3);
//			}else if(dow.equals(DayOfWeek.SATURDAY)){
//				return ldt4.plusDays(2);
//			}else{
//				return ldt4.plusDays(1);
//			}
//		});
//		LocalDateTime firstDayOfMonth = ldt.with(TemporalAdjusters.firstDayOfMonth());
//		LocalDateTime lastDayOfYear = ldt.with(TemporalAdjusters.lastDayOfYear());
//		LocalDateTime firstDayOfNextYear = ldt.with(TemporalAdjusters.firstDayOfNextYear());
//		System.out.println(firstDayOfMonth);
//		System.out.println(lastDayOfYear);
//		System.out.println(firstDayOfNextYear);
		
		//DateTimeFormatter：格式化时间/日期
//		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
//		DateTimeFormatter dtf2 = DateTimeFormatter.ISO_DATE;
//		LocalDateTime ldt = LocalDateTime.now();
//		System.out.println(ldt.format(dtf));
//		System.out.println(ldt.format(dtf2));
		
//		String today = localDateTime.format(DateTimeFormatter.ofPattern("MM月dd日"));
//		LocalDateTime localDateTime = LocalDateTime.parse("2018-02-12 12:23:12", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
//		LocalDate lt = LocalDate.parse("2018-02-12", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//		System.out.println(lt.format(dtm));
		
//		String strDate2 = ldt.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss"));
//		System.out.println(strDate2+", "+dtf2.format(ldt));
//		System.out.println(ldt.parse(strDate2, dtf2));
		
		//时区：ZonedDate、ZonedTime、ZonedDateTime
//		Set<String> set = ZoneId.getAvailableZoneIds();
//		set.forEach(System.out::println);	//打印所有时区
		
//		LocalDateTime ldtd = LocalDateTime.now(ZoneId.of("Europe/Tallinn"));
//		System.out.println(ldtd);
		
//		LocalDateTime ldtd2 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
//		ZonedDateTime zdt = ldtd2.atZone(ZoneId.of("Asia/Shanghai"));
//		System.out.println(zdt);
		
		LocalDate local = LocalDate.now();
		System.out.println(local.plusDays(1).toString());
	}
}
