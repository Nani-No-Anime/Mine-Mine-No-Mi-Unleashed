/*    */ package xyz.pixelatedw.mineminenomi.blocks.traps;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraftforge.common.ToolType;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ 
/*    */ public class SunaSandBlock
/*    */   extends TrapAbilityBlock
/*    */ {
/*    */   public SunaSandBlock() {
/* 15 */     super(Block.Properties.create(Material.SAND).doesNotBlockMovement().hardnessAndResistance(5.0F, 3.0F).noDrops().harvestTool(ToolType.SHOVEL));
/* 16 */     setDamageDealt(4);
/* 17 */     setHorizontalFallSpeed(0.3D);
/* 18 */     setVerticalFallSpeed(0.15D);
/* 19 */     setPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 80, 1, false, false, false));
/*    */   }
/*    */ 
/*    */   
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 24 */     return ModAbilities.SUNA_SUNA_NO_MI;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\traps\SunaSandBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */