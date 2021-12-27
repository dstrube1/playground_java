
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
Build:
javac -cp bin:bin/jsoup-1.14.3.jar -d bin Scrape3WordPhrase.java
Run:
java -cp bin:bin/jsoup-1.14.3.jar Scrape3WordPhrase

Results as of 2021-12-21:
Unexpected next found at page 144: supertiny.htm
Unexpected prev found at page 147: flattered.htm
Unexpected next found at page 160: thanksgiving.htm
At page 161: subdue.htm: caught exception: HTTP error fetching URL. Status=404, URL=[http://threewordphrase.com/subdue.htm]
Unexpected prev found at page 161: subdue.htm
Unexpected next found at page 161: subdue.htm
Unexpected prev found at page 162: beatit.htm
Unexpected next found at page 162: beatit.htm
At page 163: whoaman.htm: caught exception: HTTP error fetching URL. Status=404, URL=[http://threewordphrase.com/whoaman.htm]
Unexpected prev found at page 163: whoaman.htm
Unexpected next found at page 163: whoaman.htm
Unexpected prev found at page 164: nautical.htm
Unexpected next found at page 205: wish.htm
Unexpected prev found at page 207: heaven.htm
Unexpected next found at page 213: meditation.htm
Unexpected next found at page 214: riverscene.htm
Unexpected prev found at page 215: fluffer.htm

*/
public class Scrape3WordPhrase{

	private static final List<String> hyperLinks = new ArrayList<>();
	private static final Map<String,String> map = new HashMap<>();
	private static final List<Page> pages = new ArrayList<>();

	public static void main(String[] args) throws IOException{
		final String root = "http://threewordphrase.com/";

		//Put all links in the archive into a list
		Document archive = Jsoup.connect(root + "archive.htm").get();
		Elements pageElements = archive.select("a[href]");
		for (Element e:pageElements) {
			if(e.text().length() > 0){
				String link = e.attr("href");
				if(link.startsWith("/")){
					link = link.substring(1);
				}
				hyperLinks.add(link);
				map.put(link, e.text());
			}
		}

		//Reverse the list
		int i = 0;
		for (int j = hyperLinks.size() - 1; j >= hyperLinks.size() / 2; j--){
			String temp = hyperLinks.get(j);
			hyperLinks.set(j, hyperLinks.get(i));
			hyperLinks.set(i, temp);
			i++;
		}

		//Set pages list
		for (int k = 0; k < hyperLinks.size(); k++) {
			String link = hyperLinks.get(k);
			Page page = new Page();
			page.link = link;
			if (k > 0){
				page.prev = pages.get(k-1).link;
			}
			if (k < hyperLinks.size()-1){
				page.next = hyperLinks.get(k+1);
			}
			page.num = Integer.parseInt(map.get(link).substring(0, map.get(link).indexOf(".")));
			//System.out.println("page " + page.num + ": " + page.link + ", prev = " + page.prev + ", next = " + page.next);
			pages.add(page);
		}

		//Check each page
		for(Page page : pages){
			boolean prevFound = false;
			boolean nextFound = false;
			try{
				Document document = Jsoup.connect(root + page.link).get();
				Elements aElements = document.select("a[href]");
				for (Element aTag:aElements) {
					String link = aTag.attr("href");
					if(link.startsWith("/")) {
						link = link.substring(1);
					}
					for(Element img:aTag.getElementsByTag("img")){
						if(img.attr("src").equals("/prevlink.gif") && page.prev.equals(link)){
							prevFound = true;
						}
						if(img.attr("src").equals("/nextlink.gif") && page.next.equals(link)){
							nextFound = true;
						}
					}
				}
			}
			catch(Exception exception){
				System.out.println("At page " + page.num + ": " + page.link + ": caught exception: " + exception.getMessage());
			}
			if(page.prev != null && !prevFound){
				System.out.println("Unexpected prev found at page " + page.num + ": " + page.link);
			}
			if(page.next != null && !nextFound){
				System.out.println("Unexpected next found at page " + page.num + ": " + page.link);
			}
		}
	}

	private static class Page{
		public String link;
		public String prev;
		public String next;
		public int num;
	}
}
