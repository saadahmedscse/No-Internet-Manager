/*
 * Copyright 2018-2023 Saad Ahmed
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.saadahmedev.networkutil.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.saadahmedev.networkutil.NetworkUtil;
import com.saadahmedev.networkutil.utils.Constants;

public class ConnectivityReceiver extends BroadcastReceiver {

    private boolean executed = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Constants.INTENT_FILTER_ACTION.equals(intent.getAction())) {
            if (executed && NetworkUtil.isInternetAvailable(context)) {
                Toast.makeText(context, Constants.CONNECTION_RESTORED, Toast.LENGTH_SHORT).show();
                return;
            }
            if (executed && !NetworkUtil.isInternetAvailable(context)) {
                Toast.makeText(context, Constants.NO_INTERNET_AVAILABLE, Toast.LENGTH_SHORT).show();
            }

            executed = true;
        }
    }
}
