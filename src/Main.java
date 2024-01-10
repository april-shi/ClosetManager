import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class Main {

    //clothing class, global variable for clothes
    static Clothing[] clothes = new Clothing[0];
    static Scanner scanner = new Scanner(System.in);

    //global variable for the file
    static String clothingLibraryFile = "ClothingLibrary.txt";
    //change text color variables
    static String COLOR_BLUE = "\u001B[34m";
    static String COLOR_RESET = "\u001B[0m";

    public static void main(String[] args) {

        ReadFromFile(clothingLibraryFile);
        //GenerateClothing(100);
        LandingPage();

    }

    //interface
    public static void LandingPage() {

        System.out.println("\nHello, this is Closet Manager, what can I help you with today?\n");
        System.out.println("1) generate your ootd");
        System.out.println("2) view clothes library");
        System.out.println("3) generate what's trendy today");
        System.out.println("4) quit\n");

        boolean isDone = false;
        while (!isDone) {

            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1" -> {
                    GenerateOutfit();
                    isDone = true;
                }
                case "2" -> {
                    ViewLibrary();
                    isDone = true;
                }
                case "3" -> {
                    GenerateRandomOutfit(clothes);
                    isDone = true;
                }
                case "4" -> { //quits
                    quit();
                    isDone = true;
                }
                default -> System.out.println("I don't understand, please input again.");
            }

        }

    }

    //this method generates & scores the matchability of the outfit
    public static void GenerateOutfit() {
        String userInput;
        Clothing filter = new Clothing();

        System.out.println("Which style are you in the mood for today?");

        for (int i = 0; i < Style.values().length; i++) {

            System.out.println(i + ") " + Style.values()[i]);

        }

        boolean isDone = false;
        while (!isDone) {

            userInput = scanner.nextLine();

            try {
                int number = Integer.parseInt(userInput);
                if (number >= 0 && number < Style.values().length) {

                    filter.style = Style.values()[number];
                    isDone = true;

                } else {

                    System.out.println("out of range!");

                }
            } catch (NumberFormatException ex) {
                System.out.println("invalid input");
            }

        }

        System.out.println("What's the weather like today?");

        for (int i = 0; i < Weather.values().length; i++) {

            System.out.println(i + ") " + Weather.values()[i]);

        }

        isDone = false;
        while (!isDone) {

            userInput = scanner.nextLine();

            try {
                int number = Integer.parseInt(userInput);
                if (number >= 0 && number < Weather.values().length) {

                    filter.weather = Weather.values()[number];
                    isDone = true;

                } else {

                    System.out.println("out of range!");

                }
            } catch (NumberFormatException ex) {
                System.out.println("invalid input");
            }

        }

        System.out.println("What is the occasion?");

        for (int i = 0; i < Occasion.values().length; i++) {

            System.out.println(i + ") " + Occasion.values()[i]);

        }

        isDone = false;
        while (!isDone) {

            userInput = scanner.nextLine();

            try {
                int number = Integer.parseInt(userInput);
                if (number >= 0 && number < Occasion.values().length) {

                    filter.occasion = Occasion.values()[number];
                    isDone = true;

                } else {

                    System.out.println("out of range!");

                }
            } catch (NumberFormatException ex) {
                System.out.println("invalid input");
            }

        }

        //Clothing[] sortedList = SortByStyle(filter.style, clothes);
        //sortedList = SortByWeather(filter.weather, sortedList);
        //sortedList = SortByOccasion(filter.occasion, sortedList);

        for (int i = 0; i < clothes.length; i++) {

            clothes[i].score = 0;

            if (clothes[i].style == filter.style) {

                clothes[i].score++;

            }

            if (clothes[i].weather == filter.weather) {

                clothes[i].score++;

            }

            if (clothes[i].occasion == filter.occasion) {

                clothes[i].score++;

            }

        }

        Clothing[] outfit = new Clothing[ClothingCategory.values().length];

        RandomiseList(clothes);

        for (int j = 0; j < outfit.length; j++) {

            for (int i = 0; i < clothes.length; i++) {

                //checks one by one
                if (clothes[i].category == ClothingCategory.values()[j]) {

                    //at first it is null
                    if (outfit[j] == null) {

                        outfit[j] = clothes[i];
                        break;

                        //replaces the clothes each time when score is greater
                    } else if (clothes[i].score > outfit[j].score) {

                        outfit[j] = clothes[i];
                        break;

                    }

                }

            }

        }

        Random random = new Random();

        boolean isDress = false;

        if (random.nextInt(0, 2) == 1) {

            isDress = true;

        }

        int totalScore = 0;
        for (int i = 0; i < outfit.length; i++) {

            //don't print top and bottom if isDress
            if (isDress && i == 1) {


            } else if (isDress && i == 2) {


                //don't print dress if !isDress
            } else if (!isDress && i == 5) {


            } else {

                totalScore += outfit[i].score;
                System.out.println(outfit[i].ToString());

            }

        }

        /*
        no dress is 4 x 3 = 12
        have dress is 5 x 3 = 15
         */

        double finalPercentage = 0;
        if (isDress) {

            finalPercentage = (double) totalScore / 12 * 100;

        } else {

            finalPercentage = (double) totalScore / 15 * 100;

        }

        //rounds the double to 2 decimal places
        finalPercentage = (double) Math.round(finalPercentage * 100d) / 100d;

        System.out.println("\nYour total outfit matchability score is: " + finalPercentage + "%");

        System.out.println("\nPress any key to continue");
        scanner.nextLine();

        LandingPage();

    }


    public static void GenerateRandomOutfit(Clothing[] list) {

        System.out.println("Here's what's trendy today:\n");

        Random random = new Random();

        boolean isDress = false;

        if (random.nextInt(0, 2) == 1) {

            isDress = true;

        }

        Clothing[] outfit = new Clothing[ClothingCategory.values().length];

        for (int i = 0; i < outfit.length; i++) {

            outfit[i] = GetRandomClothingItemByCategory(ClothingCategory.values()[i], list);


            //don't print top and bottom if isDress
            if (isDress && i == 1) {


            } else if (isDress && i == 2) {


                //don't print dress if !isDress
            } else if (!isDress && i == 5) {


            } else {

                System.out.println(outfit[i].ToString());

            }

        }

        System.out.println("\nPress any key to continue");
        scanner.nextLine();

        LandingPage();

    }

    public static void ViewLibrary() {

        System.out.println(clothes.length);

        for (int i = 0; i < clothes.length; i++) {

            System.out.println(i + ": " + clothes[i].ToString());

        }

        System.out.println("\t1) Add Clothes");
        System.out.println("\t2) Delete Clothes");
        System.out.println("\t3) Back");

        boolean isDone = false;
        while (!isDone) {

            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1" -> {
                    AddClothingItemInterface();
                    isDone = true;
                    ViewLibrary();
                }
                case "2" -> DeleteClothingItemInterface();
                case "3" -> {
                    //quits
                    isDone = true;
                    LandingPage();
                }
                default -> System.out.println("I don't understand, please input again.");
            }

        }

    }

    public static void quit() {

        System.out.println("Thank you for using closet manager. " +
                "\nI hope I did a good job in helping you choose your outfit today. Goodbye!");

    }

    //interface for adding clothes
    public static void AddClothingItemInterface() {

        Clothing item = new Clothing();
        System.out.println("What is the name?");
        String userInput = scanner.nextLine();

        //because it doesn't change the orginal variable so we change it
        userInput = userInput.replace(",", "");
        item.name = userInput;

        System.out.println("What's the category?");
        for (int i = 0; i < ClothingCategory.values().length; i++) {

            System.out.println(i + ") " + ClothingCategory.values()[i]);

        }

        boolean isDone = false;
        while (!isDone) {

            userInput = scanner.nextLine();

            try {
                int number = Integer.parseInt(userInput);
                if (number >= 0 && number < ClothingCategory.values().length) {

                    item.category = ClothingCategory.values()[number];
                    isDone = true;

                } else {

                    System.out.println("out of range!");

                }
            } catch (NumberFormatException ex) {
                System.out.println("invalid input");
            }

        }

        System.out.println("What do u think the style of this piece of clothing is?\n");
        for (int i = 0; i < Style.values().length; i++) {

            System.out.println(i + ") " + Style.values()[i]);

        }

        isDone = false;
        while (!isDone) {

            userInput = scanner.nextLine();

            try {
                int number = Integer.parseInt(userInput);
                if (number >= 0 && number < Style.values().length) {

                    item.style = Style.values()[number];
                    isDone = true;

                } else {

                    System.out.println("out of range!");

                }
            } catch (NumberFormatException ex) {
                System.out.println("invalid input");
            }

        }

        System.out.println("What is the color?");
        userInput = scanner.nextLine();

        //because it doesn't change the orginal variable so we change it
        userInput = userInput.replace(",", "");
        item.color = userInput;

        System.out.println("Under what weather condition would you wear this?\n");

        for (int i = 0; i < Weather.values().length; i++) {

            System.out.println(i + ") " + Weather.values()[i]);

        }

        isDone = false;
        while (!isDone) {

            userInput = scanner.nextLine();

            try {
                int number = Integer.parseInt(userInput);
                if (number >= 0 && number < Weather.values().length) {

                    item.weather = Weather.values()[number];
                    isDone = true;

                } else {

                    System.out.println("out of range!");

                }
            } catch (NumberFormatException ex) {
                System.out.println("invalid input");
            }

        }

        System.out.println("what occasion would you wear this?\n");
        for (int i = 0; i < Occasion.values().length; i++) {

            System.out.println(i + ") " + Occasion.values()[i]);

        }

        isDone = false;
        while (!isDone) {

            userInput = scanner.nextLine();

            try {
                int number = Integer.parseInt(userInput);
                if (number >= 0 && number < Occasion.values().length) {

                    item.occasion = Occasion.values()[number];
                    isDone = true;

                } else {

                    System.out.println("out of range!");

                }
            } catch (NumberFormatException ex) {
                System.out.println("invalid input");
            }

        }

        AddClothingItem(item);

        WriteToFile(clothingLibraryFile);

    }

    //interface2 to delete clothing
    public static void DeleteClothingConfirmation(Clothing item) {

        System.out.println("Are you sure you want to do delete " + COLOR_BLUE + item.name + COLOR_RESET + "?");
        System.out.println("Y / N");

        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("y")) {

            DeleteClothingItem(item);

            System.out.println(item.name + " deleted!");

        } else {

            System.out.println(item.name + " not deleted!");

        }

        ViewLibrary();

    }

    //interface to delete clothing
    public static void DeleteClothingItemInterface() {

        String userInput;

        System.out.println("What's the clothing you want to delete? Type in the number of the clothing from the list above");

        boolean isDone = false;
        while (!isDone) {

            userInput = scanner.nextLine();

            try {
                int number = Integer.parseInt(userInput);
                if (number >= 0 && number < clothes.length) {

                    DeleteClothingConfirmation(clothes[number]);
                    isDone = true;

                } else {

                    System.out.println("out of range!");

                }
            } catch (NumberFormatException ex) {
                System.out.println("invalid input");
            }

        }

    }

    //randomise the list before we start sorting, so we get different results every time when we generate outfit
    public static void RandomiseList(Clothing[] list) {
        int index;
        //temp variable
        Clothing temp;
        Random random = new Random();

        for (int i = list.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = list[index];
            list[index] = list[i];
            list[i] = temp;
        }
    }

    //gets random clothing item by category
    public static Clothing GetRandomClothingItemByCategory(ClothingCategory category, Clothing[] list) {
        Random random = new Random();

        //filtered list
        Clothing[] sortedList = FilterClothingList(category, list);

        if (sortedList.length == 0) {

            Clothing clothes = new Clothing();

            clothes.name = "invalid";

            clothes.category = category;

            return clothes;

        }

        return sortedList[random.nextInt(0, sortedList.length)];

    }

    //this method sorts the list by style and moves the best ones to the top
    public static Clothing[] SortByStyle(Style style, Clothing[] unsorted) {
        // Make a copy of the unsorted list
        Clothing[] sorted = unsorted.clone();

        Clothing temp;
        for (int i = 0; i < sorted.length; i++) {
            if (sorted[i].style == style) {
                // Temporarily remember the item to move to the top
                temp = sorted[i];

                // Shift everything above that item down by one, writing over the moved item
                for (int z = i - 1; z >= 0; z--) {
                    sorted[z + 1] = sorted[z];
                }

                // Move the remembered item to the top
                sorted[0] = temp;
            }
        }
        return sorted;
    }

    //these are methods that sorts the clothing[] array to the categories / enum classes
    public static Clothing[] SortByWeather(Weather weather, Clothing[] unsorted) {

        Clothing[] sorted = unsorted.clone();

        Clothing temp;
        for (int i = 0; i < sorted.length; i++) {
            if (sorted[i].weather == weather) {
                // Temporarily remember the item to move to the top
                temp = sorted[i];

                // Shift everything above that item down by one, writing over the moved item
                for (int z = i - 1; z >= 0; z--) {
                    sorted[z + 1] = sorted[z];
                }

                // Move the remembered item to the top
                sorted[0] = temp;
            }
        }

        return sorted;

    }

    public static Clothing[] SortByOccasion(Occasion occasion, Clothing[] unsorted) {

        Clothing[] sorted = unsorted.clone();

        Clothing temp;
        for (int i = 0; i < sorted.length; i++) {
            if (sorted[i].occasion == occasion) {
                // Temporarily remember the item to move to the top
                temp = sorted[i];

                // Shift everything above that item down by one, writing over the moved item
                for (int z = i - 1; z >= 0; z--) {
                    sorted[z + 1] = sorted[z];
                }

                // Move the remembered item to the top
                sorted[0] = temp;
            }
        }

        return sorted;

    }

    public static Clothing[] SortByCategory(ClothingCategory category, Clothing[] unsorted) {

        Clothing[] sorted = unsorted.clone();

        Clothing temp;
        for (int i = 0; i < sorted.length; i++) {
            if (sorted[i].category == category) {
                // Temporarily remember the item to move to the top
                temp = sorted[i];

                // Shift everything above that item down by one, writing over the moved item
                for (int z = i - 1; z >= 0; z--) {
                    sorted[z + 1] = sorted[z];
                }

                // Move the remembered item to the top
                sorted[0] = temp;
            }
        }

        return sorted;

    }

    /*
    Filter methods by enum class --> below are unused methods but if we use them they are fully functional
    I didn't use it because there isn't enough clothing items hence it won't generate/ filter a full outfit
     */
    public static Clothing[] FilterClothingList(Style style, Clothing[] list) {

        int number = 0;

        for (Clothing item : list) {

            if (item.style == style) {

                number++;

            }

        }

        Clothing[] result = new Clothing[number];

        int count = 0;

        for (Clothing item : list) {

            if (item.style == style) {

                result[count] = item;
                count++;

            }

        }

        return result;

    }

    public static Clothing[] FilterClothingList(Weather weather, Clothing[] list) {

        int number = 0;

        for (Clothing item : list) {

            if (item.weather == weather) {

                number++;

            }

        }

        Clothing[] result = new Clothing[number];

        int count = 0;

        for (Clothing item : list) {

            if (item.weather == weather) {

                result[count] = item;
                count++;

            }

        }

        return result;

    }

    public static Clothing[] FilterClothingList(Occasion occasion, Clothing[] list) {

        int number = 0;

        for (Clothing item : list) {

            if (item.occasion == occasion) {

                number++;

            }

        }

        Clothing[] result = new Clothing[number];

        int count = 0;

        for (Clothing item : list) {

            if (item.occasion == occasion) {

                result[count] = item;
                count++;

            }

        }

        return result;

    }

    //gets the total number of clothes for the category
    public static Clothing[] FilterClothingList(ClothingCategory category, Clothing[] list) {

        int number = 0;

        for (Clothing item : list) {

            if (item.category == category) {

                number++;

            }

        }

        Clothing[] result = new Clothing[number];

        int count = 0;

        for (Clothing item : list) {

            if (item.category == category) {

                result[count] = item;
                count++;

            }

        }

        return result;

    }

    /*
    this method deletes clothes from the file
    temp --> make clothing 1 less --> when we add it back we skip the deleted one (if i = deleted one)
     */
    public static void DeleteClothingItem(Clothing item) {

        Clothing[] temp = new Clothing[clothes.length];

        for (int i = 0; i < clothes.length; i++) {

            temp[i] = clothes[i];

        }

        //minus one from total number of clothes
        clothes = new Clothing[clothes.length - 1];

        int count = 0;

        for (int i = 0; i < temp.length; i++) {

            if (temp[i] != item) {

                clothes[count] = temp[i];
                count++;

            }

        }

        WriteToFile(clothingLibraryFile);

    }

    //this method adds clothes to the file
    public static void AddClothingItem(Clothing item) {

        Clothing[] temp = new Clothing[clothes.length];

        for (int i = 0; i < clothes.length; i++) {

            temp[i] = clothes[i];

        }

        clothes = new Clothing[clothes.length + 1];

        for (int i = 0; i < temp.length; i++) {

            clothes[i] = temp[i];

        }

        clothes[clothes.length - 1] = item;

        //WriteToFile(clothingLibraryFile);

    }

    //method that creates file and see if it was created
    public static void CreateFile(String fileName) {

        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                //System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //method that writes to the file
    public static void WriteToFile(String fileName) {
        CreateFile(clothingLibraryFile);
        try {
            FileWriter myWriter = new FileWriter(fileName);
            for (int i = 0; i < clothes.length; i++) {

                myWriter.write(clothes[i].ToString() + "\n");

            }
            myWriter.close();
            //System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void ReadFromFile(String fileName) {
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                //reads line of text from our file
                String data = myReader.nextLine();
                //creates a new clothing variable and adds it to the constructor
                Clothing newClothes = new Clothing(data);
                //adds new clothes to the array
                AddClothingItem(newClothes);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    //this method generates a bunch of random clothing for testing
    public static void GenerateClothing(int number) {
        Random random = new Random();

        String[] noun = {"Trench coat",
                "Maxi dress",
                "Hoodie",
                "Blazer",
                "Jumpsuit",
                "Cargo pants",
                "Pencil skirt",
                "Turtleneck sweater",
                "Denim jacket",
                "Bikini",
                "Chinos",
                "Peplum top",
                "Bomber jacket",
                "Palazzo pants",
                "Off-the-shoulder blouse"};

        String[] adjectives = {
                "Sophisticated",
                "Elegant",
                "Casual",
                "Stylish",
                "Versatile",
                "Trendy",
                "Chic",
                "Comfortable",
                "Edgy",
                "Classic",
                "Glamorous",
                "Sporty",
                "Flattering",
                "Playful",
                "Bohemian"};

        String[] colors = {
                "Red",
                "Blue",
                "Green",
                "Yellow",
                "Orange",
                "Purple",
                "Pink",
                "Black",
                "White",
                "Gray"
        };

        for (int i = 0; i < number; i++) {

            Clothing item = new Clothing();

            item.name = adjectives[random.nextInt(adjectives.length)] + " " + noun[random.nextInt(noun.length)];
            item.category = ClothingCategory.values()[random.nextInt(0, ClothingCategory.values().length)];
            item.style = Style.values()[random.nextInt(0, Style.values().length)];
            item.color = colors[random.nextInt(colors.length)];
            item.weather = Weather.values()[random.nextInt(0, Weather.values().length)];
            item.occasion = Occasion.values()[random.nextInt(0, Occasion.values().length)];

            AddClothingItem(item);

        }

    }

}