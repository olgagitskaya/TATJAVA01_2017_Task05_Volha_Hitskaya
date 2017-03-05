package bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 04.03.2017.
 */
public class MenuCategory
{
    private ArrayList<Dish> dishList = new ArrayList<Dish>();
    private String categoryName;

    public MenuCategory(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public ArrayList<Dish> getDishList()
    {
        return dishList;
    }

    public void addDish(Dish dish)
    {
        this.dishList.add(dish);
    }

}
