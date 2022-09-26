package com.lionparcel.commonandroid.navbar.utils

import com.lionparcel.commonandroid.R

enum class CANavbarMenu(val menuRes: Int) {
    HOME(R.id.navHome),
    TRACK(R.id.navTrack),
    PAYMENT(R.id.navPayment),
    PROFILE(R.id.navAccount)
}

enum class CANavbarLongMenu(val menuRes: Int) {
    HOME(R.id.navHome),
    TRACK(R.id.navTrack),
    HELPDESK(R.id.navHelpdesk),
    PAYMENT(R.id.navPayment),
    PROFILE(R.id.navAccount)
}

enum class DANavbarMenu(val menuRes: Int) {
    HOME(R.id.navHome),
    HISTORY(R.id.navHistory),
    SYNC(R.id.navSync),
    PROFILE(R.id.navAccount)
}

enum class DANavbarLongMenu(val menuRes: Int) {
    HOME(R.id.navHome),
    HISTORY(R.id.navHistory),
    SYNC(R.id.navSync),
    EARNING(R.id.navEarning),
    PROFILE(R.id.navAccount)
}