/*    */ package xyz.pixelatedw.mineminenomi.abilities.cyborg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class CoupDeBooAbility extends Ability implements IFallDamageBlockingAbility {
/* 24 */   public static final Ability INSTANCE = new CoupDeBooAbility();
/*    */   
/*    */   private static final int COLA_REQUIRED = 20;
/*    */   
/*    */   private boolean hasFallDamage = true;
/*    */   
/*    */   public CoupDeBooAbility() {
/* 31 */     super("Coup De Boo", AbilityHelper.getRacialCategory());
/* 32 */     setMaxCooldown(10.0D);
/* 33 */     setDescription("Launches the user into the sky\nConsumes §220§r cola");
/*    */     
/* 35 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 40 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */     
/* 42 */     if (props.getCola() - 20 < 0) {
/*    */       
/* 44 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_COLA, new Object[0]));
/* 45 */       return false;
/*    */     } 
/*    */     
/* 48 */     this.hasFallDamage = false;
/*    */     
/* 50 */     Vec3d speed = WyHelper.propulsion((LivingEntity)player, 2.0D, 1.5D, 2.0D);
/* 51 */     player.setMotion(speed.x, speed.y + 3.5D, speed.z);
/* 52 */     props.alterCola(-20);
/* 53 */     WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
/* 54 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/* 55 */     spawnParticles((ServerWorld)player.world, player.getPosX(), player.getPosY(), player.getPosZ());
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void spawnParticles(ServerWorld world, double posX, double posY, double posZ) {
/* 61 */     for (int i = 0; i < 200; i++) {
/*    */       
/* 63 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
/* 64 */       data.setColor(0.48F, 0.36F, 0.0F);
/* 65 */       data.setLife(30);
/* 66 */       data.setSize(3.0F);
/* 67 */       data.setMotion(WyHelper.randomDouble() / 4.0D, WyHelper.randomDouble(), WyHelper.randomDouble() / 4.0D);
/* 68 */       double offsetX = WyHelper.randomWithRange(-3, 3) * WyHelper.randomDouble();
/* 69 */       double offsetY = WyHelper.randomWithRange(-2, 2) * WyHelper.randomDouble();
/* 70 */       double offsetZ = WyHelper.randomWithRange(-3, 3) * WyHelper.randomDouble();
/* 71 */       WyHelper.spawnParticles(data, world, posX + offsetX, posY + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetFallDamage(LivingEntity player) {
/* 78 */     this.hasFallDamage = true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasFallDamage() {
/* 84 */     return this.hasFallDamage;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\cyborg\CoupDeBooAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */