import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/student_management";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {
            StringBuilder json = new StringBuilder("[");
            while (rs.next()) {
                json.append("{\"id\":").append(rs.getInt("id"))
                    .append(",\"name\":\"").append(rs.getString("name"))
                    .append("\",\"age\":").append(rs.getInt("age"))
                    .append(",\"email\":\"").append(rs.getString("email"))
                    .append("\",\"phone\":\"").append(rs.getString("phone"))
                    .append("\"},");
            }
            if (json.length() > 1) json.deleteCharAt(json.length() - 1);
            json.append("]");
            out.print(json.toString());
        } catch (SQLException e) {
            out.print("{\"error\":\"Database error: " + e.getMessage() + "\"}");
        }
    }
}
