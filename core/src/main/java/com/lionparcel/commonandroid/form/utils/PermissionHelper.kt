package com.lionparcel.commonandroid.form.utils

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*

class PermissionHelper {

    private var permissionRequestExecutable: ((Boolean) -> Unit)? = null

    private var permissionRationaleExecutable: ((String) -> Unit)? = null

    private var permissionRationaleDeniedAction: ((List<String>) -> Unit)? = null

    private var permissionRequestDeniedAction: ((List<String>) -> Unit)? = null

    private var requestedPermissions: Array<String>? = null

    private var permissionRequestCode: Int? = null

    fun executeWithAllPermissions(
        activity: Activity,
        permissions: Array<String>,
        executable: (Boolean) -> Unit,
        rationaleExecutable: (String) -> Unit = {},
        rationaleDenyAction: (List<String>) -> Unit = {},
        denyAction: (List<String>) -> Unit = {}
    ) {
        executePermissions(
            activity,
            permissions,
            executable,
            rationaleExecutable,
            rationaleDenyAction,
            denyAction
        )
    }

    private fun executePermissions(
        activity : Activity,
        permissions: Array<String>,
        executable: (Boolean) -> Unit,
        rationaleExecutable: (String) -> Unit,
        rationaleDenyAction: (List<String>) -> Unit,
        denyAction: (List<String>) -> Unit
    ) {
        val context = activity ?: return
        val grantedFilter = { permission: String ->
            ContextCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }
        val grantedPermissions = permissions.filter(grantedFilter)
        if (permissions.size == grantedPermissions.size) {
            executable(true)
        } else {
            permissionRequestExecutable = executable
            permissionRationaleExecutable = rationaleExecutable
            permissionRationaleDeniedAction = rationaleDenyAction
            permissionRequestDeniedAction = denyAction
            requestedPermissions = permissions
            permissionRequestCode = Random().nextInt(Short.MAX_VALUE.toInt())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.requestPermissions(permissions, permissionRequestCode as Int)
            }
        }
    }
    fun onRequestPermissionsResult(
        getActivity: Activity,
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == permissionRequestCode) {
            val grantedFilter = { index: Int, _: String ->
                grantResults[index] == PackageManager.PERMISSION_GRANTED
            }
            val grantedPermissions = permissions.filterIndexed(grantedFilter)
            val halfDeniedPermissions = permissions.filterNot(grantedPermissions::contains)
            if (halfDeniedPermissions.isEmpty()) {
                Handler(Looper.getMainLooper()).postDelayed({
                    permissionRequestExecutable?.invoke(false)
                }, 1000)
            } else {
                var allHalfDeniedPermissionsDenied = true
                val deniedPermissions = mutableListOf<String>()
                halfDeniedPermissions.forEach { permission ->
                    val activity = getActivity ?: return
                    val shouldShowRationale =
                        ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
                    if (!shouldShowRationale) {
                        deniedPermissions.add(permission)
                    } else {
                        permissionRationaleExecutable?.invoke(permission)
                        allHalfDeniedPermissionsDenied = false
                    }
                }
                if (allHalfDeniedPermissionsDenied) {
                    permissionRequestDeniedAction?.invoke(deniedPermissions)
                } else {
                    permissionRationaleDeniedAction?.invoke(deniedPermissions)
                }
            }
        }
    }
}