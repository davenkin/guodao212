package davenkin.bookrank.model;

import java.util.List;

/**
 * Created by Davenkin on 9/19/14.
 */
public class HighChartsRecord {
    private String name;
    private List<List<Long>> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<List<Long>> getData() {
        return data;
    }

    public void setData(List<List<Long>> data) {
        this.data = data;
    }
}
