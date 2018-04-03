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
    public static final User username27YGD = new User("un27","123","Username27 TEST","YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ");
    public static final User userMbozdemir = new User("mbozdemir","123","Mehmet BOZDEMİR","YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ");
    public static final User userYakyol = new User("yakyol","123","Yasemin AKYOL","YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ");

    public static final User userZtekin = new User("ztekin","123","Zübeye TEKİN","YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ");

    public static final User user21g = new User("username21g","123","Username21g TEST","YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ");
    public static final User uservv = new User("usernameVV","123","usernameVV TEST","YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ");
    public static final User userva = new User("usernameVA","123","usernameVA TEST","YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ");

    public static final User userSezaiCelik = new User("sezaicelik", "123", "Sezai ÇELİK", "Optiim Birim");

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
    public static final String password22n = "123";

    public static final String usernamevv = "usernamevv";
    public static final String passwordvv = "123";

    public static final String usernameva = "usernameva";
    public static final String passwordva = "123";

    public static final String username23t = "username23t";
    public static final String passwor23t = "123";

    public static final String username21g = "username21g";
    public static final String passwor21g = "123";

    public static final String usernameUn30 = "un30";
    public static final String passwordUn30 = "123";

    public static final String usernameAntetIslem1 = "antetislem1";
    public static final String passwordAntetIslem1 = "123";



    public static String docPathWindows = "C:\\TestAutomation\\BelgenetFTA\\documents\\";
    public static String docDownloadPathWindows = "C:\\Users\\" + getPCUsername() + "\\Downloads\\";
    public static String docPathLinux = "documents/";
    public static String docDownloadPathLinux = "/home/optiim";//"home/optiim/Downloads";
}
