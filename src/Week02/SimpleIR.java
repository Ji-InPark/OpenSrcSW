package Week02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SimpleIR {

	public static void main(String[] args) throws ParserConfigurationException, FileNotFoundException, TransformerException{
		File dir = new File("C:/Users/kapsu/Documents/대학 관련 자료/강의관련자료/2학년 1학기/오픈소스SW입문/강의자료/2주차 실습 html/2주차 실습 html");
		File[] files = dir.listFiles();
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		Document docFile = docBuilder.newDocument();
		
		int id = 0;
		Element docs_tag = docFile.createElement("docs");
		docFile.appendChild(docs_tag);
		
		for(int i = 0; i < files.length; i++)
		{
			if(files[i].getName().contains(".html"))
			{
				Scanner scan = new Scanner(new FileReader("C:/Users/kapsu/Documents/대학 관련 자료/강의관련자료/2학년 1학기/오픈소스SW입문/강의자료/2주차 실습 html/2주차 실습 html/" + files[i].getName()));
				BufferedReader br = null;
				
				String title = "", body = "", str_id = Integer.toString(id++);
				while(scan.hasNext())
				{
					String str = scan.nextLine();
					if(str != null)
					{							
						if(str.contains("<title>"))
						{
							str = str.replace("<title>", "");
							str = str.replace("</title>", "");
							title = str.trim();
						}
						else if(str.contains("<p>"))
						{
							str = str.replace("<p>", "");
							str = str.replace("</p>", "");
							body += str.trim() + " ";
						}
					}
				}
				Element doc_tag = docFile.createElement("doc");
				docs_tag.appendChild(doc_tag);
				
				doc_tag.setAttribute("id", str_id);
				
				Element title_tag = docFile.createElement("title");
				title_tag.appendChild(docFile.createTextNode(title));
				doc_tag.appendChild(title_tag);
				
				Element body_tag = docFile.createElement("Body");
				body_tag.appendChild(docFile.createTextNode(body));
				doc_tag.appendChild(body_tag);
			}
		}
		
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		
		DOMSource source = new DOMSource(docFile);
		StreamResult result = new StreamResult(new FileOutputStream(new File("C:/Users/kapsu/Documents/대학 관련 자료/강의관련자료/2학년 1학기/오픈소스SW입문/과제/SimpleIR/book.xml")));
		
		tf.transform(source, result);
	}

}
