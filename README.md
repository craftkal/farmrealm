# Farmrealm v1.0.0

Custom "Farm" dimension for Fabric — mob farm/grinder with 100% command-driven spawning.

## Versi

| File | Minecraft | Fabric Loader | Java |
|---|---|---|---|
| `farmrealm-26.1.2.jar` | 26.1.2 | >=0.19.3 | >=25 |
| `farmrealm-1.21.11.jar` | 1.21.11 | >=0.15.11 | >=21 |
| `farmrealm-1.21.1.jar` | 1.21.1 | >=0.15.11 | >=21 |

Semua versi membutuhkan **Fabric API**.

## Fitur

- Dimensi `/farm` — langit hitam, full-bright, platform stone 5×5
- Spawn alami **dinonaktifkan** — mob hanya lewat `/farm spawn set`
- Hujan item otomatis (common/uncommon/rare)
- Growth acceleration (wheat, carrot, potato, beetroot, sugar cane, melon, pumpkin)
- Anti-void (Levitation + Slow Falling)
- Data persist lewat world save (tahan restart)

## Command

| Command | Permission |
|---|---|
| `/farm`, `/farm join` | Semua pemain |
| `/farm quit`, `/farm logout` | Semua pemain |
| `/farm spawn set <nama> <pos1> <pos2> <mob> [rate]` | Semua pemain |
| `/farm spawn del <nama>` | Semua pemain |
| `/farm spawn list` | Semua pemain |
| `/farm give [target] [item] [count]` | Terbatas (craftkal1 + OP) |
| `/farm set spawn <true\|false>` | OP |
| `/farm set weather <rain\|clear\|on\|off>` | OP |
| `/farm set spawn_dimension [pos]` | OP |
| `/farm set growth <crop> <stages_per_tick>` | OP |

## Author

craftkal
