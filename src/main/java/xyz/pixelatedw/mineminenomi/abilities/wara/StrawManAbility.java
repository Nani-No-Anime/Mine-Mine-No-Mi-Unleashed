/*    */ package xyz.pixelatedw.mineminenomi.abilities.wara;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.items.StrawDollItem;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class StrawManAbility extends PunchAbility {
/* 17 */   public static final Ability INSTANCE = (Ability)new StrawManAbility();
/*    */ 
/*    */   
/*    */   public StrawManAbility() {
/* 21 */     super("Straw Man", AbilityHelper.getDevilFruitCategory());
/* 22 */     setDescription("Creates a doll that representing the entity you attacked, any damage will get redirected to it");
/* 23 */     setMaxCooldown(30.0D);
/*    */     
/* 25 */     this.onHitEntityEvent = this::onHitEntity;
/* 26 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 31 */     IEntityStats targetProps = EntityStatsCapability.get(target);
/*    */     
/* 33 */     int dolls = player.inventory.count(ModItems.STRAW_DOLL);
/*    */     
/* 35 */     if (dolls < 3)
/*    */     {
/*    */ 
/*    */ 
/*    */       
/* 40 */       if (targetProps.hasStrawDoll()) {
/*    */         
/* 42 */         ItemStack doll = new ItemStack((IItemProvider)ModItems.STRAW_DOLL);
/* 43 */         ((StrawDollItem)doll.getItem()).setStrawDollOwner(doll, target);
/* 44 */         doll.setDisplayName((ITextComponent)new StringTextComponent(target.getCommandSource().getName() + "'s Straw Doll"));
/* 45 */         player.inventory.addItemStackToInventory(doll);
/* 46 */         targetProps.setStrawDoll(false);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 53 */     return 1.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\wara\StrawManAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */