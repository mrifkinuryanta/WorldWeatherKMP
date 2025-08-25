package id.mrn.services.util

import platform.Foundation.NSUUID

object Util {

}

actual fun generateUUID(): String = NSUUID().UUIDString()