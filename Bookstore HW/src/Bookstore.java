/**
 * A simple Bookstore class
 *
 *
 * @author Vaastav Arora, arora74@purdue.edu
 */
public class Bookstore {

    private int row, col;
    private Book[][][] bookshelf;

    /**
     * Construct a newly allocated {@code Bookstore} object with given parameters
     * @param row Number of rows in each shelf
     * @param col Number of columns in each shelf
     * @param books Array of books to be placed in the bookshelf
     */
    public Bookstore(int row, int col, Book[] books) {

        bookshelf = new Book[10][][];
        this.row = row;
        this.col = col;

        for (int i = 0; i < bookshelf.length; i++) {
            bookshelf[i] = new Book[row][col];
        }

        int alpha = 0;
        int r = 0;
        int c = 0;

        for (int i = 0; i < books.length; i++) {
            bookshelf[alpha][r][c] = books[i];
            c++;
            if (c == col ) {
                r++;
                c = 0;
            }
            if (r == row ) {
                alpha++;
                r = 0;
            }
        }
    }

    /**
     * Book look up in bookshelf using the name of the book
     * @param name Name of the book
     * @return String with the format    Book: [name] [ISBN] [author] [lowest price]
     */
    public String lookUpName(String name) {

        for(int i=0;i<10;i++){
            for(int j=0;j<bookshelf[i].length;j++){
                for(int k=0;k<bookshelf[i][j].length;k++){
                    if(bookshelf[i][j][k].getName().equals(name)){
                        return "Book: "+bookshelf[i][j][k].getName()+" "+bookshelf[i][j][k].getISBN()+" "+bookshelf[i][j][k].getAuthor() +" "+ bookshelf[i][j][k].getLowestPrice();
                    }
                }
            }
        }

        return "Not found";
    }

    /**
     * Book look up in bookshelf using the ISBN of the book
     * @param ISBN ISBN of the book
     * @return String with the format    Book: [name] [ISBN] [author] [lowest price]
     */
    public String lookUpISBN(int ISBN) {

        for(int i=0;i<10;i++){
            for(int j=0;j<bookshelf[i].length;j++){
                for(int k=0;k<bookshelf[i][j].length;k++){
                    if(bookshelf[i][j][k].getISBN() == ISBN){
                        return "Book: "+bookshelf[i][j][k].getName()+" "+bookshelf[i][j][k].getISBN()+" "+bookshelf[i][j][k].getAuthor()+" "+ bookshelf[i][j][k].getLowestPrice();
                    }
                }
            }
        }

        return "Not found";
    }

    public Book[][][] getBookshelf(){
        return bookshelf;
    }
}

