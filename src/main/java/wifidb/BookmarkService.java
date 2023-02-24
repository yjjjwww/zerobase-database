package wifidb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookmarkService {
	public void registerBookmarkGroup(String name, int order) {
		String dbFile = "/Users/yjwmac/Documents/eclipse-workspace/zerobase-database/src/main/db/testdb";
		String sql = "insert into bookmark_group (bookmark_name, orders, register_date, edit_date) values(?, ?, ?, ?)";
		
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
			
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, order);
			preparedStatement.setString(3, strDate);
			preparedStatement.setString(4, "");
			
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
	
	public List<BookmarkGroup> getBookmarkGroup() {
		List<BookmarkGroup> bookmarkGroupList = new ArrayList<>();
		
		String dbFile = "/Users/yjwmac/Documents/eclipse-workspace/zerobase-database/src/main/db/testdb";
		String sql = "select id, bookmark_name, orders, register_date, edit_date from bookmark_group";
		
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
				BookmarkGroup bookmarkGroup = new BookmarkGroup();
				
				bookmarkGroup.setID(rs.getInt("ID"));
				bookmarkGroup.setBOOKMARK_NAME(rs.getString("BOOKMARK_NAME"));
				bookmarkGroup.setORDER(rs.getInt("ORDERS"));
				bookmarkGroup.setREGISTER_DATE(rs.getString("REGISTER_DATE"));
				bookmarkGroup.setEDIT_DATE(rs.getString("EDIT_DATE"));
				
				bookmarkGroupList.add(bookmarkGroup);
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
		
		return bookmarkGroupList;
	}
	
	public void deleteBookmarkGroup(int id) {
		String dbFile = "/Users/yjwmac/Documents/eclipse-workspace/zerobase-database/src/main/db/testdb";
		String sql = "delete from bookmark_group where id = ?";
		
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
	
	public void editBookmarkGroup(int id, String name, int order) {
		String dbFile = "/Users/yjwmac/Documents/eclipse-workspace/zerobase-database/src/main/db/testdb";
		String sql = "update bookmark_group set bookmark_name = ?, orders = ?, edit_date = ? where id = ?";
		
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
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String strDate = sdf.format(new Date());
			
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, order);
			preparedStatement.setString(3, strDate);
			preparedStatement.setInt(4, id);
			
			int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("수정 성공");
            } else {
                System.out.println("수정 실패");
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
	
	public BookmarkGroup getOneBookmarkGroup(int id) {
		BookmarkGroup bookmarkGroup = new BookmarkGroup();
		
		String dbFile = "/Users/yjwmac/Documents/eclipse-workspace/zerobase-database/src/main/db/testdb";
		String sql = "select bookmark_name, orders from bookmark_group where id = ?";
		
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
			
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				bookmarkGroup.setBOOKMARK_NAME(rs.getString("BOOKMARK_NAME"));
				bookmarkGroup.setORDER(rs.getInt("ORDERS"));
				
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
        
		return bookmarkGroup;
	}
	
	public void registerBookmark(String MRG_NO, int id) {
		String dbFile = "/Users/yjwmac/Documents/eclipse-workspace/zerobase-database/src/main/db/testdb";
		String sql = "insert into bookmark (MGR_NO, BG_ID, REGISTER_DATE) values(?, ?, ?)";
		
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
			
			preparedStatement.setString(1, MRG_NO);
			preparedStatement.setInt(2, id);
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
	
	public List<Bookmark> getBookmarkList() {
		List<Bookmark> bookmarkList = new ArrayList<>();
		String dbFile = "/Users/yjwmac/Documents/eclipse-workspace/zerobase-database/src/main/db/testdb";
		String sql = "select BOOKMARK.ID, BOOKMARK.REGISTER_DATE, BOOKMARK_GROUP.BOOKMARK_NAME, wifi.MAIN_NM from BOOKMARK "
				+ " join BOOKMARK_GROUP on BOOKMARK.BG_ID = BOOKMARK_GROUP.ID "
				+ " join wifi on wifi.MRG_NO = BOOKMARK.MGR_NO";
		
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
				Bookmark bookmark = new Bookmark();
				
				bookmark.setID(rs.getInt("id"));
				bookmark.setBookmark_name(rs.getString("BOOKMARK_NAME"));
				bookmark.setMAIN_NM(rs.getString("MAIN_NM"));
				bookmark.setREGISTER_DATE(rs.getString("REGISTER_DATE"));
				
				bookmarkList.add(bookmark);
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
		return bookmarkList;
	}
	
	public void deleteBookmark(int id) {
		String dbFile = "/Users/yjwmac/Documents/eclipse-workspace/zerobase-database/src/main/db/testdb";
		String sql = "delete from bookmark where id = ?";
		
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
	
	public Bookmark getOneBookmark(int id) {
		Bookmark bookmark = new Bookmark();
		
		String dbFile = "/Users/yjwmac/Documents/eclipse-workspace/zerobase-database/src/main/db/testdb";
		String sql = "select BOOKMARK.ID, BOOKMARK.REGISTER_DATE, BOOKMARK_GROUP.BOOKMARK_NAME, wifi.MAIN_NM from BOOKMARK "
				+ " join BOOKMARK_GROUP on BOOKMARK.BG_ID = BOOKMARK_GROUP.ID "
				+ " join wifi on wifi.MRG_NO = BOOKMARK.MGR_NO "
				+ " where BOOKMARK.ID = ?";
		
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
			
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				bookmark.setBookmark_name(rs.getString("BOOKMARK_NAME"));
				bookmark.setMAIN_NM(rs.getString("MAIN_NM"));
				bookmark.setREGISTER_DATE(rs.getString("REGISTER_DATE"));
				
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
        
		return bookmark;
	}
}
