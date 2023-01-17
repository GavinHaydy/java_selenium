package org.theRuffian.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class v {
    public static void main(String[] args) {
         JSON score = mySqlOperation.select("select * from cj where score > 16 ");
        JSONArray keyWord = getKeyWord.getKeyWords(score, "$.result.score");
        System.out.println(keyWord.get(0));
    }
}
