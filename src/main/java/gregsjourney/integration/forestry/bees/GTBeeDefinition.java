package gregsjourney.integration.forestry.bees;

import static forestry.api.apiculture.EnumBeeChromosome.*;
import static forestry.apiculture.genetics.BeeDefinition.*;
import static forestry.apiculture.items.EnumHoneyComb.HONEY;
import static gregsjourney.api.unification.material.GJSpaceMaterials.*;
import static gregsjourney.integration.forestry.item.GJCombType.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.integration.forestry.bees.GTBeeDefinition.*;

import java.util.Arrays;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import org.apache.commons.lang3.text.WordUtils;

import gregtech.api.GTValues;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.Mods;
import gregtech.common.items.MetaItems;
import gregtech.integration.forestry.ForestryModule;
import gregtech.integration.forestry.ForestryUtil;
import gregtech.integration.forestry.bees.GTAlleleBeeSpecies;
import gregtech.integration.forestry.bees.GTCombType;
import gregtech.integration.forestry.mutation.MaterialMutationCondition;

import forestry.api.apiculture.*;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IMutationBuilder;
import forestry.apiculture.ModuleApiculture;
import forestry.apiculture.genetics.Bee;
import forestry.apiculture.genetics.BeeDefinition;
import forestry.apiculture.genetics.IBeeDefinition;
import forestry.apiculture.genetics.alleles.AlleleEffects;
import forestry.apiculture.items.EnumHoneyComb;
import forestry.core.ModuleCore;
import forestry.core.genetics.alleles.AlleleHelper;
import forestry.core.genetics.alleles.EnumAllele;
import gregsjourney.utils.GJUtil;
import gregsjourney.integration.forestry.bees.mutation.DimensionMutationCondition;
import gregsjourney.integration.forestry.item.GJCombType;

public enum GTBeeDefinition implements IBeeDefinition {

