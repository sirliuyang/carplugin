package service;

import org.apache.ibatis.session.SqlSessionFactory;

import dao.CarDao;
import entity.Car;

public class CarService {
	
	private static CarDao cardao;
	
	public CarService(SqlSessionFactory sqlSessionFactory){
		cardao = new CarDao(sqlSessionFactory);
	}
	
	public void saveCar(Car newcar){
		Car car = cardao.searchByName(newcar.getCarname());
		if(car != null){
			//System.out.println(car);
			if(car.getTotal_sales() < newcar.getTotal_sales())
				cardao.updateTotal(newcar);
		}else{
			cardao.insert(newcar);
		}
	}
	
}
