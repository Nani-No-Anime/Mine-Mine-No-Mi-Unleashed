package xyz.pixelatedw.mineminenomi.init;

import xyz.pixelatedw.mineminenomi.challenges.ArenaData;
import xyz.pixelatedw.mineminenomi.challenges.arenas.BaratieArena;
import xyz.pixelatedw.mineminenomi.challenges.arenas.Marine153rdArena;

import java.util.ArrayList;
import java.util.List;


public class ModArenas
{
  public static final List<ArenaData> ARENAS = new ArrayList<>();
  
  public static final ArenaData BARATIE = (ArenaData)new BaratieArena();
  public static final ArenaData MARINE_153rd_BRANCH = (ArenaData)new Marine153rdArena();
}


