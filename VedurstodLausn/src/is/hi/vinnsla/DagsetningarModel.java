/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.vinnsla;

import java.util.LinkedHashSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Andrea Ósk Sigurðardóttir - aos26@hi.is
 */
public class DagsetningarModel {
    private ObservableList<String> dagsItems;    
    private static final String ALLIR_DAGAR = "allir dagar";
    /**
     * Smiður fyrir KlukkustundirModel. Tekur inn lista af veðurspám 
     * Síar út mengi af klukkustundum sem veðurspár eru gefnar fyrir 
     * @param dLidir fylki af dagskrárliðum 
     */
    public DagsetningarModel(ObservableList<Nidurstodur.Vedurstod.Vedurspa> dLidir) {
        
        // Náum í klukkustundir 
        LinkedHashSet dagar = new LinkedHashSet();
        final int BIL = 5; 
        dagar.add(ALLIR_DAGAR);
        for (Nidurstodur.Vedurstod.Vedurspa r:dLidir) {
           String u = r.getFtime().substring(BIL,BIL+5);
           dagar.add(u);
        }
   
        dagsItems = FXCollections.observableArrayList();
        // Setjum dagsetningar í módelið 
        for (Object t:dagar)
            dagsItems.add(t.toString());
    }


    public ObservableList<String> getItems() {
       return dagsItems;
    }
    
    public void setItems(ObservableList<String> o) {
       dagsItems = o;
    }
    
    
}
