package com.team13.dealmymeal.ui.editmeal

import com.team13.dealmymeal.ui.home.AddMealViewModel


import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import com.team13.dealmymeal.*
import kotlinx.android.synthetic.main.fragment_addmeal.view.*
import kotlinx.android.synthetic.main.fragment_editmeal.view.*
import kotlinx.android.synthetic.main.fragment_editmeal.view.check_meat
import kotlinx.android.synthetic.main.fragment_editmeal.view.check_special
import kotlinx.android.synthetic.main.fragment_editmeal.view.check_veggie
import kotlinx.android.synthetic.main.fragment_editmeal.view.form_edit
import kotlinx.android.synthetic.main.fragment_editmeal.view.form_ratingBar
import kotlinx.android.synthetic.main.fragment_editmeal.view.form_save
import kotlinx.android.synthetic.main.fragment_meal_overview.view.*


class EditMealFragment : Fragment() {
    private val mealViewModel: MealViewModel by viewModels {
        MealViewModelFactory((activity?.application as MealApplication).repository)
    }
    private lateinit var EditMealViewModel: AddMealViewModel
    private val fragmentTag = "EditMealFragment"


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        EditMealViewModel =
                ViewModelProvider(this).get(AddMealViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_editmeal, container, false)


        val btn = root.findViewById<ImageButton>(R.id.btnmenu)
        val menu = root.findViewById<NavigationView>(R.id.nav_bar_id)
        btn.setOnClickListener()
        {
            if(menu.visibility == View.VISIBLE)
            {
                menu.visibility = View.INVISIBLE
            }
            else
            {
                menu.visibility = View.VISIBLE
            }

        }





        AppCompatActivity()
        //val view = inflater.inflate(R.layout.fragment_addmeal, container, true)


        val form_textview = root.findViewById<TextView>(R.id.form_showEntry)
        val save_button = root.findViewById<Button>(R.id.form_save)
        val form_editText = root.findViewById<EditText>(R.id.form_edit)
        val form_ratingBar = root.findViewById<RatingBar>(R.id.form_ratingBar)
        val form_checkMeat = root.findViewById<CheckBox>(R.id.check_meat)
        val form_checkVeggie = root.findViewById<CheckBox>(R.id.check_veggie)
        val form_checkSpecial = root.findViewById<CheckBox>(R.id.check_special)
        val text = form_editText.text



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val meal = arguments?.getParcelable<Meal>("Meal")
        if (meal != null) {
            view.form_edit.setText(meal.title)
            view.form_ratingBar.setRating(meal.rating?.toFloat()!!)
            val isMeat = meal.categories!!.contains("Meat")
            val isSpecial = meal.categories!!.contains("Special")
            val isVeggie = meal.categories!!.contains("Veggie")

            view.check_meat.isChecked = isMeat
            view.check_veggie.isChecked = isVeggie
            view.check_special.isChecked = isSpecial

            view.form_save.setOnClickListener {
                val type = ArrayList<String>()
                if (view.check_meat.isChecked)
                    type.add("Meat")
                if (view.check_veggie.isChecked)
                    type.add("Veggie")
                if (view.check_special.isChecked)
                    type.add("Special")

                meal.title = view.form_edit.text.toString()
                meal.rating = view.form_ratingBar.rating
                meal.categories = type

                mealViewModel.update(meal)
                activity?.supportFragmentManager?.popBackStack()
            }
        }

    }

}





