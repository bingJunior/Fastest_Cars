package com.bingjunior.fastestcars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bingjunior.fastestcars.databinding.FragmentCarDetailsBinding

//1
class FragmentCarDetails : Fragment() {

    //2
    companion object {

        private const val CARMODEL = "model"

        fun newInstance(carModel: CarModel): FragmentCarDetails {
            val args = Bundle()
            args.putSerializable(CARMODEL, carModel)
            val fragment = FragmentCarDetails()
            fragment.arguments = args
            return fragment
        }
    }

    //3
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentCarDetailsBinding =
            FragmentCarDetailsBinding.inflate(inflater, container, false)

        val model = requireArguments().getSerializable(CARMODEL) as CarModel
        fragmentCarDetailsBinding.carModel = model
        model.text = String.format(getString(R.string.description_format), model.description, model.url)
        return fragmentCarDetailsBinding.root
    }

}
