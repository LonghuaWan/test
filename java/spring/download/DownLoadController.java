import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lewx.hiwifi.core.service.POIService;
import com.lewx.hiwifi.portal.view.ViewExcel;

@Controller
@RequestMapping("/download")
public class DownloadController {
	
	@Autowired
	POIService poiService;
	
	@RequestMapping(value="/promotion.shtml", method = RequestMethod.GET)
	public ModelAndView downloadPromotion(ModelMap model,HttpServletRequest request, HttpServletResponse response){
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id, statis_date AS '日期',");
		sb.append("promotion_id,download_count AS '下载次数', install_count AS '安装次数',");
		sb.append("active_count AS '激活次数', run_count AS '运行次数', uninstall_count AS '卸载次数', finished_count AS '完成次数'");
		sb.append(" FROM luhu.promotion_statis ");
		sb.append(" WHERE ");
		sb.append(" statis_date  BETWEEN ");
		sb.append(" '");
		sb.append(startTime);
		sb.append("' ");
		sb.append(" AND ");
		sb.append(" '");
		sb.append(endTime);
		sb.append("' ");
		
		String SQL = sb.toString();
    	HSSFWorkbook wb =(HSSFWorkbook)poiService.ResultSetOutput(SQL);
    	Date d = new Date();
    	SimpleDateFormat  sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
    	ViewExcel viewExcel = new ViewExcel("Promotion数据_"+sdf.format(d));
    	try {
			viewExcel.buildExcelDocument(null, wb, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView(viewExcel,model);
	}
}
