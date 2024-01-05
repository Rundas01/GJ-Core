package gregicality.legacy.api.unification.material;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;

public final class GCYLRUniversalChemicalMaterials {

    private GCYLRUniversalChemicalMaterials() {}

    public static Material ImpureSodiumHydroxideSolution;
    public static Material SodiumHydroxideSolution;
    public static Material HydrogenFluoride;
    public static Material HydrogenChloride;
    public static Material SodiumNitrite;
    public static Material NitrousAcid;
    public static Material CalciumOxide;
    public static Material SulfurDichloride;
    public static Material SodiumSulfate;
    public static Material ChloricAcid;
    public static Material ChlorineMonoxide;

    public static void init() {

        ImpureSodiumHydroxideSolution = new Material.Builder(4000, gcylrId("impure_sodium_hydroxide_solution"))
                .fluid()
                .color(0x3a4991)
                .build()
                .setFormula("?",true);

        SodiumHydroxideSolution = new Material.Builder(4001,gcylrId("sodium_hydroxide_solution"))
                .fluid()
                .color(SodiumHydroxide.getMaterialRGB()-20)
                .components(SodiumHydroxide,1,Water,1)
                .build();

        HydrogenFluoride = new Material.Builder(4002, gcylrId("hydrogen_fluoride"))
                .gas()
                .components(Hydrogen, 1, Fluorine, 1)
                .colorAverage()
                .build();

        HydrogenChloride = new Material.Builder(4003, gcylrId("hydrogen_chloride"))
                .gas()
                .components(Hydrogen, 1, Chlorine, 1)
                .colorAverage()
                .build();

        SodiumNitrite = new Material.Builder(4004,gcylrId("sodium_nitrite"))
                .dust()
                .colorAverage()
                .iconSet(SHINY)
                .components(Sodium,1,Nitrogen,1,Oxygen,2)
                .build();

        NitrousAcid = new Material.Builder(4005,gcylrId("nitrous_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .colorAverage()
                .components(Hydrogen,1,Nitrogen,1,Oxygen,2)
                .build();

        CalciumOxide = new Material.Builder(4006,gcylrId("calcium_oxide"))
                .dust()
                .colorAverage()
                .iconSet(DULL)
                .components(Calcium,1,Oxygen,1)
                .build();

        SulfurDichloride = new Material.Builder(4007, gcylrId("sulfur_dichloride"))
                .fluid()
                .components(Sulfur, 1, Chlorine, 2)
                .colorAverage()
                .build();

        SodiumSulfate = new Material.Builder(4008,gcylrId("sodium_sulfate"))
                .dust()
                .colorAverage()
                .iconSet(DULL)
                .components(Sodium,2,Sulfur,1,Oxygen,4)
                .build();

        ChloricAcid = new Material.Builder(4009,gcylrId("chloric_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .colorAverage()
                .components(Hydrogen,1,Chlorine,1,Oxygen,3)
                .build();

        ChlorineMonoxide = new Material.Builder(4010, gcylrId("chlorine_monoxide"))
                .fluid()
                .components(Chlorine,1,Oxygen,1)
                .colorAverage()
                .build();

        /*Polyimide = new Material.Builder(3216,gcylrId("polyimide"))
                .ingot()
                .iconSet(DULL)
                .color(0xFF7F50)
                .components(Carbon,22,Hydrogen,12,Nitrogen,2,Oxygen,6)
                .flags(GENERATE_PLATE,FLAMMABLE,NO_SMASHING,DISABLE_DECOMPOSITION)
                .build();

        FluorinatedEthylenePropylene = new Material.Builder(3216,gcylrId("fluorinated_ethylene_propylene"))
                .ingot()
                .iconSet(DULL)
                .color(0xC8C8C8)
                .components(Carbon,5,Fluorine,10)
                .flags(GENERATE_PLATE,FLAMMABLE,NO_SMASHING,DISABLE_DECOMPOSITION)
                .build();

        Polyurethane = new Material.Builder(3217,gcylrId("polyurethane"))
                .ingot()
                .iconSet(DULL)
                .color(0xeffcef)
                .components(Carbon,17,Hydrogen,16,Nitrogen,2,Oxygen,4)
                .flags(EXCLUDE_BLOCK_CRAFTING_RECIPES,GENERATE_ROD,NO_SMASHING,DISABLE_DECOMPOSITION)
                .build();

        Polyetheretherketone = new Material.Builder(3218,gcylrId("polyetheretherketone"))
                .ingot()
                .iconSet(DULL)
                .color(0x403e37)
                .components(Carbon,20,Hydrogen,12,Oxygen,3)
                .flags(EXCLUDE_BLOCK_CRAFTING_RECIPES,NO_SMASHING,GENERATE_FOIL,DISABLE_DECOMPOSITION,GENERATE_FINE_WIRE)
                .build();

        Zylon = new Material.Builder(3219,gcylrId("zylon"))
                .ingot()
                .iconSet(SHINY)
                .color(0xFFE000)
                .components(Carbon,14,Hydrogen,6,Nitrogen,2,Oxygen,2)
                .flags(EXCLUDE_BLOCK_CRAFTING_RECIPES,GENERATE_FOIL,NO_SMASHING,DISABLE_DECOMPOSITION)
                .build();
        
        Ethylhexanol = new Material.Builder(3220, gcylrId("ethylhexanol"))
                .fluid()
                .color(0xfeea9a)
                .components(Carbon,8,Hydrogen,18,Oxygen,1)
                .build();

        DiethylhexylPhosphoricAcid = new Material.Builder("di_ethylhexyl_phosphoric_acid", 0xffff99, "C16H35O4P")
                .fluid()
                .color(0xffff99)
                .components(Carbon,16,Hydrogen,35,Oxygen,4,Phosphorus,1)
                .build();

        Turpentine = new Material.Builder("turpentine", 0x93bd46, "C10H16")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Acetylene = new Material.Builder("acetylene", 0x959c60, "C2H2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Formaldehyde = new Material.Builder("formaldehyde", 0x95a13a, "CH2O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        PropargylAlcohol = new Material.Builder("propargyl_alcohol", 0xbfb32a, "CHCCH2OH")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        PropargylChloride = new Material.Builder("propargyl_chloride", 0x918924, "HC2CH2Cl")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Citral = new Material.Builder("citral", 0xf2e541, "C10H16O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        BetaIonone = new Material.Builder("beta_ionone", 0xdc5ce6, "C13H20O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        VitaminA = new Material.Builder("vitamin_a", 0x8d5c91, "C20H30O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        EthyleneOxide = new Material.Builder("ethylene_oxide", 0xa0c3de, "C2H4O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Ethanolamine = new Material.Builder("ethanolamine", 0x6f7d87, "HOCH2CH2NH2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Biotin = new Material.Builder("biotin", 0x68cc6a, "C10H16N2O3S")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        B27Supplement = new Material.Builder("b27_supplement", 0x386939, "C142H230N36O44S")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Catalase = new Material.Builder("catalase", 0xdb6596, "?")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Blood = new Material.Builder("blood", 0x5c0606, "Blood")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        BloodCells = new Material.Builder("blood_cells", 0xad2424, "???")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        BloodPlasma = new Material.Builder("blood_plasma", 0xe37171, "???")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        NitroBenzene = new Material.Builder("nitro_benzene", 0x81c951, "C6H5NO2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Aniline = new Material.Builder("aniline", 0x4c911d, "C6H5NH2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Sulfanilamide = new Material.Builder("sulfanilamide", 0x523b0a, "C6H8N2O2S")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Ethanol100 = new Material.Builder("ethanol_100", Ethanol.getMaterialRGB(), "C2H5OH")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        BacterialGrowthMedium = new Material.Builder("bacterial_growth_medium", 0x0b2e12, "For Bacteria")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        DepletedGrowthMedium = new Material.Builder("depleted_growth_medium", 0x071209, "Depleted")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        AnimalCells = new Material.Builder("animal_cells", 0xc94996, "???")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        RapidlyReplicatingAnimalCells = new Material.Builder("rapidly_replicating_animal_cells", 0x7a335e, TextFormatting.OBFUSCATED + "????")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        MycGene = new Material.Builder("myc_gene", 0x445724, "?")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Oct4Gene = new Material.Builder("oct_4_gene", 0x374f0d, "?")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        SOX2Gene = new Material.Builder("sox_2_gene", 0x5d8714, "?")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        KFL4Gene = new Material.Builder("kfl_4_gene", 0x759143, "?")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Cas9 = new Material.Builder("cas_9", 0x5f6e46, "?")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        GenePlasmids = new Material.Builder("pluripotency_induction_gene_plasmids", 0xabe053, "?")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Chitin = new Material.Builder("chitin", 0xcbd479, "?")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Chitosan = new Material.Builder("chitosan", 0xb1bd42, "?")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        GeneTherapyFluid = new Material.Builder("pluripotency_induction_gene_therapy_fluid", 0x6b2f66, "?")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Resin = new Material.Builder("resin", 0x3d2f11, "?")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        LinoleicAcid = new Material.Builder("linoleic_acid", 0xD5D257, "C18H32O2")
                .liquid(new FluidBuilder().attribute(ACID))
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        CarbonFluoride = new Material.Builder("carbone_fluoride", 0xE6E6E6, "CF4")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        TributylPhosphate = new Material.Builder("tributyl_phosphate", 0x7C5B2C, "(C4H9)3PO4")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Butanol = new Material.Builder("butanol", (FermentedBiomass.getMaterialRGB()+20), "C4H9OH")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        BenzenediazoniumTetrafluoroborate = new Material.Builder("benzenediazonium_tetrafluoroborate", 0xD5D2D7, "C6H5BF4N2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        FluoroBenzene = new Material.Builder("fluoro_benzene", 0xD5D2D7, "C6H5F")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Fluorotoluene = new Material.Builder("fluorotoluene", 0xE0DA99, "C7H7F")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        OrthoXylene = new Material.Builder("ortho_xylene", 0xB9575E, "C6H4(CH3)2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        OrthoXyleneZeoliteMixture = new Material.Builder("ortho_xylene_zeolite", 0xB9785E, "(NaC4Si27Al9(H2O)28O72)C6H4(CH3)2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        ParaXylene = new Material.Builder("para_xylene", 0xB9575E, "C6H4(CH3)2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Dibromomethylbenzene = new Material.Builder("dibromomethylbenzene", 0x0A1D2C, "C7H6Br2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        AceticAnhydride = new Material.Builder("acetic_anhydride", 0xD5DDDF, "(CH3CO)2O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Isochloropropane = new Material.Builder("isochloropropane", 0xD5DD95, "CH3CClCH3")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Resorcinol = new Material.Builder("resorcinol", 0xD5DDBE, "C6H6O2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Dinitrodipropanyloxybenzene = new Material.Builder("dinitrodipropanyloxybenzene", 0x83945F, "C12H16O2(NO2)2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Naphthaldehyde = new Material.Builder("napthaldehyde", 0xBCA853, "C10H7CHO")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Diisopropylcarbodiimide = new Material.Builder("diisopropylcarbodiimide", 0xA0CFFE, "C7H14N2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Pyridine = new Material.Builder("pyridine", (Ammonia.getMaterialRGB()+Formaldehyde.rgb)/2, "C5H5N")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Phenylpentanoicacid = new Material.Builder("phenylpentanoicacid", (Butene.getMaterialRGB()+CarbonMonoxde.getMaterialRGB())/2, "C11H14O2")
                .liquid(new FluidBuilder().attribute(ACID))
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Dimethylsulfide = new Material.Builder("dimethylsulfide", (Methanol.getMaterialRGB()+HydrogenSulfide.getMaterialRGB())/2, "(CH3)2S")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        BenzoylChloride = new Material.Builder("benzoyl_chloride", (Toluene.getMaterialRGB()+ThionylChloride.rgb)/2, "C7H5ClO")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        PCBA = new Material.Builder("pcba", (Chlorobenzene.getMaterialRGB()+Dimethylsulfide.rgb+Phenylpentanoicacid.rgb)/3, "C72H14O2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        PCBS = new Material.Builder("pcbs", (Styrene.getMaterialRGB()+PCBA.rgb-40)/2, "C80H21O2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Ferrocene = new Material.Builder("ferrocene", (Water.getMaterialRGB()+Chlorine.getMaterialRGB()+Iron.getMaterialRGB())/3, "C10H10Fe")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Ferrocenylfulleropyrrolidine = new Material.Builder("ferrocenylfulleropyrddolidine", (Ferrocene.rgb+Ethylene.getMaterialRGB()+ CarbonMonoxde.getMaterialRGB())/3, "C74H19FeN")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Hydroquinone = new Material.Builder("hydroquinone", (Oxygen.getMaterialRGB()+Propene.getMaterialRGB())/2, "C6H4(OH)2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        SodiumAcetate = new Material.Builder("sodium_acetate", (Sodium.getMaterialRGB()+AceticAnhydride.rgb)/2, "C2H3NaO2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Methylamine = new Material.Builder("methylamine", (Methanol.getMaterialRGB()+Ammonia.getMaterialRGB())/2, "CH3NH2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Phosgene = new Material.Builder("phosgene", (Chlorine.getMaterialRGB()+CarbonMonoxde.getMaterialRGB())/2, "COCl2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        IsopropylAlcohol = new Material.Builder("isopropyl_alcohol", (Water.getMaterialRGB()+Propene.getMaterialRGB())/2, "C3H8O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        CarbonatedEthanolamine = new Material.Builder("carbonated_ethanolamine", 0x6f7d87, "H2NCH2CH2OHC")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        MesitylOxide = new Material.Builder("mesityl_oxide", Acetone.getMaterialRGB()-10, "C6H10O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        MethylIsobutylKetone = new Material.Builder("methyl_isobutyl_ketone", (MesitylOxide.rgb+WaterAgarMix.rgb)/2, "C6H12O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        PolyphenolMix = new Material.Builder("polyphenol_mix", (Phenol.getMaterialRGB()+10), "?")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        AcidifiedPolyphenolMix = new Material.Builder("acidified_polyphenol_mix", (PolyphenolMix.rgb+SulfuricAcid.getMaterialRGB())/2, "?")
                .liquid(new FluidBuilder().attribute(ACID))
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Diethylether = new Material.Builder("diethylether", AcidifiedPolyphenolMix.rgb-20, "(C2H5)2O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        TannicAcid = new Material.Builder("tannic_acid", (Diethylether.rgb+AcidifiedPolyphenolMix.rgb)/4, "C76H52O46")
                .liquid(new FluidBuilder().attribute(ACID))
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        AcryloNitrile = new Material.Builder("acrylonitrile", 0x9999ff, "CH2CHCN")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        MethylFormate = new Material.Builder("methyl_formate", 0Xff9999, "HCOOCH3")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        WetFormamide = new Material.Builder("wet_formamide", 0x33CCFF, "(H2O)CH3NO")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Formamide = new Material.Builder("formamide", 0x33CCFF, "CH3NO")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Amidoxime = new Material.Builder("amidoxime", 0x66ff33, "H3N2O(CH)")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        AmineMixture = new Material.Builder("amine_mixture", (Methanol.getMaterialRGB()-20+Ammonia.getMaterialRGB()-10)/2, "(NH3)CH4")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        DimethylthiocarbamoilChloride = new Material.Builder("dimethylthiocarbamoil_chloride", 0xd9ff26, "(CH3)2NC(S)Cl")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Trimethylamine = new Material.Builder("trimetylamine", (Dimethylamine.getMaterialRGB()+20), "(CH3)3N")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Mercaptophenol = new Material.Builder("mercaptophenol", 0xbaaf18, "C6H6OS")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Dimethylformamide = new Material.Builder("dimethylformamide", 0x42bdff, "(CH3)2NCH")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Oct1ene = new Material.Builder("1_octene", 0x7e8778, "C8H16")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        CetaneTrimethylAmmoniumBromide = new Material.Builder("cetane_trimethyl_ammonium_bromide", 0xb9c1c9, "C19H42BrN")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        ButylLithium = new Material.Builder("butyl_lithium", (Butane.getMaterialRGB()+Lithium.getMaterialRGB())/2, "C4H9Li")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Acetaldehyde = new Material.Builder("acetaldehyde", 0xFF9933, "C2H4O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Benzaldehyde = new Material.Builder("benzaldehyde", 0xb26f22, "C7H6O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Dibenzylideneacetone = new Material.Builder("dibenzylideneacetone", 0Xcc6699, "C17H14O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        TrimethyltinChloride = new Material.Builder("trimethyltin_chloride", 0x8c8075, "(CH3)3SnCl")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Cyclooctadiene = new Material.Builder("cyclooctadiene", 0x33CC33, "C8H12")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Cycloparaphenylene = new Material.Builder("cycloparaphenylene", 0x333333, "CPP")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        WetEthyleneOxide = new Material.Builder("wet_etylene_oxide", 0x90b3ff, "(H2O)C2H4O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        EthyleneGlycol = new Material.Builder("ethylene_glycol", 0x8080fa, "C2H6O2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Chloroethanol = new Material.Builder("chloroethanol", 0xcfb050, "C2H5ClO")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Choline = new Material.Builder("choline", 0x63e45f, "C5H14NO")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        ViscoelasticPolyurethane = new Material.Builder("viscoelastic_polyurethane", 0xeffcef, "C17H16N2O4?")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        ViscoelasticPolyurethaneFoam = new Material.Builder("viscoelastic_polyurethane_foam", 0xeffcef, "C17H16N2O4?")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        TolueneDiisocyanate = new Material.Builder("toluene_diisocyanate", 0xbaf6ca, "C9H6N2O2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Aminophenol = new Material.Builder("aminophenol", 0xafca3a, "C6H4(OH)(NH2)")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Hydroxyquinoline = new Material.Builder("hydroxyquinoline", 0x3a9a71, "C9H7NO")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Perbromothiophene = new Material.Builder("perbromothiophene", 0x87cc17, "C4Br4S")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Diethoxythiophene = new Material.Builder("dietoxythiophene", 0x90ff43, "C4H2(OC2H5)2S")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        EDOT = new Material.Builder("ethylenedioxythiophene", 0x7a9996, "C2H4O2C4H2S")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        CitricAcid = new Material.Builder("citric_acid", 0xffcc00, "C6H8O7")
                .liquid(new FluidBuilder().attribute(ACID))
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        OxalicAcid = new Material.Builder("oxalic_acid", 0x4aaae2, "HOOCCOOH")
                .liquid(new FluidBuilder().attribute(ACID))
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Trimethylchlorosilane = new Material.Builder("trimethylchlorosilane", Dimethyldichlorosilane.getMaterialRGB(), "(CH3)3SiCl")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Dibromoacrolein = new Material.Builder("dibromoacrolein", 0x4a4a4a, "C2H2Br2O2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Bromohydrothiine = new Material.Builder("bromodihydrothiine", 0x40ff3a, "C4H4S2Br2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Bromobutane = new Material.Builder("bromobutane", 0xff3333, "CH3(CH2)3Br")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Biperfluoromethanedisulfide = new Material.Builder("biperfluoromethanedisulfide", 0x3ada40, "C2F6S2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        BariumTriflateSolution = new Material.Builder("barium_triflate_solution", (Barium.getMaterialRGB()+Fluorine.getMaterialRGB())/2, "(H2O)3(Hg)C2BaF6O6S2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        BariumStrontiumAcetateSolution = new Material.Builder("basr_acetate_solution", 0x9a9b98, "C2H3BaO2Sr")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        TitaniumIsopropoxide = new Material.Builder("titanium_isopropoxide", 0xFF0066, "Ti(OCH(CH3)2)4")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        CarbonTetrachloride = new Material.Builder("carbon_tetrachloride", 0x2d8020, "CCl4")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Chloroethane = new Material.Builder("chloroethane", 0x33aa33, "CH3CH2Cl")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        IsopropylAcetate = new Material.Builder("isopropyl_acetate", (Strontium.getMaterialRGB()+IsopropylAlcohol.rgb+Water.getMaterialRGB())/3, "(CH3)2CHCOOCH3")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        Dichloromethane = new Material.Builder("dichloromethane", Chloromethane.getMaterialRGB()-10, "CH2Cl2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();

        ButanolGas = new Material.Builder("butanol_gas",Butanol.rgb+20, "C4H9OH")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Tributylamine = new Material.Builder("tributylamine",0x801a80, "(C4H9)3N")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Ethylenediamine = new Material.Builder("ethylenediamine", Ethanolamine.rgb, "C2H4(NH2)2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        EDTASolution = new Material.Builder("edta_solution",0x0026d9, "(C10H16N2O8)3(C2H8N2)O2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        EDTA = new Material.Builder("edta",0x0026d9, "C10H16N2O8")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Glycine = new Material.Builder("glycine", (Ethylenediamine.rgb+Formaldehyde.rgb)/2, "NH2CH2COOH")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Nitrotoluene = new Material.Builder("nitrotoluene",0xfcca00, "C7H7NO2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Naphthylamine = new Material.Builder("naphthylamine",0xe3e81c, "C10H9N")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Acetoacetanilide = new Material.Builder("acetoacetanilide",0xffffc2, "C10H11NO2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Quinizarin = new Material.Builder("quinizarin",0x3c5a2c0, "C14H8O4")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Toluenesulfonate = new Material.Builder("toluenesulfonate",0x8f8f00, "C7H7SO3Na")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Isopropylsuccinate = new Material.Builder("isopropylsuccinate",0xb26680, "C7H12O4")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        MaleicAnhydride = new Material.Builder("maleic_anhydride",0x3c20ad, "C4H2O3")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Benzonitrile = new Material.Builder("benzonitrile",0x2c2c9c, "C7H5N")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        AmmoniumNiobiumOxalateSolution = new Material.Builder("ammonium_niobium_oxalate_solution",0x6c6cac, "(NH4)C10Nb2O20")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Iodobenzene = new Material.Builder("iodobenzene",0x2c2c6c0, "C6H5I")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Amino3phenol = new Material.Builder("3_aminophenol",Aminophenol.rgb, "C6H7NO")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Dimethylnaphthalene = new Material.Builder("dimethylnaphthalene",0xe34fb0, "C12H12")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        AcetylatingReagent = new Material.Builder("acetylating_reagent",0x8d5e63, "C9H12Si(MgBr)2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Dihydroiodotetracene = new Material.Builder("dihydroiodotetracene",0x5c4d38, "H2C18H11I")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Dichlorodicyanobenzoquinone = new Material.Builder("dichlorodicyanobenzoquinone",0x3a2aba, "C8Cl2N2O2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Dichlorodicyanohydroquinone = new Material.Builder("dichlorodicyanohidroquinone",0x3a2aba, "C8Cl2N2(OH)2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        IodobenzoicAcid = new Material.Builder("iodobenzoic_acid",0x2cac6c0, "C7H5IO2")
                .liquid(new FluidBuilder().attribute(ACID))
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Methoxybenzaldehyde = new Material.Builder("methoxybenzaldehyde",0x3c3a7a, "C8H8O2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Butylaniline = new Material.Builder("butylaniline", Aniline.rgb, "C10H15N")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        MBBA = new Material.Builder("mbba",0xfa30fa, "C18H21NO")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        PotassiumEthoxide = new Material.Builder("potassium_ethoxide",Ethanol.getMaterialRGB(), "C2H5KO")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        TetraethylammoniumBromide = new Material.Builder("tetraethylammonium_bromide",0xcc33ff, "C8H20NBr")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Hexanediol = new Material.Builder("hexanediol", EthyleneGlycol.rgb, "C6H14O2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Hexamethylenediamine = new Material.Builder("hexamethylenediamine",Ethylenediamine.rgb, "C6H16N2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Tertbutanol = new Material.Builder("tertbutanol",0xcccc2c, "C4H10O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Triaminoethaneamine = new Material.Builder("triaminoethaneamine",0x6f7d87, "(NH2CH2CH2)3N")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        TertButylAzidoformate = new Material.Builder("tertbuthylcarbonylazide",0x888818, "C5H9N3O2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        AminatedFullerene = new Material.Builder("aminated_fullerene",0x2c2caa, "C60N12H12")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Azafullerene = new Material.Builder("azafullerene",0x8a7a1a, "C60N12H12")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Ethylamine = new Material.Builder("ethylamine",Ethylenediamine.rgb, "C2H5NH2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Trimethylsilane = new Material.Builder("trimethylsilane",Trimethylchlorosilane.rgb, "C3H10Si")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Phenylsodium = new Material.Builder("phenylsodium",0x2c2cc8, "C6H5Na")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Difluoroaniline = new Material.Builder("difluoroaniline",0x3fac4a, "C6H5F2N")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Succinaldehyde = new Material.Builder("succinaldehyde",0x7c6d9a, "C4H6O2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        NDifluorophenylpyrrole = new Material.Builder("n_difluorophenylpyrrole",0x3a9aa9, "C10H7F2N")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        PhotopolymerSolution = new Material.Builder("photopolymer_solution",0x8a526d, "C149H97N10O2(TiBF20)")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        GlucoseIronSolution = new Material.Builder("glucose_iron_solution", (Sugar.getMaterialRGB()+Iron.getMaterialRGB())/2, "(C6H12O6)FeCl3")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Methylethanolamine = new Material.Builder("methylethanolamine",0x6a3baa, "C3H9NO")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Methylguanidine = new Material.Builder("methylguanidine",0x5a9a3c, "C2H7N3")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Methylnitronitrosoguanidine = new Material.Builder("methylnitronitrosoguanidine",0x68b15d, "C2H5N5O3")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        IsoamylAlcohol = new Material.Builder("isoamyl_alcohol",0xcaba77, "C5H12O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Octanol = new Material.Builder("octanol",0xa2b8c2, "C8H18O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Trioctylamine = new Material.Builder("trioctylamine",0x87a2bc, "C24H51N")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        RheniumSeparationMixture = new Material.Builder("rhenium_separation_mixture",0xed2c3a, "C11H24")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        AcetylsulfanilylChloride = new Material.Builder("acetylsulfanilyl_chloride", (Aniline.rgb + AceticAnhydride.rgb + ChlorosulfonicAcid.rgb)/3, "C8H8ClNO3S")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        DibenzoylPeroxide = new Material.Builder("dibenzoyl_peroxide", (Barium.getMaterialRGB() + BenzoylChloride.rgb)/2, "C14H10O4")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Propadiene = new Material.Builder("propadiene", (Butanol.rgb-20), "C3H4")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        PhenylenedioxydiaceticAcid = new Material.Builder("phenylenedioxydiacetic_acid", 0x99546a, "C10H10O6")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Diethylthiourea = new Material.Builder("diethylthiourea", 0x2acaa4, "(C2H5NH)2CS")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Isophthaloylbisdiethylthiourea = new Material.Builder("isophthaloylbisdiethylthiourea", 0x8a7b9c, "C18H26N4O2S2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        AscorbicAcid = new Material.Builder("ascorbic_acid",0xe6cd00, "C6H8O6")
                .liquid(new FluidBuilder().attribute(ACID))
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        DehydroascorbicAcid = new Material.Builder("dehydroascorbic_acid",0xe6cd00, "C6H6O6")
                .liquid(new FluidBuilder().attribute(ACID))
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        ATLEthylene = new Material.Builder("atl_ethylene_mixture", (ATL.rgb + EthyleneGlycol.rgb) / 2)
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Cyclopentadiene = new Material.Builder("cyclopentadiene", Cyclooctadiene.rgb, "C5H6")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Oxydianiline = new Material.Builder("oxydianiline", 0xF0E130, "C12H12N2O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        PolyamicAcid = new Material.Builder("polyamic_acid", 0xFFAE42, "C22H14N2O7")
                .liquid(new FluidBuilder().attribute(ACID))
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Hexafluoropropylene = new Material.Builder("hexafluoropropylene", 0x111111, "C3F6")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Dimethylether = new Material.Builder("dimethylether", 0xe6cd11, "C2H6O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Dimethoxyethane = new Material.Builder("dimethoxyethane", 0x2acbb4, "C4H10O2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        StearicAcid = new Material.Builder("stearicacid", 0x2bbbb4, "C18H36O2")
                .liquid(new FluidBuilder().attribute(ACID))
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Trioctylphosphine = new Material.Builder("trioctylphosphine", 0xF1E130, "C24H51P")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        LithiumCyclopentadienide = new Material.Builder("lithiumcyclopentadienide", 0x95556a, "LiC5H5")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Toluidine = new Material.Builder("toluidine",(Toluene.getMaterialRGB()+ Aniline.rgb)/2,"C7H9N")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Glyoxal = new Material.Builder("glyoxal", 0xf2f068, "C2H2O2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        BenzylChloride = new Material.Builder("benzyl_chloride", 0xaef7fc, "C7H7Cl")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Benzylamine = new Material.Builder("benzylamine", 0x5c8082, "C7H9N")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Tetrahydrofuran = new Material.Builder("tetrahydrofuran", 0xb7ebcd, "(CH2)4O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Triethylamine = new Material.Builder("triethylamine", Ethylenediamine.rgb, "N(CH2CH3)3")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        BoronTrifluorideEtherate = new Material.Builder("boron_trifluoride_etherate", (BoronFluoride.rgb+Diethylether.rgb)/2, "(BF3)(C2H5)2O")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        BoraneDimethylsulfide = new Material.Builder("borane_dimethylsulfide", (Diborane.rgb+Dimethylsulfide.rgb)/2, "(BH3)(CH3)2S")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Perfluorobenzene = new Material.Builder("perfluorobenzene", 0x226E22, "C6F6")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        EthylTrifluoroacetate = new Material.Builder("ethyl_trifluoroacetate", 0x88a12d, "C4H5F3O2")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        Acetothienone = new Material.Builder("acetothieone", 0x79882a, "C6H6SO")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        TheonylTrifluoroacetate = new Material.Builder("theonyl_trifluoroacetate",0x88882b, "C8H5F3O2S")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        AcetylChloride = new Material.Builder("acetyl_chloride", AceticAcid.getMaterialRGB(), "C2H3OCl")
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();
        
        ChlorodiisopropylPhosphine = new Material.Builder("chlorodiisopropyl_phosphine", 0xa2c122)
                .fluid()
                .color()
                .components(Carbon,,Hydrogen,,Oxygen,)
                .build();*/







    }
}
