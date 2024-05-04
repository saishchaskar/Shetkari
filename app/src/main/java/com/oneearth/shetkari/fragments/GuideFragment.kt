package com.oneearth.shetkari.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.oneearth.shetkari.R

class GuideFragment : Fragment() {

    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_guide, container, false)

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance()

        // Retrieve data from Firestore
        retrieveData(view)

        return view
    }

    private fun retrieveData(view: View) {
        val guideRef = firestore.collection("TomatoCultivationGuide").document("Guide")

        // Retrieve document "Varieties"
        guideRef.collection("Sections").document("Varieties").get()
            .addOnSuccessListener { document ->
                val varietiesTextView = view.findViewById<TextView>(R.id.varietiesTextView)
                val varietiesDescription = document.getString("Big Beef or Celebrity")
                varietiesTextView.text = "Varieties: $varietiesDescription"
                Log.d("GuideFragment", "Varieties Description: $varietiesDescription")
            }
            .addOnFailureListener { exception ->
                Log.e("GuideFragment", "Error retrieving Varieties: $exception")
            }

        // Retrieve document "SoilPreparation"
        guideRef.collection("Sections").document("SoilPreparation").get()
            .addOnSuccessListener { document ->
                val soilPreparationTextView = view.findViewById<TextView>(R.id.soilPreparationTextView)
                val soilPreparationDescription = document.getString("Description")
                soilPreparationTextView.text = "Soil Preparation: $soilPreparationDescription"
                Log.d("GuideFragment", "Soil Preparation Description: $soilPreparationDescription")
            }
            .addOnFailureListener { exception ->
                Log.e("GuideFragment", "Error retrieving Soil Preparation: $exception")
            }

        // Retrieve document "ClimateConditions"
        guideRef.collection("Sections").document("ClimateConditions").get()
            .addOnSuccessListener { document ->
                val climateConditionsTextView = view.findViewById<TextView>(R.id.climateConditionsTextView)
                val climateConditionsTitle = document.getString("Title")
                val climateConditionsDescription = document.getString("Description")
                climateConditionsTextView.text = "$climateConditionsTitle: $climateConditionsDescription"
                Log.d("GuideFragment", "Climate Conditions Description: $climateConditionsDescription")
            }
            .addOnFailureListener { exception ->
                Log.e("GuideFragment", "Error retrieving Climate Conditions: $exception")
            }

        // Retrieve document "PlantingGuide"
        guideRef.collection("Sections").document("PlantingGuide").get()
            .addOnSuccessListener { document ->
                val plantingGuideTextView = view.findViewById<TextView>(R.id.plantingGuideTextView)
                val plantingGuideDescription = document.getString("Description")
                plantingGuideTextView.text = "Planting Guide: $plantingGuideDescription"
                Log.d("GuideFragment", "Planting Guide Description: $plantingGuideDescription")
            }
            .addOnFailureListener { exception ->
                Log.e("GuideFragment", "Error retrieving Planting Guide: $exception")
            }

        // Retrieve document "SupportPruning"
        guideRef.collection("Sections").document("SupportPruning").get()
            .addOnSuccessListener { document ->
                val supportPruningTextView = view.findViewById<TextView>(R.id.supportPruningTextView)
                val supportPruningDescription = document.getString("Description")
                supportPruningTextView.text = "Support Pruning: $supportPruningDescription"
                Log.d("GuideFragment", "Support Pruning Description: $supportPruningDescription")
            }
            .addOnFailureListener { exception ->
                Log.e("GuideFragment", "Error retrieving Support Pruning: $exception")
            }

        // Retrieve document "Harvesting"
        guideRef.collection("Sections").document("Harvesting").get()
            .addOnSuccessListener { document ->
                val harvestingTextView = view.findViewById<TextView>(R.id.harvestingTextView)
                val harvestingDescription = document.getString("Description")
                harvestingTextView.text = "Harvesting: $harvestingDescription"
                Log.d("GuideFragment", "Harvesting Description: $harvestingDescription")
            }
            .addOnFailureListener { exception ->
                Log.e("GuideFragment", "Error retrieving Harvesting: $exception")
            }
    }




}
