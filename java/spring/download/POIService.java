import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.lewx.hiwifi.core.bean.Statistics;
import com.lewx.hiwifi.core.bean.TbArea;
import com.lewx.hiwifi.core.bean.User;
import com.lewx.hiwifi.core.dao.StatisticsReadDao;
import com.lewx.hiwifi.core.dao.TbAreaDao;
import com.lewx.hiwifi.core.dao.LegacyUserDao;
import com.lewx.hiwifi.core.service.POIService;
import com.lewx.hiwifi.core.util.DateFormat;
import com.lewx.hiwifi.core.util.DefaultValueManager;

public class POIServiceImpl extends JdbcDaoSupport implements POIService {
	public Workbook ResultSetOutput(String sql) {
		Workbook workbook = new HSSFWorkbook();
		final Sheet sheet = workbook.createSheet();
		getJdbcTemplate().query(sql, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ResultSetMetaData rsmd = rs.getMetaData();
				int rowno = rs.getRow();
				Row row;
				if(rowno == 1) {
					row = sheet.createRow(0);
					for(int i = 0;i < rsmd.getColumnCount();i ++) {
						String name = rsmd.getColumnName(i+1);
						Cell cell = row.createCell(i);
						cell.setCellValue(name);
					}
					
				}
				row = sheet.createRow(rowno);
				for(int i = 0;i < rsmd.getColumnCount();i ++) {
//					String name = rsmd.getColumnName(i+1);
					Cell cell = row.createCell(i);
					cell.setCellValue(rs.getString(i+1));
				}
			}
			
		});
		return workbook ;
//		return ResultSetOutput(sql, new ArrayList<String>());
	}
}
