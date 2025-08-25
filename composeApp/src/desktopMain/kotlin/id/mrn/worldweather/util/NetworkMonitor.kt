//package id.mrn.worldweather.util
//
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.flowOn
//import java.net.InetSocketAddress
//import java.net.Socket
//
//actual class JvmNetworkMonitor : NetworkMonitor {
//    override val isOnline: Flow<Boolean> = flow {
//        while (true) {
//            val isConnected = try {
//                Socket().use { socket ->
//                    socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
//                    true
//                }
//            } catch (e: Exception) {
//                false
//            }
//            emit(isConnected)
//            delay(5000)
//        }
//    }.flowOn(Dispatchers.IO)
//}