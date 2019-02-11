package nbparsexhtml;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author limonta_alberto
 */
public class Parser {

    private List table;

    public Parser() {
        table = new ArrayList();
    }

    public List<NodeList> parseDocument(String fileName)
            throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document document;
        Element root, element;
        NodeList nodelist;

        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document = builder.parse(fileName);
        root = document.getDocumentElement();

        boolean trovato = false; //controllare se contiene 4 elementi
        nodelist = root.getElementsByTagName("tr");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                element = (Element) nodelist.item(i);
                String value = element.getFirstChild().getTextContent();

                if (value != null) {
                    if (value.equals("DISCIPLINA")) {
                        table.add(element);
                        trovato = true;
                    } else if (element.getChildNodes().getLength() == 4 && trovato == true) {
                        table.add(element);
                    } else {
                        trovato = false;
                    }
                }

            }
        }

        return table;
    }

    public String toCSV(List<NodeList> testo) {
        
        List lista1 = new ArrayList();
        List lista2 = new ArrayList();
        lista1 = getText(testo.get(0), "td");
        
        String stringa = "";

        stringa += lista1.get(0) + ";";
        stringa += lista1.get(1) + ";";
        stringa += lista1.get(2) + ";";
        stringa += lista1.get(3) + "\r\n";

        for (int i = 1; i < testo.size() - 1; i = i + 2) {
            lista1 = getText(testo.get(i), "td");
            lista2 = getText(testo.get(i + 1), "td");

            stringa += lista1.get(0) + " " + lista2.get(0) + ";";
            stringa += lista1.get(1) + " " + lista2.get(1) + ";";
            stringa += lista1.get(2) + " " + lista2.get(2) + ";";
            stringa += lista1.get(3) + " " + lista2.get(3) + "\n";
        }

        return stringa;
    }

    public List getText(NodeList nodelist, String tag) {
        List testp = new ArrayList();

        if (nodelist != null && nodelist.getLength() > 0) {
            testp.add(nodelist.item(0).getTextContent());
            testp.add(nodelist.item(1).getTextContent());
            testp.add(nodelist.item(2).getTextContent());
            testp.add(nodelist.item(3).getTextContent());
        }
        return testp;
    }
}
