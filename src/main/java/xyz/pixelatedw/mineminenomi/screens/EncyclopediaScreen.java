package xyz.pixelatedw.mineminenomi.screens;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ReadBookScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ChangePageButton;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

@OnlyIn(Dist.CLIENT)
public class EncyclopediaScreen extends Screen {
    private int currPage;
    private ItemStack bookStack;
    private List<DFEncyclopediaEntry> entries = Lists.newArrayList();
    public static final ResourceLocation QUESTION_MARK = new ResourceLocation("mineminenomi", "textures/gui/icons/question-mark.png");

    protected EncyclopediaScreen(ItemStack stack) {
        super((ITextComponent) new StringTextComponent(""));
        this.bookStack = stack;
        this.entries = getDefaultList(stack);
    }

    public void render(int mouseX, int mouseY, float partialTicks) {
        ResourceLocation baseIcon;
        renderBackground();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(ReadBookScreen.BOOK_TEXTURES);
        int posX = (this.width - 192) / 2;
        int posY = 2;
        blit(posX, posY, 0, 0, 192, 192);
        DFEncyclopediaEntry entry = !this.entries.isEmpty() ? this.entries.get(this.currPage) : null;
        String currentPageLabel = I18n.format("book.pageIndicator", new Object[] { Integer.valueOf(this.currPage + 1), Integer.valueOf(Math.max(getPageCount(), 1)) });
        int textSize = getTextWidth(currentPageLabel);
        this.font.drawString(currentPageLabel, (posX - textSize + 192 - 44), 18.0F, 0);
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();
        ResourceLocation stemIcon = null;
        Color baseColor = (entry != null) ? entry.getBaseColor().orElse(Color.BLACK) : Color.BLACK;
        Color stemColor = (entry != null) ? entry.getStemColor().orElse(Color.BLACK) : Color.BLACK;
        if (entry == null || !entry.getShape().isPresent() || ((Integer) entry.getShape().get()).intValue() <= 0) {
            baseIcon = QUESTION_MARK;
        } else {
            baseIcon = new ResourceLocation("mineminenomi", "textures/items/fruits/generic/generic_fruit_" + entry.getShape().get() + ".png");
            stemIcon = new ResourceLocation("mineminenomi", "textures/items/fruits/generic/generic_fruit_" + entry.getShape().get() + "_stem.png");
        }
        RenderSystem.translated((posX + 55), (posY + 40), 2.0D);
        RenderSystem.translated(64.0D, 64.0D, 0.0D);
        RenderSystem.scaled(0.8D, 0.8D, 0.8D);
        RenderSystem.translated(-64.0D, -64.0D, 1.0D);
        this.minecraft.getTextureManager().bindTexture(baseIcon);
        if (baseIcon.equals(QUESTION_MARK)) {
            RenderSystem.color4f(stemColor.getRed() / 255.0F, stemColor.getGreen() / 255.0F, stemColor.getBlue() / 255.0F, 1.0F);
            drawUpperIcon(baseIcon, 0, 0, 64, 32);
            RenderSystem.color4f(baseColor.getRed() / 255.0F, baseColor.getGreen() / 255.0F, baseColor.getBlue() / 255.0F, 1.0F);
            drawLowerIcon(baseIcon, 0, 32, 64, 32);
        } else {
            RenderSystem.color4f(baseColor.getRed() / 255.0F, baseColor.getGreen() / 255.0F, baseColor.getBlue() / 255.0F, 1.0F);
            WyHelper.drawIcon(baseIcon, 0, 0, 64, 64);
        }
        if (stemIcon != null) {
            this.minecraft.getTextureManager().bindTexture(stemIcon);
            RenderSystem.color4f(stemColor.getRed() / 255.0F, stemColor.getGreen() / 255.0F, stemColor.getBlue() / 255.0F, 1.0F);
            if (entry.getShape().isPresent() && ((Integer) entry.getShape().get()).intValue() > 0) {
                WyHelper.drawIcon(stemIcon, 0, 0, 64, 64);
            }
        }
        RenderSystem.popMatrix();
        if (entry != null) {
            String fruitName = (new ItemStack((IItemProvider) entry.getDevilFruit())).getDisplayName().getFormattedText();
            fruitName = (entry.getCompletion() >= 3) ? (TextFormatting.GOLD + fruitName) : fruitName;
            List<String> splitText = WyHelper.splitString(this.font, fruitName, posX, 100);
            for (int j = 0; j < splitText.size(); j++) {
                WyHelper.drawStringWithBorder(this.font, splitText.get(j), posX + 93 - this.font.getStringWidth(splitText.get(j)) / 2, posY + 113 + j * 12, -1);
            }
        }
        super.render(mouseX, mouseY, partialTicks);
    }

