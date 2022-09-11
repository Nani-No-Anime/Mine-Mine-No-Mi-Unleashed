package xyz.pixelatedw.mineminenomi.entities.mobs;

import java.util.Arrays;
import java.util.Random;

public class OPEntityTextureWeight {
	public Integer weight;
	public String texture;

	public OPEntityTextureWeight(String texture, int weight) {
		this.weight = weight;
		this.texture = texture;

	}
	
	public static OPEntityTextureWeight[] fromList(String[] textures) {
		return Arrays.stream(textures).<OPEntityTextureWeight>map(texture -> {
			return new OPEntityTextureWeight(texture, 1);
		}).<OPEntityTextureWeight>toArray(OPEntityTextureWeight[]::new);
	}

	public static String getRandomTexture(OPEntityTextureWeight[] textures, Random rand) {
		int id = rand.nextInt(textures.length);
		return textures[id].texture;
	 }

}