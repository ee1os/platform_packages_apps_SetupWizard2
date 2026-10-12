package app.grapheneos.setupwizard.view.activity

import android.app.KeyguardManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import app.grapheneos.setupwizard.action.SetupWizard

/** Offers the protected space (Settings) once the owner has a screen lock. */
class ProtectedSpaceActivity : ProxyActivity() {

    companion object {
        private const val TAG = "ProtectedSpaceActivity"
        private const val ACTION_SETUP_PROTECTED_SPACE =
            "com.android.settings.SETUP_PROTECTED_SPACE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!getSystemService(KeyguardManager::class.java)!!.isDeviceSecure) {
            Log.d(TAG, "onCreate: skipping, no screen lock")
            finish()
            SetupWizard.next(this)
        }
    }

    override fun launchActual() {
        SetupWizard.startActivityForResult(this, Intent(ACTION_SETUP_PROTECTED_SPACE))
    }

    override fun handleResult(resultCode: Int, data: Intent?) {
        Log.d(TAG, "handleResult: $resultCode")
        finish()
        SetupWizard.next(this)
    }
}
