package Week02;

import java.io.FileNotFoundException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class kuir {

	public static void main(String[] args) {
		
		if(args[1].equals("-c"))
		{
			makeCollection mc = new makeCollection();
			try {
				mc.makeCol(args[2]);
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
			mk.test("꼬꼬마형태소분석기를테스트하고있어요.테스트결과를볼게요.");
		}

	}

}
