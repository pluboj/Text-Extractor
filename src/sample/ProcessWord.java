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

                    filteredP = filteredP.replaceAll("\n","").replaceAll("\r","");
                    filteredP = filteredP.replaceAll("<o:p>.*</o:p>","");
                    filteredP = filteredP.replaceAll("<!--.*-->","");
                    filteredP = filteredP.replaceAll("\">","\n");
                    filteredP = filteredP.replaceAll("<span.*","");
                    filteredP = filteredP.replaceAll("</span>","");
                    filteredP = filteredP.replaceAll("<a.*","");
                    filteredP = filteredP.replaceAll("</a>","");
                    filteredP = filteredP.replaceAll("\n","").replaceAll("\r","");
                    filteredP = filteredP.replaceAll("(?i)page break","-----------------------------------------------\n\n");

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
