package gregsjourney.api.unification.material;

import static gregsjourney.api.utils.GJUtil.gjId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

import gregtech.api.unification.material.Material;

public class GJSpaceMaterials {

    private GJSpaceMaterials() {}

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
        MarsAtmosphere = new Material.Builder(10000, gjId("mars_atmosphere"))
                .gas()
                .colorAverage()
                .components(CarbonDioxide, 95, Nitrogen, 3, Argon, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        PhobosStone = new Material.Builder(10001, gjId("phobos_stone"))
                .dust()
                .colorAverage()
                .components(Uvarovite, 1, SiliconDioxide, 6)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        DeimosStone = new Material.Builder(10002, gjId("deimos_stone"))
                .dust()
                .colorAverage()
                .components(Garnierite, 1, Chromite, 1, SiliconDioxide, 6)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        CeresStone = new Material.Builder(10003, gjId("ceres_stone"))
                .dust()
                .colorAverage()
                .components(Talc, 1, Biotite, 1, Lepidolite, 1, GlauconiteSand, 1, SiliconDioxide, 4)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        MercuryAtmosphere = new Material.Builder(10004, gjId("mercury_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        VenusAtmosphere = new Material.Builder(10005, gjId("venus_atmosphere"))
                .gas()
                .colorAverage()
                .components(CarbonDioxide, 70, CarbonMonoxide, 15, Nitrogen, 5, SulfurDioxide, 4, Argon, 3, Helium, 3)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        EuropaStone = new Material.Builder(10006, gjId("europa_stone"))
                .dust()
                .colorAverage()
                .components(Chalcocite, 1, PhosphorusPentoxide, 1, SodaAsh, 1, SiliconDioxide, 5)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        IoStone = new Material.Builder(10007, gjId("io_stone"))
                .dust()
                .colorAverage()
                .components(Chalcopyrite, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        GanymedeStone = new Material.Builder(10008, gjId("ganymede_stone"))
                .dust()
                .colorAverage()
                .components(SiliconDioxide, 4, Water, 4)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        CallistoStone = new Material.Builder(10009, gjId("callisto_stone"))
                .dust()
                .colorAverage()
                .components(Asbestos, 1, Pyrite, 1, SulfurDioxide, 1)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        EnceladusStone = new Material.Builder(10010, gjId("enceladus_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        TitanAtmosphere = new Material.Builder(10011, gjId("titan_atmosphere"))
                .gas()
                .colorAverage()
                .components(Nitrogen, 94, Methane, 4, Hydrogen, 1, Argon, 1)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        TitanStone = new Material.Builder(10012, gjId("titan_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        MirandaAtmosphere = new Material.Builder(10013, gjId("miranda_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        MirandaStone = new Material.Builder(10014, gjId("miranda_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        OberonAtmosphere = new Material.Builder(10015, gjId("oberon_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        OberonStone = new Material.Builder(10016, gjId("oberon_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        ProteusAtmosphere = new Material.Builder(10118, gjId("proteus_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        ProteusStone = new Material.Builder(10017, gjId("proteus_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        TritonAtmosphere = new Material.Builder(10018, gjId("triton_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        TritonStone = new Material.Builder(10019, gjId("triton_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        PlutoAtmosphere = new Material.Builder(10119, gjId("pluto_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        HaumeaAtmosphere = new Material.Builder(10020, gjId("haumea_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        HaumeaStone = new Material.Builder(10021, gjId("haumea_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        MakemakeAtmosphere = new Material.Builder(10022, gjId("makemake_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        MakemakeStone = new Material.Builder(10023, gjId("makemake_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        ProximaAtmosphere = new Material.Builder(10024, gjId("proxima_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        BarnadaAtmosphere = new Material.Builder(10025, gjId("barnada_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        BarnadaC1Atmosphere = new Material.Builder(10026, gjId("barnada_c_1_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        BarnadaC1Stone = new Material.Builder(10027, gjId("baranda_c_1_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        BarnadaC2Atmosphere = new Material.Builder(10028, gjId("barnada_c_2_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        BarnadaC2Stone = new Material.Builder(10029, gjId("barnada_c_2_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        CetiAtmosphere = new Material.Builder(10030, gjId("ceti_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        ChalosAtmosphere = new Material.Builder(10031, gjId("chalos_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        DionaAtmosphere = new Material.Builder(10032, gjId("diona_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        FronosAtmosphere = new Material.Builder(10033, gjId("fronos_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        NibiruAtmosphere = new Material.Builder(10034, gjId("nibiru_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        KoentusAtmosphere = new Material.Builder(10035, gjId("koentus_atmosphere"))
                .gas()
                .colorAverage()
                .components(Hydrogen, 70, Helium, 2, Oxygen, 20, Water, 8)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        RheaStone = new Material.Builder(10036, gjId("rhea_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        IapetusStone = new Material.Builder(10037, gjId("iapetus_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        TitaniaStone = new Material.Builder(10038, gjId("titania_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();

        ErisStone = new Material.Builder(10039, gjId("eris_stone"))
                .dust()
                .colorAverage()
                .components(Phosphate, 1, PhosphorusPentoxide, 1, SiliconDioxide, 2)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .build();
    }
}
