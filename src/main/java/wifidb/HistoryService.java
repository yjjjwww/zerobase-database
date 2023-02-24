package wifidb;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryService {
	public void registerHistory(double lat, double lnt) {
		String dbFile = "/Users/yjwmac/Documents/eclipse-workspace/zerobase-database/src/main/db/testdb";
		String sql = "insert into location_history (LAT, LNT, HISTORY_DATE) values(?, ?, ?)";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setDouble(1, lat);
			preparedStatement.setDouble(2, lnt);
			preparedStatement.setString(3, strDate);
			
			int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("저장 성공");
            } else {
                System.out.println("저장 실패");
            }
			
			
		} catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
	}
	
	public List<History> getHistory() {
		List<History> historyList = new ArrayList<>();
		
		String dbFile = "/Users/yjwmac/Documents/eclipse-workspace/zerobase-database/src/main/db/testdb";
		String sql = "select id, lat, lnt, history_date from location_history";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
        	
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			preparedStatement = connection.prepareStatement(sql);
			
			rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	int id = rs.getInt("id");
            	double lat = rs.getDouble("lat");
            	double lnt = rs.getDouble("lnt");
            	String history_date = rs.getString("history_date");
            	
            	History history = new History();
            	history.setId(id);
            	history.setLat(lat);
            	history.setLnt(lnt);
            	history.setHistory_date(history_date);
            	
            	historyList.add(history);
            	
            }
			
			
		} catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        return historyList;
	}
	
	public void deleteHistory(int id) {
		
		String dbFile = "/Users/yjwmac/Documents/eclipse-workspace/zerobase-database/src/main/db/testdb";
		String sql = "delete from location_history where id = ?";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
        	connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, id);
			
			int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("삭제 성공");
            } else {
                System.out.println("삭제 실패");
            }
			
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
	}
}
