package com.example.testingintentadvanced

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
internal class MainActivityTest{

    @get:Rule
    val intentsTestRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun testCameraIntentIsBitmapSetToImageView()
    {
        val activityResult = createImageCaptureActivityResultStub()
        val expectedIntent = hasAction(MediaStore.ACTION_IMAGE_CAPTURE)
        intending(expectedIntent).respondWith(activityResult)

        onView(withId(R.id.buttonLaunchCamera)).perform(click())
        intending(expectedIntent)
    }

    private fun createImageCaptureActivityResultStub():ActivityResult
    {
        val bundle = Bundle()
        bundle.putParcelable(KEY_IMAGE_DATA ,BitmapFactory.decodeResource(intentsTestRule.activity.resources, R.drawable.ic_launcher_background))

        val resultDate = Intent()
        resultDate.putExtras(bundle)

        return ActivityResult(Activity.RESULT_OK ,resultDate)
    }
}