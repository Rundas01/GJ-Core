package gregicality.legacy.integration.forestry;

import forestry.api.apiculture.*;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.IAllele;
import forestry.apiculture.genetics.Bee;
import forestry.apiculture.genetics.BeeDefinition;
import forestry.apiculture.genetics.IBeeDefinition;
import forestry.apiculture.items.EnumHoneyComb;
import forestry.core.genetics.alleles.AlleleHelper;
import forestry.core.genetics.alleles.EnumAllele;
import gregicality.multiblocks.api.unification.GCYMMaterials;
import gregtech.api.GTValues;
import gregtech.api.unification.OreDictUnifier;
import gregtech.integration.forestry.bees.GTAlleleBeeSpecies;
import gregtech.integration.forestry.bees.GTBranchDefinition;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.text.WordUtils;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static forestry.api.apiculture.EnumBeeChromosome.*;
import static forestry.apiculture.genetics.BeeDefinition.*;
import static gregicality.legacy.integration.forestry.ForestryModule.*;
import static gregicality.multiblocks.api.unification.GCYMMaterials.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.integration.forestry.bees.GTBeeDefinition.*;

public enum GTBeeDefinition implements IBeeDefinition {
    //Element Materials
    MOLYBDENUM(GTBranchDefinition.GT_METAL, "Molybdena", false, 0xDEDEFF, 0xFAFAFA,
            beeSpecies -> {
                beeSpecies.addProduct(getGCYLRComb(GTCombType.MOLYBDENUM), 0.15f);
            },
            template -> {
                AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(ARSENIC, FLUORINE, 15);
                mutation.requireResource("blockMolybdenum");
            }
    ),

    NIOBIUM(GTBranchDefinition.GT_METAL, "Niobia", false, 0x9D95A5, 0xFAFAFA,
            beeSpecies -> {
                beeSpecies.addProduct(getGCYLRComb(GTCombType.NIOBIUM), 0.15f);
            },
            template -> {
                AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(MANGANESE, SULFUR, 15);
                mutation.requireResource("blockNiobium");
            }
    ),

