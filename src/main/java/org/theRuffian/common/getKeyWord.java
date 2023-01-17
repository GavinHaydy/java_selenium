package org.theRuffian.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPath;


public class getKeyWord {
    public static String getKeyWord(JSON jsonString, String keyWord){
        return beforeGetKeyWord(jsonString,keyWord).get(0).toString();
    }

    public static JSONArray getKeyWords(JSON jsonString, String keyWord){
        return beforeGetKeyWord(jsonString,keyWord);
    }

    public static JSONArray beforeGetKeyWord(JSON json, String keyWord){
        return JSONArray.parseArray(JSONPath.eval(json,keyWord).toString());
    }
}
