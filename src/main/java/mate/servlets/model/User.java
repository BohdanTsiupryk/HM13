package mate.servlets.model;

public class User {
    private int ID;
    private String login;
    private String password;
    private String email;
    private String country;

    public User(int ID, String login, String password, String email, String country) {
        this.ID = ID;
        this.login = login;
        this.password = password;
        this.email = email;
        this.country = country;
    }
    public User(String login, String password, String email, String country) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.country = country;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
