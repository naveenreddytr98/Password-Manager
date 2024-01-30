package edu.udayton.passwordmanager2;

public class userlogins {

    private String userId;
    private String userName;
    private String passwords;
    private String url;

    public userlogins() {

    }

    public userlogins(String userId, String userName, String passwords, String url) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.passwords = passwords;
        this.url = url;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPasswords() {
        return passwords;
    }
    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
