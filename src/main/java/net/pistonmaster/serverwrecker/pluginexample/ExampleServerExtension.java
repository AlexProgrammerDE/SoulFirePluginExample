/*
 * ServerWrecker
 *
 * Copyright (C) 2023 ServerWrecker
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
package net.pistonmaster.serverwrecker.pluginexample;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.lenni0451.lambdaevents.EventHandler;
import net.pistonmaster.serverwrecker.api.PluginHelper;
import net.pistonmaster.serverwrecker.api.ServerExtension;
import net.pistonmaster.serverwrecker.api.ServerWreckerAPI;
import net.pistonmaster.serverwrecker.api.event.bot.BotJoinedEvent;
import net.pistonmaster.serverwrecker.api.event.lifecycle.SettingsRegistryInitEvent;
import net.pistonmaster.serverwrecker.plugins.AutoEat;
import net.pistonmaster.serverwrecker.settings.lib.SettingsObject;
import net.pistonmaster.serverwrecker.settings.lib.property.BooleanProperty;
import net.pistonmaster.serverwrecker.settings.lib.property.IntProperty;
import net.pistonmaster.serverwrecker.settings.lib.property.Property;
import org.pf4j.Extension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Extension
public class ExampleServerExtension implements ServerExtension {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleServerExtension.class);

    @EventHandler
    public static void onSettingsManagerInit(SettingsRegistryInitEvent event) {
        event.settingsRegistry().addClass(HackJumpBoostSettings.class, "Hack Jump Boost");
    }

    @Override
    public void onLoad() {
        LOGGER.info("Plugin is loading!");

        ServerWreckerAPI.registerListeners(ExampleServerExtension.class);

        LOGGER.info("Plugin is loaded!");
        LOGGER.info("Type \"jump\" to see the hacked jump boost!");
    }

    @NoArgsConstructor(access = AccessLevel.NONE)
    public static class HackJumpBoostSettings implements SettingsObject {
        private static final Property.Builder BUILDER = Property.builder("plugin-example-hack-jump-boost");
        public static final BooleanProperty HACK_JUMP_BOOST = BUILDER.ofBoolean(
            "hack-jump-boost",
            "Hack Jump Boost?",
            "Hack Jump Boost?",
            new String[]{"--hack-jump-boost"},
            true
        );
        public static final IntProperty JUMP_BOOST_LEVEL = BUILDER.ofInt(
            "jump-boost-level",
            "Jump Boost Level",
            "Jump Boost Level",
            new String[]{"--jump-boost-level"},
            5,
            0,
            255,
            1
        );
    }
}
