package com.timeslot.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget._
import macroid._
import macroid.FullDsl._

// include implicit contexts
class MainActivity extends Activity with Contexts[Activity] {

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    val intent = new Intent(Intent.ACTION_MAIN)
    intent.setType("*/*")

    // the layout goes here
    setContentView {
      getUi {
        l[LinearLayout](
          w[Button],
          w[TextView]
        )
      }
    }
  }
}