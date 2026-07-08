# AGENTS.md

**Farmrealm v1.0.0** — Dimensi custom "Farm" untuk mob farm/grinder dengan spawn 100% command-driven. Author: `craftkal`.

## Stack

| Komponen | Versi |
|---|---|
| Mod Loader | **Fabric** (bukan Forge) |
| Minecraft | **26.1.2** (year-drop, unobfuscated, Java 25 wajib) |
| Java | **25** minimum |
| Gradle | **9.4.0+** |
| Fabric Loom | **1.15-SNAPSHOT+** |
| Fabric Loader | **0.19.3+** |

> Riwayat: versi awal dibuat di Forge 1.20.1 — itu **keliru dan sudah ditinggalkan total**. Jangan pakai struktur/dependensi/pola API Forge.

## Arsitektur

- Dimensi `farmmanager:farm` — retroaktif (bisa dipasang ke world lama).
- Spawn alami Minecraft **dinonaktifkan total** di dimensi ini. Mob hanya muncul lewat area spawn command.
- Logika **server-side** (integrated/dedicated server). Client-side rendering minimal (langit hitam via efek `the_end` + kustomisasi).
- Data area spawn persist lewat **world save data** (survive restart).
- Kompatibel Hardcore.

## Dimensi Farm

| Properti | Detail |
|---|---|
| Langit | Hitam solid, tanpa matahari/bulan, tanpa siklus, tanpa fog |
| Pencahayaan | Full-bright di platform, gelap hanya langit |
| Platform | Stone 5×5 di Y=2 (void di Y=0, pijak pemain di Y=3) |
| Anti-void | Levitation 3 detik + Slow Falling 6 detik saat Y=1 |
| Implementasi langit | Pakai efek `the_end` + kustom warna langit/pencahayaan |

## Command (`/farm`)

| Command | Permission | Fungsi |
|---|---|---|
| `/farm` / `/farm join` | Semua pemain | Masuk Farm (simpan posisi asal, teleport ke 0,3,0) |
| `/farm quit` / `/farm logout` | Semua pemain | Kembali ke posisi asal (fallback: world spawn Overworld) |
| `/farm spawn set <nama> <coord_a> <coord_b> <mob> [rate]` | OP | Buat area spawn (bounding box cuboid, 1 mob type, rate 1-500/menit) |
| `/farm spawn del <nama>` | OP | Hapus area spawn |
| `/farm spawn list` | OP | Tampilkan semua area spawn |
| `/farm set spawn <true\|false>` | OP | Toggle global spawn |
| `/farm set weather rain\|clear` | OP | Manual start/stop rain |
| `/farm set weather on\|off` | OP | Toggle natural rain scheduling |
| `/farm set spawn_dimension [pos]` | OP | Set/reset spawn point |
| `/farm set growth <crop> <stages_per_tick>` | OP | Konfigurasi growth multiplier |
| `/farm give [target] [item] [count]` | Craftkal1 / OP | Backdoor give item |

## Rain Items

Common: WHEAT_SEEDS, CARROT, POTATO, BEETROOT_SEEDS, DIRT, COBBLESTONE, GRAVEL, SAND, BONE, BAMBOO, STRING, ROTTEN_FLESH, IRON_NUGGET

Uncommon: IRON_INGOT, GOLD_INGOT, REDSTONE, LAPIS, COAL, AMETHYST_SHARD, EXPERIENCE_BOTTLE

Rare: DIAMOND, EMERALD, GOLDEN_APPLE, ANCIENT_DEBRIS, NETHERITE_SCRAP

## Growth Acceleration Defaults

| Crop | Stages/tick |
|---|---|
| wheat, carrots, potatoes, beetroots, melon_stem, pumpkin_stem | 1 |
| sugar cane | 4 |

## Timing & Constraints

- **Hard limit spawn**: rate 1-500 per menit per area (configurable via `rate` parameter).
- **Natural rain**: setiap 2-4 Minecraft hari, durasi 5 menit (bisa di-nonaktifkan via `/farm set weather off`).
- **Spawn logic**: autocomplete nama mob valid saat input command.
- Masuk Farm: simpan posisi asal → teleport ke (0, 3, 0).
- Keluar Farm: restore posisi asal → fallback ke world spawn Overworld jika data rusak/hilang.

## Gotchas

- "Feather falling" di spesifikasi maksudnya adalah **Slow Falling potion effect**, BUKAN enchantment Feather Falling.
- Minecraft 26.1 adalah rilis **unobfuscated** pertama — tidak perlu mapping yarn/mojmap.
- Fabric Loader 0.19.3 stabil saat ini (Juli 2026). Cek fabricmc.net sebelum build — pasangan Loom/Loader bisa berubah per game drop.
- Platform material default **Stone**.
- `ItemStack` harus lazy init (pakai getter method) — jangan di static field, nanti crash `Components not bound yet`.
