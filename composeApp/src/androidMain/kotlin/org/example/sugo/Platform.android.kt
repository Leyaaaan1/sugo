package org.example.sugo

import android.os.Build
import org.example.sugo.util.AndroidPlatform

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()