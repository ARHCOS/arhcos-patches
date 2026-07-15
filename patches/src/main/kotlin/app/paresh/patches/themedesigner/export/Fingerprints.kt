package app.paresh.patches.themedesigner.export

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags

/**
 * IconPackCreateTask.save() — Entry point for saving custom icon packs
 * Location: com/samsung/android/themedesigner/domain/IconPackCreateTask.smali
 * Purpose: Triggered when user saves an icon pack
 */
object SaveIconPackFingerprint : Fingerprint(
    definingClass = "Lcom/samsung/android/themedesigner/domain/IconPackCreateTask;",
    name = "save",
    returnType = "V",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    parameters = listOf("Ljava/lang/String;")
)

/**
 * IconPackCreateTask.prepareSaveData() — Prepares data structure before saving
 * Location: com/samsung/android/themedesigner/domain/IconPackCreateTask.smali
 * Purpose: Gathers all icon pack data (PNG files, metadata, configs) before ZIP export
 */
object PrepareSaveDataFingerprint : Fingerprint(
    definingClass = "Lcom/samsung/android/themedesigner/domain/IconPackCreateTask;",
    name = "prepareSaveData",
    returnType = "V",
    accessFlags = listOf(AccessFlags.PRIVATE, AccessFlags.FINAL)
)

/**
 * IconPackCreateTask.saveJson() — Serializes icon pack config to JSON2
 * Location: com/samsung/android/themedesigner/domain/IconPackCreateTask.smali
 * Purpose: Writes JSON2 config file with app filters, drawables, and metadata
 */
object SaveJsonFingerprint : Fingerprint(
    definingClass = "Lcom/samsung/android/themedesigner/domain/IconPackCreateTask;",
    name = "saveJson",
    returnType = "V",
    accessFlags = listOf(AccessFlags.PRIVATE, AccessFlags.FINAL),
    parameters = listOf("Ljava/lang/String;")
)

/**
 * BackupWorker.doWork() — Async backup task execution
 * Location: com/samsung/android/themedesigner/worker/BackupWorker.smali
 * Purpose: Main entry point for backup/export operations
 */
object BackupWorkerDoWorkFingerprint : Fingerprint(
    definingClass = "Lcom/samsung/android/themedesigner/worker/BackupWorker;",
    name = "doWork",
    returnType = "Ljava/lang/Object;",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    filters = listOf(
        methodCall(definingClass = "Lkotlin/coroutines/Continuation;", name = ""),
        string("doBackup")
    )
)

/**
 * BackupWorker.performBackup() — Creates ZIP file with backup data
 * Location: com/samsung/android/themedesigner/worker/BackupWorker.smali
 * Purpose: Performs actual ZIP export operation
 */
object PerformBackupFingerprint : Fingerprint(
    definingClass = "Lcom/samsung/android/themedesigner/worker/BackupWorker;",
    name = "performBackup",
    returnType = "I",
    accessFlags = listOf(AccessFlags.PRIVATE, AccessFlags.FINAL),
    parameters = listOf("Ljava/util/List;")
)

/**
 * RestoreWorker.doWork() — Async restore task execution
 * Location: com/samsung/android/themedesigner/worker/RestoreWorker.smali
 * Purpose: Main entry point for restore/import operations with validation
 */
object RestoreWorkerDoWorkFingerprint : Fingerprint(
    definingClass = "Lcom/samsung/android/themedesigner/worker/RestoreWorker;",
    name = "doWork",
    returnType = "Ljava/lang/Object;",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    filters = listOf(
        methodCall(definingClass = "Lkotlin/coroutines/Continuation;", name = ""),
        string("doRestore")
    )
)

/**
 * IconPack.load() — Loads icon pack from resources
 * Location: com/samsung/android/themedesigner/iconpack/IconPack.smali
 * Purpose: Loads icon pack data structure (used during import validation)
 */
object IconPackLoadFingerprint : Fingerprint(
    definingClass = "Lcom/samsung/android/themedesigner/iconpack/IconPack;",
    name = "load",
    returnType = "V",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL)
)

/**
 * GTSUtil.extract() — Extracts ZIP files during restore
 * Location: com/samsung/android/themedesigner/gts/GTSUtil.smali
 * Purpose: ZIP extraction utility (validation point for safe re-import)
 */
object GTSUtilExtractFingerprint : Fingerprint(
    definingClass = "Lcom/samsung/android/themedesigner/gts/GTSUtil;",
    name = "extract",
    returnType = "V",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    parameters = listOf("Landroid/content/Context;", "Ljava/io/File;")
)
