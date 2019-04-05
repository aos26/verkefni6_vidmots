/*
Ebba Þóra Hvannberg ebba@hi.is
 */
package is.hi.utlit;

import is.hi.vinnsla.Vedur;
import is.hi.vinnsla.Nidurstodur;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * Birtir veðurspár í töflu og nafn veðurstöðvar í textasviði
 *
 * @author Ebba Þóra Hvannberg ebba@hi.is
 */
public class SkodaVedurspaController implements Initializable {

    @FXML
    private Label vedurstod;
    @FXML
    private TableView<Vedur> vedurspaTafla;
    @FXML
    private TableColumn<Vedur, String> vedur;
    @FXML
    private TableColumn<Vedur, String> vindatt;
    @FXML
    private TableColumn<Vedur, String> hitastig;
    @FXML
    private TableColumn<Vedur, String> vindhradi;
    @FXML
    private TableColumn<Vedur, LocalDate> dagsetning;
    @FXML
    private TableColumn<Vedur, LocalTime> timi;
    @FXML
    private TableColumn<Vedur, Mynd> vedurMynd;
    @FXML
    private TableColumn<Vedur, String> daggarmark;

    public Label getVedurstod() {
        return vedurstod;
    }

    public TableColumn<Vedur, String> getVedur() {
        return vedur;
    }

    public TableColumn<Vedur, String> getVindatt() {
        return vindatt;
    }

    public TableColumn<Vedur, String> getHitastig() {
        return hitastig;
    }

    public TableColumn<Vedur, String> getVindhradi() {
        return vindhradi;
    }

    public TableColumn<Vedur, LocalDate> getDagsetning() {
        return dagsetning;
    }

    public TableColumn<Vedur, LocalTime> getTimi() {
        return timi;
    }

    public TableColumn<Vedur, String> getDaggarmark() {
        return daggarmark;
    }

    public TableColumn<Vedur, String> getSkyjahula() {
        return skyjahula;
    }

    public TableColumn<Vedur, String> getUppsofnudUrkoma() {
        return uppsofnudUrkoma;
    }
    @FXML
    private TableColumn<Vedur, String> skyjahula;
    @FXML
    private TableColumn<Vedur, String> uppsofnudUrkoma;

    public TableColumn<Vedur, Mynd> getVedurMynd() {
        return vedurMynd;
    }

    /**
     * Frumstillir stýriklasann. Setur upp vörpun á milli gagnaklasa og töflu
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tofluVorpunVedur();     
    }    

    /**
     * Býr til vörpun frá Vedur klasa og í töfludálka
     */
    private void tofluVorpunVedur() {
        // Hér fyrir neðan mætti hafa <> í staðinn fyrir <Vedur,String> 
        // en ég hef það til að skýra vörpunina
        vedur.setCellValueFactory(
                new PropertyValueFactory<Vedur,String>("w"));
        vindatt.setCellValueFactory( 
                new PropertyValueFactory<Vedur,String>("D"));
        vindhradi.setCellValueFactory( 
                new PropertyValueFactory<Vedur,String>("F"));
        hitastig.setCellValueFactory( 
                new PropertyValueFactory<Vedur,String>("T"));
        dagsetning.setCellValueFactory( 
                new PropertyValueFactory<Vedur,LocalDate>("dagsetning"));
        timi.setCellValueFactory( 
                new PropertyValueFactory<Vedur,LocalTime>("timi"));
        daggarmark.setCellValueFactory(
                new PropertyValueFactory<Vedur,String>("TD"));
        skyjahula.setCellValueFactory(
                new PropertyValueFactory<Vedur,String>("N"));
        uppsofnudUrkoma.setCellValueFactory(
                new PropertyValueFactory<Vedur,String>("R"));
        vedurMynd.setCellValueFactory(
                new PropertyValueFactory<Vedur,Mynd>("myndAfVedri"));
        
         vedurMynd.setCellFactory(new Callback<TableColumn<Vedur, Mynd>, TableCell<Vedur, Mynd>>() {

            @Override
            public TableCell<Vedur, Mynd> call(TableColumn<Vedur, Mynd> param) {
                return new MyndSella<>();
            }
        });
 }    

    /**
     * Birtir veðurspá á veðurstöð v. Uppfærir einnig textasvið
     * @param v veðurstöð
     * @param spaListi listi af veðurspám
     */
    void vedurspaBirta(Nidurstodur.Vedurstod v, List<Nidurstodur.Vedurstod.Vedurspa> spaListi) {
        vedurstod.setText(v.getName());
        ObservableList<Vedur> obs = geraVedurspaLista(spaListi);
        uppfaeraVedurspa(obs);
    }

    /** 
     * Plokkar út viðeigandi gögn úr frumgögnum og býr til nýja Vedur hluti og skilar
     * lista af þeim 
     * @param valdirLidir frumgögn af veðurspám 
     * @return lista af veðurspám (Vedur) 
     */
     public  ObservableList<Vedur> geraVedurspaLista(List<Nidurstodur.Vedurstod.Vedurspa> valdirLidir) {
        ObservableList<Vedur> obs = FXCollections.observableArrayList();
        for (Nidurstodur.Vedurstod.Vedurspa s : valdirLidir) {
            obs.add(new Vedur(s));
        }
        return obs;
    }
    
     /**
     * Uppfærir veðurspátöfluna með veðurspám 
     * @param obs veðurspárlisti 
     */
    public void uppfaeraVedurspa(ObservableList<Vedur> obs) {
        vedurspaTafla.setItems(obs);
    }

}
