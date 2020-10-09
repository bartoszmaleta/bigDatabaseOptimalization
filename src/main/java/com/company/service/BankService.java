package com.company.service;

import com.company.connector.JSONService;
import com.company.connector.PostgreSQLJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankService {
    private final PostgreSQLJDBC database;
    private Connection c;

    public BankService(String path) {
        this.database = new PostgreSQLJDBC(
                new JSONService()
                        .readEnvironment2(path));
    }

    public void showNumberOfCustomers() throws SQLException {
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT customers_count();");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customersCount = rs.getInt("customers_count");
                System.out.println("Customers Count = " + customersCount);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
    }

    public int showSumOfProvidedNumberCustomersBalances(int numberOfCustomersToSumBalances) throws SQLException {
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT richest_balances_sum(?);");
            ps.setString(1, String.valueOf(numberOfCustomersToSumBalances));
            ResultSet rs = ps.executeQuery();
            rs.next();

            return rs.getInt("richest_balances_sum");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return 0;
    }

    public String getFirstFiveCustomersInfo() throws SQLException {
        StringBuilder sb = new StringBuilder();
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM customer_information_limit_five;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("ID");
                String firstName = rs.getString("First name");
                String secondName = rs.getString("Second name");
                String surname = rs.getString("Surname");
                String street = rs.getString("Street");
                String city = rs.getString("City");
                String custInfo = String.format("%d %s %s %s %s %s"
                        , customerId
                        , firstName
                        , surname
                        , secondName
                        , street
                        , city);
                sb.append(custInfo).append("\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return sb.toString();
    }

    public String getCardsTypes() throws SQLException {
        StringBuilder sb = new StringBuilder();
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM cards_types;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int cardId = rs.getInt("id");
                String cardType = rs.getString("type");
                String cardRecord = String.format("%d %s"
                        , cardId
                        , cardType);
                sb.append(cardRecord).append("\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return sb.toString();
    }

    public String getFiveOldestCustomers() throws SQLException {
        StringBuilder sb = new StringBuilder();
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM five_oldest_customer_information;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("ID");
                String firstName = rs.getString("First name");
                String secondName = rs.getString("Second name");
                String surname = rs.getString("Surname");
                String street = rs.getString("Street");
                String city = rs.getString("City");
                String custInfo = String.format("%d %s %s %s %s %s"
                        , customerId
                        , firstName
                        , surname
                        , secondName
                        , street
                        , city);
                sb.append(custInfo).append("\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return sb.toString();
    }

    public String getFiveYoungestCustomers() throws SQLException {
        StringBuilder sb = new StringBuilder();
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM five_youngest_customer_information;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("ID");
                String firstName = rs.getString("First name");
                String secondName = rs.getString("Second name");
                String surname = rs.getString("Surname");
                String street = rs.getString("Street");
                String city = rs.getString("City");
                String custInfo = String.format("%d %s %s %s %s %s"
                        , customerId
                        , firstName
                        , surname
                        , secondName
                        , street
                        , city);
                sb.append(custInfo).append("\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return sb.toString();
    }

    public String getFiveLastTransactions() throws SQLException {
        StringBuilder sb = new StringBuilder();
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM five_last_transactions_value;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int value = rs.getInt("value");
                String date = String.valueOf(rs.getDate("date"));
                String transaction = String.format("%d %d %s"
                        , id
                        , value
                        , date);
                sb.append(transaction).append("\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return sb.toString();
    }

    public int getAverageBalance() throws SQLException {
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT average_balance();");
            ResultSet rs = ps.executeQuery();
            rs.next();

            return rs.getInt("average_balance");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return 0;
    }

    public String getMaxAndMinBalances() throws SQLException {
        return getMinBalance() + getMaxBalance();
    }

    private String getMaxBalance() throws SQLException {
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT max_balance();");
            ResultSet rs = ps.executeQuery();
            rs.next();

            return "\nmax balance = " + rs.getInt("max_balance");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return "There is none.";
    }

    private String getMinBalance() throws SQLException {
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT min_balance();");
            ResultSet rs = ps.executeQuery();
            rs.next();

            return "min balance = " + rs.getInt("min_balance");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return "There is none.";
    }

    public int getAverageTransactionValue() throws SQLException {
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT average_transaction_value();");
            ResultSet rs = ps.executeQuery();
            rs.next();

            return rs.getInt("average_transaction_value");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return 0;
    }

    public int getCountOfTransactionsWhereValueGreaterThanProvidedValue(int valueProvided) throws SQLException {
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT count_transactions_where_value_greater_than_parameter(" + valueProvided + ");");
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("count_transactions_where_value_greater_than_parameter");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return 0;
    }

    public String getSelectsInfo() throws SQLException {
        return getSelectWithoutIndex() + getSelectWithIndex();
    }

    private String getSelectWithoutIndex() throws SQLException {
        PostgreSQLJDBC secondDatabase = new PostgreSQLJDBC(
                new JSONService()
                        .readEnvironment2(
                                "src/main/resources/environmentWithoutIndex.json"));
        StringBuilder sb = new StringBuilder();
        try {
            Connection connection = secondDatabase.getConnection2();
            PreparedStatement ps = connection.prepareStatement("SELECT explain_select_customer_by_first_name();");
            ResultSet rs = ps.executeQuery();
            rs.next();
            String select = rs.getString("explain_select_customer_by_first_name");
            sb.append("Select without index:\n").append(select);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            secondDatabase.disconnect();
        }
        return sb.toString().length() != 0 ? sb.toString() : "There is no customer";
    }

    private String getSelectWithIndex() throws SQLException {
        StringBuilder sb = new StringBuilder();
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT explain_select_customer_by_first_name();");
            ResultSet rs = ps.executeQuery();
            rs.next();

            String select = rs.getString("explain_select_customer_by_first_name");
            sb.append("\n\nSelect with index:\n").append(select);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return sb.toString().length() != 0 ? sb.toString() : "There is no customer";
    }

    public String getTransactionsExplainInfo() throws SQLException {
        return getSelectTransactionWithoutIndex() + getSelectTransactionWithIndex();
    }

    private String getSelectTransactionWithIndex() throws SQLException {
        StringBuilder sb = new StringBuilder();
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT explain_select_transaction_by_value();");
            ResultSet rs = ps.executeQuery();
            rs.next();

            String select = rs.getString("explain_select_transaction_by_value");
            sb.append("\n\nSelect with index:\n").append(select);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return sb.toString().length() != 0 ? sb.toString() : "There is no transaction";
    }

    private String getSelectTransactionWithoutIndex() throws SQLException {
        PostgreSQLJDBC secondDatabase = new PostgreSQLJDBC(
                new JSONService()
                        .readEnvironment2(
                                "src/main/resources/environmentWithoutIndex.json"));
        StringBuilder sb = new StringBuilder();
        try {
            Connection connection = secondDatabase.getConnection2();
            PreparedStatement ps = connection.prepareStatement("SELECT explain_select_transaction_by_value();");
            ResultSet rs = ps.executeQuery();
            rs.next();
            String select = rs.getString("explain_select_transaction_by_value");
            sb.append("Select without index:\n").append(select);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            secondDatabase.disconnect();
        }
        return sb.toString().length() != 0 ? sb.toString() : "There is no transaction";
    }

    public String getCardExplainInfo() throws SQLException {
        return getSelectCardWithoutIndex() + getSelectCardWithIndex();
    }

    private String getSelectCardWithoutIndex() throws SQLException {
        PostgreSQLJDBC secondDatabase = new PostgreSQLJDBC(
                new JSONService()
                        .readEnvironment2(
                                "src/main/resources/environmentWithoutIndex.json"));
        StringBuilder sb = new StringBuilder();
        try {
            Connection connection = secondDatabase.getConnection2();
            PreparedStatement ps = connection.prepareStatement("SELECT explain_select_card_by_expiration_date();");
            ResultSet rs = ps.executeQuery();
            rs.next();
            String select = rs.getString("explain_select_card_by_expiration_date");
            sb.append("Select without index:\n").append(select);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            secondDatabase.disconnect();
        }
        return sb.toString().length() != 0 ? sb.toString() : "There is no card";
    }

    private String getSelectCardWithIndex() throws SQLException {
        StringBuilder sb = new StringBuilder();
        try {
            c = database.getConnection2();
            PreparedStatement ps = c.prepareStatement("SELECT explain_select_card_by_expiration_date();");
            ResultSet rs = ps.executeQuery();
            rs.next();
            String select = rs.getString("explain_select_card_by_expiration_date");
            sb.append("\n\nSelect with index:\n").append(select);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return sb.toString().length() != 0 ? sb.toString() : "There is no card";
    }
}
