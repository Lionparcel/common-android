package com.lionparcel.commonandroidsample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.multidex.MultiDex
import com.lionparcel.commonandroidsample.accordion.AccordionComponentSampleActivity
import com.lionparcel.commonandroidsample.alert.AlertComponentSampleActivity
import com.lionparcel.commonandroidsample.badge.BadgeComponentSampleActivity
import com.lionparcel.commonandroidsample.button.ButtonSampleActivity
import com.lionparcel.commonandroidsample.emptystate.EmptyStateSampleActivity
import com.lionparcel.commonandroidsample.card.CardSampleActivity
import com.lionparcel.commonandroidsample.divider.DividerSampleActivity
import com.lionparcel.commonandroidsample.form.FormComponentSampleActivity
import com.lionparcel.commonandroidsample.header.HeaderSampleActivity
import com.lionparcel.commonandroidsample.label.LabelSampleActivity
import com.lionparcel.commonandroidsample.listing.ListingSampleActivity
import com.lionparcel.commonandroidsample.loading.LoadingComponentSampleActivity
import com.lionparcel.commonandroidsample.modal.ModalComponentSampleActivity
import com.lionparcel.commonandroidsample.popup.PopupComponentSampleActivity
import com.lionparcel.commonandroidsample.popupbanner.PopupBannerSampleActivity
import com.lionparcel.commonandroidsample.progressbar.ProgressBarSampleActivity
import com.lionparcel.commonandroidsample.selectioncontrol.SelectionControlComponentSampleActivity
import com.lionparcel.commonandroidsample.tag.TagComponentSampleActivity
import com.lionparcel.commonandroidsample.tab.TabLayoutSampleActivity
import com.lionparcel.commonandroidsample.stepper.StepperSampleActivity
import com.lionparcel.commonandroidsample.toastsnackbar.SnackbarSampleActivity
import com.lionparcel.commonandroidsample.toastsnackbar.ToastSampleActivity
import com.lionparcel.commonandroidsample.walktrough.WalkThroughSampleActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_to_formcomponentsampleactivity).setOnClickListener {
            startActivity(Intent(this, FormComponentSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_loadingcomponentsampleactivity).setOnClickListener {
            startActivity(Intent(this, LoadingComponentSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_modalComponentSampleactivity).setOnClickListener {
            startActivity(Intent(this, ModalComponentSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_walkthroughsampleactivity).setOnClickListener {
            startActivity(Intent(this, WalkThroughSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_toastSampleActivity).setOnClickListener {
            startActivity(Intent(this, ToastSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_snackbarSampleActivity).setOnClickListener {
            startActivity(Intent(this, SnackbarSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_buttonSampleActivity).setOnClickListener {
            startActivity(Intent(this, ButtonSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_popupSampleActivity).setOnClickListener {
            startActivity(Intent(this, PopupComponentSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_headerSampleActivity).setOnClickListener {
            startActivity(Intent(this, HeaderSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_emptystateSampleActivity).setOnClickListener {
            startActivity(Intent(this, EmptyStateSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_cardSampleActivity).setOnClickListener {
            startActivity(Intent(this, CardSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_dividerSampleActivity).setOnClickListener {
            startActivity(Intent(this, DividerSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_labelSampleActivity).setOnClickListener {
            startActivity(Intent(this, LabelSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_tagSampleActivity).setOnClickListener {
            startActivity(Intent(this, TagComponentSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_listingSampleActivity).setOnClickListener {
            startActivity(Intent(this, ListingSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_tabSampleActivity).setOnClickListener {
            startActivity(Intent(this, TabLayoutSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_stepperSampleActivity).setOnClickListener {
            startActivity(Intent(this, StepperSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_progressBarSampleActivity).setOnClickListener {
            startActivity(Intent(this, ProgressBarSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_alertSampleActivity).setOnClickListener {
            startActivity(Intent(this, AlertComponentSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_selectionControlSampleActivity).setOnClickListener {
            startActivity(Intent(this, SelectionControlComponentSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_accordionSampleActivity).setOnClickListener {
            startActivity(Intent(this, AccordionComponentSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_badgeSampleActivity).setOnClickListener {
            startActivity(Intent(this, BadgeComponentSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_popupBannerSampleActivity).setOnClickListener {
            startActivity(Intent(this, PopupBannerSampleActivity::class.java))
        }

    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        MultiDex.install(this)
    }
}
