/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年12月18日
 * <修改描述:>
 */
package com.tx.fetch.excelmodel;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.tx.core.support.poi.excel.model.ExportExcelModel;
import com.tx.fetch.model.PersonInfo;
import com.tx.fetch.model.UnitInfo;


 /**
  * 个人信息导出excel模型
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年12月18日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class UnitInfoExportExcelModel extends ExportExcelModel<UnitInfo>{

    public UnitInfoExportExcelModel(List<UnitInfo> dataList) {
        super(dataList);
    }

    /**
     * @param index
     * @param row
     * @param data
     */
    @Override
    protected void doWriteSheet1Row(int index, Row row, UnitInfo data) {
        int cellIndex = 0;
        row.createCell(cellIndex++).setCellValue(data.getIname());
        row.createCell(cellIndex++).setCellValue("");
        row.createCell(cellIndex++).setCellValue(data.getCardNum());
        row.createCell(cellIndex++).setCellValue("法院网站");
        row.createCell(cellIndex++).setCellValue("恶意拖欠");
        row.createCell(cellIndex++).setCellValue("公司");
        row.createCell(cellIndex++).setCellValue("中华人民共和国最高人民法院公布的全国法院失信被执行人员，欠钱未还");
    }

    /**
     * @param sheet
     * @return
     */
    @Override
    protected int writeSheet1Header(Sheet sheet) {
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("个人姓名、公司名称");
        row.createCell(1).setCellValue("身份证号码");
        row.createCell(2).setCellValue("营业执照号");
        row.createCell(3).setCellValue("黑名单来源");
        row.createCell(4).setCellValue("黑名单类型");
        row.createCell(5).setCellValue("黑名单信息类型");
        row.createCell(6).setCellValue("原因描述");
        return 1;
    }
    
    
}
