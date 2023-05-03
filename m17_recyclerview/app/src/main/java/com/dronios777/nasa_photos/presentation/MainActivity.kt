package com.dronios777.nasa_photos.presentation


import android.app.DatePickerDialog
import android.app.PendingIntent.getActivity
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.dronios777.nasa_photos.R
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private val calendar = Calendar.getInstance()
    private val pattern = SimpleDateFormat("yyyy-MM-dd", Locale.US)


    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // установка изменения в ToolBar label фрагмента
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        /*  if (savedInstanceState == null) {
              supportFragmentManager.beginTransaction()
                  .replace(R.id.container, MainFragment.newInstance())
                  .commitNow()
          }*/
    }

    override fun onSupportNavigateUp(): Boolean {
        val controller = findNavController(R.id.fragmentContainerView)
        return controller.navigateUp() || super.onSupportNavigateUp()
    }

    /*
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (item.itemId == R.id.dateMenu) {
                //нажатие на кнопку даты в ToolBar
                // поиск по выбранной дате не реализовал...

               DatePickerDialog(
                    this,
                    this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            return true
        }
    */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayofMonth: Int) {
        calendar.set(year, month, dayofMonth)
        displayFormat(calendar.timeInMillis)

    }

    private fun displayFormat(z: Long) {
        // Toast.makeText(applicationContext, pattern.format(z), Toast.LENGTH_SHORT).show()
    }

}