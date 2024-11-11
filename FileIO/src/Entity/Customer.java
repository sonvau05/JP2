package Entity;

public class Customer {
    private int id;
    private String code;
    private String name;

    public Customer() {;}
    public Customer(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String toString(String separator) {
        StringBuilder builder = new StringBuilder();
        return builder.append(this.getId())
                .append(separator)
                .append(this.getCode())
                .append(separator)
                .append(this.getName())
                .toString();
    }
}
