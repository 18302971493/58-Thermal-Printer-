package print;

import com.alibaba.fastjson.JSONObject;

public class PrintTest {
    public static void main(String[] args) throws Exception {

        String url1 = "http://blog.csdn.net/muzi45ya/article/details/50633957";
        String url2 = "http://blog.csdn.net/muzi45ya/article/details/50633957";

        JSONObject jsonObject = new JSONObject();
        //总额
        String count = "总额，参数1";
        //学员联1金额
        String count1 = "学员联1金额，参数2";
        //学员联2金额
        String count2 =  "学员联2金额，参数3";

        String subject = "科目名，参数4";

        String number = "票号，参数5";

        String project1 = "项目名1，参数6";
        //学员联2项目名
        String project2 = "项目名2，参数7";
        //有效期
        String validity = "有效期，参数8";
        jsonObject.put("count", count);
        jsonObject.put("count1", count1);
        jsonObject.put("count2", count2);
        jsonObject.put("subject", subject);
        jsonObject.put("project1", project1);
        jsonObject.put("project2", project2);
        jsonObject.put("number", number);
        jsonObject.put("validity", validity);
        int type = 1;
        boolean flag = PrintTemplate.print(jsonObject, url1, url2, type);
        System.out.println(flag);
    }
}
