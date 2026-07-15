package app.paresh.patches.themedesigner.export

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch

/**
 * Theme Park Icon Pack Export/Restore Patches
 * 
 * Samsung Theme Park (com.samsung.android.themedesigner v1.1.02.2)
 * 
 * These patches add logging to restore/backup operations without
 * modifying core functionality.
 */

@Suppress("unused")
val restoreWorkerLoggingPatch = bytecodePatch(
    name = "Log Icon Restore Operations",
    description = "Adds logging for icon pack restore operations",
    default = true
) {
    execute {
        // Find the doWork method and add a log at the very start
        RestoreWorkerDoWorkFingerprint.methodOrNull?.apply {
            addInstructions(0, """
                const-string v15, "ThemeDesigner"
                const-string v14, "RestoreWorker.doWork() started"
                invoke-static {v15, v14}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I
                move-result v15
            """)
        }
    }
}

@Suppress("unused")
val backupWorkerLoggingPatch = bytecodePatch(
    name = "Log Icon Backup Operations",
    description = "Adds logging for icon pack backup operations",
    default = true
) {
    execute {
        // Find the doWork method in BackupWorker and add a log
        BackupWorkerDoWorkFingerprint.methodOrNull?.apply {
            addInstructions(0, """
                const-string v15, "ThemeDesigner"
                const-string v14, "BackupWorker.doWork() started"
                invoke-static {v15, v14}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I
                move-result v15
            """)
        }
    }
}
