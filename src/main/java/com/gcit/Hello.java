package com.gcit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.gcit.domain.Author;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
 * Hello world!
 *
 */
public class Hello implements RequestHandler<String, Author> {

	//JSONParser parser = new JSONParser();

	public Connection getConnection() throws SQLException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = (Connection) DriverManager.getConnection(
				"jdbc:mysql://mytestdb.cxcwtsbed0pr.us-east-1.rds.amazonaws.com:3306/library", "mytestdb", "mytestdb");
		return conn;
	}

	public Author handleRequest(String inputStream, Context context) {
		System.out.println("Hello");
		//BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		System.out.println(inputStream);
		try {
			//JSONParser parser = new JSONParser();
			JSONObject jsonObject = new JSONObject(inputStream);//(JSONObject) parser.parse(reader);
			JSONObject object = (JSONObject) jsonObject.get("context");
			String method = (String) object.get("http-method");
			if ("GET".equals(method)) {
				System.out.println("GET Method");
				JSONObject params = (JSONObject) jsonObject.get("params");
				JSONObject path = (JSONObject) params.get("path");
				String authorId = (String) path.get("authorId");
				String sql = "SELECT * FROM library.tbl_author where authorId=?";
				Author author = null;
				PreparedStatement prepareStatement = getConnection().prepareStatement(sql);
				prepareStatement.setInt(1, Integer.parseInt(authorId));
				ResultSet resultSet = prepareStatement.executeQuery();
				author = new Author();
				while (resultSet.next()) {
					author.setAuthorId(resultSet.getInt("authorId"));
					author.setAuthorName(resultSet.getString("authorName"));
				}
				System.out.println(author.toString());
				return author;

			}
			if ("POST".equals(method)) {
				System.out.println("POST Method");
				JSONObject body = (JSONObject) jsonObject.get("body-json");
				String authorId = (String) body.get("authorId");
				String authorName = (String) body.get("authorName");
				String sql = "INSERT INTO `library`.`tbl_author` (`authorId`, `authorName`) VALUES (?, ?)";
				PreparedStatement prepareStatement = getConnection().prepareStatement(sql);
				prepareStatement.setInt(1, Integer.parseInt(authorId));
				prepareStatement.setString(2, authorName);
				prepareStatement.executeUpdate();
				return null;

			}

			//context.getLogger().log(jsonObject.toJSONString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

