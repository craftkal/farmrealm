package com.farmmanager.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;

public class FarmGrowthData extends SavedData {
    private static final String DATA_KEY = "farmmanager_farm_growth";

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

    public static FarmGrowthData get(MinecraftServer server) {
        ServerLevel overworld = server.overworld();
        return overworld.getDataStorage().computeIfAbsent(
            new SavedData.Factory<>(FarmGrowthData::new, FarmGrowthData::load, DataFixTypes.LEVEL),
            DATA_KEY
        );
    }

    public static FarmGrowthData load(CompoundTag tag, HolderLookup.Provider registries) {
        FarmGrowthData data = new FarmGrowthData();
        CompoundTag multipliers = tag.getCompound("growthMultipliers");
        for (String key : multipliers.getAllKeys()) {
            data.growthMultipliers.put(key, multipliers.getInt(key));
        }
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        CompoundTag multipliers = new CompoundTag();
        for (Map.Entry<String, Integer> entry : growthMultipliers.entrySet()) {
            multipliers.putInt(entry.getKey(), entry.getValue());
        }
        tag.put("growthMultipliers", multipliers);
        return tag;
    }

    public int getMultiplier(Block block) {
        ResourceLocation id = BuiltInRegistries.BLOCK.getKey(block);
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
