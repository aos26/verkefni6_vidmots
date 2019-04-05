/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.utlit;

import is.hi.vinnsla.DagsetningarModel;
import is.hi.vinnsla.Nidurstodur;
import is.hi.vinnsla.Vedur;
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

/**
 * FXML Controller class
 *
 * @author Andrea Ósk Sigurðardóttir - aos26@hi.is
 */
public class DagsetningarleitController implements Initializable {

    @FXML
    private ComboBox<String> leitDagsetning;
    private AdalController adalController;      // Aðalcontroller
    private SkodaVedurspaController spaController; // Veðurspácontroller
    private static final String ALLIR_DAGAR = "allir dagar";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        veljaVedurspaDag(ObservableList<Nidurstodur.Vedurstod.Vedurspa> vedurspaListi, 
                String dag) {
        ObservableList<Nidurstodur.Vedurstod.Vedurspa> 
                nyrVektor = FXCollections.observableArrayList();

        for (Nidurstodur.Vedurstod.Vedurspa s : vedurspaListi) {
            if (Dagsetning(s.getFtime()).equals(dag)) {
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
    public static String Dagsetning(String startTime) {
        return startTime.substring(5, 10);
    }

    /**
     * Setur valið á tímanum sem allan daginn
     */
    public void allirDagar() {
        leitDagsetning.getSelectionModel().select(0);
    }

    /**
     * Setur upp tímacontrollerinn með því að setja upp tengingu við
     * AðalController setja klukkustundir í comboboxið og frumstilla handler til
     * að hægt sé að afmarka veðurspár eftir kls.
     *
     * @param aThis tenging á aðalcontroller
     * @param skodaVedurspaController tenging á veðurspárcontroller
     */
    public void setjaUppDagsetningController(AdalController aThis, SkodaVedurspaController skodaVedurspaController) {
        setControllera(aThis, skodaVedurspaController);
        setjaDagsetningar();
        frumstillaGognHandlerDagsetningarAfmorkun();
    }

    /**
     * *
     * Frumstillir gögn og val-handler fyrir klukkustundar afmörkun
     * Fyrsta stakið er valið
     */
    private void frumstillaGognHandlerDagsetningarAfmorkun() {

        SingleSelectionModel tsl = leitDagsetning.getSelectionModel();
        tsl.selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ObservableList<Nidurstodur.Vedurstod.Vedurspa> obl = 
                        adalController.vedursparValdrarStodvar();
                ObservableList<Nidurstodur.Vedurstod.Vedurspa> valdirLidir;
                if (newValue == null) {
                    return;
                }
                if (newValue.equals(ALLIR_DAGAR)) {
                    valdirLidir = obl;
                } else {
                    valdirLidir = veljaVedurspaDag(obl, newValue);
                }
                ObservableList<Vedur> obs = spaController.geraVedurspaLista(valdirLidir);
                spaController.uppfaeraVedurspa(obs);
            }
        });
        setjaDagsetningar();
        leitDagsetning.getSelectionModel().selectFirst();
    }

    /**
     * Setur viðeigandi klukkustundir í combobox
     */
    private void setjaDagsetningar() {
        DagsetningarModel dags = new DagsetningarModel(adalController.vedursparValdrarStodvar());
        leitDagsetning.getSelectionModel().select(null);
        leitDagsetning.setItems(dags.getItems());
    }
    
}
