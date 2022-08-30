/*     */ package xyz.pixelatedw.mineminenomi.renderers.blocks;
/*     */ 
/*     */ import com.mojang.authlib.minecraft.MinecraftProfileTexture;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.Quaternion;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*     */ import net.minecraft.state.IProperty;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.WantedPosterBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.models.blocks.WantedPosterModel;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class WantedPosterTileEntityRenderer
/*     */   extends TileEntityRenderer<WantedPosterTileEntity>
/*     */ {
/*  36 */   private static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/wantedposter.png");
/*     */   private static final String FONT_HEX_COLOR = "#513413";
/*     */   private WantedPosterModel posterModel;
/*  39 */   private final ModelRenderer face = new ModelRenderer(64, 64, 7, 7);
/*     */ 
/*     */   
/*     */   public WantedPosterTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
/*  43 */     super(dispatcher);
/*  44 */     this.posterModel = new WantedPosterModel();
/*  45 */     this.face.addBox(0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 1.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(WantedPosterTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
/*  51 */     BlockState blockstate = tileEntity.getBlockState();
/*     */     
/*  53 */     String name = tileEntity.getEntityName();
/*  54 */     String uuid = tileEntity.getUUID();
/*  55 */     ResourceLocation rs = null;
/*     */     
/*  57 */     matrixStack.push();
/*     */     
/*  59 */     matrixStack.translate(0.5D, 0.5D, 0.5D);
/*  60 */     matrixStack.rotate(new Quaternion(Vector3f.YP, -((Direction)blockstate.get((IProperty)WantedPosterBlock.FACING)).getHorizontalAngle() + 180.0F, true));
/*  61 */     matrixStack.rotate(new Quaternion(Vector3f.ZP, 180.0F, true));
/*     */ 
/*     */     
/*  64 */     matrixStack.push();
/*     */     
/*  66 */     matrixStack.rotate(new Quaternion(Vector3f.ZP, 180.0F, true));
/*  67 */     matrixStack.translate(-0.36D, 0.6D, 0.5D);
/*  68 */     matrixStack.scale(0.6F, -0.6F, -0.01F);
/*  69 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(ModRenderTypes.getWantedPoster(TEXTURE));
/*  70 */     this.posterModel.render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  72 */     matrixStack.pop();
/*     */     
/*  74 */     if (WyHelper.isNullOrEmpty(name)) {
/*     */       
/*  76 */       matrixStack.pop();
/*     */       
/*     */       return;
/*     */     } 
/*  80 */     ExtendedWorldData worldData = ExtendedWorldData.get(tileEntity.getWorld());
/*     */     
/*  82 */     DecimalFormat decimalFormat = new DecimalFormat("#,##0");
/*  83 */     if (WyHelper.isNullOrEmpty(tileEntity.getPosterBounty()))
/*  84 */       tileEntity.setPosterBounty("0"); 
/*  85 */     String bounty = "0";
/*     */     
/*     */     try {
/*  88 */       bounty = decimalFormat.format(Long.parseLong(tileEntity.getPosterBounty()));
/*     */     }
/*  90 */     catch (Exception e) {
/*     */       
/*  92 */       bounty = "0";
/*  93 */       e.printStackTrace();
/*     */     } 
/*     */ 
/*     */     
/*  97 */     matrixStack.push();
/*     */ 
/*     */     
/* 100 */     if (worldData.getBounty(uuid) != Long.parseLong(bounty.replaceAll("\\D+", ""))) {
/*     */       
/* 102 */       matrixStack.push();
/*     */       
/* 104 */       matrixStack.translate(-0.35D, -0.389D, 0.496D);
/* 105 */       matrixStack.scale(0.173F, 0.132F, 0.001F);
/* 106 */       IVertexBuilder iVertexBuilder = buffer.getBuffer(ModRenderTypes.getWantedPosterExpiration(ModResources.EXPIRED));
/* 107 */       RendererHelper.drawQuad(matrixStack, iVertexBuilder, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 3.9F, 3.9F);
/*     */       
/* 109 */       matrixStack.pop();
/*     */     } 
/*     */ 
/*     */     
/* 113 */     matrixStack.push();
/*     */     
/* 115 */     if (tileEntity.getGameProfile() != null) {
/*     */       
/* 117 */       Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = Minecraft.getInstance().getSkinManager().loadSkinFromCache(tileEntity.getGameProfile());
/* 118 */       if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
/* 119 */         rs = Minecraft.getInstance().getSkinManager().loadSkin(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
/*     */       } else {
/* 121 */         rs = DefaultPlayerSkin.getDefaultSkin(UUID.fromString(uuid));
/*     */       } 
/*     */     } else {
/* 124 */       rs = DefaultPlayerSkin.getDefaultSkin(UUID.fromString(uuid));
/* 125 */     }  matrixStack.translate(-0.21D, -0.32D, 0.498D);
/* 126 */     matrixStack.scale(0.8F, 0.8F, 0.001F);
/* 127 */     IVertexBuilder iVertexBuilder1 = buffer.getBuffer(ModRenderTypes.getWantedPoster(rs));
/* 128 */     this.face.render(matrixStack, iVertexBuilder1, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 130 */     matrixStack.pop();
/*     */ 
/*     */     
/* 133 */     matrixStack.push();
/*     */     
/* 135 */     if (tileEntity.isPirate()) {
/*     */       
/* 137 */       Crew crew = worldData.getCrewWithMember(UUID.fromString(uuid));
/* 138 */       if (crew != null)
/*     */       {
/* 140 */         matrixStack.translate(0.10999999940395355D, -0.09000000357627869D, 0.4970000088214874D);
/* 141 */         matrixStack.scale(0.2F, 0.2F, 1.0F);
/* 142 */         RendererHelper.drawPlayerJollyRoger(crew.getJollyRoger(), matrixStack, buffer);
/*     */       }
/*     */     
/* 145 */     } else if (tileEntity.isRevolutionary()) {
/*     */       
/* 147 */       matrixStack.translate(-0.05000000074505806D, -0.20999999344348907D, 0.4970000088214874D);
/* 148 */       iVertexBuilder1 = buffer.getBuffer(ModRenderTypes.getWantedPoster(ModResources.REVOLUTIONARY_ARMY_ICON));
/* 149 */       RendererHelper.drawQuad(matrixStack, iVertexBuilder1, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.5F, 0.5F);
/*     */     } 
/*     */     
/* 152 */     matrixStack.pop();
/*     */ 
/*     */     
/* 155 */     matrixStack.push();
/*     */     
/* 157 */     matrixStack.translate(-0.35D, -0.389D, 0.499D);
/* 158 */     matrixStack.scale(0.173F, 0.132F, 0.001F);
/* 159 */     ResourceLocation texture = new ResourceLocation("mineminenomi", "textures/gui/wantedposters/backgrounds/" + tileEntity.getBackground() + ".jpg");
/* 160 */     IVertexBuilder iVertexBuilder2 = buffer.getBuffer(ModRenderTypes.getWantedPoster(texture));
/* 161 */     RendererHelper.drawQuad(matrixStack, iVertexBuilder2, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 3.9F, 3.9F);
/*     */     
/* 163 */     matrixStack.pop();
/*     */     
/* 165 */     matrixStack.pop();
/*     */ 
/*     */     
/* 168 */     matrixStack.push();
/*     */     
/* 170 */     matrixStack.scale(0.0075F, 0.008F, 0.0075F);
/* 171 */     matrixStack.translate(-12.0D, 29.9D, 66.6D);
/*     */     
/* 173 */     if (name.length() > 13)
/* 174 */       name = name.substring(0, 10) + "..."; 
/* 175 */     this.renderDispatcher.getFontRenderer().renderString(TextFormatting.BOLD + name, 9.0F - this.renderDispatcher.getFontRenderer().getStringWidth(name) / 1.75F, -1.0F, WyHelper.hexToRGB("#513413").getRGB(), false, matrixStack.getLast().getMatrix(), buffer, false, 0, combinedLight);
/*     */     
/* 177 */     matrixStack.scale(1.2F, 1.2F, 1.2F);
/*     */     
/* 179 */     boolean flag = (bounty.length() > 9);
/*     */     
/* 181 */     if (flag) {
/*     */       
/* 183 */       matrixStack.push();
/* 184 */       matrixStack.translate(-5.0D, 1.5D, 0.0D);
/* 185 */       matrixStack.scale(0.72F, 0.89F, 1.005F);
/*     */     } 
/* 187 */     this.renderDispatcher.getFontRenderer().renderString(TextFormatting.BOLD + bounty, -18.0F, 9.5F, WyHelper.hexToRGB("#513413").getRGB(), false, matrixStack.getLast().getMatrix(), buffer, false, 0, combinedLight);
/* 188 */     if (flag) {
/* 189 */       matrixStack.pop();
/*     */     }
/* 191 */     matrixStack.scale(0.7F, 0.9F, 0.8F);
/* 192 */     this.renderDispatcher.getFontRenderer().renderString(TextFormatting.BOLD + tileEntity.getIssuedDate(), -40.0F, 25.0F, WyHelper.hexToRGB("#513413").getRGB(), false, matrixStack.getLast().getMatrix(), buffer, false, 0, combinedLight);
/*     */     
/* 194 */     matrixStack.pop();
/*     */     
/* 196 */     matrixStack.pop();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\blocks\WantedPosterTileEntityRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */