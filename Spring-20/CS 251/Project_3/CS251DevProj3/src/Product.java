public class Product {

    String vendor;
    int depRating;
    double price;
    int stock;

    public Product (String vendor, int depRating, double price, int stock) {

        this.vendor = vendor;
        this.depRating = depRating;
        this.price = price;
        this.stock = stock;

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

    public String toString() {

        //change later maybe?
        return vendor + " " + depRating + " " + price + " " + stock;

    }
}
