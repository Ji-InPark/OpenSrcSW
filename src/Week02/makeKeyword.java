package Week02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class makeKeyword {
	public static void test(String path) throws FileNotFoundException, ParserConfigurationException, TransformerException
	{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		Document docFile = docBuilder.newDocument();
		
		int id = 0;
		Element docs_tag = docFile.createElement("docs");
		docFile.appendChild(docs_tag);

		Scanner scan = new Scanner(new FileReader(path));
		String str = "";
		while(scan.hasNext() && !str.contains("</docs>"))
		{
			String title = "", body_temp = "", body = "", str_id = Integer.toString(id++);
			while(!str.contains("</doc>")) 
			{
				if(str != null)
				{							
					if(str.contains("<title>"))
					{
						str = str.replace("<title>", "");
						str = str.replace("</title>", "");
						title = str.trim();
					}
					else if(str.contains("<body>"))
					{
						str = str.replace("<body>", "");
						str = str.replace("</body>", "");
						body_temp = str.trim();
						
						KeywordExtractor ke = new KeywordExtractor();
						KeywordList k1 = ke.extractKeyword(body_temp, true);
						
						for(int i = 0; i < k1.size(); i++)
						{
							Keyword kwrd = k1.get(i);
							body += kwrd.getString() + ":" + kwrd.getCnt() + "#";
						}		
					}
				}
				str = scan.nextLine();
			}
						
			Element doc_tag = docFile.createElement("doc");
			docs_tag.appendChild(doc_tag);
			
			doc_tag.setAttribute("id", str_id);
			
			Element title_tag = docFile.createElement("title");
			title_tag.appendChild(docFile.createTextNode(title));
			doc_tag.appendChild(title_tag);
			
			Element body_tag = docFile.createElement("body");
			body_tag.appendChild(docFile.createTextNode(body));
			doc_tag.appendChild(body_tag);
			
			str = scan.nextLine();
		}
		
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		
		path = path.replace("collection.xml", "index.xml");
		
		DOMSource source = new DOMSource(docFile);
		StreamResult result = new StreamResult(new FileOutputStream(new File(path)));
		
		tf.transform(source, result);	
	}
}
