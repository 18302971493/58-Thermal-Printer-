package print;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.commons.lang3.ArrayUtils;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.event.PrintJobEvent;
import javax.print.event.PrintJobListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Hashtable;

/**
 * @author tuzhengsong\
 * 二维码生成与处理
 */
public class QRCode {
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private static final int PRINT_DATA_TRANSFER_COMPLETED = 106;
    private static final int PRINT_JOB_NO_MORE_EVENTS = 105;

    static int printDataTransferCompletedID=0;
    static int printJobNoMoreEventsID = 0;


    //把bufferedImage格式的二维码处理成热敏打印机可识别的byte[]
    private static byte[] getBitmapCode(BufferedImage bm) {
        int w = bm.getWidth();
        int h = bm.getHeight();
        int bitw = ((w + 7) / 8) * 8;
        int bith = h;
        int pitch = bitw / 8;
        byte[] cmd = {0x1D, 0x76, 0x30, 0x00, (byte) (pitch & 0xff), (byte) ((pitch >> 8) & 0xff), (byte) (bith & 0xff), (byte) ((bith >> 8) & 0xff)};
        byte[] bits = new byte[bith * pitch];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int color = bm.getRGB(x, y);
                if ((color & 0xFF) < 128) {
                    bits[y * pitch + x / 8] |= (0x80 >> (x % 8));
                }
            }
        }
        ByteBuffer bb = ByteBuffer.allocate(cmd.length + bits.length);
        bb.put(cmd);
        bb.put(bits);
        return bb.array();
    }


    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static byte[] writeQrCodeContent(String url) throws Exception {
        // 二维码图片宽度
        int width = 400;
        // 二维码图片高度
        int height = 400;
        // 二维码的图片格式
        String format = "jpg";

        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);
        // 生成二维码
        BufferedImage bufferedImage = toBufferedImage(bitMatrix);
        byte[] data = getBitmapCode(bufferedImage);
        return data;

    }


    //打印设置
    public static boolean doPrint(String printContent1, String printContent2, String printContent3, String printContent4, byte[] data1,byte[] data2, int type) {
        boolean pringFlag = false;
        HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        //查找所有的可用的打印服务
        PrintService[] printService = PrintServiceLookup.lookupPrintServices(flavor, pras);

        // 使用默认打印机，如果默认打印机不是POS打印机，请通过名称查找。
        PrintService printer = printService[0];

        String printerName = "Gprinter GP-58";
        boolean flag = false;
        for (PrintService aPrintService : printService) {
            if (aPrintService.getName().equals(printerName)) {
                printer = aPrintService;

                flag = true;
            }
        }

        if (flag) {
            try {
                DocPrintJob job = printer.createPrintJob();


                job.addPrintJobListener(new PrintJobListener() {
                    @Override
                    public void printDataTransferCompleted(PrintJobEvent pje) {
                            printDataTransferCompletedID = pje.getPrintEventType();
                    }

                    @Override
                    public void printJobCompleted(PrintJobEvent pje) {
                        System.out.println("printJobCompleted" + pje.getPrintEventType());
                    }

                    @Override
                    public void printJobFailed(PrintJobEvent pje) {
                        System.out.println("printJobFailed" + pje.getPrintEventType());

                    }

                    @Override
                    public void printJobCanceled(PrintJobEvent pje) {
                        System.out.println("printJobCanceled" + pje.getPrintEventType());

                    }

                    @Override
                    public void printJobNoMoreEvents(PrintJobEvent pje) {
                            printJobNoMoreEventsID = pje.getPrintEventType();
                    }

                    @Override
                    public void printJobRequiresAttention(PrintJobEvent pje) {
                        System.out.println("printJobRequiresAttention" + pje.getPrintEventType());

                    }
                });

                byte[] str1;
                byte[] str2;
                byte[] str3;
                byte[] str4;
                byte[] print1;
                byte[] print2;
                byte[] before1;
                byte[] before2;
                byte[] printAll = null;

                if (type == 1) {
                    str1 = printContent1.getBytes("GB2312");
                    str2 = printContent2.getBytes("GB2312");
                    before1 = ArrayUtils.addAll(str1, data1);
                    printAll = ArrayUtils.addAll(before1, str2);
                } else if (type == 2) {
                    str1 = printContent1.getBytes("GB2312");
                    str2 = printContent2.getBytes("GB2312");
                    before1 = ArrayUtils.addAll(str1, data1);
                    print1 = ArrayUtils.addAll(before1, str2);
                    str3 = printContent3.getBytes("GB2312");
                    str4 = printContent4.getBytes("GB2312");
                    before2 = ArrayUtils.addAll(str3, data2);
                    print2 = ArrayUtils.addAll(before2, str4);
                    printAll = ArrayUtils.addAll(print1, print2);
                }
                InputStream stream = null;
                if (printAll != null) {
                    stream = new ByteArrayInputStream(printAll);
                }
                Doc doc = null;
                if (stream != null) {
                    doc = new SimpleDoc(stream, flavor, null);
                }
                job.print(doc, pras);

                if (printDataTransferCompletedID == PRINT_DATA_TRANSFER_COMPLETED && printJobNoMoreEventsID == PRINT_JOB_NO_MORE_EVENTS) {
                    pringFlag = true;
                }
            } catch (PrintException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Gprinter GP-58热敏打印机未找到，检查是否安装成功！");
        }
        return pringFlag;
    }
}
