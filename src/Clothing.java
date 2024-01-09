public class Clothing {

    public String name = "New Clothing";
    public ClothingCategory category = ClothingCategory.BOTTOM;
    public Style style = Style.CASUAL;

    public String color = "white";

    public Weather weather = Weather.TEMPERATE;

    public Occasion occasion = Occasion.CASUAL;
    public int score = -1;

    public Clothing() { //we want it here everytime we run the clothing thingy, a constructor



    }

    public Clothing(String data) { //overloaded the class

        String[] split = data.split(",", 6);
        name = split[0];
        category = ClothingCategory.valueOf(split[1]);
        style = Style.valueOf(split[2]); //(googled java string to enum) valueOf is a built-in function for enum which selects everything from the list
        color = split[3];
        weather = Weather.valueOf(split[4]);
        occasion = Occasion.valueOf(split[5]);

    }

    public String ToString() {

        return name + "," + category + "," + style + "," + color + "," + weather + "," + occasion;

    }

}
