/**
 * A simple Book class
 *
 *
 * @author Vaastav Arora, arora74@purdue.edu
 */
public class Book {

    private String name;
    private int ISBN;
    private double[] prices;
    private String author;

    /**
    Construct a newly allocated {@code Book} object with given parameters

     @param name Name of the book
     @param ISBN ISBN of the book
     @param prices Available prices of the book
     @param author Author of the book
     */
    public Book(String name,int ISBN,double[] prices,String author){
        this.name = name;
        this.ISBN = ISBN;
        this.prices = prices;
        this.author = author;
    }

    /**
     * Returns Name of the book
     * @return Name of the book
     */
    public String getName() {
        return name;
    }

    /**
     * Returns author of the book
     * @return Author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns ISBN of the book
     * @return ISBN of the book
     */
    public int getISBN() {
        return ISBN;
    }

    /**
     * Returns lowest price out of price options
     * @return Lowest price out of price options
     */
    public double getLowestPrice() {

        double min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) min = prices[i];
        }

        return min;
    }

    /**
     * Returns String representation of a Book
     * @return String representation of a Book
     */
    @Override
    public String toString() {
        return "Book: "+this.name+" "+this.ISBN+" "+ this.author+" "+this.getLowestPrice();
    }
}