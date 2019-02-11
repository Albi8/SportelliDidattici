/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbparsexhtml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author limonta_alberto
 */
public class myFile {

    FileReader f;
    Writer writer;

    public String read(String percorsoFile) throws IOException {

        try {
            f = new FileReader(percorsoFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(myFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader b;
        b = new BufferedReader(f);
        String s = "";
        while (true) {
            s += b.readLine();
            if (b.readLine() == null) {
                f.close();
                break;
            }
        }
        return s;
    }

    public void write(String path, String message) throws IOException {
        File file = new File(path);
        writer = new FileWriter(file.getPath());
        writer.write(message);
        writer.close();
    }

}
