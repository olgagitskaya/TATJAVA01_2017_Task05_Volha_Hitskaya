package view;

import bean.Dish;
import bean.MenuCategory;
import org.xml.sax.SAXException;
import parsers.dom.DomParser;
import parsers.sax.SaxParser;
import parsers.stax.StaxParser;

import javax.xml.bind.Element;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Volha_Hitskaya on 3/2/2017.
 */
public class Runner
{
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
    {
        ArrayList<MenuCategory> menuCategoryArrayList;
        System.out.println("SAX Parser: ");
        SaxParser saxParser = new SaxParser();
        menuCategoryArrayList = saxParser.parseXml();
        printResult(menuCategoryArrayList);
        menuCategoryArrayList = null;
        System.out.println("-------------------------------------");
        System.out.println("StAX Parser: ");
        StaxParser staxParser = new StaxParser();
        menuCategoryArrayList = staxParser.parseXml();
        printResult(menuCategoryArrayList);
        menuCategoryArrayList = null;
        System.out.println("-------------------------------------");
        System.out.println("Dom Parser: ");
        DomParser domParser = new DomParser();
        menuCategoryArrayList = domParser.parseXml();
        printResult(menuCategoryArrayList);
    }

    public static void printResult(ArrayList<MenuCategory> menuCategoryArrayList)
    {
        for(MenuCategory category:menuCategoryArrayList)
        {
            System.out.println(category.getCategoryName());
            for(Dish dish: category.getDishList())
            {
                String dishStr = "    "+ dish.getPhoto()+ " " +dish.getTitle()+ " " +dish.getDescription()+ " " +
                        dish.getPortion()+ " " + dish.getPrice();
                System.out.println(dishStr);

            }
        }

    }
}
