package gregicality.legacy.integration.forestry;

public enum GTCombType {

    STELLITE100("stellite100", 0xDEDEFF, 0xFAFAFA);

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
