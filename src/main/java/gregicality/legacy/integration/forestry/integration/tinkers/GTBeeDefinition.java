package gregicality.legacy.integration.forestry.integration.tinkers;

import forestry.api.apiculture.*;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.IAllele;
import forestry.apiculture.ModuleApiculture;
import forestry.apiculture.genetics.Bee;
import forestry.apiculture.genetics.IBeeDefinition;
import forestry.apiculture.items.EnumHoneyComb;
import forestry.core.genetics.alleles.AlleleHelper;
import forestry.core.genetics.alleles.EnumAllele;
import gregicality.legacy.integration.forestry.ForestryModule;
import gregicality.legacy.integration.forestry.GTCombType;
import gregicality.multiblocks.api.unification.GCYMMaterials;
import gregtech.api.GTValues;
import gregtech.api.unification.OreDictUnifier;
import gregtech.integration.IntegrationUtil;
import gregtech.integration.forestry.bees.GTAlleleBeeSpecies;
import gregtech.integration.forestry.bees.GTBranchDefinition;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;
import org.apache.commons.lang3.text.WordUtils;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static forestry.api.apiculture.EnumBeeChromosome.*;
import static forestry.apiculture.genetics.BeeDefinition.*;
import static gregicality.legacy.integration.forestry.ForestryModule.*;
import static gregicality.legacy.integration.forestry.GTBeeDefinition.COBALT;
import static gregtech.api.unification.ore.OrePrefix.block;
import static gregtech.api.unification.ore.OrePrefix.dust;

public enum GTBeeDefinition implements IBeeDefinition {
    ARDITE(GTBranchDefinition.GT_METAL,"Auranticao",false,0x720000, 0xFF9E00,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.18f);
                beeSpecies.addSpecialty(getGCYLRTinkersComb(gregicality.legacy.integration.forestry.integration.tinkers.GTCombType.ARDITE), 0.1f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> {
                AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(INDUSTRIOUS, DEMONIC, 11);
                mutation.requireResource("blockArdite");
            }
    ),

    MANYULLYN(GTBranchDefinition.GT_METAL,"Manyullyn",true,0x481D6D, 0xBD92F1,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.15f);
                beeSpecies.addProduct(getGCYLRComb(GTCombType.COBALT), 0.05f);
                beeSpecies.addProduct(getGCYLRTinkersComb(gregicality.legacy.integration.forestry.integration.tinkers.GTCombType.ARDITE), 0.05f);
                beeSpecies.addSpecialty(getGCYLRTinkersComb(gregicality.legacy.integration.forestry.integration.tinkers.GTCombType.MANYULLUN),0.1f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HOT);
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(COBALT, ARDITE, 9);
                mutation.requireResource("blockManyullun");
            }
    );

    private final GTBranchDefinition branch;
    private final GTAlleleBeeSpecies species;
    private final Consumer<GTAlleleBeeSpecies> speciesProperties;
    private final Consumer<IAllele[]> alleles;
    private final Consumer<gregicality.legacy.integration.forestry.integration.tinkers.GTBeeDefinition> mutations;
    private IAllele[] template;
    private IBeeGenome genome;
    private final Supplier<Boolean> generationCondition;

    GTBeeDefinition(GTBranchDefinition branch,
                    String binomial,
                    boolean dominant,
                    int primary,
                    int secondary,
                    Consumer<GTAlleleBeeSpecies> speciesProperties,
                    Consumer<IAllele[]> alleles,
                    Consumer<gregicality.legacy.integration.forestry.integration.tinkers.GTBeeDefinition> mutations) {
        this(branch, binomial, dominant, primary, secondary, speciesProperties, alleles, mutations, () -> true);
    }

    GTBeeDefinition(GTBranchDefinition branch,
                    String binomial,
                    boolean dominant,
                    int primary,
                    int secondary,
                    Consumer<GTAlleleBeeSpecies> speciesProperties,
                    Consumer<IAllele[]> alleles,
                    Consumer<gregicality.legacy.integration.forestry.integration.tinkers.GTBeeDefinition> mutations,
                    Supplier<Boolean> generationCondition) {
        this.alleles = alleles;
        this.mutations = mutations;
        this.speciesProperties = speciesProperties;
        String lowercaseName = this.toString().toLowerCase(Locale.ENGLISH);
        String species = WordUtils.capitalize(lowercaseName);

        String uid = "gregtech.bee.species" + species;
        String description = "for.bees.description." + lowercaseName;
        String name = "for.bees.species." + lowercaseName;

        this.branch = branch;
        this.species = new GTAlleleBeeSpecies(GTValues.MODID, uid, name, "GregTech", description, dominant, branch.getBranch(), binomial, primary, secondary);
        this.generationCondition = generationCondition;
    }

    public static void initBees() {
        for (gregicality.legacy.integration.forestry.integration.tinkers.GTBeeDefinition bee : values()) {
            bee.init();
        }
        for (gregicality.legacy.integration.forestry.integration.tinkers.GTBeeDefinition bee : values()) {
            bee.registerMutations();
        }
    }

    private void setSpeciesProperties(GTAlleleBeeSpecies beeSpecies) {
        this.speciesProperties.accept(beeSpecies);
    }

    private void setAlleles(IAllele[] template) {
        this.alleles.accept(template);
    }

    private void registerMutations() {
        if (generationCondition.get()) {
            this.mutations.accept(this);
        }
    }

    private void init() {
        if (generationCondition.get()) {
            setSpeciesProperties(species);

            template = branch.getTemplate();
            AlleleHelper.getInstance().set(template, SPECIES, species);
            setAlleles(template);

            genome = BeeManager.beeRoot.templateAsGenome(template);

            BeeManager.beeRoot.registerTemplate(template);
        }
    }

    private IBeeMutationBuilder registerMutation(IBeeDefinition parent1, IBeeDefinition parent2, int chance) {
        return registerMutation(parent1.getGenome().getPrimary(), parent2.getGenome().getPrimary(), chance);
    }

    private IBeeMutationBuilder registerMutation(IAlleleBeeSpecies parent1, IBeeDefinition parent2, int chance) {
        return registerMutation(parent1, parent2.getGenome().getPrimary(), chance);
    }

    private IBeeMutationBuilder registerMutation(IBeeDefinition parent1, IAlleleBeeSpecies parent2, int chance) {
        return registerMutation(parent1.getGenome().getPrimary(), parent2, chance);
    }

    private IBeeMutationBuilder registerMutation(IAlleleBeeSpecies parent1, IAlleleBeeSpecies parent2, int chance) {
        return BeeManager.beeMutationFactory.createMutation(parent1, parent2, getTemplate(), chance);
    }

    @Override
    @Nonnull
    public final IAllele[] getTemplate() {
        return Arrays.copyOf(template, template.length);
    }

    @Override
    @Nonnull
    public final IBeeGenome getGenome() {
        return genome;
    }

    @Nonnull
    @Override
    public final IBee getIndividual() {
        return new Bee(genome);
    }

    @Nonnull
    @Override
    public final ItemStack getMemberStack(@Nonnull EnumBeeType beeType) {
        return BeeManager.beeRoot.getMemberStack(getIndividual(), beeType);
    }
}
