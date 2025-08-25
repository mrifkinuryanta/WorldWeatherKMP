//package id.mrn.worldweather.util
//
//import kotlinx.cinterop.ExperimentalForeignApi
//import kotlinx.coroutines.channels.awaitClose
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.callbackFlow
//import kotlinx.coroutines.flow.conflate
//import platform.Network.NWPathMonitor
//import platform.Network.NWPathStatus
//import platform.darwin.dispatch_get_main_queue
//
//@OptIn(ExperimentalForeignApi::class)
//actual class IOSNetworkMonitor : NetworkMonitor {
//    override val isOnline: Flow<Boolean> = callbackFlow {
//        val monitor = NWPathMonitor()
//
//        // Handler yang akan dipanggil setiap kali status jaringan berubah
//        monitor.pathUpdateHandler = { path ->
//            trySend(path.status == NWPathStatus.NWPathStatusSatisfied)
//        }
//
//        // Mulai memantau di main thread
//        monitor.start(dispatch_get_main_queue())
//
//        // Saat Flow ditutup, hentikan monitor
//        awaitClose {
//            monitor.cancel()
//        }
//    }.conflate()
//}