package util;


import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.CarDao;
import dao.PriceDao;
import dao.SalesDao;
import entity.Car;
import entity.Price;
import entity.Sales;
import htmlparser.HtmlParser;
import service.CarService;

public class FromHtmlToDB {
	HtmlParser parser = new HtmlParser();
	
	private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader; 
	
	static{
		try {
			reader = Resources.getResourceAsReader("mybatis.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		CarDao cardao = new CarDao(sqlSessionFactory);
		PriceDao pricedao = new PriceDao(sqlSessionFactory);
		SalesDao salesdao = new SalesDao(sqlSessionFactory);
		CarService carservice = new CarService(sqlSessionFactory);
		//List<Price> pricelist = pricedao.selectAll();
		
		boolean network = true;
		
		if(network){
			
			HtmlParser htmlparser = new HtmlParser();
			htmlparser.parseHTML();
			List<Car> carlist = new ArrayList<Car>();
			List<Sales> saleslist = new ArrayList<Sales>();
			carlist = htmlparser.getCarList();
			saleslist = htmlparser.getSalesList();
			
			for(Car car : carlist){
				//System.out.println(car);
				carservice.saveCar(car);
			}
			/*
			for(Sales sales : saleslist){
				String carname = sales.getCarname();
				if(cardao.getIdByName(carname) != null){
					int id = cardao.getIdByName(carname);
					sales.setId(id);
					salesdao.insert(sales);
					//System.out.println(sales);
				}
			}
			*/
		}else{
			Car car= cardao.searchByName("英朗");
			System.out.println(car);
		}
		/*
		for(Price price : pricelist){
			System.out.println(price);
		}
		*/
	}
}