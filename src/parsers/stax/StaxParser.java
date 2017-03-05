package parsers.stax;

import bean.Dish;
import bean.MenuCategory;
import bean.MenuTagName;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Created by Volha_Hitskaya on 3/2/2017.
 */
public class StaxParser
{

    public ArrayList<MenuCategory> parseXml() throws FileNotFoundException
    {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        ArrayList<MenuCategory> menuCategoryList = null;
        try
        {
            InputStream input = new FileInputStream("menu.xml");
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            menuCategoryList = process(reader);
        }
        catch (XMLStreamException e)
        {
            e.printStackTrace();
        }
        return menuCategoryList;
    }

    private static ArrayList<MenuCategory> process(XMLStreamReader reader)
            throws XMLStreamException
    {
        ArrayList<MenuCategory> menuCategoryList = new ArrayList<MenuCategory>();
        MenuCategory category = null;
        Dish dish = null;
        MenuTagName elementName = null;
        while (reader.hasNext())
        {
// определение типа "прочтённого" элемента (тега)
            int type = reader.next();
            switch (type)
            {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = getElementTagName(reader
                            .getLocalName());
                    switch (elementName)
                    {
                        case CATEGORY:
                            category = new MenuCategory(reader.getAttributeValue(null, "categoryName"));
                            break;
                        case DISH:
                            dish = new Dish();
                            Integer id = Integer.parseInt(reader.getAttributeValue(null, "id"));
                            dish.setId(id);
                            break;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty())
                    {
                        break;
                    }
                    switch (elementName)
                    {
                        case PHOTO:
                            dish.setPhoto(text);
                        case TITLE:
                            dish.setTitle(text);
                            break;
                        case DESCRIPTION:
                            dish.setDescription(text);
                            break;
                        case PORTION:
                            dish.setPortion(text);
                            break;
                        case PRICE:
                            if(text.isEmpty())
                            {
                                dish.setPrice(0);
                            }
                            else{
                                Integer price = Integer.parseInt(text);
                                dish.setPrice(price);
                            }
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    elementName = getElementTagName(reader.getLocalName());
                    switch (elementName)
                    {
                        case CATEGORY:
                            menuCategoryList.add(category);
                            break;
                        case DISH:
                            category.addDish(dish);
                    }
            }
        }
        return menuCategoryList;
    }

    public static MenuTagName getElementTagName(String element)
    {
        switch (element)
        {
            case "category":
                return MenuTagName.CATEGORY;
            case "dish":
                return MenuTagName.DISH;
            case "photo":
                return MenuTagName.PHOTO;
            case "title":
                return MenuTagName.TITLE;
            case "description":
                return MenuTagName.DESCRIPTION;
            case "portion":
                return MenuTagName.PORTION;
            case "price":
                return MenuTagName.PRICE;
            case "menu":
                return MenuTagName.MENU;
            default:
                throw new EnumConstantNotPresentException(MenuTagName.class, element);
        }
    }
}


