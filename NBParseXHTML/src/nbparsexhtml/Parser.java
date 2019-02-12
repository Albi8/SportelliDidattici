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

    public List<NodeList> parseDocument(String fileName, String cost)
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
                    if (value.equals(cost)) {
                        table.add(element);
                        trovato = true;
                    } /*else if ((element.getChildNodes().getLength() == 4 || element.getChildNodes().getLength() == 5) && trovato == true) {
                        table.add(element);
                    }*/ else {
                        trovato = false;
                    }
                }

            }
        }

        return table;
    }

    public String toCSV(List<NodeList> testo) {

        List lista1 = new ArrayList();
        int conta = 0;
        String stringa = "";
        while (conta < testo.size()) {
            if (testo.get(0).getLength() == 4) {
                lista1 = getText(testo.get(0), "td");
            } else if (testo.get(conta).getLength() == 5) {
                lista1 = getText(testo.get(0), "td");
            }

            for (int i = 0; i < lista1.size(); i++) {

                stringa += lista1.get(i) + ";";
                //stringa += lista1.get(1) + ";";
                //stringa += lista1.get(2) + ";";
                //stringa += lista1.get(3) + "\r\n";
            }
            List lista2 = new ArrayList();
            for (int i = 1; i < testo.size() - 1; i = i + 2) {

                lista1 = getText(testo.get(i), "td");
                lista2 = getText(testo.get(i + 1), "td");

                for (int j = 0; j < lista1.size(); j++) {
                    stringa += lista1.get(j) + " " + lista2.get(j) + ";";
                    //stringa += lista1.get(1) + " " + lista2.get(1) + ";";
                    //stringa += lista1.get(2) + " " + lista2.get(2) + ";";
                    //stringa += lista1.get(3) + " " + lista2.get(3) + "\n";
                }
            }
            conta++;
        }

        return stringa;
    }

    public List getText(NodeList nodelist, String tag) {
        List testo = new ArrayList();

        if (nodelist != null && nodelist.getLength() > 0) {
            testo.add(nodelist.item(0).getTextContent());
            testo.add(nodelist.item(1).getTextContent());
            testo.add(nodelist.item(2).getTextContent());
            testo.add(nodelist.item(3).getTextContent());
        }
        return testo;
    }

    public List getText5(NodeList nodelist, String tag) {
        List testo = new ArrayList();

        if (nodelist != null && nodelist.getLength() > 0) {
            testo.add(nodelist.item(0).getTextContent());
            testo.add(nodelist.item(1).getTextContent());
            testo.add(nodelist.item(2).getTextContent());
            testo.add(nodelist.item(3).getTextContent());
            testo.add(nodelist.item(4).getTextContent());
        }
        return testo;
    }

}
