package bean;

/**
 * Created by Volha_Hitskaya on 3/2/2017.
 */
public class Dish
{
    private int id;
    private String photo;
    private String title;
    private String description;
    private String portion;
    private int price;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getPhoto()
    {
        return photo;
    }

    public void setPhoto(String photo)
    {
        this.photo = photo;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getPortion()
    {
        return portion;
    }

    public void setPortion(String portion)
    {
        this.portion = portion;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (id != dish.id) return false;
        if (price != dish.price) return false;
        if (!photo.equals(dish.photo)) return false;
        if (!title.equals(dish.title)) return false;
        if (description != null ? !description.equals(dish.description) : dish.description != null) return false;
        return portion.equals(dish.portion);

    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + photo.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + portion.hashCode();
        result = 31 * result + price;
        return result;
    }
}

