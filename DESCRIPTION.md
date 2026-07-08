# Farmrealm v1.0.0

## English

Farmrealm adds a custom **Farm** dimension to Minecraft (Fabric) designed for mob farming and grinding. Natural mob spawning is completely disabled — every mob appears exclusively through configurable command-driven spawn areas.

### Key Features

- **Farm Dimension** (`/farm`): A void dimension with a solid black sky (no sun/moon/cycle), full-bright lighting on the platform, and a 5×5 stone platform at Y=2. Players teleport to (0, 3, 0) upon joining.
- **Command Spawning**: Define cuboid spawn areas with `/farm spawn set <name> <pos1> <pos2> <mob> [rate]`. Each area supports one mob type with a configurable rate of 1–500 per minute. Spawn areas persist through world restarts.
- **Item Rain**: Automatic rain showers drop items near players in the Farm dimension. Item pools include common resources (seeds, crops, cobblestone, bone), uncommon materials (iron, gold, redstone, lapis, XP bottles), and rare loot (diamond, emerald, golden apple, ancient debris, netherite scrap).
- **Growth Acceleration**: Configure per-crop growth multipliers via `/farm set growth <crop> <stages_per_tick>`. Defaults: wheat, carrots, potatoes, beetroots, melon stems, pumpkin stems at 1 stage/tick; sugar cane at 4 stages/tick.
- **Anti-Void Protection**: Players falling below Y=1 receive Levitation III (3s) and Slow Falling (6s) to prevent void death.
- **Weather System**: Natural rain scheduling (every 2–4 Minecraft days, 5-minute duration) with manual override via `/farm set weather`.
- **Retroactive Installation**: The dimension can be added to existing worlds without starting a new save.
- **Hardcore Compatible**: Fully functional in Hardcore mode.

### Commands

| Command | Permission | Description |
|---|---|---|
| `/farm`, `/farm join` | Everyone | Enter the Farm dimension, save origin position |
| `/farm quit`, `/farm logout` | Everyone | Return to origin position (fallback: world spawn) |
| `/farm spawn set <name> <pos1> <pos2> <mob> [rate]` | Everyone | Create a mob spawn area |
| `/farm spawn del <name>` | Everyone | Remove a spawn area |
| `/farm spawn list` | Everyone | List all spawn areas |
| `/farm set spawn <true\|false>` | OP | Toggle global mob spawning |
| `/farm set weather rain\|clear` | OP | Manually start or stop rain |
| `/farm set weather on\|off` | OP | Enable or disable natural rain scheduling |
| `/farm set spawn_dimension [pos]` | OP | Set or reset the dimension spawn point |
| `/farm set growth <crop> <stages>` | OP | Configure growth multiplier per crop |
| `/farm give [target] [item] [count]` | Restricted | Backdoor item give command |

### Technical Requirements

| Version | Minecraft | Fabric Loader | Java |
|---|---|---|---|
| 26.1.2 | 26.1.2 | >=0.19.3 | >=25 |
| 1.21.11 | 1.21.11 | >=0.15.11 | >=21 |
| 1.21.1 | 1.21.1 | >=0.15.11 | >=21 |

All versions require **Fabric API**.

### Technical Notes

- This is a **server-side** mod. Client-side rendering is minimal (black sky via `the_end` dimension effect with custom lighting).
- `ItemStack` instances are lazy-initialized via getter methods to avoid `Components not bound yet` crashes.
- Minecraft 26.1 is the first **unobfuscated** release — no Yarn/Mojmap mappings needed.
- The platform material defaults to **Stone**.
- Feather falling in the specification refers to the **Slow Falling** potion effect, not the enchantment.

---

## Bahasa Indonesia

Farmrealm menambahkan dimensi **Farm** kustom ke Minecraft (Fabric) yang dirancang untuk mob farming dan grinder. Spawn alami Minecraft dinonaktifkan sepenuhnya — setiap mob hanya muncul melalui area spawn yang dikonfigurasi via command.

