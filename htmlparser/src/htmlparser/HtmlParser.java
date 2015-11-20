package htmlparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import entity.Car;
import entity.Price;
import entity.Sales;
import util.DateTransfer;

public class HtmlParser {

	private static List<Car> carlist = new ArrayList<Car>();
	private static List<Price> pricelist = new ArrayList<Price>();
	private static List<Sales> saleslist = new ArrayList<Sales>();

	private static String URL = "";
	// "http://www.515ha.com/xiaoliang/1341.html";
	private static String regex = ".*FFFFFF.*";

	public static void printAll(Elements trs) {
		for (Element tr : trs) {
			Elements tds = tr.select("td");
			for (int i = 0; i < tds.size(); i++) {
				System.out.println(i + ", " + tds.get(i).text());
			}
		}
	}
	
	public HtmlParser(String Url){
		URL = Url;
	}

	public static void saveToDao(Elements trs) {
		String temp_car_info;
		String month = "";

		for (Element tr : trs) {
			Elements tds = tr.select("td");
			Car car = new Car();
			Price price = new Price();
			Sales sales = new Sales();
			for (int i = 0; i < tds.size(); i++) {
				temp_car_info = tds.get(i).text();
				if (temp_car_info.matches(".*轿车销量排行.*")) {
					month = temp_car_info.substring(0, 8);
					break;
					// System.out.println(month);
				} else if (i == 0 && !temp_car_info.matches("^-?\\d+$")) {
					break;
				} else {
					price.setMonth(month);
					sales.setMonth(DateTransfer.parserToDate(month));
					switch (i) {
					case 1:
						car.setCarname(temp_car_info);
						sales.setCarname(temp_car_info);
						break;
					case 2:
						car.setCarcompany(temp_car_info);
						break;
					case 3:
						car.setCarbrand(temp_car_info);
						break;
					case 4:
						sales.setSale_num(Integer.parseInt(temp_car_info));
						break;
					case 5:
						car.setTotal_sales(Integer.parseInt(temp_car_info));
						break;
					// default: ;
					}
					// System.out.println(i+" "+temp_car_info);
				}
			}

			// saveToDao
			if (car.getCarbrand() != null) {
				carlist.add(car);
			}
			if (sales.getMonth() != null) {
				saleslist.add(sales);
			}
			if (price.getPrice() != 0) {
				pricelist.add(price);
			}

		}
	}

	public static Elements getTrs() {
		Elements trs = null;
		try {
			Document doc = Jsoup.connect(URL)
					.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0")
					.timeout(20000).get();
			Elements contents = doc.select("table[width=680]");
			trs = contents.get(0).select("tr");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return trs;
	}

	public boolean parseHTML() {
		Elements trs = getTrs();
		// printAll(trs);
		saveToDao(trs);
		return true;
	}

	public List<Car> getCarList() {
		return carlist;
	}

	public List<Price> getPriceList() {
		return pricelist;
	}

	public List<Sales> getSalesList() {
		return saleslist;
	}
}
