public class Clothing {

    public String name = "New Clothing";
    public ClothingCategory category = ClothingCategory.BOTTOM;
    public Style style = Style.CASUAL;

    public String color = "white";

    public Weather weather = Weather.TEMPERATE;

    public Occasion occasion = Occasion.CASUAL;
    public int score = -1;

    //we want it here everytime we run the clothing thingy, a constructor
    public Clothing() {



    }

    //overloaded the class
    public Clothing(String data) {

        String[] split = data.split(",", 6);
        name = split[0];
        category = ClothingCategory.valueOf(split[1]);
        //(googled java string to enum) valueOf is a built-in function for enum which selects everything from the list
        style = Style.valueOf(split[2]);
        color = split[3];
        weather = Weather.valueOf(split[4]);
        occasion = Occasion.valueOf(split[5]);

    }

    public String ToString() {

        return name + "," + category + "," + style + "," + color + "," + weather + "," + occasion;

    }

}
