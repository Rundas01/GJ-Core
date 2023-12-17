package gregicality.legacy.integration.forestry;

public enum GCYLRCombType {

    STELLITE100("stellite100", 0xDEDEFF, 0xFAFAFA);

    public static final GCYLRCombType[] VALUES = values();

    public final boolean showInList;
    public final String name;
    public final int[] color;

    GCYLRCombType(String name, int primary, int secondary) {
        this(name, primary, secondary, true);
    }

    GCYLRCombType(String name, int primary, int secondary, boolean show) {
        this.name = name;
        this.color = new int[]{primary, secondary};
        this.showInList = show;
    }

    public static GCYLRCombType getComb(int meta) {
        return meta < 0 || meta > VALUES.length ? VALUES[0] : VALUES[meta];
    }
}
