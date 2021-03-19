package Week02;

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;

public class makeKeyword {
	public static void test(String str)
	{
		KeywordExtractor ke = new KeywordExtractor();
		KeywordList k1 = ke.extractKeyword(str, true);
		
		for(int i = 0; i < k1.size(); i++)
		{
			Keyword kwrd = k1.get(i);
			System.out.println(kwrd.getString() + "\t" + kwrd.getCnt());
		}
	}
}
