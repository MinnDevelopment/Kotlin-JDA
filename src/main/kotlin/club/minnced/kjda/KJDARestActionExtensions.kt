/*
 *     Copyright 2016 - 2017 Florian Spieß
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:Suppress("UNUSED")
@file:JvmName("KJDARestActionExtensions")
package club.minnced.kjda

import net.dv8tion.jda.core.Permission
import net.dv8tion.jda.core.managers.GuildController
import net.dv8tion.jda.core.requests.restaction.AuditableRestAction
import net.dv8tion.jda.core.requests.restaction.ChannelAction
import net.dv8tion.jda.core.requests.restaction.PermissionOverrideAction
import net.dv8tion.jda.core.requests.restaction.RoleAction
import java.awt.Color

// Guild Controller shortcuts

infix inline fun GuildController.createRole(init: RoleAction.() -> Unit) = with(createRole())
{
    init()
    promise()
}

inline fun GuildController.createTextChannel(name: String, init: ChannelAction.() -> Unit) = with(createTextChannel(name))
{
    init()
    promise()
}

inline fun GuildController.createVoiceChannel(name: String, init: ChannelAction.() -> Unit) = with(createVoiceChannel(name))
{
    init()
    promise()
}

infix inline fun RoleAction.name(lazy: () -> String?) : RoleAction = setName(lazy())
infix inline fun RoleAction.color(lazy: () -> Color?) : RoleAction = setColor(lazy())
infix inline fun RoleAction.hoisted(lazy: () -> Boolean) : RoleAction = setHoisted(lazy())
infix inline fun RoleAction.mentionable(lazy: () -> Boolean) : RoleAction = setMentionable(lazy())
infix inline fun RoleAction.permissions(lazy: ArrayList<Permission>.() -> Unit) : RoleAction = with(ArrayList<Permission>())
{
    lazy(this)
    setPermissions(this)
}

infix inline fun ChannelAction.name(lazy: () -> String) : ChannelAction = setName(lazy())
infix inline fun ChannelAction.topic(lazy: () -> String) : ChannelAction = setTopic(lazy())
infix inline fun ChannelAction.userLimit(lazy: () -> Int) : ChannelAction = setUserlimit(lazy())
infix inline fun ChannelAction.bitrate(lazy: () -> Int) : ChannelAction = setBitrate(lazy())

infix inline fun <T> AuditableRestAction<T>.reason(lazy: () -> String) = reason(lazy()).promise()

infix inline fun <T : PermissionOverrideAction> T.allow(lazy: ArrayList<Permission>.() -> Unit) : T =
    with(ArrayList<Permission>()) {
        lazy()
        this@allow
    }
infix inline fun <reified T : PermissionOverrideAction> T.deny(lazy: ArrayList<Permission>.() -> Unit) : T =
    with(ArrayList<Permission>()) {
        lazy()
        this@deny
    }