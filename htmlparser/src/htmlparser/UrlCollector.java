package htmlparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UrlCollector {
	private static String rootUrl = "http://www.515fa.com/";
	private static Set<String> hrefs = new HashSet<String>();
	private static List<String> urls = new ArrayList<String>();

	private static boolean getHrefs() {
		try {
			Document doc = Jsoup.connect(rootUrl)
					.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0")
					.timeout(20000).get();
			Elements tables = doc.select("table");
			for (Element table : tables) {
				Elements trs = table.select("tr");
				for (Element tr : trs) {
					Elements tds = tr.select("td");
					// System.out.println(td);
					String content = tds.text();
					if (content.matches(".*汽车销量排行.*")) {
						// System.out.println(tds);
						for (Element td : tds) {
							Elements links = td.select("a");
							for (Element link : links) {
								String linkcontent = link.text();
								String linkcontext = link.toString();
								if (linkcontent.matches(".*汽车销量排行.*")
										&& linkcontext.matches(".*class.*href.*target.*")) {
									String href = link.attr("href");
									hrefs.add(href);
									// System.out.println(href);
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	private static boolean assembleUrls(){
		System.out.println(hrefs);
		Iterator it = hrefs.iterator();
		while(it.hasNext()){
			String prefix = (String) it.next();
			int idx = prefix.indexOf("/");
			String tempUrl = rootUrl+prefix.substring(idx+1);
			urls.add(tempUrl);
		}
		System.out.println(urls);
		return true;
	}
	
	public static List<String> collectUrl() {
		getHrefs();
		assembleUrls();
		return urls;
	}

	public static void main(String[] args) {
		collectUrl();
	}

}
