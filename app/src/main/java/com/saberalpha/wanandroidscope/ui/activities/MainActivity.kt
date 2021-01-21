package com.saberalpha.wanandroidscope.ui.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.saberalpha.mvvmlib.base.BaseActivity
import com.saberalpha.wanandroidscope.BR
import com.saberalpha.wanandroidscope.R
import com.saberalpha.wanandroidscope.common.Constant
import com.saberalpha.wanandroidscope.common.Constant.HOME
import com.saberalpha.wanandroidscope.databinding.ActivityMainBinding
import com.saberalpha.wanandroidscope.ui.activities.search.SearchActivity
import com.saberalpha.wanandroidscope.ui.fragments.ArticleListFragment
import com.saberalpha.wanandroidscope.ui.fragments.NavigationFragment
import com.saberalpha.wanandroidscope.ui.fragments.ProjectFragment
import com.saberalpha.wanandroidscope.ui.fragments.PublicCodeFragment
import com.saberalpha.wanandroidscope.ui.viewmodel.MainViewModel
import com.saberalpha.wanandroidscope.utils.attachPopup
import com.saberalpha.wanandroidscope.utils.createSortReposPopup
import com.saberalpha.wanandroidscope.utils.createSortUsesPopup
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?): Int = R.layout.activity_main

    override fun initVariableId(): Int = BR.viewModel

    private lateinit var mCurrentFragment: Fragment

    private val homeFragment:ArticleListFragment by lazy { ArticleListFragment.newInstance(Constant.HOME) }

    private val publicFragment:PublicCodeFragment by lazy { PublicCodeFragment() }

    private val systemFragment:ArticleListFragment by lazy { ArticleListFragment() }

    private val navigationFragment:NavigationFragment by lazy { NavigationFragment() }

    private val projectFragment: ProjectFragment by lazy { ProjectFragment() }

    override fun initData() {
        super.initData()
        initToolbar()
        initDrawerLayout()
        initFabButton()
        initBottomNavigationBar()
        initDefaultFragment()
    }

    private fun initToolbar() {
        changeContentLoadingView(binding.rlMain)
        setToolbar("主页",true){
            binding.drawerContainer.openDrawer(binding.navigation)
        }
    }

    private fun initDrawerLayout() {
        binding.navigation.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_menu_collect -> {

                }
                R.id.nav_menu_logout -> {

                }
                R.id.nav_about -> {

                }
            }
            binding.drawerContainer.closeDrawers()
            true
        }
    }

    private fun initFabButton() {
        binding.bnbBar.setFab(binding.fab)
        binding.fab.setOnClickListener {
            startActivity<SearchActivity>()
        }
    }

    private fun initBottomNavigationBar() {
        binding.bnbBar.setMode(BottomNavigationBar.MODE_DEFAULT)
            .setActiveColor(R.color.colorPrimaryDark)
            .addItem(BottomNavigationItem(R.mipmap.navigation_home,getString(R.string.navigation_home)))
            .addItem(BottomNavigationItem(R.mipmap.navigation_wechat,getString(R.string.navigation_wechat)))
//            .addItem(BottomNavigationItem(R.mipmap.navigation_system,getString(R.string.navigation_system)))
//            .addItem(BottomNavigationItem(R.mipmap.navigation_navigation,getString(R.string.navigation_navigation)))
//            .addItem(BottomNavigationItem(R.mipmap.nagivation_project,getString(R.string.navigation_project)))
            .setBarBackgroundColor(R.color.white)
            .setFirstSelectedPosition(HOME)
            .initialise()

        binding.bnbBar.setTabSelectedListener(object :BottomNavigationBar.OnTabSelectedListener{

            override fun onTabUnselected(position: Int) {}

            override fun onTabReselected(position: Int) {}

            override fun onTabSelected(position: Int) {
                switchFragment(position)
            }

        })
    }

    private fun switchFragment(position: Int) {
        when(position){
            Constant.HOME -> {
                setToolbarTitle(getString(R.string.navigation_home))
                changeFragment(homeFragment)
            }

            Constant.WE_CHAT ->{
                setToolbarTitle(getString(R.string.navigation_wechat))
                changeFragment(publicFragment)
            }

//            Constant.NAGIVATION ->{
//                setToolbarTitle(getString(R.string.navigation_navigation))
//                changeFragment(navigationFragment)
//            }
//
//            Constant.SYSTEM ->{
//                setToolbarTitle(getString(R.string.navigation_system))
//                changeFragment(systemFragment)
//            }
//
//            Constant.PROJECT ->{
//                setToolbarTitle(getString(R.string.navigation_project))
//                changeFragment(projectFragment)
//            }

            else -> {

            }
        }
    }

    private fun changeFragment(selectFragment: Fragment) {
        if (mCurrentFragment != selectFragment) {
            val transaction = supportFragmentManager.beginTransaction()
            if (selectFragment.isAdded)
                transaction.hide(mCurrentFragment).show(selectFragment)
            else
                transaction.hide(mCurrentFragment).add(R.id.fl_content, selectFragment)
            transaction.commit()
            mCurrentFragment = selectFragment
        }
    }

    private fun initDefaultFragment() {
        mCurrentFragment = homeFragment
        supportFragmentManager.beginTransaction().add(R.id.fl_content,homeFragment).commit()
    }


}