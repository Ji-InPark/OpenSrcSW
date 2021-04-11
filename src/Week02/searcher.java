package Week02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.w3c.dom.Element;

public class searcher {
	
	public static void CalcSim(String path, String query) throws IOException, ClassNotFoundException
	{
		FileInputStream fs = new FileInputStream(path);
		ObjectInputStream ois = new ObjectInputStream(fs);
		
		Object obj = ois.readObject();
		ois.close();
		
		Scanner scan = new Scanner(new FileReader(path.replace("index.post", "collection.xml")));
		String str = "";
		ArrayList<String> title_list = new ArrayList<String>();
		while(scan.hasNext() && !str.contains("</docs>"))
		{
			String title = "";
			while(!str.contains("</doc>")) 
			{
				if(str != null)
				{							
					if(str.contains("<title>"))
					{
						str = str.replace("<title>", "");
						str = str.replace("</title>", "");
						title = str.trim();
						
						title_list.add(title);
					}
				}
				str = scan.nextLine();
			}
			str = scan.nextLine();
		}
		
		double[] id_result = new double[title_list.size()];
		Arrays.fill(id_result, 0.0);
		
		HashMap<String, ArrayList <Double>> map = (HashMap<String, ArrayList <Double>>)obj;
		
		KeywordExtractor ke = new KeywordExtractor();
		KeywordList k1 = ke.extractKeyword(query, true);
		
		for(int i = 0; i < k1.size(); i++)
		{
			Keyword kwrd = k1.get(i);
			ArrayList <Double> temp_array = (ArrayList <Double>) map.get(kwrd.getString());
			for(int j = 0; j < temp_array.size(); j += 2)
			{
				id_result[Integer.parseInt(String.valueOf(Math.round(temp_array.get(j))))] += (double)kwrd.getCnt() * temp_array.get(j + 1);
			}
		}
		
		for(int i = 0; i < 3; i++)
		{
			int max_index = 0;
			for(int j = 0; j < id_result.length; j++)
			{
				if(id_result[j] > id_result[max_index])
				{
					max_index = j;
				}
			}
			
			System.out.printf("문서 title: %s - 문서의 유사도: %.2f\n", title_list.get(max_index), id_result[max_index]);
			id_result[max_index] = -1;
		}
	}
}
