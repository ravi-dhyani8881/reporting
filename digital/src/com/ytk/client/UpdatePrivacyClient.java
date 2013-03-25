package com.ytk.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ytk.utility.LogDetails;

// String queryText = "("+query+" AND -AccountID:"+LoginID+" AND (Everyone:1 OR((AccountID:"+friendListResult+") AND (ContactsShowList:0) AND (FoldersShowList:"+folderListResult+" OR FoldersShowList:0))) NOT ((Onlyme:1) OR (ContactsHideList:"+LoginID+") OR (FoldersHideList:"+folderListResult+"))) OR ("+query+" AND AccountID:"+LoginID+") OR    ("+query+"  AND AccountID:"+friendListResult+" AND ContactsShowList:"+LoginID+") -AccountID:0 ";

@Component("updatePrivacyClient")
public class UpdatePrivacyClient {
	
	private static final Logger logger = LoggerFactory.getLogger(UpdatePrivacyClient.class);

	
	@Autowired
	LogDetails logDetails;
	
	public String commonPrivacyQuery(String query ,String friendListResult , String folderListResult , String blockListResult , String LoginID , String AccountID){

		if(friendListResult.equals("") || friendListResult.equals(null) )
			friendListResult="0";
		
		if(folderListResult.equals("") || folderListResult.equals(null) )
			folderListResult="99";
		
		if( blockListResult.equals("") || blockListResult.equals(null) )
			blockListResult="0";
		
		String queryText = "((("+query+" AND -AccountID:"+LoginID+" AND (Everyone:1 OR((AccountID:"+friendListResult+") AND (ContactsShowList:0))) NOT ((OnlyMe:1) OR (ContactsHideList:"+LoginID+"))) OR ("+query+" AND AccountID:"+LoginID+") OR    ("+query+"  AND AccountID:"+friendListResult+" AND ContactsShowList:"+LoginID+") -AccountID:"+blockListResult+" OR (ContactsShowList:"+LoginID+")) AND -(FoldersHideList:"+folderListResult+" )  ) ";
		
		return queryText;
	}
	
}


//	String connectionOnlyCase="OR ("+query+" AND FoldersHideList:0 AND FoldersShowList:0 AND OnlyMe:0 AND Everyone:0 )";


//Last update2 failed for connection only

//	String queryText = "((("+query+" AND -AccountID:"+LoginID+" AND (Everyone:1 OR((AccountID:"+friendListResult+") AND (FoldersShowList:"+folderListResult+") AND (ContactsShowList:0))) NOT ((OnlyMe:1) OR (ContactsHideList:"+LoginID+"))) OR ("+query+" AND AccountID:"+LoginID+") OR    ("+query+"  AND AccountID:"+friendListResult+" AND ContactsShowList:"+LoginID+") -AccountID:"+blockListResult+" OR (ContactsShowList:"+LoginID+")) AND -(FoldersHideList:"+folderListResult+" )  ) ";
	
//	String queryText = "((("+query+" AND -AccountID:"+LoginID+" AND (Everyone:1 OR((AccountID:"+friendListResult+") AND (FoldersShowList:"+folderListResult+") AND (ContactsShowList:0))) NOT ((OnlyMe:1) OR (ContactsHideList:"+LoginID+"))) OR ("+query+" AND AccountID:"+LoginID+") "+connectionOnlyCase+" OR    ("+query+"  AND AccountID:"+friendListResult+" AND ContactsShowList:"+LoginID+") -AccountID:"+blockListResult+" OR (ContactsShowList:"+LoginID+")) AND -(FoldersHideList:"+folderListResult+" )  ) ";
