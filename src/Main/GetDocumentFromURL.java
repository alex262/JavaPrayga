package Main;

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
	private static ArrayList<String> listProducer = new ArrayList<String>(); // ������ ������ �� ���� �������������� �����
	private static final float  InterestRate = (float) 30.0;
	///----------------------------------------------------------------------------------------
	/// ���� 
	private static ArrayList<ProducerPryag> listProducerPryag = new ArrayList<ProducerPryag>();
	///----------------------------------------------------------------------------------------
	
	public static void main(String[] args) throws IOException	{
		
		Document jsDoc = null;
		jsDoc = Jsoup.connect(MAIN_URL).get();
		all_tables = jsDoc.select("table[class=mceItemTable]");
		
		for (Element table : all_tables){	// ���������� �� ���� ��������
			Elements alllinks = table.select(ALL_LINKS);

	        getTranslationLinks(alllinks, listProducer);

		}
		//-----------------------------------------------------------
		// ������ ����� ��������� �� ������������ ������ listProducer
		for(int i=0; i<listProducer.size(); i++)
		{
		//listProducer.get(0);
			while( Collections.frequency(listProducer, listProducer.get(i))>1) // ������� ��������� 2 ������� ��� ���������
			{
				listProducer.remove(i);
			}
		}
		//-----------------------------------------------------------
		// ����� ������ �� �������� ��������������
		for (String tLink : listProducer) {
			System.out.println(tLink);// ����� ������ �� �������� ��������������
	    }
		//-----------------------------------------------------------
		//�������� ������ �� �������������� �����
		for(int i=0; i<listProducer.size(); i++)
		{
			Element table;
			Elements rows;
			
			//------------------------------------------
			// ��������� ���� ��������������
			ProducerPryag Producer = new ProducerPryag();
			Producer.Link = listProducer.get(i);
			//Producer.Name
			listProducerPryag.add(Producer);
			//------------------------------------------
			jsDoc = Jsoup.connect(listProducer.get(i)).get();
			table = jsDoc.select("table[class=query]").first();
			rows = table.select("tr");
			System.out.println(listProducer.get(i));
			for (int j = 0; j < rows.size(); j++) {
	            Element row = rows.get(j); 			//�� ������ ������� �������� ������
	            Elements cols = row.select("td");	// ��������� ���������� ������ �� ����  �� ������
	            String[] strCol = new String[cols.size()];
				for (int k = 0; k < cols.size(); k++) {
					//������ ������
					strCol[k] = cols.get(k).text();
					
					System.out.print(strCol[k]+' ');// ����� � ������� n-�� �������
				}
				if(j>0) { // � ������ ������ �������� �������� 
					Pryaga iPryaga = new Pryaga();
					iPryaga.Name		= strCol[0];
					iPryaga.Weight		= Integer.parseInt(strCol[1]);	 
					iPryaga.Lenght		= Integer.parseInt(strCol[2]);	 
					iPryaga.Composition	= strCol[3];	 
					iPryaga.packageCnt	= Integer.parseInt(strCol[4]);	 
					iPryaga.priceBase	= Float.parseFloat(strCol[5]);
					iPryaga.priceBasePackage= Float.parseFloat(strCol[6]);
					
					iPryaga.sellingPricePackage= (float) Math.ceil(iPryaga.priceBasePackage*((float)1.0+InterestRate/(float)100.0));
					iPryaga.profitPackage = iPryaga.sellingPricePackage - iPryaga.priceBasePackage;
					listProducerPryag.get(i).listPryaga.add(iPryaga); // �������� ����� � ����
				}
				strCol = null;
	            System.out.println();
	        }	
		}		
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
	          items = alllinks.get(i).attr("abs:_mce_href");// abs ��������� ���������� ����� � �� �������������
	          arrList.add(items);
	      }
	  }
}
 