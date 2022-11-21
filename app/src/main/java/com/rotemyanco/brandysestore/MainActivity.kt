package com.rotemyanco.brandysestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

	private lateinit var navController: NavController
	private lateinit var appBarConfiguration: AppBarConfiguration


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		supportActionBar?.hide()
		setContentView(R.layout.activity_main)

		val navView: BottomNavigationView = findViewById(R.id.nav_view)
		val navHostFragment =
			supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
		navController = navHostFragment.findNavController()

		navView.setupWithNavController(navController)

		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		appBarConfiguration = AppBarConfiguration(
			setOf(
				R.id.navigation_home,
				R.id.navigation_dashboard,
				R.id.navigation_notifications
			)
		)
		setupActionBarWithNavController(navController, appBarConfiguration)
	}

	override fun onSupportNavigateUp(): Boolean {
		return navController.navigateUp(appBarConfiguration)
	}
}


//class Box<T>(t: T) {
//    var value = t
//}
//
//
//class Box1(
//    val _id: Int,
//    t: Box<Category>
//) {
//    private val id: Int
//        get() {
//            TODO()
//        }
//}
//
//
//
//class Box2(
//    val _id: Int,
//    t: Box<BaseProduct>
//) {
//    private val id: Int
//        get() {
//            TODO()
//        }
//}
//
//
//fun <T : Comparable<T>?> compare(t1: T, t2: T): Int {
//    return t1!!.compareTo(t2)
//}