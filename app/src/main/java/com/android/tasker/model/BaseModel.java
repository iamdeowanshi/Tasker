package com.android.tasker.model;

import com.activeandroid.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sibi on 30/09/15.
 */
public abstract class BaseModel extends Model {

    public abstract String toJSON();

    public abstract BaseModel fromJSON( String json);

    public String toJSONArray(List<Task> models) {
        try {
            JSONArray jsonArr = new JSONArray();

            for (BaseModel model : models) {
                JSONObject jsonObject = new JSONObject(model.toJSON());
                jsonArr.put(jsonObject);
            }

            JSONObject jsonObject = new JSONObject();
            return jsonObject.put("result",jsonArr).toString();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<BaseModel> fromJSONArray(String toJSONArray) {
        try {
            JSONObject jsonObj = new JSONObject(toJSONArray);
            JSONArray jsonArray = jsonObj.getJSONArray("result");

            List<BaseModel> baseModelList = new ArrayList();

            for(int i = 0; i < jsonArray.length(); i++) {
                baseModelList.add(fromJSON(jsonArray.getJSONObject(i).toString()));
            }

            return baseModelList;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
