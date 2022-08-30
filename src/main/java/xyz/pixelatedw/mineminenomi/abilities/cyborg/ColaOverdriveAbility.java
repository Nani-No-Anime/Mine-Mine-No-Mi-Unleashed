/*    */ package xyz.pixelatedw.mineminenomi.abilities.cyborg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class ColaOverdriveAbility extends Ability {
/* 18 */   public static final Ability INSTANCE = new ColaOverdriveAbility();
/*    */ 
/*    */   
/*    */   public ColaOverdriveAbility() {
/* 22 */     super("Cola Overdrive", AbilityHelper.getRacialCategory());
/* 23 */     setMaxCooldown(50.0D);
/* 24 */     setDescription("The user absorbs half of their cola at once to boost their physical abilities temporarily");
/*    */     
/* 26 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 31 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */     
/* 33 */     double half = (props.getMaxCola() / 2.0F);
/*    */     
/* 35 */     if (props.getCola() - half < 0.0D) {
/*    */       
/* 37 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_COLA, new Object[0]));
/* 38 */       return false;
/*    */     } 
/*    */     
/* 41 */     props.setCola((int)(props.getCola() - half));
/* 42 */     WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
/*    */     
/* 44 */     player.heal((float)(half / 1000.0D * player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue()));
/* 45 */     player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 300, 3));
/* 46 */     player.addPotionEffect(new EffectInstance(Effects.SPEED, 300, 2));
/* 47 */     player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 300, 3));
/*    */     
/* 49 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\cyborg\ColaOverdriveAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */