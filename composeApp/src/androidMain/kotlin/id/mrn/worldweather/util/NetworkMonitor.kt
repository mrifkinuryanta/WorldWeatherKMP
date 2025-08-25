//package id.mrn.worldweather.util
//
//import android.content.Context
//import android.net.ConnectivityManager
//import android.net.Network
//import android.net.NetworkCapabilities
//import android.net.NetworkRequest
//import kotlinx.coroutines.channels.awaitClose
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.callbackFlow
//import kotlinx.coroutines.flow.conflate
//
///**
// * Monitors network connectivity changes.
// * This class provides a Flow that emits true when the device is online and false otherwise.
// *
// * @param context The application context.
// */
//actual class AndroidNetworkMonitor(private val context: Context) : NetworkMonitor {
//    private val connectivityManager =
//        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//
//    /**
//     * A Flow that emits true if the network is available, false otherwise.
//     * It emits the initial network state and then updates on every change.
//     * The flow is conflated, meaning that if multiple network state changes occur in a short period,
//     * only the latest state will be emitted.
//     */
//    override val isOnline: Flow<Boolean> = callbackFlow {
//        val callback = object : ConnectivityManager.NetworkCallback() {
//            override fun onAvailable(network: Network) {
//                super.onAvailable(network)
//                trySend(true)
//            }
//
//            override fun onLost(network: Network) {
//                super.onLost(network)
//                trySend(false)
//            }
//
//            override fun onUnavailable() {
//                super.onUnavailable()
//                trySend(false)
//            }
//        }
//
//        val initialNetwork = connectivityManager.activeNetwork
//        val capabilities = connectivityManager.getNetworkCapabilities(initialNetwork)
//        val isInitiallyOnline =
//            capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
//        trySend(isInitiallyOnline)
//
//        val networkRequest = NetworkRequest.Builder()
//            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//            .build()
//        connectivityManager.registerNetworkCallback(networkRequest, callback)
//
//        awaitClose {
//            connectivityManager.unregisterNetworkCallback(callback)
//        }
//    }.conflate()
//}