package gregicality.legacy.integration.forestry;

import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IClassification;
import forestry.apiculture.genetics.alleles.AlleleEffects;
import forestry.core.genetics.alleles.AlleleHelper;
import forestry.core.genetics.alleles.EnumAllele;

import java.util.Arrays;
import java.util.function.Consumer;

import static forestry.api.apiculture.EnumBeeChromosome.*;

public enum GTBranchDefinition {

    GT_MAGIC("Magia", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.BOTH_2);
        AlleleHelper.getInstance().set(alleles, TOLERATES_RAIN, true);
        AlleleHelper.getInstance().set(alleles, FLOWERING, EnumAllele.Flowering.FASTEST);
        AlleleHelper.getInstance().set(alleles, LIFESPAN, EnumAllele.Lifespan.LONGER);
        AlleleHelper.getInstance().set(alleles, SPEED, EnumAllele.Speed.FASTEST);
        AlleleHelper.getInstance().set(alleles, TERRITORY, EnumAllele.Territory.AVERAGE);
    }),

    GT_PLANETS("Planetaris", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.NONE);
        AlleleHelper.getInstance().set(alleles, TOLERATES_RAIN, true);
        AlleleHelper.getInstance().set(alleles, FLOWERING, EnumAllele.Flowering.SLOW);
        AlleleHelper.getInstance().set(alleles, LIFESPAN, EnumAllele.Lifespan.NORMAL);
        AlleleHelper.getInstance().set(alleles, SPEED, EnumAllele.Speed.FASTER);
        AlleleHelper.getInstance().set(alleles, TERRITORY, EnumAllele.Territory.AVERAGE);
    });

    private static IAllele[] defaultTemplate;
    private final IClassification branch;
    private final Consumer<IAllele[]> branchProperties;

    GTBranchDefinition(String scientific, Consumer<IAllele[]> branchProperties) {
        this.branch = BeeManager.beeFactory.createBranch(this.name().toLowerCase(), scientific);
        AlleleManager.alleleRegistry.getClassification("family.apidae").addMemberGroup(this.branch);
        this.branchProperties = branchProperties;
    }

    private static IAllele[] getDefaultTemplate() {
        if (defaultTemplate == null) {
            defaultTemplate = new IAllele[EnumBeeChromosome.values().length];

            AlleleHelper.getInstance().set(defaultTemplate, SPEED, EnumAllele.Speed.SLOWEST);
            AlleleHelper.getInstance().set(defaultTemplate, LIFESPAN, EnumAllele.Lifespan.SHORTER);
            AlleleHelper.getInstance().set(defaultTemplate, FERTILITY, EnumAllele.Fertility.NORMAL);
            AlleleHelper.getInstance().set(defaultTemplate, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.getInstance().set(defaultTemplate, NEVER_SLEEPS, false);
            AlleleHelper.getInstance().set(defaultTemplate, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.getInstance().set(defaultTemplate, TOLERATES_RAIN, false);
            AlleleHelper.getInstance().set(defaultTemplate, CAVE_DWELLING, false);
            AlleleHelper.getInstance().set(defaultTemplate, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
            AlleleHelper.getInstance().set(defaultTemplate, FLOWERING, EnumAllele.Flowering.SLOWEST);
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

