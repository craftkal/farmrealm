package com.farmmanager.data;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.*;

public class SpawnAreaData extends SavedData {
    private static final String DATA_KEY = "farmmanager_spawn_areas";

    private boolean spawnEnabled = true;
    private final Map<String, SpawnAreaEntry> areas = new LinkedHashMap<>();

    public SpawnAreaData() {
    }

    public static SpawnAreaData get(MinecraftServer server) {
        ServerLevel overworld = server.overworld();
        return overworld.getDataStorage().computeIfAbsent(
            new SavedData.Factory<>(SpawnAreaData::new, SpawnAreaData::load, DataFixTypes.LEVEL),
            DATA_KEY
        );
    }

    public static SpawnAreaData load(CompoundTag tag, HolderLookup.Provider registries) {
        SpawnAreaData data = new SpawnAreaData();
        data.spawnEnabled = tag.getBoolean("spawnEnabled");
        CompoundTag areasTag = tag.getCompound("areas");
        for (String key : areasTag.getAllKeys()) {
            CompoundTag entryTag = areasTag.getCompound(key);
            int minX = entryTag.getInt("minX");
            int minY = entryTag.getInt("minY");
            int minZ = entryTag.getInt("minZ");
            int maxX = entryTag.getInt("maxX");
            int maxY = entryTag.getInt("maxY");
            int maxZ = entryTag.getInt("maxZ");
            String entityType = entryTag.getString("entityType");
            int rate = entryTag.getInt("rate");
            data.areas.put(key, new SpawnAreaEntry(minX, minY, minZ, maxX, maxY, maxZ, entityType, rate));
        }
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putBoolean("spawnEnabled", spawnEnabled);
        CompoundTag areasTag = new CompoundTag();
        for (Map.Entry<String, SpawnAreaEntry> entry : areas.entrySet()) {
            CompoundTag entryTag = new CompoundTag();
            SpawnAreaEntry area = entry.getValue();
            entryTag.putInt("minX", area.minX);
            entryTag.putInt("minY", area.minY);
            entryTag.putInt("minZ", area.minZ);
            entryTag.putInt("maxX", area.maxX);
            entryTag.putInt("maxY", area.maxY);
            entryTag.putInt("maxZ", area.maxZ);
            entryTag.putString("entityType", area.entityTypeId);
            entryTag.putInt("rate", area.rate);
            areasTag.put(entry.getKey(), entryTag);
        }
        tag.put("areas", areasTag);
        return tag;
    }

    public boolean isSpawnEnabled() {
        return spawnEnabled;
    }

    public void setSpawnEnabled(boolean enabled) {
        this.spawnEnabled = enabled;
        setDirty();
    }

    public boolean addArea(String name, BlockPos pos1, BlockPos pos2, String entityTypeId, int rate) {
        if (areas.containsKey(name)) {
            return false;
        }
        int minX = Math.min(pos1.getX(), pos2.getX());
        int minY = Math.min(pos1.getY(), pos2.getY());
        int minZ = Math.min(pos1.getZ(), pos2.getZ());
        int maxX = Math.max(pos1.getX(), pos2.getX());
        int maxY = Math.max(pos1.getY(), pos2.getY());
        int maxZ = Math.max(pos1.getZ(), pos2.getZ());
        areas.put(name, new SpawnAreaEntry(minX, minY, minZ, maxX, maxY, maxZ, entityTypeId, rate));
        setDirty();
        return true;
    }

    public boolean removeArea(String name) {
        SpawnAreaEntry removed = areas.remove(name);
        if (removed != null) {
            setDirty();
            return true;
        }
        return false;
    }

    public Map<String, SpawnAreaEntry> getAreas() {
        return Collections.unmodifiableMap(areas);
    }

    public static class SpawnAreaEntry {
        public final int minX, minY, minZ;
        public final int maxX, maxY, maxZ;
        public final String entityTypeId;
        public final int rate;

        public SpawnAreaEntry(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, String entityTypeId, int rate) {
            this.minX = minX;
            this.minY = minY;
            this.minZ = minZ;
            this.maxX = maxX;
            this.maxY = maxY;
            this.maxZ = maxZ;
            this.entityTypeId = entityTypeId;
            this.rate = rate;
        }

        public BlockPos getRandomPos(Random random) {
            int x = minX + random.nextInt(maxX - minX + 1);
            int y = minY + random.nextInt(maxY - minY + 1);
            int z = minZ + random.nextInt(maxZ - minZ + 1);
            return new BlockPos(x, y, z);
        }
    }
}
