package utils;

import jxl.Cell;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.*;
import jxl.format.VerticalAlignment;
import jxl.write.*;
/**
 * @author daiyihua
 * @create 2019-01-15 10:40 PM
 * @desc java 生成 excel 文件
 **/

public class ExcelUtil {


    /**
     * excel 下载（服务器生成直接输出，不产生中间文件）
     *
     * @return
     */
    @RequestMapping({"/downloadExcel"})
    public void downloadExcel(HttpServletResponse httpServletResponse) {
        OutputStream out = null;
        String filename = "excel.xls";
        try {
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "iso-8859-1"));
            httpServletResponse.setContentType("application/vnd.ms-excel;charset=UTF-8");
            httpServletResponse.setHeader("Pragma", "no-cache");
            httpServletResponse.setHeader("Cache-Control", "no-cache");
            httpServletResponse.setDateHeader("Expires", 0);

            out = httpServletResponse.getOutputStream();
            // 创建写工作簿对象
            WritableWorkbook workbook = Workbook.createWorkbook(out);
            // 工作表
            WritableSheet sheet = workbook.createSheet("excel", 0); // workbook.getSheet("Sheet2");//
            //设置字体;
            WritableFont font = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);

            WritableCellFormat cellFormat = new WritableCellFormat(font);
            //设置背景颜色;
            cellFormat.setBackground(Colour.WHITE);
            //设置边框;
            cellFormat.setBorder(Border.ALL, BorderLineStyle.DASH_DOT);
            //设置自动换行;
            cellFormat.setWrap(true);
            //设置文字居中对齐方式;
            cellFormat.setAlignment(Alignment.CENTRE);
            //设置垂直居中;
            cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            //分别给1,5,9,15列设置不同的宽度;
            sheet.setColumnView(0, 30);
            sheet.setColumnView(1, 30);
            sheet.setColumnView(2, 40);
            sheet.setColumnView(3, 30);
            //给sheet电子版中所有的列设置默认的列的宽度;
            sheet.getSettings().setDefaultColumnWidth(20);
            sheet.getSettings().setDefaultRowHeight(30 * 20);

            // 单元格
            Label label0 = new Label(0, 0, "交易时间", cellFormat);
            Label label1 = new Label(1, 0, "微信订单号", cellFormat);
            Label label2 = new Label(2, 0, "商户订单号", cellFormat);
            Label label3 = new Label(3, 0, "交易状态", cellFormat);
            Label label4 = new Label(4, 0, "货币种类", cellFormat);
            Label label5 = new Label(5, 0, "总金额", cellFormat);

            sheet.addCell(label0);
            sheet.addCell(label1);
            sheet.addCell(label2);
            sheet.addCell(label3);
            sheet.addCell(label4);
            sheet.addCell(label5);

            //上面是产生表头
            //得到里面的数据
            int n = 1;

            //给第二行设置背景、字体颜色、对齐方式等等;
            WritableFont font2 = new WritableFont(WritableFont.ARIAL, 14, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
            WritableCellFormat cellFormat2 = new WritableCellFormat(font2);
            //设置文字居中对齐方式;
            cellFormat2.setAlignment(Alignment.CENTRE);
            //设置垂直居中;
            cellFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
            cellFormat2.setBackground(Colour.WHITE);
            cellFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat2.setWrap(true);

            // 按条件查找记录
            List<String> lst = null;    // 查找记录
            if (!Checker.isNone(lst)) {
                // 遍历
                for (String v : lst) {
                    if (!Checker.isNone(v)) {


                        //交易时间
                        Label str1 = new Label(0, n, "内容1", cellFormat);
                        //微信订单号
                        Label str2 = new Label(1, n, "内容2", cellFormat);


                        sheet.addCell(str1);
                        sheet.addCell(str1);
                        n++;
                    }
                }
            }

            //开始执行写入操作
            workbook.write();
            //关闭流
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    // 批量上传模板上的数据
    @RequestMapping(params = "method=plShangchuan")
    public String plShangchuan(HttpServletRequest request,
                               HttpServletResponse response) throws BiffException, IOException {


        String path2 = request.getSession().getServletContext().getRealPath(
                "/admin/goods.xls");
        File file = new File(path2);

        // 如果不存在该文件，就新建
        if (file.exists() == false)
            file.mkdir();

        Workbook workbook = Workbook.getWorkbook(file);

        Sheet sheet = workbook.getSheet("Sheet1");// "Sheet1.5"
        int rows = sheet.getRows();// 总行数

        for (int i = 1; i < rows; i++) {
            Cell bianhao = sheet.getCell(0, i);// 商品编号
            Cell yiji = sheet.getCell(1, i);// 商品一级分类
            Cell erji = sheet.getCell(2, i);// 商品二级分类
            Cell name = sheet.getCell(3, i);// 商品三级分类

            // 根据代码输入的商品名称，自动生成商品名称首字母
            String pinyin = PinYinUtil.getFirstSpell(name.getContents());

            // 处理业务

        }

        String str = "<script>alert('上传成功!');location='/goods.do?p=findGoods'</script>";
        response.getWriter().print(str);
        return null;// "/goods.do?p=findGoods";
    }


    // 上传文件
    @RequestMapping(params = "p=uplod")
    public String upLoad(HttpServletRequest request,
                         HttpServletResponse response) throws BiffException, IOException {

        String path = request.getSession().getServletContext().getRealPath(
                "/admin");
        Map<String, String> map = Upload
                .upload(request, 10 * 1024 * 1024, path);

        String file = map.get("plUpload");

        if (Checker.isNotNull(file)
                && file.trim().equalsIgnoreCase("goods.xls")) {
            plShangchuan(request, response);
        } else {
            String str = "<script>alert('请选择要批量上传的商品信息文件');history.back();</script>";
            response.getWriter().print(str);
        }
        return null;
    }
}
