package main;

import java.io.IOException;
//import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
public class MainBase{
	
	private static final String MAIN_URL = "http://www.terrakot18.ru/yarnlist";
	//private static final String ALL_LINKS = "a[_mce_href]";
	private static Elements all_tables;
	private static ArrayList<String> listProducer = new ArrayList<String>();	// ������ ������ �� ���� �������������� �����
	private static final float  InterestRate = (float) 30.0;					// ������� �������
	///----------------------------------------------------------------------------------------
	/// ���� 
	private static ArrayList<ProducerPryag> listProducerPryag = new ArrayList<ProducerPryag>();
	///----------------------------------------------------------------------------------------
	
//	public static void main(String[] args) throws IOException	{
	public void CreateBase(JTable tableMain) throws IOException{
		
		
		Document jsDoc = null;
		try {
			jsDoc = Jsoup.connect(MAIN_URL).get();
		}catch(IOException e)
		{
			// ���� �� ��������
			e.printStackTrace();
			return;
		}
		
		all_tables = jsDoc.select("table[class=mceItemTable]");
		
		for (Element table : all_tables){	// ���������� �� ���� ��������
			Elements alllinks = table.select("a[_mce_href]");

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
		//for (String tLink : listProducer) {
		//	System.out.println(tLink);// ����� ������ �� �������� ��������������
	    //}
		//-----------------------------------------------------------
		//�������� ������ �� �������������� �����
		DefaultTableModel model = (DefaultTableModel) tableMain.getModel();
		int PosP=0, NumPak=0;
		for(int i=0; i<listProducer.size(); i++)
		{
			//Element table;
			//Elements rows;
			
			//------------------------------------------
			// ��������� ���� ��������������
			//brand=XXXXX&
			int i1, i2;
			ProducerPryag Producer = new ProducerPryag();
			Producer.Link = listProducer.get(i);
			i1 = Producer.Link.indexOf('=');
			i2 = Producer.Link.indexOf('&');
			Producer.Name = Producer.Link.substring(i1+1, i2);
			Producer.UpdateBase(InterestRate); //��������� ����

			listProducerPryag.add(Producer);
			for(int j=0; j<Producer.listPryaga.size(); j++)
			{
				for(int k=0; k<Producer.listPryaga.get(j).listColorsAvail.size(); k++)
				{
					Vector <String> v = new Vector<String>(Producer.listPryaga.size());
					v.add(Integer.toString(PosP++));
					v.add(Producer.Name);
					v.add(Producer.listPryaga.get(j).Name);
					v.add(Integer.toString(Producer.listPryaga.get(j).listColorsAvail.get(k).Id));
					v.add(Integer.toString(Producer.listPryaga.get(j).listColorsAvail.get(k).Ostatok));
					NumPak+=Producer.listPryaga.get(j).listColorsAvail.get(k).Ostatok;
					v.add(Float.toString(Producer.listPryaga.get(j).priceBasePackage));
					
					model.addRow(v);
				}
			}
		}
		Vector <String> v = new Vector<String>(6);
		v.add("");
		v.add("");
		v.add("");
		v.add("");
		v.add(Integer.toString(NumPak));
		v.add("");
		
		model.addRow(v);
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
	          items = alllinks.get(i).attr("abs:_mce_href");// abs ��������� ���������� ����� � �� �������������
	          arrList.add(items);
	      }
	  }
}
 