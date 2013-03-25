package com.ytk.utility;

import org.springframework.stereotype.Component;


@Component("serverurlConstants")
public class ServerurlConstants {

//	For live enviroment
	public static final String MEMCACHE_URL="172.16.2.80";
	

//	For local enviroment
//	public static final String MEMCACHE_URL="192.168.0.48";

	
	
//	For Qc enviroment
//	public static final String MEMCACHE_URL="192.168.0.192";
	
	public static final int MEMCACHE_PORT=11211;
	
	public static final int hostPort=8983;
	

	public static final String hostIp ="localhost";
	
	
	public static final String ADD_FOLDER_URL="http://"+hostIp+":8881/solr";
	
	public static final String ADD_CONTACTS_URL="http://"+hostIp+":8983/solr";
	
	public static final String ADD_NEIBHOURHOOD_URL="http://"+hostIp+":8998/solr";
	
	public static final String ADD_PLACES_REVIEW_URL="http://"+hostIp+":8992/solr";
	
	public static final String ADD_UPDATE_URL="http://"+hostIp+":8993/solr";

	public static final String ADD_FACES_URL="http://"+hostIp+":8988/solr";

	public static final String ADD_CONNECTION_URL="http://"+hostIp+":8990/solr";

	public static final String ADD_PLACES_URL="http://"+hostIp+":8991/solr";
	
	public static final String ADD_PROFILES_URL="http://"+hostIp+":8989/solr";
	

	

	
	public static final String ADD_THINGS_URL="http://"+hostIp+":8987/solr";
	
	public static final String DELETE_CONNECTION_URL="http://"+hostIp+":8984/solr";


	
	public static final String DELETE_MEMBER_URL="http://"+hostIp+":8983/solr";
	
	public static final String DELETE_MESSAGES_URL="http://"+hostIp+":8986/solr";
	
	
	
	public static final String DELETE_PCONNECTION_URL="http://"+hostIp+":8990/solr";
	

	
	public static final String DELETE_THINGS2_URL="http://"+hostIp+":8992/solr";
	
	public static final String ADD_PTHINGS_URL="http://"+hostIp+":8992/solr";
	
	public static final String DELETE_UPDATES_URL="http://"+hostIp+":8993/solr";
	
	public static final String DELETE_UPDATES_BY_ACCOUNT_TYPE_URL="http://"+hostIp+":8993/solr";	
	
	public static final String ADD_DISSCUSSION_QUESTION_URL="http://"+hostIp+":8994/solr";
	
	public static final String ADD_MESSAGES_URL="http://"+hostIp+":8995/solr";
	
	public static final String ADD_PLANS_URL="http://"+hostIp+":8996/solr";
	
	public static final String ADD_PLAN_GUEST_URL="http://"+hostIp+":8997/solr";
	
	
	
	
	
	
	
	public static final String ADD_URL="http://"+hostIp+":8983/solr";
	
	public static final String DELETE_PLACES_REVIEW_URL="http://"+hostIp+":8992/solr";
	
	
	public static final String ADD_DEMO_URL="http://"+hostIp+":8983/solr/demo/";
	
	
//	public static final String DELETE_ALL_DEMO_URL="http://192.168.0.153:8983/solr/demo/";
	
	public static final String DELETE_ALL_DEMO_URL="http://"+hostIp+":8983/solr/demo/";
	
	
/*
	
	public static final String ADD_CONTACTS_URL="http://localhost:8983/solr";
	
	public static final String ADD_FOLDER_URL="http://localhost:8881/solr";
	
	public static final String ADD_NEIBHOURHOOD_URL="http://localhost:8998/solr";
	
	public static final String ADD_PLACES_REVIEW_URL="http://localhost:8992/solr";
	
	public static final String ADD_UPDATE_URL="http://localhost:8993/solr";

	public static final String ADD_FACES_URL="http://localhost:8988/solr";

	public static final String ADD_PCONNECTION_URL="http://localhost:8990/solr";

	public static final String ADD_PLACES_URL="http://localhost:8991/solr";
	
	public static final String ADD_PROFILES_URL="http://localhost:8989/solr";
	
	public static final String ADD_CONNECTIONS_URL="http://localhost:8984/solr";
	
	public static final String ADD_MESSAGES_URL="http://localhost:8986/solr";
	
	public static final String ADD_THINGS_URL="http://localhost:8987/solr";
	
	public static final String DELETE_CONNECTION_URL="http://localhost:8984/solr";

	public static final String DELETE_THINGS_URL="http://localhost:8987/solr";
	
	public static final String DELETE_MEMBER_URL="http://localhost:8983/solr";
	
	public static final String DELETE_MESSAGES_URL="http://localhost:8986/solr";
	
	public static final String DELETE_FACES_URL="http://localhost:8988/solr";
	
	public static final String DELETE_PCONNECTION_URL="http://localhost:8990/solr";
	
	public static final String DELETE_PLACES_URL="http://localhost:8991/solr";
	
	public static final String DELETE_THINGS2_URL="http://localhost:8992/solr";
	
	public static final String ADD_PTHINGS_URL="http://localhost:8992/solr";
	
	public static final String DELETE_UPDATES_URL="http://localhost:8993/solr";
	
	public static final String DELETE_UPDATES_BY_ACCOUNT_TYPE_URL="http://localhost:8993/solr";	
	
	public static final String ADD_DISSCUSSION_QUESTION_URL="http://localhost:8994/solr";
	
	public static final String ADD_MESSAGES2_URL="http://localhost:8995/solr";
	
	public static final String ADD_PLANS_URL="http://localhost:8996/solr";
	
	public static final String ADD_PLAN_GUEST_URL="http://localhost:8997/solr";
	
	public static final String DELETE_MESSAGE_URL="http://localhost:8995/solr";
	
	public static final String DELETE_PLANS_URL="http://localhost:8996/solr";
	
	public static final String DELETE_PLAN_GUEST_URL="http://localhost:8997/solr";
	
	public static final String ADD_URL="http://localhost:8983/solr";

	public static final String DELETE_PLACES_REVIEW_URL="http://localhost:8992/solr";

   public static final String ADD_DEMO_URL="http://localhost:8881/solr";

*/
}

