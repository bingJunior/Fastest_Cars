package com.bingjunior.fastestcars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), CarListFragment.OnCarSelected {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, CarListFragment.newInstance(), "carList")
                .commit()
        }
    }

    override fun onCarSelected(carModel: CarModel) {
        val detailsFragment =
            FragmentCarDetails.newInstance(carModel)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, detailsFragment, "carDetails")
            .addToBackStack(null)
            .commit()
    }


}
