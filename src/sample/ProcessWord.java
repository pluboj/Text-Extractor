package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class ProcessWord {
    private String fileName;

    public ProcessWord (String fileName) {
        this.fileName = fileName;
    }

    public void processUrl () {
        try {
            Document doc = Jsoup.parse(new File(fileName), "utf-8");
            Elements paragraphs = doc.select("p");
            for (Element p : paragraphs ) {
                if (p.hasText()) {
                    String filteredP = p.html();
                    filteredP = filteredP.replaceAll("<span\\s+[^>]*|</span>|<o:p>|</o:p>","");
                    filteredP = filteredP.replaceAll("<b\\s.*\">","<b>");
                    System.out.println(filteredP);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "{path to file: "+fileName+"}";
    }
}
