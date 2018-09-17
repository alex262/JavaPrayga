package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Pryaga {

		String	Name;		// Название пряжи
		String	Link;		// ссылка на страницу с данной пряжей
		int 	Weight;		// Вес
		int 	Lenght;		// длина 
		String	Composition;// состав пряжи
		int		packageCnt;	// кол-во в упаковке
		float 	priceBase;	// цена за моток на базе
		float	priceBasePackage; // цена за упаковку на базе
		float 	sellingPricePackage;// цена продажи упаковки конечная
		float	profitPackage;// прибыль с пачки, надо учесть доставку	
		// список цветов, ссылки на картинки цветов, кол-во каждого цвета на базе
		ArrayList<ColorInfo>	listColorsAvail = new ArrayList<ColorInfo>(); // список доступных цветов данной пряжи
		
		public void UpdateBaseColors() throws IOException{
			Document jsDoc = null;
			Element table;
			
			listColorsAvail.clear(); // очищаем список если он не пуст
			jsDoc = Jsoup.connect(Link).get();
			
			table = jsDoc.select("table[class=colors]").first();
			if(table != null) {
				
				Elements imgs = table.select("img");	// получаем все теги <img>
				Elements ps = table.select("p");	// получаем все теги <p>
				for (int i = 0; i < imgs.size(); i++) {
					ColorInfo iColor = new ColorInfo();
					iColor.LinkImage	= imgs.get(i).attr("abs:src"); // ссылка на фото цвета
					String str			= imgs.get(i).attr("title");
					//Pattern p = Pattern.compile("(\\d+)\\, (\\p{Alpha}+)\\, (\\d+)\\, (\\p{Alpha}+)");
					Pattern p = Pattern.compile(",\\s");
					String[] fields = p.split(str);
					//Matcher m = p.matcher(str);
					int n=fields.length;
					iColor.Name = fields[n-1];//3
					//iColor.Id	= Integer.parseInt(ps.get(i*2).text());	// так тоже работает// ни хрена не на всех страницах присутствует
					iColor.Id		= Integer.parseInt(fields[n-2]);//2
					str = (ps.get(i*2+1).text()).replaceAll("\\D+", "");
					iColor.Ostatok	= Integer.parseInt(str);		// достаем остаток
					listColorsAvail.add(iColor);	
				}
			}
		}
		

}
/*
url = new URL(links[i] + "jpg"); //Формирование url-адреса файла
conn = url.openConnection();
conn.setDoOutput(true);
conn.setDoInput(true);

BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
FileOutputStream fos = new FileOutputStream(new File(pathToSave + naming(i+1)));
	
int ch;
while ((ch = bis.read())!=-1) 
{
	fos.write(ch);
}
bis.close();
fos.flush();
fos.close();
*/