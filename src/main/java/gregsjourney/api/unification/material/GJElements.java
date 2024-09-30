package gregsjourney.api.unification.material;

import static gregtech.api.unification.Elements.add;

import gregtech.api.unification.Element;

public class GJElements {

    private GJElements() {}

    public static Element Ad;

    public static void init() {
        Ad = add(119, 177, -1, null, "Ardite", "Ad", false);
    }
}
