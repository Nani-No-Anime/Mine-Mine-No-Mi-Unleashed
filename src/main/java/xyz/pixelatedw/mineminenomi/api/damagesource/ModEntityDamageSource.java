/*    */ package xyz.pixelatedw.mineminenomi.api.damagesource;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ 
/*    */ 
/*    */ public class ModEntityDamageSource
/*    */   extends ModDamageSource
/*    */ {
/*    */   @Nullable
/*    */   protected final Entity damageSourceEntity;
/*    */   
/*    */   public ModEntityDamageSource(String damageTypeIn, @Nullable Entity damageSourceEntityIn) {
/* 20 */     super(damageTypeIn, false, false, false);
/* 21 */     this.damageSourceEntity = damageSourceEntityIn;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Entity getTrueSource() {
/* 26 */     return this.damageSourceEntity;
/*    */   }
/*    */   
/*    */   public ITextComponent getDeathMessage(LivingEntity entityLivingBaseIn) {
/* 30 */     ItemStack itemstack = (this.damageSourceEntity instanceof LivingEntity) ? ((LivingEntity)this.damageSourceEntity).getHeldItemMainhand() : ItemStack.EMPTY;
/* 31 */     String s = "death.attack." + this.damageType;
/* 32 */     return (!itemstack.isEmpty() && itemstack.hasDisplayName()) ? (ITextComponent)new TranslationTextComponent(s + ".item", new Object[] { entityLivingBaseIn.getDisplayName(), this.damageSourceEntity.getDisplayName(), itemstack.getTextComponent() }) : (ITextComponent)new TranslationTextComponent(s, new Object[] { entityLivingBaseIn.getDisplayName(), this.damageSourceEntity.getDisplayName() });
/*    */   }
/*    */   
/*    */   public boolean isDifficultyScaled() {
/* 36 */     return (this.damageSourceEntity != null && this.damageSourceEntity instanceof LivingEntity && !(this.damageSourceEntity instanceof net.minecraft.entity.player.PlayerEntity));
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Vec3d getDamageLocation() {
/* 41 */     return (this.damageSourceEntity != null) ? this.damageSourceEntity.getPositionVec() : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\damagesource\ModEntityDamageSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */