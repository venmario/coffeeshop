package com.example.s160419101_coffeshop_tycoon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.simulation_list.view.*

class SimulationAdapter(val context: Context): RecyclerView.Adapter<SimulationAdapter.SimulationViewHolder>() {
    class SimulationViewHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimulationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.simulation_list,parent,false)
        return SimulationViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimulationViewHolder, position: Int) {
        val simulation = GlobalData.simulation[position]
        with(holder.view){
            textViewSimulation.text = simulation
        }
    }

    override fun getItemCount(): Int =GlobalData.simulation.size


}