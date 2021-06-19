package com.arvind.loginroomapp.view.dashboard

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.arvind.loginroomapp.R
import com.arvind.loginroomapp.adapter.CustomStaffAdapter
import com.arvind.loginroomapp.databinding.FragmentDashboardBinding
import com.arvind.loginroomapp.model.LoginUser
import com.arvind.loginroomapp.utils.hide
import com.arvind.loginroomapp.utils.indianRupee
import com.arvind.loginroomapp.utils.show
import com.arvind.loginroomapp.view.base.BaseFragment
import com.arvind.loginroomapp.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.layout_total_staff.*
import kotlinx.coroutines.flow.first

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding, LoginViewModel>() {
    private lateinit var customStaffAdapter: CustomStaffAdapter
    override val viewModel: LoginViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        doinits()
        swipeToDelete()
    }

    private fun onTotalStaffSalaryLoaded(loginUser: List<LoginUser>) = with(binding) {

        val totalAmount: Double = loginUser.map { it.salary }.sum()

        tv_total_balance.text = indianRupee(totalAmount)
    }

    private fun swipeToDelete() {
        // init item touch callback for swipe action
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // get item position & delete notes
                val position = viewHolder.adapterPosition
                val userdata = customStaffAdapter.differ.currentList[position]
                val loginstaffItem = LoginUser(
                    userdata.name,
                    userdata.designationType,
                    userdata.salary,
                    "0",
                    "0",
                    "0",
                    "0",
                    userdata.createdAt,
                    userdata.id
                )
                viewModel.deleteloginstaff(loginstaffItem)
                Snackbar.make(
                    binding.root,
                    getString(R.string.success_addstaff_delete),
                    Snackbar.LENGTH_LONG
                )
                    .apply {
                        setAction("Undo") {
                            viewModel.insertstaff(
                                loginstaffItem
                            )
                        }
                        show()
                    }
            }
        }

        // attach swipe callback to rv
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvStaffdashboard)
        }
    }

    private fun setupRv() {
        customStaffAdapter = CustomStaffAdapter()
        rv_staffdashboard.apply {
            setHasFixedSize(true)
            adapter = customStaffAdapter

            viewModel.getAllloginstaff().observe(viewLifecycleOwner, { loginstaffuser ->
                customStaffAdapter.differ.submitList(loginstaffuser)
               getUI(loginstaffuser)

                onTotalStaffSalaryLoaded(loginstaffuser)

            })
        }
    }

    private fun getUI(login: List<LoginUser>) {

        if (login.isNotEmpty()) {
            emptyStateLayout.hide()
            rv_staffdashboard.show()
            layout_total_balance_view.show()

        } else {
            emptyStateLayout.show()
            rv_staffdashboard.hide()
            layout_total_balance_view.hide()

        }

    }

    private fun doinits() = with(binding) {
        btnAddNewstaff.setOnClickListener {
            val direction = DashboardFragmentDirections.actionDashboardFragmentToAddStaffFragment()
            it.findNavController().navigate(direction)
        }

        mainDashboardScrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY -> // the delay of the extension of the FAB is set for 12 items
            if (scrollY > oldScrollY + 12 && btnAddNewstaff.isExtended) {
                btnAddNewstaff.shrink()
            }

            if (scrollY < oldScrollY - 12 && !btnAddNewstaff.isExtended) {
                btnAddNewstaff.extend()
            }

            if (scrollY == 0) {
                btnAddNewstaff.extend()
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.main_menu, menu)
        lifecycleScope.launchWhenStarted {
            val isChecked = viewModel.getUIMode.first()
            val uiMode = menu.findItem(R.id.action_night_mode)
            uiMode.isChecked = isChecked
            setUIMode(uiMode, isChecked)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        return when (item.itemId) {
            R.id.action_night_mode -> {
                item.isChecked = !item.isChecked
                setUIMode(item, item.isChecked)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUIMode(item: MenuItem, isChecked: Boolean) {
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            viewModel.saveToDataStore(true)
            item.setIcon(R.drawable.ic_night)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            viewModel.saveToDataStore(false)
            item.setIcon(R.drawable.ic_day)
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDashboardBinding.inflate(inflater, container, false)
}