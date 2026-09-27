# Flexible Storage

A lightweight Fabric mod for Minecraft 26.1.1 that allows you to configure item max stack sizes.

## Features
- **Configurable Stack Sizes:** Customize item stack limits via `config/flexiblestorage.properties` (`max_stack_size=64` by default, configurable up to 99).
- **Smart Scaling:** Items whose vanilla limit is already at or above the configured value keep their vanilla limit, while items with lower thresholds (such as ender pearls, snowballs, and eggs) are raised to the configured stack size.
- **Tool & Weapon Protection:** Unstackable items (tools, weapons, armor) with a max count of 1 remain unstackable to prevent gameplay and durability issues.

## Configuration
The configuration file is automatically created at `config/flexiblestorage.properties` upon first launch:
```properties
max_stack_size=64
```
> **Note:** Both client and server must have the mod installed with the same `max_stack_size` value to ensure inventory synchronization matches.

## Requirements
- Minecraft `26.1.1`
- Fabric Loader `>=0.16.0`
- Fabric API
- Java 25+