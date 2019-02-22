package com.noemi.android.queens.ui

import android.app.DatePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*
import com.noemi.android.queens.R
import com.noemi.android.queens.adapter.MenuPageTransformer
import com.noemi.android.queens.adapter.MenuAdapter
import com.noemi.android.queens.model.Course
import com.noemi.android.queens.model.CourseType
import com.noemi.android.queens.model.Meal
import com.noemi.android.queens.model.Menu
import com.noemi.android.queens.room.OrderDataBase
import kotlinx.android.synthetic.main.toolbar.*
import java.text.SimpleDateFormat
import java.util.*

class MenuActivity : AppCompatActivity() {

    private var dateLong: Long = 0
    private var dateChild: String = ""
    internal val calendarDate = Calendar.getInstance()

    private var editDate: EditText? = null
    private var sup1: EditText? = null
    private var pretSup1: EditText? = null
    private var sup2: EditText? = null
    private var pretSup2: EditText? = null
    private var sup3: EditText? = null
    private var pretSup3: EditText? = null
    private var fel1: EditText? = null
    private var pretFel1: EditText? = null
    private var fel2: EditText? = null
    private var pretFel2: EditText? = null
    private var fel3: EditText? = null
    private var pretFel3: EditText? = null
    private var garn1: EditText? = null
    private var pretGarn1: EditText? = null
    private var garn2: EditText? = null
    private var pretGarn2: EditText? = null

    private var database: DatabaseReference? = null
    private var childEventListener: ChildEventListener? = null

    private var viewPager: ViewPager? = null
    private var tabLayout: TabLayout? = null
    private var menu: Menu? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar?.setCustomView(R.layout.toolbar)

        val myDatabase = OrderDataBase.getOrderDataBase(this)

        database = FirebaseDatabase.getInstance().reference.child("queens_menu")
        val menuList: MutableList<Menu> = mutableListOf()

        viewPager = findViewById(R.id.view_pager)
        viewPager!!.setPageTransformer(true, MenuPageTransformer())

        val menuAdapter = MenuAdapter(this, menuList)
        viewPager!!.adapter = menuAdapter

        tabLayout = findViewById(R.id.tab_layout)
        tabLayout!!.setupWithViewPager(viewPager)

        back_arrow.setOnClickListener{
            val intent = Intent(this, LaunchActivity::class.java)
            startActivity(intent)
        }

        add_menu.setOnClickListener{
            addMenu()
        }


        childEventListener = object :ChildEventListener{
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                menu = dataSnapshot.getValue(Menu::class.java)
                menuList.add(menu!!)
                menuAdapter.notifyDataSetChanged()
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}

