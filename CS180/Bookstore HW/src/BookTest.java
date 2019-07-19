import org.junit.Assert;
import org.junit.Test;

public class BookTest {

    @Test(timeout = 1000)
    public void _0_testParameterizedConstructor() {
        double[] prices = {1.0,2.0,3.0,4.0};
        Book book  = new Book("Java",12345,prices ,"Dunsmore");

        Assert.assertEquals("Ensure that the Name field is initialized to the specified name!", "Java", book.getName());

        Assert.assertEquals("Ensure that ISBN field is initialized to the specified ISBN!", 12345, book.getISBN());

        Assert.assertEquals("Ensure that you get the lowest price from the array!", 1.0, book.getLowestPrice(),1E-15);

        Assert.assertEquals("Ensure that the author field is initialized to the specified author!", "Dunsmore", book.getAuthor());
    } //_0_testParameterizedConstructor

    @Test(timeout = 1000)
    public void _1_testGetLowestPrice(){
        double[] prices = {23,123,432,56,657,56};
        Book book  = new Book("Java",12345,prices ,"Dunsmore");
        Assert.assertEquals("Ensure that you get the lowest price from the array!", 23, book.getLowestPrice(),1E-15);

    }

}