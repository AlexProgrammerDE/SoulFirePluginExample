/*
 * SoulFire
 * Copyright (C) 2024  AlexProgrammerDE
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.soulfiremc.pluginexample;

import com.soulfiremc.server.api.ExternalPlugin;
import com.soulfiremc.server.api.PluginInfo;
import com.soulfiremc.server.api.event.lifecycle.InstanceSettingsRegistryInitEvent;
import com.soulfiremc.server.settings.lib.SettingsObject;
import com.soulfiremc.server.settings.lib.SettingsSource;
import com.soulfiremc.server.settings.property.BooleanProperty;
import com.soulfiremc.server.settings.property.ImmutableBooleanProperty;
import com.soulfiremc.server.settings.property.ImmutableIntProperty;
import com.soulfiremc.server.settings.property.IntProperty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lenni0451.lambdaevents.EventHandler;

@Slf4j
public class ExampleServerExtension extends ExternalPlugin {
    protected ExampleServerExtension() {
        super(new PluginInfo(
            "soulfire-plugin-example",
            "SoulFire Plugin Example",
            "An example plugin for SoulFire Server",
            "AlexProgrammerDE",
            "1.0.0",
            "https://soulfiremc.com"
        ));
    }

    @EventHandler
    public void onSettingsRegistryInit(InstanceSettingsRegistryInitEvent event) {
        event.settingsPageRegistry().addPluginPage(HackJumpBoostSettings.class, "hack-jump-boost", "Hack Jump Boost", this, "rabbit", HackJumpBoostSettings.ENABLED);
        log.info("Run \"jump\" to see the hacked jump boost!");
    }

    @NoArgsConstructor(access = AccessLevel.NONE)
    public static class HackJumpBoostSettings implements SettingsObject {
        private static final String NAMESPACE = "hack-jump-boost";
        public static final BooleanProperty<SettingsSource.Bot> ENABLED = ImmutableBooleanProperty.<SettingsSource.Bot>builder()
            .sourceType(SettingsSource.Bot.INSTANCE)
            .namespace(NAMESPACE)
            .key("enabled")
            .uiName("Enable Hack Jump Boost")
            .description("Should we hack to add fake jump boost?")
            .defaultValue(true)
            .build();
        public static final IntProperty<SettingsSource.Bot> JUMP_BOOST_LEVEL = ImmutableIntProperty.<SettingsSource.Bot>builder()
            .sourceType(SettingsSource.Bot.INSTANCE)
            .namespace(NAMESPACE)
            .key("jump-boost-level")
            .uiName("Jump Boost Level")
            .description("The level of jump boost")
            .defaultValue(2)
            .minValue(0)
            .maxValue(255)
            .build();
    }
}
