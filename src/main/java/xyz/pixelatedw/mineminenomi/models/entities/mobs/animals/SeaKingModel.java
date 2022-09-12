package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.animals.SeaKingEntity;

public class SeaKingModel<T extends SeaKingEntity> extends BipedModel<T> {
  private final ModelRenderer TailFins;
  private final ModelRenderer bone;
  private final ModelRenderer bone2;
  private final ModelRenderer bone3;
  private final ModelRenderer bone4;
  private final ModelRenderer bone5;
  private final ModelRenderer bone6;
  private final ModelRenderer bone7;
  private final ModelRenderer bone8;
  private final ModelRenderer bone9;
  private final ModelRenderer bone10;
  private final ModelRenderer bone11;
  private final ModelRenderer bone12;
  private final ModelRenderer bone13;
  private final ModelRenderer bone14;
  private final ModelRenderer bone15;
  private final ModelRenderer bone16;
  private final ModelRenderer bone17;
  private final ModelRenderer bone18;
  private final ModelRenderer headbone;
  private final ModelRenderer bb_main;
  
  public SeaKingModel() {
    super(1.0F);
    this.textureWidth = 16;
    this.textureHeight = 16;
    
    this.TailFins = new ModelRenderer((Model)this);
    this.TailFins.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.TailFins.setTextureOffset(0, 0).addBox(-1.1857F, -70.4619F, 944.9143F, 2.0F, 54.0F, 17.0F, 0.0F, false);
    this.TailFins.setTextureOffset(0, 0).addBox(-1.1857F, -65.4619F, 961.9143F, 2.0F, 47.0F, 21.0F, 0.0F, false);
    this.TailFins.setTextureOffset(0, 0).addBox(-1.1857F, -57.4619F, 981.9143F, 2.0F, 31.0F, 21.0F, 0.0F, false);
    this.TailFins.setTextureOffset(0, 0).addBox(-1.1857F, -49.4619F, 1002.9143F, 2.0F, 19.0F, 21.0F, 0.0F, false);
    
    this.bone = new ModelRenderer((Model)this);
    this.bone.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.bone.setTextureOffset(0, 0).addBox(-10.1857F, -48.4619F, 900.9143F, 20.0F, 16.0F, 44.0F, 0.0F, false);
    this.bone.setTextureOffset(0, 0).addBox(-1.1857F, -32.4619F, 900.9143F, 2.0F, 18.0F, 44.0F, 0.0F, false);
    this.bone.setTextureOffset(0, 0).addBox(-1.1857F, -66.4619F, 900.9143F, 2.0F, 18.0F, 44.0F, 0.0F, false);
    
    this.bone2 = new ModelRenderer((Model)this);
    this.bone2.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.bone2.setTextureOffset(0, 0).addBox(-1.1857F, -31.4619F, 856.9143F, 2.0F, 13.0F, 44.0F, 0.0F, false);
    this.bone2.setTextureOffset(0, 0).addBox(-11.1857F, -49.4619F, 856.9143F, 22.0F, 18.0F, 44.0F, 0.0F, false);
    this.bone2.setTextureOffset(0, 0).addBox(-1.1857F, -62.4619F, 856.9143F, 2.0F, 13.0F, 44.0F, 0.0F, false);
    
    this.bone3 = new ModelRenderer((Model)this);
    this.bone3.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.bone3.setTextureOffset(0, 0).addBox(-1.1857F, -30.4619F, 812.9143F, 2.0F, 14.0F, 44.0F, 0.0F, false);
    this.bone3.setTextureOffset(0, 0).addBox(-12.1857F, -50.4619F, 812.9143F, 24.0F, 20.0F, 44.0F, 0.0F, false);
    this.bone3.setTextureOffset(0, 0).addBox(-1.1857F, -64.4619F, 812.9143F, 2.0F, 14.0F, 44.0F, 0.0F, false);
    
    this.bone4 = new ModelRenderer((Model)this);
    this.bone4.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.bone4.setTextureOffset(0, 0).addBox(-1.1857F, -29.4619F, 773.9143F, 2.0F, 16.0F, 39.0F, 0.0F, false);
    this.bone4.setTextureOffset(0, 0).addBox(-13.1857F, -51.4619F, 773.9143F, 26.0F, 22.0F, 39.0F, 0.0F, false);
    this.bone4.setTextureOffset(0, 0).addBox(-1.1857F, -67.4619F, 773.9143F, 2.0F, 16.0F, 39.0F, 0.0F, false);
    
    this.bone5 = new ModelRenderer((Model)this);
    this.bone5.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.bone5.setTextureOffset(0, 0).addBox(-1.1857F, -28.4619F, 726.9143F, 2.0F, 16.0F, 47.0F, 0.0F, false);
    this.bone5.setTextureOffset(0, 0).addBox(-14.1857F, -52.4619F, 726.9143F, 28.0F, 24.0F, 47.0F, 0.0F, false);
    this.bone5.setTextureOffset(0, 0).addBox(-1.1857F, -68.4619F, 726.9143F, 2.0F, 16.0F, 47.0F, 0.0F, false);
    
    this.bone6 = new ModelRenderer((Model)this);
    this.bone6.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.bone6.setTextureOffset(0, 0).addBox(-1.1857F, -27.4619F, 671.9143F, 2.0F, 16.0F, 55.0F, 0.0F, false);
    this.bone6.setTextureOffset(0, 0).addBox(-15.1857F, -53.4619F, 671.9143F, 30.0F, 26.0F, 55.0F, 0.0F, false);
    this.bone6.setTextureOffset(0, 0).addBox(-1.1857F, -69.4619F, 671.9143F, 2.0F, 16.0F, 55.0F, 0.0F, false);
    
    this.bone7 = new ModelRenderer((Model)this);
    this.bone7.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.bone7.setTextureOffset(0, 0).addBox(-1.1857F, -26.4619F, 609.9143F, 2.0F, 16.0F, 62.0F, 0.0F, false);
    this.bone7.setTextureOffset(0, 0).addBox(-16.1857F, -54.4619F, 609.9143F, 32.0F, 28.0F, 62.0F, 0.0F, false);
    this.bone7.setTextureOffset(0, 0).addBox(-1.1857F, -70.4619F, 609.9143F, 2.0F, 16.0F, 62.0F, 0.0F, false);
    
    this.bone8 = new ModelRenderer((Model)this);
    this.bone8.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.bone8.setTextureOffset(0, 0).addBox(-1.1857F, -25.4619F, 573.9143F, 2.0F, 16.0F, 36.0F, 0.0F, false);
    this.bone8.setTextureOffset(0, 0).addBox(-17.1857F, -55.4619F, 573.9143F, 34.0F, 30.0F, 36.0F, 0.0F, false);
    this.bone8.setTextureOffset(0, 0).addBox(-1.1857F, -71.4619F, 573.9143F, 2.0F, 16.0F, 36.0F, 0.0F, false);
    
    this.bone9 = new ModelRenderer((Model)this);
    this.bone9.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.bone9.setTextureOffset(0, 0).addBox(-1.1857F, -24.4619F, 533.9143F, 2.0F, 16.0F, 40.0F, 0.0F, false);
    this.bone9.setTextureOffset(0, 0).addBox(-18.1857F, -56.4619F, 532.9143F, 36.0F, 32.0F, 41.0F, 0.0F, false);
    this.bone9.setTextureOffset(0, 0).addBox(-1.1857F, -72.4619F, 533.9143F, 2.0F, 16.0F, 40.0F, 0.0F, false);
    
    this.bone10 = new ModelRenderer((Model)this);
    this.bone10.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.bone10.setTextureOffset(0, 0).addBox(-1.1857F, -23.4619F, 485.9143F, 2.0F, 16.0F, 48.0F, 0.0F, false);
    this.bone10.setTextureOffset(0, 0).addBox(-19.1857F, -57.4619F, 485.9143F, 38.0F, 34.0F, 48.0F, 0.0F, false);
    this.bone10.setTextureOffset(0, 0).addBox(-1.1857F, -73.4619F, 485.9143F, 2.0F, 16.0F, 48.0F, 0.0F, false);
    
    this.bone11 = new ModelRenderer((Model)this);
    this.bone11.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.bone11.setTextureOffset(0, 0).addBox(-1.1857F, -22.4619F, 428.9143F, 2.0F, 16.0F, 57.0F, 0.0F, false);
    this.bone11.setTextureOffset(0, 0).addBox(-20.1857F, -58.4619F, 428.9143F, 40.0F, 36.0F, 57.0F, 0.0F, false);
    this.bone11.setTextureOffset(0, 0).addBox(-1.1857F, -74.4619F, 428.9143F, 2.0F, 16.0F, 57.0F, 0.0F, false);
    
    this.bone12 = new ModelRenderer((Model)this);
    this.bone12.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.bone12.setTextureOffset(0, 0).addBox(-1.1857F, -21.4619F, 373.9143F, 2.0F, 16.0F, 55.0F, 0.0F, false);
    this.bone12.setTextureOffset(0, 0).addBox(-21.1857F, -59.4619F, 373.9143F, 42.0F, 38.0F, 55.0F, 0.0F, false);
    this.bone12.setTextureOffset(0, 0).addBox(-1.1857F, -75.4619F, 373.9143F, 2.0F, 16.0F, 55.0F, 0.0F, false);
    
    this.bone13 = new ModelRenderer((Model)this);
    this.bone13.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.bone13.setTextureOffset(0, 0).addBox(-1.1857F, -22.4619F, 318.9143F, 2.0F, 19.0F, 55.0F, 0.0F, false);
    this.bone13.setTextureOffset(0, 0).addBox(-19.1857F, -58.4619F, 318.9143F, 39.0F, 36.0F, 55.0F, 0.0F, false);
    this.bone13.setTextureOffset(0, 0).addBox(-1.1857F, -74.4619F, 318.9143F, 2.0F, 16.0F, 55.0F, 0.0F, false);
    
    this.bone14 = new ModelRenderer((Model)this);
    this.bone14.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.bone14.setTextureOffset(0, 0).addBox(-1.1857F, -21.4619F, 263.9143F, 2.0F, 20.0F, 55.0F, 0.0F, false);
    this.bone14.setTextureOffset(0, 0).addBox(-21.1857F, -59.4619F, 263.9143F, 42.0F, 38.0F, 55.0F, 0.0F, false);
    this.bone14.setTextureOffset(0, 0).addBox(-1.1857F, -75.4619F, 263.9143F, 2.0F, 16.0F, 55.0F, 0.0F, false);
    
    this.bone15 = new ModelRenderer((Model)this);
    this.bone15.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.bone15.setTextureOffset(0, 0).addBox(-1.1857F, -22.4619F, 208.9143F, 2.0F, 23.0F, 55.0F, 0.0F, false);
    this.bone15.setTextureOffset(0, 0).addBox(-19.1857F, -58.4619F, 208.9143F, 39.0F, 36.0F, 55.0F, 0.0F, false);
    this.bone15.setTextureOffset(0, 0).addBox(-1.1857F, -74.4619F, 208.9143F, 2.0F, 16.0F, 55.0F, 0.0F, false);
    
    this.bone16 = new ModelRenderer((Model)this);
    this.bone16.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.bone16.setTextureOffset(0, 0).addBox(-1.1857F, -21.4619F, 153.9143F, 2.0F, 25.0F, 55.0F, 0.0F, false);
    this.bone16.setTextureOffset(0, 0).addBox(-21.1857F, -59.4619F, 153.9143F, 42.0F, 38.0F, 55.0F, 0.0F, false);
    this.bone16.setTextureOffset(0, 0).addBox(-1.1857F, -75.4619F, 153.9143F, 2.0F, 16.0F, 55.0F, 0.0F, false);
    
    this.bone17 = new ModelRenderer((Model)this);
    this.bone17.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.bone17.setTextureOffset(0, 0).addBox(-1.1857F, -19.4619F, 94.9143F, 2.0F, 27.0F, 59.0F, 0.0F, false);
    this.bone17.setTextureOffset(0, 0).addBox(-25.1857F, -62.4619F, 94.9143F, 50.0F, 44.0F, 59.0F, 0.0F, false);
    this.bone17.setTextureOffset(0, 0).addBox(-1.1857F, -78.4619F, 94.9143F, 2.0F, 16.0F, 59.0F, 0.0F, false);
    
    this.bone18 = new ModelRenderer((Model)this);
    this.bone18.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.bone18.setTextureOffset(0, 0).addBox(-1.1857F, -15.4619F, 35.9143F, 2.0F, 27.0F, 59.0F, 0.0F, false);
    this.bone18.setTextureOffset(0, 0).addBox(-28.1857F, -65.4619F, 35.9143F, 56.0F, 50.0F, 59.0F, 0.0F, false);
    this.bone18.setTextureOffset(0, 0).addBox(-1.1857F, -81.4619F, 63.9143F, 2.0F, 16.0F, 31.0F, 0.0F, false);
    
    this.headbone = new ModelRenderer((Model)this);
    this.headbone.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.headbone.setTextureOffset(0, 0).addBox(-31.1857F, -72.4619F, -7.0857F, 62.0F, 37.0F, 43.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-1.1857F, -82.4619F, 5.9143F, 2.0F, 10.0F, 30.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-31.1857F, -35.4619F, -7.0857F, 62.0F, 23.0F, 43.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-32.1857F, -29.4619F, -50.0857F, 64.0F, 15.0F, 43.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-29.1857F, -71.4619F, -24.0857F, 58.0F, 42.0F, 17.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-24.1857F, -64.4619F, -45.0857F, 49.0F, 35.0F, 21.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-23.1857F, -60.4619F, -66.0857F, 47.0F, 31.0F, 21.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-22.1857F, -51.4619F, -85.0857F, 45.0F, 22.0F, 19.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-0.1857F, -56.4619F, -72.0857F, 18.0F, 5.0F, 7.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-9.1857F, -58.4619F, -70.0857F, 18.0F, 2.0F, 5.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-19.1857F, -48.4619F, -120.0857F, 39.0F, 19.0F, 35.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-17.1857F, -46.4619F, -155.0857F, 35.0F, 17.0F, 35.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-12.1857F, -49.4619F, -153.0857F, 25.0F, 3.0F, 16.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(17.8143F, -35.4619F, -142.0857F, 2.0F, 7.0F, 5.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(17.8143F, -33.4619F, -149.0857F, 2.0F, 4.0F, 5.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(17.8143F, -38.4619F, -148.0857F, 2.0F, 5.0F, 3.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(17.8143F, -40.4619F, -141.0857F, 2.0F, 5.0F, 3.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(19.8143F, -33.4619F, -109.0857F, 2.0F, 4.0F, 4.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(19.8143F, -33.4619F, -100.0857F, 2.0F, 4.0F, 4.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(19.8143F, -36.4619F, -99.0857F, 2.0F, 3.0F, 2.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(19.8143F, -36.4619F, -108.0857F, 2.0F, 3.0F, 2.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-24.1857F, -29.4619F, -120.0857F, 49.0F, 13.0F, 35.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-25.1857F, -29.4619F, -85.0857F, 51.0F, 14.0F, 35.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-21.1857F, -29.4619F, -156.0857F, 43.0F, 13.0F, 36.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(24.8143F, -35.4619F, -41.0857F, 2.0F, 6.0F, 6.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(24.8143F, -36.4619F, -33.0857F, 2.0F, 7.0F, 6.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(24.8143F, -43.4619F, -32.0857F, 2.0F, 7.0F, 4.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(24.8143F, -46.4619F, -31.0857F, 2.0F, 3.0F, 2.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(24.8143F, -41.4619F, -40.0857F, 2.0F, 6.0F, 4.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(24.8143F, -44.4619F, -39.0857F, 2.0F, 3.0F, 2.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-19.1857F, -33.4619F, -149.0857F, 2.0F, 4.0F, 5.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-19.1857F, -38.4619F, -148.0857F, 2.0F, 5.0F, 3.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-19.1857F, -40.4619F, -141.0857F, 2.0F, 5.0F, 3.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-19.1857F, -35.4619F, -142.0857F, 2.0F, 7.0F, 5.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-21.1857F, -33.4619F, -109.0857F, 2.0F, 4.0F, 4.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-21.1857F, -36.4619F, -108.0857F, 2.0F, 3.0F, 2.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-21.1857F, -33.4619F, -100.0857F, 2.0F, 4.0F, 4.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-21.1857F, -36.4619F, -99.0857F, 2.0F, 3.0F, 2.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-26.1857F, -35.4619F, -41.0857F, 2.0F, 6.0F, 6.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-26.1857F, -41.4619F, -40.0857F, 2.0F, 6.0F, 4.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-26.1857F, -44.4619F, -39.0857F, 2.0F, 3.0F, 2.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-26.1857F, -46.4619F, -31.0857F, 2.0F, 3.0F, 2.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-26.1857F, -43.4619F, -32.0857F, 2.0F, 7.0F, 4.0F, 0.0F, false);
    this.headbone.setTextureOffset(0, 0).addBox(-26.1857F, -36.4619F, -33.0857F, 2.0F, 7.0F, 6.0F, 0.0F, false);
    
    this.bb_main = new ModelRenderer((Model)this);
    this.bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.bb_main.setTextureOffset(0, 0).addBox(-28.1857F, -70.4619F, 35.9143F, 56.0F, 5.0F, 28.0F, 0.0F, false);
    this.bb_main.setTextureOffset(0, 0).addBox(-18.1857F, -56.4619F, -72.0857F, 18.0F, 5.0F, 7.0F, 0.0F, false);
    this.bb_main.setTextureOffset(0, 0).addBox(-1.1857F, -86.4619F, 35.9143F, 2.0F, 16.0F, 28.0F, 0.0F, false);
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.headbone.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F) * 1.0F * limbSwingAmount;
    this.bone18.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F + 0.1F) * 1.0F * limbSwingAmount;
    this.bone17.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F + 0.2F) * 1.0F * limbSwingAmount;
    this.bone16.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F + 0.3F) * 1.0F * limbSwingAmount;
    this.bone15.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F + 0.4F) * 1.0F * limbSwingAmount;
    this.bone14.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F + 0.5F) * 1.0F * limbSwingAmount;
    this.bone13.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F + 0.6F) * 1.0F * limbSwingAmount;
    this.bone12.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F + 0.7F) * 1.0F * limbSwingAmount;
    this.bone11.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F + 0.8F) * 1.0F * limbSwingAmount;
    this.bone10.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F + 0.9F) * 1.0F * limbSwingAmount;
    this.bone9.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F + 1.0F) * 1.0F * limbSwingAmount;
    this.bone8.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F + 1.1F) * 1.0F * limbSwingAmount;
    this.bone7.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F + 1.2F) * 1.0F * limbSwingAmount;
    this.bone6.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F + 1.3F) * 1.0F * limbSwingAmount;
    this.bone5.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F + 1.4F) * 1.0F * limbSwingAmount;
    this.bone4.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F + 1.5F) * 1.0F * limbSwingAmount;
    this.bone3.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F + 1.6F) * 1.0F * limbSwingAmount;
    this.bone2.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F + 1.7F) * 1.0F * limbSwingAmount;
    this.bone.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F + 1.8F) * 1.0F * limbSwingAmount;
    this.TailFins.rotateAngleY = MathHelper.cos(limbSwing * 0.3662F + 1.9F) * 1.0F * limbSwingAmount;
  }



  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.TailFins.render(matrixStack, buffer, packedLight, packedOverlay);
    this.bone.render(matrixStack, buffer, packedLight, packedOverlay);
    this.bone2.render(matrixStack, buffer, packedLight, packedOverlay);
    this.bone3.render(matrixStack, buffer, packedLight, packedOverlay);
    this.bone4.render(matrixStack, buffer, packedLight, packedOverlay);
    this.bone5.render(matrixStack, buffer, packedLight, packedOverlay);
    this.bone6.render(matrixStack, buffer, packedLight, packedOverlay);
    this.bone7.render(matrixStack, buffer, packedLight, packedOverlay);
    this.bone8.render(matrixStack, buffer, packedLight, packedOverlay);
    this.bone9.render(matrixStack, buffer, packedLight, packedOverlay);
    this.bone10.render(matrixStack, buffer, packedLight, packedOverlay);
    this.bone11.render(matrixStack, buffer, packedLight, packedOverlay);
    this.bone12.render(matrixStack, buffer, packedLight, packedOverlay);
    this.bone13.render(matrixStack, buffer, packedLight, packedOverlay);
    this.bone14.render(matrixStack, buffer, packedLight, packedOverlay);
    this.bone15.render(matrixStack, buffer, packedLight, packedOverlay);
    this.bone16.render(matrixStack, buffer, packedLight, packedOverlay);
    this.bone17.render(matrixStack, buffer, packedLight, packedOverlay);
    this.bone18.render(matrixStack, buffer, packedLight, packedOverlay);
    this.headbone.render(matrixStack, buffer, packedLight, packedOverlay);
    this.bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
  
  public void renderSaddle(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int noOverlay, int i, int i1, int i2, int i3) {}
}


