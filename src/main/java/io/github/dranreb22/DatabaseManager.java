package io.github.dranreb22;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager extends Main {

  private Connection conn;
  private String sqlQuery;
  private PreparedStatement preparedStatement;
  private ResultSet result;

  void initializeDb() {

    try {
      //Open a connection
      String dbUrl = "jdbc:sqlite:src/Data/BookStore.db";
      //Database credentials
      // to create a database username and password,
      // type Create USER [username] PASSWORD "[password]"
      // to allow the user to edit the database use GRANT ALTER ANY SCHEMA TO [username]; in console

      Properties prop = new Properties();
      prop.load(new FileInputStream("src/Data/properties"));
      String pass = prop.getProperty("password");
      String user = prop.getProperty("username");
      conn = DriverManager.getConnection(dbUrl, user, pass);

    } catch (SQLException | IOException exception) {
      exception.printStackTrace();
    }
  }

  public void closeDB() {
    try {
      result.close();
      preparedStatement.close();
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  void insertIntoAuthor(String name, String email, String url) {
    initializeDb();
    String[] author_information = {name, email, url};
    int index = 1;
    try {

      //Execute a query
      sqlQuery = "INSERT OR IGNORE INTO author(author_name, author_email, author_url) VALUES(?,?,?);";
      preparedStatement = conn.prepareStatement(sqlQuery);
      for (String s : author_information) {
        preparedStatement.setString(index, s);
        index++;
      }
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

  }

  void addBook(String isbn, String title, String author, String publisher){
    initializeDb();
    String[] book_information = {isbn, title, author, publisher};
    int index = 1;
    try {

      //Execute a query
      sqlQuery = "INSERT or ignore INTO book(isbn, book_title, author_name, publisher_name) VALUES(?,?,?,?);";
      preparedStatement = conn.prepareStatement(sqlQuery);
      for (String s : book_information) {
        preparedStatement.setString(index, s);
        index++;
      }
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
}
