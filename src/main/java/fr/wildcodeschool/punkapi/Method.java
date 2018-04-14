
package fr.wildcodeschool.punkapi;

import javax.json.JsonValue;
import java.util.List;

public class Method {

    public List<MashTemp> mashTemp;
    private Fermentation fermentation;
    private JsonValue twist;

    Method(List<MashTemp> mashTemp, Fermentation fermentation, JsonValue twist) {
        this.mashTemp = mashTemp;
        this.fermentation = fermentation;
        this.twist = twist;
    }
}
