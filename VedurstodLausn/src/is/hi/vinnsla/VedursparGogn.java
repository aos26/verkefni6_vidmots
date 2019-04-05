/*
Ebba Þóra Hvannberg ebba@hi.is
 */

package is.hi.vinnsla;

import is.hi.syniApi.gogn.org.json.JSONArray;
import is.hi.syniApi.gogn.org.json.JSONException;
import is.hi.syniApi.gogn.org.json.JSONObject;
import is.hi.syniApi.gogn.org.json.XML;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Ebba Þóra Hvannberg ebba@hi.is 
 * Háskóli Íslands
 */
public class VedursparGogn {
    private Nidurstodur ollGognin;
    private ArrayList<Nidurstodur.Vedurstod> allarStodvar; // listi af veðurstöðvum - hér til þæginda

    //private final String VEDURSPARXML="testAVedur.xml";
    private final String VILLA_Í_LESTRI_Á_XML_SKRÁ="Villa í lestri á XML skrá";
    
    /**
     * Smiður sem les inn XML skrána is eða en veður
     * Birtir villu ef XML skrá fannst ekki 
     */
    public VedursparGogn(String vedurxml) {
        InputStream inputStream = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Nidurstodur.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            inputStream = VedursparGogn.class.getResourceAsStream(vedurxml);

            ollGognin = (Nidurstodur) jaxbUnmarshaller.unmarshal(inputStream);
            allarStodvar = (ArrayList<Nidurstodur.Vedurstod>) ollGognin.getVedurstod();

        } catch (JAXBException e) {
            e.printStackTrace();
            System.out.println(VILLA_Í_LESTRI_Á_XML_SKRÁ);
        }

    }
    
    
    public void frumstillaGogn(String stadur) throws JSONException, IOException {
        JSONArray vedur = frumstillaJSONGognIS();
        
        
        for (Object v : vedur) {
            JSONObject station = (JSONObject) v;
            String name = (String) station.get("name");
            
            JSONArray forecast = (JSONArray) station.get("forecast");
            for (Object f : forecast) {
                JSONObject breytur = (JSONObject) f;
                String vedurlysing = (String) breytur.get("W");
                String vindstefna = (String) breytur.get("D");
                int vindhradi = (int) breytur.get("F");
                int hitastig = (int) breytur.get("T");
                String dagsTimi = (String) breytur.get("ftime");
                int skyjahula = (int) breytur.get("N");
                int daggarmark = (int) breytur.get("TD");
                double uppsofnudUrkoma = (double) breytur.get("R");
                
                System.out.println("Lýsing: " + uppsofnudUrkoma);
                //System.out.println(f);
            }
            
            System.out.println(name);
            System.out.println(v);
        }
    }
    
    
    /**
     * Ég tek inn staðinn sem ég vil birta. Bý til streng með nafnið á xml skránni og umbreyti xml yfir í JSON.
     * @param stadur
     * @throws JSONException
     * @throws IOException 
     */
    private JSONArray frumstillaJSONGognIS() throws JSONException, IOException {
        String line="",str="";
        String link = "vedurIS.xml";
        BufferedReader br = new BufferedReader(new FileReader(link));
        while ((line = br.readLine()) != null) 
        {   
            str+=line;  
        }
        JSONObject jsondata = XML.toJSONObject(str);
        System.out.println(jsondata);
        return  (JSONArray) jsondata.getJSONObject("root").get("results");
    }
    
    /**
     * Ég tek inn staðinn sem ég vil birta. Bý til streng með nafnið á xml skránni og umbreyti xml yfir í JSON.
     * @param stadur
     * @throws JSONException
     * @throws IOException 
     */
    private JSONArray frumstillaJSONGognEN() throws JSONException, IOException {
        String line="",str="";
        String link = "vedurEN.xml";
        BufferedReader br = new BufferedReader(new FileReader(link));
        while ((line = br.readLine()) != null) 
        {   
            str+=line;  
        }
        JSONObject jsondata = XML.toJSONObject(str);
        System.out.println(jsondata);
        return  (JSONArray) jsondata.getJSONObject("root").get("results");
    }
    
    /**
     * Skilar öllum gögnunum 
     * @return 
     */
    public Nidurstodur getNidurstodur() {
        return ollGognin;
    }

    /**
     * Skilar öllum veðurstöðvum þ.m.t. veðurspám þeirra 
     * @return 
     */
    public ArrayList<Nidurstodur.Vedurstod> getAllarStodvar() {
        return allarStodvar;
    }
    
    /**
     * Prentar út veðurspá á console. 
     * Getur verið gagnlegt til að sjá hvort gögnin hafa komist inn 
    */
    public void birtaVedurstodvar() {
         for (Nidurstodur.Vedurstod s:allarStodvar) {
            System.out.println("  nafn Stöðvar "+s.getName());
        }
    }
        
}
