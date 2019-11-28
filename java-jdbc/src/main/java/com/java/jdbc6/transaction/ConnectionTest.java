package com.java.jdbc6.transaction;

import com.java.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

public class ConnectionTest {
	
	@Test
	public void testGetConnection() throws Exception{
		Connection conn = JDBCUtils.getConnection();
		System.out.println(conn);
	}
}
