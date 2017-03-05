package parsers.sax;

import java.util.ArrayList;

import bean.Dish;
import bean.MenuCategory;
import bean.MenuTagName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Volha_Hitskaya on 3/2/2017.
 */

public class MenuSaxHandler extends DefaultHandler {
    private ArrayList<MenuCategory> menuCategoryList = new ArrayList<MenuCategory>();
    private MenuCategory category;
    private Dish dish;
    private StringBuilder text;

    public ArrayList<MenuCategory> getMenuCategoryList() {
        return menuCategoryList;
    }

    public void startDocument() throws SAXException {
        //System.out.println("Parsing started.");
    }

    public void endDocument() throws SAXException {
        //System.out.println("Parsing ended.");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //System.out.println("startElement -> " + "uri: " + uri + ", localName: " + localName + ", qName: " + qName);
        text = new StringBuilder();
        if (qName.equals("category")) {
            this.category = new MenuCategory(attributes.getValue("categoryName"));
        }
        if (qName.equals("dish")) {
            this.dish = new Dish();
            dish.setId((Integer.parseInt(attributes.getValue("id"))));
        }
    }

    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        MenuTagName tagName = MenuTagName.valueOf(qName.toUpperCase());
        switch (tagName) {
            case PHOTO:
                this.dish.setPhoto(text.toString());
                break;
            case TITLE:
                this.dish.setTitle(text.toString());
                break;
            case DESCRIPTION:
                this.dish.setDescription(text.toString());
                break;
            case PRICE:
                if(text.toString().isEmpty())
                {
                    this.dish.setPrice(0);
                }
                else
                {
                    this.dish.setPrice(Integer.parseInt(text.toString()));
                }
                break;
            case PORTION:
                this.dish.setPortion(text.toString());
                break;
            case DISH:
                this.category.addDish(this.dish);
                this.dish = null;
                break;
            case CATEGORY:
                this.menuCategoryList.add(this.category);
                this.category = null;
                break;
        }
    }

    public void warning(SAXParseException exception) {
        System.err.println("WARNING: line " + exception.getLineNumber() + ": " + exception.getMessage());
    }

    public void error(SAXParseException exception) {
        System.err.println("ERROR: line " + exception.getLineNumber() + ": " + exception.getMessage());
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        System.err.println("FATAL: line " + exception.getLineNumber() + ": " + exception.getMessage());
        throw (exception);
    }
}