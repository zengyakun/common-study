package com.test;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
public class MongoDBJDBC {

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

//            mongoDatabase.createCollection("test1");
//            System.out.println("集合创建成功");

            MongoCollection<Document> collection = mongoDatabase.getCollection("test1");
            System.out.println("集合 test 选择成功");

            //插入文档
            /**
             * 1. 创建文档 org.bson.Document 参数为key-value的格式
             * 2. 创建文档集合List<Document>
             * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document)
             * */
            Document document = new Document("title", "MongoDB")
                    .append("description", "database")
                    .append("likes", 100)
                    .append("by", "Fly");
            List<Document> documents = new ArrayList<>();
            documents.add(document);
//            collection.insertMany(documents);
            collection.insertOne(document);
            System.out.println("文档插入成功");

        } catch (Exception e) {
            System.err.println(e.getCause().getCause() + ":" + e.getMessage());
        }
    }
}
