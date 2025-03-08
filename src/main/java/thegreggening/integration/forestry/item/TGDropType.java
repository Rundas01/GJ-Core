package thegreggening.integration.forestry.item;

public enum TGDropType {

    TEST("test", 0xFFC100, 0x00FF11);

    public static final TGDropType[] VALUES = values();

    public final String name;
    public final int[] color;

    TGDropType(String name, int primary, int secondary) {
        this.name = name;
        this.color = new int[] { primary, secondary };
    }

    public static TGDropType getDrop(int meta) {
        return meta < 0 || meta >= VALUES.length ? VALUES[0] : VALUES[meta];
    }
}
