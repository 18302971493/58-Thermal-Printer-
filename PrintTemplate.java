package print;

import com.alibaba.fastjson.JSONObject;

public class PrintTemplate {
    private final static int pringType = 2;

    public static boolean print(JSONObject jsonObject, String url1, String url2, int type) throws Exception {
        //学员联1二维码
        byte[] data1 = QRCode.writeQrCodeContent(url1);
        //学员联2二维码
        byte[] data2 = QRCode.writeQrCodeContent(url2);

        String printContent1 = "";
        String printContent2 = "";
        String printContent3 = "";
        String printContent4 = "";

        //总额
        String count = jsonObject.getString("count");
        //学员联1金额
        String count1 = jsonObject.getString("count1");
        //学员联2金额
        String count2 = jsonObject.getString("count2");
        //科目名
        String subject = jsonObject.getString("subject");
        //学员联1项目名
        String project1 = jsonObject.getString("project1");
        //学员联2项目名
        String project2 = jsonObject.getString("project2");
        //票号
        String number = jsonObject.getString("number");
        //有效期
        String validity = jsonObject.getString("validity");

        printContent1 += "\n";
        printContent1 += "\n";
        printContent1 += "\n";
        printContent1 += "\n";

        printContent1 += PrinterCmdUtils.alignCenter();
        printContent1 += "********************\n";
        printContent1 += "********************\n";
        printContent1 += "********************\n";

        printContent1 += PrinterCmdUtils.alignCenter();
        printContent1 += "******学员联-1******\n";
        printContent1 += PrinterCmdUtils.alignLeft();
        printContent1 += "1,科目名：" + subject + "\n2,总额：";
        printContent1 += PrinterCmdUtils.longitudinalDouble();
        printContent1 += PrinterCmdUtils.margin2();
        printContent1 += count + "\n";
        printContent1 += PrinterCmdUtils.marginCancle();
        printContent1 += PrinterCmdUtils.ZoomCancel();
        printContent1 += "\n";
        printContent1 += "3,项目名：" + project1 + "\n4,票号：" + number + "\n5,本项：";
        printContent1 += PrinterCmdUtils.longitudinalDouble();
        printContent1 += PrinterCmdUtils.margin2();
        printContent1 += count1 + "\n";
        printContent1 += PrinterCmdUtils.marginCancle();
        printContent1 += PrinterCmdUtils.ZoomCancel();
        printContent1 += "\n";
        printContent1 += "6,提醒：如需开票，请到待考大厅二楼会计室\n7," + validity + "\n8,一经售出，概不退换\n9,丢失不补\n";
        printContent1 += PrinterCmdUtils.boldOn();
        printContent1 += PrinterCmdUtils.doubleFont();
        printContent1 += "10,下方二维码仅用于教练员用微信扫描,扫描一次即作废,请保护好,避免误操作。\n";
        printContent1 += PrinterCmdUtils.doubleCancel();
        printContent1 += PrinterCmdUtils.boldOff();

        printContent1 += "\n";
        printContent1 += "\n";


        printContent2 += PrinterCmdUtils.alignCenter();
        printContent2 += "\n";
        printContent2 += "\n";
        printContent2 += "\n";
        printContent2 += "******存根联-1******\n";
        printContent2 += PrinterCmdUtils.alignLeft();
        printContent2 += "1,科目名：" + subject + "\n2,总额：";
        printContent2 += PrinterCmdUtils.longitudinalDouble();
        printContent2 += PrinterCmdUtils.margin2();
        printContent2 += count + "\n";
        printContent2 += PrinterCmdUtils.marginCancle();
        printContent2 += PrinterCmdUtils.ZoomCancel();
        printContent2 += "\n";
        printContent2 += "3,项目名：" + project1 + "\n4,票号：" + number + "\n5,本项：";
        printContent2 += PrinterCmdUtils.longitudinalDouble();
        printContent2 += PrinterCmdUtils.margin2();
        printContent2 += count1 + "\n";
        printContent2 += PrinterCmdUtils.marginCancle();
        printContent2 += PrinterCmdUtils.ZoomCancel();
        printContent2 += "\n";

        printContent2 += "\n";
        printContent2 += "\n";
        printContent2 += "\n";
        printContent2 += "\n";
        printContent2 += "\n";

        if (type == pringType) {
            printContent3 += PrinterCmdUtils.alignCenter();
            printContent3 += "\n";
            printContent3 += "\n";
            printContent3 += "\n";
            printContent3 += "******学员联-2******\n";
            printContent3 += PrinterCmdUtils.alignLeft();
            printContent3 += "1,科目名：" + subject + "\n2,总额：";
            printContent3 += PrinterCmdUtils.longitudinalDouble();
            printContent3 += PrinterCmdUtils.margin2();
            printContent3 += count + "\n";
            printContent3 += PrinterCmdUtils.marginCancle();
            printContent3 += PrinterCmdUtils.ZoomCancel();
            printContent3 += "\n";
            printContent3 += "3,项目名：" + project2 + "\n4,票号：" + number + "\n5,本项：";
            printContent3 += PrinterCmdUtils.longitudinalDouble();
            printContent3 += PrinterCmdUtils.margin2();
            printContent3 += count2 + "\n";
            printContent3 += PrinterCmdUtils.marginCancle();
            printContent3 += PrinterCmdUtils.ZoomCancel();
            printContent3 += "\n";
            printContent3 += "6,提醒：如需开票，请到待考大厅二楼会计室\n7," + validity + "\n8,一经售出，概不退换\n9,丢失不补\n";
            printContent3 += PrinterCmdUtils.boldOn();
            printContent3 += PrinterCmdUtils.doubleFont();
            printContent3 += "10,下方二维码仅用于教练员用微信扫描,扫描一次即作废,请保护好,避免误操作。\n";
            printContent3 += PrinterCmdUtils.doubleCancel();
            printContent3 += PrinterCmdUtils.boldOff();
            printContent3 += "\n";
            printContent3 += "\n";

            printContent4 += PrinterCmdUtils.alignCenter();
            printContent4 += "\n";
            printContent4 += "\n";
            printContent4 += "\n";
            printContent4 += "******存根联-2******\n";
            printContent4 += PrinterCmdUtils.alignLeft();
            printContent4 += "1,科目名：" + subject + "\n2,总额：";
            printContent4 += PrinterCmdUtils.longitudinalDouble();
            printContent4 += PrinterCmdUtils.margin2();
            printContent4 += count + "\n";
            printContent4 += PrinterCmdUtils.marginCancle();
            printContent4 += PrinterCmdUtils.ZoomCancel();
            printContent4 += "\n";
            printContent4 += "3,项目名：" + project2 + "\n4,票号：" + number + "\n5,本项：";
            printContent4 += PrinterCmdUtils.longitudinalDouble();
            printContent4 += PrinterCmdUtils.margin2();
            printContent4 += count2 + "\n";
            printContent4 += PrinterCmdUtils.marginCancle();
            printContent4 += PrinterCmdUtils.ZoomCancel();
            printContent4 += "\n";

            printContent4 += "\n";
            printContent4 += "\n";
            printContent4 += "\n";
            printContent4 += "\n";
            printContent4 += "\n";
        }

        return QRCode.doPrint(printContent1, printContent2, printContent3, printContent4, data1, data2, type);
    }
}
