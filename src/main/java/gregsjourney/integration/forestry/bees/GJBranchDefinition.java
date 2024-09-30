package gregsjourney.integration.forestry.bees;

import static forestry.api.apiculture.EnumBeeChromosome.*;

import java.util.Arrays;
import java.util.function.Consumer;

import gregtech.integration.forestry.bees.GTAlleleBeeSpecies;

import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IClassification;
import forestry.apiculture.genetics.alleles.AlleleEffects;
import forestry.core.genetics.alleles.AlleleHelper;
import forestry.core.genetics.alleles.EnumAllele;

public enum GJBranchDefinition {

    // Noble Gas + Hydrogen
    GJ_NOBLEGAS("Gasa Nobilia", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.BOTH_2);
        AlleleHelper.getInstance().set(alleles, TOLERATES_RAIN, true);
        AlleleHelper.getInstance().set(alleles, FLOWERING, EnumAllele.Flowering.FASTEST);
        AlleleHelper.getInstance().set(alleles, LIFESPAN, EnumAllele.Lifespan.NORMAL);
        AlleleHelper.getInstance().set(alleles, SPEED, EnumAllele.Speed.FASTEST);
        AlleleHelper.getInstance().set(alleles, TERRITORY, EnumAllele.Territory.AVERAGE);
    }),
    // Alkaline Metals + Earth Alkaline Metals
    GJ_ALKALINEMETAL("Mineralis Alkalis", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.DOWN_1);
        AlleleHelper.getInstance().set(alleles, NEVER_SLEEPS, false);
        AlleleHelper.getInstance().set(alleles, FLOWER_PROVIDER, EnumAllele.Flowers.CACTI);
        AlleleHelper.getInstance().set(alleles, FLOWERING, EnumAllele.Flowering.FAST);
    }),
    // Organic Non-Metals
    GJ_ORGANIC("Fuelis", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.NONE);
        AlleleHelper.getInstance().set(alleles, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.BOTH_2);
        AlleleHelper.getInstance().set(alleles, NEVER_SLEEPS, false);
        AlleleHelper.getInstance().set(alleles, FLOWER_PROVIDER, EnumAllele.Flowers.WHEAT);
        AlleleHelper.getInstance().set(alleles, FLOWERING, EnumAllele.Flowering.SLOW);
        AlleleHelper.getInstance().set(alleles, LIFESPAN, EnumAllele.Lifespan.SHORTER);
        AlleleHelper.getInstance().set(alleles, SPEED, EnumAllele.Speed.SLOWEST);
    }),
    // Inorganic Non-Metals
    GJ_NONMETAL("Fuelis", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.NONE);
        AlleleHelper.getInstance().set(alleles, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.BOTH_2);
        AlleleHelper.getInstance().set(alleles, NEVER_SLEEPS, false);
        AlleleHelper.getInstance().set(alleles, FLOWER_PROVIDER, EnumAllele.Flowers.WHEAT);
        AlleleHelper.getInstance().set(alleles, FLOWERING, EnumAllele.Flowering.SLOW);
        AlleleHelper.getInstance().set(alleles, LIFESPAN, EnumAllele.Lifespan.SHORTER);
        AlleleHelper.getInstance().set(alleles, SPEED, EnumAllele.Speed.SLOWEST);
    }),
    // Half Metals
    GJ_HALFMETAL("Metaliferis Semis", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.DOWN_2);
        AlleleHelper.getInstance().set(alleles, CAVE_DWELLING, true);
        AlleleHelper.getInstance().set(alleles, NEVER_SLEEPS, false);
        AlleleHelper.getInstance().set(alleles, FLOWER_PROVIDER, EnumAllele.Flowers.JUNGLE);
        AlleleHelper.getInstance().set(alleles, FLOWERING, EnumAllele.Flowering.SLOWER);
    }),
    // Metals
    GJ_METAL("Metaliferis", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.DOWN_2);
        AlleleHelper.getInstance().set(alleles, CAVE_DWELLING, true);
        AlleleHelper.getInstance().set(alleles, NEVER_SLEEPS, false);
        AlleleHelper.getInstance().set(alleles, FLOWER_PROVIDER, EnumAllele.Flowers.JUNGLE);
        AlleleHelper.getInstance().set(alleles, FLOWERING, EnumAllele.Flowering.SLOWER);
    }),
    // Rare Metals
    GJ_RAREMETAL("Mineralis", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.DOWN_1);
        AlleleHelper.getInstance().set(alleles, NEVER_SLEEPS, false);
        AlleleHelper.getInstance().set(alleles, FLOWER_PROVIDER, EnumAllele.Flowers.CACTI);
        AlleleHelper.getInstance().set(alleles, FLOWERING, EnumAllele.Flowering.FAST);
    }),
    // Gem
    GJ_GEM("Ornamentis", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.NONE);
        AlleleHelper.getInstance().set(alleles, NEVER_SLEEPS, false);
        AlleleHelper.getInstance().set(alleles, FLOWER_PROVIDER, EnumAllele.Flowers.NETHER);
        AlleleHelper.getInstance().set(alleles, FLOWERING, EnumAllele.Flowering.AVERAGE);
    }),
    // Radioactive
    GJ_RADIOACTIVE("Criticalis", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.NONE);
        AlleleHelper.getInstance().set(alleles, NEVER_SLEEPS, false);
        AlleleHelper.getInstance().set(alleles, FLOWER_PROVIDER, EnumAllele.Flowers.END);
        AlleleHelper.getInstance().set(alleles, FLOWERING, EnumAllele.Flowering.AVERAGE);
        AlleleHelper.getInstance().set(alleles, SPEED, GTAlleleBeeSpecies.speedBlinding);
        AlleleHelper.getInstance().set(alleles, EFFECT, AlleleEffects.effectRadioactive);
    }),
    // Halogenides
    GJ_HALOGENS("Halogenis", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.DOWN_2);
        AlleleHelper.getInstance().set(alleles, LIFESPAN, EnumAllele.Lifespan.LONG);
        AlleleHelper.getInstance().set(alleles, SPEED, EnumAllele.Speed.FAST);
        AlleleHelper.getInstance().set(alleles, TERRITORY, EnumAllele.Territory.AVERAGE);
    }),
    // Industrial
    GJ_INDUSTRIAL("Industrialis", alleles -> {}),
    // Planets
    GJ_PLANETS("Planetaris", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.BOTH_2);
        AlleleHelper.getInstance().set(alleles, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.BOTH_2);
        AlleleHelper.getInstance().set(alleles, TOLERATES_RAIN, true);
        AlleleHelper.getInstance().set(alleles, FLOWERING, EnumAllele.Flowering.SLOWEST);
        AlleleHelper.getInstance().set(alleles, LIFESPAN, EnumAllele.Lifespan.NORMAL);
        AlleleHelper.getInstance().set(alleles, SPEED, EnumAllele.Speed.FASTER);
        AlleleHelper.getInstance().set(alleles, TERRITORY, EnumAllele.Territory.LARGE);
    }),
    // Magic
    GJ_MAGIC("Magia", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.BOTH_1);
        AlleleHelper.getInstance().set(alleles, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.BOTH_1);
        AlleleHelper.getInstance().set(alleles, TOLERATES_RAIN, false);
        AlleleHelper.getInstance().set(alleles, FLOWERING, EnumAllele.Flowering.FASTEST);
        AlleleHelper.getInstance().set(alleles, LIFESPAN, EnumAllele.Lifespan.LONGEST);
        AlleleHelper.getInstance().set(alleles, SPEED, EnumAllele.Speed.FASTEST);
        AlleleHelper.getInstance().set(alleles, TERRITORY, EnumAllele.Territory.AVERAGE);
    });

    private static IAllele[] defaultTemplate;
    private final IClassification branch;
    private final Consumer<IAllele[]> branchProperties;

    GJBranchDefinition(String scientific, Consumer<IAllele[]> branchProperties) {
        this.branch = BeeManager.beeFactory.createBranch(this.name().toLowerCase(), scientific);
        AlleleManager.alleleRegistry.getClassification("family.apidae").addMemberGroup(this.branch);
        this.branchProperties = branchProperties;
    }

    private static IAllele[] getDefaultTemplate() {
        if (defaultTemplate == null) {
            defaultTemplate = new IAllele[EnumBeeChromosome.values().length];

            AlleleHelper.getInstance().set(defaultTemplate, SPEED, EnumAllele.Speed.NORMAL);
            AlleleHelper.getInstance().set(defaultTemplate, LIFESPAN, EnumAllele.Lifespan.NORMAL);
            AlleleHelper.getInstance().set(defaultTemplate, FERTILITY, EnumAllele.Fertility.NORMAL);
            AlleleHelper.getInstance().set(defaultTemplate, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.getInstance().set(defaultTemplate, NEVER_SLEEPS, false);
            AlleleHelper.getInstance().set(defaultTemplate, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.getInstance().set(defaultTemplate, TOLERATES_RAIN, false);
            AlleleHelper.getInstance().set(defaultTemplate, CAVE_DWELLING, false);
            AlleleHelper.getInstance().set(defaultTemplate, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
            AlleleHelper.getInstance().set(defaultTemplate, FLOWERING, EnumAllele.Flowering.AVERAGE);
            AlleleHelper.getInstance().set(defaultTemplate, TERRITORY, EnumAllele.Territory.AVERAGE);
            AlleleHelper.getInstance().set(defaultTemplate, EFFECT, AlleleEffects.effectNone);
        }
        return Arrays.copyOf(defaultTemplate, defaultTemplate.length);
    }

    private void setBranchProperties(IAllele[] template) {
        this.branchProperties.accept(template);
    }

    public IAllele[] getTemplate() {
        IAllele[] template = getDefaultTemplate();
        setBranchProperties(template);
        return template;
    }

    public IClassification getBranch() {
        return branch;
    }
}
