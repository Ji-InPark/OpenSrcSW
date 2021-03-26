package Week02;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.w3c.dom.Element;


public class indexer {
	void makePost(String path) throws IOException
	{
		Scanner scan = new Scanner(new FileReader(path));
		String str = "";
		
		ArrayList<String[][]> word_list = new ArrayList<String[][]>();
		
		
		
		while(scan.hasNext() && !str.contains("</docs>"))
		{
			String body_temp = "";
			while(!str.contains("</doc>")) 
			{
				if(str != null)
				{							
					if(str.contains("<body>"))
					{
						str = str.replace("<body>", "");
						str = str.replace("</body>", "");
						body_temp = str.trim();		
						
						String[] temp_string = body_temp.split("#");
						String[][] result_array = new String[temp_string.length][2];
						for(int i = 0; i < temp_string.length; i++)
						{
							result_array[i] = temp_string[i].split(":");
						}
						word_list.add(result_array);
					}
				}
				str = scan.nextLine();
			}
			str = scan.nextLine();
		}
		
		int[] total_word_num = new int[word_list.size()];
		
		for(int i = 0; i < word_list.size(); i++)
		{
			for(int j = 0; j < word_list.get(i).length; j++)
			{
				total_word_num[i] += Integer.parseInt(word_list.get(i)[j][1]);
			}
		}
		
		HashMap<String, ArrayList <Double>> map = new HashMap<String, ArrayList <Double>>();
		
		for(int i = 0; i < word_list.size(); i++)
		{
			for(int j = 0; j < word_list.get(i).length; j++)
			{			
				if(map.containsKey(word_list.get(i)[j][0]))
				{
					continue;
				}
				int word_fre = 1;
				int[] file_num = new int[word_list.size()], word_index = new int[word_list.size()];
				file_num[0] = i;
				word_index[0] = j;
				for(int a = i + 1; a < word_list.size(); a++)
				{
					for(int b = 0; b < word_list.get(a).length; b++)
					{
						if(word_list.get(i)[j][0].equals(word_list.get(a)[b][0]))
						{
							file_num[word_fre] = a;
							word_index[word_fre] = b;
							word_fre++;
							break;
						}
					}
				}
				ArrayList <Double> value_list = new ArrayList<Double>(); 
				double log = Math.log((double)word_list.size() / (double)word_fre);
				for(int a = 0; a < word_fre; a++)
				{
					double tf = Double.parseDouble(word_list.get(file_num[a])[word_index[a]][1]);
					double result = tf * log;
					
					value_list.add((double) file_num[a]);
					value_list.add(Math.round(result*100)/100.0);
				}
				
				map.put(word_list.get(i)[j][0], value_list);
			}
		}
		
		path = path.replace(".xml", ".post");
		FileOutputStream fs = new FileOutputStream(path);
		
		ObjectOutputStream oos = new ObjectOutputStream(fs);
		
		oos.writeObject(map);
		
		oos.close();
		
		
	}
}
