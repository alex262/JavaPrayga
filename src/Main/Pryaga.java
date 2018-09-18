package Main;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Pryaga {

		String	Name;		// Название пряжи
		String	Link;		// ссылка на страницу с данной пряжей
		String	LinkImage;	// ссылка на изображение мотка
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
			try {
				jsDoc = Jsoup.connect(Link).get();
			}catch(IOException e)
			{
				e.printStackTrace();
				return;
			}
			
			table = jsDoc.select("table[class=colors]").first();
			if(table != null) {
				
				Elements imgs = table.select("img");	// получаем все теги <img>
				Elements ps = table.select("p");	// получаем все теги <p>
				for (int i = 0; i < imgs.size(); i++) {
					ColorInfo iColor = new ColorInfo();
					iColor.LinkImage	= imgs.get(i).attr("abs:src"); // ссылка на фото цвета
					
					//-----------------------------------------------------------------
					// идиотская строка
					// -----------------
					// варианты
					// 1. 10239, A-elita, 1, чёрный
					// 2. Деревенька 2454 натуральный тёмный
					// 3. Кроха С/К 4052
					// 4. Кроха С/К 4061 белый-жёлтый-салат
					// 5. Аргентинская Шерсть жемчужный
					// 6. Аргентинская Шерсть коралл темный 
					// 7. Чистая Шерсть 1331золото
					// 8. Бамбук Стрейч отбелка 002
					
					String str			= imgs.get(i).attr("title");
					String[] fields = str.split(",\\s*|\\s");
					int n=fields.length;
					int nDigit = 0;		
					iColor.Name= "";
					iColor.Id  = 0;
					for(int j=0; j<n; j++) {
						if(StringUtils.isNumeric(fields[j])) nDigit++;
					}
					
					if(nDigit == 0) {// ver 5,6 неверно 
						for(int j=1; j<n; j++) {
							iColor.Name+=fields[j]+' ';
						}	
					}else
					{
						if(n == 3) {//ver 3
							if(StringUtils.isNumeric(fields[n-1]))
							{
								iColor.Id	= Integer.parseInt(fields[n-1]);
								iColor.Name = " ";
							}
								
						}else{
							if(StringUtils.isNumeric(fields[n-2])) {
									iColor.Id	= Integer.parseInt(fields[n-2]);
									iColor.Name = fields[n-1];// имя цвета
							}else {
								if(StringUtils.isNumeric(fields[n-3])){
									iColor.Name = fields[n-2]+' '+fields[n-1];
									iColor.Id	= Integer.parseInt(fields[n-3]);
								}else{
									if(StringUtils.isNumeric(fields[n-4])){
										iColor.Name = fields[n-3]+' '+fields[n-2]+' '+fields[n-1];
										iColor.Id	= Integer.parseInt(fields[n-4]);	
									}else
									{
										iColor.Name = fields[n-4]+' '+fields[n-3]+' '+fields[n-2]+' '+fields[n-1];
										iColor.Id	= Integer.parseInt(fields[n-5]);
									}
								}
							}
						}
					}
					//iColor.Id	= Integer.parseInt(ps.get(i*2).text());	// так тоже работает// ни хрена не на всех страницах присутствует
					//String[] fields = str.split("\\s*(\\s|,)\\s*");
					//Pattern p = Pattern.compile("(\\d+)\\, (\\p{Alpha}+)\\, (\\d+)\\, (\\p{Alpha}+)");
					//Pattern p = Pattern.compile(",\\s");
					//Matcher m = p.matcher(str);
					//-----------------------------------------------------------------
					str = (ps.get(i*2+1).text()).replaceAll("\\D+", "");
					iColor.Ostatok	= Integer.parseInt(str);		// достаем остаток
					//-----------------------------------------------------------------
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