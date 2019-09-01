

BlockingQueue的核心方法
    
    方法类型  |  抛出异常   |     特殊值     |    阻塞     |     超时
    ----------------------------------------------------------------------------
     插入    |   add(e)    |    offer(e)   |   put(e)    |    offer(e, time, unit)
    ----------------------------------------------------------------------------
     移除    |  remove()   |   remove()    |   take()    |    poll(time, unit)
    ----------------------------------------------------------------------------
     检查    |  element()  |   peek()      |   不可用     |    不可用
    ----------------------------------------------------------------------------
    
    抛出异常：   当阻塞队列满时,再往队列里面add插入元素会抛IllegalStateException: Queue full
                当阻塞队列空时,再从队列Remove元素时候回抛出NoSuchElementException
    
    特殊值：     插入方法,成功返回true 失败返回false
                移除方法,成功返回元素,队列里面没有就返回null
    
    一直阻塞：   当阻塞队列满时,生产者继续往队列里面put元素,队列会一直阻塞直到put数据or响应中断退出
                当阻塞队列空时,消费者试图从队列take元素,队列会一直阻塞消费者线程直到队列可用.
    
    超时退出：   当阻塞队列满时,队列会阻塞生产者线程一定时间,超过后限时后生产者线程就会退出
 
    
    种类：
    ArrayBlockingQueue：由数组结构组成的有界阻塞队列
    LinkedBlockingQueue： 由链表结构组成的有界（但大小默认值Integer>MAX_VALUE）阻塞队列
    PriorityBlockingQueue： 支持优先级排序的无界阻塞队列
    DelayQueue：使用优先级队列实现的延迟无界阻塞队列
    SynchronousQueue： 不存储元素的阻塞队列，也即是单个元素的队列
    LinkedTransferQueue： 由链表结构组成的无界阻塞队列
    LinkedBlockingDeque： 由链表结构组成的双向阻塞队列
    
--------------------------

    public class QueueBasicTest {
    
        public static void main(String[] args) throws InterruptedException {
            ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);
            // 1、
    //		queue.add("123");
    //		queue.add("456");
    //		queue.add("456");
    
            // 2、
    //		boolean flag = queue.offer("123");
    //		System.out.println(flag);
    //		
    //		flag = queue.offer("456");
    //		System.out.println(flag);
    //		
    //		flag = queue.offer("456");
    //		System.out.println(flag);
    
            // 3、
    //		queue.put("123");
    //		queue.put("456");
    //		queue.put("456");
    
            // 4、
            boolean flag = queue.offer("123", 2, TimeUnit.SECONDS);
            System.out.println(flag);
            flag = queue.offer("456", 2, TimeUnit.SECONDS);
            System.out.println(flag);
            flag = queue.offer("456", 2, TimeUnit.SECONDS);
            System.out.println(flag);
    
            System.out.println("======所有元素=====" + queue.toString() + " , size=" + queue.size());
    
            while (!queue.isEmpty()) {
                // 1、 2、
    //			System.out.println("去除 = "+queue.remove() + ", 剩余  = " + queue.toString());
                // 3、
    //			System.out.println(" take = " + queue.take()+", 剩余 = "+ queue.toString());
                // 4、
                System.out.println("去除 = " + queue.poll(2, TimeUnit.SECONDS) + ", 剩余  = " + queue.toString());
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    