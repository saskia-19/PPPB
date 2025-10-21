package com.example.mycourse

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.Fragment //ini ngaruh banget sih ga tau kenapa

//kelas untuk mengatur antara Tab Layout dan ViewPager
class SectionPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    //fungsi yang menentukan
    //tab 1 menuju mana
    //tab 2 menuju mana
    override fun createFragment(position: Int): androidx.fragment.app.Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = MateriSatuFragment()
            1 -> fragment = MateriDuaFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        //karena kita punya 2 tabs
        //maka kita return 2
        return 2
    }

}