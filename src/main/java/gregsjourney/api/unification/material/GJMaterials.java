package gregsjourney.api.unification.material;

import gregsjourney.api.unification.material.materiallines.*;

public class GJMaterials {

    private GJMaterials() {}

    public static void init() {
        // Materials
        materialInit();
        // Flags
        GJMaterialFlagAddition.init();
    }

    private static void materialInit() {
        //7000-X
        GJOreMaterials.init();
        //4001-4100
        GJMiscMaterials.init();
        //4101-4150
        AluminiumLineMaterials.init();
        //4151-4200
        AntimonyLineMaterials.init();
        //4201-4250
        ArsenicLineMaterials.init();
        //4251-4300
        BariumLineMaterials.init();
        //4301-4350
        BerylliumLineMaterials.init();
        //4351-4400
        BismuthLineMaterials.init();
        //4401-4450
        BoronLineMaterials.init();
        //4451-4500
        BromineLineMaterials.init();
        //4501-4550
        CaesiumLineMaterials.init();
        //4551-4600
        CalciumLineMaterials.init();
        //4601-4650
        CeriumLineMaterials.init();
        //4651-4700
        ChromeLineMaterials.init();
        //4701-4750
        CobaltLineMaterials.init();
        //4751-4800
        CopperLineMaterials.init();
        //4801-4850
        GalliumLineMaterials.init();
        //4851-4900
        IronLineMaterials.init();
        //4901-4950
        LanthanumLineMaterials.init();
        //4951-5000
        LeadLineMaterials.init();
        //5001-5050
        LithiumLineMaterials.init();
        //5051-5100
        MagnesiumLineMaterials.init();
        //5101-5150
        ManganeseLineMaterials.init();
        MercuryLineMaterials.init();
        //5151-5200
        NeodymiumLineMaterials.init();
        //5201-5250
        PalladiumLineMaterials.init();
        //5251-5300
        PlatinumLineMaterials.init();
        //5301-5350
        PotassiumLineMaterials.init();
        //5351-5400
        SamariumLineMaterials.init();
        //5401-5450
        ScandiumLineMaterials.init();
        //5451-5500
        SiliconLineMaterials.init();
        //5501-5550
        SilverLineMaterials.init();
        //5551-5600
        SodiumLineMaterials.init();
        //5601-5650
        ThalliumLineMaterials.init();
        //5651-5700
        TinLineMaterials.init();
        //5701-5750
        TitaniumLineMaterials.init();
        //5751-5800
        VanadiumLineMaterials.init();
    }
}
