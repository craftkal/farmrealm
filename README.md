# Farmrealm v1.0.0

Custom **Farm** dimension for Fabric — mob farm/grinder with 100% command-driven spawning.

## Versions

| File | Minecraft | Fabric Loader | Java |
|---|---|---|---|
| `farmrealm-26.1.2.jar` | 26.1.2 | >=0.19.3 | >=25 |
| `farmrealm-1.21.11.jar` | 1.21.11 | >=0.15.11 | >=21 |
| `farmrealm-1.21.1.jar` | 1.21.1 | >=0.15.11 | >=21 |

All versions require **Fabric API**.

## Features

- `/farm` dimension — black sky, full-bright, 5×5 stone platform
- Natural spawns **disabled** — mobs only via `/farm spawn set`
- Automatic item rain (common / uncommon / rare loot)
- Growth acceleration (wheat, carrot, potato, beetroot, sugar cane, melon, pumpkin)
- Anti-void protection (Levitation + Slow Falling)
- Persistent data across world restarts
- Hardcore compatible

## Commands

| Command | Permission |
|---|---|
| `/farm`, `/farm join` | Everyone |
| `/farm quit`, `/farm logout` | Everyone |
| `/farm spawn set <name> <pos1> <pos2> <mob> [rate]` | Everyone |
| `/farm spawn del <name>` | Everyone |
| `/farm spawn list` | Everyone |
| `/farm give [target] [item] [count]` | Restricted (craftkal1 + OP) |
| `/farm set spawn <true\|false>` | OP |
| `/farm set weather <rain\|clear\|on\|off>` | OP |
| `/farm set spawn_dimension [pos]` | OP |
| `/farm set growth <crop> <stages_per_tick>` | OP |

## License

Apache-2.0

## Author

craftkal
