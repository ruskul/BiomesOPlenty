package biomesoplenty.common.biomes.overworld.subbiomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPSubBiome;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;

public class BiomeGenScrubland extends BOPSubBiome
{
	private static final Height biomeHeight = new Height(0.3F, 0.5F);
	
	public BiomeGenScrubland(int biomeID)
	{
		super(biomeID);
		
		this.zoom = 0.25D;
		this.threshold = 0D;
		
        this.setHeight(biomeHeight);
		this.setColor(11445290);
        this.setTemperatureRainfall(1.2F, 0.05F);

		this.theBiomeDecorator.treesPerChunk = 7;

        this.bopWorldFeatures.setFeature("shrubsPerChunk", 2);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 2);
        this.bopWorldFeatures.setFeature("generatePumpkins", false);
		
        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 30);
        
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 0), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPDoubleFlora(2), 0.5D);
	}
	
	@Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(3) == 0 ? new WorldGenShrub(3, 0) : new WorldGenOriginalTree(Blocks.log, Blocks.leaves, 3, 2, false, 2, 1, false);
	}
	
	@Override
	public void decorate(World world, Random random, int chunkX, int chunkZ)
	{
		super.decorate(world, random, chunkX, chunkZ);
		int var5 = 12 + random.nextInt(6);

		for (int var6 = 0; var6 < var5; ++var6)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(28) + 4;
			int z = chunkZ + random.nextInt(16);

			Block block = world.getBlock(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 2, 2);
			}
		}
	}
}
