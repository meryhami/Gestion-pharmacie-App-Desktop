package sample;

public class categories {
    private int id;
    private String label;

    public categories(int id, String label) {
        this.id = id;
        this.label = label;
    }
    public categories(String label)
    {
        this.label=label;
    }

    public categories() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label ;

    }
}
