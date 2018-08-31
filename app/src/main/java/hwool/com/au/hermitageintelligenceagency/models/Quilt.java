package hwool.com.au.hermitageintelligenceagency.models;

public class Quilt {
    private String id;
    private String name;
    private String color;

    private String description;


    private String getId(){
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Quilt(String name, String color, String width, String height, String description) {
        this.name = name;
        this.color = color;

        this.description = description;
    }
}