    COBALT(GTBranchDefinition.GT_METAL, "Caerulio", false, 0x03265F, 0x59AAEF,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY),0.18f);
                beeSpecies.addSpecialty(getGCYLRComb(GTCombType.COBALT), 0.1f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> {
                AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(IMPERIAL, DEMONIC, 11);
                mutation.requireResource("blockCobalt");
            }
    ),

    //Composite Materials

    GLOWSTONE(GTBranchDefinition.GT_GEM, "Glowstone", false, 0x9D95A5, 0xFAFAFA,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.STONE), 0.3f);
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.RAREEARTH), 0.15f);
                beeSpecies.addSpecialty(getGCYLRComb(GTCombType.GLOWSTONE),0.15f);
            },
            template -> {
                AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(REDSTONE, GOLD, 8);
                mutation.requireResource("blockGlowstone");
            }
    ),

    MICA(GTBranchDefinition.GT_ORGANIC, "Mica", false, 0xFFC826,0xC1C1F6,
            beeSpecies -> {
                beeSpecies.addProduct(getGCYLRComb(GTCombType.MICA), 0.25f);
            },
            template -> {
                AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(PEAT, SILICON, 15);
                mutation.requireResource("blockMica");
            }
    ),

    //GCYM Materials
    STELLITE100(GTBranchDefinition.GT_ALLOY, "Stellite", false, 0xDEDEFF, 0xFAFAFA,
        beeSpecies -> {
            beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.STAINLESSSTEEL), 0.15f);
            beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.TUNGSTEN), 0.15f);
            beeSpecies.addSpecialty(getGCYLRComb(GTCombType.STELLITE100), 0.05f);
        },
        template -> {
            AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
            AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
        },
        dis -> {
            IBeeMutationBuilder mutation = dis.registerMutation(STAINLESSSTEEL, TUNGSTEN, 8);
            mutation.requireResource("blockStellite100");
        }
    ),

    WATERTIGHTSTEEL(GTBranchDefinition.GT_ALLOY,"Grisium",false,0x355D6A,0xFAFAFA,
        beeSpecies -> {
            beeSpecies.addProduct(getGCYLRComb(GTCombType.WATERTIGHTSTEEL), 0.15f);
            beeSpecies.addSpecialty(OreDictUnifier.get(dust, GCYMMaterials.WatertightSteel), 0.05f);
        },
        template -> {
            AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
            AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
        },
        dis -> {
            IBeeMutationBuilder mutation = dis.registerMutation(STAINLESSSTEEL, ALUMINIUM, 8);
            mutation.requireResource("blockWatertightSteel");
        }
    ),

    MARAGINGSTEEL300(GTBranchDefinition.GT_ALLOY,"Maraging Steel",false,0x637087,0xFAFAFA,
        beeSpecies -> {
            beeSpecies.addProduct(getGCYLRComb(GTCombType.MARAGINGSTEEL300), 0.15f);
            beeSpecies.addSpecialty(OreDictUnifier.get(dust, GCYMMaterials.MaragingSteel300), 0.05f);
            beeSpecies.setHasEffect();
        },
        template -> {
            AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
            AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
        },
        dis -> {
            IBeeMutationBuilder mutation = dis.registerMutation(WATERTIGHTSTEEL, TITANIUM, 7);
            mutation.requireResource("blockMaragingSteel300");
        }
    ),

    HASTELLOYC276(GTBranchDefinition.GT_ALLOY,"Hastelloy-C",false,0xCF3939,0xFAFAFA,
        beeSpecies -> {
            beeSpecies.addProduct(getGCYLRComb(GTCombType.HASTELLOYC276), 0.15f);
            beeSpecies.addSpecialty(OreDictUnifier.get(dust, GCYMMaterials.HastelloyC276), 0.05f);
        },
        template -> {
            AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
            AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
        },
        dis -> {
            IBeeMutationBuilder mutation = dis.registerMutation(STELLITE100, MOLYBDENUM, 8);
            mutation.requireResource("blockHastelloyC276");
        }
    ),

    HASTELLOYX(GTBranchDefinition.GT_ALLOY,"Hastelloy-X",false,0x6BA3E3,0xFAFAFA,
        beeSpecies -> {
            beeSpecies.addProduct(getGCYLRComb(GTCombType.HASTELLOYC276), 0.15f);
            beeSpecies.addProduct(getGCYLRComb(GTCombType.NIOBIUM), 0.15f);
            beeSpecies.addSpecialty(getGCYLRComb(GTCombType.HASTELLOYX), 0.05f);
            beeSpecies.setHasEffect();
        },
        template -> {
            AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
            AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
        },
        dis -> {
            IBeeMutationBuilder mutation = dis.registerMutation(HASTELLOYC276, NIOBIUM, 8);
            mutation.requireResource("blockHastelloyX");
        }
    ),

    TRINAQUADALLOY(GTBranchDefinition.GT_ALLOY,"Trinaquadalloy",true,0x281832,0xFAFAFA,
        beeSpecies -> {
            beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.TRINIUM), 0.15f);
            beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.NAQUADAH), 0.15f);
            beeSpecies.addSpecialty(getGCYLRComb(GTCombType.TRINAQUADALLOY), 0.05f);
            beeSpecies.setHasEffect();
        },
        template -> {
            AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
            AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
        },
        dis -> {
            IBeeMutationBuilder mutation = dis.registerMutation(TRINIUM, NAQUADAH, 2);
            mutation.requireResource("blockTrinaquadalloy");
        }
    ),

    ZERON100(GTBranchDefinition.GT_ALLOY,"Zeron",false,0x325A8C,0xFAFAFA,
        beeSpecies -> {
            beeSpecies.addProduct(getGCYLRComb(GTCombType.ZERON100), 0.15f);
            beeSpecies.addSpecialty(OreDictUnifier.get(dust, GCYMMaterials.Zeron100), 0.05f);
            beeSpecies.setHasEffect();
        },
        template -> {
            AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
            AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
        },
        dis -> {
            IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.INDUSTRIOUS, IMPERIAL, 10);
            mutation.requireResource("blockZeron100");
        }
    ),

    TITANIUMCARBIDE(GTBranchDefinition.GT_ALLOY,"Titaniumcarbide",false,0xB20B3A,0xFAFAFA,
        beeSpecies -> {
            beeSpecies.addProduct(getGCYLRComb(GTCombType.TITANIUMCARBIDE), 0.15f);
            beeSpecies.addSpecialty(OreDictUnifier.get(dust, GCYMMaterials.TitaniumCarbide), 0.05f);
        },
        template -> {
            AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
            AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
        },
        dis -> {
            IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.INDUSTRIOUS, IMPERIAL, 10);
            mutation.requireResource("blockTitaniumCarbide");
        }
    ),

    TANTALUMCARBIDE(GTBranchDefinition.GT_ALLOY,"Tantalumcarbide",false,0x56566A,0xFAFAFA,
        beeSpecies -> {
            beeSpecies.addProduct(getGCYLRComb(GTCombType.TANTALUMCARBIDE), 0.15f);
            beeSpecies.addSpecialty(OreDictUnifier.get(dust, GCYMMaterials.TantalumCarbide), 0.05f);
        },
        template -> {
            AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
            AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
        },
        dis -> {
            IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.INDUSTRIOUS, IMPERIAL, 10);
            mutation.requireResource("blockTantalumCarbide");
        }
    ),

    MOLYBDENUMDISILICIDE(GTBranchDefinition.GT_ALLOY,"Molybdenumdisilicide",false,0x6A5BA3,0xFAFAFA,
        beeSpecies -> {
            beeSpecies.addProduct(OreDictUnifier.get(dust, Molybdenum), 0.15f);
            beeSpecies.addProduct(OreDictUnifier.get(dust, Silicon), 0.15f);
            beeSpecies.addSpecialty(getGCYLRComb(GTCombType.MOLYBDENUMDISILICIDE),0.1f);
        },
        template -> {
            AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
            AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
        },
        dis -> {
            IBeeMutationBuilder mutation = dis.registerMutation(MOLYBDENUM, SILICON, 9);
            mutation.requireResource("blockMolybdenumDisilicide");
        }
    ),

    HSLASTEEL(GTBranchDefinition.GT_ALLOY,"HSLA",false,0x808080,0xFAFAFA,
        beeSpecies -> {
            beeSpecies.addProduct(getGCYLRComb(GTCombType.HSLASTEEL), 0.15f);
            beeSpecies.addSpecialty(OreDictUnifier.get(dust, GCYMMaterials.HSLASteel), 0.05f);
        },
        template -> {
            AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
            AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
        },
        dis -> {
            IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.INDUSTRIOUS, IMPERIAL, 10);
            mutation.requireResource("blockHSLASteel");
        }
    ),

    TITANIUMTUNGSTENCARBIDE(GTBranchDefinition.GT_ALLOY,"Titaniumtungstencarbide",false,0x800D0D,0xFAFAFA,
        beeSpecies -> {
            beeSpecies.addProduct(getGCYLRComb(GTCombType.TITANIUMTUNGSTENCARBIDE), 0.15f);
            beeSpecies.addSpecialty(OreDictUnifier.get(dust, GCYMMaterials.TitaniumTungstenCarbide), 0.05f);
            beeSpecies.setHasEffect();
        },
        template -> {
            AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
            AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
        },
        dis -> {
            IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.INDUSTRIOUS, IMPERIAL, 10);
            mutation.requireResource("blockTitaniumTungstenCarbide");
        }
    ),

    INCOLOYMA976(GTBranchDefinition.GT_ALLOY,"Incoloy",false,0x37BF7E,0xFAFAFA,
        beeSpecies -> {
            beeSpecies.addProduct(getGCYLRComb(GTCombType.INCOLOYMA956), 0.15f);
            beeSpecies.addSpecialty(OreDictUnifier.get(dust, GCYMMaterials.IncoloyMA956), 0.05f);
            beeSpecies.setHasEffect();
        },
        template -> {
            AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
            AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
        },
        dis -> {
            IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.INDUSTRIOUS, IMPERIAL, 10);
            mutation.requireResource("blockIncoloyMA956");
        }
    );

    private final GTBranchDefinition branch;
    private final GTAlleleBeeSpecies species;
    private final Consumer<GTAlleleBeeSpecies> speciesProperties;
    private final Consumer<IAllele[]> alleles;
    private final Consumer<GTBeeDefinition> mutations;
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
                    Consumer<GTBeeDefinition> mutations) {
        this(branch, binomial, dominant, primary, secondary, speciesProperties, alleles, mutations, () -> true);
    }

    GTBeeDefinition(GTBranchDefinition branch,
                    String binomial,
                    boolean dominant,
                    int primary,
                    int secondary,
                    Consumer<GTAlleleBeeSpecies> speciesProperties,
                    Consumer<IAllele[]> alleles,
                    Consumer<GTBeeDefinition> mutations,
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
        for (GTBeeDefinition bee : values()) {
            bee.init();
        }
        for (GTBeeDefinition bee : values()) {
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

