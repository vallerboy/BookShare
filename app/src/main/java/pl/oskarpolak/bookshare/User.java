package pl.oskarpolak.bookshare;

/**
 * Created by OskarPraca on 2017-01-17.
 */

public class User {

    private String login;
    private String password; // Hasło powinno być szyfrowane

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
