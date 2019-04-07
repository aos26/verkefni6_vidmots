/*
Ebba Þóra Hvannberg ebba@hi.is
 */
package is.hi.utlit;

import is.hi.syniApi.gogn.org.json.JSONException;
import is.hi.vinnsla.Nidurstodur;
import is.hi.vinnsla.VedursparGogn;
import is.hi.vinnsla.apisUrl;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 *
 * @author Ebba Þóra Hvannberg ebba@hi.is
 * Aðal controller fyrir veðurupplýsingar. Birtir lista af veðurstöðvum sem eru
 * lesnar úr XML skrá og leyfir notanda að velja veðurstöð og skoða
 * veðurspár. Leyfir notanda einnig að afmarka veðurstöðvar með textaleit
 */
public class AdalController implements Initializable {
    @FXML
    private SkodaVedurspaController skodaVedurspaController; 
    @FXML
    private DagsetningarleitController dagsetningarleitController;
    @FXML
    private TimaLeitController timaleitController;
    @FXML
    private ListView<Nidurstodur.Vedurstod> vedurstodvarView;
    @FXML
    private TextField leitaVedurstod;
    private VedursparGogn vedursparStodva;    // Safnklasi sem hefur veðurspár stöðva
                                              // Óafmarkaður listi
    
    private int virkurIndex;                  // index staksins sem síðast var valið
    @FXML
    private Button enska;
    @FXML
    private Button islenska;
    
    private boolean isEN = false;
    @FXML
    private Button skodaButton;
    
    Stage stage;
    Parent nyRot;
    @FXML
    private Button afram;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apisUrl example = new apisUrl();
        String url1 = "https://apis.is/weather/forecasts/is?stations=1,422";
        String url2 = "https://apis.is/weather/forecasts/en?stations=1,422";
        
