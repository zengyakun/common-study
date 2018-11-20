package com.test.elkdemo;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ESLink
 * @Description TODO
 * @Author zeng.yakun (0178)
 * @Date 2018/11/2 15:01
 * @Version 1.0
 **/
public class ESLink {
    public static final String CLUSTER_NAME = "docker-cluster"; //实例名称
    private static final String IP = "47.93.121.126";
    private static final int PORT = 9200;  //端口
    //1.设置集群名称：默认是elasticsearch，并设置client.transport.sniff为true，使客户端嗅探整个集群状态，把集群中的其他机器IP加入到客户端中
    private static Settings settings = Settings.builder()
            .put("cluster.name", CLUSTER_NAME)
            .put("client.transport.sniff", false)
            .build();
    //创建私有对象
    private static TransportClient client;

    //反射机制创建单例的TransportClient对象
    static {
//        try {
//            client = new PreBuiltTransportClient(settings);
//            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(IP), PORT));
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
    }

    //取得实例
    public static synchronized TransportClient getTransportClient() {
        return client;
    }

    /**
     * 简单的查询
     * select * from {index} where {field}={Accept} limit {size};
     *
     * @param index  查询的索引
     * @param type   查询的type,可以使用heda查看
     * @param field  查询的字段
     * @param Accept 查询的内容
     * @param size   查询结果的条数
     * @return SearchResponse的Json
     */
    public List<String> queryByFilter_Accept(String index, String type, String field, String Accept, int size) {
        TransportClient client = (TransportClient) ESLink.build();
        SearchResponse response = client.prepareSearch(index)//设置要查询的索引(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(type)//设置type, 这个在建立索引的时候同时设置了, 或者可以使用head工具查看
                .setQuery(QueryBuilders.matchQuery(field, Accept)) //在这里"message"是要查询的field,"Accept"是要查询的内容
                .setFrom(0)
                .setSize(size)
                .setExplain(true)
                .execute()
                .actionGet();
        List<String> docList = new ArrayList<>();
        for (SearchHit hit : response.getHits()) {
            docList.add(hit.getSourceAsString());
        }
        client.close();
        return docList;
    }

    /**
     * 简单的查询
     * select * from {index} limit {size};
     *
     * @param index 查询的索引
     * @param type  查询的type,可以使用heda查看
     * @param size  查询结果的条数
     * @return 结果list
     */
    public List<String> queryByFilter(String index, String type, int size) {
        TransportClient client = ESLink.getTransportClient();
        SearchResponse response = client.prepareSearch(index)//设置要查询的索引(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(type)//设置type, 这个在建立索引的时候同时设置了, 或者可以使用head工具查看
                .setFrom(0)
                .setSize(size)
                .setExplain(true)
                .execute()
                .actionGet();
        List<String> docList = new ArrayList<>();
        for (SearchHit hit : response.getHits()) {
            docList.add(hit.getSourceAsString());
        }
        client.close();
        return docList;
    }

    private static Client build() {
        if (null != client) {
            return client;
        }
        Client client = null;
        try {
            Settings settings = Settings.builder().put("cluster.name", CLUSTER_NAME).put("client.transport.sniff", true).build();
            client = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(InetAddress.getByName(IP), PORT));
            System.out.println("创建Elasticsearch Client 结束");
        } catch (Exception e) {
            System.out.println("创建Client异常");
        }
        return client;
    }

    public static void main(String[] args) {
        try {
            ESLink utils = new ESLink();
            List docList = utils.queryByFilter_Accept("product_workorder-server", "order", "msg", "5363767", 10);
            for (Object doc : docList) {
                System.out.println(doc);
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
