package parsers.dom;

import java.io.IOException;
import java.util.ArrayList;
import bean.MenuCategory;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * Created by Volha_Hitskaya on 3/2/2017.
 */
public class DomParser
{
    public ArrayList<MenuCategory> parseXml() throws SAXException, IOException
    {
//создание DOM-анализатора (Xerces)
        DOMParser parser = new DOMParser();
        parser.parse("menu.xml");
        Document document = parser.getDocument();
        Element root = document.getDocumentElement();
        List<Food> menu = new ArrayList<Food>();
        NodeList foodNodes = root.getElementsByTagName("food");
        Food food = null;
        for (int i = 0; i < foodNodes.getLength(); i++)
        {
            food = new Food();
            Element foodElement = (Element) foodNodes.item(i);
            food.setId(Integer.parseInt(foodElement.getAttribute("id")));
            food.setName(getSingleChild(foodElement,
                    "name").getTextContent().trim());
            food.setDescription(getSingleChild(foodElement,
                    "description").getTextContent().trim());
            menu.add(food);
        }
        for (Food f : menu)
        {
            System.out.println(f.getName() + ", " + f.getId() + ", "
                    + f.getDescription());
        }
    }

    private static Element getSingleChild(Element element, String childName){
        NodeList nlist = element.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return child;
    }
}

