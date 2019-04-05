/*
Ebba Þóra Hvannberg ebba@hi.is
 */
package is.hi.utlit;

import javafx.scene.control.TableCell;
import javafx.scene.image.ImageView;

/**
 * Stjórnar hvernig mynd birtist í töflu
 *
 * @author Ebba Þóra Hvannberg ebba@hi.is Fyrirmynd fengin frá riptutorial
 * Háskóli Íslands
 */

public class MyndSella<T> extends TableCell<T, Mynd> {

    private final ImageView image;  // Myndin

    public MyndSella() {

        image = new ImageView();
        image.setFitWidth(64);
        image.setFitHeight(64);
        image.setPreserveRatio(true);

        setGraphic(image);
        setMinHeight(70);
    }

    /**
     * Uppfærir myndina 
     * @param mynd sellan er uppfærð með mynd 
     * @param ekkert ef ekkert er til staðar 
     */
    @Override
    protected void updateItem(Mynd mynd, boolean ekkert) {
        super.updateItem(mynd, ekkert);

        if (ekkert || mynd == null) {
            image.setImage(null);
        } else {
            image.setImage(mynd.getVedurMynd());

        }
    }
}