        try {
            example.runXMLString(url1, "./src/is/hi/vinnsla/"+"vedurIS");
            example.runXMLString(url2, "./src/is/hi/vinnsla/"+"vedurEN");
        } catch (IOException ex) {
            Logger.getLogger(AdalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        frumstillaXMLGogn("vedurIS.xml");
        frumstillaGognHandlerListi();
        skodaVedurspaController.vedurspaBirta(vedurstodvarView.getItems().get(0), vedurstodvarView.getItems().get(0)
                                    .getVedurspa());
        timaleitController.setjaUppTimaController(this, skodaVedurspaController);
        dagsetningarleitController.setjaUppDagsetningController(this, skodaVedurspaController);
        
        
        Image imageGB = new Image(getClass().getResourceAsStream("myndir/GBFlag.PNG"));
        enska.setGraphic(new ImageView(imageGB));
        
        Image imageIS = new Image(getClass().getResourceAsStream("myndir/IcelandFlag.PNG"));
        islenska.setGraphic(new ImageView(imageIS));
    }    


    /**
     * Búa til ObservableList af veðurstöðvum v
     * @param v veðurstöðvar 
     * @return lista af veðurstöðvum
     */
    private ObservableList<Nidurstodur.Vedurstod> heildarListi(ArrayList<Nidurstodur.Vedurstod> v) {
            ObservableList<Nidurstodur.Vedurstod> obl = FXCollections.observableArrayList(v);
            return obl;
    }    

    /***
     * Frumstillir atburðahandler fyrir lista af veðurstöðvum. Gert til að hægt
     * sé að geyma index-inn
     * 
     */
    private void frumstillaGognHandlerListi() {
        MultipleSelectionModel<Nidurstodur.Vedurstod> msl = vedurstodvarView.getSelectionModel();
        msl.selectedItemProperty().addListener(new ChangeListener<Nidurstodur.Vedurstod>() {

            @Override
            public void changed(ObservableValue<? extends Nidurstodur.Vedurstod> observable, 
                    Nidurstodur.Vedurstod oldValue, Nidurstodur.Vedurstod newValue) {
                virkurIndex = msl.getSelectedIndex();
            }
        });
    }
    
    /**
     * Handler fyrir að skoða veðurspár fyrir veðurstöð
     * Birtir lista af veðurspám. Setur kls val fyrir combobox sem allan daginn
     * @param event 
     */
    @FXML
    private void skodaHandler(ActionEvent event) {
        if (virkurIndex != -1) {
            skodaVedurspaController
                    .vedurspaBirta(vedurstodvarView.getItems().get(virkurIndex), 
                            vedurstodvarView.getItems().get(virkurIndex)
                                    .getVedurspa());
        }
        timaleitController.allanDaginn();
        dagsetningarleitController.allirDagar();
    }

    /**
     * Handler fyrir að leita að veðurstöðvum sem innihalda leitarstreng úr
     * leitaVedurstod. Uppfærir listann af veðurstöðvum. Valinn veðurstöð er 0
     * @param event 
     */
    @FXML
    private void leitaHandler(ActionEvent event) {
        String l = leitaVedurstod.getText();
        ArrayList<Nidurstodur.Vedurstod>  allarStodvar = vedursparStodva.getAllarStodvar();
        ArrayList<Nidurstodur.Vedurstod> nidurstada = new ArrayList<>();
        for (Nidurstodur.Vedurstod s:allarStodvar) {
            if (s.getName().contains(l)) {
                nidurstada.add(s);
            }
         }
        vedurstodvarView.setItems(heildarListi(nidurstada));
        virkurIndex=0;
    }
    

    /**
     * Skilar veðurspám valdra stöðvar (virkurIndex)
     * @return listi af veðurspám fyrir eina veðurstöð 
     */
    
    ObservableList<Nidurstodur.Vedurstod.Vedurspa> vedursparValdrarStodvar() {
        return FXCollections.observableArrayList(vedurstodvarView.getItems().get(virkurIndex).getVedurspa());
    }

    private void frumstillaXMLGogn(String lang) {
        vedursparStodva = new VedursparGogn(lang);
        
        vedursparStodva.birtaVedurstodvar();
        // Ná í veðurstöðvarnar
        vedurstodvarView.setItems(heildarListi(vedursparStodva.
                getAllarStodvar()));
    }

    @FXML
    private void birtaEN(ActionEvent event) {
        frumstillaXMLGogn("vedurEN.xml");
        if (virkurIndex != -1) {
            skodaVedurspaController
                    .vedurspaBirta(vedurstodvarView.getItems().get(virkurIndex), 
                            vedurstodvarView.getItems().get(virkurIndex)
                                    .getVedurspa());
        }
        breytaTexta(true);
    }

    @FXML
    private void birtaIS(ActionEvent event) {
        frumstillaXMLGogn("vedurIS.xml");
        if (virkurIndex != -1) {
            skodaVedurspaController
                    .vedurspaBirta(vedurstodvarView.getItems().get(virkurIndex), 
                            vedurstodvarView.getItems().get(virkurIndex)
                                    .getVedurspa());
        }
        
        breytaTexta(false);
    }
    
    private void breytaTexta(boolean isEN){
        if(isEN == true)
        {
            skodaVedurspaController.getVedurMynd().setText("Image");
            skodaVedurspaController.getVedur().setText("Weather");
            skodaVedurspaController.getVindatt().setText("Wind Direction");
            skodaVedurspaController.getVindhradi().setText("Wind speed");
            skodaVedurspaController.getHitastig().setText("Temperature");
            skodaVedurspaController.getTimi().setText("Time");
            skodaVedurspaController.getDagsetning().setText("Date");
            skodaVedurspaController.getDaggarmark().setText("Dew point");
            skodaVedurspaController.getSkyjahula().setText("Cloud cover");
            skodaVedurspaController.getUppsofnudUrkoma().setText("Cumulative percipitation");
            skodaVedurspaController.getVedurstod().setText("Station:");
            skodaButton.setText("Examine");
        }
        else {
            skodaVedurspaController.getVedurMynd().setText("Mynd");
            skodaVedurspaController.getVedur().setText("Veður");
            skodaVedurspaController.getVindatt().setText("Vindátt");
            skodaVedurspaController.getVindhradi().setText("Vindhraði");
            skodaVedurspaController.getHitastig().setText("Hitastig");
            skodaVedurspaController.getTimi().setText("Tími");
            skodaVedurspaController.getDagsetning().setText("Dagsetning");
            skodaVedurspaController.getDaggarmark().setText("Daggarmark");
            skodaVedurspaController.getSkyjahula().setText("Skýjahula");
            skodaVedurspaController.getUppsofnudUrkoma().setText("Uppsöfnuð úrkoma");
            skodaVedurspaController.getVedurstod().setText("Veðurstöð:");
            skodaButton.setText("Skoða");
        }
    }

    @FXML
    private void birtaVedurkort(ActionEvent event) throws IOException {
        Stage(new Stage(),"VedurKort");
        
    }
    
    /***
     * Býr til stage (svið) með því að hlaða inn notendaviðmóti sem hefur 
     * rót í skránni nafnAstage.fxml 
     * @param stage Sviðið (stage) 
     * @param nafnAStage Nafn á lýsingu á notendaviðmóti 
     * @throws IOException 
     */
    private void Stage(Stage stage, String nafnAStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nafnAStage
                + ".fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
}
