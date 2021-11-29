/*
 * Copyright 2014-2021 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package io.ktor.server.application

import io.ktor.util.pipeline.*

/**
 * Contains handlers executed before the same handler is finished for all [otherPlugins].
 **/
public class BeforePluginsBuilder<PluginConfig : Any> internal constructor(
    currentPlugin: ApplicationPluginBuilder<PluginConfig>,
    otherPlugins: List<ApplicationPluginBuilder<*>>
) : RelativePluginBuilder<PluginConfig>(currentPlugin, otherPlugins) {

    override val application: Application = currentPlugin.application

    override fun selectPhase(phases: List<PipelinePhase>): PipelinePhase? = phases.firstOrNull()

    override fun insertPhase(
        pipeline: Pipeline<*, ApplicationCall>,
        relativePhase: PipelinePhase,
        newPhase: PipelinePhase
    ) {
        pipeline.insertPhaseBefore(relativePhase, newPhase)
    }
}
