package id.paniclabs.android.mvrxplayground.feature.main

import android.os.Bundle
import com.airbnb.mvrx.BaseMvRxActivity
import id.paniclabs.android.mvrxplayground.R

class MainActivity : BaseMvRxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
