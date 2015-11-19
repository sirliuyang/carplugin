package dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import entity.Sales;

public class SalesDao {
	private SqlSessionFactory sqlSessionFactory;
	
	public SalesDao(SqlSessionFactory sqlSessionFactory){
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	

	public SqlSession openSqlSession(){
		return sqlSessionFactory.openSession();
	}
	
	public void insert(Sales sales) {
		SqlSession session = openSqlSession();
		session.insert("insertSales", sales);
		session.commit();
	}
}
