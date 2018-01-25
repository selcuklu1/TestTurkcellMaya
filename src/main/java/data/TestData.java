package data;

import common.BaseLibrary;

public class TestData extends BaseLibrary {

    // PoC'de kullnailan Belgenet
    // public static final String belgenetURL = "http://www.belgenet.com.tr:8282/edys-web/mainInbox.xhtml";

    // Mevcut Belgenet - internal IP
    // public static final String belgenetURL = "http://10.101.20.153:8889/edys-web/sistemeGiris.xhtml";

    // Mevcut Belgenet - external IP

    public static final String belgenetURL = "http://94.55.114.18:8889/edys-web/sistemeGiris.xhtml";
    //    public static final String belgenetURL = "http://10.101.20.153:8889/edys-web/sistemeGiris.xhtml";

    public static final User optiim = new User("optiim", "123", "Optiim TEST", "Optiim Birim");

    //Default usernameOPTIIM
    public static final String usernameOPTIIM = "optiim";
    public static final String passwordOPTIIM = "123";

    public static final String usernameZTEKIN = "ztekin";
    public static final String passwordZTEKIN = "123";

    public static final String usernameYAKYOL = "yakyol";
    public static final String passwordYAKYOL = "123";

    public static final String usernameMBOZDEMIR = "mbozdemir";
    public static final String passwordMBOZDEMIR = "123";

    public static final String usernamSEZAICELIK = "sezaicelik";
    public static final String passwordSEZAICELIK  = "123";

    public static final String usernameGSAHIN = "gsahin";
    public static final String passwordGSAHIN = "123";

    public static String docPathWindows = "C:\\TestAutomation\\BelgenetFTA\\documents\\";
    //public static String docDownloadPathWindows = "C:\\TestAutomation\\BelgenetFTA\\downloads";
    public static String docDownloadPathWindows = "C:\\Users\\" + getPCUsername() + "\\Downloads\\";
    public static String docPathLinux = "documents";
    public static String docDownloadPathLinux = "/home/optiim";//"home/optiim/Downloads";
}
