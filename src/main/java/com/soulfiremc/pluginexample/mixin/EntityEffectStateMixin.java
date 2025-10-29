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
import com.soulfiremc.server.bot.BotConnection;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class EntityEffectStateMixin {
    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void localPlayerTick(CallbackInfo ci) {
        var localPlayer = (LocalPlayer) (Object) this;
        var botConnection = BotConnection.CURRENT.get();
        if (botConnection.settingsSource().get(ExampleServerExtension.HackJumpBoostSettings.ENABLED)) {
            localPlayer.addEffect(new MobEffectInstance(
                MobEffects.JUMP_BOOST,
                20,
                botConnection.settingsSource().get(ExampleServerExtension.HackJumpBoostSettings.JUMP_BOOST_LEVEL)
            ));
        }
    }
}
