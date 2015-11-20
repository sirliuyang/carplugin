package dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import entity.Car;

public class CarDao {
	
	private static SqlSessionFactory sqlSessionFactory;
	private static SqlSession session;
	
	public CarDao(SqlSessionFactory tsqlSessionFactory){
		sqlSessionFactory = tsqlSessionFactory;
		session = sqlSessionFactory.openSession();
	}
	
	
	public void insert(Car car) {
		session.insert("insertCar", car);
		session.commit();
	}
	
	public void updateTotal(Car car){
		session.update("updateTotal", car);
		session.commit();
	}
	
	public Car searchByName(String carname) {
		return session.selectOne("selectByName", carname);
	}
	
	public Integer getIdByName(String carname){
		return session.selectOne("getIdByName", carname);
	}
}
