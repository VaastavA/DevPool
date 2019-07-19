import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BookstoreTest {


    @Test
    public void test1(){
        Book[] books = csvParser(40);
        Bookstore bookstore = new Bookstore(2,2,books);
        for(Book b:books){
            Assert.assertEquals("Name Look-Up failed!", bookstore.lookUpName(b.getName()), b.toString());
        }

    }

    @Test
    public void test2(){
        Book[] books = csvParser(160);
        Bookstore bookstore = new Bookstore(4,4,books);
        for(Book b:books){
            Assert.assertEquals("Name Look-Up failed!", bookstore.lookUpName(b.getName()), b.toString());
        }
    }

    @Test
    public void test3(){
        Book[] books = csvParser(90);
        Bookstore bookstore = new Bookstore(3,3,books);
        for(Book b:books){
            Assert.assertEquals("Name Look-Up failed!", bookstore.lookUpISBN(b.getISBN()), b.toString());
        }
    }

    @Test
    public void test4(){
        Book[] books = csvParser(250);
        Bookstore bookstore = new Bookstore(5,5,books);
        for(Book b:books){
            Assert.assertEquals("Name Look-Up failed!", bookstore.lookUpISBN(b.getISBN()), b.toString());
        }
    }


    private Book[] csvParser(int num){

        String csvFile = "/Users/vaastavarora/IdeaProjects/HW7/src/BX-Books.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        Book[] books = new Book[num];

        try {
            boolean first = true;
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null && num!=0) {

                if(first) {
                    first  = false;
                    continue;
                }
                // use comma as separator
                try {
                    String[] country = line.split(cvsSplitBy);

                    //System.out.println("Book data: " + country[0].substring(1,country[0].length()-1) +" "+ country[1].substring(1,country[1].length()-1) +" "+ country[2].substring(1,country[2].length()-1));

                    double[] myDoubleArray = new double[5];
                    for (int i = 0; i < myDoubleArray.length; i++) {
                        myDoubleArray[i] = Math.random() * 100;
                    }
                    books[num-1] = new Book(country[1].substring(1, country[1].length() - 1), Integer.parseInt(country[0].substring(1, country[0].length() - 1)), myDoubleArray, country[2].substring(1, country[2].length() - 1));
                    num--;
                }catch (Exception e){
                    continue;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return books;

    }
}