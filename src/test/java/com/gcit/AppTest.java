package com.gcit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import org.junit.Test;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.gcit.Hello;

/**
 * Unit test for simple App.
 */
public class AppTest {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 * @throws FileNotFoundException 
	 */
	@Test
	public void appTest() throws FileNotFoundException {
		File initialFile = new File("testFile.txt");
		InputStream targetStream = new FileInputStream(initialFile);
		String test = "{\r\n    \"context\": {\r\n        \"authorizer-principal-id\": \"\",\r\n        \"cognito-authentication-type\": \"\",\r\n        \"cognito-identity-id\": \"\",\r\n        \"resource-path\": \"/lms/authors/{authorId}\",\r\n        \"account-id\": \"985863529922\",\r\n        \"cognito-identity-pool-id\": \"\",\r\n        \"request-id\": \"test-invoke-request\",\r\n        \"api-id\": \"ffstl2zfs1\",\r\n        \"resource-id\": \"scif8o\",\r\n        \"user-arn\": \"arn:aws:iam::985863529922:root\",\r\n        \"caller\": \"985863529922\",\r\n        \"http-method\": \"GET\",\r\n        \"cognito-authentication-provider\": \"\",\r\n        \"api-key\": \"test-invoke-api-key\",\r\n        \"stage\": \"test-invoke-stage\",\r\n        \"source-ip\": \"test-invoke-source-ip\",\r\n        \"user\": \"985863529922\",\r\n        \"user-agent\": \"Apache-HttpClient/4.5.x (Java/1.8.0_112)\"\r\n    },\r\n    \"body-json\": {\"authorId\": \"3\",\"authorName\":\"aloi\"},\r\n    \"params\": {\r\n        \"path\": {\r\n            \"authorId\": \"3\"\r\n        },\r\n        \"querystring\": {},\r\n        \"header\": {}\r\n    },\r\n    \"stage-variables\": {}\r\n}";
		Hello hello = new Hello();
		Context context = new Context() {
			
			public int getRemainingTimeInMillis() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public int getMemoryLimitInMB() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public LambdaLogger getLogger() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getLogStreamName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getLogGroupName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getInvokedFunctionArn() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public CognitoIdentity getIdentity() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getFunctionVersion() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getFunctionName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public ClientContext getClientContext() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getAwsRequestId() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		System.out.println(hello.handleRequest(test, context));
	}

}
