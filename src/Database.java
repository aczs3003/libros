package database;

import models.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:catalogo_libros.db";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public void createTables() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS books (id INTEGER PRIMARY KEY, title TEXT, author TEXT)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(createTableSQL)) {
            pstmt.execute();
        }
    }

    public void insertBook(Book book) throws SQLException {
        String insertSQL = "INSERT INTO books(id, title, author) VALUES(?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setInt(1, book.getId());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, String.join(", ", book.getAuthors()));
            pstmt.executeUpdate();
        }
    }
}
