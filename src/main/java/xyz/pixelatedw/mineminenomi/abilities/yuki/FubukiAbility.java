/*    */ package xyz.pixelatedw.mineminenomi.abilities.yuki;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yuki.FubukiParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class FubukiAbility extends Ability {
/* 21 */   public static final FubukiAbility INSTANCE = new FubukiAbility();
/*    */   
/* 23 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new FubukiParticleEffect();
/*    */ 
/*    */   
/*    */   public FubukiAbility() {
/* 27 */     super("Fubuki", AbilityHelper.getDevilFruitCategory());
/* 28 */     setMaxCooldown(10.0D);
/* 29 */     setDescription("The user releases an extremely cold stream around themselves inflicting §2Frostbite§r and causing serious damage to those affected by it.");
/*    */     
/* 31 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 36 */     BlockProtectionRule.IReplaceBlockRule test = (world, pos, state) -> 
/* 37 */       (world.getBlockState(pos.down()).isSolid() && world.getBlockState(pos.down()).getBlock() != Blocks.SNOW);
/*    */ 
/*    */     
/* 40 */     AbilityHelper.createSphere(player.world, player.getPosition(), 25, 25, false, Blocks.SNOW, test, 3, DefaultProtectionRules.AIR_FOLIAGE);
/*    */     
/* 42 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 25.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 43 */     targets.remove(player);
/*    */     
/* 45 */     for (LivingEntity entity : targets) {
/*    */       
/* 47 */       AbilityHelper.addFrostbite(entity, (LivingEntity)player, 110);
/*    */       
/* 49 */       if (entity.isPotionActive(ModEffects.FROSTBITE) || entity.isPotionActive(ModEffects.FROZEN)) {
/* 50 */         entity.attackEntityFrom((DamageSource)AbilityDamageSource.causeAbilityDamage((LivingEntity)player, this), 20.0F);
/*    */       }
/*    */     } 
/* 53 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 55 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\yuki\FubukiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */