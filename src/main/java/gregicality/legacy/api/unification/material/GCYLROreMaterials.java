package gregicality.legacy.api.unification.material;

import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.LIGNITE;
import static gregtech.api.unification.material.info.MaterialIconSet.SHINY;

public class GCYLROreMaterials {
    private GCYLROreMaterials() {}

    public static Material Petalite;
    public static Material Amblygonite;
    public static Material Thortveitite;
    public static Material Perovskite;
    public static Material Titanomagnetite;
    public static Material Fluorapatite;
    public static Material VanadiferousTitanomagnetite;
    public static Material Baddeleyite;
    public static Material Vanadinite;
    public static Material  Cerussite;
    public static Material Anglesite;
    public static Material Enargite;
    public static Material Smithsonite;
    public static Material Arsenopyrite;
    public static Material Acanthite;
    public static Material Pyrargyrite;
    public static Material Stephanite;
    public static Material Proustite;
    public static Material Celestine;
    public static Material Strontianite;
    public static Material Witherite;
    public static Material Wolframite;
    public static Material Rhodplumsite;
    public static Material Bowieite;
    public static Material Polarite;
    public static Material Livingstonite;
    public static Material Bismuthinite;
    public static Material Lorandite;
    public static Material Hutchinsonite;
    public static Material Crookesite;
    public static Material Dilithium;
    public static Material Anthracite;
    public static Material Phosphorite;
    public static Material Sperrylite;
    public static Material Kernite;
    public static Material Colemanite;
    public static Material Ulexite;
    public static Material Carnallite;
    public static Material Kainite;
    public static Material Kieserite;
    public static Material Polyhalite;
    public static Material Titanite;
    public static Material Leucoxene;
    public static Material Zircon;
    public static Material Mimetite;
    public static Material Pyromorphite;
    public static Material Descloizite;
    public static Material Mottramite;
    public static Material Chlorapatite;
    public static Material Hematite;
    public static Material Skutterudite;
    public static Material Allanite;
    public static Material Azurite;
    public static Material Goethite;
    public static Material Hydrozincite;
    public static Material Chlorargyrite;
    public static Material Columbite;
    public static Material Ferrimolybdite;
    public static Material Aguilarite;
    public static Material Orpiment;
    public static Material Braggite;
    public static Material Stannite;
    public static Material Euxenite;
    public static Material Xenotime;
    public static Material Caliche;
    public static Material Lauterite;
    public static Material Langbeinite;
    public static Material Hydroxyapatite;
    public static Material Rhodochrosite;
    public static Material Carnotite;
    public static Material Cryolite;
    public static void register() {

        Petalite = new Material.Builder(7000, gcylrId("petalite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .color(0xd2f7f6)
                .components(Lithium, 1, Aluminium, 1, Silicon, 4, Oxygen, 10)
                .build();

        Amblygonite = new Material.Builder(7001, gcylrId("amblygonite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .color(0xf7f5d2)
                .components(Lithium, 1, Aluminium, 1, Phosphorus, 1, Oxygen, 4, Fluorine, 1)
                .build()
                .setFormula("(Li,Na)AlPO4(F,OH)", true);

        Thortveitite = new Material.Builder(7002, gcylrId("thortveitite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .color(0xb38969)
                .components(Scandium, 1, Yttrium, 1, Silicon, 2, Oxygen, 7)
                .build()
                .setFormula("(Sc,Y)2Si2O7", true);

        Perovskite = new Material.Builder(7003, gcylrId("perovskite"))
                .gem().ore()
                .flags(NO_SMELTING)
                .iconSet(SHINY)
                .color(0x0b3d1d)
                .components(Calcium, 1, Titanium, 1, Oxygen, 3)
                .build();

        Titanomagnetite = new Material.Builder(7004, gcylrId("titanomagnetite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .color(0x555c45)
                .components(Iron, 1, Titanium, 1, Oxygen, 4)
                .build()
                .setFormula("Fe(Fe,Ti)2O4", true);

        Fluorapatite = new Material.Builder(7005, gcylrId("fluorapatite"))
                .gem().ore()
                .flags(NO_SMELTING)
                .iconSet(SHINY)
                .color(0xcc9681)
                .components(Calcium, 5, Phosphorus, 3, Oxygen, 12, Fluorine, 1)
                .build()
                .setFormula("Ca5(PO4)3F", true);
        

        VanadiferousTitanomagnetite = new Material.Builder(7006, gcylrId("vanadiferous_titanomagnetite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .color(0x87685c)
                .components(Vanadium, 1, Iron, 1, Titanium, 1, Oxygen, 4)
                .build()
                .setFormula("(V,Fe,Ti)2O4", true);

        Baddeleyite = new Material.Builder(7007, gcylrId("baddeleyite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .color(0x3a4f43)
                .components(Zirconium, 1, Oxygen, 2)
                .build()
                .setFormula("ZrO2", true);

        Vanadinite = new Material.Builder(7008, gcylrId("vanadinite"))
                .gem().ore()
                .flags(NO_SMELTING)
                .iconSet(SHINY)
                .color(0xbf5915)
                .components(Lead, 5, Vanadium, 3, Oxygen, 12, Chlorine, 1)
                .build()
                .setFormula("Pb5(VO4)3Cl", true);

        Cerussite = new Material.Builder(7009, gcylrId("cerussite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .color(0xe0d9ba)
                .components(Lead, 1, Carbon, 1, Oxygen, 3)
                .build();

        Anglesite = new Material.Builder(7010, gcylrId("anglesite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .color(0xe0ded5)
                .components(Lead, 1, Sulfur, 1, Oxygen, 4)
                .build();

        Enargite = new Material.Builder(7011, gcylrId("enargite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .iconSet(SHINY)
                .color(0xc9c7bd)
                .components(Copper, 3, Arsenic, 1, Sulfur, 4)
                .build();

        Smithsonite = new Material.Builder(7012, gcylrId("smithsonite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .color(0x6aadab)
                .components(Zinc, 1, Carbon, 1, Oxygen, 3)
                .build();

        Arsenopyrite = new Material.Builder(7013, gcylrId("arsenopyrite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .iconSet(SHINY)
                .color(0x7d7269)
                .components(Iron, 1, Arsenic, 1, Sulfur, 1)
                .build();

        Acanthite = new Material.Builder(7014, gcylrId("acanthite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .iconSet(SHINY)
                .color(0x2c332d)
                .components(Silver, 2, Sulfur, 1)
                .build();

        Pyrargyrite = new Material.Builder(7015, gcylrId("pyrargyrite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .iconSet(SHINY)
                .color(0x362a2a)
                .components(Silver, 3, Antimony, 1, Sulfur, 3)
                .build();

        Stephanite = new Material.Builder(7016, gcylrId("stephanite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .iconSet(SHINY)
                .color(0x3d3d36)
                .components(Silver, 5, Antimony, 1, Sulfur, 4)
                .build();

        Proustite = new Material.Builder(7017, gcylrId("proustite"))
                .dust().gem().ore()
                .flags(NO_SMELTING)
                .iconSet(SHINY)
                .color(0x73142d)
                .components(Silver, 3, Arsenic, 1, Sulfur, 3)
                .build();

        Celestine = new Material.Builder(7018, gcylrId("celestine"))
                .dust().gem().ore()
                .flags(NO_SMELTING)
                .color(0xafe3e0)
                .iconSet(SHINY)
                .components(Strontium, 1, Sulfur, 1, Oxygen, 4)
                .build();

        Strontianite = new Material.Builder(7019, gcylrId("strontianite"))
                .dust().gem().ore()
                .flags(NO_SMELTING)
                .color(0xe3d3af)
                .iconSet(SHINY)
                .components(Strontium, 1, Carbon, 1, Oxygen, 3)
                .build();

        Witherite = new Material.Builder(7020, gcylrId("witherite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .color(0xd1d0bc)
                .components(Barium, 1, Carbon, 1, Oxygen, 3)
                .build();

        Wolframite = new Material.Builder(7021, gcylrId("wolframite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .color(0x1e1c36)
                .components(Iron, 1, Manganese, 1, Tungsten, 1, Oxygen, 4)
                .build()
                .setFormula("(Fe,Mn)WO4", true);

        Rhodplumsite = new Material.Builder(7022, gcylrId("rhodplumsite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .color(0x292726)
                .components(Rhodium, 3, Lead, 2, Sulfur, 2)
                .build();

        Bowieite = new Material.Builder(7023, gcylrId("bowieite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .color(0x323325)
                .components(Rhodium, 2, Sulfur, 3)
                .build()
                .setFormula("(Rh,Ir,Pt)2S3", true);

        Polarite = new Material.Builder(7024, gcylrId("polarite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .color(0x2e360a)
                .components(Palladium, 1, Bismuth, 1, Lead, 1)
                .build()
                .setFormula("Pd(Bi,Pb)", true);

        Livingstonite = new Material.Builder(7025, gcylrId("livingstonite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .color(0x5e5654)
                .components(Mercury, 1, Antimony, 4, Sulfur, 8)
                .build();

        Bismuthinite = new Material.Builder(7026, gcylrId("bismuthinite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .color(0x85795b)
                .components(Bismuth, 2, Sulfur, 3)
                .build();

        Lorandite = new Material.Builder(7027, gcylrId("lorandite"))
                .dust().gem().ore()
                .flags(NO_SMELTING)
                .color(0x913111)
                .components(Thallium, 1, Arsenic, 1, Sulfur, 2)
                .build();

        Hutchinsonite = new Material.Builder(7028, gcylrId("hutchinsonite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .iconSet(SHINY)
                .color(0x332f2e)
                .components(Thallium, 1, Lead, 1, Arsenic, 5, Sulfur, 9)
                .build()
                .setFormula("(Tl,Pb)2As5S9", true);

        Crookesite = new Material.Builder(7029, gcylrId("crookesite"))
                .dust().ore()
                .flags(NO_SMELTING)
                .color(0x3e4a3f)
                .components(Copper, 7, Thallium, 1, Silver, 1, Selenium, 4)
                .build()
                .setFormula("Cu7(Tl,Ag)Se4", true);

        Dilithium = new Material.Builder(7032, gcylrId("dilithium"))
                .gem().ore()
                .components(Lithium, 2)
                .flags(DISABLE_DECOMPOSITION)
                .colorAverage()
                .build();

        Anthracite = new Material.Builder(7034, gcylrId("anthracite"))
                .gem(1, 2000).ore(2, 1)
                .color(0x241212).iconSet(LIGNITE)
                .components(Carbon, 1)
                .flags(FLAMMABLE, NO_SMELTING, NO_SMASHING, MORTAR_GRINDABLE)
                .build();

        Phosphorite = new Material.Builder(7035, gcylrId("phosphorite"))
                .dust()
                .iconSet(SHINY)
                .color(0x8a6e42)
                .build();

        Sperrylite = new Material.Builder(7036, gcylrId("sperrylite"))
                .gem().ore()
                .components(Platinum, 1, Arsenic, 2)
                .iconSet(SHINY)
                .color(0xb3af86)
                .build();

        Kernite = new Material.Builder(7037, gcylrId("kernite"))
                .dust()
                .color(0xd9c1a0)
                .components(Sodium, 2, Boron, 4, Oxygen, 8, Hydrogen, 2, Water, 3)
                .build()
                .setFormula("Na2B4O6(OH)2 * (H2O)3", true);

        Colemanite = new Material.Builder(7038, gcylrId("colemanite"))
                .dust()
                .color(0xdbcd8f)
                .components(Calcium, 2, Boron, 6, Oxygen, 11, Water, 5)
                .build()
                .setFormula("Ca2B6O11 * (H2O)5", true);

        Ulexite = new Material.Builder(7039, gcylrId("ulexite"))
                .dust()
                .color(0xc2aba1)
                .components(Sodium, 1, Calcium, 1, Boron, 5, Oxygen, 12, Hydrogen, 6, Water, 5)
                .build()
                .setFormula("NaCaB5O6(OH)6 * (H2O)5", true);

        Carnallite = new Material.Builder(7040, gcylrId("carnallite"))
                .dust()
                .iconSet(SHINY)
                .color(0xffffff)
                .components(Potassium, 1, Magnesium, 1, Chlorine, 3, Water, 6)
                .build()
                .setFormula("KCl.MgCl2 * (H2O)6", true);

        Kainite = new Material.Builder(7041, gcylrId("kainite"))
                .dust()
                .iconSet(SHINY)
                .color(0xfff7c4)
                .components(Potassium, 1, Magnesium, 1, Sulfur, 1, Oxygen, 4, Chlorine, 1, Water, 3)
                .build()
                .setFormula("KMg(SO4)Cl * (H2O)3", true);

        Kieserite = new Material.Builder(7042, gcylrId("kieserite"))
                .dust()
                .color(0xdccade)
                .components(Magnesium, 1, Sulfur, 1, Oxygen, 4, Water, 1)
                .build()
                .setFormula("MgSO4 * H2O", true);

        Polyhalite = new Material.Builder(7043, gcylrId("polyhalite"))
                .dust()
                .color(0xe0b2a4)
                .components(Potassium, 2, Calcium, 2, Magnesium, 1, Sulfur, 4, Oxygen, 16, Water, 2)
                .build()
                .setFormula("K2Ca2Mg(SO4)4 * (H2O)2", true);

        Titanite = new Material.Builder(7044, gcylrId("titanite"))
                .gem()
                .iconSet(SHINY)
                .color(0x967051)
                .components(Calcium, 1, Titanium, 1, Silicon, 1, Oxygen, 5)
                .build();

        Leucoxene = new Material.Builder(7045, gcylrId("leucoxene"))
                .dust()
                .iconSet(SHINY)
                .color(0xb987c4)
                .components(Titanium, 1, Oxygen, 2)
                .build();

        Zircon = new Material.Builder(7046, gcylrId("zircon"))
                .gem().ore()
                .iconSet(SHINY)
                .color(0xf05c51)
                .components(Zirconium, 1, Silicon, 1, Oxygen, 4)
                .build();

        Mimetite = new Material.Builder(7047, gcylrId("mimetite"))
                .dust()
                .iconSet(SHINY)
                .color(0xe8aa2e)
                .components(Lead, 5, Arsenic, 3, Oxygen, 12, Chlorine, 1)
                .build()
                .setFormula("Pb5(AsO4)3Cl", true);

        Pyromorphite = new Material.Builder(7048, gcylrId("pyromorphite"))
                .dust()
                .iconSet(SHINY)
                .color(0xd7e632)
                .components(Lead, 5, Phosphorus, 4, Oxygen, 12, Chlorine, 1)
                .build()
                .setFormula("Pb5(PO4)3Cl", true);

        Descloizite = new Material.Builder(7049, gcylrId("descloizite"))
                .dust()
                .iconSet(SHINY)
                .color(0x5e5d41)
                .components(Lead, 1, Zinc, 1, Vanadium, 1, Oxygen, 5, Hydrogen, 1)
                .build()
                .setFormula("(Pb,Zn)2VO4OH", true);

        Mottramite = new Material.Builder(7050, gcylrId("mottramite"))
                .dust()
                .color(0x53730a)
                .components(Lead, 1, Copper, 1, Vanadium, 1, Oxygen, 5, Hydrogen, 1)
                .build()
                .setFormula("PbCu(VO4)(OH)", true);

        Chlorapatite = new Material.Builder(7051, gcylrId("chlorapatite"))
                .dust().gem().ore()
                .iconSet(SHINY)
                .color(0xc7af85)
                .components(Calcium, 5, Phosphorus, 3, Oxygen, 12, Chlorine, 1)
                .build()
                .setFormula("Ca5(PO4)3Cl", true);

        Hematite = new Material.Builder(7052, gcylrId("hematite"))
                .dust()
                .color(0x240f0f)
                .components(Iron, 2, Oxygen, 3)
                .build();

        Skutterudite = new Material.Builder(7053, gcylrId("skutterudite"))
                .dust()
                .iconSet(SHINY)
                .color(0xabbec2)
                .components(Cobalt, 1, Arsenic, 3)
                .build();

        Allanite = new Material.Builder(7054, gcylrId("allanite"))
                .dust()
                .iconSet(SHINY)
                .color(0x6b7a7d)
                .components(Cerium, 1, Calcium, 1, Yttrium, 1, Lanthanum, 1, Aluminium, 1, Iron, 1, Silicon, 3, Oxygen, 13, Hydrogen, 1)
                .build()
                .setFormula("(Ce,Ca,Y,La)2(Al,Fe)3(SiO4)3(OH)", true);

        Azurite = new Material.Builder(7055, gcylrId("azurite"))
                .dust().gem()
                .iconSet(SHINY)
                .color(0x2216c9)
                .components(Copper, 3, Carbon, 2, Oxygen, 8, Hydrogen, 2)
                .build()
                .setFormula("Cu3(CO3)2(OH)2", true);

        Goethite = new Material.Builder(7056, gcylrId("goethite"))
                .dust()
                .color(0x30251f)
                .components(Iron, 1, Oxygen, 2, Hydrogen, 1)
                .build()
                .setFormula("α-FeO(OH)", true);

        Hydrozincite = new Material.Builder(7057, gcylrId("hydrozincite"))
                .dust()
                .color(0xebd5ca)
                .components(Zinc, 5, Carbon, 2, Oxygen, 12, Hydrogen, 6)
                .build()
                .setFormula("Zn5(CO3)2(OH)6", true);

        Chlorargyrite = new Material.Builder(7058, gcylrId("chlorargyrite"))
                .dust()
                .color(0x524728)
                .components(Silver, 1, Chlorine, 1)
                .build();

        Columbite = new Material.Builder(7059, gcylrId("columbite"))
                .ore().dust()
                .color(0x574f2f)
                .iconSet(SHINY)
                .components(Iron, 1, Manganese, 1, Niobium, 2, Oxygen, 6)
                .build()
                .setFormula("(Fe,Mn)Nb2O6", true);

        Ferrimolybdite = new Material.Builder(7060, gcylrId("ferrimolybdite"))
                .dust()
                .color(0xc1c71c)
                .components(Iron, 2, Molybdenum, 3, Oxygen, 12, Water, 8)
                .build()
                .setFormula("Fe2(MoO4)3 * (H2O)8", true);

        Aguilarite = new Material.Builder(7061, gcylrId("aguilarite"))
                .dust().gem()
                .color(0x303030)
                .iconSet(SHINY)
                .components(Silver, 4, Selenium, 1, Sulfur, 1)
                .build();

        Orpiment = new Material.Builder(7062, gcylrId("orpiment"))
                .dust().gem()
                .color(0xc28034)
                .iconSet(SHINY)
                .components(Arsenic, 2, Sulfur, 3)
                .build();

        Braggite = new Material.Builder(7063, gcylrId("braggite"))
                .dust()
                .color(0x686b5f)
                .components(Platinum, 1, Palladium, 1, Nickel, 1, Sulfur, 1)
                .build()
                .setFormula("(Pt,Pd,Ni)S", true);

        Stannite = new Material.Builder(7064, gcylrId("stannite"))
                .dust()
                .color(0x474f3e)
                .components(Copper, 2, Iron, 1, Tin, 1, Sulfur, 4)
                .build();

        Euxenite = new Material.Builder(7065, gcylrId("euxenite"))
                .dust()
                .color(0x9c973e)
                .components(Yttrium, 1, Calcium, 1, Cerium, 1, Thorium, 1, Niobium, 1, Tantalum, 1, Titanium, 1, Oxygen, 6)
                .build()
                .setFormula("(Y,Ca,Ce,U,Th)(Nb,Ta,Ti)2O6", true);

        Xenotime = new Material.Builder(7066, gcylrId("xenotime"))
                .dust()
                .iconSet(SHINY)
                .color(0x6e572e)
                .components(Yttrium, 1, Phosphorus, 1, Oxygen, 4)
                .build();

        Caliche = new Material.Builder(7067, gcylrId("caliche"))
                .dust()
                .color(0xb3ae98)
                .components(Calcium, 1, Carbon, 1, Oxygen, 3)
                .build();

        Lauterite = new Material.Builder(7068, gcylrId("lauterite"))
                .dust()
                .components(Calcium, 1, Iodine, 2, Oxygen, 6)
                .colorAverage()
                .build()
                .setFormula("Ca(IO3)2", true);

        Langbeinite = new Material.Builder(7069, gcylrId("langbeinite"))
                .dust()
                .components(Potassium, 2, Magnesium, 2, Sulfur, 3, Oxygen, 12)
                .colorAverage()
                .build()
                .setFormula("K2Mg2(SO4)3", true);

        Hydroxyapatite = new Material.Builder(7070, gcylrId("hydroxyapatite"))
                .dust().gem().ore()
                .iconSet(SHINY)
                .color(0xc7afaa)
                .components(Calcium, 5, Phosphorus, 3, Oxygen, 13, Hydrogen, 1)
                .build()
                .setFormula("Ca5(PO4)3OH", true);

        Rhodochrosite = new Material.Builder(7071, gcylrId("rhodochrosite"))
                .ore()
                .iconSet(SHINY)
                .components(Manganese, 1, Carbon, 1, Oxygen, 3)
                .colorAverage()
                .build();

        Carnotite = new Material.Builder(7072, gcylrId("carnotite"))
                .ore()
                .components(Potassium, 2, Uranium235, 2, Oxygen, 12, Vanadium, 2, Water, 3)
                .colorAverage()
                .build()
                .setFormula("K2(UO2)2(VO4)2 * 3H2O", true);

        Cryolite = new Material.Builder(3611, gcylrId("cryolite"))
                .fluid().dust().ore()
                .components(Sodium, 3, Aluminium, 1, Fluorine, 6)
                .color(0x2497a6)
                .build();
    }
}
