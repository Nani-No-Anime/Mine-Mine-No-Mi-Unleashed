/*    */ package xyz.pixelatedw.mineminenomi.abilities.rokushiki;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class ShiganAbility extends PunchAbility {
/* 13 */   public static final Ability INSTANCE = (Ability)new ShiganAbility();
/*    */ 
/*    */   
/*    */   public ShiganAbility() {
/* 17 */     super("Shigan", AbilityHelper.getRacialCategory());
/* 18 */     setMaxCooldown(8.0D);
/* 19 */     setDescription("The user thrusts their finger at the opponent to pierce them");
/*    */     
/* 21 */     this.onHitEntityEvent = this::onHitEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 26 */     player.world.playSound(null, target.getPosition(), ModSounds.SHIGAN_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/* 27 */     return 20.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\rokushiki\ShiganAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */