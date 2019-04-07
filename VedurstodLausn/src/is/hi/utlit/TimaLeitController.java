/*
Ebba Þóra Hvannberg ebba@hi.is
 */
package is.hi.utlit;

import is.hi.vinnsla.Vedur;
import is.hi.vinnsla.KlukkustundirModel;
import is.hi.vinnsla.Nidurstodur;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Slider;

/**
 * Controller klasi fyrir að afmarka leit í veðurspám eftir kls. 
 * Mögulegar kls afmarkast af gögnunum
 *
 * @author Ebba Þóra Hvannberg ebba@hi.is
 */
public class TimaLeitController implements Initializable {

    @FXML
    private Slider leitKlukkustund;   // Leitar drop-down

    private AdalController adalController;      // Aðalcontroller
    private SkodaVedurspaController spaController; // Veðurspácontroller
    private static final String ALLAN_DAGINN = "allan daginn";

    /**
     * Frumstillir stýriklasann. Engri virkni bætt við
     * @param url óntað
     * @param rb  ónotað
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");  
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);
        
    }

    /**
     * Setja tengingar í aðal og spá controllera
     *
     * @param adal
     * @param skoda
     */
    private void setControllera(AdalController adal, SkodaVedurspaController skoda) {
        this.adalController = adal;
        this.spaController = skoda;
    }

    /**
     * Velur veðurspá sem er fyrir klukkustund kl Valið úr vedurspalisti
     *
     * @param vedurspaListi listi af veðurspám
     * @param kl klukkustund
     * @return listi af veðurspám
     */
    private ObservableList<Nidurstodur.Vedurstod.Vedurspa> 
        veljaVedurspaKl(ObservableList<Nidurstodur.Vedurstod.Vedurspa> vedurspaListi, 
                String kl) {
        ObservableList<Nidurstodur.Vedurstod.Vedurspa> 
                nyrVektor = FXCollections.observableArrayList();

        for (Nidurstodur.Vedurstod.Vedurspa s : vedurspaListi) {
            if (Klukkustund(s.getFtime()).equals(kl)) {
                nyrVektor.add(s);
            }
        }
        return nyrVektor;
    }

    /**
     * Skilar klukkustund úr dagsetningar- og tímastreng
     *
     * @param startTime
     * @return klukkustund sem tveggja stafa tölu
     */
    public static String Klukkustund(String startTime) {
        return startTime.substring(11, 13);
    }

    /**
     * Setur valið á tímanum sem allan daginn
     */
    public void allanDaginn() {
        //leitKlukkustund.getItems().get(0);
    }

    /**
     * Setur upp tímacontrollerinn með því að setja upp tengingu við
     * AðalController setja klukkustundir í comboboxið og frumstilla handler til
     * að hægt sé að afmarka veðurspár eftir kls.
     *
     * @param aThis tenging á aðalcontroller
     * @param skodaVedurspaController tenging á veðurspárcontroller
     */
    public void setjaUppTimaController(AdalController aThis, SkodaVedurspaController skodaVedurspaController) {
        setControllera(aThis, skodaVedurspaController);
        frumstillaGognHandlerTimaAfmorkun();
    }

    /**
     * *
     * Frumstillir gögn og val-handler fyrir klukkustundar afmörkun
     * Fyrsta stakið er valið
     */
    private void frumstillaGognHandlerTimaAfmorkun() {
        leitKlukkustund.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue <? extends Number >  
                      observable, Number oldValue, Number newValue) 
            { 
                ObservableList<Nidurstodur.Vedurstod.Vedurspa> obl = 
                        adalController.vedursparValdrarStodvar();
                ObservableList<Nidurstodur.Vedurstod.Vedurspa> valdirLidir;
                newValue = Math.round(newValue.doubleValue());
                if (newValue == null) {
                    return;
                }
                String currValue;
                if(newValue.intValue() < 10 && newValue.intValue() != 0){
                    currValue = "0";
                    currValue += newValue.toString();
                } else {
                    currValue = newValue.toString();
                }
                if (newValue.intValue()==0) {
                    valdirLidir = obl;
                } else {
                    valdirLidir = veljaVedurspaKl(obl, currValue);
                }
                
                ObservableList<Vedur> obs = spaController.geraVedurspaLista(valdirLidir);
                spaController.uppfaeraVedurspa(obs);
            }
        });
    }
}
