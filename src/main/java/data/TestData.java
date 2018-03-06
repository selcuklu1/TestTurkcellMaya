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
    public static final User gsahin = new User("gsahin", "123", "Gökçe ŞAHİN", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ");

    public static final User username27 = new User("un27","123","Username27 TEST","TS2200 Alt Birim");

    //Default usernameOPTIIM
    public static final String usernameOPTIIM = "optiim";
    public static final String passwordOPTIIM = "123";

    public static final String usernameZTEKIN = "ztekin";
    public static final String passwordZTEKIN = "123";

    public static final String usernameYAKYOL = "yakyol";
    public static final String passwordYAKYOL = "123";

    public static final String usernameMBOZDEMIR = "mbozdemir";
    public static final String passwordMBOZDEMIR = "123";

    public static final String usernameSEZAICELIK = "sezaicelik";
    public static final String passwordSEZAICELIK = "123";

    public static final String usernameGSAHIN = "gsahin";
    public static final String passwordGSAHIN = "123";

    public static final String usernameOPTIIMTEST6 = "optiimtest6";
    public static final String passwordPTIIMTEST6 = "123";

    public static final String usernameSCELIK = "scelik";
    public static final String passwordSCELIK = "123";

    public static final String username22n = "username22n";
    public static final String passwor22n = "123";

    public static final String username23t = "username23t";
    public static final String passwor23t = "123";

    public static final String username21g = "username21g";
    public static final String passwor21g = "123";




    public static String docPathWindows = "C:\\TestAutomation\\BelgenetFTA\\documents\\";
    //public static String docDownloadPathWindows = "C:\\TestAutomation\\BelgenetFTA\\downloads";
    public static String docDownloadPathWindows = "C:\\Users\\" + getPCUsername() + "\\Downloads\\";
    public static String docPathLinux = "documents/";
    public static String docDownloadPathLinux = "/home/optiim";//"home/optiim/Downloads";
}
