/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jimmy.navigationarchcomponent.views.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions

import com.jimmy.navigationarchcomponent.R
import kotlinx.android.synthetic.main.home_fragment.*

/**
 * Fragment used to show how to navigate to another destination
 */
class HomeFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // default navigation impl.
//        navigate_destination_button?.setOnClickListener {
//            findNavController().navigate(R.id.flow_step_one_dest)
//        }


        // add navigation transition animation options
        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        navigate_destination_button.setOnClickListener {
            findNavController().navigate(R.id.flow_step_one_dest, null, options)
        }


        /*
        use safe args to navigate in a type safe way, with or without adding arguments.
         You do this using the generated Directions classes
         */
//         usage of curly braces since we are defining the click listener lambda
        navigate_action_button.setOnClickListener {

//                Navigation.createNavigateOnClickListener(R.id.next_action, null)

            /*
            Navigation by actions has the following benefits over navigation by destination:

            You can visualize the navigation paths through your app

            Actions can contain additional associated attributes you can set,
            such as a transition animation, arguments values, and backstack behavior

            You can use the plugin safe args to navigate

             */
            val action = HomeFragmentDirections.nextAction()
            action.setFlowStepNumber(1)
            findNavController().navigate(action)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }
}
