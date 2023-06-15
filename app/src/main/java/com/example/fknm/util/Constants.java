package com.example.fknm.util;
 
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.HBaseConfiguration;
 
 
public class Constants {
    //final关键字用来指定这个变量不能被修改
    //1、configuration，HBase的配置信息
//    public static final Configuration CONFIGURATION = HBaseConfiguration.create();
 
    //2、命名空间
    public static final String NAMESPACE = "WeiBo";
 
    //3、微博内容表
    public static final String CONTENT_TABLE = "WeiBo:content";
    //微博内容表的列族,用来存放用户发布的微博
    public static final String CONTENT_TABLE_CF = "info";
    //设置最大版本数
    public static final int CONTENT_TABLE_VERSIONS = 1;
 
    //4、用户关系表
    public static final String RELATION_TABLE = "WeiBo:relation";
    //用户关系表的列族
    //用来存放你关注的人的用户id
    public static final String RELATION_TABLE_ATTEND = "attends";
    //用来存放关注你的用的的id
    public static final String RELATION_TABLE_FANS = "fans";
    //设置最大版本数：
    public static final int RELATION_TABLE_VERSIONS = 1;
 
    //收件箱表
    public static final String INBOX_TABLE = "WeiBo:inbox";
    //收件箱表列族：用来存放你关注的人近期发布的一些微博
    public static final String INBOX_TABLE_CONCERN = "concern";
    //设置最大版本数,即你可以接收到的你关注的人最近发布的微博的数量，每人2条
    public static final int INBOX_TABLE_VERSIONS = 2;
    public static final String SERVER_TAG = "SOCKET_ADB";
}