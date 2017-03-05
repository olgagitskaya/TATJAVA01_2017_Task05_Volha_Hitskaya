package parsers.dom;

import java.io.IOException;
import java.util.ArrayList;

import bean.Dish;
import bean.MenuCategory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Volha_Hitskaya on 3/2/2017.
 */
public class DomParser
{
    public ArrayList<MenuCategory> parseXml() throws SAXException, IOException
    {
        ArrayList<MenuCategory> menuCategoryArrayList = new ArrayList<MenuCategory>();

        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse("menu.xml");

            Element root = document.getDocumentElement();
            MenuCategory category = null;
            NodeList categoryNodes = root.getElementsByTagName("category");
            for (int i = 0; i < categoryNodes.getLength(); i++)
            {
                Element categoryElement = (Element) categoryNodes.item(i);
                category = new MenuCategory(categoryElement.getAttribute("categoryName"));

                Dish dish = null;
                NodeList dishNodes = categoryElement.getElementsByTagName("dish");

                for (int j = 0; j < dishNodes.getLength(); j++)
                {
                    Element dishElement = (Element) dishNodes.item(j);
                    dish = new Dish();
                    int dishId = Integer.parseInt(dishElement.getAttribute("id"));
                    dish.setId(dishId);
                    dish.setPhoto(getSingleChild(dishElement, "photo").getTextContent().trim());
                    dish.setTitle(getSingleChild(dishElement, "title").getTextContent().trim());
                    dish.setDescription(getSingleChild(dishElement, "description").getTextContent().trim());
                    dish.setPortion(getSingleChild(dishElement, "portion").getTextContent().trim());
                    String parseString = getSingleChild(dishElement, "price").getTextContent().trim();
                    if(parseString.isEmpty())
                    {
                        dish.setPrice(0);
                    }
                    else
                    {
                        dish.setPrice(Integer.parseInt(parseString));
                    }
                    category.addDish(dish);
                }
                menuCategoryArrayList.add(category);
            }
        }
        catch (ParserConfigurationException e)
        {
            System.out.println(e.getMessage());

        }
        return menuCategoryArrayList;
    }

    private static Element getSingleChild(Element element, String childName)
    {
        NodeList nlist = element.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return child;
    }
}

