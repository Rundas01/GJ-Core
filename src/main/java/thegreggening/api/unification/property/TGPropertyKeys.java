package thegreggening.api.unification.property;

import gregtech.api.unification.material.properties.PropertyKey;

public class TGPropertyKeys {

    public static final PropertyKey<DistillationProperty> DISTILLATION_PROPERTY = new PropertyKey<>(
            "distillation_property", DistillationProperty.class);
    public static final PropertyKey<CrystallizationProperty> CRYSTALLIZATION_PROPERTY = new PropertyKey<>(
            "crystallization_property", CrystallizationProperty.class);
    public static final PropertyKey<LeachProperty> LEACH_PROPERTY = new PropertyKey<>("leach_property",
            LeachProperty.class);
    public static final PropertyKey<RoastedOreProperty> ROASTED_ORE_PROPERTY = new PropertyKey<>("roasted_ore_property",
            RoastedOreProperty.class);
    public static final PropertyKey<SolutionMixingProperty> SOLUTION_MIXING_PROPERTY = new PropertyKey<>(
            "solution_mixing_property", SolutionMixingProperty.class);
    public static final PropertyKey<OreTailingProperty> ORE_TAILING_PROPERTY = new PropertyKey<>("ore_tailing_property",
            OreTailingProperty.class);
    public static final PropertyKey<NuclearProperty> NUCLEAR_PROPERTY = new PropertyKey<>("nuclear_property",
            NuclearProperty.class);
    public static final PropertyKey<CoolantProperty> COOLANT_PROPERTY = new PropertyKey<>("coolant_property",
            CoolantProperty.class);
    public static final PropertyKey<DensityProperty> AM_DENSITY_PROPERTY = new PropertyKey<>("density_property",
            DensityProperty.class);
    public static final PropertyKey<SHCProperty> AM_SHC_PROPERTY = new PropertyKey<>("shc_property",
            SHCProperty.class);
}
