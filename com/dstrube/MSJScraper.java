package com.dstrube;

/*

Using the 3rd party Jsoup for scraping, if timeouts occur due to slow internet, 
increase the JSOUP_TIMEOUT. (If it's going too slowly, feel free to decrease it, 
but be aware that 6000 seems to be the lower limit.)

From ~/java:
Mac / Linux:

Compile:
javac -cp bin:bin/jsoup-1.14.3.jar -d bin com/dstrube/MSJScraper.java

Run:
java -cp bin:bin/jsoup-1.14.3.jar com.dstrube.MSJScraper [URL]

[URL]1
10
100
1000
10000
100000
=>
not found

[URL]930845
=> found; 

what is the youngest findable?

Starting from here: https://github.com/dstrube1/playground_java/blob/master/com/dstrube/codeForAtlanta/GeorgiaElectionResultsScraper/GeorgiaElectionResultsScraper.java
*/

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MSJScraper {
	private static final int JSOUP_TIMEOUT = 8000;
	private static final int MAX_SUFFIX = 930845;

	public static void main(String[] args) {
		if (args.length < 1){
			System.out.println("Usage: java -cp bin:bin/jsoup-{n}.{nn}.{n}.jar com.dstrube.MSJScraper {URL}");
			return;
		}
		final String PREFIX = args[0];
		int suffix = 1;
		Elements elements;
		try {
			final Response response = Jsoup.connect(PREFIX + MAX_SUFFIX).timeout(JSOUP_TIMEOUT).execute(); 
			final Document document = response.parse();

			if (document == null){
				System.out.println("Error: document is null.");
				return;
			}
			//System.out.println("Document title: " + document.title());
			//[title]

			//System.out.println("Document text: " + document.head().text());
			//same^

			//System.out.println("Document text: " + document.body().text());
			//nothing

			//System.out.println("Document elements count: " + document.getAllElements().size());
			//17

			/*int count = 0;
			for(Element element : document.getAllElements()){
				//System.out.println("element className: " + element.className()); //nothing
				//some empty, the rest are useless:
				//System.out.println("element data: " + element.data()); 
				//System.out.println("element " + count + " html: " + element.html()); 
				count++;
			}*/

			/* From the source of the website when rendered in a browser:
			<h1 aria-label="Job not found" class="errorStateHeading-478">Job not found</h1>
			No such tag found this way: * /
			elements = document.getElementsByTag("h1");
			if (!verifyAtLeastOne(elements, "h1")){
				return;
		}/*/

		//System.out.println("Document to string: " + document.toString());
		//Same output whether it's PREFIX + suffix or PREFIX + MAX_SUFFIX

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		System.out.println("Done");
	}

	private static boolean verifyAtLeastOne(final Elements elements, final String fieldName){
		if (elements == null){
			System.out.println("Error: " + fieldName + " is null");
			return false;
	   	}
	   	if (elements.size() == 0){
			System.out.println("Error: " + fieldName + " is empty");
			return false;
		}
		return true;
	}

/*

*/	

}
