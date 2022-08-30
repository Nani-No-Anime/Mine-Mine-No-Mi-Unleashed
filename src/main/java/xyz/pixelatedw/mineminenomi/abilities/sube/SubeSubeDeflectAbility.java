/*    */ package xyz.pixelatedw.mineminenomi.abilities.sube;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IOnDamageAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class SubeSubeDeflectAbility
/*    */   extends ContinuousAbility implements IOnDamageAbility {
/* 16 */   public static final SubeSubeDeflectAbility INSTANCE = new SubeSubeDeflectAbility();
/* 17 */   private List<String> blockedSources = Arrays.asList(new String[] { "mob", "player", "ability_projectile", "arrow", "sting", "trident", "thrown", "sweetBerryBush", "cactus" });
/*    */ 
/*    */   
/*    */   public SubeSubeDeflectAbility() {
/* 21 */     super("Sube Sube Deflect", AbilityHelper.getDevilFruitCategory());
/* 22 */     setDescription("Temporarily makes the user immune to attacks either by weapon or by hand to hand combat, as those attacks would just slip off their body.");
/* 23 */     setThreshold(12.0D);
/*    */     
/* 25 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onDamage(LivingEntity entity, DamageSource source, double amount) {
/* 31 */     if (source.getImmediateSource() instanceof AbilityProjectileEntity && !((AbilityProjectileEntity)source.getImmediateSource()).isPhysical()) {
/* 32 */       return false;
/*    */     }
/* 34 */     return !this.blockedSources.contains(source.getDamageType());
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 39 */     int cooldown = (int)Math.round(this.continueTime / 8.0D) + 4;
/* 40 */     setMaxCooldown(cooldown);
/* 41 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\sube\SubeSubeDeflectAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */