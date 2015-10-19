//package com.thoughtriott.metaplay.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//import com.mongodb.Mongo;
//import com.mongodb.MongoClient;
//
//@Configuration
//@EnableMongoRepositories(basePackages="com.thoughtriott.metaplay.data.repositories.mongo")
//public class MongoConfig extends AbstractMongoConfiguration {
//
//	
//	@Override
//	protected String getDatabaseName() {
//		return "metaplay";
//	}
//
//	@Override
//	public Mongo mongo() throws Exception {
//		return new MongoClient();
//	}
//	
//	
////	private static final String HOST = "ec2-52-10-48-29.compute-1.amazonaws.com";
////	private static final String HOST2 = "ec2-52-10-48-29.us-west-2.compute.amazonaws.com";
////	private static final String pw = "renegades";
//	
////	@Override
////	public Mongo mongo() throws Exception {
////		MongoCredential credential = MongoCredential.createMongoCRCredential("driott", "metaplay", pw.toCharArray());
////		MongoClient mongo = new MongoClient(new ServerAddress(HOST2, 27017), Arrays.asList(credential));
////		DB db = mongo.getDB("metaplay");
////		System.out.println(db);
////		
////		return mongo;
////	}
//
//}
