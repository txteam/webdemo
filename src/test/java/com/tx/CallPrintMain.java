/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年11月4日
 * <修改描述:>
 */
package com.tx;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaName;
import javax.print.attribute.standard.Sides;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年11月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CallPrintMain {
    
    public static void searchPrintService(String printerName) {
        //
        PrintService printService = null;
        if (printerName != null) {
            //获得本台电脑连接的所有打印机
            PrintService[] printServices = PrinterJob.lookupPrintServices();
            if (printServices == null || printServices.length == 0) {
                System.out.print("打印失败，未找到可用打印机，请检查。");
                return;
            }
            //匹配指定打印机
            for (int i = 0; i < printServices.length; i++) {
                System.out.println(printServices[i].getName());
                if (printServices[i].getName().contains(printerName)) {
                    printService = printServices[i];
                    break;
                }
            }
            if (printService == null) {
                System.out.print("打印失败，未找到名称为" + printerName + "的打印机，请检查。");
                return;
            }
        }
    }
    
    public static void printPDF(File file, PrintService printService)
            throws Exception {
        PDDocument document = null;
        try {
            document = PDDocument.load(file);
            PrinterJob printJob = PrinterJob.getPrinterJob();
            printJob.setJobName(file.getName());
            //设置纸张及缩放
            PDFPrintable pdfPrintable = new PDFPrintable(document,
                    Scaling.ACTUAL_SIZE);
            //设置多页打印
            Book book = new Book();
            PageFormat pageFormat = new PageFormat();
            //设置打印方向
            pageFormat.setOrientation(PageFormat.PORTRAIT);//纵向
            pageFormat.setPaper(getPaper());//设置纸张
            book.append(pdfPrintable, pageFormat, document.getNumberOfPages());
            printJob.setPageable(book);
            printJob.setCopies(1);//设置打印份数
            //添加打印属性
            HashPrintRequestAttributeSet pars = new HashPrintRequestAttributeSet();
            pars.add(Sides.DUPLEX); //设置单双页
            printJob.print(pars);
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static Paper getPaper() {
        Paper paper = new Paper();
        // 默认为A4纸张，对应像素宽和高分别为 595, 842
        int width = 595;
        int height = 842;
        // 设置边距，单位是像素，10mm边距，对应 28px
        int marginLeft = 10;
        int marginRight = 0;
        int marginTop = 10;
        int marginBottom = 0;
        paper.setSize(width, height);
        // 下面一行代码，解决了打印内容为空的问题
        paper.setImageableArea(marginLeft,
                marginRight,
                width - (marginLeft + marginRight),
                height - (marginTop + marginBottom));
        return paper;
    }
    
    // 传入文件和打印机名称
    public static void printImage(File file, PrintService printService)
            throws PrintException {
        if (file == null) {
            System.err.println("缺少打印文件");
        }
        InputStream fis = null;
        try {
            // 设置打印格式，如果未确定类型，可选择autosense
            DocFlavor flavor = DocFlavor.INPUT_STREAM.JPEG;
            switch (StringUtils
                    .getFilenameExtension(file.getPath().toUpperCase())
                    .toUpperCase()) {
                case "PNG":
                    flavor = DocFlavor.INPUT_STREAM.PNG;
                    break;
                case "GIF":
                    flavor = DocFlavor.INPUT_STREAM.GIF;
                    break;
                case "JPG":
                    flavor = DocFlavor.INPUT_STREAM.JPEG;
                    break;
                default:
                    flavor = DocFlavor.INPUT_STREAM.JPEG;
                    break;
            }
            // 设置打印参数
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            aset.add(new Copies(1)); //份数
            //aset.add(MediaSize.ISO.A4); //纸张
            //WEWaset.add(Finishings.STAPLE);//装订
            aset.add(Sides.DUPLEX);//单双面
            // 定位打印服务
            fis = new FileInputStream(file); // 构造待打印的文件流
            Doc doc = new SimpleDoc(fis, flavor, null);
            DocPrintJob job = printService.createPrintJob(); // 创建打印作业
            job.print(doc, aset);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } finally {
            // 关闭打印的文件流
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    // 传入文件和打印机名称
    public static void printTXT(File file, PrintService printService)
            throws PrintException {
        if (file == null) {
            System.err.println("缺少打印文件");
        }
        InputStream fis = null;
        try {
            // 设置打印格式，如果未确定类型，可选择autosense
            DocFlavor flavor = DocFlavor.INPUT_STREAM.TEXT_HTML_UTF_8;
            switch (StringUtils.getFilenameExtension(file.getPath())
                    .toUpperCase()) {
                case "TXT":
                    flavor = DocFlavor.INPUT_STREAM.TEXT_HTML_UTF_8;
                    break;
                case "GIF":
                    flavor = DocFlavor.INPUT_STREAM.GIF;
                    break;
                case "JPG":
                    flavor = DocFlavor.INPUT_STREAM.JPEG;
                    break;
                default:
                    flavor = DocFlavor.INPUT_STREAM.JPEG;
                    break;
            }
            // 设置打印参数
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            aset.add(new Copies(1)); //份数
            //aset.add(MediaSize.ISO.A4); //纸张
            //WEWaset.add(Finishings.STAPLE);//装订
            aset.add(Sides.DUPLEX);//单双面
            // 定位打印服务
            fis = new FileInputStream(file); // 构造待打印的文件流
            Doc doc = new SimpleDoc(fis, flavor, null);
            DocPrintJob job = printService.createPrintJob(); // 创建打印作业
            job.print(doc, aset);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } finally {
            // 关闭打印的文件流
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void wordToHtml(String fileName, String outPutFile)
            throws TransformerException, IOException,
            ParserConfigurationException {
        HWPFDocument wordDocument = new HWPFDocument(
                new FileInputStream(fileName));// WordToHtmlUtils.loadDoc(new
                                                                                            // FileInputStream(inputFile));
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder()
                        .newDocument());
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            @Override
            public String savePicture(byte[] content, PictureType pictureType,
                    String suggestedName, float widthInches,
                    float heightInches) {
                return "test/" + suggestedName;
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        // save pictures
        List pics = wordDocument.getPicturesTable().getAllPictures();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                Picture pic = (Picture) pics.get(i);
                System.out.println();
                try {
                    pic.writeImageContent(new FileOutputStream(
                            "D:/test/" + pic.suggestFullFileName()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(out);
        
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "GB2312");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        out.close();
        //writeFile(new String(out.toByteArray()), outPutFile);
    }
    
    public static void main(String[] args) throws Exception {
        PrintService printService = PrintServiceLookup
                .lookupDefaultPrintService();
        
        String path = "E:\\jb\\533";
        Collection<File> fs = FileUtils.listFiles(new File(path),
                new String[] { "png", "jpg", "gif", "pdf", "doc", "docx" },
                true);
        if (true) {
            for (File f : fs) {
                System.out.println(f.getPath());
                //                if ("pdf".equals(
                //                        StringUtils.getFilenameExtension(f.getPath()))) {
                //                    printPDF(f, printService);
                //                } else {
                //                    System.out.println(f.getPath());
                //                    printImage(f, printService);
                //                }
            }
            return;
        }
        
        // 构建打印请求属性集
        HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        // 设置打印格式，因为未确定类型，所以选择autosense
        DocFlavor flavor = DocFlavor.INPUT_STREAM.PNG;
        pras.add(MediaName.ISO_A4_TRANSPARENT);//A4纸张
        //遍历
        //PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras); 
        //   
        //for (PrintService printService2 : printService) {
        //  logger.info("本机可使用打印机列表：==================="+printService2);
        //}
        // 定位默认的打印服务
        
        System.out.println("打印工具选择打印机为：===================" + printService);
        //            try {
        //                DocPrintJob job = defaultService.createPrintJob(); // 创建打印作业
        //                FileInputStream fis = new FileInputStream(file); // 构造待打印的文件流
        //                DocAttributeSet das = new HashDocAttributeSet();
        //                Doc doc = new SimpleDoc(fis, flavor, das);
        //                job.print(doc, pras);
        //            } catch (Exception e) {
        //                e.printStackTrace();
        //                logger.info("打印异常",e);
        //               throw new Exception();
        //            }
        
        try {
            DocPrintJob job = printService.createPrintJob(); // 创建打印作业
            FileInputStream fis = new FileInputStream(
                    "d:\\Users\\PengQingyang\\Desktop\\吉普赛女郎之死\\组织者手册.docx"); // 构造待打印的文件流
            DocAttributeSet das = new HashDocAttributeSet();
            Doc doc = new SimpleDoc(fis, flavor, das);
            job.print(doc, pras);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
