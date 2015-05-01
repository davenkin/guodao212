package davenkin.guodao212.model;

/**
 * Created by twer on 5/1/14.
 */
public class Category {
    private int categoryId;
    private String merchant;
    private String name;
    private String description;
    private String urlTemplate;
    private int maxNumber;

    public Category(int categoryId, String merchant, String name, String description, String urlTemplate, int maxPageNumber) {
        this.categoryId = categoryId;
        this.merchant = merchant;
        this.name = name;
        this.description = description;
        this.urlTemplate = urlTemplate;
        this.maxNumber = maxPageNumber;
    }

    public String getMerchant() {
        return merchant;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public String getUrlTemplate() {
        return urlTemplate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getMaxNumber() {
        return maxNumber;
    }
}
