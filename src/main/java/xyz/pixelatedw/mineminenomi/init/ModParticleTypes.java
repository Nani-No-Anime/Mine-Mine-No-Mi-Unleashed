package xyz.pixelatedw.mineminenomi.init;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;

@EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticleTypes {
  @Deprecated
  public static final ParticleType<GenericParticleData> GENERIC = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> YUKI = new ParticleType(true, GenericParticleData.DESERIALIZER);
  public static final ParticleType<GenericParticleData> YUKI2 = new ParticleType(true, GenericParticleData.DESERIALIZER);
  public static final ParticleType<GenericParticleData> YUKI3 = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> PIKA = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> MERA = new ParticleType(true, GenericParticleData.DESERIALIZER);
  public static final ParticleType<GenericParticleData> MERA2 = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> MOKU = new ParticleType(true, GenericParticleData.DESERIALIZER);
  public static final ParticleType<GenericParticleData> MOKU2 = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> SUNA = new ParticleType(true, GenericParticleData.DESERIALIZER);
  public static final ParticleType<GenericParticleData> SUNA2 = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> GASU = new ParticleType(true, GenericParticleData.DESERIALIZER);
  public static final ParticleType<GenericParticleData> GASU2 = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> BLUE_FLAME = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> DARKNESS = new ParticleType(true, GenericParticleData.DESERIALIZER);
  public static final ParticleType<GenericParticleData> KUROUZU = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> GORO = new ParticleType(true, GenericParticleData.DESERIALIZER);
  public static final ParticleType<GenericParticleData> GORO2 = new ParticleType(true, GenericParticleData.DESERIALIZER);
  public static final ParticleType<GenericParticleData> GORO3 = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> MAGU = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> DOKU = new ParticleType(true, GenericParticleData.DESERIALIZER);
  public static final ParticleType<GenericParticleData> DOKU_PINK = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> ITO = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> GURA = new ParticleType(true, GenericParticleData.DESERIALIZER);
  public static final ParticleType<GenericParticleData> GURA2 = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> HIE = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> MERO = new ParticleType(true, GenericParticleData.DESERIALIZER);
  public static final ParticleType<GenericParticleData> HORU = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> CHIYU = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> RUST = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> AWA = new ParticleType(true, GenericParticleData.DESERIALIZER);
  public static final ParticleType<GenericParticleData> AWA2 = new ParticleType(true, GenericParticleData.DESERIALIZER);
  public static final ParticleType<GenericParticleData> AWA3 = new ParticleType(true, GenericParticleData.DESERIALIZER);
  public static final ParticleType<GenericParticleData> AWA_FOAM = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> BETA = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> NETSU = new ParticleType(true, GenericParticleData.DESERIALIZER);
  public static final ParticleType<GenericParticleData> NETSU2 = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> GRILL = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> MEDIC = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> GORO_YELLOW = new ParticleType(true, GenericParticleData.DESERIALIZER);
  public static final ParticleType<GenericParticleData> GORO2_YELLOW = new ParticleType(true, GenericParticleData.DESERIALIZER);
  public static final ParticleType<GenericParticleData> GORO3_YELLOW = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> HANA = new ParticleType(true, GenericParticleData.DESERIALIZER);
  
  public static final ParticleType<GenericParticleData> MAGNET = new ParticleType(true, GenericParticleData.DESERIALIZER);

  
  static {
    WyRegistry.registerParticleType(YUKI, "Yuki Particle");
    WyRegistry.registerParticleType(YUKI2, "Yuki2 Particle");
    WyRegistry.registerParticleType(YUKI3, "Yuki3 Particle");
    
    WyRegistry.registerParticleType(PIKA, "Pika Particle");
    
    WyRegistry.registerParticleType(MERA, "Mera Particle");
    WyRegistry.registerParticleType(MERA2, "Mera2 Particle");
    
    WyRegistry.registerParticleType(MOKU, "Moku Particle");
    WyRegistry.registerParticleType(MOKU2, "Moku2 Particle");
    
    WyRegistry.registerParticleType(SUNA, "Suna Particle");
    WyRegistry.registerParticleType(SUNA2, "Suna2 Particle");
    
    WyRegistry.registerParticleType(GASU, "Gasu Particle");
    WyRegistry.registerParticleType(GASU2, "Gasu2 Particle");
    
    WyRegistry.registerParticleType(BLUE_FLAME, "Blue Flame Particle");
    
    WyRegistry.registerParticleType(DARKNESS, "Darkness Particle");
    WyRegistry.registerParticleType(KUROUZU, "Kurouzu Particle");
    
    WyRegistry.registerParticleType(GORO, "Goro Particle");
    WyRegistry.registerParticleType(GORO2, "Goro2 Particle");
    WyRegistry.registerParticleType(GORO3, "Goro3 Particle");
    
    WyRegistry.registerParticleType(MAGU, "Magu Particle");
    
    WyRegistry.registerParticleType(DOKU, "Doku Particle");
    WyRegistry.registerParticleType(DOKU_PINK, "Doku Pink Particle");
    
    WyRegistry.registerParticleType(ITO, "Ito Particle");
    
    WyRegistry.registerParticleType(GURA, "Gura Particle");
    WyRegistry.registerParticleType(GURA2, "Gura2 Particle");
    
    WyRegistry.registerParticleType(HIE, "Hie Particle");
    
    WyRegistry.registerParticleType(MERO, "Mero Particle");
    WyRegistry.registerParticleType(HORU, "Horu Particle");
    
    WyRegistry.registerParticleType(CHIYU, "Chiyu Particle");
    
    WyRegistry.registerParticleType(RUST, "Rust Particle");
    
    WyRegistry.registerParticleType(AWA, "Awa Particle");
    WyRegistry.registerParticleType(AWA2, "Awa2 Particle");
    WyRegistry.registerParticleType(AWA3, "Awa3 Particle");
    WyRegistry.registerParticleType(AWA_FOAM, "Awa Foam Particle");
    
    WyRegistry.registerParticleType(BETA, "Beta Particle");
    
    WyRegistry.registerParticleType(NETSU, "Netsu Particle");
    WyRegistry.registerParticleType(NETSU2, "Netsu2 Particle");
    
    WyRegistry.registerParticleType(GRILL, "Grill Particle");
    
    WyRegistry.registerParticleType(MEDIC, "Medic Particle");
    
    WyRegistry.registerParticleType(GORO_YELLOW, "Goro Yellow Particle");
    WyRegistry.registerParticleType(GORO2_YELLOW, "Goro2 Yellow Particle");
    WyRegistry.registerParticleType(GORO3_YELLOW, "Goro3 Yellow Particle");
    
    WyRegistry.registerParticleType(HANA, "Hana Particle");
    
    WyRegistry.registerParticleType(MAGNET, "Magnet Particle");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
    ParticleManager manager = (Minecraft.getInstance()).particles;
    
    manager.registerFactory(YUKI, (IParticleFactory)new SimpleParticle.Factory(ModResources.YUKI));
    manager.registerFactory(YUKI2, (IParticleFactory)new SimpleParticle.Factory(ModResources.YUKI2));
    manager.registerFactory(YUKI3, (IParticleFactory)new SimpleParticle.Factory(ModResources.YUKI3));
    
    manager.registerFactory(PIKA, (IParticleFactory)new SimpleParticle.Factory(ModResources.PIKA));
    
    manager.registerFactory(MERA, (IParticleFactory)new SimpleParticle.Factory(ModResources.MERA));
    manager.registerFactory(MERA2, (IParticleFactory)new SimpleParticle.Factory(ModResources.MERA2));
    
    manager.registerFactory(MOKU, (IParticleFactory)new SimpleParticle.Factory(ModResources.MOKU));
    manager.registerFactory(MOKU2, (IParticleFactory)new SimpleParticle.Factory(ModResources.MOKU2));
    
    manager.registerFactory(SUNA, (IParticleFactory)new SimpleParticle.Factory(ModResources.SUNA));
    manager.registerFactory(SUNA2, (IParticleFactory)new SimpleParticle.Factory(ModResources.SUNA2));
    
    manager.registerFactory(GASU, (IParticleFactory)new SimpleParticle.Factory(ModResources.GASU));
    manager.registerFactory(GASU2, (IParticleFactory)new SimpleParticle.Factory(ModResources.GASU2));
    
    manager.registerFactory(BLUE_FLAME, (IParticleFactory)new SimpleParticle.Factory(ModResources.BLUE_FLAME));
    
    manager.registerFactory(DARKNESS, (IParticleFactory)new SimpleParticle.Factory(ModResources.DARKNESS));
    manager.registerFactory(KUROUZU, (IParticleFactory)new SimpleParticle.Factory(ModResources.KUROUZU));
    
    manager.registerFactory(GORO, (IParticleFactory)new SimpleParticle.Factory(ModResources.GORO));
    manager.registerFactory(GORO2, (IParticleFactory)new SimpleParticle.Factory(ModResources.GORO2));
    manager.registerFactory(GORO3, (IParticleFactory)new SimpleParticle.Factory(ModResources.GORO3));
    
    manager.registerFactory(MAGU, (IParticleFactory)new SimpleParticle.Factory(ModResources.MAGU));
    
    manager.registerFactory(DOKU, (IParticleFactory)new SimpleParticle.Factory(ModResources.DOKU));
    manager.registerFactory(DOKU_PINK, (IParticleFactory)new SimpleParticle.Factory(ModResources.DOKU_PINK));
    
    manager.registerFactory(ITO, (IParticleFactory)new SimpleParticle.Factory(ModResources.ITO));
    
    manager.registerFactory(GURA, (IParticleFactory)new SimpleParticle.Factory(ModResources.GURA));
    manager.registerFactory(GURA2, (IParticleFactory)new SimpleParticle.Factory(ModResources.GURA2));
    
    manager.registerFactory(HIE, (IParticleFactory)new SimpleParticle.Factory(ModResources.HIE));
    
    manager.registerFactory(MERO, (IParticleFactory)new SimpleParticle.Factory(ModResources.MERO));
    manager.registerFactory(HORU, (IParticleFactory)new SimpleParticle.Factory(ModResources.HORU));
    
    manager.registerFactory(CHIYU, (IParticleFactory)new SimpleParticle.Factory(ModResources.CHIYU));
    
    manager.registerFactory(RUST, (IParticleFactory)new SimpleParticle.Factory(ModResources.RUST));
    
    manager.registerFactory(AWA, (IParticleFactory)new SimpleParticle.Factory(ModResources.AWA));
    manager.registerFactory(AWA2, (IParticleFactory)new SimpleParticle.Factory(ModResources.AWA2));
    manager.registerFactory(AWA3, (IParticleFactory)new SimpleParticle.Factory(ModResources.AWA3));
    manager.registerFactory(AWA_FOAM, (IParticleFactory)new SimpleParticle.Factory(ModResources.AWA_FOAM));
    
    manager.registerFactory(BETA, (IParticleFactory)new SimpleParticle.Factory(ModResources.BETA));
    
    manager.registerFactory(NETSU, (IParticleFactory)new SimpleParticle.Factory(ModResources.NETSU));
    manager.registerFactory(NETSU2, (IParticleFactory)new SimpleParticle.Factory(ModResources.NETSU2));
    
    manager.registerFactory(GRILL, (IParticleFactory)new SimpleParticle.Factory(ModResources.GRILL));
    
    manager.registerFactory(MEDIC, (IParticleFactory)new SimpleParticle.Factory(ModResources.MEDIC));
    
    manager.registerFactory(GORO_YELLOW, (IParticleFactory)new SimpleParticle.Factory(ModResources.GORO_YELLOW));
    manager.registerFactory(GORO2_YELLOW, (IParticleFactory)new SimpleParticle.Factory(ModResources.GORO2_YELLOW));
    manager.registerFactory(GORO3_YELLOW, (IParticleFactory)new SimpleParticle.Factory(ModResources.GORO3_YELLOW));
    
    manager.registerFactory(HANA, (IParticleFactory)new SimpleParticle.Factory(ModResources.HANA));
    
    manager.registerFactory(MAGNET, (IParticleFactory)new SimpleParticle.Factory(ModResources.MAGNET));
  }
}


