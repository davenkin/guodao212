package davenkin.bookrank.model;

import davenkin.bookrank.model.Category;

/**
 * Created by twer on 4/29/14.
 */
public class RankBook {
    private int bookId;
    private String name;
    private String bookPageUrl;
    private Category category;

    public RankBook(int bookId, String name, String bookPageUrl, Category category) {
        this.bookId = bookId;
        this.name = name;
        this.bookPageUrl = bookPageUrl;
        this.category = category;
    }

    public String getName() {
        return name;
    }


    public String getBookPageUrl() {
        return bookPageUrl;
    }

    public String merchant() {
        return category.getMerchant();
    }

    public String urlTemplate() {
        return category.getUrlTemplate();
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String category() {
        return category.getName();
    }

    public int maxCount() {
        return category.getMaxNumber();
    }

    public String toString() {
        return String.format("[%s:%s:%s]", name, merchant(), category());
    }
}
