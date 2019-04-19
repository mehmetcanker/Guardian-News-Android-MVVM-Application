package com.canker.core.extensions

import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
  this.addTextChangedListener(object : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun afterTextChanged(editable: Editable?) {
      afterTextChanged.invoke(editable.toString())
    }
  })
}

fun TabLayout.onTabSelected(onTabSelectedListener: (TabLayout.Tab) -> Unit) {
  this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
      onTabSelectedListener.invoke(tab!!)
    }

  })
}

fun ViewPager.onPageScrolled(onPageChangeListener: (Int) -> Unit) {
  this.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(
      position: Int,
      positionOffset: Float,
      positionOffsetPixels: Int
    ) {
      onPageChangeListener.invoke(position)
    }

    override fun onPageSelected(position: Int) {
    }
  })
}

fun View.clickWithDebounce(debounceTime: Long = 600L, action: () -> Unit) {
  this.setOnClickListener(object : View.OnClickListener {
    private var lastClickTime: Long = 0

    override fun onClick(v: View) {
      if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
      else action()

      lastClickTime = SystemClock.elapsedRealtime()
    }
  })
}
