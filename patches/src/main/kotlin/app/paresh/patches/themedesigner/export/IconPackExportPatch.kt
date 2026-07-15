package app.paresh.patches.themedesigner.export

import app.morphe.patcher.patch.bytecodePatch

/**
 * Theme Park patches - DISABLED
 * 
 * The fingerprints are causing bytecode corruption even when patches aren't selected.
 * This means the Fingerprint objects themselves are breaking the DEX processing.
 * 
 * Disabled until fingerprints can be fixed to not interfere with DEX compilation.
 */

@Suppress("unused")
val restoreWorkerLoggingPatch = bytecodePatch(
    name = "Log Icon Restore Operations",
    description = "Adds logging for icon pack restore operations",
    default = false
) {
    execute { }
}

@Suppress("unused")
val backupWorkerLoggingPatch = bytecodePatch(
    name = "Log Icon Backup Operations",
    description = "Adds logging for icon pack backup operations",
    default = false
) {
    execute { }
}
