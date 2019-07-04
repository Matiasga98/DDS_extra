package ui.representacion;

import org.apache.commons.collections15.Transformer;

public class SiONo implements Transformer<Boolean,String> {
    @Override
    public String transform(Boolean aBoolean) {
        return aBoolean?"SI":"NO";
    }
}
