package com.example.url_launcher_test
import android.app.Activity
import io.flutter.embedding.android.FlutterActivity
import android.content.Intent
import android.net.Uri
import androidx.annotation.NonNull
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import android.app.ActivityManager

class MainActivity : FlutterActivity() {
    private val CHANNEL = "skype_integration"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        ).setMethodCallHandler { call, result ->
            if (call.method == "makeSkypeCall") {
                val manager: ActivityManager =
                    getSystemService(Activity.ACTIVITY_SERVICE) as ActivityManager
                manager.killBackgroundProcesses("com.skype.raider")
                result.success("Success")
            } else {
                result.notImplemented()
            }
        }
    }
}
