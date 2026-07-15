package app.paresh.patches.ohealth.alarm

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.extensions.InstructionExtensions.addInstructionsWithLabels
import app.morphe.patcher.extensions.InstructionExtensions.getInstruction
import app.morphe.patcher.patch.bytecodePatch
import app.paresh.patches.ohealth.shared.Constants.COMPATIBILITY_OHEALTH
import com.android.tools.smali.dexlib2.iface.instruction.OneRegisterInstruction

private const val EXTENSION = "Lapp/paresh/extension/ohealth/AlarmSyncExtension;"

@Suppress("unused")
val alarmSyncPatch = bytecodePatch(
    name = "Universal Alarm Sync",
    description = "Unlocks the alarm sync toggle for all Android devices and reads alarms via AlarmManager."
) {
    compatibleWith(COMPATIBILITY_OHEALTH)

    extendWith("extensions/extension.mpe")

    execute {
        // 1. Make the "is compatible clock installed?" check always return true.
        //    This enables the alarm sync toggle in the settings UI on all devices.
        ClockInstalledCheckFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x1
                return v0
            """
        )

        // 2. Redirect PlatformClockManager.c(Context) (the bind method) to our extension.
        //    The extension injects an AlarmManager-based IClockAidlInterface implementation
        //    into the mIClockAidlInterface field (field 'a'), then returns true so the rest
        //    of the method proceeds as if the bind succeeded.
        //
        //    p0 = PlatformClockManager instance (this)
        //    p1 = Context
        PlatformClockManagerBindFingerprint.method.addInstructionsWithLabels(
            0,
            """
                invoke-static {p0, p1}, $EXTENSION->injectAlarmManagerImpl(Lcom/coloros/platformalarmclock/PlatformClockManager;Landroid/content/Context;)Z
                move-result v0
                if-eqz v0, :continue
                const/4 v0, 0x1
                return v0
                :continue
                nop
            """
        )
    }
}
