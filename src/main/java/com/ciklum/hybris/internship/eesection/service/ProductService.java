package com.ciklum.hybris.internship.eesection.service;

import com.ciklum.hybris.internship.eesection.repository.CrudRepository;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ProductService implements CrudRepository {
    private final DriverManagerDataSource dataSource;

    public ProductService(DriverManagerDataSource driverManagerDataSource) {
        this.dataSource = driverManagerDataSource;
    }

    @Override
    public void create(HttpServletRequest req, HttpServletResponse resp) {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(
                "INSERT INTO Product (name, price, status, created_at) VALUES (?,?,?,?)")) {
            String name = req.getParameter("name");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, Integer.parseInt(req.getParameter("price")));
            preparedStatement.setString(3, req.getParameter("status"));
            LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
            preparedStatement.setString(4, String.valueOf(localDateTime));
            int result = preparedStatement.executeUpdate();
            String title = "Added Product";
            PrintWriter writer = resp.getWriter();
            writer.println("<html>");
            writer.println("<head><title>" + title + "</title></head><body>");
            if (result > 0) {
                writer.println("<H3>Your product " + name + " is added to database.</H3>");
            } else {
                writer.println("<H3>Something went wrong. Please try again</H3>");
            }
            writer.print("<a href=\"index.html\">Back</a>");
            writer.println("</body></html>");
        } catch (SQLException | IOException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void read(HttpServletRequest req, HttpServletResponse resp) {
        String query = "SELECT * FROM Product";
        try (ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery(query)) {
            String title = "List of products";
            PrintWriter writer = resp.getWriter();
            writer.println("<html>");
            writer.println("<head><title>" + title + "</title></head><body>");
            if (!resultSet.next()) {
                writer.println("<H3>There are not any products.</H3>");
            } else {
                writer.println("<H3>List of products.</H3>");
                writer.print("<table>");
                writer.print("<tr>");
                writer.println("<th> Id </th>");
                writer.println("<th> Name </th>");
                writer.println("<th> Price </th>");
                writer.println("<th> Status </th>");
                writer.println("<th> Created at </th>");
                writer.print("</tr>");
                fillTable(resultSet, writer);
                writer.println("</table>");
            }
            writer.print("<a href=\"index.html\">Back</a>");
            writer.println("</body></html>");
        } catch (SQLException | IOException sqlException) {
            sqlException.printStackTrace();
        }
    }

    private void fillTable(ResultSet resultSet, PrintWriter writer) throws SQLException {
        while (resultSet.next()) {
            writer.println("<tr>");
            writer.println("<td>");
            writer.print(resultSet.getInt("id"));
            writer.println("</td>");
            writer.println("<td>");
            writer.print(resultSet.getString("name"));
            writer.println("</td>");
            writer.println("<td>");
            writer.print(resultSet.getInt("price"));
            writer.println("</td>");
            writer.println("<td>");
            writer.print(resultSet.getString("status"));
            writer.println("</td>");
            writer.println("<td>");
            writer.print(resultSet.getString("created_at"));
            writer.println("</td>");
            writer.println("</tr>");
        }
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(
                "DELETE FROM Product WHERE name = ?")) {
            String name = req.getParameter("name");
            preparedStatement.setString(1, name);
            int result = preparedStatement.executeUpdate();
            String title = "Removed product";
            PrintWriter writer = resp.getWriter();
            writer.println("<html>");
            writer.println("<head><title>" + title + "</title></head><body>");
            if (result > 0) {
                writer.println("<H3>Product is removed from database.</H3>");
            } else {
                writer.println("<H3>Product is not removed from database. Please try again</H3>");
            }
            writer.print("<a href=\"index.html\">Back</a>");
            writer.println("</body></html>");
        } catch (SQLException | IOException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void update(HttpServletRequest req, HttpServletResponse resp) {
        try (PreparedStatement preparedStatement = dataSource.getConnection()
                .prepareStatement("UPDATE Product SET price = ? WHERE name = ?")) {
            String name = req.getParameter("name");
            preparedStatement.setInt(1, Integer.parseInt(req.getParameter("price")));
            preparedStatement.setString(2, name);
            int result = preparedStatement.executeUpdate();
            String title = "Updated Product";
            PrintWriter writer = resp.getWriter();
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>" + title + "</title></head><body>");
            if (result > 0) {
                writer.println("<H3>Price of your product " + name + " is changed and put to database.</H3>");
            } else {
                writer.println("<H3>Something went wrong. Please try again</H3>");
            }
            writer.print("<a href=\"index.html\">Back</a>");
            writer.println("</body></html>");
        } catch (SQLException | IOException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
