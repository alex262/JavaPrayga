package Main;

import java.util.ArrayList;

public class Pryaga {

		String	Name;		// Ќазвание пр€жи
		String	Link;		// ссылка на страницу с данной пр€жей
		int 	Weight;		// ¬ес
		int 	Lenght;		// длина 
		String	Composition;// состав пр€жи
		int		packageCnt;	// кол-во в упаковке
		float 	priceBase;	// цена за моток на базе
		float	priceBasePackage; // цена за упаковку на базе
		float 	sellingPricePackage;// цена продажи упаковки конечна€
		float	profitPackage;// прибыль с пачки, надо учесть доставку	
		// список цветов, ссылки на картинки цветов, кол-во каждого цвета на базе

}
/*
url = new URL(links[i] + "jpg"); //‘ормирование url-адреса файла
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