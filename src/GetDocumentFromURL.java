//package org.o7planning.tutorial.jsoup.document;

import java.io.IOException;
//import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

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

	        getTranslationLinks(alllinks, listProducer);

	        //for (String tLink : translationLinks) {
	        //   System.out.println(tLink);
	        //}
		}
		//-----------------------------------------------------------
		// теперь нужно проверить на дублирование ссылок listProducer
		for(int i=0; i<listProducer.size(); i++)
		{
		//listProducer.get(0);
			while( Collections.frequency(listProducer, listProducer.get(i))>1) // получим результат 2 сколько раз совпадают
			{
				listProducer.remove(i);
			}
		}
		//-----------------------------------------------------------
		// вывод списка
		for (String tLink : listProducer) {
			System.out.println(tLink);
	    }
		//-----------------------------------------------------------
		
/*      //-----------------------------------------------------------
		//Document doc = Jsoup.connect("http://www.terrakot18.ru/yarnlist").get();
		//File input = new File("C:\\temp\\terrakot18.htm");
		//Document doc = Jsoup.parse(input,"UTF-8");
		//Elements tables = doc.select("table[class=mceItemTable]");
   	}*/
 
	}
	 public static void getTranslationLinks(Elements alllinks, ArrayList<String> arrList){
	      String items;
	      for(int i = 0;i<alllinks.size();i++){
	          items = alllinks.get(i).attr("abs:_mce_href");// abs формирует абсолютный адрес а не относительный
	          arrList.add(items);
	      }
	  }
}
 