package org.theRuffian.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TheRuffian
 */
public class documentOperation {
    public static Object[][] readCsv(String fileNameRoot, boolean ignoreTheFirstLine) throws IOException {
        List<Object[]> records = new ArrayList<Object[]>();
        String record;
        //设定UTF-8字符集，使用带缓冲区的字符输入流BufferedReader读取文件内容
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(fileNameRoot), StandardCharsets.UTF_8));
        if (ignoreTheFirstLine){
            //忽略读取CSV文件的标题行（第一行）
            file.readLine();
        }
        //遍历读取文件中除第一行外的其他所有内容并存储在名为records的ArrayList中，每一行records中存储的对象为一个String数组
        while ((record = file.readLine()) != null) {
            String[] fields = record.split(",");
            records.add(fields);
        }
        //关闭文件对象
        file.close();
        //将存储测试数据的List转换为一个Object的二维数组
        Object[][] results = new Object[records.size()][];
        //设置二位数组每行的值，每行是一个Object对象
        for (int i = 0; i < records.size(); i++) {
            results[i] = records.get(i);
        }
        return results;
    }

    public static Object[][] readCsv(String fileNameRoot) throws IOException{
        return readCsv(fileNameRoot,false);
    }


}
