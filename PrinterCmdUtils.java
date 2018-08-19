package print;


import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class PrinterCmdUtils {

    public static final byte ESC = 27;//换码
    public static final byte FS = 28;//文本分隔符
    public static final byte GS = 29;//组分隔符
    public static final byte DLE = 16;//数据连接换码
    public static final byte EOT = 4;//传输结束
    public static final byte ENQ = 5;//询问字符
    public static final byte SP = 32;//空格
    public static final byte HT = 9;//横向列表
    public static final byte LF = 10;//打印并换行（水平定位）
    public static final byte CR = 13;//归位键
    public static final byte FF = 12;//走纸控制（打印并回到标准模式（在页模式下） ）
    public static final byte CAN = 24;//作废（页模式下取消打印数据 ）

//------------------------换行-----------------------------
    /**
     * 换行
     * @param
     * @return
     */
    public static byte[] nextLine(int lineNum)
    {
        byte[] result = new byte[lineNum];
        for(int i=0;i<lineNum;i++)
        {
            result[i] = LF;
        }

        return result;
    }

//------------------------加粗-----------------------------


    //选择加粗模式
    public static String boldOn()
    {
        byte[] result = new byte[3];
        result[0] = ESC;
        result[1] = 69;
        result[2] = 0xF;
        return new String(result);
    }


    //取消加粗模式
    public static String boldOff()
    {
        byte[] result = new byte[3];
        result[0] = ESC;
        result[1] = 69;
        result[2] = 0;
        return new String(result);
    }

//------------------------对齐-----------------------------


    /**
     * 左对齐
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String alignLeft()
    {
        byte[] result = new byte[3];
        result[0] = ESC;
        result[1] = 97;
        result[2] = 0;
        return new String(result);
    }


    /**
     * 居中对齐
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String alignCenter()
    {
        byte[] result = new byte[3];
        result[0] = ESC;
        result[1] = 97;
        result[2] = 1;
        return new String(result);
    }


    /**
     * 右对齐
     * @return
     */
    public static byte[] alignRight()
    {
        byte[] result = new byte[3];
        result[0] = ESC;
        result[1] = 97;
        result[2] = 2;
        return result;
    }


    /**
     * 水平方向向右移动col列
     * @param col
     * @return
     */
    public static byte[] set_HT_position( byte col )
    {
        byte[] result = new byte[4];
        result[0] = ESC;
        result[1] = 68;
        result[2] = col;
        result[3] = 0;
        return result;
    }

    /**
     * Select Font A
     * ESC M n
     * @return bytes for this command
     * @throws UnsupportedEncodingException
     */
    public static String select_fontA()
    {
        byte[] result = new byte[3];
        result[0] = ESC;
        result[1] = 77;
        result[2] = 0;
        return new String(result);
    }

    /**
     * Select Font B
     * ESC M n
     * @return bytes for this command
     * @throws UnsupportedEncodingException
     */
    public static String select_fontB()
    {
        byte[] result = new byte[3];
        result[0] = ESC;
        result[1] = 77;
        result[2] = 1;
        return new String(result);
    }

    /**
     * Select Font C ( some printers don't have font C )
     * ESC M n
     * @return bytes for this command
     * @throws UnsupportedEncodingException
     */
    public static String select_fontC()
    {
        byte[] result = new byte[3];
        result[0] = ESC;
        result[1] = 77;
        result[2] = 2;
        return new String(result);
    }

    //反显打印
    public static String reversePrint(){
        byte[] result = new byte[3];
        result[0] = GS;
        result[1] = 66;
        result[2] = 1;
        return new String(result);
    }

    //取消反显
    public static String reverseCancel(){
        byte[] result = new byte[3];
        result[0] = GS;
        result[1] = 66;
        result[2] = 0;
        return new String(result);
    }



    /****************************下划线*******************************/

    //下划线一点宽（不支持汉字）
    public static String underlineOne(){
        byte[] result = new byte[3];
        result[0] = ESC;
        result[1] = 45;
        result[2] = 1;
        return new String(result);
    }

    //下划线两点宽（不支持汉字）
    public static String underlineTwo(){
        byte[] result = new byte[3];
        result[0] = ESC;
        result[1] = 45;
        result[2] = 2;
        return new String(result);
    }

    //取消下划线（不支持汉字）
    public static String UnderlineCancle(){
        byte[] result = new byte[3];
        result[0] = ESC;
        result[1] = 45;
        result[2] = 0;
        return new String(result);
    }

    /****************************跳格*******************************/

    //设置横向跳格位置
    public static String Jump(){
        byte[] result = new byte[3];
        result[0] = ESC;
        result[1] = 68;
        result[2] = 0;
        return new String(result);
    }

    /****************************倍数放大*******************************/

    //纵向放大两倍（汉字，英文，数字都支持）
    public static String longitudinalDouble(){
        byte[] result = new byte[3];
        result[0] = GS;
        result[1] = 33;
        result[2] = 1;
        return new String(result);
    }

    //横向放大两倍（汉字，英文，数字都支持）
    public static String transverseDouble(){
        byte[] result = new byte[3];
        result[0] = GS;
        result[1] = 33;
        result[2] = 16;
        return new String(result);
    }

    //纵向，横向放大两倍（汉字，英文，数字都支持）
    public static String bothDouble(){
        byte[] result = new byte[3];
        result[0] = GS;
        result[1] = 33;
        result[2] = 17;
        return new String(result);
    }

    //纵向，横向放大取消（汉字，英文，数字都支持）
    public static String ZoomCancel(){
        byte[] result = new byte[3];
        result[0] = GS;
        result[1] = 33;
        result[2] = 0;
        return new String(result);
    }

    //倍宽，倍高
    public static String doubleFont(){
        byte[] result = new byte[3];
        result[0] = FS;
        result[1] = 87;
        result[2] = 1;
        return new String(result);
    }

    //取消倍宽，倍高
    public static String doubleCancel(){
        byte[] result = new byte[3];
        result[0] = FS;
        result[1] = 87;
        result[2] = 0;
        return new String(result);
    }

    //字體
    public static String fontA(){
        byte[] result = new byte[3];
        result[0] = ESC;
        result[1] = 77;
        result[2] = 0;
        return new String(result);
    }

    public static String fontB(){
        byte[] result = new byte[3];
        result[0] = ESC;
        result[1] = 77;
        result[2] = 1;
        return new String(result);
    }

    //字符集
    public static String fontji(){
        byte[] result = new byte[3];
        result[0] = ESC;
        result[1] = 82;
        result[2] = 15;
        return new String(result);
    }

    //字符
    public static String fontzidingyi(){
        byte[] result = new byte[5];
        result[0] = FS;
        result[1] = 50;
        result[2] = 1;
        result[3] = 1;

        result[4] = 12;

        return new String(result);
    }

    //设置汉字字符左右边距
    public static String margin1(){
        byte[] result = new byte[4];
        result[0] = FS;
        result[1] = 83;
        result[2] = 1;
        result[3] = 1;
        return new String(result);
    }

    //设置汉字字符左右边距
    public static String margin2(){
        byte[] result = new byte[4];
        result[0] = FS;
        result[1] = 83;
        result[2] = 2;
        result[3] = 2;
        return new String(result);
    }

    //设置汉字字符左右边距
    public static String marginCancle(){
        byte[] result = new byte[4];
        result[0] = FS;
        result[1] = 83;
        result[2] = 0;
        result[3] = 0;
        return new String(result);
    }

    //设置行间距1
    public static String rowSpacing1(){
        byte[] result = new byte[3];
        result[0] = ESC;
        result[1] = 51;
        result[2] = 1;
        return new String(result);
    }

    //设置行间距2
    public static String rowSpacing2(){
        byte[] result = new byte[3];
        result[0] = FS;
        result[1] = 51;
        result[2] = 2;
        return new String(result);
    }

    //设置行间距
    public static String rowSpacingCancel(){
        byte[] result = new byte[3];
        result[0] = FS;
        result[1] = 51;
        result[2] = 0;
        return new String(result);
    }

    public static String getCutPaperByte(){
        byte [] buffer = new byte[5];
        //命令必须是单行
        buffer[0]='\n';
        buffer[1]=29;
        buffer[2]=86;
        buffer[3]=66;
        buffer[4]=1;
        return Arrays.toString(buffer);
    }


    // ------------------------字体变大-----------------------------

    /**
     * 字体变大为标准的n倍
     */
    public static byte[] fontSizeSetBig(int num) {
        byte realSize = 0;
        switch (num) {
            case 1:
                realSize = 0;
                break;
            case 2:
                realSize = 17;
                break;
            case 3:
                realSize = 34;
                break;
            case 4:
                realSize = 51;
                break;
            case 5:
                realSize = 68;
                break;
            case 6:
                realSize = 85;
                break;
            case 7:
                realSize = 102;
                break;
            case 8:
                realSize = 119;
                break;
                default:
                    break;
        }
        byte[] result = new byte[3];
        result[0] = 29;
        result[1] = 33;
        result[2] = realSize;
        return result;
    }

    /**
     * 字体取消倍宽倍高
     */
    public static byte[] fontSizeSetSmall(int num) {
        byte[] result = new byte[3];
        result[0] = ESC;
        result[1] = 33;

        return result;
    }


}