    // GT Elements
    HYDROGEN(GJBranchDefinition.GJ_NOBLEGAS, "Hydrogenia", false, 0xFFFFFF, 0xFF1493,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.HYDROGEN), 0.45f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.NITROGEN), 0.20f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.ICY);
                beeSpecies.setNocturnal();
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.SHORTEST),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(INDUSTRIOUS, IMPERIAL, 10);
                mutation.restrictTemperature(EnumTemperature.ICY);
            }),
    HELIUM(GJBranchDefinition.GJ_NOBLEGAS, "Helia", false, 0xFFA9FF, 0xC8B8B4,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.HELIUM), 0.35f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.ICY);
                beeSpecies.setNocturnal();
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.SHORTEST),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(HYDROGEN, HEROIC, 10);
                mutation1.restrictTemperature(EnumTemperature.ICY);
            }),
    LITHIUM(GJBranchDefinition.GJ_RAREMETAL, "Lithos", false, 0xF0328C, 0xE1DCFF,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.LITHIUM), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.SALT), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.COLD);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(HYDROGEN, HELIUM, 5);
                mutation.addMutationCondition(new MaterialMutationCondition(Lithium));
            }),
    BERYLLIUM(GJBranchDefinition.GJ_METAL, "Beryllos", false, 0x64B464, 0x006600,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.BERYLLIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(LITHIUM, HYDROGEN, 5);
                mutation1.addMutationCondition(new MaterialMutationCondition(Beryllium));
            }),
    BORON(GJBranchDefinition.GJ_HALFMETAL, "Bora", false, 0xD2FAD2, 0x006600,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.BORON), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(LITHIUM, HELIUM, 5);
                mutation1.addMutationCondition(new MaterialMutationCondition(Boron));
                IBeeMutationBuilder mutation2 = dis.registerMutation(BERYLLIUM, HYDROGEN, 4);
                mutation2.addMutationCondition(new MaterialMutationCondition(Boron));
            }),
    CARBON(GJBranchDefinition.GJ_ORGANIC, "Carba", false, 0x141414, 0x006600,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.CARBON), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(BERYLLIUM, HELIUM, 5);
                mutation1.addMutationCondition(new MaterialMutationCondition(Carbon));
                IBeeMutationBuilder mutation2 = dis.registerMutation(BORON, HYDROGEN, 4);
                mutation2.addMutationCondition(new MaterialMutationCondition(Carbon));
            }),
    NITROGEN(GJBranchDefinition.GJ_NONMETAL, "Nitrogenium", false, 0xFFC832, 0xA52A2A,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.NITROGEN), 0.45f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.FLUORINE), 0.20f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.ICY);
                beeSpecies.setNocturnal();
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.SHORTEST),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(BERYLLIUM, LITHIUM, 5);
                mutation1.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation2 = dis.registerMutation(BORON, HELIUM, 4);
                mutation2.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation3 = dis.registerMutation(CARBON, HYDROGEN, 3);
                mutation3.restrictTemperature(EnumTemperature.ICY);
            }),
    OXYGEN(GJBranchDefinition.GJ_NONMETAL, "Oxygeni", false, 0xFFFFFF, 0x8F8FFF,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.OXYGEN), 0.45f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.HYDROGEN), 0.20f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.ICY);
                beeSpecies.setNocturnal();
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.SHORTEST),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(BORON, LITHIUM, 5);
                mutation1.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation2 = dis.registerMutation(CARBON, HELIUM, 4);
                mutation2.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation3 = dis.registerMutation(NITROGEN, HYDROGEN, 3);
                mutation3.restrictTemperature(EnumTemperature.ICY);
            }),
    FLUORINE(GJBranchDefinition.GJ_HALOGENS, "Fluens", false, 0x86AFF0, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.FLUORINE), 0.45f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.OXYGEN), 0.20f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.ICY);
                beeSpecies.setNocturnal();
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.SHORTEST),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(BORON, BERYLLIUM, 5);
                mutation1.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation2 = dis.registerMutation(CARBON, LITHIUM, 4);
                mutation2.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation3 = dis.registerMutation(NITROGEN, HELIUM, 3);
                mutation3.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation4 = dis.registerMutation(OXYGEN, HYDROGEN, 2);
                mutation4.restrictTemperature(EnumTemperature.ICY);
            }),
    NEON(GJBranchDefinition.GJ_NOBLEGAS, "Novum", false, 0xFFC826, 0xFF7200,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.NEON), 0.35f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.ICY);
                beeSpecies.setNocturnal();
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.SHORTEST),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(CARBON, BERYLLIUM, 5);
                mutation1.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation2 = dis.registerMutation(NITROGEN, LITHIUM, 4);
                mutation2.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation3 = dis.registerMutation(OXYGEN, HELIUM, 3);
                mutation3.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation4 = dis.registerMutation(FLUORINE, HYDROGEN, 2);
                mutation4.restrictTemperature(EnumTemperature.ICY);
            }),
    SODIUM(GJBranchDefinition.GJ_ALKALINEMETAL, "Natria", false, 0x000096, 0x006600,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.SODIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(CARBON, BORON, 5);
                mutation1.addMutationCondition(new MaterialMutationCondition(Sodium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(NITROGEN, BERYLLIUM, 4);
                mutation2.addMutationCondition(new MaterialMutationCondition(Sodium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(BORON, CARBON, 3);
                mutation3.addMutationCondition(new MaterialMutationCondition(Sodium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(OXYGEN, LITHIUM, 2);
                mutation4.addMutationCondition(new MaterialMutationCondition(Sodium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(FLUORINE, HELIUM, 1);
                mutation5.addMutationCondition(new MaterialMutationCondition(Sodium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(NEON, HYDROGEN, 1);
                mutation6.addMutationCondition(new MaterialMutationCondition(Sodium));
            }),
    MAGNESIUM(GJBranchDefinition.GJ_ALKALINEMETAL, "Magnesia", false, 0xFFC8C8, 0x006600,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.MAGNESIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(NITROGEN, BORON, 5);
                mutation1.addMutationCondition(new MaterialMutationCondition(Magnesium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(OXYGEN, BERYLLIUM, 4);
                mutation2.addMutationCondition(new MaterialMutationCondition(Magnesium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(FLUORINE, LITHIUM, 3);
                mutation3.addMutationCondition(new MaterialMutationCondition(Magnesium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(NEON, HELIUM, 2);
                mutation4.addMutationCondition(new MaterialMutationCondition(Magnesium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(SODIUM, HYDROGEN, 1);
                mutation5.addMutationCondition(new MaterialMutationCondition(Magnesium));
            }),
    ALUMINIUM(GJBranchDefinition.GJ_RAREMETAL, "Alumen", true, 0xB8B8FF, 0xD6D6FF,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.ALUMINIUM), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.BAUXITE), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(NITROGEN, CARBON, 6);
                mutation1.addMutationCondition(new MaterialMutationCondition(Aluminium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(OXYGEN, BORON, 5);
                mutation2.addMutationCondition(new MaterialMutationCondition(Aluminium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(FLUORINE, BERYLLIUM, 4);
                mutation3.addMutationCondition(new MaterialMutationCondition(Aluminium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(NEON, LITHIUM, 3);
                mutation4.addMutationCondition(new MaterialMutationCondition(Aluminium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(SODIUM, HELIUM, 2);
                mutation5.addMutationCondition(new MaterialMutationCondition(Aluminium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(MAGNESIUM, HYDROGEN, 1);
                mutation6.addMutationCondition(new MaterialMutationCondition(Aluminium));
            }),
    SILICON(GJBranchDefinition.GJ_HALFMETAL, "Silex", false, 0xADA2A7, 0x736675,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.10f);
                beeSpecies.addSpecialty(OreDictUnifier.get(OrePrefix.dust, Materials.Silicon), 0.30f);
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOW);
                AlleleHelper.getInstance().set(template, TERRITORY, EnumAllele.Territory.LARGER);
                AlleleHelper.getInstance().set(template, TOLERATES_RAIN, true);
            },
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(OXYGEN, CARBON, 6);
                mutation1.addMutationCondition(new MaterialMutationCondition(Silicon));
                IBeeMutationBuilder mutation2 = dis.registerMutation(FLUORINE, BORON, 5);
                mutation2.addMutationCondition(new MaterialMutationCondition(Silicon));
                IBeeMutationBuilder mutation3 = dis.registerMutation(NEON, BERYLLIUM, 4);
                mutation3.addMutationCondition(new MaterialMutationCondition(Silicon));
                IBeeMutationBuilder mutation4 = dis.registerMutation(SODIUM, LITHIUM, 3);
                mutation4.addMutationCondition(new MaterialMutationCondition(Silicon));
                IBeeMutationBuilder mutation5 = dis.registerMutation(MAGNESIUM, HELIUM, 2);
                mutation5.addMutationCondition(new MaterialMutationCondition(Silicon));
                IBeeMutationBuilder mutation6 = dis.registerMutation(ALUMINIUM, HYDROGEN, 1);
                mutation6.addMutationCondition(new MaterialMutationCondition(Silicon));
            }),
    PHOSPHORUS(GJBranchDefinition.GJ_ORGANIC, "Phosphorus", false, 0xFFC826, 0xC1C1F6,
            beeSpecies -> {
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.PHOSPHORUS), 0.35f);
                beeSpecies.setTemperature(EnumTemperature.HOT);
                beeSpecies.setNocturnal();
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.SHORTEST),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(OXYGEN, NITROGEN, 7);
                mutation1.addMutationCondition(new MaterialMutationCondition(Phosphorus));
                IBeeMutationBuilder mutation2 = dis.registerMutation(FLUORINE, CARBON, 6);
                mutation2.addMutationCondition(new MaterialMutationCondition(Phosphorus));
                IBeeMutationBuilder mutation3 = dis.registerMutation(NEON, BORON, 5);
                mutation3.addMutationCondition(new MaterialMutationCondition(Phosphorus));
                IBeeMutationBuilder mutation4 = dis.registerMutation(SODIUM, BERYLLIUM, 4);
                mutation4.addMutationCondition(new MaterialMutationCondition(Phosphorus));
                IBeeMutationBuilder mutation5 = dis.registerMutation(MAGNESIUM, LITHIUM, 3);
                mutation5.addMutationCondition(new MaterialMutationCondition(Phosphorus));
                IBeeMutationBuilder mutation6 = dis.registerMutation(ALUMINIUM, HELIUM, 2);
                mutation6.addMutationCondition(new MaterialMutationCondition(Phosphorus));
                IBeeMutationBuilder mutation7 = dis.registerMutation(SILICON, HYDROGEN, 1);
                mutation7.addMutationCondition(new MaterialMutationCondition(Phosphorus));
            }),
    SULFUR(GJBranchDefinition.GJ_ORGANIC, "Sulphur", false, 0x1E90FF, 0x3CB4C8,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SULFUR), 0.70f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.NORMAL),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(FLUORINE, NITROGEN, 7);
                mutation1.addMutationCondition(new MaterialMutationCondition(Sulfur));
                IBeeMutationBuilder mutation2 = dis.registerMutation(NEON, CARBON, 6);
                mutation2.addMutationCondition(new MaterialMutationCondition(Sulfur));
                IBeeMutationBuilder mutation3 = dis.registerMutation(SODIUM, BORON, 5);
                mutation3.addMutationCondition(new MaterialMutationCondition(Sulfur));
                IBeeMutationBuilder mutation4 = dis.registerMutation(MAGNESIUM, BERYLLIUM, 4);
                mutation4.addMutationCondition(new MaterialMutationCondition(Sulfur));
                IBeeMutationBuilder mutation5 = dis.registerMutation(ALUMINIUM, LITHIUM, 3);
                mutation5.addMutationCondition(new MaterialMutationCondition(Sulfur));
                IBeeMutationBuilder mutation6 = dis.registerMutation(SILICON, HELIUM, 2);
                mutation6.addMutationCondition(new MaterialMutationCondition(Sulfur));
                IBeeMutationBuilder mutation7 = dis.registerMutation(PHOSPHORUS, HYDROGEN, 1);
                mutation7.addMutationCondition(new MaterialMutationCondition(Sulfur));
            }),
    CHLORINE(GJBranchDefinition.GJ_HALOGENS, "Chloros", false, 0x2D8C8C, 0x006600,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.CHLORINE), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(FLUORINE, OXYGEN, 5);
                mutation1.addMutationCondition(new MaterialMutationCondition(Salt));
                IBeeMutationBuilder mutation2 = dis.registerMutation(NEON, NITROGEN, 4);
                mutation2.addMutationCondition(new MaterialMutationCondition(Salt));
                IBeeMutationBuilder mutation3 = dis.registerMutation(SODIUM, CARBON, 4);
                mutation3.addMutationCondition(new MaterialMutationCondition(Salt));
                IBeeMutationBuilder mutation4 = dis.registerMutation(MAGNESIUM, BORON, 3);
                mutation4.addMutationCondition(new MaterialMutationCondition(Salt));
                IBeeMutationBuilder mutation5 = dis.registerMutation(ALUMINIUM, BERYLLIUM, 3);
                mutation5.addMutationCondition(new MaterialMutationCondition(Salt));
                IBeeMutationBuilder mutation6 = dis.registerMutation(SILICON, LITHIUM, 2);
                mutation6.addMutationCondition(new MaterialMutationCondition(Salt));
                IBeeMutationBuilder mutation7 = dis.registerMutation(PHOSPHORUS, HELIUM, 2);
                mutation7.addMutationCondition(new MaterialMutationCondition(Salt));
                IBeeMutationBuilder mutation8 = dis.registerMutation(SULFUR, HYDROGEN, 1);
                mutation8.addMutationCondition(new MaterialMutationCondition(Salt));
            }),
    ARGON(GJBranchDefinition.GJ_NOBLEGAS, "Argon", false, 0x89D9E1, 0xBDA5C2,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.ARGON), 0.35f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.ICY);
                beeSpecies.setNocturnal();
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.SHORTEST),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(NEON, OXYGEN, 8);
                mutation1.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation2 = dis.registerMutation(SODIUM, NITROGEN, 7);
                mutation2.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation3 = dis.registerMutation(MAGNESIUM, CARBON, 6);
                mutation3.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation4 = dis.registerMutation(ALUMINIUM, BORON, 5);
                mutation4.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation5 = dis.registerMutation(SILICON, BERYLLIUM, 4);
                mutation5.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation6 = dis.registerMutation(PHOSPHORUS, LITHIUM, 3);
                mutation6.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation7 = dis.registerMutation(SULFUR, HELIUM, 2);
                mutation7.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation8 = dis.registerMutation(CHLORINE, HYDROGEN, 1);
                mutation8.restrictTemperature(EnumTemperature.ICY);
            }),
    POTASSIUM(GJBranchDefinition.GJ_ALKALINEMETAL, "Kalia", false, 0xBEDCFF, 0x006600,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.POTASSIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(NEON, FLUORINE, 5);
                mutation1.addMutationCondition(new MaterialMutationCondition(Potassium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(SODIUM, OXYGEN, 4);
                mutation2.addMutationCondition(new MaterialMutationCondition(Potassium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(MAGNESIUM, NITROGEN, 4);
                mutation3.addMutationCondition(new MaterialMutationCondition(Potassium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(ALUMINIUM, CARBON, 3);
                mutation4.addMutationCondition(new MaterialMutationCondition(Potassium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(SILICON, BORON, 3);
                mutation5.addMutationCondition(new MaterialMutationCondition(Potassium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(PHOSPHORUS, BERYLLIUM, 2);
                mutation6.addMutationCondition(new MaterialMutationCondition(Potassium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(SULFUR, LITHIUM, 2);
                mutation7.addMutationCondition(new MaterialMutationCondition(Potassium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(CHLORINE, HELIUM, 1);
                mutation8.addMutationCondition(new MaterialMutationCondition(Potassium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(ARGON, HYDROGEN, 1);
                mutation9.addMutationCondition(new MaterialMutationCondition(Potassium));
            }),
    CALCIUM(GJBranchDefinition.GJ_ALKALINEMETAL, "Calcia", false, 0xFFF5DE, 0x006600,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.CALCIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(SODIUM, FLUORINE, 5);
                mutation1.addMutationCondition(new MaterialMutationCondition(Calcium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(MAGNESIUM, OXYGEN, 4);
                mutation2.addMutationCondition(new MaterialMutationCondition(Calcium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(ALUMINIUM, NITROGEN, 4);
                mutation3.addMutationCondition(new MaterialMutationCondition(Calcium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(SILICON, CARBON, 3);
                mutation4.addMutationCondition(new MaterialMutationCondition(Calcium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(PHOSPHORUS, BORON, 3);
                mutation5.addMutationCondition(new MaterialMutationCondition(Calcium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(SULFUR, BERYLLIUM, 2);
                mutation6.addMutationCondition(new MaterialMutationCondition(Calcium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(CHLORINE, LITHIUM, 2);
                mutation7.addMutationCondition(new MaterialMutationCondition(Calcium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(ARGON, HELIUM, 1);
                mutation8.addMutationCondition(new MaterialMutationCondition(Calcium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(POTASSIUM, HYDROGEN, 1);
                mutation9.addMutationCondition(new MaterialMutationCondition(Calcium));
            }),
    SCANDIUM(GJBranchDefinition.GJ_RAREMETAL, "Scandia", false, 0xFFFFFF, 0x006600,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.SCANDIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(SODIUM, NEON, 5);
                mutation1.addMutationCondition(new MaterialMutationCondition(Scandium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(MAGNESIUM, FLUORINE, 5);
                mutation2.addMutationCondition(new MaterialMutationCondition(Scandium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(ALUMINIUM, OXYGEN, 4);
                mutation3.addMutationCondition(new MaterialMutationCondition(Scandium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(SILICON, CARBON, 4);
                mutation4.addMutationCondition(new MaterialMutationCondition(Scandium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(PHOSPHORUS, CARBON, 3);
                mutation5.addMutationCondition(new MaterialMutationCondition(Scandium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(SULFUR, BORON, 3);
                mutation6.addMutationCondition(new MaterialMutationCondition(Scandium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(CHLORINE, BERYLLIUM, 2);
                mutation7.addMutationCondition(new MaterialMutationCondition(Scandium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(ARGON, LITHIUM, 2);
                mutation8.addMutationCondition(new MaterialMutationCondition(Scandium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(POTASSIUM, HELIUM, 1);
                mutation9.addMutationCondition(new MaterialMutationCondition(Scandium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(CALCIUM, HYDROGEN, 1);
                mutation10.addMutationCondition(new MaterialMutationCondition(Scandium));
            }),
    TITANIUM(GJBranchDefinition.GJ_RAREMETAL, "Titanus", true, 0xCC99FF, 0xDBB8FF,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.TITANIUM), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.ALMANDINE), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(MAGNESIUM, NEON, 5);
                mutation1.addMutationCondition(new MaterialMutationCondition(Titanium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(ALUMINIUM, FLUORINE, 5);
                mutation2.addMutationCondition(new MaterialMutationCondition(Titanium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(SILICON, OXYGEN, 4);
                mutation3.addMutationCondition(new MaterialMutationCondition(Titanium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(PHOSPHORUS, NITROGEN, 4);
                mutation4.addMutationCondition(new MaterialMutationCondition(Titanium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(SULFUR, CARBON, 3);
                mutation5.addMutationCondition(new MaterialMutationCondition(Titanium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(CHLORINE, BORON, 3);
                mutation6.addMutationCondition(new MaterialMutationCondition(Titanium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(ARGON, BERYLLIUM, 2);
                mutation7.addMutationCondition(new MaterialMutationCondition(Titanium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(POTASSIUM, LITHIUM, 2);
                mutation8.addMutationCondition(new MaterialMutationCondition(Titanium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(CALCIUM, HELIUM, 1);
                mutation9.addMutationCondition(new MaterialMutationCondition(Titanium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(SCANDIUM, HYDROGEN, 1);
                mutation10.addMutationCondition(new MaterialMutationCondition(Titanium));
            }),
    VANADIUM(GJBranchDefinition.GJ_RAREMETAL, "Vanadia", false, 0x323232, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.VANADIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(MAGNESIUM, SODIUM, 5);
                mutation1.addMutationCondition(new MaterialMutationCondition(Vanadium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(ALUMINIUM, NEON, 5);
                mutation2.addMutationCondition(new MaterialMutationCondition(Vanadium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(SILICON, FLUORINE, 4);
                mutation3.addMutationCondition(new MaterialMutationCondition(Vanadium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(PHOSPHORUS, OXYGEN, 4);
                mutation4.addMutationCondition(new MaterialMutationCondition(Vanadium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(SULFUR, NITROGEN, 3);
                mutation5.addMutationCondition(new MaterialMutationCondition(Vanadium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(CHLORINE, CARBON, 3);
                mutation6.addMutationCondition(new MaterialMutationCondition(Vanadium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(ARGON, BORON, 2);
                mutation7.addMutationCondition(new MaterialMutationCondition(Vanadium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(POTASSIUM, BERYLLIUM, 2);
                mutation8.addMutationCondition(new MaterialMutationCondition(Vanadium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(CALCIUM, LITHIUM, 1);
                mutation9.addMutationCondition(new MaterialMutationCondition(Vanadium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(TITANIUM, HYDROGEN, 1);
                mutation10.addMutationCondition(new MaterialMutationCondition(Vanadium));
            }),
    CHROME(GJBranchDefinition.GJ_RAREMETAL, "Chroma", true, 0xEBA1EB, 0xF2C3F2,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.CHROME), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.MAGNESIUM), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(ALUMINIUM, SODIUM, 6);
                mutation1.addMutationCondition(new MaterialMutationCondition(Chrome));
                IBeeMutationBuilder mutation2 = dis.registerMutation(SILICON, NEON, 5);
                mutation2.addMutationCondition(new MaterialMutationCondition(Chrome));
                IBeeMutationBuilder mutation3 = dis.registerMutation(PHOSPHORUS, FLUORINE, 5);
                mutation3.addMutationCondition(new MaterialMutationCondition(Chrome));
                IBeeMutationBuilder mutation4 = dis.registerMutation(SULFUR, OXYGEN, 4);
                mutation4.addMutationCondition(new MaterialMutationCondition(Chrome));
                IBeeMutationBuilder mutation5 = dis.registerMutation(CHLORINE, NITROGEN, 4);
                mutation5.addMutationCondition(new MaterialMutationCondition(Chrome));
                IBeeMutationBuilder mutation6 = dis.registerMutation(ARGON, CARBON, 3);
                mutation6.addMutationCondition(new MaterialMutationCondition(Chrome));
                IBeeMutationBuilder mutation7 = dis.registerMutation(POTASSIUM, BORON, 3);
                mutation7.addMutationCondition(new MaterialMutationCondition(Chrome));
                IBeeMutationBuilder mutation8 = dis.registerMutation(CALCIUM, BERYLLIUM, 2);
                mutation8.addMutationCondition(new MaterialMutationCondition(Chrome));
                IBeeMutationBuilder mutation9 = dis.registerMutation(SCANDIUM, LITHIUM, 2);
                mutation9.addMutationCondition(new MaterialMutationCondition(Chrome));
                IBeeMutationBuilder mutation10 = dis.registerMutation(TITANIUM, HELIUM, 1);
                mutation10.addMutationCondition(new MaterialMutationCondition(Chrome));
                IBeeMutationBuilder mutation11 = dis.registerMutation(VANADIUM, HYDROGEN, 1);
                mutation11.addMutationCondition(new MaterialMutationCondition(Chrome));
            }),
    MANGANESE(GJBranchDefinition.GJ_RAREMETAL, "Manganum", true, 0xD5D5D5, 0xAAAAAA,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.MANGANESE), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.IRON), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(ALUMINIUM, MAGNESIUM, 6);
                mutation1.addMutationCondition(new MaterialMutationCondition(Manganese));
                IBeeMutationBuilder mutation2 = dis.registerMutation(SILICON, SODIUM, 6);
                mutation2.addMutationCondition(new MaterialMutationCondition(Manganese));
                IBeeMutationBuilder mutation3 = dis.registerMutation(PHOSPHORUS, NEON, 5);
                mutation3.addMutationCondition(new MaterialMutationCondition(Manganese));
                IBeeMutationBuilder mutation4 = dis.registerMutation(SULFUR, FLUORINE, 5);
                mutation4.addMutationCondition(new MaterialMutationCondition(Manganese));
                IBeeMutationBuilder mutation5 = dis.registerMutation(CHLORINE, OXYGEN, 4);
                mutation5.addMutationCondition(new MaterialMutationCondition(Manganese));
                IBeeMutationBuilder mutation6 = dis.registerMutation(ARGON, NITROGEN, 4);
                mutation6.addMutationCondition(new MaterialMutationCondition(Manganese));
                IBeeMutationBuilder mutation7 = dis.registerMutation(POTASSIUM, CARBON, 3);
                mutation7.addMutationCondition(new MaterialMutationCondition(Manganese));
                IBeeMutationBuilder mutation8 = dis.registerMutation(CALCIUM, BORON, 3);
                mutation8.addMutationCondition(new MaterialMutationCondition(Manganese));
                IBeeMutationBuilder mutation9 = dis.registerMutation(SCANDIUM, BERYLLIUM, 2);
                mutation9.addMutationCondition(new MaterialMutationCondition(Manganese));
                IBeeMutationBuilder mutation10 = dis.registerMutation(TITANIUM, LITHIUM, 2);
                mutation10.addMutationCondition(new MaterialMutationCondition(Manganese));
                IBeeMutationBuilder mutation11 = dis.registerMutation(VANADIUM, HELIUM, 1);
                mutation11.addMutationCondition(new MaterialMutationCondition(Manganese));
                IBeeMutationBuilder mutation12 = dis.registerMutation(CHROME, HYDROGEN, 1);
                mutation12.addMutationCondition(new MaterialMutationCondition(Manganese));
            }),
    IRON(GJBranchDefinition.GJ_METAL, "Ferrum", true, 0xDA9147, 0xDE9C59,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.IRON), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.TIN), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(SILICON, MAGNESIUM, 6);
                mutation1.addMutationCondition(new MaterialMutationCondition(Iron));
                IBeeMutationBuilder mutation2 = dis.registerMutation(PHOSPHORUS, SODIUM, 6);
                mutation2.addMutationCondition(new MaterialMutationCondition(Iron));
                IBeeMutationBuilder mutation3 = dis.registerMutation(SULFUR, NEON, 5);
                mutation3.addMutationCondition(new MaterialMutationCondition(Iron));
                IBeeMutationBuilder mutation4 = dis.registerMutation(CHLORINE, FLUORINE, 5);
                mutation4.addMutationCondition(new MaterialMutationCondition(Iron));
                IBeeMutationBuilder mutation5 = dis.registerMutation(ARGON, OXYGEN, 4);
                mutation5.addMutationCondition(new MaterialMutationCondition(Iron));
                IBeeMutationBuilder mutation6 = dis.registerMutation(POTASSIUM, NITROGEN, 4);
                mutation6.addMutationCondition(new MaterialMutationCondition(Iron));
                IBeeMutationBuilder mutation7 = dis.registerMutation(CALCIUM, CARBON, 3);
                mutation7.addMutationCondition(new MaterialMutationCondition(Iron));
                IBeeMutationBuilder mutation8 = dis.registerMutation(SCANDIUM, BORON, 3);
                mutation8.addMutationCondition(new MaterialMutationCondition(Iron));
                IBeeMutationBuilder mutation9 = dis.registerMutation(TITANIUM, BERYLLIUM, 2);
                mutation9.addMutationCondition(new MaterialMutationCondition(Iron));
                IBeeMutationBuilder mutation10 = dis.registerMutation(VANADIUM, LITHIUM, 2);
                mutation10.addMutationCondition(new MaterialMutationCondition(Iron));
                IBeeMutationBuilder mutation11 = dis.registerMutation(CHROME, HELIUM, 1);
                mutation11.addMutationCondition(new MaterialMutationCondition(Iron));
                IBeeMutationBuilder mutation12 = dis.registerMutation(MANGANESE, HYDROGEN, 1);
                mutation12.addMutationCondition(new MaterialMutationCondition(Iron));
            }),
    COBALT(GJBranchDefinition.GJ_RAREMETAL, "Cobalta", false, 0x5050FA, 0x443B44,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.COBALT), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(SILICON, ALUMINIUM, 6);
                mutation1.addMutationCondition(new MaterialMutationCondition(Cobalt));
                IBeeMutationBuilder mutation2 = dis.registerMutation(PHOSPHORUS, MAGNESIUM, 6);
                mutation2.addMutationCondition(new MaterialMutationCondition(Cobalt));
                IBeeMutationBuilder mutation3 = dis.registerMutation(SULFUR, SODIUM, 5);
                mutation3.addMutationCondition(new MaterialMutationCondition(Cobalt));
                IBeeMutationBuilder mutation4 = dis.registerMutation(CHLORINE, NEON, 5);
                mutation4.addMutationCondition(new MaterialMutationCondition(Cobalt));
                IBeeMutationBuilder mutation5 = dis.registerMutation(ARGON, FLUORINE, 4);
                mutation5.addMutationCondition(new MaterialMutationCondition(Cobalt));
                IBeeMutationBuilder mutation6 = dis.registerMutation(POTASSIUM, OXYGEN, 4);
                mutation6.addMutationCondition(new MaterialMutationCondition(Cobalt));
                IBeeMutationBuilder mutation7 = dis.registerMutation(CALCIUM, NITROGEN, 3);
                mutation7.addMutationCondition(new MaterialMutationCondition(Cobalt));
                IBeeMutationBuilder mutation8 = dis.registerMutation(TITANIUM, BORON, 3);
                mutation8.addMutationCondition(new MaterialMutationCondition(Cobalt));
                IBeeMutationBuilder mutation9 = dis.registerMutation(VANADIUM, BERYLLIUM, 2);
                mutation9.addMutationCondition(new MaterialMutationCondition(Cobalt));
                IBeeMutationBuilder mutation10 = dis.registerMutation(CHROME, LITHIUM, 2);
                mutation10.addMutationCondition(new MaterialMutationCondition(Cobalt));
                IBeeMutationBuilder mutation11 = dis.registerMutation(MANGANESE, HELIUM, 1);
                mutation11.addMutationCondition(new MaterialMutationCondition(Cobalt));
                IBeeMutationBuilder mutation12 = dis.registerMutation(IRON, HYDROGEN, 1);
                mutation12.addMutationCondition(new MaterialMutationCondition(Cobalt));
            }),
    NICKEL(GJBranchDefinition.GJ_METAL, "Nichelium", true, 0x8585AD, 0x8585AD,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.NICKEL), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.PLATINUM), 0.02f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(PHOSPHORUS, ALUMINIUM, 7);
                mutation1.addMutationCondition(new MaterialMutationCondition(Nickel));
                IBeeMutationBuilder mutation2 = dis.registerMutation(SULFUR, MAGNESIUM, 6);
                mutation2.addMutationCondition(new MaterialMutationCondition(Nickel));
                IBeeMutationBuilder mutation3 = dis.registerMutation(CHLORINE, SODIUM, 6);
                mutation3.addMutationCondition(new MaterialMutationCondition(Nickel));
                IBeeMutationBuilder mutation4 = dis.registerMutation(ARGON, NEON, 5);
                mutation4.addMutationCondition(new MaterialMutationCondition(Nickel));
                IBeeMutationBuilder mutation5 = dis.registerMutation(POTASSIUM, FLUORINE, 5);
                mutation5.addMutationCondition(new MaterialMutationCondition(Nickel));
                IBeeMutationBuilder mutation6 = dis.registerMutation(CALCIUM, OXYGEN, 4);
                mutation6.addMutationCondition(new MaterialMutationCondition(Nickel));
                IBeeMutationBuilder mutation7 = dis.registerMutation(SCANDIUM, NITROGEN, 4);
                mutation7.addMutationCondition(new MaterialMutationCondition(Nickel));
                IBeeMutationBuilder mutation8 = dis.registerMutation(TITANIUM, CARBON, 3);
                mutation8.addMutationCondition(new MaterialMutationCondition(Nickel));
                IBeeMutationBuilder mutation9 = dis.registerMutation(VANADIUM, BORON, 3);
                mutation9.addMutationCondition(new MaterialMutationCondition(Nickel));
                IBeeMutationBuilder mutation10 = dis.registerMutation(CHROME, BERYLLIUM, 2);
                mutation10.addMutationCondition(new MaterialMutationCondition(Nickel));
                IBeeMutationBuilder mutation11 = dis.registerMutation(MANGANESE, LITHIUM, 2);
                mutation11.addMutationCondition(new MaterialMutationCondition(Nickel));
                IBeeMutationBuilder mutation12 = dis.registerMutation(IRON, HELIUM, 1);
                mutation12.addMutationCondition(new MaterialMutationCondition(Nickel));
                IBeeMutationBuilder mutation13 = dis.registerMutation(COBALT, HYDROGEN, 1);
                mutation13.addMutationCondition(new MaterialMutationCondition(Nickel));
            }),
    COPPER(GJBranchDefinition.GJ_METAL, "Cuprum", true, 0xFF6600, 0xE65C00,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.COPPER), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.GOLD), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(PHOSPHORUS, SILICON, 7);
                mutation1.addMutationCondition(new MaterialMutationCondition(Copper));
                IBeeMutationBuilder mutation2 = dis.registerMutation(SULFUR, ALUMINIUM, 7);
                mutation2.addMutationCondition(new MaterialMutationCondition(Copper));
                IBeeMutationBuilder mutation3 = dis.registerMutation(CHLORINE, MAGNESIUM, 6);
                mutation3.addMutationCondition(new MaterialMutationCondition(Copper));
                IBeeMutationBuilder mutation4 = dis.registerMutation(ARGON, SODIUM, 6);
                mutation4.addMutationCondition(new MaterialMutationCondition(Copper));
                IBeeMutationBuilder mutation5 = dis.registerMutation(POTASSIUM, NEON, 5);
                mutation5.addMutationCondition(new MaterialMutationCondition(Copper));
                IBeeMutationBuilder mutation6 = dis.registerMutation(CALCIUM, FLUORINE, 5);
                mutation6.addMutationCondition(new MaterialMutationCondition(Copper));
                IBeeMutationBuilder mutation7 = dis.registerMutation(SCANDIUM, OXYGEN, 4);
                mutation7.addMutationCondition(new MaterialMutationCondition(Copper));
                IBeeMutationBuilder mutation8 = dis.registerMutation(TITANIUM, NITROGEN, 4);
                mutation8.addMutationCondition(new MaterialMutationCondition(Copper));
                IBeeMutationBuilder mutation9 = dis.registerMutation(VANADIUM, CARBON, 3);
                mutation9.addMutationCondition(new MaterialMutationCondition(Copper));
                IBeeMutationBuilder mutation10 = dis.registerMutation(CHROME, BORON, 3);
                mutation10.addMutationCondition(new MaterialMutationCondition(Copper));
                IBeeMutationBuilder mutation11 = dis.registerMutation(MANGANESE, BERYLLIUM, 2);
                mutation11.addMutationCondition(new MaterialMutationCondition(Copper));
                IBeeMutationBuilder mutation12 = dis.registerMutation(IRON, LITHIUM, 2);
                mutation12.addMutationCondition(new MaterialMutationCondition(Copper));
                IBeeMutationBuilder mutation13 = dis.registerMutation(COBALT, HELIUM, 1);
                mutation13.addMutationCondition(new MaterialMutationCondition(Copper));
                IBeeMutationBuilder mutation14 = dis.registerMutation(NICKEL, HYDROGEN, 1);
                mutation14.addMutationCondition(new MaterialMutationCondition(Copper));
            }),
    ZINC(GJBranchDefinition.GJ_METAL, "Cadmiae", true, 0xF0DEF0, 0xF2E1F2,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.ZINC), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.GALLIUM), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(SULFUR, SILICON, 7);
                mutation1.addMutationCondition(new MaterialMutationCondition(Zinc));
                IBeeMutationBuilder mutation2 = dis.registerMutation(CHLORINE, ALUMINIUM, 7);
                mutation2.addMutationCondition(new MaterialMutationCondition(Zinc));
                IBeeMutationBuilder mutation3 = dis.registerMutation(ARGON, MAGNESIUM, 6);
                mutation3.addMutationCondition(new MaterialMutationCondition(Zinc));
                IBeeMutationBuilder mutation4 = dis.registerMutation(POTASSIUM, SODIUM, 6);
                mutation4.addMutationCondition(new MaterialMutationCondition(Zinc));
                IBeeMutationBuilder mutation5 = dis.registerMutation(CALCIUM, NEON, 5);
                mutation5.addMutationCondition(new MaterialMutationCondition(Zinc));
                IBeeMutationBuilder mutation6 = dis.registerMutation(SCANDIUM, FLUORINE, 5);
                mutation6.addMutationCondition(new MaterialMutationCondition(Zinc));
                IBeeMutationBuilder mutation7 = dis.registerMutation(TITANIUM, OXYGEN, 4);
                mutation7.addMutationCondition(new MaterialMutationCondition(Zinc));
                IBeeMutationBuilder mutation8 = dis.registerMutation(VANADIUM, NITROGEN, 4);
                mutation8.addMutationCondition(new MaterialMutationCondition(Zinc));
                IBeeMutationBuilder mutation9 = dis.registerMutation(CHROME, CARBON, 3);
                mutation9.addMutationCondition(new MaterialMutationCondition(Zinc));
                IBeeMutationBuilder mutation10 = dis.registerMutation(MANGANESE, BORON, 3);
                mutation10.addMutationCondition(new MaterialMutationCondition(Zinc));
                IBeeMutationBuilder mutation11 = dis.registerMutation(IRON, BERYLLIUM, 2);
                mutation11.addMutationCondition(new MaterialMutationCondition(Zinc));
                IBeeMutationBuilder mutation12 = dis.registerMutation(COBALT, LITHIUM, 2);
                mutation12.addMutationCondition(new MaterialMutationCondition(Zinc));
                IBeeMutationBuilder mutation13 = dis.registerMutation(NICKEL, HELIUM, 1);
                mutation13.addMutationCondition(new MaterialMutationCondition(Zinc));
                IBeeMutationBuilder mutation14 = dis.registerMutation(COPPER, HYDROGEN, 1);
                mutation14.addMutationCondition(new MaterialMutationCondition(Zinc));
            }),
    GALLIUM(GJBranchDefinition.GJ_RAREMETAL, "Gallia", false, 0xDCDCFF, 0x006600,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.GALLIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(SULFUR, PHOSPHORUS, 7);
                mutation1.addMutationCondition(new MaterialMutationCondition(Gallium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(SILICON, CHLORINE, 6);
                mutation2.addMutationCondition(new MaterialMutationCondition(Gallium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(ALUMINIUM, ARGON, 6);
                mutation3.addMutationCondition(new MaterialMutationCondition(Gallium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(POTASSIUM, MAGNESIUM, 5);
                mutation4.addMutationCondition(new MaterialMutationCondition(Gallium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(CALCIUM, SODIUM, 5);
                mutation5.addMutationCondition(new MaterialMutationCondition(Gallium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(TITANIUM, FLUORINE, 4);
                mutation6.addMutationCondition(new MaterialMutationCondition(Gallium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(VANADIUM, OXYGEN, 4);
                mutation7.addMutationCondition(new MaterialMutationCondition(Gallium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(CHROME, NITROGEN, 3);
                mutation8.addMutationCondition(new MaterialMutationCondition(Gallium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(MANGANESE, CARBON, 3);
                mutation9.addMutationCondition(new MaterialMutationCondition(Gallium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(IRON, BORON, 2);
                mutation10.addMutationCondition(new MaterialMutationCondition(Gallium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(NICKEL, LITHIUM, 2);
                mutation11.addMutationCondition(new MaterialMutationCondition(Gallium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(COPPER, HELIUM, 1);
                mutation12.addMutationCondition(new MaterialMutationCondition(Gallium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(ZINC, HYDROGEN, 1);
                mutation13.addMutationCondition(new MaterialMutationCondition(Gallium));
            }),
    GERMANIUM(GJBranchDefinition.GJ_RAREMETAL, "Germania", false, 0x434343, 0x006600,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.GERMANIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(CHLORINE, PHOSPHORUS, 8);
                mutation1.addMutationCondition(new MaterialMutationCondition(Germanium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(ARGON, SILICON, 7);
                mutation2.addMutationCondition(new MaterialMutationCondition(Germanium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(POTASSIUM, ALUMINIUM, 7);
                mutation3.addMutationCondition(new MaterialMutationCondition(Germanium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(CALCIUM, MAGNESIUM, 6);
                mutation4.addMutationCondition(new MaterialMutationCondition(Germanium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(SCANDIUM, SODIUM, 6);
                mutation5.addMutationCondition(new MaterialMutationCondition(Germanium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(TITANIUM, NEON, 5);
                mutation6.addMutationCondition(new MaterialMutationCondition(Germanium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(VANADIUM, FLUORINE, 5);
                mutation7.addMutationCondition(new MaterialMutationCondition(Germanium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(CHROME, OXYGEN, 4);
                mutation8.addMutationCondition(new MaterialMutationCondition(Germanium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(MANGANESE, NITROGEN, 4);
                mutation9.addMutationCondition(new MaterialMutationCondition(Germanium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(IRON, CARBON, 3);
                mutation10.addMutationCondition(new MaterialMutationCondition(Germanium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(COBALT, BORON, 3);
                mutation11.addMutationCondition(new MaterialMutationCondition(Germanium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(NICKEL, BERYLLIUM, 2);
                mutation12.addMutationCondition(new MaterialMutationCondition(Germanium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(COPPER, LITHIUM, 2);
                mutation13.addMutationCondition(new MaterialMutationCondition(Germanium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(ZINC, HELIUM, 1);
                mutation14.addMutationCondition(new MaterialMutationCondition(Germanium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(GALLIUM, HYDROGEN, 1);
                mutation15.addMutationCondition(new MaterialMutationCondition(Germanium));
            }),
    ARSENIC(GJBranchDefinition.GJ_HALFMETAL, "Arsenicum", true, 0x736C52, 0x292412,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.ARSENIC), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.WARM);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(CHLORINE, SULFUR, 8);
                mutation1.addMutationCondition(new MaterialMutationCondition(Arsenic));
                IBeeMutationBuilder mutation2 = dis.registerMutation(ARGON, PHOSPHORUS, 8);
                mutation2.addMutationCondition(new MaterialMutationCondition(Arsenic));
                IBeeMutationBuilder mutation3 = dis.registerMutation(POTASSIUM, SILICON, 7);
                mutation3.addMutationCondition(new MaterialMutationCondition(Arsenic));
                IBeeMutationBuilder mutation4 = dis.registerMutation(CALCIUM, ALUMINIUM, 7);
                mutation4.addMutationCondition(new MaterialMutationCondition(Arsenic));
                IBeeMutationBuilder mutation5 = dis.registerMutation(SCANDIUM, MAGNESIUM, 6);
                mutation5.addMutationCondition(new MaterialMutationCondition(Arsenic));
                IBeeMutationBuilder mutation6 = dis.registerMutation(TITANIUM, SODIUM, 6);
                mutation6.addMutationCondition(new MaterialMutationCondition(Arsenic));
                IBeeMutationBuilder mutation7 = dis.registerMutation(VANADIUM, NEON, 5);
                mutation7.addMutationCondition(new MaterialMutationCondition(Arsenic));
                IBeeMutationBuilder mutation8 = dis.registerMutation(CHROME, FLUORINE, 5);
                mutation8.addMutationCondition(new MaterialMutationCondition(Arsenic));
                IBeeMutationBuilder mutation9 = dis.registerMutation(MANGANESE, OXYGEN, 4);
                mutation9.addMutationCondition(new MaterialMutationCondition(Arsenic));
                IBeeMutationBuilder mutation10 = dis.registerMutation(IRON, NITROGEN, 4);
                mutation10.addMutationCondition(new MaterialMutationCondition(Arsenic));
                IBeeMutationBuilder mutation11 = dis.registerMutation(COBALT, CARBON, 3);
                mutation11.addMutationCondition(new MaterialMutationCondition(Arsenic));
                IBeeMutationBuilder mutation12 = dis.registerMutation(NICKEL, BORON, 3);
                mutation12.addMutationCondition(new MaterialMutationCondition(Arsenic));
                IBeeMutationBuilder mutation13 = dis.registerMutation(COPPER, BERYLLIUM, 2);
                mutation13.addMutationCondition(new MaterialMutationCondition(Arsenic));
                IBeeMutationBuilder mutation14 = dis.registerMutation(ZINC, LITHIUM, 2);
                mutation14.addMutationCondition(new MaterialMutationCondition(Arsenic));
                IBeeMutationBuilder mutation15 = dis.registerMutation(GALLIUM, HELIUM, 1);
                mutation15.addMutationCondition(new MaterialMutationCondition(Arsenic));
                IBeeMutationBuilder mutation16 = dis.registerMutation(GERMANIUM, HYDROGEN, 1);
                mutation16.addMutationCondition(new MaterialMutationCondition(Arsenic));
            }),
    SELENIUM(GJBranchDefinition.GJ_RAREMETAL, "Selenia", false, 0xB6BA6B, 0x006600,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.SELENIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(ARGON, SULFUR, 8);
                mutation1.addMutationCondition(new MaterialMutationCondition(Selenium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(POTASSIUM, PHOSPHORUS, 8);
                mutation2.addMutationCondition(new MaterialMutationCondition(Selenium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(CALCIUM, SILICON, 7);
                mutation3.addMutationCondition(new MaterialMutationCondition(Selenium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(SCANDIUM, ALUMINIUM, 7);
                mutation4.addMutationCondition(new MaterialMutationCondition(Selenium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(TITANIUM, MAGNESIUM, 6);
                mutation5.addMutationCondition(new MaterialMutationCondition(Selenium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(VANADIUM, SODIUM, 6);
                mutation6.addMutationCondition(new MaterialMutationCondition(Selenium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(CHROME, NEON, 5);
                mutation7.addMutationCondition(new MaterialMutationCondition(Selenium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(MANGANESE, FLUORINE, 5);
                mutation8.addMutationCondition(new MaterialMutationCondition(Selenium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(IRON, OXYGEN, 4);
                mutation9.addMutationCondition(new MaterialMutationCondition(Selenium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(COBALT, NITROGEN, 4);
                mutation10.addMutationCondition(new MaterialMutationCondition(Selenium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(NICKEL, CARBON, 3);
                mutation11.addMutationCondition(new MaterialMutationCondition(Selenium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(COPPER, BORON, 3);
                mutation12.addMutationCondition(new MaterialMutationCondition(Selenium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(ZINC, BERYLLIUM, 2);
                mutation13.addMutationCondition(new MaterialMutationCondition(Selenium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(GALLIUM, LITHIUM, 2);
                mutation14.addMutationCondition(new MaterialMutationCondition(Selenium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(GERMANIUM, HELIUM, 1);
                mutation15.addMutationCondition(new MaterialMutationCondition(Selenium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(ARSENIC, HYDROGEN, 1);
                mutation16.addMutationCondition(new MaterialMutationCondition(Selenium));
            }),
    BROMINE(GJBranchDefinition.GJ_HALOGENS, "Bromos", false, 0x500A0A, 0x006600,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.BROMINE), 0.10f);
            }, template -> {},
            dis -> {
                dis.registerMutation(ARGON, CHLORINE, 9);
                dis.registerMutation(POTASSIUM, SULFUR, 8);
                dis.registerMutation(CALCIUM, PHOSPHORUS, 8);
                dis.registerMutation(SCANDIUM, SILICON, 7);
                dis.registerMutation(TITANIUM, ALUMINIUM, 7);
                dis.registerMutation(VANADIUM, MAGNESIUM, 6);
                dis.registerMutation(CHROME, SODIUM, 6);
                dis.registerMutation(MANGANESE, NEON, 5);
                dis.registerMutation(IRON, FLUORINE, 5);
                dis.registerMutation(COBALT, OXYGEN, 4);
                dis.registerMutation(NICKEL, NITROGEN, 4);
                dis.registerMutation(COPPER, CARBON, 3);
                dis.registerMutation(ZINC, BORON, 3);
                dis.registerMutation(GALLIUM, BERYLLIUM, 2);
                dis.registerMutation(GERMANIUM, LITHIUM, 2);
                dis.registerMutation(ARSENIC, HELIUM, 1);
                dis.registerMutation(SELENIUM, HYDROGEN, 1);
            }),
    KRYPTON(GJBranchDefinition.GJ_NOBLEGAS, "Kryptos", false, 0x8A97B0, 0x160822,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.KRYPTON), 0.35f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.ICY);
                beeSpecies.setNocturnal();
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.SHORTEST),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(POTASSIUM, CHLORINE, 9);
                mutation1.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation2 = dis.registerMutation(CALCIUM, SULFUR, 8);
                mutation2.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation3 = dis.registerMutation(SCANDIUM, PHOSPHORUS, 8);
                mutation3.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation4 = dis.registerMutation(TITANIUM, SILICON, 7);
                mutation4.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation5 = dis.registerMutation(VANADIUM, ALUMINIUM, 7);
                mutation5.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation6 = dis.registerMutation(CHROME, MAGNESIUM, 6);
                mutation6.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation7 = dis.registerMutation(MANGANESE, SODIUM, 6);
                mutation7.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation8 = dis.registerMutation(IRON, NEON, 5);
                mutation8.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation9 = dis.registerMutation(COBALT, FLUORINE, 5);
                mutation9.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation10 = dis.registerMutation(NICKEL, OXYGEN, 4);
                mutation10.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation11 = dis.registerMutation(COPPER, NITROGEN, 4);
                mutation11.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation12 = dis.registerMutation(ZINC, CARBON, 3);
                mutation12.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation13 = dis.registerMutation(GALLIUM, BORON, 3);
                mutation13.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation14 = dis.registerMutation(GERMANIUM, BERYLLIUM, 2);
                mutation14.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation15 = dis.registerMutation(ARSENIC, LITHIUM, 2);
                mutation15.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation16 = dis.registerMutation(SELENIUM, HELIUM, 1);
                mutation16.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation17 = dis.registerMutation(BROMINE, HYDROGEN, 1);
                mutation17.restrictTemperature(EnumTemperature.ICY);
            }),
    RUBIDIUM(GJBranchDefinition.GJ_ALKALINEMETAL, "Rubidia", false, 0xF01E1E, 0x006600,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.RUBIDIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(POTASSIUM, ARGON, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Rubidium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(CALCIUM, CHLORINE, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Rubidium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(SCANDIUM, SULFUR, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Rubidium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(TITANIUM, PHOSPHORUS, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Rubidium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(VANADIUM, SILICON, 7);
                mutation5.addMutationCondition(new MaterialMutationCondition(Rubidium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(CHROME, ALUMINIUM, 7);
                mutation6.addMutationCondition(new MaterialMutationCondition(Rubidium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(MANGANESE, MAGNESIUM, 6);
                mutation7.addMutationCondition(new MaterialMutationCondition(Rubidium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(IRON, SODIUM, 6);
                mutation8.addMutationCondition(new MaterialMutationCondition(Rubidium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(COBALT, NEON, 5);
                mutation9.addMutationCondition(new MaterialMutationCondition(Rubidium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(NICKEL, FLUORINE, 5);
                mutation10.addMutationCondition(new MaterialMutationCondition(Rubidium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(COPPER, OXYGEN, 4);
                mutation11.addMutationCondition(new MaterialMutationCondition(Rubidium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(ZINC, NITROGEN, 4);
                mutation12.addMutationCondition(new MaterialMutationCondition(Rubidium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(GALLIUM, CARBON, 3);
                mutation13.addMutationCondition(new MaterialMutationCondition(Rubidium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(GERMANIUM, BORON, 3);
                mutation14.addMutationCondition(new MaterialMutationCondition(Rubidium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(ARSENIC, BERYLLIUM, 2);
                mutation15.addMutationCondition(new MaterialMutationCondition(Rubidium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(SELENIUM, LITHIUM, 2);
                mutation16.addMutationCondition(new MaterialMutationCondition(Rubidium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(BROMINE, HELIUM, 1);
                mutation17.addMutationCondition(new MaterialMutationCondition(Rubidium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(KRYPTON, HYDROGEN, 1);
                mutation18.addMutationCondition(new MaterialMutationCondition(Rubidium));
            }),
    STRONTIUM(GJBranchDefinition.GJ_ALKALINEMETAL, "Strontia", false, 0xC8C8C8, 0x006600,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.STRONTIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(CALCIUM, ARGON, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Strontium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(SCANDIUM, CHLORINE, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Strontium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(TITANIUM, SULFUR, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Strontium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(VANADIUM, PHOSPHORUS, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Strontium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(CHROME, SILICON, 7);
                mutation5.addMutationCondition(new MaterialMutationCondition(Strontium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(MANGANESE, ALUMINIUM, 7);
                mutation6.addMutationCondition(new MaterialMutationCondition(Strontium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(IRON, MAGNESIUM, 6);
                mutation7.addMutationCondition(new MaterialMutationCondition(Strontium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(COBALT, SODIUM, 6);
                mutation8.addMutationCondition(new MaterialMutationCondition(Strontium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(NICKEL, NEON, 5);
                mutation9.addMutationCondition(new MaterialMutationCondition(Strontium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(COPPER, FLUORINE, 5);
                mutation10.addMutationCondition(new MaterialMutationCondition(Strontium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(ZINC, OXYGEN, 4);
                mutation11.addMutationCondition(new MaterialMutationCondition(Strontium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(GALLIUM, NITROGEN, 4);
                mutation12.addMutationCondition(new MaterialMutationCondition(Strontium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(GERMANIUM, CARBON, 3);
                mutation13.addMutationCondition(new MaterialMutationCondition(Strontium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(ARSENIC, BORON, 3);
                mutation14.addMutationCondition(new MaterialMutationCondition(Strontium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(SELENIUM, BERYLLIUM, 2);
                mutation15.addMutationCondition(new MaterialMutationCondition(Strontium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(BROMINE, LITHIUM, 2);
                mutation16.addMutationCondition(new MaterialMutationCondition(Strontium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(KRYPTON, HELIUM, 1);
                mutation17.addMutationCondition(new MaterialMutationCondition(Strontium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(RUBIDIUM, HYDROGEN, 1);
                mutation18.addMutationCondition(new MaterialMutationCondition(Strontium));
            }),
    YTTRIUM(GJBranchDefinition.GJ_RAREMETAL, "Yttria", false, 0x76524C, 0x006600,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.YTTRIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(CALCIUM, POTASSIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Yttrium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(SCANDIUM, ARGON, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Yttrium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(TITANIUM, CHLORINE, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Yttrium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(VANADIUM, SULFUR, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Yttrium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(CHROME, PHOSPHORUS, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Yttrium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(MANGANESE, SILICON, 7);
                mutation6.addMutationCondition(new MaterialMutationCondition(Yttrium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(IRON, ALUMINIUM, 7);
                mutation7.addMutationCondition(new MaterialMutationCondition(Yttrium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(COBALT, MAGNESIUM, 6);
                mutation8.addMutationCondition(new MaterialMutationCondition(Yttrium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(NICKEL, SODIUM, 6);
                mutation9.addMutationCondition(new MaterialMutationCondition(Yttrium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(COPPER, NEON, 5);
                mutation10.addMutationCondition(new MaterialMutationCondition(Yttrium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(ZINC, FLUORINE, 5);
                mutation11.addMutationCondition(new MaterialMutationCondition(Yttrium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(GALLIUM, OXYGEN, 4);
                mutation12.addMutationCondition(new MaterialMutationCondition(Yttrium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(GERMANIUM, NITROGEN, 4);
                mutation13.addMutationCondition(new MaterialMutationCondition(Yttrium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(ARSENIC, CARBON, 3);
                mutation14.addMutationCondition(new MaterialMutationCondition(Yttrium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(SELENIUM, BORON, 3);
                mutation15.addMutationCondition(new MaterialMutationCondition(Yttrium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(BROMINE, BERYLLIUM, 2);
                mutation16.addMutationCondition(new MaterialMutationCondition(Yttrium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(KRYPTON, LITHIUM, 2);
                mutation17.addMutationCondition(new MaterialMutationCondition(Yttrium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(RUBIDIUM, HELIUM, 1);
                mutation18.addMutationCondition(new MaterialMutationCondition(Yttrium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(STRONTIUM, HYDROGEN, 1);
                mutation19.addMutationCondition(new MaterialMutationCondition(Yttrium));
            }),
    ZIRCONIUM(GJBranchDefinition.GJ_METAL, "Zirconia", false, 0xC8FFFF, 0x006600,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.ZIRCONIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(SCANDIUM, POTASSIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Zirconium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(TITANIUM, ARGON, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Zirconium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(VANADIUM, CHLORINE, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Zirconium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(CHROME, SULFUR, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Zirconium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(MANGANESE, PHOSPHORUS, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Zirconium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(IRON, SILICON, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Zirconium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(COBALT, ALUMINIUM, 7);
                mutation7.addMutationCondition(new MaterialMutationCondition(Zirconium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(NICKEL, MAGNESIUM, 7);
                mutation8.addMutationCondition(new MaterialMutationCondition(Zirconium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(COPPER, SODIUM, 6);
                mutation9.addMutationCondition(new MaterialMutationCondition(Zirconium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(ZINC, NEON, 6);
                mutation10.addMutationCondition(new MaterialMutationCondition(Zirconium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(GALLIUM, FLUORINE, 5);
                mutation11.addMutationCondition(new MaterialMutationCondition(Zirconium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(GERMANIUM, OXYGEN, 5);
                mutation12.addMutationCondition(new MaterialMutationCondition(Zirconium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(ARSENIC, NITROGEN, 4);
                mutation13.addMutationCondition(new MaterialMutationCondition(Zirconium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(SELENIUM, CARBON, 4);
                mutation14.addMutationCondition(new MaterialMutationCondition(Zirconium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(BROMINE, BORON, 3);
                mutation15.addMutationCondition(new MaterialMutationCondition(Zirconium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(KRYPTON, BERYLLIUM, 3);
                mutation16.addMutationCondition(new MaterialMutationCondition(Zirconium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(RUBIDIUM, LITHIUM, 2);
                mutation17.addMutationCondition(new MaterialMutationCondition(Zirconium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(STRONTIUM, HELIUM, 2);
                mutation18.addMutationCondition(new MaterialMutationCondition(Zirconium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(STRONTIUM, HYDROGEN, 1);
                mutation19.addMutationCondition(new MaterialMutationCondition(Zirconium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(YTTRIUM, HYDROGEN, 1);
                mutation20.addMutationCondition(new MaterialMutationCondition(Zirconium));
            }),
    NIOBIUM(GJBranchDefinition.GJ_RAREMETAL, "Niobia", false, 0xBEB4C8, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.NIOBIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(SCANDIUM, CALCIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Niobium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(TITANIUM, POTASSIUM, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Niobium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(VANADIUM, ARGON, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Niobium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(CHROME, CHLORINE, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Niobium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(MANGANESE, SULFUR, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Niobium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(IRON, PHOSPHORUS, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Niobium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(COBALT, SILICON, 7);
                mutation7.addMutationCondition(new MaterialMutationCondition(Niobium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(NICKEL, ALUMINIUM, 7);
                mutation8.addMutationCondition(new MaterialMutationCondition(Niobium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(COPPER, MAGNESIUM, 6);
                mutation9.addMutationCondition(new MaterialMutationCondition(Niobium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(ZINC, SODIUM, 6);
                mutation10.addMutationCondition(new MaterialMutationCondition(Niobium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(GALLIUM, NEON, 5);
                mutation11.addMutationCondition(new MaterialMutationCondition(Niobium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(GERMANIUM, FLUORINE, 5);
                mutation12.addMutationCondition(new MaterialMutationCondition(Niobium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(ARSENIC, OXYGEN, 4);
                mutation13.addMutationCondition(new MaterialMutationCondition(Niobium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(SELENIUM, NITROGEN, 4);
                mutation14.addMutationCondition(new MaterialMutationCondition(Niobium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(BROMINE, CARBON, 3);
                mutation15.addMutationCondition(new MaterialMutationCondition(Niobium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(KRYPTON, BORON, 3);
                mutation16.addMutationCondition(new MaterialMutationCondition(Niobium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(RUBIDIUM, BERYLLIUM, 2);
                mutation17.addMutationCondition(new MaterialMutationCondition(Niobium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(STRONTIUM, LITHIUM, 2);
                mutation18.addMutationCondition(new MaterialMutationCondition(Niobium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(YTTRIUM, HELIUM, 1);
                mutation19.addMutationCondition(new MaterialMutationCondition(Niobium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(ZIRCONIUM, HYDROGEN, 1);
                mutation20.addMutationCondition(new MaterialMutationCondition(Niobium));
            }),
    MOLYBDENUM(GJBranchDefinition.GJ_RAREMETAL, "Molybdena", false, 0xB4B4DC, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.MOLYBDENUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(TITANIUM, CALCIUM, 8);
                mutation1.addMutationCondition(new MaterialMutationCondition(Molybdenum));
                IBeeMutationBuilder mutation2 = dis.registerMutation(VANADIUM, POTASSIUM, 7);
                mutation2.addMutationCondition(new MaterialMutationCondition(Molybdenum));
                IBeeMutationBuilder mutation3 = dis.registerMutation(CHROME, ARGON, 7);
                mutation3.addMutationCondition(new MaterialMutationCondition(Molybdenum));
                IBeeMutationBuilder mutation4 = dis.registerMutation(MANGANESE, CHLORINE, 6);
                mutation4.addMutationCondition(new MaterialMutationCondition(Molybdenum));
                IBeeMutationBuilder mutation5 = dis.registerMutation(IRON, SULFUR, 6);
                mutation5.addMutationCondition(new MaterialMutationCondition(Molybdenum));
                IBeeMutationBuilder mutation6 = dis.registerMutation(COBALT, PHOSPHORUS, 5);
                mutation6.addMutationCondition(new MaterialMutationCondition(Molybdenum));
                IBeeMutationBuilder mutation7 = dis.registerMutation(NICKEL, SILICON, 5);
                mutation7.addMutationCondition(new MaterialMutationCondition(Molybdenum));
                IBeeMutationBuilder mutation8 = dis.registerMutation(COPPER, ALUMINIUM, 4);
                mutation8.addMutationCondition(new MaterialMutationCondition(Molybdenum));
                IBeeMutationBuilder mutation9 = dis.registerMutation(ZINC, MAGNESIUM, 4);
                mutation9.addMutationCondition(new MaterialMutationCondition(Molybdenum));
                IBeeMutationBuilder mutation10 = dis.registerMutation(GALLIUM, SODIUM, 3);
                mutation10.addMutationCondition(new MaterialMutationCondition(Molybdenum));
                IBeeMutationBuilder mutation11 = dis.registerMutation(GERMANIUM, NEON, 3);
                mutation11.addMutationCondition(new MaterialMutationCondition(Molybdenum));
                IBeeMutationBuilder mutation12 = dis.registerMutation(ARSENIC, FLUORINE, 3);
                mutation12.addMutationCondition(new MaterialMutationCondition(Molybdenum));
                IBeeMutationBuilder mutation13 = dis.registerMutation(SELENIUM, OXYGEN, 3);
                mutation13.addMutationCondition(new MaterialMutationCondition(Molybdenum));
                IBeeMutationBuilder mutation14 = dis.registerMutation(BROMINE, NITROGEN, 2);
                mutation14.addMutationCondition(new MaterialMutationCondition(Molybdenum));
                IBeeMutationBuilder mutation15 = dis.registerMutation(KRYPTON, CARBON, 2);
                mutation15.addMutationCondition(new MaterialMutationCondition(Molybdenum));
                IBeeMutationBuilder mutation16 = dis.registerMutation(RUBIDIUM, BORON, 3);
                mutation16.addMutationCondition(new MaterialMutationCondition(Molybdenum));
                IBeeMutationBuilder mutation17 = dis.registerMutation(STRONTIUM, BERYLLIUM, 3);
                mutation17.addMutationCondition(new MaterialMutationCondition(Molybdenum));
                IBeeMutationBuilder mutation18 = dis.registerMutation(YTTRIUM, LITHIUM, 1);
                mutation18.addMutationCondition(new MaterialMutationCondition(Molybdenum));
                IBeeMutationBuilder mutation19 = dis.registerMutation(ZIRCONIUM, HELIUM, 3);
                mutation19.addMutationCondition(new MaterialMutationCondition(Molybdenum));
                IBeeMutationBuilder mutation20 = dis.registerMutation(NIOBIUM, HYDROGEN, 1);
                mutation20.addMutationCondition(new MaterialMutationCondition(Molybdenum));
            }),
    TECHNETIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Technetia", false, 0x545455, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.TECHNETIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(TITANIUM, SCANDIUM, 7);
                mutation1.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(VANADIUM, CALCIUM, 7);
                mutation2.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(CHROME, POTASSIUM, 7);
                mutation3.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(MANGANESE, ARGON, 6);
                mutation4.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(IRON, CHLORINE, 6);
                mutation5.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(COBALT, SULFUR, 6);
                mutation6.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(NICKEL, PHOSPHORUS, 5);
                mutation7.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(COPPER, SILICON, 5);
                mutation8.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(ZINC, ALUMINIUM, 5);
                mutation9.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(GALLIUM, MAGNESIUM, 4);
                mutation10.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(GERMANIUM, SODIUM, 4);
                mutation11.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(ARSENIC, NEON, 4);
                mutation12.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(SELENIUM, FLUORINE, 3);
                mutation13.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(BROMINE, OXYGEN, 3);
                mutation14.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(KRYPTON, NITROGEN, 3);
                mutation15.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(RUBIDIUM, CARBON, 2);
                mutation16.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(STRONTIUM, BORON, 2);
                mutation17.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(YTTRIUM, BERYLLIUM, 2);
                mutation18.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(ZIRCONIUM, LITHIUM, 1);
                mutation19.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(NIOBIUM, HELIUM, 1);
                mutation20.addMutationCondition(new MaterialMutationCondition(Technetium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(MOLYBDENUM, HYDROGEN, 1);
                mutation21.addMutationCondition(new MaterialMutationCondition(Technetium));
            }),
    RUTHENIUM(GJBranchDefinition.GJ_RAREMETAL, "Ruthenia", false, 0x50ACCD, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.RUTHENIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(VANADIUM, SCANDIUM, 7);
                mutation1.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(CHROME, CALCIUM, 7);
                mutation2.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(MANGANESE, POTASSIUM, 7);
                mutation3.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(IRON, ARGON, 6);
                mutation4.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(COBALT, CHLORINE, 6);
                mutation5.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(NICKEL, SULFUR, 6);
                mutation6.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(COPPER, PHOSPHORUS, 5);
                mutation7.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(ZINC, SILICON, 5);
                mutation8.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(GALLIUM, ALUMINIUM, 5);
                mutation9.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(GERMANIUM, MAGNESIUM, 4);
                mutation10.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(ARSENIC, SODIUM, 4);
                mutation11.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(SELENIUM, NEON, 4);
                mutation12.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(BROMINE, FLUORINE, 3);
                mutation13.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(KRYPTON, OXYGEN, 3);
                mutation14.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(RUBIDIUM, NITROGEN, 3);
                mutation15.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(STRONTIUM, CARBON, 2);
                mutation16.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(YTTRIUM, BORON, 2);
                mutation17.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(ZIRCONIUM, BERYLLIUM, 2);
                mutation18.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(NIOBIUM, LITHIUM, 1);
                mutation19.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(MOLYBDENUM, HELIUM, 1);
                mutation20.addMutationCondition(new MaterialMutationCondition(Ruthenium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(TECHNETIUM, HYDROGEN, 1);
                mutation21.addMutationCondition(new MaterialMutationCondition(Ruthenium));
            }),
    RHODIUM(GJBranchDefinition.GJ_RAREMETAL, "Rhodia", false, 0xDC0C58, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.RHODIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(VANADIUM, TITANIUM, 8);
                mutation1.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(CHROME, SCANDIUM, 7);
                mutation2.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(MANGANESE, CALCIUM, 7);
                mutation3.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(IRON, POTASSIUM, 7);
                mutation4.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(COBALT, ARGON, 6);
                mutation5.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(NICKEL, CHLORINE, 6);
                mutation6.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(COPPER, SULFUR, 6);
                mutation7.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(ZINC, PHOSPHORUS, 5);
                mutation8.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(GALLIUM, SILICON, 5);
                mutation9.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(GERMANIUM, ALUMINIUM, 5);
                mutation10.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(ARSENIC, MAGNESIUM, 4);
                mutation11.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(SELENIUM, SODIUM, 4);
                mutation12.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(BROMINE, NEON, 4);
                mutation13.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(KRYPTON, FLUORINE, 3);
                mutation14.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(RUBIDIUM, OXYGEN, 3);
                mutation15.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(STRONTIUM, NITROGEN, 3);
                mutation16.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(YTTRIUM, CARBON, 2);
                mutation17.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(ZIRCONIUM, BORON, 2);
                mutation18.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(NIOBIUM, BERYLLIUM, 2);
                mutation19.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(MOLYBDENUM, LITHIUM, 1);
                mutation20.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(TECHNETIUM, HELIUM, 1);
                mutation21.addMutationCondition(new MaterialMutationCondition(Rhodium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(RUTHENIUM, HYDROGEN, 1);
                mutation22.addMutationCondition(new MaterialMutationCondition(Rhodium));
            }),
    PALLADIUM(GJBranchDefinition.GJ_RAREMETAL, "Palladia", false, 0x808080, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.PALLADIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(CHROME, TITANIUM, 8);
                mutation1.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(MANGANESE, SCANDIUM, 7);
                mutation2.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(IRON, CALCIUM, 7);
                mutation3.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(COBALT, POTASSIUM, 7);
                mutation4.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(NICKEL, ARGON, 6);
                mutation5.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(COPPER, CHLORINE, 6);
                mutation6.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(ZINC, SULFUR, 6);
                mutation7.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(GALLIUM, PHOSPHORUS, 5);
                mutation8.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(GERMANIUM, SILICON, 5);
                mutation9.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(ARSENIC, ALUMINIUM, 5);
                mutation10.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(SELENIUM, MAGNESIUM, 4);
                mutation11.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(BROMINE, SODIUM, 4);
                mutation12.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(KRYPTON, NEON, 4);
                mutation13.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(RUBIDIUM, FLUORINE, 3);
                mutation14.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(STRONTIUM, OXYGEN, 3);
                mutation15.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(YTTRIUM, NITROGEN, 3);
                mutation16.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(ZIRCONIUM, CARBON, 2);
                mutation17.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(NIOBIUM, BORON, 2);
                mutation18.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(MOLYBDENUM, BERYLLIUM, 2);
                mutation19.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(TECHNETIUM, LITHIUM, 1);
                mutation20.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(RUTHENIUM, HELIUM, 1);
                mutation21.addMutationCondition(new MaterialMutationCondition(Palladium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(RHODIUM, HYDROGEN, 1);
                mutation22.addMutationCondition(new MaterialMutationCondition(Palladium));
            }),
    SILVER(GJBranchDefinition.GJ_METAL, "Argenti", true, 0xC2C2D6, 0xCECEDE,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SILVER), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.SULFUR), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.COLD);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(CHROME, VANADIUM, 8);
                mutation1.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation2 = dis.registerMutation(MANGANESE, TITANIUM, 8);
                mutation2.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation3 = dis.registerMutation(IRON, SCANDIUM, 7);
                mutation3.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation4 = dis.registerMutation(COBALT, CALCIUM, 7);
                mutation4.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation5 = dis.registerMutation(NICKEL, POTASSIUM, 7);
                mutation5.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation6 = dis.registerMutation(COPPER, ARGON, 6);
                mutation6.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation7 = dis.registerMutation(ZINC, CHLORINE, 6);
                mutation7.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation8 = dis.registerMutation(GALLIUM, SULFUR, 6);
                mutation8.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation9 = dis.registerMutation(GERMANIUM, PHOSPHORUS, 5);
                mutation9.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation10 = dis.registerMutation(ARSENIC, SILICON, 5);
                mutation10.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation11 = dis.registerMutation(SELENIUM, ALUMINIUM, 5);
                mutation11.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation12 = dis.registerMutation(BROMINE, MAGNESIUM, 4);
                mutation12.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation13 = dis.registerMutation(KRYPTON, SODIUM, 4);
                mutation13.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation14 = dis.registerMutation(RUBIDIUM, NEON, 4);
                mutation14.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation15 = dis.registerMutation(STRONTIUM, FLUORINE, 3);
                mutation15.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation16 = dis.registerMutation(YTTRIUM, OXYGEN, 3);
                mutation16.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation17 = dis.registerMutation(ZIRCONIUM, NITROGEN, 3);
                mutation17.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation18 = dis.registerMutation(NIOBIUM, CARBON, 2);
                mutation18.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation19 = dis.registerMutation(MOLYBDENUM, BORON, 2);
                mutation19.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation20 = dis.registerMutation(TECHNETIUM, BERYLLIUM, 2);
                mutation20.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation21 = dis.registerMutation(RUTHENIUM, LITHIUM, 1);
                mutation21.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation22 = dis.registerMutation(RHODIUM, HELIUM, 1);
                mutation22.addMutationCondition(new MaterialMutationCondition(Silver));
                IBeeMutationBuilder mutation23 = dis.registerMutation(PALLADIUM, HYDROGEN, 1);
                mutation23.addMutationCondition(new MaterialMutationCondition(Silver));
            }),
    CADMIUM(GJBranchDefinition.GJ_RAREMETAL, "Cadmia", false, 0x32323C, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.CADMIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(MANGANESE, VANADIUM, 8);
                mutation1.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(IRON, TITANIUM, 8);
                mutation2.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(COBALT, SCANDIUM, 7);
                mutation3.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(NICKEL, CALCIUM, 7);
                mutation4.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(COPPER, POTASSIUM, 7);
                mutation5.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(ZINC, ARGON, 6);
                mutation6.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(GALLIUM, CHLORINE, 6);
                mutation7.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(GERMANIUM, SULFUR, 6);
                mutation8.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(ARSENIC, PHOSPHORUS, 5);
                mutation9.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(SELENIUM, SILICON, 5);
                mutation10.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(BROMINE, ALUMINIUM, 5);
                mutation11.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(KRYPTON, MAGNESIUM, 4);
                mutation12.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(RUBIDIUM, SODIUM, 4);
                mutation13.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(STRONTIUM, NEON, 4);
                mutation14.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(YTTRIUM, FLUORINE, 3);
                mutation15.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(ZIRCONIUM, OXYGEN, 3);
                mutation16.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(NIOBIUM, NITROGEN, 3);
                mutation17.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(MOLYBDENUM, CARBON, 2);
                mutation18.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(TECHNETIUM, BORON, 2);
                mutation19.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(RUTHENIUM, BERYLLIUM, 2);
                mutation20.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(RHODIUM, LITHIUM, 1);
                mutation21.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(PALLADIUM, HELIUM, 1);
                mutation22.addMutationCondition(new MaterialMutationCondition(Cadmium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(SILVER, HYDROGEN, 1);
                mutation23.addMutationCondition(new MaterialMutationCondition(Cadmium));
            }),
    INDIUM(GJBranchDefinition.GJ_RAREMETAL, "Indicium", false, 0xFFA9FF, 0x8F5D99,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.INDIUM), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.HOT);
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWEST),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(MANGANESE, CHROME, 8);
                mutation1.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(IRON, VANADIUM, 8);
                mutation2.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(COBALT, TITANIUM, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(NICKEL, SCANDIUM, 7);
                mutation4.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(COPPER, CALCIUM, 7);
                mutation5.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(ZINC, POTASSIUM, 7);
                mutation6.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(GALLIUM, ARGON, 6);
                mutation7.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(GERMANIUM, CHLORINE, 6);
                mutation8.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(ARSENIC, SULFUR, 6);
                mutation9.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(SELENIUM, PHOSPHORUS, 5);
                mutation10.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(BROMINE, SILICON, 5);
                mutation11.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(KRYPTON, ALUMINIUM, 5);
                mutation12.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(RUBIDIUM, MAGNESIUM, 4);
                mutation13.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(STRONTIUM, SODIUM, 4);
                mutation14.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(YTTRIUM, NEON, 4);
                mutation15.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(ZIRCONIUM, FLUORINE, 3);
                mutation16.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(NIOBIUM, OXYGEN, 3);
                mutation17.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(MOLYBDENUM, NITROGEN, 3);
                mutation18.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(TECHNETIUM, CARBON, 2);
                mutation19.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(RUTHENIUM, BORON, 2);
                mutation20.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(RHODIUM, BERYLLIUM, 2);
                mutation21.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(PALLADIUM, LITHIUM, 1);
                mutation22.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(SILVER, HELIUM, 1);
                mutation23.addMutationCondition(new MaterialMutationCondition(Indium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(CADMIUM, HYDROGEN, 1);
                mutation24.addMutationCondition(new MaterialMutationCondition(Indium));
            }),
    TIN(GJBranchDefinition.GJ_METAL, "Stannum", true, 0xD4D4D4, 0xDDDDDD,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.TIN), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.ZINC), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(IRON, CHROME, 8);
                mutation1.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation2 = dis.registerMutation(COBALT, VANADIUM, 8);
                mutation2.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation3 = dis.registerMutation(NICKEL, TITANIUM, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation4 = dis.registerMutation(COPPER, SCANDIUM, 7);
                mutation4.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation5 = dis.registerMutation(ZINC, CALCIUM, 7);
                mutation5.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation6 = dis.registerMutation(GALLIUM, POTASSIUM, 7);
                mutation6.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation7 = dis.registerMutation(GERMANIUM, ARGON, 6);
                mutation7.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation8 = dis.registerMutation(ARSENIC, CHLORINE, 6);
                mutation8.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation9 = dis.registerMutation(SELENIUM, SULFUR, 6);
                mutation9.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation10 = dis.registerMutation(BROMINE, PHOSPHORUS, 5);
                mutation10.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation11 = dis.registerMutation(KRYPTON, SILICON, 5);
                mutation11.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation12 = dis.registerMutation(RUBIDIUM, ALUMINIUM, 5);
                mutation12.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation13 = dis.registerMutation(STRONTIUM, MAGNESIUM, 4);
                mutation13.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation14 = dis.registerMutation(YTTRIUM, SODIUM, 4);
                mutation14.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation15 = dis.registerMutation(ZIRCONIUM, NEON, 4);
                mutation15.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation16 = dis.registerMutation(NIOBIUM, FLUORINE, 3);
                mutation16.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation17 = dis.registerMutation(MOLYBDENUM, OXYGEN, 3);
                mutation17.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation18 = dis.registerMutation(TECHNETIUM, NITROGEN, 3);
                mutation18.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation19 = dis.registerMutation(RUTHENIUM, CARBON, 2);
                mutation19.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation20 = dis.registerMutation(RHODIUM, BORON, 2);
                mutation20.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation21 = dis.registerMutation(PALLADIUM, BERYLLIUM, 2);
                mutation21.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation22 = dis.registerMutation(SILVER, LITHIUM, 1);
                mutation22.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation23 = dis.registerMutation(CADMIUM, HELIUM, 1);
                mutation23.addMutationCondition(new MaterialMutationCondition(Tin));
                IBeeMutationBuilder mutation24 = dis.registerMutation(INDIUM, HYDROGEN, 1);
                mutation24.addMutationCondition(new MaterialMutationCondition(Tin));
            }),
    ANTIMONY(GJBranchDefinition.GJ_METAL, "Antimona", false, 0xDCDCF0, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.ANTIMONY), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(IRON, MANGANESE, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation2 = dis.registerMutation(COBALT, CHROME, 8);
                mutation2.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation3 = dis.registerMutation(NICKEL, VANADIUM, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation4 = dis.registerMutation(COPPER, TITANIUM, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation5 = dis.registerMutation(ZINC, SCANDIUM, 7);
                mutation5.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation6 = dis.registerMutation(GALLIUM, CALCIUM, 7);
                mutation6.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation7 = dis.registerMutation(GERMANIUM, POTASSIUM, 7);
                mutation7.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation8 = dis.registerMutation(ARSENIC, ARGON, 6);
                mutation8.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation9 = dis.registerMutation(SELENIUM, CHLORINE, 6);
                mutation9.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation10 = dis.registerMutation(BROMINE, SULFUR, 6);
                mutation10.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation11 = dis.registerMutation(KRYPTON, PHOSPHORUS, 5);
                mutation11.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation12 = dis.registerMutation(RUBIDIUM, SILICON, 5);
                mutation12.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation13 = dis.registerMutation(STRONTIUM, ALUMINIUM, 5);
                mutation13.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation14 = dis.registerMutation(YTTRIUM, MAGNESIUM, 4);
                mutation14.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation15 = dis.registerMutation(ZIRCONIUM, SODIUM, 4);
                mutation15.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation16 = dis.registerMutation(NIOBIUM, NEON, 4);
                mutation16.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation17 = dis.registerMutation(MOLYBDENUM, FLUORINE, 3);
                mutation17.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation18 = dis.registerMutation(TECHNETIUM, OXYGEN, 3);
                mutation18.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation19 = dis.registerMutation(RUTHENIUM, NITROGEN, 3);
                mutation19.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation20 = dis.registerMutation(RHODIUM, CARBON, 2);
                mutation20.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation21 = dis.registerMutation(PALLADIUM, BORON, 2);
                mutation21.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation22 = dis.registerMutation(SILVER, BERYLLIUM, 2);
                mutation22.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation23 = dis.registerMutation(CADMIUM, LITHIUM, 1);
                mutation23.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation24 = dis.registerMutation(INDIUM, HELIUM, 1);
                mutation24.addMutationCondition(new MaterialMutationCondition(Antimony));
                IBeeMutationBuilder mutation25 = dis.registerMutation(TIN, HYDROGEN, 1);
                mutation25.addMutationCondition(new MaterialMutationCondition(Antimony));
            }),
    TELLURIUM(GJBranchDefinition.GJ_HALFMETAL, "Telluria", false, 0xFFFFFF, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.TELLURIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(COBALT, MANGANESE, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(NICKEL, CHROME, 8);
                mutation2.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(COPPER, VANADIUM, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(ZINC, TITANIUM, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(GALLIUM, SCANDIUM, 7);
                mutation5.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(GERMANIUM, CALCIUM, 7);
                mutation6.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(ARSENIC, POTASSIUM, 7);
                mutation7.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(SELENIUM, ARGON, 6);
                mutation8.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(BROMINE, CHLORINE, 6);
                mutation9.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(KRYPTON, SULFUR, 6);
                mutation10.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(RUBIDIUM, PHOSPHORUS, 5);
                mutation11.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(STRONTIUM, SILICON, 5);
                mutation12.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(YTTRIUM, ALUMINIUM, 5);
                mutation13.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(ZIRCONIUM, MAGNESIUM, 4);
                mutation14.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(NIOBIUM, SODIUM, 4);
                mutation15.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(MOLYBDENUM, NEON, 4);
                mutation16.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(TECHNETIUM, FLUORINE, 3);
                mutation17.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(RUTHENIUM, OXYGEN, 3);
                mutation18.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(RHODIUM, NITROGEN, 3);
                mutation19.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(PALLADIUM, CARBON, 2);
                mutation20.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(SILVER, BORON, 2);
                mutation21.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(CADMIUM, BERYLLIUM, 2);
                mutation22.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(INDIUM, LITHIUM, 1);
                mutation23.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(TIN, HELIUM, 1);
                mutation24.addMutationCondition(new MaterialMutationCondition(Tellurium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(ANTIMONY, HYDROGEN, 1);
                mutation25.addMutationCondition(new MaterialMutationCondition(Tellurium));
            }),
    IODINE(GJBranchDefinition.GJ_HALOGENS, "Iodia", false, 0x2C344F, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.IODINE), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(COBALT, IRON, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation2 = dis.registerMutation(NICKEL, MANGANESE, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation3 = dis.registerMutation(COPPER, CHROME, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation4 = dis.registerMutation(ZINC, VANADIUM, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation5 = dis.registerMutation(GALLIUM, TITANIUM, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation6 = dis.registerMutation(GERMANIUM, SCANDIUM, 7);
                mutation6.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation7 = dis.registerMutation(ARSENIC, CALCIUM, 7);
                mutation7.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation8 = dis.registerMutation(SELENIUM, POTASSIUM, 7);
                mutation8.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation9 = dis.registerMutation(BROMINE, ARGON, 6);
                mutation9.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation10 = dis.registerMutation(KRYPTON, CHLORINE, 6);
                mutation10.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation11 = dis.registerMutation(RUBIDIUM, SULFUR, 6);
                mutation11.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation12 = dis.registerMutation(STRONTIUM, PHOSPHORUS, 5);
                mutation12.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation13 = dis.registerMutation(YTTRIUM, SILICON, 5);
                mutation13.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation14 = dis.registerMutation(ZIRCONIUM, ALUMINIUM, 5);
                mutation14.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation15 = dis.registerMutation(NIOBIUM, MAGNESIUM, 4);
                mutation15.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation16 = dis.registerMutation(MOLYBDENUM, SODIUM, 4);
                mutation16.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation17 = dis.registerMutation(TECHNETIUM, NEON, 4);
                mutation17.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation18 = dis.registerMutation(RUTHENIUM, FLUORINE, 3);
                mutation18.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation19 = dis.registerMutation(RHODIUM, OXYGEN, 3);
                mutation19.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation20 = dis.registerMutation(PALLADIUM, NITROGEN, 3);
                mutation20.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation21 = dis.registerMutation(SILVER, CARBON, 2);
                mutation21.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation22 = dis.registerMutation(CADMIUM, BORON, 2);
                mutation22.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation23 = dis.registerMutation(INDIUM, BERYLLIUM, 2);
                mutation23.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation24 = dis.registerMutation(TIN, LITHIUM, 1);
                mutation24.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation25 = dis.registerMutation(ANTIMONY, HELIUM, 1);
                mutation25.addMutationCondition(new MaterialMutationCondition(Iodine));
                IBeeMutationBuilder mutation26 = dis.registerMutation(TELLURIUM, HYDROGEN, 1);
                mutation26.addMutationCondition(new MaterialMutationCondition(Iodine));
            }),
    XENON(GJBranchDefinition.GJ_NOBLEGAS, "Hostis", false, 0x8A97B0, 0x160822,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.XENON), 0.525f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.ICY);
                beeSpecies.setNocturnal();
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.SHORTEST),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(NICKEL, IRON, 9);
                mutation1.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation2 = dis.registerMutation(COPPER, MANGANESE, 9);
                mutation2.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation3 = dis.registerMutation(ZINC, CHROME, 8);
                mutation3.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation4 = dis.registerMutation(GALLIUM, VANADIUM, 8);
                mutation4.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation5 = dis.registerMutation(GERMANIUM, TITANIUM, 8);
                mutation5.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation6 = dis.registerMutation(ARSENIC, SCANDIUM, 7);
                mutation6.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation7 = dis.registerMutation(SELENIUM, CALCIUM, 7);
                mutation7.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation8 = dis.registerMutation(BROMINE, POTASSIUM, 7);
                mutation8.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation9 = dis.registerMutation(KRYPTON, ARGON, 6);
                mutation9.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation10 = dis.registerMutation(RUBIDIUM, CHLORINE, 6);
                mutation10.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation11 = dis.registerMutation(STRONTIUM, SULFUR, 6);
                mutation11.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation12 = dis.registerMutation(YTTRIUM, PHOSPHORUS, 5);
                mutation12.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation13 = dis.registerMutation(ZIRCONIUM, SILICON, 5);
                mutation13.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation14 = dis.registerMutation(NIOBIUM, ALUMINIUM, 5);
                mutation14.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation15 = dis.registerMutation(MOLYBDENUM, MAGNESIUM, 4);
                mutation15.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation16 = dis.registerMutation(TECHNETIUM, SODIUM, 4);
                mutation16.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation17 = dis.registerMutation(RUTHENIUM, NEON, 4);
                mutation17.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation18 = dis.registerMutation(RHODIUM, FLUORINE, 3);
                mutation18.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation19 = dis.registerMutation(PALLADIUM, OXYGEN, 3);
                mutation19.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation20 = dis.registerMutation(SILVER, NITROGEN, 3);
                mutation20.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation21 = dis.registerMutation(CADMIUM, CARBON, 2);
                mutation21.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation22 = dis.registerMutation(INDIUM, BORON, 2);
                mutation22.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation23 = dis.registerMutation(TIN, BERYLLIUM, 2);
                mutation23.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation24 = dis.registerMutation(ANTIMONY, LITHIUM, 1);
                mutation24.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation25 = dis.registerMutation(TELLURIUM, HELIUM, 1);
                mutation25.restrictTemperature(EnumTemperature.ICY);
                IBeeMutationBuilder mutation26 = dis.registerMutation(IODINE, HYDROGEN, 1);
                mutation26.restrictTemperature(EnumTemperature.ICY);
            }),
    CAESIUM(GJBranchDefinition.GJ_ALKALINEMETAL, "Caesium", false, 0x80620B, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.CAESIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(NICKEL, COBALT, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(COPPER, IRON, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(ZINC, MANGANESE, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(GALLIUM, CHROME, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(GERMANIUM, VANADIUM, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(ARSENIC, TITANIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(SELENIUM, SCANDIUM, 7);
                mutation7.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(BROMINE, CALCIUM, 7);
                mutation8.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(KRYPTON, POTASSIUM, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(RUBIDIUM, ARGON, 6);
                mutation10.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(STRONTIUM, CHLORINE, 6);
                mutation11.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(YTTRIUM, SULFUR, 6);
                mutation12.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(ZIRCONIUM, PHOSPHORUS, 5);
                mutation13.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(NIOBIUM, SILICON, 5);
                mutation14.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(MOLYBDENUM, ALUMINIUM, 5);
                mutation15.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(TECHNETIUM, MAGNESIUM, 4);
                mutation16.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(RUTHENIUM, SODIUM, 4);
                mutation17.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(RHODIUM, NEON, 4);
                mutation18.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(PALLADIUM, FLUORINE, 3);
                mutation19.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(SILVER, OXYGEN, 3);
                mutation20.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(CADMIUM, NITROGEN, 3);
                mutation21.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(INDIUM, CARBON, 2);
                mutation22.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(TIN, BORON, 2);
                mutation23.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(ANTIMONY, BERYLLIUM, 2);
                mutation24.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(TELLURIUM, LITHIUM, 1);
                mutation25.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(IODINE, HELIUM, 1);
                mutation26.addMutationCondition(new MaterialMutationCondition(Caesium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(XENON, HYDROGEN, 1);
                mutation27.addMutationCondition(new MaterialMutationCondition(Caesium));
            }),
    BARIUM(GJBranchDefinition.GJ_ALKALINEMETAL, "Baria", false, 0x83824C, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.BARIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(COPPER, COBALT, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(ZINC, IRON, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(GALLIUM, MANGANESE, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(GERMANIUM, CHROME, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(ARSENIC, VANADIUM, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(SELENIUM, TITANIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(BROMINE, SCANDIUM, 7);
                mutation7.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(KRYPTON, CALCIUM, 7);
                mutation8.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(RUBIDIUM, POTASSIUM, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(STRONTIUM, ARGON, 6);
                mutation10.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(YTTRIUM, CHLORINE, 6);
                mutation11.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(ZIRCONIUM, SULFUR, 6);
                mutation12.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(NIOBIUM, PHOSPHORUS, 5);
                mutation13.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(MOLYBDENUM, SILICON, 5);
                mutation14.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(TECHNETIUM, ALUMINIUM, 5);
                mutation15.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(RUTHENIUM, MAGNESIUM, 4);
                mutation16.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(RHODIUM, SODIUM, 4);
                mutation17.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(PALLADIUM, NEON, 4);
                mutation18.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(SILVER, FLUORINE, 3);
                mutation19.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(CADMIUM, OXYGEN, 3);
                mutation20.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(INDIUM, NITROGEN, 3);
                mutation21.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(TIN, CARBON, 2);
                mutation22.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(ANTIMONY, BORON, 2);
                mutation23.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(TELLURIUM, BERYLLIUM, 2);
                mutation24.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(IODINE, LITHIUM, 1);
                mutation25.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(XENON, HELIUM, 1);
                mutation26.addMutationCondition(new MaterialMutationCondition(Barium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(CAESIUM, HYDROGEN, 1);
                mutation27.addMutationCondition(new MaterialMutationCondition(Barium));
            }),
    LANTHANUM(GJBranchDefinition.GJ_RAREMETAL, "Lanthania", false, 0x5D7575, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.LANTHANUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(COPPER, NICKEL, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation2 = dis.registerMutation(ZINC, COBALT, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation3 = dis.registerMutation(GALLIUM, IRON, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation4 = dis.registerMutation(GERMANIUM, MANGANESE, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation5 = dis.registerMutation(ARSENIC, CHROME, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation6 = dis.registerMutation(SELENIUM, VANADIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation7 = dis.registerMutation(BROMINE, TITANIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation8 = dis.registerMutation(KRYPTON, SCANDIUM, 7);
                mutation8.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation9 = dis.registerMutation(RUBIDIUM, CALCIUM, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation10 = dis.registerMutation(STRONTIUM, POTASSIUM, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation11 = dis.registerMutation(YTTRIUM, ARGON, 6);
                mutation11.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation12 = dis.registerMutation(ZIRCONIUM, CHLORINE, 6);
                mutation12.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation13 = dis.registerMutation(NIOBIUM, SULFUR, 6);
                mutation13.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation14 = dis.registerMutation(MOLYBDENUM, PHOSPHORUS, 5);
                mutation14.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation15 = dis.registerMutation(TECHNETIUM, SILICON, 5);
                mutation15.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation16 = dis.registerMutation(RUTHENIUM, ALUMINIUM, 5);
                mutation16.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation17 = dis.registerMutation(RHODIUM, MAGNESIUM, 4);
                mutation17.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation18 = dis.registerMutation(PALLADIUM, SODIUM, 4);
                mutation18.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation19 = dis.registerMutation(SILVER, NEON, 4);
                mutation19.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation20 = dis.registerMutation(CADMIUM, FLUORINE, 3);
                mutation20.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation21 = dis.registerMutation(INDIUM, OXYGEN, 3);
                mutation21.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation22 = dis.registerMutation(TIN, NITROGEN, 3);
                mutation22.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation23 = dis.registerMutation(ANTIMONY, CARBON, 2);
                mutation23.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation24 = dis.registerMutation(TELLURIUM, BORON, 2);
                mutation24.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation25 = dis.registerMutation(IODINE, BERYLLIUM, 2);
                mutation25.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation26 = dis.registerMutation(XENON, LITHIUM, 1);
                mutation26.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation27 = dis.registerMutation(CAESIUM, HELIUM, 1);
                mutation27.addMutationCondition(new MaterialMutationCondition(Lanthanum));
                IBeeMutationBuilder mutation28 = dis.registerMutation(BARIUM, HYDROGEN, 1);
                mutation28.addMutationCondition(new MaterialMutationCondition(Lanthanum));
            }),
    CERIUM(GJBranchDefinition.GJ_RAREMETAL, "Ceria", false, 0x87917D, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.CERIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(ZINC, NICKEL, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(GALLIUM, COBALT, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(GERMANIUM, IRON, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(ARSENIC, MANGANESE, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(SELENIUM, CHROME, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(BROMINE, VANADIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(KRYPTON, TITANIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(RUBIDIUM, SCANDIUM, 7);
                mutation8.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(STRONTIUM, CALCIUM, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(YTTRIUM, POTASSIUM, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(ZIRCONIUM, ARGON, 6);
                mutation11.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(NIOBIUM, CHLORINE, 6);
                mutation12.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(MOLYBDENUM, SULFUR, 6);
                mutation13.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(TECHNETIUM, PHOSPHORUS, 5);
                mutation14.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(RUTHENIUM, SILICON, 5);
                mutation15.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(RHODIUM, ALUMINIUM, 5);
                mutation16.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(PALLADIUM, MAGNESIUM, 4);
                mutation17.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(SILVER, SODIUM, 4);
                mutation18.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(CADMIUM, NEON, 4);
                mutation19.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(INDIUM, FLUORINE, 3);
                mutation20.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(TIN, OXYGEN, 3);
                mutation21.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(ANTIMONY, NITROGEN, 3);
                mutation22.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(TELLURIUM, CARBON, 2);
                mutation23.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(IODINE, BORON, 2);
                mutation24.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(XENON, BERYLLIUM, 2);
                mutation25.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(CAESIUM, LITHIUM, 1);
                mutation26.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(BARIUM, HELIUM, 1);
                mutation27.addMutationCondition(new MaterialMutationCondition(Cerium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(LANTHANUM, HYDROGEN, 1);
                mutation28.addMutationCondition(new MaterialMutationCondition(Cerium));
            }),
    PRASEODYMIUM(GJBranchDefinition.GJ_RAREMETAL, "Praseodymia", false, 0xCECECE, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.PRASEODYMIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(ZINC, COPPER, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(GALLIUM, NICKEL, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(GERMANIUM, COBALT, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(ARSENIC, IRON, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(SELENIUM, MANGANESE, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(BROMINE, CHROME, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(KRYPTON, VANADIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(RUBIDIUM, TITANIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(STRONTIUM, SCANDIUM, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(YTTRIUM, CALCIUM, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(ZIRCONIUM, POTASSIUM, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(NIOBIUM, ARGON, 6);
                mutation12.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(MOLYBDENUM, CHLORINE, 6);
                mutation13.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(TECHNETIUM, SULFUR, 6);
                mutation14.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(RUTHENIUM, PHOSPHORUS, 5);
                mutation15.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(RHODIUM, SILICON, 5);
                mutation16.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(PALLADIUM, ALUMINIUM, 5);
                mutation17.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(SILVER, MAGNESIUM, 4);
                mutation18.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(CADMIUM, SODIUM, 4);
                mutation19.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(INDIUM, NEON, 4);
                mutation20.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(TIN, FLUORINE, 3);
                mutation21.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(ANTIMONY, OXYGEN, 3);
                mutation22.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(TELLURIUM, NITROGEN, 3);
                mutation23.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(IODINE, CARBON, 2);
                mutation24.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(XENON, BORON, 2);
                mutation25.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(CAESIUM, BERYLLIUM, 2);
                mutation26.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(BARIUM, LITHIUM, 1);
                mutation27.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(LANTHANUM, HELIUM, 1);
                mutation28.addMutationCondition(new MaterialMutationCondition(Praseodymium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(CERIUM, HYDROGEN, 1);
                mutation29.addMutationCondition(new MaterialMutationCondition(Praseodymium));
            }),
    NEODYMIUM(GJBranchDefinition.GJ_RAREMETAL, "Neodymia", false, 0x646464, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.NEODYMIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(GALLIUM, COPPER, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(GERMANIUM, NICKEL, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(ARSENIC, COBALT, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(SELENIUM, IRON, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(BROMINE, MANGANESE, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(KRYPTON, CHROME, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(RUBIDIUM, VANADIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(STRONTIUM, TITANIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(YTTRIUM, SCANDIUM, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(ZIRCONIUM, CALCIUM, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(NIOBIUM, POTASSIUM, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(MOLYBDENUM, ARGON, 6);
                mutation12.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(TECHNETIUM, CHLORINE, 6);
                mutation13.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(RUTHENIUM, SULFUR, 6);
                mutation14.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(RHODIUM, PHOSPHORUS, 5);
                mutation15.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(PALLADIUM, SILICON, 5);
                mutation16.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(SILVER, ALUMINIUM, 5);
                mutation17.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(CADMIUM, MAGNESIUM, 4);
                mutation18.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(INDIUM, SODIUM, 4);
                mutation19.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(TIN, NEON, 4);
                mutation20.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(ANTIMONY, FLUORINE, 3);
                mutation21.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(TELLURIUM, OXYGEN, 3);
                mutation22.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(IODINE, NITROGEN, 3);
                mutation23.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(XENON, CARBON, 2);
                mutation24.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(CAESIUM, BORON, 2);
                mutation25.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(BARIUM, BERYLLIUM, 2);
                mutation26.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(LANTHANUM, LITHIUM, 1);
                mutation27.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(CERIUM, HELIUM, 1);
                mutation28.addMutationCondition(new MaterialMutationCondition(Neodymium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(PRASEODYMIUM, HYDROGEN, 1);
                mutation29.addMutationCondition(new MaterialMutationCondition(Neodymium));
            }),
    PROMETHIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Promethia", false, 0xFFFFFF, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.PROMETHIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(GALLIUM, ZINC, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(GERMANIUM, COPPER, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(ARSENIC, NICKEL, 10);
                mutation3.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(SELENIUM, COBALT, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(BROMINE, IRON, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(KRYPTON, MANGANESE, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(RUBIDIUM, CHROME, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(STRONTIUM, VANADIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(YTTRIUM, TITANIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(ZIRCONIUM, SCANDIUM, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(NIOBIUM, CALCIUM, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(MOLYBDENUM, POTASSIUM, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(TECHNETIUM, ARGON, 6);
                mutation13.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(RUTHENIUM, CHLORINE, 6);
                mutation14.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(RHODIUM, SULFUR, 6);
                mutation15.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(PALLADIUM, PHOSPHORUS, 5);
                mutation16.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(SILVER, SILICON, 5);
                mutation17.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(CADMIUM, ALUMINIUM, 5);
                mutation18.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(INDIUM, MAGNESIUM, 4);
                mutation19.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(TIN, SODIUM, 4);
                mutation20.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(ANTIMONY, NEON, 4);
                mutation21.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(TELLURIUM, FLUORINE, 3);
                mutation22.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(IODINE, OXYGEN, 3);
                mutation23.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(XENON, NITROGEN, 3);
                mutation24.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(CAESIUM, CARBON, 2);
                mutation25.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(BARIUM, BORON, 2);
                mutation26.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(LANTHANUM, BERYLLIUM, 2);
                mutation27.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(CERIUM, LITHIUM, 1);
                mutation28.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(PRASEODYMIUM, HELIUM, 1);
                mutation29.addMutationCondition(new MaterialMutationCondition(Promethium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(NEODYMIUM, HYDROGEN, 1);
                mutation30.addMutationCondition(new MaterialMutationCondition(Promethium));
            }),
    SAMARIUM(GJBranchDefinition.GJ_RAREMETAL, "Samaria", false, 0xFFFFCC, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.SAMARIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(GERMANIUM, ZINC, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(ARSENIC, COPPER, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(SELENIUM, NICKEL, 10);
                mutation3.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(BROMINE, COBALT, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(KRYPTON, IRON, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(RUBIDIUM, MANGANESE, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(STRONTIUM, CHROME, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(YTTRIUM, VANADIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(ZIRCONIUM, TITANIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(NIOBIUM, SCANDIUM, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(MOLYBDENUM, CALCIUM, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(TECHNETIUM, POTASSIUM, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(RUTHENIUM, ARGON, 6);
                mutation13.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(RHODIUM, CHLORINE, 6);
                mutation14.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(PALLADIUM, SULFUR, 6);
                mutation15.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(SILVER, PHOSPHORUS, 5);
                mutation16.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(CADMIUM, SILICON, 5);
                mutation17.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(INDIUM, ALUMINIUM, 5);
                mutation18.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(TIN, MAGNESIUM, 4);
                mutation19.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(ANTIMONY, SODIUM, 4);
                mutation20.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(TELLURIUM, NEON, 4);
                mutation21.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(IODINE, FLUORINE, 3);
                mutation22.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(XENON, OXYGEN, 3);
                mutation23.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(CAESIUM, NITROGEN, 3);
                mutation24.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(BARIUM, CARBON, 2);
                mutation25.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(LANTHANUM, BORON, 2);
                mutation26.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(CERIUM, BERYLLIUM, 2);
                mutation27.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(PRASEODYMIUM, LITHIUM, 1);
                mutation28.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(NEODYMIUM, HELIUM, 1);
                mutation29.addMutationCondition(new MaterialMutationCondition(Samarium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(PROMETHIUM, HYDROGEN, 1);
                mutation30.addMutationCondition(new MaterialMutationCondition(Samarium));
            }),
    EUROPIUM(GJBranchDefinition.GJ_RAREMETAL, "Europia", false, 0x20FFFF, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.EUROPIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(GERMANIUM, GALLIUM, 8);
                mutation1.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(ARSENIC, ZINC, 8);
                mutation2.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(SELENIUM, COPPER, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(BROMINE, NICKEL, 7);
                mutation4.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(KRYPTON, COBALT, 7);
                mutation5.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(RUBIDIUM, IRON, 7);
                mutation6.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(STRONTIUM, MANGANESE, 7);
                mutation7.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(YTTRIUM, CHROME, 6);
                mutation8.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(ZIRCONIUM, VANADIUM, 6);
                mutation9.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(NIOBIUM, TITANIUM, 6);
                mutation10.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(MOLYBDENUM, SCANDIUM, 6);
                mutation11.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(TECHNETIUM, CALCIUM, 5);
                mutation12.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(RUTHENIUM, POTASSIUM, 5);
                mutation13.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(RHODIUM, ARGON, 5);
                mutation14.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(PALLADIUM, CHLORINE, 5);
                mutation15.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(SILVER, SULFUR, 4);
                mutation16.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(CADMIUM, PHOSPHORUS, 4);
                mutation17.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(INDIUM, SILICON, 4);
                mutation18.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(TIN, ALUMINIUM, 4);
                mutation19.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(ANTIMONY, MAGNESIUM, 3);
                mutation20.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(TELLURIUM, SODIUM, 3);
                mutation21.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(IODINE, NEON, 3);
                mutation22.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(XENON, FLUORINE, 3);
                mutation23.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(CAESIUM, OXYGEN, 2);
                mutation24.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(BARIUM, NITROGEN, 2);
                mutation25.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(LANTHANUM, CARBON, 2);
                mutation26.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(CERIUM, BORON, 2);
                mutation27.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(PRASEODYMIUM, BERYLLIUM, 1);
                mutation28.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(NEODYMIUM, LITHIUM, 1);
                mutation29.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(PROMETHIUM, HELIUM, 1);
                mutation30.addMutationCondition(new MaterialMutationCondition(Europium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(SAMARIUM, HYDROGEN, 1);
                mutation31.addMutationCondition(new MaterialMutationCondition(Europium));
            }),
    GADOLINIUM(GJBranchDefinition.GJ_RAREMETAL, "Gadolinia", false, 0xDDDDFF, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.GADOLINIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(ARSENIC, GALLIUM, 8);
                mutation1.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(SELENIUM, ZINC, 8);
                mutation2.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(BROMINE, COPPER, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(KRYPTON, NICKEL, 7);
                mutation4.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(RUBIDIUM, COBALT, 7);
                mutation5.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(STRONTIUM, IRON, 7);
                mutation6.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(YTTRIUM, MANGANESE, 7);
                mutation7.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(ZIRCONIUM, CHROME, 6);
                mutation8.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(NIOBIUM, VANADIUM, 6);
                mutation9.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(MOLYBDENUM, TITANIUM, 6);
                mutation10.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(TECHNETIUM, SCANDIUM, 6);
                mutation11.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(RUTHENIUM, CALCIUM, 5);
                mutation12.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(RHODIUM, POTASSIUM, 5);
                mutation13.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(PALLADIUM, ARGON, 5);
                mutation14.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(SILVER, CHLORINE, 5);
                mutation15.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(CADMIUM, SULFUR, 4);
                mutation16.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(INDIUM, PHOSPHORUS, 4);
                mutation17.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(TIN, SILICON, 4);
                mutation18.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(ANTIMONY, ALUMINIUM, 4);
                mutation19.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(TELLURIUM, MAGNESIUM, 3);
                mutation20.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(IODINE, SODIUM, 3);
                mutation21.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(XENON, NEON, 3);
                mutation22.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(CAESIUM, FLUORINE, 3);
                mutation23.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(BARIUM, OXYGEN, 2);
                mutation24.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(LANTHANUM, NITROGEN, 2);
                mutation25.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(CERIUM, CARBON, 2);
                mutation26.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(PRASEODYMIUM, BORON, 2);
                mutation27.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(NEODYMIUM, BERYLLIUM, 1);
                mutation28.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(PROMETHIUM, LITHIUM, 1);
                mutation29.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(SAMARIUM, HELIUM, 1);
                mutation30.addMutationCondition(new MaterialMutationCondition(Gadolinium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(EUROPIUM, HYDROGEN, 1);
                mutation31.addMutationCondition(new MaterialMutationCondition(Gadolinium));
            }),
    TERBIUM(GJBranchDefinition.GJ_RAREMETAL, "Terbia", false, 0xFFFFFF, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.TERBIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(ARSENIC, GERMANIUM, 8);
                mutation1.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(SELENIUM, GALLIUM, 8);
                mutation2.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(BROMINE, ZINC, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(KRYPTON, COPPER, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(RUBIDIUM, NICKEL, 7);
                mutation5.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(STRONTIUM, COBALT, 7);
                mutation6.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(YTTRIUM, IRON, 7);
                mutation7.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(ZIRCONIUM, MANGANESE, 7);
                mutation8.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(NIOBIUM, CHROME, 6);
                mutation9.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(MOLYBDENUM, VANADIUM, 6);
                mutation10.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(TECHNETIUM, TITANIUM, 6);
                mutation11.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(RUTHENIUM, SCANDIUM, 6);
                mutation12.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(RHODIUM, CALCIUM, 5);
                mutation13.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(PALLADIUM, POTASSIUM, 5);
                mutation14.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(SILVER, ARGON, 5);
                mutation15.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(CADMIUM, CHLORINE, 5);
                mutation16.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(INDIUM, SULFUR, 4);
                mutation17.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(TIN, PHOSPHORUS, 4);
                mutation18.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(ANTIMONY, SILICON, 4);
                mutation19.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(TELLURIUM, ALUMINIUM, 4);
                mutation20.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(IODINE, MAGNESIUM, 3);
                mutation21.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(XENON, SODIUM, 3);
                mutation22.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(CAESIUM, NEON, 3);
                mutation23.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(BARIUM, FLUORINE, 3);
                mutation24.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(LANTHANUM, OXYGEN, 2);
                mutation25.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(CERIUM, NITROGEN, 2);
                mutation26.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(PRASEODYMIUM, CARBON, 2);
                mutation27.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(NEODYMIUM, BORON, 2);
                mutation28.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(PROMETHIUM, BERYLLIUM, 1);
                mutation29.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(SAMARIUM, LITHIUM, 1);
                mutation30.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(EUROPIUM, HELIUM, 1);
                mutation31.addMutationCondition(new MaterialMutationCondition(Terbium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(GADOLINIUM, HYDROGEN, 1);
                mutation32.addMutationCondition(new MaterialMutationCondition(Terbium));
            }),
    DYSPROSIUM(GJBranchDefinition.GJ_RAREMETAL, "Dysprosia", false, 0xFFFFEE, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.DYSPROSIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(SELENIUM, GERMANIUM, 8);
                mutation1.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(BROMINE, GALLIUM, 8);
                mutation2.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(KRYPTON, ZINC, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(RUBIDIUM, COPPER, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(STRONTIUM, NICKEL, 7);
                mutation5.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(YTTRIUM, COBALT, 7);
                mutation6.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(ZIRCONIUM, IRON, 7);
                mutation7.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(NIOBIUM, MANGANESE, 7);
                mutation8.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(MOLYBDENUM, CHROME, 6);
                mutation9.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(TECHNETIUM, VANADIUM, 6);
                mutation10.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(RUTHENIUM, TITANIUM, 6);
                mutation11.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(RHODIUM, SCANDIUM, 6);
                mutation12.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(PALLADIUM, CALCIUM, 5);
                mutation13.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(SILVER, POTASSIUM, 5);
                mutation14.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(CADMIUM, ARGON, 5);
                mutation15.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(INDIUM, CHLORINE, 5);
                mutation16.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(TIN, SULFUR, 4);
                mutation17.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(ANTIMONY, PHOSPHORUS, 4);
                mutation18.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(TELLURIUM, SILICON, 4);
                mutation19.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(IODINE, ALUMINIUM, 4);
                mutation20.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(XENON, MAGNESIUM, 3);
                mutation21.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(CAESIUM, SODIUM, 3);
                mutation22.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(BARIUM, NEON, 3);
                mutation23.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(LANTHANUM, FLUORINE, 3);
                mutation24.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(CERIUM, OXYGEN, 2);
                mutation25.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(PRASEODYMIUM, NITROGEN, 2);
                mutation26.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(NEODYMIUM, CARBON, 2);
                mutation27.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(PROMETHIUM, BORON, 2);
                mutation28.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(SAMARIUM, BERYLLIUM, 1);
                mutation29.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(EUROPIUM, LITHIUM, 1);
                mutation30.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(GADOLINIUM, HELIUM, 1);
                mutation31.addMutationCondition(new MaterialMutationCondition(Dysprosium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(TERBIUM, HYDROGEN, 1);
                mutation32.addMutationCondition(new MaterialMutationCondition(Dysprosium));
            }),

    HOLMIUM(GJBranchDefinition.GJ_RAREMETAL, "Holmia", false, 0xFFFFFF, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.HOLMIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(SELENIUM, ARSENIC, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(BROMINE, GERMANIUM, 8);
                mutation2.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(KRYPTON, GALLIUM, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(RUBIDIUM, ZINC, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(STRONTIUM, COPPER, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(YTTRIUM, NICKEL, 7);
                mutation6.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(ZIRCONIUM, COBALT, 7);
                mutation7.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(NIOBIUM, IRON, 7);
                mutation8.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(MOLYBDENUM, MANGANESE, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(TECHNETIUM, CHROME, 6);
                mutation10.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(RUTHENIUM, VANADIUM, 6);
                mutation11.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(RHODIUM, TITANIUM, 6);
                mutation12.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(PALLADIUM, SCANDIUM, 6);
                mutation13.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(SILVER, CALCIUM, 5);
                mutation14.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(CADMIUM, POTASSIUM, 5);
                mutation15.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(INDIUM, ARGON, 5);
                mutation16.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(TIN, CHLORINE, 5);
                mutation17.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(ANTIMONY, SULFUR, 4);
                mutation18.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(TELLURIUM, PHOSPHORUS, 4);
                mutation19.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(IODINE, SILICON, 4);
                mutation20.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(XENON, ALUMINIUM, 4);
                mutation21.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(CAESIUM, MAGNESIUM, 3);
                mutation22.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(BARIUM, SODIUM, 3);
                mutation23.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(LANTHANUM, NEON, 3);
                mutation24.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(CERIUM, FLUORINE, 3);
                mutation25.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(PRASEODYMIUM, OXYGEN, 2);
                mutation26.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(NEODYMIUM, NITROGEN, 2);
                mutation27.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(PROMETHIUM, CARBON, 2);
                mutation28.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(SAMARIUM, BORON, 2);
                mutation29.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(EUROPIUM, BERYLLIUM, 1);
                mutation30.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(GADOLINIUM, LITHIUM, 1);
                mutation31.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(TERBIUM, HELIUM, 1);
                mutation32.addMutationCondition(new MaterialMutationCondition(Holmium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(DYSPROSIUM, HYDROGEN, 1);
                mutation33.addMutationCondition(new MaterialMutationCondition(Holmium));
            }),
    ERBIUM(GJBranchDefinition.GJ_RAREMETAL, "Erbia", false, 0xEEEEEE, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.ERBIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(BROMINE, ARSENIC, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(KRYPTON, GERMANIUM, 8);
                mutation2.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(RUBIDIUM, GALLIUM, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(STRONTIUM, ZINC, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(YTTRIUM, COPPER, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(ZIRCONIUM, NICKEL, 7);
                mutation6.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(NIOBIUM, COBALT, 7);
                mutation7.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(MOLYBDENUM, IRON, 7);
                mutation8.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(TECHNETIUM, MANGANESE, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(RUTHENIUM, CHROME, 6);
                mutation10.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(RHODIUM, VANADIUM, 6);
                mutation11.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(PALLADIUM, TITANIUM, 6);
                mutation12.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(SILVER, SCANDIUM, 6);
                mutation13.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(CADMIUM, CALCIUM, 5);
                mutation14.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(INDIUM, POTASSIUM, 5);
                mutation15.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(TIN, ARGON, 5);
                mutation16.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(ANTIMONY, CHLORINE, 5);
                mutation17.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(TELLURIUM, SULFUR, 4);
                mutation18.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(IODINE, PHOSPHORUS, 4);
                mutation19.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(XENON, SILICON, 4);
                mutation20.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(CAESIUM, ALUMINIUM, 4);
                mutation21.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(BARIUM, MAGNESIUM, 3);
                mutation22.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(LANTHANUM, SODIUM, 3);
                mutation23.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(CERIUM, NEON, 3);
                mutation24.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(PRASEODYMIUM, FLUORINE, 3);
                mutation25.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(NEODYMIUM, OXYGEN, 2);
                mutation26.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(PROMETHIUM, NITROGEN, 2);
                mutation27.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(SAMARIUM, CARBON, 2);
                mutation28.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(EUROPIUM, BORON, 2);
                mutation29.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(GADOLINIUM, BERYLLIUM, 1);
                mutation30.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(TERBIUM, LITHIUM, 1);
                mutation31.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(DYSPROSIUM, HELIUM, 1);
                mutation32.addMutationCondition(new MaterialMutationCondition(Erbium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(HOLMIUM, HYDROGEN, 1);
                mutation33.addMutationCondition(new MaterialMutationCondition(Erbium));
            }),
    THULIUM(GJBranchDefinition.GJ_RAREMETAL, "Thulia", false, 0xFFFFFF, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.THULIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(BROMINE, SELENIUM, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(KRYPTON, ARSENIC, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(RUBIDIUM, GERMANIUM, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(STRONTIUM, GALLIUM, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(YTTRIUM, ZINC, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(ZIRCONIUM, COPPER, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(NIOBIUM, NICKEL, 7);
                mutation7.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(MOLYBDENUM, COBALT, 7);
                mutation8.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(TECHNETIUM, IRON, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(RUTHENIUM, MANGANESE, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(RHODIUM, CHROME, 6);
                mutation11.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(PALLADIUM, VANADIUM, 6);
                mutation12.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(SILVER, TITANIUM, 6);
                mutation13.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(CADMIUM, SCANDIUM, 6);
                mutation14.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(INDIUM, CALCIUM, 5);
                mutation15.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(TIN, POTASSIUM, 5);
                mutation16.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(ANTIMONY, ARGON, 5);
                mutation17.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(TELLURIUM, CHLORINE, 5);
                mutation18.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(IODINE, SULFUR, 4);
                mutation19.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(XENON, PHOSPHORUS, 4);
                mutation20.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(CAESIUM, SILICON, 4);
                mutation21.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(BARIUM, ALUMINIUM, 4);
                mutation22.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(LANTHANUM, MAGNESIUM, 3);
                mutation23.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(CERIUM, SODIUM, 3);
                mutation24.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(PRASEODYMIUM, NEON, 3);
                mutation25.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(NEODYMIUM, FLUORINE, 3);
                mutation26.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(PROMETHIUM, OXYGEN, 2);
                mutation27.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(SAMARIUM, NITROGEN, 2);
                mutation28.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(EUROPIUM, CARBON, 2);
                mutation29.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(GADOLINIUM, BORON, 2);
                mutation30.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(TERBIUM, BERYLLIUM, 1);
                mutation31.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(DYSPROSIUM, LITHIUM, 1);
                mutation32.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(HOLMIUM, HELIUM, 1);
                mutation33.addMutationCondition(new MaterialMutationCondition(Thulium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(ERBIUM, HYDROGEN, 1);
                mutation34.addMutationCondition(new MaterialMutationCondition(Thulium));
            }),
    YTTERBIUM(GJBranchDefinition.GJ_RAREMETAL, "Ytterbia", false, 0xA7A7A7, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.YTTERBIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(KRYPTON, SELENIUM, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(RUBIDIUM, ARSENIC, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(STRONTIUM, GERMANIUM, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(YTTRIUM, GALLIUM, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(ZIRCONIUM, ZINC, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(NIOBIUM, COPPER, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(MOLYBDENUM, NICKEL, 7);
                mutation7.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(TECHNETIUM, COBALT, 7);
                mutation8.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(RUTHENIUM, IRON, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(RHODIUM, MANGANESE, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(PALLADIUM, CHROME, 6);
                mutation11.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(SILVER, VANADIUM, 6);
                mutation12.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(CADMIUM, TITANIUM, 6);
                mutation13.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(INDIUM, SCANDIUM, 6);
                mutation14.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(TIN, CALCIUM, 5);
                mutation15.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(ANTIMONY, POTASSIUM, 5);
                mutation16.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(TELLURIUM, ARGON, 5);
                mutation17.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(IODINE, CHLORINE, 5);
                mutation18.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(XENON, SULFUR, 4);
                mutation19.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(CAESIUM, PHOSPHORUS, 4);
                mutation20.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(BARIUM, SILICON, 4);
                mutation21.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(LANTHANUM, ALUMINIUM, 4);
                mutation22.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(CERIUM, MAGNESIUM, 3);
                mutation23.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(PRASEODYMIUM, SODIUM, 3);
                mutation24.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(NEODYMIUM, NEON, 3);
                mutation25.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(PROMETHIUM, FLUORINE, 3);
                mutation26.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(SAMARIUM, OXYGEN, 2);
                mutation27.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(EUROPIUM, NITROGEN, 2);
                mutation28.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(GADOLINIUM, CARBON, 2);
                mutation29.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(TERBIUM, BORON, 2);
                mutation30.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(DYSPROSIUM, BERYLLIUM, 1);
                mutation31.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(HOLMIUM, LITHIUM, 1);
                mutation32.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(ERBIUM, HELIUM, 1);
                mutation33.addMutationCondition(new MaterialMutationCondition(Ytterbium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(THULIUM, HYDROGEN, 1);
                mutation34.addMutationCondition(new MaterialMutationCondition(Ytterbium));
            }),
    LUTETIUM(GJBranchDefinition.GJ_RAREMETAL, "Lutetia", false, 0x00AAFF, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.LUTETIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(KRYPTON, BROMINE, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(RUBIDIUM, SELENIUM, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(STRONTIUM, ARSENIC, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(YTTRIUM, GERMANIUM, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(ZIRCONIUM, GALLIUM, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(NIOBIUM, ZINC, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(MOLYBDENUM, COPPER, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(TECHNETIUM, NICKEL, 7);
                mutation8.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(RUTHENIUM, COBALT, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(RHODIUM, IRON, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(PALLADIUM, MANGANESE, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(SILVER, CHROME, 6);
                mutation12.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(CADMIUM, VANADIUM, 6);
                mutation13.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(INDIUM, TITANIUM, 6);
                mutation14.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(TIN, SCANDIUM, 6);
                mutation15.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(ANTIMONY, CALCIUM, 5);
                mutation16.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(TELLURIUM, POTASSIUM, 5);
                mutation17.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(IODINE, ARGON, 5);
                mutation18.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(XENON, CHLORINE, 5);
                mutation19.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(CAESIUM, SULFUR, 4);
                mutation20.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(BARIUM, PHOSPHORUS, 4);
                mutation21.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(LANTHANUM, SILICON, 4);
                mutation22.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(CERIUM, ALUMINIUM, 4);
                mutation23.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(PRASEODYMIUM, MAGNESIUM, 3);
                mutation24.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(NEODYMIUM, SODIUM, 3);
                mutation25.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(PROMETHIUM, NEON, 3);
                mutation26.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(SAMARIUM, FLUORINE, 3);
                mutation27.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(EUROPIUM, OXYGEN, 2);
                mutation28.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(GADOLINIUM, NITROGEN, 2);
                mutation29.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(TERBIUM, CARBON, 2);
                mutation30.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(DYSPROSIUM, BORON, 2);
                mutation31.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(HOLMIUM, BERYLLIUM, 1);
                mutation32.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(ERBIUM, LITHIUM, 1);
                mutation33.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(THULIUM, HELIUM, 1);
                mutation34.addMutationCondition(new MaterialMutationCondition(Lutetium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(YTTERBIUM, HYDROGEN, 1);
                mutation35.addMutationCondition(new MaterialMutationCondition(Lutetium));
            }),

    HAFNIUM(GJBranchDefinition.GJ_RAREMETAL, "Hafnia", false, 0x99999A, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.HAFNIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(RUBIDIUM, BROMINE, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(STRONTIUM, SELENIUM, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(YTTRIUM, ARSENIC, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(ZIRCONIUM, GERMANIUM, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(NIOBIUM, GALLIUM, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(MOLYBDENUM, ZINC, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(TECHNETIUM, COPPER, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(RUTHENIUM, NICKEL, 7);
                mutation8.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(RHODIUM, COBALT, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(PALLADIUM, IRON, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(SILVER, MANGANESE, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(CADMIUM, CHROME, 6);
                mutation12.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(INDIUM, VANADIUM, 6);
                mutation13.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(TIN, TITANIUM, 6);
                mutation14.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(ANTIMONY, SCANDIUM, 6);
                mutation15.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(TELLURIUM, CALCIUM, 5);
                mutation16.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(IODINE, POTASSIUM, 5);
                mutation17.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(XENON, ARGON, 5);
                mutation18.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(CAESIUM, CHLORINE, 5);
                mutation19.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(BARIUM, SULFUR, 4);
                mutation20.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(LANTHANUM, PHOSPHORUS, 4);
                mutation21.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(CERIUM, SILICON, 4);
                mutation22.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(PRASEODYMIUM, ALUMINIUM, 4);
                mutation23.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(NEODYMIUM, MAGNESIUM, 3);
                mutation24.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(PROMETHIUM, SODIUM, 3);
                mutation25.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(SAMARIUM, NEON, 3);
                mutation26.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(EUROPIUM, FLUORINE, 3);
                mutation27.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(GADOLINIUM, OXYGEN, 2);
                mutation28.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(TERBIUM, NITROGEN, 2);
                mutation29.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(DYSPROSIUM, CARBON, 2);
                mutation30.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(HOLMIUM, BORON, 2);
                mutation31.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(ERBIUM, BERYLLIUM, 1);
                mutation32.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(THULIUM, LITHIUM, 1);
                mutation33.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(YTTERBIUM, HELIUM, 1);
                mutation34.addMutationCondition(new MaterialMutationCondition(Hafnium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(LUTETIUM, HYDROGEN, 1);
                mutation35.addMutationCondition(new MaterialMutationCondition(Hafnium));
            }),
    TANTALUM(GJBranchDefinition.GJ_RAREMETAL, "Tantala", false, 0x69B7FF, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.TANTALUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(RUBIDIUM, KRYPTON, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation2 = dis.registerMutation(STRONTIUM, BROMINE, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation3 = dis.registerMutation(YTTRIUM, SELENIUM, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation4 = dis.registerMutation(ZIRCONIUM, ARSENIC, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation5 = dis.registerMutation(NIOBIUM, GERMANIUM, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation6 = dis.registerMutation(MOLYBDENUM, GALLIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation7 = dis.registerMutation(TECHNETIUM, ZINC, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation8 = dis.registerMutation(RUTHENIUM, COPPER, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation9 = dis.registerMutation(RHODIUM, NICKEL, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation10 = dis.registerMutation(PALLADIUM, COBALT, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation11 = dis.registerMutation(SILVER, IRON, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation12 = dis.registerMutation(CADMIUM, MANGANESE, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation13 = dis.registerMutation(INDIUM, CHROME, 6);
                mutation13.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation14 = dis.registerMutation(TIN, VANADIUM, 6);
                mutation14.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation15 = dis.registerMutation(ANTIMONY, TITANIUM, 6);
                mutation15.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation16 = dis.registerMutation(TELLURIUM, SCANDIUM, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation17 = dis.registerMutation(IODINE, CALCIUM, 5);
                mutation17.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation18 = dis.registerMutation(XENON, POTASSIUM, 5);
                mutation18.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation19 = dis.registerMutation(CAESIUM, ARGON, 5);
                mutation19.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation20 = dis.registerMutation(BARIUM, CHLORINE, 5);
                mutation20.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation21 = dis.registerMutation(LANTHANUM, SULFUR, 4);
                mutation21.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation22 = dis.registerMutation(CERIUM, PHOSPHORUS, 4);
                mutation22.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation23 = dis.registerMutation(PRASEODYMIUM, SILICON, 4);
                mutation23.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation24 = dis.registerMutation(NEODYMIUM, ALUMINIUM, 4);
                mutation24.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation25 = dis.registerMutation(PROMETHIUM, MAGNESIUM, 3);
                mutation25.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation26 = dis.registerMutation(SAMARIUM, SODIUM, 3);
                mutation26.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation27 = dis.registerMutation(EUROPIUM, NEON, 3);
                mutation27.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation28 = dis.registerMutation(GADOLINIUM, FLUORINE, 3);
                mutation28.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation29 = dis.registerMutation(TERBIUM, OXYGEN, 2);
                mutation29.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation30 = dis.registerMutation(DYSPROSIUM, NITROGEN, 2);
                mutation30.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation31 = dis.registerMutation(HOLMIUM, CARBON, 2);
                mutation31.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation32 = dis.registerMutation(ERBIUM, BORON, 2);
                mutation32.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation33 = dis.registerMutation(THULIUM, BERYLLIUM, 1);
                mutation33.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation34 = dis.registerMutation(YTTERBIUM, LITHIUM, 1);
                mutation34.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation35 = dis.registerMutation(LUTETIUM, HELIUM, 1);
                mutation35.addMutationCondition(new MaterialMutationCondition(Tantalum));
                IBeeMutationBuilder mutation36 = dis.registerMutation(HAFNIUM, HYDROGEN, 1);
                mutation36.addMutationCondition(new MaterialMutationCondition(Tantalum));
            }),
    TUNGSTEN(GJBranchDefinition.GJ_RAREMETAL, "Wolframium", false, 0x5C5C8A, 0x7D7DA1,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.TUNGSTEN), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.MOLYBDENUM), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(STRONTIUM, KRYPTON, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation2 = dis.registerMutation(YTTRIUM, BROMINE, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation3 = dis.registerMutation(ZIRCONIUM, SELENIUM, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation4 = dis.registerMutation(NIOBIUM, ARSENIC, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation5 = dis.registerMutation(MOLYBDENUM, GERMANIUM, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation6 = dis.registerMutation(TECHNETIUM, GALLIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation7 = dis.registerMutation(RUTHENIUM, ZINC, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation8 = dis.registerMutation(RHODIUM, COPPER, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation9 = dis.registerMutation(PALLADIUM, NICKEL, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation10 = dis.registerMutation(SILVER, COBALT, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation11 = dis.registerMutation(CADMIUM, IRON, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation12 = dis.registerMutation(INDIUM, MANGANESE, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation13 = dis.registerMutation(TIN, CHROME, 6);
                mutation13.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation14 = dis.registerMutation(ANTIMONY, VANADIUM, 6);
                mutation14.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation15 = dis.registerMutation(TELLURIUM, TITANIUM, 6);
                mutation15.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation16 = dis.registerMutation(IODINE, SCANDIUM, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation17 = dis.registerMutation(XENON, CALCIUM, 5);
                mutation17.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation18 = dis.registerMutation(CAESIUM, POTASSIUM, 5);
                mutation18.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation19 = dis.registerMutation(BARIUM, ARGON, 5);
                mutation19.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation20 = dis.registerMutation(LANTHANUM, CHLORINE, 5);
                mutation20.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation21 = dis.registerMutation(CERIUM, SULFUR, 4);
                mutation21.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation22 = dis.registerMutation(PRASEODYMIUM, PHOSPHORUS, 4);
                mutation22.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation23 = dis.registerMutation(NEODYMIUM, SILICON, 4);
                mutation23.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation24 = dis.registerMutation(PROMETHIUM, ALUMINIUM, 4);
                mutation24.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation25 = dis.registerMutation(SAMARIUM, MAGNESIUM, 3);
                mutation25.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation26 = dis.registerMutation(EUROPIUM, SODIUM, 3);
                mutation26.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation27 = dis.registerMutation(GADOLINIUM, NEON, 3);
                mutation27.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation28 = dis.registerMutation(TERBIUM, FLUORINE, 3);
                mutation28.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation29 = dis.registerMutation(DYSPROSIUM, OXYGEN, 2);
                mutation29.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation30 = dis.registerMutation(HOLMIUM, NITROGEN, 2);
                mutation30.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation31 = dis.registerMutation(ERBIUM, CARBON, 2);
                mutation31.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation32 = dis.registerMutation(THULIUM, BORON, 2);
                mutation32.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation33 = dis.registerMutation(YTTERBIUM, BERYLLIUM, 1);
                mutation33.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation34 = dis.registerMutation(LUTETIUM, LITHIUM, 1);
                mutation34.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation35 = dis.registerMutation(HAFNIUM, HELIUM, 1);
                mutation35.addMutationCondition(new MaterialMutationCondition(Tungsten));
                IBeeMutationBuilder mutation36 = dis.registerMutation(TANTALUM, HYDROGEN, 1);
                mutation36.addMutationCondition(new MaterialMutationCondition(Tungsten));
            }),
    RHENIUM(GJBranchDefinition.GJ_RAREMETAL, "Rhenia", false, 0xB6BAC3, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.RHENIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(STRONTIUM, RUBIDIUM, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(ZIRCONIUM, BROMINE, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(NIOBIUM, SELENIUM, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(MOLYBDENUM, ARSENIC, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(TECHNETIUM, GERMANIUM, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(RUTHENIUM, GALLIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(RHODIUM, ZINC, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(PALLADIUM, COPPER, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(SILVER, NICKEL, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(CADMIUM, COBALT, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(INDIUM, IRON, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(TIN, MANGANESE, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(ANTIMONY, CHROME, 6);
                mutation13.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(TELLURIUM, VANADIUM, 6);
                mutation14.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(IODINE, TITANIUM, 6);
                mutation15.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(XENON, SCANDIUM, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(CAESIUM, CALCIUM, 5);
                mutation17.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(BARIUM, POTASSIUM, 5);
                mutation18.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(LANTHANUM, ARGON, 5);
                mutation19.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(CERIUM, CHLORINE, 5);
                mutation20.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(PRASEODYMIUM, SULFUR, 4);
                mutation21.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(NEODYMIUM, PHOSPHORUS, 4);
                mutation22.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(PROMETHIUM, SILICON, 4);
                mutation23.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(SAMARIUM, ALUMINIUM, 4);
                mutation24.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(EUROPIUM, MAGNESIUM, 3);
                mutation25.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(GADOLINIUM, SODIUM, 3);
                mutation26.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(TERBIUM, NEON, 3);
                mutation27.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(DYSPROSIUM, FLUORINE, 3);
                mutation28.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(HOLMIUM, OXYGEN, 2);
                mutation29.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(ERBIUM, NITROGEN, 2);
                mutation30.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(THULIUM, CARBON, 2);
                mutation31.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(YTTERBIUM, BORON, 2);
                mutation32.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(LUTETIUM, BERYLLIUM, 1);
                mutation33.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(HAFNIUM, LITHIUM, 1);
                mutation34.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(TANTALUM, HELIUM, 1);
                mutation35.addMutationCondition(new MaterialMutationCondition(Rhenium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(TUNGSTEN, HYDROGEN, 1);
                mutation36.addMutationCondition(new MaterialMutationCondition(Rhenium));
            }),
    OSMIUM(GJBranchDefinition.GJ_RAREMETAL, "Osmia", false, 0x2B2BDA, 0x8B8B8B,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.OSMIUM), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.IRIDIUM), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.COLD);
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(YTTRIUM, RUBIDIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(ZIRCONIUM, KRYPTON, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(NIOBIUM, BROMINE, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(MOLYBDENUM, SELENIUM, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(TECHNETIUM, ARSENIC, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(RUTHENIUM, GERMANIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(RHODIUM, GALLIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(PALLADIUM, ZINC, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(SILVER, COPPER, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(CADMIUM, NICKEL, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(INDIUM, COBALT, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(TIN, IRON, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(ANTIMONY, MANGANESE, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(TELLURIUM, CHROME, 6);
                mutation14.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(IODINE, VANADIUM, 6);
                mutation15.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(XENON, TITANIUM, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(CAESIUM, SCANDIUM, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(BARIUM, CALCIUM, 5);
                mutation18.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(LANTHANUM, POTASSIUM, 5);
                mutation19.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(CERIUM, ARGON, 5);
                mutation20.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(PRASEODYMIUM, CHLORINE, 5);
                mutation21.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(NEODYMIUM, SULFUR, 4);
                mutation22.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(PROMETHIUM, PHOSPHORUS, 4);
                mutation23.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(SAMARIUM, SILICON, 4);
                mutation24.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(EUROPIUM, ALUMINIUM, 4);
                mutation25.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(GADOLINIUM, MAGNESIUM, 3);
                mutation26.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(TERBIUM, SODIUM, 3);
                mutation27.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(DYSPROSIUM, NEON, 3);
                mutation28.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(HOLMIUM, FLUORINE, 3);
                mutation29.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(ERBIUM, OXYGEN, 2);
                mutation30.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(THULIUM, NITROGEN, 2);
                mutation31.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(YTTERBIUM, CARBON, 2);
                mutation32.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(LUTETIUM, BORON, 2);
                mutation33.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(HAFNIUM, BERYLLIUM, 1);
                mutation34.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(TANTALUM, LITHIUM, 1);
                mutation35.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(TUNGSTEN, HELIUM, 1);
                mutation36.addMutationCondition(new MaterialMutationCondition(Osmium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(RHENIUM, HYDROGEN, 1);
                mutation37.addMutationCondition(new MaterialMutationCondition(Osmium));
            }),
    IRIDIUM(GJBranchDefinition.GJ_RAREMETAL, "Iris", false, 0xDADADA, 0xD1D1E0,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.IRIDIUM), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.OSMIUM), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HELLISH);
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(YTTRIUM, STRONTIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(ZIRCONIUM, RUBIDIUM, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(NIOBIUM, KRYPTON, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(MOLYBDENUM, BROMINE, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(TECHNETIUM, SELENIUM, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(RUTHENIUM, ARSENIC, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(RHODIUM, GERMANIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(PALLADIUM, GALLIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(SILVER, ZINC, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(CADMIUM, COPPER, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(INDIUM, NICKEL, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(TIN, COBALT, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(ANTIMONY, IRON, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(TELLURIUM, MANGANESE, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(IODINE, CHROME, 6);
                mutation15.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(XENON, VANADIUM, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(CAESIUM, TITANIUM, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(BARIUM, SCANDIUM, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(LANTHANUM, CALCIUM, 5);
                mutation19.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(CERIUM, POTASSIUM, 5);
                mutation20.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(PRASEODYMIUM, ARGON, 5);
                mutation21.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(NEODYMIUM, CHLORINE, 5);
                mutation22.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(PROMETHIUM, SULFUR, 4);
                mutation23.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(SAMARIUM, PHOSPHORUS, 4);
                mutation24.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(EUROPIUM, SILICON, 4);
                mutation25.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(GADOLINIUM, ALUMINIUM, 4);
                mutation26.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(TERBIUM, MAGNESIUM, 3);
                mutation27.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(DYSPROSIUM, SODIUM, 3);
                mutation28.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(HOLMIUM, NEON, 3);
                mutation29.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(ERBIUM, FLUORINE, 3);
                mutation30.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(THULIUM, OXYGEN, 2);
                mutation31.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(YTTERBIUM, NITROGEN, 2);
                mutation32.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(LUTETIUM, CARBON, 2);
                mutation33.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(HAFNIUM, BORON, 2);
                mutation34.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(TANTALUM, BERYLLIUM, 1);
                mutation35.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(TUNGSTEN, LITHIUM, 1);
                mutation36.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(RHENIUM, HELIUM, 1);
                mutation37.addMutationCondition(new MaterialMutationCondition(Iridium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(OSMIUM, HYDROGEN, 1);
                mutation38.addMutationCondition(new MaterialMutationCondition(Iridium));
            }),
    PLATINUM(GJBranchDefinition.GJ_RAREMETAL, "Platina", false, 0xE6E6E6, 0xFFFFCC,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.PLATINUM), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.IRIDIUM), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(ZIRCONIUM, STRONTIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation2 = dis.registerMutation(NIOBIUM, RUBIDIUM, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation3 = dis.registerMutation(MOLYBDENUM, KRYPTON, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation4 = dis.registerMutation(TECHNETIUM, BROMINE, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation5 = dis.registerMutation(RUTHENIUM, SELENIUM, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation6 = dis.registerMutation(RHODIUM, ARSENIC, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation7 = dis.registerMutation(PALLADIUM, GERMANIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation8 = dis.registerMutation(SILVER, GALLIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation9 = dis.registerMutation(CADMIUM, ZINC, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation10 = dis.registerMutation(INDIUM, COPPER, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation11 = dis.registerMutation(TIN, NICKEL, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation12 = dis.registerMutation(ANTIMONY, COBALT, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation13 = dis.registerMutation(TELLURIUM, IRON, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation14 = dis.registerMutation(IODINE, MANGANESE, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation15 = dis.registerMutation(XENON, CHROME, 6);
                mutation15.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation16 = dis.registerMutation(CAESIUM, VANADIUM, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation17 = dis.registerMutation(BARIUM, TITANIUM, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation18 = dis.registerMutation(LANTHANUM, SCANDIUM, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation19 = dis.registerMutation(CERIUM, CALCIUM, 5);
                mutation19.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation20 = dis.registerMutation(PRASEODYMIUM, POTASSIUM, 5);
                mutation20.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation21 = dis.registerMutation(NEODYMIUM, ARGON, 5);
                mutation21.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation22 = dis.registerMutation(PROMETHIUM, CHLORINE, 5);
                mutation22.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation23 = dis.registerMutation(SAMARIUM, SULFUR, 4);
                mutation23.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation24 = dis.registerMutation(EUROPIUM, PHOSPHORUS, 4);
                mutation24.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation25 = dis.registerMutation(GADOLINIUM, SILICON, 4);
                mutation25.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation26 = dis.registerMutation(TERBIUM, ALUMINIUM, 4);
                mutation26.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation27 = dis.registerMutation(DYSPROSIUM, MAGNESIUM, 3);
                mutation27.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation28 = dis.registerMutation(HOLMIUM, SODIUM, 3);
                mutation28.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation29 = dis.registerMutation(ERBIUM, NEON, 3);
                mutation29.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation30 = dis.registerMutation(THULIUM, FLUORINE, 3);
                mutation30.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation31 = dis.registerMutation(YTTERBIUM, OXYGEN, 2);
                mutation31.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation32 = dis.registerMutation(LUTETIUM, NITROGEN, 2);
                mutation32.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation33 = dis.registerMutation(HAFNIUM, CARBON, 2);
                mutation33.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation34 = dis.registerMutation(TANTALUM, BORON, 2);
                mutation34.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation35 = dis.registerMutation(TUNGSTEN, BERYLLIUM, 1);
                mutation35.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation36 = dis.registerMutation(RHENIUM, LITHIUM, 1);
                mutation36.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation37 = dis.registerMutation(OSMIUM, HELIUM, 1);
                mutation37.addMutationCondition(new MaterialMutationCondition(Platinum));
                IBeeMutationBuilder mutation38 = dis.registerMutation(IRIDIUM, HYDROGEN, 1);
                mutation38.addMutationCondition(new MaterialMutationCondition(Platinum));
            }),
    GOLD(GJBranchDefinition.GJ_RAREMETAL, "Aurum", true, 0xEBC633, 0xEDCC47,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.GOLD), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.NICKEL), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.WARM);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(ZIRCONIUM, YTTRIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation2 = dis.registerMutation(NIOBIUM, STRONTIUM, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation3 = dis.registerMutation(MOLYBDENUM, RUBIDIUM, 10);
                mutation3.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation4 = dis.registerMutation(TECHNETIUM, KRYPTON, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation5 = dis.registerMutation(RUTHENIUM, BROMINE, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation6 = dis.registerMutation(RHODIUM, SELENIUM, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation7 = dis.registerMutation(PALLADIUM, ARSENIC, 9);
                mutation7.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation8 = dis.registerMutation(SILVER, GERMANIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation9 = dis.registerMutation(CADMIUM, GALLIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation10 = dis.registerMutation(INDIUM, ZINC, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation11 = dis.registerMutation(TIN, COPPER, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation12 = dis.registerMutation(ANTIMONY, NICKEL, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation13 = dis.registerMutation(TELLURIUM, COBALT, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation14 = dis.registerMutation(IODINE, IRON, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation15 = dis.registerMutation(XENON, MANGANESE, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation16 = dis.registerMutation(CAESIUM, CHROME, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation17 = dis.registerMutation(BARIUM, VANADIUM, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation18 = dis.registerMutation(LANTHANUM, TITANIUM, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation19 = dis.registerMutation(CERIUM, SCANDIUM, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation20 = dis.registerMutation(PRASEODYMIUM, CALCIUM, 5);
                mutation20.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation21 = dis.registerMutation(NEODYMIUM, POTASSIUM, 5);
                mutation21.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation22 = dis.registerMutation(PROMETHIUM, ARGON, 5);
                mutation22.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation23 = dis.registerMutation(SAMARIUM, CHLORINE, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation24 = dis.registerMutation(EUROPIUM, SULFUR, 4);
                mutation24.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation25 = dis.registerMutation(GADOLINIUM, PHOSPHORUS, 4);
                mutation25.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation26 = dis.registerMutation(TERBIUM, SILICON, 4);
                mutation26.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation27 = dis.registerMutation(DYSPROSIUM, ALUMINIUM, 4);
                mutation27.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation28 = dis.registerMutation(HOLMIUM, MAGNESIUM, 3);
                mutation28.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation29 = dis.registerMutation(ERBIUM, SODIUM, 3);
                mutation29.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation30 = dis.registerMutation(THULIUM, NEON, 3);
                mutation30.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation31 = dis.registerMutation(YTTERBIUM, FLUORINE, 3);
                mutation31.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation32 = dis.registerMutation(LUTETIUM, OXYGEN, 2);
                mutation32.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation33 = dis.registerMutation(HAFNIUM, NITROGEN, 2);
                mutation33.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation34 = dis.registerMutation(TANTALUM, CARBON, 2);
                mutation34.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation35 = dis.registerMutation(TUNGSTEN, BORON, 2);
                mutation35.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation36 = dis.registerMutation(RHENIUM, BERYLLIUM, 1);
                mutation36.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation37 = dis.registerMutation(OSMIUM, LITHIUM, 1);
                mutation37.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation38 = dis.registerMutation(IRIDIUM, HELIUM, 1);
                mutation38.addMutationCondition(new MaterialMutationCondition(Gold));
                IBeeMutationBuilder mutation39 = dis.registerMutation(PLATINUM, HYDROGEN, 1);
                mutation39.addMutationCondition(new MaterialMutationCondition(Gold));
            }),
    QUICKSILVER(GJBranchDefinition.GJ_METAL, "Mercuria", false, 0xE6DCDC, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.QUICKSILVER), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(NIOBIUM, YTTRIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation2 = dis.registerMutation(MOLYBDENUM, STRONTIUM, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation3 = dis.registerMutation(TECHNETIUM, RUBIDIUM, 10);
                mutation3.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation4 = dis.registerMutation(RUTHENIUM, KRYPTON, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation5 = dis.registerMutation(RHODIUM, BROMINE, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation6 = dis.registerMutation(PALLADIUM, SELENIUM, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation7 = dis.registerMutation(SILVER, ARSENIC, 9);
                mutation7.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation8 = dis.registerMutation(CADMIUM, GERMANIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation9 = dis.registerMutation(INDIUM, GALLIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation10 = dis.registerMutation(TIN, ZINC, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation11 = dis.registerMutation(ANTIMONY, COPPER, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation12 = dis.registerMutation(TELLURIUM, NICKEL, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation13 = dis.registerMutation(IODINE, COPPER, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation14 = dis.registerMutation(XENON, IRON, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation15 = dis.registerMutation(CAESIUM, MANGANESE, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation16 = dis.registerMutation(BARIUM, CHROME, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation17 = dis.registerMutation(LANTHANUM, VANADIUM, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation18 = dis.registerMutation(CERIUM, TITANIUM, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation19 = dis.registerMutation(PRASEODYMIUM, SCANDIUM, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation20 = dis.registerMutation(NEODYMIUM, CALCIUM, 5);
                mutation20.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation21 = dis.registerMutation(PROMETHIUM, POTASSIUM, 5);
                mutation21.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation22 = dis.registerMutation(SAMARIUM, ARGON, 5);
                mutation22.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation23 = dis.registerMutation(EUROPIUM, CHLORINE, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation24 = dis.registerMutation(GADOLINIUM, SULFUR, 4);
                mutation24.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation25 = dis.registerMutation(TERBIUM, PHOSPHORUS, 4);
                mutation25.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation26 = dis.registerMutation(DYSPROSIUM, SILICON, 4);
                mutation26.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation27 = dis.registerMutation(HOLMIUM, ALUMINIUM, 4);
                mutation27.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation28 = dis.registerMutation(ERBIUM, MAGNESIUM, 3);
                mutation28.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation29 = dis.registerMutation(THULIUM, SODIUM, 3);
                mutation29.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation30 = dis.registerMutation(YTTERBIUM, NEON, 3);
                mutation30.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation31 = dis.registerMutation(LUTETIUM, FLUORINE, 3);
                mutation31.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation32 = dis.registerMutation(HAFNIUM, OXYGEN, 2);
                mutation32.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation33 = dis.registerMutation(TANTALUM, NITROGEN, 2);
                mutation33.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation34 = dis.registerMutation(TUNGSTEN, CARBON, 2);
                mutation34.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation35 = dis.registerMutation(RHENIUM, BORON, 2);
                mutation35.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation36 = dis.registerMutation(OSMIUM, BERYLLIUM, 1);
                mutation36.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation37 = dis.registerMutation(IRIDIUM, LITHIUM, 1);
                mutation37.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation38 = dis.registerMutation(PLATINUM, HELIUM, 1);
                mutation38.addMutationCondition(new MaterialMutationCondition(Mercury));
                IBeeMutationBuilder mutation39 = dis.registerMutation(GOLD, HYDROGEN, 1);
                mutation39.addMutationCondition(new MaterialMutationCondition(Mercury));
            }),
    THALLIUM(GJBranchDefinition.GJ_RAREMETAL, "Thallia", false, 0xC1C1DE, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.THALLIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(NIOBIUM, ZIRCONIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(MOLYBDENUM, YTTRIUM, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(TECHNETIUM, STRONTIUM, 10);
                mutation3.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(RUTHENIUM, RUBIDIUM, 10);
                mutation4.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(RHODIUM, KRYPTON, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(PALLADIUM, BROMINE, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(SILVER, SELENIUM, 9);
                mutation7.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(CADMIUM, ARSENIC, 9);
                mutation8.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(INDIUM, GERMANIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(TIN, GALLIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(ANTIMONY, ZINC, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(TELLURIUM, COPPER, 8);
                mutation12.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(IODINE, NICKEL, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(XENON, COBALT, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(CAESIUM, IRON, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(BARIUM, MANGANESE, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(LANTHANUM, CHROME, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(CERIUM, VANADIUM, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(PRASEODYMIUM, TITANIUM, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(NEODYMIUM, SCANDIUM, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(PROMETHIUM, CALCIUM, 5);
                mutation21.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(SAMARIUM, POTASSIUM, 5);
                mutation22.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(EUROPIUM, ARGON, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(GADOLINIUM, CHLORINE, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(TERBIUM, SULFUR, 4);
                mutation25.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(DYSPROSIUM, PHOSPHORUS, 4);
                mutation26.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(HOLMIUM, SILICON, 4);
                mutation27.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(ERBIUM, ALUMINIUM, 4);
                mutation28.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(THULIUM, MAGNESIUM, 3);
                mutation29.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(YTTERBIUM, SODIUM, 3);
                mutation30.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(LUTETIUM, NEON, 3);
                mutation31.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(HAFNIUM, FLUORINE, 3);
                mutation32.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(TANTALUM, OXYGEN, 2);
                mutation33.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(TUNGSTEN, NITROGEN, 2);
                mutation34.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(RHENIUM, CARBON, 2);
                mutation35.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(OSMIUM, BORON, 2);
                mutation36.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(IRIDIUM, BERYLLIUM, 1);
                mutation37.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(PLATINUM, LITHIUM, 1);
                mutation38.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(GOLD, HELIUM, 1);
                mutation39.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(QUICKSILVER, HYDROGEN, 1);
                mutation40.addMutationCondition(new MaterialMutationCondition(Thallium));
            }),
    LEAD(GJBranchDefinition.GJ_METAL, "Plumbum", true, 0x666699, 0xA3A3CC,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.LEAD), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.SULFUR), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.DAMP);
                beeSpecies.setTemperature(EnumTemperature.WARM);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(MOLYBDENUM, ZIRCONIUM, 8);
                mutation1.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation2 = dis.registerMutation(TECHNETIUM, YTTRIUM, 8);
                mutation2.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation3 = dis.registerMutation(RUTHENIUM, STRONTIUM, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation4 = dis.registerMutation(RHODIUM, RUBIDIUM, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation5 = dis.registerMutation(PALLADIUM, KRYPTON, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation6 = dis.registerMutation(SILVER, BROMINE, 7);
                mutation6.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation7 = dis.registerMutation(CADMIUM, SELENIUM, 7);
                mutation7.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation8 = dis.registerMutation(INDIUM, ARSENIC, 7);
                mutation8.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation9 = dis.registerMutation(TIN, GERMANIUM, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation10 = dis.registerMutation(ANTIMONY, GALLIUM, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation11 = dis.registerMutation(TELLURIUM, ZINC, 6);
                mutation11.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation12 = dis.registerMutation(IODINE, COPPER, 6);
                mutation12.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation13 = dis.registerMutation(XENON, NICKEL, 6);
                mutation13.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation14 = dis.registerMutation(CAESIUM, COBALT, 6);
                mutation14.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation15 = dis.registerMutation(BARIUM, IRON, 6);
                mutation15.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation16 = dis.registerMutation(LANTHANUM, MANGANESE, 5);
                mutation16.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation17 = dis.registerMutation(CERIUM, CHROME, 5);
                mutation17.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation18 = dis.registerMutation(PRASEODYMIUM, VANADIUM, 5);
                mutation18.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation19 = dis.registerMutation(NEODYMIUM, TITANIUM, 5);
                mutation19.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation20 = dis.registerMutation(PROMETHIUM, SCANDIUM, 5);
                mutation20.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation21 = dis.registerMutation(SAMARIUM, CALCIUM, 4);
                mutation21.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation22 = dis.registerMutation(EUROPIUM, POTASSIUM, 4);
                mutation22.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation23 = dis.registerMutation(GADOLINIUM, ARGON, 4);
                mutation23.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation24 = dis.registerMutation(TERBIUM, CHLORINE, 4);
                mutation24.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation25 = dis.registerMutation(DYSPROSIUM, SULFUR, 4);
                mutation25.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation26 = dis.registerMutation(HOLMIUM, PHOSPHORUS, 3);
                mutation26.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation27 = dis.registerMutation(ERBIUM, SILICON, 3);
                mutation27.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation28 = dis.registerMutation(THULIUM, ALUMINIUM, 3);
                mutation28.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation29 = dis.registerMutation(YTTERBIUM, MAGNESIUM, 3);
                mutation29.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation30 = dis.registerMutation(LUTETIUM, SODIUM, 3);
                mutation30.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation31 = dis.registerMutation(HAFNIUM, NEON, 2);
                mutation31.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation32 = dis.registerMutation(TANTALUM, FLUORINE, 2);
                mutation32.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation33 = dis.registerMutation(TUNGSTEN, OXYGEN, 2);
                mutation33.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation34 = dis.registerMutation(RHENIUM, NITROGEN, 2);
                mutation34.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation35 = dis.registerMutation(OSMIUM, CARBON, 2);
                mutation35.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation36 = dis.registerMutation(IRIDIUM, BORON, 1);
                mutation36.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation37 = dis.registerMutation(PLATINUM, BERYLLIUM, 1);
                mutation37.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation38 = dis.registerMutation(GOLD, LITHIUM, 1);
                mutation38.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation39 = dis.registerMutation(QUICKSILVER, HELIUM, 1);
                mutation39.addMutationCondition(new MaterialMutationCondition(Lead));
                IBeeMutationBuilder mutation40 = dis.registerMutation(THALLIUM, HYDROGEN, 1);
                mutation40.addMutationCondition(new MaterialMutationCondition(Lead));
            }),
    BISMUTH(GJBranchDefinition.GJ_RADIOACTIVE, "Bismuthia", false, 0x64A0A0, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.BISMUTH), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(MOLYBDENUM, NIOBIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(TECHNETIUM, ZIRCONIUM, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(RUTHENIUM, YTTRIUM, 10);
                mutation3.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(PALLADIUM, RUBIDIUM, 10);
                mutation4.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(SILVER, KRYPTON, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(CADMIUM, BROMINE, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(INDIUM, SELENIUM, 9);
                mutation7.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(TIN, ARSENIC, 9);
                mutation8.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(ANTIMONY, GERMANIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(TELLURIUM, GALLIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(IODINE, ZINC, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(XENON, COPPER, 8);
                mutation12.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(CAESIUM, NICKEL, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(BARIUM, COBALT, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(LANTHANUM, IRON, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(CERIUM, MANGANESE, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(PRASEODYMIUM, CHROME, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(NEODYMIUM, VANADIUM, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(PROMETHIUM, TITANIUM, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(SAMARIUM, SCANDIUM, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(EUROPIUM, CALCIUM, 5);
                mutation21.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(GADOLINIUM, POTASSIUM, 5);
                mutation22.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(TERBIUM, ARGON, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(DYSPROSIUM, CHLORINE, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(HOLMIUM, SULFUR, 4);
                mutation25.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(ERBIUM, PHOSPHORUS, 4);
                mutation26.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(THULIUM, SILICON, 4);
                mutation27.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(YTTERBIUM, ALUMINIUM, 4);
                mutation28.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(LUTETIUM, MAGNESIUM, 3);
                mutation29.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(HAFNIUM, SODIUM, 3);
                mutation30.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(TANTALUM, NEON, 3);
                mutation31.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(TUNGSTEN, FLUORINE, 3);
                mutation32.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(RHENIUM, OXYGEN, 2);
                mutation33.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(OSMIUM, NITROGEN, 2);
                mutation34.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(IRIDIUM, CARBON, 2);
                mutation35.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(PLATINUM, BORON, 2);
                mutation36.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(GOLD, BERYLLIUM, 1);
                mutation37.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(QUICKSILVER, LITHIUM, 1);
                mutation38.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(THALLIUM, HELIUM, 1);
                mutation39.addMutationCondition(new MaterialMutationCondition(Thallium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(LEAD, HYDROGEN, 1);
                mutation40.addMutationCondition(new MaterialMutationCondition(Thallium));
            }),
    POLONIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Polonia", false, 0xC9D47E, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.POLONIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(TECHNETIUM, NIOBIUM, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(RUTHENIUM, ZIRCONIUM, 8);
                mutation2.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(RHODIUM, YTTRIUM, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(PALLADIUM, STRONTIUM, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(SILVER, RUBIDIUM, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(CADMIUM, KRYPTON, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(INDIUM, BROMINE, 7);
                mutation7.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(TIN, SELENIUM, 7);
                mutation8.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(ANTIMONY, ARSENIC, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(TELLURIUM, GERMANIUM, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(IODINE, GALLIUM, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(XENON, ZINC, 6);
                mutation12.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(CAESIUM, COPPER, 6);
                mutation13.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(BARIUM, NICKEL, 6);
                mutation14.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(LANTHANUM, COBALT, 6);
                mutation15.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(CERIUM, IRON, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(PRASEODYMIUM, MANGANESE, 5);
                mutation17.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(NEODYMIUM, CHROME, 5);
                mutation18.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(PROMETHIUM, VANADIUM, 5);
                mutation19.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(SAMARIUM, TITANIUM, 5);
                mutation20.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(EUROPIUM, SCANDIUM, 5);
                mutation21.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(GADOLINIUM, CALCIUM, 4);
                mutation22.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(TERBIUM, POTASSIUM, 4);
                mutation23.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(DYSPROSIUM, ARGON, 4);
                mutation24.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(HOLMIUM, CHLORINE, 4);
                mutation25.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(ERBIUM, SULFUR, 4);
                mutation26.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(THULIUM, PHOSPHORUS, 3);
                mutation27.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(YTTERBIUM, SILICON, 3);
                mutation28.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(LUTETIUM, ALUMINIUM, 3);
                mutation29.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(HAFNIUM, MAGNESIUM, 3);
                mutation30.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(TANTALUM, SODIUM, 3);
                mutation31.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(TUNGSTEN, NEON, 2);
                mutation32.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(RHENIUM, FLUORINE, 2);
                mutation33.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(OSMIUM, OXYGEN, 2);
                mutation34.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(IRIDIUM, NITROGEN, 2);
                mutation35.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(PLATINUM, CARBON, 2);
                mutation36.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(GOLD, BORON, 1);
                mutation37.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(QUICKSILVER, BERYLLIUM, 1);
                mutation38.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(THALLIUM, LITHIUM, 1);
                mutation39.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(LEAD, HELIUM, 1);
                mutation40.addMutationCondition(new MaterialMutationCondition(Polonium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(BISMUTH, HYDROGEN, 1);
                mutation41.addMutationCondition(new MaterialMutationCondition(Polonium));
            }),
    ASTATINE(GJBranchDefinition.GJ_HALOGENS, "Astatina", false, 0x241A24, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.ASTATINE), 0.10f);
            },
            template -> AlleleHelper.getInstance().set(template, EFFECT, AlleleEffects.effectRadioactive),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(TECHNETIUM, MOLYBDENUM, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation2 = dis.registerMutation(RUTHENIUM, NIOBIUM, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation3 = dis.registerMutation(RHODIUM, ZIRCONIUM, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation4 = dis.registerMutation(PALLADIUM, YTTRIUM, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation5 = dis.registerMutation(SILVER, STRONTIUM, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation6 = dis.registerMutation(CADMIUM, RUBIDIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation7 = dis.registerMutation(INDIUM, KRYPTON, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation8 = dis.registerMutation(TIN, BROMINE, 7);
                mutation8.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation9 = dis.registerMutation(ANTIMONY, SELENIUM, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation10 = dis.registerMutation(TELLURIUM, ARSENIC, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation11 = dis.registerMutation(IODINE, GERMANIUM, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation12 = dis.registerMutation(XENON, GALLIUM, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation13 = dis.registerMutation(CAESIUM, ZINC, 6);
                mutation13.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation14 = dis.registerMutation(BARIUM, COPPER, 6);
                mutation14.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation15 = dis.registerMutation(LANTHANUM, NICKEL, 6);
                mutation15.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation16 = dis.registerMutation(CERIUM, COBALT, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation17 = dis.registerMutation(PRASEODYMIUM, IRON, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation18 = dis.registerMutation(NEODYMIUM, MANGANESE, 5);
                mutation18.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation19 = dis.registerMutation(PROMETHIUM, CHROME, 5);
                mutation19.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation20 = dis.registerMutation(SAMARIUM, VANADIUM, 5);
                mutation20.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation21 = dis.registerMutation(EUROPIUM, TITANIUM, 5);
                mutation21.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation22 = dis.registerMutation(GADOLINIUM, SCANDIUM, 5);
                mutation22.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation23 = dis.registerMutation(TERBIUM, CALCIUM, 4);
                mutation23.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation24 = dis.registerMutation(DYSPROSIUM, POTASSIUM, 4);
                mutation24.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation25 = dis.registerMutation(HOLMIUM, ARGON, 4);
                mutation25.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation26 = dis.registerMutation(ERBIUM, CHLORINE, 4);
                mutation26.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation27 = dis.registerMutation(THULIUM, SULFUR, 4);
                mutation27.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation28 = dis.registerMutation(YTTERBIUM, PHOSPHORUS, 3);
                mutation28.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation29 = dis.registerMutation(LUTETIUM, SILICON, 3);
                mutation29.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation30 = dis.registerMutation(HAFNIUM, ALUMINIUM, 3);
                mutation30.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation31 = dis.registerMutation(TANTALUM, MAGNESIUM, 3);
                mutation31.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation32 = dis.registerMutation(TUNGSTEN, SODIUM, 3);
                mutation32.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation33 = dis.registerMutation(RHENIUM, NEON, 2);
                mutation33.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation34 = dis.registerMutation(OSMIUM, FLUORINE, 2);
                mutation34.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation35 = dis.registerMutation(IRIDIUM, OXYGEN, 2);
                mutation35.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation36 = dis.registerMutation(PLATINUM, NITROGEN, 2);
                mutation36.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation37 = dis.registerMutation(GOLD, CARBON, 2);
                mutation37.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation38 = dis.registerMutation(QUICKSILVER, BORON, 1);
                mutation38.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation39 = dis.registerMutation(THALLIUM, BERYLLIUM, 1);
                mutation39.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation40 = dis.registerMutation(LEAD, LITHIUM, 1);
                mutation40.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation41 = dis.registerMutation(BISMUTH, HELIUM, 1);
                mutation41.addMutationCondition(new MaterialMutationCondition(Astatine));
                IBeeMutationBuilder mutation42 = dis.registerMutation(POLONIUM, HYDROGEN, 1);
                mutation42.addMutationCondition(new MaterialMutationCondition(Astatine));
            }),
    RADON(GJBranchDefinition.GJ_NOBLEGAS, "Rada", false, 0xFF39FF, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.RADON), 0.10f);
            },
            template -> AlleleHelper.getInstance().set(template, EFFECT, AlleleEffects.effectRadioactive),
            dis -> {
                dis.registerMutation(RUTHENIUM, MOLYBDENUM, 9);
                dis.registerMutation(RHODIUM, NIOBIUM, 9);
                dis.registerMutation(PALLADIUM, ZIRCONIUM, 8);
                dis.registerMutation(SILVER, YTTRIUM, 8);
                dis.registerMutation(CADMIUM, STRONTIUM, 8);
                dis.registerMutation(INDIUM, RUBIDIUM, 8);
                dis.registerMutation(TIN, KRYPTON, 8);
                dis.registerMutation(ANTIMONY, BROMINE, 7);
                dis.registerMutation(TELLURIUM, SELENIUM, 7);
                dis.registerMutation(IODINE, ASTATINE, 7);
                dis.registerMutation(XENON, GERMANIUM, 7);
                dis.registerMutation(CAESIUM, GALLIUM, 7);
                dis.registerMutation(BARIUM, ZINC, 6);
                dis.registerMutation(LANTHANUM, COPPER, 6);
                dis.registerMutation(CERIUM, NICKEL, 6);
                dis.registerMutation(PRASEODYMIUM, COBALT, 6);
                dis.registerMutation(NEODYMIUM, IRON, 6);
                dis.registerMutation(PROMETHIUM, MANGANESE, 5);
                dis.registerMutation(SAMARIUM, CHROME, 5);
                dis.registerMutation(EUROPIUM, VANADIUM, 5);
                dis.registerMutation(GADOLINIUM, TITANIUM, 5);
                dis.registerMutation(TERBIUM, SCANDIUM, 5);
                dis.registerMutation(DYSPROSIUM, CALCIUM, 4);
                dis.registerMutation(HOLMIUM, POTASSIUM, 4);
                dis.registerMutation(ERBIUM, ARGON, 4);
                dis.registerMutation(THULIUM, CHLORINE, 4);
                dis.registerMutation(YTTERBIUM, SULFUR, 4);
                dis.registerMutation(LUTETIUM, PHOSPHORUS, 3);
                dis.registerMutation(HAFNIUM, SILICON, 3);
                dis.registerMutation(TANTALUM, ALUMINIUM, 3);
                dis.registerMutation(TUNGSTEN, MAGNESIUM, 3);
                dis.registerMutation(RHENIUM, SODIUM, 3);
                dis.registerMutation(OSMIUM, NEON, 2);
                dis.registerMutation(IRIDIUM, FLUORINE, 2);
                dis.registerMutation(PLATINUM, OXYGEN, 2);
                dis.registerMutation(GOLD, NITROGEN, 2);
                dis.registerMutation(QUICKSILVER, CARBON, 2);
                dis.registerMutation(THALLIUM, BORON, 1);
                dis.registerMutation(LEAD, BERYLLIUM, 1);
                dis.registerMutation(BISMUTH, LITHIUM, 1);
                dis.registerMutation(POLONIUM, HELIUM, 1);
                dis.registerMutation(ASTATINE, HYDROGEN, 1);
            }),
    FRANCIUM(GJBranchDefinition.GJ_ALKALINEMETAL, "Francia", false, 0xAAAAAA, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.FRANCIUM), 0.10f);
            },
            template -> AlleleHelper.getInstance().set(template, EFFECT, AlleleEffects.effectRadioactive),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(RUTHENIUM, TECHNETIUM, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(RHODIUM, MOLYBDENUM, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(PALLADIUM, NIOBIUM, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(SILVER, ZIRCONIUM, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(CADMIUM, YTTRIUM, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(INDIUM, STRONTIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(TIN, RUBIDIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(ANTIMONY, KRYPTON, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(TELLURIUM, BROMINE, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(IODINE, SELENIUM, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(XENON, ARSENIC, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(CAESIUM, GERMANIUM, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(BARIUM, GALLIUM, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(LANTHANUM, ZINC, 6);
                mutation14.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(CERIUM, COPPER, 6);
                mutation15.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(PRASEODYMIUM, NICKEL, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(NEODYMIUM, COBALT, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(PROMETHIUM, IRON, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(SAMARIUM, MANGANESE, 5);
                mutation19.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(EUROPIUM, CHROME, 5);
                mutation20.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(GADOLINIUM, VANADIUM, 5);
                mutation21.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(TERBIUM, TITANIUM, 5);
                mutation22.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(DYSPROSIUM, SCANDIUM, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(HOLMIUM, CALCIUM, 4);
                mutation24.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(ERBIUM, POTASSIUM, 4);
                mutation25.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(THULIUM, ARGON, 4);
                mutation26.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(YTTERBIUM, CHLORINE, 4);
                mutation27.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(LUTETIUM, SULFUR, 4);
                mutation28.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(HAFNIUM, PHOSPHORUS, 3);
                mutation29.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(TANTALUM, SILICON, 3);
                mutation30.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(TUNGSTEN, ALUMINIUM, 3);
                mutation31.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(RHENIUM, MAGNESIUM, 3);
                mutation32.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(OSMIUM, SODIUM, 3);
                mutation33.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(IRIDIUM, NEON, 2);
                mutation34.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(PLATINUM, FLUORINE, 2);
                mutation35.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(GOLD, OXYGEN, 2);
                mutation36.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(QUICKSILVER, NITROGEN, 2);
                mutation37.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(THALLIUM, CARBON, 2);
                mutation38.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(LEAD, BORON, 1);
                mutation39.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(BISMUTH, BERYLLIUM, 1);
                mutation40.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(POLONIUM, LITHIUM, 1);
                mutation41.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(ASTATINE, HELIUM, 1);
                mutation42.addMutationCondition(new MaterialMutationCondition(Francium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(RADON, HYDROGEN, 1);
                mutation43.addMutationCondition(new MaterialMutationCondition(Francium));
            }),
    RADIUM(GJBranchDefinition.GJ_ALKALINEMETAL, "Radia", false, 0xFFFFCD, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.RADIUM), 0.10f);
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, EFFECT, AlleleEffects.effectRadioactive),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(RHODIUM, TECHNETIUM, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(PALLADIUM, MOLYBDENUM, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(SILVER, NIOBIUM, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(CADMIUM, ZIRCONIUM, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(INDIUM, YTTRIUM, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(TIN, STRONTIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(ANTIMONY, RUBIDIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(TELLURIUM, KRYPTON, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(IODINE, BROMINE, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(XENON, SELENIUM, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(CAESIUM, ARSENIC, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(BARIUM, GERMANIUM, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(LANTHANUM, GALLIUM, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(CERIUM, ZINC, 6);
                mutation14.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(PRASEODYMIUM, COPPER, 6);
                mutation15.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(NEODYMIUM, NICKEL, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(PROMETHIUM, COBALT, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(SAMARIUM, IRON, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(EUROPIUM, MANGANESE, 5);
                mutation19.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(GADOLINIUM, CHROME, 5);
                mutation20.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(TERBIUM, VANADIUM, 5);
                mutation21.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(DYSPROSIUM, TITANIUM, 5);
                mutation22.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(HOLMIUM, SCANDIUM, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(ERBIUM, CALCIUM, 4);
                mutation24.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(THULIUM, POTASSIUM, 4);
                mutation25.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(YTTERBIUM, ARGON, 4);
                mutation26.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(LUTETIUM, CHLORINE, 4);
                mutation27.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(HAFNIUM, SULFUR, 4);
                mutation28.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(TANTALUM, PHOSPHORUS, 3);
                mutation29.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(TUNGSTEN, SILICON, 3);
                mutation30.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(RHENIUM, ALUMINIUM, 3);
                mutation31.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(OSMIUM, MAGNESIUM, 3);
                mutation32.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(IRIDIUM, SODIUM, 3);
                mutation33.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(PLATINUM, NEON, 2);
                mutation34.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(GOLD, FLUORINE, 2);
                mutation35.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(QUICKSILVER, OXYGEN, 2);
                mutation36.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(THALLIUM, NITROGEN, 2);
                mutation37.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(LEAD, CARBON, 2);
                mutation38.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(BISMUTH, BORON, 1);
                mutation39.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(POLONIUM, BERYLLIUM, 1);
                mutation40.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(ASTATINE, LITHIUM, 1);
                mutation41.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(RADON, HELIUM, 1);
                mutation42.addMutationCondition(new MaterialMutationCondition(Radium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(FRANCIUM, HYDROGEN, 1);
                mutation43.addMutationCondition(new MaterialMutationCondition(Radium));
            }),
    ACTINIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Actinia", false, 0xC3D1FF, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.ACTINIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(RHODIUM, RUTHENIUM, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(PALLADIUM, TECHNETIUM, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(SILVER, MOLYBDENUM, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(CADMIUM, NIOBIUM, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(INDIUM, ZIRCONIUM, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(TIN, YTTRIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(ANTIMONY, STRONTIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(TELLURIUM, RUBIDIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(IODINE, KRYPTON, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(XENON, BROMINE, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(CAESIUM, SELENIUM, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(BARIUM, ARSENIC, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(LANTHANUM, GERMANIUM, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(CERIUM, GALLIUM, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(PRASEODYMIUM, ZINC, 6);
                mutation15.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(NEODYMIUM, COPPER, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(PROMETHIUM, NICKEL, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(SAMARIUM, COBALT, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(EUROPIUM, IRON, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(GADOLINIUM, MANGANESE, 5);
                mutation20.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(TERBIUM, CHROME, 5);
                mutation21.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(DYSPROSIUM, VANADIUM, 5);
                mutation22.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(HOLMIUM, TITANIUM, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(ERBIUM, SCANDIUM, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(THULIUM, CALCIUM, 4);
                mutation25.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(YTTERBIUM, POTASSIUM, 4);
                mutation26.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(LUTETIUM, ARGON, 4);
                mutation27.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(HAFNIUM, CHLORINE, 4);
                mutation28.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(TANTALUM, SULFUR, 4);
                mutation29.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(TUNGSTEN, PHOSPHORUS, 3);
                mutation30.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(RHENIUM, SILICON, 3);
                mutation31.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(OSMIUM, ALUMINIUM, 3);
                mutation32.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(IRIDIUM, MAGNESIUM, 3);
                mutation33.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(PLATINUM, SODIUM, 3);
                mutation34.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(GOLD, NEON, 2);
                mutation35.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(QUICKSILVER, FLUORINE, 2);
                mutation36.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(THALLIUM, OXYGEN, 2);
                mutation37.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(LEAD, NITROGEN, 2);
                mutation38.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(BISMUTH, CARBON, 2);
                mutation39.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(POLONIUM, BORON, 1);
                mutation40.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(ASTATINE, BERYLLIUM, 1);
                mutation41.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(RADON, LITHIUM, 1);
                mutation42.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(FRANCIUM, HELIUM, 1);
                mutation43.addMutationCondition(new MaterialMutationCondition(Actinium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(RADIUM, HYDROGEN, 1);
                mutation44.addMutationCondition(new MaterialMutationCondition(Actinium));
            }),
    THORIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Thorium", false, 0x001E00, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.THORIUM), 0.10f);
                beeSpecies.setHasEffect();
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(PALLADIUM, RUTHENIUM, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(SILVER, TECHNETIUM, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(CADMIUM, MOLYBDENUM, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(INDIUM, NIOBIUM, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(TIN, ZIRCONIUM, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(ANTIMONY, YTTRIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(TELLURIUM, STRONTIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(IODINE, RUBIDIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(XENON, KRYPTON, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(CAESIUM, BROMINE, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(BARIUM, SELENIUM, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(LANTHANUM, ARSENIC, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(CERIUM, GERMANIUM, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(PRASEODYMIUM, GALLIUM, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(NEODYMIUM, ZINC, 6);
                mutation15.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(PROMETHIUM, COPPER, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(SAMARIUM, NICKEL, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(EUROPIUM, COBALT, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(GADOLINIUM, IRON, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(TERBIUM, MANGANESE, 5);
                mutation20.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(DYSPROSIUM, CHROME, 5);
                mutation21.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(HOLMIUM, VANADIUM, 5);
                mutation22.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(ERBIUM, TITANIUM, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(THULIUM, SCANDIUM, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(YTTERBIUM, CALCIUM, 4);
                mutation25.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(LUTETIUM, POTASSIUM, 4);
                mutation26.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(HAFNIUM, ARGON, 4);
                mutation27.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(TANTALUM, CHLORINE, 4);
                mutation28.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(TUNGSTEN, SULFUR, 4);
                mutation29.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(RHENIUM, PHOSPHORUS, 3);
                mutation30.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(OSMIUM, SILICON, 3);
                mutation31.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(IRIDIUM, ALUMINIUM, 3);
                mutation32.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(PLATINUM, MAGNESIUM, 3);
                mutation33.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(GOLD, SODIUM, 3);
                mutation34.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(QUICKSILVER, NEON, 2);
                mutation35.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(THALLIUM, FLUORINE, 2);
                mutation36.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(LEAD, OXYGEN, 2);
                mutation37.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(BISMUTH, NITROGEN, 2);
                mutation38.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(POLONIUM, CARBON, 2);
                mutation39.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(ASTATINE, BORON, 1);
                mutation40.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(RADON, BERYLLIUM, 1);
                mutation41.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(FRANCIUM, LITHIUM, 1);
                mutation42.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(RADIUM, HELIUM, 1);
                mutation43.addMutationCondition(new MaterialMutationCondition(Thorium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(ACTINIUM, HYDROGEN, 1);
                mutation44.addMutationCondition(new MaterialMutationCondition(Thorium));
            }),
    PROTACTINIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Protactinium", false, 0xA78B6D, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.PROTACTINIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(PALLADIUM, RHODIUM, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(SILVER, RUTHENIUM, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(CADMIUM, TECHNETIUM, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(INDIUM, MOLYBDENUM, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(TIN, NIOBIUM, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(ANTIMONY, ZIRCONIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(TELLURIUM, YTTRIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(IODINE, STRONTIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(XENON, RUBIDIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(CAESIUM, KRYPTON, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(BARIUM, BROMINE, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(LANTHANUM, SELENIUM, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(CERIUM, ARSENIC, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(PRASEODYMIUM, GERMANIUM, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(NEODYMIUM, GALLIUM, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(PROMETHIUM, ZINC, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(SAMARIUM, COPPER, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(EUROPIUM, NICKEL, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(GADOLINIUM, COBALT, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(TERBIUM, IRON, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(DYSPROSIUM, MANGANESE, 5);
                mutation21.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(HOLMIUM, CHROME, 5);
                mutation22.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(ERBIUM, VANADIUM, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(THULIUM, TITANIUM, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(YTTERBIUM, SCANDIUM, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(LUTETIUM, CALCIUM, 4);
                mutation26.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(HAFNIUM, POTASSIUM, 4);
                mutation27.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(TANTALUM, ARGON, 4);
                mutation28.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(TUNGSTEN, CHLORINE, 4);
                mutation29.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(RHENIUM, SULFUR, 4);
                mutation30.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(OSMIUM, PHOSPHORUS, 3);
                mutation31.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(IRIDIUM, SILICON, 3);
                mutation32.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(PLATINUM, ALUMINIUM, 3);
                mutation33.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(GOLD, MAGNESIUM, 3);
                mutation34.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(QUICKSILVER, SODIUM, 3);
                mutation35.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(THALLIUM, NEON, 2);
                mutation36.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(LEAD, FLUORINE, 2);
                mutation37.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(BISMUTH, OXYGEN, 2);
                mutation38.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(POLONIUM, NITROGEN, 2);
                mutation39.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(ASTATINE, CARBON, 2);
                mutation40.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(RADON, BORON, 1);
                mutation41.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(FRANCIUM, BERYLLIUM, 1);
                mutation42.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(RADIUM, LITHIUM, 1);
                mutation43.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(ACTINIUM, HELIUM, 1);
                mutation44.addMutationCondition(new MaterialMutationCondition(Protactinium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(THORIUM, HYDROGEN, 1);
                mutation45.addMutationCondition(new MaterialMutationCondition(Protactinium));
            }),
    URANIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Ouranos", true, 0x19AF19, 0x169E16,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.URANIUM), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.COLD);
                beeSpecies.setNocturnal();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.LONGEST);
            },
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(SILVER, RHODIUM, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation2 = dis.registerMutation(CADMIUM, RUTHENIUM, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation3 = dis.registerMutation(INDIUM, TECHNETIUM, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation4 = dis.registerMutation(TIN, MOLYBDENUM, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation5 = dis.registerMutation(ANTIMONY, NIOBIUM, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation6 = dis.registerMutation(TELLURIUM, ZIRCONIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation7 = dis.registerMutation(IODINE, YTTRIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation8 = dis.registerMutation(XENON, STRONTIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation9 = dis.registerMutation(CAESIUM, RUBIDIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation10 = dis.registerMutation(BARIUM, KRYPTON, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation11 = dis.registerMutation(LANTHANUM, BROMINE, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation12 = dis.registerMutation(CERIUM, SELENIUM, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation13 = dis.registerMutation(PRASEODYMIUM, ARSENIC, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation14 = dis.registerMutation(NEODYMIUM, GERMANIUM, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation15 = dis.registerMutation(PROMETHIUM, GALLIUM, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation16 = dis.registerMutation(SAMARIUM, ZINC, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation17 = dis.registerMutation(EUROPIUM, COPPER, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation18 = dis.registerMutation(GADOLINIUM, NICKEL, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation19 = dis.registerMutation(TERBIUM, COBALT, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation20 = dis.registerMutation(DYSPROSIUM, IRON, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation21 = dis.registerMutation(HOLMIUM, MANGANESE, 5);
                mutation21.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation22 = dis.registerMutation(ERBIUM, CHROME, 5);
                mutation22.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation23 = dis.registerMutation(THULIUM, VANADIUM, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation24 = dis.registerMutation(YTTERBIUM, TITANIUM, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation25 = dis.registerMutation(LUTETIUM, SCANDIUM, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation26 = dis.registerMutation(HAFNIUM, CALCIUM, 4);
                mutation26.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation27 = dis.registerMutation(TANTALUM, POTASSIUM, 4);
                mutation27.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation28 = dis.registerMutation(TUNGSTEN, ARGON, 4);
                mutation28.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation29 = dis.registerMutation(RHENIUM, CHLORINE, 4);
                mutation29.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation30 = dis.registerMutation(OSMIUM, SULFUR, 4);
                mutation30.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation31 = dis.registerMutation(IRIDIUM, PHOSPHORUS, 3);
                mutation31.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation32 = dis.registerMutation(PLATINUM, SILICON, 3);
                mutation32.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation33 = dis.registerMutation(GOLD, ALUMINIUM, 3);
                mutation33.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation34 = dis.registerMutation(QUICKSILVER, MAGNESIUM, 3);
                mutation34.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation35 = dis.registerMutation(THALLIUM, SODIUM, 3);
                mutation35.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation36 = dis.registerMutation(LEAD, NEON, 2);
                mutation36.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation37 = dis.registerMutation(BISMUTH, FLUORINE, 2);
                mutation37.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation38 = dis.registerMutation(POLONIUM, OXYGEN, 2);
                mutation38.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation39 = dis.registerMutation(ASTATINE, NITROGEN, 2);
                mutation39.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation40 = dis.registerMutation(RADON, CARBON, 2);
                mutation40.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation41 = dis.registerMutation(FRANCIUM, BORON, 1);
                mutation41.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation42 = dis.registerMutation(RADIUM, BERYLLIUM, 1);
                mutation42.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation43 = dis.registerMutation(ACTINIUM, LITHIUM, 1);
                mutation43.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation44 = dis.registerMutation(THORIUM, HELIUM, 1);
                mutation44.addMutationCondition(new MaterialMutationCondition(Uranium238));
                IBeeMutationBuilder mutation45 = dis.registerMutation(PROTACTINIUM, HYDROGEN, 1);
                mutation45.addMutationCondition(new MaterialMutationCondition(Uranium238));
            }),
    NEPTUNIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Neptunia", false, 0x284D7B, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.NEPTUNIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(SILVER, PALLADIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(CADMIUM, RHODIUM, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(INDIUM, RUTHENIUM, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(TIN, TECHNETIUM, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(ANTIMONY, MOLYBDENUM, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(TELLURIUM, NIOBIUM, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(IODINE, ZIRCONIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(XENON, YTTRIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(CAESIUM, STRONTIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(BARIUM, RUBIDIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(LANTHANUM, KRYPTON, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(CERIUM, BROMINE, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(PRASEODYMIUM, SELENIUM, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(NEODYMIUM, ARSENIC, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(PROMETHIUM, GERMANIUM, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(SAMARIUM, GALLIUM, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(EUROPIUM, ZINC, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(GADOLINIUM, COPPER, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(TERBIUM, NICKEL, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(DYSPROSIUM, COBALT, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(HOLMIUM, IRON, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(ERBIUM, MANGANESE, 5);
                mutation22.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(THULIUM, CHROME, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(YTTERBIUM, VANADIUM, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(LUTETIUM, TITANIUM, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(HAFNIUM, SCANDIUM, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(TANTALUM, CALCIUM, 4);
                mutation27.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(TUNGSTEN, POTASSIUM, 4);
                mutation28.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(RHENIUM, ARGON, 4);
                mutation29.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(OSMIUM, CHLORINE, 4);
                mutation30.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(IRIDIUM, SULFUR, 4);
                mutation31.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(PLATINUM, PHOSPHORUS, 3);
                mutation32.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(GOLD, SILICON, 3);
                mutation33.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(QUICKSILVER, ALUMINIUM, 3);
                mutation34.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(THALLIUM, MAGNESIUM, 3);
                mutation35.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(LEAD, SODIUM, 3);
                mutation36.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(BISMUTH, NEON, 2);
                mutation37.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(POLONIUM, FLUORINE, 2);
                mutation38.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(ASTATINE, OXYGEN, 2);
                mutation39.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(RADON, NITROGEN, 2);
                mutation40.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(FRANCIUM, CARBON, 2);
                mutation41.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(RADIUM, BORON, 1);
                mutation42.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(ACTINIUM, BERYLLIUM, 1);
                mutation43.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(THORIUM, LITHIUM, 1);
                mutation44.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(PROTACTINIUM, HELIUM, 1);
                mutation45.addMutationCondition(new MaterialMutationCondition(Neptunium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(URANIUM, HYDROGEN, 1);
                mutation46.addMutationCondition(new MaterialMutationCondition(Neptunium));
            }),
    PLUTONIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Plutos", true, 0x570000, 0x240000,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.LEAD), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.PLUTONIUM), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.ICY);
                beeSpecies.setNocturnal();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.LONGEST);
            },
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(CADMIUM, PALLADIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation2 = dis.registerMutation(INDIUM, RHODIUM, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation3 = dis.registerMutation(TIN, RUTHENIUM, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation4 = dis.registerMutation(ANTIMONY, TECHNETIUM, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation5 = dis.registerMutation(TELLURIUM, MOLYBDENUM, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation6 = dis.registerMutation(IODINE, NIOBIUM, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation7 = dis.registerMutation(XENON, ZIRCONIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation8 = dis.registerMutation(CAESIUM, YTTRIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation9 = dis.registerMutation(BARIUM, STRONTIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation10 = dis.registerMutation(LANTHANUM, RUBIDIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation11 = dis.registerMutation(CERIUM, KRYPTON, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation12 = dis.registerMutation(PRASEODYMIUM, BROMINE, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation13 = dis.registerMutation(NEODYMIUM, SELENIUM, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation14 = dis.registerMutation(PROMETHIUM, ARSENIC, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation15 = dis.registerMutation(SAMARIUM, GERMANIUM, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation16 = dis.registerMutation(EUROPIUM, GALLIUM, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation17 = dis.registerMutation(GADOLINIUM, ZINC, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation18 = dis.registerMutation(TERBIUM, COPPER, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation19 = dis.registerMutation(DYSPROSIUM, NICKEL, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation20 = dis.registerMutation(HOLMIUM, COBALT, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation21 = dis.registerMutation(ERBIUM, IRON, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation22 = dis.registerMutation(THULIUM, MANGANESE, 5);
                mutation22.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation23 = dis.registerMutation(YTTERBIUM, CHROME, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation24 = dis.registerMutation(LUTETIUM, VANADIUM, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation25 = dis.registerMutation(HAFNIUM, TITANIUM, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation26 = dis.registerMutation(TANTALUM, SCANDIUM, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation27 = dis.registerMutation(TUNGSTEN, CALCIUM, 4);
                mutation27.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation28 = dis.registerMutation(RHENIUM, POTASSIUM, 4);
                mutation28.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation29 = dis.registerMutation(OSMIUM, ARGON, 4);
                mutation29.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation30 = dis.registerMutation(IRIDIUM, CHLORINE, 4);
                mutation30.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation31 = dis.registerMutation(PLATINUM, SULFUR, 4);
                mutation31.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation32 = dis.registerMutation(GOLD, PHOSPHORUS, 3);
                mutation32.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation33 = dis.registerMutation(QUICKSILVER, SILICON, 3);
                mutation33.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation34 = dis.registerMutation(THALLIUM, ALUMINIUM, 3);
                mutation34.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation35 = dis.registerMutation(LEAD, MAGNESIUM, 3);
                mutation35.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation36 = dis.registerMutation(BISMUTH, SODIUM, 3);
                mutation36.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation37 = dis.registerMutation(POLONIUM, NEON, 2);
                mutation37.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation38 = dis.registerMutation(ASTATINE, FLUORINE, 2);
                mutation38.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation39 = dis.registerMutation(RADON, OXYGEN, 2);
                mutation39.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation40 = dis.registerMutation(FRANCIUM, NITROGEN, 2);
                mutation40.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation41 = dis.registerMutation(RADIUM, CARBON, 2);
                mutation41.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation42 = dis.registerMutation(ACTINIUM, BORON, 1);
                mutation42.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation43 = dis.registerMutation(THORIUM, BERYLLIUM, 1);
                mutation43.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation44 = dis.registerMutation(PROTACTINIUM, LITHIUM, 1);
                mutation44.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation45 = dis.registerMutation(URANIUM, HELIUM, 1);
                mutation45.addMutationCondition(new MaterialMutationCondition(Plutonium239));
                IBeeMutationBuilder mutation46 = dis.registerMutation(NEPTUNIUM, HYDROGEN, 1);
                mutation46.addMutationCondition(new MaterialMutationCondition(Plutonium239));
            }),
    AMERICIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Americia", false, 0x287869, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.AMERICIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(CADMIUM, SILVER, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(INDIUM, PALLADIUM, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(TIN, RHODIUM, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(ANTIMONY, RUTHENIUM, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(TELLURIUM, TECHNETIUM, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(IODINE, MOLYBDENUM, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(XENON, NIOBIUM, 9);
                mutation7.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(CAESIUM, ZIRCONIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(BARIUM, YTTRIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(LANTHANUM, STRONTIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(CERIUM, RUBIDIUM, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(PRASEODYMIUM, KRYPTON, 8);
                mutation12.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(NEODYMIUM, BROMINE, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(PROMETHIUM, SELENIUM, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(SAMARIUM, ARSENIC, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(EUROPIUM, GERMANIUM, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(GADOLINIUM, GALLIUM, 7);
                mutation17.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(TERBIUM, ZINC, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(DYSPROSIUM, COPPER, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(HOLMIUM, NICKEL, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(ERBIUM, COBALT, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(THULIUM, IRON, 6);
                mutation22.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(YTTERBIUM, MANGANESE, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(LUTETIUM, CHROME, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(HAFNIUM, VANADIUM, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(TANTALUM, TITANIUM, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(TUNGSTEN, SCANDIUM, 5);
                mutation27.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(RHENIUM, CALCIUM, 4);
                mutation28.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(OSMIUM, POTASSIUM, 4);
                mutation29.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(IRIDIUM, ARGON, 4);
                mutation30.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(PLATINUM, CHLORINE, 4);
                mutation31.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(GOLD, SULFUR, 4);
                mutation32.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(QUICKSILVER, PHOSPHORUS, 3);
                mutation33.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(THALLIUM, SILICON, 3);
                mutation34.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(LEAD, ALUMINIUM, 3);
                mutation35.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(BISMUTH, MAGNESIUM, 3);
                mutation36.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(POLONIUM, SODIUM, 3);
                mutation37.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(ASTATINE, NEON, 2);
                mutation38.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(RADON, FLUORINE, 2);
                mutation39.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(FRANCIUM, OXYGEN, 2);
                mutation40.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(RADIUM, NITROGEN, 2);
                mutation41.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(ACTINIUM, CARBON, 2);
                mutation42.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(THORIUM, BORON, 1);
                mutation43.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(PROTACTINIUM, BERYLLIUM, 1);
                mutation44.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(URANIUM, LITHIUM, 1);
                mutation45.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(NEPTUNIUM, HELIUM, 1);
                mutation46.addMutationCondition(new MaterialMutationCondition(Americium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(PLUTONIUM, HYDROGEN, 1);
                mutation47.addMutationCondition(new MaterialMutationCondition(Americium));
            }),
    CURIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Curia", false, 0x7B544E, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.CURIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(INDIUM, SILVER, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(TIN, PALLADIUM, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(ANTIMONY, RHODIUM, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(TELLURIUM, RUTHENIUM, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(IODINE, TECHNETIUM, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(XENON, MOLYBDENUM, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(CAESIUM, NIOBIUM, 9);
                mutation7.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(BARIUM, ZIRCONIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(LANTHANUM, YTTRIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(CERIUM, STRONTIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(PRASEODYMIUM, RUBIDIUM, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(NEODYMIUM, KRYPTON, 8);
                mutation12.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(PROMETHIUM, BROMINE, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(SAMARIUM, SELENIUM, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(EUROPIUM, ARSENIC, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(GADOLINIUM, GERMANIUM, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(TERBIUM, GALLIUM, 7);
                mutation17.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(DYSPROSIUM, ZINC, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(HOLMIUM, COPPER, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(ERBIUM, NICKEL, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(THULIUM, COBALT, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(YTTERBIUM, IRON, 6);
                mutation22.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(LUTETIUM, MANGANESE, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(HAFNIUM, CHROME, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(TANTALUM, VANADIUM, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(TUNGSTEN, TITANIUM, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(RHENIUM, SCANDIUM, 5);
                mutation27.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(OSMIUM, CALCIUM, 4);
                mutation28.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(IRIDIUM, POTASSIUM, 4);
                mutation29.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(PLATINUM, ARGON, 4);
                mutation30.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(GOLD, CHLORINE, 4);
                mutation31.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(QUICKSILVER, SULFUR, 4);
                mutation32.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(THALLIUM, PHOSPHORUS, 3);
                mutation33.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(LEAD, SILICON, 3);
                mutation34.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(BISMUTH, ALUMINIUM, 3);
                mutation35.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(POLONIUM, MAGNESIUM, 3);
                mutation36.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(ASTATINE, SODIUM, 3);
                mutation37.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(RADON, NEON, 2);
                mutation38.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(FRANCIUM, FLUORINE, 2);
                mutation39.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(RADIUM, OXYGEN, 2);
                mutation40.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(ACTINIUM, NITROGEN, 2);
                mutation41.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(THORIUM, CARBON, 2);
                mutation42.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(PROTACTINIUM, BORON, 1);
                mutation43.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(URANIUM, BERYLLIUM, 1);
                mutation44.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(NEPTUNIUM, LITHIUM, 1);
                mutation45.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(PLUTONIUM, HELIUM, 1);
                mutation46.addMutationCondition(new MaterialMutationCondition(Curium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(AMERICIUM, HYDROGEN, 1);
                mutation47.addMutationCondition(new MaterialMutationCondition(Curium));
            }),
    BERKELIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Berkelia", false, 0x645A88, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.BERKELIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(INDIUM, CADMIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(TIN, SILVER, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(ANTIMONY, PALLADIUM, 10);
                mutation3.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(TELLURIUM, RHODIUM, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(IODINE, RUTHENIUM, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(XENON, TECHNETIUM, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(CAESIUM, MOLYBDENUM, 9);
                mutation7.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(BARIUM, NIOBIUM, 9);
                mutation8.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(LANTHANUM, ZIRCONIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(CERIUM, YTTRIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(PRASEODYMIUM, STRONTIUM, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(NEODYMIUM, RUBIDIUM, 8);
                mutation12.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(PROMETHIUM, KRYPTON, 8);
                mutation13.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(SAMARIUM, BROMINE, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(EUROPIUM, SELENIUM, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(GADOLINIUM, ARSENIC, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(TERBIUM, GERMANIUM, 7);
                mutation17.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(DYSPROSIUM, GALLIUM, 7);
                mutation18.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(HOLMIUM, ZINC, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(ERBIUM, COPPER, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(THULIUM, NICKEL, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(YTTERBIUM, COBALT, 6);
                mutation22.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(LUTETIUM, IRON, 6);
                mutation23.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(HAFNIUM, MANGANESE, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(TANTALUM, CHROME, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(TUNGSTEN, VANADIUM, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(RHENIUM, TITANIUM, 5);
                mutation27.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(OSMIUM, SCANDIUM, 5);
                mutation28.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(IRIDIUM, CALCIUM, 4);
                mutation29.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(PLATINUM, POTASSIUM, 4);
                mutation30.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(GOLD, ARGON, 4);
                mutation31.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(QUICKSILVER, CHLORINE, 4);
                mutation32.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(THALLIUM, SULFUR, 4);
                mutation33.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(LEAD, PHOSPHORUS, 3);
                mutation34.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(BISMUTH, SILICON, 3);
                mutation35.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(POLONIUM, ALUMINIUM, 3);
                mutation36.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(ASTATINE, MAGNESIUM, 3);
                mutation37.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(RADON, SODIUM, 3);
                mutation38.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(FRANCIUM, NEON, 2);
                mutation39.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(RADIUM, FLUORINE, 2);
                mutation40.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(ACTINIUM, OXYGEN, 2);
                mutation41.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(THORIUM, NITROGEN, 2);
                mutation42.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(PROTACTINIUM, CARBON, 2);
                mutation43.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(URANIUM, BORON, 1);
                mutation44.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(NEPTUNIUM, BERYLLIUM, 1);
                mutation45.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(PLUTONIUM, LITHIUM, 1);
                mutation46.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(AMERICIUM, HELIUM, 1);
                mutation47.addMutationCondition(new MaterialMutationCondition(Berkelium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(CURIUM, HYDROGEN, 1);
                mutation48.addMutationCondition(new MaterialMutationCondition(Berkelium));
            }),
    CALIFORNIUM(GJBranchDefinition.GJ_RADIOACTIVE, "California", false, 0xA85A12, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.CALIFORNIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(TIN, CADMIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(ANTIMONY, SILVER, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(TELLURIUM, PALLADIUM, 10);
                mutation3.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(IODINE, RHODIUM, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(XENON, RUTHENIUM, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(CAESIUM, TECHNETIUM, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(BARIUM, MOLYBDENUM, 9);
                mutation7.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(LANTHANUM, NIOBIUM, 9);
                mutation8.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(CERIUM, ZIRCONIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(PRASEODYMIUM, YTTRIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(NEODYMIUM, STRONTIUM, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(PROMETHIUM, RUBIDIUM, 8);
                mutation12.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(SAMARIUM, KRYPTON, 8);
                mutation13.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(EUROPIUM, BROMINE, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(GADOLINIUM, SELENIUM, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(TERBIUM, ARSENIC, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(DYSPROSIUM, GERMANIUM, 7);
                mutation17.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(HOLMIUM, GALLIUM, 7);
                mutation18.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(ERBIUM, ZINC, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(THULIUM, COPPER, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(YTTERBIUM, NICKEL, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(LUTETIUM, COBALT, 6);
                mutation22.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(HAFNIUM, IRON, 6);
                mutation23.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(TANTALUM, MANGANESE, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(TUNGSTEN, CHROME, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(RHENIUM, VANADIUM, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(OSMIUM, TITANIUM, 5);
                mutation27.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(IRIDIUM, SCANDIUM, 5);
                mutation28.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(PLATINUM, CALCIUM, 4);
                mutation29.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(GOLD, POTASSIUM, 4);
                mutation30.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(QUICKSILVER, ARGON, 4);
                mutation31.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(THALLIUM, CHLORINE, 4);
                mutation32.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(LEAD, SULFUR, 4);
                mutation33.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(BISMUTH, PHOSPHORUS, 3);
                mutation34.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(POLONIUM, SILICON, 3);
                mutation35.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(ASTATINE, ALUMINIUM, 3);
                mutation36.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(RADON, MAGNESIUM, 3);
                mutation37.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(FRANCIUM, SODIUM, 3);
                mutation38.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(RADIUM, NEON, 2);
                mutation39.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(ACTINIUM, FLUORINE, 2);
                mutation40.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(THORIUM, OXYGEN, 2);
                mutation41.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(PROTACTINIUM, NITROGEN, 2);
                mutation42.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(URANIUM, CARBON, 2);
                mutation43.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(NEPTUNIUM, BORON, 1);
                mutation44.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(PLUTONIUM, BERYLLIUM, 1);
                mutation45.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(AMERICIUM, LITHIUM, 1);
                mutation46.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(CURIUM, HELIUM, 1);
                mutation47.addMutationCondition(new MaterialMutationCondition(Californium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(BERKELIUM, HYDROGEN, 1);
                mutation48.addMutationCondition(new MaterialMutationCondition(Californium));
            }),
    EINSTEINIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Einsteinia", false, 0xCE9F00, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.EINSTEINIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(TIN, INDIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(ANTIMONY, CADMIUM, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(TELLURIUM, SILVER, 10);
                mutation3.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(IODINE, PALLADIUM, 10);
                mutation4.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(XENON, RHODIUM, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(CAESIUM, RUTHENIUM, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(BARIUM, TECHNETIUM, 9);
                mutation7.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(LANTHANUM, MOLYBDENUM, 9);
                mutation8.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(CERIUM, NIOBIUM, 9);
                mutation9.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(PRASEODYMIUM, ZIRCONIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(NEODYMIUM, YTTRIUM, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(PROMETHIUM, STRONTIUM, 8);
                mutation12.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(SAMARIUM, RUBIDIUM, 8);
                mutation13.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(EUROPIUM, KRYPTON, 8);
                mutation14.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(GADOLINIUM, BROMINE, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(TERBIUM, SELENIUM, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(DYSPROSIUM, ARSENIC, 7);
                mutation17.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(HOLMIUM, GERMANIUM, 7);
                mutation18.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(ERBIUM, GALLIUM, 7);
                mutation19.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(THULIUM, ZINC, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(YTTERBIUM, COPPER, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(LUTETIUM, NICKEL, 6);
                mutation22.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(HAFNIUM, COBALT, 6);
                mutation23.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(TANTALUM, IRON, 6);
                mutation24.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(TUNGSTEN, MANGANESE, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(RHENIUM, CHROME, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(OSMIUM, VANADIUM, 5);
                mutation27.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(IRIDIUM, TITANIUM, 5);
                mutation28.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(PLATINUM, SCANDIUM, 5);
                mutation29.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(GOLD, CALCIUM, 4);
                mutation30.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(QUICKSILVER, POTASSIUM, 4);
                mutation31.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(THALLIUM, ARGON, 4);
                mutation32.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(LEAD, CHLORINE, 4);
                mutation33.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(BISMUTH, SULFUR, 4);
                mutation34.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(POLONIUM, PHOSPHORUS, 3);
                mutation35.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(ASTATINE, SILICON, 3);
                mutation36.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(RADON, ALUMINIUM, 3);
                mutation37.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(FRANCIUM, MAGNESIUM, 3);
                mutation38.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(RADIUM, SODIUM, 3);
                mutation39.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(ACTINIUM, NEON, 2);
                mutation40.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(THORIUM, FLUORINE, 2);
                mutation41.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(PROTACTINIUM, OXYGEN, 2);
                mutation42.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(URANIUM, NITROGEN, 2);
                mutation43.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(NEPTUNIUM, CARBON, 2);
                mutation44.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(PLUTONIUM, BORON, 1);
                mutation45.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(AMERICIUM, BERYLLIUM, 1);
                mutation46.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(CURIUM, LITHIUM, 1);
                mutation47.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(BERKELIUM, HELIUM, 1);
                mutation48.addMutationCondition(new MaterialMutationCondition(Einsteinium));
                IBeeMutationBuilder mutation49 = dis.registerMutation(CALIFORNIUM, HYDROGEN, 1);
                mutation49.addMutationCondition(new MaterialMutationCondition(Einsteinium));
            }),
    FERMIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Fermia", false, 0x984ACF, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.FERMIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(ANTIMONY, INDIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(TELLURIUM, CADMIUM, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(IODINE, SILVER, 10);
                mutation3.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(XENON, PALLADIUM, 10);
                mutation4.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(CAESIUM, RHODIUM, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(BARIUM, RUTHENIUM, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(LANTHANUM, TECHNETIUM, 9);
                mutation7.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(CERIUM, MOLYBDENUM, 9);
                mutation8.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(PRASEODYMIUM, NIOBIUM, 9);
                mutation9.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(NEODYMIUM, ZIRCONIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(PROMETHIUM, YTTRIUM, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(SAMARIUM, STRONTIUM, 8);
                mutation12.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(EUROPIUM, RUBIDIUM, 8);
                mutation13.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(GADOLINIUM, KRYPTON, 8);
                mutation14.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(TERBIUM, BROMINE, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(DYSPROSIUM, SELENIUM, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(HOLMIUM, ARSENIC, 7);
                mutation17.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(ERBIUM, GERMANIUM, 7);
                mutation18.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(THULIUM, GALLIUM, 7);
                mutation19.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(YTTERBIUM, ZINC, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(LUTETIUM, COPPER, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(HAFNIUM, NICKEL, 6);
                mutation22.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(TANTALUM, COBALT, 6);
                mutation23.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(TUNGSTEN, IRON, 6);
                mutation24.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(RHENIUM, MANGANESE, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(OSMIUM, CHROME, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(IRIDIUM, VANADIUM, 5);
                mutation27.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(PLATINUM, TITANIUM, 5);
                mutation28.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(GOLD, SCANDIUM, 5);
                mutation29.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(QUICKSILVER, CALCIUM, 4);
                mutation30.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(THALLIUM, POTASSIUM, 4);
                mutation31.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(LEAD, ARGON, 4);
                mutation32.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(BISMUTH, CHLORINE, 4);
                mutation33.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(POLONIUM, SULFUR, 4);
                mutation34.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(ASTATINE, PHOSPHORUS, 3);
                mutation35.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(RADON, SILICON, 3);
                mutation36.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(FRANCIUM, ALUMINIUM, 3);
                mutation37.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(RADIUM, MAGNESIUM, 3);
                mutation38.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(ACTINIUM, SODIUM, 3);
                mutation39.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(THORIUM, NEON, 2);
                mutation40.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(PROTACTINIUM, FLUORINE, 2);
                mutation41.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(URANIUM, OXYGEN, 2);
                mutation42.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(NEPTUNIUM, NITROGEN, 2);
                mutation43.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(PLUTONIUM, CARBON, 2);
                mutation44.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(AMERICIUM, BORON, 1);
                mutation45.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(CURIUM, BERYLLIUM, 1);
                mutation46.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(BERKELIUM, LITHIUM, 1);
                mutation47.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(CALIFORNIUM, HELIUM, 1);
                mutation48.addMutationCondition(new MaterialMutationCondition(Fermium));
                IBeeMutationBuilder mutation49 = dis.registerMutation(EINSTEINIUM, HYDROGEN, 1);
                mutation49.addMutationCondition(new MaterialMutationCondition(Fermium));
            }),

    MENDELEVIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Mendelevia", false, 0x1D4ACF, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.MENDELEVIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(ANTIMONY, TIN, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(TELLURIUM, INDIUM, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(IODINE, CADMIUM, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(XENON, SILVER, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(CAESIUM, PALLADIUM, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(BARIUM, RHODIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(LANTHANUM, RUTHENIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(CERIUM, TECHNETIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(PRASEODYMIUM, MOLYBDENUM, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(NEODYMIUM, NIOBIUM, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(PROMETHIUM, ZIRCONIUM, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(SAMARIUM, YTTRIUM, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(EUROPIUM, STRONTIUM, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(GADOLINIUM, RUBIDIUM, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(TERBIUM, KRYPTON, 6);
                mutation15.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(DYSPROSIUM, BROMINE, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(HOLMIUM, SELENIUM, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(ERBIUM, ARSENIC, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(THULIUM, GERMANIUM, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(YTTERBIUM, GALLIUM, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(LUTETIUM, ZINC, 5);
                mutation21.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(HAFNIUM, COPPER, 5);
                mutation22.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(TANTALUM, NICKEL, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(TUNGSTEN, COBALT, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(RHENIUM, IRON, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(OSMIUM, MANGANESE, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(IRIDIUM, CHROME, 4);
                mutation27.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(PLATINUM, VANADIUM, 4);
                mutation28.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(GOLD, TITANIUM, 4);
                mutation29.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(QUICKSILVER, SCANDIUM, 4);
                mutation30.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(THALLIUM, CALCIUM, 4);
                mutation31.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(LEAD, POTASSIUM, 4);
                mutation32.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(BISMUTH, ARGON, 3);
                mutation33.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(POLONIUM, CHLORINE, 3);
                mutation34.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(ASTATINE, SULFUR, 3);
                mutation35.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(RADON, PHOSPHORUS, 3);
                mutation36.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(FRANCIUM, SILICON, 3);
                mutation37.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(RADIUM, ALUMINIUM, 3);
                mutation38.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(ACTINIUM, MAGNESIUM, 2);
                mutation39.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(THORIUM, SODIUM, 2);
                mutation40.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(PROTACTINIUM, NEON, 2);
                mutation41.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(URANIUM, FLUORINE, 2);
                mutation42.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(NEPTUNIUM, OXYGEN, 2);
                mutation43.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(PLUTONIUM, NITROGEN, 2);
                mutation44.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(AMERICIUM, CARBON, 1);
                mutation45.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(CURIUM, BORON, 1);
                mutation46.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(BERKELIUM, BERYLLIUM, 1);
                mutation47.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(CALIFORNIUM, LITHIUM, 1);
                mutation48.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation49 = dis.registerMutation(EINSTEINIUM, HELIUM, 1);
                mutation49.addMutationCondition(new MaterialMutationCondition(Mendelevium));
                IBeeMutationBuilder mutation50 = dis.registerMutation(FERMIUM, HYDROGEN, 1);
                mutation50.addMutationCondition(new MaterialMutationCondition(Mendelevium));
            }),
    NOBELIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Nobelia", false, 0x3C7B1F, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.NOBELIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(TELLURIUM, TIN, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(IODINE, INDIUM, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(XENON, CADMIUM, 8);
                mutation3.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(CAESIUM, SILVER, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(BARIUM, PALLADIUM, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(LANTHANUM, RHODIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(CERIUM, RUTHENIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(PRASEODYMIUM, TECHNETIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(NEODYMIUM, MOLYBDENUM, 7);
                mutation9.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(PROMETHIUM, NIOBIUM, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(SAMARIUM, ZIRCONIUM, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(EUROPIUM, YTTRIUM, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(GADOLINIUM, STRONTIUM, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(TERBIUM, RUBIDIUM, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(DYSPROSIUM, KRYPTON, 6);
                mutation15.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(HOLMIUM, BROMINE, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(ERBIUM, SELENIUM, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(THULIUM, ARSENIC, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(YTTERBIUM, GERMANIUM, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(LUTETIUM, GALLIUM, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(HAFNIUM, ZINC, 5);
                mutation21.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(TANTALUM, COPPER, 5);
                mutation22.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(TUNGSTEN, NICKEL, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(RHENIUM, COBALT, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(OSMIUM, IRON, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(IRIDIUM, MANGANESE, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(PLATINUM, CHROME, 4);
                mutation27.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(GOLD, VANADIUM, 4);
                mutation28.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(QUICKSILVER, TITANIUM, 4);
                mutation29.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(THALLIUM, SCANDIUM, 4);
                mutation30.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(LEAD, CALCIUM, 4);
                mutation31.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(BISMUTH, POTASSIUM, 4);
                mutation32.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(POLONIUM, ARGON, 3);
                mutation33.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(ASTATINE, CHLORINE, 3);
                mutation34.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(RADON, SULFUR, 3);
                mutation35.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(FRANCIUM, PHOSPHORUS, 3);
                mutation36.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(RADIUM, SILICON, 3);
                mutation37.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(ACTINIUM, ALUMINIUM, 3);
                mutation38.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(THORIUM, MAGNESIUM, 2);
                mutation39.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(PROTACTINIUM, SODIUM, 2);
                mutation40.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(URANIUM, NEON, 2);
                mutation41.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(NEPTUNIUM, FLUORINE, 2);
                mutation42.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(PLUTONIUM, OXYGEN, 2);
                mutation43.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(AMERICIUM, NITROGEN, 2);
                mutation44.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(CURIUM, CARBON, 1);
                mutation45.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(BERKELIUM, BORON, 1);
                mutation46.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(CALIFORNIUM, BERYLLIUM, 1);
                mutation47.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(EINSTEINIUM, LITHIUM, 1);
                mutation48.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation49 = dis.registerMutation(FERMIUM, HELIUM, 1);
                mutation49.addMutationCondition(new MaterialMutationCondition(Nobelium));
                IBeeMutationBuilder mutation50 = dis.registerMutation(MENDELEVIUM, HYDROGEN, 1);
                mutation50.addMutationCondition(new MaterialMutationCondition(Nobelium));
            }),
    LAWRENCIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Lawrencia", false, 0x412B6D, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.LAWRENCIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(TELLURIUM, ANTIMONY, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(IODINE, TIN, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(XENON, INDIUM, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(CAESIUM, CADMIUM, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(BARIUM, SILVER, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(LANTHANUM, PALLADIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(CERIUM, RHODIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(PRASEODYMIUM, RUTHENIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(NEODYMIUM, TECHNETIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(PROMETHIUM, MOLYBDENUM, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(SAMARIUM, NIOBIUM, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(EUROPIUM, ZIRCONIUM, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(GADOLINIUM, YTTRIUM, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(TERBIUM, STRONTIUM, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(DYSPROSIUM, RUBIDIUM, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(HOLMIUM, KRYPTON, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(ERBIUM, BROMINE, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(THULIUM, SELENIUM, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(YTTERBIUM, ARSENIC, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(LUTETIUM, GERMANIUM, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(HAFNIUM, GALLIUM, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(TANTALUM, ZINC, 5);
                mutation22.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(TUNGSTEN, COPPER, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(RHENIUM, NICKEL, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(OSMIUM, COBALT, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(IRIDIUM, IRON, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(PLATINUM, MANGANESE, 5);
                mutation27.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(GOLD, CHROME, 4);
                mutation28.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(QUICKSILVER, VANADIUM, 4);
                mutation29.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(THALLIUM, TITANIUM, 4);
                mutation30.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(LEAD, SCANDIUM, 4);
                mutation31.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(BISMUTH, CALCIUM, 4);
                mutation32.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(POLONIUM, POTASSIUM, 4);
                mutation33.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(ASTATINE, ARGON, 3);
                mutation34.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(RADON, CHLORINE, 3);
                mutation35.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(FRANCIUM, SULFUR, 3);
                mutation36.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(RADIUM, PHOSPHORUS, 3);
                mutation37.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(ACTINIUM, SILICON, 3);
                mutation38.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(THORIUM, ALUMINIUM, 3);
                mutation39.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(PROTACTINIUM, MAGNESIUM, 2);
                mutation40.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(URANIUM, SODIUM, 2);
                mutation41.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(NEPTUNIUM, NEON, 2);
                mutation42.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(PLUTONIUM, FLUORINE, 2);
                mutation43.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(AMERICIUM, OXYGEN, 2);
                mutation44.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(CURIUM, NITROGEN, 2);
                mutation45.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(BERKELIUM, CARBON, 1);
                mutation46.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(CALIFORNIUM, BORON, 1);
                mutation47.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(EINSTEINIUM, BERYLLIUM, 1);
                mutation48.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation49 = dis.registerMutation(FERMIUM, LITHIUM, 1);
                mutation49.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation50 = dis.registerMutation(MENDELEVIUM, HELIUM, 1);
                mutation50.addMutationCondition(new MaterialMutationCondition(Lawrencium));
                IBeeMutationBuilder mutation51 = dis.registerMutation(NOBELIUM, HYDROGEN, 1);
                mutation51.addMutationCondition(new MaterialMutationCondition(Lawrencium));
            }),
    RUTHERFODIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Rutherfordia", false, 0xFFF6A1, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.RUTHERFORDIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(IODINE, ANTIMONY, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(XENON, TIN, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(CAESIUM, INDIUM, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(BARIUM, CADMIUM, 8);
                mutation4.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(LANTHANUM, SILVER, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(CERIUM, PALLADIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(PRASEODYMIUM, RHODIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(NEODYMIUM, RUTHENIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(PROMETHIUM, TECHNETIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(SAMARIUM, MOLYBDENUM, 7);
                mutation10.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(EUROPIUM, NIOBIUM, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(GADOLINIUM, ZIRCONIUM, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(TERBIUM, YTTRIUM, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(DYSPROSIUM, STRONTIUM, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(HOLMIUM, RUBIDIUM, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(ERBIUM, KRYPTON, 6);
                mutation16.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(THULIUM, BROMINE, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(YTTERBIUM, SELENIUM, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(LUTETIUM, ARSENIC, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(HAFNIUM, GERMANIUM, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(TANTALUM, GALLIUM, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(TUNGSTEN, ZINC, 5);
                mutation22.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(RHENIUM, COPPER, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(OSMIUM, NICKEL, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(IRIDIUM, COBALT, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(PLATINUM, IRON, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(GOLD, MANGANESE, 5);
                mutation27.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(QUICKSILVER, CHROME, 4);
                mutation28.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(THALLIUM, VANADIUM, 4);
                mutation29.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(LEAD, TITANIUM, 4);
                mutation30.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(BISMUTH, SCANDIUM, 4);
                mutation31.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(POLONIUM, CALCIUM, 4);
                mutation32.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(ASTATINE, POTASSIUM, 4);
                mutation33.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(RADON, ARGON, 3);
                mutation34.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(FRANCIUM, CHLORINE, 3);
                mutation35.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(RADIUM, SULFUR, 3);
                mutation36.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(ACTINIUM, PHOSPHORUS, 3);
                mutation37.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(THORIUM, SILICON, 3);
                mutation38.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(PROTACTINIUM, ALUMINIUM, 3);
                mutation39.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(URANIUM, MAGNESIUM, 2);
                mutation40.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(NEPTUNIUM, SODIUM, 2);
                mutation41.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(PLUTONIUM, NEON, 2);
                mutation42.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(AMERICIUM, FLUORINE, 2);
                mutation43.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(CURIUM, OXYGEN, 2);
                mutation44.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(BERKELIUM, NITROGEN, 2);
                mutation45.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(CALIFORNIUM, CARBON, 1);
                mutation46.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(EINSTEINIUM, BORON, 1);
                mutation47.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(FERMIUM, BERYLLIUM, 1);
                mutation48.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation49 = dis.registerMutation(MENDELEVIUM, LITHIUM, 1);
                mutation49.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation50 = dis.registerMutation(NOBELIUM, HELIUM, 1);
                mutation50.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
                IBeeMutationBuilder mutation51 = dis.registerMutation(LAWRENCIUM, HYDROGEN, 1);
                mutation51.addMutationCondition(new MaterialMutationCondition(Rutherfordium));
            }),
    DUBNIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Dubnia", false, 0xD3FDFF, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.DUBNIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(IODINE, TELLURIUM, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(XENON, ANTIMONY, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(CAESIUM, TIN, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(BARIUM, INDIUM, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(LANTHANUM, CADMIUM, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(CERIUM, SILVER, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(PRASEODYMIUM, PALLADIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(NEODYMIUM, RHODIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(PROMETHIUM, RUTHENIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(SAMARIUM, TECHNETIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(EUROPIUM, MOLYBDENUM, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(GADOLINIUM, NIOBIUM, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(TERBIUM, ZIRCONIUM, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(DYSPROSIUM, YTTRIUM, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(HOLMIUM, STRONTIUM, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(ERBIUM, RUBIDIUM, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(THULIUM, KRYPTON, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(YTTERBIUM, BROMINE, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(LUTETIUM, SELENIUM, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(HAFNIUM, ARSENIC, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(TANTALUM, GERMANIUM, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(TUNGSTEN, GALLIUM, 6);
                mutation22.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(RHENIUM, ZINC, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(OSMIUM, COPPER, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(IRIDIUM, NICKEL, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(PLATINUM, COBALT, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(GOLD, IRON, 5);
                mutation27.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(QUICKSILVER, MANGANESE, 5);
                mutation28.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(THALLIUM, CHROME, 4);
                mutation29.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(LEAD, VANADIUM, 4);
                mutation30.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(BISMUTH, TITANIUM, 4);
                mutation31.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(POLONIUM, SCANDIUM, 4);
                mutation32.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(ASTATINE, CALCIUM, 4);
                mutation33.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(RADON, POTASSIUM, 4);
                mutation34.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(FRANCIUM, ARGON, 3);
                mutation35.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(RADIUM, CHLORINE, 3);
                mutation36.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(ACTINIUM, SULFUR, 3);
                mutation37.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(THORIUM, PHOSPHORUS, 3);
                mutation38.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(PROTACTINIUM, SILICON, 3);
                mutation39.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(URANIUM, ALUMINIUM, 3);
                mutation40.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(NEPTUNIUM, MAGNESIUM, 2);
                mutation41.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(PLUTONIUM, SODIUM, 2);
                mutation42.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(AMERICIUM, NEON, 2);
                mutation43.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(CURIUM, FLUORINE, 2);
                mutation44.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(BERKELIUM, OXYGEN, 2);
                mutation45.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(CALIFORNIUM, NITROGEN, 2);
                mutation46.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(EINSTEINIUM, CARBON, 1);
                mutation47.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(FERMIUM, BORON, 1);
                mutation48.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation49 = dis.registerMutation(MENDELEVIUM, BERYLLIUM, 1);
                mutation49.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation50 = dis.registerMutation(NOBELIUM, LITHIUM, 1);
                mutation50.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation51 = dis.registerMutation(LAWRENCIUM, HELIUM, 1);
                mutation51.addMutationCondition(new MaterialMutationCondition(Dubnium));
                IBeeMutationBuilder mutation52 = dis.registerMutation(RUTHERFODIUM, HYDROGEN, 1);
                mutation52.addMutationCondition(new MaterialMutationCondition(Dubnium));
            }),
    SEABORGIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Seaborgia", false, 0x19c5ff, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.SEABORGIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(XENON, TELLURIUM, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(CAESIUM, ANTIMONY, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(BARIUM, TIN, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(LANTHANUM, INDIUM, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(CERIUM, CADMIUM, 8);
                mutation5.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(PRASEODYMIUM, SILVER, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(NEODYMIUM, PALLADIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(PROMETHIUM, RHODIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(SAMARIUM, RUTHENIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(EUROPIUM, TECHNETIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(GADOLINIUM, MOLYBDENUM, 7);
                mutation11.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(TERBIUM, NIOBIUM, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(DYSPROSIUM, ZIRCONIUM, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(HOLMIUM, YTTRIUM, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(ERBIUM, STRONTIUM, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(THULIUM, RUBIDIUM, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(YTTERBIUM, KRYPTON, 6);
                mutation17.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(LUTETIUM, BROMINE, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(HAFNIUM, SELENIUM, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(TANTALUM, ARSENIC, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(TUNGSTEN, GERMANIUM, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(RHENIUM, GALLIUM, 6);
                mutation22.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(OSMIUM, ZINC, 5);
                mutation23.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(IRIDIUM, COPPER, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(PLATINUM, NICKEL, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(GOLD, COBALT, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(QUICKSILVER, IRON, 5);
                mutation27.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(THALLIUM, MANGANESE, 5);
                mutation28.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(LEAD, CHROME, 4);
                mutation29.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(BISMUTH, VANADIUM, 4);
                mutation30.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(POLONIUM, TITANIUM, 4);
                mutation31.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(ASTATINE, SCANDIUM, 4);
                mutation32.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(RADON, CALCIUM, 4);
                mutation33.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(FRANCIUM, POTASSIUM, 4);
                mutation34.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(RADIUM, ARGON, 3);
                mutation35.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(ACTINIUM, CHLORINE, 3);
                mutation36.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(THORIUM, SULFUR, 3);
                mutation37.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(PROTACTINIUM, PHOSPHORUS, 3);
                mutation38.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(URANIUM, SILICON, 3);
                mutation39.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(NEPTUNIUM, ALUMINIUM, 3);
                mutation40.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(PLUTONIUM, MAGNESIUM, 2);
                mutation41.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(AMERICIUM, SODIUM, 2);
                mutation42.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(CURIUM, NEON, 2);
                mutation43.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(BERKELIUM, FLUORINE, 2);
                mutation44.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(CALIFORNIUM, OXYGEN, 2);
                mutation45.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(EINSTEINIUM, NITROGEN, 2);
                mutation46.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(FERMIUM, CARBON, 1);
                mutation47.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(MENDELEVIUM, BORON, 1);
                mutation48.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation49 = dis.registerMutation(NOBELIUM, BERYLLIUM, 1);
                mutation49.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation50 = dis.registerMutation(LAWRENCIUM, LITHIUM, 1);
                mutation50.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation51 = dis.registerMutation(RUTHERFODIUM, HELIUM, 1);
                mutation51.addMutationCondition(new MaterialMutationCondition(Seaborgium));
                IBeeMutationBuilder mutation52 = dis.registerMutation(DUBNIUM, HYDROGEN, 1);
                mutation52.addMutationCondition(new MaterialMutationCondition(Seaborgium));
            }),
    BOHRIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Bohria", false, 0xdc57ff, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.BOHRIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(XENON, IODINE, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(CAESIUM, TELLURIUM, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(BARIUM, ANTIMONY, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(LANTHANUM, TIN, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(CERIUM, INDIUM, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(PRASEODYMIUM, CADMIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(NEODYMIUM, SILVER, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(PROMETHIUM, PALLADIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(SAMARIUM, RHODIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(EUROPIUM, RUTHENIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(GADOLINIUM, TECHNETIUM, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(TERBIUM, MOLYBDENUM, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(DYSPROSIUM, NIOBIUM, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(HOLMIUM, ZIRCONIUM, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(ERBIUM, YTTRIUM, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(THULIUM, STRONTIUM, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(YTTERBIUM, RUBIDIUM, 7);
                mutation17.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(LUTETIUM, KRYPTON, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(HAFNIUM, BROMINE, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(TANTALUM, SELENIUM, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(TUNGSTEN, ARSENIC, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(RHENIUM, GERMANIUM, 6);
                mutation22.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(OSMIUM, GALLIUM, 6);
                mutation23.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(IRIDIUM, ZINC, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(PLATINUM, COPPER, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(GOLD, NICKEL, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(QUICKSILVER, COBALT, 5);
                mutation27.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(THALLIUM, IRON, 5);
                mutation28.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(LEAD, MANGANESE, 5);
                mutation29.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(BISMUTH, CHROME, 4);
                mutation30.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(POLONIUM, VANADIUM, 4);
                mutation31.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(ASTATINE, TITANIUM, 4);
                mutation32.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(RADON, SCANDIUM, 4);
                mutation33.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(FRANCIUM, CALCIUM, 4);
                mutation34.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(RADIUM, POTASSIUM, 4);
                mutation35.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(ACTINIUM, ARGON, 3);
                mutation36.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(THORIUM, CHLORINE, 3);
                mutation37.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(PROTACTINIUM, SULFUR, 3);
                mutation38.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(URANIUM, PHOSPHORUS, 3);
                mutation39.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(NEPTUNIUM, SILICON, 3);
                mutation40.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(PLUTONIUM, ALUMINIUM, 3);
                mutation41.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(AMERICIUM, MAGNESIUM, 2);
                mutation42.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(CURIUM, SODIUM, 2);
                mutation43.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(BERKELIUM, NEON, 2);
                mutation44.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(CALIFORNIUM, FLUORINE, 2);
                mutation45.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(EINSTEINIUM, OXYGEN, 2);
                mutation46.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(FERMIUM, NITROGEN, 2);
                mutation47.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(MENDELEVIUM, CARBON, 1);
                mutation48.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation49 = dis.registerMutation(NOBELIUM, BORON, 1);
                mutation49.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation50 = dis.registerMutation(LAWRENCIUM, BERYLLIUM, 1);
                mutation50.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation51 = dis.registerMutation(RUTHERFODIUM, LITHIUM, 1);
                mutation51.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation52 = dis.registerMutation(DUBNIUM, HELIUM, 1);
                mutation52.addMutationCondition(new MaterialMutationCondition(Bohrium));
                IBeeMutationBuilder mutation53 = dis.registerMutation(SEABORGIUM, HYDROGEN, 1);
                mutation53.addMutationCondition(new MaterialMutationCondition(Bohrium));
            }),
    HASSIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Hassia", false, 0x2d3a9d, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.HASSIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(CAESIUM, IODINE, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(BARIUM, TELLURIUM, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(LANTHANUM, ANTIMONY, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(CERIUM, TIN, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(PRASEODYMIUM, INDIUM, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(NEODYMIUM, CADMIUM, 8);
                mutation6.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(PROMETHIUM, SILVER, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(SAMARIUM, PALLADIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(EUROPIUM, RHODIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(GADOLINIUM, RUTHENIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(TERBIUM, TECHNETIUM, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(DYSPROSIUM, MOLYBDENUM, 7);
                mutation12.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(HOLMIUM, NIOBIUM, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(ERBIUM, ZIRCONIUM, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(THULIUM, YTTRIUM, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(YTTERBIUM, STRONTIUM, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(LUTETIUM, RUBIDIUM, 7);
                mutation17.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(HAFNIUM, KRYPTON, 6);
                mutation18.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(TANTALUM, BROMINE, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(TUNGSTEN, SELENIUM, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(RHENIUM, ARSENIC, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(OSMIUM, GERMANIUM, 6);
                mutation22.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(IRIDIUM, GALLIUM, 6);
                mutation23.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(PLATINUM, ZINC, 5);
                mutation24.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(GOLD, COPPER, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(QUICKSILVER, NICKEL, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(THALLIUM, COBALT, 5);
                mutation27.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(LEAD, IRON, 5);
                mutation28.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(BISMUTH, MANGANESE, 5);
                mutation29.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(POLONIUM, CHROME, 4);
                mutation30.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(ASTATINE, VANADIUM, 4);
                mutation31.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(RADON, TITANIUM, 4);
                mutation32.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(FRANCIUM, SCANDIUM, 4);
                mutation33.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(RADIUM, CALCIUM, 4);
                mutation34.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(ACTINIUM, POTASSIUM, 4);
                mutation35.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(THORIUM, ARGON, 3);
                mutation36.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(PROTACTINIUM, CHLORINE, 3);
                mutation37.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(URANIUM, SULFUR, 3);
                mutation38.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(NEPTUNIUM, PHOSPHORUS, 3);
                mutation39.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(PLUTONIUM, SILICON, 3);
                mutation40.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(AMERICIUM, ALUMINIUM, 3);
                mutation41.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(CURIUM, MAGNESIUM, 2);
                mutation42.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(BERKELIUM, SODIUM, 2);
                mutation43.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(CALIFORNIUM, NEON, 2);
                mutation44.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(EINSTEINIUM, FLUORINE, 2);
                mutation45.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(FERMIUM, OXYGEN, 2);
                mutation46.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(MENDELEVIUM, NITROGEN, 2);
                mutation47.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(NOBELIUM, CARBON, 1);
                mutation48.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation49 = dis.registerMutation(LAWRENCIUM, BORON, 1);
                mutation49.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation50 = dis.registerMutation(RUTHERFODIUM, BERYLLIUM, 1);
                mutation50.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation51 = dis.registerMutation(DUBNIUM, LITHIUM, 1);
                mutation51.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation52 = dis.registerMutation(SEABORGIUM, HELIUM, 1);
                mutation52.addMutationCondition(new MaterialMutationCondition(Hassium));
                IBeeMutationBuilder mutation53 = dis.registerMutation(BOHRIUM, HYDROGEN, 1);
                mutation53.addMutationCondition(new MaterialMutationCondition(Hassium));
            }),
    MEITNERIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Meitneria", false, 0x2246be, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.MEITNERIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(CAESIUM, XENON, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(BARIUM, IODINE, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(LANTHANUM, TELLURIUM, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(CERIUM, ANTIMONY, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(PRASEODYMIUM, TIN, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(NEODYMIUM, INDIUM, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(PROMETHIUM, CADMIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(SAMARIUM, SILVER, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(EUROPIUM, PALLADIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(GADOLINIUM, RHODIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(TERBIUM, RUTHENIUM, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(DYSPROSIUM, TECHNETIUM, 8);
                mutation12.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(HOLMIUM, MOLYBDENUM, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(ERBIUM, NIOBIUM, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(THULIUM, ZIRCONIUM, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(YTTERBIUM, YTTRIUM, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(LUTETIUM, STRONTIUM, 7);
                mutation17.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(HAFNIUM, RUBIDIUM, 7);
                mutation18.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(TANTALUM, KRYPTON, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(TUNGSTEN, BROMINE, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(RHENIUM, SELENIUM, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(OSMIUM, ARSENIC, 6);
                mutation22.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(IRIDIUM, GERMANIUM, 6);
                mutation23.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(PLATINUM, GALLIUM, 6);
                mutation24.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(GOLD, ZINC, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(QUICKSILVER, COPPER, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(THALLIUM, NICKEL, 5);
                mutation27.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(LEAD, COBALT, 5);
                mutation28.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(BISMUTH, IRON, 5);
                mutation29.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(POLONIUM, MANGANESE, 5);
                mutation30.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(ASTATINE, CHROME, 4);
                mutation31.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(RADON, VANADIUM, 4);
                mutation32.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(FRANCIUM, TITANIUM, 4);
                mutation33.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(RADIUM, SCANDIUM, 4);
                mutation34.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(ACTINIUM, CALCIUM, 4);
                mutation35.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(THORIUM, POTASSIUM, 4);
                mutation36.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(PROTACTINIUM, ARGON, 3);
                mutation37.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(URANIUM, CHLORINE, 3);
                mutation38.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(NEPTUNIUM, SULFUR, 3);
                mutation39.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(PLUTONIUM, PHOSPHORUS, 3);
                mutation40.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(AMERICIUM, SILICON, 3);
                mutation41.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(CURIUM, ALUMINIUM, 3);
                mutation42.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(BERKELIUM, MAGNESIUM, 2);
                mutation43.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(CALIFORNIUM, SODIUM, 2);
                mutation44.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(EINSTEINIUM, NEON, 2);
                mutation45.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(FERMIUM, FLUORINE, 2);
                mutation46.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(MENDELEVIUM, OXYGEN, 2);
                mutation47.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(NOBELIUM, NITROGEN, 2);
                mutation48.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation49 = dis.registerMutation(LAWRENCIUM, CARBON, 1);
                mutation49.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation50 = dis.registerMutation(RUTHERFODIUM, BORON, 1);
                mutation50.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation51 = dis.registerMutation(DUBNIUM, BERYLLIUM, 1);
                mutation51.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation52 = dis.registerMutation(SEABORGIUM, LITHIUM, 1);
                mutation52.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation53 = dis.registerMutation(BOHRIUM, HELIUM, 1);
                mutation53.addMutationCondition(new MaterialMutationCondition(Meitnerium));
                IBeeMutationBuilder mutation54 = dis.registerMutation(HASSIUM, HYDROGEN, 1);
                mutation54.addMutationCondition(new MaterialMutationCondition(Meitnerium));
            }),
    DARMSTADTIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Darmstadtia", false, 0x578062, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.DARMSTADTIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(BARIUM, XENON, 9);
                mutation1.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(LANTHANUM, IODINE, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(CERIUM, TELLURIUM, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(PRASEODYMIUM, ANTIMONY, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(NEODYMIUM, TIN, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(PROMETHIUM, INDIUM, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(SAMARIUM, CADMIUM, 8);
                mutation7.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(EUROPIUM, SILVER, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(GADOLINIUM, PALLADIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(TERBIUM, RHODIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(DYSPROSIUM, RUTHENIUM, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(HOLMIUM, TECHNETIUM, 8);
                mutation12.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(ERBIUM, MOLYBDENUM, 7);
                mutation13.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(THULIUM, NIOBIUM, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(YTTERBIUM, ZIRCONIUM, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(LUTETIUM, YTTRIUM, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(HAFNIUM, STRONTIUM, 7);
                mutation17.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(TANTALUM, RUBIDIUM, 7);
                mutation18.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(TUNGSTEN, KRYPTON, 6);
                mutation19.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(RHENIUM, BROMINE, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(OSMIUM, SELENIUM, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(IRIDIUM, ARSENIC, 6);
                mutation22.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(PLATINUM, GERMANIUM, 6);
                mutation23.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(GOLD, GALLIUM, 6);
                mutation24.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(QUICKSILVER, ZINC, 5);
                mutation25.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(THALLIUM, COPPER, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(LEAD, NICKEL, 5);
                mutation27.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(BISMUTH, COBALT, 5);
                mutation28.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(POLONIUM, IRON, 5);
                mutation29.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(ASTATINE, MANGANESE, 5);
                mutation30.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(RADON, CHROME, 4);
                mutation31.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(FRANCIUM, VANADIUM, 4);
                mutation32.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(RADIUM, TITANIUM, 4);
                mutation33.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(ACTINIUM, SCANDIUM, 4);
                mutation34.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(THORIUM, CALCIUM, 4);
                mutation35.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(PROTACTINIUM, POTASSIUM, 4);
                mutation36.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(URANIUM, ARGON, 3);
                mutation37.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(NEPTUNIUM, CHLORINE, 3);
                mutation38.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(PLUTONIUM, SULFUR, 3);
                mutation39.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(AMERICIUM, PHOSPHORUS, 3);
                mutation40.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(CURIUM, SILICON, 3);
                mutation41.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(BERKELIUM, ALUMINIUM, 3);
                mutation42.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(CALIFORNIUM, MAGNESIUM, 2);
                mutation43.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(EINSTEINIUM, SODIUM, 2);
                mutation44.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(FERMIUM, NEON, 2);
                mutation45.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(MENDELEVIUM, FLUORINE, 2);
                mutation46.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(NOBELIUM, OXYGEN, 2);
                mutation47.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(LAWRENCIUM, NITROGEN, 2);
                mutation48.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation49 = dis.registerMutation(RUTHERFODIUM, CARBON, 1);
                mutation49.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation50 = dis.registerMutation(DUBNIUM, BORON, 1);
                mutation50.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation51 = dis.registerMutation(SEABORGIUM, BERYLLIUM, 1);
                mutation51.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation52 = dis.registerMutation(BOHRIUM, LITHIUM, 1);
                mutation52.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation53 = dis.registerMutation(HASSIUM, HELIUM, 1);
                mutation53.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
                IBeeMutationBuilder mutation54 = dis.registerMutation(MEITNERIUM, HYDROGEN, 1);
                mutation54.addMutationCondition(new MaterialMutationCondition(Darmstadtium));
            }),
    ROENTGENIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Roentgenia", false, 0xe3fdec, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.ROENTGENIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(BARIUM, CAESIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(LANTHANUM, XENON, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(CERIUM, IODINE, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(PRASEODYMIUM, TELLURIUM, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(NEODYMIUM, ANTIMONY, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(PROMETHIUM, TIN, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(SAMARIUM, INDIUM, 9);
                mutation7.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(EUROPIUM, CADMIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(GADOLINIUM, SILVER, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(TERBIUM, PALLADIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(DYSPROSIUM, RHODIUM, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(HOLMIUM, RUTHENIUM, 8);
                mutation12.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(ERBIUM, TECHNETIUM, 8);
                mutation13.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(THULIUM, MOLYBDENUM, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(YTTERBIUM, NIOBIUM, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(LUTETIUM, ZIRCONIUM, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(HAFNIUM, YTTRIUM, 7);
                mutation17.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(TANTALUM, STRONTIUM, 7);
                mutation18.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(TUNGSTEN, RUBIDIUM, 7);
                mutation19.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(RHENIUM, KRYPTON, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(OSMIUM, BROMINE, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(IRIDIUM, SELENIUM, 6);
                mutation22.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(PLATINUM, ARSENIC, 6);
                mutation23.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(GOLD, GERMANIUM, 6);
                mutation24.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(QUICKSILVER, GALLIUM, 6);
                mutation25.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(THALLIUM, ZINC, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(LEAD, COPPER, 5);
                mutation27.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(BISMUTH, NICKEL, 5);
                mutation28.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(POLONIUM, COBALT, 5);
                mutation29.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(ASTATINE, IRON, 5);
                mutation30.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(RADON, MANGANESE, 5);
                mutation31.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(FRANCIUM, CHROME, 4);
                mutation32.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(RADIUM, VANADIUM, 4);
                mutation33.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(ACTINIUM, TITANIUM, 4);
                mutation34.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(THORIUM, SCANDIUM, 4);
                mutation35.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(PROTACTINIUM, CALCIUM, 4);
                mutation36.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(URANIUM, POTASSIUM, 4);
                mutation37.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(NEPTUNIUM, ARGON, 3);
                mutation38.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(PLUTONIUM, CHLORINE, 3);
                mutation39.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(AMERICIUM, SULFUR, 3);
                mutation40.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(CURIUM, PHOSPHORUS, 3);
                mutation41.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(BERKELIUM, SILICON, 3);
                mutation42.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(CALIFORNIUM, ALUMINIUM, 3);
                mutation43.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(EINSTEINIUM, MAGNESIUM, 2);
                mutation44.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(FERMIUM, SODIUM, 2);
                mutation45.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(MENDELEVIUM, NEON, 2);
                mutation46.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(NOBELIUM, FLUORINE, 2);
                mutation47.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(LAWRENCIUM, OXYGEN, 2);
                mutation48.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation49 = dis.registerMutation(RUTHERFODIUM, NITROGEN, 2);
                mutation49.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation50 = dis.registerMutation(DUBNIUM, CARBON, 1);
                mutation50.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation51 = dis.registerMutation(SEABORGIUM, BORON, 1);
                mutation51.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation52 = dis.registerMutation(BOHRIUM, BERYLLIUM, 1);
                mutation52.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation53 = dis.registerMutation(HASSIUM, LITHIUM, 1);
                mutation53.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation54 = dis.registerMutation(MEITNERIUM, HELIUM, 1);
                mutation54.addMutationCondition(new MaterialMutationCondition(Roentgenium));
                IBeeMutationBuilder mutation55 = dis.registerMutation(DARMSTADTIUM, HYDROGEN, 1);
                mutation55.addMutationCondition(new MaterialMutationCondition(Roentgenium));
            }),
    COPERNICIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Copernicia", false, 0xFFFEFF, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.COPERNICIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(LANTHANUM, CAESIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(CERIUM, XENON, 9);
                mutation2.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(PRASEODYMIUM, IODINE, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(NEODYMIUM, TELLURIUM, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(PROMETHIUM, ANTIMONY, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(SAMARIUM, TIN, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(EUROPIUM, INDIUM, 9);
                mutation7.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(GADOLINIUM, CADMIUM, 8);
                mutation8.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(TERBIUM, SILVER, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(DYSPROSIUM, PALLADIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(HOLMIUM, RHODIUM, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(ERBIUM, RUTHENIUM, 8);
                mutation12.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(THULIUM, TECHNETIUM, 8);
                mutation13.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(YTTERBIUM, MOLYBDENUM, 7);
                mutation14.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(LUTETIUM, NIOBIUM, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(HAFNIUM, ZIRCONIUM, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(TANTALUM, YTTRIUM, 7);
                mutation17.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(TUNGSTEN, STRONTIUM, 7);
                mutation18.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(RHENIUM, RUBIDIUM, 7);
                mutation19.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(OSMIUM, KRYPTON, 6);
                mutation20.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(IRIDIUM, BROMINE, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(PLATINUM, SELENIUM, 6);
                mutation22.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(GOLD, ARSENIC, 6);
                mutation23.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(QUICKSILVER, GERMANIUM, 6);
                mutation24.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(THALLIUM, GALLIUM, 6);
                mutation25.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(LEAD, ZINC, 5);
                mutation26.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(BISMUTH, COPPER, 5);
                mutation27.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(POLONIUM, NICKEL, 5);
                mutation28.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(ASTATINE, COBALT, 5);
                mutation29.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(RADON, IRON, 5);
                mutation30.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(FRANCIUM, MANGANESE, 5);
                mutation31.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(RADIUM, CHROME, 4);
                mutation32.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(ACTINIUM, VANADIUM, 4);
                mutation33.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(THORIUM, TITANIUM, 4);
                mutation34.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(PROTACTINIUM, SCANDIUM, 4);
                mutation35.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(URANIUM, CALCIUM, 4);
                mutation36.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(NEPTUNIUM, POTASSIUM, 4);
                mutation37.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(PLUTONIUM, ARGON, 3);
                mutation38.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(AMERICIUM, CHLORINE, 3);
                mutation39.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(CURIUM, SULFUR, 3);
                mutation40.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(BERKELIUM, PHOSPHORUS, 3);
                mutation41.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(CALIFORNIUM, SILICON, 3);
                mutation42.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(EINSTEINIUM, ALUMINIUM, 3);
                mutation43.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(FERMIUM, MAGNESIUM, 2);
                mutation44.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(MENDELEVIUM, SODIUM, 2);
                mutation45.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(NOBELIUM, NEON, 2);
                mutation46.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(LAWRENCIUM, FLUORINE, 2);
                mutation47.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(RUTHERFODIUM, OXYGEN, 2);
                mutation48.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation49 = dis.registerMutation(DUBNIUM, NITROGEN, 2);
                mutation49.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation50 = dis.registerMutation(SEABORGIUM, CARBON, 1);
                mutation50.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation51 = dis.registerMutation(BOHRIUM, BORON, 1);
                mutation51.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation52 = dis.registerMutation(HASSIUM, BERYLLIUM, 1);
                mutation52.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation53 = dis.registerMutation(MEITNERIUM, LITHIUM, 1);
                mutation53.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation54 = dis.registerMutation(DARMSTADTIUM, HELIUM, 1);
                mutation54.addMutationCondition(new MaterialMutationCondition(Copernicium));
                IBeeMutationBuilder mutation55 = dis.registerMutation(ROENTGENIUM, HYDROGEN, 1);
                mutation55.addMutationCondition(new MaterialMutationCondition(Copernicium));
            }),
    NIHONIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Nihonia", false, 0x08269e, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.NIHONIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(LANTHANUM, BARIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(CERIUM, CAESIUM, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(PRASEODYMIUM, XENON, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(NEODYMIUM, IODINE, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(PROMETHIUM, TELLURIUM, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(SAMARIUM, ANTIMONY, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(EUROPIUM, TIN, 9);
                mutation7.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(GADOLINIUM, INDIUM, 9);
                mutation8.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(TERBIUM, CADMIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(DYSPROSIUM, SILVER, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(HOLMIUM, PALLADIUM, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(ERBIUM, RHODIUM, 8);
                mutation12.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(THULIUM, RUTHENIUM, 8);
                mutation13.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(YTTERBIUM, TECHNETIUM, 8);
                mutation14.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(LUTETIUM, MOLYBDENUM, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(HAFNIUM, NIOBIUM, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(TANTALUM, ZIRCONIUM, 7);
                mutation17.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(TUNGSTEN, YTTRIUM, 7);
                mutation18.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(RHENIUM, STRONTIUM, 7);
                mutation19.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(OSMIUM, RUBIDIUM, 7);
                mutation20.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(IRIDIUM, KRYPTON, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(PLATINUM, BROMINE, 6);
                mutation22.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(GOLD, SELENIUM, 6);
                mutation23.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(QUICKSILVER, ARSENIC, 6);
                mutation24.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(THALLIUM, GERMANIUM, 6);
                mutation25.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(LEAD, GALLIUM, 6);
                mutation26.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(BISMUTH, ZINC, 5);
                mutation27.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(POLONIUM, COPPER, 5);
                mutation28.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(ASTATINE, NICKEL, 5);
                mutation29.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(RADON, COBALT, 5);
                mutation30.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(FRANCIUM, IRON, 5);
                mutation31.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(RADIUM, MANGANESE, 5);
                mutation32.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(ACTINIUM, CHROME, 4);
                mutation33.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(THORIUM, VANADIUM, 4);
                mutation34.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(PROTACTINIUM, TITANIUM, 4);
                mutation35.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(URANIUM, SCANDIUM, 4);
                mutation36.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(NEPTUNIUM, CALCIUM, 4);
                mutation37.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(PLUTONIUM, POTASSIUM, 4);
                mutation38.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(AMERICIUM, ARGON, 3);
                mutation39.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(CURIUM, CHLORINE, 3);
                mutation40.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(BERKELIUM, SULFUR, 3);
                mutation41.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(CALIFORNIUM, PHOSPHORUS, 3);
                mutation42.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(EINSTEINIUM, SILICON, 3);
                mutation43.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(FERMIUM, ALUMINIUM, 3);
                mutation44.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(MENDELEVIUM, MAGNESIUM, 2);
                mutation45.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(NOBELIUM, SODIUM, 2);
                mutation46.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(LAWRENCIUM, NEON, 2);
                mutation47.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(RUTHERFODIUM, FLUORINE, 2);
                mutation48.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation49 = dis.registerMutation(DUBNIUM, OXYGEN, 2);
                mutation49.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation50 = dis.registerMutation(SEABORGIUM, NITROGEN, 2);
                mutation50.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation51 = dis.registerMutation(BOHRIUM, CARBON, 1);
                mutation51.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation52 = dis.registerMutation(HASSIUM, BORON, 1);
                mutation52.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation53 = dis.registerMutation(MEITNERIUM, BERYLLIUM, 1);
                mutation53.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation54 = dis.registerMutation(DARMSTADTIUM, LITHIUM, 1);
                mutation54.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation55 = dis.registerMutation(ROENTGENIUM, HELIUM, 1);
                mutation55.addMutationCondition(new MaterialMutationCondition(Nihonium));
                IBeeMutationBuilder mutation56 = dis.registerMutation(COPERNICIUM, HYDROGEN, 1);
                mutation56.addMutationCondition(new MaterialMutationCondition(Nihonium));
            }),
    FLEROVIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Flerovia", false, 0x521973, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.FLEROVIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(CERIUM, BARIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(PRASEODYMIUM, CAESIUM, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(NEODYMIUM, XENON, 9);
                mutation3.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(PROMETHIUM, IODINE, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(SAMARIUM, TELLURIUM, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(EUROPIUM, ANTIMONY, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(GADOLINIUM, TIN, 9);
                mutation7.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(TERBIUM, INDIUM, 9);
                mutation8.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(DYSPROSIUM, CADMIUM, 8);
                mutation9.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(HOLMIUM, SILVER, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(ERBIUM, PALLADIUM, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(THULIUM, RHODIUM, 8);
                mutation12.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(YTTERBIUM, RUTHENIUM, 8);
                mutation13.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(LUTETIUM, TECHNETIUM, 8);
                mutation14.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(HAFNIUM, MOLYBDENUM, 7);
                mutation15.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(TANTALUM, NIOBIUM, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(TUNGSTEN, ZIRCONIUM, 7);
                mutation17.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(RHENIUM, YTTRIUM, 7);
                mutation18.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(OSMIUM, STRONTIUM, 7);
                mutation19.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(IRIDIUM, RUBIDIUM, 7);
                mutation20.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(PLATINUM, KRYPTON, 6);
                mutation21.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(GOLD, BROMINE, 6);
                mutation22.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(QUICKSILVER, SELENIUM, 6);
                mutation23.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(THALLIUM, ARSENIC, 6);
                mutation24.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(LEAD, GERMANIUM, 6);
                mutation25.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(BISMUTH, GALLIUM, 6);
                mutation26.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(POLONIUM, ZINC, 5);
                mutation27.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(ASTATINE, COPPER, 5);
                mutation28.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(RADON, NICKEL, 5);
                mutation29.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(FRANCIUM, COBALT, 5);
                mutation30.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(RADIUM, IRON, 5);
                mutation31.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(ACTINIUM, MANGANESE, 5);
                mutation32.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(THORIUM, CHROME, 4);
                mutation33.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(PROTACTINIUM, VANADIUM, 4);
                mutation34.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(URANIUM, TITANIUM, 4);
                mutation35.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(NEPTUNIUM, SCANDIUM, 4);
                mutation36.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(PLUTONIUM, CALCIUM, 4);
                mutation37.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(AMERICIUM, POTASSIUM, 4);
                mutation38.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(CURIUM, ARGON, 3);
                mutation39.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(BERKELIUM, CHLORINE, 3);
                mutation40.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(CALIFORNIUM, SULFUR, 3);
                mutation41.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(EINSTEINIUM, PHOSPHORUS, 3);
                mutation42.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(FERMIUM, SILICON, 3);
                mutation43.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(MENDELEVIUM, ALUMINIUM, 3);
                mutation44.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(NOBELIUM, MAGNESIUM, 2);
                mutation45.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(LAWRENCIUM, SODIUM, 2);
                mutation46.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(RUTHERFODIUM, NEON, 2);
                mutation47.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(DUBNIUM, FLUORINE, 2);
                mutation48.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation49 = dis.registerMutation(SEABORGIUM, OXYGEN, 2);
                mutation49.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation50 = dis.registerMutation(BOHRIUM, NITROGEN, 2);
                mutation50.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation51 = dis.registerMutation(HASSIUM, CARBON, 1);
                mutation51.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation52 = dis.registerMutation(MEITNERIUM, BORON, 1);
                mutation52.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation53 = dis.registerMutation(DARMSTADTIUM, BERYLLIUM, 1);
                mutation53.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation54 = dis.registerMutation(ROENTGENIUM, LITHIUM, 1);
                mutation54.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation55 = dis.registerMutation(COPERNICIUM, HELIUM, 1);
                mutation55.addMutationCondition(new MaterialMutationCondition(Flerovium));
                IBeeMutationBuilder mutation56 = dis.registerMutation(NIHONIUM, HYDROGEN, 1);
                mutation56.addMutationCondition(new MaterialMutationCondition(Flerovium));
            }),
    MOSCOVIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Moscovia", false, 0x7854AD, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.MOSCOVIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(CERIUM, LANTHANUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(PRASEODYMIUM, BARIUM, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(NEODYMIUM, CAESIUM, 10);
                mutation3.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(PROMETHIUM, XENON, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(SAMARIUM, IODINE, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(EUROPIUM, TELLURIUM, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(GADOLINIUM, ANTIMONY, 9);
                mutation7.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(TERBIUM, TIN, 9);
                mutation8.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(DYSPROSIUM, INDIUM, 9);
                mutation9.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(HOLMIUM, CADMIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(ERBIUM, SILVER, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(THULIUM, PALLADIUM, 8);
                mutation12.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(YTTERBIUM, RHODIUM, 8);
                mutation13.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(LUTETIUM, RUTHENIUM, 8);
                mutation14.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(HAFNIUM, TECHNETIUM, 8);
                mutation15.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(TANTALUM, MOLYBDENUM, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(TUNGSTEN, NIOBIUM, 7);
                mutation17.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(RHENIUM, ZIRCONIUM, 7);
                mutation18.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(OSMIUM, YTTRIUM, 7);
                mutation19.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(IRIDIUM, STRONTIUM, 7);
                mutation20.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(PLATINUM, RUBIDIUM, 7);
                mutation21.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(GOLD, KRYPTON, 6);
                mutation22.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(QUICKSILVER, BROMINE, 6);
                mutation23.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(THALLIUM, SELENIUM, 6);
                mutation24.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(LEAD, ARSENIC, 6);
                mutation25.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(BISMUTH, GERMANIUM, 6);
                mutation26.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(POLONIUM, GALLIUM, 6);
                mutation27.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(ASTATINE, ZINC, 5);
                mutation28.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(RADON, COPPER, 5);
                mutation29.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(FRANCIUM, NICKEL, 5);
                mutation30.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(RADIUM, COBALT, 5);
                mutation31.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(ACTINIUM, IRON, 5);
                mutation32.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(THORIUM, MANGANESE, 5);
                mutation33.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(PROTACTINIUM, CHROME, 4);
                mutation34.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(URANIUM, VANADIUM, 4);
                mutation35.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(NEPTUNIUM, TITANIUM, 4);
                mutation36.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(PLUTONIUM, SCANDIUM, 4);
                mutation37.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(AMERICIUM, CALCIUM, 4);
                mutation38.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(CURIUM, POTASSIUM, 4);
                mutation39.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(BERKELIUM, ARGON, 3);
                mutation40.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(CALIFORNIUM, CHLORINE, 3);
                mutation41.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(EINSTEINIUM, SULFUR, 3);
                mutation42.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(FERMIUM, PHOSPHORUS, 3);
                mutation43.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(MENDELEVIUM, SILICON, 3);
                mutation44.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(NOBELIUM, ALUMINIUM, 3);
                mutation45.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(LAWRENCIUM, MAGNESIUM, 2);
                mutation46.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(RUTHERFODIUM, SODIUM, 2);
                mutation47.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(DUBNIUM, NEON, 2);
                mutation48.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation49 = dis.registerMutation(SEABORGIUM, FLUORINE, 2);
                mutation49.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation50 = dis.registerMutation(BOHRIUM, OXYGEN, 2);
                mutation50.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation51 = dis.registerMutation(HASSIUM, NITROGEN, 2);
                mutation51.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation52 = dis.registerMutation(MEITNERIUM, CARBON, 1);
                mutation52.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation53 = dis.registerMutation(DARMSTADTIUM, BORON, 1);
                mutation53.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation54 = dis.registerMutation(ROENTGENIUM, BERYLLIUM, 1);
                mutation54.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation55 = dis.registerMutation(COPERNICIUM, LITHIUM, 1);
                mutation55.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation56 = dis.registerMutation(NIHONIUM, HELIUM, 1);
                mutation56.addMutationCondition(new MaterialMutationCondition(Moscovium));
                IBeeMutationBuilder mutation57 = dis.registerMutation(FLEROVIUM, HYDROGEN, 1);
                mutation57.addMutationCondition(new MaterialMutationCondition(Moscovium));
            }),
    LIVERMORIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Livermoria", false, 0xAAAAAA, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.LIVERMORIUM), 0.10f);
            }, template -> {},
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(PRASEODYMIUM, LANTHANUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation2 = dis.registerMutation(NEODYMIUM, BARIUM, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation3 = dis.registerMutation(PROMETHIUM, CAESIUM, 10);
                mutation3.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation4 = dis.registerMutation(SAMARIUM, XENON, 9);
                mutation4.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation5 = dis.registerMutation(EUROPIUM, IODINE, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation6 = dis.registerMutation(GADOLINIUM, TELLURIUM, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation7 = dis.registerMutation(TERBIUM, ANTIMONY, 9);
                mutation7.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation8 = dis.registerMutation(DYSPROSIUM, TIN, 9);
                mutation8.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation9 = dis.registerMutation(HOLMIUM, INDIUM, 9);
                mutation9.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation10 = dis.registerMutation(ERBIUM, CADMIUM, 8);
                mutation10.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation11 = dis.registerMutation(THULIUM, SILVER, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation12 = dis.registerMutation(YTTERBIUM, PALLADIUM, 8);
                mutation12.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation13 = dis.registerMutation(LUTETIUM, RHODIUM, 8);
                mutation13.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation14 = dis.registerMutation(HAFNIUM, RUTHENIUM, 8);
                mutation14.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation15 = dis.registerMutation(TANTALUM, TECHNETIUM, 8);
                mutation15.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation16 = dis.registerMutation(TUNGSTEN, MOLYBDENUM, 7);
                mutation16.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation17 = dis.registerMutation(RHENIUM, NIOBIUM, 7);
                mutation17.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation18 = dis.registerMutation(OSMIUM, ZIRCONIUM, 7);
                mutation18.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation19 = dis.registerMutation(IRIDIUM, YTTRIUM, 7);
                mutation19.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation20 = dis.registerMutation(PLATINUM, STRONTIUM, 7);
                mutation20.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation21 = dis.registerMutation(GOLD, RUBIDIUM, 7);
                mutation21.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation22 = dis.registerMutation(QUICKSILVER, KRYPTON, 6);
                mutation22.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation23 = dis.registerMutation(THALLIUM, BROMINE, 6);
                mutation23.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation24 = dis.registerMutation(LEAD, SELENIUM, 6);
                mutation24.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation25 = dis.registerMutation(BISMUTH, ARSENIC, 6);
                mutation25.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation26 = dis.registerMutation(POLONIUM, GERMANIUM, 6);
                mutation26.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation27 = dis.registerMutation(ASTATINE, GALLIUM, 6);
                mutation27.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation28 = dis.registerMutation(RADON, ZINC, 5);
                mutation28.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation29 = dis.registerMutation(FRANCIUM, COPPER, 5);
                mutation29.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation30 = dis.registerMutation(RADIUM, NICKEL, 5);
                mutation30.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation31 = dis.registerMutation(ACTINIUM, COBALT, 5);
                mutation31.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation32 = dis.registerMutation(THORIUM, IRON, 5);
                mutation32.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation33 = dis.registerMutation(PROTACTINIUM, MANGANESE, 5);
                mutation33.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation34 = dis.registerMutation(URANIUM, CHROME, 4);
                mutation34.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation35 = dis.registerMutation(NEPTUNIUM, VANADIUM, 4);
                mutation35.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation36 = dis.registerMutation(PLUTONIUM, TITANIUM, 4);
                mutation36.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation37 = dis.registerMutation(AMERICIUM, SCANDIUM, 4);
                mutation37.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation38 = dis.registerMutation(CURIUM, CALCIUM, 4);
                mutation38.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation39 = dis.registerMutation(BERKELIUM, POTASSIUM, 4);
                mutation39.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation40 = dis.registerMutation(CALIFORNIUM, ARGON, 3);
                mutation40.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation41 = dis.registerMutation(EINSTEINIUM, CHLORINE, 3);
                mutation41.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation42 = dis.registerMutation(FERMIUM, SULFUR, 3);
                mutation42.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation43 = dis.registerMutation(MENDELEVIUM, PHOSPHORUS, 3);
                mutation43.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation44 = dis.registerMutation(NOBELIUM, SILICON, 3);
                mutation44.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation45 = dis.registerMutation(LAWRENCIUM, ALUMINIUM, 3);
                mutation45.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation46 = dis.registerMutation(RUTHERFODIUM, MAGNESIUM, 2);
                mutation46.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation47 = dis.registerMutation(DUBNIUM, SODIUM, 2);
                mutation47.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation48 = dis.registerMutation(SEABORGIUM, NEON, 2);
                mutation48.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation49 = dis.registerMutation(BOHRIUM, FLUORINE, 2);
                mutation49.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation50 = dis.registerMutation(HASSIUM, OXYGEN, 2);
                mutation50.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation51 = dis.registerMutation(MEITNERIUM, NITROGEN, 2);
                mutation51.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation52 = dis.registerMutation(DARMSTADTIUM, CARBON, 1);
                mutation52.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation53 = dis.registerMutation(ROENTGENIUM, BORON, 1);
                mutation53.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation54 = dis.registerMutation(COPERNICIUM, BERYLLIUM, 1);
                mutation54.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation55 = dis.registerMutation(NIHONIUM, LITHIUM, 1);
                mutation55.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation56 = dis.registerMutation(FLEROVIUM, HELIUM, 1);
                mutation56.addMutationCondition(new MaterialMutationCondition(Livermorium));
                IBeeMutationBuilder mutation57 = dis.registerMutation(MOSCOVIUM, HYDROGEN, 1);
                mutation57.addMutationCondition(new MaterialMutationCondition(Livermorium));
            }),
    TENNESSINE(GJBranchDefinition.GJ_HALOGENS, "Tennessina", false, 0x977FD6, 0xFFBC5E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.TENNESSINE), 0.10f);
            },
            template -> AlleleHelper.getInstance().set(template, EFFECT, AlleleEffects.effectRadioactive),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(PRASEODYMIUM, CERIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation2 = dis.registerMutation(NEODYMIUM, LANTHANUM, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation3 = dis.registerMutation(PROMETHIUM, BARIUM, 10);
                mutation3.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation4 = dis.registerMutation(SAMARIUM, CAESIUM, 10);
                mutation4.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation5 = dis.registerMutation(EUROPIUM, XENON, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation6 = dis.registerMutation(GADOLINIUM, IODINE, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation7 = dis.registerMutation(TERBIUM, TELLURIUM, 9);
                mutation7.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation8 = dis.registerMutation(DYSPROSIUM, ANTIMONY, 9);
                mutation8.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation9 = dis.registerMutation(HOLMIUM, TIN, 9);
                mutation9.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation10 = dis.registerMutation(ERBIUM, INDIUM, 9);
                mutation10.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation11 = dis.registerMutation(THULIUM, CADMIUM, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation12 = dis.registerMutation(YTTERBIUM, SILVER, 8);
                mutation12.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation13 = dis.registerMutation(LUTETIUM, PALLADIUM, 8);
                mutation13.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation14 = dis.registerMutation(HAFNIUM, RHODIUM, 8);
                mutation14.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation15 = dis.registerMutation(TANTALUM, RUTHENIUM, 8);
                mutation15.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation16 = dis.registerMutation(TUNGSTEN, TECHNETIUM, 8);
                mutation16.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation17 = dis.registerMutation(RHENIUM, MOLYBDENUM, 7);
                mutation17.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation18 = dis.registerMutation(OSMIUM, NIOBIUM, 7);
                mutation18.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation19 = dis.registerMutation(IRIDIUM, ZIRCONIUM, 7);
                mutation19.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation20 = dis.registerMutation(PLATINUM, YTTRIUM, 7);
                mutation20.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation21 = dis.registerMutation(GOLD, STRONTIUM, 7);
                mutation21.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation22 = dis.registerMutation(QUICKSILVER, RUBIDIUM, 7);
                mutation22.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation23 = dis.registerMutation(THALLIUM, KRYPTON, 6);
                mutation23.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation24 = dis.registerMutation(LEAD, BROMINE, 6);
                mutation24.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation25 = dis.registerMutation(BISMUTH, SELENIUM, 6);
                mutation25.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation26 = dis.registerMutation(POLONIUM, ARSENIC, 6);
                mutation26.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation27 = dis.registerMutation(ASTATINE, GERMANIUM, 6);
                mutation27.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation28 = dis.registerMutation(RADON, GALLIUM, 6);
                mutation28.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation29 = dis.registerMutation(FRANCIUM, ZINC, 5);
                mutation29.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation30 = dis.registerMutation(RADIUM, COPPER, 5);
                mutation30.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation31 = dis.registerMutation(ACTINIUM, NICKEL, 5);
                mutation31.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation32 = dis.registerMutation(THORIUM, COBALT, 5);
                mutation32.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation33 = dis.registerMutation(PROTACTINIUM, IRON, 5);
                mutation33.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation34 = dis.registerMutation(URANIUM, MANGANESE, 5);
                mutation34.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation35 = dis.registerMutation(NEPTUNIUM, CHROME, 4);
                mutation35.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation36 = dis.registerMutation(PLUTONIUM, VANADIUM, 4);
                mutation36.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation37 = dis.registerMutation(AMERICIUM, TITANIUM, 4);
                mutation37.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation38 = dis.registerMutation(CURIUM, SCANDIUM, 4);
                mutation38.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation39 = dis.registerMutation(BERKELIUM, CALCIUM, 4);
                mutation39.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation40 = dis.registerMutation(CALIFORNIUM, POTASSIUM, 4);
                mutation40.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation41 = dis.registerMutation(EINSTEINIUM, ARGON, 3);
                mutation41.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation42 = dis.registerMutation(FERMIUM, CHLORINE, 3);
                mutation42.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation43 = dis.registerMutation(MENDELEVIUM, SULFUR, 3);
                mutation43.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation44 = dis.registerMutation(NOBELIUM, PHOSPHORUS, 3);
                mutation44.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation45 = dis.registerMutation(LAWRENCIUM, SILICON, 3);
                mutation45.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation46 = dis.registerMutation(RUTHERFODIUM, ALUMINIUM, 3);
                mutation46.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation47 = dis.registerMutation(DUBNIUM, MAGNESIUM, 2);
                mutation47.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation48 = dis.registerMutation(SEABORGIUM, SODIUM, 2);
                mutation48.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation49 = dis.registerMutation(BOHRIUM, NEON, 2);
                mutation49.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation50 = dis.registerMutation(HASSIUM, FLUORINE, 2);
                mutation50.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation51 = dis.registerMutation(MEITNERIUM, OXYGEN, 2);
                mutation51.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation52 = dis.registerMutation(DARMSTADTIUM, NITROGEN, 2);
                mutation52.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation53 = dis.registerMutation(ROENTGENIUM, CARBON, 1);
                mutation53.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation54 = dis.registerMutation(COPERNICIUM, BORON, 1);
                mutation54.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation55 = dis.registerMutation(NIHONIUM, BERYLLIUM, 1);
                mutation55.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation56 = dis.registerMutation(FLEROVIUM, LITHIUM, 1);
                mutation56.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation57 = dis.registerMutation(MOSCOVIUM, HELIUM, 1);
                mutation57.addMutationCondition(new MaterialMutationCondition(Tennessine));
                IBeeMutationBuilder mutation58 = dis.registerMutation(LIVERMORIUM, HYDROGEN, 1);
                mutation58.addMutationCondition(new MaterialMutationCondition(Tennessine));
            }),
    OGANESSON(GJBranchDefinition.GJ_NOBLEGAS, "Oganessa", false, 0x142D64, 0xFAFAFA,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.20f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.OGANESSON), 0.10f);
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, EFFECT, AlleleEffects.effectRadioactive),
            dis -> {
                IBeeMutationBuilder mutation1 = dis.registerMutation(NEODYMIUM, CERIUM, 10);
                mutation1.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation2 = dis.registerMutation(PROMETHIUM, LANTHANUM, 10);
                mutation2.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation3 = dis.registerMutation(SAMARIUM, BARIUM, 10);
                mutation3.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation4 = dis.registerMutation(EUROPIUM, CAESIUM, 10);
                mutation4.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation5 = dis.registerMutation(GADOLINIUM, XENON, 9);
                mutation5.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation6 = dis.registerMutation(TERBIUM, IODINE, 9);
                mutation6.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation7 = dis.registerMutation(DYSPROSIUM, TELLURIUM, 9);
                mutation7.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation8 = dis.registerMutation(HOLMIUM, ANTIMONY, 9);
                mutation8.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation9 = dis.registerMutation(ERBIUM, TIN, 9);
                mutation9.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation10 = dis.registerMutation(THULIUM, INDIUM, 9);
                mutation10.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation11 = dis.registerMutation(YTTERBIUM, CADMIUM, 8);
                mutation11.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation12 = dis.registerMutation(LUTETIUM, SILVER, 8);
                mutation12.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation13 = dis.registerMutation(HAFNIUM, PALLADIUM, 8);
                mutation13.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation14 = dis.registerMutation(TANTALUM, RHODIUM, 8);
                mutation14.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation15 = dis.registerMutation(TUNGSTEN, RUTHENIUM, 8);
                mutation15.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation16 = dis.registerMutation(RHENIUM, TECHNETIUM, 8);
                mutation16.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation17 = dis.registerMutation(OSMIUM, MOLYBDENUM, 7);
                mutation17.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation18 = dis.registerMutation(IRIDIUM, NIOBIUM, 7);
                mutation18.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation19 = dis.registerMutation(PLATINUM, ZIRCONIUM, 7);
                mutation19.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation20 = dis.registerMutation(GOLD, YTTRIUM, 7);
                mutation20.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation21 = dis.registerMutation(QUICKSILVER, STRONTIUM, 7);
                mutation21.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation22 = dis.registerMutation(THALLIUM, RUBIDIUM, 7);
                mutation22.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation23 = dis.registerMutation(LEAD, KRYPTON, 6);
                mutation23.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation24 = dis.registerMutation(BISMUTH, BROMINE, 6);
                mutation24.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation25 = dis.registerMutation(POLONIUM, SELENIUM, 6);
                mutation25.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation26 = dis.registerMutation(ASTATINE, ARSENIC, 6);
                mutation26.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation27 = dis.registerMutation(RADON, GERMANIUM, 6);
                mutation27.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation28 = dis.registerMutation(FRANCIUM, GALLIUM, 6);
                mutation28.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation29 = dis.registerMutation(RADIUM, ZINC, 5);
                mutation29.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation30 = dis.registerMutation(ACTINIUM, COPPER, 5);
                mutation30.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation31 = dis.registerMutation(THORIUM, NICKEL, 5);
                mutation31.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation32 = dis.registerMutation(PROTACTINIUM, COBALT, 5);
                mutation32.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation33 = dis.registerMutation(URANIUM, IRON, 5);
                mutation33.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation34 = dis.registerMutation(NEPTUNIUM, MANGANESE, 5);
                mutation34.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation35 = dis.registerMutation(PLUTONIUM, CHROME, 4);
                mutation35.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation36 = dis.registerMutation(AMERICIUM, VANADIUM, 4);
                mutation36.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation37 = dis.registerMutation(CURIUM, TITANIUM, 4);
                mutation37.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation38 = dis.registerMutation(BERKELIUM, SCANDIUM, 4);
                mutation38.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation39 = dis.registerMutation(CALIFORNIUM, CALCIUM, 4);
                mutation39.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation40 = dis.registerMutation(EINSTEINIUM, POTASSIUM, 4);
                mutation40.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation41 = dis.registerMutation(FERMIUM, ARGON, 3);
                mutation41.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation42 = dis.registerMutation(MENDELEVIUM, CHLORINE, 3);
                mutation42.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation43 = dis.registerMutation(NOBELIUM, SULFUR, 3);
                mutation43.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation44 = dis.registerMutation(LAWRENCIUM, PHOSPHORUS, 3);
                mutation44.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation45 = dis.registerMutation(RUTHERFODIUM, SILICON, 3);
                mutation45.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation46 = dis.registerMutation(DUBNIUM, ALUMINIUM, 3);
                mutation46.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation47 = dis.registerMutation(SEABORGIUM, MAGNESIUM, 2);
                mutation47.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation48 = dis.registerMutation(BOHRIUM, SODIUM, 2);
                mutation48.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation49 = dis.registerMutation(HASSIUM, NEON, 2);
                mutation49.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation50 = dis.registerMutation(MEITNERIUM, FLUORINE, 2);
                mutation50.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation51 = dis.registerMutation(DARMSTADTIUM, OXYGEN, 2);
                mutation51.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation52 = dis.registerMutation(ROENTGENIUM, NITROGEN, 2);
                mutation52.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation53 = dis.registerMutation(COPERNICIUM, CARBON, 1);
                mutation53.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation54 = dis.registerMutation(NIHONIUM, BORON, 1);
                mutation54.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation55 = dis.registerMutation(FLEROVIUM, BERYLLIUM, 1);
                mutation55.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation56 = dis.registerMutation(MOSCOVIUM, LITHIUM, 1);
                mutation56.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation57 = dis.registerMutation(LIVERMORIUM, HELIUM, 1);
                mutation57.addMutationCondition(new MaterialMutationCondition(Oganesson));
                IBeeMutationBuilder mutation58 = dis.registerMutation(TENNESSINE, HYDROGEN, 1);
                mutation58.addMutationCondition(new MaterialMutationCondition(Oganesson));
            }),
    // Other GT
    CLAY(GJBranchDefinition.GJ_ORGANIC, "Lutum", true, 0xC8C8DA, 0x0000FF,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.30f);
                beeSpecies.addSpecialty(new ItemStack(Items.CLAY_BALL), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.DAMP);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
                if (Mods.BiomesOPlenty.isModLoaded()) {
                    beeSpecies.addSpecialty(Mods.BiomesOPlenty.getItem("mudball", 0), 0.05f);
                }
            },
            template -> {
                AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(INDUSTRIOUS, BeeDefinition.DILIGENT,
                        10);
                mutation.requireResource(Blocks.HARDENED_CLAY.getDefaultState());
            }),
    SLIMEBALL(GJBranchDefinition.GJ_ORGANIC, "Bituminipila", true, 0x4E9E55, 0x00FF15,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.MOSSY), 0.30f);
                beeSpecies.addProduct(new ItemStack(Items.SLIME_BALL), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.STICKY), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.DAMP);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
                if (Mods.TinkersConstruct.isModLoaded()) {
                    beeSpecies.addProduct(Mods.TinkersConstruct.getItem("edible", 1), 0.10f);
                    beeSpecies.addSpecialty(Mods.TinkersConstruct.getItem("slime_congealed", 2), 0.01f);
                } else {
                    beeSpecies.addSpecialty(new ItemStack(Blocks.SLIME_BLOCK), 0.01f);
                }
            },
            template -> {
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.MUSHROOMS);
                AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
                AlleleHelper.getInstance().set(template, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.BOTH_1);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.BOTH_1);
                if (Mods.ExtraBees.isModLoaded()) {
                    AlleleHelper.getInstance().set(template, FLOWER_PROVIDER,
                            ForestryUtil.getFlowers(Mods.ExtraBees, "water"));
                }
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.MARSHY, CLAY, 7);
                mutation.requireResource(Blocks.SLIME_BLOCK.getDefaultState());
            }),
    PEAT(GJBranchDefinition.GJ_ORGANIC, "Limus", true, 0x906237, 0x58300B,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.15f);
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.COAL), 0.15f);
                beeSpecies.addSpecialty(ModuleCore.getItems().peat.getItemStack(), 0.30f);
                beeSpecies.addSpecialty(ModuleCore.getItems().mulch.getItemStack(), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER);
                AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.SHORTER);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.WHEAT);
                AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.FASTER);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
            },
            dis -> dis.registerMutation(BeeDefinition.RURAL, CLAY, 10)),
    STICKYRESIN(GJBranchDefinition.GJ_ORGANIC, "Lenturesinae", true, 0x2E8F5B, 0xDCC289,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.30f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.STICKY), 0.15f);
                beeSpecies.addSpecialty(MetaItems.STICKY_RESIN.getStackForm(), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> {
                AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(SLIMEBALL, PEAT, 15);
                mutation.requireResource("logRubber");
            }),
    COAL(GJBranchDefinition.GJ_ORGANIC, "Carbo", true, 0x666666, 0x525252,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.COAL), 0.30f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.COKE), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> {
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.CACTI);
                AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.LONGER);
                AlleleHelper.getInstance().set(template, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.DOWN_2);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.DOWN_1);
                AlleleHelper.getInstance().set(template, NEVER_SLEEPS, true);
                AlleleHelper.getInstance().set(template, EFFECT, AlleleEffects.effectCreeper);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(INDUSTRIOUS, PEAT, 9);
                mutation.addMutationCondition(new MaterialMutationCondition(Coal));
            }),
    OIL(GJBranchDefinition.GJ_ORGANIC, "Oleum", true, 0x4C4C4C, 0x333333,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.30f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.OIL), 0.75f);
                beeSpecies.setHumidity(EnumHumidity.DAMP);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.SLOWER);
                AlleleHelper.getInstance().set(template, NEVER_SLEEPS, true);
                AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.NORMAL);
                AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER);
                AlleleHelper.getInstance().set(template, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.NONE);
                AlleleHelper.getInstance().set(template, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
                if (Mods.ExtraBees.isModLoaded()) {
                    AlleleHelper.getInstance().set(template, FLOWER_PROVIDER,
                            ForestryUtil.getFlowers(Mods.ExtraBees, "water"));
                }
            },
            dis -> dis.registerMutation(COAL, STICKYRESIN, 4)),
    ASH(GJBranchDefinition.GJ_ORGANIC, "Cinis", true, 0x1E1A18, 0xC6C6C6,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.30f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.ASH), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.NORMAL);
                AlleleHelper.getInstance().set(template, TERRITORY, EnumAllele.Territory.LARGE);
                AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.SHORTER);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.WHEAT);
                AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.FASTER);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(COAL, CLAY, 10);
                mutation.restrictTemperature(EnumTemperature.HELLISH);
            }),
    APATITE(GJBranchDefinition.GJ_ORGANIC, "Stercorat", true, 0x7FCEF5, 0x654525,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.APATITE), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.WARM);
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.FASTEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.LONGER);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.WHEAT);
                AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.FASTER);
                if (Mods.ExtraBees.isModLoaded()) {
                    AlleleHelper.getInstance().set(template, FLOWER_PROVIDER,
                            ForestryUtil.getFlowers(Mods.ExtraBees, "rock"));
                }
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(ASH, COAL, 10);
                mutation.requireResource("blockApatite");
            }),
    BIOMASS(GJBranchDefinition.GJ_ORGANIC, "Taeda", true, 0x21E118, 0x17AF0E,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.MOSSY), 0.30f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.BIOMASS), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.FASTEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.LONGEST);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.WHEAT);
                AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.FASTER);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(INDUSTRIOUS, BeeDefinition.RURAL, 10);
                mutation.restrictBiomeType(BiomeDictionary.Type.FOREST);
            }),
    FERTILIZER(GJBranchDefinition.GJ_ORGANIC, "Stercorat", true, 0x7FCEF5, 0x654525,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.MOSSY), 0.15f);
                beeSpecies.addSpecialty(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash), 0.2f);
                beeSpecies.addSpecialty(OreDictUnifier.get(OrePrefix.dustTiny, Materials.DarkAsh), 0.2f);
                beeSpecies.addSpecialty(MetaItems.FERTILIZER.getStackForm(), 0.3f);
                beeSpecies.addSpecialty(Mods.Forestry.getItem("fertilizer_compound", 0), 0.3f);
                beeSpecies.setHumidity(EnumHumidity.DAMP);
                beeSpecies.setTemperature(EnumTemperature.WARM);
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.FASTEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.LONGER);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.WHEAT);
                AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.FASTER);
            },
            dis -> dis.registerMutation(ASH, APATITE, 8)),
    REDSTONE(GJBranchDefinition.GJ_GEM, "Rubrumlapis", true, 0x7D0F0F, 0xD11919,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.STONE), 0.30f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.REDSTONE), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.RAREEARTH), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(INDUSTRIOUS, BeeDefinition.DEMONIC,
                        10);
                mutation.addMutationCondition(new MaterialMutationCondition(Redstone));
            }),
    LAPIS(GJBranchDefinition.GJ_GEM, "Lapidi", true, 0x1947D1, 0x476CDA,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.STONE), 0.30f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.LAPIS), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.DEMONIC, IMPERIAL, 10);
                mutation.addMutationCondition(new MaterialMutationCondition(Lapis));
            }),
    CERTUS(GJBranchDefinition.GJ_GEM, "Quarzeus", true, 0x57CFFB, 0xBBEEFF,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.STONE), 0.30f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.CERTUS), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.HERMITIC, LAPIS, 10);
                mutation.addMutationCondition(new MaterialMutationCondition(CertusQuartz));
            }),
    DIAMOND(GJBranchDefinition.GJ_GEM, "Adamas", false, 0xCCFFFF, 0xA3CCCC,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.STONE), 0.30f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.DIAMOND), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.HOT);
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(CERTUS, COAL, 3);
                mutation.addMutationCondition(new MaterialMutationCondition(Diamond));
            }),
    EMERALD(GJBranchDefinition.GJ_GEM, "Smaragdus", false, 0x248F24, 0x2EB82E,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.STONE), 0.30f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.EMERALD), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.ALUMINIUM), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.COLD);
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(OLIVINE, DIAMOND, 4);
                mutation.addMutationCondition(new MaterialMutationCondition(Emerald));
            }),
    ELECTROTINE(GJBranchDefinition.GJ_RAREMETAL, "Electrum", false, 0x1E90FF, 0x3CB4C8,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.ELECTROTINE), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.REDSTONE), 0.05f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.HOT);
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(REDSTONE, GOLD, 5);
                mutation.addMutationCondition(new MaterialMutationCondition(Electrotine));
            }),
    // Industrial
    ENERGY(GJBranchDefinition.GJ_INDUSTRIAL, "Industria", false, 0xC11F1F, 0xEBB9B9,
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.SIMMERING), 0.30f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.ENERGY), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.WARM);
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER);
                AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.LONGER);
                AlleleHelper.getInstance().set(template, EFFECT, AlleleEffects.effectIgnition);
                AlleleHelper.getInstance().set(template, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.DOWN_2);
                AlleleHelper.getInstance().set(template, NEVER_SLEEPS, true);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.NETHER);
                AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.AVERAGE);
            },
            dis -> {
                IBeeMutationBuilder mutation;
                if (Mods.ExtraBees.isModLoaded()) {
                    mutation = dis.registerMutation(BeeDefinition.DEMONIC,
                            ForestryUtil.getSpecies(Mods.ExtraBees, "volcanic"), 10);
                } else {
                    mutation = dis.registerMutation(BeeDefinition.DEMONIC, BeeDefinition.FIENDISH, 10);
                }
                mutation.addMutationCondition(new MaterialMutationCondition(Redstone));
            }),
    LAPOTRON(GJBranchDefinition.GJ_INDUSTRIAL, "Azureus", false, 0xFFEBC4, 0xE36400,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.LAPIS), 0.20f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.ENERGY), 0.15f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.LAPOTRON), 0.10f);
                beeSpecies.setHumidity(EnumHumidity.DAMP);
                beeSpecies.setTemperature(EnumTemperature.ICY);
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWER);
                AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.LONGER);
                AlleleHelper.getInstance().set(template, EFFECT, AlleleEffects.effectIgnition);
                AlleleHelper.getInstance().set(template, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.UP_1);
                AlleleHelper.getInstance().set(template, NEVER_SLEEPS, true);
                AlleleHelper.getInstance().set(template, FLOWER_PROVIDER, EnumAllele.Flowers.SNOW);
                AlleleHelper.getInstance().set(template, FLOWERING, EnumAllele.Flowering.AVERAGE);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(LAPIS, ENERGY, 6);
                mutation.addMutationCondition(new MaterialMutationCondition(Lapis));
                mutation.restrictTemperature(EnumTemperature.ICY);
            }),
    NAQUADAH(GJBranchDefinition.GJ_RADIOACTIVE, "Nasquis", false, 0x003300, 0x002400,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.NAQUADAH), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.ICY);
                beeSpecies.setNocturnal();
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.LONGEST);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(PLUTONIUM, IRIDIUM, 1);
                mutation.addMutationCondition(new MaterialMutationCondition(Naquadah));
            }),
    NAQUADRIA(GJBranchDefinition.GJ_RADIOACTIVE, "Nasquidrius", false, 0x000000, 0x002400,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.SLAG), 0.30f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.NAQUADAH), 0.20f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.NAQUADRIA), 0.15f);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setTemperature(EnumTemperature.ICY);
                beeSpecies.setNocturnal();
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.LONGEST);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(PLUTONIUM, IRIDIUM, 1);
                mutation.addMutationCondition(new MaterialMutationCondition(Naquadria));
            }),
    TRINIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Trinium", false, 0xB0E0E6, 0xC8C8D2,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.TRINIUM), 0.75f);
                beeSpecies.addSpecialty(getGTComb(gregtech.integration.forestry.bees.GTCombType.NAQUADAH), 0.10f);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setTemperature(EnumTemperature.COLD);
                beeSpecies.setNocturnal();
                beeSpecies.setHasEffect();
            },
            template -> AlleleHelper.getInstance().set(template, SPEED, GTAlleleBeeSpecies.speedBlinding),
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(IRIDIUM, NAQUADAH, 4);
                mutation.addMutationCondition(new MaterialMutationCondition(Trinium));
            }),
    NEUTRONIUM(GJBranchDefinition.GJ_RADIOACTIVE, "Media", false, 0xFFF0F0, 0xFAFAFA,
            beeSpecies -> {
                beeSpecies.addProduct(getGTComb(gregtech.integration.forestry.bees.GTCombType.NEUTRONIUM), 0.0001f);
                beeSpecies.setHumidity(EnumHumidity.DAMP);
                beeSpecies.setTemperature(EnumTemperature.HELLISH);
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, SPEED, EnumAllele.Speed.SLOWEST);
                AlleleHelper.getInstance().set(template, LIFESPAN, EnumAllele.Lifespan.LONGEST);
                AlleleHelper.getInstance().set(template, NEVER_SLEEPS, true);
            },
            dis -> {
                IMutationBuilder mutation = dis.registerMutation(NAQUADRIA, AMERICIUM, 1).setIsSecret();
                mutation.addMutationCondition(new MaterialMutationCondition(Neutronium));
            }),
    // Thaumcraft
    // Primal
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
    // Composite
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
            }),
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
            }),
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
            }),
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
            }),
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
            }),
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
            }),
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
            }),
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
            }),
    POTENTIA(GJBranchDefinition.GJ_MAGIC, "Potentia", false, 0xc0ffff, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(ORDER), 0.25f);
                beeSpecies.addProduct(getGJComb(FIRE), 0.25f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.ENERGY), 0.2f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(ORDO, IGNIS, 15);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }),
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
            }),
    PRAECANTATIO(GJBranchDefinition.GJ_MAGIC, "Praecantatio", true, 0xcf00ff, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(AIR), 0.5f);
                beeSpecies.addProduct(getGJComb(GJCombType.ENERGY), 0.5f);
                beeSpecies.addSpecialty(getGJComb(MAGIC), 0.25f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(AER, POTENTIA, 10);
                mutation.requireNight();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }),
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
            }),
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
            }),
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
            }),
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
            }),
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
            }),
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
            }),
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
            }),
    INSTRUMENTUM(GJBranchDefinition.GJ_MAGIC, "Instrumentum", true, 0x4040ee, 0xFF6D00,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(METAL), 0.75f);
                beeSpecies.addProduct(getGJComb(GJCombType.ENERGY), 0.75f);
                beeSpecies.addSpecialty(getGJComb(TOOL), 0.5f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(METALLUM, POTENTIA, 5);
                mutation.requireDay();
                mutation.restrictBiomeType(BiomeDictionary.Type.MAGICAL);
            }),
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
            }),
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
            }),
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
            }),
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
            }),
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
            }),
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
            }),
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
            }),
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
            }),
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
            }),
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
            }),
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
            }),
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
            }),
    // Glaktikraft
    SPACE(GJBranchDefinition.GJ_PLANETS, "Space", false, 0x003366, 0xC0C0C0,
            beeSpecies -> beeSpecies.addProduct(getGJComb(GJCombType.SPACE), 0.75f),
            template -> {},
            dis -> dis.registerMutation(INDUSTRIOUS, IMPERIAL, 10)),
    METEORICIRON(GJBranchDefinition.GJ_PLANETS, "MeteoricIron", false, 0x321928, 0x643250,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.SPACE), 0.25f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.METEORICIRON), 0.2f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(SPACE, IRON, 9);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.MOON));
            }),
    LUNA(GJBranchDefinition.GJ_PLANETS, "Luna", false, 0x373735, 0x7E7E78,
            beeSpecies -> beeSpecies.addProduct(getGJComb(MOON), 0.75f),
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(SPACE, CLAY, 4);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.MOON));
            }),
    MARS(GJBranchDefinition.GJ_PLANETS, "Mars", false, 0x220D05, 0x3A1505,
            beeSpecies -> beeSpecies.addProduct(getGJComb(GJCombType.MARS), 0.75f),
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(LUNA, IRON, 20);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.MARS));
            }),
    PHOBOS(GJBranchDefinition.GJ_PLANETS, "Phobos", true, 0x220D05, 0x7a5706,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.MARS), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust, PhobosStone), 0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(MARS, LUNA, 20);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.PHOBOS));
            }),
    DEIMOS(GJBranchDefinition.GJ_PLANETS, "Deimos", true, 0x220D05, 0x7a3206,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.MARS), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust, DeimosStone), 0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(MARS, SPACE, 20);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.DEIMOS));
            }),
    CERES(GJBranchDefinition.GJ_PLANETS, "Ceres", true, 0x3ca5b7, 0x1e7267,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.MARS), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust, CeresStone), 0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(MARS, METEORICIRON, 20);
                mutation.requireDay();
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.CERES));
            }),
    DESH(GJBranchDefinition.GJ_PLANETS, "Desh", false, 0x323232, 0x282828,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.SPACE), 0.25f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.DESH), 0.2f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(MARS, TITANIUM, 9);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.MARS));
            }),
    MERCURY(GJBranchDefinition.GJ_PLANETS, "Mercury", false, 0x4A4033, 0xB5A288,
            beeSpecies -> beeSpecies.addProduct(getGJComb(GJCombType.MERCURY), 0.75f),
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(METEORICIRON, TUNGSTEN, 25);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.MERCURY));
            }),
    VENUS(GJBranchDefinition.GJ_PLANETS, "Venus", false, 0x4A4033, 0xB5A288,
            beeSpecies -> beeSpecies.addProduct(getGJComb(GJCombType.VENUS), 0.75f),
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(MERCURY, PLATINUM, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.VENUS));
            }),
    JUPITER(GJBranchDefinition.GJ_PLANETS, "Jupiter", false, 0x734B2E, 0xD0CBC4,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.JUPITER), 0.35f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(MARS, DESH, 15);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.ASTEROIDS));
            }),
    EUROPA(GJBranchDefinition.GJ_PLANETS, "Europa", true, 0x5982ea, 0x0b36a3,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.JUPITER), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust, EuropaStone), 0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(JUPITER, IRON, 15);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.EUROPA));
            }),
    IO(GJBranchDefinition.GJ_PLANETS, "Io", true, 0x734B2E, 0xe5701b,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.JUPITER), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust, IoStone), 0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(JUPITER, DEMONIC, 15);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.IO));
            }),
    GANYMEDE(GJBranchDefinition.GJ_PLANETS, "Ganymede", true, 0x3d1b10, 0x190c07,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.JUPITER), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust, GanymedeStone), 0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(JUPITER, TITANIUM, 15);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.GANYMEDE));
            }),
    CALLISTO(GJBranchDefinition.GJ_PLANETS, "Callisto", true, 0x0f333d, 0x0d84a5,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.JUPITER), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust, CallistoStone), 0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(JUPITER, GLACIAL, 15);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.CALLISTO));
            }),
    LEDOX(GJBranchDefinition.GJ_PLANETS, "Ledox", false, 0x0000CD, 0x0074FF,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.SPACE), 0.25f);
                beeSpecies.addSpecialty(getGJComb(GJCombType.LEDOX), 0.2f);
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(CALLISTO, LEAD, 15);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.CALLISTO));
            }),
    SATURN(GJBranchDefinition.GJ_PLANETS, "Saturn", false, 0xD2A472, 0xF8C37B,
            beeSpecies -> beeSpecies.addProduct(getGJComb(GJCombType.SATURN), 0.5f),
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(JUPITER, LEDOX, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.ASTEROIDS));
            }),
    ENCELADUS(GJBranchDefinition.GJ_PLANETS, "Enceladus", true, 0xD2A472, 0x193fa0,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.SATURN), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust, EnceladusStone), 0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(SATURN, CHROME, 25);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.ENCELADUS));
            }),
    TITAN(GJBranchDefinition.GJ_PLANETS, "Titan", true, 0xa0641b, 0x7c1024,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.SATURN), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust, TitanStone), 0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(SATURN, NICKEL, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.TITAN));
            }),
    URANUS(GJBranchDefinition.GJ_PLANETS, "Uranus", false, 0x75C0C9, 0x84D8EC,
            beeSpecies -> beeSpecies.addProduct(getGJComb(GJCombType.URANUS), 0.75f),
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(SATURN, TRINIUM, 10);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.ASTEROIDS));
            }),
    MIRANDA(GJBranchDefinition.GJ_PLANETS, "Miranda", true, 0x75C0C9, 0x0d211c,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.URANUS), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust, MirandaStone), 0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(URANUS, TIN, 10);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.MIRANDA));
            }),
    OBERON(GJBranchDefinition.GJ_PLANETS, "Oberon", true, 0x4A4033, 0xB5A288,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.URANUS), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust, OberonStone), 0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(URANUS, IRIDIUM, 10);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.OBERON));
            }),
    NEPTUNE(GJBranchDefinition.GJ_PLANETS, "Neptune", false, 0x334CFF, 0x576DFF,
            beeSpecies -> beeSpecies.addProduct(getGJComb(GJCombType.NEPTUNE), 0.75f),
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(URANUS, OBERON, 7);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.ASTEROIDS));
            }),
    PROTEUS(GJBranchDefinition.GJ_PLANETS, "Proteus", true, 0x334CFF, 0x592610,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.NEPTUNE), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust, ProteusStone), 0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(NEPTUNE, COPPER, 7);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.PROTEUS));
            }),
    TRITON(GJBranchDefinition.GJ_PLANETS, "Triton", true, 0x334CFF, 0x421118,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.NEPTUNE), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust, TritonStone), 0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(NEPTUNE, GOLD, 7);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.TRITON));
            }),
    PLUTO(GJBranchDefinition.GJ_PLANETS, "Pluto", false, 0x34271E, 0x69503D,
            beeSpecies -> beeSpecies.addProduct(getGJComb(GJCombType.PLUTO), 0.75f),
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(NEPTUNE, PLUTONIUM, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.PLUTO));
            }),
    HAUMEA(GJBranchDefinition.GJ_PLANETS, "Haumea", true, 0x1C1413, 0x392B28,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.PLUTO), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust, HaumeaStone), 0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(PLUTO, NAQUADAH, 7);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.HAUMEA));
            }),
    MAKEMAKE(GJBranchDefinition.GJ_PLANETS, "Makemake", true, 0x301811, 0x120A07,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.PLUTO), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust, MakemakeStone), 0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(PLUTO, NAQUADRIA, 7);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.MAKEMAKE));
            }),
    CENTAURI(GJBranchDefinition.GJ_PLANETS, "Centauri", false, 0x2F2A14, 0xB06B32,
            beeSpecies -> beeSpecies.addProduct(getGJComb(GJCombType.CENTAURI), 0.75f),
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(MAKEMAKE, DESH, 3);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.ASTEROIDS));
            }),
    PROXIMA_B(GJBranchDefinition.GJ_PLANETS, "ProximaB", false, 0x2F2A14, 0xC17C43,
            beeSpecies -> beeSpecies.addProduct(getGJComb(PROXIMA), 0.5f),
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(CENTAURI, TUNGSTEN, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.PROXIMA));
            }),
    BARNADA(GJBranchDefinition.GJ_PLANETS, "BarnadaC", false, 0x0D5A0D, 0xE6C18D,
            beeSpecies -> beeSpecies.addProduct(getGJComb(GJCombType.BARNADA), 0.75f),
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(CENTAURI, THORIUM, 3);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.BARNADA));
            }),
    BARNADA_C1(GJBranchDefinition.GJ_PLANETS, "BarnadaC1", true, 0x0D5A0D, 0x1e0b49,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.BARNADA), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust, BarnadaC1Stone), 0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BARNADA, NAQUADRIA, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.BARNADAC1));
            }),
    BARNADA_C2(GJBranchDefinition.GJ_PLANETS, "BarnadaC2", true, 0x0D5A0D, 0x1e0b49,
            beeSpecies -> {
                beeSpecies.addProduct(getGJComb(GJCombType.BARNADA), 0.5f);
                beeSpecies.addSpecialty(OreDictUnifier.get(dust, BarnadaC2Stone), 0.25f);
                beeSpecies.setHasEffect();
            },
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BARNADA, NEUTRONIUM, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.BARNADAC2));
            }),
    TAUCETI(GJBranchDefinition.GJ_PLANETS, "TauCeti", false, 0xffd7c0, 0xFF6D00,
            beeSpecies -> beeSpecies.addProduct(getGJComb(CETI), 0.75f),
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BARNADA, ELECTROTINE, 5);
                // TODO: Dimensions ID
            }),
    CHALOS(GJBranchDefinition.GJ_PLANETS, "Chalos", false, 0x0074FF, 0x1EB1FF,
            beeSpecies -> beeSpecies.addProduct(getGJComb(GJCombType.CHALOS), 0.75f),
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(TAUCETI, HEROIC, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.CHALOS));
            }),
    DIONA(GJBranchDefinition.GJ_PLANETS, "Diona", false, 0xDAA520, 0xF26404,
            beeSpecies -> beeSpecies.addProduct(getGJComb(GJCombType.DIONA), 0.75f),
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(CHALOS, REDALLOY, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.DIONA));
            }),
    FRONOS(GJBranchDefinition.GJ_PLANETS, "Fronos", false, 0x00FF00, 0x00D10B,
            beeSpecies -> beeSpecies.addProduct(getGJComb(GJCombType.FRONOS), 0.75f),
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(DIONA, URANIUM, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.FRONOS));
            }),
    NIBIRU(GJBranchDefinition.GJ_PLANETS, "Nibiru", false, 0x228B22, 0x677D68,
            beeSpecies -> beeSpecies.addProduct(getGJComb(GJCombType.NIBIRU), 0.75f),
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(FRONOS, THORIUM, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.NIBIRU));
            }),
    KOENTUS(GJBranchDefinition.GJ_PLANETS, "Koentus", false, 0x3CB371, 0x16856C,
            beeSpecies -> beeSpecies.addProduct(getGJComb(GJCombType.KOENTUS), 0.75f),
            template -> {},
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(NIBIRU, NAQUADRIA, 5);
                mutation.addMutationCondition(new DimensionMutationCondition(GJUtil.Dimensions.KOENTUS));
            });

    private final GJBranchDefinition branch;
    private final GTAlleleBeeSpecies species;
    private final Consumer<GTAlleleBeeSpecies> speciesProperties;
    private final Consumer<IAllele[]> alleles;
    private final Consumer<GTBeeDefinition> mutations;
    private IAllele[] template;
    private IBeeGenome genome;
    private final Supplier<Boolean> generationCondition;

    GTBeeDefinition(GJBranchDefinition branch,
                    String binomial,
                    boolean dominant,
                    int primary,
                    int secondary,
                    Consumer<GTAlleleBeeSpecies> speciesProperties,
                    Consumer<IAllele[]> alleles,
                    Consumer<GTBeeDefinition> mutations) {
        this(branch, binomial, dominant, primary, secondary, speciesProperties, alleles, mutations, () -> true);
    }

    GTBeeDefinition(GJBranchDefinition branch,
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
        this.species = new GTAlleleBeeSpecies(GTValues.MODID, uid, name, "GregTech", description, dominant,
                branch.getBranch(), binomial, primary, secondary);
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

    public static ItemStack getForestryComb(EnumHoneyComb type) {
        return getForestryComb(type, 1);
    }

    public static ItemStack getForestryComb(EnumHoneyComb type, int amount) {
        return ModuleApiculture.getItems().beeComb.get(type, amount);
    }

    public static ItemStack getGTComb(GTCombType type) {
        return getGTComb(type, 1);
    }

    public static ItemStack getGTComb(GTCombType type, int amount) {
        return new ItemStack(ForestryModule.COMBS, amount, type.ordinal());
    }

    public static ItemStack getGJComb(GJCombType type) {
        return getJComb(type, 1);
    }

    public static ItemStack getJComb(GJCombType type, int amount) {
        return new ItemStack(gregsjourney.integration.forestry.ForestryModule.COMBS, amount, type.ordinal());
    }
}
