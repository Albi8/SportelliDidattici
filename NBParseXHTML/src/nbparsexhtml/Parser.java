package nbparsexhtml;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Parser {

    private List srtinghe;

    public Parser() {
        srtinghe = new ArrayList();
    }

    public List parseDocument(String filename)
            throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document document;
        Element root, element;
        NodeList nodelist;
        Link stringa;
        // creazione dell’albero DOM dal documento XML
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document = builder.parse(filename);
        root = document.getDocumentElement();
        // generazione della lista degli elementi "libro"
        nodelist = root.getElementsByTagName("td");
        for(int j=0;j<nodelist.getLength();j++){
        if (nodelist.item(j).getTextContent().equals("DISCIPLINA")) {
                element = (Element) nodelist.item(j);
                stringa = getLink(element);
                if (stringa != null) {
                    srtinghe.add(stringa);
                
            }
        }
        
    }
        return srtinghe;
    }

    private Link getLink(Element element) {
        Link stringa = null;
        String href = element.getAttribute("tr");
        Element elementParent = (Element) element.getParentNode().getParentNode();
        String disciplina = getTextValue(elementParent, "td", 0);
        String docente = getTextValue(elementParent, "td", 1);
        String giorno = getTextValue(elementParent, "td", 2);
        String ora = getTextValue(elementParent, "td", 3);
        if (disciplina != null) {
            stringa = new Link(href, disciplina, docente, giorno,ora);
        }
        return stringa;
    }

    // restituisce il valore testuale dell’elemento figlio specificato
    private String getTextValue(Element element, String tag, int child) {
        String value = null;
        NodeList nodelist;
        nodelist = element.getElementsByTagName(tag);
        if (nodelist != null && nodelist.getLength() > child) {
            Node nodeChild = nodelist.item(child).getFirstChild();
            if ((nodeChild != null) && nodeChild.hasChildNodes()) {
                value = nodeChild.getFirstChild().getNodeValue();
            }
        }
        return value;
    }

}
