package gregicality.legacy.integration.forestry.mutation;

import com.google.common.base.MoreObjects;
import forestry.api.climate.IClimateProvider;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.*;
import forestry.core.genetics.mutations.*;
import gregtech.api.unification.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class CustomMutation implements IMutation, IMutationBuilder {
    private final int chance;
    private final IAlleleSpecies species0;
    private final IAlleleSpecies species1;
    private final IAllele[] template;
    private final List<IMutationCondition> mutationConditions = new ArrayList();
    private final List<String> specialConditions = new ArrayList();
    private boolean isSecret = false;

    protected CustomMutation(IAlleleSpecies species0, IAlleleSpecies species1, IAllele[] template, int chance) {
        this.species0 = species0;
        this.species1 = species1;
        this.template = template;
        this.chance = chance;
    }

    public @NotNull Collection<String> getSpecialConditions() {
        return this.specialConditions;
    }

    public @NotNull CustomMutation setIsSecret() {
        this.isSecret = true;
        return this;
    }

    public @NotNull CustomMutation restrictTemperature(@NotNull EnumTemperature temperature) {
        return this.restrictTemperature(temperature, temperature);
    }

    public @NotNull CustomMutation restrictTemperature(@NotNull EnumTemperature minTemperature, @NotNull EnumTemperature maxTemperature) {
        IMutationCondition mutationCondition = new MutationConditionTemperature(minTemperature, maxTemperature);
        return this.addMutationCondition(mutationCondition);
    }

    public @NotNull CustomMutation restrictHumidity(@NotNull EnumHumidity humidity) {
        return this.restrictHumidity(humidity, humidity);
    }

    public @NotNull CustomMutation restrictHumidity(@NotNull EnumHumidity minHumidity, @NotNull EnumHumidity maxHumidity) {
        IMutationCondition mutationCondition = new MutationConditionHumidity(minHumidity, maxHumidity);
        return this.addMutationCondition(mutationCondition);
    }

    public @NotNull CustomMutation restrictBiomeType(BiomeDictionary.Type @NotNull ... types) {
        IMutationCondition mutationCondition = new MutationConditionBiome(types);
        return this.addMutationCondition(mutationCondition);
    }

    public @NotNull CustomMutation requireDay() {
        IMutationCondition mutationCondition = new MutationConditionDaytime(true);
        return this.addMutationCondition(mutationCondition);
    }

    public @NotNull CustomMutation requireNight() {
        IMutationCondition mutationCondition = new MutationConditionDaytime(false);
        return this.addMutationCondition(mutationCondition);
    }

    public @NotNull CustomMutation restrictDateRange(int startMonth, int startDay, int endMonth, int endDay) {
        IMutationCondition mutationCondition = new MutationConditionTimeLimited(startMonth, startDay, endMonth, endDay);
        return this.addMutationCondition(mutationCondition);
    }

    public @NotNull CustomMutation requireResourceCustom(Material material) {
        IMutationCondition mutationCondition = new CustomMutationConditionRequiresResource(material);
        return this.addMutationCondition(mutationCondition);
    }

    public @NotNull CustomMutation addMutationCondition(@NotNull IMutationCondition mutationCondition) {
        this.mutationConditions.add(mutationCondition);
        this.specialConditions.add(mutationCondition.getDescription());
        return this;
    }

    protected float getChance(World world, BlockPos pos, IAllele allele0, IAllele allele1, IGenome genome0, IGenome genome1, IClimateProvider climate) {
        float mutationChance = (float)this.chance;
        Iterator var9 = this.mutationConditions.iterator();

        do {
            if (!var9.hasNext()) {
                return mutationChance;
            }

            IMutationCondition mutationCondition = (IMutationCondition)var9.next();
            mutationChance *= mutationCondition.getChance(world, pos, allele0, allele1, genome0, genome1, climate);
        } while(mutationChance != 0.0F);

        return 0.0F;
    }

    public @NotNull IAlleleSpecies getAllele0() {
        return this.species0;
    }

    public @NotNull IAlleleSpecies getAllele1() {
        return this.species1;
    }

    public float getBaseChance() {
        return (float)this.chance;
    }

    public IAllele @NotNull [] getTemplate() {
        return this.template;
    }

    public boolean isPartner(IAllele allele) {
        return this.species0.getUID().equals(allele.getUID()) || this.species1.getUID().equals(allele.getUID());
    }

    public @NotNull IAllele getPartner(IAllele allele) {
        if (this.species0.getUID().equals(allele.getUID())) {
            return this.species1;
        } else if (this.species1.getUID().equals(allele.getUID())) {
            return this.species0;
        } else {
            throw new IllegalArgumentException("Tried to get partner for allele that is not part of this mutation.");
        }
    }

    public boolean isSecret() {
        return this.isSecret;
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this).add("first", this.species0).add("second", this.species1).add("result", this.template[0]);
        if (!this.specialConditions.isEmpty()) {
            stringHelper.add("conditions", this.getSpecialConditions());
        }

        return stringHelper.toString();
    }
}
