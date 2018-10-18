package com.test;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MongoDBJDBC
 * @Description TODO
 * @Author zeng.yakun (0178)
 * @Date 2018/10/18 10:06
 * @Version 1.0
 **/
public class MongoDBJDBC2 {

    public static void main(String[] args) {
        try {
            //需要验证用户名及密码
//            ServerAddress serverAddress = new ServerAddress("10.101.0.223", 27017);
//            List<ServerAddress> addrs = new ArrayList<>();
//            addrs.add(serverAddress);
//            MongoCredential credential = MongoCredential.createCredential("mongoUser", "mycol", "123".toCharArray());
//            List<MongoCredential> credentials = new ArrayList<>();
//            credentials.add(credential);
//            MongoClient mongoClient = new MongoClient(addrs, credentials);

            MongoClient mongoClient = new MongoClient("10.101.0.223", 27017);
//            MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
            MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
            System.out.println("Connect to database successfully");

            MongoCollection<Document> collection = mongoDatabase.getCollection("test1");
            System.out.println("集合 test 选择成功");

//            collection.updateMany(Filters.eq("likes", 100), new Document("$set", new Document("likes", 200)));
//            collection.updateMany(Filters.eq("likes", 200), new Document("$set", new Document("likes2", 200)));

//            collection.deleteOne(Filters.eq("likes", 200));
            collection.deleteMany(Filters.eq("likes", 200));



            //检索所有文档
            /**
             * 1. 获取迭代器FindIterable<Document>
             * 2. 获取游标MongoCursor<Document>
             * 3. 通过游标遍历检索出的文档集合
             * */
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                System.out.println(mongoCursor.next());
            }

        } catch (Exception e) {
            System.err.println(e.getCause().getCause() + ":" + e.getMessage());
        }
    }
}
