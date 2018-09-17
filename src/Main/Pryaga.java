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

		String	Name;		// �������� �����
		String	Link;		// ������ �� �������� � ������ ������
		int 	Weight;		// ���
		int 	Lenght;		// ����� 
		String	Composition;// ������ �����
		int		packageCnt;	// ���-�� � ��������
		float 	priceBase;	// ���� �� ����� �� ����
		float	priceBasePackage; // ���� �� �������� �� ����
		float 	sellingPricePackage;// ���� ������� �������� ��������
		float	profitPackage;// ������� � �����, ���� ������ ��������	
		// ������ ������, ������ �� �������� ������, ���-�� ������� ����� �� ����
		ArrayList<ColorInfo>	listColorsAvail = new ArrayList<ColorInfo>(); // ������ ��������� ������ ������ �����
		
		public void UpdateBaseColors() throws IOException{
			Document jsDoc = null;
			Element table;
			
			listColorsAvail.clear(); // ������� ������ ���� �� �� ����
			jsDoc = Jsoup.connect(Link).get();
			
			table = jsDoc.select("table[class=colors]").first();
			if(table != null) {
				
				Elements imgs = table.select("img");	// �������� ��� ���� <img>
				Elements ps = table.select("p");	// �������� ��� ���� <p>
				for (int i = 0; i < imgs.size(); i++) {
					ColorInfo iColor = new ColorInfo();
					iColor.LinkImage	= imgs.get(i).attr("abs:src"); // ������ �� ���� �����
					String str			= imgs.get(i).attr("title");
					//Pattern p = Pattern.compile("(\\d+)\\, (\\p{Alpha}+)\\, (\\d+)\\, (\\p{Alpha}+)");
					Pattern p = Pattern.compile(",\\s");
					String[] fields = p.split(str);
					//Matcher m = p.matcher(str);
					int n=fields.length;
					iColor.Name = fields[n-1];//3
					//iColor.Id	= Integer.parseInt(ps.get(i*2).text());	// ��� ���� ��������// �� ����� �� �� ���� ��������� ������������
					iColor.Id		= Integer.parseInt(fields[n-2]);//2
					str = (ps.get(i*2+1).text()).replaceAll("\\D+", "");
					iColor.Ostatok	= Integer.parseInt(str);		// ������� �������
					listColorsAvail.add(iColor);	
				}
			}
		}
		

}
/*
url = new URL(links[i] + "jpg"); //������������ url-������ �����
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