package com.lionparcel.commonandroidsample.popupbanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.popup.showCustomPopup
import com.lionparcel.commonandroid.popupbanner.LPPopupBannerDialogFragment
import com.lionparcel.commonandroidsample.R

class PopupBannerSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_banner_sample)
        showCustomPopup(
            supportFragmentManager,
            "LP_POPUP_BANNER",
            LPPopupBannerDialogFragment.newInstance(
                "https://images.unsplash.com/photo-1535332371349-a5d229f49cb5?ixlib=rb-1.2.1&w=1000&q=80",
                { }
            )
        )
    }
}