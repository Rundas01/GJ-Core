package gregsjourney.api.unification.material;

import static gregtech.api.unification.Elements.*;
import static gregtech.api.unification.Elements.add;

import gregtech.api.unification.Element;

public class GJElements {

    private GJElements() {}

    //Krypton
    public static Element Kr88;
    //Barium
    public static Element Ba145;
    //Thallium
    public static Element Tl207;
    public static Element Tl208;
    //Lead
    public static Element Pb207;
    public static Element Pb208;
    public static Element Pb211;
    public static Element Pb212;
    //Bismuth
    public static Element Bi211;
    public static Element Bi212;
    public static Element Bi215;
    //Polonium
    public static Element Po211;
    public static Element Po212;
    public static Element Po215;
    public static Element Po216;
    //Astatine
    public static Element At215;
    public static Element At219;
    //Radon
    public static Element Rn219;
    public static Element Rn220;
    //Francium
    public static Element Fr223;
    //Radium
    public static Element Ra223;
    public static Element Ra224;
    public static Element Ra227;
    public static Element Ra228;
    //Actinium
    public static Element Ac227;
    public static Element Ac228;
    //Thorium
    public static Element Th227;
    public static Element Th228;
    public static Element Th231;
    public static Element Th232;
    //Protactinium
    public static Element Pa231;
    public static Element Pa232;
    //Uranium
    public static Element U232;
    public static Element U236;
    public static Element U237;
    //Neptunium
    public static Element Np236;
    public static Element Np237;
    public static Element Np239;
    //Plutonium
    public static Element Pu236;
    public static Element Pu243;
    //Americium
    public static Element Am241;
    public static Element Am243;
    //Curium
    public static Element Cm245;
    public static Element Cm247;

    public static void init() {
        //Krypton
        Kr88 = registerIsotope(88, Kr);
        //Barium
        Ba145 = registerIsotope(145, Ba);
        //Thallium
        Tl207 = registerIsotope(207, Tl);
        Tl208 = registerIsotope(208, Tl);
        //Lead
        Pb207 = registerIsotope(207, Pb);
        Pb208 = registerIsotope(208, Pb);
        Pb211 = registerIsotope(211, Pb);
        Pb212 = registerIsotope(212, Pb);
        //Bismuth
        Bi211 = registerIsotope(211, Bi);
        Bi212 = registerIsotope(212, Bi);
        Bi215 = registerIsotope(215, Bi);
        //Polonium
        Po211 = registerIsotope(211, Po);
        Po212 = registerIsotope(212, Po);
        Po215 = registerIsotope(215, Po);
        Po216 = registerIsotope(216, Po);
        //Astatine
        At215 = registerIsotope(215, At);
        At219 = registerIsotope(219, At);
        //Radon
        Rn219 = registerIsotope(219, Rn);
        Rn220 = registerIsotope(220, Rn);
        //Francium
        Fr223 = registerIsotope(223, Fr);
        //Radium
        Ra223 = registerIsotope(223, Ra);
        Ra224 = registerIsotope(224, Ra);
        Ra227 = registerIsotope(227, Ra);
        Ra228 = registerIsotope(228, Ra);
        //Actinium
        Ac227 = registerIsotope(227, Ac);
        Ac228 = registerIsotope(228, Ac);
        //Thorium
        Th227 = registerIsotope(227, Th);
        Th228 = registerIsotope(228, Th);
        Th231 = registerIsotope(231, Th);
        Th232 = registerIsotope(232, Th);
        //Protactinium
        Pa231 = registerIsotope(231, Pa);
        Pa232 = registerIsotope(232, Pa);
        //Uranium
        U232 = registerIsotope(232, U);
        U236 = registerIsotope(236, U);
        U237 = registerIsotope(237, U);
        //Neptunium
        Np236 = registerIsotope(236, Np);
        Np237 = registerIsotope(237, Np);
        Np239 = registerIsotope(239, Np);
        //Plutonium
        Pu236 = registerIsotope(236, Pu);
        Pu243 = registerIsotope(243, Pu);
        //Americium
        Am241 = registerIsotope(241, Am);
        Am243 = registerIsotope(243, Am);
        //Curium
        Cm245 = registerIsotope(245, Cm);
        Cm247 = registerIsotope(247, Cm);
    }

    private static Element registerIsotope(long nucleons, Element baseIsotope) {
        long protons = baseIsotope.getProtons();
        return add(protons, nucleons - protons, -1, null, baseIsotope.getName() + "-" + nucleons, baseIsotope.getSymbol() + "-" + nucleons, true);
    }
}
