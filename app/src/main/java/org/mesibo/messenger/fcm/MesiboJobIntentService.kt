/** Copyright (c) 2019 Mesibo
 * https://mesibo.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the terms and condition mentioned on https://mesibo.com
 * as well as following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions, the following disclaimer and links to documentation and source code
 * repository.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or other
 * materials provided with the distribution.
 *
 * Neither the name of Mesibo nor the names of its contributors may be used to endorse
 * or promote products derived from this software without specific prior written
 * permission.
 *
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * Documentation
 * https://mesibo.com/documentation/
 *
 * Source Code Repository
 * https://github.com/mesibo/messengerKotlin-app-android
 *
 */

package org.mesibo.messenger.fcm

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.support.v4.app.JobIntentService
import android.widget.Toast

/**
 * Require WAKE_LOCK persmission for API level earlier than Android O
 */

class MesiboJobIntentService : JobIntentService() {

    internal val mHandler = Handler()

    override fun onHandleWork(intent: Intent) {
        try {
            MesiboRegistrationIntentService.sendMessageToListener(intent.extras, true)
        } catch (e: Exception) {

        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    // Helper for showing tests
    internal fun toast(text: CharSequence) {
        mHandler.post { Toast.makeText(this@MesiboJobIntentService, text, Toast.LENGTH_SHORT).show() }
    }

    companion object {
        internal val JOB_ID = 1000

        /**
         * Convenience method for enqueuing work in to this service.
         */
        internal fun enqueueWork(context: Context, work: Intent) {
            try {
                JobIntentService.enqueueWork(context, MesiboJobIntentService::class.java, JOB_ID, work)
            } catch (e: Exception) {

            }

        }
    }
}
