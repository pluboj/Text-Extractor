package sample;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractFileGenerator {
    private String fileName = "study.txt";

    public void generateTextFile(StringBuilder sb) {
        File path = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory();

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path+"/"+fileName),
                StandardCharsets.UTF_8))) {
            writer.write(sb.toString());
        } catch (IOException ex ) {
            Logger.getLogger(AbstractFileGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
