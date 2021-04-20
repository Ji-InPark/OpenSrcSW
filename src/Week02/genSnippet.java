package Week02;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class genSnippet {

	public static void main(String[] args) throws FileNotFoundException {
		
		if(args[0].equals("-f"))
		{
			String path = "";
			int i = 1;
			while(!args[i].contains("-q"))
			{
				path += args[i++] + " ";
			}
			
			ArrayList query = new ArrayList<String>();
			for(int j = i + 1; j < args.length; j++)
			{
				query.add(args[j]);
			}
			
			Scanner scan = new Scanner(new FileReader(path));
			
			
			String result = "";
			int result_index = 0;
			
			
			
			while(scan.hasNext())
			{
				String[] str = scan.nextLine().split(" ");
				int index = 0;
				
				for(int a = 0; a < query.size(); a++)
				{
					for(int b = 0; b < str.length; b++)
					{
						String temp = (String) query.get(a);
						if(str[b].equals(temp))
						{
							index++;
						}
					}
					/*
					if(str.contains(temp));
					{
						System.out.println(str);
						System.out.println(query.get(a));
						index++;
						System.out.println(index);
					}
					*/
				}
				
				//System.out.println(index);
				
				if(index > result_index)
				{
					result = "";
					for(int b = 0; b < str.length; b++)
					{
						result += str[b] + " ";
					}
					result_index = index;
				}
				
			}
			
			System.out.println(result);
		}

	}

}
