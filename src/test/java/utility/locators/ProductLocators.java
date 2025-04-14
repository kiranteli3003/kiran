package utility.locators;

public interface ProductLocators {

    String CatalogLocator = "a[href='/catalog']";
    String AddProductButtonLocator = "//button[text()='Add Product']";
    String ProductNameLocator = "#product-name";
    String ProductURLLocator = "#product-url";
    String ProductSKULocator = "#product-sku";
    String ProductImagesLocator = "#product-images";
    String ProductDescriptionLocator  = "#product-description";
    String ProductColorLocator = "#product-color";
    String ProductSizeLocator = "#product-size";
    String ProductPriceLocator = "#product-price";
    String AddProductSubmitButtonLocator = "//button[text()='Submit']";
    String DeleteYesLocator = "//button[text()='YES']";
    String DownloadCSVFileLocator = "//button[text()='Download Sample CSV file']";
    String UploadCSVFileButtonLocator = "//input[@aria-label='Upload CSV']";


}
