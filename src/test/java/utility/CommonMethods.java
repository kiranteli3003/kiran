package utility;

import com.github.javafaker.Faker;

import java.util.Random;

public class CommonMethods {
    Faker faker = new Faker();

    public String generateFirstName() {
        return faker.name().firstName().replace("'", "");
    }

    public String generateLastName() {
        return faker.name().lastName().replace("'", "");
    }

    public String generateNumber(int length) {
        return faker.number().digits(length).replace("0", "9");

    }

    public String generateProductName() {
        return faker.commerce().productName().replace("'", "");
    }

    public String generateProductDescription() {
        return faker.lorem().sentence().replace("'", "");
    }

    public String genrateProductcolor() {
        return faker.color().name();
    }
    public String generateProductURL() {
        String[] domains = {"amazon.com", "flipkart.com", "nykaa.com"};
        String selectedDomain = domains[new Random().nextInt(domains.length)];
        return "https://www." + selectedDomain + "/product/" + faker.commerce().productName().replace(" ", "-").toLowerCase();
    }

    public String generateSKU() {
        return faker.regexify("[A-Z0-9]{6,7}"); // Generates a 6-7 alphanumeric SKU
    }

    public String generateImageURL() {
        String[] domains = {"amazon.com", "flipkart.com", "nykaa.com"};
        String selectedDomain = domains[new Random().nextInt(domains.length)];
        String productId = faker.internet().uuid().substring(0, 10).toUpperCase();
        return "https://m.media-" + selectedDomain + ".com/images/I/" + productId + "._SL1500_.jpg";

    }

    public String generatePrice() {
        return String.format("%.2f", faker.number().randomDouble(2, 10, 99)); // Two-digit decimal format
    }

    public String generateSize() {
        return faker.options().option("Small", "Medium", "Large", "Extra Large");
    }


    public String genrategolivetitle() {
        return faker.book().title();

    }

    public String genrategolivedescription() {
        return faker.lorem().sentence(5);
    }

}

