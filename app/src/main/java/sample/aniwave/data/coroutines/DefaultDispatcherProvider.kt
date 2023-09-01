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
