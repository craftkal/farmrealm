package com.farmmanager.data;

import com.farmmanager.FarmManagerMod;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

import java.util.HashMap;
import java.util.Map;

public class FarmGrowthData extends SavedData {
    private static final Identifier DATA_ID = Identifier.fromNamespaceAndPath(FarmManagerMod.MOD_ID, "farm_growth");

    private static final Codec<Map<String, Integer>> MULTIPLIERS_CODEC =
        Codec.unboundedMap(Codec.STRING, Codec.INT);

    public static final Codec<FarmGrowthData> CODEC = RecordCodecBuilder.create(instance ->
        instance.group(
            MULTIPLIERS_CODEC.fieldOf("growthMultipliers").forGetter(d -> d.growthMultipliers)
        ).apply(instance, FarmGrowthData::new)
    );

    public static final SavedDataType<FarmGrowthData> TYPE = new SavedDataType<>(
        DATA_ID,
        FarmGrowthData::new,
        CODEC,
        DataFixTypes.LEVEL
    );

    private final Map<String, Integer> growthMultipliers = new HashMap<>();

    public FarmGrowthData() {
        growthMultipliers.put("minecraft:wheat", 1);
        growthMultipliers.put("minecraft:carrots", 1);
        growthMultipliers.put("minecraft:potatoes", 1);
        growthMultipliers.put("minecraft:beetroots", 1);
        growthMultipliers.put("minecraft:sugar_cane", 4);
        growthMultipliers.put("minecraft:melon_stem", 1);
        growthMultipliers.put("minecraft:pumpkin_stem", 1);
    }

    public FarmGrowthData(Map<String, Integer> growthMultipliers) {
        this.growthMultipliers.putAll(growthMultipliers);
    }

    public static FarmGrowthData get(MinecraftServer server) {
        ServerLevel overworld = server.overworld();
        return overworld.getDataStorage().computeIfAbsent(TYPE);
    }

    public int getMultiplier(Block block) {
        Identifier id = BuiltInRegistries.BLOCK.getKey(block);
        return growthMultipliers.getOrDefault(id.toString(), 0);
    }

    public void setMultiplier(String blockId, int stages) {
        growthMultipliers.put(blockId, stages);
        setDirty();
    }

    public Map<String, Integer> getMultipliers() {
        return Map.copyOf(growthMultipliers);
    }
}
