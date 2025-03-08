package thegreggening.api.unification.material;

import static thegreggening.utils.TGUtil.tgId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

import gregtech.api.unification.material.Material;

public class TGSpaceMaterials {

    private TGSpaceMaterials() {}

    public static Material MarsAtmosphere;
    public static Material PhobosStone;
    public static Material DeimosStone;
    public static Material CeresStone;
    public static Material MercuryAtmosphere;
    public static Material VenusAtmosphere;
    public static Material EuropaStone;
    public static Material IoStone;
    public static Material GanymedeStone;
    public static Material CallistoStone;
    public static Material EnceladusStone;
    public static Material TitanAtmosphere;
    public static Material TitanStone;
    public static Material MirandaAtmosphere;
    public static Material MirandaStone;
    public static Material OberonAtmosphere;
    public static Material OberonStone;
    public static Material ProteusAtmosphere;
    public static Material ProteusStone;
    public static Material TritonAtmosphere;
    public static Material TritonStone;
    public static Material PlutoAtmosphere;
    public static Material HaumeaAtmosphere;
    public static Material HaumeaStone;
    public static Material MakemakeAtmosphere;
    public static Material MakemakeStone;
    public static Material ProximaAtmosphere;
    public static Material BarnadaAtmosphere;
    public static Material BarnadaC1Atmosphere;
    public static Material BarnadaC1Stone;
    public static Material BarnadaC2Atmosphere;
    public static Material BarnadaC2Stone;
    public static Material CetiAtmosphere;
    public static Material ChalosAtmosphere;
    public static Material DionaAtmosphere;
    public static Material FronosAtmosphere;
    public static Material NibiruAtmosphere;
    public static Material KoentusAtmosphere;
    public static Material RheaStone;
    public static Material ErisStone;
    public static Material IapetusStone;
    public static Material TitaniaStone;

    public static void init() {
        MarsAtmosphere = new Material.Builder(10000, tgId("mars_atmosphere"))
                .gas()
                .colorAverage()
                .components(CarbonDioxide, 95, Nitrogen, 3, Argon, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        PhobosStone = new Material.Builder(10001, tgId("phobos_stone"))
                .dust()
                .colorAverage()
                .components(Uvarovite, 1, SiliconDioxide, 6)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        DeimosStone = new Material.Builder(10002, tgId("deimos_stone"))
                .dust()
                .colorAverage()
                .components(Garnierite, 1, Chromite, 1, SiliconDioxide, 6)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        CeresStone = new Material.Builder(10003, tgId("ceres_stone"))
                .dust()
                .colorAverage()
                .components(Talc, 1, Biotite, 1, Lepidolite, 1, GlauconiteSand, 1, SiliconDioxide, 4)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        MercuryAtmosphere = new Material.Builder(10004, tgId("mercury_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        VenusAtmosphere = new Material.Builder(10005, tgId("venus_atmosphere"))
                .gas()
                .colorAverage()
                .components(CarbonDioxide, 70, CarbonMonoxide, 15, Nitrogen, 5, SulfurDioxide, 4, Argon, 3, Helium, 3)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        EuropaStone = new Material.Builder(10006, tgId("europa_stone"))
                .dust()
                .colorAverage()
                .components(Chalcocite, 1, PhosphorusPentoxide, 1, SodaAsh, 1, SiliconDioxide, 5)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        IoStone = new Material.Builder(10007, tgId("io_stone"))
                .dust()
                .colorAverage()
                .components(Chalcopyrite, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        GanymedeStone = new Material.Builder(10008, tgId("ganymede_stone"))
                .dust()
                .colorAverage()
                .components(SiliconDioxide, 4, Water, 4)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        CallistoStone = new Material.Builder(10009, tgId("callisto_stone"))
                .dust()
                .colorAverage()
                .components(Asbestos, 1, Pyrite, 1, SulfurDioxide, 1)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        EnceladusStone = new Material.Builder(10010, tgId("enceladus_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        TitanAtmosphere = new Material.Builder(10011, tgId("titan_atmosphere"))
                .gas()
                .colorAverage()
                .components(Nitrogen, 94, Methane, 4, Hydrogen, 1, Argon, 1)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        TitanStone = new Material.Builder(10012, tgId("titan_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        MirandaAtmosphere = new Material.Builder(10013, tgId("miranda_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        MirandaStone = new Material.Builder(10014, tgId("miranda_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        OberonAtmosphere = new Material.Builder(10015, tgId("oberon_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        OberonStone = new Material.Builder(10016, tgId("oberon_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        ProteusAtmosphere = new Material.Builder(10118, tgId("proteus_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        ProteusStone = new Material.Builder(10017, tgId("proteus_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        TritonAtmosphere = new Material.Builder(10018, tgId("triton_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        TritonStone = new Material.Builder(10019, tgId("triton_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        PlutoAtmosphere = new Material.Builder(10119, tgId("pluto_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        HaumeaAtmosphere = new Material.Builder(10020, tgId("haumea_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        HaumeaStone = new Material.Builder(10021, tgId("haumea_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        MakemakeAtmosphere = new Material.Builder(10022, tgId("makemake_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        MakemakeStone = new Material.Builder(10023, tgId("makemake_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        ProximaAtmosphere = new Material.Builder(10024, tgId("proxima_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        BarnadaAtmosphere = new Material.Builder(10025, tgId("barnada_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        BarnadaC1Atmosphere = new Material.Builder(10026, tgId("barnada_c_1_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        BarnadaC1Stone = new Material.Builder(10027, tgId("baranda_c_1_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        BarnadaC2Atmosphere = new Material.Builder(10028, tgId("barnada_c_2_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        BarnadaC2Stone = new Material.Builder(10029, tgId("barnada_c_2_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        CetiAtmosphere = new Material.Builder(10030, tgId("ceti_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        ChalosAtmosphere = new Material.Builder(10031, tgId("chalos_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        DionaAtmosphere = new Material.Builder(10032, tgId("diona_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        FronosAtmosphere = new Material.Builder(10033, tgId("fronos_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        NibiruAtmosphere = new Material.Builder(10034, tgId("nibiru_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        KoentusAtmosphere = new Material.Builder(10035, tgId("koentus_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        RheaStone = new Material.Builder(10036, tgId("rhea_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        IapetusStone = new Material.Builder(10037, tgId("iapetus_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        TitaniaStone = new Material.Builder(10038, tgId("titania_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        ErisStone = new Material.Builder(10039, tgId("eris_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();
    }
}