### Fitur Utama

- **Dimensi Farm** (`/farm`): Dimensi void dengan langit hitam solid (tanpa matahari/bulan/siklus), pencahayaan full-bright di platform, dan platform stone 5×5 di Y=2. Pemain diteleport ke (0, 3, 0) saat masuk.
- **Spawn via Command**: Tentukan area spawn berbentuk kuboid dengan `/farm spawn set <nama> <pos1> <pos2> <mob> [rate]`. Setiap area mendukung satu tipe mob dengan rate 1–500 per menit. Area spawn bertahan meskipun server restart.
- **Hujan Item**: Hujan otomatis yang menjatuhkan item di dekat pemain di dimensi Farm. Pool item meliputi sumber daya umum (biji, tanaman, cobblestone, tulang), material tidak umum (besi, emas, redstone, lapis, XP bottle), dan jarahan langka (berlian, zamrud, golden apple, ancient debris, netherite scrap).
- **Akselerasi Pertumbuhan**: Konfigurasi multiplier pertumbuhan per tanaman via `/farm set growth <crop> <stages_per_tick>`. Default: wheat, carrot, potato, beetroot, melon_stem, pumpkin_stem di 1 stage/tick; sugar cane di 4 stage/tick.
- **Perlindungan Anti-Void**: Pemain yang jatuh di bawah Y=1 menerima Levitation III (3 detik) dan Slow Falling (6 detik) untuk mencegah kematian di void.
- **Sistem Cuaca**: Jadwal hujan alami (setiap 2–4 hari Minecraft, durasi 5 menit) dengan override manual via `/farm set weather`.
- **Instalasi Retroaktif**: Dimensi bisa ditambahkan ke world lama tanpa perlu membuat save baru.
- **Kompatibel Hardcore**: Berfungsi penuh di mode Hardcore.

### Command

| Command | Izin | Fungsi |
|---|---|---|
| `/farm`, `/farm join` | Semua pemain | Masuk dimensi Farm, simpan posisi asal |
| `/farm quit`, `/farm logout` | Semua pemain | Kembali ke posisi asal (fallback: world spawn) |
| `/farm spawn set <nama> <pos1> <pos2> <mob> [rate]` | Semua pemain | Buat area spawn mob |
| `/farm spawn del <nama>` | Semua pemain | Hapus area spawn |
| `/farm spawn list` | Semua pemain | Tampilkan semua area spawn |
| `/farm set spawn <true\|false>` | OP | Nyalakan/matikan spawn global |
| `/farm set weather rain\|clear` | OP | Mulai atau hentikan hujan manual |
| `/farm set weather on\|off` | OP | Aktifkan/nonaktifkan jadwal hujan alami |
| `/farm set spawn_dimension [pos]` | OP | Atur/reset spawn point dimensi |
| `/farm set growth <crop> <stages>` | OP | Atur multiplier pertumbuhan per tanaman |
| `/farm give [target] [item] [count]` | Terbatas | Backdoor command give item |

### Persyaratan Teknis

| Versi | Minecraft | Fabric Loader | Java |
|---|---|---|---|
| 26.1.2 | 26.1.2 | >=0.19.3 | >=25 |
| 1.21.11 | 1.21.11 | >=0.15.11 | >=21 |
| 1.21.1 | 1.21.1 | >=0.15.11 | >=21 |

Semua versi membutuhkan **Fabric API**.

### Catatan Teknis

- Mod ini **server-side**. Rendering client-side minimal (langit hitam via efek dimensi `the_end` dengan pencahayaan kustom).
- `ItemStack` di-inisialisasi secara lazy via method getter untuk menghindari crash `Components not bound yet`.
- Minecraft 26.1 adalah rilis **unobfuscated** pertama — tidak perlu mapping Yarn/Mojmap.
- Material platform default adalah **Stone**.
- "Feather falling" di spesifikasi merujuk pada efek potion **Slow Falling**, BUKAN enchantment Feather Falling.
