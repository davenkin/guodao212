package davenkin.guodao212.model;

import java.sql.Timestamp;

/**
 * Created by twer on 4/27/14.
 */
public class BookRankRecord {
    private String bookName;
    private String merchant;
    private String category;
    private Timestamp date;
    private int rank;

    public BookRankRecord(String bookName, String merchant, String category, Timestamp date, int rank) {
        this.bookName = bookName;
        this.merchant = merchant;
        this.category = category;
        this.date = date;
        this.rank = rank;
    }

    public String getBookName() {
        return bookName;
    }

    public String getMerchant() {
        return merchant;
    }

    public String getCategory() {
        return category;
    }

    public Timestamp getDate() {
        return date;
    }

    public int getRank() {
        return rank;
    }
}
