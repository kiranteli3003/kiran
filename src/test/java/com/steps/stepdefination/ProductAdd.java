package com.steps.stepdefination;


import com.microsoft.playwright.FileChooser;
import com.steps.cucumber.AbstractSteps;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.CommonMethods;
import utility.CommonStaticStrings;
import utility.locators.ProductLocators;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class ProductAdd extends AbstractSteps implements ProductLocators {

    private final Page page = testContext().getBrowserPage();

    CommonMethods commonMethods = new CommonMethods();
    String ProductName = commonMethods.generateProductName();
    String ProductURL = commonMethods.generateProductURL();
    String ProductSKU = commonMethods.generateSKU();
    String ProductImageURL = "https://m.media-amazon.com/images/I/71GLMJ7TQiL._SL1500_.jpg";
    String ProductDesc = commonMethods.generateProductDescription();
    String ProductColor = commonMethods.genrateProductcolor();
    String ProductSize = commonMethods.generateSize();
    String ProductPrice = commonMethods.generatePrice();

    @And("User go to the Catalog page")
    public void UserGoToCatalogPage() {
        page.waitForSelector(CatalogLocator);
        page.locator(CatalogLocator).click();
    }

    @And("Product: User Go to the Add Product Screen")
    public void productUserGoToTheAddProductScreen() {
        page.locator(AddProductButtonLocator).click();
    }

    @When("Product > Add Product: Enters all valid data")
    public void ProductAddProductEnterAllValidData() throws InterruptedException {
        // Store product data in the context
        testContext().set(CommonStaticStrings.PRODUCT_NAME, ProductName);
        testContext().set(CommonStaticStrings.PRODUCT_URL, ProductURL);
        testContext().set(CommonStaticStrings.PRODUCT_SKU, ProductSKU);
        testContext().set(CommonStaticStrings.PRODUCT_IMAGE_URL, ProductImageURL);
        testContext().set(CommonStaticStrings.PRODUCT_DESCRIPTION, ProductDesc);
        testContext().set(CommonStaticStrings.PRODUCT_COLOR, ProductColor);
        testContext().set(CommonStaticStrings.PRODUCT_SIZE, ProductSize);
        testContext().set(CommonStaticStrings.PRODUCT_PRICE, ProductPrice);

        Thread.sleep(3000);

        page.locator(ProductNameLocator).fill(ProductName);
        page.locator(ProductURLLocator).fill(ProductURL);
        page.locator(ProductSKULocator).fill(ProductSKU);
        page.locator(ProductImagesLocator).fill(ProductImageURL);
        page.locator(ProductDescriptionLocator).fill(ProductDesc);
        page.locator(ProductColorLocator).fill(ProductColor);
        page.locator(ProductSizeLocator).fill(ProductSize);
        page.locator(ProductPriceLocator).fill(ProductPrice);
        page.locator(AddProductSubmitButtonLocator).click();
    }

    @And("Product > Product Listing: User clicks on edit button of added product")
    public void productProductListingUserClicksOnEditButtonOfAddedProduct() throws InterruptedException {
        Thread.sleep(3000);
        String addedProductEditButtonLocator = "//div[@class='namecol-desc' and text()='" + ProductName + "']/../../../td/div/a/span[@class='icon-pen']";
        page.locator(addedProductEditButtonLocator).click();
    }

    @Then("Product > Edit Product: Verify that data shown are correct.")
    public void productEditProductVerifyThatDataShownAreCorrect() {
        // Step 1: Store expected values in a Map
        Map<String, String> expectedData = new HashMap<>();
        expectedData.put("ProductName", testContext().get(CommonStaticStrings.PRODUCT_NAME).toString());
        expectedData.put("ProductURL", testContext().get(CommonStaticStrings.PRODUCT_URL).toString());
        expectedData.put("ProductSKU", testContext().get(CommonStaticStrings.PRODUCT_SKU).toString());
        expectedData.put("ProductImageURL", testContext().get(CommonStaticStrings.PRODUCT_IMAGE_URL).toString());
        expectedData.put("ProductDescription", testContext().get(CommonStaticStrings.PRODUCT_DESCRIPTION).toString());
        expectedData.put("ProductColor", testContext().get(CommonStaticStrings.PRODUCT_COLOR).toString());
        expectedData.put("ProductSize", testContext().get(CommonStaticStrings.PRODUCT_SIZE).toString());
        expectedData.put("ProductPrice", testContext().get(CommonStaticStrings.PRODUCT_PRICE).toString());

        // Step 2: Store displayed values in a Map
        Map<String, String> displayedData = new HashMap<>();
        displayedData.put("ProductName", page.locator("#product-name").inputValue());
        displayedData.put("ProductURL", page.locator("#product-url").inputValue());
        displayedData.put("ProductSKU", page.locator("#product-sku").inputValue());
        displayedData.put("ProductImageURL", page.locator("#product-images").inputValue());
        displayedData.put("ProductDescription", page.locator("#product-description").inputValue());
        displayedData.put("ProductColor", page.locator("#product-color").inputValue());
        displayedData.put("ProductSize", page.locator("#product-size").inputValue());
        displayedData.put("ProductPrice", page.locator("#product-price").inputValue());

        // Step 3: Iterate and compare the values
        for (String key : expectedData.keySet()) {
            String expectedValue = expectedData.get(key);
            String displayedValue = displayedData.get(key);

            // Assertion for comparison
            assert expectedValue.equals(displayedValue) :
                    "Mismatch for " + key + ": Expected '" + expectedValue + "', but got '" + displayedValue + "'";
        }
    }

    @And("Product > Edit Product: User update the product data")
    public void productEditProductUserUpdateTheProductData() throws InterruptedException {
        Thread.sleep(3000);


        page.locator(ProductNameLocator).fill(ProductName + "test");
        page.locator(ProductDescriptionLocator).fill(ProductDesc + "desc");
        page.locator(ProductColorLocator).fill(ProductColor + "test");
        page.locator(AddProductSubmitButtonLocator).click();

        // Save updated product data
        testContext().set(CommonStaticStrings.PRODUCT_NAME, ProductName);
        testContext().set(CommonStaticStrings.PRODUCT_DESCRIPTION, ProductDesc);
        testContext().set(CommonStaticStrings.PRODUCT_COLOR, ProductColor);
    }

    @When("Product: User delete the same product")
    public void UserDeleteTheSameProduct() throws InterruptedException {
        Thread.sleep(5000);
        String addedProductDeleteButtonLocator = "//div[@class='namecol-desc' and text()='" + ProductName + "test" + "']/../../../td/div/a/span[@class='icon-trash']";
        page.locator(addedProductDeleteButtonLocator).click();
        page.locator(DeleteYesLocator).click();
    }


    @And("Product: User Download the CSV file Format")
    public void productUserDownloadTheCSVFileFormat() throws InterruptedException {
        page.locator(DownloadCSVFileLocator).click();
        testContext().getScenarioLogger().log("Product CSV File should be downloaded properly");
        Thread.sleep(4000);

        page.setInputFiles(UploadCSVFileButtonLocator, Paths.get("WHI_catlogue.csv"));
        testContext().getScenarioLogger().log("Product CSV file uploaded successfully");



//        page.locator("//input[@aria-label='Upload CSV']").click();
//        FileChooser fileChooser = page.waitForFileChooser(
//                () -> page.locator("//input[@aria-label='Upload CSV']").click()
//        );
//        Thread.sleep(3000);
//        Path filePath = Paths.get("WHI_catlogue.csv").toAbsolutePath();
//        if (Files.exists(filePath)) {
//            // Select the file for upload
//            fileChooser.setFiles(filePath);
//            Thread.sleep(30000);
//            testContext().getScenarioLogger().log("Product CSV file uploaded successfully");
//        } else {
//            System.out.println("File not found at: " + filePath);
//        }

    }


}



//        Thread.sleep(4000);
//        //page.setInputFiles("//input[@aria-label='Upload CSV']", Paths.get("Downloads/WHI_catlogue.csv"));
//        page.getByLabel("Upload CSV").click();
//        page.getByLabel("Upload CSV").setInputFiles(Paths.get("/home/kiran.teli@brainvire.com/Downloads/WHI_catlogue.csv"));





