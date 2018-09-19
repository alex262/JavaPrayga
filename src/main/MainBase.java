package main;

import java.io.IOException;
//import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
public class MainBase{
	
	private static final String MAIN_URL = "http://www.terrakot18.ru/yarnlist";
	//private static final String ALL_LINKS = "a[_mce_href]";
	private static Elements all_tables;
	private static ArrayList<String> listProducer = new ArrayList<String>();	// список ссылок на всех производителей пряжи
	private static final float  InterestRate = (float) 30.0;					// процент прибыли
	///----------------------------------------------------------------------------------------
	/// БАЗА 
	private static ArrayList<ProducerPryag> listProducerPryag = new ArrayList<ProducerPryag>();
	///----------------------------------------------------------------------------------------
	
//	public static void main(String[] args) throws IOException	{
	public void CreateBase() throws IOException{
		
		
		Document jsDoc = null;
		try {
			jsDoc = Jsoup.connect(MAIN_URL).get();
		}catch(IOException e)
		{
			// сайт не доступен
			e.printStackTrace();
			return;
		}
		
		all_tables = jsDoc.select("table[class=mceItemTable]");
		
		for (Element table : all_tables){	// проходимся по всем таблицам
			Elements alllinks = table.select("a[_mce_href]");

	        getTranslationLinks(alllinks, listProducer);

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
		// вывод ссылок на страницы производителей
		//for (String tLink : listProducer) {
		//	System.out.println(tLink);// вывод ссылок на страницы производителей
	    //}
		//-----------------------------------------------------------
		//начинаем проход по производителям пряжи
		for(int i=0; i<listProducer.size(); i++)
		{
			//Element table;
			//Elements rows;
			
			//------------------------------------------
			// Формируем базу производителей
			//brand=XXXXX&
			int i1, i2;
			ProducerPryag Producer = new ProducerPryag();
			Producer.Link = listProducer.get(i);
			i1 = Producer.Link.indexOf('=');
			i2 = Producer.Link.indexOf('&');
			Producer.Name = Producer.Link.substring(i1+1, i2);
			Producer.UpdateBase(InterestRate); //формируем базу

			listProducerPryag.add(Producer);
		}
		//============================================================
/*      //-----------------------------------------------------------
		//Document doc = Jsoup.connect("http://www.terrakot18.ru/yarnlist").get();
		//File input = new File("C:\\temp\\terrakot18.htm");
		//Document doc = Jsoup.parse(input,"UTF-8");
		//Elements tables = doc.select("table[class=mceItemTable]");
   	}*/
		jsDoc = null;
	}
	 public static void getTranslationLinks(Elements alllinks, ArrayList<String> arrList){
	      String items;
	      for(int i = 0;i<alllinks.size();i++){
	          items = alllinks.get(i).attr("abs:_mce_href");// abs формирует абсолютный адрес а не относительный
	          arrList.add(items);
	      }
	  }
}
 