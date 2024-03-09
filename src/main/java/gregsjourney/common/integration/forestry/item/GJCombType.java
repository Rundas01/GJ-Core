package gregsjourney.common.integration.forestry.item;

public enum GJCombType {
    //Thaumcraft
    AIR("aer", 0xffff7e, 0x86AFF0),
    EARTH("terra", 0x56c000, 0x86AFF0),
    FIRE("ignis", 0xff5a01, 0x86AFF0),
    WATER("aqua", 0x3cd4fc, 0x86AFF0),
    ORDER("ordo", 0xd5d4ec, 0x86AFF0),
    ENTROPY("perditio", 0x404040, 0x86AFF0),
    VOID("vacuos",0x888888,0x86AFF0),
    LIGHT("lux",0xffffc0,0x86AFF0),
    MOTION("motus",0xcdccf4,0x86AFF0),
    COLD("gelum",0xe1ffff,0x86AFF0),
    CRYSTAL("vitreus",0x80ffff,0x86AFF0),
    METAL("metallum",0xb5b5cd,0x86AFF0),
    LIFE("victus",0xde0005,0x86AFF0),
    DEATH("mortuus",0x6a0005,0x86AFF0),
    ENERGY("potentia",0xc0ffff,0x86AFF0),
    EXCHANGE("permutatio",0x578357,0x86AFF0),
    MAGIC("praecantatio",0xcf00ff,0x86AFF0),
    AURA("auram",0xffc0ff,0x86AFF0),
    ALCHEMY("alkimia",0x23ac9d,0x86AFF0),
    FLUX("vitium",0x800080,0x86AFF0),
    DARKNESS("tenebrae",0x222222,0x86AFF0),
    ELDRITCH("alienis",0x805080,0x86AFF0),
    FLIGHT("volatus",0xe7e7d7,0x86AFF0),
    PLANT("herba",0x01ac00,0x86AFF0),
    TOOL("instrumentum",0x4040ee,0x86AFF0),
    CRAFT("fabrico",0x809d80,0x86AFF0),
    MECHANISM("machina",0x8080a0,0x86AFF0),
    TRAP("vinculum",0x9a8080,0x86AFF0),
    SOUL("spiritus",0xebebfb,0x86AFF0),
    MIND("cognitio",0xf9967f,0x86AFF0),
    SENSES("sensus",0xc0ffc0,0x86AFF0),
    AVERSION("aversio",0xc05050,0x86AFF0),
    PROTECT("praemunio",0x00c0c0,0x86AFF0),
    DESIRE("desiderium",0xe6be44,0x86AFF0),
    UNDEAD("exanimis",0x3a4000,0x86AFF0),
    BEAST("bestia",0x9f6409,0x86AFF0),
    MAN("humanus",0xffd7c0,0x86AFF0),
    //Galacticraft
    SPACE("space",0x003366,0xC0C0C0),
    METEORICIRON("meteoric_iron",0x321928,0x643250),
    DESH("desh",0x282828,0x323232),
    LEDOX("ledox",0x0000CD,0x0074FF),
    MERCURY("mercury",0x4A4033,0xB5A288),
    VENUS("venus",0x120E07,0x272010),
    MOON("moon",0x373735,0x7E7E78),
    MARS("mars",0x220D05,0x3A1505),
    JUPITER("jupiter",0x734B2E,0xD0CBC4),
    SATURN("saturn",0xD2A472,0xF8C37B),
    URANUS("uranus",0x75C0C9,0x84D8EC),
    NEPTUNE("neptune",0x334CFF,0x576DFF),
    PLUTO("pluto",0x34271E,0x69503D),
    CENTAURI("centauri",0x2F2A14,0xB06B32),
    CETI("ceti",0x46241A,0x7B412F),
    BARNADA("barnada",0x0D5A0D,0xE6C18D),
    PROXIMA("proxima",0x0D5A0D,0xE6C18D),
    CHALOS("chalos",0x0D5A0D,0xE6C18D),
    DIONA("diona",0x0D5A0D,0xE6C18D),
    FRONOS("fronos",0x0D5A0D,0xE6C18D),
    NIBIRU("nibiru",0x0D5A0D,0xE6C18D),
    KOENTUS("koentus",0x15CFED,0x57AB1E);
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
