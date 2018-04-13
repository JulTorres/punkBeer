
package fr.wildcodeschool.punkapi;

import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.List;

public class Method {

    public List<MashTemp> mashTemp = null;
    public Fermentation fermentation;
    public JsonValue twist;

    Method(List<MashTemp> mashTemp, Fermentation fermentation, JsonValue twist) {
        this.mashTemp = mashTemp;
        this.fermentation = fermentation;
        this.twist = twist;
    }
}