    protected void init() {
        int posX = (this.width - 192) / 2;
        int posY = 2;
        this.addButton(new ChangePageButton(posX + 116, posY + 159, true, (button) -> {
            this.nextPage();
        }, true));
        this.addButton(new ChangePageButton(posX + 43, posY + 159, false, (button) -> {
            this.previousPage();
        }, true));
        this.addButton(new Button(this.width / 2 - 150, 196, 98, 20, "All", (button) -> {
            this.entries = this.getDefaultList(this.bookStack);
            this.currPage = 0;
        }));
        this.addButton(new Button(this.width / 2 - 50, 196, 98, 20, "Partially Complete", (button) -> {
            this.entries = this.getDefaultList(this.bookStack).stream().filter((entry) -> {
                return entry.getCompletion() > 0;
            }).collect(Collectors.toList());
            this.currPage = 0;
        }));
        this.addButton(new Button(this.width / 2 + 50, 196, 98, 20, "Only Complete", (button) -> {
            this.entries = this.getDefaultList(this.bookStack).stream().filter((entry) -> {
                return entry.getCompletion() >= 3;
            }).collect(Collectors.toList());
            this.currPage = 0;
        }));
        super.init();
    }

    private void previousPage() {
        if (this.currPage > 0) {
            this.currPage--;
        } else if (this.currPage == 0) {
            this.currPage = getPageCount() - 1;
        }
    }

    private void nextPage() {
        if (this.currPage < getPageCount() - 1) {
            this.currPage++;
        } else if (this.currPage == getPageCount() - 1) {
            this.currPage = 0;
        }
    }

    private int getPageCount() { return this.entries.size(); }

    private int getTextWidth(String text) { return this.font.getStringWidth(this.font.getBidiFlag() ? this.font.bidiReorder(text) : text); }

    public List<DFEncyclopediaEntry> getDefaultList(ItemStack stack) {
        List<DFEncyclopediaEntry> list = Lists.newArrayList();
        CompoundNBT nbt = stack.getOrCreateChildTag("unlocked");
        for (AkumaNoMiItem fruit : ModValues.devilfruits) {
            String key = DevilFruitHelper.getDevilFruitKey(fruit);
            if (!nbt.isEmpty() && nbt.contains(key)) {
                DFEncyclopediaEntry dFEncyclopediaEntry = DFEncyclopediaEntry.of(nbt.getCompound(key));
                dFEncyclopediaEntry.setDevilFruit(fruit);
                list.add(dFEncyclopediaEntry);
                continue;
            }
            DFEncyclopediaEntry entry = DFEncyclopediaEntry.of(Optional.empty(), Optional.empty(), Optional.empty());
            entry.setDevilFruit(fruit);
            list.add(entry);
        }
        Collections.reverse(list);
        return list;
    }

    public boolean isPauseScreen() { return false; }

    public static void drawUpperIcon(ResourceLocation rs, int x, int y, int u, int v) {
        Minecraft.getInstance().getTextureManager().bindTexture(rs);
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(x, (y + v), 1.0D).tex(0.0F, 0.5F).endVertex();
        bufferbuilder.pos((x + u), (y + v), 1.0D).tex(1.0F, 0.5F).endVertex();
        bufferbuilder.pos((x + u), y, 1.0D).tex(1.0F, 0.0F).endVertex();
        bufferbuilder.pos(x, y, 1.0D).tex(0.0F, 0.0F).endVertex();
        Tessellator.getInstance().draw();
    }

    public static void drawLowerIcon(ResourceLocation rs, int x, int y, int u, int v) {
        Minecraft.getInstance().getTextureManager().bindTexture(rs);
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(x, (y + v), 1.0D).tex(0.0F, 1.0F).endVertex();
        bufferbuilder.pos((x + u), (y + v), 1.0D).tex(1.0F, 1.0F).endVertex();
        bufferbuilder.pos((x + u), y, 1.0D).tex(1.0F, 0.5F).endVertex();
        bufferbuilder.pos(x, y, 1.0D).tex(0.0F, 0.5F).endVertex();
        Tessellator.getInstance().draw();
    }

    public static void open(ItemStack stack) { Minecraft.getInstance().displayGuiScreen(new EncyclopediaScreen(stack)); }
}
