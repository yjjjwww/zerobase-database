
package wifidb;

import java.util.List;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class TbPublicWifiInfo {

    private Integer list_total_count;
    private Result RESULT;
    private List<Row> row;
    
	public Integer getList_total_count() {
		return list_total_count;
	}
	public void setList_total_count(Integer list_total_count) {
		this.list_total_count = list_total_count;
	}
	public Result getRESULT() {
		return RESULT;
	}
	public void setRESULT(Result rESULT) {
		RESULT = rESULT;
	}
	public List<Row> getRow() {
		return row;
	}
	public void setRow(List<Row> row) {
		this.row = row;
	}

}
