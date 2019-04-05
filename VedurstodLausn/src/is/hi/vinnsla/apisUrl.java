package is.hi.vinnsla;

/**
 * Created by Siggigauti on 31/03/2017.
 */
import is.hi.syniApi.gogn.org.json.XML;
import is.hi.syniApi.gogn.org.json.JSONObject;
import okhttp3.*;
import java.io.*;

//Fyrir:    Þarf að hafa okhttp3 i libraries (http://square.github.io/okhttp/)
//          Þarf JSONObject í libraries (https://github.com/stleary/JSON-java) Eða í pakka og referenca.
//Notkun:   Þessi klasi hefur 2 föll, bæði taka inn URL streng og skila mismunandi hlutum. Einn skilari JSONObject hlut
//          og hitt skilar String sem er XML'ið af response.
public class apisUrl {
    public static void main(String[] args){
        apisUrl example = new apisUrl();
        String response = null;
        try {
            example.runXMLString(url1, "test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static OkHttpClient client = new OkHttpClient();
    private static String url1 = "https://apis.is/weather/forecasts/is?stations=1,422";

    //Skilar streng sem er response.
    //Sjá notkun í main.
    //Tekur inn url (á t.d. apis.is) og fileName sem er nafnið á skránni sem skrifast út.
    public void runXMLString(String url, String fileName) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            JSONObject  json = new JSONObject(response.body().string());
            String xml = XML.toString(json);
            xml = "<root>" + xml +"</root>";
            xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"+ xml;
            System.out.println(xml);
            try{
                stringToDom(xml, fileName);
            }catch(IOException e){
                e.printStackTrace();
            }
        }

    //Tekur XML streng og skrifar út file sem xml.
    //Fyrir, xmlSource er String af xml gögnum. fileName er nafnið á skránni sem á að skrifa út (ekki setja .xml).
    private static void stringToDom(String xmlSource, String fileName)
            throws IOException {
        java.io.FileWriter fw = new java.io.FileWriter(fileName+".xml");
        fw.write(xmlSource);
        fw.close();
    }
}
