package com.bingjunior.fastestcars

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bingjunior.fastestcars.databinding.RecyclerItemCarModelBinding


class CarListFragment : Fragment() {

    private lateinit var imageResIds: IntArray
    private lateinit var names: Array<String>
    private lateinit var descriptions: Array<String>
    private lateinit var urls: Array<String>
    private lateinit var listener: OnCarSelected

    companion object {

        fun newInstance(): CarListFragment {
            return CarListFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnCarSelected) {
            listener = context
        } else {
            throw ClassCastException("$context must implement OnCarSelected.")
        }

        // Get car names and descriptions.
        val resources = context.resources
        names = resources.getStringArray(R.array.names)
        descriptions = resources.getStringArray(R.array.descriptions)
        urls = resources.getStringArray(R.array.urls)

        // Get car images.
        val typedArray = resources.obtainTypedArray(R.array.images)
        val imageCount = names.size
        imageResIds = IntArray(imageCount)
        for (i in 0 until imageCount) {
            imageResIds[i] = typedArray.getResourceId(i, 0)
        }
        typedArray.recycle()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_cars_list, container,
            false)
        val activity = activity as Context
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity, 1)
        recyclerView.adapter = CarListAdapter(activity)
        return view
    }

    internal inner class CarListAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {

        private val layoutInflater = LayoutInflater.from(context)

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val recyclerCarModelBinding =
                RecyclerItemCarModelBinding.inflate(layoutInflater, viewGroup, false)
            return ViewHolder(recyclerCarModelBinding.root, recyclerCarModelBinding)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val car = CarModel(imageResIds[position], names[position],
                descriptions[position], urls[position])
            viewHolder.setData(car)
            viewHolder.itemView.setOnClickListener { listener.onCarSelected(car) }
        }

        override fun getItemCount() = names.size
    }

    internal inner class ViewHolder constructor(itemView: View,
                                                private val recyclerItemCarListBinding:
                                                RecyclerItemCarModelBinding
    ) :
        RecyclerView.ViewHolder(itemView) {

        fun setData(carModel: CarModel) {
            recyclerItemCarListBinding.carModel = carModel
        }
    }

    interface OnCarSelected {
        fun onCarSelected(carModel: CarModel)
    }

}
