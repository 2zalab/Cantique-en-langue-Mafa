package com.sintel.cantiquesenmafa


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

 class PageAdapter(fm:FragmentManager): FragmentPagerAdapter(fm) {
  override fun getItem(position: Int): Fragment {
  return when(position){
   0->{
    CantiquesFragment()
   }
   1->{
    FavorisFragment()
   }
   else ->{
    return AproposFragment()
   }
  }
  }

  override fun getCount(): Int {
  return 3
  }

  override fun getPageTitle(position: Int): CharSequence? {
   return when(position){
    0->"Cantiques"
    1->"Favoris"
    else ->{
     return "A Propos"
    }
   }
  }

 }