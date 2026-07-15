package app.paresh.patches.themedesigner.export

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags

/**
 * Fingerprints for Samsung Theme Park (com.samsung.android.themedesigner)
 * These are verified against actual smali bytecode
 */

/**
 * RestoreWorker.doWork() - Kotlin suspend function
 * Signature: public doWork(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
 * 
 * Identified by:
 * - Returns Object (suspend function)
 * - Takes Continuation parameter
 * - Calls getInputData() 
 * - String "URIs file does not exist: "
 * - Calls File.exists()
 */
object RestoreWorkerDoWorkFingerprint : Fingerprint(
    definingClass = "Lcom/samsung/android/themedesigner/worker/RestoreWorker;",
    name = "doWork",
    returnType = "Ljava/lang/Object;",
    accessFlags = listOf(AccessFlags.PUBLIC),
    parameters = listOf("Lkotlin/coroutines/Continuation;"),
    filters = listOf(
        string("URIs file does not exist: "),
        methodCall(definingClass = "Ljava/io/File;", name = "exists")
    )
)

/**
 * BackupWorker.doWork() - Similar structure to RestoreWorker
 * Signature: public doWork(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
 */
object BackupWorkerDoWorkFingerprint : Fingerprint(
    definingClass = "Lcom/samsung/android/themedesigner/worker/BackupWorker;",
    name = "doWork",
    returnType = "Ljava/lang/Object;",
    accessFlags = listOf(AccessFlags.PUBLIC),
    parameters = listOf("Lkotlin/coroutines/Continuation;")
)

/**
 * IconPackCreateTask - likely handles icon pack creation/export
 * This needs verification - may not exist or have different name
 */
object IconPackSaveFingerprint : Fingerprint(
    definingClass = "L",  // Placeholder - needs actual class discovery
    returnType = "V",
    accessFlags = listOf(AccessFlags.PRIVATE)
)
