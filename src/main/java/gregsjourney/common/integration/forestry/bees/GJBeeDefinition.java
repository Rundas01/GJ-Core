package gregsjourney.common.integration.forestry.bees;

import forestry.api.apiculture.*;
import forestry.api.genetics.IAllele;
import forestry.apiculture.ModuleApiculture;
import forestry.apiculture.genetics.Bee;
import forestry.apiculture.genetics.IBeeDefinition;
import forestry.apiculture.items.EnumHoneyComb;
import forestry.core.genetics.alleles.AlleleHelper;
import gregsjourney.common.integration.forestry.bees.mutation.DimensionMutationCondition;
import gregsjourney.common.integration.forestry.item.GJCombType;
import gregtech.api.GTValues;
import gregtech.api.unification.OreDictUnifier;
import gregtech.integration.forestry.ForestryModule;
import gregtech.integration.forestry.bees.GTAlleleBeeSpecies;
import gregtech.integration.forestry.bees.GTCombType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;
import org.apache.commons.lang3.text.WordUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static forestry.api.apiculture.EnumBeeChromosome.SPECIES;
import static forestry.apiculture.genetics.BeeDefinition.*;
import static forestry.apiculture.items.EnumHoneyComb.HONEY;
import static gregsjourney.api.unification.material.GJSpaceMaterials.*;
import static gregsjourney.common.integration.forestry.item.GJCombType.*;
import static gregsjourney.common.integration.forestry.item.GJCombType.ENERGY;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.integration.forestry.bees.GTBeeDefinition.*;

