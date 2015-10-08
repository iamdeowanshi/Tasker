package com.android.tasker.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sibi on 16/09/15.
 */
@Table(name = "User")
public class User extends BaseModel {

    @Column(name = "userId" )
    private long userId;
    @Column(name = "userName")
    private String name;
    @Column(name = "userEmail")
    private String email;
    @Column(name = "userPassword")
    private String password;
//    @Column(name = "userImage")
//    private String image;

    private String accessToken;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(int id, String name, String email, String password, String image, String accessToken) {
        this.userId = id;
        this.name = name;
        this.email = email;
        this.password = password;
//        this.image = image;
        this.accessToken = accessToken;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    @Override
    public String toJSON() {
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("name", name);
            jsonObj.put("userId", userId);
            jsonObj.put("email", email);
            jsonObj.put("password", password);

            return jsonObj.toString();
        }

        catch(JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User fromJSON(String json) {
        try {
            JSONObject jsonObj = new JSONObject(json);
            setName(jsonObj.getString("name"));
            setEmail(jsonObj.getString("email"));
            setPassword(jsonObj.getString("password"));
            setUserId(jsonObj.getInt("userId"));
            return this;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
