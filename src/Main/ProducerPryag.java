package Main;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProducerPryag {
	
	String Name;	// имя производителя пряжи
	String Link;	// ссылка на страницу с пряжей данного производителя
	// ссылка на картинку
	ArrayList<Pryaga>	listPryaga = new ArrayList<Pryaga>(); // список пряжи у производителя
	
	public void UpdateBase(float Rate) throws IOException{
		Document jsDoc = null;
		Element table;
		Elements rows;
		
		listPryaga.clear(); // очищаем если повторный вызов
		
		jsDoc = Jsoup.connect(Link).get();
		table = jsDoc.select("table[class=query]").first();
		//-------------------------------------------------
		// вытаскиваемм ссылки на каждую пряжу
		Elements alllinks = table.select("a[href]");
		//-------------------------------------------------
		rows = table.select("tr");
		System.out.println(Link);
		for (int j = 0; j < rows.size(); j++) {
            Element row = rows.get(j); 			//по номеру индекса получает строку
            Elements cols = row.select("td");	// разбиваем полученную строку по тегу  на столбы
            String[] strCol = new String[cols.size()];
			for (int k = 0; k < cols.size(); k++) {
				//парсим строку
				strCol[k] = cols.get(k).text();
				
				//System.out.print(strCol[k]+' ');// вывод в консоль n-ый столбец
			}
			if(j>0) { // в первой строке названия столбцов 
				Pryaga iPryaga = new Pryaga();
				iPryaga.Link		= alllinks.get(j-1).attr("abs:href");
				iPryaga.Name		= strCol[0];
				iPryaga.Weight		= Integer.parseInt(strCol[1]);	 
				iPryaga.Lenght		= Integer.parseInt(strCol[2]);	 
				iPryaga.Composition	= strCol[3];	 
				iPryaga.packageCnt	= Integer.parseInt(strCol[4]);	 
				iPryaga.priceBase	= Float.parseFloat(strCol[5]);
				iPryaga.priceBasePackage= Float.parseFloat(strCol[6]);
				
				iPryaga.sellingPricePackage= (float) Math.ceil(iPryaga.priceBasePackage*((float)1.0+Rate/(float)100.0));
				iPryaga.profitPackage = iPryaga.sellingPricePackage - iPryaga.priceBasePackage;
				
				iPryaga.UpdateBaseColors();
				
				listPryaga.add(iPryaga); // добавили пряжу в базу
				
				System.out.format("%-40s %-3d %-3d %-60s %-2d %8.2f %8.2f %8.2f %4.0f", iPryaga.Name, iPryaga.Weight, iPryaga.Lenght, iPryaga.Composition, iPryaga.packageCnt, iPryaga.priceBase, iPryaga.priceBasePackage, iPryaga.sellingPricePackage, iPryaga.profitPackage);
			}
			strCol = null;
            System.out.println();
        }	
	}		
}
