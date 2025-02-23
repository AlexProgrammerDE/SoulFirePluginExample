/*
 * SoulFire
 *
 * Copyright (C) 2023 SoulFire
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */
package com.soulfiremc.pluginexample;

import com.soulfiremc.server.api.ExternalPlugin;
import com.soulfiremc.server.api.event.lifecycle.InstanceSettingsRegistryInitEvent;
import com.soulfiremc.server.settings.lib.SettingsObject;
import com.soulfiremc.server.settings.property.BooleanProperty;
import com.soulfiremc.server.settings.property.ImmutableBooleanProperty;
import com.soulfiremc.server.settings.property.ImmutableIntProperty;
import com.soulfiremc.server.settings.property.IntProperty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lenni0451.lambdaevents.EventHandler;
import org.pf4j.Extension;

@Slf4j
@Extension
public class ExampleServerExtension extends ExternalPlugin {
    @EventHandler
    public void onSettingsRegistryInit(InstanceSettingsRegistryInitEvent event) {
        event.settingsRegistry().addPluginPage(HackJumpBoostSettings.class, "Hack Jump Boost", this, "rabbit", HackJumpBoostSettings.ENABLED);
        log.info("Run \"jump\" to see the hacked jump boost!");
    }

    @NoArgsConstructor(access = AccessLevel.NONE)
    public static class HackJumpBoostSettings implements SettingsObject {
        private static final String NAMESPACE = "hack-jump-boost";
        public static final BooleanProperty ENABLED = ImmutableBooleanProperty.builder()
            .namespace(NAMESPACE)
            .key("enabled")
            .uiName("Enable Hack Jump Boost")
            .description("Should we hack to add fake jump boost?")
            .defaultValue(true)
            .build();
        public static final IntProperty JUMP_BOOST_LEVEL = ImmutableIntProperty.builder()
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
