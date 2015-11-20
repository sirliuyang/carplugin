package service;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import dao.CarDao;
import dao.SalesDao;
import entity.Sales;

public class SalesService {
	private static SalesDao salesdao;
	private static CarDao cardao;
	private static Logger logger = Logger.getLogger(SalesService.class);

	public SalesService(SqlSessionFactory sqlSessionFactory) {
		salesdao = new SalesDao(sqlSessionFactory);
		cardao = new CarDao(sqlSessionFactory);
	}

	public void saveSales(Sales newsales) {
		String carname = newsales.getCarname();
		if (cardao.getIdByName(carname) != null) {
			int id = cardao.getIdByName(carname);
			newsales.setId(id);
			Sales sales = salesdao.select(newsales);
			if (sales == null) {
				newsales.setId(id);
				salesdao.insert(newsales);
			} else {
				boolean info = sales.getCarname().equals(newsales.getCarname()) && 
						sales.getSale_num() == newsales.getSale_num();
				boolean month = sales.getMonth().toString().equals(newsales.getMonth().toString());
				logger.debug("month : " + month);
				logger.debug("sales.getMonth() : " + sales.getMonth());
				logger.debug("newsales.getMonth() : " + newsales.getMonth());
				if(!month){
					salesdao.insert(newsales);
				}else if(!info){
					salesdao.update(newsales);
				}
			}

			// System.out.println(sales);
		}
	}

}
