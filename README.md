# MoreStorageStack

A lightweight Fabric mod for Minecraft 26.1.1 that allows you to configure item max stack sizes with native in-game controls and instant presets.

## Features
- **In-Game Preset GUI:** Access an in-game settings screen with 8 instant presets: `64`, `127`, `128`, `256`, `512`, `1024`, `2048`, and `4096`.
- **Configurable Keybind:** Fully customizable hotkey (unbound by default, configurable in `Options` -> `Controls` -> `Key Binds`).
- **Mod Menu Support:** Integrates seamlessly with Mod Menu—open settings directly from the in-game mod list.
- **Smart Scaling:** Items whose vanilla limit is already at or above the configured value keep their limit, while items with lower thresholds (such as ender pearls, snowballs, and eggs) are raised to the selected stack size.
- **Tool & Weapon Protection:** Unstackable items (tools, weapons, armor) with a max count of 1 remain unstackable to prevent gameplay and durability issues.
- **Zero Overhead:** Completely native vanilla GUI widgets with no extra library dependencies required.

## Presets
- **SAFE Tier (64 – 127):** Stable, recommended for vanilla-like survival, servers, and modpacks.
- **BULK Tier (128 – 4096):** High-capacity stack sizes for massive storage systems.

## Configuration
Settings can be changed via the in-game GUI or directly in `config/morestoragestack.properties`:
```properties
max_stack_size=64
```
> **Note:** Both client and server must have the mod installed with the same `max_stack_size` value to ensure inventory synchronization matches.

## Requirements
- Minecraft `26.1.1`
- Fabric Loader `>=0.16.0`
- Fabric API
- Java 25+

## License
All Rights Reserved (ARR). Copyright (c) 2026 Ruinerpie.  
This mod is proprietary software intended for gameplay. You may not decompile, redistribute, modify, or reuse this mod or its source code without explicit written permission.