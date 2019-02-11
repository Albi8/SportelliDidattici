/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbparsexhtml;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author limonta_alberto
 */
public class NBValidateXHTML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
         List<NodeList> tabella = null;
        Parser pars = new Parser();
        myFile file = new myFile();
        try {
            tabella = pars.parseDocument("circolare.xml");
            String CSV = pars.toCSV(tabella);
          
            
            file.write("contenuto.csv", CSV);
            
        } catch (ParserConfigurationException | SAXException | IOException exception) {
            System.out.println("Errore!");
        }
    }

}
