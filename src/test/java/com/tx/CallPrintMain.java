///*
// * 描          述:  <描述>
// * 修  改   人:  PengQingyang
// * 修改时间:  2020年11月4日
// * <修改描述:>
// */
//package com.tx;
//
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.awt.print.Book;
//import java.awt.print.PageFormat;
//import java.awt.print.Paper;
//import java.awt.print.PrinterJob;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map.Entry;
//
//import javax.imageio.ImageIO;
//import javax.print.Doc;
//import javax.print.DocFlavor;
//import javax.print.DocPrintJob;
//import javax.print.PrintException;
//import javax.print.PrintService;
//import javax.print.PrintServiceLookup;
//import javax.print.SimpleDoc;
//import javax.print.attribute.HashPrintRequestAttributeSet;
//import javax.print.attribute.PrintRequestAttributeSet;
//import javax.print.attribute.standard.Copies;
//import javax.print.attribute.standard.MediaSize;
//import javax.print.attribute.standard.Sides;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.printing.PDFPrintable;
//import org.apache.pdfbox.printing.Scaling;
//import org.apache.poi.hwpf.HWPFDocument;
//import org.apache.poi.hwpf.converter.PicturesManager;
//import org.apache.poi.hwpf.converter.WordToHtmlConverter;
//import org.apache.poi.hwpf.usermodel.Picture;
//import org.apache.poi.hwpf.usermodel.PictureType;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.core.io.InputStreamSource;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.util.StringUtils;
//import org.w3c.dom.Document;
//
//import com.itextpdf.text.Image;
//import com.itextpdf.text.Rectangle;
//import com.itextpdf.text.pdf.PdfWriter;
//
///**
// * <功能简述>
// * <功能详细描述>
// * 
// * @author  PengQingyang
// * @version  [版本号, 2020年11月4日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//public class CallPrintMain {
//    
//    public static void searchPrintService(String printerName) {
//        //
//        PrintService printService = null;
//        if (printerName != null) {
//            //获得本台电脑连接的所有打印机
//            PrintService[] printServices = PrinterJob.lookupPrintServices();
//            if (printServices == null || printServices.length == 0) {
//                System.out.print("打印失败，未找到可用打印机，请检查。");
//                return;
//            }
//            //匹配指定打印机
//            for (int i = 0; i < printServices.length; i++) {
//                System.out.println(printServices[i].getName());
//                if (printServices[i].getName().contains(printerName)) {
//                    printService = printServices[i];
//                    break;
//                }
//            }
//            if (printService == null) {
//                System.out.print("打印失败，未找到名称为" + printerName + "的打印机，请检查。");
//                return;
//            }
//        }
//    }
//    
//    public static void printPDF(String filename, File file,
//            PrintService printService) throws Exception {
//        PDDocument document = null;
//        try {
//            document = PDDocument.load(file);
//            PrinterJob printJob = PrinterJob.getPrinterJob();
//            printJob.setJobName(file.getName());
//            //设置纸张及缩放
//            PDFPrintable pdfPrintable = new PDFPrintable(document,
//                    Scaling.ACTUAL_SIZE);
//            //设置多页打印
//            Book book = new Book();
//            PageFormat pageFormat = new PageFormat();
//            //设置打印方向
//            if (filename.contains("短边") || filename.contains("横")
//                    || file.getPath().contains("短边")
//                    || file.getPath().contains("横")) {
//                pageFormat.setOrientation(PageFormat.LANDSCAPE);//横向
//                pageFormat.setPaper(getPaper());//设置纸张
//            } else {
//                pageFormat.setOrientation(PageFormat.PORTRAIT);//纵向
//                pageFormat.setPaper(getPaper());//设置纸张
//            }
//            
//            book.append(pdfPrintable, pageFormat, document.getNumberOfPages());
//            printJob.setPageable(book);
//            printJob.setCopies(1);//设置打印份数
//            //添加打印属性
//            HashPrintRequestAttributeSet pars = new HashPrintRequestAttributeSet();
//            if (filename.contains("单面") || file.getPath().contains("单面")) {
//                pars.add(Sides.ONE_SIDED); //设置单双页
//            } else {
//                pars.add(Sides.DUPLEX); //设置单双页
//            }
//            printJob.print(pars);
//        } finally {
//            if (document != null) {
//                try {
//                    document.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//    
//    public static void printPDF(String filename, InputStream file,
//            PrintService printService) throws Exception {
//        PDDocument document = null;
//        PrinterJob printJob = null;
//        try {
//            document = PDDocument.load(file);
//            printJob = PrinterJob.getPrinterJob();
//            printJob.setJobName(filename);
//            //设置纸张及缩放
//            PDFPrintable pdfPrintable = new PDFPrintable(document,
//                    Scaling.ACTUAL_SIZE);
//            //设置多页打印
//            Book book = new Book();
//            PageFormat pageFormat = new PageFormat();
//            //设置打印方向
//            if (filename.contains("短边")) {
//                pageFormat.setOrientation(PageFormat.LANDSCAPE);//横向
//            } else {
//                pageFormat.setOrientation(PageFormat.PORTRAIT);//纵向
//            }
//            pageFormat.setPaper(getPaper());//设置纸张
//            book.append(pdfPrintable, pageFormat, document.getNumberOfPages());
//            printJob.setPageable(book);
//            printJob.setCopies(1);//设置打印份数
//            //添加打印属性
//            HashPrintRequestAttributeSet pars = new HashPrintRequestAttributeSet();
//            if (filename.contains("单面")) {
//                pars.add(Sides.ONE_SIDED); //设置单双页
//            } else {
//                pars.add(Sides.DUPLEX); //设置单双页
//            }
//            printJob.print(pars);
//        } finally {
//            if (document != null) {
//                try {
//                    document.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (printJob != null) {
//                printJob.cancel();
//            }
//        }
//    }
//    
//    public static Paper getPaper() {
//        Paper paper = new Paper();
//        // 默认为A4纸张，对应像素宽和高分别为 595, 842
//        int width = 595;
//        int height = 842;
//        // 设置边距，单位是像素，10mm边距，对应 28px
//        int marginLeft = 0;
//        int marginRight = 0;
//        int marginTop = 0;
//        int marginBottom = 0;
//        paper.setSize(width, height);
//        // 下面一行代码，解决了打印内容为空的问题
//        paper.setImageableArea(marginLeft,
//                marginRight,
//                width - (marginLeft + marginRight),
//                height - (marginTop + marginBottom));
//        return paper;
//    }
//    
//    /**
//     * 
//     * @param outPdfFilepath 生成pdf文件路径
//     * @param imageFiles 需要转换的图片File类Array,按array的顺序合成图片
//     */
//    public static ByteArrayOutputStream imagesToPdf(List<File> imageFiles,
//            boolean isFlip) throws Exception {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        // 第一步：创建一个document对象。
//        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
//        document.setMargins(0, 0, 0, 0);
//        // 第二步：
//        // 创建一个PdfWriter实例，
//        PdfWriter.getInstance(document, outputStream);
//        
//        // 第三步：打开文档。
//        document.open();
//        // 第四步：在文档中增加图片。
//        int i = 0;
//        for (File file : imageFiles) {
//            if (file.getName().toLowerCase().endsWith(".bmp")
//                    || file.getName().toLowerCase().endsWith(".jpg")
//                    || file.getName().toLowerCase().endsWith(".jpeg")
//                    || file.getName().toLowerCase().endsWith(".gif")
//                    || file.getName().toLowerCase().endsWith(".png")) {
//                String temp = file.getAbsolutePath();
//                Image img = null;
//                if (isFlip && i++ % 2 != 0) {
//                    BufferedImage biTemp = ImageIO
//                            .read(Files.newInputStream(Paths.get(temp)));
//                    biTemp = flipImage(biTemp);
//                    //ByteArrayOutputStream out = new ByteArrayOutputStream();
//                    //ImageIO.write(biTemp, file.getName().toLowerCase(), out);
//                    //Image.getInstance(image, color)
//                    img = Image.getInstance(biTemp, null, false);
//                    
//                    //                    System.out.println(
//                    //                            temp + "_new." + file.getName().toLowerCase());
//                    //                    File nf = new File(
//                    //                            temp + "_new." + file.getName().toLowerCase());
//                    //                    if (!nf.exists()) {
//                    //                        nf.createNewFile();
//                    //                    }
//                    //ImageIO.write(biTemp, file.getName().toLowerCase(), nf);
//                } else {
//                    img = Image.getInstance(temp);
//                }
//                
//                img.setAlignment(Image.ALIGN_CENTER);
//                img.scaleAbsolute(597, 844);// 直接设定显示尺寸
//                // 根据图片大小设置页面，一定要先设置页面，再newPage（），否则无效
//                //document.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
//                document.setPageSize(new Rectangle(597, 844));
//                document.newPage();
//                document.add(img);
//            }
//        }
//        // 第五步：关闭文档。
//        document.close();
//        return outputStream;
//    }
//    
//    //Image转换成BufferedImage
//    public static BufferedImage ImageToBufferedImage(java.awt.Image image) {
//        BufferedImage bufferedimage = new BufferedImage(image.getWidth(null),
//                image.getHeight(null), BufferedImage.TYPE_INT_RGB);
//        Graphics g = bufferedimage.createGraphics();
//        g.drawImage(image, 0, 0, null); //这里，大家可能会有疑问，似乎没有对bufferedimage干啥
//        g.dispose(); //但是是正确的，g调用drawImage就自动保存了
//        return bufferedimage;
//    }
//    
//    //BufferedImage 转换成Image类型
//    public static java.awt.Image BufferedImageToImage(BufferedImage b) {
//        return b;
//    }
//    
//    /**
//     * 水平翻转图像
//     * 
//     * @param bufferedimage 目标图像
//     * @return
//     */
//    public static BufferedImage flipImage(final BufferedImage bufferedimage) {
//        int w = bufferedimage.getWidth();
//        int h = bufferedimage.getHeight();
//        BufferedImage img;
//        Graphics2D graphics2d;
//        (graphics2d = (img = new BufferedImage(w, h,
//                bufferedimage.getColorModel().getTransparency()))
//                        .createGraphics()).drawImage(bufferedimage,
//                                0,
//                                0,
//                                w,
//                                h,
//                                w,
//                                0,
//                                0,
//                                h,
//                                null);
//        graphics2d.dispose();
//        return img;
//    }
//    
//    //    public static BufferedImage flipImage(final BufferedImage bufferedimage) {
//    //        int imageWidth = bufferedimage.getWidth();
//    //        int imageHeight = bufferedimage.getHeight();
//    //        BufferedImage img = null;
//    //        //        Graphics2D graphics2d = null;
//    //        //        try {
//    //        //            (graphics2d = (img = new BufferedImage(imageWidth, imageHeight,
//    //        //                    bufferedimage.getColorModel().getTransparency()))
//    //        //                            .createGraphics()).drawImage(bufferedimage,
//    //        //                                    0,
//    //        //                                    0,
//    //        //                                    imageWidth,
//    //        //                                    imageHeight,
//    //        //                                    imageWidth,
//    //        //                                    0,
//    //        //                                    0,
//    //        //                                    imageHeight,
//    //        //                                    null);
//    //        //        } finally {
//    //        //            graphics2d.dispose();
//    //        //        }
//    //        int w = bufferedimage.getWidth();
//    //        int h = bufferedimage.getHeight();
//    //        int[][] datas = new int[w][h];
//    //        for (int i = 0; i < h; i++) {
//    //            for (int j = 0; j < w; j++) {
//    //                datas[j][i] = bufferedimage.getRGB(j, i);
//    //            }
//    //        }
//    //        int[][] tmps = new int[w][h];
//    //        for (int i = 0; i < h; i++) {
//    //            for (int j = 0, b = w - 1; j < w; j++, b--) {
//    //                tmps[b][i] = datas[j][i];
//    //            }
//    //        }
//    //        for (int i = 0; i < h; i++) {
//    //            for (int j = 0; j < w; j++) {
//    //                bufferedimage.setRGB(j, i, tmps[j][i]);
//    //            }
//    //        }
//    //        return bufferedimage;
//    //    }
//    
//    public static void printImages(String parentPath, List<File> files,
//            PrintService printService, boolean isFlip) throws Exception {
//        if (files == null) {
//            System.err.println("缺少打印文件");
//        }
//        
//        ByteArrayOutputStream outputStream = imagesToPdf(files, isFlip);
//        InputStreamSource inputStreamSource = new ByteArrayResource(
//                outputStream.toByteArray());
//        printPDF(parentPath, inputStreamSource.getInputStream(), printService);
//    }
//    
//    // 传入文件和打印机名称
//    //    public static void printImages(List<File> files, PrintService printService,
//    //            String filenameExtension) throws PrintException {
//    //        if (files == null) {
//    //            System.err.println("缺少打印文件");
//    //        }
//    //        
//    //        InputStream fis = null;
//    //        try {
//    //            // 设置打印格式，如果未确定类型，可选择autosense
//    //            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
//    //            switch (filenameExtension.toUpperCase()) {
//    //                case "PNG":
//    //                    flavor = DocFlavor.INPUT_STREAM.PNG;
//    //                    break;
//    //                case "GIF":
//    //                    flavor = DocFlavor.INPUT_STREAM.GIF;
//    //                    break;
//    //                case "JPG":
//    //                    flavor = DocFlavor.INPUT_STREAM.JPEG;
//    //                    break;
//    //                default:
//    //                    flavor = DocFlavor.INPUT_STREAM.JPEG;
//    //                    break;
//    //            }
//    //            Vector<InputStream> v = new Vector<InputStream>();
//    //            for (File file : files) {
//    //                v.add(new FileInputStream(file));
//    //            }
//    //            Enumeration<InputStream> en = v.elements();
//    //            fis = new SequenceInputStream(en);
//    //            
//    //            DocPrintJob job = printService.createPrintJob(); // 创建打印作业
//    //            // 设置打印参数
//    //            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
//    //            aset.add(new Copies(1)); //份数
//    //            aset.add(Sides.DUPLEX); //单双面
//    //            //aset.add(MediaSize.ISO.A4); //纸张
//    //            //WEWaset.add(Finishings.STAPLE); //装订
//    //            //定位打印服务
//    //            Doc doc = new SimpleDoc(fis, flavor, null);
//    //            job.print(doc, aset);
//    //        } catch (FileNotFoundException e1) {
//    //            e1.printStackTrace();
//    //        } finally {
//    //            // 关闭打印的文件流
//    //            if (fis != null) {
//    //                try {
//    //                    fis.close();
//    //                } catch (IOException e) {
//    //                    e.printStackTrace();
//    //                }
//    //            }
//    //        }
//    //    }
//    
//    // 传入文件和打印机名称
//    public static void printImage(File file, PrintService printService)
//            throws PrintException {
//        if (file == null) {
//            System.err.println("缺少打印文件");
//        }
//        InputStream fis = null;
//        try {
//            // 设置打印格式，如果未确定类型，可选择autosense
//            DocFlavor flavor = DocFlavor.INPUT_STREAM.JPEG;
//            switch (StringUtils
//                    .getFilenameExtension(file.getPath().toUpperCase())
//                    .toUpperCase()) {
//                case "PNG":
//                    flavor = DocFlavor.INPUT_STREAM.PNG;
//                    break;
//                case "GIF":
//                    flavor = DocFlavor.INPUT_STREAM.GIF;
//                    break;
//                case "JPG":
//                    flavor = DocFlavor.INPUT_STREAM.JPEG;
//                    break;
//                default:
//                    flavor = DocFlavor.INPUT_STREAM.JPEG;
//                    break;
//            }
//            // 设置打印参数
//            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
//            aset.add(new Copies(1)); //份数
//            aset.add(MediaSize.ISO.A4); //纸张
//            aset.add(Sides.DUPLEX);//单双面
//            //WEWaset.add(Finishings.STAPLE);//装订
//            // 定位打印服务
//            fis = new FileInputStream(file); // 构造待打印的文件流
//            Doc doc = new SimpleDoc(fis, flavor, null);
//            DocPrintJob job = printService.createPrintJob(); // 创建打印作业
//            job.print(doc, aset);
//        } catch (FileNotFoundException e1) {
//            e1.printStackTrace();
//        } finally {
//            // 关闭打印的文件流
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//    
//    // 传入文件和打印机名称
//    public static void printTXT(File file, PrintService printService)
//            throws PrintException {
//        if (file == null) {
//            System.err.println("缺少打印文件");
//        }
//        InputStream fis = null;
//        try {
//            // 设置打印格式，如果未确定类型，可选择autosense
//            DocFlavor flavor = DocFlavor.INPUT_STREAM.TEXT_HTML_UTF_8;
//            switch (StringUtils.getFilenameExtension(file.getPath())
//                    .toUpperCase()) {
//                case "TXT":
//                    flavor = DocFlavor.INPUT_STREAM.TEXT_HTML_UTF_8;
//                    break;
//                case "GIF":
//                    flavor = DocFlavor.INPUT_STREAM.GIF;
//                    break;
//                case "JPG":
//                    flavor = DocFlavor.INPUT_STREAM.JPEG;
//                    break;
//                default:
//                    flavor = DocFlavor.INPUT_STREAM.JPEG;
//                    break;
//            }
//            // 设置打印参数
//            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
//            aset.add(new Copies(1)); //份数
//            //aset.add(MediaSize.ISO.A4); //纸张
//            //WEWaset.add(Finishings.STAPLE);//装订
//            aset.add(Sides.DUPLEX);//单双面
//            // 定位打印服务
//            fis = new FileInputStream(file); // 构造待打印的文件流
//            Doc doc = new SimpleDoc(fis, flavor, null);
//            DocPrintJob job = printService.createPrintJob(); // 创建打印作业
//            job.print(doc, aset);
//        } catch (FileNotFoundException e1) {
//            e1.printStackTrace();
//        } finally {
//            // 关闭打印的文件流
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//    
//    public static void wordToHtml(String fileName, String outPutFile)
//            throws TransformerException, IOException,
//            ParserConfigurationException {
//        HWPFDocument wordDocument = new HWPFDocument(
//                new FileInputStream(fileName));// WordToHtmlUtils.loadDoc(new
//                                                                                            // FileInputStream(inputFile));
//        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
//                DocumentBuilderFactory.newInstance()
//                        .newDocumentBuilder()
//                        .newDocument());
//        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
//            @Override
//            public String savePicture(byte[] content, PictureType pictureType,
//                    String suggestedName, float widthInches,
//                    float heightInches) {
//                return "test/" + suggestedName;
//            }
//        });
//        wordToHtmlConverter.processDocument(wordDocument);
//        // save pictures
//        List pics = wordDocument.getPicturesTable().getAllPictures();
//        if (pics != null) {
//            for (int i = 0; i < pics.size(); i++) {
//                Picture pic = (Picture) pics.get(i);
//                System.out.println();
//                try {
//                    pic.writeImageContent(new FileOutputStream(
//                            "D:/test/" + pic.suggestFullFileName()));
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        Document htmlDocument = wordToHtmlConverter.getDocument();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        DOMSource domSource = new DOMSource(htmlDocument);
//        StreamResult streamResult = new StreamResult(out);
//        
//        TransformerFactory tf = TransformerFactory.newInstance();
//        Transformer serializer = tf.newTransformer();
//        serializer.setOutputProperty(OutputKeys.ENCODING, "GB2312");
//        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
//        serializer.setOutputProperty(OutputKeys.METHOD, "html");
//        serializer.transform(domSource, streamResult);
//        out.close();
//        //writeFile(new String(out.toByteArray()), outPutFile);
//    }
//    
//    public static void main(String[] args) throws Exception {
//        PrintService printService = PrintServiceLookup
//                .lookupDefaultPrintService();
//        
//        String path = "E:\\jb\\夜华鬼棺-公众号可乐味男孩\\打印\\剧本_横";
//        boolean isflip = true;
//        
//        List<File> fs = new ArrayList<>(FileUtils.listFiles(new File(path),
//                new String[] { "png", "jpg", "gif", "pdf", "doc", "docx" },
//                true));
//        Collections.sort(fs);
//        
//        MultiValueMap<String, File> imagesMultiMap = new LinkedMultiValueMap<>();
//        for (File f : fs) {
//            if ("PDF".equals(StringUtils.getFilenameExtension(f.getPath())
//                    .toUpperCase())) {
//                System.out.println("---- print task: ----");
//                System.out.println(f.getPath());
//                printPDF(String.valueOf(Math.abs(f.getPath().hashCode())),
//                        f,
//                        printService);
//            } else if ("JPG".equals(
//                    StringUtils.getFilenameExtension(f.getPath()).toUpperCase())
//                    || "PNG".equals(
//                            StringUtils.getFilenameExtension(f.getPath())
//                                    .toUpperCase())
//                    || "GIF".equals(
//                            StringUtils.getFilenameExtension(f.getPath())
//                                    .toUpperCase())) {
//                imagesMultiMap.add(f.getParentFile().getPath(), f);
//            }
//            //相同目录下面的进行组合以后再进行打印
//        }
//        for (Entry<String, List<File>> entryTemp : imagesMultiMap.entrySet()) {
//            //相同目录下面的进行组合以后再进行打印
//            System.out.println("---- print task: ----" + entryTemp.getKey());
//            System.out.println(entryTemp.getKey());
//            for (File f : entryTemp.getValue()) {
//                System.out.println(f.getPath());
//            }
//            printImages(String.valueOf(Math.abs(entryTemp.getKey().hashCode())),
//                    entryTemp.getValue(),
//                    printService,
//                    isflip);
//        }
//    }
//}
