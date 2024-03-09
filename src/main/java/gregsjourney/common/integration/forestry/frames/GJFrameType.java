//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package gregsjourney.common.integration.forestry.frames;

import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeModifier;
import org.jetbrains.annotations.Nullable;

public enum GJFrameType implements IBeeModifier {
    TEST("Test", 420, 1.0F, 0.0F, 69.0F, 42.0F, 1.0F, 0.0F);

    private final String frameName;
    public final int maxDamage;
    private final float territoryMod;
    private final float mutationMod;
    private final float lifespanMod;
    private final float productionMod;
    private final float floweringMod;
    private final float geneticDecayMod;

    private GJFrameType(String name, int maxDamage, float territory, float mutation, float lifespan, float production, float flowering, float geneticDecay) {
        this.frameName = name;
        this.maxDamage = maxDamage;
        this.territoryMod = territory;
        this.mutationMod = mutation;
        this.lifespanMod = lifespan;
        this.productionMod = production;
        this.floweringMod = flowering;
        this.geneticDecayMod = geneticDecay;
    }

    public String getName() {
        return this.frameName;
    }

    public float getTerritoryModifier(@Nullable IBeeGenome iBeeGenome, float v) {
        return this.territoryMod;
    }

    public float getMutationModifier(@Nullable IBeeGenome iBeeGenome, @Nullable IBeeGenome iBeeGenome1, float v) {
        return this.mutationMod;
    }

    public float getLifespanModifier(@Nullable IBeeGenome iBeeGenome, @Nullable IBeeGenome iBeeGenome1, float v) {
        return this.lifespanMod;
    }

    public float getProductionModifier(@Nullable IBeeGenome iBeeGenome, float v) {
        return this.productionMod;
    }

    public float getFloweringModifier(@Nullable IBeeGenome iBeeGenome, float v) {
        return this.floweringMod;
    }

    public float getGeneticDecay(@Nullable IBeeGenome iBeeGenome, float v) {
        return this.geneticDecayMod;
    }

    public boolean isSealed() {
        return false;
    }

    public boolean isSelfLighted() {
        return false;
    }

    public boolean isSunlightSimulated() {
        return false;
    }

    public boolean isHellish() {
        return false;
    }
}
