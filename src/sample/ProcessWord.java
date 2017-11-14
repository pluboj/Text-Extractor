package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class ProcessWord  extends AbstractFileGenerator {
    private String fileName;

    public ProcessWord (String fileName) {
        this.fileName = fileName;
    }

    public void processFile () {
        try {
            Document doc = Jsoup.parse(new File(fileName), "utf-8");
            Pattern lineBreak = Pattern.compile("\n");
            Pattern returnCar = Pattern.compile("\r");
            Pattern op = Pattern.compile("<o:p>.*</o:p>");
            Pattern comment = Pattern.compile("<!--.*-->");
            Pattern endTag = Pattern.compile("\">");
            Pattern span = Pattern.compile("<span.*");
            Pattern spanEnd = Pattern.compile("</span>");
            Pattern anchor = Pattern.compile("<a.*");
            Pattern anchorEnd = Pattern.compile("</a>");
            Pattern bold = Pattern.compile("<b\\s.*");
            Pattern underline = Pattern.compile("<u\\s.*");

            StringBuilder sb = new StringBuilder();

            Elements parents = doc.select("h1, h2, h3, h4, h5, h6, p");
            for ( Element child : parents) {
                String childToString = child.html();
                childToString = lineBreak.matcher(childToString).replaceAll("");
                childToString = returnCar.matcher(childToString).replaceAll("");
                childToString = op.matcher(childToString).replaceAll("");
                childToString = comment.matcher(childToString).replaceAll("");
                childToString = endTag.matcher(childToString).replaceAll(">\n");
                childToString = span.matcher(childToString).replaceAll("");
                childToString = spanEnd.matcher(childToString).replaceAll("");
                childToString = anchor.matcher(childToString).replaceAll("");
                childToString = anchorEnd.matcher(childToString).replaceAll("");
                childToString = bold.matcher(childToString).replaceAll("<b>");
                childToString = underline.matcher(childToString).replaceAll("<u>");
                childToString = lineBreak.matcher(childToString).replaceAll("");
                childToString = returnCar.matcher(childToString).replaceAll("");

                if (childToString.length() == 2 && childToString.charAt(0) == 'O') {
                    continue;
                } else {
                    sb.append(childToString);
                }

                sb.append("\r\n===================================================\r\n");
            }
            generateTextFile(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "{path to file: "+fileName+"}";
    }
}
