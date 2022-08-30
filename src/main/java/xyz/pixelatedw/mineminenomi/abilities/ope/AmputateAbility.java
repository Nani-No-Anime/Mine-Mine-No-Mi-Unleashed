/*    */ package xyz.pixelatedw.mineminenomi.abilities.ope;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.ope.SpatialSlashProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ 
/*    */ public class AmputateAbility extends Ability {
/* 13 */   public static final AmputateAbility INSTANCE = new AmputateAbility();
/*    */ 
/*    */   
/*    */   public AmputateAbility() {
/* 17 */     super("Amputate", AbilityHelper.getDevilFruitCategory());
/* 18 */     setDescription("The user cuts the every block on his sight, horizontal sneaking causes a vertical cut");
/*    */     
/* 20 */     setMaxCooldown(5.0D);
/*    */     
/* 22 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 27 */     RoomAbility ability = (RoomAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)RoomAbility.INSTANCE);
/* 28 */     if (ability == null || !ability.isEntityInThisRoom((Entity)player)) {
/*    */       
/* 30 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_ROOM, new Object[] { getName() }));
/* 31 */       return false;
/*    */     } 
/*    */     
/* 34 */     SpatialSlashProjectile proj = new SpatialSlashProjectile(player.world, (LivingEntity)player);
/* 35 */     player.world.addEntity((Entity)proj);
/* 36 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ope\AmputateAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */