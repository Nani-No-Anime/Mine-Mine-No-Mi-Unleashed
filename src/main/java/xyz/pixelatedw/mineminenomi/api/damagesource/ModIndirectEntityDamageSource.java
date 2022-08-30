/*    */ package xyz.pixelatedw.mineminenomi.api.damagesource;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ 
/*    */ 
/*    */ public class ModIndirectEntityDamageSource
/*    */   extends ModEntityDamageSource
/*    */ {
/*    */   private final Entity indirectEntity;
/*    */   
/*    */   public ModIndirectEntityDamageSource(String damageTypeIn, Entity source, @Nullable Entity indirectEntityIn) {
/* 17 */     super(damageTypeIn, source);
/* 18 */     this.indirectEntity = indirectEntityIn;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Entity getImmediateSource() {
/* 23 */     return this.damageSourceEntity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Entity getTrueSource() {
/* 32 */     return this.indirectEntity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ITextComponent getDeathMessage(LivingEntity entityLivingBaseIn) {
/* 39 */     ITextComponent itextcomponent = (this.indirectEntity == null) ? this.damageSourceEntity.getDisplayName() : this.indirectEntity.getDisplayName();
/* 40 */     ItemStack itemstack = (this.indirectEntity instanceof LivingEntity) ? ((LivingEntity)this.indirectEntity).getHeldItemMainhand() : ItemStack.EMPTY;
/* 41 */     String s = "death.attack." + this.damageType;
/* 42 */     String s1 = s + ".item";
/* 43 */     return (!itemstack.isEmpty() && itemstack.hasDisplayName()) ? (ITextComponent)new TranslationTextComponent(s1, new Object[] { entityLivingBaseIn.getDisplayName(), itextcomponent, itemstack.getTextComponent() }) : (ITextComponent)new TranslationTextComponent(s, new Object[] { entityLivingBaseIn.getDisplayName(), itextcomponent });
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\damagesource\ModIndirectEntityDamageSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */