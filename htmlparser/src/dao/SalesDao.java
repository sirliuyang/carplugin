package dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import entity.Sales;

public class SalesDao {
	private static SqlSessionFactory sqlSessionFactory;
	private static SqlSession session;
	
	public SalesDao(SqlSessionFactory tsqlSessionFactory){
		sqlSessionFactory = tsqlSessionFactory;
		session = sqlSessionFactory.openSession();
	}
	
	public void setSqlSessionFactory(SqlSessionFactory tsqlSessionFactory) {
		sqlSessionFactory = tsqlSessionFactory;
	}
	
	public void insert(Sales sales) {
		session.insert("insertSales", sales);
		session.commit();
	}
}
