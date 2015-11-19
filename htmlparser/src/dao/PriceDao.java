package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import entity.Price;

public class PriceDao {
	private SqlSessionFactory sqlSessionFactory;
	
	public PriceDao(SqlSessionFactory sqlSessionFactory){
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	

	public SqlSession openSqlSession(){
		return sqlSessionFactory.openSession();
	}
	
	public int insert(Price price) {
		return openSqlSession().insert("insertPrice", price);
	}
	
	public List<Price> selectAll(){
		return openSqlSession().selectList("selectAllPrice");
	}
}
