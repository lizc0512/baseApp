package com.tg.coloursteward.constants;

/**
 * @name ${lizc}
 * @class name：com.colourlife.safelife.constants
 * @class 常量定义类
 * @anthor ${lizc} QQ:510906433
 * @time 2019/1/9 17:12
 * @change
 * @chang time
 * @class describe
 */
public interface Contants {
    interface URl {

        ////////////////   正式地址 ///////////////////////

        String URL_ICETEST = "https://openapi.colourlife.com/v1";//2.0
        String URL_CPMOBILE = "http://cpmobile.colourlife.com";
        String URL_CAIHUI = "https://caihui-bishow.colourlife.com";
        String URL_OAUTH2 = "https://oauth2-cgj.colourlife.com";
        String SINGLE_DEVICE = "https://single.colourlife.com";
        String URL_NEW = "https://cgj-backyard.colourlife.com";
        String URL_ICESTAFF = "https://staff-ice.colourlife.com";
        String CLIENT_SECRET = "t2o0a1xl2lOmoPi4tuHf5uw4VZloXGs7y1Kd0Yoq";
        String URL_QRCODE = "https://qrcode.colourlife.com";
        String URL_IMPUSH = "https://impush-cgj.colourlife.com";
        String environment = "release";
        String cqj_appid = "327494513335603200";
        String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCTFnAR7ORLx0jGzf9Ux1We7yHvRi+kQXKSRmtgBjDCXQzakGm2mrb6EupCkDbUcj4BUs7S7zm/rICQuVNC9fujeJGjcNWRg0XWVtm90XpbTqfKiXzGDHI9W8aULYZ3of/JJ9lyCyjqjigyCdLBPtQ27gOuboDzQuieR2ywPHawzQIDAQAB";

        ///////////////  测试地址   //////////////////////////////
//        String URL_ICETEST = "https://openapi-test.colourlife.com/v1";//2.0
//        String URL_CPMOBILE = "http://cpmobile-czytest.colourlife.com";
//        String URL_CAIHUI = "https://caihui-bishow.colourlife.com";
//        String URL_OAUTH2 = "https://oauth2-cgj-test.colourlife.com";
//        String SINGLE_DEVICE = "https://single-czytest.colourlife.com";
//        String URL_NEW = "https://cgj-backyard-test.colourlife.com";
//        String URL_ICESTAFF = "http://staff.ice.test.colourlife.com";
//        String CLIENT_SECRET = "xlsfrQS5R49upmfZbhlsrUzAt9HDA5K4ptLYsqK5";
//        String URL_QRCODE = "http://qrcode-czytest.colourlife.com";
//        String URL_IMPUSH = "https://impush-cgj-test.colourlife.com";
//        String environment = "debug";
//        String cqj_appid = "323521861252157440";
//        String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDZDqnWph9LxtD0zgtGAYTTf2jY" +
//                "eV+ni5F1o0w3Fag4OOD1YHCRUCXIsFy+iJYmuPf5vMkZrkoiJmKBfkaIzNlrJZzH" +
//                "zq+LsPQNCF86p1nLsuHbkWNvyjOEPn/CUryP2Kxme4S+eEqLIeNwp70VOaMuPmRo" +
//                "EZxMDAgvc6Z0DWsVdQIDAQAB";
/////////////////////////////////////////////////////////////////////////////////////
    }

    interface APP {
        String captchaURL = URl.URL_NEW + "/app/home/captcha/start";
        String validateURL = URl.URL_NEW + "/app/home/login/verify";
        /***彩管家4.0加密的秘钥***/
        String secertKey = "gbiwgbiwkgnkwgnkjbkkrthmnjwjgeh";
    }

    interface EMPLOYEE_LOGIN {
        String key = "EmployeeLogin_key";
        String secret = "EmployeeLogin_secret";
    }
    interface Html5 {
        String HEAD_ICON_URL = "http://avatar.ice.colourlife.com/";//头像
    }
}
