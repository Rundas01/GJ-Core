package gregsjourney.common.metatileentities.part;

import gregsjourney.api.metatileentity.part.FilteredItemBus;
import gregsjourney.utils.StringUtil;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.*;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class OreDictItemBus extends FilteredItemBus {
    private String oredictString = "";
    private int mode = 0;
    public static final Pattern allowedSymbols = Pattern.compile("[a-zA-Z,&|!()* ]+");
    private final boolean isExport;

    public OreDictItemBus(ResourceLocation metaTileEntityId, int tier, boolean isExport) {
        super(metaTileEntityId, tier, isExport);
        this.isExport = isExport;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new OreDictItemBus(metaTileEntityId, getTier(), this.isExport);
    }

    private boolean belongsToOreDict(ItemStack stack) {
        if (oredictString.isEmpty()) {
            return false;
        }
        int[] ids = OreDictionary.getOreIDs(stack);
        List<String> names = new ArrayList<>();
        for (int id : ids) {
            names.add(OreDictionary.getOreName(id));
        }
        String[] conditions = oredictConditions(oredictString, true);
        for (String name : names) {
            if (StringUtil.checkOredictString(name, conditions)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCorrectName(ItemStack stack) {
        if (oredictString.isEmpty()) {
            return false;
        }
        String[] conditions = oredictConditions(oredictString,false);
        String name = stack.getItem().getItemStackDisplayName(stack);
        return StringUtil.checkOredictString(name, conditions);
    }

    private static String[] oredictConditions(String s, boolean removeWhitespaces) {
        String ss = s.replaceAll(", ","");
        ss = ss.replaceAll(" ,", "");
        ss = removeWhitespaces ? s.replaceAll("\\s","") : ss;
        if (!ss.contains(",")) {
            return new String[]{ss};
        }
        return ss.split(",");
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        SlotWidget gsWidget = new GhostCircuitSlotWidget(circuitInventory, 0, 8, 34).setBackgroundTexture(GuiTextures.SLOT, GuiTextures.INT_CIRCUIT_OVERLAY);
        ImageWidget imWidget = new ImageWidget(8, 14, 158, 16, GuiTextures.DISPLAY);
        TextFieldWidget2 tfWidget = new TextFieldWidget2(10, 19, 176, 16, () -> oredictString, value -> {oredictString = value; handler.changeCondition(mode == 0 ? this::hasCorrectName : this::belongsToOreDict);}).setAllowedChars(allowedSymbols);
        CycleButtonWidget cbWidget = new CycleButtonWidget(112, 33, 46, 20, new String[]{"Name", "Oredict"}, () -> mode, value -> {mode = value; handler.changeCondition(mode == 0 ? this::hasCorrectName : this::belongsToOreDict);});

        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 130 + 18 * getTier()).label(10, 5, getMetaFullName());
        int halfwidth = 88, offset;
        for (int i = 0; i < getTier(); i++) {
            offset = -9 * getTier();
            for (int j = 0; j < getTier(); j++) {
                builder.widget(new SlotWidget(handler, i * getTier() + j, halfwidth + offset, 18 * (1 + i) + 16, true, !isExport).setBackgroundTexture(GuiTextures.SLOT));
                offset += 18;
            }
        }
        builder.widget(imWidget);
        builder.widget(tfWidget);
        if (!isExport) {
            builder.widget(gsWidget.setConsumer(this::getCircuitSlotTooltip));
        }
        builder.widget(cbWidget);
        return builder.bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT, 7, 18 * getTier() + 46).build(getHolder(), entityPlayer);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        data.setString("oredictString", oredictString);
        data.setInteger("mode", mode);
        return super.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        oredictString = data.getString("oredictString");
        mode = data.getInteger("mode");
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeInt(oredictString.length());
        buf.writeString(oredictString);
        buf.writeInt(mode);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        oredictString = buf.readString(buf.readInt());
        mode = buf.readInt();
    }
}
