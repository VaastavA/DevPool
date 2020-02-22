public class Product {

    String name;
    String vendor;
    int depRating;
    double price;
    int stock;

    public Product (String name, String vendor, int depRating, double price, int stock) {
        this.name = name;
        this.vendor = vendor;
        this.depRating = depRating;
        this.price = price;
        this.stock = stock;

    }
    public String getName() {
        return name;
    }

    public String getVendor() {
        return vendor;
    }

    public int getDepRating() {
        return depRating;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }


}
