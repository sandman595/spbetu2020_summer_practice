package parser;

public class ItemData {
    public Integer id;
    public String name;
    public String photo;

    public ItemData(Integer id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[Name: " + name + " id: " + id + " img: " + photo + "]");
        return builder.toString();
    }
}
