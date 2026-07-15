package app.paresh.patches.ohealth.alarm

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags

/**
 * Matches xb.b.b(Context) — logged as "GlobalClockVersionUtils.isClockInstalled".
 *
 * Returns true only if com.coloros.alarmclock (ColorOS) or com.oneplus.deskclock
 * (OnePlus) is installed. Used in AlarmNotifyActivity to enable/disable the alarm
 * sync toggle. Patched to always return true.
 *
 * Smali (classes5/xb/b.smali):
 *   .method public static b(Landroid/content/Context;)Z
 *     const-string v0, "com.coloros.alarmclock"
 *     invoke-static ..., Lf6/a;->a(...)Z
 *     ...
 *     const-string v0, "com.oneplus.deskclock"
 *     invoke-static ..., Lf6/a;->a(...)Z
 *     ...
 *     const-string v1, "isClockInstalled --> "
 *     return v0
 */
object ClockInstalledCheckFingerprint : Fingerprint(
    returnType = "Z",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.STATIC),
    parameters = listOf("Landroid/content/Context;"),
    filters = listOf(
        string("com.coloros.alarmclock"),
        string("com.oneplus.deskclock"),
        string("isClockInstalled --> "),
    )
)

/**
 * Matches PlatformClockManager.c(Context) — the clock service bind() method.
 *
 * Tries to bind to ColorOS/OnePlus clock AIDL service. Patched to instead inject
 * our AlarmManager-based IClockAidlInterface implementation so alarm sync works
 * on any Android device.
 *
 * Smali (classes5/com/coloros/platformalarmclock/PlatformClockManager.smali):
 *   .method public c(Landroid/content/Context;)Z
 *     const-string v5, "com.coloros.alarmclock.service.PlatformUtilsClockServices"
 *     invoke-virtual ..., Landroid/content/Context;->bindService(...)Z
 *     const-string v0, "bind  isBindSuccess = "
 */
object PlatformClockManagerBindFingerprint : Fingerprint(
    returnType = "Z",
    accessFlags = listOf(AccessFlags.PUBLIC),
    parameters = listOf("Landroid/content/Context;"),
    filters = listOf(
        string("com.coloros.alarmclock.service.PlatformUtilsClockServices"),
        methodCall(definingClass = "Landroid/content/Context;", name = "bindService"),
        string("bind  isBindSuccess = "),
    )
)
