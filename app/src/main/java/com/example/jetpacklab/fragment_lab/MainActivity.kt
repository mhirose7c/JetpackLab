package com.example.jetpacklab.fragment_lab

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.jetpacklab.R
import com.example.jetpacklab.room_lab.RoomLabActivity
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.openDrawer(GravityCompat.START)

        createDrawer()
    }

    /**
     * drawerメニューの描画
     * 遷移先で消えてしまうので、本来は以下のどちらかにすべきなのだと思う
     *
     * ・room_labをfragmentで実装する
     * ・createDrawerをActivityとは別のクラスに実装して個々のActivityから呼び出す
     *
     * */
    private fun createDrawer() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.tool_bar)
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            if (it.itemId == R.id.nav_navigation_lab) {
                drawerLayout.closeDrawers()
            } else if (it.itemId == R.id.nav_room_lab) {
                val intent = Intent(this, RoomLabActivity::class.java)
                startActivity(intent)
            }
            false
        }
    }
}
