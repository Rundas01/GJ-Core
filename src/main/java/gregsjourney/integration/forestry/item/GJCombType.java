package gregsjourney.integration.forestry.item;

public enum GJCombType {

    // Elements
    BERYLLIUM("beryllium", 0x64B464, 0x403e38),
    BORON("boron", 0xD2FAD2, 0x403e38),
    CARBON("carbon", 0x141414, 0x403e38),
    SODIUM("sodium", 0x000096, 0x403e38),
    SILICON("silicon", 0x858279, 0x403e38),
    CHLORINE("chlorine", 0x2D8C8C, 0x403e38),
    POTASSIUM("potassium", 0xBEDCFF, 0x403e38),
    CALCIUM("calcium", 0xFFF5DE, 0x403e38),
    SCANDIUM("scandium", 0xFFFFFF, 0x006600),
    VANADIUM("vanadium", 0x323232, 0xD02001),
    COBALT("cobalt", 0x5050FA, 0x28874B),
    GERMANIUM("germanium", 0x434343, 0x006600),
    SELENIUM("selenium", 0xB6BA6B, 0x006600),
    BROMINE("bromine", 0x500A0A, 0x403e38),
    RUBIDIUM("rubidium", 0xF01E1E, 0x006600),
    STRONTIUM("strontium", 0xC8C8C8, 0x006600),
    YTTRIUM("yttrium", 0x76524C, 0x403e38),
    ZIRCONIUM("zirconium", 0xC8FFFF, 0x006600),
    NIOBIUM("niobium", 0xBEB4C8, 0xB591FF),
    TECHNETIUM("technetium", 0x545455, 0xFFBC5E),
    RUTHENIUM("ruthenium", 0x50ACCD, 0xB591FF),
    RHODIUM("rhodium", 0xDC0C58, 0xB591FF),
    PALLADIUM("palladium", 0x808080, 0xB591FF),
    CADMIUM("cadmium", 0x32323C, 0xB591FF),
    ANTIMONY("antimony", 0xDCDCF0, 0xB591FF),
    TELLURIUM("tellurium", 0xFFFFFF, 0xFFBC5E),
    IODINE("tellurium", 0x2C344F, 0xFFBC5E),
    CAESIUM("caesium", 0x80620B, 0xB591FF),
    BARIUM("barium", 0x83824C, 0xB591FF),
    LANTHANUM("lanthanum", 0x5D7575, 0xB591FF),
    CERIUM("cerium", 0x87917D, 0xB591FF),
    PRASEODYMIUM("praseodymium", 0xCECECE, 0xFFBC5E),
    NEODYMIUM("neodymium", 0x646464, 0xB591FF),
    PROMETHIUM("promethium", 0xFFFFFF, 0xFFBC5E),
    SAMARIUM("samarium", 0xFFFFCC, 0xB591FF),
    EUROPIUM("europium", 0x20FFFF, 0xB591FF),
    GADOLINIUM("gadolinium", 0xDDDDFF, 0xFFBC5E),
    TERBIUM("terbium", 0xFFFFFF, 0xFFBC5E),
    DYSPROSIUM("dysprosium", 0xFFFFEE, 0xFFBC5E),
    HOLMIUM("dysprosium", 0xFFFFFF, 0xFFBC5E),
    ERBIUM("dysprosium", 0xEEEEEE, 0xFFBC5E),
    THULIUM("dysprosium", 0xFFFFFF, 0xFFBC5E),
    YTTERBIUM("dysprosium", 0xA7A7A7, 0xFFBC5E),
    LUTETIUM("dysprosium", 0x00AAFF, 0xFFBC5E),
    HAFNIUM("dysprosium", 0x99999A, 0xFFBC5E),
    TANTALUM("tantalum", 0x69B7FF, 0xD02001),
    RHENIUM("dysprosium", 0xB6BAC3, 0xFFBC5E),
    QUICKSILVER("quicksilver", 0xE6DCDC, 0xb5b3df),
    THALLIUM("thallium", 0xC1C1DE, 0xB591FF),
    BISMUTH("bismuth", 0x64A0A0, 0xB591FF),
    POLONIUM("polonium", 0xC9D47E, 0xB591FF),
    ASTATINE("astatine", 0x241A24, 0xB591FF),
    RADON("radon", 0xFF39FF, 0xB591FF),
    FRANCIUM("francium", 0xAAAAAA, 0xB591FF),
    RADIUM("radium", 0xFFFFCD, 0xB591FF),
    ACTINIUM("actinium", 0xC3D1FF, 0xB591FF),
    PROTACTINIUM("protactinium", 0xA78B6D, 0xB591FF),
    NEPTUNIUM("neptunium", 0x284D7B, 0xFFBC5E),
    AMERICIUM("americium", 0x287869, 0xFFBC5E),
    CURIUM("curium", 0x7B544E, 0xFFBC5E),
    BERKELIUM("berkelium", 0x645A88, 0xFFBC5E),
    CALIFORNIUM("californium", 0xA85A12, 0xFFBC5E),
    EINSTEINIUM("einsteinium", 0xCE9F00, 0xFFBC5E),
    FERMIUM("fermium", 0x984ACF, 0xFFBC5E),
    MENDELEVIUM("mendelevium", 0x1D4ACF, 0xFFBC5E),
    NOBELIUM("nobelium", 0x3C7B1F, 0xFFBC5E),
    LAWRENCIUM("lawrencium", 0x412B6D, 0xFFBC5E),
    RUTHERFORDIUM("rutherfordium", 0xFFF6A1, 0xFFBC5E),
    DUBNIUM("dubnium", 0xD3FDFF, 0xFFBC5E),
    SEABORGIUM("seaborgium", 0x19c5ff, 0xFFBC5E),
    BOHRIUM("bohrium", 0xdc57ff, 0xFFBC5E),
    HASSIUM("hassium", 0x2d3a9d, 0xFFBC5E),
    MEITNERIUM("meitnerium", 0x2246be, 0xFFBC5E),
    DARMSTADTIUM("darmstadtium", 0x578062, 0xFFBC5E),
    ROENTGENIUM("roentgenium", 0xe3fdec, 0xFFBC5E),
    COPERNICIUM("copernicium", 0xFFFEFF, 0xFFBC5E),
    NIHONIUM("nihonium", 0x08269e, 0xFFBC5E),
    FLEROVIUM("flerovium", 0x521973, 0xFFBC5E),
    MOSCOVIUM("moscovium", 0x7854AD, 0xFFBC5E),
    LIVERMORIUM("livermorium", 0xAAAAAA, 0xFFBC5E),
    TENNESSINE("tennessine", 0x977FD6, 0xFFBC5E),
    OGANESSON("oganesson", 0x142D64, 0xFAFAFA),
    // Misc
    GLOWSTONE("glowstone", 0xf7c51e, 0xf5e7b8),
    FLUIX("fluix", 0xA375FF, 0xB591FF),
    // Draconic Evo
    DRACONIC("draconium", 0x161616, 0x6200e7),
    AWAKENEDDRACONIUM("awakeneddraconium", 0xD41238, 0xFFA157),
    // Tinkers
    ARDITE("ardite", 0xf56a2f, 0x28874B),
    MANYULLUN("manyullun", 0xe922f0, 0x28874B),
    // Thaumcraft
    AIR("aer", 0xffff7e, 0x86AFF0),
    EARTH("terra", 0x56c000, 0x86AFF0),
    FIRE("ignis", 0xff5a01, 0x86AFF0),
    WATER("aqua", 0x3cd4fc, 0x86AFF0),
    ORDER("ordo", 0xd5d4ec, 0x86AFF0),
    ENTROPY("perditio", 0x404040, 0x86AFF0),
    VOID("vacuos", 0x888888, 0x86AFF0),
    LIGHT("lux", 0xffffc0, 0x86AFF0),
    MOTION("motus", 0xcdccf4, 0x86AFF0),
    COLD("gelum", 0xe1ffff, 0x86AFF0),
    CRYSTAL("vitreus", 0x80ffff, 0x86AFF0),
    METAL("metallum", 0xb5b5cd, 0x86AFF0),
    LIFE("victus", 0xde0005, 0x86AFF0),
    DEATH("mortuus", 0x6a0005, 0x86AFF0),
    ENERGY("potentia", 0xc0ffff, 0x86AFF0),
    EXCHANGE("permutatio", 0x578357, 0x86AFF0),
    MAGIC("praecantatio", 0xcf00ff, 0x86AFF0),
    AURA("auram", 0xffc0ff, 0x86AFF0),
    ALCHEMY("alkimia", 0x23ac9d, 0x86AFF0),
    FLUX("vitium", 0x800080, 0x86AFF0),
    DARKNESS("tenebrae", 0x222222, 0x86AFF0),
    ELDRITCH("alienis", 0x805080, 0x86AFF0),
    FLIGHT("volatus", 0xe7e7d7, 0x86AFF0),
    PLANT("herba", 0x01ac00, 0x86AFF0),
    TOOL("instrumentum", 0x4040ee, 0x86AFF0),
    CRAFT("fabrico", 0x809d80, 0x86AFF0),
    MECHANISM("machina", 0x8080a0, 0x86AFF0),
    TRAP("vinculum", 0x9a8080, 0x86AFF0),
    SOUL("spiritus", 0xebebfb, 0x86AFF0),
    MIND("cognitio", 0xf9967f, 0x86AFF0),
    SENSES("sensus", 0xc0ffc0, 0x86AFF0),
    AVERSION("aversio", 0xc05050, 0x86AFF0),
    PROTECT("praemunio", 0x00c0c0, 0x86AFF0),
    DESIRE("desiderium", 0xe6be44, 0x86AFF0),
    UNDEAD("exanimis", 0x3a4000, 0x86AFF0),
    BEAST("bestia", 0x9f6409, 0x86AFF0),
    MAN("humanus", 0xffd7c0, 0x86AFF0),
    THAUMIUMDUST("thaumiumdust", 0x7A007A, 0x5C005C),
    THAUMIUMSHARD("thaumiumshard", 0x9966FF, 0xAD85FF),
    AMBER("amber", 0x774B15, 0xEE7700),
    SALISMUNDUS("salismundus", 0xF7ADDE, 0x592582),
    TAINTED("tainted", 0x904BB8, 0xE800FF),
    // Galacticraft
    SPACE("space", 0x003366, 0xC0C0C0),
    METEORICIRON("meteoric_iron", 0x321928, 0x643250),
    DESH("desh", 0x282828, 0x323232),
    LEDOX("ledox", 0x0000CD, 0x0074FF),
    MERCURY("mercury", 0x4A4033, 0xB5A288),
    VENUS("venus", 0x120E07, 0x272010),
    MOON("moon", 0x373735, 0x7E7E78),
    MARS("mars", 0x220D05, 0x3A1505),
    JUPITER("jupiter", 0x734B2E, 0xD0CBC4),
    SATURN("saturn", 0xD2A472, 0xF8C37B),
    URANUS("uranus", 0x75C0C9, 0x84D8EC),
    NEPTUNE("neptune", 0x334CFF, 0x576DFF),
    PLUTO("pluto", 0x34271E, 0x69503D),
    CENTAURI("centauri", 0x2F2A14, 0xB06B32),
    CETI("ceti", 0x46241A, 0x7B412F),
    BARNADA("barnada", 0x0D5A0D, 0xE6C18D),
    PROXIMA("proxima", 0x0D5A0D, 0xE6C18D),
    CHALOS("chalos", 0x0D5A0D, 0xE6C18D),
    DIONA("diona", 0x0D5A0D, 0xE6C18D),
    FRONOS("fronos", 0x0D5A0D, 0xE6C18D),
    NIBIRU("nibiru", 0x0D5A0D, 0xE6C18D),
    KOENTUS("koentus", 0x15CFED, 0x57AB1E),
    KEPLER("kepler", 0x15CFED, 0x57AB1E),
    // Twilight Forest
    NAGA("naga", 0x0D5A0D, 0x28874B),
    LICH("lich", 0x5C605E, 0xC5C5C5),
    HYDRA("hydra", 0x872836, 0xB8132C),
    URGHAST("urghast", 0x7C0618, 0xA7041C),
    SNOWQUEEN("snowqueen", 0x9C0018, 0xD02001),
    // GCYM
    STELLITE("stellite", 0x0D5A0D, 0x28874B),
    WATERTIGHTSTEEL("watertightsteel", 0x5C605E, 0xC5C5C5),
    MARAGINGSTEEL("maragingsteel", 0x872836, 0xB8132C),
    TRINAQUADALLOY("trinaquadalloy", 0x7C0618, 0xA7041C),
    TITANIUMCARBIDE("titaniumcarbide", 0x9C0018, 0xD02001),
    TANTALUMCARBIDE("tantalumcarbide", 0x9C0018, 0xD02001),
    TUNGSTENCARBIDE("tungstencarbide", 0x9C0018, 0xD02001),
    TITANIUMTUNGSTENCARBIDE("titaniumtungstencarbide", 0x9C0018, 0xD02001),
    MOLYBDENUMDISILICIDE("molybdenumdisilicide", 0x9C0018, 0xD02001),
    HSLASTEEL("hslasteel", 0x9C0018, 0xD02001),
    // Ender IO
    REDSTONEALLOY("redstonealloy", 0xB80000, 0xA50808),
    CONDUCTIVEIRON("conductiveiron", 0x817671, 0xCEADA3),
    VIBRANTALLOY("vibrantalloy", 0x86A12D, 0xC4F2AE),
    ENERGETICALLOY("energeticalloy", 0xFF9933, 0xFFAD5C),
    ELECTRICALSTEEL("electricalsteel", 0x787878, 0xD8D8D8),
    DARKSTEEL("darksteel", 0x252525, 0x443B44),
    PULSATINGIRON("pulsatingiron", 0x006600, 0x6DD284);

    public static final GJCombType[] VALUES = values();

    public final boolean showInList;
    public final String name;
    public final int[] color;

    GJCombType(String name, int primary, int secondary) {
        this(name, primary, secondary, true);
    }

    GJCombType(String name, int primary, int secondary, boolean show) {
        this.name = name;
        this.color = new int[] { primary, secondary };
        this.showInList = show;
    }

    public static GJCombType getComb(int meta) {
        return meta < 0 || meta > VALUES.length ? VALUES[0] : VALUES[meta];
    }
}
