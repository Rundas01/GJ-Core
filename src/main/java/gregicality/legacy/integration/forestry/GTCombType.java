package gregicality.legacy.integration.forestry;

public enum GTCombType {
    GLOWSTONE("glowstone",0x9D95A5, 0xFAFAFA),
    MOLYBDENUM("molybdenum", 0xA8A8CD, 0xFAFAFA),
    NIOBIUM("niobium",0x9D95A5,0xFAFAFA),
    COBALT("cobalt",0x03265F, 0x59AAEF),
    MICA("mica",0xFFC826,0xC1C1F6),
    STELLITE100("stellite100", 0xDEDEFF, 0xFAFAFA),
    WATERTIGHTSTEEL("watertightsteel", 0x355D6A, 0xFAFAFA),
    MARAGINGSTEEL300("maragingsteel300", 0x637087, 0xFAFAFA),
    HASTELLOYC276("hastelloyc276", 0xCF3939, 0xFAFAFA),
    HASTELLOYX("hastelloyx", 0x6BA3E3, 0xFAFAFA),
    TRINAQUADALLOY("trinaquadalloy", 0x281832, 0xFAFAFA),
    ZERON100("zeron100", 0x325A8C, 0xFAFAFA),
    TITANIUMCARBIDE("titaniumcarbide", 0xB20B3A, 0xFAFAFA),
    TANTALUMCARBIDE("tantalumcarbide", 0x56566A, 0xFAFAFA),
    MOLYBDENUMDISILICIDE("molybdenumdisilicide", 0x6A5BA3, 0xFAFAFA),
    HSLASTEEL("hslasteel", 0x808080, 0xFAFAFA),
    TITANIUMTUNGSTENCARBIDE("titaniumtungstencarbide", 0x800D0D, 0xFAFAFA),
    INCOLOYMA956("incoloyma976", 0x37BF7E, 0xFAFAFA);


    public static final GTCombType[] VALUES = values();

    public final boolean showInList;
    public final String name;
    public final int[] color;

    GTCombType(String name, int primary, int secondary) {
        this(name, primary, secondary, true);
    }

    GTCombType(String name, int primary, int secondary, boolean show) {
        this.name = name;
        this.color = new int[]{primary, secondary};
        this.showInList = show;
    }

    public static GTCombType getComb(int meta) {
        return meta < 0 || meta > VALUES.length ? VALUES[0] : VALUES[meta];
    }
}
