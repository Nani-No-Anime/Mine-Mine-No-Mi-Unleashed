/*    */ package xyz.pixelatedw.mineminenomi.blocks.traps;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMaterials;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ 
/*    */ public class DarknessBlock
/*    */   extends TrapAbilityBlock
/*    */ {
/*    */   public DarknessBlock() {
/* 14 */     super(Block.Properties.create(ModMaterials.DARKNESS_MATERIAL).doesNotBlockMovement().hardnessAndResistance(-1.0F, 10000.0F).noDrops());
/* 15 */     setDamageDealt(6);
/* 16 */     setHorizontalFallSpeed(0.005D);
/* 17 */     setVerticalFallSpeed(0.3D);
/* 18 */     setPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 80, 0, false, false, false));
/*    */   }
/*    */ 
/*    */   
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 23 */     return ModAbilities.YAMI_YAMI_NO_MI;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\traps\DarknessBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */