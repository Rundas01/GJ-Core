package gregsjourney.integration.forestry.item;

public enum GJDropType {

    TEST("test", 0xFFC100, 0x00FF11);

    public static final GJDropType[] VALUES = values();

    public final String name;
    public final int[] color;

    GJDropType(String name, int primary, int secondary) {
        this.name = name;
        this.color = new int[] { primary, secondary };
    }

    public static GJDropType getDrop(int meta) {
        return meta < 0 || meta >= VALUES.length ? VALUES[0] : VALUES[meta];
    }
}