public enum GJBeeDefinition implements IBeeDefinition {
    //Primal
    AER(GJBranchDefinition.GJ_MAGIC, "Aer", false, 0xffff7e, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.2f);
                beeSpecies.addSpecialty(getGJComb(AIR), 0.1f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(FOREST, MEADOWS, 15);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }),
    TERRA(GJBranchDefinition.GJ_MAGIC, "Terra", false, 0x56c000, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.2f);
                beeSpecies.addSpecialty(getGJComb(EARTH), 0.1f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(FOREST, MEADOWS, 15);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }),
    IGNIS(GJBranchDefinition.GJ_MAGIC, "Ignis", false, 0xff5a01, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.2f);
                beeSpecies.addSpecialty(getGJComb(FIRE), 0.1f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(FOREST, MEADOWS, 15);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }),
    AQUA(GJBranchDefinition.GJ_MAGIC, "Aqua", false, 0x3cd4fc, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.2f);
                beeSpecies.addSpecialty(getGJComb(WATER), 0.1f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(FOREST, MEADOWS, 15);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }),
    ORDO(GJBranchDefinition.GJ_MAGIC, "Ordo", false, 0xd5d4ec, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.2f);
                beeSpecies.addSpecialty(getGJComb(ORDER), 0.1f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(FOREST, MEADOWS, 15);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }),
    PERDITIO(GJBranchDefinition.GJ_MAGIC, "Perditio", false, 0x404040, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.2f);
                beeSpecies.addSpecialty(getGJComb(ENTROPY), 0.1f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(FOREST, MEADOWS, 15);
                mutation.requireNight();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }),
    //Composite
    VACUOUS(GJBranchDefinition.GJ_MAGIC, "Vacuous", false, 0x888888, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(AIR), 0.25f);
                beeSpecies.addProduct(getGJComb(ENTROPY), 0.25f);
                beeSpecies.addSpecialty(getGJComb(VOID), 0.2f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(AER, PERDITIO, 15);
                mutation.requireNight();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
        ),
    LUX(GJBranchDefinition.GJ_MAGIC, "Lux", false, 0xffffc0, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(AIR), 0.25f);
                beeSpecies.addProduct(getGJComb(FIRE), 0.25f);
                beeSpecies.addSpecialty(getGJComb(LIGHT), 0.2f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(AER, IGNIS, 15);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    MOTUS(GJBranchDefinition.GJ_MAGIC, "Motus", false, 0xcdccf4, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(AIR), 0.25f);
                beeSpecies.addProduct(getGJComb(ORDER), 0.25f);
                beeSpecies.addSpecialty(getGJComb(MOTION), 0.2f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(AER, ORDO, 15);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    GELUM(GJBranchDefinition.GJ_MAGIC, "Gelum", false, 0xe1ffff, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(FIRE), 0.25f);
                beeSpecies.addProduct(getGJComb(ENTROPY), 0.25f);
                beeSpecies.addSpecialty(getGJComb(COLD), 0.2f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(IGNIS, PERDITIO, 15);
                mutation.requireNight();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    VITREUS(GJBranchDefinition.GJ_MAGIC, "Vitreus", false, 0x80ffff, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(AIR), 0.25f);
                beeSpecies.addProduct(getGJComb(EARTH), 0.25f);
                beeSpecies.addSpecialty(getGJComb(CRYSTAL), 0.2f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(AER, TERRA, 15);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    METALLUM(GJBranchDefinition.GJ_MAGIC, "Metallum", false, 0xb5b5cd, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(EARTH), 0.25f);
                beeSpecies.addProduct(getGJComb(ORDER), 0.25f);
                beeSpecies.addSpecialty(getGJComb(METAL), 0.2f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(TERRA, ORDO, 15);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    VICTUS(GJBranchDefinition.GJ_MAGIC, "Victus", false, 0xde0005, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(EARTH), 0.25f);
                beeSpecies.addProduct(getGJComb(WATER), 0.25f);
                beeSpecies.addSpecialty(getGJComb(LIFE), 0.2f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(TERRA, AQUA, 15);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    MORTUUS(GJBranchDefinition.GJ_MAGIC, "Mortuus", false, 0x6a0005, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(WATER), 0.25f);
                beeSpecies.addProduct(getGJComb(ENTROPY), 0.25f);
                beeSpecies.addSpecialty(getGJComb(DEATH), 0.2f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(AQUA, PERDITIO, 15);
                mutation.requireNight();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    POTENTIA(GJBranchDefinition.GJ_MAGIC, "Potentia", false, 0xc0ffff, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(ORDER), 0.25f);
                beeSpecies.addProduct(getGJComb(FIRE), 0.25f);
                beeSpecies.addSpecialty(getGJComb(ENERGY), 0.2f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(ORDO, IGNIS, 15);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    PERMUTATIO(GJBranchDefinition.GJ_MAGIC, "Permutatio", false, 0x578357, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(ORDER), 0.25f);
                beeSpecies.addProduct(getGJComb(ENTROPY), 0.25f);
                beeSpecies.addSpecialty(getGJComb(EXCHANGE), 0.2f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(ORDO, PERDITIO, 15);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    PRAECANTATIO(GJBranchDefinition.GJ_MAGIC, "Praecantatio", true, 0xcf00ff, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(AIR), 0.5f);
                beeSpecies.addProduct(getGJComb(ENERGY), 0.5f);
                beeSpecies.addSpecialty(getGJComb(MAGIC), 0.25f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(AER, POTENTIA, 10);
                mutation.requireNight();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    AURAM(GJBranchDefinition.GJ_MAGIC, "Auram", true, 0xffc0ff, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(MAGIC), 0.5f);
                beeSpecies.addProduct(getGJComb(AIR), 0.5f);
                beeSpecies.addSpecialty(getGJComb(AURA), 0.25f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(AER, PRAECANTATIO, 10);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    ALKIMIA(GJBranchDefinition.GJ_MAGIC, "Alkimia", true, 0x23ac9d, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(MAGIC), 0.5f);
                beeSpecies.addProduct(getGJComb(WATER), 0.5f);
                beeSpecies.addSpecialty(getGJComb(ALCHEMY), 0.25f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(PRAECANTATIO, AQUA, 10);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    VITIUM(GJBranchDefinition.GJ_MAGIC, "Vitium", true, 0x800080, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(MAGIC), 0.5f);
                beeSpecies.addProduct(getGJComb(ENTROPY), 0.5f);
                beeSpecies.addSpecialty(getGJComb(FLUX), 0.25f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(PRAECANTATIO, PERDITIO, 10);
                mutation.requireNight();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    TENEBRAE(GJBranchDefinition.GJ_MAGIC, "Tenebrae", true, 0x222222, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(LIGHT), 0.75f);
                beeSpecies.addProduct(getGJComb(VOID), 0.75f);
                beeSpecies.addSpecialty(getGJComb(DARKNESS), 0.5f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(LUX, VACUOUS, 5);
                mutation.requireNight();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    ALIENIS(GJBranchDefinition.GJ_MAGIC, "Alienis", true, 0x805080, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(VOID), 0.75f);
                beeSpecies.addProduct(getGJComb(DARKNESS), 0.75f);
                beeSpecies.addSpecialty(getGJComb(ELDRITCH), 0.5f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(VACUOUS, TENEBRAE, 5);
                mutation.requireNight();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    VOLATUS(GJBranchDefinition.GJ_MAGIC, "Volatus", true, 0xe7e7d7, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(AIR), 0.5f);
                beeSpecies.addProduct(getGJComb(MOTION), 0.5f);
                beeSpecies.addSpecialty(getGJComb(FLIGHT), 0.25f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(AER, MOTUS, 10);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    HERBA(GJBranchDefinition.GJ_MAGIC, "Herba", true, 0x01ac00, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(LIFE), 0.5f);
                beeSpecies.addProduct(getGJComb(EARTH), 0.5f);
                beeSpecies.addSpecialty(getGJComb(PLANT), 0.25f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(VICTUS, TERRA, 10);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    INSTRUMENTUM(GJBranchDefinition.GJ_MAGIC, "Instrumentum", true, 0x4040ee, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(METAL), 0.75f);
                beeSpecies.addProduct(getGJComb(ENERGY), 0.75f);
                beeSpecies.addSpecialty(getGJComb(TOOL), 0.5f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(METALLUM, POTENTIA, 5);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    FABRICO(GJBranchDefinition.GJ_MAGIC, "Fabrico", true, 0x809d80, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addSpecialty(getGJComb(CRAFT), 0.75f);
                beeSpecies.addProduct(getGJComb(TOOL), 0.75f);
                beeSpecies.addProduct(getGJComb(EXCHANGE), 0.5f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(INSTRUMENTUM, PERMUTATIO, 5);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    MACHINA(GJBranchDefinition.GJ_MAGIC, "Machina", true, 0x8080a0, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(MOTION), 0.75f);
                beeSpecies.addProduct(getGJComb(TOOL), 0.75f);
                beeSpecies.addSpecialty(getGJComb(MECHANISM), 0.5f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(MOTUS, INSTRUMENTUM, 5);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    VINCULUM(GJBranchDefinition.GJ_MAGIC, "Vinculum", true, 0x9a8080, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(MOTION), 0.5f);
                beeSpecies.addProduct(getGJComb(ENTROPY), 0.5f);
                beeSpecies.addSpecialty(getGJComb(TRAP), 0.25f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(MOTUS, PERDITIO, 10);
                mutation.requireNight();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    SPIRITUS(GJBranchDefinition.GJ_MAGIC, "Spiritus", true, 0xebebfb, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(LIFE), 0.75f);
                beeSpecies.addProduct(getGJComb(DEATH), 0.75f);
                beeSpecies.addSpecialty(getGJComb(SOUL), 0.5f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(VICTUS, MORTUUS, 5);
                mutation.requireNight();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    COGNITIO(GJBranchDefinition.GJ_MAGIC, "Cognitio", true, 0xf9967f, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(FIRE), 0.5f);
                beeSpecies.addProduct(getGJComb(SOUL), 0.5f);
                beeSpecies.addSpecialty(getGJComb(MIND), 0.25f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(IGNIS, SPIRITUS, 10);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    SENSUS(GJBranchDefinition.GJ_MAGIC, "Sensus", true, 0xc0ffc0, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(SOUL), 0.5f);
                beeSpecies.addProduct(getGJComb(AIR), 0.5f);
                beeSpecies.addSpecialty(getGJComb(SENSES), 0.25f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(AER, SPIRITUS, 10);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    AVERSIO(GJBranchDefinition.GJ_MAGIC, "Aversio", true, 0xc05050, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(SOUL), 0.5f);
                beeSpecies.addProduct(getGJComb(ENTROPY), 0.5f);
                beeSpecies.addSpecialty(getGJComb(AVERSION), 0.25f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(SPIRITUS, PERDITIO, 10);
                mutation.requireNight();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    PRAEMUNIO(GJBranchDefinition.GJ_MAGIC, "Praemunio", true, 0x00c0c0, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(EARTH), 0.5f);
                beeSpecies.addProduct(getGJComb(SOUL), 0.5f);
                beeSpecies.addSpecialty(getGJComb(PROTECT), 0.25f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(TERRA, SPIRITUS, 10);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    DESIDERIUM(GJBranchDefinition.GJ_MAGIC, "Desiderium", true, 0xe6be44, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(SOUL), 0.75f);
                beeSpecies.addProduct(getGJComb(VOID), 0.75f);
                beeSpecies.addSpecialty(getGJComb(DESIRE), 0.5f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(SPIRITUS, VACUOUS, 5);
                mutation.requireNight();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    EXANIMIS(GJBranchDefinition.GJ_MAGIC, "Exanimis", true, 0x3a4000, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(MOTION), 0.75f);
                beeSpecies.addProduct(getGJComb(DEATH), 0.75f);
                beeSpecies.addSpecialty(getGJComb(UNDEAD), 0.5f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(MOTUS, MORTUUS, 5);
                mutation.requireNight();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    BESTIA(GJBranchDefinition.GJ_MAGIC, "Bestia", true, 0x9f6409, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(MOTION), 0.75f);
                beeSpecies.addProduct(getGJComb(LIFE), 0.75f);
                beeSpecies.addSpecialty(getGJComb(BEAST), 0.5f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(MOTUS, VICTUS, 5);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    HUMANUS(GJBranchDefinition.GJ_MAGIC, "Humanus", true, 0xffd7c0, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(SOUL), 0.75f);
                beeSpecies.addProduct(getGJComb(LIFE), 0.75f);
                beeSpecies.addSpecialty(getGJComb(MAN), 0.5f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(SPIRITUS, VICTUS, 5);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }
    ),
    //Planetary
    SPACE(GJBranchDefinition.GJ_PLANETS, "Space", false, 0x003366, 0xC0C0C0,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.SPACE), 0.75f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(INDUSTRIOUS, IMPERIAL, 10);
            }
    ),
    METEORICIRON(GJBranchDefinition.GJ_PLANETS, "MeteoricIron", false, 0x321928, 0x643250,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.SPACE), 0.25f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.METEORICIRON), 0.2f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(SPACE, IRON, 9);
                mutation.addMutationCondition(new DimensionMutationCondition(-28,"Luna"));
            }
    ),
    LUNA(GJBranchDefinition.GJ_PLANETS, "Luna", false, 0x373735, 0x7E7E78,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(MOON), 0.75f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(SPACE, CLAY, 4);
                mutation.addMutationCondition(new DimensionMutationCondition(-28,"Luna"));
            }
    ),
    MARS(GJBranchDefinition.GJ_PLANETS, "Mars", false, 0x220D05, 0x3A1505,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.MARS), 0.75f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(LUNA, IRON, 20);
                mutation.addMutationCondition(new DimensionMutationCondition(-29,"Mars"));
            }
    ),
    PHOBOS(GJBranchDefinition.GJ_PLANETS, "Phobos", true, 0x220D05, 0x7a5706,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.MARS), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust,PhobosStone),0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(MARS, LUNA, 20);
                mutation.addMutationCondition(new DimensionMutationCondition(-1012,"Phobos"));
            }
    ),
    DEIMOS(GJBranchDefinition.GJ_PLANETS, "Deimos", true, 0x220D05, 0x7a3206,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.MARS), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust,DeimosStone),0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(MARS, SPACE, 20);
                mutation.addMutationCondition(new DimensionMutationCondition(-1013,"Deimos"));
            }
    ),
    CERES(GJBranchDefinition.GJ_PLANETS, "Ceres", true, 0x3ca5b7, 0x1e7267,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.MARS), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust,CeresStone),0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(MARS, METEORICIRON, 20);
                mutation.requireDay();
                mutation.addMutationCondition(new DimensionMutationCondition(-1007,"Ceres"));
            }
    ),
    DESH(GJBranchDefinition.GJ_PLANETS, "Desh", false, 0x323232, 0x282828,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.SPACE), 0.25f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.DESH), 0.2f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(MARS, TITANIUM, 9);
                mutation.addMutationCondition(new DimensionMutationCondition(-29,"Mars"));
            }
    ),
    MERCURY(GJBranchDefinition.GJ_PLANETS, "Mercury", false, 0x4A4033, 0xB5A288,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.MERCURY), 0.75f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(METEORICIRON, TUNGSTEN, 25);
                mutation.addMutationCondition(new DimensionMutationCondition(-1005,"Mercury"));
            }
    ),
    VENUS(GJBranchDefinition.GJ_PLANETS, "Venus", false, 0x4A4033, 0xB5A288,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.VENUS), 0.75f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(MERCURY, PLATINUM, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(-31,"Venus"));
            }
    ),
    JUPITER(GJBranchDefinition.GJ_PLANETS, "Jupiter", false, 0x734B2E, 0xD0CBC4,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.JUPITER), 0.35f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(MARS, DESH, 15);
                mutation.addMutationCondition(new DimensionMutationCondition(-30,"Asteroid Belt"));
            }
    ),
    EUROPA(GJBranchDefinition.GJ_PLANETS, "Europa", true, 0x5982ea, 0x0b36a3,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.JUPITER), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust,EuropaStone),0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(JUPITER, IRON, 15);
                mutation.addMutationCondition(new DimensionMutationCondition(-1017,"Europa"));
            }
    ),
    IO(GJBranchDefinition.GJ_PLANETS, "Io", true, 0x734B2E, 0xe5701b,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.JUPITER), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust,IoStone),0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(JUPITER, DEMONIC, 15);
                mutation.addMutationCondition(new DimensionMutationCondition(-1014,"Io"));
            }
    ),
    GANYMEDE(GJBranchDefinition.GJ_PLANETS, "Ganymede", true, 0x3d1b10, 0x190c07,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.JUPITER), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust,GanymedeStone),0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(JUPITER, TITANIUM, 15);
                mutation.addMutationCondition(new DimensionMutationCondition(-1016,"Ganymede"));
            }
    ),
    CALLISTO(GJBranchDefinition.GJ_PLANETS, "Callisto", true, 0x0f333d, 0x0d84a5,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.JUPITER), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust,CallistoStone),0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(JUPITER, GLACIAL, 15);
                mutation.addMutationCondition(new DimensionMutationCondition(-1022,"Callisto"));
            }
    ),
    LEDOX(GJBranchDefinition.GJ_PLANETS, "Ledox", false, 0x0000CD, 0x0074FF,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.SPACE), 0.25f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.LEDOX), 0.2f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(CALLISTO, LEAD, 15);
                mutation.addMutationCondition(new DimensionMutationCondition(-1022,"Callisto"));
            }
    ),
    SATURN(GJBranchDefinition.GJ_PLANETS, "Saturn", false, 0xD2A472, 0xF8C37B,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.SATURN), 0.5f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(JUPITER, LEDOX, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(-30,"Asteroid Belt"));
            }
    ),
    ENCELADUS(GJBranchDefinition.GJ_PLANETS, "Enceladus", true, 0xD2A472, 0x193fa0,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.SATURN), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust,EnceladusStone),0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(SATURN, CHROME, 25);
                mutation.addMutationCondition(new DimensionMutationCondition(-1017,"Enceladus"));
            }
    ),
    TITAN(GJBranchDefinition.GJ_PLANETS, "Titan", true, 0xa0641b, 0x7c1024,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.SATURN), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust,TitanStone),0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(SATURN, NICKEL, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(-1018,"Titan"));
            }
    ),
    URANUS(GJBranchDefinition.GJ_PLANETS, "Uranus", false, 0x75C0C9, 0x84D8EC,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.URANUS), 0.75f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(SATURN, TRINIUM, 10);
                mutation.addMutationCondition(new DimensionMutationCondition(-30,"Asteroid Belt"));
            }
    ),
    MIRANDA(GJBranchDefinition.GJ_PLANETS, "Miranda", true, 0x75C0C9, 0x0d211c,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.URANUS), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust,MirandaStone),0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(URANUS, TIN, 10);
                mutation.addMutationCondition(new DimensionMutationCondition(-1024,"Miranda"));
            }
    ),
    OBERON(GJBranchDefinition.GJ_PLANETS, "Oberon", true, 0x4A4033, 0xB5A288,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.URANUS), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust,OberonStone),0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(URANUS, IRIDIUM, 10);
                mutation.addMutationCondition(new DimensionMutationCondition(-1019,"Oberon"));
            }
    ),
    NEPTUNE(GJBranchDefinition.GJ_PLANETS, "Neptune", false, 0x334CFF, 0x576DFF,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.NEPTUNE), 0.75f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(URANUS, OBERON, 7);
                mutation.addMutationCondition(new DimensionMutationCondition(-30,"Asteroid Belt"));
            }
    ),
    PROTEUS(GJBranchDefinition.GJ_PLANETS, "Proteus", true, 0x334CFF, 0x592610,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.NEPTUNE), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust,ProteusStone),0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(NEPTUNE, COPPER, 7);
                mutation.addMutationCondition(new DimensionMutationCondition(-1020,"Proteus"));
            }
    ),
    TRITON(GJBranchDefinition.GJ_PLANETS, "Triton", true, 0x334CFF, 0x421118,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.NEPTUNE), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust,TritonStone),0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(NEPTUNE, GOLD, 7);
                mutation.addMutationCondition(new DimensionMutationCondition(-1021,"Triton"));
            }
    ),
    PLUTO(GJBranchDefinition.GJ_PLANETS, "Pluto", false, 0x34271E, 0x69503D,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.PLUTO), 0.75f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(NEPTUNE, PLUTONIUM, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(-1008,"Pluto"));
            }
    ),
    HAUMEA(GJBranchDefinition.GJ_PLANETS, "Haumea", true, 0x1C1413, 0x392B28,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.PLUTO), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust,HaumeaStone),0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(PLUTO, NAQUADAH, 7);
                mutation.addMutationCondition(new DimensionMutationCondition(-1023,"Haumea"));
            }
    ),
    MAKEMAKE(GJBranchDefinition.GJ_PLANETS, "Makemake", true, 0x301811, 0x120A07,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.PLUTO), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust,MakemakeStone),0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(PLUTO, NAQUADRIA, 7);
                mutation.addMutationCondition(new DimensionMutationCondition(-1011,"Makemake"));
            }
    ),
    CENTAURI(GJBranchDefinition.GJ_PLANETS, "Centauri", false, 0x2F2A14, 0xB06B32,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.CENTAURI), 0.75f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(MAKEMAKE, DESH, 3);
                mutation.addMutationCondition(new DimensionMutationCondition(-1009,"Kuiper Belt"));
            }
    ),
    PROXIMA_B(GJBranchDefinition.GJ_PLANETS, "ProximaB", false, 0x2F2A14, 0xC17C43,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(PROXIMA), 0.5f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(CENTAURI, TUNGSTEN, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(-1025,"Proxima B"));
            }
    ),
    BARNADA(GJBranchDefinition.GJ_PLANETS, "BarnadaC", false, 0x0D5A0D, 0xE6C18D,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.BARNADA), 0.75f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(CENTAURI, THORIUM, 3);
                mutation.addMutationCondition(new DimensionMutationCondition(-1030,"Barnada C"));
            }
    ),
    BARNADA_C1(GJBranchDefinition.GJ_PLANETS, "BarnadaC1", true, 0x0D5A0D, 0x1e0b49,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.BARNADA), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust,BarnadaC1Stone),0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BARNADA, NAQUADRIA, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(-1031,"Barnada C1"));
            }
    ),
    BARNADA_C2(GJBranchDefinition.GJ_PLANETS, "BarnadaC2", true, 0x0D5A0D, 0x1e0b49,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.BARNADA), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust,BarnadaC2Stone),0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BARNADA, NEUTRONIUM, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(-1032,"Barnada C2"));
            }
    ),
    TAUCETI(GJBranchDefinition.GJ_PLANETS, "TauCeti", false, 0xffd7c0, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(CETI), 0.75f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BARNADA, ELECTROTINE, 5);
                //TODO: Dimensions ID
            }
    ),
    CHALOS(GJBranchDefinition.GJ_PLANETS, "Chalos", false, 0x0074FF, 0x1EB1FF,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.CHALOS), 0.75f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(TAUCETI, HEROIC, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(-2543,"Chalos"));
            }
    ),
    DIONA(GJBranchDefinition.GJ_PLANETS, "Diona", false, 0xDAA520, 0xF26404,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.DIONA), 0.75f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(CHALOS, REDALLOY, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(-2542,"Diona"));
            }
    ),
    FRONOS(GJBranchDefinition.GJ_PLANETS, "Fronos", false, 0x00FF00, 0x00D10B,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.FRONOS), 0.75f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(DIONA, URANIUM, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(-2545,"Fronos"));
            }
    ),
    NIBIRU(GJBranchDefinition.GJ_PLANETS, "Nibiru", false, 0x228B22, 0x677D68,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.NIBIRU), 0.75f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(FRONOS, THORIUM, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(-2544,"Nibiru"));
            }
    ),
    KOENTUS(GJBranchDefinition.GJ_PLANETS, "Koentus", false, 0x3CB371, 0x16856C,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.KOENTUS), 0.75f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(NIBIRU, NAQUADRIA, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(-2642,"Koentus"));
            }
    );

    private final GJBranchDefinition branch;
    private final GTAlleleBeeSpecies species;
    private final Consumer<GTAlleleBeeSpecies> speciesProperties;
    private final Consumer<IAllele[]> alleles;
    private final Consumer<GJBeeDefinition> mutations;
    private IAllele[] template;
    private IBeeGenome genome;
    private final Supplier<Boolean> generationCondition;

    GJBeeDefinition(GJBranchDefinition branch,
                    String binomial,
                    boolean dominant,
                    int primary,
                    int secondary,
                    Consumer<GTAlleleBeeSpecies> speciesProperties,
                    Consumer<IAllele[]> alleles,
                    Consumer<GJBeeDefinition> mutations) {
        this(branch, binomial, dominant, primary, secondary, speciesProperties, alleles, mutations, () -> true);
    }

    GJBeeDefinition(GJBranchDefinition branch,
                    String binomial,
                    boolean dominant,
                    int primary,
                    int secondary,
                    Consumer<GTAlleleBeeSpecies> speciesProperties,
                    Consumer<IAllele[]> alleles,
                    Consumer<GJBeeDefinition> mutations,
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
        this.species = new GTAlleleBeeSpecies(GTValues.MODID, uid, name, "GregTech", description, dominant,
                branch.getBranch(), binomial, primary, secondary);
        this.generationCondition = generationCondition;
    }

    public static void initBees() {
        for (GJBeeDefinition bee : values()) {
            bee.init();
        }
        for (GJBeeDefinition bee : values()) {
            bee.registerMutations();
        }
    }

    private static ItemStack getForestryComb(EnumHoneyComb type) {
        return ModuleApiculture.getItems().beeComb.get(type, 1);
    }

    private static ItemStack getGTComb(GTCombType type) {
        return new ItemStack(ForestryModule.COMBS, 1, type.ordinal());
    }

    private static ItemStack getGJComb(GJCombType type) {
        return new ItemStack(gregsjourney.common.integration.forestry.ForestryModule.COMBS, 1, type.ordinal());
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
    public final IAllele @NotNull [] getTemplate() {
        return Arrays.copyOf(template, template.length);
    }

    @NotNull
    @Override
    public final IBeeGenome getGenome() {
        return genome;
    }

    @NotNull
    @Override
    public final IBee getIndividual() {
        return new Bee(genome);
    }

    @NotNull
    @Override
    public final ItemStack getMemberStack(@NotNull EnumBeeType beeType) {
        return BeeManager.beeRoot.getMemberStack(getIndividual(), beeType);
    }
}
