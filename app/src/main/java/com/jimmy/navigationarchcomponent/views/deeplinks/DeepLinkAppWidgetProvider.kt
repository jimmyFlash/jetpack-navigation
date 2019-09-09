/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jimmy.navigationarchcomponent.views.deeplinks

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.os.Bundle
import android.widget.RemoteViews
import androidx.navigation.NavDeepLinkBuilder
import com.jimmy.navigationarchcomponent.R

/**
 * App Widget that deep links you to the [DeepLinkFragment].
 *
 *  Deep links are a way to jump into the middle of your app's navigation,
 *  whether that's from an actual URL link or a pending intent from a notification.
 *  One benefit of using the navigation library to handle deep links is that it ensures users start
 *  on the right destination with the appropriate back stack from other entry points such as app widgets,
 *  notifications, or web links (covered in the next step).
 *
 *  Navigation provides a NavDeepLinkBuilder class to construct a PendingIntent that will take the user to a specific destination.
 */
class DeepLinkAppWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        val remoteViews = RemoteViews(
            context.packageName,
            R.layout.deep_link_appwidget
        )

        val args = Bundle()
        args.putString("myarg", "From Widget")
        // construct and set a PendingIntent using DeepLinkBuilder
        /*
        By default NavDeepLinkBuilder will start your launcher Activity. You can override this behavior by passing in an
        activity as the context or set an explicit activity class via setComponentName()
         */
        val pendingIntent = NavDeepLinkBuilder(context)
                .setGraph(R.navigation.mobile_navigation)//setGraph includes the navigation graph.
                .setDestination(R.id.deeplink_dest) // setDestination specifies where the link goes to.
                .setArguments(args) //setArguments includes any arguments you want to pass into your deep link.
                .createPendingIntent()

        remoteViews.setOnClickPendingIntent(R.id.deep_link_button, pendingIntent)

        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews)
    }
}
