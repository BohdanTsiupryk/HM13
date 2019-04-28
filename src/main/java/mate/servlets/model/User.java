package mate.servlets.model;

import java.util.Objects;

public class User {
    private int id;
    private String login;
    private String password;
    private String email;
    private String country;

    public User(int id, String login, String password, String email, String country) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return id == user.id &&
                login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login);
    }
}
