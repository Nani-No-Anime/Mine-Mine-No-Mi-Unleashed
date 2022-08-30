/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.ArenaData;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.BaratieArena;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.Marine153rdArena;
/*    */ 
/*    */ 
/*    */ public class ModArenas
/*    */ {
/* 12 */   public static final List<ArenaData> ARENAS = new ArrayList<>();
/*    */   
/* 14 */   public static final ArenaData BARATIE = (ArenaData)new BaratieArena();
/* 15 */   public static final ArenaData MARINE_153rd_BRANCH = (ArenaData)new Marine153rdArena();
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModArenas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */