package app.paresh.patches.ohealth.shared

import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

object Constants {
    val COMPATIBILITY_OHEALTH = Compatibility(
        name = "OHealth",
        packageName = "com.heytap.health.international",
        apkFileType = ApkFileType.APK,
        appIconColor = 0x00C2A8,
        targets = listOf(
            AppTarget(version = "4.60.17_3e103a6_260324")
        )
    )
}
