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
package com.soulfiremc.pluginexample.mixin;

import com.soulfiremc.pluginexample.ExampleServerExtension;
import com.soulfiremc.server.data.EffectType;
import com.soulfiremc.server.protocol.bot.state.entity.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(LocalPlayer.class)
public class EntityEffectStateMixin {
    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void localPlayerTick() {
        var localPlayer = (LocalPlayer) (Object) this;
        var botConnection = localPlayer.connection();
        if (botConnection.settingsSource().get(ExampleServerExtension.HackJumpBoostSettings.ENABLED)) {
            localPlayer.effectState().updateEffect(
                EffectType.JUMP,
                botConnection.settingsSource().get(ExampleServerExtension.HackJumpBoostSettings.JUMP_BOOST_LEVEL),
                20,
                false,
                false,
                false,
                false);
        }
    }
}
