//package org.o7planning.tutorial.jsoup.document;

import java.io.IOException;
//import java.io.File;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
public class GetDocumentFromURL{
	private static final String MAIN_URL = "http://www.terrakot18.ru/yarnlist";
	private static final String ALL_LINKS = "a[_mce_href]";
	private static Elements all_tables;
	private static ArrayList<String> listProducer = new ArrayList<String>(); // список ссылок на всех производителей пряжи
	public static void main(String[] args) throws IOException	{
		Document jsDoc = null;
		jsDoc = Jsoup.connect(MAIN_URL).get();
		all_tables = jsDoc.select("table[class=mceItemTable]");
		for (Element table : all_tables){	// проходимся по всем таблицам
			Elements alllinks = table.select(ALL_LINKS);

	        String[] translationLinks = getTranslationLinks(alllinks);

	        for (String tLink : translationLinks) {
	           System.out.println(tLink);
	        }
		}
		//-----------------------------------------------------------
		// теперь нужно проверить на дублирование ссылок listProducer
		//listProducer.get(0);
		//int count = Collections.frequency(cats, "Мурзик"); // получим результат 2 сколько раз совпадают
		//-----------------------------------------------------------
/*      //Document doc = Jsoup.connect("http://www.terrakot18.ru/yarnlist").get();
		//File input = new File("C:\\temp\\terrakot18.htm");
		//Document doc = Jsoup.parse(input,"UTF-8");
		//Elements tables = doc.select("table[class=mceItemTable]");
   	}*/
 
	}
	 public static String[] getTranslationLinks(Elements alllinks){
	      String[] items = new String[alllinks.size()];
	      Element tempelement;
	      for(int i = 0;i<items.length;i++){
	          tempelement = alllinks.get(i);

	          items[i] = tempelement.attr("abs:_mce_href");
	          listProducer.add(items[i]);
	      }
	      return items;
	  }
}
 