            override fun onCancelled(databaseError: DatabaseError) {}
        }

        database!!.addChildEventListener(childEventListener!!)
    }

    override fun onStop() {
        super.onStop()
        if(childEventListener != null){
            database!!.removeEventListener(childEventListener!!)
        }
    }

    fun addMenu(){

        val dialog = AlertDialog.Builder(this).create()
        val layout = layoutInflater.inflate(R.layout.add_menu, null)

        editDate = layout.findViewById(R.id.edit_date)
        editDate!!.setOnClickListener{

            val date = DatePickerDialog.OnDateSetListener { view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                calendarDate.set(Calendar.YEAR, year)
                calendarDate.set(Calendar.MONTH, monthOfYear)
                calendarDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val dateFormat = SimpleDateFormat("EEEE, d LLL", Locale.forLanguageTag("ro-RO"))
                val time = dateFormat.format(calendarDate.timeInMillis)

                editDate!!.setText(time.substring(0, time.length-1).toUpperCase())
                dateLong = calendarDate.timeInMillis
                dateChild = dateLong.toString()
                Log.d("Menu Activity", dateChild)

            }

            DatePickerDialog(this@MenuActivity, date, calendarDate.get(Calendar.YEAR),
                    calendarDate.get(Calendar.MONTH), calendarDate.get(Calendar.DAY_OF_MONTH)).show()
        }

        sup1 = layout.findViewById<EditText>(R.id.supa_1)
        pretSup1 = layout.findViewById<EditText>(R.id.supa_pret_1)

        sup2 = layout.findViewById<EditText>(R.id.supa_2)
        pretSup2 = layout.findViewById<EditText>(R.id.supa_pret_2)

        sup3 = layout.findViewById<EditText>(R.id.supa_3)
        pretSup3 = layout.findViewById<EditText>(R.id.supa_pret_3)

        fel1 = layout.findViewById<EditText>(R.id.felul_doi_1)
        pretFel1 = layout.findViewById<EditText>(R.id.felul_doi_pret_1)

        fel2 = layout.findViewById<EditText>(R.id.felul_doi_2)
        pretFel2 = layout.findViewById<EditText>(R.id.felul_doi_pret_2)

        fel3 = layout.findViewById<EditText>(R.id.felul_doi_3)
        pretFel3 = layout.findViewById<EditText>(R.id.felul_doi_pret_3)

        garn1 = layout.findViewById<EditText>(R.id.garnitura_1)
        pretGarn1 = layout.findViewById<EditText>(R.id.garnitura_pret_1)

        garn2 = layout.findViewById<EditText>(R.id.garnitura_2)
        pretGarn2= layout.findViewById<EditText>(R.id.garnitura_pret_2)

        val button_ok = layout.findViewById<Button>(R.id.yes)
        button_ok.setOnClickListener{
            val Supa1 = sup1!!.text.toString().trim()
            val pretSupa1 = pretSup1!!.text.toString().trim()

            val Supa2 = sup2!!.text.toString().trim()
            val pretSupa2 = pretSup2!!.text.toString().trim()

            val Supa3 = sup3!!.text.toString().trim()
            val pretSupa3 = pretSup3!!.text.toString().trim()

            val Fel1 = fel1!!.text.toString().trim()
            val pretFel1 = pretFel1!!.text.toString().trim()

            val Fel2 = fel2!!.text.toString().trim()
            val pretFel2 = pretFel2!!.text.toString().trim()

            val Fel3 = fel3!!.text.toString().trim()
            val pretFel3 = pretFel3!!.text.toString().trim()

            val Garnit1 = garn1!!.text.toString().trim()
            val pretGarn1 = pretGarn1!!.text.toString().trim()

            val Garnit2 = garn2!!.text.toString().trim()
            val pretGarn2 = pretGarn2!!.text.toString().trim()

            val course1 = Course(0, CourseType.Felul_1, Meal(Supa1, pretSupa1.toFloat()))
            val course2 = Course(1, CourseType.Felul_1, Meal(Supa2, pretSupa2.toFloat()))
            val course3 = Course(2, CourseType.Felul_1, Meal(Supa3, pretSupa3.toFloat()))
            val course4 = Course(3, CourseType.Felul_2, Meal(Fel1, pretFel1.toFloat()))
            val course5 = Course(4, CourseType.Felul_2, Meal(Fel2, pretFel2.toFloat()))
            val course6 = Course(5, CourseType.Felul_2, Meal(Fel3, pretFel3.toFloat()))
            val course7 = Course(6, CourseType.Garnituri, Meal(Garnit1, pretGarn1.toFloat()))
            val course8 = Course(7, CourseType.Garnituri, Meal(Garnit2, pretGarn2.toFloat()))

            val listOfCourse: MutableList<Course> = mutableListOf(course1, course2, course3, course4, course5, course6, course7, course8)

            val menu = Menu(listOfCourse, dateLong)

            database!!.child(dateChild).setValue(menu)

            dialog.dismiss()

            Toast.makeText(this, "Menu was added to database", Toast.LENGTH_LONG).show()

        }


        val button_no = layout.findViewById<Button>(R.id.no)
        button_no.setOnClickListener{
            dialog.dismiss()
        }

        dialog.setView(layout)
        dialog.show()

    }
}
