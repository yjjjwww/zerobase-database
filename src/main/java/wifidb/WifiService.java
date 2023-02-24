package wifidb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class WifiService {
	
	public int getTotalCount() {
		int totalCount = 0;
		try {
			String url = "http://openapi.seoul.go.kr:8088/5543744453796a6a34384945614868/json/TbPublicWifiInfo/1/1/";
			OkHttpClient client = new OkHttpClient();
			
			Request.Builder builder = new Request.Builder().url(url).get();
			Request request = builder.build();
			
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				ResponseBody body = response.body();
				if (body != null) {
					String result = body.string();
					Gson gson = new Gson();
					WifiDTO wifiDTO = gson.fromJson(result, WifiDTO.class);
					totalCount = wifiDTO.getTbPublicWifiInfo().getList_total_count();
				}
			} else {
				System.err.println("Error Occured");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	public void insertWifi(int totalCount) {
		int start = 1;
		int end = 1000;
		String dbFile = "/Users/yjwmac/Documents/eclipse-workspace/zerobase-database/src/main/db/testdb";
		String sql = "insert into wifi (MRG_NO, WRDOFC, MAIN_NM, ADRES1, ADRES2, INSTL_FLOOR, INSTL_TY, INSTL_MBY, SVC_SE, CMCWR, CNSTC_YEAR, INOUT_DOOR, REMARS3, LAT, LNT, WORK_DTTM)"
				+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
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
		} catch (Exception e) {
	        e.printStackTrace();
	    }
	
		try {
			while (end <= totalCount) {
				String url = "http://openapi.seoul.go.kr:8088/5543744453796a6a34384945614868/json/TbPublicWifiInfo/" + start + "/" + end + "/";
				OkHttpClient client = new OkHttpClient();
				
				Request.Builder builder = new Request.Builder().url(url).get();
				Request request = builder.build();
				
				Response response = client.newCall(request).execute();
				if (response.isSuccessful()) {
					ResponseBody body = response.body();
					if (body != null) {
						String result = body.string();
						Gson gson = new Gson();
						WifiDTO wifiDTO = gson.fromJson(result, WifiDTO.class);
						int rowCount = wifiDTO.getTbPublicWifiInfo().getRow().size();
						for (int i = 0; i < rowCount; i++) {
							Row rowItem = wifiDTO.getTbPublicWifiInfo().getRow().get(i);
							try {
								
								preparedStatement.setString(1, rowItem.getX_SWIFI_MGR_NO());
								preparedStatement.setString(2, rowItem.getX_SWIFI_WRDOFC());
								preparedStatement.setString(3, rowItem.getX_SWIFI_MAIN_NM());
								preparedStatement.setString(4, rowItem.getX_SWIFI_ADRES1());
								preparedStatement.setString(5, rowItem.getX_SWIFI_ADRES2());
								preparedStatement.setString(6, rowItem.getX_SWIFI_INSTL_FLOOR());
								preparedStatement.setString(7, rowItem.getX_SWIFI_INSTL_TY());
								preparedStatement.setString(8, rowItem.getX_SWIFI_INSTL_MBY());
								preparedStatement.setString(9, rowItem.getX_SWIFI_SVC_SE());
								preparedStatement.setString(10, rowItem.getX_SWIFI_CMCWR());
								preparedStatement.setString(11, rowItem.getX_SWIFI_CNSTC_YEAR());
								preparedStatement.setString(12, rowItem.getX_SWIFI_INOUT_DOOR());
								preparedStatement.setString(13, rowItem.getX_SWIFI_REMARS3());
								preparedStatement.setDouble(14, Double.parseDouble(rowItem.getLAT()));
								preparedStatement.setDouble(15, Double.parseDouble(rowItem.getLNT()));
								preparedStatement.setString(16, rowItem.getWORK_DTTM());
								
								int affectedRows = preparedStatement.executeUpdate();
								
								if (affectedRows <= 0) {
					                System.out.println("저장 실패");
					            }
							} catch (SQLException e) {
					            throw new RuntimeException(e);
					        }
							
														
						}
						if (end == totalCount) {
							break;
						} else if (end + 1000 > totalCount) {
							start = end + 1;
							end = totalCount;
						} else {
							start = end + 1;
							end = end + 1000;
						}
					}
				} else {
					System.err.println("Error Occured");
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
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
	
	public List<WifiInformation> getWifiByDistance(double lat, double lnt) {
		List<WifiInformation> wifiList = new ArrayList<>();
		
		String dbFile = "/Users/yjwmac/Documents/eclipse-workspace/zerobase-database/src/main/db/testdb";
		String wifiCol = " MRG_NO, WRDOFC, MAIN_NM, ADRES1, ADRES2, INSTL_FLOOR, INSTL_TY, INSTL_MBY, SVC_SE, CMCWR, CNSTC_YEAR, INOUT_DOOR, REMARS3, LAT, LNT, WORK_DTTM ";
		String distanceCol = " (6371 * acos(cos(radians(LAT)) * cos(radians(CUR_LAT)) " + 
				" * cos(radians(CUR_LNT) - radians(LNT)) " +
				" + sin(radians(LAT)) * sin(radians(CUR_LAT)))) " +
				"AS DISTANCE";
		String sql = "select T.*, " + distanceCol + 
				" from (select " + wifiCol + ", ? as CUR_LAT, ? as CUR_LNT from WIFI) T" + 
				" order by DISTANCE " +
				" limit 20 ";
		
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
			preparedStatement.setDouble(1, lat);
			preparedStatement.setDouble(2, lnt);
			
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				WifiInformation wifi = new WifiInformation();
				
				wifi.setDistance(Math.round(rs.getDouble("DISTANCE") * 10000.0) / 10000.0);
				wifi.setMRG_NO(rs.getString("MRG_NO"));
				wifi.setWRDOFC(rs.getString("WRDOFC"));
				wifi.setMAIN_NM(rs.getString("MAIN_NM"));
				wifi.setADRES1(rs.getString("ADRES1"));
				wifi.setADRES2(rs.getString("ADRES2"));
				wifi.setINSTL_FLOOR(rs.getString("INSTL_FLOOR"));
				wifi.setINSTL_TY(rs.getString("INSTL_TY"));
				wifi.setINSTL_MBY(rs.getString("INSTL_MBY"));
				wifi.setCMCWR(rs.getString("CMCWR"));
				wifi.setSVC_SE(rs.getString("SVC_SE"));
				wifi.setCNSTC_YEAR(rs.getString("CNSTC_YEAR"));
				wifi.setINOUT_DOOR(rs.getString("INOUT_DOOR"));
				wifi.setREMARS3(rs.getString("REMARS3"));
				wifi.setLAT(rs.getDouble("LAT"));
				wifi.setLNT(rs.getDouble("LNT"));
				wifi.setWORK_DTTM(rs.getString("WORK_DTTM"));
				
				wifiList.add(wifi);
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
		return wifiList;
	}
	
	public WifiInformation getSelectedWifi(String MRG_NO) {
		WifiInformation wifi = new WifiInformation();
		
		String dbFile = "/Users/yjwmac/Documents/eclipse-workspace/zerobase-database/src/main/db/testdb";
		String wifiCol = " MRG_NO, WRDOFC, MAIN_NM, ADRES1, ADRES2, INSTL_FLOOR, INSTL_TY, INSTL_MBY, SVC_SE, CMCWR, CNSTC_YEAR, INOUT_DOOR, REMARS3, LAT, LNT, WORK_DTTM ";
		
		String sql = "select * from WIFI where MRG_NO = ?";
		
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
			preparedStatement.setString(1, MRG_NO);
			
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				wifi.setDistance(0);
				wifi.setMRG_NO(rs.getString("MRG_NO"));
				wifi.setWRDOFC(rs.getString("WRDOFC"));
				wifi.setMAIN_NM(rs.getString("MAIN_NM"));
				wifi.setADRES1(rs.getString("ADRES1"));
				wifi.setADRES2(rs.getString("ADRES2"));
				wifi.setINSTL_FLOOR(rs.getString("INSTL_FLOOR"));
				wifi.setINSTL_TY(rs.getString("INSTL_TY"));
				wifi.setINSTL_MBY(rs.getString("INSTL_MBY"));
				wifi.setCMCWR(rs.getString("CMCWR"));
				wifi.setSVC_SE(rs.getString("SVC_SE"));
				wifi.setCNSTC_YEAR(rs.getString("CNSTC_YEAR"));
				wifi.setINOUT_DOOR(rs.getString("INOUT_DOOR"));
				wifi.setREMARS3(rs.getString("REMARS3"));
				wifi.setLAT(rs.getDouble("LAT"));
				wifi.setLNT(rs.getDouble("LNT"));
				wifi.setWORK_DTTM(rs.getString("WORK_DTTM"));
				
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
		return wifi;
	}
	
}
