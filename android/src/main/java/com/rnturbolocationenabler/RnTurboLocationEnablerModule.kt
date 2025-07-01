package com.rnturbolocationenabler

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import com.facebook.react.bridge.ActivityEventListener
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.annotations.ReactModule
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority

@ReactModule(name = RnTurboLocationEnablerModule.NAME)
class RnTurboLocationEnablerModule(
  private val reactContext: ReactApplicationContext
) : NativeRnTurboLocationEnablerSpec(reactContext), ActivityEventListener {

  private var pendingPromise: Promise? = null

  init {
    reactContext.addActivityEventListener(this)
  }

  override fun getName() = NAME

  override fun requestLocationEnabled(promise: Promise?) {
    currentActivity?.let { activity ->
      val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY,
        10_000
      ).build()

      val settingsRequest = LocationSettingsRequest.Builder()
        .addLocationRequest(locationRequest)
        .setAlwaysShow(true)
        .build()

      val client = LocationServices.getSettingsClient(activity)
      client.checkLocationSettings(settingsRequest)
        .addOnSuccessListener {
          promise?.resolve(true)
        }
        .addOnFailureListener { exception ->
          if (exception is ResolvableApiException) {
            try {
              pendingPromise = promise
              exception.startResolutionForResult(activity, REQUEST_CODE)
            } catch (ex: Exception) {
              promise?.reject("ERROR_RESOLVING_SETTINGS", ex.message, ex)
            }
          } else {
            promise?.resolve(false)
          }
        }
    } ?: promise?.reject("NO_ACTIVITY", "Current activity is null.")
  }

  override fun isLocationEnabled(): Boolean {
    val locationManager =
      reactContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
      locationManager.isLocationEnabled
    } else {
      val mode = Settings.Secure.getInt(
        reactContext.contentResolver,
        Settings.Secure.LOCATION_MODE,
        Settings.Secure.LOCATION_MODE_OFF
      )
      mode != Settings.Secure.LOCATION_MODE_OFF
    }
  }

  override fun onActivityResult(
    activity: Activity,
    requestCode: Int,
    resultCode: Int,
    data: Intent?
  ) {
    if (requestCode == REQUEST_CODE) {
      pendingPromise?.resolve(resultCode == Activity.RESULT_OK)
      pendingPromise = null
    }
  }

  override fun onNewIntent(intent: Intent?) {
    // no-op
  }

  companion object {
    const val NAME = "RnTurboLocationEnabler"
    private const val REQUEST_CODE = 98765
  }
}
