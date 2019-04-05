/*
Ebba Þóra Hvannberg ebba@hi.is
 */
package is.hi.utlit;

import is.hi.vinnsla.Vedur;
import is.hi.vinnsla.KlukkustundirModel;
import is.hi.vinnsla.Nidurstodur;
import java.net.URL;
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
        leitKlukkustund.getValue();
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
        setjaKlukkustundir();
        frumstillaGognHandlerTimaAfmorkun();
    }

    /**
     * *
     * Frumstillir gögn og val-handler fyrir klukkustundar afmörkun
     * Fyrsta stakið er valið
     */
    private void frumstillaGognHandlerTimaAfmorkun() {

        SingleSelectionModel tsl = leitKlukkustund.getSelectionModel();
        tsl.selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ObservableList<Nidurstodur.Vedurstod.Vedurspa> obl = 
                        adalController.vedursparValdrarStodvar();
                ObservableList<Nidurstodur.Vedurstod.Vedurspa> valdirLidir;
                if (newValue == null) {
                    return;
                }
                if (newValue.equals(ALLAN_DAGINN)) {
                    valdirLidir = obl;
                } else {
                    valdirLidir = veljaVedurspaKl(obl, newValue);
                }
                ObservableList<Vedur> obs = spaController.geraVedurspaLista(valdirLidir);
                spaController.uppfaeraVedurspa(obs);
            }
        });
        setjaKlukkustundir();
        leitKlukkustund.getSelectionModel().selectFirst();
    }

    /**
     * Setur viðeigandi klukkustundir í combobox
     */
    private void setjaKlukkustundir() {
        KlukkustundirModel kls = new KlukkustundirModel(adalController.vedursparValdrarStodvar());
        leitKlukkustund.getSelectionModel().select(null);
        leitKlukkustund.setItems(kls.getItems());
    }
}
