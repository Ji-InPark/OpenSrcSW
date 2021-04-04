package Week02;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class kuir {

	public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, ClassNotFoundException {

		if(args[0].equals("-c"))
		{
			makeCollection mc = new makeCollection();
			try {
				String path = "";
				for(int i = 1; i < args.length; i++)
				{
					path += args[i] + " ";
				}
				mc.makeCol(path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		}
		else if(args[0].equals("-k"))
		{
			makeKeyword mk = new makeKeyword();
			String path = "";
			for(int i = 1; i < args.length; i++)
			{
				path += args[i] + " ";
			}
			mk.test(path);
		}
		else if(args[0].equals("-i"))
		{
			indexer idx = new indexer();
			String path = "";
			for(int i = 1; i < args.length; i++)
			{
				path += args[i] + " ";
			}
			idx.makePost(path);
		}
		else if(args[0].equals("-s"))
		{
			String path = "";
			int i = 1;
			while(!args[i].contains("-"))
			{
				path += args[i++] + " ";
			}
			
			String query = "";
			for(int j = i + 1; j < args.length; j++)
			{
				query += args[j] + " ";
			}
			
			System.out.println(path);
			System.out.println(query);
			
			searcher sc = new searcher();
			sc.search(path, query);
		}

	}

}
