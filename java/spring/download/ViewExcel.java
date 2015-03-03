import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.lewx.hiwifi.portal.util.StringUtil;

public class ViewExcel extends AbstractExcelView{
	String fileName;
	public ViewExcel(){
		this("导出数据");
	}
	public ViewExcel(String fileName){
		this.fileName = fileName+".xls";
	}
	@Override
	public void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String filename = fileName;//"抽奖活动人员名单.xls";//设置下载时客户端Excel的名称       
        filename = StringUtil.encodeFilename(filename, request);//处理中文文件名    
        response.setContentType("application/vnd.ms-excel");       
        response.setHeader("Content-disposition", "attachment;filename=" + filename);       
        OutputStream ouputStream = response.getOutputStream();       
        workbook.write(ouputStream);       
        ouputStream.flush();       
        ouputStream.close();   
	}
	
}

