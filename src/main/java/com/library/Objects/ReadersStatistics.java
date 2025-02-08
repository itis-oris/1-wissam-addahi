package com.library.Objects;

import com.library.Data.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class ReadersStatistics {

    public static void main(String[] args) {
        ReadersStatistics stats = new ReadersStatistics();
        stats.calculateStatistics();
    }

    public void calculateStatistics() {
        String totalQuery = "SELECT COUNT(*) AS totalReaders FROM Readers";
        String educationLevelQuery = "SELECT educationLevel, COUNT(*) AS count FROM Readers GROUP BY educationLevel";
        String academicDegreeQuery = "SELECT academicDegree, COUNT(*) AS count FROM Readers WHERE academicDegree IS NOT NULL GROUP BY academicDegree";

        try (Connection connection = DataBaseConnection.getConnection()) {
            int totalReaders = getTotalReaders(connection, totalQuery);
            System.out.println("Total Readers: " + totalReaders);

            if (totalReaders > 0) {
                // Percentages for education levels
                System.out.println("\nPercentages by Education Level:");
                Map<String, Double> educationPercentages = getPercentages(connection, educationLevelQuery, totalReaders);
                educationPercentages.forEach((level, percentage) ->
                        System.out.printf("%s: %.2f%%\n", level, percentage));

                // Percentages for academic degrees, based on higher education only
                System.out.println("\nPercentages by Academic Degree:");
                int higherReaders = educationPercentages.getOrDefault("Higher", 0.0).intValue(); // Get higher education readers count
                if (higherReaders > 0) {
                    Map<String, Double> degreePercentages = getPercentages(connection, academicDegreeQuery, higherReaders);
                    degreePercentages.forEach((degree, percentage) ->
                            System.out.printf("%s: %.2f%%\n", degree, percentage));
                } else {
                    System.out.println("No readers with academic degrees.");
                }
            } else {
                System.out.println("No readers in the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getTotalReaders(Connection connection, String query) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("totalReaders");
            }
        }
        return 0;
    }

    private Map<String, Double> getPercentages(Connection connection, String query, int total) throws Exception {
        Map<String, Double> percentages = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String category = resultSet.getString(1); // Education level or academic degree
                int count = resultSet.getInt("count");
                percentages.put(category, (count / (double) total) * 100);
            }
        }
        return percentages;
    }
}
