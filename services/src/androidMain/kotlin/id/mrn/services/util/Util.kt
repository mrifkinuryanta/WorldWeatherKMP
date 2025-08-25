package id.mrn.services.util

import java.util.UUID

object Util {

}

actual fun generateUUID(): String = UUID.randomUUID().toString()