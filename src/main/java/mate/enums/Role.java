package mate.enums;

public enum  Role {
    NOTAUTORIZE(1, "NOTAUTORIZE"),
    USER(2, "USER"),
    ADMIN(3, "ADMIN");

    private int id;
    private String name;

    Role(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
