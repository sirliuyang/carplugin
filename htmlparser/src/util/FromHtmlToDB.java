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
import htmlparser.UrlCollector;
import service.CarService;
import service.SalesService;

public class FromHtmlToDB {
	
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
		List<String> urls = UrlCollector.collectUrl();
		for(String url : urls){
			HtmlParser htmlparser = new HtmlParser(url);

			CarService carservice = new CarService(sqlSessionFactory);
			SalesService salesservice = new SalesService(sqlSessionFactory);
			//List<Price> pricelist = pricedao.selectAll();
			boolean network = true;
			
			if(network){	
				htmlparser.parseHTML();
				List<Car> carlist = new ArrayList<Car>();
				List<Sales> saleslist = new ArrayList<Sales>();
				carlist = htmlparser.getCarList();
				saleslist = htmlparser.getSalesList();
				
				for(Car car : carlist){
					//System.out.println(car);
					carservice.saveCar(car);
				}
				
				for(Sales sales : saleslist){
					salesservice.saveSales(sales);
				}
			}else{
			}
		}
	}
}
