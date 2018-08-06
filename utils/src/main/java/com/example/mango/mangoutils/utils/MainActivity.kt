package com.example.mango.mangoutils.utils

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.MediaController
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import java.io.File

class MainActivity : AppCompatActivity() {
    private var mContext : Context? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        // Example of a call to a native method
        sample_text.text = stringFromJNI()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_rate_us -> gotoRateUs()
            R.id.action_share_with_friends -> {
                gotoShareWithFriends()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
    ############################# Java Version ###################################
    /**
     * rate star on google play store
    */
    private void gotoRateUs() {
    if (mContext != null) {
    try {
    Intent intent = new Intent(Intent.ACTION_VIEW,
    Uri.parse("market://details?id=" + mContext.getPackageName()));
    startActivity(intent);
    } catch (Exception e) {
    Log.d(TAG,"Error:" + e.toString());
    Toast.makeText(mContext, "Can't find google play store",Toast.LENGTH_SHORT)
    .show();
    }
    }
    }
    ############################ Java Version ####################################
    */
    private fun gotoRateUs(): Boolean {
        if(this.mContext != null) {
            try {
                var intent : Intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.data = Uri.parse("market://details?id=" + mContext!!.packageName)
                startActivity(intent)
            } catch (e: Exception) {
                Log.d("mango","Error:" + e.toString())
                Toast.makeText(mContext, "Can't find google play store",Toast.LENGTH_SHORT)
                        .show()
            }
        }
        return true
    }

    /**
    ############################# Java Version ###################################
    /**
     * share application to friends
    */
    private void gotoShareWithFriends() {
    if (mContext != null) {
    String packageName = mContext.getPackageName();

    try {
    ApplicationInfo info = mContext.getPackageManager()
    .getApplicationInfo(packageName, 0);
    String apkPath = info.sourceDir;

    Intent share = new Intent();
    share.setAction(Intent.ACTION_SEND);
    share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkPath)));
    share.setType("application/vnd.android.package-archive");

    startActivity(Intent.createChooser(share, getString(R.string.share_with_friends)));
    } catch (Exception e) {
    Log.d(TAG, "Error:" + e.toString());
    Toast.makeText(mContext, "Can't share with friends", Toast.LENGTH_SHORT)
    .show();
    }
    }
    }
    ############################ Java Version ####################################
     */

    /**
     * share application to friends
     */
    fun gotoShareWithFriends() {
        try {
            var packageName : String = mContext!!.packageName;
            var info : ApplicationInfo = mContext!!.packageManager.getApplicationInfo(packageName, 0)
            var apkPath :String = info.sourceDir

            var share : Intent = Intent()
            share.action = Intent.ACTION_SEND
            share.type = "application/vnd.android.package-archive"
            share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(File(apkPath)))

            startActivity(Intent.createChooser(share,getString(R.string.share_with_friends)))
        } catch (e: Exception) {
            Log.d("mango", "Error:" + e.toString())
            Toast.makeText(mContext, "Can't share with friends",Toast.LENGTH_SHORT).show()
        }

    }



    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
