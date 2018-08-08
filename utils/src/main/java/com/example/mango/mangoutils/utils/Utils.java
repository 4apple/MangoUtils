

/**
 **2018-7-4
 **Use runOnUiThread() method to execute the UI action from a Non-UI thread.
 **/
/**
 ############################# Java Version ###################################
 private class ReceiverThread extends Thread
 {
 @Override
 public void run() {
 Activity_name.this.runOnUiThread(new Runnable () {

 @Override
 public void run() {
 mAdapter.notifyDataSetChanged();
 }
 });
 }
 }
 */
package com.example.mango.mangoutils.utils;

import android.app.Activity;

class Utils {
    private class ReceiverThread extends Thread {

        private final Activity mActivity;

        ReceiverThread(Activity activity) {
            mActivity = activity;
        }

        @Override
        public void run() {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //TODO add UI code here
                    //mAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}