/*
 * Copyright 2023 Eton Otieno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample.aniwave.data.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DefaultDispatcherProvider @Inject constructor() : DispatcherProvider {

    override val main: CoroutineDispatcher get() = Dispatchers.Main

    override val mainImmediate: CoroutineDispatcher get() = Dispatchers.Main.immediate

    override val default: CoroutineDispatcher get() = Dispatchers.Default

    override val io: CoroutineDispatcher get() = Dispatchers.IO

    override val unconfined: CoroutineDispatcher get() = Dispatchers.Unconfined
}
