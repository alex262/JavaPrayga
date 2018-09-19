package main;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Pryaga {

		String	Name;		// �������� �����
		String	Link;		// ������ �� �������� � ������ ������
		String	LinkImage;	// ������ �� ����������� �����
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
			try {
				jsDoc = Jsoup.connect(Link).get();
			}catch(IOException e)
			{
				e.printStackTrace();
				return;
			}
			
			table = jsDoc.select("table[class=colors]").first();
			if(table != null) {
				
				Elements imgs = table.select("img");	// �������� ��� ���� <img>
				Elements ps = table.select("p");	// �������� ��� ���� <p>
				for (int i = 0; i < imgs.size(); i++) {
					ColorInfo iColor = new ColorInfo();
					iColor.LinkImage	= imgs.get(i).attr("abs:src"); // ������ �� ���� �����
					String str;
					iColor.Name= "";
					iColor.Id  = 0;
					
/*					-----------------------------------------------------------------
					 ��������� ������
					 -----------------
					 ��������
					 1. 10239, A-elita, 1, ������
					 2. ���������� 2454 ����������� �����
					 3. ����� �/� 4052
					 4. ����� �/� 4061 �����-�����-�����
					 5. ������������ ������ ���������
					 6. ������������ ������ ������ ������ 
					 7. ������ ������ 1331������
					 8. ������ ������ ������� 002
*//*				str			= imgs.get(i).attr("title");
					String[] fields = str.split(",\\s*|\\s");
					int n=fields.length;
					int nDigit = 0;		
					for(int j=0; j<n; j++) {
						if(StringUtils.isNumeric(fields[j])) nDigit++;
					}
					
					if(nDigit == 0) {// ver 5,6 ������� 
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
									iColor.Name = fields[n-1];// ��� �����
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
*/
//					iColor.Id	= Integer.parseInt(ps.get(i*2).text());	// ��� ���� ��������// �� ����� �� �� ���� ��������� ������������
//					String[] fields = str.split("\\s*(\\s|,)\\s*");
//					Pattern p = Pattern.compile("(\\d+)\\, (\\p{Alpha}+)\\, (\\d+)\\, (\\p{Alpha}+)");
//					Pattern p = Pattern.compile(",\\s");
//					Matcher m = p.matcher(str);
//					-----------------------------------------------------------------
					str 			= (ps.get(i*2+1).text()).replaceAll("\\D+", "");
					iColor.Ostatok	= Integer.parseInt(str);// ������� �������
					str 			= ps.get(i*2).text();
					if(StringUtils.isNumeric(str)){
						iColor.Id		= Integer.parseInt(str);// Id ����
					}
					//-----------------------------------------------------------------
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