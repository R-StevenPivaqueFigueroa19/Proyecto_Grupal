package demo_jdbc.respositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import demo_jdbc.models.DriverResult;

public class DriverResultRepository {

    String jdbcUrl = "jdbc:postgresql://localhost:5432/Formula_01";
    String user = "postgres";
    String password = "admi1234";

    public List<Integer> getAvailableYears() {
        List<Integer> years = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
            String sql = "SELECT DISTINCT year FROM races ORDER BY year";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                years.add(rs.getInt("year"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return years;
    }

    public List<DriverResult> getResultByYear(int year) {
        List<DriverResult> results = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(jdbcUrl, user, password);

            String sql = "SELECT\n" +
                         "    c.name AS constructorName,\n" +
                         "    COUNT(CASE WHEN res.position = 1 THEN 1 END) AS wins,\n" +
                         "    SUM(res.points) AS total_points,\n" +
                         "    RANK() OVER (ORDER BY SUM(res.points) DESC) AS season_rank\n" +
                         "FROM\n" +
                         "    results res\n" +
                         "JOIN\n" +
                         "    races r ON res.race_id = r.race_id\n" +
                         "JOIN\n" +
                         "    constructors c ON res.constructor_id = c.constructor_id\n" +
                         "WHERE r.year = ?\n" +
                         "GROUP BY\n" +
                         "    c.name\n" +
                         "ORDER BY\n" +
                         "    season_rank;";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, year);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String constructorName = rs.getString("constructorName");
                int wins = rs.getInt("wins");
                int totalPoints = rs.getInt("total_points");
                int rank = rs.getInt("season_rank");

                DriverResult result = new DriverResult(constructorName, wins, totalPoints, rank);
                results.add(result);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return results;
    }

    }