/*    */ package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixAssaultZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.BlueFlamesParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.TenseiNoSoen2ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class SaiseiNoHonoAbility
/*    */   extends PunchAbility implements IFormRequiredAbility {
/* 22 */   public static final SaiseiNoHonoAbility INSTANCE = new SaiseiNoHonoAbility();
/*    */   
/* 24 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new BlueFlamesParticleEffect();
/* 25 */   private static final ParticleEffect PARTICLES2 = (ParticleEffect)new TenseiNoSoen2ParticleEffect();
/*    */ 
/*    */   
/*    */   public SaiseiNoHonoAbility() {
/* 29 */     super("Saisei No Hono", AbilityHelper.getDevilFruitCategory());
/* 30 */     setMaxCooldown(15.0D);
/* 31 */     setDescription("Uses the blue flames to heal the target by hitting them \n\n§2SHIFT-USE§r: Will heal entities around the user including the user themselves");
/*    */     
/* 33 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 34 */     this.onHitEntityEvent = this::onHitEntityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 39 */     if (player.isSneaking()) {
/*    */       
/* 41 */       List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, FactionHelper.getSameGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 42 */       int healed = 0;
/* 43 */       for (LivingEntity target : targets) {
/*    */         
/* 45 */         healed++;
/* 46 */         target.heal(10.0F);
/* 47 */         target.addPotionEffect(new EffectInstance(Effects.REGENERATION, 400, 1));
/* 48 */         PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */       } 
/* 50 */       setMaxCooldown(Math.max(1, healed * 6));
/* 51 */       PARTICLES2.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/* 52 */       endContinuity(player);
/* 53 */       return false;
/*    */     } 
/*    */     
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/* 61 */     target.heal(10.0F);
/* 62 */     target.addPotionEffect(new EffectInstance(Effects.REGENERATION, 400, 1));
/*    */     
/* 64 */     PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
/* 65 */     setMaxCooldown(5.0D);
/* 66 */     endContinuity(player);
/*    */     
/* 68 */     return -1.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 74 */     return new ZoanInfo[] { (ZoanInfo)PhoenixAssaultZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\toriphoenix\SaiseiNoHonoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */