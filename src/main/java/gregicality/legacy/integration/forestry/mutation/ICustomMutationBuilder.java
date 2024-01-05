package gregicality.legacy.integration.forestry.mutation;

import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.IMutation;
import forestry.api.genetics.IMutationBuilder;
import forestry.api.genetics.IMutationCondition;
import gregtech.api.unification.material.Material;
import net.minecraftforge.common.BiomeDictionary;

public interface ICustomMutationBuilder {
    IMutation build();

    /**
     * Prevent this mutation from being shown in the analyzers
     */
    IMutationBuilder setIsSecret();

    /**
     * Require a specific temperature for this mutation to occur
     */
    IMutationBuilder restrictTemperature(EnumTemperature temperature);

    IMutationBuilder restrictTemperature(EnumTemperature minTemperature, EnumTemperature maxTemperature);

    /**
     * Require a specific humidity for this mutation to occur
     */
    IMutationBuilder restrictHumidity(EnumHumidity humidity);

    IMutationBuilder restrictHumidity(EnumHumidity minHumidity, EnumHumidity maxHumidity);

    /**
     * Restrict this mutation to certain types of biomes.
     *
     * @param types The types of biomes this mutation can occur.
     */
    IMutationBuilder restrictBiomeType(BiomeDictionary.Type... types);

    /**
     * Restrict the days of the year that this mutation can occur
     */
    IMutationBuilder restrictDateRange(int startMonth, int startDay, int endMonth, int endDay);

    /**
     * Restrict the time of day that this mutation can occur
     */
    IMutationBuilder requireDay();

    IMutationBuilder requireNight();

    /**
     * Require a specific resource to be under the location of the mutation
     */
    IMutationBuilder requireResourceCustom(Material material);

    /**
     * Require some other custom mutation condition
     */
    IMutationBuilder addMutationCondition(IMutationCondition mutationCondition);
}
