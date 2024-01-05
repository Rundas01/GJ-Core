package gregicality.legacy.api.unification.material;

import gregtech.api.unification.material.Material;

import static gregicality.legacy.api.unification.material.GCYLRMaterials.*;
import static gregicality.legacy.api.utils.GCYLRUtil.gcylrId;
import static gregtech.api.unification.material.info.MaterialIconSet.*;

public final class GCYLRBacteriaMaterials {

    private GCYLRBacteriaMaterials() {}

    public static Material StreptococcusPyogenes;
    public static Material CupriavidiusNecator;
    public static Material SelectivelyMutatedCupriavidiusNecator;
    public static Material EschericiaColi;
    public static Material BrevibacteriumFlavium;
    public static Material BifidobacteriumBreve ;
    public static Material Shewanella;

    public static void init() {

        StreptococcusPyogenes = new Material.Builder(3203,gcylrId("streptococcus_pyogenes"))
                .dust()
                .iconSet(ROUGH)
                .color(0x1c3b15)
                .build();
        StreptococcusPyogenes.setFormula("Bacteria");

        CupriavidiusNecator = new Material.Builder(3204,gcylrId("cupriavidus_necator"))
                .dust()
                .iconSet(ROUGH)
                .color(0x22704f)
                .build();
        CupriavidiusNecator.setFormula("Bacteria");

        SelectivelyMutatedCupriavidiusNecator = new Material.Builder(3205,gcylrId("selectively_mutated_cupriavidius_necator"))
                .dust()
                .iconSet(SHINY)
                .color(CupriavidiusNecator.getMaterialRGB() * 5 / 4)
                .build();
        SelectivelyMutatedCupriavidiusNecator.setFormula("Bacteria");

        EschericiaColi = new Material.Builder(3206,gcylrId("eschericia_coli"))
                .dust()
                .iconSet(ROUGH)
                .color(0x2d4228)
                .build();
        EschericiaColi.setFormula("Bacteria");

        BrevibacteriumFlavium = new Material.Builder(3207,gcylrId("brevibacterium_flavium"))
                .dust()
                .iconSet(ROUGH)
                .color(0x2c4d24)
                .build();
        BrevibacteriumFlavium.setFormula("Bacteria");

        BifidobacteriumBreve = new Material.Builder(3208,gcylrId("bifidobacterium_breve"))
                .dust()
                .iconSet(ROUGH)
                .color(0x377528)
                .build();
        BifidobacteriumBreve.setFormula("Bacteria");

        Shewanella = new Material.Builder(3209,gcylrId("shewanella"))
                .dust()
                .iconSet(METALLIC)
                .color(0x8752ab)
                .build();
        Shewanella.setFormula("Bacteria");

    }
}
