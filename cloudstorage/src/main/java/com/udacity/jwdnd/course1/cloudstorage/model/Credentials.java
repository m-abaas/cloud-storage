package com.udacity.jwdnd.course1.cloudstorage.model;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;

public class Credentials {
    private Integer credentialId;
    private String url;
    private String userName;
    private String key;
    private String password;
    private Integer userId;

    public Credentials(Integer credentialId, String url, String userName, String key, String password, Integer userId)
    {
        this.credentialId = credentialId;
        this.url = url;
        this.userName = userName;
        this.key = key;
        this.password = password;
        this.userId = userId;
    }

    // Getters and Setters
    public Integer getCredentialId() { return credentialId; }
    public void setCredentialId(Integer credentialId) { this.credentialId = credentialId; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getUserName() { return userName; }
    public void setUserName(String username) { this.userName = username; }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    // This function makes use of the EncryptionService to decrypt the password and populate the html model
    public String decryptPassword()
    {
        EncryptionService encryptionService = new EncryptionService();
        return encryptionService.decryptValue(this.getPassword(), this.getKey());
    }

}