package dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import entity.Car;

public class CarDao {
	
	private SqlSessionFactory sqlSessionFactory;
	
	public CarDao(SqlSessionFactory sqlSessionFactory){
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public SqlSession openSqlSession(){
		return sqlSessionFactory.openSession();
	}
	
	public void insert(Car car) {
		SqlSession session = openSqlSession();
		session.insert("insertCar", car);
		session.commit();
	}
	
	public void updateTotal(Car car){
		SqlSession session = openSqlSession();
		session.update("updateTotal", car);
		session.commit();
	}
	
	public Car searchByName(String carname) {
		return openSqlSession().selectOne("selectByName", carname);
	}
	
	public Integer getIdByName(String carname){
		return openSqlSession().selectOne("getIdByName", carname);
	}
}
