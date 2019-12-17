package com.lucene.index;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;

import java.io.IOException;

/**
 * @author liuzhenbo5@jd.com
 * @description
 * @date 2019/12/17
 */
public class ResourceManager {

    public static void closeIndexWriter(IndexWriter writer){
        try {
            if (writer != null) writer.close();
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
