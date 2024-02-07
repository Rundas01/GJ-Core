package gregicality.legacy.api.unification.material;

import gregtech.api.unification.Element;

import static gregtech.api.unification.Elements.add;

public class GCYLRElements {

    private GCYLRElements() {}

    public static final Element Tl205 = createAndRegisterIsotope(81, 124,-1, "Thallium-205", "Tl-205");
    public static final Element Tl206 = createAndRegisterIsotope(81, 125,252, "Thallium-206", "Tl-206");
    public static final Element Tl207 = createAndRegisterIsotope(81, 126,286, "Thallium-207", "Tl-207");
    public static final Element Tl208 = createAndRegisterIsotope(81, 127,183, "Thallium-208", "Tl-208");
    public static final Element Tl209 = createAndRegisterIsotope(81, 128,132, "Thallium-209", "Tl-209");
    public static final Element Tl210 = createAndRegisterIsotope(81, 129,78, "Thallium-210", "Tl-210");
    public static final Element Pb204 = createAndRegisterIsotope(82, 122,-1, "Lead-204", "Pb-204");
    public static final Element Pb205 = createAndRegisterIsotope(82, 123,-1, "Lead-205", "Pb-205");
    public static final Element Pb206 = createAndRegisterIsotope(82, 124,-1, "Lead-206", "Pb-206");
    public static final Element Pb207 = createAndRegisterIsotope(82, 125,-1, "Lead-207", "Pb-207");
    public static final Element Pb208 = createAndRegisterIsotope(82, 126,208, "Lead-208", "Pb-208");
    public static final Element Pb209 = createAndRegisterIsotope(82, 127,11710, "Lead-209", "Pb-209");
    public static final Element Pb210 = createAndRegisterIsotope(82, 128,703252800, "Lead-210", "Pb-210");
    public static final Element Pb211 = createAndRegisterIsotope(82, 129,2166, "Lead-211", "Pb-211");
    public static final Element Pb212 = createAndRegisterIsotope(82, 130,38304, "Lead-212", "Pb-212");
    public static final Element Pb213 = createAndRegisterIsotope(82, 131,612, "Lead-213", "Pb-213");
    public static final Element Pb214 = createAndRegisterIsotope(82, 132,1608, "Lead-214", "Pb-214");
    public static final Element Bi205 = createAndRegisterIsotope(83, 122,1322784, "Bismuth-202", "Bi-202");
    public static final Element Bi209 = createAndRegisterIsotope(83, 126,-1, "Bismuth-209", "Bi-209");
    public static final Element Bi210 = createAndRegisterIsotope(83, 127,433123, "Bismuth-210", "Bi-210");
    public static final Element Bi211 = createAndRegisterIsotope(83, 128,128, "Bismuth-211", "Bi-211");
    public static final Element Bi212 = createAndRegisterIsotope(83, 129,3633, "Bismuth-212", "Bi-212");
    public static final Element Bi213 = createAndRegisterIsotope(83, 130,2735, "Bismuth-213", "Bi-213");
    public static final Element Bi214 = createAndRegisterIsotope(83, 131,1194, "Bismuth-214", "Bi-214");
    public static final Element Bi215 = createAndRegisterIsotope(83, 132,456, "Bismuth-215", "Bi-215");
    public static final Element Po209 = createAndRegisterIsotope(84, 125,3248208000L, "Polonium-209", "Po-209");
    public static final Element Po210 = createAndRegisterIsotope(84, 126,-1, "Polonium-210", "Po-210");
    public static final Element Po211 = createAndRegisterIsotope(84, 127,1, "Polonium-211", "Po-211");
    public static final Element Po212 = createAndRegisterIsotope(84, 128,1, "Polonium-212", "Po-212");
    public static final Element Po213 = createAndRegisterIsotope(84, 129,1, "Polonium-213", "Po-213");
    public static final Element Po214 = createAndRegisterIsotope(84, 130,1, "Polonium-214", "Po-214");
    public static final Element Po215 = createAndRegisterIsotope(84, 131,1, "Polonium-215", "Po-215");
    public static final Element Po216 = createAndRegisterIsotope(84, 132,1, "Polonium-216", "Po-216");
    public static final Element Po218 = createAndRegisterIsotope(84, 134,183, "Polonium-218", "Po-218");
    public static final Element At215 = createAndRegisterIsotope(85, 130,1, "Astatine-215", "At-215");
    public static final Element At217 = createAndRegisterIsotope(85, 132,1, "Astatine-217", "At-217");
    public static final Element At218 = createAndRegisterIsotope(85, 133,2, "Astatine-218", "At-218");
    public static final Element At219 = createAndRegisterIsotope(85, 134,56, "Astatine-219", "At-219");
    public static final Element Rn217 = createAndRegisterIsotope(86, 131,1, "Radon-217", "Rn-217");
    public static final Element Rn218 = createAndRegisterIsotope(86, 132,1, "Radon-218", "Rn-218");
    public static final Element Rn219 = createAndRegisterIsotope(86, 133,4, "Radon-219", "Rn-219");
    public static final Element Rn222 = createAndRegisterIsotope(86, 136,330393, "Radon-222", "Rn-222");
    public static final Element Fr223 = createAndRegisterIsotope(87, 136,1308, "Francium-223", "Fr-223");
    public static final Element Ra221 = createAndRegisterIsotope(88, 133,28,"Radium-221", "Ra-221");
    public static final Element Ra223 = createAndRegisterIsotope(88, 135,987984, "Radium-223", "Ra-223");
    public static final Element Ra226 = createAndRegisterIsotope(88, 138,50520672000L, "Radium-226", "Ra-226");
    public static final Element Ra227 = createAndRegisterIsotope(88, 139,2532, "Radium-227", "Ra-227");
    public static final Element Ra228 = createAndRegisterIsotope(88, 140,181332000, "Radium-228", "Ra-228");
    public static final Element Ac227 = createAndRegisterIsotope(89, 138,686633328, "Actinium-227", "Ac-227");
    public static final Element Ac228 = createAndRegisterIsotope(89, 139,22140, "Actinium-228", "Ac-228");
    public static final Element Th227 = createAndRegisterIsotope(90, 137,1617408, "Thorium-227", "Th-227");
    public static final Element Th228 = createAndRegisterIsotope(90, 138,60284218, "Thorium-228", "Th-228");
    public static final Element Th231 = createAndRegisterIsotope(90, 141,91872, "Thorium-231", "Th-231");

    public static Element createAndRegisterIsotope(long protons, long neutrons, long halfLifeSeconds, String name, String symbol) {
        return add(protons,neutrons,halfLifeSeconds,null,name,symbol,true);
    }

    public static void init(){}
}
