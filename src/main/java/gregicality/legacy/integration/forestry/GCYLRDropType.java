package gregicality.legacy.integration.forestry;

public enum GCYLRDropType {

    STELLITE100("stellite100", 0xDEDEFF, 0xFAFAFA);

    public static final GCYLRDropType[] VALUES = values();

    public final String name;
    public final int[] color;

    GCYLRDropType(String name, int primary, int secondary) {
        this.name = name;
        this.color = new int[]{primary, secondary};
    }

    public static GCYLRDropType getDrop(int meta) {
        return meta < 0 || meta >= VALUES.length ? VALUES[0] : VALUES[meta];
    }
}
