package Main;

import java.util.ArrayList;

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