package Week02;

import java.io.FileNotFoundException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class kuir {

	public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, TransformerException {

		if(args[1].equals("-c"))
		{
			makeCollection mc = new makeCollection();
			try {
				String path = "";
				for(int i = 2; i < args.length; i++)
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
		else if(args[1].equals("-k"))
		{
			makeKeyword mk = new makeKeyword();
			String path = "";
			for(int i = 2; i < args.length; i++)
			{
				path += args[i] + " ";
			}
			mk.test(path);
		}

	}

}
